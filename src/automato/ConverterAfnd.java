/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import matriz.Matriz;

/**
 *
 * @author Francisco
 */
public class ConverterAfnd {

    private final AutomatoFinitoDeterministico automato;

    public ConverterAfnd(AutomatoFinitoDeterministico automato) {
        this.automato = automato;
        converter();
    }

    public AutomatoFinitoDeterministico getAutomato() {
        return automato;
    }

    public void converter() {
        Matriz matriz = automato.getMatrizTransicoes();
        List<String> estados = new ArrayList(automato.getEstados());

        int length = estados.size();
        for (int i = 0; i < length; i++) {
            String estado = estados.get(i);
            for (String terminal : automato.getTerminais()) {
                String[] transicao = matriz.getTransicao(estado, terminal).split(",");
                if (transicao.length > 1) {
                    String novoEstado = novoEstado(transicao);
                    matriz.substituirTransicao(estado, terminal, novoEstado);
                    if (!matriz.containsEstado(novoEstado)) {
                        matriz.adicionarNovoEstado(novoEstado);
                        estados.add(novoEstado);
                        automato.getEstados().add(novoEstado);
                        adicionarTransicoesNovoEstado(novoEstado);
                        length++;
                    }
                }
            }
        }
    }

    public void adicionarTransicoesNovoEstado(String novoEstado) {
        Matriz matriz = automato.getMatrizTransicoes();
        for (String terminal : automato.getTerminais()) {
            for (int i = 0; i < novoEstado.length(); i++) {
                System.out.println(matriz.getTransicao(novoEstado.substring(i, i+1), terminal));
                matriz.addTransicao(novoEstado, terminal, matriz.getTransicao(novoEstado.substring(i, i+1), terminal));
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

}
