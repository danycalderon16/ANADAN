/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.StringTokenizer;

/**
 *
 * @author juani
 */
public class Optimizacion {
    String fuente="";    
    String saliente = "";
    int enciclo = 0;
    String variables = "";
    String dentro = "";
    public int tab=0;
    
    public Optimizacion(String sentencias) {
        fuente = sentencias;
        expresiones();
    }
    public String getArduino(){
        return saliente;
    }
    public void expresiones(){
        
    //EXPRESIONES REGULARES DEL LENGUAJE DE ANADAN PARA CAMBIAR POR ESTRUCTURAS EN ARDUINO
       String iniint = ".*int.*;.*";
       String iniboolean = ".*boolean.*;.*";
       String inifloat = ".*float.*[//;].*";
       String inistring = ".*String.*[//;].*";
       String sentfor = ".*for[//(].*";
       String sentwhile = ".*while[//(].*";
       String poruno = ".*[//*]1.*";
       
       
       //SEPARAMOS EL CÓDIGO FUENTE ESCRITO POR EL USUARIO EN TOKENS INDIVIDUALES
        StringTokenizer splitfake = new StringTokenizer(fuente,"\n");
        //EVALUACIÓN DE TOKENS INDIVIDUALES
        while(splitfake.hasMoreTokens()){
        //linea CONTIENE CADA TOKEN EN CADA CICLO DEL WHILE
            String linea = splitfake.nextToken();
            
            //SE EVALUA CADA LINEA CON LAS EXPRESIONES REGULARES ANTERIORES A TRAVÉS DE MATCHES
            if((enciclo==1)&&linea.contains("}")){
                enciclo=0;
                
                dentro += linea+"\n";
                 linea = splitfake.nextToken();
                saliente +=variables+dentro;
                variables="";
                dentro="";
            }else if(linea.matches(sentfor) || linea.matches(sentwhile)){
                enciclo = 1;
                dentro += linea+"\n";
            }else if((linea.matches(iniint)||linea.matches(iniboolean)||linea.matches(inifloat)
                    ||linea.matches(inistring))&&(enciclo==1)){
                System.err.println(linea);
                if(variables.isEmpty()){
                    variables = linea+"\n";
                }else{
                    variables += linea+"\n";
                }    
            }else if(linea.matches(poruno)){
                String[] first = linea.split("[//*]1");
                if(enciclo==0){
                saliente+=first[0]+first[1]+"\n";
                }else{
                    
                dentro+=first[0]+first[1]+"\n";
                }
            }else{
                if(enciclo==0){
                saliente += linea+"\n";
                }else{
                dentro += linea+"\n";
                }
            }    
        }
    }
}
