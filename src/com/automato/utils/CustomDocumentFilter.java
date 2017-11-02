/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automato.utils;

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
    
    public CustomDocumentFilter(String filter){
        this.filter = filter;
    }
    
    @Override
    public void replace(FilterBypass fb, int offs, int length,
        String str, AttributeSet a) throws BadLocationException {

        String text = fb.getDocument().getText(0, fb.getDocument().getLength()) + str;
        
        int lastIndex = text.lastIndexOf("\n");
        
        if(lastIndex != -1  && !str.equals("\n")){
            text = text.substring(lastIndex + 1, text.length());
        }
             
        if ((text.matches(filter))) {
            super.replace(fb, offs, length, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }


}


