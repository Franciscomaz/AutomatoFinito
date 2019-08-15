/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public final class Fita {
    private final List<String> fita = new ArrayList<>();
    private int index = -1;
    
    public Fita(String sentenca) {
        construirFita(sentenca);
    }
    
    public void construirFita(String sentenca){
        for (int i = 0; i < sentenca.length(); i++) {
            fita.add(sentenca.substring(i, i + 1));
        }
    }
    
    public String getProximo(){
        if(++index == fita.size())
            return null;
        return fita.get(index);
    }  
}
