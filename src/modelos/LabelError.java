/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Comparator;
import javax.swing.JLabel;

/**
 *
 * @author danyc
 */
public class LabelError implements Comparable<LabelError>{
    
    private JLabel label;
    private String error;
    private int linea;
    private int tipo;
    private Gramatica gramatica;

    public LabelError(JLabel label, String error, int linea, int tipo) {
        this.label = label;
        this.error = error;
        this.linea = linea;
        this.tipo = tipo;
    }

    public LabelError(JLabel label, String error, int linea, int tipo, Gramatica gramatica) {
        this.label = label;
        this.error = error;
        this.linea = linea;
        this.tipo = tipo;
        this.gramatica = gramatica;
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

    @Override
    public String toString() {
        return "LabelError{" + "label=" + label.getText() + ", error=" + error + ", linea=" + linea + ", tipo=" + tipo + ", gramatica=" + gramatica + '}';
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Gramatica getGramatica() {
        return gramatica;
    }

    public void setGramatica(Gramatica gramatica) {
        this.gramatica = gramatica;
    }
    
    

    
    @Override
    public int compareTo(LabelError o) {
        return (this.getLinea()< o.getLinea()? -1 : 
            (this.getLinea()== o.getLinea()? 0 : 1));     
    }
}
