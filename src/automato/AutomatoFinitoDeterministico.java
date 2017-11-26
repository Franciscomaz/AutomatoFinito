/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import matriz.Matriz;

/**
 *
 * @author Francisco
 */
public class AutomatoFinitoDeterministico implements AutomatoFinito {

    private final Matriz matrizTransicoes;
    private final Gramatica gramatica;

    public AutomatoFinitoDeterministico(Gramatica gramatica) {
        this.gramatica = gramatica;
        this.matrizTransicoes = new Matriz(this);
    }
    
    public HashMap<String, List<String>> getTransicoes(){
        return gramatica.getTransicoes();
    }
    
    /**
     * @return the nTerminais
     */
    @Override
    public Set<String> getEstados() {
        return gramatica.getNaoTerminais();
    }

    /**
     * @return the terminais
     */
    @Override
    public Set<String> getTerminais() {
        return gramatica.getTerminais();
    }

    /**
     * @return the inicial
     */
    @Override
    public String getInicial() {
        return gramatica.getInicial();
    }

    public void setFinais() {

    }

    @Override
    public Matriz getMatrizTransicoes() {
        return matrizTransicoes;
    }

    public boolean reconhecerSentenca(List<String> fita) {
        String estado = getInicial();

        for (String caracter : fita) {
            estado = matrizTransicoes.getTransicao(estado, caracter);
            if (estado == null) {
                return false;
            }
        }

        return true;
    }

}
