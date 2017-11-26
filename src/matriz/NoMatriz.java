/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Francisco
 */
public class NoMatriz {
    private final HashMap<String, List<String>> no;

    public NoMatriz() {
        no = new HashMap<>();
    }

    public List<String> getNTerminal(String terminal) {
        return no.get(terminal)
                .stream()
                .filter(s-> s != null && !s.isEmpty())
                .collect(Collectors.toList());
    }

    public NoMatriz add(String terminal, String nTerminal) {
        List<String> nTerminais = new ArrayList();

        nTerminais.add(nTerminal);
        
        if (no.get(terminal) != null) {
            nTerminais.addAll(no.get(terminal));
            no.put(terminal, nTerminais);
        } else {
            no.put(terminal, nTerminais);
        }

        return this;
    }
    
    public void imprimir(){
        no.forEach((k,v)->{
            System.out.println("Terminal: " + k);
            System.out.println("Não terminais: "+ v);
        });
    }

}
