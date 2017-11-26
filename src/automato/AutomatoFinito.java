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
    protected final Set<Estado> estados;

    public AutomatoFinito(Gramatica gramatica) {
        this.gramatica = gramatica;
        this.estados = new HashSet<>();
        this.matrizTransicoes = new Matriz(this);
    }
    
    public HashMap<String, List<String>> getTransicoes(){
        return gramatica.getTransicoes();
    }   
    /**
     * @return the nTerminais
     */
    public Set<String> getEstados() {
        return gramatica.getNaoTerminais();
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

    public void setFinais() {

    }

    public Matriz getMatrizTransicoes() {
        return matrizTransicoes;
    }
    
}
