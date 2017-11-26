/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Francisco
 */
public class TabelaAutomato extends AbstractTableModel{
    
    private final AutomatoFinito automato;
    private final List<String> colunas;
    
    public TabelaAutomato(AutomatoFinito automato){
        this.automato = automato;
        colunas = new ArrayList<>(automato.getTerminais());
        colunas.add(0, "Não terminais");
    }

    @Override
    public int getRowCount() {
        return automato.getEstados().size();
    }

    @Override
    public int getColumnCount() {
        return automato.getTerminais().size()+1;
    }
    
    @Override
    public String getColumnName(int num){
        return this.colunas.get(num);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        final List<String> nTerminais = new ArrayList<>(automato.getEstados());
        final List<String> terminais = new ArrayList<>(automato.getTerminais());
        
        if(coluna == 0) return nTerminais.get(linha);
        
        return automato
                .getMatrizTransicoes()
                .getTransicao(nTerminais.get(linha), terminais.get(coluna-1))
                .toString();
    }
    

}
