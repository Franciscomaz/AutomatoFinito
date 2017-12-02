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
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import utils.AutomatoException;

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

        try {
            Gramatica gramatica = new Gramatica(telaGramatica.getGramatica());

            validarGramatica(gramatica);

            TelaPrincipal tp = (TelaPrincipal) SwingUtilities.getWindowAncestor(telaGramatica);
            telaGramatica.setVisible(false);

            tp.add(new TelaAutomato(AutomatoFactory.getAutomato(gramatica)));
        } catch (AutomatoException ex) {
            JOptionPane.showMessageDialog(telaGramatica, ex.getMessage());
        }
    }

    public void validarGramatica(Gramatica gramatica) throws AutomatoException {
        for (Map.Entry<String, List<String>> entry : gramatica.getTransicoes().entrySet()) {
            for (String producao : entry.getValue()) {
                if (producao.length() > 1) {
                    String nTerminal = producao.substring(1);
                    if (!gramatica.getNaoTerminais().contains(nTerminal)) {
                        throw new AutomatoException("Não terminal não existente no lado esquerdo da gramática.");
                    }
                }
            }
        }
    }
}
