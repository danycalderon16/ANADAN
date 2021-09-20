/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import pila.Nodo;

/**
 *
 * @author danyc
 */
public class Pila {

    private Nodo raiz;

    public Pila() {
        raiz = null;
    }

    public void push(String x) {
        if (x.equals("*")) {
            float op1 = Float.parseFloat(pop());
            float op2 = Float.parseFloat(pop());
            push(op1 * op2 + "");
        } else {
            if (x.equals("/")) {
                float op1 = Float.parseFloat(pop());
                float op2 = Float.parseFloat(pop());
                push(op2 / op1 + "");
            } else {
                if (x.equals("+")) {
                    float op1 = Float.parseFloat(pop());
                    float op2 = Float.parseFloat(pop());
                    push(op2 + op1 + "");
                } else {
                    if (x.equals("-")) {
                        float op1 = Float.parseFloat(pop());
                        float op2 = Float.parseFloat(pop());
                        push(op1 - op2 + "");
                    } else {
                        Nodo nuevo;
                        nuevo = new Nodo();
                        nuevo.info = x;
                        if (raiz == null) {
                            nuevo.sig = null;
                            raiz = nuevo;
                        } else {
                            nuevo.sig = raiz;
                            raiz = nuevo;
                        }
                    }
                }
            }
        }

    }

    public String pop() {
        if (raiz != null) {
            String informacion = raiz.info;
            raiz = raiz.sig;
            return informacion;
        } else {
            return null;
        }
    }

    public void imprimir() {
        Nodo reco = raiz;
        System.out.println("Listado de todos los elementos de la pila.");
        while (reco != null) {
            System.out.println(reco.info + "-");
            reco = reco.sig;
        }
        System.out.println();
    }

    public static void main(String[] ar) {
        String exp = "123*+45/-";
        String[] arb = exp.split("");
        Pila pila = new Pila();
        for(int i = 0;i<exp.length();i++){
            pila.push(arb[i]);
        }
        pila.imprimir();
    }
}
