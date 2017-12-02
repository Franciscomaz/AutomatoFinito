/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public AutomatoFinito(Gramatica gramatica) {
        this.estados = new ArrayList<>(gramatica.getNaoTerminais());
        this.inicial = gramatica.getInicial();
        this.terminais = new ArrayList<>(gramatica.getTerminais());
        this.transicoes = gramatica.getTransicoes();
        verificarNovoEstado();
        this.matrizTransicoes = new Matriz(this).construirMatriz();     
    }
    
    public AutomatoFinito(String inicial, List<String> estados, List<String> terminais, HashMap<String, List<String>> transicoes, Matriz matrizTransicoes) {
        this.estados = estados;
        this.inicial = inicial;
        this.terminais = terminais;
        this.transicoes = transicoes;
        verificarNovoEstado();
        this.matrizTransicoes = matrizTransicoes;     
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
    
    public void verificarNovoEstado() {
        List<String> transicoesAux = new ArrayList<>();
        
        transicoes.forEach((k, v) -> {
            for (String producao : v) {
                if (producao.length() < 2 && !producao.equals("&")) {
                    transicoesAux.add(producao.substring(0, 1)+k);                 
                }
            }
        });
        if(!transicoesAux.isEmpty())
            adicionarNovoEstado(transicoesAux); 
    }

    public void adicionarNovoEstado(List<String> transicoesAux) {
        estados.add("X");
        for(String transicao : transicoesAux){
            transicoes.put("X", Arrays.asList("&"));
            List<String> aux = transicoes.get(transicao.substring(1));
            aux.set(aux.indexOf(transicao.substring(0, 1)), transicao.substring(0, 1) + "X");
        }
    }

    public abstract boolean reconhecerSentenca(Fita fita);
    
    public abstract AutomatoFinitoDeterministico getAutomatoFinitoDeterministico();
}
