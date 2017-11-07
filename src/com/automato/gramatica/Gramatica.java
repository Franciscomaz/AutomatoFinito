/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.gramatica;


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
        return getProducoes()//recebe as produções
                .stream()//pega uma produção por vez
                .map(s -> s.substring(0, 1))//pega o primeiro carácter de cada produção
                .collect(Collectors.toSet());//passa cada carácter para um set      
    }
  //retorna os não terminais
    public Set<String> getNaoTerminais(){
        return getLinhas()//recebe as linhas
                .stream()//pega uma linha por vez
                .map(linha -> linha.substring(0, 1))//pega o primeiro carácter de cada linha
                .collect(Collectors.toSet());//passa cada carácter para um set
    }
    //retorna as transições
    public HashMap<Character, List> getTransicoes(){
        // hash criado para conseguir pegar o Não terminal como key
        // e todo restante como seu valor.
        HashMap<Character, List> gramaticaHash = new HashMap<>();
        //Linha abaixo criado para conseguir armazenar cada linha
        //apos o enter dado
        //Laço for criado para tratar a quantidade de linha
        for (String linha : getLinhas()) {
            //Transforma a primeira linha toda em char para comparacao
            char nTerminal = linha.charAt(0); // 1 opcao dentro
            //Elimina os dois primeiros carácteres da linha e passa para uma string
            List valorHash = Arrays.asList(linha.substring(2).split("[|]"));
            //chama o hash map para inserir sua key e seus valores
            gramaticaHash.put(nTerminal, valorHash);
        }

        return gramaticaHash;
    }
    //faz um array de strings à cada \n e depois passa para uma lista
    public List<String> getLinhas(){
        return Arrays.asList(gramatica.split("\n"));
    }
    //retorna as produções
    public List<String> getProducoes(){
        return getLinhas()//recebe as linhas
                .stream()//pega uma linha por vez
                .map(s->s.substring(2))//elimina os dois primeiros caráracteres de cada linha(não-terminal e =)
                .map(s->s.split("[|]"))//divide as produções
                .flatMap(Arrays::stream)//pega uma produção por vez
                .collect(Collectors.toList());//passa cada produção para uma lista
    }
    
}
