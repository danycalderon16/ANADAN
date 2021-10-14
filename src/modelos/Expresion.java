/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import pila.Pila;

/**
 *
 * @author Calderon
 */
public class Expresion {
   
    String infija;
    String infija_parentesis;
    ArrayList<String> pila_ops;
    String postfija;
    String result;

    public Expresion(String infija) {
        this.infija = infija;
        this.pila_ops = new ArrayList<>();
    }
    
    public String getInfija() {
        return infija;
    }

    public void setInfija(String infija) {
        this.infija = infija;
    }

    public String getInfija_parentesis() {
        return infija_parentesis;
    }

    public void setInfija_parentesis(String infija_parentesis) {
        this.infija_parentesis = infija_parentesis;
    }

    public ArrayList<String> getPila_ops() {
        return pila_ops;
    }

    public void setPila_ops(String pila_ops) {
        this.pila_ops.add(pila_ops);
    }

    public String getPostfija() {
        return postfija;
    }

    public void setPostfija(String postfija) {
        this.postfija = postfija;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Expresion{" + "infija=" + infija + ", infija_parentesis=" + infija_parentesis + ", pila_ops=" + pila_ops + ", postfija=" + postfija + ", result=" + result + '}';
    }

    private String showPila() {
        String str = "";
        for (String pila_op : pila_ops) {
            str += pila_op+"\n";
        }
        return str;
    }

      
    
}
