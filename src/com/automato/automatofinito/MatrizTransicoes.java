/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.automatofinito;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Francisco
 */
public final class MatrizTransicoes {
    private final HashMap<String, Integer> indexNTerminais = new HashMap<>();
    private final HashMap<String, Integer> indexTerminais = new HashMap<>();
    private final String[][] matriz;
    
    public MatrizTransicoes(Set<String> terminais, Set<String> nTerminais, HashMap<String, List<String>> transicoes){
        matriz = new String[nTerminais.size()][terminais.size()];
        setPosicaoMatriz(terminais, indexTerminais);
        setPosicaoMatriz(nTerminais, indexNTerminais);
        construirMatriz(transicoes);
    }
    
    public void construirMatriz(HashMap<String, List<String>> transicoes){
        transicoes.forEach((k,v)->{
            for(String producao : v){
                if(producao.length() > 1)
                    matriz[indexNTerminais.get(k)][indexTerminais.get(producao.substring(0, 1))] = producao.substring(1);
            }
        });
    }
    
    public String getTransicao(String nTerminal, String terminal){
        return matriz[indexNTerminais.get(nTerminal)][indexTerminais.get(terminal)];
    }
     
    public void setPosicaoMatriz(Set<String> aux, HashMap<String, Integer> index){
        int i = 0;
        for(String s : aux){
            index.put(s, i++);
        }
    }
}
