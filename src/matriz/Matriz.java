/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriz;

import automato.AutomatoFinito;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Matriz {

    private final List<String> terminais;
    private final List<String> estados;
    private final HashMap<String, List<String>> transicoes;
    private String[][] matriz;

    public Matriz(AutomatoFinito automato) {
        this.terminais = automato.getTerminais();
        this.estados = automato.getEstados();
        this.transicoes = automato.getTransicoes();
        this.matriz = new String[estados.size()+10][terminais.size()];
    }

    public Matriz construirMatriz() {
        fillMatriz(matriz);
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

    public void substituirTransicao(String estado, String terminal, String novo) {
        matriz[estados.indexOf(estado)][terminais.indexOf(terminal)] = novo;
    }

    public void addTransicao(String estado, String terminal, String estado1) {
        String transicao = matriz[estados.indexOf(estado)][terminais.indexOf(terminal)];

        if (estado1.equals("")) {
            return;
        }

        if ("".equals(transicao)) {
            matriz[estados.indexOf(estado)][terminais.indexOf(terminal)] = estado1;
        } else {
            matriz[estados.indexOf(estado)][terminais.indexOf(terminal)] = transicao + "," + estado1;
        }

    }

    public void adicionarNovoEstado(String estado) {
        if (matriz.length - 1 <= estados.size()) {
            matriz = novaMatriz();
        }
        this.estados.add(estado);
    }

    public String getTransicao(String estado, String terminal) {
        if (estado.equals("") || !terminais.contains(terminal)) {
            return "";
        }
        return matriz[estados.indexOf(estado)][terminais.indexOf(terminal)];
    }

    public boolean containsEstado(String estado) {
        return this.estados.contains(estado);
    }

    public boolean excluirEstado(String estado) {
        int linha = estados.indexOf(estado);

        if (linha == -1) {
            return false;
        }

        for (int i = linha; i < estados.size(); i++) {
            for (String terminal : terminais) {
                int coluna = terminais.indexOf(terminal);
                matriz[i][coluna] = matriz[i + 1][coluna];
            }
        }

        estados.remove(estado);

        return true;
    }
    
    private void fillMatriz(String[][] matriz){
        for(String[] linha : matriz){
            Arrays.fill(linha, "");
        }
    }
    
    private String[][] novaMatriz(){
        String[][] novaMatriz = new String[matriz.length+10][terminais.size()];
        
        fillMatriz(novaMatriz);
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j<terminais.size(); j++){
                novaMatriz[i][j] = matriz[i][j];
            }
        }
        return novaMatriz;
    }
}
