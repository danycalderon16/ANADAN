package codigo;

import static codigo.VentanaPrincipal.txtAreaEdit;
import java.awt.Color;
import java.util.ArrayList;
import java.util.StringTokenizer;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;




public class Arduino {

    String fuente = "";
    String saliente = "";
    String salientetab = "";
    String salienteloop = "void loop() {\n";
    String[] codseg;
    public int tab=0;
    
    public Arduino(String sentencias) {
        fuente = sentencias;
        expresiones();
    }
    public String getArduino(){
        return salientetab;
    }
    public void expresiones(){
       String inijust = ".*just.*;.*";
       String iniflag = ".*flag.*;.*";
       String inibroken = ".*broken.*[//;].*";
       String iniword = ".*word.*[//;].*";
       String inicio = ".*begin.*";
       String fin = ".*end.*";
       String sentif = ".*if[//(].*";
       String sentfor = ".*for[//(].*";
       String sentwhile = ".*while[//(].*";
       String sentswitch = ".*switch[//(].*";
       String sentcase = ".*case[//:].*";
       String sentcut = ".*cut[//;].*";
       String printerport = ".*printerport[//(].*";
       
       String llavecierra = ".*[//}].*";
       String metodo = ".*method.*[//(].*";
       
       //FUNCIONES BÁSICAS
       String fb1 = ".*setfilamenttype[//(].*";
       String fb2 = ".*fillrectangle[//(].*";
       String fb3 = ".*drawrentangle[//(].*";
       String fb4 = ".*sleep[//(].*";
       String fb5 = ".*fillcircle[//(].*";
       String fb6 = ".*drawcircle[//(].*";
       String fb7 = ".*drawtriangle[//(].*";
       String fb8 = ".*filltriangle[//(].*";
       String fb9 = ".*stop[//(].*";
       String fb10 = ".*getextrusorx[//(].*";
       String fb11 = ".*getextrusory[//(].*";
       String fb12 = ".*getextrusorz[//(].*";
       String fb13 = ".*getfilamenttype[//(].*";
       String fb14 = ".*gettemperature[//(].*";
       String fb15 = ".*get[//(].*";
       String fb16 = ".*give[//(].*";
       
       
       
        
          
          
   
        StringTokenizer splitfake = new StringTokenizer(fuente,"\n");
        while(splitfake.hasMoreTokens()){
    
            String linea = splitfake.nextToken().replaceAll("\\s", "");
            
            //System.out.println(linea);
            if(linea.matches(metodo)){
                String[] first = linea.split("[//(]");
                String[] second = first[0].split("method");
                String[] n1 = first[1].split("[//)]");
                String[] n2 = n1[0].split(",");
                saliente += "void " + second[1] + "(";
                
                if(n2.length>0){for(int i=0; i<n2.length; i++){
                        
                        if(n2[i].contains("broken")){
                            if(i>=1){
                            n2[i]=n2[i].replace("broken", ",float "); 
                            }else{
                            n2[i]=n2[i].replace("broken", "float ");
                            }
                            saliente +=n2[i];
                        }
                        if(n2[i].contains("flag")){
                            if(i>=1){
                            n2[i]=n2[i].replace("flag", ",boolean "); 
                            }else{
                            n2[i]=n2[i].replace("flag", "boolean "); 
                            }
                            saliente +=n2[i];
                        }if(n2[i].contains("just")){
                            if(i>=1){
                            n2[i]=n2[i].replace("just", ",int "); 
                            }else{
                            n2[i]=n2[i].replace("just", "int "); 
                            }
                            saliente +=n2[i];
                        }
                        if(n2[i].contains("word")){
                            if(i>=1){
                            n2[i]=n2[i].replace("word", ",string "); 
                            }else{
                            n2[i]=n2[i].replace("word", "string "); 
                            }
                            saliente +=n2[i];
                        }
                }
                saliente +="){\n";
                }       
            }else if(linea.matches(inijust)){
                String[] first = linea.split(";");
                String[] second = first[0].split("just");
                saliente += "int " + second[1] + ";\n"; 
            }else if(linea.matches(iniflag)){
                String[] first = linea.split(";");
                String[] second = first[0].split("flag");
                saliente += "boolean " + second[1] + ";\n"; 
            }else if(linea.matches(inibroken)){
                String[] first = linea.split(";");
                String[] second = first[0].split("broken");
                saliente += "float " + second[1] + ";\n"; 
            }else if(linea.matches(iniword)){
                String[] first = linea.split(";");
                String[] second = first[0].split("word");
                saliente += "string " + second[1] + ";\n"; 
            }else if(linea.matches(inicio)){
                saliente += "void setup() {\n"; 
            }else if(linea.matches(fin)){
                saliente += "}\n"; 
            }else if(linea.matches(sentif) || linea.matches(llavecierra) || linea.matches(sentfor) || linea.matches(sentwhile)
                    || linea.matches(sentswitch) || linea.matches(sentcase) || linea.matches(sentcut) || linea.matches(fb1)
                    || linea.matches(fb2) || linea.matches(fb3) || linea.matches(fb4) || linea.matches(fb5) || linea.matches(fb6)
                    || linea.matches(fb7) || linea.matches(fb8) || linea.matches(fb9) || linea.matches(fb10) || linea.matches(fb11)
                    || linea.matches(fb12) || linea.matches(fb13) || linea.matches(fb14) || linea.matches(fb15) || linea.matches(fb16)){
                saliente += linea + "\n"; 
            }else if(linea.matches(printerport)){
                String[] first = linea.split("[//(]");
                String[] second = first[1].split("[//)]");
                saliente += "pinMode("+second[0]+",OUTPUT);\n";
                salienteloop += "digitalWrite("+second[0]+",HIGH);\n"; 
                salienteloop += "delay(1000);\n"; 
                salienteloop += "digitalWrite("+second[0]+",LOW);\n"; 
                salienteloop += "delay(1000);\n";
            }
        }
        saliente += salienteloop + "}";
        codseg = saliente.split("\n");
        
        for(int i=0; i<codseg.length; i++){
            for(int j=0; j<tab; j++){
                codseg[i] = "\t"+codseg[i];
            }
            salientetab +=codseg[i]+"\n"; 
            if(codseg[i].contains("{")){tab++;}
            if(codseg[i].contains("}")){tab--;}
        }
        
        
        
        System.out.println("\n Código Intermedio Generado: \n"+saliente);
    }

}
