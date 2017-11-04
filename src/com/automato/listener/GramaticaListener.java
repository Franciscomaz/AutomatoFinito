/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.listener;

import com.automato.gramatica.Gramatica;
import com.automato.views.TelaGramatica;
import com.automato.views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Francisco
 */
public class GramaticaListener implements ActionListener{
    private final TelaGramatica telaGramatica;
    
    public GramaticaListener(TelaGramatica telaGramatica){
        this.telaGramatica = telaGramatica;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "Enviar":
                enviar();
                break;
        }
    }
    
    public void enviar(){        
        TelaPrincipal tp = (TelaPrincipal)SwingUtilities.getWindowAncestor(telaGramatica);
        //telaGramatica.setVisible(false);
        gramaticaHashTable();
        
        //tp.add(new TelaAutomato());
        tp.pack();
    }
    
    public void gramaticaHashTable(){
        String gramatica = telaGramatica.getGramatica();
        
        if("".equals(gramatica)){
           JOptionPane.showMessageDialog(telaGramatica, "Insira uma gramatica valida.");
           return;
        }
        
        test(gramatica);
        
        //HashMap<Character, String[]> transicoes = getProducoes(gramatica);
        
        //transicoes.keySet().forEach(System.out::println);

    }  
    
    public void test(String gramatica){
        Gramatica g = new Gramatica(gramatica);
        System.out.println(g.getNaoTerminais());
        System.out.println(g.getTerminais());
    }
    /*
    public HashMap<Character, String[]> getProducoes(String gramatica){
        HashMap<Character, String[]> transicoes = new HashMap<>();
        
        for(String temp : gramatica.split("\n")){            
            String[] producoes = temp.substring(2).split("|");
            Character key = temp.charAt(0);
            if(transicoes.containsKey(key)){
                transicoes.put(key, StringArray.concat(producoes, transicoes.get(key)));
            } else {
                transicoes.put(key, producoes);
            }
        }
        
        return transicoes;
    }*/
    
}
