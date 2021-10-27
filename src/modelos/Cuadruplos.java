/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Calderon
 */
public class Cuadruplos {

    private List<Cuadruplo> cuadruplos;
    private String text;
    private int temp;
    private Cuadruplo ultimo;
    private Cuadruplo antepenultimo;

    public Cuadruplos() {
        this.temp = 1;
        ultimo = null;
        antepenultimo = null;
        cuadruplos = new ArrayList<>();
        this.text = "OP    Arg1    Arg2    Result\n";
    }

    public Cuadruplos(List<Cuadruplo> cuadruplos) {
        this.cuadruplos = cuadruplos;
        this.text = "OP    Arg1    Arg2    Result\n";
    }

    public List<Cuadruplo> getCuadruplos() {
        return cuadruplos;
    }

    public void setCuadruplos(List<Cuadruplo> cuadruplos) {
        this.cuadruplos = cuadruplos;
    }
    
    public void addSalto(){
        this.text = ("\n");
        this.cuadruplos.add(new Cuadruplo("","","",""));
    }
    
    public void addCuadruplo(Cuadruplo cua) {
        if (ultimo == null) {
            cua.setTemp("TEMP" + temp++);
            //System.err.println("ultimo null");
            this.text += cua.getOp() + "    " + cua.getArg1() + "    " + cua.getArg2() + "    " + cua.getTemp() + "\n";
            this.cuadruplos.add(cua);
        } else {
            //System.err.println("linea 43: "+cua.getArg1() +" | "+ ultimo.getResult());
            if (cua.getArg1().equals(ultimo.getResult())) {
                cua.setArg1(ultimo.getTemp());
            }
            if (cua.getArg2().equals(ultimo.getResult())) {
            //System.err.println("linea 42: "+cua.getArg2()+" | "+ ultimo.getResult());
                cua.setArg2(ultimo.getTemp());
            }
            if (antepenultimo != null) {
                if (cua.getArg1().equals(antepenultimo.getResult())) {
                    cua.setArg1(antepenultimo.getTemp());
                }
                if (cua.getArg2().equals(antepenultimo.getResult())) {
                    cua.setArg2(antepenultimo.getTemp());
                }
            }
            cua.setTemp("TEMP" + (temp++));
            this.text += cua.getOp() + "    " + cua.getArg1() + "    " + cua.getArg2() + "    " + cua.getTemp() + "\n";
            this.cuadruplos.add(cua);
        }

        this.antepenultimo = this.ultimo;
        this.ultimo = cua;
    }

    public String imprimir() {
        return this.text;
    }

}
