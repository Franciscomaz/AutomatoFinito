/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.automatofinito;

import com.automato.gramatica.Gramatica;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco
 */
public class AutomatoFinito {

    private final Gramatica gramatica;

    public AutomatoFinito(Gramatica gramatica) {
        this.gramatica = gramatica;
    }

    public DefaultTableModel getTabela() {
        List<String> titulo = new ArrayList<>();
        
        titulo.addAll(gramatica.getTerminais());
        titulo.add(0, "Não terminais");

        return new javax.swing.table.DefaultTableModel(
                getTabelaMatriz(),
                titulo.toArray(new String[titulo.size()])
        );
    }

    private String[][] getTabelaMatriz() {
        List<String> terminais = new ArrayList<>(gramatica.getTerminais());
        List<String> nTerminais = new ArrayList<>(gramatica.getNaoTerminais());
        List<String> linhas = gramatica.getLinhas();

        String[][] matriz = new String[nTerminais.size()][terminais.size() + 1];
        
        for (int i = 0; i < nTerminais.size(); i++) {
            matriz[i][0] = verificaEstado(nTerminais.get(i));
            for (int j = 1; j < terminais.size() + 1; j++) {
                for (String producao : gramatica.getProducoes(linhas.get(i))) {                   
                    if (producao.length() > 1 && producao.contains(terminais.get(j - 1))) {
                        if(matriz[i][j]==null)
                            matriz[i][j] = "";
                        if(matriz[i][j].length() > 0)
                            matriz[i][j]+=",";                    
                        matriz[i][j] += producao.substring(1);
                    }
                }
            }
        }
        return matriz;
    }
    
    private String verificaEstado(String nTerminal) {
        String estado = "";

        if (gramatica.getInicial().equals(nTerminal)) {
            estado += "->";
        }

        if (getFinais().contains(nTerminal)) {
            estado += "*";
        }
        
        return estado + nTerminal;
    }

    public Set<String> getFinais() {
        HashSet<String> pegaFinal = new HashSet<>();
        
        for (String linha : gramatica.getLinhas()) {
            //Converte a linha em char
            List<String> producoes = gramatica.getProducoes(linha);
            //for criado para fazer toda a operacao com a linha
            for (String producao : producoes) {
                //Checa se a letra na posicao j e minuscula E checa se na posicao j+1 e maiuscula
                if (producao.length() < 2) {
                    if (linha.contains(producao)) {
                        for (String verificaFinal : producoes) {
                            if(producao.equals("&"))
                                pegaFinal.add(linha.substring(0,1));
                            if (verificaFinal.contains(producao) && verificaFinal.length() > 1) 
                                pegaFinal.add(verificaFinal.substring(1));                            
                        }
                    }
                }
            }
        }
        return pegaFinal;
    }

}
