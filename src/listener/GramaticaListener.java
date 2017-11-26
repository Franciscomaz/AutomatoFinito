/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import automato.AutomatoFinitoDeterministico;
import gramatica.Gramatica;
import views.TelaAutomato;
import views.TelaGramatica;
import views.TelaPrincipal;
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
        String gramatica = telaGramatica.getGramatica();
        
        if(gramatica == null)
            return;
        
        TelaPrincipal tp = (TelaPrincipal)SwingUtilities.getWindowAncestor(telaGramatica);
        telaGramatica.setVisible(false);
        tp.add(new TelaAutomato(new AutomatoFinitoDeterministico(new Gramatica(gramatica))));
        test(gramatica);
    }
     
    
    public void test(String gramatica){
        Gramatica g = new Gramatica(gramatica);
        System.out.println(g.getNaoTerminais());
        System.out.println(g.getTerminais());
    }    
}
