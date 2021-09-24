/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.swing.JLabel;

/**
 *
 * @author danyc
 */
public class LabelError {
    
    private JLabel label;
    private String error;
    private int linea;

    public LabelError(JLabel label, String error, int linea) {
        this.label = label;
        this.error = error;
        this.linea = linea;
    }

    
    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    
    
}
