/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import automato.AfndParaAfd;
import automato.AutomatoFinito;
import automato.AutomatoFinitoDeterministico;
import gramatica.Gramatica;
import views.TelaAutomato;
import views.TelaGramatica;
import views.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
        String gramatica = telaGramatica.getGramatica();

        if (gramatica == null) {
            return;
        }

        TelaPrincipal tp = (TelaPrincipal) SwingUtilities.getWindowAncestor(telaGramatica);
        telaGramatica.setVisible(false);
        tp.add(new TelaAutomato(validarGramatica()));
    }

    public AutomatoFinitoDeterministico validarGramatica() {
        Gramatica gramatica = new Gramatica(telaGramatica.getGramatica());
        Set<String> nTerminais = gramatica.getNaoTerminais();
        HashMap<String, List<String>> transicoes = gramatica.getTransicoes();
        List<String> excluiEstados = new ArrayList<>();

        transicoes.forEach((k, v) -> {
            for (String producao : v) {
                if (producao.length() < 2) {
                    excluiEstados.add(k + producao);
                    nTerminais.add("X");
                }
            }
        });
        
        criarEstados(excluiEstados, transicoes);
        
        transicoes.forEach((k,v)->{
            System.out.println(k + ": " + v);
        });
        
        return new AutomatoFinitoDeterministico(transicoes, nTerminais, gramatica.getTerminais(), gramatica.getInicial());
    }

    public void criarEstados(List<String> excluiEstados, HashMap<String, List<String>> transicoes) {
        for (String terminal : excluiEstados) {
            List<String> aux = new ArrayList(transicoes.get(terminal.substring(0, 1)));
            List<String> list = new ArrayList<>();
            aux.remove(aux.indexOf(terminal.substring(1)));
            aux.add(terminal.substring(1) + "X");
            list.add("&");
            transicoes.put(terminal.substring(0,1), aux);
            transicoes.put("X", list);
        }
    }

}
