/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.gramatica;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Francisco
 */
public class Gramatica {
    private final String gramatica;
    
    public Gramatica(String gramatica){
        this.gramatica = gramatica;
    }
    //retorna a gramática
    public String getGramatica(){
        return gramatica;
    }
    //retorna o inicial
    public String getInicial(){
        return gramatica.substring(0,1);
    }
    //retorn os terminais
    public Set<String> getTerminais(){
        return getLinhas()
                .stream()
                .map(s->getProducoes(s))
                .flatMap(List::stream)
                .map(s->s.substring(0, 1))
                .collect(Collectors.toSet());               
    }
  //retorna os não terminais
    public Set<String> getNaoTerminais(){
        return getLinhas()//recebe as linhas
                .stream()//pega uma linha por vez
                .map(linha -> linha.substring(0, 1))//pega o primeiro carácter de cada linha
                .collect(Collectors.toSet());//passa cada carácter para um set
    }

    //faz um array de strings à cada \n e depois passa para uma lista
    public List<String> getLinhas(){
        return Arrays.asList(gramatica.split("\n"));
    }
    //retorna as produções
    public List<String> getProducoes(String linha){
        return Arrays.asList(linha.substring(2).split("[|]"));
    }
    
}
