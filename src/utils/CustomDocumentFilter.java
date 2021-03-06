/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Francisco
 */
public class CustomDocumentFilter extends DocumentFilter {

    private final String filter;

    //recebe o regex que sera usado como filtro
    public CustomDocumentFilter(String filter) {
        this.filter = filter;
    }

    //le o input do usuário e verifica se faz parte do regex recebido
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());

        text = text.substring(text.lastIndexOf("\n") + 1, text.length()) + str;
        
        if ((text.matches(filter))) {
            super.replace(fb, offs, length, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }

    }
}
