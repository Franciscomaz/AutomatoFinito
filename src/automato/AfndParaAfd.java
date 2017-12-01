/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;
import matriz.Matriz;
import matriz.MatrizTransicao;

/**
 *
 * @author Francisco
 */
public class AfndParaAfd {

    private final AutomatoFinitoDeterministico automato;

    public AfndParaAfd(AutomatoFinitoDeterministico automato) {
        this.automato = automato;
        converter();
    }

    public AutomatoFinitoDeterministico getAutomato() {
        return automato;
    }

    public void adicionarNovoEstado(Set<String> lista, Matriz matriz, String novoEstado) {
        automato.getEstados().add(novoEstado);
        for (String estado : lista) {
            for (String terminal : automato.getTerminais()) {
                String transicao;
                try {
                    transicao = matriz.getTransicao(estado, terminal).stream().collect(Collectors.joining(""));
                } catch (Exception e) {
                    transicao = "";
                }
                matriz.addTransicao(novoEstado, terminal, transicao, false);
            }
        }
    }

    public String criarNovoEstado(Set<String> lista) {
        HashSet<String> novoEstado = new HashSet<>();

        for (String estado : lista) {
            for (int i = 0; i < estado.length(); i++) {
                novoEstado.add(estado.substring(i, i + 1));
            }
        }

        System.out.println(novoEstado);

        return novoEstado
                .stream()
                .collect(Collectors.joining(""));
    }

    public void converter() {
        teste();
        Matriz matriz = automato.getMatrizTransicoes();
        HashSet<String> aux = new HashSet<>(automato.getEstados());
        boolean temNaoDeterminacao = false;

        for (String estado : aux) {
            for (String terminal : automato.getTerminais()) {
                Set<String> lista = matriz.getTransicao(estado, terminal);
                if (lista.size() > 1) {
                    temNaoDeterminacao = true;
                    String novoEstado = criarNovoEstado(lista);
                    if (!matriz.verificaEstado(novoEstado)) {
                        adicionarNovoEstado(lista, matriz, novoEstado);
                    }
                    matriz.addTransicao(estado, terminal, novoEstado, true);
                }
            }
        }

        if (temNaoDeterminacao) {
            converter();
        }

    }

    public void teste() {
        MatrizTransicao matriz = new MatrizTransicao(automato).construirMatriz();
        List<String> estados = new ArrayList(automato.getEstados());
        ListIterator<String> iterator = estados.listIterator();

        while (iterator.hasNext()) {
            String estado = iterator.next();
            System.out.println(estado);
            iterator.add("A");
            for (String terminal : automato.getTerminais()) {
                String[] transicao = matriz.getTransicao("A", terminal).split(",");
                iterator.add("A");
                if (transicao.length > 1) {
                    String novoEstado = novoEstado(transicao);
                    //matriz.substituirTransicao("A", terminal, novoEstado);
                    if (matriz.containsEstado(novoEstado)) {
                        iterator.add(novoEstado);
                    }
                }
            }
        }
        int length = estados.size();
        for (int i = 0; i < length; i++) {
            String estado = estados.get(i);
            for (String terminal : automato.getTerminais()) {
                String[] transicao = matriz.getTransicao(estado, terminal).split(",");
                if (transicao.length > 1) {
                    String novoEstado = novoEstado(transicao);
                    matriz.substituirTransicao(estado, terminal, novoEstado);
                    if (!matriz.containsEstado(novoEstado)) {
                        estados.add(novoEstado);
                        length++;
                    }
                }
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
