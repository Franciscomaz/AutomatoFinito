/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
/**
 *
 * @author Francisco
 */
public class AutomatoFinitoNaoDeterministico extends AutomatoFinito{

    public AutomatoFinitoNaoDeterministico(Gramatica gramatica) {
        super(gramatica);
    }

    @Override
    public boolean reconhecerSentenca(Fita fita) {
        return false;
    }
    
}
