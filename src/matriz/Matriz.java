/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriz;

import automato.AutomatoFinito;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Francisco
 */
public final class Matriz {

    private final HashMap<String, NoMatriz> matriz;
    private final AutomatoFinito automato;

    public Matriz(AutomatoFinito automato) {
        this.matriz = new HashMap<>();
        this.automato = automato;
        construirMatriz();
    }

    public Set<String> getTransicao(String nTerminal, String terminal){
        Set<String> aux = null;
        try{
            aux = matriz.get(nTerminal).getNTerminal(terminal);
        } catch(Exception e){
            
        }
        return aux;
    }

    private NoMatriz construirNo() {
        NoMatriz no = new NoMatriz();

        for (String terminal : automato.getTerminais()) {
            no.add(terminal, null);
        }
        
        return no;
    }

    private void construirMatriz() {
        automato.getTransicoes().forEach((k, v) -> {
            for (String producao : v) {
                //automato.getEstados().stream().filter(s-> s.equals(producao.substring(1))).findFirst();
                if (producao.length() > 1) {
                    addTransicao(k, producao.substring(0, 1), producao.substring(1), false);
                } else {
                    addTransicao(k, producao.substring(0, 1), "", false);
                }
            }
        });
    }

    public void addTransicao(String nTerminal, String terminal, String nTerminal2, boolean substituir) {
        
        if(substituir){
            matriz.get(nTerminal).substituirEstado(terminal, nTerminal2);
            return;
        }
        
        if (matriz.get(nTerminal) != null) {
            matriz.put(nTerminal, matriz.get(nTerminal).add(terminal, nTerminal2));
        } else {
            matriz.put(nTerminal, construirNo().add(terminal, nTerminal2));
        }
    }
    
    public boolean verificaEstado(String estado){
        return matriz.containsKey(estado);
    }
    
    
    public void imprimir() {
        matriz.forEach((k, v) -> {
            System.out.println("\nNão terminal: " + k);
            v.imprimir();
        });
    }
}
