/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import matriz.Matriz;

/**
 *
 * @author Francisco
 */
public interface AutomatoFinito {

    public Set<String> getEstados();

    public Set<String> getTerminais();

    public String getInicial();
    
    public HashMap<String, List<String>> getTransicoes();
    
    public Matriz getMatrizTransicoes();
}
