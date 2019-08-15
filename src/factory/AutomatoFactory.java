/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import automato.AutomatoFinito;
import automato.AutomatoFinitoDeterministico;
import automato.AutomatoFinitoNaoDeterministico;
import automato.VerificadorDeAfnd;
import gramatica.Gramatica;

/**
 *
 * @author Chicom
 */
public class AutomatoFactory {

    private AutomatoFactory() {}
    
    public static AutomatoFinito getAutomato(Gramatica gramatica){
        if(new VerificadorDeAfnd(gramatica).isNaoDeterministico()){
            return new AutomatoFinitoNaoDeterministico(gramatica);
        } else {
            return new AutomatoFinitoDeterministico(gramatica);
        }
    }
}
