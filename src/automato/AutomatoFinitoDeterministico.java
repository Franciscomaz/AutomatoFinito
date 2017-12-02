/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import gramatica.Gramatica;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import matriz.Matriz;


/**
 *
 * @author Francisco
 */
public final class AutomatoFinitoDeterministico extends AutomatoFinito {
    
    public AutomatoFinitoDeterministico(Gramatica gramatica) {
        super(gramatica);
        excluirEstadosInuteis();
    }
    
    public AutomatoFinitoDeterministico(String inicial, List<String> estados, List<String> terminais, HashMap<String, List<String>> transicoes, Matriz matrizTransicoes){
        super(inicial, estados, terminais, transicoes, matrizTransicoes);
        excluirEstadosInuteis();
    }
    
    public void excluirEstadosInuteis(){
        List<String> estadosUteis = new ArrayList<>();
        List<String> estados = new ArrayList<>(this.estados);
        
        verificarEstadosInuteis(inicial, estadosUteis);
        
        for(String estado : estados){
            if(!estadosUteis.contains(estado))
                getMatrizTransicoes().excluirEstado(estado);
        }
    }
    
    public void verificarEstadosInuteis(String estado, List<String> estados) {
        if (estados.contains(estado) || estado.equals("")) {
            return;
        }

        estados.add(estado);

        for (String terminal : getTerminais()) {
            verificarEstadosInuteis(getMatrizTransicoes().getTransicao(estado, terminal), estados);
        }
    }
    
    @Override
    public boolean reconhecerSentenca(Fita fita) {
        String estado = getInicial();
        String caracter;
        
        while ((caracter = fita.getProximo())!=null) {
            estado = matrizTransicoes.getTransicao(estado, caracter);
            if (estado == null) {
                return false;
            }
        }
        return verificaFinal(estado);
    }
    


}
