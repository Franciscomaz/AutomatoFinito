/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import factory.AutomatoFactory;
import gramatica.Gramatica;
import views.TelaAutomato;
import views.TelaGramatica;
import views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Francisco
 */
public class GramaticaListener implements ActionListener {

    private final TelaGramatica telaGramatica;

    public GramaticaListener(TelaGramatica telaGramatica) {
        this.telaGramatica = telaGramatica;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Enviar":
                enviar();
                break;
        }
    }

    public void enviar() {
        if (telaGramatica.getGramatica() == null) {
            return;
        }
        
        Gramatica gramatica = new Gramatica(telaGramatica.getGramatica());
        TelaPrincipal tp = (TelaPrincipal) SwingUtilities.getWindowAncestor(telaGramatica);
        telaGramatica.setVisible(false);
        tp.add(new TelaAutomato(AutomatoFactory.getAutomato(gramatica)));
    }
}
