/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import matriz.Matriz;

/**
 *
 * @author Francisco
 */
public abstract class AutomatoFinito {

    protected final Matriz matrizTransicoes;
    protected final List<String> estados;
    protected final HashMap<String, List<String>> transicoes;
    protected final List<String> terminais;
    protected final String inicial;
    
    public AutomatoFinito(HashMap<String, List<String>>transicoes, 
        Set<String> estados, Set<String> terminais, String inicial) {
        this.estados = new ArrayList<>(estados);
        this.inicial = inicial;
        this.terminais = new ArrayList<>(terminais);
        this.transicoes = transicoes;
        this.matrizTransicoes = new Matriz(this).construirMatriz();
    }

    public HashMap<String, List<String>> getTransicoes() {
        return transicoes;
    }

    /**
     * @return the nTerminais
     */
    public List<String> getEstados() {
        return estados;
    }

    /**
     * @return the terminais
     */
    public List<String> getTerminais() {
        return terminais;
    }

    /**
     * @return the inicial
     */
    public String getInicial() {
        return inicial;
    }

    public Set<String> getFinais() {
        Set<String> finais = new HashSet<>();
        
        transicoes.forEach((k,v)->{
            if(v.contains("&"))
                finais.add(k);
        });
        
        return finais;
    }

    public boolean isNaoDeterministico() {
        for (Map.Entry<String, List<String>> entry : getTransicoes().entrySet()) {
            for (String producao : entry.getValue()) {
                if (producao.length() < 2) {
                    if (temNaoDeterminacao(entry.getValue(), producao)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public abstract boolean reconhecerSentenca(Fita fita);
    
    public boolean temNaoDeterminacao(List<String> producoes, String terminal) {
        return producoes
                .stream()
                .filter(producao -> producao.contains(terminal))
                .count() > 2;
    }

    public Matriz getMatrizTransicoes() {
        return matrizTransicoes;
    }

    public boolean verificaFinal(String estado) {
        System.out.println(getFinais());
        for (String s : getFinais()) {
            if (estado.contains(s)) {
                return true;
            }
        }
        return false;
    }

}
