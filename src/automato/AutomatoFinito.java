/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matriz.Matriz;

/**
 *
 * @author Francisco
 */
public abstract class AutomatoFinito {

    protected final Matriz matrizTransicoes;
    protected final Gramatica gramatica;
    protected final Set<String> estados;

    public AutomatoFinito(Gramatica gramatica) {
        this.gramatica = gramatica;
        this.estados = gramatica.getNaoTerminais();
        this.matrizTransicoes = new Matriz(this);
    }

    public HashMap<String, List<String>> getTransicoes() {
        return gramatica.getTransicoes();
    }

    /**
     * @return the nTerminais
     */
    public Set<String> getEstados() {
        return estados;
    }

    /**
     * @return the terminais
     */
    public Set<String> getTerminais() {
        return gramatica.getTerminais();
    }

    /**
     * @return the inicial
     */
    public String getInicial() {
        return gramatica.getInicial();
    }

    public Set<String> getFinais() {
        HashSet<String> pegaFinal = new HashSet<>();

        for (String linha : gramatica.getLinhas()) {
            //Converte a linha em char
            List<String> producoes = gramatica.getProducoes(linha);
            //for criado para fazer toda a operacao com a linha
            for (String producao : producoes) {
                //Checa se a letra na posicao j e minuscula E checa se na posicao j+1 e maiuscula
                if (producao.length() < 2) {
                    if (!temNaoDeterminacao(producoes, producao)) {
                        if (linha.contains(producao)) {
                            for (String verificaFinal : producoes) {
                                if (producao.equals("&")) {
                                    pegaFinal.add(linha.substring(0, 1));
                                }
                                if (verificaFinal.contains(producao) && verificaFinal.length() > 1) {
                                    pegaFinal.add(verificaFinal.substring(1));
                                }
                            }
                        }
                    }
                }
            }
        }
        return pegaFinal;
    }

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
