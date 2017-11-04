/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.automatofinito;

import com.automato.gramatica.Gramatica;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Francisco
 */
public class AutomatoFinito {
    private final Gramatica gramatica;
    
    public AutomatoFinito(Gramatica gramatica){
        this.gramatica = gramatica;
    }
    
    public Set<Character> getFinais(){
        //ArrayList pegaFinal = new ArrayList<>();
        HashSet<Character> pegaFinal = new HashSet<>();
        for (String linha : gramatica.getLinhas()) {
            //Converte a linha em char
            String[] producoes = linha.substring(2).split("[|]");
            //for criado para fazer toda a operacao com a linha
            for (String producao : producoes) {
                //Checa se a letra na posicao j e minuscula E checa se na posicao j+1 e maiuscula
                if (producao.length() < 2) {                 
                    if (linha.contains(producao)) {
                        for (String verificaFinal:producoes) {                          
                                if (verificaFinal.contains(producao) && verificaFinal.length() > 1) {
                                    pegaFinal.add(verificaFinal.charAt(1));
                                }                           
                        }
                    }
                }
            }
        }
        System.out.println("Final detectado: " + pegaFinal);
        
        return pegaFinal;
    }
}
