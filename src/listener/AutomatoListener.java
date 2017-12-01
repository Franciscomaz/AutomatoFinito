/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import automato.ConverterAfnd;
import automato.Fita;
import automato.TabelaAutomato;
import views.TelaAutomato;
import views.TelaGramatica;
import views.TelaPrincipal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
                verificar();
                break;
            case "TransformarAfnd":
                TransformarAfnd();
                break;
            case "Voltar":
                voltar();
                break;
        }
    }

    public void verificar() {
        String sentenca = telaAutomato.getSentenca();      
        try {
            boolean sentencaReconhecida = telaAutomato.getAutomatoFinito().reconhecerSentenca(new Fita(sentenca));
            verificarSentenca(sentencaReconhecida);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(telaAutomato, "Terminal não existente.");
        }

    }

    public void verificarSentenca(boolean sentencaReconhecida) {
        if (sentencaReconhecida) {
            JOptionPane.showMessageDialog(telaAutomato, "Sentença reconhecida.");
        } else {
            JOptionPane.showMessageDialog(telaAutomato, "Sentença invalida.");
        }
    }

    public void voltar() {
        TelaPrincipal tp = (TelaPrincipal) SwingUtilities.getWindowAncestor(telaAutomato);
        tp.remove(telaAutomato);
        getComponent(tp);
    }
    
    public void TransformarAfnd(){
       ConverterAfnd afndParaAfd = new ConverterAfnd(telaAutomato.getAutomatoFinito());
       afndParaAfd.getAutomato().getMatrizTransicoes().imprimir();
       telaAutomato.setModel(new TabelaAutomato(afndParaAfd.getAutomato()));
    }
    
    public void getComponent(TelaPrincipal tp) {
        for (Component c : tp.getContentPane().getComponents()) {
            if (c instanceof TelaGramatica) {
                c.setVisible(true);
                tp.add(c);
                return;
            }
        }
    }

}
