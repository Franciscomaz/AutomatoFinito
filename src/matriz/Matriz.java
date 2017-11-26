/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriz;

import automato.AutomatoFinito;
import java.util.HashMap;
import java.util.List;

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

    public List<String> getTransicao(String nTerminal, String terminal) {
        return matriz.get(nTerminal).getNTerminal(terminal);
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
                    addTransicao(k, producao.substring(0, 1), producao.substring(1));
                } else {
                    addTransicao(k, producao.substring(0, 1), "");
                }
            }
        });
    }

    public void addTransicao(String nTerminal, String terminal, String nTerminal2) {
        if (matriz.get(nTerminal) != null) {
            matriz.put(nTerminal, matriz.get(nTerminal).add(terminal, nTerminal2));
        } else {
            matriz.put(nTerminal, construirNo().add(terminal, nTerminal2));
        }
    }

    public void imprimir() {
        matriz.forEach((k, v) -> {
            System.out.println("Não terminal: " + k);
            v.imprimir();
        });
    }
}
