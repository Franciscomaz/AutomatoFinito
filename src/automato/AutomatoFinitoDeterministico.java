/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 *
 * @author Francisco
 */
public class AutomatoFinitoDeterministico extends AutomatoFinito {

    public AutomatoFinitoDeterministico(HashMap<String, List<String>> transicoes, Set<String> estados, Set<String> terminais, String inicial) {
        super(transicoes, estados, terminais, inicial);
    }

    @Override
    public boolean reconhecerSentenca(List<String> fita) {
        String estado = getInicial();

        for (String caracter : fita) {
            estado = matrizTransicoes.getTransicao(estado, caracter);
            if (estado == null) {
                return false;
            }
        }
        return verificaFinal(estado);
    }
    


}
