/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.listener;

import com.automato.views.TelaAutomato;
import com.automato.views.TelaGramatica;
import com.automato.views.TelaPrincipal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Francisco
 */
public class AutomatoListener implements ActionListener {

    private final TelaAutomato telaAutomato;

    public AutomatoListener(TelaAutomato telaAutomato) {
        this.telaAutomato = telaAutomato;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Verificar":
                break;
            case "Voltar":
                voltar();
                break;
        }
    }

    public void voltar() {
        TelaPrincipal tp = (TelaPrincipal) SwingUtilities.getWindowAncestor(telaAutomato);
        getComponent(tp);
        //tp.remove(telaAutomato);      
        tp.pack();
    }
    
    public void getComponent(TelaPrincipal tp){
        
        for (Component c : tp.getContentPane().getComponents()){
            if(c instanceof TelaGramatica){             
                c.setVisible(true);
                c.setFocusable(true);
                telaAutomato.setVisible(false);
                System.out.println("funciona");
                return;
            }
        
        }  
    }

}
