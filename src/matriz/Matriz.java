/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriz;

import automato.AutomatoFinito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Matriz {

    private final List<String> terminais;
    private final List<String> nTerminais;
    private final HashMap<String, List<String>> transicoes;
    private String[][] matriz;

    public Matriz(AutomatoFinito automato) {
        this.terminais = new ArrayList<>(automato.getTerminais());
        this.nTerminais = new ArrayList<>(automato.getEstados());
        this.transicoes = automato.getTransicoes();
        this.matriz = new String[nTerminais.size()+10][terminais.size()];
    }

    public Matriz construirMatriz() {
        for(String[] linha : matriz)
            Arrays.fill(linha, "");
        
        transicoes.forEach((k, v) -> {
            for (String producao : v) {
                if (producao.length() > 1) {
                    String estado = producao.substring(1);
                    String terminal = producao.substring(0, 1);
                    addTransicao(k, terminal, estado);
                }
            }
        });
        return this;
    }
    
    public void substituirTransicao(String estado, String terminal, String novo){
        matriz[nTerminais.indexOf(estado)][terminais.indexOf(terminal)] = novo;
    }
    
    public void addTransicao(String estado, String terminal, String estado1){
        String transicao = matriz[nTerminais.indexOf(estado)][terminais.indexOf(terminal)];
        if(transicao == null)
            matriz[nTerminais.indexOf(estado)][terminais.indexOf(terminal)] = estado1;
        else
            matriz[nTerminais.indexOf(estado)][terminais.indexOf(terminal)] = transicao + "," + estado1;       
    }
    
    public void adicionarNovoEstado(String estado){
        nTerminais.add(estado);
        if(matriz.length==nTerminais.size()-1){
            matriz = Arrays.copyOf(matriz, nTerminais.size()+10);
        }
    }
    
    public String getTransicao(String estado, String terminal){
        return matriz[nTerminais.indexOf(estado)][terminais.indexOf(terminal)];
    }
    
    public boolean containsEstado(String estado){
        return nTerminais.contains(estado);
    }
    
    public void imprimir(){
        for(int i = 0; i < nTerminais.size(); i++){
            for(int j = 0; j < terminais.size(); j++){
                System.out.println("("+nTerminais.get(i)
                        +","+terminais.get(j)+")"
                                +"->"+matriz[i][j]);
            }
        }
    }
}
