/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.List;


/**
 *
 * @author Francisco
 */
public class AutomatoFinitoDeterministico extends AutomatoFinito {

    public AutomatoFinitoDeterministico(Gramatica gramatica) {
        super(gramatica);
    }

    public boolean reconhecerSentenca(List<String> fita) {
        String estado = getInicial();

        for (String caracter : fita) {
            estado = matrizTransicoes.getTransicao(estado, caracter).get(0);
            if (estado == null) {
                return false;
            }
        }

        return true;
    }

}
