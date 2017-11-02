package com.automato.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francisco
 */
public final class GramaticaRegex {
    public static String INICIAL = "[A-Z]";    
    
    public static String N_TERMINAIS = "([A-Z],)*|([A-Z],)*[A-Z]";
    
    public static String TERMINAIS = "([a-z],)*|([a-z],)*[a-z]";
   
    public static String PRODUCOES = "([A-Z])|"
                                    + "([A-Z]=)|"
                                    + "([A-Z]=[a-z][A-Z]*)|"
                                    + "([A-Z]=([a-z][A-Z]*[|])*)|"    
                                    + "(([A-Z]=([a-z][A-Z]*[|])*[a-z][A-Z]*)\n?)*";                                       
}
