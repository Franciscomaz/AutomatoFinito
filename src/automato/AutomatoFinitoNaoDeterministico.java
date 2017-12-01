/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Francisco
 */
public class AutomatoFinitoNaoDeterministico extends AutomatoFinito{

    public AutomatoFinitoNaoDeterministico(HashMap<String, List<String>> transicoes, Set<String> estados, Set<String> terminais, String inicial) {
        super(transicoes, estados, terminais, inicial);
    }

    @Override
    public boolean reconhecerSentenca(Fita fita) {
        return false;
    }
    
}
