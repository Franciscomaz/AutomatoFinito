/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chicom
 */
public class VerificadorDeAfnd {

    private final Gramatica gramatica;

    public VerificadorDeAfnd(Gramatica gramatica) {
        this.gramatica = gramatica;
    }

    public boolean isNaoDeterministico() {
        for (Map.Entry<String, List<String>> entry : gramatica.getTransicoes().entrySet()) {
            for (String producao : entry.getValue()) {
                if (temNaoDeterminacao(entry.getValue(), producao.substring(0, 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean temNaoDeterminacao(List<String> producoes, String terminal) {
        return producoes
                .stream()
                .filter(producao -> producao.contains(terminal))
                .count() > 1;
    }

}
