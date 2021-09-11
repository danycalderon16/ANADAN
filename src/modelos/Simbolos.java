/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class Simbolos implements Comparable<Simbolos> {
    
    private String componente;
    private int linea;
    private String lexema;
    private String tipo;
    private String valor;

    public Simbolos(String componente, int linea, String lexema, String tipo, String valor) {
        this.componente = componente;
        this.linea = linea;
        this.lexema = lexema;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }    
    
    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Simbolos{" + "componente=" + componente + ", linea=" + linea + ", lexema=" + lexema + ", tipo=" + tipo + ", valor=" + valor + '}';
    }
    
    @Override
    public int compareTo(Simbolos s) {
       return lexema.compareTo(s.getLexema());
    }
}
