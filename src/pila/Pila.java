/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import javax.swing.JOptionPane;
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

    /*
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
     */
    public boolean isEmpty() {
        return raiz == null;
    }

    public void push(String s) {
        Nodo n = new Nodo(s);
        if (raiz == null) {
            raiz = n;
        } else {
            n.sig = raiz;
            raiz = n;
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
        Nodo nodos = raiz;
        System.out.println("------------------------");
        while (nodos != null) {
            System.out.print(nodos.info+",");
            nodos = nodos.sig;
        }
        System.out.println();
    }

    public static void main(String[] ar) {
        /* String exp = "123";
        String[] arb = exp.split("");
        Pila pila = new Pila();
        for(int i = 0;i<exp.length();i++){
            pila.push(arb[i]);
        }
        pila.pop();
        pila.imprimir();*/
        /*
        String str = "id = 4+3*(3+4)";
        String s1[] = str.split("=");
        str = s1[1];
        String newStr = "(";
        str = str.replace(" ", "");
        Pila pila = new Pila();

        //Agregando parentesis entre terminos de suma y resta
        String c[] = str.split("");
        for (int i = 0; i < c.length; i++) {
            if ("-".equals(c[i])) {
                newStr += ")-(";
            } else {
                if ("+".equals(c[i])) {
                    newStr += ")+(";
                } else {
                    newStr += c[i];
                }
            }
        }
        //System.out.println(newStr);

        //Creado pila con operadores y operandos en notacion infija
        pila.push("=");
        newStr = newStr + ")";
        String s2[] = newStr.split("");
        for (int i = 0; i < s2.length; i++) {
            pila.push(s2[i]);
        }
        Pila newPila = new Pila();
        String termino = "";
        while (!pila.isEmpty()) {
            String character = pila.pop();
            if ("*".equals(character)
                    || "/".equals(character)
                    || "+".equals(character)
                    || "-".equals(character)
                    || "(".equals(character)
                    || ")".equals(character)
                    || "=".equals(character)) {
                newPila.push(termino);
                newPila.push(character);
                termino = "";
            } else {
                termino = character +termino;
            }
        }

        newPila.push(s1[0]);
        //System.out.print("154 newPila: ");
        //newPila.imprimir();
        //Haciendo notacion postfija
        String postfija = "";
        Pila operadores = new Pila();

        while (!newPila.isEmpty()) {
            String character = newPila.pop();
            if ("*".equals(character)
                    || "/".equals(character)
                    || "+".equals(character)
                    || "-".equals(character)
                    || "(".equals(character)
                    || "=".equals(character)) {
                operadores.push(character);
            } else {
                if (")".equals(character)) {
                    String op = operadores.pop();
                    while (!"(".equals(op)) {
                        postfija += op + " ";
                        op = operadores.pop();
                    }
                } else {
                    //System.out.println(character);
                    postfija += character + " ";
                    //System.out.println(postfija);
                }
            }
        }

        //System.out.print("183 pilaOp: ");
        //operadores.imprimir();
        while (!operadores.isEmpty()) {
            String op = operadores.pop();
            //System.out.println("187 op: "+op);
            postfija += op + " ";
            //op = operadores.pop();

        }

        //System.out.println("189 Postfija: "+postfija);
        String post[] = postfija.split(" ");

        int i = 0;
        for (String string : post) {
            if (!string.isEmpty()) {
                i++;
            }
        }
        //System.out.println(i);
        String post_sin_espacios[] = new String[i];
        int j = 0;
        for (String string : post) {
            if (!string.isEmpty()) {
                post_sin_espacios[j++] = string;
            }
        }

        Pila pila_exp = new Pila();
       
        for (String exp : post_sin_espacios) {
            pila_exp.imprimir();
            if ("*".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op1+"*"+op2+"="+(op1*op2));
                pila_exp.push((op1 * op2) + "");
                continue;
            }
            if ("/".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op2+"/"+op1+"="+(op2/op1));
                pila_exp.push((op2 / op1) + "");
                continue;
            }
            if ("+".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op1+"+"+op2+"="+(op1+op2));
                pila_exp.push((op1 + op2) + "");
                continue;
            }
            if ("-".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op1+"-"+op2+"="+(op1-op2));
                pila_exp.push((op2 - op1) + "");
                continue;
            }
            if ("=".equals(exp)) {/*
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                pila_exp.push((op1 * op2) + "");
                continue;
            }

        //    System.out.println(exp);
            pila_exp.push(exp);
        }
        pila_exp.imprimir();
        String result = pila_exp.pop();
        String id = pila_exp.pop();
        int r_int = (int) Double.parseDouble(result);
        JOptionPane.showMessageDialog(null, r_int);*/
        
        double d = 8;
        if(d%1==0)
            System.out.println("Entero");
        else
            System.out.println("Flotane");

    }
}
