/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 *
 * @author Francisco
 */
public class AutomatoFinitoNaoDeterministico extends AutomatoFinito{

    public AutomatoFinitoNaoDeterministico(Gramatica gramatica) {
        super(gramatica);
    }
    
    @Override
    public AutomatoFinitoDeterministico getAutomatoFinitoDeterministico() {
        return converterParaAfd(); 
    }

    public AutomatoFinitoDeterministico converterParaAfd() {
        int length = estados.size();
        
        for (int i = 0; i < length; i++) {
            String estado = estados.get(i);
            for (String terminal : terminais) {
                String[] transicao = matrizTransicoes.getTransicao(estado, terminal).split(",");
                if (transicao.length > 1) {
                    String novoEstado = novoEstado(transicao);
                    matrizTransicoes.substituirTransicao(estado, terminal, novoEstado);
                    if (!matrizTransicoes.containsEstado(novoEstado)) {
                        matrizTransicoes.adicionarNovoEstado(novoEstado);
                        adicionarTransicoesNovoEstado(novoEstado);
                        length++;
                    }
                }
            }
        }
        
        return new AutomatoFinitoDeterministico(inicial, estados, terminais, transicoes, matrizTransicoes);
    }

    public void adicionarTransicoesNovoEstado(String novoEstado) {
        for (String terminal : terminais) {
            for (int i = 0; i < novoEstado.length(); i++) {
                matrizTransicoes.addTransicao(novoEstado, terminal, matrizTransicoes.getTransicao(novoEstado.substring(i, i+1), terminal));
            }            
        }
    }

    public String novoEstado(String[] estados) {
        Set<String> novoEstado = new HashSet<>();

        for (String estado : estados) {
            for (int i = 0; i < estado.length(); i++) {
                novoEstado.add(estado.substring(i, i + 1));
            }
        }

        return novoEstado
                .stream()
                .collect(Collectors.joining());
    }
    
    @Override
    public boolean reconhecerSentenca(Fita fita) {
        return false;
    }  
}
