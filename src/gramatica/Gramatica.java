/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramatica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Francisco
 */
public class Gramatica {

    private final String gramatica;

    public Gramatica(String gramatica) {
        this.gramatica = gramatica;
    }

    //retorna o inicial
    public String getInicial() {
        return gramatica.substring(0, 1);
    }

    //retorn os terminais
    public Set<String> getTerminais() {
        Set<String> terminais = new HashSet<>();

        for (String s : getLinhas()) {
            for (String producao : getProducoes(s)) {
                if(!producao.equals("&"))
                    terminais.add(producao.substring(0, 1));
            }
        }

        return terminais;
    }
    //retorna os não terminais

    public Set<String> getNaoTerminais() {
        Set<String> nTerminais = new HashSet<>();

        for (String s : getLinhas()) {
            nTerminais.add(s.substring(0, 1));

        }

        return nTerminais;
    }

    public HashMap<String, List<String>> getTransicoes() {
        HashMap<String, List<String>> transicoes = new HashMap<>();

        for (String linha : getLinhas()) {
            transicoes.put(new String(linha.substring(0, 1)), getProducoes(linha));
        }

        return transicoes;
    }

    //faz um array de strings à cada \n e depois passa para uma lista
    public List<String> getLinhas() {
        return Arrays.asList(gramatica.split("\n"));
    }

    //retorna as produções
    public List<String> getProducoes(String linha) {
        return Arrays.asList(linha.substring(2).split("[|]"));
    }

}
