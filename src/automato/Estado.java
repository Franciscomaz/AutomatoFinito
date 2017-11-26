/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.Objects;

/**
 *
 * @author Francisco
 */
public class Estado {
    private final String estado;
    private boolean isFinal = false;
    
    public Estado(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setToFinal(){
        isFinal = true;
    }
    
    public boolean isFinal(){
        return isFinal;
    }
    
    @Override
    public String toString(){
        return isFinal ? "*" + estado : estado;
    }
    
    @Override
    public int hashCode() {
        return estado.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Estado other = (Estado) obj;
        
        return Objects.equals(this.estado, other.estado);
    }
}
