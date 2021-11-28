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




public class CodigoG {

    String fuente = ""; //CONTENDRÁ EL CÓDIGO FUENTE
    String salienteinicio = ""; //CONTENDRA EL CÓDIGO INTERMEDIO EN ARDUINO
    String salientetab = ""; //CONTENDRÁ EL CÓDIGO INTERMEDIO EN ARDUINO CON TABULACIONES
    String salienteloop = "void loop() {\n"; //CONTENTDRÁ LO CORRESPONDIENTE AL MÉTODO LOOP
    String resultado ="";
    String[] codseg; 
    String[] nada;
    String[] salientemarcos = new String [10];
    String[] lefttemplate = new String [3];
    String[] righttemplate = new String [3];
    public int tab=0;
    VariablesCodigoG variables = new VariablesCodigoG();
    
    public CodigoG(String sentencias) {
        fuente = sentencias;
        salientemarcos[0]="";
        salientemarcos[1]="";
        salientemarcos[2]="";
        salientemarcos[3]="";
        salientemarcos[4]="";
        salientemarcos[5]="";
        salientemarcos[6]="";
        salientemarcos[7]="";
        salientemarcos[8]="";
        salientemarcos[9]="";
        lefttemplate[0]="";
        lefttemplate[1]="";
        lefttemplate[2]="";
        righttemplate[0]="";
        righttemplate[1]="";
        righttemplate[2]="";
        expresiones();
    }
    public String[] getCodigoG(){
            return salientemarcos;
    }
    public String[] getCodigoG2(){
            return lefttemplate;
    }
    public String[] getCodigoG3(){
            return righttemplate;
    }
    public void expresiones(){
        
    //EXPRESIONES REGULARES DEL LENGUAJE DE ANADAN PARA CAMBIAR POR ESTRUCTURAS EN ARDUINO
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
       
       String asignacion = ".*=.*;.*";
       
       //EXPRESIONES REGULARES DE LAS FUNCIONES BÁSICAS DE ANADAN
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
       
       //String fb17 = ".*rightrim[//(].*";
       String fb18 = ".*rims[//(].*";
       String fb19 = ".*lefttemple[//(].*";
       String fb20 = ".*righttemple[//(].*";
       
       //SEPARAMOS EL CÓDIGO FUENTE ESCRITO POR EL USUARIO EN TOKENS INDIVIDUALES
        StringTokenizer splitfake = new StringTokenizer(fuente,"\n");
        //EVALUACIÓN DE TOKENS INDIVIDUALES
        while(splitfake.hasMoreTokens()){
            
        //linea CONTIENE CADA TOKEN EN CADA CICLO DEL WHILE
            String linea = splitfake.nextToken().replaceAll("\\s", "");
            //SE EVALUA CADA LINEA CON LAS EXPRESIONES REGULARES ANTERIORES A TRAVÉS DE MATCHES
            if(linea.matches(metodo)){
                String[] first = linea.split("[//(]");
                String[] second = first[0].split("method");
                String[] n1 = first[1].split("[//)]");
                String[] n2 = n1[0].split(",");
                //saliente += "void " + second[1] + "(";
                
                if(n2.length>0){for(int i=0; i<n2.length; i++){
                        
                        if(n2[i].contains("broken")){
                            if(i>=1){
                            n2[i]=n2[i].replace("broken", ",float "); 
                            }else{
                            n2[i]=n2[i].replace("broken", "float ");
                            }
                            //saliente +=n2[i];
                        }
                        if(n2[i].contains("flag")){
                            if(i>=1){
                            n2[i]=n2[i].replace("flag", ",boolean "); 
                            }else{
                            n2[i]=n2[i].replace("flag", "boolean "); 
                            }
                            //saliente +=n2[i];
                        }if(n2[i].contains("just")){
                            if(i>=1){
                            n2[i]=n2[i].replace("just", ",int "); 
                            }else{
                            n2[i]=n2[i].replace("just", "int "); 
                            }
                            //saliente +=n2[i];
                        }
                        if(n2[i].contains("word")){
                            if(i>=1){
                            n2[i]=n2[i].replace("word", ",string "); 
                            }else{
                            n2[i]=n2[i].replace("word", "string "); 
                            }
                            //saliente +=n2[i];
                        }
                }
                //saliente +="){\n";
                }       
            }else if(linea.matches(inijust)){
                String[] first = linea.split(";");
                String[] second = first[0].split("just");
                //saliente += "int " + second[1] + ";\n"; 
            }else if(linea.matches(iniflag)){
                String[] first = linea.split(";");
                String[] second = first[0].split("flag");
                //saliente += "boolean " + second[1] + ";\n"; 
            }else if(linea.matches(inibroken)){
                String[] first = linea.split(";");
                String[] second = first[0].split("broken");
                //saliente += "float " + second[1] + ";\n"; 
            }else if(linea.matches(iniword)){
                String[] first = linea.split(";");
                String[] second = first[0].split("word");
                //saliente += "string " + second[1] + ";\n"; 
            }else if(linea.matches(inicio)){
                //saliente += "void setup() {\n"; 
            }else if(linea.matches(fin)){
                //saliente += "}\n"; 
            }else if(linea.matches(sentif) || linea.matches(llavecierra) || linea.matches(sentfor) || linea.matches(sentwhile)
                    || linea.matches(sentswitch) || linea.matches(sentcase) || linea.matches(sentcut) || linea.matches(fb1)
                    || linea.matches(fb2) || linea.matches(fb3) || linea.matches(fb4) || linea.matches(fb5) || linea.matches(fb6)
                    || linea.matches(fb7) || linea.matches(fb8) || linea.matches(fb9) || linea.matches(fb10) || linea.matches(fb11)
                    || linea.matches(fb12) || linea.matches(fb13) || linea.matches(fb14) || linea.matches(fb15) || linea.matches(fb16)
                    || linea.matches(asignacion)){
                //saliente += linea + "\n"; 
            }else if(linea.matches(printerport)){
                String[] first = linea.split("[//(]");
                String[] second = first[1].split("[//)]");
                //saliente += "pinMode("+second[0]+",OUTPUT);\n";
                salienteloop += "digitalWrite("+second[0]+",HIGH);\n"; 
                salienteloop += "delay(1000);\n"; 
                salienteloop += "digitalWrite("+second[0]+",LOW);\n"; 
                salienteloop += "delay(1000);\n";
            }else if(linea.matches(fb18)){
                    String[] first2 = linea.split("[//(]");
                    String[] n3 = first2[1].split("[//)]");
                    String[] n4 = n3[0].split(",");

                    if(n4[0].toString().contains("square")){
                        salientemarcos[7]="G92 E0\n" +
                        "G1 X171.364 Y105.464 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X170.870 Y104.969 E2.01936\n" +
                        "G1 X170.004 Y105.025 E2.04339\n" +
                        "G1 X171.371 Y106.393 E2.09693\n" +
                        "G1 X171.387 Y107.331 E2.12290\n" +
                        "G1 X169.137 Y105.081 E2.21096\n" +
                        "G1 X168.271 Y105.137 E2.23499\n" +
                        "G1 X171.402 Y108.269 E2.35758\n" +
                        "G1 X171.418 Y109.207 E2.38355\n" +
                        "G1 X167.273 Y105.062 E2.54582\n" +
                        "G1 X166.640 Y104.901 E2.56391\n" +
                        "G1 X166.512 Y104.830 E2.56796\n" +
                        "G1 X166.510 Y105.222 E2.57879\n" +
                        "G1 X171.434 Y110.145 E2.77154\n" +
                        "G1 X171.449 Y111.083 E2.79751\n" +
                        "G1 X166.471 Y106.104 E2.99240\n" +
                        "G1 X166.419 Y106.976 E3.01655\n" +
                        "G1 X170.985 Y111.541 E3.19529\n" +
                        "G1 X170.256 Y111.735 E3.21617\n" +
                        "G1 X166.296 Y107.774 E3.37121\n" +
                        "G1 X166.204 Y108.292 E3.38575\n" +
                        "G1 X166.135 Y108.536 E3.39278\n" +
                        "G1 X169.527 Y111.928 E3.52558\n" +
                        "G1 X168.798 Y112.121 E3.54645\n" +
                        "G1 X165.928 Y109.251 E3.65883\n" +
                        "G1 X165.662 Y109.908 E3.67844\n" +
                        "G1 X168.069 Y112.315 E3.77267\n" +
                        "G1 X167.340 Y112.508 E3.79355\n" +
                        "G1 X165.397 Y110.565 E3.86963\n" +
                        "G1 X165.129 Y111.220 E3.88922\n" +
                        "G1 X166.611 Y112.702 E3.94723\n" +
                        "G1 X165.882 Y112.895 E3.96810\n" +
                        "G1 X164.406 Y111.419 E4.02591\n" +
                        "G1 X163.977 Y111.536 E4.03822\n" +
                        "G1 X163.918 Y111.574 E4.04016\n" +
                        "G1 X163.880 Y111.669 E4.04299\n" +
                        "G1 X163.887 Y111.822 E4.04723\n" +
                        "G1 X165.153 Y113.089 E4.09682\n" +
                        "G1 X164.424 Y113.282 E4.11770\n" +
                        "G1 X163.930 Y112.788 E4.13706\n" +
                        "G1 E2.13706 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X107.205 Y109.263 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X106.711 Y108.768 E2.01894\n" +
                        "G1 X106.408 Y109.134 E2.03180\n" +
                        "G1 X106.288 Y109.248 E2.03628\n" +
                        "G1 X107.421 Y110.381 E2.07969\n" +
                        "G1 X107.649 Y111.494 E2.11045\n" +
                        "G1 X108.785 Y112.031 E2.14448\n" +
                        "G1 X108.856 Y112.094 E2.14707\n" +
                        "G1 X108.881 Y112.188 E2.14969\n" +
                        "G1 X108.779 Y112.642 E2.16230\n" +
                        "G1 X105.817 Y109.679 E2.27578\n" +
                        "G1 X105.296 Y110.061 E2.29326\n" +
                        "G1 X108.614 Y113.379 E2.42035\n" +
                        "G1 X108.591 Y113.478 E2.42311\n" +
                        "G1 X108.749 Y113.658 E2.42958\n" +
                        "G1 X107.658 Y113.325 E2.46048\n" +
                        "G1 X104.727 Y110.395 E2.57273\n" +
                        "G1 X104.113 Y110.683 E2.59111\n" +
                        "G1 X106.509 Y113.079 E2.68289\n" +
                        "G1 X105.387 Y112.860 E2.71384\n" +
                        "G1 X103.450 Y110.922 E2.78805\n" +
                        "G1 X102.741 Y111.116 E2.80795\n" +
                        "G1 X104.266 Y112.641 E2.86635\n" +
                        "G1 X104.062 Y112.601 E2.87199\n" +
                        "G1 X103.241 Y112.519 E2.89432\n" +
                        "G1 X101.981 Y111.258 E2.94260\n" +
                        "G1 X101.164 Y111.344 E2.96484\n" +
                        "G1 X102.238 Y112.418 E3.00597\n" +
                        "G1 X101.870 Y112.381 E3.01600\n" +
                        "G1 X101.296 Y112.379 E3.03153\n" +
                        "G1 X100.293 Y111.375 E3.06997\n" +
                        "G1 X99.390 Y111.375 E3.09442\n" +
                        "G1 X100.390 Y112.375 E3.13272\n" +
                        "G1 X99.490 Y112.378 E3.15709\n" +
                        "G1 X98.469 Y111.357 E3.19621\n" +
                        "G1 X97.455 Y111.245 E3.22383\n" +
                        "G1 X98.594 Y112.384 E3.26746\n" +
                        "G1 X97.774 Y112.467 E3.28979\n" +
                        "G1 X96.298 Y110.991 E3.34632\n" +
                        "G1 X95.925 Y110.886 E3.35681\n" +
                        "G1 X95.252 Y110.629 E3.37634\n" +
                        "G1 X94.821 Y110.416 E3.38936\n" +
                        "G1 X96.954 Y112.549 E3.47106\n" +
                        "G1 X96.433 Y112.601 E3.48524\n" +
                        "G1 X96.157 Y112.655 E3.49284\n" +
                        "G1 X92.724 Y109.221 E3.62435\n" +
                        "G1 X92.564 Y109.963 E3.64492\n" +
                        "G1 X95.402 Y112.802 E3.75365\n" +
                        "G1 X94.647 Y112.950 E3.77448\n" +
                        "G1 X92.403 Y110.705 E3.86045\n" +
                        "G1 X92.340 Y110.997 E3.86851\n" +
                        "G1 X91.970 Y111.175 E3.87964\n" +
                        "G1 X93.892 Y113.097 E3.95328\n" +
                        "G1 X93.137 Y113.245 E3.97412\n" +
                        "G1 X91.360 Y111.468 E4.04217\n" +
                        "G1 X91.159 Y111.579 E4.04841\n" +
                        "G1 X91.120 Y111.670 E4.05110\n" +
                        "G1 X91.244 Y112.254 E4.06727\n" +
                        "G1 X92.437 Y113.447 E4.11297\n" +
                        "G1 X91.746 Y113.658 E4.13255\n" +
                        "G1 X91.251 Y113.163 E4.15149\n" +
                        "G1 E2.15149 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X33.488 Y105.325 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X32.994 Y104.830 E2.02013\n" +
                        "G1 X32.919 Y104.872 E2.02259\n" +
                        "G1 X32.269 Y105.064 E2.04210\n" +
                        "G1 X33.516 Y106.312 E2.09286\n" +
                        "G1 X33.563 Y107.318 E2.12185\n" +
                        "G1 X31.383 Y105.138 E2.21057\n" +
                        "G1 X30.368 Y105.081 E2.23983\n" +
                        "G1 X33.736 Y108.449 E2.37691\n" +
                        "G1 X33.796 Y108.786 E2.38675\n" +
                        "G1 X34.072 Y109.744 E2.41544\n" +
                        "G1 X29.343 Y105.015 E2.60789\n" +
                        "G1 X28.637 Y104.970 E2.62825\n" +
                        "G1 X28.638 Y105.270 E2.63687\n" +
                        "G1 X34.722 Y111.353 E2.88448\n" +
                        "G1 X34.868 Y111.714 E2.89566\n" +
                        "G1 X35.164 Y111.795 E2.90449\n" +
                        "G1 X36.095 Y112.726 E2.94240\n" +
                        "G1 X36.070 Y113.282 E2.95841\n" +
                        "G1 X35.555 Y113.146 E2.97374\n" +
                        "G1 X28.624 Y106.214 E3.25585\n" +
                        "G1 X28.608 Y107.157 E3.28300\n" +
                        "G1 X34.250 Y112.799 E3.51264\n" +
                        "G1 X32.945 Y112.453 E3.55150\n" +
                        "G1 X28.592 Y108.100 E3.72865\n" +
                        "G1 X28.577 Y109.044 E3.75580\n" +
                        "G1 X31.639 Y112.106 E3.88045\n" +
                        "G1 X30.334 Y111.760 E3.91932\n" +
                        "G1 X28.561 Y109.987 E3.99149\n" +
                        "G1 X28.534 Y110.919 E4.01833\n" +
                        "G1 X29.028 Y111.413 E4.03845\n" +
                        "M106 S219.3\n" +
                        "G1 Z3.350 F7800.000\n" +
                        "G1 E2.03845 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.802 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.801 Y107.981 E2.02853\n" +
                        "G1 X169.431 Y107.981 E2.09248\n" +
                        "G1 X169.431 Y106.924 E2.12101\n" +
                        "G1 X171.727 Y106.924 E2.18295\n" +
                        "G1 X171.995 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.995 Y103.944 E2.24619\n" +
                        "G1 X172.384 Y103.946 E2.25668\n" +
                        "G1 X172.420 Y104.017 E2.25883\n" +
                        "G1 X172.440 Y104.139 E2.26216\n" +
                        "G1 X172.435 Y111.765 E2.46788\n" +
                        "G1 X172.419 Y111.811 E2.46920\n" +
                        "G1 X172.345 Y111.880 E2.47190\n" +
                        "G1 X172.209 Y111.914 E2.47570\n" +
                        "G1 X171.995 Y111.915 E2.48146\n" +
                        "G1 X171.995 Y108.617 E2.57044\n" +
                        "G1 X168.795 Y108.617 E2.65677\n" +
                        "G1 X168.795 Y106.288 E2.71959\n" +
                        "G1 X171.920 Y106.288 E2.80389\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.379 E2.85229\n" +
                        "G1 X172.575 Y103.387 E2.87579\n" +
                        "G1 X172.678 Y103.447 E2.87825\n" +
                        "G1 X172.850 Y103.624 E2.88333\n" +
                        "G1 X172.959 Y103.843 E2.88838\n" +
                        "G1 X173.000 Y104.094 E2.89362\n" +
                        "G1 X172.995 Y111.828 E3.05302\n" +
                        "G1 X172.962 Y111.973 E3.05608\n" +
                        "G1 X172.895 Y112.115 E3.05932\n" +
                        "G1 X172.675 Y112.338 E3.06578\n" +
                        "G1 X172.545 Y112.409 E3.06884\n" +
                        "G1 X172.278 Y112.475 E3.07451\n" +
                        "G1 X171.435 Y112.478 E3.09188\n" +
                        "G1 X171.435 Y109.177 E3.15991\n" +
                        "G1 X168.234 Y109.177 E3.22587\n" +
                        "G1 X168.235 Y105.727 E3.29698\n" +
                        "G1 X171.360 Y105.727 E3.36138\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E1.36138 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X111.227 Y92.700 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.555 Y94.478 E2.05128\n" +
                        "G1 X110.028 Y95.991 E2.09450\n" +
                        "G1 X108.760 Y100.904 E2.23136\n" +
                        "G1 X108.628 Y101.476 E2.24721\n" +
                        "G1 X108.369 Y102.947 E2.28750\n" +
                        "G1 X108.258 Y103.712 E2.30835\n" +
                        "G1 X108.180 Y104.433 E2.32792\n" +
                        "G1 X108.062 Y106.322 E2.37899\n" +
                        "G1 X108.090 Y107.606 E2.41363\n" +
                        "G1 X107.155 Y107.151 E2.44169\n" +
                        "G1 X106.784 Y106.887 E2.45399\n" +
                        "G1 X107.322 Y104.809 E2.51190\n" +
                        "G1 X107.766 Y102.841 E2.56631\n" +
                        "G1 X108.224 Y101.144 E2.61373\n" +
                        "G1 X109.144 Y97.973 E2.70281\n" +
                        "G1 X110.623 Y93.371 E2.83320\n" +
                        "G1 X110.914 Y92.559 E2.85647\n" +
                        "G1 X111.159 Y92.669 E2.86373\n" +
                        "G1 X111.929 Y92.431 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X111.082 Y94.670 E2.91306\n" +
                        "G1 X110.565 Y96.154 E2.94546\n" +
                        "G1 X109.305 Y101.037 E3.04939\n" +
                        "G1 X109.178 Y101.588 E3.06105\n" +
                        "G1 X108.923 Y103.036 E3.09136\n" +
                        "G1 X108.814 Y103.783 E3.10690\n" +
                        "G1 X108.738 Y104.481 E3.12138\n" +
                        "G1 X108.623 Y106.334 E3.15964\n" +
                        "G1 X108.653 Y107.705 E3.18792\n" +
                        "G1 X108.743 Y108.482 E3.20403\n" +
                        "G1 X107.731 Y108.062 E3.22661\n" +
                        "G1 X106.877 Y107.639 E3.24624\n" +
                        "G1 X106.611 Y107.466 E3.25280\n" +
                        "G1 X106.360 Y107.266 E3.25942\n" +
                        "G1 X106.154 Y107.066 E3.26532\n" +
                        "G1 X106.166 Y107.003 E3.26664\n" +
                        "G1 X106.329 Y106.443 E3.27866\n" +
                        "G1 X106.551 Y105.508 E3.29847\n" +
                        "G1 X106.777 Y104.673 E3.31630\n" +
                        "G1 X107.221 Y102.706 E3.35786\n" +
                        "G1 X107.685 Y100.993 E3.39443\n" +
                        "G1 X108.607 Y97.809 E3.46276\n" +
                        "G1 X110.092 Y93.191 E3.56274\n" +
                        "G1 X110.574 Y91.845 E3.59220\n" +
                        "G1 X111.125 Y92.039 E3.60426\n" +
                        "G1 X111.862 Y92.396 E3.62113\n" +
                        "G1 X111.532 Y92.735 F7800.000\n" +
                        "G1 E1.62113 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.088 Y92.565 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X89.442 Y93.558 E2.02846\n" +
                        "G1 X90.856 Y97.972 E2.15349\n" +
                        "G1 X91.776 Y101.144 E2.24258\n" +
                        "G1 X92.240 Y102.861 E2.29056\n" +
                        "G1 X92.678 Y104.808 E2.34441\n" +
                        "G1 X93.218 Y106.886 E2.40230\n" +
                        "G1 X92.846 Y107.150 E2.41461\n" +
                        "G1 X91.910 Y107.608 E2.44271\n" +
                        "G1 X91.938 Y106.295 E2.47815\n" +
                        "G1 X91.800 Y104.230 E2.53398\n" +
                        "G1 X91.637 Y102.981 E2.56795\n" +
                        "G1 X91.372 Y101.476 E2.60917\n" +
                        "G1 X91.240 Y100.903 E2.62503\n" +
                        "G1 X89.950 Y95.917 E2.76397\n" +
                        "G1 X89.402 Y94.361 E2.80847\n" +
                        "G1 X88.773 Y92.701 E2.85636\n" +
                        "G1 X89.019 Y92.594 E2.86359\n" +
                        "G1 X89.428 Y91.848 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X89.974 Y93.379 E2.89708\n" +
                        "G1 X91.393 Y97.809 E2.99296\n" +
                        "G1 X92.315 Y100.993 E3.06129\n" +
                        "G1 X92.784 Y102.726 E3.09829\n" +
                        "G1 X93.223 Y104.673 E3.13943\n" +
                        "G1 X93.449 Y105.508 E3.15726\n" +
                        "G1 X93.671 Y106.444 E3.17709\n" +
                        "G1 X93.846 Y107.066 E3.19039\n" +
                        "G1 X93.640 Y107.266 E3.19630\n" +
                        "G1 X93.389 Y107.466 E3.20293\n" +
                        "G1 X93.123 Y107.639 E3.20947\n" +
                        "G1 X92.156 Y108.113 E3.23166\n" +
                        "G1 X91.266 Y108.478 E3.25149\n" +
                        "G1 X91.348 Y107.681 E3.26801\n" +
                        "G1 X91.377 Y106.307 E3.29632\n" +
                        "G1 X91.242 Y104.285 E3.33810\n" +
                        "G1 X91.082 Y103.066 E3.36343\n" +
                        "G1 X90.822 Y101.588 E3.39437\n" +
                        "G1 X90.695 Y101.037 E3.40603\n" +
                        "G1 X89.413 Y96.081 E3.51153\n" +
                        "G1 X88.876 Y94.554 E3.54490\n" +
                        "G1 X88.072 Y92.431 E3.59168\n" +
                        "G1 X88.497 Y92.210 E3.60157\n" +
                        "G1 X89.093 Y91.952 E3.61494\n" +
                        "G1 X89.356 Y91.870 E3.62063\n" +
                        "G1 X89.342 Y92.340 F7800.000\n" +
                        "G1 E1.62063 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.569 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.569 Y107.981 E2.02853\n" +
                        "G1 X28.196 Y107.981 E2.09256\n" +
                        "G1 X28.196 Y106.924 E2.12109\n" +
                        "G1 X30.494 Y106.924 E2.18308\n" +
                        "G1 E0.18308 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.005 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.205 Y106.288 E2.08632\n" +
                        "G1 X31.205 Y108.617 E2.14914\n" +
                        "G1 X28.005 Y108.617 E2.23547\n" +
                        "G1 X28.005 Y111.919 E2.32455\n" +
                        "G1 X27.828 Y111.919 E2.32931\n" +
                        "G1 X27.719 Y111.908 E2.33227\n" +
                        "G1 X27.609 Y111.849 E2.33564\n" +
                        "G1 X27.568 Y111.782 E2.33776\n" +
                        "G1 X27.560 Y111.708 E2.33978\n" +
                        "G1 X27.560 Y104.137 E2.54401\n" +
                        "G1 X27.583 Y103.995 E2.54788\n" +
                        "G1 X27.616 Y103.938 E2.54968\n" +
                        "G1 X28.005 Y103.938 E2.56015\n" +
                        "G1 X28.005 Y106.213 E2.62152\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.765 Y105.727 E2.68747\n" +
                        "G1 X31.766 Y109.177 E2.75858\n" +
                        "G1 X28.565 Y109.177 E2.82454\n" +
                        "G1 X28.565 Y112.479 E2.89259\n" +
                        "G1 X27.801 Y112.479 E2.90834\n" +
                        "G1 X27.624 Y112.462 E2.91201\n" +
                        "G1 X27.388 Y112.377 E2.91718\n" +
                        "G1 X27.194 Y112.229 E2.92221\n" +
                        "G1 X27.109 Y112.119 E2.92509\n" +
                        "G1 X27.038 Y111.975 E2.92839\n" +
                        "G1 X26.999 Y111.739 E2.93332\n" +
                        "G1 X27.000 Y104.093 E3.09090\n" +
                        "G1 X27.039 Y103.846 E3.09606\n" +
                        "G1 X27.153 Y103.617 E3.10133\n" +
                        "G1 X27.321 Y103.449 E3.10623\n" +
                        "G1 X27.445 Y103.377 E3.10919\n" +
                        "G1 X28.565 Y103.377 E3.13227\n" +
                        "G1 X28.565 Y105.652 E3.17916\n" +
                        "G1 X28.639 Y106.222 F7800.000\n" +
                        "G1 Z3.650 F7800.000\n" +
                        "G1 E1.17916 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.432 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.911 Y106.924 E2.06688\n" +
                        "G1 X171.910 Y107.981 E2.09540\n" +
                        "G1 X169.431 Y107.981 E2.16228\n" +
                        "G1 X169.432 Y106.999 E2.18878\n" +
                        "G1 E0.18878 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.995 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.995 Y103.938 E2.06340\n" +
                        "G1 X172.511 Y103.938 E2.07730\n" +
                        "G1 X172.548 Y104.114 E2.08214\n" +
                        "G1 X172.544 Y111.765 E2.28856\n" +
                        "G1 X172.526 Y111.815 E2.28998\n" +
                        "G1 X172.457 Y111.878 E2.29250\n" +
                        "G1 X172.349 Y111.915 E2.29559\n" +
                        "G1 X171.995 Y111.916 E2.30512\n" +
                        "G1 X171.995 Y108.617 E2.39413\n" +
                        "G1 X168.795 Y108.617 E2.48045\n" +
                        "G1 X168.796 Y106.288 E2.54327\n" +
                        "G1 X171.920 Y106.288 E2.62755\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.377 E2.67599\n" +
                        "G1 X172.763 Y103.377 E2.70337\n" +
                        "G1 X172.931 Y103.548 E2.70830\n" +
                        "G1 X173.011 Y103.677 E2.71142\n" +
                        "G1 X173.071 Y103.833 E2.71488\n" +
                        "G1 X173.109 Y104.070 E2.71982\n" +
                        "G1 X173.104 Y111.828 E2.87972\n" +
                        "G1 X173.071 Y111.973 E2.88278\n" +
                        "G1 X172.955 Y112.182 E2.88772\n" +
                        "G1 X172.785 Y112.338 E2.89246\n" +
                        "G1 X172.647 Y112.413 E2.89571\n" +
                        "G1 X172.484 Y112.463 E2.89921\n" +
                        "G1 X171.435 Y112.478 E2.92085\n" +
                        "G1 X171.435 Y109.177 E2.98888\n" +
                        "G1 X168.234 Y109.177 E3.05484\n" +
                        "G1 X168.236 Y105.727 E3.12595\n" +
                        "G1 X171.360 Y105.727 E3.19033\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E1.19033 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X111.007 Y93.234 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.466 Y94.711 E2.04244\n" +
                        "G1 X109.999 Y96.086 E2.08161\n" +
                        "G1 X108.791 Y100.768 E2.21206\n" +
                        "G1 X108.628 Y101.476 E2.23165\n" +
                        "G1 X108.362 Y102.989 E2.27309\n" +
                        "G1 X108.260 Y103.699 E2.29242\n" +
                        "G1 X108.174 Y104.495 E2.31402\n" +
                        "G1 X108.061 Y106.410 E2.36579\n" +
                        "G1 X108.074 Y106.957 E2.38053\n" +
                        "G1 X107.565 Y106.706 E2.39584\n" +
                        "G1 X107.289 Y106.539 E2.40454\n" +
                        "G1 X107.102 Y106.385 E2.41107\n" +
                        "G1 X107.218 Y105.205 E2.44306\n" +
                        "G1 X107.760 Y102.863 E2.50790\n" +
                        "G1 X108.226 Y101.138 E2.55613\n" +
                        "G1 X109.158 Y97.926 E2.64634\n" +
                        "G1 X110.717 Y93.106 E2.78299\n" +
                        "G1 X110.938 Y93.204 E2.78951\n" +
                        "G1 X111.708 Y92.974 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.994 Y94.899 E2.83182\n" +
                        "G1 X110.537 Y96.246 E2.86115\n" +
                        "G1 X109.336 Y100.901 E2.96023\n" +
                        "G1 X109.178 Y101.588 E2.97475\n" +
                        "G1 X108.916 Y103.078 E3.00593\n" +
                        "G1 X108.816 Y103.769 E3.02031\n" +
                        "G1 X108.733 Y104.541 E3.03633\n" +
                        "G1 X108.622 Y106.420 E3.07512\n" +
                        "G1 X108.665 Y107.835 E3.10430\n" +
                        "G1 X107.977 Y107.534 E3.11978\n" +
                        "G1 X107.296 Y107.198 E3.13545\n" +
                        "G1 X106.970 Y107.000 E3.14331\n" +
                        "G1 X106.751 Y106.828 E3.14905\n" +
                        "G1 X106.556 Y106.630 E3.15478\n" +
                        "G1 X106.537 Y106.558 E3.15630\n" +
                        "G1 X106.538 Y106.392 E3.15972\n" +
                        "G1 X106.664 Y105.114 E3.18619\n" +
                        "G1 X107.216 Y102.727 E3.23669\n" +
                        "G1 X107.686 Y100.986 E3.27385\n" +
                        "G1 X108.622 Y97.763 E3.34304\n" +
                        "G1 X110.027 Y93.378 E3.43793\n" +
                        "G1 X110.375 Y92.391 E3.45951\n" +
                        "G1 X110.910 Y92.579 E3.47120\n" +
                        "G1 X111.252 Y92.729 E3.47891\n" +
                        "G1 X111.642 Y92.939 E3.48803\n" +
                        "G1 X111.312 Y93.279 F7800.000\n" +
                        "G1 E1.48803 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.287 Y93.115 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.972 E2.13767\n" +
                        "G1 X91.903 Y101.610 E2.23980\n" +
                        "G1 X92.240 Y102.863 E2.27481\n" +
                        "G1 X92.782 Y105.205 E2.33965\n" +
                        "G1 X92.897 Y106.388 E2.37173\n" +
                        "G1 X92.640 Y106.585 E2.38046\n" +
                        "G1 X91.927 Y106.953 E2.40212\n" +
                        "G1 X91.939 Y106.410 E2.41677\n" +
                        "G1 X91.825 Y104.488 E2.46869\n" +
                        "G1 X91.642 Y103.016 E2.50871\n" +
                        "G1 X91.372 Y101.476 E2.55089\n" +
                        "G1 X91.209 Y100.768 E2.57049\n" +
                        "G1 X89.978 Y96.012 E2.70303\n" +
                        "G1 X89.477 Y94.554 E2.74461\n" +
                        "G1 X88.990 Y93.243 E2.78235\n" +
                        "G1 X89.216 Y93.142 E2.78903\n" +
                        "G1 X89.626 Y92.391 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X89.973 Y93.378 E2.81060\n" +
                        "G1 X91.393 Y97.809 E2.90648\n" +
                        "G1 X92.443 Y101.459 E2.98478\n" +
                        "G1 X92.784 Y102.727 E3.01183\n" +
                        "G1 X93.336 Y105.114 E3.06233\n" +
                        "G1 X93.466 Y106.452 E3.09004\n" +
                        "G1 X93.448 Y106.632 E3.09376\n" +
                        "G1 X93.253 Y106.825 E3.09942\n" +
                        "G1 X92.940 Y107.062 E3.10751\n" +
                        "G1 X92.020 Y107.535 E3.12882\n" +
                        "G1 X91.334 Y107.835 E3.14426\n" +
                        "G1 X91.378 Y106.420 E3.17346\n" +
                        "G1 X91.267 Y104.540 E3.21227\n" +
                        "G1 X91.088 Y103.099 E3.24219\n" +
                        "G1 X90.822 Y101.588 E3.27382\n" +
                        "G1 X90.664 Y100.901 E3.28834\n" +
                        "G1 X89.441 Y96.173 E3.38900\n" +
                        "G1 X88.949 Y94.743 E3.42017\n" +
                        "G1 X88.293 Y92.976 E3.45902\n" +
                        "G1 X88.859 Y92.678 E3.47220\n" +
                        "G1 X89.555 Y92.414 E3.48754\n" +
                        "G1 X89.546 Y92.885 F7800.000\n" +
                        "G1 E1.48754 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.568 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.569 Y107.981 E2.02853\n" +
                        "G1 X28.087 Y107.981 E2.09548\n" +
                        "G1 X28.087 Y106.924 E2.12401\n" +
                        "G1 X30.493 Y106.924 E2.18892\n" +
                        "G1 E0.18892 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.005 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.204 Y106.288 E2.08630\n" +
                        "G1 X31.205 Y108.617 E2.14912\n" +
                        "G1 X28.005 Y108.617 E2.23545\n" +
                        "G1 X28.005 Y111.919 E2.32452\n" +
                        "G1 X27.717 Y111.919 E2.33228\n" +
                        "G1 X27.607 Y111.907 E2.33527\n" +
                        "G1 X27.500 Y111.850 E2.33854\n" +
                        "G1 X27.459 Y111.782 E2.34068\n" +
                        "G1 X27.451 Y111.708 E2.34269\n" +
                        "G1 X27.452 Y104.114 E2.54755\n" +
                        "G1 X27.489 Y103.938 E2.55240\n" +
                        "G1 X28.005 Y103.938 E2.56630\n" +
                        "G1 X28.005 Y106.213 E2.62768\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.764 Y105.727 E2.69361\n" +
                        "G1 X31.766 Y109.177 E2.76471\n" +
                        "G1 X28.565 Y109.177 E2.83067\n" +
                        "G1 X28.565 Y112.479 E2.89873\n" +
                        "G1 X27.530 Y112.465 E2.92006\n" +
                        "G1 X27.279 Y112.377 E2.92554\n" +
                        "G1 X27.073 Y112.216 E2.93093\n" +
                        "G1 X26.935 Y111.989 E2.93642\n" +
                        "G1 X26.890 Y111.739 E2.94165\n" +
                        "G1 X26.891 Y104.070 E3.09971\n" +
                        "G1 X26.929 Y103.833 E3.10465\n" +
                        "G1 X26.989 Y103.677 E3.10811\n" +
                        "G1 X27.069 Y103.548 E3.11123\n" +
                        "G1 X27.237 Y103.377 E3.11616\n" +
                        "G1 X28.565 Y103.377 E3.14354\n" +
                        "G1 X28.565 Y105.652 E3.19043\n" +
                        "G1 X28.651 Y106.220 F7800.000\n" +
                        "M106 S221.85\n" +
                        "G1 Z3.950 F7800.000\n" +
                        "G1 E1.19043 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.432 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X172.019 Y106.924 E2.06979\n" +
                        "G1 X172.019 Y107.981 E2.09832\n" +
                        "G1 X169.432 Y107.981 E2.16811\n" +
                        "G1 X169.432 Y106.999 E2.19462\n" +
                        "G1 E0.19462 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.995 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.995 Y103.938 E2.06340\n" +
                        "G1 X172.628 Y103.938 E2.08047\n" +
                        "G1 X172.656 Y104.091 E2.08466\n" +
                        "G1 X172.653 Y111.765 E2.29170\n" +
                        "G1 X172.633 Y111.819 E2.29323\n" +
                        "G1 X172.569 Y111.876 E2.29556\n" +
                        "G1 X172.445 Y111.916 E2.29908\n" +
                        "G1 X171.995 Y111.917 E2.31121\n" +
                        "G1 X171.995 Y108.617 E2.40023\n" +
                        "G1 X168.796 Y108.617 E2.48655\n" +
                        "G1 X168.797 Y106.288 E2.54937\n" +
                        "G1 X171.920 Y106.288 E2.63363\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.377 E2.68206\n" +
                        "G1 X172.927 Y103.377 E2.71283\n" +
                        "G1 X173.054 Y103.535 E2.71700\n" +
                        "G1 X173.129 Y103.672 E2.72022\n" +
                        "G1 X173.181 Y103.818 E2.72340\n" +
                        "G1 X173.217 Y104.047 E2.72818\n" +
                        "G1 X173.213 Y111.828 E2.88855\n" +
                        "G1 X173.181 Y111.973 E2.89162\n" +
                        "G1 X173.053 Y112.195 E2.89689\n" +
                        "G1 X172.896 Y112.337 E2.90127\n" +
                        "G1 X172.756 Y112.413 E2.90454\n" +
                        "G1 X172.594 Y112.462 E2.90802\n" +
                        "G1 X171.435 Y112.479 E2.93193\n" +
                        "G1 X171.435 Y109.177 E2.99997\n" +
                        "G1 X168.235 Y109.177 E3.06593\n" +
                        "G1 X168.237 Y105.727 E3.13703\n" +
                        "G1 X171.360 Y105.727 E3.20140\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E1.20140 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.842 Y93.581 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.872 Y93.597 E2.00092\n" +
                        "G1 X110.293 Y95.190 E2.04665\n" +
                        "G1 X109.986 Y96.130 E2.07332\n" +
                        "G1 X108.823 Y100.633 E2.19879\n" +
                        "G1 X108.628 Y101.476 E2.22214\n" +
                        "G1 X108.360 Y103.002 E2.26393\n" +
                        "G1 X108.260 Y103.697 E2.28285\n" +
                        "G1 X108.173 Y104.506 E2.30482\n" +
                        "G1 X108.061 Y106.337 E2.35431\n" +
                        "G1 X108.067 Y106.527 E2.35943\n" +
                        "G1 X107.650 Y106.321 E2.37196\n" +
                        "G1 X107.445 Y106.179 E2.37869\n" +
                        "G1 X107.301 Y106.042 E2.38406\n" +
                        "G1 X107.269 Y105.471 E2.39949\n" +
                        "G1 X107.268 Y105.011 E2.41191\n" +
                        "G1 X107.772 Y102.819 E2.47258\n" +
                        "G1 X108.228 Y101.131 E2.51975\n" +
                        "G1 X109.144 Y97.972 E2.60846\n" +
                        "G1 X110.589 Y93.470 E2.73603\n" +
                        "G1 X110.774 Y93.551 E2.74148\n" +
                        "G1 X111.565 Y93.332 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.823 Y95.373 E2.78622\n" +
                        "G1 X110.525 Y96.287 E2.80605\n" +
                        "G1 X109.367 Y100.766 E2.90139\n" +
                        "G1 X109.178 Y101.588 E2.91877\n" +
                        "G1 X108.914 Y103.091 E2.95022\n" +
                        "G1 X108.816 Y103.767 E2.96430\n" +
                        "G1 X108.732 Y104.553 E2.98060\n" +
                        "G1 X108.622 Y106.347 E3.01763\n" +
                        "G1 X108.652 Y107.406 E3.03947\n" +
                        "G1 X108.137 Y107.187 E3.05099\n" +
                        "G1 X107.366 Y106.805 E3.06873\n" +
                        "G1 X107.090 Y106.616 E3.07561\n" +
                        "G1 X106.830 Y106.368 E3.08302\n" +
                        "G1 X106.769 Y106.248 E3.08581\n" +
                        "G1 X106.737 Y106.077 E3.08938\n" +
                        "G1 X106.708 Y105.485 E3.10160\n" +
                        "G1 X106.707 Y104.948 E3.11268\n" +
                        "G1 X107.228 Y102.683 E3.16058\n" +
                        "G1 X107.688 Y100.979 E3.19694\n" +
                        "G1 X108.607 Y97.809 E3.26498\n" +
                        "G1 X110.027 Y93.378 E3.36087\n" +
                        "G1 X110.247 Y92.749 E3.37461\n" +
                        "G1 X110.762 Y92.934 E3.38590\n" +
                        "G1 X111.088 Y93.076 E3.39322\n" +
                        "G1 X111.499 Y93.297 E3.40284\n" +
                        "G1 X111.170 Y93.639 F7800.000\n" +
                        "G1 E1.40284 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.442 Y93.556 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.972 E2.12510\n" +
                        "G1 X91.772 Y101.131 E2.21381\n" +
                        "G1 X92.240 Y102.863 E2.26221\n" +
                        "G1 X92.730 Y105.001 E2.32138\n" +
                        "G1 X92.697 Y106.049 E2.34967\n" +
                        "G1 X92.564 Y106.173 E2.35458\n" +
                        "G1 X92.303 Y106.347 E2.36304\n" +
                        "G1 X91.936 Y106.527 E2.37407\n" +
                        "G1 X91.938 Y106.291 E2.38045\n" +
                        "G1 X91.827 Y104.499 E2.42889\n" +
                        "G1 X91.646 Y103.040 E2.46853\n" +
                        "G1 X91.372 Y101.476 E2.51136\n" +
                        "G1 X91.177 Y100.633 E2.53470\n" +
                        "G1 X90.047 Y96.248 E2.65687\n" +
                        "G1 X89.708 Y95.192 E2.68680\n" +
                        "G1 X89.128 Y93.597 E2.73258\n" +
                        "G1 X89.417 Y93.485 E2.74094\n" +
                        "G1 X89.754 Y92.752 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X89.973 Y93.378 E2.75461\n" +
                        "G1 X91.393 Y97.809 E2.85050\n" +
                        "G1 X92.312 Y100.979 E2.91854\n" +
                        "G1 X92.784 Y102.727 E2.95585\n" +
                        "G1 X93.292 Y104.944 E3.00274\n" +
                        "G1 X93.263 Y106.078 E3.02612\n" +
                        "G1 X93.220 Y106.290 E3.03056\n" +
                        "G1 X93.192 Y106.345 E3.03183\n" +
                        "G1 X92.912 Y106.614 E3.03984\n" +
                        "G1 X92.583 Y106.834 E3.04800\n" +
                        "G1 X91.864 Y107.187 E3.06452\n" +
                        "G1 X91.355 Y107.403 E3.07589\n" +
                        "G1 X91.377 Y106.306 E3.09850\n" +
                        "G1 X91.268 Y104.551 E3.13475\n" +
                        "G1 X91.091 Y103.123 E3.16439\n" +
                        "G1 X90.822 Y101.588 E3.19652\n" +
                        "G1 X90.633 Y100.766 E3.21389\n" +
                        "G1 X89.508 Y96.404 E3.30674\n" +
                        "G1 X89.177 Y95.373 E3.32905\n" +
                        "G1 X88.435 Y93.332 E3.37381\n" +
                        "G1 X88.909 Y93.078 E3.38491\n" +
                        "G1 X89.682 Y92.771 E3.40204\n" +
                        "G1 X89.666 Y93.244 F7800.000\n" +
                        "G1 E1.40204 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.568 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.568 Y107.981 E2.02853\n" +
                        "G1 X27.978 Y107.981 E2.09840\n" +
                        "G1 X27.978 Y106.924 E2.12693\n" +
                        "G1 X30.493 Y106.924 E2.19476\n" +
                        "G1 E0.19476 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.005 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.203 Y106.288 E2.08628\n" +
                        "G1 X31.204 Y108.617 E2.14910\n" +
                        "G1 X28.005 Y108.617 E2.23542\n" +
                        "G1 X28.005 Y111.919 E2.32450\n" +
                        "G1 X27.622 Y111.919 E2.33482\n" +
                        "G1 X27.519 Y111.906 E2.33763\n" +
                        "G1 X27.401 Y111.857 E2.34107\n" +
                        "G1 X27.349 Y111.778 E2.34361\n" +
                        "G1 X27.341 Y111.708 E2.34551\n" +
                        "G1 X27.344 Y104.091 E2.55099\n" +
                        "G1 X27.374 Y103.938 E2.55520\n" +
                        "G1 X28.005 Y103.938 E2.57221\n" +
                        "G1 X28.005 Y106.213 E2.63359\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.763 Y105.727 E2.69950\n" +
                        "G1 X31.765 Y109.177 E2.77060\n" +
                        "G1 X28.565 Y109.177 E2.83656\n" +
                        "G1 X28.565 Y112.479 E2.90462\n" +
                        "G1 X27.587 Y112.479 E2.92478\n" +
                        "G1 X27.373 Y112.452 E2.92922\n" +
                        "G1 X27.121 Y112.347 E2.93485\n" +
                        "G1 X26.931 Y112.176 E2.94012\n" +
                        "G1 X26.830 Y112.000 E2.94431\n" +
                        "G1 X26.799 Y111.904 E2.94639\n" +
                        "G1 X26.781 Y111.739 E2.94980\n" +
                        "G1 X26.783 Y104.047 E3.10834\n" +
                        "G1 X26.819 Y103.821 E3.11306\n" +
                        "G1 X26.892 Y103.627 E3.11734\n" +
                        "G1 X26.981 Y103.485 E3.12078\n" +
                        "G1 X27.084 Y103.377 E3.12386\n" +
                        "G1 X28.565 Y103.377 E3.15438\n" +
                        "G1 X28.565 Y105.652 E3.20127\n" +
                        "G1 X28.658 Y106.219 F7800.000\n" +
                        "G1 Z4.250 F7800.000\n" +
                        "G1 E1.20127 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.433 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X172.132 Y106.924 E2.07282\n" +
                        "G1 X172.132 Y107.981 E2.10134\n" +
                        "G1 X169.432 Y107.981 E2.17419\n" +
                        "G1 X169.433 Y106.999 E2.20069\n" +
                        "G1 E0.20069 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.995 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.995 Y103.938 E2.06340\n" +
                        "G1 X172.744 Y103.938 E2.08359\n" +
                        "G1 X172.768 Y104.120 E2.08853\n" +
                        "G1 X172.768 Y111.690 E2.29275\n" +
                        "G1 X172.752 Y111.793 E2.29555\n" +
                        "G1 X172.691 Y111.870 E2.29821\n" +
                        "G1 X172.625 Y111.902 E2.30020\n" +
                        "G1 X172.488 Y111.919 E2.30392\n" +
                        "G1 X171.995 Y111.919 E2.31720\n" +
                        "G1 X171.995 Y108.617 E2.40628\n" +
                        "G1 X168.796 Y108.617 E2.49259\n" +
                        "G1 X168.798 Y106.288 E2.55541\n" +
                        "G1 X171.920 Y106.288 E2.63965\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.377 E2.68808\n" +
                        "G1 X173.073 Y103.377 E2.72184\n" +
                        "G1 X173.207 Y103.581 E2.72687\n" +
                        "G1 X173.273 Y103.742 E2.73045\n" +
                        "G1 X173.314 Y103.912 E2.73407\n" +
                        "G1 X173.328 Y104.098 E2.73790\n" +
                        "G1 X173.328 Y111.733 E2.89527\n" +
                        "G1 X173.294 Y111.953 E2.89985\n" +
                        "G1 X173.223 Y112.112 E2.90345\n" +
                        "G1 X173.130 Y112.232 E2.90658\n" +
                        "G1 X173.009 Y112.335 E2.90986\n" +
                        "G1 X172.864 Y112.414 E2.91326\n" +
                        "G1 X172.745 Y112.452 E2.91582\n" +
                        "G1 X172.522 Y112.479 E2.92046\n" +
                        "G1 X171.435 Y112.479 E2.94287\n" +
                        "G1 X171.435 Y109.177 E3.01093\n" +
                        "G1 X168.235 Y109.177 E3.07688\n" +
                        "G1 X168.238 Y105.727 E3.14799\n" +
                        "G1 X171.360 Y105.727 E3.21233\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E1.21233 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.717 Y93.833 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.770 Y93.861 E2.00161\n" +
                        "G1 X110.253 Y95.300 E2.04285\n" +
                        "G1 X109.947 Y96.263 E2.07010\n" +
                        "G1 X108.854 Y100.498 E2.18810\n" +
                        "G1 X108.628 Y101.476 E2.21518\n" +
                        "G1 X108.358 Y103.015 E2.25732\n" +
                        "G1 X108.260 Y103.694 E2.27584\n" +
                        "G1 X108.174 Y104.490 E2.29744\n" +
                        "G1 X108.069 Y106.210 E2.34393\n" +
                        "G1 X107.793 Y106.073 E2.35226\n" +
                        "G1 X107.615 Y105.952 E2.35805\n" +
                        "G1 X107.450 Y105.790 E2.36429\n" +
                        "G1 X107.413 Y105.680 E2.36743\n" +
                        "G1 X107.321 Y105.128 E2.38251\n" +
                        "G1 X107.310 Y104.845 E2.39015\n" +
                        "G1 X107.775 Y102.806 E2.44658\n" +
                        "G1 X108.230 Y101.124 E2.49359\n" +
                        "G1 X109.144 Y97.972 E2.58210\n" +
                        "G1 X110.501 Y93.736 E2.70210\n" +
                        "G1 X110.648 Y93.802 E2.70647\n" +
                        "G1 X111.460 Y93.597 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.784 Y95.480 E2.74769\n" +
                        "G1 X110.486 Y96.418 E2.76798\n" +
                        "G1 X109.398 Y100.631 E2.85766\n" +
                        "G1 X109.178 Y101.588 E2.87790\n" +
                        "G1 X108.912 Y103.104 E2.90962\n" +
                        "G1 X108.817 Y103.765 E2.92338\n" +
                        "G1 X108.733 Y104.538 E2.93941\n" +
                        "G1 X108.622 Y106.351 E2.97686\n" +
                        "G1 X108.635 Y107.094 E2.99217\n" +
                        "G1 X108.258 Y106.931 E3.00065\n" +
                        "G1 X107.509 Y106.558 E3.01789\n" +
                        "G1 X107.266 Y106.393 E3.02394\n" +
                        "G1 X107.005 Y106.141 E3.03140\n" +
                        "G1 X106.935 Y106.019 E3.03431\n" +
                        "G1 X106.868 Y105.814 E3.03876\n" +
                        "G1 X106.763 Y105.186 E3.05188\n" +
                        "G1 X106.747 Y104.793 E3.05998\n" +
                        "G1 X107.231 Y102.670 E3.10486\n" +
                        "G1 X107.690 Y100.973 E3.14111\n" +
                        "G1 X108.607 Y97.809 E3.20900\n" +
                        "G1 X110.152 Y93.019 E3.31272\n" +
                        "G1 X110.657 Y93.192 E3.32373\n" +
                        "G1 X110.965 Y93.330 E3.33068\n" +
                        "G1 X111.394 Y93.562 E3.34073\n" +
                        "G1 X111.066 Y93.904 F7800.000\n" +
                        "G1 E1.34073 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.491 Y93.748 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.857 Y97.973 E2.11980\n" +
                        "G1 X91.770 Y101.124 E2.20828\n" +
                        "G1 X92.240 Y102.862 E2.25685\n" +
                        "G1 X92.690 Y104.845 E2.31171\n" +
                        "G1 X92.679 Y105.128 E2.31937\n" +
                        "G1 X92.587 Y105.676 E2.33433\n" +
                        "G1 X92.549 Y105.788 E2.33754\n" +
                        "G1 X92.382 Y105.954 E2.34389\n" +
                        "G1 X92.207 Y106.073 E2.34958\n" +
                        "G1 X91.932 Y106.210 E2.35787\n" +
                        "G1 X91.823 Y104.451 E2.40541\n" +
                        "G1 X91.650 Y103.064 E2.44310\n" +
                        "G1 X91.372 Y101.477 E2.48658\n" +
                        "G1 X91.146 Y100.498 E2.51366\n" +
                        "G1 X90.098 Y96.424 E2.62714\n" +
                        "G1 X89.748 Y95.302 E2.65887\n" +
                        "G1 X89.230 Y93.861 E2.70015\n" +
                        "G1 X89.422 Y93.776 E2.70581\n" +
                        "G1 X89.847 Y93.025 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.809 E2.80943\n" +
                        "G1 X92.310 Y100.973 E2.87731\n" +
                        "G1 X92.784 Y102.726 E2.91476\n" +
                        "G1 X93.253 Y104.793 E2.95844\n" +
                        "G1 X93.237 Y105.186 E2.96654\n" +
                        "G1 X93.133 Y105.813 E2.97964\n" +
                        "G1 X93.058 Y106.034 E2.98446\n" +
                        "G1 X92.967 Y106.175 E2.98790\n" +
                        "G1 X92.733 Y106.393 E2.99450\n" +
                        "G1 X92.491 Y106.558 E3.00053\n" +
                        "G1 X91.742 Y106.931 E3.01777\n" +
                        "G1 X91.364 Y107.094 E3.02626\n" +
                        "G1 X91.377 Y106.306 E3.04250\n" +
                        "G1 X91.264 Y104.503 E3.07974\n" +
                        "G1 X91.095 Y103.147 E3.10790\n" +
                        "G1 X90.822 Y101.588 E3.14053\n" +
                        "G1 X90.602 Y100.631 E3.16076\n" +
                        "G1 X89.559 Y96.578 E3.24702\n" +
                        "G1 X89.216 Y95.480 E3.27072\n" +
                        "G1 X88.540 Y93.597 E3.31196\n" +
                        "G1 X89.032 Y93.332 E3.32347\n" +
                        "G1 X89.600 Y93.095 E3.33616\n" +
                        "G1 X89.775 Y93.046 E3.33991\n" +
                        "G1 X89.750 Y93.516 F7800.000\n" +
                        "G1 E1.33991 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.567 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.568 Y107.981 E2.02853\n" +
                        "G1 X27.869 Y107.981 E2.10133\n" +
                        "G1 X27.870 Y106.924 E2.12985\n" +
                        "G1 X30.492 Y106.924 E2.20059\n" +
                        "G1 E0.20059 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.005 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.202 Y106.288 E2.08626\n" +
                        "G1 X31.204 Y108.617 E2.14908\n" +
                        "G1 X28.005 Y108.617 E2.23539\n" +
                        "G1 X28.005 Y111.919 E2.32447\n" +
                        "G1 X27.516 Y111.919 E2.33765\n" +
                        "G1 X27.395 Y111.903 E2.34095\n" +
                        "G1 X27.295 Y111.860 E2.34387\n" +
                        "G1 X27.241 Y111.783 E2.34642\n" +
                        "G1 X27.232 Y111.708 E2.34845\n" +
                        "G1 X27.235 Y104.068 E2.55456\n" +
                        "G1 X27.256 Y103.938 E2.55811\n" +
                        "G1 X28.005 Y103.938 E2.57829\n" +
                        "G1 X28.005 Y106.213 E2.63967\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.762 Y105.727 E2.70556\n" +
                        "G1 X31.765 Y109.177 E2.77666\n" +
                        "G1 X28.565 Y109.177 E2.84262\n" +
                        "G1 X28.565 Y112.479 E2.91068\n" +
                        "G1 X27.479 Y112.479 E2.93307\n" +
                        "G1 X27.247 Y112.448 E2.93788\n" +
                        "G1 X27.027 Y112.356 E2.94281\n" +
                        "G1 X26.929 Y112.288 E2.94528\n" +
                        "G1 X26.821 Y112.175 E2.94849\n" +
                        "G1 X26.727 Y112.015 E2.95230\n" +
                        "G1 X26.690 Y111.903 E2.95475\n" +
                        "G1 X26.672 Y111.739 E2.95815\n" +
                        "G1 X26.675 Y104.024 E3.11716\n" +
                        "G1 X26.709 Y103.805 E3.12173\n" +
                        "G1 X26.766 Y103.641 E3.12531\n" +
                        "G1 X26.846 Y103.488 E3.12886\n" +
                        "G1 X26.934 Y103.377 E3.13178\n" +
                        "G1 X28.565 Y103.377 E3.16539\n" +
                        "G1 X28.565 Y105.652 E3.21228\n" +
                        "G1 X28.666 Y106.217 F7800.000\n" +
                        "G1 Z4.550 F7800.000\n" +
                        "G1 E1.21228 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.434 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X172.241 Y106.924 E2.07575\n" +
                        "G1 X172.241 Y107.981 E2.10427\n" +
                        "G1 X169.432 Y107.981 E2.18005\n" +
                        "G1 X169.433 Y106.999 E2.20655\n" +
                        "G1 E0.20655 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.995 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.995 Y103.938 E2.06340\n" +
                        "G1 X172.856 Y103.938 E2.08661\n" +
                        "G1 X172.877 Y104.120 E2.09154\n" +
                        "G1 X172.877 Y111.687 E2.29567\n" +
                        "G1 X172.851 Y111.818 E2.29929\n" +
                        "G1 X172.800 Y111.870 E2.30125\n" +
                        "G1 X172.722 Y111.905 E2.30354\n" +
                        "G1 X171.995 Y111.919 E2.32316\n" +
                        "G1 X171.995 Y108.617 E2.41224\n" +
                        "G1 X168.796 Y108.617 E2.49855\n" +
                        "G1 X168.799 Y106.288 E2.56137\n" +
                        "G1 X171.920 Y106.288 E2.64558\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.377 E2.69402\n" +
                        "G1 X173.208 Y103.377 E2.73056\n" +
                        "G1 X173.305 Y103.532 E2.73432\n" +
                        "G1 X173.377 Y103.708 E2.73825\n" +
                        "G1 X173.422 Y103.895 E2.74222\n" +
                        "G1 X173.438 Y104.098 E2.74640\n" +
                        "G1 X173.438 Y111.732 E2.90375\n" +
                        "G1 X173.399 Y111.969 E2.90869\n" +
                        "G1 X173.333 Y112.111 E2.91191\n" +
                        "G1 X173.240 Y112.232 E2.91508\n" +
                        "G1 X173.118 Y112.335 E2.91836\n" +
                        "G1 X172.974 Y112.413 E2.92175\n" +
                        "G1 X172.839 Y112.456 E2.92465\n" +
                        "G1 X172.633 Y112.479 E2.92893\n" +
                        "G1 X171.435 Y112.479 E2.95362\n" +
                        "G1 X171.435 Y109.177 E3.02168\n" +
                        "G1 X168.235 Y109.177 E3.08763\n" +
                        "G1 X168.239 Y105.727 E3.15874\n" +
                        "G1 X171.360 Y105.727 E3.22306\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E1.22306 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.689 Y94.071 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.271 Y95.243 E2.03357\n" +
                        "G1 X109.907 Y96.402 E2.06634\n" +
                        "G1 X108.885 Y100.363 E2.17669\n" +
                        "G1 X108.628 Y101.477 E2.20752\n" +
                        "G1 X108.355 Y103.036 E2.25022\n" +
                        "G1 X108.176 Y104.468 E2.28917\n" +
                        "G1 X108.084 Y105.978 E2.32996\n" +
                        "G1 X107.904 Y105.889 E2.33537\n" +
                        "G1 X107.756 Y105.786 E2.34024\n" +
                        "G1 X107.631 Y105.670 E2.34484\n" +
                        "G1 X107.549 Y105.557 E2.34859\n" +
                        "G1 X107.434 Y105.267 E2.35701\n" +
                        "G1 X107.361 Y104.926 E2.36641\n" +
                        "G1 X107.354 Y104.664 E2.37349\n" +
                        "G1 X107.778 Y102.793 E2.42523\n" +
                        "G1 X108.232 Y101.117 E2.47208\n" +
                        "G1 X109.144 Y97.972 E2.56040\n" +
                        "G1 X110.433 Y93.947 E2.67441\n" +
                        "G1 X110.621 Y94.038 E2.68006\n" +
                        "G1 X111.383 Y93.794 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.803 Y95.422 E2.71567\n" +
                        "G1 X110.446 Y96.557 E2.74019\n" +
                        "G1 X109.429 Y100.496 E2.82404\n" +
                        "G1 X109.178 Y101.588 E2.84714\n" +
                        "G1 X108.910 Y103.119 E2.87917\n" +
                        "G1 X108.734 Y104.520 E2.90828\n" +
                        "G1 X108.622 Y106.356 E2.94619\n" +
                        "G1 X108.627 Y106.857 E2.95650\n" +
                        "G1 X107.618 Y106.372 E2.97957\n" +
                        "G1 X107.404 Y106.224 E2.98494\n" +
                        "G1 X107.209 Y106.043 E2.99041\n" +
                        "G1 X107.054 Y105.829 E2.99587\n" +
                        "G1 X106.896 Y105.430 E3.00470\n" +
                        "G1 X106.802 Y104.993 E3.01392\n" +
                        "G1 X106.792 Y104.608 E3.02185\n" +
                        "G1 X107.234 Y102.658 E3.06307\n" +
                        "G1 X107.692 Y100.966 E3.09920\n" +
                        "G1 X108.607 Y97.809 E3.16695\n" +
                        "G1 X110.079 Y93.227 E3.26614\n" +
                        "G1 X110.566 Y93.389 E3.27673\n" +
                        "G1 X111.318 Y93.756 E3.29397\n" +
                        "G1 X110.990 Y94.103 F7800.000\n" +
                        "G1 E1.29397 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.559 Y93.950 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.857 Y97.973 E2.11404\n" +
                        "G1 X91.768 Y101.117 E2.20234\n" +
                        "G1 X92.240 Y102.861 E2.25108\n" +
                        "G1 X92.646 Y104.663 E2.30092\n" +
                        "G1 X92.639 Y104.926 E2.30802\n" +
                        "G1 X92.566 Y105.267 E2.31742\n" +
                        "G1 X92.451 Y105.556 E2.32580\n" +
                        "G1 X92.365 Y105.674 E2.32976\n" +
                        "G1 X92.245 Y105.785 E2.33416\n" +
                        "G1 X92.096 Y105.889 E2.33905\n" +
                        "G1 X91.918 Y105.977 E2.34441\n" +
                        "G1 X91.824 Y104.469 E2.38518\n" +
                        "G1 X91.654 Y103.088 E2.42269\n" +
                        "G1 X91.372 Y101.477 E2.46683\n" +
                        "G1 X91.115 Y100.363 E2.49766\n" +
                        "G1 X90.119 Y96.495 E2.60541\n" +
                        "G1 X89.739 Y95.273 E2.63993\n" +
                        "G1 X89.309 Y94.067 E2.67447\n" +
                        "G1 X89.491 Y93.982 E2.67989\n" +
                        "G1 X89.914 Y93.223 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.809 E2.77920\n" +
                        "G1 X92.308 Y100.966 E2.84694\n" +
                        "G1 X92.784 Y102.726 E2.88453\n" +
                        "G1 X93.208 Y104.608 E2.92429\n" +
                        "G1 X93.198 Y104.993 E2.93222\n" +
                        "G1 X93.104 Y105.430 E2.94144\n" +
                        "G1 X92.946 Y105.828 E2.95027\n" +
                        "G1 X92.785 Y106.049 E2.95589\n" +
                        "G1 X92.596 Y106.223 E2.96119\n" +
                        "G1 X92.382 Y106.372 E2.96657\n" +
                        "G1 X91.372 Y106.857 E2.98966\n" +
                        "G1 X91.377 Y106.305 E3.00103\n" +
                        "G1 X91.266 Y104.520 E3.03788\n" +
                        "G1 X91.099 Y103.171 E3.06590\n" +
                        "G1 X90.822 Y101.588 E3.09903\n" +
                        "G1 X90.571 Y100.496 E3.12212\n" +
                        "G1 X89.580 Y96.648 E3.20401\n" +
                        "G1 X89.207 Y95.451 E3.22986\n" +
                        "G1 X88.618 Y93.796 E3.26606\n" +
                        "G1 X88.858 Y93.659 E3.27176\n" +
                        "G1 X89.428 Y93.392 E3.28474\n" +
                        "G1 X89.842 Y93.245 E3.29379\n" +
                        "G1 X89.822 Y93.715 F7800.000\n" +
                        "G1 E1.29379 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.566 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.568 Y107.981 E2.02853\n" +
                        "G1 X27.761 Y107.981 E2.10425\n" +
                        "G1 X27.761 Y106.924 E2.13278\n" +
                        "G1 X30.491 Y106.924 E2.20643\n" +
                        "G1 E0.20643 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.005 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.201 Y106.288 E2.08623\n" +
                        "G1 X31.204 Y108.617 E2.14906\n" +
                        "G1 X28.005 Y108.617 E2.23536\n" +
                        "G1 X28.005 Y111.919 E2.32444\n" +
                        "G1 X27.410 Y111.919 E2.34049\n" +
                        "G1 X27.270 Y111.899 E2.34429\n" +
                        "G1 X27.184 Y111.858 E2.34686\n" +
                        "G1 X27.136 Y111.794 E2.34904\n" +
                        "G1 X27.123 Y111.708 E2.35137\n" +
                        "G1 X27.127 Y104.045 E2.55810\n" +
                        "G1 X27.144 Y103.938 E2.56102\n" +
                        "G1 X28.005 Y103.938 E2.58424\n" +
                        "G1 X28.005 Y106.213 E2.64561\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.761 Y105.727 E2.71148\n" +
                        "G1 X31.765 Y109.177 E2.78259\n" +
                        "G1 X28.565 Y109.177 E2.84854\n" +
                        "G1 X28.565 Y112.479 E2.91660\n" +
                        "G1 X27.371 Y112.479 E2.94122\n" +
                        "G1 X27.121 Y112.444 E2.94641\n" +
                        "G1 X26.933 Y112.366 E2.95062\n" +
                        "G1 X26.819 Y112.287 E2.95348\n" +
                        "G1 X26.711 Y112.174 E2.95670\n" +
                        "G1 X26.625 Y112.031 E2.96014\n" +
                        "G1 X26.581 Y111.902 E2.96295\n" +
                        "G1 X26.562 Y111.739 E2.96633\n" +
                        "G1 X26.567 Y104.001 E3.12582\n" +
                        "G1 X26.599 Y103.792 E3.13017\n" +
                        "G1 X26.660 Y103.609 E3.13414\n" +
                        "G1 X26.794 Y103.377 E3.13967\n" +
                        "G1 X28.565 Y103.377 E3.17617\n" +
                        "G1 X28.565 Y105.652 E3.22306\n" +
                        "G1 X28.672 Y106.216 F7800.000\n" +
                        "M106 S224.4\n" +
                        "G1 Z4.850 F7800.000\n" +
                        "G1 E1.22306 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.434 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X172.031 Y106.924 E2.07005\n" +
                        "G1 X172.031 Y107.981 E2.09857\n" +
                        "G1 X169.433 Y107.981 E2.16866\n" +
                        "G1 X169.434 Y106.999 E2.19517\n" +
                        "G1 E0.19517 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.995 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.995 Y103.938 E2.06339\n" +
                        "G1 X172.666 Y103.939 E2.08149\n" +
                        "G1 X172.666 Y111.919 E2.29677\n" +
                        "G1 X171.995 Y111.919 E2.31487\n" +
                        "G1 X171.995 Y108.617 E2.40395\n" +
                        "G1 X168.796 Y108.617 E2.49026\n" +
                        "G1 X168.799 Y106.288 E2.55308\n" +
                        "G1 X171.920 Y106.288 E2.63727\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.377 E2.68570\n" +
                        "G1 X173.227 Y103.378 E2.72264\n" +
                        "G1 X173.227 Y112.335 E2.90725\n" +
                        "G1 X173.089 Y112.411 E2.91050\n" +
                        "G1 X172.924 Y112.462 E2.91405\n" +
                        "G1 X172.744 Y112.479 E2.91778\n" +
                        "G1 X171.435 Y112.479 E2.94476\n" +
                        "G1 X171.435 Y109.177 E3.01282\n" +
                        "G1 X168.235 Y109.177 E3.07877\n" +
                        "G1 X168.240 Y105.727 E3.14988\n" +
                        "G1 X171.360 Y105.727 E3.21418\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E1.21418 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.560 Y94.182 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.633 Y94.222 E2.00222\n" +
                        "G1 X110.228 Y95.367 E2.03500\n" +
                        "G1 X109.889 Y96.463 E2.06594\n" +
                        "G1 X108.916 Y100.228 E2.17083\n" +
                        "G1 X108.628 Y101.477 E2.20541\n" +
                        "G1 X108.353 Y103.048 E2.24844\n" +
                        "G1 X108.184 Y104.386 E2.28484\n" +
                        "G1 X108.096 Y105.793 E2.32285\n" +
                        "G1 X107.811 Y105.611 E2.33196\n" +
                        "G1 X107.698 Y105.494 E2.33634\n" +
                        "G1 X107.574 Y105.305 E2.34244\n" +
                        "G1 X107.469 Y105.050 E2.34988\n" +
                        "G1 X107.407 Y104.737 E2.35849\n" +
                        "G1 X107.403 Y104.460 E2.36595\n" +
                        "G1 X107.782 Y102.781 E2.41240\n" +
                        "G1 X108.234 Y101.110 E2.45909\n" +
                        "G1 X109.144 Y97.972 E2.54723\n" +
                        "G1 X110.382 Y94.107 E2.65672\n" +
                        "G1 X110.491 Y94.153 E2.65991\n" +
                        "G1 X111.321 Y93.957 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.760 Y95.544 E2.69458\n" +
                        "G1 X110.429 Y96.616 E2.71772\n" +
                        "G1 X109.461 Y100.361 E2.79744\n" +
                        "G1 X109.178 Y101.588 E2.82339\n" +
                        "G1 X108.908 Y103.131 E2.85569\n" +
                        "G1 X108.742 Y104.439 E2.88285\n" +
                        "G1 X108.622 Y106.361 E2.92255\n" +
                        "G1 X108.624 Y106.673 E2.92898\n" +
                        "G1 X107.703 Y106.222 E2.95010\n" +
                        "G1 X107.444 Y106.038 E2.95666\n" +
                        "G1 X107.259 Y105.847 E2.96214\n" +
                        "G1 X107.076 Y105.568 E2.96901\n" +
                        "G1 X106.930 Y105.213 E2.97692\n" +
                        "G1 X106.847 Y104.796 E2.98568\n" +
                        "G1 X106.841 Y104.402 E2.99381\n" +
                        "G1 X107.237 Y102.646 E3.03091\n" +
                        "G1 X107.694 Y100.959 E3.06693\n" +
                        "G1 X108.607 Y97.809 E3.13453\n" +
                        "G1 X110.025 Y93.384 E3.23028\n" +
                        "G1 X110.300 Y93.464 E3.23619\n" +
                        "G1 X110.803 Y93.676 E3.24744\n" +
                        "G1 X111.255 Y93.921 E3.25802\n" +
                        "G1 X110.929 Y94.268 F7800.000\n" +
                        "G1 E1.25802 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.615 Y94.111 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.973 E2.10942\n" +
                        "G1 X91.766 Y101.110 E2.19754\n" +
                        "G1 X92.239 Y102.861 E2.24646\n" +
                        "G1 X92.597 Y104.460 E2.29067\n" +
                        "G1 X92.593 Y104.737 E2.29815\n" +
                        "G1 X92.531 Y105.050 E2.30676\n" +
                        "G1 X92.426 Y105.305 E2.31420\n" +
                        "G1 X92.301 Y105.495 E2.32034\n" +
                        "G1 X92.195 Y105.605 E2.32444\n" +
                        "G1 X91.906 Y105.791 E2.33373\n" +
                        "G1 X91.816 Y104.386 E2.37172\n" +
                        "G1 X91.647 Y103.040 E2.40831\n" +
                        "G1 X91.372 Y101.476 E2.45115\n" +
                        "G1 X91.084 Y100.228 E2.48571\n" +
                        "G1 X90.140 Y96.567 E2.58770\n" +
                        "G1 X89.774 Y95.373 E2.62139\n" +
                        "G1 X89.367 Y94.222 E2.65432\n" +
                        "G1 X89.547 Y94.142 E2.65961\n" +
                        "G1 X89.970 Y93.385 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.809 E2.75539\n" +
                        "G1 X92.306 Y100.959 E2.82298\n" +
                        "G1 X92.784 Y102.726 E2.86071\n" +
                        "G1 X93.159 Y104.402 E2.89610\n" +
                        "G1 X93.153 Y104.796 E2.90424\n" +
                        "G1 X93.070 Y105.213 E2.91299\n" +
                        "G1 X92.924 Y105.568 E2.92090\n" +
                        "G1 X92.741 Y105.847 E2.92779\n" +
                        "G1 X92.563 Y106.031 E2.93305\n" +
                        "G1 X92.296 Y106.222 E2.93982\n" +
                        "G1 X91.376 Y106.674 E2.96096\n" +
                        "G1 X91.377 Y106.304 E2.96860\n" +
                        "G1 X91.258 Y104.439 E3.00711\n" +
                        "G1 X91.092 Y103.124 E3.03442\n" +
                        "G1 X90.822 Y101.588 E3.06657\n" +
                        "G1 X90.539 Y100.361 E3.09252\n" +
                        "G1 X89.600 Y96.719 E3.17003\n" +
                        "G1 X89.241 Y95.548 E3.19527\n" +
                        "G1 X88.678 Y93.954 E3.23012\n" +
                        "G1 X89.011 Y93.767 E3.23799\n" +
                        "G1 X89.640 Y93.486 E3.25219\n" +
                        "G1 X89.899 Y93.407 E3.25776\n" +
                        "G1 X89.876 Y93.876 F7800.000\n" +
                        "G1 E1.25776 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.566 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.567 Y107.981 E2.02853\n" +
                        "G1 X27.969 Y107.981 E2.09862\n" +
                        "G1 X27.969 Y106.924 E2.12714\n" +
                        "G1 X30.491 Y106.924 E2.19516\n" +
                        "G1 E0.19516 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.005 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.201 Y106.288 E2.08621\n" +
                        "G1 X31.204 Y108.617 E2.14903\n" +
                        "G1 X28.005 Y108.617 E2.23534\n" +
                        "G1 X28.005 Y111.919 E2.32441\n" +
                        "G1 X27.334 Y111.919 E2.34252\n" +
                        "G1 X27.334 Y103.938 E2.55781\n" +
                        "G1 X28.005 Y103.938 E2.57592\n" +
                        "G1 X28.005 Y106.213 E2.63730\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.760 Y105.727 E2.70315\n" +
                        "G1 X31.765 Y109.177 E2.77425\n" +
                        "G1 X28.565 Y109.177 E2.84020\n" +
                        "G1 X28.565 Y112.479 E2.90826\n" +
                        "G1 X27.263 Y112.479 E2.93511\n" +
                        "G1 X26.988 Y112.438 E2.94083\n" +
                        "G1 X26.847 Y112.379 E2.94398\n" +
                        "G1 X26.773 Y112.325 E2.94588\n" +
                        "G1 X26.773 Y103.377 E3.13029\n" +
                        "G1 X28.565 Y103.377 E3.16723\n" +
                        "G1 X28.565 Y105.652 E3.21413\n" +
                        "G1 X28.673 Y106.216 F7800.000\n" +
                        "M106 S229.5\n" +
                        "G1 Z5.150 F7800.000\n" +
                        "G1 E1.21413 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.435 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.207 Y106.924 E2.04780\n" +
                        "G1 X171.207 Y107.981 E2.07632\n" +
                        "G1 X169.433 Y107.981 E2.12417\n" +
                        "G1 X169.435 Y106.999 E2.15067\n" +
                        "G1 X168.800 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.842 Y106.288 E2.23273\n" +
                        "G1 X171.842 Y108.617 E2.29555\n" +
                        "G1 X168.796 Y108.617 E2.37772\n" +
                        "G1 X168.800 Y106.363 E2.43851\n" +
                        "G1 E0.43851 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.435 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.435 Y103.377 E2.04844\n" +
                        "G1 X172.403 Y103.377 E2.06839\n" +
                        "G1 X172.403 Y112.479 E2.25599\n" +
                        "G1 X171.435 Y112.479 E2.27594\n" +
                        "G1 X171.435 Y109.177 E2.34400\n" +
                        "G1 X168.235 Y109.177 E2.40995\n" +
                        "G1 X168.241 Y105.727 E2.48105\n" +
                        "G1 X171.360 Y105.727 E2.54534\n" +
                        "G1 X171.916 Y105.592 F7800.000\n" +
                        "G1 E0.54534 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.415 Y94.250 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.588 Y94.339 E2.00526\n" +
                        "G1 X110.247 Y95.310 E2.03301\n" +
                        "G1 X109.839 Y96.637 E2.07046\n" +
                        "G1 X108.947 Y100.093 E2.16674\n" +
                        "G1 X108.628 Y101.476 E2.20505\n" +
                        "G1 X108.356 Y103.027 E2.24752\n" +
                        "G1 X108.202 Y104.206 E2.27960\n" +
                        "G1 X108.103 Y105.611 E2.31759\n" +
                        "G1 X107.944 Y105.513 E2.32262\n" +
                        "G1 X107.784 Y105.364 E2.32852\n" +
                        "G1 X107.609 Y105.105 E2.33697\n" +
                        "G1 X107.561 Y105.000 E2.34008\n" +
                        "G1 X107.521 Y104.878 E2.34354\n" +
                        "G1 X107.460 Y104.521 E2.35330\n" +
                        "G1 X107.459 Y104.215 E2.36156\n" +
                        "G1 X107.761 Y102.859 E2.39904\n" +
                        "G1 X107.990 Y102.000 E2.42303\n" +
                        "G1 X109.144 Y97.971 E2.53608\n" +
                        "G1 X110.345 Y94.222 E2.64227\n" +
                        "G1 X109.986 Y93.505 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.260 Y93.583 E2.64814\n" +
                        "G1 X110.648 Y93.740 E2.65676\n" +
                        "G1 X111.276 Y94.073 E2.67142\n" +
                        "G1 X110.779 Y95.485 E2.70226\n" +
                        "G1 X110.379 Y96.789 E2.73038\n" +
                        "G1 X109.492 Y100.226 E2.80352\n" +
                        "G1 X109.178 Y101.588 E2.83234\n" +
                        "G1 X108.911 Y103.112 E2.86423\n" +
                        "G1 X108.760 Y104.268 E2.88826\n" +
                        "G1 X108.707 Y104.841 E2.90012\n" +
                        "G1 X108.622 Y106.511 E2.93458\n" +
                        "G1 X107.765 Y106.066 E2.95448\n" +
                        "G1 X107.610 Y105.965 E2.95829\n" +
                        "G1 X107.345 Y105.716 E2.96579\n" +
                        "G1 X107.119 Y105.380 E2.97413\n" +
                        "G1 X106.975 Y105.013 E2.98226\n" +
                        "G1 X106.900 Y104.570 E2.99153\n" +
                        "G1 X106.898 Y104.154 E3.00009\n" +
                        "G1 X107.216 Y102.725 E3.03026\n" +
                        "G1 X107.450 Y101.850 E3.04893\n" +
                        "G1 X108.608 Y97.808 E3.13559\n" +
                        "G1 X109.963 Y93.576 E3.22718\n" +
                        "G1 X110.183 Y93.711 F7800.000\n" +
                        "G1 E1.22718 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.654 Y94.224 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.971 E2.10616\n" +
                        "G1 X92.010 Y102.000 E2.21921\n" +
                        "G1 X92.239 Y102.859 E2.24319\n" +
                        "G1 X92.541 Y104.215 E2.28068\n" +
                        "G1 X92.540 Y104.521 E2.28894\n" +
                        "G1 X92.479 Y104.878 E2.29870\n" +
                        "G1 X92.439 Y105.000 E2.30215\n" +
                        "G1 X92.391 Y105.105 E2.30527\n" +
                        "G1 X92.220 Y105.358 E2.31350\n" +
                        "G1 X92.133 Y105.447 E2.31688\n" +
                        "G1 X91.898 Y105.609 E2.32456\n" +
                        "G1 X91.852 Y104.803 E2.34635\n" +
                        "G1 X91.789 Y104.126 E2.36467\n" +
                        "G1 X91.648 Y103.050 E2.39397\n" +
                        "G1 X91.372 Y101.476 E2.43706\n" +
                        "G1 X91.053 Y100.093 E2.47537\n" +
                        "G1 X90.161 Y96.637 E2.57164\n" +
                        "G1 X89.766 Y95.349 E2.60799\n" +
                        "G1 X89.412 Y94.340 E2.63683\n" +
                        "G1 X89.585 Y94.254 E2.64203\n" +
                        "G1 X90.009 Y93.496 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.808 E2.73538\n" +
                        "G1 X92.550 Y101.850 E2.82204\n" +
                        "G1 X92.784 Y102.725 E2.84071\n" +
                        "G1 X93.102 Y104.154 E2.87088\n" +
                        "G1 X93.100 Y104.570 E2.87944\n" +
                        "G1 X93.025 Y105.013 E2.88871\n" +
                        "G1 X92.881 Y105.380 E2.89684\n" +
                        "G1 X92.657 Y105.714 E2.90512\n" +
                        "G1 X92.499 Y105.874 E2.90975\n" +
                        "G1 X92.230 Y106.068 E2.91659\n" +
                        "G1 X91.377 Y106.515 E2.93644\n" +
                        "G1 X91.293 Y104.845 E2.97091\n" +
                        "G1 X91.232 Y104.189 E2.98448\n" +
                        "G1 X91.094 Y103.135 E3.00639\n" +
                        "G1 X90.822 Y101.588 E3.03876\n" +
                        "G1 X90.508 Y100.226 E3.06757\n" +
                        "G1 X89.621 Y96.789 E3.14071\n" +
                        "G1 X89.234 Y95.524 E3.16799\n" +
                        "G1 X88.726 Y94.079 E3.19956\n" +
                        "G1 X89.253 Y93.787 E3.21197\n" +
                        "G1 X89.937 Y93.519 E3.22712\n" +
                        "G1 X89.912 Y93.986 F7800.000\n" +
                        "G1 E1.22712 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.565 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.567 Y107.981 E2.02853\n" +
                        "G1 X28.793 Y107.981 E2.07637\n" +
                        "G1 X28.793 Y106.924 E2.10489\n" +
                        "G1 X30.490 Y106.924 E2.15067\n" +
                        "G1 X31.200 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.204 Y108.617 E2.21349\n" +
                        "G1 X28.158 Y108.617 E2.29565\n" +
                        "G1 X28.158 Y106.288 E2.35847\n" +
                        "G1 X31.125 Y106.288 E2.43851\n" +
                        "G1 E0.43851 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.565 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X31.759 Y105.727 E2.06583\n" +
                        "G1 X31.765 Y109.177 E2.13694\n" +
                        "G1 X28.565 Y109.177 E2.20289\n" +
                        "G1 X28.565 Y112.479 E2.27094\n" +
                        "G1 X27.597 Y112.479 E2.29090\n" +
                        "G1 X27.597 Y103.377 E2.47849\n" +
                        "G1 X28.565 Y103.377 E2.49845\n" +
                        "G1 X28.565 Y105.652 E2.54534\n" +
                        "G1 X28.630 Y106.223 F7800.000\n" +
                        "M106 S237.15\n" +
                        "G1 Z5.450 F7800.000\n" +
                        "G1 E0.54534 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.435 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X170.710 Y106.924 E2.03439\n" +
                        "G1 X170.710 Y107.981 E2.06291\n" +
                        "G1 X169.433 Y107.981 E2.09735\n" +
                        "G1 X169.435 Y106.999 E2.12386\n" +
                        "G1 X168.801 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.346 Y106.288 E2.19250\n" +
                        "G1 X171.346 Y108.617 E2.25532\n" +
                        "G1 X168.797 Y108.617 E2.32409\n" +
                        "G1 X168.801 Y106.363 E2.38489\n" +
                        "G1 X168.242 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.906 Y105.727 E2.46042\n" +
                        "G1 X171.906 Y109.177 E2.53152\n" +
                        "G1 X168.235 Y109.177 E2.60720\n" +
                        "G1 X168.241 Y105.802 E2.67676\n" +
                        "G1 X168.726 Y105.852 F7800.000\n" +
                        "G1 E0.67676 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.329 Y94.319 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.553 Y94.435 E2.00680\n" +
                        "G1 X110.192 Y95.472 E2.03643\n" +
                        "G1 X109.818 Y96.709 E2.07127\n" +
                        "G1 X108.978 Y99.957 E2.16179\n" +
                        "G1 X108.628 Y101.476 E2.20385\n" +
                        "G1 X108.355 Y103.035 E2.24653\n" +
                        "G1 X108.205 Y104.184 E2.27780\n" +
                        "G1 X108.111 Y105.406 E2.31086\n" +
                        "G1 X107.982 Y105.318 E2.31508\n" +
                        "G1 X107.819 Y105.154 E2.32131\n" +
                        "G1 X107.676 Y104.921 E2.32869\n" +
                        "G1 X107.571 Y104.628 E2.33708\n" +
                        "G1 X107.517 Y104.292 E2.34627\n" +
                        "G1 X107.522 Y103.985 E2.35455\n" +
                        "G1 X107.762 Y102.855 E2.38572\n" +
                        "G1 X107.974 Y102.060 E2.40791\n" +
                        "G1 X109.144 Y97.971 E2.52265\n" +
                        "G1 X110.297 Y94.371 E2.62464\n" +
                        "G1 X110.111 Y93.624 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.564 Y93.809 E2.63472\n" +
                        "G1 X110.812 Y93.936 E2.64047\n" +
                        "G1 X111.230 Y94.196 E2.65062\n" +
                        "G1 X110.726 Y95.646 E2.68226\n" +
                        "G1 X110.358 Y96.860 E2.70840\n" +
                        "G1 X109.523 Y100.090 E2.77717\n" +
                        "G1 X109.178 Y101.588 E2.80884\n" +
                        "G1 X108.909 Y103.120 E2.84090\n" +
                        "G1 X108.762 Y104.244 E2.86427\n" +
                        "G1 X108.683 Y105.198 E2.88399\n" +
                        "G1 X108.624 Y106.337 E2.90751\n" +
                        "G1 X107.785 Y105.866 E2.92734\n" +
                        "G1 X107.618 Y105.748 E2.93155\n" +
                        "G1 X107.375 Y105.502 E2.93868\n" +
                        "G1 X107.168 Y105.165 E2.94683\n" +
                        "G1 X107.025 Y104.769 E2.95551\n" +
                        "G1 X106.955 Y104.332 E2.96463\n" +
                        "G1 X106.962 Y103.922 E2.97309\n" +
                        "G1 X107.217 Y102.724 E2.99832\n" +
                        "G1 X107.434 Y101.911 E3.01567\n" +
                        "G1 X108.608 Y97.808 E3.10362\n" +
                        "G1 X109.960 Y93.585 E3.19502\n" +
                        "G1 X110.038 Y93.605 E3.19667\n" +
                        "G1 X110.416 Y94.006 F7800.000\n" +
                        "G1 E1.19667 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.684 Y94.314 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.971 E2.10360\n" +
                        "G1 X91.985 Y101.908 E2.21410\n" +
                        "G1 X92.239 Y102.858 E2.24061\n" +
                        "G1 X92.474 Y103.924 E2.27006\n" +
                        "G1 X92.483 Y104.295 E2.28007\n" +
                        "G1 X92.429 Y104.628 E2.28918\n" +
                        "G1 X92.324 Y104.921 E2.29757\n" +
                        "G1 X92.175 Y105.164 E2.30526\n" +
                        "G1 X92.085 Y105.260 E2.30880\n" +
                        "G1 X91.890 Y105.406 E2.31539\n" +
                        "G1 X91.788 Y104.114 E2.35034\n" +
                        "G1 X91.650 Y103.061 E2.37899\n" +
                        "G1 X91.372 Y101.476 E2.42240\n" +
                        "G1 X91.022 Y99.957 E2.46446\n" +
                        "G1 X90.182 Y96.709 E2.55496\n" +
                        "G1 X89.818 Y95.504 E2.58892\n" +
                        "G1 X89.447 Y94.435 E2.61944\n" +
                        "G1 X89.616 Y94.346 E2.62461\n" +
                        "G1 X90.040 Y93.587 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.808 E2.71597\n" +
                        "G1 X92.525 Y101.759 E2.80067\n" +
                        "G1 X92.784 Y102.725 E2.82129\n" +
                        "G1 X93.033 Y103.856 E2.84516\n" +
                        "G1 X93.045 Y104.333 E2.85499\n" +
                        "G1 X92.975 Y104.769 E2.86409\n" +
                        "G1 X92.832 Y105.165 E2.87276\n" +
                        "G1 X92.623 Y105.506 E2.88100\n" +
                        "G1 X92.461 Y105.678 E2.88587\n" +
                        "G1 X92.210 Y105.868 E2.89236\n" +
                        "G1 X91.377 Y106.337 E2.91207\n" +
                        "G1 X91.317 Y105.198 E2.93559\n" +
                        "G1 X91.231 Y104.174 E2.95676\n" +
                        "G1 X91.096 Y103.146 E2.97813\n" +
                        "G1 X90.822 Y101.588 E3.01074\n" +
                        "G1 X90.477 Y100.090 E3.04241\n" +
                        "G1 X89.642 Y96.860 E3.11118\n" +
                        "G1 X89.285 Y95.677 E3.13665\n" +
                        "G1 X88.770 Y94.194 E3.16900\n" +
                        "G1 X89.188 Y93.936 E3.17913\n" +
                        "G1 X89.437 Y93.809 E3.18489\n" +
                        "G1 X89.966 Y93.602 E3.19661\n" +
                        "G1 X89.941 Y94.077 F7800.000\n" +
                        "G1 E1.19661 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.564 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.566 Y107.981 E2.02853\n" +
                        "G1 X29.290 Y107.981 E2.06297\n" +
                        "G1 X29.290 Y106.924 E2.09149\n" +
                        "G1 X30.489 Y106.924 E2.12385\n" +
                        "G1 X31.199 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.203 Y108.617 E2.18667\n" +
                        "G1 X28.654 Y108.617 E2.25544\n" +
                        "G1 X28.654 Y106.288 E2.31826\n" +
                        "G1 X31.124 Y106.288 E2.38488\n" +
                        "G1 X31.758 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.765 Y109.177 E2.45598\n" +
                        "G1 X28.094 Y109.177 E2.53165\n" +
                        "G1 X28.094 Y105.727 E2.60275\n" +
                        "G1 X31.683 Y105.727 E2.67674\n" +
                        "G1 X31.625 Y106.209 F7800.000\n" +
                        "G1 Z5.750 F7800.000\n" +
                        "G1 E0.67674 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.529 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X170.819 Y106.924 E2.03480\n" +
                        "G1 X170.819 Y107.981 E2.06332\n" +
                        "G1 X169.532 Y107.981 E2.09805\n" +
                        "G1 X169.530 Y106.999 E2.12455\n" +
                        "G1 X168.892 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.455 Y106.288 E2.19368\n" +
                        "G1 X171.455 Y108.617 E2.25650\n" +
                        "G1 X168.898 Y108.617 E2.32548\n" +
                        "G1 X168.893 Y106.363 E2.38628\n" +
                        "G1 X168.330 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X172.016 Y105.727 E2.46223\n" +
                        "G1 X172.016 Y109.177 E2.53333\n" +
                        "G1 X168.339 Y109.177 E2.60912\n" +
                        "G1 X168.331 Y105.802 E2.67868\n" +
                        "G1 X168.815 Y105.851 F7800.000\n" +
                        "G1 E0.67868 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.512 Y94.547 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.192 Y95.473 E2.02645\n" +
                        "G1 X109.798 Y96.779 E2.06324\n" +
                        "G1 X109.009 Y99.822 E2.14803\n" +
                        "G1 X108.629 Y101.475 E2.19379\n" +
                        "G1 X108.409 Y102.698 E2.22731\n" +
                        "G1 X108.241 Y103.849 E2.25870\n" +
                        "G1 X108.126 Y105.170 E2.29446\n" +
                        "G1 X107.879 Y104.943 E2.30351\n" +
                        "G1 X107.679 Y104.578 E2.31474\n" +
                        "G1 X107.602 Y104.220 E2.32461\n" +
                        "G1 X107.584 Y103.820 E2.33541\n" +
                        "G1 X107.615 Y103.508 E2.34387\n" +
                        "G1 X107.791 Y102.746 E2.36496\n" +
                        "G1 X108.240 Y101.090 E2.41126\n" +
                        "G1 X109.144 Y97.972 E2.49882\n" +
                        "G1 X110.283 Y94.414 E2.59963\n" +
                        "G1 X110.447 Y94.509 E2.60473\n" +
                        "G1 X111.181 Y94.325 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.725 Y95.646 E2.63353\n" +
                        "G1 X110.338 Y96.931 E2.66119\n" +
                        "G1 X109.554 Y99.955 E2.72558\n" +
                        "G1 X109.178 Y101.587 E2.76011\n" +
                        "G1 X108.963 Y102.788 E2.78524\n" +
                        "G1 X108.798 Y103.917 E2.80876\n" +
                        "G1 X108.697 Y104.961 E2.83037\n" +
                        "G1 X108.641 Y106.151 E2.85493\n" +
                        "G1 X107.824 Y105.649 E2.87470\n" +
                        "G1 X107.533 Y105.402 E2.88257\n" +
                        "G1 X107.421 Y105.269 E2.88615\n" +
                        "G1 X107.212 Y104.918 E2.89457\n" +
                        "G1 X107.141 Y104.743 E2.89846\n" +
                        "G1 X107.044 Y104.292 E2.90797\n" +
                        "G1 X107.022 Y103.805 E2.91802\n" +
                        "G1 X107.061 Y103.417 E2.92606\n" +
                        "G1 X107.247 Y102.610 E2.94312\n" +
                        "G1 X107.700 Y100.938 E2.97882\n" +
                        "G1 X108.607 Y97.809 E3.04598\n" +
                        "G1 X109.940 Y93.647 E3.13603\n" +
                        "G1 X110.104 Y93.699 E3.13958\n" +
                        "G1 X110.438 Y93.855 E3.14717\n" +
                        "G1 X110.843 Y94.091 E3.15684\n" +
                        "G1 X111.120 Y94.282 E3.16377\n" +
                        "G1 X110.780 Y94.623 F7800.000\n" +
                        "G1 E1.16377 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.716 Y94.411 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.972 E2.10087\n" +
                        "G1 X91.760 Y101.089 E2.18842\n" +
                        "G1 X92.239 Y102.861 E2.23793\n" +
                        "G1 X92.384 Y103.506 E2.25577\n" +
                        "G1 X92.416 Y103.820 E2.26428\n" +
                        "G1 X92.398 Y104.220 E2.27508\n" +
                        "G1 X92.325 Y104.562 E2.28451\n" +
                        "G1 X92.223 Y104.787 E2.29117\n" +
                        "G1 X92.136 Y104.922 E2.29551\n" +
                        "G1 X92.022 Y105.050 E2.30013\n" +
                        "G1 X91.876 Y105.167 E2.30517\n" +
                        "G1 X91.787 Y104.099 E2.33409\n" +
                        "G1 X91.652 Y103.073 E2.36202\n" +
                        "G1 X91.372 Y101.476 E2.40574\n" +
                        "G1 X90.991 Y99.822 E2.45154\n" +
                        "G1 X90.202 Y96.779 E2.53633\n" +
                        "G1 X89.807 Y95.468 E2.57327\n" +
                        "G1 X89.488 Y94.546 E2.59958\n" +
                        "G1 X89.651 Y94.450 E2.60469\n" +
                        "G1 X90.083 Y93.720 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.809 E2.69317\n" +
                        "G1 X92.300 Y100.938 E2.76033\n" +
                        "G1 X92.784 Y102.726 E2.79850\n" +
                        "G1 X92.939 Y103.416 E2.81308\n" +
                        "G1 X92.978 Y103.805 E2.82113\n" +
                        "G1 X92.956 Y104.292 E2.83118\n" +
                        "G1 X92.861 Y104.738 E2.84058\n" +
                        "G1 X92.717 Y105.055 E2.84775\n" +
                        "G1 X92.584 Y105.262 E2.85283\n" +
                        "G1 X92.410 Y105.458 E2.85823\n" +
                        "G1 X92.178 Y105.644 E2.86436\n" +
                        "G1 X91.361 Y106.150 E2.88416\n" +
                        "G1 X91.325 Y105.298 E2.90175\n" +
                        "G1 X91.229 Y104.159 E2.92530\n" +
                        "G1 X91.098 Y103.158 E2.94612\n" +
                        "G1 X90.822 Y101.588 E2.97897\n" +
                        "G1 X90.446 Y99.955 E3.01350\n" +
                        "G1 X89.662 Y96.930 E3.07789\n" +
                        "G1 X89.273 Y95.641 E3.10566\n" +
                        "G1 X88.819 Y94.325 E3.13435\n" +
                        "G1 X89.157 Y94.091 E3.14284\n" +
                        "G1 X89.489 Y93.894 E3.15077\n" +
                        "G1 X89.850 Y93.718 E3.15906\n" +
                        "G1 X90.060 Y93.649 E3.16362\n" +
                        "G1 X89.925 Y94.195 F7800.000\n" +
                        "G1 E1.16362 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.471 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.468 Y107.981 E2.02853\n" +
                        "G1 X29.181 Y107.981 E2.06326\n" +
                        "G1 X29.181 Y106.924 E2.09179\n" +
                        "G1 X30.396 Y106.924 E2.12457\n" +
                        "G1 X31.108 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.102 Y108.617 E2.18739\n" +
                        "G1 X28.545 Y108.617 E2.25638\n" +
                        "G1 X28.545 Y106.288 E2.31920\n" +
                        "G1 X31.033 Y106.288 E2.38632\n" +
                        "G1 X31.670 Y105.741 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.662 Y109.177 E2.45715\n" +
                        "G1 X27.984 Y109.177 E2.53294\n" +
                        "G1 X27.984 Y105.727 E2.60404\n" +
                        "G1 X31.608 Y105.727 E2.67873\n" +
                        "G1 X31.534 Y106.222 F7800.000\n" +
                        "G1 Z6.050 F7800.000\n" +
                        "G1 E0.67873 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.639 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X170.929 Y106.924 E2.03478\n" +
                        "G1 X170.929 Y107.981 E2.06331\n" +
                        "G1 X169.642 Y107.981 E2.09803\n" +
                        "G1 X169.639 Y106.999 E2.12453\n" +
                        "G1 X169.002 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.564 Y106.288 E2.19364\n" +
                        "G1 X171.564 Y108.617 E2.25646\n" +
                        "G1 X169.007 Y108.617 E2.32543\n" +
                        "G1 X169.002 Y106.363 E2.38623\n" +
                        "G1 X168.466 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X172.125 Y105.727 E2.46165\n" +
                        "G1 X172.125 Y109.177 E2.53275\n" +
                        "G1 X168.448 Y109.177 E2.60853\n" +
                        "G1 X168.441 Y105.777 E2.67861\n" +
                        "G1 X168.950 Y105.852 F7800.000\n" +
                        "G1 E0.67861 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.463 Y94.683 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.143 Y95.623 E2.02679\n" +
                        "G1 X109.777 Y96.850 E2.06135\n" +
                        "G1 X109.041 Y99.686 E2.14039\n" +
                        "G1 X108.629 Y101.476 E2.18992\n" +
                        "G1 X108.376 Y102.904 E2.22906\n" +
                        "G1 X108.239 Y103.868 E2.25532\n" +
                        "G1 X108.139 Y104.916 E2.28372\n" +
                        "G1 X107.945 Y104.726 E2.29105\n" +
                        "G1 X107.799 Y104.449 E2.29950\n" +
                        "G1 X107.697 Y104.110 E2.30903\n" +
                        "G1 X107.675 Y103.964 E2.31303\n" +
                        "G1 X107.669 Y103.494 E2.32570\n" +
                        "G1 X107.767 Y102.861 E2.34298\n" +
                        "G1 X108.242 Y101.081 E2.39268\n" +
                        "G1 X109.144 Y97.973 E2.47999\n" +
                        "G1 X110.248 Y94.535 E2.57741\n" +
                        "G1 X110.402 Y94.640 E2.58244\n" +
                        "G1 X111.127 Y94.473 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.677 Y95.794 E2.61119\n" +
                        "G1 X110.317 Y97.001 E2.63716\n" +
                        "G1 X109.585 Y99.820 E2.69718\n" +
                        "G1 X109.178 Y101.588 E2.73457\n" +
                        "G1 X108.929 Y102.992 E2.76398\n" +
                        "G1 X108.796 Y103.934 E2.78358\n" +
                        "G1 X108.692 Y105.020 E2.80606\n" +
                        "G1 X108.646 Y105.924 E2.82471\n" +
                        "G1 X107.855 Y105.415 E2.84409\n" +
                        "G1 X107.576 Y105.165 E2.85181\n" +
                        "G1 X107.471 Y105.030 E2.85534\n" +
                        "G1 X107.278 Y104.662 E2.86390\n" +
                        "G1 X107.149 Y104.233 E2.87313\n" +
                        "G1 X107.115 Y104.009 E2.87780\n" +
                        "G1 X107.108 Y103.469 E2.88894\n" +
                        "G1 X107.139 Y103.173 E2.89507\n" +
                        "G1 X107.220 Y102.738 E2.90419\n" +
                        "G1 X107.702 Y100.931 E2.94273\n" +
                        "G1 X108.607 Y97.809 E3.00973\n" +
                        "G1 X109.923 Y93.714 E3.09837\n" +
                        "G1 X110.457 Y93.998 E3.11085\n" +
                        "G1 X111.069 Y94.426 E3.12623\n" +
                        "G1 X110.721 Y94.765 F7800.000\n" +
                        "G1 E1.12623 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.754 Y94.533 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.856 Y97.972 E2.09744\n" +
                        "G1 X91.758 Y101.081 E2.18476\n" +
                        "G1 X92.230 Y102.847 E2.23405\n" +
                        "G1 X92.331 Y103.494 E2.25173\n" +
                        "G1 X92.325 Y103.964 E2.26441\n" +
                        "G1 X92.303 Y104.110 E2.26841\n" +
                        "G1 X92.201 Y104.448 E2.27793\n" +
                        "G1 X92.055 Y104.726 E2.28638\n" +
                        "G1 X91.861 Y104.914 E2.29367\n" +
                        "G1 X91.785 Y104.080 E2.31625\n" +
                        "G1 X91.654 Y103.084 E2.34336\n" +
                        "G1 X91.372 Y101.476 E2.38739\n" +
                        "G1 X90.959 Y99.686 E2.43694\n" +
                        "G1 X90.223 Y96.851 E2.51598\n" +
                        "G1 X89.863 Y95.644 E2.54996\n" +
                        "G1 X89.537 Y94.683 E2.57733\n" +
                        "G1 X89.693 Y94.575 E2.58244\n" +
                        "G1 X90.080 Y93.713 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.393 Y97.809 E2.67109\n" +
                        "G1 X92.298 Y100.931 E2.73809\n" +
                        "G1 X92.777 Y102.723 E2.77631\n" +
                        "G1 X92.861 Y103.173 E2.78576\n" +
                        "G1 X92.892 Y103.469 E2.79188\n" +
                        "G1 X92.885 Y104.009 E2.80303\n" +
                        "G1 X92.851 Y104.233 E2.80769\n" +
                        "G1 X92.722 Y104.662 E2.81692\n" +
                        "G1 X92.529 Y105.030 E2.82548\n" +
                        "G1 X92.425 Y105.165 E2.82899\n" +
                        "G1 X92.171 Y105.388 E2.83596\n" +
                        "G1 X91.354 Y105.931 E2.85617\n" +
                        "G1 X91.308 Y105.026 E2.87485\n" +
                        "G1 X91.227 Y104.142 E2.89313\n" +
                        "G1 X91.100 Y103.169 E2.91337\n" +
                        "G1 X90.822 Y101.588 E2.94645\n" +
                        "G1 X90.415 Y99.820 E2.98385\n" +
                        "G1 X89.683 Y97.001 E3.04387\n" +
                        "G1 X89.329 Y95.814 E3.06940\n" +
                        "G1 X88.872 Y94.471 E3.09863\n" +
                        "G1 X89.534 Y94.003 E3.11533\n" +
                        "G1 X90.011 Y93.741 E3.12655\n" +
                        "G1 X90.013 Y94.208 F7800.000\n" +
                        "G1 E1.12655 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.362 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.359 Y107.981 E2.02853\n" +
                        "G1 X29.071 Y107.981 E2.06326\n" +
                        "G1 X29.071 Y106.924 E2.09179\n" +
                        "G1 X30.287 Y106.924 E2.12457\n" +
                        "G1 X30.999 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X30.993 Y108.617 E2.18739\n" +
                        "G1 X28.436 Y108.617 E2.25638\n" +
                        "G1 X28.436 Y106.288 E2.31920\n" +
                        "G1 X30.924 Y106.288 E2.38632\n" +
                        "G1 X31.560 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.552 Y109.177 E2.45742\n" +
                        "G1 X27.875 Y109.177 E2.53321\n" +
                        "G1 X27.875 Y105.727 E2.60431\n" +
                        "G1 X31.485 Y105.727 E2.67871\n" +
                        "G1 X31.459 Y106.090 F7800.000\n" +
                        "M106 S239.7\n" +
                        "G1 Z6.350 F7800.000\n" +
                        "G1 E0.67871 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.749 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.038 Y106.924 E2.03476\n" +
                        "G1 X171.038 Y107.981 E2.06329\n" +
                        "G1 X169.751 Y107.981 E2.09800\n" +
                        "G1 X169.749 Y106.999 E2.12450\n" +
                        "G1 X169.112 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.673 Y106.288 E2.19359\n" +
                        "G1 X171.673 Y108.617 E2.25641\n" +
                        "G1 X169.117 Y108.617 E2.32538\n" +
                        "G1 X169.112 Y106.363 E2.38618\n" +
                        "G1 X168.743 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X172.234 Y105.727 E2.45812\n" +
                        "G1 X172.234 Y109.177 E2.52922\n" +
                        "G1 X168.557 Y109.177 E2.60500\n" +
                        "G1 X168.551 Y105.727 E2.67611\n" +
                        "G1 X168.668 Y105.727 E2.67854\n" +
                        "G1 X169.172 Y105.985 F7800.000\n" +
                        "G1 E0.67854 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.325 Y94.764 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.410 Y94.833 E2.00295\n" +
                        "G1 X110.150 Y95.602 E2.02485\n" +
                        "G1 X109.756 Y96.921 E2.06196\n" +
                        "G1 X109.072 Y99.551 E2.13529\n" +
                        "G1 X108.629 Y101.476 E2.18857\n" +
                        "G1 X108.377 Y102.898 E2.22752\n" +
                        "G1 X108.237 Y103.885 E2.25443\n" +
                        "G1 X108.167 Y104.622 E2.27440\n" +
                        "G1 X108.011 Y104.449 E2.28068\n" +
                        "G1 X107.874 Y104.182 E2.28878\n" +
                        "G1 X107.787 Y103.830 E2.29856\n" +
                        "G1 X107.758 Y103.498 E2.30756\n" +
                        "G1 X107.772 Y103.127 E2.31756\n" +
                        "G1 X107.840 Y102.674 E2.32991\n" +
                        "G1 X107.997 Y102.018 E2.34811\n" +
                        "G1 X109.374 Y97.247 E2.48208\n" +
                        "G1 X110.203 Y94.676 E2.55495\n" +
                        "G1 X110.264 Y94.720 E2.55698\n" +
                        "G1 X111.064 Y94.646 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.684 Y95.772 E2.58148\n" +
                        "G1 X110.296 Y97.071 E2.60943\n" +
                        "G1 X109.616 Y99.685 E2.66508\n" +
                        "G1 X109.178 Y101.588 E2.70533\n" +
                        "G1 X108.930 Y102.986 E2.73460\n" +
                        "G1 X108.794 Y103.951 E2.75469\n" +
                        "G1 X108.687 Y105.079 E2.77804\n" +
                        "G1 X108.653 Y105.689 E2.79062\n" +
                        "G1 X107.910 Y105.156 E2.80946\n" +
                        "G1 X107.639 Y104.889 E2.81732\n" +
                        "G1 X107.533 Y104.746 E2.82098\n" +
                        "G1 X107.345 Y104.380 E2.82946\n" +
                        "G1 X107.232 Y103.922 E2.83918\n" +
                        "G1 X107.197 Y103.511 E2.84767\n" +
                        "G1 X107.213 Y103.075 E2.85668\n" +
                        "G1 X107.290 Y102.567 E2.86725\n" +
                        "G1 X107.455 Y101.875 E2.88192\n" +
                        "G1 X108.838 Y97.083 E2.98472\n" +
                        "G1 X109.892 Y93.813 E3.05552\n" +
                        "G1 X110.234 Y94.009 E3.06365\n" +
                        "G1 X110.667 Y94.319 E3.07462\n" +
                        "G1 X111.007 Y94.598 E3.08368\n" +
                        "G1 X110.655 Y94.933 F7800.000\n" +
                        "G1 E1.08368 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.797 Y94.681 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.626 Y97.248 E2.07275\n" +
                        "G1 X92.073 Y102.280 E2.21400\n" +
                        "G1 X92.187 Y102.811 E2.22866\n" +
                        "G1 X92.227 Y103.120 E2.23705\n" +
                        "G1 X92.242 Y103.498 E2.24726\n" +
                        "G1 X92.213 Y103.830 E2.25625\n" +
                        "G1 X92.126 Y104.182 E2.26604\n" +
                        "G1 X91.989 Y104.449 E2.27414\n" +
                        "G1 X91.834 Y104.621 E2.28038\n" +
                        "G1 X91.783 Y104.062 E2.29553\n" +
                        "G1 X91.598 Y102.727 E2.33187\n" +
                        "G1 X91.371 Y101.474 E2.36623\n" +
                        "G1 X90.928 Y99.551 E2.41947\n" +
                        "G1 X90.244 Y96.921 E2.49279\n" +
                        "G1 X89.857 Y95.624 E2.52928\n" +
                        "G1 X89.590 Y94.832 E2.55183\n" +
                        "G1 X89.736 Y94.726 E2.55671\n" +
                        "G1 X88.935 Y94.645 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X89.220 Y94.408 E2.56435\n" +
                        "G1 X89.775 Y94.004 E2.57850\n" +
                        "G1 X90.104 Y93.809 E2.58638\n" +
                        "G1 X91.162 Y97.083 E2.65732\n" +
                        "G1 X92.617 Y102.144 E2.76584\n" +
                        "G1 X92.740 Y102.716 E2.77789\n" +
                        "G1 X92.787 Y103.072 E2.78530\n" +
                        "G1 X92.803 Y103.511 E2.79436\n" +
                        "G1 X92.768 Y103.922 E2.80285\n" +
                        "G1 X92.655 Y104.380 E2.81258\n" +
                        "G1 X92.467 Y104.746 E2.82105\n" +
                        "G1 X92.361 Y104.889 E2.82472\n" +
                        "G1 X92.091 Y105.155 E2.83253\n" +
                        "G1 X91.346 Y105.672 E2.85123\n" +
                        "G1 X91.313 Y105.085 E2.86335\n" +
                        "G1 X91.226 Y104.126 E2.88320\n" +
                        "G1 X91.044 Y102.816 E2.91046\n" +
                        "G1 X90.822 Y101.587 E2.93620\n" +
                        "G1 X90.384 Y99.685 E2.97644\n" +
                        "G1 X89.704 Y97.071 E3.03209\n" +
                        "G1 X89.323 Y95.794 E3.05956\n" +
                        "G1 X88.959 Y94.716 E3.08300\n" +
                        "G1 X89.305 Y94.630 F7800.000\n" +
                        "G1 E1.08300 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.253 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.250 Y107.981 E2.02853";
                        salientemarcos[8]="G1 X28.962 Y107.981 E2.06326\n" +
                        "G1 X28.962 Y106.924 E2.09179\n" +
                        "G1 X30.178 Y106.924 E2.12457\n" +
                        "G1 X30.889 Y106.466 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X30.884 Y108.617 E2.18259\n" +
                        "G1 X28.327 Y108.617 E2.25158\n" +
                        "G1 X28.327 Y106.288 E2.31439\n" +
                        "G1 X30.889 Y106.288 E2.38353\n" +
                        "G1 X30.889 Y106.391 E2.38631\n" +
                        "G1 X31.450 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.443 Y109.177 E2.45741\n" +
                        "G1 X27.766 Y109.177 E2.53320\n" +
                        "G1 X27.766 Y105.727 E2.60430\n" +
                        "G1 X31.375 Y105.727 E2.67868\n" +
                        "G1 X31.315 Y106.209 F7800.000\n" +
                        "G1 Z6.650 F7800.000\n" +
                        "G1 E0.67868 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.859 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.147 Y106.924 E2.03475\n" +
                        "G1 X171.147 Y107.981 E2.06327\n" +
                        "G1 X169.861 Y107.981 E2.09798\n" +
                        "G1 X169.859 Y106.999 E2.12448\n" +
                        "G1 X169.222 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.783 Y106.288 E2.19355\n" +
                        "G1 X171.783 Y108.617 E2.25637\n" +
                        "G1 X169.226 Y108.617 E2.32533\n" +
                        "G1 X169.222 Y106.363 E2.38613\n" +
                        "G1 X168.661 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X172.343 Y105.727 E2.46203\n" +
                        "G1 X172.343 Y109.177 E2.53313\n" +
                        "G1 X168.666 Y109.177 E2.60891\n" +
                        "G1 X168.661 Y105.802 E2.67847\n" +
                        "G1 X169.010 Y105.817 F7800.000\n" +
                        "G1 E0.67847 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.276 Y94.949 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.347 Y95.015 E2.00261\n" +
                        "G1 X110.082 Y95.816 E2.02539\n" +
                        "G1 X109.735 Y96.992 E2.05845\n" +
                        "G1 X109.103 Y99.416 E2.12602\n" +
                        "G1 X108.629 Y101.474 E2.18301\n" +
                        "G1 X108.425 Y102.602 E2.21393\n" +
                        "G1 X108.262 Y103.678 E2.24327\n" +
                        "G1 X108.197 Y104.298 E2.26010\n" +
                        "G1 X108.097 Y104.161 E2.26467\n" +
                        "G1 X107.959 Y103.854 E2.27374\n" +
                        "G1 X107.924 Y103.728 E2.27728\n" +
                        "G1 X107.872 Y103.250 E2.29026\n" +
                        "G1 X107.875 Y103.001 E2.29695\n" +
                        "G1 X107.934 Y102.526 E2.30988\n" +
                        "G1 X108.048 Y102.032 E2.32356\n" +
                        "G1 X110.018 Y95.379 E2.51072\n" +
                        "G1 X110.185 Y94.875 E2.52506\n" +
                        "G1 X110.218 Y94.902 E2.52621\n" +
                        "G1 X110.994 Y94.847 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.617 Y95.984 E2.55088\n" +
                        "G1 X110.276 Y97.142 E2.57577\n" +
                        "G1 X109.647 Y99.549 E2.62704\n" +
                        "G1 X109.178 Y101.587 E2.67015\n" +
                        "G1 X108.978 Y102.694 E2.69333\n" +
                        "G1 X108.819 Y103.749 E2.71532\n" +
                        "G1 X108.713 Y104.747 E2.73600\n" +
                        "G1 X108.669 Y105.401 E2.74950\n" +
                        "G1 X107.967 Y104.868 E2.76768\n" +
                        "G1 X107.826 Y104.742 E2.77156\n" +
                        "G1 X107.609 Y104.444 E2.77916\n" +
                        "G1 X107.431 Y104.046 E2.78816\n" +
                        "G1 X107.372 Y103.835 E2.79267\n" +
                        "G1 X107.310 Y103.276 E2.80425\n" +
                        "G1 X107.315 Y102.963 E2.81071\n" +
                        "G1 X107.381 Y102.428 E2.82181\n" +
                        "G1 X107.506 Y101.889 E2.83322\n" +
                        "G1 X109.483 Y95.211 E2.97675\n" +
                        "G1 X109.899 Y93.958 E3.00397\n" +
                        "G1 X110.271 Y94.219 E3.01332\n" +
                        "G1 X110.938 Y94.797 E3.03151\n" +
                        "G1 X110.580 Y95.128 F7800.000\n" +
                        "G1 E1.03151 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.657 Y95.024 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X89.818 Y94.883 E2.00577\n" +
                        "G1 X91.952 Y102.033 E2.20706\n" +
                        "G1 X92.066 Y102.526 E2.22073\n" +
                        "G1 X92.125 Y103.001 E2.23365\n" +
                        "G1 X92.128 Y103.250 E2.24035\n" +
                        "G1 X92.076 Y103.728 E2.25332\n" +
                        "G1 X92.041 Y103.854 E2.25686\n" +
                        "G1 X91.903 Y104.161 E2.26593\n" +
                        "G1 X91.803 Y104.299 E2.27052\n" +
                        "G1 X91.711 Y103.478 E2.29278\n" +
                        "G1 X91.594 Y102.700 E2.31402\n" +
                        "G1 X91.371 Y101.474 E2.34763\n" +
                        "G1 X90.897 Y99.416 E2.40461\n" +
                        "G1 X90.264 Y96.991 E2.47222\n" +
                        "G1 X89.851 Y95.605 E2.51122\n" +
                        "G1 X89.681 Y95.095 E2.52572\n" +
                        "G1 X89.008 Y94.849 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X89.671 Y94.265 E2.54393\n" +
                        "G1 X90.100 Y93.956 E2.55484\n" +
                        "G1 X90.430 Y94.946 E2.57635\n" +
                        "G1 X92.494 Y101.889 E2.72565\n" +
                        "G1 X92.619 Y102.428 E2.73705\n" +
                        "G1 X92.685 Y102.963 E2.74815\n" +
                        "G1 X92.690 Y103.276 E2.75461\n" +
                        "G1 X92.628 Y103.835 E2.76619\n" +
                        "G1 X92.569 Y104.046 E2.77071\n" +
                        "G1 X92.391 Y104.444 E2.77970\n" +
                        "G1 X92.174 Y104.742 E2.78730\n" +
                        "G1 X92.034 Y104.867 E2.79117\n" +
                        "G1 X91.329 Y105.398 E2.80935\n" +
                        "G1 X91.278 Y104.642 E2.82496\n" +
                        "G1 X91.155 Y103.552 E2.84758\n" +
                        "G1 X91.040 Y102.792 E2.86342\n" +
                        "G1 X90.822 Y101.587 E2.88865\n" +
                        "G1 X90.353 Y99.549 E2.93175\n" +
                        "G1 X89.724 Y97.142 E2.98304\n" +
                        "G1 X89.317 Y95.774 E3.01245\n" +
                        "G1 X89.031 Y94.920 E3.03100\n" +
                        "G1 X89.507 Y94.820 F7800.000\n" +
                        "G1 E1.03100 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.143 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.141 Y107.981 E2.02853\n" +
                        "G1 X28.853 Y107.981 E2.06326\n" +
                        "G1 X28.853 Y106.924 E2.09179\n" +
                        "G1 X30.068 Y106.924 E2.12457\n" +
                        "G1 X30.779 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X30.775 Y108.617 E2.18739\n" +
                        "G1 X28.217 Y108.617 E2.25638\n" +
                        "G1 X28.217 Y106.288 E2.31919\n" +
                        "G1 X30.704 Y106.288 E2.38628\n" +
                        "G1 X31.340 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.334 Y109.177 E2.45738\n" +
                        "G1 X27.657 Y109.177 E2.53317\n" +
                        "G1 X27.657 Y105.727 E2.60427\n" +
                        "G1 X31.265 Y105.727 E2.67864\n" +
                        "G1 X31.205 Y106.209 F7800.000\n" +
                        "G1 Z6.950 F7800.000\n" +
                        "G1 E0.67864 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.969 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X171.256 Y106.924 E2.03473\n" +
                        "G1 X171.256 Y107.981 E2.06326\n" +
                        "G1 X169.970 Y107.981 E2.09795\n" +
                        "G1 X169.969 Y106.999 E2.12445\n" +
                        "G1 X169.332 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X171.892 Y106.288 E2.19350\n" +
                        "G1 X171.892 Y108.617 E2.25632\n" +
                        "G1 X169.335 Y108.617 E2.32528\n" +
                        "G1 X169.332 Y106.363 E2.38608\n" +
                        "G1 X168.771 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X172.452 Y105.727 E2.46196\n" +
                        "G1 X172.452 Y109.177 E2.53306\n" +
                        "G1 X168.776 Y109.177 E2.60884\n" +
                        "G1 X168.771 Y105.802 E2.67840\n" +
                        "G1 X169.255 Y105.852 F7800.000\n" +
                        "G1 E0.67840 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.271 Y95.241 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.052 Y95.913 E2.01904\n" +
                        "G1 X109.715 Y97.062 E2.05137\n" +
                        "G1 X109.134 Y99.280 E2.11321\n" +
                        "G1 X108.629 Y101.474 E2.17394\n" +
                        "G1 X108.430 Y102.571 E2.20401\n" +
                        "G1 X108.238 Y103.904 E2.24036\n" +
                        "G1 X108.144 Y103.728 E2.24576\n" +
                        "G1 X108.039 Y103.331 E2.25683\n" +
                        "G1 X108.017 Y103.129 E2.26231\n" +
                        "G1 X108.021 Y102.744 E2.27270\n" +
                        "G1 X108.045 Y102.508 E2.27911\n" +
                        "G1 X108.150 Y102.061 E2.29148\n" +
                        "G1 X110.119 Y95.411 E2.47856\n" +
                        "G1 X110.198 Y95.170 E2.48541\n" +
                        "G1 X110.217 Y95.189 E2.48612\n" +
                        "G1 X110.912 Y95.085 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X110.588 Y96.079 E2.50766\n" +
                        "G1 X110.255 Y97.212 E2.53202\n" +
                        "G1 X109.679 Y99.414 E2.57892\n" +
                        "G1 X109.178 Y101.587 E2.62488\n" +
                        "G1 X108.983 Y102.663 E2.64742\n" +
                        "G1 X108.819 Y103.747 E2.67001\n" +
                        "G1 X108.690 Y105.065 E2.69732\n" +
                        "G1 X108.053 Y104.550 E2.71420\n" +
                        "G1 X107.928 Y104.425 E2.71786\n" +
                        "G1 X107.730 Y104.162 E2.72464\n" +
                        "G1 X107.616 Y103.922 E2.73012\n" +
                        "G1 X107.487 Y103.434 E2.74053\n" +
                        "G1 X107.456 Y103.157 E2.74627\n" +
                        "G1 X107.461 Y102.712 E2.75544\n" +
                        "G1 X107.491 Y102.414 E2.76161\n" +
                        "G1 X107.608 Y101.917 E2.77213\n" +
                        "G1 X109.584 Y95.244 E2.91557\n" +
                        "G1 X109.936 Y94.179 E2.93868\n" +
                        "G1 X110.289 Y94.474 E2.94816\n" +
                        "G1 X110.858 Y95.032 E2.96459\n" +
                        "G1 X110.495 Y95.362 F7800.000\n" +
                        "G1 E0.96459 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X91.759 Y103.909 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X91.702 Y103.406 E2.01364\n" +
                        "G1 X91.372 Y101.478 E2.06641\n" +
                        "G1 X90.866 Y99.280 E2.12725\n" +
                        "G1 X90.285 Y97.062 E2.18911\n" +
                        "G1 X89.729 Y95.239 E2.24054\n" +
                        "G1 X89.801 Y95.167 E2.24327\n" +
                        "G1 X91.850 Y102.061 E2.43728\n" +
                        "G1 X91.954 Y102.504 E2.44956\n" +
                        "G1 X91.986 Y102.844 E2.45877\n" +
                        "G1 X91.961 Y103.331 E2.47192\n" +
                        "G1 X91.856 Y103.728 E2.48299\n" +
                        "G1 X91.799 Y103.846 E2.48652\n" +
                        "G1 X91.309 Y105.062 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X91.147 Y103.485 E2.51921\n" +
                        "G1 X90.822 Y101.588 E2.55886\n" +
                        "G1 X90.321 Y99.414 E2.60485\n" +
                        "G1 X89.745 Y97.212 E2.65176\n" +
                        "G1 X89.391 Y96.011 E2.67757\n" +
                        "G1 X89.088 Y95.083 E2.69769\n" +
                        "G1 X89.657 Y94.522 E2.71414\n" +
                        "G1 X90.064 Y94.173 E2.72520\n" +
                        "G1 X90.416 Y95.244 E2.74842\n" +
                        "G1 X92.392 Y101.917 E2.89187\n" +
                        "G1 X92.508 Y102.413 E2.90237\n" +
                        "G1 X92.547 Y102.820 E2.91078\n" +
                        "G1 X92.544 Y103.154 E2.91768\n" +
                        "G1 X92.513 Y103.434 E2.92348\n" +
                        "G1 X92.384 Y103.922 E2.93388\n" +
                        "G1 X92.270 Y104.162 E2.93936\n" +
                        "G1 X92.072 Y104.425 E2.94614\n" +
                        "G1 X91.947 Y104.550 E2.94979\n" +
                        "G1 X91.368 Y105.015 E2.96510\n" +
                        "G1 X91.406 Y104.669 F7800.000\n" +
                        "G1 E0.96510 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.034 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.032 Y107.981 E2.02853\n" +
                        "G1 X28.744 Y107.981 E2.06326\n" +
                        "G1 X28.744 Y106.924 E2.09179\n" +
                        "G1 X29.959 Y106.924 E2.12455\n" +
                        "G1 X30.669 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X30.666 Y108.617 E2.18737\n" +
                        "G1 X28.108 Y108.617 E2.25636\n" +
                        "G1 X28.108 Y106.288 E2.31918\n" +
                        "G1 X30.594 Y106.288 E2.38624\n" +
                        "G1 X31.230 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.225 Y109.177 E2.45734\n" +
                        "G1 X27.548 Y109.177 E2.53313\n" +
                        "G1 X27.548 Y105.727 E2.60423\n" +
                        "G1 X31.155 Y105.727 E2.67857\n" +
                        "G1 X31.095 Y106.209 F7800.000\n" +
                        "M106 S247.35\n" +
                        "G1 Z7.250 F7800.000\n" +
                        "G1 E0.67857 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.575 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X172.562 Y105.727 E2.02033\n" +
                        "G1 X172.562 Y109.177 E2.09143\n" +
                        "G1 X171.575 Y109.177 E2.11176\n" +
                        "G1 X171.575 Y105.802 E2.18131\n" +
                        "G1 X172.030 Y105.936 F7800.000\n" +
                        "G1 X170.068 Y106.288 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X170.068 Y108.617 E2.24413\n" +
                        "G1 X169.445 Y108.617 E2.26095\n" +
                        "G1 X169.442 Y106.288 E2.32376\n" +
                        "G1 X169.993 Y106.288 E2.33863\n" +
                        "G1 X170.629 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X170.629 Y109.177 E2.40974\n" +
                        "G1 X168.885 Y109.177 E2.44568\n" +
                        "G1 X168.881 Y105.727 E2.51678\n" +
                        "G1 X170.554 Y105.727 E2.55127\n" +
                        "G1 X170.379 Y106.160 F7800.000\n" +
                        "G1 E0.55127 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X109.107 Y99.398 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X108.629 Y101.474 E2.05747\n" +
                        "G1 X108.435 Y102.539 E2.08669\n" +
                        "G1 X108.304 Y103.402 E2.11021\n" +
                        "G1 X108.234 Y103.173 E2.11667\n" +
                        "G1 X108.204 Y102.947 E2.12281\n" +
                        "G1 X108.199 Y102.679 E2.13003\n" +
                        "G1 X108.231 Y102.415 E2.13721\n" +
                        "G1 X108.305 Y102.106 E2.14580\n" +
                        "G1 X109.086 Y99.470 E2.21995\n" +
                        "G1 E0.21995 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X109.999 Y94.499 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.818 Y95.367 E2.02459\n" +
                        "G1 X110.413 Y96.659 E2.05250\n" +
                        "G1 X109.921 Y98.452 E2.09081\n" +
                        "G1 X109.710 Y99.280 E2.10842\n" +
                        "G1 X109.178 Y101.587 E2.15722\n" +
                        "G1 X108.988 Y102.632 E2.17910\n" +
                        "G1 X108.819 Y103.746 E2.20234\n" +
                        "G1 X108.726 Y104.689 E2.22187\n" +
                        "G1 X108.164 Y104.196 E2.23728\n" +
                        "G1 X108.040 Y104.063 E2.24104\n" +
                        "G1 X107.931 Y103.912 E2.24486\n" +
                        "G1 X107.765 Y103.577 E2.25257\n" +
                        "G1 X107.684 Y103.286 E2.25881\n" +
                        "G1 X107.644 Y102.989 E2.26497\n" +
                        "G1 X107.638 Y102.651 E2.27193\n" +
                        "G1 X107.678 Y102.316 E2.27889\n" +
                        "G1 X107.764 Y101.960 E2.28644\n" +
                        "G1 X109.737 Y95.293 E2.42974\n" +
                        "G1 X109.975 Y94.571 E2.44542\n" +
                        "G1 X110.120 Y94.759 F7800.000\n" +
                        "G1 E0.44542 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X90.893 Y99.398 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X91.695 Y102.106 E2.07618\n" +
                        "G1 X91.769 Y102.415 E2.08477\n" +
                        "G1 X91.801 Y102.679 E2.09195\n" +
                        "G1 X91.796 Y102.947 E2.09917\n" +
                        "G1 X91.766 Y103.173 E2.10530\n" +
                        "G1 X91.700 Y103.393 E2.11151\n" +
                        "G1 X91.372 Y101.477 E2.16395\n" +
                        "G1 X90.910 Y99.471 E2.21949\n" +
                        "G1 E0.21949 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X90.001 Y94.501 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X90.263 Y95.293 E2.01720\n" +
                        "G1 X92.236 Y101.960 E2.16050\n" +
                        "G1 X92.322 Y102.316 E2.16805\n" +
                        "G1 X92.362 Y102.651 E2.17501\n" +
                        "G1 X92.356 Y102.989 E2.18197\n" +
                        "G1 X92.316 Y103.286 E2.18814\n" +
                        "G1 X92.235 Y103.577 E2.19437\n" +
                        "G1 X92.068 Y103.912 E2.20208\n" +
                        "G1 X91.960 Y104.063 E2.20590\n" +
                        "G1 X91.836 Y104.196 E2.20965\n" +
                        "G1 X91.276 Y104.683 E2.22495\n" +
                        "G1 X91.134 Y103.386 E2.25184\n" +
                        "G1 X90.822 Y101.588 E2.28946\n" +
                        "G1 X90.290 Y99.279 E2.33830\n" +
                        "G1 X89.766 Y97.282 E2.38084\n" +
                        "G1 X89.183 Y95.365 E2.42214\n" +
                        "G1 X89.947 Y94.553 E2.44512\n" +
                        "G1 X89.981 Y95.000 F7800.000\n" +
                        "G1 E0.44512 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.559 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.556 Y108.617 E2.06282\n" +
                        "G1 X29.946 Y108.617 E2.07927\n" +
                        "G1 X29.946 Y106.288 E2.14209\n" +
                        "G1 X30.484 Y106.288 E2.15659\n" +
                        "G1 X31.120 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.116 Y109.177 E2.22769\n" +
                        "G1 X29.386 Y109.177 E2.26334\n" +
                        "G1 X29.386 Y105.727 E2.33445\n" +
                        "G1 X31.045 Y105.727 E2.36863\n" +
                        "G1 X31.042 Y106.221 F7800.000\n" +
                        "G1 E0.36863 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.439 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X28.439 Y109.177 E2.07110\n" +
                        "G1 X27.438 Y109.177 E2.09173\n" +
                        "G1 X27.438 Y105.727 E2.16283\n" +
                        "G1 X28.364 Y105.727 E2.18191\n" +
                        "G1 X28.392 Y106.225 F7800.000\n" +
                        "M106 S252.45\n" +
                        "G1 Z7.550 F7800.000\n" +
                        "G1 E0.18191 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X172.049 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X172.665 Y105.727 E2.01271\n" +
                        "G1 X172.665 Y109.177 E2.08381\n" +
                        "G1 X172.049 Y109.177 E2.09652\n" +
                        "G1 X172.049 Y105.802 E2.16608\n" +
                        "G1 X172.496 Y105.952 F7800.000\n" +
                        "G1 E0.16608 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.584 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X169.584 Y108.617 E2.06282\n" +
                        "G1 X169.554 Y108.617 E2.06362\n" +
                        "G1 X169.552 Y106.331 E2.12527\n" +
                        "G1 X170.144 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X170.144 Y109.177 E2.19638\n" +
                        "G1 X168.994 Y109.177 E2.22009\n" +
                        "G1 X168.991 Y105.727 E2.29119\n" +
                        "G1 X170.069 Y105.727 E2.31342\n" +
                        "G1 X169.894 Y106.160 F7800.000\n" +
                        "G1 E0.31342 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.093 Y94.987 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X110.703 Y95.720 E2.01965\n" +
                        "G1 X110.331 Y96.941 E2.04596\n" +
                        "G1 X109.882 Y98.594 E2.08126\n" +
                        "G1 X109.178 Y101.588 E2.14465\n" +
                        "G1 X108.865 Y103.412 E2.18281\n" +
                        "G1 X108.773 Y104.222 E2.19960\n" +
                        "G1 X108.312 Y103.788 E2.21265\n" +
                        "G1 X108.190 Y103.649 E2.21646\n" +
                        "G1 X108.086 Y103.491 E2.22037\n" +
                        "G1 X108.002 Y103.298 E2.22471\n" +
                        "G1 X107.924 Y103.008 E2.23089\n" +
                        "G1 X107.895 Y102.784 E2.23555\n" +
                        "G1 X107.904 Y102.449 E2.24245\n" +
                        "G1 X108.001 Y102.022 E2.25148\n" +
                        "G1 X110.070 Y95.058 E2.40120\n" +
                        "G1 X110.272 Y95.454 F7800.000\n" +
                        "G1 E0.40120 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.905 Y94.991 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X91.999 Y102.022 E2.15121\n" +
                        "G1 X92.096 Y102.449 E2.16023\n" +
                        "G1 X92.105 Y102.784 E2.16714\n" +
                        "G1 X92.076 Y103.008 E2.17180\n" +
                        "G1 X91.998 Y103.298 E2.17798\n" +
                        "G1 X91.914 Y103.491 E2.18232\n" +
                        "G1 X91.810 Y103.649 E2.18623\n" +
                        "G1 X91.688 Y103.788 E2.19003\n" +
                        "G1 X91.228 Y104.220 E2.20303\n" +
                        "G1 X91.138 Y103.412 E2.21978\n" +
                        "G1 X90.822 Y101.588 E2.25793\n" +
                        "G1 X90.259 Y99.143 E2.30964\n" +
                        "G1 X89.786 Y97.352 E2.34783\n" +
                        "G1 X89.296 Y95.719 E2.38296\n" +
                        "G1 X89.856 Y95.047 E2.40099\n" +
                        "G1 X89.886 Y95.490 F7800.000\n" +
                        "G1 E0.40099 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.420 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F900\n" +
                        "G1 X30.449 Y106.288 E2.00078\n" +
                        "G1 X30.447 Y108.617 E2.06360\n" +
                        "G1 X30.420 Y108.617 E2.06434\n" +
                        "G1 X30.420 Y106.363 E2.12513\n" +
                        "G1 X29.859 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X31.010 Y105.727 E2.14884\n" +
                        "G1 X31.006 Y109.177 E2.21994\n" +
                        "G1 X29.859 Y109.177 E2.24359\n" +
                        "G1 X29.859 Y105.802 E2.31314\n" +
                        "G1 X30.316 Y105.930 F7800.000\n" +
                        "G1 X27.955 Y105.727 F7800.000\n" +
                        "G1 F900\n" +
                        "G1 X27.955 Y109.177 E2.38424\n" +
                        "G1 X27.335 Y109.177 E2.39703\n" +
                        "G1 X27.335 Y105.727 E2.46813\n" +
                        "G1 X27.880 Y105.727 E2.47937\n" +
                        "G1 X27.925 Y106.227 F7800.000\n" +
                        "M106 S255\n" +
                        "G1 Z7.850 F7800.000\n" +
                        "G1 E0.47937 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X172.207 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F787.655\n" +
                        "G1 X172.736 Y105.727 E2.01089\n" +
                        "G1 X172.736 Y109.177 E2.08199\n" +
                        "G1 X172.207 Y109.177 E2.09289\n" +
                        "G1 X172.207 Y105.802 E2.16244\n" +
                        "G1 X172.652 Y105.955 F7800.000\n" +
                        "G1 E0.16244 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.986 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F787.655\n" +
                        "G1 X169.986 Y109.177 E2.07110\n" +
                        "G1 X169.103 Y109.177 E2.08929\n" +
                        "G1 X169.101 Y105.727 E2.16040\n" +
                        "G1 X169.911 Y105.727 E2.17710\n" +
                        "G1 X169.944 Y106.226 F7800.000\n" +
                        "G1 E0.17710 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X110.243 Y95.776 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F787.655\n" +
                        "G1 X110.553 Y96.198 E2.01080\n" +
                        "G1 X110.193 Y97.423 E2.03710\n" +
                        "G1 X109.842 Y98.733 E2.06507\n" +
                        "G1 X109.178 Y101.587 E2.12546\n" +
                        "G1 X108.935 Y102.953 E2.15405\n" +
                        "G1 X108.847 Y103.600 E2.16750\n" +
                        "G1 X108.541 Y103.290 E2.17648\n" +
                        "G1 X108.434 Y103.151 E2.18010\n" +
                        "G1 X108.350 Y102.980 E2.18403\n" +
                        "G1 X108.292 Y102.772 E2.18846\n" +
                        "G1 X108.282 Y102.504 E2.19400\n" +
                        "G1 X108.364 Y102.119 E2.20212\n" +
                        "G1 X110.222 Y95.847 E2.33692\n" +
                        "G1 X110.404 Y96.249 F7800.000\n" +
                        "G1 E0.33692 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X89.759 Y95.773 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F787.655\n" +
                        "G1 X91.636 Y102.119 E2.13638\n" +
                        "G1 X91.718 Y102.504 E2.14450\n" +
                        "G1 X91.708 Y102.772 E2.15004\n" +
                        "G1 X91.650 Y102.980 E2.15447\n" +
                        "G1 X91.566 Y103.151 E2.15840\n" +
                        "G1 X91.459 Y103.291 E2.16202\n" +
                        "G1 X91.158 Y103.595 E2.17086\n" +
                        "G1 X91.112 Y103.238 E2.17829\n" +
                        "G1 X90.822 Y101.588 E2.21281\n" +
                        "G1 X90.228 Y99.008 E2.26738\n" +
                        "G1 X89.807 Y97.423 E2.30118\n" +
                        "G1 X89.447 Y96.199 E2.32748\n" +
                        "G1 X89.714 Y95.834 E2.33681\n" +
                        "G1 X89.859 Y96.263 F7800.000\n" +
                        "G1 E0.33681 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.899 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F787.655\n" +
                        "G1 X30.897 Y109.177 E2.07110\n" +
                        "G1 X30.018 Y109.177 E2.08923\n" +
                        "G1 X30.018 Y105.727 E2.16033\n" +
                        "G1 X30.824 Y105.727 E2.17696\n" +
                        "G1 X30.858 Y106.226 F7800.000\n" +
                        "G1 E0.17696 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X27.797 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F787.655\n" +
                        "G1 X27.797 Y109.177 E2.07110\n" +
                        "G1 X27.264 Y109.177 E2.08207\n" +
                        "G1 X27.264 Y105.727 E2.15317\n" +
                        "G1 X27.722 Y105.727 E2.16259\n" +
                        "G1 X27.771 Y106.227 F7800.000\n" +
                        "G1 Z8.150 F7800.000\n" +
                        "G1 E0.16259 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X172.238 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X172.754 Y105.727 E2.01063\n" +
                        "G1 X172.754 Y109.177 E2.08173\n" +
                        "G1 X172.238 Y109.177 E2.09237\n" +
                        "G1 X172.238 Y105.802 E2.16192\n" +
                        "G1 X172.683 Y105.956 F7800.000\n" +
                        "G1 E0.16192 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X169.947 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X169.947 Y109.177 E2.07110\n" +
                        "G1 X169.213 Y109.177 E2.08623\n" +
                        "G1 X169.211 Y105.727 E2.15733\n" +
                        "G1 X169.872 Y105.727 E2.17095\n" +
                        "G1 X169.912 Y106.226 F7800.000\n" +
                        "G1 E0.17095 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.789 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X30.788 Y109.177 E2.07110\n" +
                        "G1 X30.049 Y109.177 E2.08634\n" +
                        "G1 X30.049 Y105.727 E2.15744\n" +
                        "G1 X30.714 Y105.727 E2.17116\n" +
                        "G1 X30.754 Y106.226 F7800.000\n" +
                        "G1 E0.17116 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X27.757 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X27.757 Y109.177 E2.07110\n" +
                        "G1 X27.246 Y109.177 E2.08164\n" +
                        "G1 X27.246 Y105.727 E2.15274\n" +
                        "G1 X27.682 Y105.727 E2.16173\n" +
                        "G1 X27.733 Y106.227 F7800.000\n" +
                        "G1 Z8.450 F7800.000\n" +
                        "G1 E0.16173 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X172.168 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X172.730 Y105.727 E2.01159\n" +
                        "G1 X172.730 Y109.177 E2.08269\n" +
                        "G1 X172.168 Y109.177 E2.09427\n" +
                        "G1 X172.168 Y105.802 E2.16383\n" +
                        "G1 X172.614 Y105.954 F7800.000\n" +
                        "G1 E0.16383 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X170.023 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X170.023 Y109.177 E2.07110\n" +
                        "G1 X169.322 Y109.177 E2.08556\n" +
                        "G1 X169.321 Y105.727 E2.15666\n" +
                        "G1 X169.948 Y105.727 E2.16959\n" +
                        "G1 X169.773 Y106.160 F7800.000\n" +
                        "G1 E0.16959 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.679 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X30.679 Y109.177 E2.07110\n" +
                        "G1 X29.979 Y109.177 E2.08553\n" +
                        "G1 X29.979 Y105.727 E2.15664\n" +
                        "G1 X30.604 Y105.727 E2.16953\n" +
                        "G1 X30.646 Y106.226 F7800.000\n" +
                        "G1 E0.16953 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X27.834 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X27.834 Y109.177 E2.07110\n" +
                        "G1 X27.270 Y109.177 E2.08273\n" +
                        "G1 X27.270 Y105.727 E2.15383\n" +
                        "G1 X27.759 Y105.727 E2.16391\n" +
                        "G1 X27.807 Y106.227 F7800.000\n" +
                        "G1 Z8.750 F7800.000\n" +
                        "G1 E0.16391 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X171.947 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X172.656 Y105.727 E2.01462\n" +
                        "G1 X172.656 Y109.177 E2.08572\n" +
                        "G1 X171.947 Y109.177 E2.10033\n" +
                        "G1 X171.947 Y105.802 E2.16989\n" +
                        "G1 X172.396 Y105.948 F7800.000\n" +
                        "G1 X170.242 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X170.242 Y109.177 E2.24099\n" +
                        "G1 X169.431 Y109.177 E2.25771\n" +
                        "G1 X169.431 Y105.727 E2.32881\n" +
                        "G1 X170.167 Y105.727 E2.34399\n" +
                        "G1 X170.204 Y106.226 F7800.000\n" +
                        "G1 E0.34399 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X30.569 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X30.569 Y109.177 E2.07110\n" +
                        "G1 X29.758 Y109.177 E2.08783\n" +
                        "G1 X29.758 Y105.727 E2.15893\n" +
                        "G1 X30.494 Y105.727 E2.17411\n" +
                        "G1 X30.531 Y106.226 F7800.000\n" +
                        "G1 E0.17411 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X28.053 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X28.053 Y109.177 E2.07110\n" +
                        "G1 X27.344 Y109.177 E2.08572\n" +
                        "G1 X27.344 Y105.727 E2.15682\n" +
                        "G1 X27.978 Y105.727 E2.16989\n" +
                        "G1 X28.019 Y106.226 F7800.000\n" +
                        "G1 Z9.050 F7800.000\n" +
                        "G1 E0.16989 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X170.756 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X170.767 Y106.924 E2.00030\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X171.335 Y106.924 E2.01608\n" +
                        "G1 X171.335 Y107.981 E2.04545\n" +
                        "G1 X170.767 Y107.981 E2.06123\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X170.756 Y106.999 E2.08773\n" +
                        "G1 X170.121 Y106.288 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X170.767 Y106.288 E2.10518\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X171.422 Y106.288 E2.12336\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X171.971 Y106.288 E2.13818\n" +
                        "G1 X171.971 Y108.617 E2.20099\n" +
                        "G1 X171.422 Y108.617 E2.21580\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X170.767 Y108.617 E2.23399\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X170.121 Y108.617 E2.25144\n" +
                        "G1 X170.121 Y106.363 E2.31223\n" +
                        "G1 X169.560 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X170.767 Y105.727 E2.33712\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X171.422 Y105.727 E2.35530\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X172.532 Y105.727 E2.37817\n" +
                        "G1 X172.532 Y109.177 E2.44927\n" +
                        "G1 X171.422 Y109.177 E2.47214\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X170.767 Y109.177 E2.49032\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X169.560 Y109.177 E2.51521\n" +
                        "G1 X169.560 Y105.802 E2.58477\n" +
                        "G1 X170.018 Y105.927 F7800.000\n" +
                        "G1 E0.58477 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X29.233 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X29.233 Y107.981 E2.02852\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X28.665 Y107.981 E2.04430\n" +
                        "G1 X28.665 Y106.924 E2.07367\n" +
                        "G1 X29.158 Y106.924 E2.08737\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 X29.879 Y106.288 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X29.879 Y108.617 E2.15018\n" +
                        "G1 X29.233 Y108.617 E2.16763\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X28.578 Y108.617 E2.18582\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X28.029 Y108.617 E2.20063\n" +
                        "G1 X28.029 Y106.288 E2.26345\n" +
                        "G1 X28.578 Y106.288 E2.27826\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X29.233 Y106.288 E2.29644\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X29.804 Y106.288 E2.31187\n" +
                        "G1 X30.440 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X30.440 Y109.177 E2.38297\n" +
                        "G1 X29.233 Y109.177 E2.40785\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X28.578 Y109.177 E2.42604\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X27.468 Y109.177 E2.44891\n" +
                        "G1 X27.468 Y105.727 E2.52001\n" +
                        "G1 X28.578 Y105.727 E2.54288\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F3600\n" +
                        "G1 X29.233 Y105.727 E2.56106\n" +
                        "M106 S255\n" +
                        "\n" +
                        "G1 F600\n" +
                        "G1 X30.365 Y105.727 E2.58440\n" +
                        "G1 X29.971 Y105.899 F7800.000\n" +
                        "G1 Z9.350 F7800.000\n" +
                        "G1 E0.58440 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X170.946 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X171.141 Y106.924 E2.00525\n" +
                        "G1 X171.141 Y107.981 E2.03377\n" +
                        "G1 X170.946 Y107.981 E2.03902\n" +
                        "G1 X170.946 Y106.999 E2.06552\n" +
                        "G1 X170.311 Y106.288 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X171.776 Y106.288 E2.10506\n" +
                        "G1 X171.776 Y108.617 E2.16788\n" +
                        "G1 X170.311 Y108.617 E2.20742\n" +
                        "G1 X170.311 Y106.363 E2.26821\n" +
                        "G1 X169.750 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X172.337 Y105.727 E2.32153\n" +
                        "G1 X172.337 Y109.177 E2.39263\n" +
                        "G1 X169.750 Y109.177 E2.44595\n" +
                        "G1 X169.750 Y105.802 E2.51551\n" +
                        "G1 X170.226 Y105.880 F7800.000\n" +
                        "G1 E0.51551 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X29.054 Y106.924 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X29.054 Y107.981 E2.02852\n" +
                        "G1 X28.859 Y107.981 E2.03377\n" +
                        "G1 X28.859 Y106.924 E2.06230\n" +
                        "G1 X28.979 Y106.924 E2.06552\n" +
                        "G1 X29.690 Y106.288 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X29.690 Y108.617 E2.12834\n" +
                        "G1 X28.224 Y108.617 E2.16788\n" +
                        "G1 X28.224 Y106.288 E2.23070\n" +
                        "G1 X29.615 Y106.288 E2.26822\n" +
                        "G1 X30.250 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X30.250 Y109.177 E2.33932\n" +
                        "G1 X27.663 Y109.177 E2.39264\n" +
                        "G1 X27.663 Y105.727 E2.46374\n" +
                        "G1 X30.175 Y105.727 E2.51551\n" +
                        "G1 X30.144 Y106.216 F7800.000\n" +
                        "G1 Z9.650 F7800.000\n" +
                        "G1 E0.51551 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X170.597 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X171.487 Y106.288 E2.02400\n" +
                        "G1 X171.487 Y108.617 E2.08682\n" +
                        "G1 X170.597 Y108.617 E2.11082\n" +
                        "G1 X170.597 Y106.363 E2.17161\n" +
                        "G1 X170.037 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X172.048 Y105.727 E2.21306\n" +
                        "G1 X172.048 Y109.177 E2.28416\n" +
                        "G1 X170.037 Y109.177 E2.32560\n" +
                        "G1 X170.037 Y105.802 E2.39516\n" +
                        "G1 X170.507 Y105.898 F7800.000\n" +
                        "G1 E0.39516 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X29.403 Y106.288 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X29.403 Y108.617 E2.06282\n" +
                        "G1 X28.513 Y108.617 E2.08682\n" +
                        "G1 X28.513 Y106.288 E2.14963\n" +
                        "G1 X29.328 Y106.288 E2.17161\n" +
                        "G1 X29.963 Y105.727 F7800.000\n" +
                        "G1 F600\n" +
                        "G1 X29.963 Y109.177 E2.24271\n" +
                        "G1 X27.952 Y109.177 E2.28416\n" +
                        "G1 X27.952 Y105.727 E2.35526\n" +
                        "G1 X29.888 Y105.727 E2.39516\n" +
                        "G1 X29.876 Y106.220 F7800.000\n" +
                        "G1 Z9.950 F7800.000\n" +
                        "G1 E0.39516 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X170.521 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X171.561 Y105.727 E2.02144\n" +
                        "G1 X171.561 Y109.177 E2.09254\n" +
                        "G1 X170.521 Y109.177 E2.11398\n" +
                        "G1 X170.521 Y105.802 E2.18354\n" +
                        "G1 X170.976 Y105.934 F7800.000\n" +
                        "G1 E0.18354 F2400.00000\n" +
                        "G92 E0\n" +
                        "G1 X29.479 Y105.727 F7800.000\n" +
                        "G1 E2.00000 F2400.00000\n" +
                        "G1 F600\n" +
                        "G1 X29.479 Y109.177 E2.07110\n" +
                        "G1 X28.439 Y109.177 E2.09254\n" +
                        "G1 X28.439 Y105.727 E2.16364\n" +
                        "G1 X29.404 Y105.727 E2.18354\n" +
                        "G1 X29.431 Y106.225 F7800.000\n" +
                        "G1 E0.18354 F2400.00000\n" +
                        "G92 E0\n" +
                        "M107\n" +
                        "; Filament-specific end gcode \n" +
                        ";END gcode for filament\n" +
                        "\n" +
                        "M104 S0 ; turn off temperature\n" +
                        "G28 X0  ; home X axis\n" +
                        "M84     ; disable motors\n" +
                        "\n" +
                        "M140 S0 ; set bed temperature";
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
//--------------............................................-----------------------
                    salientemarcos[0]="; Filament gcode\n" +
                    "\n" +
                    "M109 S200 ; set temperature and wait for it to be reached\n" +
                    "G21 ; set units to millimeters\n" +
                    "G90 ; use absolute coordinates\n" +
                    "M82 ; use absolute distances for extrusion\n" +
                    "G92 E0\n" +
                    "G1 Z0.350 F7800.000\n" +
                    "G1 E-2.00000 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X40.838 Y76.319 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800";
                     salientemarcos[1]+="G1 X41.443 Y76.017 E2.01878\n" +
                    "G1 X42.317 Y75.637 E2.04525\n" +
                    "G1 X42.935 Y75.404 E2.06361\n" +
                    "G1 X43.944 Y75.081 E2.09303\n" +
                    "G1 X44.544 Y74.919 E2.11030\n" +
                    "G1 X45.745 Y74.657 E2.14443\n" +
                    "G1 X46.421 Y74.546 E2.16348\n" +
                    "G1 X49.672 Y74.185 E2.25433\n" +
                    "G1 X50.192 Y74.148 E2.26880\n" +
                    "G1 X56.099 Y73.967 E2.43297\n" +
                    "G1 X143.901 Y73.967 E4.87191\n" +
                    "G1 X149.808 Y74.148 E5.03608\n" +
                    "G1 X150.328 Y74.185 E5.05054\n" +
                    "G1 X153.579 Y74.546 E5.14140\n" +
                    "G1 X154.255 Y74.657 E5.16044\n" +
                    "G1 X155.456 Y74.919 E5.19458\n" +
                    "G1 X156.056 Y75.081 E5.21184\n" +
                    "G1 X157.065 Y75.404 E5.24127\n" +
                    "G1 X157.683 Y75.637 E5.25963\n" +
                    "G1 X158.557 Y76.017 E5.28610\n" +
                    "G1 X159.162 Y76.319 E5.30488\n" +
                    "G1 X160.482 Y77.076 E5.34716\n" +
                    "G1 X161.011 Y77.430 E5.36482\n" +
                    "G1 X162.008 Y78.157 E5.39911\n" +
                    "G1 X162.397 Y78.464 E5.41288\n" +
                    "G1 X163.198 Y79.171 E5.44256\n" +
                    "G1 X163.954 Y79.920 E5.47210\n" +
                    "G1 X164.412 Y80.424 E5.49102\n" +
                    "G1 X165.060 Y81.216 E5.51946\n" +
                    "G1 X165.371 Y81.628 E5.53380\n" +
                    "G1 X178.094 Y99.934 E6.15305\n" +
                    "G1 X178.532 Y100.661 E6.17662\n" +
                    "G1 X178.667 Y100.925 E6.18487\n" +
                    "G1 X179.265 Y102.843 E6.24065\n" +
                    "G1 X179.320 Y103.274 E6.25275\n" +
                    "G1 X179.358 Y103.852 E6.26882\n" +
                    "G1 X179.524 Y111.931 E6.49329\n" +
                    "G1 X179.460 Y112.991 E6.52279\n" +
                    "G1 X179.422 Y113.258 E6.53029\n" +
                    "G1 X178.712 Y115.427 E6.59368\n" +
                    "G1 X177.594 Y116.944 E6.64603\n" +
                    "G1 X177.278 Y117.249 E6.65821\n" +
                    "G1 X175.790 Y118.284 E6.70856\n" +
                    "G1 X174.491 Y118.806 E6.74745\n" +
                    "G1 X163.913 Y121.697 E7.05206\n" +
                    "G1 X162.176 Y122.132 E7.10180\n" +
                    "G1 X153.717 Y123.893 E7.34183\n" +
                    "G1 X149.144 Y124.730 E7.47096\n" +
                    "G1 X144.202 Y125.402 E7.60950\n" +
                    "G1 X135.970 Y126.017 E7.83880\n" +
                    "G1 X135.378 Y126.043 E7.85525\n" +
                    "G1 X64.622 Y126.043 E9.82070\n" +
                    "G1 X64.151 Y126.026 E9.83379\n" +
                    "G1 X55.800 Y125.402 E10.06641\n" +
                    "G1 X50.855 Y124.730 E10.20503\n" +
                    "G1 X46.283 Y123.893 E10.33413\n" +
                    "G1 X38.076 Y122.189 E10.56699\n" +
                    "G1 X36.229 Y121.734 E10.61983\n" +
                    "G1 X25.674 Y118.853 E10.92375\n" +
                    "G1 X24.551 Y118.452 E10.95687\n" +
                    "G1 X24.143 Y118.250 E10.96952\n" +
                    "G1 X22.617 Y117.150 E11.02178\n" +
                    "G1 X22.514 Y117.051 E11.02572\n" +
                    "G1 X21.174 Y115.204 E11.08912\n" +
                    "G1 X20.584 Y113.296 E11.14459\n" +
                    "G1 X20.533 Y112.939 E11.15461\n" +
                    "G1 X20.476 Y111.931 E11.18265\n" +
                    "G1 X20.642 Y103.852 E11.40712\n" +
                    "G1 X20.680 Y103.274 E11.42321\n" +
                    "G1 X20.735 Y102.842 E11.43529\n" +
                    "G1 X21.405 Y100.783 E11.49545\n" +
                    "G1 X21.906 Y99.935 E11.52280\n" +
                    "G1 X34.628 Y81.628 E12.14207\n" +
                    "G1 X34.940 Y81.216 E12.15641\n" +
                    "G1 X35.588 Y80.424 E12.18485\n" +
                    "G1 X36.046 Y79.920 E12.20377\n" +
                    "G1 X36.802 Y79.171 E12.23331\n" +
                    "G1 X37.603 Y78.464 E12.26299\n" +
                    "G1 X37.992 Y78.157 E12.27677\n" +
                    "G1 X39.305 Y77.213 E12.32167\n" +
                    "G1 X39.844 Y76.880 E12.33930\n" +
                    "G1 X40.773 Y76.355 E12.36891\n" +
                    "G1 E10.36891 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X67.302 Y117.689 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X60.441 Y117.433 E2.16145\n" +
                    "G1 X54.215 Y116.866 E2.30845\n" +
                    "G1 X49.959 Y116.204 E2.40973\n" +
                    "G1 X45.899 Y115.239 E2.50786\n" +
                    "G1 X43.404 Y114.452 E2.56938\n" +
                    "G1 X40.550 Y113.325 E2.64154\n" +
                    "G1 X39.830 Y112.986 E2.66023\n" +
                    "G1 X39.022 Y112.555 E2.68179\n" +
                    "G1 X38.373 Y112.142 E2.69986\n" +
                    "G1 X37.836 Y111.728 E2.71581\n" +
                    "G1 X37.270 Y111.177 E2.73439\n" +
                    "G1 X36.910 Y110.731 E2.74787\n" +
                    "G1 X36.591 Y110.240 E2.76164\n" +
                    "G1 X36.447 Y109.978 E2.76866\n" +
                    "G1 X36.149 Y109.326 E2.78552\n" +
                    "G1 X35.869 Y108.509 E2.80583\n" +
                    "G1 X35.621 Y107.441 E2.83161\n" +
                    "G1 X35.418 Y105.791 E2.87070\n" +
                    "G1 X35.385 Y103.890 E2.91542\n" +
                    "G1 X35.556 Y101.689 E2.96733\n" +
                    "G1 X36.107 Y98.543 E3.04242\n" +
                    "G1 X37.136 Y94.824 E3.13317\n" +
                    "G1 X38.472 Y91.275 E3.22233\n" +
                    "G1 X38.956 Y90.199 E3.25008\n" +
                    "G1 X39.992 Y88.197 E3.30308\n" +
                    "G1 X41.006 Y86.586 E3.34784\n" +
                    "G1 X41.420 Y86.019 E3.36434\n" +
                    "G1 X42.066 Y85.238 E3.38816\n" +
                    "G1 X42.704 Y84.587 E3.40962\n" +
                    "G1 X43.345 Y84.042 E3.42939\n" +
                    "G1 X43.740 Y83.757 E3.44083\n" +
                    "G1 X44.310 Y83.405 E3.45660\n" +
                    "G1 X45.060 Y83.035 E3.47627\n" +
                    "G1 X46.013 Y82.679 E3.50018\n" +
                    "G1 X47.435 Y82.310 E3.53473\n" +
                    "G1 X50.539 Y81.857 E3.60850\n" +
                    "G1 X55.190 Y81.597 E3.71803\n" +
                    "G1 X60.323 Y81.708 E3.83875\n" +
                    "G1 X60.935 Y81.742 E3.85317\n" +
                    "G1 X67.361 Y82.245 E4.00474\n" +
                    "G1 X71.148 Y82.693 E4.09440\n" +
                    "G1 X74.450 Y83.255 E4.17316\n" +
                    "G1 X77.243 Y83.931 E4.24074\n" +
                    "G1 X79.592 Y84.719 E4.29899\n" +
                    "G1 X81.065 Y85.353 E4.33670\n" +
                    "G1 X82.437 Y86.056 E4.37295\n" +
                    "G1 X83.585 Y86.741 E4.40438\n" +
                    "G1 X84.465 Y87.350 E4.42955\n" +
                    "G1 X85.157 Y87.929 E4.45077\n" +
                    "G1 X85.840 Y88.639 E4.47393\n" +
                    "G1 X86.329 Y89.279 E4.49287\n" +
                    "G1 X86.840 Y90.110 E4.51582\n" +
                    "G1 X87.414 Y91.288 E4.54664\n" +
                    "G1 X88.452 Y94.142 E4.61804\n" +
                    "G1 X89.468 Y97.733 E4.70579\n" +
                    "G1 X90.254 Y101.109 E4.78730\n" +
                    "G1 X90.774 Y104.099 E4.85865\n" +
                    "G1 X90.945 Y105.969 E4.90281\n" +
                    "G1 X90.951 Y107.176 E4.93119\n" +
                    "G1 X90.862 Y108.252 E4.95658\n" +
                    "G1 X90.685 Y109.242 E4.98023\n" +
                    "G1 X90.452 Y110.065 E5.00033\n" +
                    "G1 X90.149 Y110.831 E5.01970\n" +
                    "G1 X89.766 Y111.569 E5.03927\n" +
                    "G1 X89.331 Y112.240 E5.05806\n" +
                    "G1 X88.861 Y112.841 E5.07600\n" +
                    "G1 X88.376 Y113.363 E5.09276\n" +
                    "G1 X87.886 Y113.802 E5.10823\n" +
                    "G1 X87.308 Y114.218 E5.12497\n" +
                    "G1 X86.570 Y114.640 E5.14497\n" +
                    "G1 X85.143 Y115.264 E5.18159\n" +
                    "G1 X82.668 Y116.070 E5.24278\n" +
                    "G1 X78.556 Y116.986 E5.34185\n" +
                    "G1 X74.074 Y117.517 E5.44797\n" +
                    "G1 X68.861 Y117.699 E5.57063\n" +
                    "G1 X67.377 Y117.689 E5.60552\n" +
                    "G1 X67.111 Y118.023 F7800.000\n" +
                    "G1 E3.60552 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X46.445 Y82.032 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X45.953 Y82.159 E2.01410\n" +
                    "G1 X46.371 Y82.048 E2.02612\n" +
                    "G1 X45.747 Y82.225 F7800.000\n" +
                    "G1 F1800\n" +
                    "G1 X44.854 Y82.559 E2.05258\n" +
                    "G1 X44.059 Y82.951 E2.07722\n" +
                    "G1 X43.452 Y83.327 E2.09705\n" +
                    "G1 X43.026 Y83.634 E2.11165\n" +
                    "G1 X42.351 Y84.207 E2.13624\n" +
                    "G1 X41.680 Y84.892 E2.16285\n" +
                    "G1 X41.011 Y85.701 E2.19204\n" +
                    "G1 X40.577 Y86.295 E2.21246\n" +
                    "G1 X39.542 Y87.939 E2.26643\n" +
                    "G1 X38.982 Y89.022 E2.30028\n" +
                    "G1 X39.654 Y87.517 E2.34606\n" +
                    "G1 X40.122 Y86.655 E2.37329\n" +
                    "G1 X40.656 Y85.842 E2.40031\n" +
                    "G1 X41.252 Y85.114 E2.42645\n" +
                    "G1 X41.957 Y84.416 E2.45402\n" +
                    "G1 X42.350 Y84.081 E2.46836\n" +
                    "G1 X43.289 Y83.396 E2.50064\n" +
                    "G1 X44.420 Y82.742 E2.53694\n" +
                    "G1 X45.216 Y82.395 E2.56105\n" +
                    "G1 X45.675 Y82.248 E2.57445\n" +
                    "G1 E0.57445 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X34.305 Y103.857 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X34.340 Y105.866 E2.05582\n" +
                    "G1 X34.557 Y107.630 E2.10517\n" +
                    "G1 X34.831 Y108.806 E2.13872\n" +
                    "G1 X35.145 Y109.726 E2.16573\n" +
                    "G1 X35.482 Y110.464 E2.18825\n" +
                    "G1 X35.664 Y110.795 E2.19875\n" +
                    "G1 X36.035 Y111.365 E2.21765\n" +
                    "G1 X36.471 Y111.905 E2.23692\n" +
                    "G1 X37.128 Y112.545 E2.26240\n" +
                    "G1 X37.753 Y113.026 E2.28430\n" +
                    "G1 X38.477 Y113.488 E2.30817\n" +
                    "G1 X39.347 Y113.951 E2.33553\n" +
                    "G1 X40.122 Y114.316 E2.35932\n" +
                    "G1 X41.270 Y114.769 E2.39362\n" +
                    "G1 X39.712 Y114.445 E2.43782\n" +
                    "G1 X38.169 Y114.061 E2.48201\n" +
                    "G1 X32.530 Y112.418 E2.64515\n" +
                    "G1 X28.444 Y111.391 E2.76219\n" +
                    "G1 X28.508 Y110.518 E2.78650\n" +
                    "G1 X28.576 Y107.209 E2.87846\n" +
                    "G1 X28.561 Y104.901 E2.94255\n" +
                    "G1 X31.107 Y105.066 E3.01340\n" +
                    "G1 X31.542 Y105.079 E3.02550\n" +
                    "G1 X32.200 Y105.051 E3.04378\n" +
                    "G1 X32.778 Y104.932 E3.06019\n" +
                    "G1 X33.330 Y104.689 E3.07693\n" +
                    "G1 X33.737 Y104.395 E3.09086\n" +
                    "G1 X34.105 Y104.002 E3.10584\n" +
                    "G1 X34.317 Y103.699 E3.11612\n" +
                    "G1 X34.311 Y103.782 E3.11845\n" +
                    "G1 E1.11845 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X88.563 Y114.643 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X89.133 Y114.134 E2.02121\n" +
                    "G1 X89.683 Y113.542 E2.04368\n" +
                    "G1 X90.211 Y112.867 E2.06748\n" +
                    "G1 X90.699 Y112.112 E2.09245\n" +
                    "G1 X91.132 Y111.279 E2.11854\n" +
                    "G1 X91.475 Y110.411 E2.14446\n" +
                    "G1 X91.738 Y109.485 E2.17120\n" +
                    "G1 X91.933 Y108.391 E2.20205\n" +
                    "G1 X92.030 Y107.218 E2.23477\n" +
                    "G1 X92.016 Y105.830 E2.27330\n" +
                    "G1 X92.250 Y106.641 E2.29674\n" +
                    "G1 X92.504 Y107.313 E2.31669\n" +
                    "G1 X92.771 Y107.883 E2.33419\n" +
                    "G1 X93.071 Y108.397 E2.35072\n" +
                    "G1 X93.547 Y109.021 E2.37251\n" +
                    "G1 X93.949 Y109.428 E2.38840\n" +
                    "G1 X94.392 Y109.796 E2.40439\n" +
                    "G1 X95.078 Y110.238 E2.42707\n" +
                    "G1 X95.751 Y110.562 E2.44783\n" +
                    "G1 X96.614 Y110.864 E2.47322\n" +
                    "G1 X97.522 Y111.075 E2.49909\n" +
                    "G1 X98.685 Y111.219 E2.53165\n" +
                    "G1 X100.045 Y111.245 E2.56945\n" +
                    "G1 X101.316 Y111.219 E2.60476\n" +
                    "G1 X102.478 Y111.075 E2.63730\n" +
                    "G1 X103.386 Y110.864 E2.66317\n" +
                    "G1 X104.249 Y110.562 E2.68856\n" +
                    "G1 X104.922 Y110.238 E2.70932\n" +
                    "G1 X105.608 Y109.796 E2.73200\n" +
                    "G1 X106.051 Y109.428 E2.74799\n" +
                    "G1 X106.453 Y109.021 E2.76388\n" +
                    "G1 X106.929 Y108.397 E2.78567\n" +
                    "G1 X107.229 Y107.883 E2.80221\n" +
                    "G1 X107.496 Y107.313 E2.81970\n" +
                    "G1 X107.750 Y106.641 E2.83965\n" +
                    "G1 X107.984 Y105.830 E2.86309\n" +
                    "G1 X107.970 Y107.218 E2.90162\n" +
                    "G1 X108.067 Y108.391 E2.93434\n" +
                    "G1 X108.262 Y109.485 E2.96520\n" +
                    "G1 X108.525 Y110.411 E2.99193\n" +
                    "G1 X108.868 Y111.279 E3.01785\n" +
                    "G1 X109.301 Y112.112 E3.04394\n" +
                    "G1 X109.789 Y112.867 E3.06891\n" +
                    "G1 X110.317 Y113.542 E3.09271\n" +
                    "G1 X110.867 Y114.134 E3.11517\n" +
                    "G1 X111.437 Y114.643 E3.13639\n" +
                    "G1 X110.392 Y114.296 E3.16698\n" +
                    "G1 X107.460 Y113.399 E3.25214\n" +
                    "G1 X104.792 Y112.824 E3.32795\n" +
                    "G1 X102.497 Y112.505 E3.39231\n" +
                    "G1 X101.197 Y112.429 E3.42849\n" +
                    "G1 X100.000 Y112.446 E3.46175\n" +
                    "G1 X98.803 Y112.429 E3.49500\n" +
                    "G1 X97.503 Y112.505 E3.53118\n" +
                    "G1 X95.208 Y112.824 E3.59554\n" +
                    "G1 X92.540 Y113.399 E3.67135\n" +
                    "G1 X89.608 Y114.296 E3.75651\n" +
                    "G1 X88.634 Y114.620 E3.78502\n" +
                    "G1 E1.78502 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X158.730 Y114.769 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X159.878 Y114.316 E2.03430\n" +
                    "G1 X160.653 Y113.951 E2.05809\n" +
                    "G1 X161.523 Y113.488 E2.08546\n" +
                    "G1 X162.256 Y113.021 E2.10962\n" +
                    "G1 X163.041 Y112.396 E2.13747\n" +
                    "G1 X163.537 Y111.896 E2.15703\n" +
                    "G1 X163.965 Y111.365 E2.17597\n" +
                    "G1 X164.336 Y110.795 E2.19488\n" +
                    "G1 X164.518 Y110.464 E2.20537\n" +
                    "G1 X164.855 Y109.726 E2.22790\n" +
                    "G1 X165.169 Y108.806 E2.25490\n" +
                    "G1 X165.443 Y107.630 E2.28845\n" +
                    "G1 X165.660 Y105.866 E2.33780\n" +
                    "G1 X165.695 Y103.857 E2.39362\n" +
                    "G1 X165.683 Y103.699 E2.39804\n" +
                    "G1 X165.895 Y104.002 E2.40832\n" +
                    "G1 X166.264 Y104.395 E2.42329\n" +
                    "G1 X166.670 Y104.689 E2.43723\n" +
                    "G1 X167.222 Y104.932 E2.45396\n" +
                    "G1 X167.800 Y105.051 E2.47038\n" +
                    "G1 X168.458 Y105.079 E2.48866\n" +
                    "G1 X168.893 Y105.066 E2.50076\n" +
                    "G1 X171.439 Y104.901 E2.57161\n" +
                    "G1 X171.424 Y107.209 E2.63570\n" +
                    "G1 X171.492 Y110.518 E2.72766\n" +
                    "G1 X171.556 Y111.391 E2.75196\n" +
                    "G1 X167.470 Y112.418 E2.86901\n" +
                    "G1 X161.831 Y114.061 E3.03214\n" +
                    "G1 X160.288 Y114.445 E3.07634\n" +
                    "G1 X158.803 Y114.754 E3.11845\n" +
                    "G1 E1.11845 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X172.151 Y111.820 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X167.616 Y112.959 E2.12987\n" +
                    "G1 X161.978 Y114.602 E2.29301\n" +
                    "G1 X160.413 Y114.992 E2.33782\n" +
                    "G1 X152.372 Y116.663 E2.56593\n" +
                    "G1 X148.135 Y117.446 E2.68563\n" +
                    "G1 X143.236 Y118.112 E2.82296\n" +
                    "G1 X135.350 Y118.689 E3.04260\n" +
                    "G1 X131.564 Y118.679 E3.14776\n" +
                    "G1 X126.745 Y118.549 E3.28167\n" +
                    "G1 X122.089 Y117.959 E3.41205\n" +
                    "G1 X117.525 Y117.063 E3.54124\n" +
                    "G1 X114.999 Y116.418 E3.61367\n" +
                    "G1 X110.221 Y114.830 E3.75352\n" +
                    "G1 X107.319 Y113.942 E3.83782\n" +
                    "G1 X104.694 Y113.377 E3.91240\n" +
                    "G1 X102.442 Y113.064 E3.97557\n" +
                    "G1 X101.185 Y112.990 E4.01056\n" +
                    "G1 X100.000 Y113.007 E4.04346\n" +
                    "G1 X98.815 Y112.990 E4.07637\n" +
                    "G1 X97.558 Y113.064 E4.11136\n" +
                    "G1 X95.306 Y113.377 E4.17453\n" +
                    "G1 X92.681 Y113.942 E4.24910\n" +
                    "G1 X89.779 Y114.830 E4.33341\n" +
                    "G1 X85.001 Y116.418 E4.47325\n" +
                    "G1 X82.475 Y117.063 E4.54569\n" +
                    "G1 X77.911 Y117.959 E4.67487\n" +
                    "G1 X73.255 Y118.549 E4.80526\n" +
                    "G1 X68.436 Y118.679 E4.93917\n" +
                    "G1 X64.650 Y118.689 E5.04433\n" +
                    "G1 X56.764 Y118.112 E5.26397\n" +
                    "G1 X51.865 Y117.446 E5.40129\n" +
                    "G1 X47.628 Y116.663 E5.52100\n" +
                    "G1 X39.587 Y114.992 E5.74911\n" +
                    "G1 X38.022 Y114.602 E5.79392\n" +
                    "G1 X32.383 Y112.959 E5.95706\n" +
                    "G1 X27.849 Y111.820 E6.08693\n" +
                    "G1 X27.948 Y110.492 E6.12392\n" +
                    "G1 X28.014 Y107.205 E6.21525\n" +
                    "G1 X27.997 Y104.325 E6.29524\n" +
                    "G1 X28.278 Y104.321 E6.30305\n" +
                    "G1 X31.133 Y104.505 E6.38253\n" +
                    "G1 X31.539 Y104.518 E6.39379\n" +
                    "G1 X32.131 Y104.492 E6.41025\n" +
                    "G1 X32.607 Y104.394 E6.42376\n" +
                    "G1 X33.049 Y104.199 E6.43719\n" +
                    "G1 X33.365 Y103.972 E6.44799\n" +
                    "G1 X33.669 Y103.648 E6.46033\n" +
                    "G1 X33.850 Y103.388 E6.46914\n" +
                    "G1 X34.079 Y102.977 E6.48221\n" +
                    "G1 X34.435 Y102.138 E6.50751\n" +
                    "G1 X34.782 Y101.130 E6.53712\n" +
                    "G1 X35.556 Y98.687 E6.60831\n" +
                    "G1 X35.041 Y101.624 E6.69111\n" +
                    "G1 X34.866 Y103.874 E6.75381\n" +
                    "G1 X34.901 Y105.827 E6.80808\n" +
                    "G1 X35.110 Y107.532 E6.85578\n" +
                    "G1 X35.371 Y108.652 E6.88772\n" +
                    "G1 X35.667 Y109.518 E6.91316\n" +
                    "G1 X35.984 Y110.211 E6.93433\n" +
                    "G1 X36.146 Y110.506 E6.94368\n" +
                    "G1 X36.490 Y111.036 E6.96121\n" +
                    "G1 X36.886 Y111.527 E6.97874\n" +
                    "G1 X37.496 Y112.120 E7.00239\n" +
                    "G1 X38.075 Y112.567 E7.02269\n" +
                    "G1 X38.760 Y113.003 E7.04525\n" +
                    "G1 X39.598 Y113.450 E7.07163\n" +
                    "G1 X40.344 Y113.800 E7.09453\n" +
                    "G1 X43.231 Y114.940 E7.18074\n" +
                    "G1 X45.761 Y115.739 E7.25444\n" +
                    "G1 X49.860 Y116.713 E7.37146\n" +
                    "G1 X54.152 Y117.381 E7.49212\n" +
                    "G1 X60.407 Y117.950 E7.66661\n" +
                    "G1 X66.906 Y118.204 E7.84725\n" +
                    "G1 X68.869 Y118.217 E7.90178\n" +
                    "G1 X74.114 Y118.034 E8.04757\n" +
                    "G1 X78.643 Y117.497 E8.17427\n" +
                    "G1 X82.805 Y116.570 E8.29271\n" +
                    "G1 X85.327 Y115.749 E8.36638\n" +
                    "G1 X86.803 Y115.104 E8.41113\n" +
                    "G1 X87.589 Y114.654 E8.43628\n" +
                    "G1 X88.211 Y114.206 E8.45757\n" +
                    "G1 X88.739 Y113.733 E8.47727\n" +
                    "G1 X89.256 Y113.177 E8.49834\n" +
                    "G1 X89.753 Y112.541 E8.52079\n" +
                    "G1 X90.214 Y111.830 E8.54432\n" +
                    "G1 X90.621 Y111.046 E8.56886\n" +
                    "G1 X90.943 Y110.231 E8.59320\n" +
                    "G1 X91.190 Y109.359 E8.61838\n" +
                    "G1 X91.376 Y108.319 E8.64772\n" +
                    "G1 X91.469 Y107.196 E8.67902\n" +
                    "G1 X91.463 Y105.944 E8.71379\n" +
                    "G1 X91.288 Y104.031 E8.76716\n" +
                    "G1 X90.762 Y101.006 E8.85245\n" +
                    "G1 X89.970 Y97.604 E8.94948\n" +
                    "G1 X88.945 Y93.983 E9.05400\n" +
                    "G1 X87.892 Y91.086 E9.13964\n" +
                    "G1 X87.294 Y89.861 E9.17750\n" +
                    "G1 X86.757 Y88.985 E9.20604\n" +
                    "G1 X86.233 Y88.301 E9.22996\n" +
                    "G1 X85.511 Y87.549 E9.25892\n" +
                    "G1 X84.779 Y86.938 E9.28543\n" +
                    "G1 X83.866 Y86.305 E9.31629\n" +
                    "G1 X82.688 Y85.603 E9.35437\n" +
                    "G1 X81.286 Y84.884 E9.39814\n" +
                    "G1 X79.777 Y84.234 E9.44378\n" +
                    "G1 X77.387 Y83.433 E9.51380\n" +
                    "G1 X74.555 Y82.747 E9.59474\n" +
                    "G1 X71.222 Y82.180 E9.68865\n" +
                    "G1 X67.412 Y81.729 E9.79522\n" +
                    "G1 X62.945 Y81.380 E9.91968\n" +
                    "G1 X63.612 Y81.386 E9.93821\n" +
                    "G1 X67.930 Y81.601 E10.05829\n" +
                    "G1 X71.995 Y82.018 E10.17180\n" +
                    "G1 X75.759 Y82.641 E10.27777\n" +
                    "G1 X79.133 Y83.445 E10.37413\n" +
                    "G1 X79.682 Y83.612 E10.39007\n" +
                    "G1 X81.261 Y84.194 E10.43680\n" +
                    "G1 X82.724 Y84.915 E10.48212\n" +
                    "G1 X83.659 Y85.472 E10.51235\n" +
                    "G1 X84.544 Y86.085 E10.54225\n" +
                    "G1 X85.284 Y86.691 E10.56881\n" +
                    "G1 X85.910 Y87.307 E10.59323\n" +
                    "G1 X86.241 Y87.689 E10.60728\n" +
                    "G1 X86.757 Y88.398 E10.63162\n" +
                    "G1 X87.355 Y89.430 E10.66476\n" +
                    "G1 X87.937 Y90.677 E10.70297\n" +
                    "G1 X89.353 Y94.548 E10.81748\n" +
                    "G1 X91.402 Y101.303 E11.01357\n" +
                    "G1 X92.397 Y105.132 E11.12345\n" +
                    "G1 X92.783 Y106.464 E11.16196\n" +
                    "G1 X93.021 Y107.094 E11.18068\n" +
                    "G1 X93.269 Y107.622 E11.19688\n" +
                    "G1 X93.538 Y108.084 E11.21175\n" +
                    "G1 X93.972 Y108.652 E11.23159\n" +
                    "G1 X94.328 Y109.014 E11.24571\n" +
                    "G1 X94.724 Y109.342 E11.25998\n" +
                    "G1 X95.353 Y109.747 E11.28076\n" +
                    "G1 X95.966 Y110.043 E11.29968\n" +
                    "G1 X96.771 Y110.324 E11.32335\n" +
                    "G1 X97.620 Y110.522 E11.34757\n" +
                    "G1 X98.725 Y110.659 E11.37849\n" +
                    "G1 X100.045 Y110.684 E11.41516\n" +
                    "G1 X101.276 Y110.659 E11.44937\n" +
                    "G1 X102.380 Y110.522 E11.48028\n" +
                    "G1 X103.229 Y110.324 E11.50449\n" +
                    "G1 X104.034 Y110.043 E11.52816\n" +
                    "G1 X104.647 Y109.747 E11.54708\n" +
                    "G1 X105.276 Y109.342 E11.56786\n" +
                    "G1 X105.672 Y109.014 E11.58214\n" +
                    "G1 X106.028 Y108.652 E11.59625\n" +
                    "G1 X106.462 Y108.084 E11.61610\n" +
                    "G1 X106.731 Y107.622 E11.63097\n" +
                    "G1 X106.979 Y107.094 E11.64716\n" +
                    "G1 X107.217 Y106.464 E11.66589\n" +
                    "G1 X107.603 Y105.132 E11.70439\n" +
                    "G1 X108.598 Y101.303 E11.81428\n" +
                    "G1 X110.647 Y94.548 E12.01037\n" +
                    "G1 X112.063 Y90.677 E12.12488\n" +
                    "G1 X112.645 Y89.430 E12.16309\n" +
                    "G1 X113.243 Y88.398 E12.19623\n" +
                    "G1 X113.759 Y87.689 E12.22057\n" +
                    "G1 X114.090 Y87.307 E12.23461\n" +
                    "G1 X114.716 Y86.691 E12.25903\n" +
                    "G1 X115.456 Y86.085 E12.28560\n" +
                    "G1 X116.341 Y85.472 E12.31550\n" +
                    "G1 X117.276 Y84.915 E12.34573\n" +
                    "G1 X118.739 Y84.193 E12.39105\n" +
                    "G1 X120.318 Y83.612 E12.43777\n" +
                    "G1 X120.867 Y83.445 E12.45372\n" +
                    "G1 X124.241 Y82.641 E12.55008\n" +
                    "G1 X128.005 Y82.018 E12.65605\n" +
                    "G1 X132.070 Y81.601 E12.76956\n" +
                    "G1 X136.388 Y81.386 E12.88964\n" +
                    "G1 X137.055 Y81.380 E12.90817\n" +
                    "G1 X132.588 Y81.729 E13.03262\n" +
                    "G1 X128.778 Y82.180 E13.13920\n" +
                    "G1 X125.445 Y82.747 E13.23310\n" +
                    "G1 X122.613 Y83.433 E13.31405\n" +
                    "G1 X120.223 Y84.234 E13.38407\n" +
                    "G1 X118.714 Y84.884 E13.42971\n" +
                    "G1 X117.312 Y85.603 E13.47347\n" +
                    "G1 X116.134 Y86.305 E13.51156\n" +
                    "G1 X115.218 Y86.940 E13.54254\n" +
                    "G1 X114.343 Y87.687 E13.57448\n" +
                    "G1 X113.764 Y88.305 E13.59802\n" +
                    "G1 X113.243 Y88.985 E13.62181\n" +
                    "G1 X112.706 Y89.861 E13.65035\n" +
                    "G1 X112.108 Y91.086 E13.68821\n" +
                    "G1 X111.055 Y93.983 E13.77384\n" +
                    "G1 X110.030 Y97.604 E13.87837\n" +
                    "G1 X109.238 Y101.006 E13.97540\n" +
                    "G1 X108.712 Y104.031 E14.06068\n" +
                    "G1 X108.537 Y105.944 E14.11406\n" +
                    "G1 X108.531 Y107.196 E14.14883\n" +
                    "G1 X108.624 Y108.319 E14.18012\n" +
                    "G1 X108.810 Y109.359 E14.20947\n" +
                    "G1 X109.057 Y110.231 E14.23465\n" +
                    "G1 X109.379 Y111.046 E14.25899\n" +
                    "G1 X109.786 Y111.830 E14.28353\n" +
                    "G1 X110.247 Y112.541 E14.30706\n" +
                    "G1 X110.744 Y113.177 E14.32950\n" +
                    "G1 X111.261 Y113.733 E14.35058\n" +
                    "G1 X111.789 Y114.206 E14.37028\n" +
                    "G1 X112.411 Y114.654 E14.39157\n" +
                    "G1 X113.197 Y115.104 E14.41672\n" +
                    "G1 X114.673 Y115.749 E14.46147\n" +
                    "G1 X117.195 Y116.570 E14.53514\n" +
                    "G1 X121.357 Y117.497 E14.65358\n" +
                    "G1 X125.886 Y118.034 E14.78028\n" +
                    "G1 X131.131 Y118.217 E14.92607\n" +
                    "G1 X133.094 Y118.204 E14.98059\n" +
                    "G1 X139.593 Y117.950 E15.16124\n" +
                    "G1 X145.848 Y117.381 E15.33572\n" +
                    "G1 X150.140 Y116.713 E15.45639\n" +
                    "G1 X154.239 Y115.739 E15.57341\n" +
                    "G1 X156.769 Y114.940 E15.64710\n" +
                    "G1 X159.656 Y113.800 E15.73332\n" +
                    "G1 X160.402 Y113.450 E15.75622\n" +
                    "G1 X161.240 Y113.003 E15.78259\n" +
                    "G1 X161.930 Y112.563 E15.80533\n" +
                    "G1 X162.665 Y111.977 E15.83144\n" +
                    "G1 X163.118 Y111.521 E15.84930\n" +
                    "G1 X163.510 Y111.036 E15.86663\n" +
                    "G1 X163.854 Y110.506 E15.88417\n" +
                    "G1 X164.016 Y110.211 E15.89352\n" +
                    "G1 X164.333 Y109.518 E15.91469\n" +
                    "G1 X164.629 Y108.652 E15.94013\n" +
                    "G1 X164.890 Y107.532 E15.97207\n" +
                    "G1 X165.099 Y105.827 E16.01977\n" +
                    "G1 X165.134 Y103.874 E16.07403\n" +
                    "G1 X164.959 Y101.624 E16.13673\n" +
                    "G1 X164.444 Y98.687 E16.21954\n" +
                    "G1 X165.218 Y101.130 E16.29073\n" +
                    "G1 X165.565 Y102.138 E16.32034\n" +
                    "G1 X165.921 Y102.977 E16.34564\n" +
                    "G1 X166.150 Y103.388 E16.35871\n" +
                    "G1 X166.331 Y103.648 E16.36752\n" +
                    "G1 X166.635 Y103.972 E16.37986\n" +
                    "G1 X166.951 Y104.199 E16.39066\n" +
                    "G1 X167.393 Y104.394 E16.40409\n" +
                    "G1 X167.869 Y104.492 E16.41760\n" +
                    "G1 X168.461 Y104.518 E16.43406\n" +
                    "G1 X168.867 Y104.505 E16.44532\n" +
                    "G1 X171.723 Y104.321 E16.52484\n" +
                    "G1 X172.003 Y104.324 E16.53261\n" +
                    "G1 X171.986 Y107.205 E16.61263\n" +
                    "G1 X172.052 Y110.492 E16.70396\n" +
                    "G1 X172.145 Y111.745 E16.73887\n" +
                    "G1 E14.73887 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X163.955 Y109.023 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X163.851 Y109.326 E2.00754\n" +
                    "G1 X163.553 Y109.978 E2.02439\n" +
                    "G1 X163.409 Y110.240 E2.03142\n" +
                    "G1 X163.090 Y110.731 E2.04518\n" +
                    "G1 X162.732 Y111.175 E2.05860\n" +
                    "G1 X162.319 Y111.591 E2.07238\n" +
                    "G1 X161.629 Y112.141 E2.09314\n" +
                    "G1 X160.978 Y112.555 E2.11127\n" +
                    "G1 X160.170 Y112.986 E2.13282\n" +
                    "G1 X159.450 Y113.325 E2.15151\n" +
                    "G1 X156.596 Y114.452 E2.22368\n" +
                    "G1 X154.101 Y115.239 E2.28519\n" +
                    "G1 X150.041 Y116.204 E2.38333\n" +
                    "G1 X145.785 Y116.866 E2.48460\n" +
                    "G1 X139.559 Y117.433 E2.63160\n" +
                    "G1 X133.083 Y117.686 E2.78401\n" +
                    "G1 X131.139 Y117.699 E2.82971\n" +
                    "G1 X125.926 Y117.517 E2.95237\n" +
                    "G1 X121.444 Y116.986 E3.05849\n" +
                    "G1 X117.332 Y116.070 E3.15756\n" +
                    "G1 X114.857 Y115.264 E3.21875\n" +
                    "G1 X113.430 Y114.640 E3.25538\n" +
                    "G1 X112.692 Y114.218 E3.27537\n" +
                    "G1 X112.114 Y113.802 E3.29211\n" +
                    "G1 X111.624 Y113.363 E3.30758\n" +
                    "G1 X111.139 Y112.841 E3.32434\n" +
                    "G1 X110.669 Y112.240 E3.34228\n" +
                    "G1 X110.234 Y111.569 E3.36107\n" +
                    "G1 X109.851 Y110.831 E3.38064\n" +
                    "G1 X109.548 Y110.065 E3.40001\n" +
                    "G1 X109.315 Y109.242 E3.42011\n" +
                    "G1 X109.138 Y108.252 E3.44377\n" +
                    "G1 X109.049 Y107.176 E3.46915\n" +
                    "G1 X109.055 Y105.969 E3.49753\n" +
                    "G1 X109.226 Y104.099 E3.54169\n" +
                    "G1 X109.746 Y101.109 E3.61304\n" +
                    "G1 X110.532 Y97.733 E3.69455\n" +
                    "G1 X111.548 Y94.142 E3.78230\n" +
                    "G1 X112.586 Y91.288 E3.85370\n" +
                    "G1 X113.160 Y90.110 E3.88452\n" +
                    "G1 X113.671 Y89.279 E3.90747\n" +
                    "G1 X114.159 Y88.641 E3.92637\n" +
                    "G1 X114.701 Y88.062 E3.94501\n" +
                    "G1 X115.534 Y87.351 E3.97076\n" +
                    "G1 X116.415 Y86.741 E3.99596\n" +
                    "G1 X117.563 Y86.056 E4.02739\n" +
                    "G1 X118.935 Y85.353 E4.06364\n" +
                    "G1 X120.408 Y84.719 E4.10135\n" +
                    "G1 X122.757 Y83.931 E4.15960\n" +
                    "G1 X125.550 Y83.255 E4.22718\n" +
                    "G1 X128.852 Y82.693 E4.30594\n" +
                    "G1 X132.639 Y82.245 E4.39560\n" +
                    "G1 X139.677 Y81.708 E4.56158\n" +
                    "G1 X144.810 Y81.597 E4.68231\n" +
                    "G1 X149.461 Y81.857 E4.79184\n" +
                    "G1 X152.565 Y82.310 E4.86561\n" +
                    "G1 X153.987 Y82.679 E4.90015\n" +
                    "G1 X154.940 Y83.035 E4.92407\n" +
                    "G1 X155.690 Y83.405 E4.94373\n" +
                    "G1 X156.260 Y83.757 E4.95950\n" +
                    "G1 X156.655 Y84.042 E4.97094\n" +
                    "G1 X157.296 Y84.587 E4.99072\n" +
                    "G1 X157.934 Y85.238 E5.01218\n" +
                    "G1 X158.580 Y86.019 E5.03600\n" +
                    "G1 X158.994 Y86.586 E5.05250\n" +
                    "G1 X160.008 Y88.197 E5.09725\n" +
                    "G1 X161.044 Y90.199 E5.15026\n" +
                    "G1 X161.528 Y91.275 E5.17801\n" +
                    "G1 X162.864 Y94.824 E5.26717\n" +
                    "G1 X163.893 Y98.543 E5.35792\n" +
                    "G1 X164.444 Y101.689 E5.43301\n" +
                    "G1 X164.615 Y103.890 E5.48492\n" +
                    "G1 X164.582 Y105.791 E5.52963\n" +
                    "G1 X164.379 Y107.441 E5.56873\n" +
                    "G1 X164.131 Y108.509 E5.59450\n" +
                    "G1 X163.979 Y108.952 E5.60552\n" +
                    "G1 X164.171 Y109.260 F7800.000\n" +
                    "G1 E3.60552 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X154.047 Y82.159 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X153.555 Y82.032 E2.01410\n" +
                    "G1 X153.975 Y82.136 E2.02612\n" +
                    "G1 X154.253 Y82.225 F7800.000\n" +
                    "G1 F1800\n" +
                    "G1 X154.784 Y82.395 E2.04160\n" +
                    "G1 X155.584 Y82.743 E2.06583\n" +
                    "G1 X156.505 Y83.263 E2.09521\n" +
                    "G1 X157.653 Y84.083 E2.13440\n" +
                    "G1 X158.043 Y84.416 E2.14863\n" +
                    "G1 X158.748 Y85.114 E2.17620\n" +
                    "G1 X159.344 Y85.842 E2.20234\n" +
                    "G1 X159.878 Y86.655 E2.22936\n" +
                    "G1 X160.346 Y87.517 E2.25659\n" +
                    "G1 X161.018 Y89.022 E2.30237\n" +
                    "G1 X160.458 Y87.939 E2.33623\n" +
                    "G1 X159.423 Y86.295 E2.39020\n" +
                    "G1 X158.989 Y85.701 E2.41062\n" +
                    "G1 X158.320 Y84.892 E2.43980\n" +
                    "G1 X157.649 Y84.207 E2.46641\n" +
                    "G1 X156.974 Y83.634 E2.49100\n" +
                    "G1 X156.548 Y83.327 E2.50560\n" +
                    "G1 X155.941 Y82.951 E2.52543\n" +
                    "G1 X155.146 Y82.559 E2.55007\n" +
                    "G1 X154.323 Y82.251 E2.57445\n" +
                    "G1 E0.57445 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X172.509 Y103.896 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X172.523 Y104.004 E2.00254\n" +
                    "G1 X172.503 Y107.201 E2.07772\n" +
                    "G1 X172.570 Y110.467 E2.15455\n" +
                    "G1 X172.688 Y112.057 E2.19202\n" +
                    "G1 X172.667 Y112.201 E2.19545\n" +
                    "G1 X172.601 Y112.237 E2.19722\n" +
                    "G1 X167.752 Y113.459 E2.31480\n" +
                    "G1 X162.113 Y115.102 E2.45292\n" +
                    "G1 X160.528 Y115.497 E2.49133\n" +
                    "G1 X152.472 Y117.172 E2.68480\n" +
                    "G1 X148.217 Y117.958 E2.78655\n" +
                    "G1 X143.290 Y118.627 E2.90347\n" +
                    "G1 X135.368 Y119.207 E3.09023\n" +
                    "G1 X131.557 Y119.197 E3.17986\n" +
                    "G1 X126.706 Y119.066 E3.29397\n" +
                    "G1 X122.006 Y118.470 E3.40536\n" +
                    "G1 X117.411 Y117.568 E3.51547\n" +
                    "G1 X114.853 Y116.915 E3.57756\n" +
                    "G1 X110.064 Y115.324 E3.69622\n" +
                    "G1 X107.188 Y114.444 E3.76693\n" +
                    "G1 X104.604 Y113.887 E3.82909\n" +
                    "G1 X102.391 Y113.580 E3.88163\n" +
                    "G1 X101.173 Y113.508 E3.91032\n" +
                    "G1 X100.000 Y113.525 E3.93790\n" +
                    "G1 X98.827 Y113.508 E3.96549\n" +
                    "G1 X97.609 Y113.580 E3.99418\n" +
                    "G1 X95.396 Y113.887 E4.04672\n" +
                    "G1 X92.812 Y114.444 E4.10888\n" +
                    "G1 X89.936 Y115.324 E4.17958\n" +
                    "G1 X85.147 Y116.915 E4.29825\n" +
                    "G1 X82.589 Y117.568 E4.36034\n" +
                    "G1 X77.994 Y118.470 E4.47045\n" +
                    "G1 X73.294 Y119.066 E4.58184\n" +
                    "G1 X68.443 Y119.197 E4.69595\n" +
                    "G1 X64.632 Y119.207 E4.78557\n" +
                    "G1 X56.710 Y118.627 E4.97233\n" +
                    "G1 X51.783 Y117.958 E5.08925\n" +
                    "G1 X47.528 Y117.172 E5.19101\n" +
                    "G1 X39.472 Y115.497 E5.38448\n" +
                    "G1 X37.887 Y115.102 E5.42289\n" +
                    "G1 X32.248 Y113.459 E5.56100\n" +
                    "G1 X27.399 Y112.236 E5.67859\n" +
                    "G1 X27.333 Y112.201 E5.68035\n" +
                    "G1 X27.312 Y112.057 E5.68378\n" +
                    "G1 X27.430 Y110.467 E5.72126\n" +
                    "G1 X27.497 Y107.201 E5.79808\n" +
                    "G1 X27.477 Y104.004 E5.87327\n" +
                    "G1 X27.491 Y103.897 E5.87578\n" +
                    "G1 X27.537 Y103.837 E5.87757\n" +
                    "G1 X27.634 Y103.813 E5.87991\n" +
                    "G1 X28.291 Y103.802 E5.89537\n" +
                    "G1 X31.158 Y103.988 E5.96293\n" +
                    "G1 X31.535 Y103.999 E5.97181\n" +
                    "G1 X32.067 Y103.976 E5.98432\n" +
                    "G1 X32.448 Y103.898 E5.99348\n" +
                    "G1 X32.790 Y103.747 E6.00227\n" +
                    "G1 X33.021 Y103.581 E6.00895\n" +
                    "G1 X33.265 Y103.321 E6.01735\n" +
                    "G1 X33.410 Y103.113 E6.02330\n" +
                    "G1 X33.613 Y102.749 E6.03310\n" +
                    "G1 X33.951 Y101.953 E6.05344\n" +
                    "G1 X34.290 Y100.968 E6.07793\n" +
                    "G1 X36.308 Y94.604 E6.23492\n" +
                    "G1 X37.251 Y92.001 E6.30001\n" +
                    "G1 X38.353 Y89.159 E6.37168\n" +
                    "G1 X39.190 Y87.287 E6.41990\n" +
                    "G1 X39.677 Y86.389 E6.44393\n" +
                    "G1 X40.238 Y85.535 E6.46795\n" +
                    "G1 X40.869 Y84.765 E6.49135\n" +
                    "G1 X41.606 Y84.034 E6.51578\n" +
                    "G1 X42.029 Y83.674 E6.52883\n" +
                    "G1 X43.006 Y82.961 E6.55727\n" +
                    "G1 X44.187 Y82.279 E6.58933\n" +
                    "G1 X45.033 Y81.910 E6.61105\n" +
                    "G1 X46.016 Y81.595 E6.63532\n" +
                    "G1 X47.190 Y81.339 E6.66357\n" +
                    "G1 X50.416 Y80.981 E6.73988\n" +
                    "G1 X56.102 Y80.803 E6.87366\n" +
                    "G1 X63.628 Y80.868 E7.05062\n" +
                    "G1 X67.969 Y81.085 E7.15284\n" +
                    "G1 X72.063 Y81.504 E7.24962\n" +
                    "G1 X75.861 Y82.133 E7.34013\n" +
                    "G1 X79.268 Y82.945 E7.42249\n" +
                    "G1 X79.847 Y83.121 E7.43671\n" +
                    "G1 X81.465 Y83.717 E7.47726\n" +
                    "G1 X82.972 Y84.459 E7.51675\n" +
                    "G1 X83.939 Y85.036 E7.54324\n" +
                    "G1 X84.856 Y85.671 E7.56946\n" +
                    "G1 X85.630 Y86.305 E7.59299\n" +
                    "G1 X86.288 Y86.953 E7.61470\n" +
                    "G1 X86.647 Y87.367 E7.62759\n" +
                    "G1 X87.192 Y88.115 E7.64935\n" +
                    "G1 X87.815 Y89.190 E7.67857\n" +
                    "G1 X88.416 Y90.478 E7.71199\n" +
                    "G1 X89.845 Y94.384 E7.80978\n" +
                    "G1 X91.901 Y101.163 E7.97636\n" +
                    "G1 X92.897 Y104.995 E8.06946\n" +
                    "G1 X93.275 Y106.300 E8.10140\n" +
                    "G1 X93.498 Y106.893 E8.11630\n" +
                    "G1 X93.728 Y107.381 E8.12898\n" +
                    "G1 X93.970 Y107.796 E8.14028\n" +
                    "G1 X94.364 Y108.312 E8.15555\n" +
                    "G1 X94.679 Y108.632 E8.16612\n" +
                    "G1 X95.031 Y108.924 E8.17686\n" +
                    "G1 X95.606 Y109.295 E8.19296\n" +
                    "G1 X96.165 Y109.564 E8.20754\n" +
                    "G1 X96.915 Y109.826 E8.22623\n" +
                    "G1 X97.711 Y110.011 E8.24543\n" +
                    "G1 X98.762 Y110.142 E8.27034\n" +
                    "G1 X100.044 Y110.166 E8.30050\n" +
                    "G1 X101.238 Y110.142 E8.32859\n" +
                    "G1 X102.289 Y110.011 E8.35348\n" +
                    "G1 X103.085 Y109.826 E8.37269\n" +
                    "G1 X103.835 Y109.564 E8.39138\n" +
                    "G1 X104.394 Y109.295 E8.40595\n" +
                    "G1 X104.969 Y108.924 E8.42206\n" +
                    "G1 X105.321 Y108.632 E8.43280\n" +
                    "G1 X105.636 Y108.312 E8.44337\n" +
                    "G1 X106.030 Y107.796 E8.45864\n" +
                    "G1 X106.272 Y107.381 E8.46993\n" +
                    "G1 X106.502 Y106.893 E8.48262\n" +
                    "G1 X106.725 Y106.300 E8.49752\n" +
                    "G1 X107.103 Y104.995 E8.52946\n" +
                    "G1 X108.099 Y101.163 E8.62256\n" +
                    "G1 X110.155 Y94.384 E8.78913\n" +
                    "G1 X111.584 Y90.478 E8.88693\n" +
                    "G1 X112.185 Y89.190 E8.92035\n" +
                    "G1 X112.808 Y88.115 E8.94957\n" +
                    "G1 X113.353 Y87.367 E8.97133\n" +
                    "G1 X113.712 Y86.953 E8.98422\n" +
                    "G1 X114.370 Y86.305 E9.00592\n" +
                    "G1 X115.144 Y85.671 E9.02946\n" +
                    "G1 X116.061 Y85.036 E9.05568\n" +
                    "G1 X117.028 Y84.459 E9.08217\n" +
                    "G1 X118.535 Y83.717 E9.12166\n" +
                    "G1 X120.153 Y83.121 E9.16220\n" +
                    "G1 X120.732 Y82.945 E9.17643\n" +
                    "G1 X124.139 Y82.133 E9.25879\n" +
                    "G1 X127.937 Y81.504 E9.34930\n" +
                    "G1 X132.031 Y81.085 E9.44608\n" +
                    "G1 X136.372 Y80.868 E9.54830\n" +
                    "G1 X143.898 Y80.803 E9.72525\n" +
                    "G1 X149.584 Y80.981 E9.85904\n" +
                    "G1 X152.810 Y81.339 E9.93535\n" +
                    "G1 X153.984 Y81.595 E9.96359\n" +
                    "G1 X154.967 Y81.910 E9.98787\n" +
                    "G1 X155.815 Y82.279 E10.00962\n" +
                    "G1 X156.783 Y82.826 E10.03577\n" +
                    "G1 X157.972 Y83.675 E10.07012\n" +
                    "G1 X158.394 Y84.034 E10.08314\n" +
                    "G1 X159.131 Y84.765 E10.10757\n" +
                    "G1 X159.762 Y85.535 E10.13097\n" +
                    "G1 X160.323 Y86.389 E10.15499\n" +
                    "G1 X160.810 Y87.287 E10.17902\n" +
                    "G1 X161.647 Y89.159 E10.22723\n" +
                    "G1 X162.749 Y92.001 E10.29891\n" +
                    "G1 X163.692 Y94.604 E10.36400\n" +
                    "G1 X165.710 Y100.968 E10.52099\n" +
                    "G1 X166.049 Y101.953 E10.54548\n" +
                    "G1 X166.387 Y102.749 E10.56582\n" +
                    "G1 X166.590 Y103.113 E10.57561\n" +
                    "G1 X166.735 Y103.321 E10.58157\n" +
                    "G1 X166.979 Y103.581 E10.58997\n" +
                    "G1 X167.210 Y103.747 E10.59665\n" +
                    "G1 X167.552 Y103.898 E10.60544\n" +
                    "G1 X167.933 Y103.976 E10.61460\n" +
                    "G1 X168.465 Y103.999 E10.62711\n" +
                    "G1 X168.842 Y103.988 E10.63599\n" +
                    "G1 X171.710 Y103.802 E10.70356\n" +
                    "G1 X172.335 Y103.810 E10.71826\n" +
                    "G1 X172.465 Y103.839 E10.72139\n" +
                    "G1 X172.459 Y103.992 F7800.000\n" +
                    "G1 E8.72139 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X166.017 Y105.284 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X166.453 Y104.848 E2.01765\n" +
                    "G1 X166.608 Y104.960 E2.02312\n" +
                    "G1 X166.990 Y105.128 E2.03506\n" +
                    "G1 X166.002 Y106.116 E2.07503\n" +
                    "G1 X165.889 Y107.047 E2.10185\n" +
                    "G1 X167.613 Y105.322 E2.17164\n" +
                    "G1 X167.950 Y105.391 E2.18146\n" +
                    "G1 X168.344 Y105.408 E2.19277\n" +
                    "G1 X165.769 Y107.983 E2.29695\n" +
                    "G1 X165.521 Y109.048 E2.32824\n" +
                    "G1 X169.163 Y105.406 E2.47560\n" +
                    "G1 X170.037 Y105.350 E2.50064\n" +
                    "G1 X165.095 Y110.292 E2.70060\n" +
                    "G1 X164.801 Y110.934 E2.72080\n" +
                    "G1 X164.599 Y111.301 E2.73278\n" +
                    "G1 X164.202 Y111.913 E2.75367\n" +
                    "G1 X163.829 Y112.375 E2.77063\n" +
                    "G1 X170.911 Y105.293 E3.05714\n" +
                    "G1 X171.092 Y105.282 E3.06235\n" +
                    "G1 X171.088 Y105.933 E3.08098\n" +
                    "G1 X164.031 Y112.990 E3.36650\n" +
                    "G1 X165.184 Y112.654 E3.40086\n" +
                    "G1 X171.083 Y106.755 E3.63952\n" +
                    "G1 X171.093 Y107.562 E3.66261\n" +
                    "G1 X166.337 Y112.318 E3.85502\n" +
                    "G1 X167.470 Y112.002 E3.88865\n" +
                    "G1 X171.109 Y108.363 E4.03590\n" +
                    "G1 X171.126 Y109.164 E4.05882\n" +
                    "G1 X168.561 Y111.728 E4.16257\n" +
                    "G1 X169.652 Y111.454 E4.19476\n" +
                    "G1 X171.142 Y109.965 E4.25503\n" +
                    "G1 X171.180 Y110.743 E4.27734\n" +
                    "G1 X170.744 Y111.180 E4.29500\n" +
                    "G1 E2.29500 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X164.265 Y111.938 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X164.173 Y112.053 E2.00420\n" +
                    "G1 X163.634 Y112.596 E2.02610\n" +
                    "G1 X163.506 Y112.698 E2.03079\n" +
                    "G1 X162.878 Y113.326 E2.05619\n" +
                    "G1 E0.05619 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X109.311 Y113.663 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X109.748 Y113.227 E2.01760\n" +
                    "G1 X109.550 Y112.974 E2.02674\n" +
                    "G1 X109.407 Y112.753 E2.03425\n" +
                    "G1 X108.688 Y113.473 E2.06325\n" +
                    "G1 X108.064 Y113.282 E2.08185\n" +
                    "G1 X109.087 Y112.259 E2.12309\n" +
                    "G1 X108.801 Y111.730 E2.14023\n" +
                    "G1 X107.441 Y113.091 E2.19510\n" +
                    "G1 X106.775 Y112.942 E2.21455\n" +
                    "G1 X108.532 Y111.185 E2.28541\n" +
                    "G1 X108.301 Y110.601 E2.30331\n" +
                    "G1 X106.105 Y112.798 E2.39188\n" +
                    "G1 X105.435 Y112.654 E2.41142\n" +
                    "G1 X108.101 Y109.987 E2.51896\n" +
                    "G1 X107.925 Y109.349 E2.53784\n" +
                    "G1 X104.765 Y112.509 E2.66529\n" +
                    "G1 X104.056 Y112.404 E2.68573\n" +
                    "G1 X107.802 Y108.658 E2.83678\n" +
                    "G1 X107.735 Y108.284 E2.84760\n" +
                    "G1 X107.614 Y108.492 E2.85444\n" +
                    "G1 X107.095 Y109.171 E2.87881\n" +
                    "G1 X106.649 Y109.624 E2.89694\n" +
                    "G1 X106.158 Y110.031 E2.91512\n" +
                    "G1 X105.415 Y110.510 E2.94034\n" +
                    "G1 X104.875 Y110.770 E2.95743\n" +
                    "G1 X103.341 Y112.305 E3.01930\n" +
                    "G1 X102.625 Y112.205 E3.03989\n" +
                    "G1 X103.611 Y111.220 E3.07962\n" +
                    "G1 X102.795 Y111.409 E3.10349\n" +
                    "G1 X102.580 Y111.436 E3.10967\n" +
                    "G1 X101.878 Y112.138 E3.13797\n" +
                    "G1 X101.109 Y112.093 E3.15993\n" +
                    "G1 X101.650 Y111.552 E3.18173\n" +
                    "G1 X100.811 Y111.577 E3.20567\n" +
                    "G1 X100.292 Y112.096 E3.22658\n" +
                    "G1 X99.782 Y112.103 E3.24113\n" +
                    "G1 X99.475 Y112.098 E3.24989\n" +
                    "G1 X99.990 Y111.583 E3.27068\n" +
                    "G1 X99.191 Y111.568 E3.29348\n" +
                    "G1 X98.672 Y112.087 E3.31442\n" +
                    "G1 X97.814 Y112.130 E3.33891\n" +
                    "G1 X98.436 Y111.508 E3.36399\n" +
                    "G1 X97.712 Y111.418 E3.38480\n" +
                    "G1 X96.923 Y112.207 E3.41662\n" +
                    "G1 X95.977 Y112.339 E3.44384\n" +
                    "G1 X97.045 Y111.271 E3.48690\n" +
                    "G1 X96.676 Y111.185 E3.49769\n" +
                    "G1 X96.409 Y111.092 E3.50575\n" +
                    "G1 X95.031 Y112.470 E3.56132\n" +
                    "G1 X94.007 Y112.680 E3.59113\n" +
                    "G1 X95.806 Y110.881 E3.66366\n" +
                    "G1 X95.252 Y110.621 E3.68112\n" +
                    "G1 X92.969 Y112.904 E3.77317\n" +
                    "G1 X92.180 Y113.073 E3.79617\n" +
                    "G1 X91.898 Y113.160 E3.80457\n" +
                    "G1 X94.734 Y110.324 E3.91890\n" +
                    "G1 X94.242 Y110.001 E3.93566\n" +
                    "G1 X90.725 Y113.519 E4.07750\n" +
                    "G1 X90.252 Y113.663 E4.09159\n" +
                    "G1 X90.450 Y113.411 E4.10073\n" +
                    "G1 X90.967 Y112.613 E4.12784\n" +
                    "G1 X91.129 Y112.300 E4.13788\n" +
                    "G1 X93.797 Y109.632 E4.24547\n" +
                    "G1 X93.392 Y109.223 E4.26189\n" +
                    "G1 X91.778 Y110.837 E4.32695\n" +
                    "G1 X92.088 Y109.713 E4.36018\n" +
                    "G1 X93.033 Y108.768 E4.39830\n" +
                    "G1 X92.822 Y108.492 E4.40820\n" +
                    "G1 X92.702 Y108.285 E4.41503\n" +
                    "G1 X92.265 Y108.721 E4.43263\n" +
                    "G1 E2.43263 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X36.743 Y113.343 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1800\n" +
                    "G1 X37.179 Y112.906 E2.01758\n" +
                    "G1 X36.978 Y112.751 E2.02481\n" +
                    "G1 X36.746 Y112.525 E2.03403\n" +
                    "G1 X36.113 Y113.159 E2.05955\n" +
                    "G1 X35.483 Y112.975 E2.07825\n" +
                    "G1 X36.334 Y112.124 E2.11254\n" +
                    "G1 X35.963 Y111.681 E2.12899\n" +
                    "G1 X34.853 Y112.792 E2.17373\n" +
                    "G1 X34.223 Y112.608 E2.19243\n" +
                    "G1 X35.623 Y111.207 E2.24886\n" +
                    "G1 X35.313 Y110.704 E2.26570\n" +
                    "G1 X33.593 Y112.425 E2.33500\n" +
                    "G1 X32.963 Y112.241 E2.35369\n" +
                    "G1 X35.044 Y110.159 E2.43756\n" +
                    "G1 X34.799 Y109.591 E2.45519\n" +
                    "G1 X32.329 Y112.061 E2.55471\n" +
                    "G1 X31.678 Y111.898 E2.57381\n" +
                    "G1 X34.592 Y108.985 E2.69119\n" +
                    "G1 X34.509 Y108.743 E2.69847\n" +
                    "G1 X34.417 Y108.346 E2.71007\n" +
                    "G1 X31.028 Y111.735 E2.84660\n" +
                    "G1 X30.378 Y111.571 E2.86570\n" +
                    "G1 X34.263 Y107.686 E3.02223\n" +
                    "G1 X34.222 Y107.510 E3.02739\n" +
                    "G1 X34.157 Y106.979 E3.04263\n" +
                    "G1 X29.727 Y111.408 E3.22108\n" +
                    "G1 X29.077 Y111.245 E3.24018\n" +
                    "G1 X34.068 Y106.254 E3.44124\n" +
                    "G1 X33.998 Y105.687 E3.45750\n" +
                    "G1 X33.995 Y105.513 E3.46247\n" +
                    "G1 X28.853 Y110.655 E3.66964\n" +
                    "G1 X28.870 Y109.825 E3.69330\n" +
                    "G1 X33.660 Y105.034 E3.88631\n" +
                    "G1 X33.168 Y105.251 E3.90163\n" +
                    "G1 X32.490 Y105.390 E3.92135\n" +
                    "G1 X28.887 Y108.994 E4.06654\n" +
                    "G1 X28.903 Y108.164 E4.09020\n" +
                    "G1 X31.648 Y105.419 E4.20079\n" +
                    "G1 X30.873 Y105.380 E4.22290\n" +
                    "G1 X28.918 Y107.336 E4.30167\n" +
                    "G1 X28.913 Y106.527 E4.32471\n" +
                    "G1 X30.109 Y105.331 E4.37289\n" +
                    "G1 X29.344 Y105.282 E4.39471\n" +
                    "G1 X28.908 Y105.718 E4.41229\n" +
                    "G1 Z0.650 F7800.000\n" +
                    "G1 E2.41229 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X164.872 Y103.882 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X164.838 Y105.809 E2.05200\n" +
                    "G1 X164.632 Y107.484 E2.09752\n" +
                    "G1 X164.377 Y108.579 E2.12784\n" +
                    "G1 X164.090 Y109.421 E2.15185\n" +
                    "G1 X163.784 Y110.090 E2.17168\n" +
                    "G1 X163.627 Y110.375 E2.18047\n" +
                    "G1 X163.298 Y110.882 E2.19677\n" +
                    "G1 X162.923 Y111.346 E2.21288\n" +
                    "G1 X162.489 Y111.784 E2.22952\n" +
                    "G1 X161.780 Y112.349 E2.25396\n" +
                    "G1 X161.108 Y112.776 E2.27545\n" +
                    "G1 X160.286 Y113.215 E2.30057\n" +
                    "G1 X159.548 Y113.562 E2.32259\n" +
                    "G1 X156.683 Y114.693 E2.40567\n" +
                    "G1 X154.167 Y115.487 E2.47685\n" +
                    "G1 X150.091 Y116.455 E2.58987\n" +
                    "G1 X145.816 Y117.121 E2.70657\n" +
                    "G1 X139.576 Y117.689 E2.87560\n" +
                    "G1 X133.091 Y117.942 E3.05069\n" +
                    "G1 X131.132 Y117.955 E3.10354\n" +
                    "G1 X125.905 Y117.773 E3.24461\n" +
                    "G1 X121.401 Y117.239 E3.36698\n" +
                    "G1 X117.266 Y116.318 E3.48127\n" +
                    "G1 X114.767 Y115.504 E3.55216\n" +
                    "G1 X113.316 Y114.871 E3.59486\n" +
                    "G1 X112.553 Y114.434 E3.61857\n" +
                    "G1 X111.954 Y114.002 E3.63850\n" +
                    "G1 X111.444 Y113.547 E3.65694\n" +
                    "G1 X110.943 Y113.007 E3.67680\n" +
                    "G1 X110.460 Y112.389 E3.69799\n" +
                    "G1 X110.012 Y111.698 E3.72019\n" +
                    "G1 X109.617 Y110.937 E3.74331\n" +
                    "G1 X109.305 Y110.147 E3.76623\n" +
                    "G1 X109.065 Y109.299 E3.79000\n" +
                    "G1 X108.883 Y108.285 E3.81779\n" +
                    "G1 X108.793 Y107.186 E3.84754\n" +
                    "G1 X108.799 Y105.956 E3.88073\n" +
                    "G1 X108.972 Y104.065 E3.93195\n" +
                    "G1 X109.495 Y101.059 E4.01426\n" +
                    "G1 X110.284 Y97.669 E4.10814\n" +
                    "G1 X111.305 Y94.061 E4.20931\n" +
                    "G1 X112.348 Y91.191 E4.29168\n" +
                    "G1 X112.935 Y89.987 E4.32781\n" +
                    "G1 X113.459 Y89.134 E4.35482\n" +
                    "G1 X113.963 Y88.475 E4.37722\n" +
                    "G1 X114.525 Y87.875 E4.39939\n" +
                    "G1 X115.376 Y87.149 E4.42956\n" +
                    "G1 X116.276 Y86.526 E4.45908\n" +
                    "G1 X117.439 Y85.831 E4.49564\n" +
                    "G1 X118.825 Y85.121 E4.53766\n" +
                    "G1 X120.319 Y84.478 E4.58151\n" +
                    "G1 X122.685 Y83.685 E4.64885\n" +
                    "G1 X125.497 Y83.004 E4.72691\n" +
                    "G1 X128.815 Y82.439 E4.81767\n" +
                    "G1 X132.614 Y81.990 E4.92088\n" +
                    "G1 X139.045 Y81.487 E5.09490\n" +
                    "G1 X139.671 Y81.452 E5.11183\n" +
                    "G1 X144.815 Y81.340 E5.25060\n" +
                    "G1 X149.486 Y81.602 E5.37682\n" +
                    "G1 X152.614 Y82.058 E5.46209\n" +
                    "G1 X154.063 Y82.434 E5.50248\n" +
                    "G1 X155.041 Y82.799 E5.53064\n" +
                    "G1 X155.813 Y83.180 E5.55386\n" +
                    "G1 X156.401 Y83.543 E5.57251\n" +
                    "G1 X156.815 Y83.842 E5.58629\n" +
                    "G1 X157.471 Y84.399 E5.60949\n" +
                    "G1 X158.125 Y85.067 E5.63471\n" +
                    "G1 X158.781 Y85.860 E5.66248\n" +
                    "G1 X159.208 Y86.445 E5.68203\n" +
                    "G1 X160.230 Y88.069 E5.73379\n" +
                    "G1 X161.273 Y90.085 E5.79500\n" +
                    "G1 X161.767 Y91.182 E5.82745\n" +
                    "G1 X163.108 Y94.744 E5.93015\n" +
                    "G1 X164.143 Y98.487 E6.03490\n" +
                    "G1 X164.699 Y101.655 E6.12167\n" +
                    "G1 X164.866 Y103.807 E6.17991\n" +
                    "G1 X164.311 Y103.899 F7800.000\n" +
                    "G1 F3000\n" +
                    "G1 X164.278 Y105.770 E6.21848\n" +
                    "G1 X164.080 Y107.386 E6.25204\n" +
                    "G1 X163.838 Y108.424 E6.27401\n" +
                    "G1 X163.569 Y109.213 E6.29119\n" +
                    "G1 X163.283 Y109.837 E6.30533\n" +
                    "G1 X163.146 Y110.087 E6.31121\n" +
                    "G1 X162.844 Y110.552 E6.32264\n" +
                    "G1 X162.505 Y110.972 E6.33375\n" +
                    "G1 X162.114 Y111.366 E6.34520\n" +
                    "G1 X161.454 Y111.892 E6.36259\n" +
                    "G1 X160.825 Y112.292 E6.37795\n" +
                    "G1 X160.035 Y112.713 E6.39641\n" +
                    "G1 X159.325 Y113.047 E6.41257\n" +
                    "G1 X156.495 Y114.164 E6.47527\n" +
                    "G1 X154.018 Y114.946 E6.52883\n" +
                    "G1 X149.983 Y115.905 E6.61430\n" +
                    "G1 X145.748 Y116.564 E6.70264\n" +
                    "G1 X139.540 Y117.129 E6.83112\n" +
                    "G1 X133.078 Y117.382 E6.96439\n" +
                    "G1 X131.140 Y117.394 E7.00435\n" +
                    "G1 X125.948 Y117.213 E7.11141\n" +
                    "G1 X121.495 Y116.686 E7.20384\n" +
                    "G1 X117.414 Y115.776 E7.29001\n" +
                    "G1 X114.966 Y114.980 E7.34306\n" +
                    "G1 X113.568 Y114.369 E7.37450\n" +
                    "G1 X112.857 Y113.962 E7.39139\n" +
                    "G1 X112.306 Y113.565 E7.40540\n" +
                    "G1 X111.837 Y113.146 E7.41834\n" +
                    "G1 X111.370 Y112.643 E7.43249\n" +
                    "G1 X110.917 Y112.063 E7.44767\n" +
                    "G1 X110.497 Y111.416 E7.46357\n" +
                    "G1 X110.128 Y110.704 E7.48009\n" +
                    "G1 X109.837 Y109.967 E7.49642\n" +
                    "G1 X109.612 Y109.173 E7.51343\n" +
                    "G1 X109.440 Y108.213 E7.53354\n" +
                    "G1 X109.354 Y107.164 E7.55522\n" +
                    "G1 X109.360 Y105.982 E7.57958\n" +
                    "G1 X109.528 Y104.139 E7.61774\n" +
                    "G1 X110.044 Y101.171 E7.67983\n" +
                    "G1 X110.827 Y97.809 E7.75096\n" +
                    "G1 X111.839 Y94.233 E7.82756\n" +
                    "G1 X112.865 Y91.410 E7.88946\n" +
                    "G1 X113.427 Y90.257 E7.91590\n" +
                    "G1 X113.921 Y89.452 E7.93538\n" +
                    "G1 X114.392 Y88.838 E7.95132\n" +
                    "G1 X114.913 Y88.281 E7.96705\n" +
                    "G1 X115.718 Y87.594 E7.98886\n" +
                    "G1 X116.579 Y86.997 E8.01044\n" +
                    "G1 X117.711 Y86.322 E8.03761\n" +
                    "G1 X119.064 Y85.628 E8.06896\n" +
                    "G1 X120.519 Y85.002 E8.10159\n" +
                    "G1 X122.841 Y84.224 E8.15206\n" +
                    "G1 X125.611 Y83.553 E8.21080\n" +
                    "G1 X128.895 Y82.995 E8.27946\n" +
                    "G1 X132.669 Y82.548 E8.35779\n" +
                    "G1 X139.083 Y82.047 E8.49038\n" +
                    "G1 X139.693 Y82.012 E8.50299\n" +
                    "G1 X144.805 Y81.901 E8.60837\n" +
                    "G1 X149.430 Y82.160 E8.70384\n" +
                    "G1 X152.503 Y82.608 E8.76784\n" +
                    "G1 X153.894 Y82.969 E8.79747\n" +
                    "G1 X154.818 Y83.314 E8.81780\n" +
                    "G1 X155.541 Y83.671 E8.83441\n" +
                    "G1 X156.089 Y84.010 E8.84769\n" +
                    "G1 X156.469 Y84.284 E8.85735\n" +
                    "G1 X157.088 Y84.809 E8.87409\n" +
                    "G1 X157.708 Y85.442 E8.89234\n" +
                    "G1 X158.338 Y86.204 E8.91272\n" +
                    "G1 X158.744 Y86.760 E8.92691\n" +
                    "G1 X159.743 Y88.348 E8.96557\n" +
                    "G1 X160.768 Y90.329 E9.01153\n" +
                    "G1 X161.248 Y91.396 E9.03565\n" +
                    "G1 X162.574 Y94.918 E9.11323\n" +
                    "G1 X163.595 Y98.610 E9.19218\n" +
                    "G1 X164.142 Y101.725 E9.25736\n" +
                    "G1 X164.305 Y103.824 E9.30075\n" +
                    "G1 X164.311 Y103.899 F7800.000\n" +
                    "G1 X165.422 Y102.759 F7800.000\n" +
                    "G1 F3600\n" +
                    "G1 X165.583 Y103.139 E9.31188\n" +
                    "G1 X165.831 Y103.584 E9.32562\n" +
                    "G1 X166.043 Y103.888 E9.33562\n" +
                    "G1 X166.384 Y104.252 E9.34907\n" +
                    "G1 X166.768 Y104.529 E9.36184\n" +
                    "G1 X167.278 Y104.754 E9.37688\n" +
                    "G1 X167.824 Y104.865 E9.39191\n" +
                    "G1 X168.455 Y104.892 E9.40895\n" +
                    "G1 X168.885 Y104.879 E9.42055\n" +
                    "G1 X171.625 Y104.701 E9.49463\n" +
                    "G1 X171.612 Y107.208 E9.56225\n" +
                    "G1 X171.679 Y110.509 E9.65133\n" +
                    "G1 X171.751 Y111.529 E9.67891\n" +
                    "G1 X167.523 Y112.597 E9.79653\n" +
                    "G1 X161.878 Y114.242 E9.95516\n" +
                    "G1 X160.332 Y114.627 E9.99815\n" +
                    "G1 X156.436 Y115.437 E10.10548\n" +
                    "G1 X156.896 Y115.292 E10.11848\n" +
                    "G1 X159.800 Y114.146 E10.20271\n" +
                    "G1 X160.571 Y113.783 E10.22570\n" +
                    "G1 X161.429 Y113.326 E10.25191\n" +
                    "G1 X162.150 Y112.867 E10.27497\n" +
                    "G1 X162.914 Y112.258 E10.30132\n" +
                    "G1 X163.397 Y111.771 E10.31984\n" +
                    "G1 X163.814 Y111.256 E10.33771\n" +
                    "G1 X164.173 Y110.702 E10.35552\n" +
                    "G1 X164.352 Y110.375 E10.36557\n" +
                    "G1 X164.681 Y109.656 E10.38690\n" +
                    "G1 X164.989 Y108.754 E10.41263\n" +
                    "G1 X165.259 Y107.595 E10.44472\n" +
                    "G1 X165.473 Y105.854 E10.49206\n" +
                    "G1 X165.508 Y103.863 E10.54577\n" +
                    "G1 X165.428 Y102.834 E10.57360\n" +
                    "G1 E8.57360 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X112.740 Y115.274 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X110.328 Y114.472 E2.06857\n" +
                    "G1 X107.414 Y113.580 E2.15078\n" +
                    "G1 X104.760 Y113.008 E2.22403\n" +
                    "G1 X102.480 Y112.692 E2.28612\n" +
                    "G1 X101.203 Y112.617 E2.32062\n" +
                    "G1 X98.797 Y112.617 E2.38555\n" +
                    "G1 X97.520 Y112.692 E2.42005\n" +
                    "G1 X95.240 Y113.008 E2.48214\n" +
                    "G1 X92.586 Y113.580 E2.55539\n" +
                    "G1 X89.672 Y114.472 E2.63760\n" +
                    "G1 X87.260 Y115.274 E2.70617\n" +
                    "G1 X87.792 Y114.969 E2.72270\n" +
                    "G1 X88.445 Y114.498 E2.74443\n" +
                    "G1 X89.001 Y114.001 E2.76456\n" +
                    "G1 X89.541 Y113.420 E2.78595\n" +
                    "G1 X90.059 Y112.758 E2.80863\n" +
                    "G1 X90.538 Y112.018 E2.83241\n" +
                    "G1 X90.962 Y111.201 E2.85724\n" +
                    "G1 X91.298 Y110.351 E2.88189\n" +
                    "G1 X91.555 Y109.442 E2.90737\n" +
                    "G1 X91.748 Y108.368 E2.93683\n" +
                    "G1 X91.843 Y107.211 E2.96814\n" +
                    "G1 X91.836 Y105.925 E3.00282\n" +
                    "G1 X91.659 Y103.981 E3.05547\n" +
                    "G1 X91.552 Y103.364 E3.07238\n" +
                    "G1 X92.038 Y105.236 E3.12455\n" +
                    "G1 X92.427 Y106.580 E3.16229\n" +
                    "G1 X92.676 Y107.240 E3.18133\n" +
                    "G1 X92.937 Y107.796 E3.19789\n" +
                    "G1 X93.229 Y108.296 E3.21351\n" +
                    "G1 X93.686 Y108.895 E3.23385\n" +
                    "G1 X94.075 Y109.290 E3.24880\n" +
                    "G1 X94.505 Y109.647 E3.26387\n" +
                    "G1 X95.168 Y110.074 E3.28514\n" +
                    "G1 X95.824 Y110.390 E3.30479\n" +
                    "G1 X96.666 Y110.684 E3.32884\n" +
                    "G1 X97.555 Y110.891 E3.35348\n" +
                    "G1 X98.698 Y111.033 E3.38453\n" +
                    "G1 X99.944 Y111.057 E3.41817\n" +
                    "G1 X101.301 Y111.033 E3.45477\n" +
                    "G1 X102.445 Y110.891 E3.48585\n" +
                    "G1 X103.334 Y110.684 E3.51049\n" +
                    "G1 X104.176 Y110.390 E3.53454\n" +
                    "G1 X104.832 Y110.074 E3.55419\n" +
                    "G1 X105.495 Y109.647 E3.57546\n" +
                    "G1 X105.925 Y109.290 E3.59053\n" +
                    "G1 X106.314 Y108.895 E3.60548\n" +
                    "G1 X106.771 Y108.296 E3.62582\n" +
                    "G1 X107.063 Y107.796 E3.64144\n" +
                    "G1 X107.324 Y107.240 E3.65800\n" +
                    "G1 X107.573 Y106.580 E3.67704\n" +
                    "G1 X107.962 Y105.236 E3.71478\n" +
                    "G1 X108.448 Y103.364 E3.76695\n" +
                    "G1 X108.341 Y103.981 E3.78385\n" +
                    "G1 X108.164 Y105.925 E3.83651\n" +
                    "G1 X108.157 Y107.211 E3.87118\n" +
                    "G1 X108.252 Y108.368 E3.90250\n" +
                    "G1 X108.445 Y109.442 E3.93195\n" +
                    "G1 X108.702 Y110.351 E3.95743\n" +
                    "G1 X109.038 Y111.201 E3.98209\n" +
                    "G1 X109.462 Y112.018 E4.00691\n" +
                    "G1 X109.941 Y112.758 E4.03070\n" +
                    "G1 X110.459 Y113.420 E4.05338\n" +
                    "G1 X110.999 Y114.001 E4.07476\n" +
                    "G1 X111.555 Y114.498 E4.09490\n" +
                    "G1 X112.208 Y114.969 E4.11662\n" +
                    "G1 X112.675 Y115.236 E4.13114\n" +
                    "G1 E2.13114 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X90.935 Y109.299 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X90.695 Y110.147 E2.02377\n" +
                    "G1 X90.383 Y110.937 E2.04668\n" +
                    "G1 X89.988 Y111.698 E2.06981\n" +
                    "G1 X89.540 Y112.389 E2.09201\n" +
                    "G1 X89.057 Y113.007 E2.11320\n" +
                    "G1 X88.556 Y113.547 E2.13306\n" +
                    "G1 X88.046 Y114.002 E2.15150\n" +
                    "G1 X87.447 Y114.434 E2.17142\n" +
                    "G1 X86.684 Y114.871 E2.19514\n" +
                    "G1 X85.233 Y115.504 E2.23784\n" +
                    "G1 X82.734 Y116.318 E2.30873\n" +
                    "G1 X78.599 Y117.239 E2.42301\n" +
                    "G1 X74.095 Y117.773 E2.54538\n" +
                    "G1 X68.868 Y117.955 E2.68646\n" +
                    "G1 X66.909 Y117.942 E2.73931\n" +
                    "G1 X60.424 Y117.689 E2.91440\n" +
                    "G1 X54.184 Y117.121 E3.08343\n" +
                    "G1 X49.909 Y116.455 E3.20013\n" +
                    "G1 X45.833 Y115.487 E3.31315\n" +
                    "G1 X43.317 Y114.693 E3.38433\n" +
                    "G1 X40.452 Y113.562 E3.46741\n" +
                    "G1 X39.714 Y113.215 E3.48942\n" +
                    "G1 X38.892 Y112.776 E3.51455\n" +
                    "G1 X38.225 Y112.352 E3.53586\n" +
                    "G1 X37.666 Y111.921 E3.55492\n" +
                    "G1 X37.081 Y111.352 E3.57692\n" +
                    "G1 X36.702 Y110.882 E3.59323\n" +
                    "G1 X36.373 Y110.375 E3.60952\n" +
                    "G1 X36.216 Y110.090 E3.61832\n" +
                    "G1 X35.910 Y109.421 E3.63815\n" +
                    "G1 X35.623 Y108.579 E3.66216\n" +
                    "G1 X35.368 Y107.484 E3.69247\n" +
                    "G1 X35.162 Y105.809 E3.73800\n" +
                    "G1 X35.128 Y103.882 E3.79000\n" +
                    "G1 X35.301 Y101.655 E3.85025\n" +
                    "G1 X35.857 Y98.487 E3.93703\n" +
                    "G1 X36.892 Y94.744 E4.04178\n" +
                    "G1 X38.233 Y91.182 E4.14448\n" +
                    "G1 X38.727 Y90.085 E4.17692\n" +
                    "G1 X39.770 Y88.069 E4.23814\n" +
                    "G1 X40.792 Y86.445 E4.28990\n" +
                    "G1 X41.219 Y85.860 E4.30945\n" +
                    "G1 X41.875 Y85.067 E4.33722\n" +
                    "G1 X42.529 Y84.399 E4.36244\n" +
                    "G1 X43.185 Y83.842 E4.38563\n" +
                    "G1 X43.599 Y83.543 E4.39941\n" +
                    "G1 X44.187 Y83.180 E4.41807\n" +
                    "G1 X44.959 Y82.799 E4.44129\n" +
                    "G1 X45.937 Y82.434 E4.46944\n" +
                    "G1 X47.386 Y82.058 E4.50984\n" +
                    "G1 X50.514 Y81.602 E4.59511\n" +
                    "G1 X55.185 Y81.340 E4.72133\n" +
                    "G1 X60.329 Y81.452 E4.86010\n" +
                    "G1 X60.955 Y81.487 E4.87702\n" +
                    "G1 X67.386 Y81.990 E5.05105\n" +
                    "G1 X71.185 Y82.439 E5.15425\n" +
                    "G1 X74.503 Y83.004 E5.24502\n" +
                    "G1 X77.315 Y83.685 E5.32308\n" +
                    "G1 X79.681 Y84.478 E5.39041\n" +
                    "G1 X81.175 Y85.121 E5.43427\n" +
                    "G1 X82.561 Y85.831 E5.47629\n" +
                    "G1 X83.724 Y86.526 E5.51285\n" +
                    "G1 X84.620 Y87.146 E5.54225\n" +
                    "G1 X85.334 Y87.742 E5.56732\n" +
                    "G1 X86.034 Y88.471 E5.59457\n" +
                    "G1 X86.541 Y89.134 E5.61711\n" +
                    "G1 X87.065 Y89.987 E5.64411\n" +
                    "G1 X87.652 Y91.191 E5.68025\n" +
                    "G1 X88.695 Y94.061 E5.76262\n" +
                    "G1 X89.716 Y97.669 E5.86379\n" +
                    "G1 X90.505 Y101.059 E5.95767\n" +
                    "G1 X91.028 Y104.065 E6.03998\n" +
                    "G1 X91.201 Y105.956 E6.09119\n" +
                    "G1 X91.207 Y107.186 E6.12439\n" +
                    "G1 X91.117 Y108.285 E6.15414\n" +
                    "G1 X90.948 Y109.225 E6.17991\n" +
                    "G1 X90.388 Y109.173 F7800.000\n" +
                    "G1 F3000\n" +
                    "G1 X90.163 Y109.967 E6.19692\n" +
                    "G1 X89.872 Y110.704 E6.21325\n" +
                    "G1 X89.503 Y111.416 E6.22977\n" +
                    "G1 X89.083 Y112.063 E6.24566\n" +
                    "G1 X88.630 Y112.643 E6.26085\n" +
                    "G1 X88.163 Y113.146 E6.27499\n" +
                    "G1 X87.694 Y113.565 E6.28794\n" +
                    "G1 X87.143 Y113.962 E6.30195\n" +
                    "G1 X86.432 Y114.369 E6.31884\n" +
                    "G1 X85.034 Y114.980 E6.35027\n" +
                    "G1 X82.586 Y115.776 E6.40332\n" +
                    "G1 X78.505 Y116.686 E6.48950\n" +
                    "G1 X74.052 Y117.213 E6.58193\n" +
                    "G1 X68.860 Y117.394 E6.68899\n" +
                    "G1 X66.922 Y117.382 E6.72894\n" +
                    "G1 X60.460 Y117.129 E6.86222\n" +
                    "G1 X54.252 Y116.564 E6.99069\n" +
                    "G1 X50.017 Y115.905 E7.07903\n" +
                    "G1 X45.982 Y114.946 E7.16451\n" +
                    "G1 X43.505 Y114.164 E7.21806\n" +
                    "G1 X40.675 Y113.047 E7.28076\n" +
                    "G1 X39.965 Y112.713 E7.29693\n" +
                    "G1 X39.175 Y112.292 E7.31539\n" +
                    "G1 X38.548 Y111.893 E7.33071\n" +
                    "G1 X38.034 Y111.497 E7.34408\n" +
                    "G1 X37.496 Y110.974 E7.35953\n" +
                    "G1 X37.156 Y110.552 E7.37070\n" +
                    "G1 X36.854 Y110.087 E7.38213\n" +
                    "G1 X36.717 Y109.837 E7.38800\n" +
                    "G1 X36.431 Y109.213 E7.40215\n" +
                    "G1 X36.162 Y108.424 E7.41933\n" +
                    "G1 X35.920 Y107.386 E7.44130\n" +
                    "G1 X35.722 Y105.770 E7.47486\n" +
                    "G1 X35.689 Y103.899 E7.51343\n" +
                    "G1 X35.858 Y101.725 E7.55836\n" +
                    "G1 X36.405 Y98.610 E7.62355\n" +
                    "G1 X37.426 Y94.918 E7.70250\n" +
                    "G1 X38.752 Y91.396 E7.78007\n" +
                    "G1 X39.232 Y90.329 E7.80419\n" +
                    "G1 X40.257 Y88.348 E7.85015\n" +
                    "G1 X41.256 Y86.760 E7.88881\n" +
                    "G1 X41.662 Y86.204 E7.90300\n" +
                    "G1 X42.292 Y85.442 E7.92339\n" +
                    "G1 X42.912 Y84.809 E7.94164\n" +
                    "G1 X43.531 Y84.284 E7.95837\n" +
                    "G1 X43.911 Y84.010 E7.96803\n" +
                    "G1 X44.459 Y83.671 E7.98132\n" +
                    "G1 X45.182 Y83.314 E7.99792\n" +
                    "G1 X46.106 Y82.969 E8.01825\n" +
                    "G1 X47.497 Y82.608 E8.04789\n" +
                    "G1 X50.570 Y82.160 E8.11189\n" +
                    "G1 X55.195 Y81.901 E8.20736\n" +
                    "G1 X60.307 Y82.012 E8.31273\n" +
                    "G1 X60.917 Y82.047 E8.32534\n" +
                    "G1 X67.331 Y82.548 E8.45794\n" +
                    "G1 X71.105 Y82.995 E8.53627\n" +
                    "G1 X74.389 Y83.553 E8.60492\n" +
                    "G1 X77.159 Y84.224 E8.66366\n" +
                    "G1 X79.481 Y85.002 E8.71413\n" +
                    "G1 X80.936 Y85.628 E8.74677\n" +
                    "G1 X82.289 Y86.322 E8.77811\n" +
                    "G1 X83.421 Y86.997 E8.80528\n" +
                    "G1 X84.280 Y87.593 E8.82683\n" +
                    "G1 X84.951 Y88.152 E8.84483\n" +
                    "G1 X85.607 Y88.836 E8.86437\n" +
                    "G1 X86.079 Y89.452 E8.88034\n" +
                    "G1 X86.573 Y90.257 E8.89982\n" +
                    "G1 X87.135 Y91.410 E8.92626\n" +
                    "G1 X88.161 Y94.233 E8.98817\n" +
                    "G1 X89.173 Y97.809 E9.06476\n" +
                    "G1 X89.956 Y101.171 E9.13589\n" +
                    "G1 X90.472 Y104.139 E9.19798\n" +
                    "G1 X90.640 Y105.982 E9.23614\n" +
                    "G1 X90.646 Y107.164 E9.26051\n" +
                    "G1 X90.560 Y108.213 E9.28219\n" +
                    "G1 X90.401 Y109.099 E9.30075\n" +
                    "G1 X90.388 Y109.173 F7800.000\n" +
                    "G1 E7.30075 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X43.564 Y115.437 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X39.668 Y114.627 E2.10734\n" +
                    "G1 X38.122 Y114.242 E2.15032\n" +
                    "G1 X32.476 Y112.597 E2.30895\n" +
                    "G1 X28.249 Y111.529 E2.42657\n" +
                    "G1 X28.321 Y110.509 E2.45416\n" +
                    "G1 X28.388 Y107.208 E2.54323\n" +
                    "G1 X28.375 Y104.701 E2.61086\n" +
                    "G1 X31.115 Y104.879 E2.68493\n" +
                    "G1 X31.545 Y104.892 E2.69653\n" +
                    "G1 X32.176 Y104.865 E2.71357\n" +
                    "G1 X32.722 Y104.754 E2.72861\n" +
                    "G1 X33.232 Y104.529 E2.74364\n" +
                    "G1 X33.616 Y104.252 E2.75641\n" +
                    "G1 X33.957 Y103.888 E2.76986\n" +
                    "G1 X34.169 Y103.584 E2.77987\n" +
                    "G1 X34.417 Y103.139 E2.79361\n" +
                    "G1 X34.578 Y102.759 E2.80474\n" +
                    "G1 X34.492 Y103.863 E2.83460\n" +
                    "G1 X34.527 Y105.854 E2.88830\n" +
                    "G1 X34.741 Y107.595 E2.93565\n" +
                    "G1 X35.011 Y108.754 E2.96774\n" +
                    "G1 X35.319 Y109.656 E2.99346\n" +
                    "G1 X35.648 Y110.375 E3.01479\n" +
                    "G1 X35.827 Y110.702 E3.02484\n" +
                    "G1 X36.186 Y111.256 E3.04265\n" +
                    "G1 X36.611 Y111.781 E3.06087\n" +
                    "G1 X37.249 Y112.402 E3.08489\n" +
                    "G1 X37.860 Y112.873 E3.10570\n" +
                    "G1 X38.571 Y113.326 E3.12845\n" +
                    "G1 X39.429 Y113.783 E3.15467\n" +
                    "G1 X40.200 Y114.146 E3.17766\n" +
                    "G1 X43.104 Y115.292 E3.26188\n" +
                    "G1 X43.492 Y115.415 E3.27286\n" +
                    "G1 E1.27286 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X27.578 Y112.015 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X27.686 Y110.480 E2.04150\n" +
                    "G1 X27.752 Y107.203 E2.12993\n" +
                    "G1 X27.736 Y104.076 E2.21428\n" +
                    "G1 X28.290 Y104.059 E2.22922\n" +
                    "G1 X31.145 Y104.244 E2.30642\n" +
                    "G1 X31.542 Y104.256 E2.31711\n" +
                    "G1 X32.099 Y104.232 E2.33215\n" +
                    "G1 X32.528 Y104.145 E2.34398\n" +
                    "G1 X32.915 Y103.975 E2.35537\n" +
                    "G1 X33.194 Y103.773 E2.36467\n" +
                    "G1 X33.462 Y103.487 E2.37522\n" +
                    "G1 X33.629 Y103.247 E2.38313\n" +
                    "G1 X33.845 Y102.860 E2.39509\n" +
                    "G1 X34.190 Y102.047 E2.41891\n" +
                    "G1 X34.532 Y101.051 E2.44732\n" +
                    "G1 X36.551 Y94.684 E2.62751\n" +
                    "G1 X37.491 Y92.091 E2.70190\n" +
                    "G1 X38.589 Y89.260 E2.78382\n" +
                    "G1 X39.420 Y87.399 E2.83879\n" +
                    "G1 X39.897 Y86.522 E2.86572\n" +
                    "G1 X40.445 Y85.688 E2.89265\n" +
                    "G1 X41.059 Y84.938 E2.91880\n" +
                    "G1 X41.783 Y84.220 E2.94630\n" +
                    "G1 X42.185 Y83.878 E2.96055\n" +
                    "G1 X43.161 Y83.165 E2.99315\n" +
                    "G1 X44.300 Y82.509 E3.02862\n" +
                    "G1 X45.124 Y82.150 E3.05284\n" +
                    "G1 X46.083 Y81.843 E3.08003\n" +
                    "G1 X47.228 Y81.592 E3.11165\n" +
                    "G1 X50.435 Y81.236 E3.19869\n" +
                    "G1 X56.105 Y81.059 E3.35173\n" +
                    "G1 X63.621 Y81.124 E3.55447\n" +
                    "G1 X67.949 Y81.340 E3.67138\n" +
                    "G1 X72.030 Y81.758 E3.78204\n" +
                    "G1 X75.809 Y82.384 E3.88539\n" +
                    "G1 X79.207 Y83.194 E3.97961\n" +
                    "G1 X79.761 Y83.362 E3.99523\n" +
                    "G1 X81.363 Y83.953 E4.04130\n" +
                    "G1 X82.852 Y84.686 E4.08607\n" +
                    "G1 X83.800 Y85.251 E4.11583\n" +
                    "G1 X84.701 Y85.875 E4.14542\n" +
                    "G1 X85.458 Y86.496 E4.17182\n" +
                    "G1 X86.103 Y87.130 E4.19621\n" +
                    "G1 X86.444 Y87.524 E4.21028\n" +
                    "G1 X86.976 Y88.255 E4.23466\n" +
                    "G1 X87.588 Y89.310 E4.26756\n" +
                    "G1 X88.178 Y90.574 E4.30520\n" +
                    "G1 X89.602 Y94.465 E4.41697\n" +
                    "G1 X91.654 Y101.234 E4.60777\n" +
                    "G1 X92.651 Y105.068 E4.71464\n" +
                    "G1 X93.031 Y106.379 E4.75147\n" +
                    "G1 X93.262 Y106.993 E4.76916\n" +
                    "G1 X93.501 Y107.500 E4.78429\n" +
                    "G1 X93.758 Y107.942 E4.79808\n" +
                    "G1 X94.167 Y108.478 E4.81627\n" +
                    "G1 X94.505 Y108.821 E4.82928\n" +
                    "G1 X94.881 Y109.133 E4.84245\n" +
                    "G1 X95.479 Y109.518 E4.86163\n" +
                    "G1 X96.068 Y109.802 E4.87926\n" +
                    "G1 X96.843 Y110.073 E4.90141\n" +
                    "G1 X97.667 Y110.264 E4.92423\n" +
                    "G1 X98.743 Y110.398 E4.95349\n" +
                    "G1 X99.945 Y110.421 E4.98591\n" +
                    "G1 X101.256 Y110.398 E5.02128\n" +
                    "G1 X102.333 Y110.264 E5.05056\n" +
                    "G1 X103.157 Y110.073 E5.07338\n" +
                    "G1 X103.932 Y109.802 E5.09553\n" +
                    "G1 X104.521 Y109.518 E5.11316\n" +
                    "G1 X105.119 Y109.133 E5.13234\n" +
                    "G1 X105.495 Y108.821 E5.14551\n" +
                    "G1 X105.833 Y108.478 E5.15852\n" +
                    "G1 X106.242 Y107.942 E5.17671\n" +
                    "G1 X106.499 Y107.500 E5.19050\n" +
                    "G1 X106.738 Y106.993 E5.20563\n" +
                    "G1 X106.969 Y106.379 E5.22332\n" +
                    "G1 X107.349 Y105.068 E5.26015\n" +
                    "G1 X108.346 Y101.234 E5.36702\n" +
                    "G1 X110.398 Y94.465 E5.55782\n" +
                    "G1 X111.822 Y90.574 E5.66959\n" +
                    "G1 X112.412 Y89.310 E5.70723\n" +
                    "G1 X113.024 Y88.255 E5.74013\n" +
                    "G1 X113.556 Y87.524 E5.76452\n" +
                    "G1 X113.897 Y87.130 E5.77858\n" +
                    "G1 X114.542 Y86.496 E5.80297\n" +
                    "G1 X115.299 Y85.875 E5.82937\n" +
                    "G1 X116.200 Y85.251 E5.85896\n" +
                    "G1 X117.148 Y84.686 E5.88872\n" +
                    "G1 X118.637 Y83.953 E5.93349\n" +
                    "G1 X120.239 Y83.362 E5.97956\n" +
                    "G1 X120.793 Y83.194 E5.99518\n" +
                    "G1 X124.191 Y82.384 E6.08940\n" +
                    "G1 X127.970 Y81.758 E6.19275\n" +
                    "G1 X132.051 Y81.340 E6.30341\n" +
                    "G1 X136.379 Y81.124 E6.42032\n" +
                    "G1 X143.895 Y81.059 E6.62306\n" +
                    "G1 X149.565 Y81.236 E6.77610\n" +
                    "G1 X152.772 Y81.592 E6.86314\n" +
                    "G1 X153.917 Y81.843 E6.89476\n" +
                    "G1 X154.876 Y82.150 E6.92195\n" +
                    "G1 X155.696 Y82.507 E6.94605\n" +
                    "G1 X156.855 Y83.177 E6.98218\n" +
                    "G1 X157.815 Y83.878 E7.01424\n" +
                    "G1 X158.217 Y84.220 E7.02849\n" +
                    "G1 X158.941 Y84.938 E7.05599\n" +
                    "G1 X159.555 Y85.688 E7.08213\n" +
                    "G1 X160.103 Y86.522 E7.10906\n" +
                    "G1 X160.580 Y87.399 E7.13600\n" +
                    "G1 X161.411 Y89.260 E7.19097\n" +
                    "G1 X162.509 Y92.091 E7.27289\n" +
                    "G1 X163.449 Y94.684 E7.34728\n" +
                    "G1 X165.468 Y101.051 E7.52747\n" +
                    "G1 X165.810 Y102.047 E7.55587\n" +
                    "G1 X166.155 Y102.860 E7.57970\n" +
                    "G1 X166.371 Y103.247 E7.59166\n" +
                    "G1 X166.538 Y103.487 E7.59956\n" +
                    "G1 X166.806 Y103.773 E7.61012\n" +
                    "G1 X167.085 Y103.975 E7.61941\n" +
                    "G1 X167.472 Y104.145 E7.63080\n" +
                    "G1 X167.901 Y104.232 E7.64263\n" +
                    "G1 X168.458 Y104.256 E7.65768\n" +
                    "G1 X168.855 Y104.244 E7.66837\n" +
                    "G1 X171.710 Y104.059 E7.74556\n" +
                    "G1 X172.264 Y104.076 E7.76051\n" +
                    "G1 X172.247 Y107.203 E7.84485\n" +
                    "G1 X172.314 Y110.480 E7.93328\n" +
                    "G1 X172.422 Y112.015 E7.97478\n" +
                    "G1 X167.690 Y113.210 E8.10644\n" +
                    "G1 X162.044 Y114.856 E8.26510\n" +
                    "G1 X160.473 Y115.247 E8.30876\n" +
                    "G1 X152.422 Y116.921 E8.53060\n" +
                    "G1 X148.180 Y117.704 E8.64698\n" +
                    "G1 X143.260 Y118.372 E8.78093\n" +
                    "G1 X135.358 Y118.947 E8.99466\n" +
                    "G1 X131.564 Y118.941 E9.09698\n" +
                    "G1 X126.725 Y118.810 E9.22757\n" +
                    "G1 X122.046 Y118.217 E9.35482\n" +
                    "G1 X117.466 Y117.318 E9.48072\n" +
                    "G1 X114.927 Y116.670 E9.55142\n" +
                    "G1 X110.135 Y115.078 E9.68764\n" +
                    "G1 X107.254 Y114.196 E9.76892\n" +
                    "G1 X104.649 Y113.635 E9.84080\n" +
                    "G1 X102.418 Y113.325 E9.90157\n" +
                    "G1 X101.185 Y113.252 E9.93490\n" +
                    "G1 X98.815 Y113.252 E9.99881\n" +
                    "G1 X97.582 Y113.325 E10.03213\n" +
                    "G1 X95.351 Y113.635 E10.09290\n" +
                    "G1 X92.746 Y114.196 E10.16479\n" +
                    "G1 X89.865 Y115.078 E10.24606\n" +
                    "G1 X85.073 Y116.670 E10.38229\n" +
                    "G1 X82.534 Y117.318 E10.45298\n" +
                    "G1 X77.954 Y118.217 E10.57889\n" +
                    "G1 X73.275 Y118.810 E10.70613\n" +
                    "G1 X68.436 Y118.941 E10.83672\n" +
                    "G1 X64.642 Y118.947 E10.93907\n" +
                    "G1 X56.740 Y118.372 E11.15277\n" +
                    "G1 X51.820 Y117.704 E11.28672\n" +
                    "G1 X47.578 Y116.921 E11.40310\n" +
                    "G1 X39.527 Y115.247 E11.62494\n" +
                    "G1 X37.956 Y114.856 E11.66860\n" +
                    "G1 X32.310 Y113.210 E11.82726\n" +
                    "G1 X27.650 Y112.033 E11.95690\n" +
                    "G1 X27.018 Y112.366 F7800.000\n" +
                    "G1 F3000\n" +
                    "G1 X26.997 Y112.276 E11.95879\n" +
                    "G1 X27.126 Y110.455 E11.99643\n" +
                    "G1 X27.192 Y107.199 E12.06355\n" +
                    "G1 X27.175 Y103.947 E12.13058\n" +
                    "G1 X27.200 Y103.727 E12.13514\n" +
                    "G1 X27.243 Y103.632 E12.13728\n" +
                    "G1 X27.329 Y103.573 E12.13943\n" +
                    "G1 X27.519 Y103.522 E12.14350\n" +
                    "G1 X28.299 Y103.497 E12.15957\n" +
                    "G1 X31.172 Y103.684 E12.21892\n" +
                    "G1 X31.538 Y103.695 E12.22647\n" +
                    "G1 X32.031 Y103.674 E12.23662\n" +
                    "G1 X32.358 Y103.607 E12.24350\n" +
                    "G1 X32.635 Y103.485 E12.24974\n" +
                    "G1 X32.822 Y103.350 E12.25450\n" +
                    "G1 X33.025 Y103.134 E12.26062\n" +
                    "G1 X33.153 Y102.950 E12.26524\n" +
                    "G1 X33.340 Y102.613 E12.27318\n" +
                    "G1 X33.666 Y101.846 E12.29036\n" +
                    "G1 X34.000 Y100.875 E12.31152\n" +
                    "G1 X36.020 Y94.503 E12.44928\n" +
                    "G1 X36.966 Y91.894 E12.50648\n" +
                    "G1 X38.071 Y89.044 E12.56948\n" +
                    "G1 X38.917 Y87.151 E12.61222\n" +
                    "G1 X39.415 Y86.234 E12.63373\n" +
                    "G1 X39.992 Y85.355 E12.65539\n" +
                    "G1 X40.643 Y84.560 E12.67657\n" +
                    "G1 X41.403 Y83.807 E12.69862\n" +
                    "G1 X41.837 Y83.437 E12.71038\n" +
                    "G1 X42.844 Y82.703 E12.73606\n" +
                    "G1 X44.050 Y82.006 E12.76476\n" +
                    "G1 X44.926 Y81.625 E12.78445\n" +
                    "G1 X45.938 Y81.301 E12.80635\n" +
                    "G1 X47.137 Y81.039 E12.83166\n" +
                    "G1 X50.395 Y80.677 E12.89922\n" +
                    "G1 X56.099 Y80.499 E13.01683\n" +
                    "G1 X63.637 Y80.563 E13.17220\n" +
                    "G1 X67.991 Y80.781 E13.26206\n" +
                    "G1 X72.104 Y81.202 E13.34726\n" +
                    "G1 X75.920 Y81.834 E13.42699\n" +
                    "G1 X79.353 Y82.653 E13.49972\n" +
                    "G1 X79.939 Y82.831 E13.51235\n" +
                    "G1 X81.585 Y83.437 E13.54849\n" +
                    "G1 X83.120 Y84.193 E13.58377\n" +
                    "G1 X84.103 Y84.780 E13.60736\n" +
                    "G1 X85.039 Y85.427 E13.63082\n" +
                    "G1 X85.833 Y86.079 E13.65199\n" +
                    "G1 X86.512 Y86.746 E13.67160\n" +
                    "G1 X86.884 Y87.175 E13.68329\n" +
                    "G1 X87.447 Y87.948 E13.70302\n" +
                    "G1 X88.086 Y89.050 E13.72927\n" +
                    "G1 X88.696 Y90.359 E13.75904\n" +
                    "G1 X90.134 Y94.287 E13.84525\n" +
                    "G1 X92.194 Y101.082 E13.99158\n" +
                    "G1 X93.192 Y104.919 E14.07330\n" +
                    "G1 X93.563 Y106.202 E14.10082\n" +
                    "G1 X93.779 Y106.774 E14.11344\n" +
                    "G1 X93.997 Y107.240 E14.12403\n" +
                    "G1 X94.225 Y107.630 E14.13333\n" +
                    "G1 X94.591 Y108.110 E14.14578\n" +
                    "G1 X94.885 Y108.408 E14.15441\n" +
                    "G1 X95.213 Y108.680 E14.16320\n" +
                    "G1 X95.754 Y109.028 E14.17645\n" +
                    "G1 X96.283 Y109.283 E14.18855\n" +
                    "G1 X96.999 Y109.533 E14.20419\n" +
                    "G1 X97.765 Y109.711 E14.22040\n" +
                    "G1 X98.783 Y109.838 E14.24155\n" +
                    "G1 X99.945 Y109.861 E14.26550\n" +
                    "G1 X101.216 Y109.838 E14.29170\n" +
                    "G1 X102.235 Y109.711 E14.31285\n" +
                    "G1 X103.001 Y109.533 E14.32905\n" +
                    "G1 X103.717 Y109.283 E14.34470\n" +
                    "G1 X104.246 Y109.028 E14.35680\n" +
                    "G1 X104.787 Y108.680 E14.37005\n" +
                    "G1 X105.115 Y108.408 E14.37884\n" +
                    "G1 X105.409 Y108.110 E14.38747\n" +
                    "G1 X105.775 Y107.630 E14.39991\n" +
                    "G1 X106.003 Y107.240 E14.40922\n" +
                    "G1 X106.221 Y106.774 E14.41981\n" +
                    "G1 X106.437 Y106.202 E14.43242\n" +
                    "G1 X106.808 Y104.919 E14.45994\n" +
                    "G1 X107.806 Y101.082 E14.54167\n" +
                    "G1 X109.866 Y94.287 E14.68799\n" +
                    "G1 X111.304 Y90.359 E14.77421\n" +
                    "G1 X111.914 Y89.050 E14.80398\n" +
                    "G1 X112.553 Y87.948 E14.83023\n" +
                    "G1 X113.116 Y87.175 E14.84995\n" +
                    "G1 X113.488 Y86.746 E14.86165\n" +
                    "G1 X114.167 Y86.079 E14.88126\n" +
                    "G1 X114.961 Y85.427 E14.90243\n" +
                    "G1 X115.897 Y84.780 E14.92589\n" +
                    "G1 X116.880 Y84.193 E14.94948\n" +
                    "G1 X118.415 Y83.437 E14.98476\n" +
                    "G1 X120.061 Y82.831 E15.02090\n" +
                    "G1 X120.647 Y82.653 E15.03352\n" +
                    "G1 X124.080 Y81.834 E15.10626\n" +
                    "G1 X127.896 Y81.202 E15.18598\n" +
                    "G1 X132.009 Y80.781 E15.27119\n" +
                    "G1 X136.363 Y80.563 E15.36105\n" +
                    "G1 X143.901 Y80.499 E15.51641\n" +
                    "G1 X149.605 Y80.677 E15.63403\n" +
                    "G1 X152.863 Y81.039 E15.70159\n" +
                    "G1 X154.062 Y81.301 E15.72690\n" +
                    "G1 X155.074 Y81.625 E15.74880\n" +
                    "G1 X155.949 Y82.006 E15.76845\n" +
                    "G1 X157.161 Y82.707 E15.79732\n" +
                    "G1 X158.163 Y83.437 E15.82287\n" +
                    "G1 X158.597 Y83.807 E15.83463\n" +
                    "G1 X159.357 Y84.560 E15.85668\n" +
                    "G1 X160.008 Y85.355 E15.87785\n" +
                    "G1 X160.585 Y86.234 E15.89952\n" +
                    "G1 X161.083 Y87.151 E15.92103\n" +
                    "G1 X161.929 Y89.044 E15.96376\n" +
                    "G1 X163.034 Y91.894 E16.02677\n" +
                    "G1 X163.980 Y94.503 E16.08397\n" +
                    "G1 X166.000 Y100.875 E16.22173\n" +
                    "G1 X166.334 Y101.846 E16.24289\n" +
                    "G1 X166.660 Y102.613 E16.26007\n" +
                    "G1 X166.847 Y102.950 E16.26801\n" +
                    "G1 X166.975 Y103.134 E16.27263\n" +
                    "G1 X167.178 Y103.350 E16.27874\n" +
                    "G1 X167.365 Y103.485 E16.28350\n" +
                    "G1 X167.642 Y103.607 E16.28975\n" +
                    "G1 X167.969 Y103.674 E16.29663\n" +
                    "G1 X168.462 Y103.695 E16.30678\n" +
                    "G1 X168.828 Y103.684 E16.31433\n" +
                    "G1 X171.701 Y103.497 E16.37367\n" +
                    "G1 X172.483 Y103.523 E16.38979\n" +
                    "G1 X172.671 Y103.572 E16.39381\n" +
                    "G1 X172.757 Y103.632 E16.39596\n" +
                    "G1 X172.800 Y103.727 E16.39811\n" +
                    "G1 X172.825 Y103.947 E16.40268\n" +
                    "G1 X172.808 Y107.199 E16.46970\n" +
                    "G1 X172.874 Y110.455 E16.53682\n" +
                    "G1 X173.003 Y112.277 E16.57447\n" +
                    "G1 X172.982 Y112.366 E16.57636\n" +
                    "G1 X172.911 Y112.429 E16.57831\n" +
                    "G1 X172.781 Y112.494 E16.58131\n" +
                    "G1 X167.837 Y113.751 E16.68644\n" +
                    "G1 X162.190 Y115.397 E16.80767\n" +
                    "G1 X160.598 Y115.794 E16.84149\n" +
                    "G1 X152.530 Y117.471 E17.01133\n" +
                    "G1 X148.268 Y118.258 E17.10065\n" +
                    "G1 X143.318 Y118.930 E17.20362\n" +
                    "G1 X135.378 Y119.507 E17.36769\n" +
                    "G1 X131.556 Y119.501 E17.44646\n" +
                    "G1 X126.683 Y119.370 E17.54695\n" +
                    "G1 X121.957 Y118.771 E17.64513\n" +
                    "G1 X117.343 Y117.865 E17.74203\n" +
                    "G1 X114.769 Y117.208 E17.79678\n" +
                    "G1 X109.964 Y115.612 E17.90113\n" +
                    "G1 X107.113 Y114.739 E17.96260\n" +
                    "G1 X104.551 Y114.187 E18.01660\n" +
                    "G1 X102.363 Y113.883 E18.06214\n" +
                    "G1 X101.168 Y113.813 E18.08680\n" +
                    "G1 X98.832 Y113.813 E18.13495\n" +
                    "G1 X97.637 Y113.883 E18.15961\n" +
                    "G1 X95.449 Y114.187 E18.20516\n" +
                    "G1 X92.887 Y114.739 E18.25916\n" +
                    "G1 X90.036 Y115.612 E18.32063\n" +
                    "G1 X85.231 Y117.208 E18.42498\n" +
                    "G1 X82.657 Y117.865 E18.47972\n" +
                    "G1 X78.043 Y118.771 E18.57663\n" +
                    "G1 X73.317 Y119.370 E18.67481\n" +
                    "G1 X68.444 Y119.501 E18.77530\n" +
                    "G1 X64.622 Y119.507 E18.85407\n" +
                    "G1 X56.682 Y118.930 E19.01813\n" +
                    "G1 X51.732 Y118.258 E19.12111\n" +
                    "G1 X47.470 Y117.471 E19.21043\n" +
                    "G1 X39.402 Y115.794 E19.38027\n" +
                    "G1 X37.810 Y115.397 E19.41408\n" +
                    "G1 X32.163 Y113.751 E19.53532\n" +
                    "G1 X27.409 Y112.550 E19.63637\n" +
                    "G1 X27.219 Y112.494 E19.64045\n" +
                    "G1 X27.074 Y112.416 E19.64384\n" +
                    "G1 X27.067 Y112.288 F7800.000\n" +
                    "G1 E17.64384 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X28.673 Y110.796 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1200\n" +
                    "G1 X29.168 Y111.290 E2.01950\n" +
                    "G1 X30.411 Y111.604 E2.05522\n" +
                    "G1 X28.717 Y109.910 E2.12200\n" +
                    "G1 X28.735 Y109.000 E2.14738\n" +
                    "G1 X31.654 Y111.918 E2.26242\n" +
                    "G1 X32.897 Y112.233 E2.29816\n" +
                    "G1 X28.754 Y108.090 E2.46147\n" +
                    "G1 X28.772 Y107.179 E2.48685\n" +
                    "G1 X34.207 Y112.614 E2.70109\n" +
                    "G1 X35.518 Y112.996 E2.73915\n" +
                    "G1 X28.773 Y106.251 E3.00504\n" +
                    "G1 X28.768 Y105.317 E3.03106\n" +
                    "G1 X36.829 Y113.378 E3.34881\n" +
                    "G1 X37.751 Y113.647 E3.37559\n" +
                    "G1 X37.079 Y113.130 E3.39922\n" +
                    "G1 X36.388 Y112.457 E3.42612\n" +
                    "G1 X35.918 Y111.876 E3.44693\n" +
                    "G1 X35.529 Y111.276 E3.46689\n" +
                    "G1 X35.374 Y110.995 E3.47583\n" +
                    "G1 X29.563 Y105.184 E3.70489\n" +
                    "G1 X30.556 Y105.248 E3.73263\n" +
                    "G1 X34.730 Y109.422 E3.89715\n" +
                    "G1 X34.646 Y109.176 E3.90438\n" +
                    "G1 X34.413 Y108.176 E3.93300\n" +
                    "G1 X31.509 Y105.272 E4.04749\n" +
                    "G1 X32.006 Y105.251 E4.06137\n" +
                    "G1 X32.347 Y105.181 E4.07106\n" +
                    "G1 X34.255 Y107.090 E4.14628\n" +
                    "G1 X34.137 Y106.043 E4.17564\n" +
                    "G1 X33.045 Y104.950 E4.21871\n" +
                    "G1 X33.303 Y104.837 E4.22656\n" +
                    "G1 X33.626 Y104.603 E4.23769\n" +
                    "G1 X34.121 Y105.098 E4.25718\n" +
                    "G1 E2.25718 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X90.409 Y113.904 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1200\n" +
                    "G1 X89.915 Y113.410 E2.01926\n" +
                    "G1 X90.318 Y112.895 E2.03727\n" +
                    "G1 X91.112 Y113.689 E2.06823\n" +
                    "G1 X91.815 Y113.474 E2.08847\n" +
                    "G1 X90.680 Y112.339 E2.13270\n" +
                    "G1 X90.841 Y112.090 E2.14085\n" +
                    "G1 X91.014 Y111.756 E2.15124\n" +
                    "G1 X92.518 Y113.259 E2.20980\n" +
                    "G1 X93.261 Y113.085 E2.23084\n" +
                    "G1 X91.322 Y111.146 E2.30638\n" +
                    "G1 X91.582 Y110.488 E2.32587\n" +
                    "G1 X94.017 Y112.922 E2.42070\n" +
                    "G1 X94.772 Y112.760 E2.44198\n" +
                    "G1 X91.801 Y109.788 E2.55773\n" +
                    "G1 X91.928 Y109.338 E2.57063\n" +
                    "G1 X91.980 Y109.050 E2.57870\n" +
                    "G1 X95.537 Y112.607 E2.71728\n" +
                    "G1 X96.343 Y112.495 E2.73970\n" +
                    "G1 X92.119 Y108.271 E2.90426\n" +
                    "G1 X92.157 Y107.896 E2.91464\n" +
                    "G1 X92.453 Y108.403 E2.93081\n" +
                    "G1 X92.958 Y109.066 E2.95377\n" +
                    "G1 X93.398 Y109.512 E2.97103\n" +
                    "G1 X93.882 Y109.913 E2.98835\n" +
                    "G1 X94.610 Y110.382 E3.01220\n" +
                    "G1 X95.334 Y110.731 E3.03434\n" +
                    "G1 X95.585 Y110.819 E3.04167\n" +
                    "G1 X97.149 Y112.383 E3.10261\n" +
                    "G1 X97.704 Y112.306 E3.11806\n" +
                    "G1 X97.974 Y112.290 E3.12550\n" +
                    "G1 X96.881 Y111.197 E3.16811\n" +
                    "G1 X97.197 Y111.270 E3.17705\n" +
                    "G1 X97.968 Y111.366 E3.19846\n" +
                    "G1 X98.841 Y112.239 E3.23247\n" +
                    "G1 X99.748 Y112.228 E3.25746\n" +
                    "G1 X98.951 Y111.431 E3.28851\n" +
                    "G1 X99.880 Y111.442 E3.31411\n" +
                    "G1 X100.666 Y112.228 E3.34473\n" +
                    "G1 X101.591 Y112.235 E3.37021\n" +
                    "G1 X100.782 Y111.426 E3.40173\n" +
                    "G1 X101.099 Y111.421 E3.41047\n" +
                    "G1 X101.628 Y111.355 E3.42516\n" +
                    "G1 X102.567 Y112.293 E3.46171\n" +
                    "G1 X102.790 Y112.306 E3.46788\n" +
                    "G1 X103.612 Y112.420 E3.49073\n" +
                    "G1 X102.433 Y111.241 E3.53666\n" +
                    "G1 X103.178 Y111.068 E3.55773\n" +
                    "G1 X104.678 Y112.568 E3.61616\n" +
                    "G1 X105.118 Y112.629 E3.62841\n" +
                    "G1 X105.805 Y112.777 E3.64776\n" +
                    "G1 X103.865 Y110.838 E3.72330\n" +
                    "G1 X104.172 Y110.731 E3.73225\n" +
                    "G1 X104.512 Y110.567 E3.74265\n" +
                    "G1 X106.974 Y113.029 E3.83857\n" +
                    "G1 X107.822 Y113.212 E3.86245\n" +
                    "G1 X108.187 Y113.323 E3.87296\n" +
                    "G1 X105.108 Y110.245 E3.99287\n" +
                    "G1 X105.662 Y109.881 E4.01113\n" +
                    "G1 X109.509 Y113.728 E4.16100\n" +
                    "G1 X110.085 Y113.905 E4.17758\n" +
                    "G1 X109.671 Y113.374 E4.19613\n" +
                    "G1 X109.159 Y112.585 E4.22203\n" +
                    "G1 X109.025 Y112.326 E4.23008\n" +
                    "G1 X106.159 Y109.460 E4.34171\n" +
                    "G1 X106.606 Y108.989 E4.35960\n" +
                    "G1 X108.318 Y110.701 E4.42628\n" +
                    "G1 X108.072 Y109.832 E4.45114\n" +
                    "G1 X108.007 Y109.472 E4.46121\n" +
                    "G1 X107.003 Y108.468 E4.50032\n" +
                    "G1 X107.349 Y107.896 E4.51874\n" +
                    "G1 X107.843 Y108.391 E4.53800\n" +
                    "G1 E2.53800 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X162.249 Y113.153 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F1200\n" +
                    "G1 X162.743 Y113.647 E2.01925\n" +
                    "G1 X163.454 Y113.440 E2.03961\n" +
                    "G1 X162.762 Y112.748 E2.06655\n" +
                    "G1 X163.091 Y112.485 E2.07816\n" +
                    "G1 X163.253 Y112.322 E2.08448\n" +
                    "G1 X164.164 Y113.233 E2.11993\n" +
                    "G1 X164.874 Y113.026 E2.14030\n" +
                    "G1 X163.701 Y111.853 E2.18596\n" +
                    "G1 X164.107 Y111.342 E2.20393\n" +
                    "G1 X165.584 Y112.819 E2.26143\n" +
                    "G1 X166.295 Y112.612 E2.28179\n" +
                    "G1 X164.468 Y110.786 E2.35288\n" +
                    "G1 X164.780 Y110.181 E2.37163\n" +
                    "G1 X167.005 Y112.405 E2.45823\n" +
                    "G1 X167.719 Y112.202 E2.47866\n" +
                    "G1 X165.060 Y109.543 E2.58215\n" +
                    "G1 X165.293 Y108.859 E2.60204\n" +
                    "G1 X168.451 Y112.017 E2.72495\n" +
                    "G1 X169.183 Y111.832 E2.74574\n" +
                    "G1 X165.482 Y108.131 E2.88981\n" +
                    "G1 X165.648 Y107.380 E2.91098\n" +
                    "G1 X169.915 Y111.647 E3.07708\n" +
                    "G1 X170.648 Y111.462 E3.09787\n" +
                    "G1 X165.749 Y106.563 E3.28857\n" +
                    "G1 X165.849 Y105.746 E3.31122\n" +
                    "G1 X171.322 Y111.219 E3.52425\n" +
                    "G1 X171.280 Y110.260 E3.55066\n" +
                    "G1 X165.875 Y104.855 E3.76109\n" +
                    "G1 X165.879 Y104.603 E3.76801\n" +
                    "G1 X166.203 Y104.837 E3.77900\n" +
                    "G1 X166.837 Y105.116 E3.79807\n" +
                    "G1 X167.109 Y105.171 E3.80570\n" +
                    "G1 X171.261 Y109.324 E3.96736\n" +
                    "G1 X171.242 Y108.388 E3.99313\n" +
                    "G1 X168.132 Y105.278 E4.11420\n" +
                    "G1 X169.016 Y105.244 E4.13854\n" +
                    "G1 X171.223 Y107.452 E4.22448\n" +
                    "G1 X171.228 Y106.539 E4.24960\n" +
                    "G1 X169.877 Y105.188 E4.30220\n" +
                    "G1 X170.738 Y105.132 E4.32596\n" +
                    "G1 X171.233 Y105.627 E4.34521\n" +
                    "G1 Z0.950 F7800.000\n" +
                    "G1 E2.34521 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X108.911 Y107.182 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X108.917 Y105.961 E2.03293\n" +
                    "G1 X109.089 Y104.080 E2.08387\n" +
                    "G1 X109.610 Y101.083 E2.16595\n" +
                    "G1 X110.398 Y97.699 E2.25968\n" +
                    "G1 X111.418 Y94.096 E2.36068\n" +
                    "G1 X112.456 Y91.238 E2.44270\n" +
                    "G1 X113.039 Y90.044 E2.47855\n" +
                    "G1 X113.556 Y89.201 E2.50523\n" +
                    "G1 X114.056 Y88.547 E2.52745\n" +
                    "G1 X114.746 Y87.829 E2.55432\n" +
                    "G1 X115.451 Y87.240 E2.57909\n" +
                    "G1 X116.339 Y86.625 E2.60823\n" +
                    "G1 X117.497 Y85.935 E2.64459\n" +
                    "G1 X118.876 Y85.227 E2.68640\n" +
                    "G1 X120.362 Y84.588 E2.73004\n" +
                    "G1 X122.718 Y83.798 E2.79707\n" +
                    "G1 X125.521 Y83.119 E2.87488\n" +
                    "G1 X128.831 Y82.556 E2.96546\n" +
                    "G1 X132.626 Y82.107 E3.06854\n" +
                    "G1 X139.059 Y81.605 E3.24261\n" +
                    "G1 X140.199 Y81.559 E3.27340\n" +
                    "G1 X144.813 Y81.458 E3.39789\n" +
                    "G1 X149.474 Y81.719 E3.52383\n" +
                    "G1 X152.590 Y82.173 E3.60876\n" +
                    "G1 X154.027 Y82.546 E3.64883\n" +
                    "G1 X154.994 Y82.907 E3.67667\n" +
                    "G1 X155.755 Y83.283 E3.69957\n" +
                    "G1 X156.335 Y83.641 E3.71795\n" +
                    "G1 X156.743 Y83.936 E3.73154\n" +
                    "G1 X157.390 Y84.485 E3.75443\n" +
                    "G1 X158.037 Y85.146 E3.77937\n" +
                    "G1 X158.687 Y85.932 E3.80689\n" +
                    "G1 X159.111 Y86.513 E3.82631\n" +
                    "G1 X160.128 Y88.128 E3.87777\n" +
                    "G1 X161.166 Y90.135 E3.93874\n" +
                    "G1 X161.658 Y91.228 E3.97108\n" +
                    "G1 X162.996 Y94.781 E4.07349\n" +
                    "G1 X164.028 Y98.513 E4.17794\n" +
                    "G1 X164.582 Y101.669 E4.26439\n" +
                    "G1 X164.754 Y103.886 E4.32436\n" +
                    "G1 X164.720 Y105.799 E4.37598\n" +
                    "G1 X164.540 Y107.328 E4.41752\n" +
                    "G1 X164.265 Y108.544 E4.45114\n" +
                    "G1 X163.981 Y109.377 E4.47490\n" +
                    "G1 X163.680 Y110.035 E4.49441\n" +
                    "G1 X163.525 Y110.316 E4.50306\n" +
                    "G1 X163.203 Y110.813 E4.51904\n" +
                    "G1 X162.831 Y111.273 E4.53501\n" +
                    "G1 X162.257 Y111.831 E4.55659\n" +
                    "G1 X161.707 Y112.256 E4.57534\n" +
                    "G1 X161.049 Y112.674 E4.59639\n" +
                    "G1 X160.234 Y113.109 E4.62130\n" +
                    "G1 X159.499 Y113.454 E4.64320\n" +
                    "G1 X156.644 Y114.581 E4.72600\n" +
                    "G1 X154.135 Y115.374 E4.79699\n" +
                    "G1 X150.068 Y116.340 E4.90974\n" +
                    "G1 X145.802 Y117.004 E5.02623\n" +
                    "G1 X139.569 Y117.571 E5.19507\n" +
                    "G1 X133.089 Y117.824 E5.37000\n" +
                    "G1 X131.132 Y117.837 E5.42279\n" +
                    "G1 X125.914 Y117.655 E5.56364\n" +
                    "G1 X121.420 Y117.123 E5.68571\n" +
                    "G1 X117.297 Y116.204 E5.79966\n" +
                    "G1 X114.809 Y115.394 E5.87025\n" +
                    "G1 X113.403 Y114.782 E5.91162\n" +
                    "G1 X112.618 Y114.336 E5.93598\n" +
                    "G1 X112.028 Y113.910 E5.95560\n" +
                    "G1 X111.527 Y113.463 E5.97373\n" +
                    "G1 X111.033 Y112.931 E5.99331\n" +
                    "G1 X110.556 Y112.320 E6.01423\n" +
                    "G1 X110.114 Y111.638 E6.03614\n" +
                    "G1 X109.725 Y110.888 E6.05894\n" +
                    "G1 X109.417 Y110.109 E6.08153\n" +
                    "G1 X109.180 Y109.273 E6.10499\n" +
                    "G1 X109.000 Y108.270 E6.13246\n" +
                    "G1 X108.917 Y107.256 E6.15990\n" +
                    "G1 X109.472 Y107.160 F7800.000\n" +
                    "G1 F3000\n" +
                    "G1 X109.477 Y105.988 E6.18406\n" +
                    "G1 X109.645 Y104.154 E6.22201\n" +
                    "G1 X110.160 Y101.194 E6.28393\n" +
                    "G1 X110.941 Y97.839 E6.35494\n" +
                    "G1 X111.952 Y94.269 E6.43141\n" +
                    "G1 X112.973 Y91.458 E6.49305\n" +
                    "G1 X113.531 Y90.314 E6.51927\n" +
                    "G1 X114.019 Y89.519 E6.53850\n" +
                    "G1 X114.483 Y88.913 E6.55424\n" +
                    "G1 X115.129 Y88.239 E6.57348\n" +
                    "G1 X115.791 Y87.687 E6.59125\n" +
                    "G1 X116.643 Y87.097 E6.61260\n" +
                    "G1 X117.769 Y86.425 E6.63962\n" +
                    "G1 X119.115 Y85.735 E6.67080\n" +
                    "G1 X120.562 Y85.112 E6.70327\n" +
                    "G1 X122.873 Y84.337 E6.75351\n" +
                    "G1 X125.634 Y83.669 E6.81206\n" +
                    "G1 X128.911 Y83.111 E6.88057\n" +
                    "G1 X132.680 Y82.665 E6.95880\n" +
                    "G1 X139.092 Y82.164 E7.09135\n" +
                    "G1 X140.217 Y82.119 E7.11454\n" +
                    "G1 X144.803 Y82.019 E7.20910\n" +
                    "G1 X149.418 Y82.277 E7.30436\n" +
                    "G1 X152.478 Y82.723 E7.36810\n" +
                    "G1 X153.858 Y83.081 E7.39749\n" +
                    "G1 X154.771 Y83.422 E7.41757\n" +
                    "G1 X155.483 Y83.774 E7.43393\n" +
                    "G1 X156.023 Y84.107 E7.44701\n" +
                    "G1 X156.397 Y84.378 E7.45653\n" +
                    "G1 X157.008 Y84.896 E7.47303\n" +
                    "G1 X157.620 Y85.521 E7.49107\n" +
                    "G1 X158.244 Y86.276 E7.51126\n" +
                    "G1 X158.647 Y86.828 E7.52535\n" +
                    "G1 X159.641 Y88.406 E7.56378\n" +
                    "G1 X160.661 Y90.379 E7.60955\n" +
                    "G1 X161.140 Y91.442 E7.63359\n" +
                    "G1 X162.462 Y94.955 E7.71094\n" +
                    "G1 X163.480 Y98.636 E7.78966\n" +
                    "G1 X164.025 Y101.740 E7.85460\n" +
                    "G1 X164.193 Y103.902 E7.89932\n" +
                    "G1 X164.160 Y105.761 E7.93763\n" +
                    "G1 X163.987 Y107.233 E7.96817\n" +
                    "G1 X163.725 Y108.391 E7.99264\n" +
                    "G1 X163.459 Y109.170 E8.00960\n" +
                    "G1 X163.178 Y109.783 E8.02350\n" +
                    "G1 X163.044 Y110.028 E8.02926\n" +
                    "G1 X162.748 Y110.483 E8.04045\n" +
                    "G1 X162.416 Y110.895 E8.05136\n" +
                    "G1 X161.889 Y111.407 E8.06649\n" +
                    "G1 X161.384 Y111.796 E8.07963\n" +
                    "G1 X160.766 Y112.190 E8.09475\n" +
                    "G1 X159.982 Y112.607 E8.11304\n" +
                    "G1 X159.277 Y112.939 E8.12912\n" +
                    "G1 X156.457 Y114.052 E8.19160\n" +
                    "G1 X153.985 Y114.833 E8.24502\n" +
                    "G1 X149.960 Y115.789 E8.33028\n" +
                    "G1 X145.733 Y116.447 E8.41846\n" +
                    "G1 X139.532 Y117.011 E8.54679\n" +
                    "G1 X133.076 Y117.264 E8.67995\n" +
                    "G1 X131.140 Y117.276 E8.71986\n" +
                    "G1 X125.957 Y117.096 E8.82675\n" +
                    "G1 X121.514 Y116.569 E8.91895\n" +
                    "G1 X117.445 Y115.662 E9.00487\n" +
                    "G1 X115.008 Y114.869 E9.05769\n" +
                    "G1 X113.654 Y114.280 E9.08813\n" +
                    "G1 X112.921 Y113.863 E9.10550\n" +
                    "G1 X112.380 Y113.473 E9.11926\n" +
                    "G1 X111.920 Y113.062 E9.13197\n" +
                    "G1 X111.460 Y112.567 E9.14590\n" +
                    "G1 X111.013 Y111.994 E9.16088\n" +
                    "G1 X110.599 Y111.356 E9.17655\n" +
                    "G1 X110.235 Y110.655 E9.19282\n" +
                    "G1 X109.948 Y109.930 E9.20891\n" +
                    "G1 X109.727 Y109.146 E9.22568\n" +
                    "G1 X109.557 Y108.198 E9.24554\n" +
                    "G1 X109.478 Y107.235 E9.26546\n" +
                    "G1 X109.472 Y107.160 F7800.000\n" +
                    "G1 E7.26546 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X115.081 Y86.723 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F900\n" +
                    "G1 X115.611 Y86.289 E2.01847\n" +
                    "G1 X116.480 Y85.687 E2.04701\n" +
                    "G1 X117.421 Y85.127 E2.07654\n" +
                    "G1 X118.841 Y84.429 E2.11922\n" +
                    "G1 X120.406 Y83.853 E2.16421\n" +
                    "G1 X120.927 Y83.695 E2.17889\n" +
                    "G1 X121.274 Y83.612 E2.18852\n" +
                    "G1 X120.135 Y83.994 E2.22093\n" +
                    "G1 X118.605 Y84.652 E2.26586\n" +
                    "G1 X117.189 Y85.378 E2.30879\n" +
                    "G1 X115.995 Y86.090 E2.34629\n" +
                    "G1 X115.143 Y86.681 E2.37426\n" +
                    "G1 X114.937 Y86.841 F7800.000\n" +
                    "G1 F900\n" +
                    "G1 X114.887 Y86.883 E2.37602\n" +
                    "G1 E0.37602 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X108.987 Y100.956 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X108.458 Y103.997 E2.08326\n" +
                    "G1 X108.281 Y105.930 E2.13564\n" +
                    "G1 X108.275 Y107.206 E2.17005\n" +
                    "G1 X108.369 Y108.352 E2.20108\n" +
                    "G1 X108.560 Y109.415 E2.23022\n" +
                    "G1 X108.814 Y110.313 E2.25539\n" +
                    "G1 X109.145 Y111.152 E2.27972\n" +
                    "G1 X109.564 Y111.958 E2.30422\n" +
                    "G1 X110.037 Y112.689 E2.32772\n" +
                    "G1 X110.549 Y113.343 E2.35012\n" +
                    "G1 X111.081 Y113.917 E2.37123\n" +
                    "G1 X111.629 Y114.406 E2.39105\n" +
                    "G1 X112.274 Y114.872 E2.41250\n" +
                    "G1 X113.119 Y115.351 E2.43870\n" +
                    "G1 X114.583 Y115.989 E2.48180\n" +
                    "G1 X115.443 Y116.269 E2.50618\n" +
                    "G1 X115.146 Y116.193 E2.51446\n" +
                    "G1 X110.289 Y114.584 E2.65248\n" +
                    "G1 X107.384 Y113.694 E2.73445\n" +
                    "G1 X104.646 Y113.110 E2.80997\n" +
                    "G1 X102.468 Y112.810 E2.86926\n" +
                    "G1 X101.201 Y112.735 E2.90352\n" +
                    "G1 X98.799 Y112.735 E2.96830\n" +
                    "G1 X97.531 Y112.810 E3.00258\n" +
                    "G1 X95.261 Y113.125 E3.06441\n" +
                    "G1 X92.615 Y113.695 E3.13741\n" +
                    "G1 X89.711 Y114.584 E3.21935\n" +
                    "G1 X84.854 Y116.193 E3.35737\n" +
                    "G1 X84.557 Y116.269 E3.36565\n" +
                    "G1 X85.417 Y115.989 E3.39003\n" +
                    "G1 X86.881 Y115.351 E3.43313\n" +
                    "G1 X87.726 Y114.872 E3.45934\n" +
                    "G1 X88.371 Y114.406 E3.48078\n" +
                    "G1 X88.919 Y113.917 E3.50060\n" +
                    "G1 X89.451 Y113.343 E3.52171\n" +
                    "G1 X89.963 Y112.689 E3.54411\n" +
                    "G1 X90.436 Y111.958 E3.56761\n" +
                    "G1 X90.855 Y111.152 E3.59212\n" +
                    "G1 X91.186 Y110.313 E3.61644\n" +
                    "G1 X91.440 Y109.415 E3.64162\n" +
                    "G1 X91.631 Y108.352 E3.67075\n" +
                    "G1 X91.725 Y107.206 E3.70178\n" +
                    "G1 X91.719 Y105.930 E3.73620\n" +
                    "G1 X91.542 Y103.997 E3.78857\n" +
                    "G1 X91.013 Y100.956 E3.87183\n" +
                    "G1 X90.959 Y100.724 E3.87827\n" +
                    "G1 X91.156 Y101.374 E3.89662\n" +
                    "G1 X92.153 Y105.206 E4.00343\n" +
                    "G1 X92.536 Y106.531 E4.04064\n" +
                    "G1 X92.785 Y107.194 E4.05973\n" +
                    "G1 X93.042 Y107.741 E4.07604\n" +
                    "G1 X93.329 Y108.233 E4.09141\n" +
                    "G1 X93.785 Y108.829 E4.11166\n" +
                    "G1 X94.156 Y109.204 E4.12587\n" +
                    "G1 X94.576 Y109.552 E4.14060\n" +
                    "G1 X95.229 Y109.973 E4.16156\n" +
                    "G1 X95.914 Y110.297 E4.18201\n" +
                    "G1 X96.701 Y110.571 E4.20449\n" +
                    "G1 X97.609 Y110.779 E4.22962\n" +
                    "G1 X98.708 Y110.915 E4.25949\n" +
                    "G1 X100.057 Y110.939 E4.29589\n" +
                    "G1 X101.293 Y110.915 E4.32922\n" +
                    "G1 X102.390 Y110.779 E4.35906\n" +
                    "G1 X103.300 Y110.571 E4.38422\n" +
                    "G1 X104.130 Y110.281 E4.40795\n" +
                    "G1 X104.777 Y109.969 E4.42733\n" +
                    "G1 X105.445 Y109.536 E4.44880\n" +
                    "G1 X105.845 Y109.203 E4.46284\n" +
                    "G1 X106.215 Y108.829 E4.47702\n" +
                    "G1 X106.671 Y108.233 E4.49727\n" +
                    "G1 X106.958 Y107.741 E4.51264\n" +
                    "G1 X107.215 Y107.194 E4.52895\n" +
                    "G1 X107.464 Y106.531 E4.54804\n" +
                    "G1 X107.847 Y105.206 E4.58525\n" +
                    "G1 X108.844 Y101.374 E4.69206\n" +
                    "G1 X109.041 Y100.724 E4.71041\n" +
                    "G1 X109.004 Y100.883 E4.71483\n" +
                    "G1 E2.71483 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X85.147 Y86.914 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F900\n";
                     salientemarcos[2]+=
                    "G1 X85.145 Y86.912 E2.00007\n" +
                    "G1 X84.901 Y86.711 F7800.000\n" +
                    "G1 F900\n" +
                    "G1 X84.005 Y86.090 E2.02947\n" +
                    "G1 X82.811 Y85.378 E2.06696\n" +
                    "G1 X81.395 Y84.652 E2.10990\n" +
                    "G1 X79.865 Y83.994 E2.15482\n" +
                    "G1 X78.723 Y83.611 E2.18731\n" +
                    "G1 X79.073 Y83.695 E2.19701\n" +
                    "G1 X79.594 Y83.853 E2.21170\n" +
                    "G1 X81.159 Y84.429 E2.25670\n" +
                    "G1 X82.579 Y85.127 E2.29937\n" +
                    "G1 X83.520 Y85.687 E2.32890\n" +
                    "G1 X84.388 Y86.288 E2.35739\n" +
                    "G1 X84.843 Y86.663 E2.37330\n" +
                    "G1 X84.539 Y87.233 F7800.000\n" +
                    "G1 F3600\n" +
                    "G1 X85.392 Y87.960 E2.40353\n" +
                    "G1 X85.947 Y88.551 E2.42539\n" +
                    "G1 X86.444 Y89.201 E2.44748\n" +
                    "G1 X86.961 Y90.044 E2.47416\n" +
                    "G1 X87.544 Y91.238 E2.51000\n" +
                    "G1 X88.582 Y94.096 E2.59203\n" +
                    "G1 X89.602 Y97.699 E2.69303\n" +
                    "G1 X90.390 Y101.083 E2.78676\n" +
                    "G1 X90.911 Y104.080 E2.86884\n" +
                    "G1 X91.083 Y105.961 E2.91978\n" +
                    "G1 X91.089 Y107.182 E2.95271\n" +
                    "G1 X91.000 Y108.270 E2.98218\n" +
                    "G1 X90.820 Y109.273 E3.00965\n" +
                    "G1 X90.583 Y110.109 E3.03311\n" +
                    "G1 X90.275 Y110.888 E3.05569\n" +
                    "G1 X89.886 Y111.638 E3.07850\n" +
                    "G1 X89.444 Y112.320 E3.10041\n" +
                    "G1 X88.967 Y112.931 E3.12132\n" +
                    "G1 X88.473 Y113.463 E3.14090\n" +
                    "G1 X87.972 Y113.910 E3.15903\n" +
                    "G1 X87.382 Y114.336 E3.17865\n" +
                    "G1 X86.597 Y114.782 E3.20302\n" +
                    "G1 X85.191 Y115.394 E3.24438\n" +
                    "G1 X82.703 Y116.204 E3.31497\n" +
                    "G1 X78.580 Y117.123 E3.42892\n" +
                    "G1 X74.086 Y117.655 E3.55099\n" +
                    "G1 X68.868 Y117.837 E3.69184\n" +
                    "G1 X66.911 Y117.824 E3.74464\n" +
                    "G1 X60.431 Y117.571 E3.91957\n" +
                    "G1 X54.198 Y117.004 E4.08841\n" +
                    "G1 X49.932 Y116.340 E4.20490\n" +
                    "G1 X45.865 Y115.374 E4.31764\n" +
                    "G1 X43.356 Y114.581 E4.38864\n" +
                    "G1 X40.501 Y113.454 E4.47143\n" +
                    "G1 X39.766 Y113.109 E4.49334\n" +
                    "G1 X38.951 Y112.674 E4.51824\n" +
                    "G1 X38.293 Y112.256 E4.53929\n" +
                    "G1 X37.743 Y111.831 E4.55804\n" +
                    "G1 X37.169 Y111.273 E4.57962\n" +
                    "G1 X36.797 Y110.813 E4.59559\n" +
                    "G1 X36.475 Y110.316 E4.61157\n" +
                    "G1 X36.320 Y110.035 E4.62022\n" +
                    "G1 X36.019 Y109.377 E4.63973\n" +
                    "G1 X35.735 Y108.544 E4.66349\n" +
                    "G1 X35.460 Y107.328 E4.69712\n" +
                    "G1 X35.280 Y105.799 E4.73865\n" +
                    "G1 X35.246 Y103.886 E4.79028\n" +
                    "G1 X35.418 Y101.669 E4.85024\n" +
                    "G1 X35.972 Y98.513 E4.93670\n" +
                    "G1 X37.004 Y94.781 E5.04114\n" +
                    "G1 X38.342 Y91.228 E5.14355\n" +
                    "G1 X38.834 Y90.135 E5.17590\n" +
                    "G1 X39.872 Y88.128 E5.23686\n" +
                    "G1 X40.889 Y86.513 E5.28833\n" +
                    "G1 X41.313 Y85.932 E5.30774\n" +
                    "G1 X41.963 Y85.146 E5.33526\n" +
                    "G1 X42.610 Y84.485 E5.36020\n" +
                    "G1 X43.257 Y83.936 E5.38309\n" +
                    "G1 X43.665 Y83.641 E5.39668\n" +
                    "G1 X44.245 Y83.283 E5.41506\n" +
                    "G1 X45.006 Y82.907 E5.43796\n" +
                    "G1 X45.973 Y82.546 E5.46580\n" +
                    "G1 X47.410 Y82.173 E5.50587\n" +
                    "G1 X50.526 Y81.719 E5.59080\n" +
                    "G1 X55.187 Y81.458 E5.71675\n" +
                    "G1 X60.331 Y81.570 E5.85553\n" +
                    "G1 X67.374 Y82.107 E6.04608\n" +
                    "G1 X71.169 Y82.556 E6.14918\n" +
                    "G1 X74.479 Y83.119 E6.23975\n" +
                    "G1 X77.282 Y83.798 E6.31756\n" +
                    "G1 X79.638 Y84.588 E6.38460\n" +
                    "G1 X81.124 Y85.227 E6.42824\n" +
                    "G1 X82.503 Y85.935 E6.47004\n" +
                    "G1 X83.661 Y86.625 E6.50640\n" +
                    "G1 X84.477 Y87.191 E6.53320\n" +
                    "G1 X84.210 Y87.688 F7800.000\n" +
                    "G1 F3000\n" +
                    "G1 X85.005 Y88.366 E6.55473\n" +
                    "G1 X85.518 Y88.914 E6.57021\n" +
                    "G1 X85.981 Y89.519 E6.58591\n" +
                    "G1 X86.469 Y90.314 E6.60514\n" +
                    "G1 X87.027 Y91.458 E6.63136\n" +
                    "G1 X88.048 Y94.269 E6.69300\n" +
                    "G1 X89.059 Y97.839 E6.76948\n" +
                    "G1 X89.840 Y101.194 E6.84049\n" +
                    "G1 X90.355 Y104.154 E6.90240\n" +
                    "G1 X90.523 Y105.988 E6.94035\n" +
                    "G1 X90.528 Y107.160 E6.96451\n" +
                    "G1 X90.443 Y108.198 E6.98597\n" +
                    "G1 X90.273 Y109.146 E7.00584\n" +
                    "G1 X90.052 Y109.930 E7.02261\n" +
                    "G1 X89.765 Y110.655 E7.03869\n" +
                    "G1 X89.401 Y111.356 E7.05497\n" +
                    "G1 X88.987 Y111.994 E7.07064\n" +
                    "G1 X88.540 Y112.567 E7.08562\n" +
                    "G1 X88.080 Y113.062 E7.09955\n" +
                    "G1 X87.620 Y113.473 E7.11226\n" +
                    "G1 X87.079 Y113.863 E7.12602\n" +
                    "G1 X86.346 Y114.280 E7.14339\n" +
                    "G1 X84.992 Y114.869 E7.17383\n" +
                    "G1 X82.555 Y115.662 E7.22665\n" +
                    "G1 X78.486 Y116.569 E7.31257\n" +
                    "G1 X74.043 Y117.096 E7.40477\n" +
                    "G1 X68.860 Y117.276 E7.51166\n" +
                    "G1 X66.924 Y117.264 E7.55157\n" +
                    "G1 X60.468 Y117.011 E7.68473\n" +
                    "G1 X54.267 Y116.447 E7.81306\n" +
                    "G1 X50.040 Y115.789 E7.90124\n" +
                    "G1 X46.015 Y114.833 E7.98650\n" +
                    "G1 X43.543 Y114.052 E8.03992\n" +
                    "G1 X40.723 Y112.939 E8.10240\n" +
                    "G1 X40.018 Y112.607 E8.11848\n" +
                    "G1 X39.234 Y112.190 E8.13677\n" +
                    "G1 X38.616 Y111.796 E8.15189\n" +
                    "G1 X38.111 Y111.407 E8.16503\n" +
                    "G1 X37.584 Y110.895 E8.18016\n" +
                    "G1 X37.252 Y110.483 E8.19107\n" +
                    "G1 X36.956 Y110.028 E8.20226\n" +
                    "G1 X36.822 Y109.783 E8.20802\n" +
                    "G1 X36.541 Y109.170 E8.22192\n" +
                    "G1 X36.275 Y108.391 E8.23888\n" +
                    "G1 X36.013 Y107.233 E8.26334\n" +
                    "G1 X35.840 Y105.761 E8.29389\n" +
                    "G1 X35.807 Y103.902 E8.33220\n" +
                    "G1 X35.975 Y101.740 E8.37691\n" +
                    "G1 X36.520 Y98.636 E8.44186\n" +
                    "G1 X37.538 Y94.955 E8.52058\n" +
                    "G1 X38.860 Y91.442 E8.59793\n" +
                    "G1 X39.339 Y90.379 E8.62197\n" +
                    "G1 X40.359 Y88.406 E8.66774\n" +
                    "G1 X41.353 Y86.828 E8.70617\n" +
                    "G1 X41.756 Y86.276 E8.72026\n" +
                    "G1 X42.380 Y85.521 E8.74045\n" +
                    "G1 X42.992 Y84.896 E8.75849\n" +
                    "G1 X43.603 Y84.378 E8.77499\n" +
                    "G1 X43.977 Y84.107 E8.78451\n" +
                    "G1 X44.517 Y83.774 E8.79759\n" +
                    "G1 X45.229 Y83.422 E8.81395\n" +
                    "G1 X46.142 Y83.081 E8.83403\n" +
                    "G1 X47.522 Y82.723 E8.86342\n" +
                    "G1 X50.582 Y82.277 E8.92716\n" +
                    "G1 X55.197 Y82.019 E9.02242\n" +
                    "G1 X60.303 Y82.130 E9.12769\n" +
                    "G1 X67.319 Y82.665 E9.27271\n" +
                    "G1 X71.089 Y83.111 E9.35095\n" +
                    "G1 X74.366 Y83.669 E9.41946\n" +
                    "G1 X77.127 Y84.337 E9.47801\n" +
                    "G1 X79.438 Y85.112 E9.52825\n" +
                    "G1 X80.885 Y85.735 E9.56072\n" +
                    "G1 X82.231 Y86.425 E9.59190\n" +
                    "G1 X83.357 Y87.097 E9.61892\n" +
                    "G1 X84.149 Y87.645 E9.63876\n" +
                    "G1 X84.210 Y87.688 F7800.000\n" +
                    "G1 E7.63876 F2400.00000\n" +
                    "G92 E0\n" +
                    "G1 X46.194 Y116.105 F7800.000\n" +
                    "G1 E2.00000 F2400.00000\n" +
                    "G1 F3600\n" +
                    "G1 X39.810 Y114.781 E2.17588\n" +
                    "G1 X38.091 Y114.356 E2.22366\n" +
                    "G1 X32.443 Y112.710 E2.38236\n" +
                    "G1 X28.124 Y111.624 E2.50248\n" +
                    "G1 X28.203 Y110.504 E2.53277\n" +
                    "G1 X28.270 Y107.208 E2.62171\n" +
                    "G1 X28.260 Y104.577 E2.69269\n" +
                    "G1 X31.121 Y104.761 E2.77003\n" +
                    "G1 X31.566 Y104.774 E2.78204\n" +
                    "G1 X32.164 Y104.747 E2.79820\n" +
                    "G1 X32.686 Y104.641 E2.81257\n" +
                    "G1 X33.172 Y104.428 E2.82688\n" +
                    "G1 X33.540 Y104.162 E2.83911\n" +
                    "G1 X33.898 Y103.779 E2.85327\n" +
                    "G1 X34.301 Y103.110 E2.87434\n" +
                    "G1 X34.673 Y102.233 E2.90004\n" +
                    "G1 X34.756 Y101.992 E2.90690\n" +
                    "G1 X34.610 Y103.866 E2.95761\n" +
                    "G1 X34.645 Y105.842 E3.01091\n" +
                    "G1 X34.832 Y107.436 E3.05420\n" +
                    "G1 X35.123 Y108.717 E3.08964\n" +
                    "G1 X35.428 Y109.613 E3.11517\n" +
                    "G1 X35.752 Y110.321 E3.13618\n" +
                    "G1 X35.929 Y110.642 E3.14609\n" +
                    "G1 X36.282 Y111.186 E3.16357\n" +
                    "G1 X36.699 Y111.702 E3.18147\n" +
                    "G1 X37.326 Y112.312 E3.20506\n" +
                    "G1 X37.928 Y112.776 E3.22557\n" +
                    "G1 X38.631 Y113.224 E3.24805\n" +
                    "G1 X39.481 Y113.677 E3.27405\n" +
                    "G1 X40.249 Y114.038 E3.29693\n" +
                    "G1 X43.143 Y115.181 E3.38087\n" +
                    "G1 X45.696 Y115.987 E3.45309\n" +
                    "G1 X46.121 Y116.088 E3.46487\n" +
                    "G1 E1.46487 F2400.00000\n" +
                    "G92 E0";
                    salientemarcos[3]+="G1 X63.078 Y118.311 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X65.995 Y118.425 E2.07876\n" +
"G1 X64.661 Y118.425 E2.11476\n" +
"G1 X63.153 Y118.316 E2.15555\n" +
"G1 E0.15555 F2400.00000\n" +
"G92 E0\n" +
"G1 X134.005 Y118.425 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X136.922 Y118.311 E2.07876\n" +
"G1 X135.339 Y118.425 E2.12157\n" +
"G1 X134.080 Y118.425 E2.15555\n" +
"G1 E0.15555 F2400.00000\n" +
"G92 E0\n" +
"G1 X153.899 Y116.083 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X154.304 Y115.987 E2.01124\n" +
"G1 X156.857 Y115.181 E2.08346\n" +
"G1 X159.751 Y114.038 E2.16740\n" +
"G1 X160.519 Y113.677 E2.19028\n" +
"G1 X161.369 Y113.224 E2.21628\n" +
"G1 X162.072 Y112.776 E2.23876\n" +
"G1 X162.674 Y112.312 E2.25926\n" +
"G1 X163.301 Y111.702 E2.28286\n" +
"G1 X163.718 Y111.186 E2.30075\n" +
"G1 X164.071 Y110.642 E2.31824\n" +
"G1 X164.248 Y110.321 E2.32815\n" +
"G1 X164.572 Y109.613 E2.34916\n" +
"G1 X164.877 Y108.717 E2.37468\n" +
"G1 X165.168 Y107.436 E2.41013\n" +
"G1 X165.355 Y105.842 E2.45342\n" +
"G1 X165.390 Y103.866 E2.50672\n" +
"G1 X165.244 Y101.992 E2.55743\n" +
"G1 X165.327 Y102.233 E2.56429\n" +
"G1 X165.699 Y103.110 E2.58998\n" +
"G1 X166.102 Y103.779 E2.61106\n" +
"G1 X166.460 Y104.162 E2.62522\n" +
"G1 X166.828 Y104.428 E2.63745\n" +
"G1 X167.313 Y104.641 E2.65175\n" +
"G1 X167.838 Y104.748 E2.66619\n" +
"G1 X168.454 Y104.774 E2.68282\n" +
"G1 X171.740 Y104.577 E2.77163\n" +
"G1 X171.730 Y107.208 E2.84262\n" +
"G1 X171.797 Y110.504 E2.93156\n" +
"G1 X171.876 Y111.624 E2.96184\n" +
"G1 X167.557 Y112.710 E3.08196\n" +
"G1 X161.908 Y114.356 E3.24070\n" +
"G1 X160.359 Y114.742 E3.28376\n" +
"G1 X153.972 Y116.068 E3.45973\n" +
"G1 E1.45973 F2400.00000\n" +
"G92 E0\n" +
"G1 X172.546 Y112.097 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X172.271 Y112.180 E2.00776\n" +
"G1 X167.724 Y113.324 E2.13424\n" +
"G1 X162.074 Y114.970 E2.29300\n" +
"G1 X160.501 Y115.362 E2.33673\n" +
"G1 X152.283 Y117.067 E2.56315\n" +
"G1 X148.199 Y117.820 E2.67516\n" +
"G1 X143.363 Y118.481 E2.80684\n" +
"G1 X135.362 Y119.061 E3.02325\n" +
"G1 X131.564 Y119.059 E3.12571\n" +
"G1 X126.771 Y118.931 E3.25506\n" +
"G1 X122.026 Y118.333 E3.38405\n" +
"G1 X117.440 Y117.433 E3.51015\n" +
"G1 X114.967 Y116.804 E3.57898\n" +
"G1 X110.096 Y115.189 E3.71741\n" +
"G1 X107.224 Y114.310 E3.79844\n" +
"G1 X104.536 Y113.736 E3.87259\n" +
"G1 X102.406 Y113.443 E3.93058\n" +
"G1 X101.182 Y113.370 E3.96367\n" +
"G1 X98.818 Y113.370 E4.02744\n" +
"G1 X97.593 Y113.443 E4.06053\n" +
"G1 X95.372 Y113.751 E4.12104\n" +
"G1 X92.775 Y114.310 E4.19269\n" +
"G1 X89.904 Y115.189 E4.27370\n" +
"G1 X85.033 Y116.804 E4.41213\n" +
"G1 X82.560 Y117.433 E4.48095\n" +
"G1 X77.974 Y118.333 E4.60705\n" +
"G1 X73.229 Y118.931 E4.73605\n" +
"G1 X68.436 Y119.059 E4.86539\n" +
"G1 X64.638 Y119.061 E4.96785\n" +
"G1 X56.637 Y118.481 E5.18426\n" +
"G1 X51.801 Y117.820 E5.31594\n" +
"G1 X47.555 Y117.036 E5.43241\n" +
"G1 X39.669 Y115.400 E5.64968\n" +
"G1 X37.925 Y114.970 E5.69813\n" +
"G1 X32.276 Y113.324 E5.85686\n" +
"G1 X27.729 Y112.180 E5.98333\n" +
"G1 X27.454 Y112.097 E5.99111\n" +
"G1 X27.568 Y110.475 E6.03496\n" +
"G1 X27.634 Y107.203 E6.12327\n" +
"G1 X27.622 Y103.963 E6.21067\n" +
"G1 X28.288 Y103.940 E6.22864\n" +
"G1 X31.151 Y104.126 E6.30604\n" +
"G1 X31.562 Y104.138 E6.31712\n" +
"G1 X32.086 Y104.115 E6.33129\n" +
"G1 X32.493 Y104.032 E6.34249\n" +
"G1 X32.855 Y103.873 E6.35314\n" +
"G1 X33.117 Y103.683 E6.36189\n" +
"G1 X33.388 Y103.394 E6.37257\n" +
"G1 X33.733 Y102.820 E6.39064\n" +
"G1 X34.079 Y102.005 E6.41453\n" +
"G1 X34.420 Y101.015 E6.44278\n" +
"G1 X36.440 Y94.645 E6.62305\n" +
"G1 X37.380 Y92.050 E6.69749\n" +
"G1 X38.540 Y89.076 E6.78360\n" +
"G1 X39.315 Y87.347 E6.83473\n" +
"G1 X39.795 Y86.462 E6.86190\n" +
"G1 X40.350 Y85.618 E6.88915\n" +
"G1 X40.951 Y84.881 E6.91479\n" +
"G1 X41.673 Y84.161 E6.94230\n" +
"G1 X42.112 Y83.785 E6.95788\n" +
"G1 X43.081 Y83.078 E6.99024\n" +
"G1 X44.223 Y82.416 E7.02585\n" +
"G1 X45.083 Y82.039 E7.05118\n" +
"G1 X46.053 Y81.729 E7.07866\n" +
"G1 X47.208 Y81.476 E7.11056\n" +
"G1 X50.428 Y81.118 E7.19795\n" +
"G1 X55.899 Y80.944 E7.34563\n" +
"G1 X63.625 Y81.006 E7.55404\n" +
"G1 X67.957 Y81.223 E7.67107\n" +
"G1 X72.045 Y81.641 E7.78191\n" +
"G1 X75.906 Y82.283 E7.88749\n" +
"G1 X79.239 Y83.081 E7.97996\n" +
"G1 X79.796 Y83.250 E7.99567\n" +
"G1 X81.410 Y83.844 E8.04205\n" +
"G1 X82.882 Y84.568 E8.08631\n" +
"G1 X83.863 Y85.152 E8.11711\n" +
"G1 X84.772 Y85.781 E8.14690\n" +
"G1 X85.567 Y86.436 E8.17472\n" +
"G1 X86.189 Y87.050 E8.19828\n" +
"G1 X86.536 Y87.449 E8.21256\n" +
"G1 X87.075 Y88.190 E8.23727\n" +
"G1 X87.693 Y89.256 E8.27050\n" +
"G1 X88.286 Y90.528 E8.30838\n" +
"G1 X89.713 Y94.428 E8.42040\n" +
"G1 X91.768 Y101.202 E8.61137\n" +
"G1 X92.766 Y105.038 E8.71829\n" +
"G1 X93.139 Y106.331 E8.75459\n" +
"G1 X93.371 Y106.946 E8.77234\n" +
"G1 X93.605 Y107.445 E8.78721\n" +
"G1 X93.858 Y107.878 E8.80073\n" +
"G1 X94.266 Y108.411 E8.81882\n" +
"G1 X94.586 Y108.735 E8.83110\n" +
"G1 X94.952 Y109.039 E8.84395\n" +
"G1 X95.539 Y109.416 E8.86277\n" +
"G1 X96.155 Y109.708 E8.88117\n" +
"G1 X96.877 Y109.959 E8.90179\n" +
"G1 X97.720 Y110.153 E8.92510\n" +
"G1 X98.753 Y110.280 E8.95319\n" +
"G1 X100.057 Y110.303 E8.98837\n" +
"G1 X101.248 Y110.280 E9.02050\n" +
"G1 X102.280 Y110.153 E9.04857\n" +
"G1 X103.123 Y109.959 E9.07190\n" +
"G1 X103.887 Y109.693 E9.09371\n" +
"G1 X104.465 Y109.414 E9.11103\n" +
"G1 X105.067 Y109.023 E9.13040\n" +
"G1 X105.415 Y108.734 E9.14259\n" +
"G1 X105.734 Y108.411 E9.15486\n" +
"G1 X106.142 Y107.878 E9.17295\n" +
"G1 X106.395 Y107.445 E9.18647\n" +
"G1 X106.629 Y106.946 E9.20134\n" +
"G1 X106.861 Y106.331 E9.21909\n" +
"G1 X107.234 Y105.038 E9.25539\n" +
"G1 X108.232 Y101.202 E9.36231\n" +
"G1 X110.287 Y94.428 E9.55328\n" +
"G1 X111.713 Y90.529 E9.66528\n" +
"G1 X112.327 Y89.219 E9.70431\n" +
"G1 X112.925 Y88.190 E9.73639\n" +
"G1 X113.464 Y87.449 E9.76112\n" +
"G1 X113.810 Y87.050 E9.77538\n" +
"G1 X114.463 Y86.408 E9.80007\n" +
"G1 X115.228 Y85.781 E9.82675\n" +
"G1 X116.137 Y85.152 E9.85657\n" +
"G1 X117.118 Y84.568 E9.88737\n" +
"G1 X118.590 Y83.844 E9.93162\n" +
"G1 X120.204 Y83.250 E9.97801\n" +
"G1 X120.760 Y83.081 E9.99371\n" +
"G1 X124.168 Y82.268 E10.08820\n" +
"G1 X127.955 Y81.641 E10.19176\n" +
"G1 X132.043 Y81.222 E10.30261\n" +
"G1 X136.240 Y81.011 E10.41597\n" +
"G1 X144.101 Y80.944 E10.62805\n" +
"G1 X149.572 Y81.118 E10.77573\n" +
"G1 X152.792 Y81.476 E10.86312\n" +
"G1 X153.947 Y81.729 E10.89502\n" +
"G1 X154.918 Y82.040 E10.92252\n" +
"G1 X155.752 Y82.403 E10.94706\n" +
"G1 X156.904 Y83.067 E10.98292\n" +
"G1 X157.888 Y83.785 E11.01580\n" +
"G1 X158.327 Y84.162 E11.03140\n" +
"G1 X159.028 Y84.858 E11.05805\n" +
"G1 X159.650 Y85.618 E11.08455\n" +
"G1 X160.204 Y86.461 E11.11175\n" +
"G1 X160.715 Y87.410 E11.14084\n" +
"G1 X161.520 Y89.215 E11.19415\n" +
"G1 X162.620 Y92.050 E11.27617\n" +
"G1 X163.560 Y94.645 E11.35063\n" +
"G1 X165.580 Y101.015 E11.53090\n" +
"G1 X165.921 Y102.005 E11.55916\n" +
"G1 X166.267 Y102.820 E11.58304\n" +
"G1 X166.612 Y103.394 E11.60111\n" +
"G1 X166.883 Y103.683 E11.61179\n" +
"G1 X167.145 Y103.873 E11.62054\n" +
"G1 X167.507 Y104.032 E11.63119\n" +
"G1 X167.915 Y104.115 E11.64243\n" +
"G1 X168.457 Y104.138 E11.65707\n" +
"G1 X168.849 Y104.126 E11.66764\n" +
"G1 X171.712 Y103.940 E11.74504\n" +
"G1 X172.378 Y103.963 E11.76301\n" +
"G1 X172.366 Y107.203 E11.85042\n" +
"G1 X172.432 Y110.475 E11.93872\n" +
"G1 X172.541 Y112.022 E11.98056\n" +
"G1 X173.013 Y112.511 F7800.000\n" +
"G1 F3000\n" +
"G1 X172.852 Y112.591 E11.98426\n" +
"G1 X172.420 Y112.721 E11.99356\n" +
"G1 X167.871 Y113.865 E12.09024\n" +
"G1 X162.220 Y115.511 E12.21155\n" +
"G1 X160.626 Y115.909 E12.24542\n" +
"G1 X152.390 Y117.618 E12.41876\n" +
"G1 X148.288 Y118.374 E12.50473\n" +
"G1 X143.421 Y119.038 E12.60598\n" +
"G1 X135.382 Y119.622 E12.77210\n" +
"G1 X131.556 Y119.619 E12.85096\n" +
"G1 X126.728 Y119.491 E12.95050\n" +
"G1 X121.937 Y118.887 E13.05002\n" +
"G1 X117.316 Y117.980 E13.14707\n" +
"G1 X114.810 Y117.342 E13.20039\n" +
"G1 X109.926 Y115.723 E13.30642\n" +
"G1 X107.083 Y114.853 E13.36770\n" +
"G1 X104.439 Y114.289 E13.42342\n" +
"G1 X102.351 Y114.001 E13.46686\n" +
"G1 X101.165 Y113.931 E13.49134\n" +
"G1 X98.835 Y113.931 E13.53938\n" +
"G1 X97.649 Y114.001 E13.56387\n" +
"G1 X95.469 Y114.304 E13.60922\n" +
"G1 X92.917 Y114.853 E13.66303\n" +
"G1 X90.074 Y115.723 E13.72430\n" +
"G1 X85.190 Y117.342 E13.83034\n" +
"G1 X82.684 Y117.980 E13.88365\n" +
"G1 X78.063 Y118.887 E13.98071\n" +
"G1 X73.272 Y119.491 E14.08023\n" +
"G1 X68.444 Y119.619 E14.17977\n" +
"G1 X64.618 Y119.622 E14.25863\n" +
"G1 X56.579 Y119.038 E14.42475\n" +
"G1 X51.712 Y118.374 E14.52599\n" +
"G1 X47.447 Y117.587 E14.61537\n" +
"G1 X39.545 Y115.947 E14.78171\n" +
"G1 X37.780 Y115.511 E14.81918\n" +
"G1 X32.129 Y113.865 E14.94048\n" +
"G1 X27.580 Y112.721 E15.03716\n" +
"G1 X27.148 Y112.590 E15.04647\n" +
"G1 X26.993 Y112.514 E15.05004\n" +
"G1 X26.897 Y112.420 E15.05279\n" +
"G1 X26.876 Y112.321 E15.05488\n" +
"G1 X27.008 Y110.450 E15.09354\n" +
"G1 X27.074 Y107.198 E15.16058\n" +
"G1 X27.061 Y103.924 E15.22806\n" +
"G1 X27.088 Y103.653 E15.23368\n" +
"G1 X27.137 Y103.544 E15.23614\n" +
"G1 X27.256 Y103.462 E15.23913\n" +
"G1 X27.341 Y103.436 E15.24095\n" +
"G1 X27.644 Y103.394 E15.24726\n" +
"G1 X28.300 Y103.379 E15.26078\n" +
"G1 X31.178 Y103.565 E15.32021\n" +
"G1 X31.558 Y103.577 E15.32805\n" +
"G1 X32.017 Y103.557 E15.33753\n" +
"G1 X32.322 Y103.495 E15.34395\n" +
"G1 X32.575 Y103.384 E15.34962\n" +
"G1 X32.745 Y103.261 E15.35396\n" +
"G1 X32.938 Y103.054 E15.35978\n" +
"G1 X33.233 Y102.565 E15.37155\n" +
"G1 X33.555 Y101.804 E15.38859\n" +
"G1 X33.887 Y100.839 E15.40963\n" +
"G1 X35.909 Y94.464 E15.54745\n" +
"G1 X36.855 Y91.853 E15.60470\n" +
"G1 X38.023 Y88.860 E15.67091\n" +
"G1 X38.812 Y87.098 E15.71070\n" +
"G1 X39.314 Y86.174 E15.73238\n" +
"G1 X39.897 Y85.285 E15.75428\n" +
"G1 X40.535 Y84.505 E15.77506\n" +
"G1 X41.292 Y83.750 E15.79710\n" +
"G1 X41.763 Y83.345 E15.80990\n" +
"G1 X42.774 Y82.608 E15.83568\n" +
"G1 X43.969 Y81.915 E15.86415\n" +
"G1 X44.884 Y81.514 E15.88474\n" +
"G1 X45.907 Y81.187 E15.90688\n" +
"G1 X47.117 Y80.922 E15.93240\n" +
"G1 X50.388 Y80.559 E16.00023\n" +
"G1 X55.893 Y80.384 E16.11375\n" +
"G1 X63.641 Y80.446 E16.27345\n" +
"G1 X68.000 Y80.663 E16.36340\n" +
"G1 X72.120 Y81.086 E16.44875\n" +
"G1 X76.017 Y81.734 E16.53018\n" +
"G1 X79.386 Y82.539 E16.60158\n" +
"G1 X79.975 Y82.718 E16.61426\n" +
"G1 X81.631 Y83.328 E16.65064\n" +
"G1 X83.150 Y84.075 E16.68551\n" +
"G1 X84.167 Y84.680 E16.70991\n" +
"G1 X85.110 Y85.333 E16.73355\n" +
"G1 X85.943 Y86.019 E16.75580\n" +
"G1 X86.598 Y86.666 E16.77477\n" +
"G1 X86.975 Y87.100 E16.78662\n" +
"G1 X87.546 Y87.884 E16.80659\n" +
"G1 X88.190 Y88.996 E16.83309\n" +
"G1 X88.805 Y90.313 E16.86305\n" +
"G1 X90.245 Y94.250 E16.94945\n" +
"G1 X92.308 Y101.050 E17.09591\n" +
"G1 X93.306 Y104.890 E17.17767\n" +
"G1 X93.672 Y106.154 E17.20480\n" +
"G1 X93.888 Y106.728 E17.21745\n" +
"G1 X94.102 Y107.185 E17.22784\n" +
"G1 X94.324 Y107.565 E17.23692\n" +
"G1 X94.689 Y108.042 E17.24929\n" +
"G1 X94.965 Y108.321 E17.25738\n" +
"G1 X95.284 Y108.585 E17.26592\n" +
"G1 X95.812 Y108.925 E17.27885\n" +
"G1 X96.368 Y109.189 E17.29154\n" +
"G1 X97.032 Y109.420 E17.30604\n" +
"G1 X97.817 Y109.600 E17.32262\n" +
"G1 X98.792 Y109.720 E17.34288\n" +
"G1 X100.056 Y109.742 E17.36894\n" +
"G1 X101.208 Y109.720 E17.39268\n" +
"G1 X102.183 Y109.600 E17.41293\n" +
"G1 X102.968 Y109.420 E17.42952\n" +
"G1 X103.672 Y109.174 E17.44489\n" +
"G1 X104.190 Y108.924 E17.45674\n" +
"G1 X104.734 Y108.571 E17.47012\n" +
"G1 X105.035 Y108.321 E17.47818\n" +
"G1 X105.311 Y108.042 E17.48627\n" +
"G1 X105.676 Y107.565 E17.49864\n" +
"G1 X105.898 Y107.185 E17.50772\n" +
"G1 X106.112 Y106.728 E17.51811\n" +
"G1 X106.328 Y106.154 E17.53076\n" +
"G1 X106.694 Y104.890 E17.55788\n" +
"G1 X107.692 Y101.050 E17.63965\n" +
"G1 X109.755 Y94.250 E17.78610\n" +
"G1 X111.195 Y90.313 E17.87251\n" +
"G1 X111.830 Y88.958 E17.90334\n" +
"G1 X112.454 Y87.884 E17.92896\n" +
"G1 X113.025 Y87.100 E17.94894\n" +
"G1 X113.401 Y86.666 E17.96078\n" +
"G1 X114.088 Y85.991 E17.98062\n" +
"G1 X114.890 Y85.333 E18.00200\n" +
"G1 X115.833 Y84.680 E18.02565\n" +
"G1 X116.850 Y84.075 E18.05004\n" +
"G1 X118.369 Y83.328 E18.08492\n" +
"G1 X120.025 Y82.718 E18.12129\n" +
"G1 X120.614 Y82.539 E18.13398\n" +
"G1 X124.057 Y81.718 E18.20692\n" +
"G1 X127.880 Y81.086 E18.28680\n" +
"G1 X132.000 Y80.663 E18.37216\n" +
"G1 X136.223 Y80.450 E18.45930\n" +
"G1 X144.107 Y80.384 E18.62180\n" +
"G1 X149.612 Y80.559 E18.73533\n" +
"G1 X152.883 Y80.922 E18.80315\n" +
"G1 X154.093 Y81.187 E18.82867\n" +
"G1 X155.116 Y81.514 E18.85082\n" +
"G1 X156.002 Y81.900 E18.87074\n" +
"G1 X157.221 Y82.604 E18.89974\n" +
"G1 X158.237 Y83.345 E18.92566\n" +
"G1 X158.708 Y83.749 E18.93845\n" +
"G1 X159.444 Y84.481 E18.95984\n" +
"G1 X160.103 Y85.286 E18.98128\n" +
"G1 X160.686 Y86.174 E19.00317\n" +
"G1 X161.219 Y87.162 E19.02632\n" +
"G1 X162.038 Y89.000 E19.06778\n" +
"G1 X163.145 Y91.853 E19.13086\n" +
"G1 X164.091 Y94.464 E19.18811\n" +
"G1 X166.113 Y100.839 E19.32593\n" +
"G1 X166.445 Y101.804 E19.34697\n" +
"G1 X166.767 Y102.565 E19.36401\n" +
"G1 X167.062 Y103.054 E19.37578\n" +
"G1 X167.255 Y103.261 E19.38160\n" +
"G1 X167.425 Y103.384 E19.38594\n" +
"G1 X167.678 Y103.495 E19.39161\n" +
"G1 X167.983 Y103.557 E19.39804\n" +
"G1 X168.460 Y103.577 E19.40789\n" +
"G1 X168.822 Y103.566 E19.41534\n" +
"G1 X171.700 Y103.379 E19.47478\n" +
"G1 X172.356 Y103.394 E19.48831\n" +
"G1 X172.651 Y103.435 E19.49444\n" +
"G1 X172.750 Y103.465 E19.49657\n" +
"G1 X172.858 Y103.537 E19.49926\n" +
"G1 X172.913 Y103.654 E19.50192\n" +
"G1 X172.939 Y103.924 E19.50750\n" +
"G1 X172.926 Y107.198 E19.57498\n" +
"G1 X172.992 Y110.450 E19.64202\n" +
"G1 X173.124 Y112.318 E19.68060\n" +
"G1 X173.095 Y112.434 E19.68309\n" +
"G1 X173.068 Y112.460 E19.68385\n" +
"G1 X172.847 Y112.442 F7800.000\n" +
"G1 E17.68385 F2400.00000\n" +
"G92 E0\n" +
"G1 X170.957 Y111.385 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X171.452 Y110.890 E2.01892\n" +
"G1 X171.404 Y110.037 E2.04204\n" +
"G1 X169.753 Y111.688 E2.10518\n" +
"G1 X168.549 Y111.990 E2.13875\n" +
"G1 X171.386 Y109.153 E2.24725\n" +
"G1 X171.368 Y108.270 E2.27114\n" +
"G1 X167.345 Y112.293 E2.42500\n" +
"G1 X166.085 Y112.652 E2.46044\n" +
"G1 X171.350 Y107.387 E2.66182\n" +
"G1 X171.343 Y106.493 E2.68601\n" +
"G1 X164.813 Y113.023 E2.93577\n" +
"G1 X163.542 Y113.393 E2.97159\n" +
"G1 X171.347 Y105.588 E3.27011\n" +
"G1 X171.349 Y105.004 E3.28589\n" +
"G1 X171.009 Y105.025 E3.29511\n" +
"G1 X162.270 Y113.764 E3.62934\n" +
"G1 X161.488 Y113.991 E3.65136\n" +
"G1 X162.191 Y113.544 E3.67386\n" +
"G1 X162.844 Y113.040 E3.69619\n" +
"G1 X163.525 Y112.379 E3.72186\n" +
"G1 X163.986 Y111.807 E3.74172\n" +
"G1 X164.369 Y111.216 E3.76077\n" +
"G1 X164.569 Y110.854 E3.77196\n" +
"G1 X164.813 Y110.319 E3.78786\n" +
"G1 X170.050 Y105.082 E3.98815\n" +
"G1 X169.092 Y105.140 E4.01412\n" +
"G1 X165.286 Y108.945 E4.15967\n" +
"G1 X165.549 Y107.782 E4.19193\n" +
"G1 X168.189 Y105.141 E4.29291\n" +
"G1 X168.008 Y105.134 E4.29781\n" +
"G1 X167.416 Y105.013 E4.31415\n" +
"G1 X165.669 Y106.760 E4.38096\n" +
"G1 X165.743 Y106.132 E4.39807\n" +
"G1 X165.749 Y105.779 E4.40762\n" +
"G1 X166.782 Y104.746 E4.44711\n" +
"G1 X166.260 Y104.367 E4.46456\n" +
"G1 X165.765 Y104.861 E4.48347\n" +
"G1 E2.48347 F2400.00000\n" +
"G92 E0\n" +
"G1 X109.940 Y114.135 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X110.434 Y113.640 E2.01941\n" +
"G1 X110.017 Y113.132 E2.03766\n" +
"G1 X109.231 Y113.918 E2.06851\n" +
"G1 X108.523 Y113.701 E2.08907\n" +
"G1 X109.628 Y112.597 E2.13243\n" +
"G1 X109.264 Y112.035 E2.15100\n" +
"G1 X107.815 Y113.484 E2.20789\n" +
"G1 X107.296 Y113.326 E2.22295\n" +
"G1 X107.092 Y113.282 E2.22874\n" +
"G1 X108.948 Y111.427 E2.30157\n" +
"G1 X108.813 Y111.169 E2.30964\n" +
"G1 X108.663 Y110.787 E2.32104\n" +
"G1 X106.330 Y113.119 E2.41261\n" +
"G1 X105.568 Y112.957 E2.43425\n" +
"G1 X108.413 Y110.111 E2.54596\n" +
"G1 X108.209 Y109.390 E2.56675\n" +
"G1 X104.806 Y112.794 E2.70037\n" +
"G1 X104.509 Y112.730 E2.70880\n" +
"G1 X104.012 Y112.662 E2.72271\n" +
"G1 X108.062 Y108.613 E2.88168\n" +
"G1 X107.986 Y108.187 E2.89367\n" +
"G1 X107.953 Y107.796 E2.90457\n" +
"G1 X107.007 Y108.742 E2.94173\n" +
"G1 X107.274 Y108.284 E2.95647\n" +
"G1 X107.431 Y107.950 E2.96671\n" +
"G1 X107.459 Y108.290 E2.97620\n" +
"G1 E0.97620 F2400.00000\n" +
"G92 E0\n" +
"G1 X103.199 Y112.550 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X105.724 Y110.026 E2.09910\n" +
"G1 X105.337 Y110.277 E2.11191\n" +
"G1 X104.621 Y110.622 E2.13396\n" +
"G1 X103.978 Y110.847 E2.15287\n" +
"G1 X102.386 Y112.438 E2.21534\n" +
"G1 X101.521 Y112.379 E2.23943\n" +
"G1 X102.739 Y111.160 E2.28727\n" +
"G1 X101.684 Y111.290 E2.31678\n" +
"G1 X100.628 Y112.346 E2.35825\n" +
"G1 X99.703 Y112.346 E2.38393\n" +
"G1 X100.730 Y111.319 E2.42427\n" +
"G1 X99.806 Y111.318 E2.44993\n" +
"G1 X98.778 Y112.346 E2.49029\n" +
"G1 X97.810 Y112.389 E2.51718\n" +
"G1 X98.898 Y111.301 E2.55989\n" +
"G1 X98.075 Y111.200 E2.58292\n" +
"G1 X96.791 Y112.484 E2.63333\n" +
"G1 X95.717 Y112.633 E2.66343\n" +
"G1 X97.294 Y111.055 E2.72535\n" +
"G1 X96.773 Y110.936 E2.74021\n" +
"G1 X96.562 Y110.863 E2.74640\n" +
"G1 X94.617 Y112.807 E2.82274\n" +
"G1 X93.438 Y113.061 E2.85622\n" +
"G1 X95.879 Y110.620 E2.95205\n" +
"G1 X95.252 Y110.323 E2.97133\n" +
"G1 X92.259 Y113.315 E3.08881\n" +
"G1 X90.933 Y113.716 E3.12728\n" +
"G1 X94.680 Y109.969 E3.27439\n" +
"G1 X94.447 Y109.819 E3.28210\n" +
"G1 X94.151 Y109.573 E3.29277\n" +
"G1 X89.600 Y114.124 E3.47144\n" +
"G1 X89.566 Y114.135 E3.47243\n" +
"G1 X89.688 Y114.003 E3.47741\n" +
"G1 X90.233 Y113.306 E3.50199\n" +
"G1 X90.739 Y112.525 E3.52780\n" +
"G1 X91.219 Y111.580 E3.55724\n" +
"G1 X93.675 Y109.124 E3.65365\n" +
"G1 X93.552 Y109.000 E3.65850\n" +
"G1 X93.258 Y108.616 E3.67192\n" +
"G1 X91.712 Y110.162 E3.73261\n" +
"G1 X91.813 Y109.806 E3.74289\n" +
"G1 X91.959 Y108.990 E3.76589\n" +
"G1 X92.886 Y108.064 E3.80225\n" +
"G1 X92.726 Y107.789 E3.81107\n" +
"G1 X92.569 Y107.455 E3.82131\n" +
"G1 X92.075 Y107.950 E3.84072\n" +
"G1 E1.84072 F2400.00000\n" +
"G92 E0\n" +
"G1 X38.017 Y113.991 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X38.511 Y113.497 E2.01972\n" +
"G1 X37.937 Y113.131 E2.03892\n" +
"G1 X37.289 Y113.779 E2.06477\n" +
"G1 X36.561 Y113.567 E2.08614\n" +
"G1 X37.397 Y112.732 E2.11947\n" +
"G1 X37.156 Y112.546 E2.12804\n" +
"G1 X36.896 Y112.293 E2.13828\n" +
"G1 X35.833 Y113.355 E2.18065\n" +
"G1 X35.106 Y113.143 E2.20202\n" +
"G1 X36.426 Y111.823 E2.25468\n" +
"G1 X36.007 Y111.302 E2.27353\n" +
"G1 X34.378 Y112.931 E2.33850\n" +
"G1 X33.650 Y112.719 E2.35988\n" +
"G1 X35.637 Y110.732 E2.43913\n" +
"G1 X35.431 Y110.359 E2.45114\n" +
"G1 X35.318 Y110.112 E2.45882\n" +
"G1 X32.923 Y112.507 E2.55435\n" +
"G1 X32.190 Y112.300 E2.57583\n" +
"G1 X35.035 Y109.455 E2.68930\n" +
"G1 X34.796 Y108.755 E2.71018\n" +
"G1 X31.439 Y112.112 E2.84406\n" +
"G1 X30.688 Y111.923 E2.86590\n" +
"G1 X34.612 Y107.999 E3.02240\n" +
"G1 X34.444 Y107.227 E3.04468\n" +
"G1 X29.937 Y111.734 E3.22445\n" +
"G1 X29.186 Y111.545 E3.24629\n" +
"G1 X34.345 Y106.386 E3.45205\n" +
"G1 X34.255 Y105.536 E3.47615\n" +
"G1 X28.559 Y111.232 E3.70333\n" +
"G1 X28.602 Y110.250 E3.73106\n" +
"G1 X34.239 Y104.613 E3.95589\n" +
"G1 X34.235 Y104.367 E3.96283\n" +
"G1 X33.736 Y104.735 E3.98030\n" +
"G1 X33.127 Y105.003 E3.99908\n" +
"G1 X32.853 Y105.059 E4.00697\n" +
"G1 X28.621 Y109.291 E4.17574\n" +
"G1 X28.641 Y108.331 E4.20280\n" +
"G1 X31.809 Y105.163 E4.32918\n" +
"G1 X30.911 Y105.121 E4.35453\n" +
"G1 X28.658 Y107.374 E4.44440\n" +
"G1 X28.654 Y106.438 E4.47080\n" +
"G1 X30.028 Y105.064 E4.52560\n" +
"G1 X29.145 Y105.007 E4.55056\n" +
"G1 X28.651 Y105.502 E4.57027\n" +
"M106 S140.25\n" +
"G1 Z1.250 F7800.000\n" +
"G1 E2.57027 F2400.00000\n" +
"G92 E0\n" +
"G1 X91.346 Y107.717 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X91.199 Y108.970 E2.02601\n" +
"G1 X90.973 Y110.012 E2.04798\n" +
"G1 X90.718 Y110.806 E2.06516\n" +
"G1 X90.427 Y111.476 E2.08021\n" +
"G1 X90.051 Y112.148 E2.09609\n" +
"G1 X89.587 Y112.810 E2.11274\n" +
"G1 X89.047 Y113.428 E2.12968\n" +
"G1 X88.461 Y113.971 E2.14614\n" +
"G1 X87.800 Y114.471 E2.16321\n" +
"G1 X87.068 Y114.917 E2.18088\n" +
"G1 X86.280 Y115.293 E2.19888\n" +
"G1 X85.032 Y115.745 E2.22623\n" +
"G1 X80.665 Y116.964 E2.31968\n" +
"G1 X77.701 Y117.539 E2.38191\n" +
"G1 X72.852 Y117.978 E2.48225\n" +
"G1 X65.192 Y118.022 E2.64014\n" +
"G1 X58.856 Y117.831 E2.77078\n" +
"G1 X53.006 Y117.141 E2.89219\n" +
"G1 X47.442 Y115.985 E3.00932\n" +
"G1 X42.598 Y114.571 E3.11332\n" +
"G1 X40.034 Y113.538 E3.17029\n" +
"G1 X38.314 Y112.682 E3.20989\n" +
"G1 X37.866 Y112.424 E3.22055\n" +
"G1 X37.530 Y112.194 E3.22893\n" +
"G1 X37.252 Y111.959 E3.23644\n" +
"G1 X37.042 Y111.741 E3.24267\n" +
"G1 X36.847 Y111.495 E3.24915\n" +
"G1 X36.582 Y111.086 E3.25919\n" +
"G1 X35.868 Y109.682 E3.29166\n" +
"G1 X35.546 Y108.868 E3.30970\n" +
"G1 X35.336 Y108.128 E3.32554\n" +
"G1 X35.178 Y107.229 E3.34436\n" +
"G1 X35.140 Y106.896 E3.35126\n" +
"G1 X35.066 Y105.248 E3.38526\n" +
"G1 X35.215 Y102.489 E3.44222\n" +
"G1 X35.819 Y98.515 E3.52506\n" +
"G1 X36.719 Y95.008 E3.59967\n" +
"G1 X38.086 Y91.320 E3.68074\n" +
"G1 X40.191 Y86.974 E3.78027\n" +
"G1 X40.531 Y86.392 E3.79416\n" +
"G1 X40.814 Y86.006 E3.80402\n" +
"G1 X41.875 Y84.845 E3.83645\n" +
"G1 X42.743 Y83.992 E3.86152\n" +
"G1 X43.215 Y83.594 E3.87424\n" +
"G1 X43.668 Y83.280 E3.88561\n" +
"G1 X44.234 Y82.970 E3.89892\n" +
"G1 X45.039 Y82.634 E3.91690\n" +
"G1 X46.455 Y82.203 E3.94740\n" +
"G1 X48.823 Y81.725 E3.99719\n" +
"G1 X51.778 Y81.432 E4.05839\n" +
"G1 X58.379 Y81.379 E4.19445\n" +
"G1 X65.747 Y81.671 E4.34642\n" +
"G1 X71.410 Y82.412 E4.46412\n" +
"G1 X78.422 Y84.004 E4.61233\n" +
"G1 X80.448 Y84.639 E4.65609\n" +
"G1 X81.761 Y85.157 E4.68518\n" +
"G1 X82.643 Y85.603 E4.70555\n" +
"G1 X83.459 Y86.122 E4.72548\n" +
"G1 X84.601 Y86.999 E4.75515\n" +
"G1 X85.342 Y87.686 E4.77598\n" +
"G1 X85.965 Y88.388 E4.79531\n" +
"G1 X86.544 Y89.190 E4.81570\n" +
"G1 X87.053 Y90.069 E4.83664\n" +
"G1 X87.842 Y91.850 E4.87680\n" +
"G1 X89.413 Y96.079 E4.96977\n" +
"G1 X90.822 Y101.587 E5.08696\n" +
"G1 X91.243 Y104.292 E5.14337\n" +
"G1 X91.377 Y106.309 E5.18504\n" +
"G1 X91.348 Y107.642 E5.21251\n" +
"G1 X91.346 Y107.717 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X47.586 Y81.403 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X46.318 Y81.659 E2.03490\n" +
"G1 X44.922 Y82.084 E2.07426\n" +
"G1 X45.059 Y82.025 E2.07827\n" +
"G1 X46.033 Y81.711 E2.10588\n" +
"G1 X47.196 Y81.455 E2.13800\n" +
"G1 X47.512 Y81.413 E2.14660\n" +
"G1 E0.14660 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.365 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X41.471 Y84.456 E2.03381\n" +
"G1 X40.380 Y85.651 E2.07747\n" +
"G1 X40.062 Y86.084 E2.09198\n" +
"G1 X39.696 Y86.710 E2.11153\n" +
"G1 X38.360 Y89.468 E2.19419\n" +
"G1 X39.017 Y87.913 E2.23971\n" +
"G1 X39.308 Y87.311 E2.25775\n" +
"G1 X39.772 Y86.457 E2.28397\n" +
"G1 X40.202 Y85.785 E2.30551\n" +
"G1 X40.759 Y85.065 E2.33006\n" +
"G1 X41.401 Y84.384 E2.35529\n" +
"G1 X42.105 Y83.762 E2.38064\n" +
"G1 X42.388 Y83.555 E2.39010\n" +
"G1 E0.39010 F2400.00000\n" +
"G92 E0\n" +
"G1 X159.810 Y114.890 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.457 Y114.630 E2.01879\n" +
"G1 X162.252 Y113.737 E2.07288\n" +
"G1 X162.772 Y113.437 E2.08909\n" +
"G1 X163.195 Y113.147 E2.10292\n" +
"G1 X163.567 Y112.833 E2.11605\n" +
"G1 X163.860 Y112.528 E2.12745\n" +
"G1 X164.126 Y112.193 E2.13899\n" +
"G1 X164.456 Y111.684 E2.15536\n" +
"G1 X165.224 Y110.174 E2.20106\n" +
"G1 X165.588 Y109.252 E2.22781\n" +
"G1 X165.831 Y108.396 E2.25180\n" +
"G1 X166.007 Y107.400 E2.27909\n" +
"G1 X166.053 Y106.990 E2.29022\n" +
"G1 X166.131 Y105.243 E2.33741\n" +
"G1 X166.045 Y103.642 E2.38065\n" +
"G1 X166.115 Y103.758 E2.38430\n" +
"G1 X166.511 Y104.184 E2.39999\n" +
"G1 X167.052 Y104.522 E2.41720\n" +
"G1 X167.574 Y104.684 E2.43195\n" +
"G1 X168.131 Y104.748 E2.44706\n" +
"G1 X168.844 Y104.740 E2.46630\n" +
"G1 X171.761 Y104.557 E2.54515\n" +
"G1 X171.752 Y107.121 E2.61434\n" +
"G1 X171.820 Y110.530 E2.70630\n" +
"G1 X171.903 Y111.641 E2.73636\n" +
"G1 X167.459 Y112.763 E2.85998\n" +
"G1 X162.091 Y114.327 E3.01083\n" +
"G1 X159.884 Y114.874 E3.07217\n" +
"G1 E1.07217 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.565 Y114.699 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.362 Y113.712 E2.09041\n" +
"G1 X104.701 Y113.142 E2.16382\n" +
"G1 X103.712 Y112.983 E2.19086\n" +
"G1 X102.516 Y112.839 E2.22336\n" +
"G1 X101.221 Y112.758 E2.25836\n" +
"G1 X98.860 Y112.758 E2.32203\n" +
"G1 X97.590 Y112.828 E2.35635\n" +
"G1 X96.286 Y112.983 E2.39178\n" +
"G1 X95.135 Y113.174 E2.42324\n" +
"G1 X92.637 Y113.712 E2.49219\n" +
"G1 X89.435 Y114.699 E2.58258\n" +
"G1 X89.906 Y114.263 E2.59989\n" +
"G1 X90.530 Y113.549 E2.62548\n" +
"G1 X91.066 Y112.785 E2.65065\n" +
"G1 X91.501 Y112.007 E2.67469\n" +
"G1 X91.839 Y111.227 E2.69761\n" +
"G1 X92.129 Y110.322 E2.72326\n" +
"G1 X92.380 Y109.167 E2.75514\n" +
"G1 X92.541 Y107.800 E2.79228\n" +
"G1 X92.568 Y106.545 E2.82615\n" +
"G1 X92.809 Y107.193 E2.84482\n" +
"G1 X93.061 Y107.729 E2.86078\n" +
"G1 X93.339 Y108.205 E2.87566\n" +
"G1 X93.603 Y108.574 E2.88791\n" +
"G1 X93.959 Y108.983 E2.90254\n" +
"G1 X94.579 Y109.528 E2.92481\n" +
"G1 X95.230 Y109.947 E2.94569\n" +
"G1 X95.788 Y110.221 E2.96244\n" +
"G1 X96.398 Y110.453 E2.98005\n" +
"G1 X97.115 Y110.655 E3.00016\n" +
"G1 X97.951 Y110.811 E3.02309\n" +
"G1 X98.736 Y110.894 E3.04440\n" +
"G1 X100.114 Y110.916 E3.08157\n" +
"G1 X101.266 Y110.894 E3.11266\n" +
"G1 X102.049 Y110.811 E3.13390\n" +
"G1 X102.872 Y110.658 E3.15648\n" +
"G1 X103.331 Y110.537 E3.16928\n" +
"G1 X104.127 Y110.258 E3.19202\n" +
"G1 X104.753 Y109.956 E3.21080\n" +
"G1 X105.246 Y109.651 E3.22643\n" +
"G1 X105.690 Y109.312 E3.24149\n" +
"G1 X106.203 Y108.810 E3.26086\n" +
"G1 X106.655 Y108.216 E3.28099\n" +
"G1 X106.940 Y107.728 E3.29624\n" +
"G1 X107.191 Y107.193 E3.31217\n" +
"G1 X107.432 Y106.545 E3.33084\n" +
"G1 X107.459 Y107.800 E3.36471\n" +
"G1 X107.620 Y109.167 E3.40185\n" +
"G1 X107.871 Y110.322 E3.43374\n" +
"G1 X108.161 Y111.227 E3.45938\n" +
"G1 X108.499 Y112.007 E3.48231\n" +
"G1 X108.934 Y112.785 E3.50635\n" +
"G1 X109.470 Y113.549 E3.53151\n" +
"G1 X110.094 Y114.263 E3.55710\n" +
"G1 X110.510 Y114.648 E3.57240\n" +
"G1 E1.57240 F2400.00000\n" +
"G92 E0\n" +
"G1 X40.190 Y114.890 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X37.909 Y114.327 E2.06336\n" +
"G1 X32.541 Y112.763 E2.21421\n" +
"G1 X28.097 Y111.641 E2.33784\n" +
"G1 X28.180 Y110.530 E2.36789\n" +
"G1 X28.248 Y107.122 E2.45985\n" +
"G1 X28.239 Y104.554 E2.52910\n" +
"G1 X31.142 Y104.739 E2.60757\n" +
"G1 X31.548 Y104.751 E2.61853\n" +
"G1 X32.144 Y104.727 E2.63463\n" +
"G1 X32.667 Y104.624 E2.64899\n" +
"G1 X33.152 Y104.414 E2.66324\n" +
"G1 X33.528 Y104.143 E2.67576\n" +
"G1 X33.879 Y103.766 E2.68965\n" +
"G1 X33.955 Y103.641 E2.69361\n" +
"G1 X33.869 Y105.243 E2.73689\n" +
"G1 X33.947 Y106.990 E2.78407\n" +
"G1 X33.993 Y107.400 E2.79520\n" +
"G1 X34.169 Y108.396 E2.82250\n" +
"G1 X34.412 Y109.252 E2.84649\n" +
"G1 X34.776 Y110.174 E2.87323\n" +
"G1 X35.544 Y111.684 E2.91894\n" +
"G1 X35.874 Y112.193 E2.93531\n" +
"G1 X36.140 Y112.528 E2.94684\n" +
"G1 X36.433 Y112.833 E2.95825\n" +
"G1 X36.805 Y113.147 E2.97138\n" +
"G1 X37.228 Y113.437 E2.98520\n" +
"G1 X37.748 Y113.737 E3.00141\n" +
"G1 X39.543 Y114.630 E3.05550\n" +
"G1 X40.120 Y114.862 E3.07227\n" +
"G1 E1.07227 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.425 Y112.110 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.544 Y110.500 E2.04357\n" +
"G1 X27.612 Y107.116 E2.13486\n" +
"G1 X27.602 Y103.941 E2.22052\n" +
"G1 X28.261 Y103.918 E2.23830\n" +
"G1 X31.172 Y104.104 E2.31700\n" +
"G1 X31.545 Y104.115 E2.32706\n" +
"G1 X32.069 Y104.094 E2.34122\n" +
"G1 X32.477 Y104.013 E2.35243\n" +
"G1 X32.836 Y103.858 E2.36298\n" +
"G1 X33.106 Y103.663 E2.37196\n" +
"G1 X33.369 Y103.381 E2.38237\n" +
"G1 X33.711 Y102.814 E2.40023\n" +
"G1 X34.053 Y102.011 E2.42377\n" +
"G1 X34.409 Y100.973 E2.45338\n" +
"G1 X35.452 Y97.691 E2.54628\n" +
"G1 X35.269 Y98.403 E2.56610\n" +
"G1 X34.657 Y102.431 E2.67603\n" +
"G1 X34.505 Y105.246 E2.75205\n" +
"G1 X34.581 Y106.940 E2.79782\n" +
"G1 X34.622 Y107.309 E2.80783\n" +
"G1 X34.789 Y108.254 E2.83371\n" +
"G1 X35.015 Y109.048 E2.85597\n" +
"G1 X35.356 Y109.912 E2.88105\n" +
"G1 X36.096 Y111.366 E2.92505\n" +
"G1 X36.391 Y111.822 E2.93971\n" +
"G1 X36.619 Y112.110 E2.94962\n" +
"G1 X36.868 Y112.369 E2.95930\n" +
"G1 X37.190 Y112.640 E2.97067\n" +
"G1 X37.567 Y112.898 E2.98298\n" +
"G1 X38.049 Y113.176 E2.99799\n" +
"G1 X39.804 Y114.050 E3.05088\n" +
"G1 X42.414 Y115.101 E3.12679\n" +
"G1 X47.306 Y116.529 E3.26427\n" +
"G1 X52.916 Y117.695 E3.41884\n" +
"G1 X58.815 Y118.390 E3.57906\n" +
"G1 X65.185 Y118.583 E3.75100\n" +
"G1 X72.879 Y118.538 E3.95856\n" +
"G1 X77.780 Y118.095 E4.09130\n" +
"G1 X80.794 Y117.510 E4.17414\n" +
"G1 X85.203 Y116.279 E4.29763\n" +
"G1 X86.497 Y115.811 E4.33474\n" +
"G1 X87.335 Y115.410 E4.35980\n" +
"G1 X88.116 Y114.935 E4.38446\n" +
"G1 X88.821 Y114.401 E4.40832\n" +
"G1 X89.449 Y113.819 E4.43142\n" +
"G1 X90.029 Y113.156 E4.45519\n" +
"G1 X90.527 Y112.446 E4.47856\n" +
"G1 X90.930 Y111.725 E4.50087\n" +
"G1 X91.243 Y111.003 E4.52209\n" +
"G1 X91.515 Y110.157 E4.54606\n" +
"G1 X91.753 Y109.062 E4.57628\n" +
"G1 X91.906 Y107.756 E4.61177\n" +
"G1 X91.938 Y106.297 E4.65114\n" +
"G1 X91.800 Y104.230 E4.70701\n" +
"G1 X91.372 Y101.474 E4.78224\n" +
"G1 X89.949 Y95.911 E4.93715\n" +
"G1 X88.361 Y91.639 E5.06010\n" +
"G1 X87.553 Y89.814 E5.11394\n" +
"G1 X87.015 Y88.884 E5.14292\n" +
"G1 X86.403 Y88.037 E5.17113\n" +
"G1 X85.743 Y87.294 E5.19793\n" +
"G1 X84.963 Y86.570 E5.22664\n" +
"G1 X83.780 Y85.663 E5.26685\n" +
"G1 X82.921 Y85.115 E5.29434\n" +
"G1 X81.991 Y84.645 E5.32245\n" +
"G1 X80.635 Y84.110 E5.36177\n" +
"G1 X78.568 Y83.462 E5.42020\n" +
"G1 X71.508 Y81.859 E5.61550\n" +
"G1 X65.795 Y81.112 E5.77095\n" +
"G1 X62.310 Y80.974 E5.86502\n" +
"G1 X63.696 Y80.986 E5.90239\n" +
"G1 X64.906 Y81.029 E5.93505\n" +
"G1 X68.108 Y81.214 E6.02158\n" +
"G1 X70.895 Y81.475 E6.09709\n" +
"G1 X71.981 Y81.610 E6.12661\n" +
"G1 X75.884 Y82.255 E6.23335\n" +
"G1 X76.502 Y82.382 E6.25036\n" +
"G1 X79.263 Y83.064 E6.32708\n" +
"G1 X79.794 Y83.225 E6.34204\n" +
"G1 X81.415 Y83.821 E6.38863\n" +
"G1 X82.667 Y84.424 E6.42611\n" +
"G1 X83.889 Y85.142 E6.46437\n" +
"G1 X84.760 Y85.745 E6.49294\n" +
"G1 X85.069 Y85.982 E6.50343\n" +
"G1 X85.837 Y86.657 E6.53103\n" +
"G1 X86.439 Y87.299 E6.55477\n" +
"G1 X86.953 Y87.968 E6.57751\n" +
"G1 X87.543 Y88.926 E6.60788\n" +
"G1 X87.697 Y89.214 E6.61671\n" +
"G1 X88.319 Y90.548 E6.65640\n" +
"G1 X89.739 Y94.432 E6.76797\n" +
"G1 X91.789 Y101.194 E6.95857\n" +
"G1 X92.806 Y105.099 E7.06744\n" +
"G1 X93.128 Y106.227 E7.09907\n" +
"G1 X93.396 Y106.947 E7.11980\n" +
"G1 X93.624 Y107.433 E7.13427\n" +
"G1 X93.873 Y107.859 E7.14759\n" +
"G1 X94.102 Y108.180 E7.15823\n" +
"G1 X94.410 Y108.534 E7.17089\n" +
"G1 X94.963 Y109.019 E7.19073\n" +
"G1 X95.543 Y109.393 E7.20935\n" +
"G1 X96.041 Y109.637 E7.22431\n" +
"G1 X96.597 Y109.849 E7.24036\n" +
"G1 X97.260 Y110.036 E7.25893\n" +
"G1 X98.043 Y110.182 E7.28041\n" +
"G1 X98.775 Y110.259 E7.30027\n" +
"G1 X100.113 Y110.280 E7.33637\n" +
"G1 X101.227 Y110.259 E7.36643\n" +
"G1 X101.957 Y110.182 E7.38625\n" +
"G1 X102.733 Y110.037 E7.40753\n" +
"G1 X103.145 Y109.929 E7.41902\n" +
"G1 X103.883 Y109.670 E7.44011\n" +
"G1 X104.447 Y109.398 E7.45703\n" +
"G1 X104.885 Y109.127 E7.47091\n" +
"G1 X105.273 Y108.830 E7.48408\n" +
"G1 X105.726 Y108.388 E7.50116\n" +
"G1 X106.126 Y107.863 E7.51897\n" +
"G1 X106.376 Y107.432 E7.53241\n" +
"G1 X106.604 Y106.947 E7.54686\n" +
"G1 X106.872 Y106.227 E7.56759\n" +
"G1 X107.194 Y105.099 E7.59922\n" +
"G1 X108.211 Y101.194 E7.70809\n" +
"G1 X110.261 Y94.433 E7.89869\n" +
"G1 X111.696 Y90.513 E8.01130\n" +
"G1 X112.266 Y89.289 E8.04771\n" +
"G1 X112.460 Y88.919 E8.05899\n" +
"G1 X112.909 Y88.172 E8.08250\n" +
"G1 X113.452 Y87.429 E8.10733\n" +
"G1 X113.812 Y87.016 E8.12210\n" +
"G1 X114.472 Y86.370 E8.14701\n" +
"G1 X115.199 Y85.775 E8.17236\n" +
"G1 X116.111 Y85.142 E8.20231\n" +
"G1 X117.333 Y84.424 E8.24055\n" +
"G1 X118.585 Y83.821 E8.27803\n" +
"G1 X120.206 Y83.225 E8.32462\n" +
"G1 X120.737 Y83.064 E8.33959\n" +
"G1 X123.498 Y82.382 E8.41631\n" +
"G1 X124.116 Y82.255 E8.43332\n" +
"G1 X128.019 Y81.610 E8.54005\n" +
"G1 X129.105 Y81.475 E8.56957\n" +
"G1 X131.892 Y81.214 E8.64508\n" +
"G1 X135.094 Y81.029 E8.73161\n" +
"G1 X136.304 Y80.986 E8.76427\n" +
"G1 X137.690 Y80.974 E8.80164\n" +
"G1 X134.205 Y81.112 E8.89572\n" +
"G1 X128.492 Y81.859 E9.05116\n" +
"G1 X121.432 Y83.462 E9.24646\n" +
"G1 X119.365 Y84.110 E9.30489\n" +
"G1 X118.009 Y84.645 E9.34422\n" +
"G1 X117.079 Y85.115 E9.37233\n" +
"G1 X116.220 Y85.663 E9.39982\n" +
"G1 X115.037 Y86.570 E9.44002\n" +
"G1 X114.257 Y87.294 E9.46873\n" +
"G1 X113.597 Y88.037 E9.49553\n" +
"G1 X112.985 Y88.884 E9.52374\n" +
"G1 X112.447 Y89.814 E9.55273\n" +
"G1 X111.639 Y91.639 E9.60656\n" +
"G1 X110.051 Y95.911 E9.72951\n" +
"G1 X108.628 Y101.474 E9.88442\n" +
"G1 X108.200 Y104.230 E9.95965\n" +
"G1 X108.062 Y106.297 E10.01552\n" +
"G1 X108.094 Y107.756 E10.05489\n" +
"G1 X108.247 Y109.062 E10.09038\n" +
"G1 X108.485 Y110.157 E10.12061\n" +
"G1 X108.757 Y111.003 E10.14458\n" +
"G1 X109.070 Y111.725 E10.16579\n" +
"G1 X109.473 Y112.446 E10.18810\n" +
"G1 X109.971 Y113.156 E10.21147\n" +
"G1 X110.551 Y113.819 E10.23524\n" +
"G1 X111.179 Y114.401 E10.25834\n" +
"G1 X111.884 Y114.935 E10.28220\n" +
"G1 X112.665 Y115.410 E10.30686\n" +
"G1 X113.503 Y115.811 E10.33193\n" +
"G1 X114.797 Y116.279 E10.36904\n" +
"G1 X119.206 Y117.510 E10.49253\n" +
"G1 X122.220 Y118.095 E10.57537\n" +
"G1 X127.121 Y118.538 E10.70810\n" +
"G1 X134.815 Y118.583 E10.91567\n" +
"G1 X141.185 Y118.390 E11.08760\n" +
"G1 X147.084 Y117.695 E11.24783\n" +
"G1 X152.694 Y116.529 E11.40240\n" +
"G1 X157.586 Y115.101 E11.53988\n" +
"G1 X160.196 Y114.050 E11.61578\n" +
"G1 X161.951 Y113.176 E11.66867\n" +
"G1 X162.433 Y112.898 E11.68368\n" +
"G1 X162.810 Y112.640 E11.69599\n" +
"G1 X163.132 Y112.369 E11.70736\n" +
"G1 X163.381 Y112.110 E11.71705\n" +
"G1 X163.609 Y111.822 E11.72696\n" +
"G1 X163.904 Y111.366 E11.74161\n" +
"G1 X164.644 Y109.912 E11.78561\n" +
"G1 X164.985 Y109.048 E11.81069\n" +
"G1 X165.211 Y108.254 E11.83295\n" +
"G1 X165.377 Y107.309 E11.85883\n" +
"G1 X165.419 Y106.940 E11.86885\n" +
"G1 X165.495 Y105.246 E11.91461\n" +
"G1 X165.343 Y102.431 E11.99064\n" +
"G1 X164.731 Y98.403 E12.10056\n" +
"G1 X164.548 Y97.691 E12.12038\n" +
"G1 X165.591 Y100.973 E12.21328\n" +
"G1 X165.947 Y102.011 E12.24288\n" +
"G1 X166.289 Y102.814 E12.26641\n" +
"G1 X166.625 Y103.373 E12.28403\n" +
"G1 X166.919 Y103.689 E12.29567\n" +
"G1 X167.319 Y103.939 E12.30840\n" +
"G1 X167.706 Y104.059 E12.31931\n" +
"G1 X168.164 Y104.112 E12.33175\n" +
"G1 X168.820 Y104.104 E12.34945\n" +
"G1 X171.702 Y103.918 E12.42737\n" +
"G1 X172.399 Y103.957 E12.44618\n" +
"G1 X172.388 Y107.116 E12.53141\n" +
"G1 X172.456 Y110.500 E12.62271\n" +
"G1 X172.575 Y112.110 E12.66628\n" +
"G1 X172.282 Y112.201 E12.67453\n" +
"G1 X167.626 Y113.376 E12.80408\n" +
"G1 X162.257 Y114.941 E12.95493\n" +
"G1 X160.416 Y115.404 E13.00615\n" +
"G1 X158.893 Y115.750 E13.04828\n" +
"G1 X152.368 Y117.074 E13.22789\n" +
"G1 X150.853 Y117.364 E13.26949\n" +
"G1 X148.335 Y117.819 E13.33853\n" +
"G1 X144.123 Y118.429 E13.45333\n" +
"G1 X143.331 Y118.507 E13.47480\n" +
"G1 X135.363 Y119.081 E13.69033\n" +
"G1 X131.753 Y119.081 E13.78770\n" +
"G1 X127.182 Y118.984 E13.91105\n" +
"G1 X126.757 Y118.954 E13.92253\n" +
"G1 X122.013 Y118.354 E14.05154\n" +
"G1 X117.338 Y117.432 E14.18007\n" +
"G1 X115.591 Y116.999 E14.22863\n" +
"G1 X114.676 Y116.735 E14.25431\n" +
"G1 X110.105 Y115.216 E14.38425\n" +
"G1 X107.202 Y114.328 E14.46615\n" +
"G1 X104.584 Y113.767 E14.53838\n" +
"G1 X103.623 Y113.613 E14.56464\n" +
"G1 X102.458 Y113.472 E14.59630\n" +
"G1 X101.201 Y113.393 E14.63028\n" +
"G1 X98.878 Y113.393 E14.69294\n" +
"G1 X97.645 Y113.461 E14.72624\n" +
"G1 X96.376 Y113.613 E14.76074\n" +
"G1 X95.254 Y113.799 E14.79140\n" +
"G1 X92.797 Y114.328 E14.85920\n" +
"G1 X89.895 Y115.216 E14.94109\n" +
"G1 X85.324 Y116.735 E15.07102\n" +
"G1 X84.411 Y116.999 E15.09665\n" +
"G1 X82.349 Y117.498 E15.15388\n" +
"G1 X77.988 Y118.354 E15.27378\n" +
"G1 X73.243 Y118.954 E15.40280\n" +
"G1 X72.818 Y118.984 E15.41428\n" +
"G1 X68.247 Y119.081 E15.53764\n" +
"G1 X64.637 Y119.081 E15.63500\n" +
"G1 X56.669 Y118.507 E15.85053\n" +
"G1 X55.877 Y118.429 E15.87200\n" +
"G1 X51.667 Y117.819 E15.98676\n" +
"G1 X47.629 Y117.074 E16.09751\n" +
"G1 X41.107 Y115.750 E16.27705\n" +
"G1 X39.584 Y115.404 E16.31918\n" +
"G1 X37.743 Y114.941 E16.37040\n" +
"G1 X32.374 Y113.376 E16.52126\n" +
"G1 X27.497 Y112.132 E16.65703\n" +
"G1 E14.65703 F2400.00000\n" +
"G92 E0\n" +
"G1 X108.654 Y107.717 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X108.623 Y106.309 E2.02902\n" +
"G1 X108.757 Y104.292 E2.07069\n" +
"G1 X109.178 Y101.587 E2.12710\n" +
"G1 X110.587 Y96.079 E2.24429\n" +
"G1 X112.158 Y91.850 E2.33726\n" +
"G1 X112.947 Y90.069 E2.37742\n" +
"G1 X113.456 Y89.190 E2.39835\n" +
"G1 X114.035 Y88.388 E2.41874\n" +
"G1 X114.658 Y87.686 E2.43808\n" +
"G1 X115.399 Y86.999 E2.45891\n" +
"G1 X116.541 Y86.122 E2.48858\n" +
"G1 X117.357 Y85.603 E2.50851\n" +
"G1 X118.239 Y85.157 E2.52888\n" +
"G1 X119.552 Y84.639 E2.55797\n" +
"G1 X121.578 Y84.004 E2.60173\n" +
"G1 X128.590 Y82.412 E2.74994\n" +
"G1 X134.253 Y81.671 E2.86764\n" +
"G1 X141.621 Y81.379 E3.01961\n" +
"G1 X148.222 Y81.432 E3.15567\n" +
"G1 X151.177 Y81.725 E3.21687\n" +
"G1 X153.545 Y82.203 E3.26666\n" +
"G1 X154.961 Y82.634 E3.29716\n" +
"G1 X155.766 Y82.970 E3.31514\n" +
"G1 X156.332 Y83.280 E3.32845\n" +
"G1 X156.785 Y83.594 E3.33982\n" +
"G1 X157.257 Y83.992 E3.35254\n" +
"G1 X158.125 Y84.845 E3.37761\n" +
"G1 X159.186 Y86.006 E3.41004\n" +
"G1 X159.469 Y86.392 E3.41990\n" +
"G1 X159.809 Y86.974 E3.43379\n" +
"G1 X161.914 Y91.320 E3.53332\n" +
"G1 X163.281 Y95.008 E3.61439\n" +
"G1 X164.181 Y98.515 E3.68900\n" +
"G1 X164.785 Y102.489 E3.77184\n" +
"G1 X164.934 Y105.248 E3.82880\n" +
"G1 X164.860 Y106.896 E3.86280\n" +
"G1 X164.822 Y107.229 E3.86970\n" +
"G1 X164.664 Y108.128 E3.88852\n" +
"G1 X164.454 Y108.868 E3.90436\n" +
"G1 X164.132 Y109.682 E3.92240\n" +
"G1 X163.418 Y111.086 E3.95487\n" +
"G1 X163.153 Y111.495 E3.96491\n" +
"G1 X162.958 Y111.741 E3.97138\n" +
"G1 X162.748 Y111.959 E3.97762\n" +
"G1 X162.470 Y112.194 E3.98513\n" +
"G1 X162.134 Y112.424 E3.99351\n" +
"G1 X161.686 Y112.682 E4.00417\n" +
"G1 X159.966 Y113.538 E4.04377\n" +
"G1 X157.402 Y114.571 E4.10074\n" +
"G1 X152.558 Y115.985 E4.20474\n" +
"G1 X146.994 Y117.141 E4.32187\n" +
"G1 X141.144 Y117.831 E4.44328\n" +
"G1 X134.808 Y118.022 E4.57392\n" +
"G1 X127.148 Y117.978 E4.73181\n" +
"G1 X122.299 Y117.539 E4.83215\n" +
"G1 X119.335 Y116.964 E4.89438\n" +
"G1 X114.968 Y115.745 E4.98783\n" +
"G1 X113.720 Y115.293 E5.01518\n" +
"G1 X112.932 Y114.917 E5.03317\n" +
"G1 X112.200 Y114.471 E5.05085\n" +
"G1 X111.539 Y113.971 E5.06792\n" +
"G1 X110.953 Y113.428 E5.08438\n" +
"G1 X110.413 Y112.810 E5.10132\n" +
"G1 X109.949 Y112.148 E5.11797\n" +
"G1 X109.573 Y111.476 E5.13385\n" +
"G1 X109.282 Y110.806 E5.14890\n" +
"G1 X109.027 Y110.012 E5.16608\n" +
"G1 X108.801 Y108.970 E5.18805\n" +
"G1 X108.663 Y107.791 E5.21251\n" +
"G1 X108.654 Y107.717 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X152.414 Y81.403 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X153.119 Y81.513 E2.01924\n" +
"G1 X154.243 Y81.789 E2.05048\n" +
"G1 X155.078 Y82.084 E2.07435\n" +
"G1 X153.682 Y81.659 E2.11371\n" +
"G1 X152.487 Y81.418 E2.14659\n" +
"G1 E0.14659 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.594 Y83.543 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.901 Y83.766 E2.01024\n" +
"G1 X158.593 Y84.377 E2.03514\n" +
"G1 X159.241 Y85.065 E2.06063\n" +
"G1 X159.798 Y85.785 E2.08518\n" +
"G1 X160.228 Y86.457 E2.10672\n" +
"G1 X160.692 Y87.311 E2.13293\n" +
"G1 X160.983 Y87.913 E2.15097\n" +
"G1 X161.640 Y89.468 E2.19650\n" +
"G1 X160.304 Y86.710 E2.27915\n" +
"G1 X159.938 Y86.084 E2.29870\n" +
"G1 X159.620 Y85.651 E2.31321\n" +
"G1 X158.529 Y84.456 E2.35688\n" +
"G1 X157.651 Y83.592 E2.39010\n" +
"G1 E0.39010 F2400.00000\n" +
"G92 E0\n" +
"G1 X172.927 Y103.625 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X172.959 Y103.928 E2.00628\n" +
"G1 X172.949 Y107.112 E2.07190\n" +
"G1 X173.016 Y110.474 E2.14120\n" +
"G1 X173.154 Y112.342 E2.17981\n" +
"G1 X173.139 Y112.448 E2.18203\n" +
"G1 X173.094 Y112.494 E2.18336\n" +
"G1 X172.860 Y112.609 E2.18872\n" +
"G1 X172.433 Y112.741 E2.19793\n" +
"G1 X167.773 Y113.917 E2.29699\n" +
"G1 X162.404 Y115.482 E2.41225\n" +
"G1 X160.547 Y115.950 E2.45173\n" +
"G1 X159.011 Y116.298 E2.48418\n" +
"G1 X152.477 Y117.624 E2.62161\n" +
"G1 X150.956 Y117.915 E2.65351\n" +
"G1 X148.425 Y118.372 E2.70652\n" +
"G1 X144.191 Y118.986 E2.79470\n" +
"G1 X143.379 Y119.065 E2.81152\n" +
"G1 X135.383 Y119.642 E2.97675\n" +
"G1 X131.747 Y119.642 E3.05168\n" +
"G1 X127.156 Y119.544 E3.14632\n" +
"G1 X126.702 Y119.513 E3.15570\n" +
"G1 X121.923 Y118.908 E3.25499\n" +
"G1 X117.217 Y117.979 E3.35386\n" +
"G1 X115.446 Y117.541 E3.39146\n" +
"G1 X114.510 Y117.270 E3.41153\n" +
"G1 X109.935 Y115.750 E3.51089\n" +
"G1 X107.061 Y114.871 E3.57283\n" +
"G1 X104.481 Y114.318 E3.62722\n" +
"G1 X103.545 Y114.168 E3.64676\n" +
"G1 X102.407 Y114.030 E3.67039\n" +
"G1 X101.183 Y113.954 E3.69565\n" +
"G1 X98.893 Y113.954 E3.74285\n" +
"G1 X97.694 Y114.020 E3.76760\n" +
"G1 X96.455 Y114.168 E3.79333\n" +
"G1 X95.359 Y114.349 E3.81621\n" +
"G1 X92.938 Y114.871 E3.86725\n" +
"G1 X90.065 Y115.750 E3.92919\n" +
"G1 X85.490 Y117.270 E4.02855\n" +
"G1 X84.555 Y117.541 E4.04861\n" +
"G1 X82.469 Y118.046 E4.09284\n" +
"G1 X78.077 Y118.908 E4.18509\n" +
"G1 X73.298 Y119.513 E4.28438\n" +
"G1 X72.844 Y119.544 E4.29375\n" +
"G1 X68.253 Y119.642 E4.38840\n" +
"G1 X64.617 Y119.642 E4.46333\n" +
"G1 X56.621 Y119.065 E4.62856\n" +
"G1 X55.809 Y118.986 E4.64538\n" +
"G1 X51.576 Y118.372 E4.73354\n" +
"G1 X47.523 Y117.624 E4.81849\n" +
"G1 X40.989 Y116.298 E4.95589\n" +
"G1 X39.453 Y115.950 E4.98835\n" +
"G1 X37.596 Y115.482 E5.02783\n" +
"G1 X32.227 Y113.917 E5.14309\n" +
"G1 X27.567 Y112.741 E5.24215\n" +
"G1 X27.135 Y112.608 E5.25145\n" +
"G1 X26.939 Y112.518 E5.25591\n" +
"G1 X26.858 Y112.430 E5.25836\n" +
"G1 X26.846 Y112.342 E5.26020\n" +
"G1 X26.984 Y110.474 E5.29881\n" +
"G1 X27.051 Y107.112 E5.36811\n" +
"G1 X27.041 Y103.915 E5.43400\n" +
"G1 X27.073 Y103.621 E5.44009\n" +
"G1 X27.117 Y103.531 E5.44216\n" +
"G1 X27.245 Y103.445 E5.44533\n" +
"G1 X27.445 Y103.395 E5.44958\n" +
"G1 X27.645 Y103.371 E5.45374\n" +
"G1 X28.272 Y103.357 E5.46667\n" +
"G1 X31.199 Y103.544 E5.52710\n" +
"G1 X31.542 Y103.554 E5.53418\n" +
"G1 X32.003 Y103.535 E5.54370\n" +
"G1 X32.309 Y103.475 E5.55013\n" +
"G1 X32.558 Y103.367 E5.55570\n" +
"G1 X32.734 Y103.241 E5.56017\n" +
"G1 X32.920 Y103.041 E5.56579\n" +
"G1 X33.211 Y102.559 E5.57741\n" +
"G1 X33.529 Y101.810 E5.59417\n" +
"G1 X33.877 Y100.797 E5.61624\n" +
"G1 X35.925 Y94.353 E5.75561\n" +
"G1 X36.824 Y91.872 E5.81000\n" +
"G1 X37.970 Y88.924 E5.87520\n" +
"G1 X38.507 Y87.680 E5.90311\n" +
"G1 X38.809 Y87.055 E5.91742\n" +
"G1 X39.289 Y86.172 E5.93814\n" +
"G1 X39.744 Y85.461 E5.95552\n" +
"G1 X40.332 Y84.700 E5.97535\n" +
"G1 X41.010 Y83.981 E5.99572\n" +
"G1 X41.753 Y83.324 E6.01616\n" +
"G1 X42.762 Y82.589 E6.04188\n" +
"G1 X43.697 Y82.029 E6.06435\n" +
"G1 X44.012 Y81.869 E6.07164\n" +
"G1 X44.860 Y81.499 E6.09070\n" +
"G1 X45.886 Y81.169 E6.11292\n" +
"G1 X47.098 Y80.903 E6.13849\n" +
"G1 X48.923 Y80.657 E6.17644\n" +
"G1 X50.310 Y80.542 E6.20512\n" +
"G1 X54.151 Y80.387 E6.28435\n" +
"G1 X55.998 Y80.359 E6.32242\n" +
"G1 X63.708 Y80.425 E6.48134\n" +
"G1 X64.932 Y80.469 E6.50657\n" +
"G1 X68.150 Y80.655 E6.57301\n" +
"G1 X70.956 Y80.917 E6.63109\n" +
"G1 X72.061 Y81.055 E6.65405\n" +
"G1 X75.986 Y81.704 E6.73605\n" +
"G1 X76.626 Y81.835 E6.74950\n" +
"G1 X79.412 Y82.523 E6.80865\n" +
"G1 X79.972 Y82.693 E6.82072\n" +
"G1 X81.633 Y83.305 E6.85720\n" +
"G1 X82.931 Y83.929 E6.88687\n" +
"G1 X84.191 Y84.669 E6.91700\n" +
"G1 X85.091 Y85.292 E6.93955\n" +
"G1 X85.425 Y85.548 E6.94823\n" +
"G1 X86.227 Y86.254 E6.97026\n" +
"G1 X86.867 Y86.935 E6.98952\n" +
"G1 X87.415 Y87.649 E7.00806\n" +
"G1 X88.030 Y88.647 E7.03221\n" +
"G1 X88.199 Y88.964 E7.03963\n" +
"G1 X88.837 Y90.333 E7.07076\n" +
"G1 X90.271 Y94.255 E7.15682\n" +
"G1 X92.329 Y101.042 E7.30299\n" +
"G1 X93.347 Y104.952 E7.38627\n" +
"G1 X93.662 Y106.052 E7.40985\n" +
"G1 X93.913 Y106.730 E7.42476\n" +
"G1 X94.121 Y107.172 E7.43481\n" +
"G1 X94.344 Y107.554 E7.44394\n" +
"G1 X94.543 Y107.832 E7.45099\n" +
"G1 X94.809 Y108.138 E7.45933\n" +
"G1 X95.301 Y108.571 E7.47285\n" +
"G1 X95.819 Y108.904 E7.48554\n" +
"G1 X96.265 Y109.123 E7.49578\n" +
"G1 X96.773 Y109.316 E7.50699\n" +
"G1 X97.388 Y109.489 E7.52014\n" +
"G1 X98.123 Y109.627 E7.53557\n" +
"G1 X98.808 Y109.699 E7.54977\n" +
"G1 X100.112 Y109.719 E7.57663\n" +
"G1 X101.192 Y109.699 E7.59890\n" +
"G1 X101.877 Y109.627 E7.61309\n" +
"G1 X102.610 Y109.490 E7.62846\n" +
"G1 X102.981 Y109.392 E7.63636\n" +
"G1 X103.667 Y109.152 E7.65136\n" +
"G1 X104.178 Y108.905 E7.66303\n" +
"G1 X104.567 Y108.665 E7.67246\n" +
"G1 X104.905 Y108.406 E7.68125\n" +
"G1 X105.305 Y108.016 E7.69276\n" +
"G1 X105.659 Y107.551 E7.70480\n" +
"G1 X105.879 Y107.172 E7.71384\n" +
"G1 X106.087 Y106.730 E7.72389\n" +
"G1 X106.338 Y106.052 E7.73880\n" +
"G1 X106.653 Y104.952 E7.76239\n" +
"G1 X107.671 Y101.042 E7.84566\n" +
"G1 X109.729 Y94.255 E7.99183\n" +
"G1 X111.177 Y90.297 E8.07869\n" +
"G1 X111.764 Y89.040 E8.10727\n" +
"G1 X111.972 Y88.644 E8.11649\n" +
"G1 X112.442 Y87.862 E8.13531\n" +
"G1 X113.014 Y87.078 E8.15529\n" +
"G1 X113.404 Y86.631 E8.16754\n" +
"G1 X114.098 Y85.953 E8.18754\n" +
"G1 X114.861 Y85.327 E8.20787\n" +
"G1 X115.809 Y84.669 E8.23166\n" +
"G1 X117.069 Y83.929 E8.26178\n" +
"G1 X118.367 Y83.305 E8.29145\n" +
"G1 X120.028 Y82.693 E8.32794\n" +
"G1 X120.588 Y82.523 E8.34001\n" +
"G1 X123.374 Y81.835 E8.39915\n" +
"G1 X124.014 Y81.704 E8.41260\n" +
"G1 X127.939 Y81.055 E8.49460\n" +
"G1 X129.044 Y80.917 E8.51756\n" +
"G1 X131.850 Y80.655 E8.57564\n" +
"G1 X135.068 Y80.469 E8.64208\n" +
"G1 X136.292 Y80.425 E8.66731\n" +
"G1 X144.002 Y80.359 E8.82623\n" +
"G1 X145.849 Y80.387 E8.86431\n" +
"G1 X149.690 Y80.542 E8.94353\n" +
"G1 X151.077 Y80.657 E8.97222\n" +
"G1 X152.906 Y80.903 E9.01026\n" +
"G1 X153.237 Y80.965 E9.01719\n" +
"G1 X154.400 Y81.250 E9.04186\n" +
"G1 X155.138 Y81.499 E9.05792\n" +
"G1 X155.988 Y81.869 E9.07702\n" +
"G1 X156.303 Y82.029 E9.08431\n" +
"G1 X157.238 Y82.589 E9.10678\n" +
"G1 X158.249 Y83.326 E9.13255\n" +
"G1 X158.988 Y83.979 E9.15288\n" +
"G1 X159.668 Y84.700 E9.17331\n" +
"G1 X160.256 Y85.461 E9.19314\n" +
"G1 X160.711 Y86.172 E9.21052\n" +
"G1 X161.191 Y87.055 E9.23124\n" +
"G1 X161.493 Y87.680 E9.24555\n" +
"G1 X162.030 Y88.924 E9.27346\n" +
"G1 X163.176 Y91.872 E9.33866\n" +
"G1 X164.075 Y94.353 E9.39305\n" +
"G1 X166.123 Y100.797 E9.53242\n" +
"G1 X166.471 Y101.810 E9.55449\n" +
"G1 X166.789 Y102.558 E9.57125\n" +
"G1 X167.075 Y103.034 E9.58269\n" +
"G1 X167.279 Y103.253 E9.58885\n" +
"G1 X167.555 Y103.426 E9.59556\n" +
"G1 X167.821 Y103.508 E9.60131\n" +
"G1 X168.192 Y103.551 E9.60901\n" +
"G1 X168.799 Y103.544 E9.62150\n" +
"G1 X171.695 Y103.357 E9.68131\n" +
"G1 X172.499 Y103.388 E9.69791\n" +
"G1 X172.762 Y103.448 E9.70345\n" +
"G1 X172.870 Y103.516 E9.70609\n" +
"G1 X172.892 Y103.558 E9.70709\n" +
"G1 X172.725 Y103.853 F7800.000\n" +
"G1 E7.70709 F2400.00000\n" +
"G92 E0\n" +
"G1 X171.370 Y105.481 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X170.876 Y104.986 E2.01924\n" +
"G1 X170.013 Y105.040 E2.04302\n" +
"G1 X171.367 Y106.395 E2.09571\n" +
"G1 X171.364 Y107.308 E2.12085\n" +
"G1 X169.150 Y105.095 E2.20699\n" +
"G1 X168.271 Y105.132 E2.23121\n" +
"G1 X171.381 Y108.242 E2.35224\n" +
"G1 X171.400 Y109.178 E2.37798\n" +
"G1 X167.295 Y105.073 E2.53770\n" +
"G1 X167.196 Y105.061 E2.54045\n" +
"G1 X166.512 Y104.842 E2.56021\n" +
"G1 X166.511 Y105.205 E2.57021\n" +
"G1 X171.419 Y110.113 E2.76117\n" +
"G1 X171.451 Y111.063 E2.78731\n" +
"G1 X166.472 Y106.083 E2.98107\n" +
"G1 X166.422 Y106.950 E3.00497\n" +
"G1 X170.996 Y111.524 E3.18293\n" +
"G1 X170.264 Y111.709 E3.20370\n" +
"G1 X166.301 Y107.745 E3.35791\n" +
"G1 X166.204 Y108.292 E3.37317\n" +
"G1 X166.144 Y108.505 E3.37928\n" +
"G1 X169.532 Y111.894 E3.51112\n" +
"G1 X168.800 Y112.078 E3.53189\n" +
"G1 X165.940 Y109.218 E3.64317\n" +
"G1 X165.680 Y109.875 E3.66261\n" +
"G1 X168.068 Y112.263 E3.75552\n" +
"G1 X167.342 Y112.454 E3.77617\n" +
"G1 X165.395 Y110.507 E3.85194\n" +
"G1 X165.086 Y111.114 E3.87070\n" +
"G1 X166.632 Y112.661 E3.93088\n" +
"G1 X165.922 Y112.868 E3.95123\n" +
"G1 X164.777 Y111.722 E3.99581\n" +
"G1 X164.419 Y112.281 E4.01407\n" +
"G1 X165.212 Y113.075 E4.04495\n" +
"G1 X164.503 Y113.282 E4.06530\n" +
"G1 X164.008 Y112.787 E4.08453\n" +
"G1 E2.08453 F2400.00000\n" +
"G92 E0\n" +
"G1 X107.194 Y109.131 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X106.699 Y108.636 E2.01918\n" +
"G1 X106.434 Y108.985 E2.03120\n" +
"G1 X106.282 Y109.133 E2.03702\n" +
"G1 X107.398 Y110.249 E2.08029\n" +
"G1 X107.503 Y110.734 E2.09392\n" +
"G1 X107.755 Y111.520 E2.11656\n" +
"G1 X105.815 Y109.580 E2.19182\n" +
"G1 X105.292 Y109.970 E2.20973\n" +
"G1 X108.580 Y113.259 E2.33729\n" +
"G1 X108.859 Y113.679 E2.35113\n" +
"G1 X107.746 Y113.338 E2.38307\n" +
"G1 X104.719 Y110.312 E2.50047\n" +
"G1 X104.100 Y110.607 E2.51927\n" +
"G1 X106.583 Y113.089 E2.61556\n" +
"G1 X105.420 Y112.840 E2.64818\n" +
"G1 X103.424 Y110.844 E2.72561\n" +
"G1 X102.707 Y111.042 E2.74599\n" +
"G1 X104.308 Y112.643 E2.80810\n" +
"G1 X103.257 Y112.505 E2.83718\n" +
"G1 X101.937 Y111.185 E2.88838\n" +
"G1 X101.113 Y111.276 E2.91111\n" +
"G1 X102.255 Y112.417 E2.95540\n" +
"G1 X101.293 Y112.369 E2.98181\n" +
"G1 X100.221 Y111.297 E3.02339\n" +
"G1 X99.305 Y111.295 E3.04852\n" +
"G1 X100.379 Y112.369 E3.09017\n" +
"G1 X99.465 Y112.369 E3.11525\n" +
"G1 X98.369 Y111.273 E3.15776\n" +
"G1 X97.612 Y111.193 E3.17864\n" +
"G1 X97.321 Y111.139 E3.18676\n" +
"G1 X98.579 Y112.397 E3.23557\n" +
"G1 X97.717 Y112.449 E3.25925\n" +
"G1 X96.128 Y110.860 E3.32091\n" +
"G1 X95.951 Y110.810 E3.32595\n" +
"G1 X95.287 Y110.557 E3.34544\n" +
"G1 X94.670 Y110.254 E3.36428\n" +
"G1 X94.497 Y110.143 E3.36993\n" +
"G1 X96.901 Y112.547 E3.46318\n" +
"G1 X96.098 Y112.658 E3.48539\n" +
"G1 X92.708 Y109.268 E3.61691\n" +
"G1 X92.545 Y110.019 E3.63798\n" +
"G1 X95.314 Y112.788 E3.74541\n" +
"G1 X94.560 Y112.948 E3.76655\n" +
"G1 X92.340 Y110.728 E3.85267\n" +
"G1 X92.187 Y111.204 E3.86639\n" +
"G1 X92.101 Y111.403 E3.87233\n" +
"G1 X93.808 Y113.110 E3.93855\n" +
"G1 X93.056 Y113.272 E3.95965\n" +
"G1 X91.824 Y112.040 E4.00743\n" +
"G1 X91.497 Y112.627 E4.02585\n" +
"G1 X92.334 Y113.464 E4.05833\n" +
"G1 X91.635 Y113.679 E4.07838\n" +
"G1 X91.141 Y113.185 E4.09756\n" +
"G1 E2.09756 F2400.00000\n" +
"G92 E0\n" +
"G1 X33.489 Y105.315 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X32.994 Y104.820 E2.02014\n" +
"G1 X32.610 Y104.987 E2.03222\n" +
"G1 X32.268 Y105.054 E2.04224\n" +
"G1 X33.516 Y106.302 E2.09306\n" +
"G1 X33.562 Y107.308 E2.12208\n" +
"G1 X31.391 Y105.136 E2.21054\n" +
"G1 X30.391 Y105.096 E2.23937\n" +
"G1 X33.735 Y108.440 E2.37558\n" +
"G1 X33.796 Y108.786 E2.38571\n" +
"G1 X34.068 Y109.734 E2.41410\n" +
"G1 X29.366 Y105.031 E2.60567\n" +
"G1 X28.630 Y104.985 E2.62691\n" +
"G1 X28.631 Y105.256 E2.63473\n" +
"G1 X34.843 Y111.468 E2.88779\n" +
"G1 X35.240 Y112.249 E2.91304\n" +
"G1 X35.605 Y112.812 E2.93235\n" +
"G1 X35.992 Y113.282 E2.94990\n" +
"G1 X35.575 Y113.160 E2.96241\n" +
"G1 X28.634 Y106.219 E3.24518\n" +
"G1 X28.630 Y107.175 E3.27272\n" +
"G1 X34.221 Y112.765 E3.50047\n" +
"G1 X32.871 Y112.375 E3.54095\n" +
"G1 X28.611 Y108.116 E3.71446\n" +
"G1 X28.593 Y109.057 E3.74158\n" +
"G1 X31.586 Y112.051 E3.86354\n" +
"G1 X30.302 Y111.727 E3.90170\n" +
"G1 X28.574 Y109.998 E3.97211\n" +
"G1 X28.523 Y110.908 E3.99836\n" +
"G1 X29.018 Y111.402 E4.01850\n" +
"G1 Z1.550 F7800.000\n" +
"G1 E2.01850 F2400.00000\n" +
"G92 E0\n" +
"G1 X164.869 Y104.060 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X164.934 Y105.248 E2.02453\n" +
"G1 X164.860 Y106.896 E2.05853\n" +
"G1 X164.822 Y107.229 E2.06544\n" +
"G1 X164.664 Y108.128 E2.08426\n" +
"G1 X164.454 Y108.868 E2.10010\n" +
"G1 X164.132 Y109.682 E2.11814\n" +
"G1 X163.418 Y111.086 E2.15061\n" +
"G1 X163.153 Y111.495 E2.16065\n" +
"G1 X162.958 Y111.741 E2.16712\n" +
"G1 X162.748 Y111.959 E2.17336\n" +
"G1 X162.470 Y112.194 E2.18086\n" +
"G1 X162.134 Y112.424 E2.18925\n" +
"G1 X161.686 Y112.682 E2.19991\n" +
"G1 X159.966 Y113.538 E2.23951\n" +
"G1 X157.402 Y114.571 E2.29647\n" +
"G1 X152.558 Y115.985 E2.40048\n" +
"G1 X146.994 Y117.141 E2.51761\n" +
"G1 X141.144 Y117.831 E2.63901\n" +
"G1 X134.808 Y118.022 E2.76966\n" +
"G1 X127.148 Y117.978 E2.92755\n" +
"G1 X122.299 Y117.539 E3.02788\n" +
"G1 X119.335 Y116.964 E3.09012\n" +
"G1 X114.968 Y115.745 E3.18357\n" +
"G1 X113.720 Y115.293 E3.21092\n" +
"G1 X112.932 Y114.917 E3.22891\n" +
"G1 X112.200 Y114.471 E3.24659\n" +
"G1 X111.539 Y113.971 E3.26366\n" +
"G1 X110.953 Y113.428 E3.28012\n" +
"G1 X110.413 Y112.810 E3.29705\n" +
"G1 X109.949 Y112.148 E3.31371\n" +
"G1 X109.573 Y111.476 E3.32958\n" +
"G1 X109.282 Y110.806 E3.34464\n" +
"G1 X109.027 Y110.012 E3.36182\n" +
"G1 X108.801 Y108.970 E3.38379\n" +
"G1 X108.654 Y107.717 E3.40980\n" +
"G1 X108.623 Y106.309 E3.43882\n" +
"G1 X108.757 Y104.292 E3.48048\n" +
"G1 X109.178 Y101.587 E3.53690\n" +
"G1 X110.587 Y96.079 E3.65408\n" +
"G1 X112.158 Y91.850 E3.74706\n" +
"G1 X112.947 Y90.069 E3.78721\n" +
"G1 X113.456 Y89.190 E3.80815\n" +
"G1 X114.035 Y88.388 E3.82854\n" +
"G1 X114.658 Y87.686 E3.84787\n" +
"G1 X115.399 Y86.999 E3.86870\n" +
"G1 X116.541 Y86.122 E3.89838\n" +
"G1 X117.357 Y85.603 E3.91831\n" +
"G1 X118.239 Y85.157 E3.93867\n" +
"G1 X119.552 Y84.639 E3.96777\n" +
"G1 X121.578 Y84.004 E4.01153\n" +
"G1 X128.590 Y82.412 E4.15973\n" +
"G1 X134.253 Y81.671 E4.27744\n" +
"G1 X141.621 Y81.379 E4.42941\n" +
"G1 X148.222 Y81.432 E4.56546\n" +
"G1 X151.177 Y81.725 E4.62667\n" +
"G1 X153.545 Y82.203 E4.67645\n" +
"G1 X154.961 Y82.634 E4.70696\n" +
"G1 X155.766 Y82.970 E4.72493\n" +
"G1 X156.332 Y83.280 E4.73825\n" +
"G1 X156.785 Y83.594 E4.74961\n" +
"G1 X157.257 Y83.992 E4.76234\n" +
"G1 X158.125 Y84.845 E4.78741\n" +
"G1 X159.186 Y86.006 E4.81984\n" +
"G1 X159.469 Y86.392 E4.82970\n" +
"G1 X159.809 Y86.974 E4.84359\n" +
"G1 X161.914 Y91.320 E4.94312\n" +
"G1 X163.281 Y95.008 E5.02418\n" +
"G1 X164.181 Y98.515 E5.09880\n" +
"G1 X164.785 Y102.489 E5.18164\n" +
"G1 X164.865 Y103.985 E5.21251\n" +
"G1 X165.320 Y104.275 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X155.092 Y82.088 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X153.682 Y81.659 E2.03975\n" +
"G1 X152.446 Y81.409 E2.07375\n" +
"G1 X152.859 Y81.465 E2.08497\n" +
"G1 X153.799 Y81.671 E2.11095\n" +
"G1 X154.235 Y81.786 E2.12311\n" +
"G1 X155.023 Y82.058 E2.14559\n" +
"G1 E0.14559 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.590 Y83.539 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.893 Y83.760 E2.01013\n" +
"G1 X158.595 Y84.380 E2.03539\n" +
"G1 X159.241 Y85.065 E2.06078\n" +
"G1 X159.797 Y85.784 E2.08531\n" +
"G1 X160.245 Y86.486 E2.10778\n" +
"G1 X160.767 Y87.464 E2.13768\n" +
"G1 X160.982 Y87.912 E2.15109\n" +
"G1 X161.602 Y89.390 E2.19431\n" +
"G1 X160.304 Y86.710 E2.27463\n" +
"G1 X159.938 Y86.084 E2.29418\n" +
"G1 X159.620 Y85.651 E2.30869\n" +
"G1 X158.529 Y84.456 E2.35236\n" +
"G1 X157.646 Y83.588 E2.38574\n" +
"G1 E0.38574 F2400.00000\n" +
"G92 E0\n" +
"G1 X166.099 Y103.737 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X166.517 Y104.187 E2.01656\n" +
"G1 X167.023 Y104.509 E2.03275\n" +
"G1 X167.564 Y104.682 E2.04807\n" +
"G1 X168.130 Y104.747 E2.06344\n" +
"G1 X168.783 Y104.741 E2.08105\n" +
"G1 X171.761 Y104.555 E2.16155\n" +
"G1 X171.752 Y106.861 E2.22376\n" +
"G1 X171.824 Y110.602 E2.32469\n" +
"G1 X171.902 Y111.644 E2.35289\n" +
"G1 X167.149 Y112.856 E2.48521\n" +
"G1 X162.624 Y114.174 E2.61235\n" +
"G1 X159.810 Y114.890 E2.69067\n" +
"G1 X160.457 Y114.630 E2.70946\n" +
"G1 X162.252 Y113.737 E2.76355\n" +
"G1 X162.772 Y113.437 E2.77976\n" +
"G1 X163.195 Y113.147 E2.79359\n" +
"G1 X163.567 Y112.833 E2.80671\n" +
"G1 X163.860 Y112.528 E2.81812\n" +
"G1 X164.126 Y112.193 E2.82965\n" +
"G1 X164.456 Y111.684 E2.84603\n" +
"G1 X165.224 Y110.174 E2.89173\n" +
"G1 X165.588 Y109.252 E2.91847\n" +
"G1 X165.831 Y108.396 E2.94246\n" +
"G1 X166.007 Y107.400 E2.96976\n" +
"G1 X166.053 Y106.990 E2.98089\n" +
"G1 X166.131 Y105.243 E3.02808\n" +
"G1 X166.045 Y103.646 E3.07121\n" +
"G1 X166.061 Y103.672 E3.07205\n" +
"G1 E1.07205 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.566 Y114.700 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.314 Y113.700 E2.09178\n" +
"G1 X105.202 Y113.245 E2.15006\n" +
"G1 X103.718 Y112.984 E2.19071\n" +
"G1 X102.296 Y112.820 E2.22934\n" +
"G1 X101.578 Y112.770 E2.24876\n" +
"G1 X100.938 Y112.758 E2.26600\n" +
"G1 X98.719 Y112.760 E2.32587\n" +
"G1 X97.300 Y112.861 E2.36425\n" +
"G1 X96.288 Y112.983 E2.39174\n" +
"G1 X95.447 Y113.118 E2.41473\n" +
"G1 X92.689 Y113.699 E2.49076\n" +
"G1 X89.434 Y114.700 E2.58264\n" +
"G1 X89.906 Y114.263 E2.60000\n" +
"G1 X90.530 Y113.549 E2.62559\n" +
"G1 X91.066 Y112.785 E2.65075\n" +
"G1 X91.501 Y112.007 E2.67479\n" +
"G1 X91.839 Y111.227 E2.69772\n" +
"G1 X92.129 Y110.322 E2.72336\n" +
"G1 X92.380 Y109.167 E2.75525\n" +
"G1 X92.541 Y107.800 E2.79238\n" +
"G1 X92.568 Y106.544 E2.82627\n" +
"G1 X92.826 Y107.231 E2.84606\n" +
"G1 X93.072 Y107.750 E2.86156\n" +
"G1 X93.351 Y108.224 E2.87641\n" +
"G1 X93.609 Y108.582 E2.88829\n" +
"G1 X93.944 Y108.966 E2.90204\n" +
"G1 X94.315 Y109.314 E2.91577\n" +
"G1 X94.635 Y109.566 E2.92675\n" +
"G1 X95.226 Y109.945 E2.94571\n" +
"G1 X95.787 Y110.221 E2.96256\n" +
"G1 X96.398 Y110.453 E2.98018\n" +
"G1 X97.115 Y110.655 E3.00029\n" +
"G1 X97.952 Y110.811 E3.02327\n" +
"G1 X98.776 Y110.896 E3.04562\n" +
"G1 X100.281 Y110.914 E3.08620\n" +
"G1 X101.240 Y110.896 E3.11209\n" +
"G1 X102.316 Y110.768 E3.14132\n" +
"G1 X102.885 Y110.654 E3.15697\n" +
"G1 X103.382 Y110.521 E3.17086\n" +
"G1 X104.142 Y110.251 E3.19261\n" +
"G1 X104.760 Y109.952 E3.21112\n" +
"G1 X105.249 Y109.649 E3.22663\n" +
"G1 X105.691 Y109.311 E3.24165\n" +
"G1 X106.215 Y108.797 E3.26146\n" +
"G1 X106.677 Y108.182 E3.28220\n" +
"G1 X106.945 Y107.718 E3.29666\n" +
"G1 X107.175 Y107.229 E3.31124\n" +
"G1 X107.432 Y106.544 E3.33098\n" +
"G1 X107.459 Y107.800 E3.36486\n" +
"G1 X107.620 Y109.167 E3.40200\n" +
"G1 X107.871 Y110.322 E3.43389\n" +
"G1 X108.161 Y111.227 E3.45953\n" +
"G1 X108.499 Y112.007 E3.48246\n" +
"G1 X108.934 Y112.785 E3.50650\n" +
"G1 X109.470 Y113.549 E3.53166\n" +
"G1 X110.094 Y114.263 E3.55725\n" +
"G1 X110.511 Y114.649 E3.57259\n" +
"G1 E1.57259 F2400.00000\n" +
"G92 E0\n" +
"G1 X40.190 Y114.890 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X37.376 Y114.174 E2.07832\n" +
"G1 X32.861 Y112.859 E2.20518\n" +
"G1 X31.542 Y112.507 E2.24200\n" +
"G1 X28.098 Y111.640 E2.33780\n" +
"G1 X28.176 Y110.602 E2.36590\n" +
"G1 X28.248 Y106.862 E2.46681\n" +
"G1 X28.240 Y104.559 E2.52894\n" +
"G1 X29.503 Y104.623 E2.56305\n" +
"G1 X31.228 Y104.742 E2.60968\n" +
"G1 X32.055 Y104.734 E2.63201\n" +
"G1 X32.457 Y104.676 E2.64295\n" +
"G1 X32.977 Y104.509 E2.65768\n" +
"G1 X33.472 Y104.194 E2.67351\n" +
"G1 X33.870 Y103.779 E2.68903\n" +
"G1 X33.955 Y103.640 E2.69344\n" +
"G1 X33.869 Y105.243 E2.73674\n" +
"G1 X33.947 Y106.990 E2.78393\n" +
"G1 X33.993 Y107.400 E2.79506\n" +
"G1 X34.169 Y108.397 E2.82236\n" +
"G1 X34.412 Y109.252 E2.84635\n" +
"G1 X34.776 Y110.174 E2.87309\n" +
"G1 X35.544 Y111.684 E2.91879\n" +
"G1 X35.874 Y112.193 E2.93517\n" +
"G1 X36.140 Y112.528 E2.94670\n" +
"G1 X36.433 Y112.833 E2.95811\n" +
"G1 X36.805 Y113.147 E2.97123\n" +
"G1 X37.228 Y113.437 E2.98506\n" +
"G1 X37.748 Y113.737 E3.00127\n" +
"G1 X39.543 Y114.630 E3.05536\n" +
"G1 X40.120 Y114.862 E3.07213\n" +
"G1 E1.07213 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.426 Y112.111 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.541 Y110.572 E2.04164\n" +
"G1 X27.612 Y106.856 E2.14189\n" +
"G1 X27.603 Y103.941 E2.22053\n" +
"G1 X28.181 Y103.919 E2.23615\n" +
"G1 X29.541 Y103.988 E2.27288\n" +
"G1 X31.246 Y104.106 E2.31899\n" +
"G1 X32.006 Y104.099 E2.33950\n" +
"G1 X32.313 Y104.054 E2.34785\n" +
"G1 X32.705 Y103.928 E2.35896\n" +
"G1 X33.067 Y103.699 E2.37052\n" +
"G1 X33.363 Y103.390 E2.38207\n" +
"G1 X33.705 Y102.827 E2.39984\n" +
"G1 X34.038 Y102.049 E2.42266\n" +
"G1 X35.396 Y97.910 E2.54019\n" +
"G1 X35.269 Y98.403 E2.55393\n" +
"G1 X34.657 Y102.431 E2.66385\n" +
"G1 X34.505 Y105.246 E2.73988\n" +
"G1 X34.581 Y106.940 E2.78564\n" +
"G1 X34.622 Y107.309 E2.79566\n" +
"G1 X34.789 Y108.254 E2.82154\n" +
"G1 X35.015 Y109.048 E2.84379\n" +
"G1 X35.356 Y109.912 E2.86888\n" +
"G1 X36.096 Y111.366 E2.91288\n" +
"G1 X36.391 Y111.822 E2.92753\n" +
"G1 X36.619 Y112.110 E2.93744\n" +
"G1 X36.868 Y112.369 E2.94712\n" +
"G1 X37.190 Y112.640 E2.95850\n" +
"G1 X37.567 Y112.898 E2.97080\n" +
"G1 X38.049 Y113.176 E2.98582\n" +
"G1 X39.804 Y114.050 E3.03871\n" +
"G1 X42.414 Y115.101 E3.11461\n" +
"G1 X47.306 Y116.529 E3.25209\n" +
"G1 X52.916 Y117.695 E3.40666\n" +
"G1 X58.815 Y118.390 E3.56689\n" +
"G1 X65.185 Y118.583 E3.73882\n" +
"G1 X72.879 Y118.538 E3.94639\n" +
"G1 X77.780 Y118.095 E4.07912\n" +
"G1 X80.794 Y117.510 E4.16196\n" +
"G1 X85.203 Y116.279 E4.28545\n" +
"G1 X86.497 Y115.811 E4.32256\n" +
"G1 X87.335 Y115.410 E4.34762\n" +
"G1 X88.116 Y114.935 E4.37229\n" +
"G1 X88.821 Y114.401 E4.39614\n" +
"G1 X89.449 Y113.819 E4.41924\n" +
"G1 X90.029 Y113.156 E4.44301\n" +
"G1 X90.527 Y112.446 E4.46639\n" +
"G1 X90.930 Y111.725 E4.48870\n" +
"G1 X91.243 Y111.003 E4.50991\n" +
"G1 X91.515 Y110.157 E4.53388\n" +
"G1 X91.753 Y109.062 E4.56410\n" +
"G1 X91.906 Y107.756 E4.59959\n" +
"G1 X91.938 Y106.297 E4.63897\n" +
"G1 X91.800 Y104.230 E4.69484\n" +
"G1 X91.372 Y101.474 E4.77007\n" +
"G1 X89.949 Y95.911 E4.92497\n" +
"G1 X88.361 Y91.639 E5.04793\n" +
"G1 X87.553 Y89.814 E5.10176\n" +
"G1 X87.015 Y88.884 E5.13075\n" +
"G1 X86.403 Y88.037 E5.15896\n" +
"G1 X85.743 Y87.294 E5.18575\n" +
"G1 X84.963 Y86.570 E5.21446\n" +
"G1 X83.780 Y85.663 E5.25467\n" +
"G1 X82.921 Y85.115 E5.28216\n" +
"G1 X81.991 Y84.645 E5.31027\n" +
"G1 X80.635 Y84.110 E5.34960\n" +
"G1 X78.568 Y83.462 E5.40802\n" +
"G1 X71.508 Y81.859 E5.60332\n" +
"G1 X65.795 Y81.112 E5.75877\n" +
"G1 X62.489 Y80.981 E5.84803\n" +
"G1 X63.890 Y80.993 E5.88583\n" +
"G1 X64.899 Y81.029 E5.91305\n" +
"G1 X66.098 Y81.118 E5.94551\n" +
"G1 X68.553 Y81.255 E6.01183\n" +
"G1 X70.895 Y81.475 E6.07528\n" +
"G1 X71.805 Y81.588 E6.10002\n" +
"G1 X75.987 Y82.276 E6.21436\n" +
"G1 X76.502 Y82.382 E6.22853\n" +
"G1 X79.323 Y83.082 E6.30696\n" +
"G1 X79.764 Y83.216 E6.31939\n" +
"G1 X81.392 Y83.812 E6.36614\n" +
"G1 X82.666 Y84.424 E6.40429\n" +
"G1 X83.924 Y85.165 E6.44365\n" +
"G1 X84.654 Y85.670 E6.46762\n" +
"G1 X85.072 Y85.984 E6.48171\n" +
"G1 X85.837 Y86.657 E6.50920\n" +
"G1 X86.439 Y87.299 E6.53294\n" +
"G1 X86.953 Y87.968 E6.55569\n" +
"G1 X87.543 Y88.926 E6.58606\n" +
"G1 X87.669 Y89.162 E6.59327\n" +
"G1 X88.354 Y90.634 E6.63708\n" +
"G1 X89.751 Y94.468 E6.74714\n" +
"G1 X91.787 Y101.187 E6.93656\n" +
"G1 X92.865 Y105.304 E7.05134\n" +
"G1 X93.129 Y106.228 E7.07728\n" +
"G1 X93.412 Y106.983 E7.09902\n" +
"G1 X93.634 Y107.452 E7.11302\n" +
"G1 X93.884 Y107.876 E7.12631\n" +
"G1 X94.107 Y108.186 E7.13662\n" +
"G1 X94.402 Y108.524 E7.14870\n" +
"G1 X94.730 Y108.832 E7.16084\n" +
"G1 X95.004 Y109.047 E7.17024\n" +
"G1 X95.539 Y109.390 E7.18739\n" +
"G1 X96.041 Y109.637 E7.20248\n" +
"G1 X96.597 Y109.849 E7.21854\n" +
"G1 X97.260 Y110.036 E7.23711\n" +
"G1 X98.043 Y110.182 E7.25861\n" +
"G1 X98.813 Y110.261 E7.27948\n" +
"G1 X100.278 Y110.278 E7.31902\n" +
"G1 X101.197 Y110.261 E7.34379\n" +
"G1 X102.216 Y110.140 E7.37149\n" +
"G1 X102.740 Y110.035 E7.38590\n" +
"G1 X103.193 Y109.914 E7.39856\n" +
"G1 X103.897 Y109.664 E7.41869\n" +
"G1 X104.453 Y109.394 E7.43537\n" +
"G1 X104.887 Y109.126 E7.44915\n" +
"G1 X105.273 Y108.830 E7.46227\n" +
"G1 X105.736 Y108.377 E7.47973\n" +
"G1 X106.145 Y107.831 E7.49814\n" +
"G1 X106.381 Y107.423 E7.51084\n" +
"G1 X106.589 Y106.982 E7.52401\n" +
"G1 X106.871 Y106.228 E7.54572\n" +
"G1 X107.135 Y105.304 E7.57166\n" +
"G1 X108.213 Y101.187 E7.68644\n" +
"G1 X110.249 Y94.468 E7.87583\n" +
"G1 X111.704 Y90.492 E7.99007\n" +
"G1 X112.183 Y89.465 E8.02064\n" +
"G1 X112.463 Y88.915 E8.03727\n" +
"G1 X112.931 Y88.140 E8.06171\n" +
"G1 X113.471 Y87.405 E8.08631\n" +
"G1 X113.749 Y87.085 E8.09772\n" +
"G1 X114.164 Y86.656 E8.11384\n" +
"G1 X114.538 Y86.315 E8.12749\n" +
"G1 X115.154 Y85.810 E8.14898\n" +
"G1 X116.078 Y85.164 E8.17941\n" +
"G1 X117.334 Y84.424 E8.21871\n" +
"G1 X118.608 Y83.812 E8.25686\n" +
"G1 X120.236 Y83.216 E8.30361\n" +
"G1 X120.677 Y83.082 E8.31604\n" +
"G1 X123.498 Y82.382 E8.39448\n" +
"G1 X124.013 Y82.276 E8.40864\n" +
"G1 X128.195 Y81.588 E8.52298\n" +
"G1 X129.105 Y81.475 E8.54772\n" +
"G1 X131.455 Y81.255 E8.61138\n" +
"G1 X132.647 Y81.166 E8.64364\n" +
"G1 X135.094 Y81.029 E8.70974\n" +
"G1 X136.110 Y80.993 E8.73717\n" +
"G1 X137.511 Y80.981 E8.77498\n" +
"G1 X134.205 Y81.112 E8.86423\n" +
"G1 X128.492 Y81.859 E9.01968\n" +
"G1 X121.432 Y83.462 E9.21498\n" +
"G1 X119.365 Y84.110 E9.27340\n" +
"G1 X118.009 Y84.645 E9.31273\n" +
"G1 X117.079 Y85.115 E9.34084\n" +
"G1 X116.220 Y85.663 E9.36833\n" +
"G1 X115.037 Y86.570 E9.40854\n" +
"G1 X114.257 Y87.294 E9.43725\n" +
"G1 X113.597 Y88.037 E9.46404\n" +
"G1 X112.985 Y88.884 E9.49225\n" +
"G1 X112.447 Y89.814 E9.52124\n" +
"G1 X111.639 Y91.639 E9.57508\n" +
"G1 X110.051 Y95.911 E9.69803\n" +
"G1 X108.628 Y101.474 E9.85293\n" +
"G1 X108.200 Y104.230 E9.92817\n" +
"G1 X108.062 Y106.297 E9.98403\n" +
"G1 X108.094 Y107.756 E10.02341\n" +
"G1 X108.247 Y109.062 E10.05890\n" +
"G1 X108.485 Y110.157 E10.08912\n" +
"G1 X108.757 Y111.003 E10.11309\n" +
"G1 X109.070 Y111.725 E10.13431\n" +
"G1 X109.473 Y112.446 E10.15661\n" +
"G1 X109.971 Y113.156 E10.17999\n" +
"G1 X110.551 Y113.819 E10.20376\n" +
"G1 X111.179 Y114.401 E10.22686\n" +
"G1 X111.884 Y114.935 E10.25071\n" +
"G1 X112.665 Y115.410 E10.27538\n" +
"G1 X113.503 Y115.811 E10.30044\n" +
"G1 X114.797 Y116.279 E10.33755\n" +
"G1 X119.206 Y117.510 E10.46104\n" +
"G1 X122.220 Y118.095 E10.54388\n" +
"G1 X127.121 Y118.538 E10.67661\n" +
"G1 X134.815 Y118.583 E10.88418\n" +
"G1 X141.185 Y118.390 E11.05612\n" +
"G1 X147.084 Y117.695 E11.21634\n" +
"G1 X152.694 Y116.529 E11.37091\n" +
"G1 X157.586 Y115.101 E11.50839\n" +
"G1 X160.196 Y114.050 E11.58429\n" +
"G1 X161.951 Y113.176 E11.63718\n" +
"G1 X162.433 Y112.898 E11.65220\n" +
"G1 X162.810 Y112.640 E11.66451\n" +
"G1 X163.132 Y112.369 E11.67588\n" +
"G1 X163.381 Y112.110 E11.68556\n" +
"G1 X163.609 Y111.822 E11.69547\n" +
"G1 X163.904 Y111.366 E11.71013\n" +
"G1 X164.644 Y109.912 E11.75412\n" +
"G1 X164.985 Y109.048 E11.77921\n" +
"G1 X165.211 Y108.254 E11.80146\n" +
"G1 X165.377 Y107.309 E11.82734\n" +
"G1 X165.419 Y106.940 E11.83736\n" +
"G1 X165.495 Y105.246 E11.88312\n" +
"G1 X165.343 Y102.431 E11.95915\n" +
"G1 X164.731 Y98.403 E12.06907\n" +
"G1 X164.536 Y97.643 E12.09023\n" +
"G1 X165.558 Y100.868 E12.18150\n" +
"G1 X165.963 Y102.053 E12.21528\n" +
"G1 X166.294 Y102.824 E12.23792\n" +
"G1 X166.611 Y103.353 E12.25455\n" +
"G1 X166.927 Y103.695 E12.26710\n" +
"G1 X167.295 Y103.928 E12.27886\n" +
"G1 X167.699 Y104.058 E12.29030\n" +
"G1 X168.163 Y104.111 E12.30291\n" +
"G1 X168.760 Y104.106 E12.31900\n" +
"G1 X171.701 Y103.918 E12.39852\n" +
"G1 X172.304 Y103.930 E12.41478\n" +
"G1 X172.395 Y103.940 E12.41726\n" +
"G1 X172.399 Y103.974 E12.41817\n" +
"G1 X172.388 Y106.856 E12.49594\n" +
"G1 X172.459 Y110.572 E12.59619\n" +
"G1 X172.574 Y112.111 E12.63784\n" +
"G1 X170.965 Y112.552 E12.68285\n" +
"G1 X167.315 Y113.469 E12.78437\n" +
"G1 X162.792 Y114.787 E12.91146\n" +
"G1 X160.171 Y115.460 E12.98446\n" +
"G1 X158.894 Y115.750 E13.01978\n" +
"G1 X150.850 Y117.364 E13.24112\n" +
"G1 X148.730 Y117.747 E13.29923\n" +
"G1 X147.389 Y117.960 E13.33587\n" +
"G1 X144.124 Y118.429 E13.42484\n" +
"G1 X143.461 Y118.494 E13.44281\n" +
"G1 X135.362 Y119.081 E13.66186\n" +
"G1 X132.328 Y119.081 E13.74371\n" +
"G1 X127.181 Y118.984 E13.88260\n" +
"G1 X126.831 Y118.959 E13.89206\n" +
"G1 X121.981 Y118.349 E14.02391\n" +
"G1 X117.058 Y117.362 E14.15937\n" +
"G1 X115.586 Y116.998 E14.20028\n" +
"G1 X115.037 Y116.847 E14.21563\n" +
"G1 X114.015 Y116.516 E14.24461\n" +
"G1 X110.154 Y115.233 E14.35436\n" +
"G1 X107.154 Y114.316 E14.43900\n" +
"G1 X105.080 Y113.869 E14.49622\n" +
"G1 X103.627 Y113.613 E14.53603\n" +
"G1 X102.237 Y113.454 E14.57376\n" +
"G1 X101.550 Y113.405 E14.59236\n" +
"G1 X100.933 Y113.394 E14.60900";
                                        salientemarcos[4]+="G1 X98.742 Y113.395 E14.66810\n" +
"G1 X97.361 Y113.494 E14.70546\n" +
"G1 X96.377 Y113.613 E14.73219\n" +
"G1 X95.563 Y113.743 E14.75443\n" +
"G1 X92.848 Y114.315 E14.82928\n" +
"G1 X89.846 Y115.233 E14.91397\n" +
"G1 X85.985 Y116.516 E15.02372\n" +
"G1 X84.963 Y116.847 E15.05270\n" +
"G1 X84.414 Y116.998 E15.06805\n" +
"G1 X82.937 Y117.363 E15.10910\n" +
"G1 X81.697 Y117.627 E15.14330\n" +
"G1 X78.020 Y118.348 E15.24438\n" +
"G1 X73.169 Y118.959 E15.37628\n" +
"G1 X72.819 Y118.984 E15.38574\n" +
"G1 X67.672 Y119.081 E15.52463\n" +
"G1 X64.638 Y119.081 E15.60648\n" +
"G1 X56.539 Y118.494 E15.82553\n" +
"G1 X55.882 Y118.429 E15.84333\n" +
"G1 X54.544 Y118.218 E15.87988\n" +
"G1 X51.266 Y117.747 E15.96923\n" +
"G1 X47.870 Y117.120 E16.06239\n" +
"G1 X41.107 Y115.750 E16.24853\n" +
"G1 X39.829 Y115.460 E16.28387\n" +
"G1 X37.208 Y114.787 E16.35687\n" +
"G1 X32.690 Y113.471 E16.48381\n" +
"G1 X27.497 Y112.133 E16.62847\n" +
"G1 E14.62847 F2400.00000\n" +
"G92 E0\n" +
"G1 X35.558 Y108.897 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X35.336 Y108.128 E2.01649\n" +
"G1 X35.178 Y107.229 E2.03531\n" +
"G1 X35.140 Y106.896 E2.04221\n" +
"G1 X35.066 Y105.248 E2.07621\n" +
"G1 X35.215 Y102.489 E2.13317\n" +
"G1 X35.819 Y98.515 E2.21601\n" +
"G1 X36.719 Y95.008 E2.29062\n" +
"G1 X38.086 Y91.320 E2.37169\n" +
"G1 X40.191 Y86.974 E2.47121\n" +
"G1 X40.531 Y86.392 E2.48511\n" +
"G1 X40.814 Y86.006 E2.49497\n" +
"G1 X41.875 Y84.845 E2.52739\n" +
"G1 X42.743 Y83.992 E2.55247\n" +
"G1 X43.215 Y83.594 E2.56519\n" +
"G1 X43.668 Y83.280 E2.57656\n" +
"G1 X44.234 Y82.970 E2.58987\n" +
"G1 X45.039 Y82.634 E2.60784\n" +
"G1 X46.455 Y82.203 E2.63835\n" +
"G1 X48.823 Y81.725 E2.68814\n" +
"G1 X51.778 Y81.432 E2.74934\n" +
"G1 X58.379 Y81.379 E2.88540\n" +
"G1 X65.747 Y81.671 E3.03737\n" +
"G1 X71.410 Y82.412 E3.15507\n" +
"G1 X78.422 Y84.004 E3.30328\n" +
"G1 X80.448 Y84.639 E3.34704\n" +
"G1 X81.761 Y85.157 E3.37613\n" +
"G1 X82.643 Y85.603 E3.39650\n" +
"G1 X83.459 Y86.122 E3.41642\n" +
"G1 X84.601 Y86.999 E3.44610\n" +
"G1 X85.342 Y87.686 E3.46693\n" +
"G1 X85.965 Y88.388 E3.48626\n" +
"G1 X86.544 Y89.190 E3.50665\n" +
"G1 X87.053 Y90.069 E3.52759\n" +
"G1 X87.842 Y91.850 E3.56775\n" +
"G1 X89.413 Y96.079 E3.66072\n" +
"G1 X90.822 Y101.587 E3.77791\n" +
"G1 X91.243 Y104.292 E3.83432\n" +
"G1 X91.377 Y106.309 E3.87598\n" +
"G1 X91.346 Y107.717 E3.90501\n" +
"G1 X91.199 Y108.970 E3.93101\n" +
"G1 X90.973 Y110.012 E3.95298\n" +
"G1 X90.718 Y110.806 E3.97017\n" +
"G1 X90.427 Y111.476 E3.98522\n" +
"G1 X90.051 Y112.148 E4.00110\n" +
"G1 X89.587 Y112.810 E4.01775\n" +
"G1 X89.047 Y113.428 E4.03468\n" +
"G1 X88.461 Y113.971 E4.05115\n" +
"G1 X87.800 Y114.471 E4.06822\n" +
"G1 X87.068 Y114.917 E4.08589\n" +
"G1 X86.280 Y115.293 E4.10389\n" +
"G1 X85.032 Y115.745 E4.13124\n" +
"G1 X80.665 Y116.964 E4.22468\n" +
"G1 X77.701 Y117.539 E4.28692\n" +
"G1 X72.852 Y117.978 E4.38726\n" +
"G1 X65.192 Y118.022 E4.54514\n" +
"G1 X58.856 Y117.831 E4.67579\n" +
"G1 X53.006 Y117.141 E4.79720\n" +
"G1 X47.442 Y115.985 E4.91432\n" +
"G1 X42.598 Y114.571 E5.01833\n" +
"G1 X40.034 Y113.538 E5.07530\n" +
"G1 X38.314 Y112.682 E5.11489\n" +
"G1 X37.866 Y112.424 E5.12556\n" +
"G1 X37.530 Y112.194 E5.13394\n" +
"G1 X37.252 Y111.959 E5.14145\n" +
"G1 X37.042 Y111.741 E5.14768\n" +
"G1 X36.847 Y111.495 E5.15416\n" +
"G1 X36.582 Y111.086 E5.16420\n" +
"G1 X35.868 Y109.682 E5.19666\n" +
"G1 X35.585 Y108.967 E5.21251\n" +
"G1 X35.526 Y108.893 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X44.908 Y82.088 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X45.990 Y81.723 E2.03080\n" +
"G1 X47.143 Y81.465 E2.06268\n" +
"G1 X47.554 Y81.409 E2.07384\n" +
"G1 X46.318 Y81.659 E2.10785\n" +
"G1 X44.980 Y82.066 E2.14557\n" +
"G1 E0.14557 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.365 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X41.471 Y84.456 E2.03381\n" +
"G1 X40.380 Y85.651 E2.07747\n" +
"G1 X40.062 Y86.084 E2.09198\n" +
"G1 X39.696 Y86.710 E2.11153\n" +
"G1 X38.398 Y89.390 E2.19185\n" +
"G1 X39.018 Y87.912 E2.23507\n" +
"G1 X39.233 Y87.464 E2.24849\n" +
"G1 X39.755 Y86.486 E2.27839\n" +
"G1 X40.203 Y85.784 E2.30086\n" +
"G1 X40.759 Y85.065 E2.32538\n" +
"G1 X41.400 Y84.384 E2.35061\n" +
"G1 X42.112 Y83.756 E2.37623\n" +
"G1 X42.397 Y83.548 E2.38574\n" +
"G1 E0.38574 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.119 Y103.528 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X27.244 Y103.445 E2.00310\n" +
"G1 X27.445 Y103.395 E2.00736\n" +
"G1 X27.663 Y103.370 E2.01188\n" +
"G1 X28.189 Y103.358 E2.02274\n" +
"G1 X29.575 Y103.429 E2.05133\n" +
"G1 X31.263 Y103.546 E2.08620\n" +
"G1 X31.963 Y103.538 E2.10064\n" +
"G1 X32.186 Y103.506 E2.10528\n" +
"G1 X32.465 Y103.417 E2.11132\n" +
"G1 X32.709 Y103.262 E2.11728\n" +
"G1 X32.916 Y103.046 E2.12344\n" +
"G1 X33.205 Y102.570 E2.13492\n" +
"G1 X33.513 Y101.851 E2.15105\n" +
"G1 X34.594 Y98.583 E2.22197\n" +
"G1 X36.037 Y94.040 E2.32022\n" +
"G1 X36.794 Y91.953 E2.36598\n" +
"G1 X38.056 Y88.726 E2.43739\n" +
"G1 X38.507 Y87.680 E2.46088\n" +
"G1 X38.733 Y87.210 E2.47161\n" +
"G1 X39.271 Y86.203 E2.49514\n" +
"G1 X39.744 Y85.461 E2.51328\n" +
"G1 X40.332 Y84.700 E2.53311\n" +
"G1 X41.010 Y83.981 E2.55347\n" +
"G1 X41.761 Y83.318 E2.57412\n" +
"G1 X42.757 Y82.592 E2.59952\n" +
"G1 X43.696 Y82.030 E2.62208\n" +
"G1 X44.101 Y81.828 E2.63141\n" +
"G1 X44.814 Y81.518 E2.64743\n" +
"G1 X45.841 Y81.182 E2.66971\n" +
"G1 X47.044 Y80.913 E2.69511\n" +
"G1 X48.923 Y80.657 E2.73419\n" +
"G1 X50.090 Y80.561 E2.75833\n" +
"G1 X54.151 Y80.387 E2.84209\n" +
"G1 X55.705 Y80.364 E2.87413\n" +
"G1 X63.902 Y80.432 E3.04309\n" +
"G1 X64.929 Y80.469 E3.06427\n" +
"G1 X66.135 Y80.559 E3.08918\n" +
"G1 X68.595 Y80.696 E3.13996\n" +
"G1 X70.956 Y80.917 E3.18883\n" +
"G1 X71.885 Y81.033 E3.20814\n" +
"G1 X76.089 Y81.725 E3.29595\n" +
"G1 X76.625 Y81.835 E3.30723\n" +
"G1 X79.472 Y82.542 E3.36769\n" +
"G1 X79.942 Y82.684 E3.37781\n" +
"G1 X81.610 Y83.295 E3.41442\n" +
"G1 X82.930 Y83.929 E3.44461\n" +
"G1 X84.226 Y84.692 E3.47559\n" +
"G1 X84.983 Y85.215 E3.49455\n" +
"G1 X85.426 Y85.549 E3.50599\n" +
"G1 X86.227 Y86.254 E3.52799\n" +
"G1 X86.867 Y86.935 E3.54725\n" +
"G1 X87.415 Y87.649 E3.56580\n" +
"G1 X88.030 Y88.647 E3.58995\n" +
"G1 X88.171 Y88.911 E3.59613\n" +
"G1 X88.873 Y90.420 E3.63042\n" +
"G1 X90.283 Y94.291 E3.71533\n" +
"G1 X92.327 Y101.035 E3.86058\n" +
"G1 X93.406 Y105.156 E3.94837\n" +
"G1 X93.662 Y106.053 E3.96759\n" +
"G1 X93.928 Y106.764 E3.98325\n" +
"G1 X94.130 Y107.189 E3.99294\n" +
"G1 X94.354 Y107.569 E4.00205\n" +
"G1 X94.547 Y107.838 E4.00886\n" +
"G1 X94.806 Y108.134 E4.01697\n" +
"G1 X95.096 Y108.406 E4.02516\n" +
"G1 X95.329 Y108.590 E4.03128\n" +
"G1 X95.815 Y108.901 E4.04317\n" +
"G1 X96.265 Y109.123 E4.05351\n" +
"G1 X96.773 Y109.316 E4.06472\n" +
"G1 X97.388 Y109.489 E4.07788\n" +
"G1 X98.124 Y109.627 E4.09331\n" +
"G1 X98.845 Y109.701 E4.10825\n" +
"G1 X100.276 Y109.718 E4.13776\n" +
"G1 X101.158 Y109.701 E4.15594\n" +
"G1 X102.128 Y109.586 E4.17607\n" +
"G1 X102.612 Y109.489 E4.18625\n" +
"G1 X103.027 Y109.378 E4.19509\n" +
"G1 X103.680 Y109.146 E4.20937\n" +
"G1 X104.183 Y108.902 E4.22089\n" +
"G1 X104.569 Y108.664 E4.23024\n" +
"G1 X104.905 Y108.406 E4.23898\n" +
"G1 X105.313 Y108.006 E4.25075\n" +
"G1 X105.677 Y107.522 E4.26324\n" +
"G1 X105.884 Y107.163 E4.27176\n" +
"G1 X106.072 Y106.764 E4.28087\n" +
"G1 X106.338 Y106.053 E4.29652\n" +
"G1 X106.594 Y105.156 E4.31574\n" +
"G1 X107.673 Y101.035 E4.40353\n" +
"G1 X109.717 Y94.291 E4.54878\n" +
"G1 X111.186 Y90.277 E4.63688\n" +
"G1 X111.679 Y89.219 E4.66092\n" +
"G1 X111.972 Y88.643 E4.67425\n" +
"G1 X112.464 Y87.828 E4.69387\n" +
"G1 X113.033 Y87.055 E4.71365\n" +
"G1 X113.335 Y86.707 E4.72314\n" +
"G1 X113.773 Y86.253 E4.73614\n" +
"G1 X114.171 Y85.891 E4.74724\n" +
"G1 X114.815 Y85.363 E4.76440\n" +
"G1 X115.775 Y84.692 E4.78854\n" +
"G1 X117.070 Y83.929 E4.81951\n" +
"G1 X118.390 Y83.295 E4.84970\n" +
"G1 X120.058 Y82.684 E4.88630\n" +
"G1 X120.528 Y82.542 E4.89642\n" +
"G1 X123.375 Y81.835 E4.95688\n" +
"G1 X123.911 Y81.725 E4.96817\n" +
"G1 X128.115 Y81.033 E5.05598\n" +
"G1 X129.044 Y80.917 E5.07528\n" +
"G1 X131.408 Y80.696 E5.12421\n" +
"G1 X132.611 Y80.607 E5.14907\n" +
"G1 X135.068 Y80.469 E5.19979\n" +
"G1 X136.098 Y80.432 E5.22102\n" +
"G1 X144.295 Y80.364 E5.38998\n" +
"G1 X145.849 Y80.387 E5.42201\n" +
"G1 X149.911 Y80.561 E5.50581\n" +
"G1 X151.077 Y80.657 E5.52992\n" +
"G1 X152.956 Y80.913 E5.56902\n" +
"G1 X153.931 Y81.126 E5.58957\n" +
"G1 X154.397 Y81.249 E5.59951\n" +
"G1 X155.185 Y81.517 E5.61666\n" +
"G1 X155.899 Y81.828 E5.63272\n" +
"G1 X156.304 Y82.030 E5.64204\n" +
"G1 X157.243 Y82.592 E5.66460\n" +
"G1 X158.241 Y83.320 E5.69005\n" +
"G1 X158.988 Y83.980 E5.71060\n" +
"G1 X159.668 Y84.700 E5.73101\n" +
"G1 X160.256 Y85.461 E5.75084\n" +
"G1 X160.729 Y86.203 E5.76898\n" +
"G1 X161.267 Y87.210 E5.79251\n" +
"G1 X161.493 Y87.680 E5.80324\n" +
"G1 X161.944 Y88.726 E5.82673\n" +
"G1 X163.206 Y91.953 E5.89814\n" +
"G1 X163.963 Y94.042 E5.94393\n" +
"G1 X164.473 Y95.590 E5.97752\n" +
"G1 X166.091 Y100.693 E6.08786\n" +
"G1 X166.487 Y101.852 E6.11310\n" +
"G1 X166.795 Y102.569 E6.12918\n" +
"G1 X167.062 Y103.015 E6.13991\n" +
"G1 X167.289 Y103.260 E6.14679\n" +
"G1 X167.535 Y103.417 E6.15280\n" +
"G1 X167.818 Y103.507 E6.15892\n" +
"G1 X168.193 Y103.551 E6.16670\n" +
"G1 X168.739 Y103.545 E6.17797\n" +
"G1 X171.689 Y103.357 E6.23889\n" +
"G1 X172.501 Y103.387 E6.25563\n" +
"G1 X172.761 Y103.447 E6.26114\n" +
"G1 X172.870 Y103.516 E6.26378\n" +
"G1 X172.927 Y103.624 E6.26631\n" +
"G1 X172.959 Y103.946 E6.27298\n" +
"G1 X172.948 Y106.852 E6.33287\n" +
"G1 X173.019 Y110.545 E6.40901\n" +
"G1 X173.154 Y112.342 E6.44613\n" +
"G1 X173.142 Y112.430 E6.44797\n" +
"G1 X173.084 Y112.499 E6.44982\n" +
"G1 X172.850 Y112.613 E6.45518\n" +
"G1 X172.457 Y112.734 E6.46366\n" +
"G1 X171.105 Y113.094 E6.49249\n" +
"G1 X167.462 Y114.010 E6.56992\n" +
"G1 X162.940 Y115.328 E6.66699\n" +
"G1 X160.303 Y116.005 E6.72311\n" +
"G1 X159.011 Y116.298 E6.75040\n" +
"G1 X150.955 Y117.915 E6.91976\n" +
"G1 X148.824 Y118.300 E6.96439\n" +
"G1 X147.472 Y118.514 E6.99260\n" +
"G1 X144.191 Y118.986 E7.06091\n" +
"G1 X143.509 Y119.053 E7.07505\n" +
"G1 X135.383 Y119.642 E7.24297\n" +
"G1 X132.323 Y119.642 E7.30603\n" +
"G1 X127.156 Y119.544 E7.41255\n" +
"G1 X126.776 Y119.518 E7.42039\n" +
"G1 X121.891 Y118.902 E7.52187\n" +
"G1 X116.936 Y117.909 E7.62603\n" +
"G1 X115.444 Y117.540 E7.65770\n" +
"G1 X114.876 Y117.384 E7.66984\n" +
"G1 X113.840 Y117.049 E7.69228\n" +
"G1 X109.984 Y115.767 E7.77604\n" +
"G1 X107.013 Y114.859 E7.84007\n" +
"G1 X104.973 Y114.419 E7.88308\n" +
"G1 X103.546 Y114.168 E7.91293\n" +
"G1 X102.186 Y114.012 E7.94116\n" +
"G1 X101.525 Y113.966 E7.95481\n" +
"G1 X100.928 Y113.955 E7.96712\n" +
"G1 X98.762 Y113.956 E8.01176\n" +
"G1 X97.414 Y114.052 E8.03960\n" +
"G1 X96.455 Y114.168 E8.05952\n" +
"G1 X95.665 Y114.295 E8.07600\n" +
"G1 X92.988 Y114.859 E8.13240\n" +
"G1 X90.016 Y115.767 E8.19644\n" +
"G1 X86.160 Y117.049 E8.28020\n" +
"G1 X85.124 Y117.384 E8.30264\n" +
"G1 X84.556 Y117.540 E8.31478\n" +
"G1 X83.063 Y117.910 E8.34648\n" +
"G1 X81.809 Y118.176 E8.37289\n" +
"G1 X78.109 Y118.902 E8.45060\n" +
"G1 X73.224 Y119.518 E8.55209\n" +
"G1 X72.844 Y119.544 E8.55994\n" +
"G1 X67.677 Y119.642 E8.66645\n" +
"G1 X64.617 Y119.642 E8.72952\n" +
"G1 X56.491 Y119.053 E8.89744\n" +
"G1 X55.811 Y118.986 E8.91153\n" +
"G1 X54.460 Y118.772 E8.93971\n" +
"G1 X51.175 Y118.300 E9.00812\n" +
"G1 X47.763 Y117.670 E9.07963\n" +
"G1 X40.989 Y116.298 E9.22208\n" +
"G1 X39.697 Y116.005 E9.24937\n" +
"G1 X37.060 Y115.328 E9.30550\n" +
"G1 X32.540 Y114.011 E9.40253\n" +
"G1 X31.242 Y113.665 E9.43021\n" +
"G1 X27.541 Y112.734 E9.50886\n" +
"G1 X27.146 Y112.612 E9.51739\n" +
"G1 X26.926 Y112.510 E9.52237\n" +
"G1 X26.858 Y112.430 E9.52455\n" +
"G1 X26.846 Y112.342 E9.52638\n" +
"G1 X26.981 Y110.545 E9.56351\n" +
"G1 X27.052 Y106.852 E9.63964\n" +
"G1 X27.042 Y103.894 E9.70062\n" +
"G1 X27.086 Y103.596 E9.70682\n" +
"G1 X27.266 Y103.559 F7800.000\n" +
"G1 E7.70682 F2400.00000\n" +
"G92 E0\n" +
"G1 X29.126 Y104.980 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X28.631 Y105.475 E2.01926\n" +
"G1 X28.634 Y106.389 E2.04444\n" +
"G1 X29.994 Y105.030 E2.09738\n" +
"G1 X30.852 Y105.089 E2.12106\n" +
"G1 X28.633 Y107.308 E2.20747\n" +
"G1 X28.615 Y108.243 E2.23323\n" +
"G1 X31.730 Y105.128 E2.35454\n" +
"G1 X32.351 Y105.122 E2.37164\n" +
"G1 X32.705 Y105.071 E2.38148\n" +
"G1 X28.597 Y109.179 E2.54145\n" +
"G1 X28.579 Y110.114 E2.56721\n" +
"G1 X33.489 Y105.204 E2.75841\n" +
"G1 X33.528 Y106.082 E2.78262\n" +
"G1 X28.551 Y111.060 E2.97644\n" +
"G1 X28.525 Y111.402 E2.98587\n" +
"G1 X29.006 Y111.523 E2.99951\n" +
"G1 X33.578 Y106.950 E3.17755\n" +
"G1 X33.699 Y107.746 E3.19972\n" +
"G1 X29.739 Y111.707 E3.35394\n" +
"G1 X30.472 Y111.891 E3.37476\n" +
"G1 X33.857 Y108.507 E3.50656\n" +
"G1 X34.061 Y109.220 E3.52699\n" +
"G1 X31.205 Y112.076 E3.63820\n" +
"G1 X31.932 Y112.266 E3.65890\n" +
"G1 X34.321 Y109.878 E3.75191\n" +
"G1 X34.443 Y110.188 E3.76110\n" +
"G1 X34.607 Y110.509 E3.77102\n" +
"G1 X32.656 Y112.459 E3.84696\n" +
"G1 X33.369 Y112.664 E3.86738\n" +
"G1 X34.916 Y111.117 E3.92760\n" +
"G1 X35.225 Y111.725 E3.94639\n" +
"G1 X34.080 Y112.871 E3.99099\n" +
"G1 X34.790 Y113.078 E4.01136\n" +
"G1 X35.583 Y112.285 E4.04225\n" +
"G1 X35.995 Y112.790 E4.06021\n" +
"G1 X35.501 Y113.285 E4.07946\n" +
"G1 E2.07946 F2400.00000\n" +
"G92 E0\n" +
"G1 X93.299 Y108.649 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X92.805 Y109.143 E2.01917\n" +
"G1 X92.761 Y109.519 E2.02954\n" +
"G1 X92.599 Y110.263 E2.05041\n" +
"G1 X93.718 Y109.144 E2.09380\n" +
"G1 X94.192 Y109.584 E2.11152\n" +
"G1 X92.240 Y111.536 E2.18721\n" +
"G1 X92.187 Y111.699 E2.19190\n" +
"G1 X91.824 Y112.537 E2.21694\n" +
"G1 X91.407 Y113.283 E2.24038\n" +
"G1 X94.720 Y109.970 E2.36885\n" +
"G1 X95.161 Y110.252 E2.38322\n" +
"G1 X95.288 Y110.315 E2.38710\n" +
"G1 X92.268 Y113.335 E2.50420\n" +
"G1 X93.427 Y113.089 E2.53668\n" +
"G1 X95.910 Y110.606 E2.63296\n" +
"G1 X96.582 Y110.848 E2.65253\n" +
"G1 X94.585 Y112.846 E2.72997\n" +
"G1 X95.084 Y112.740 E2.74396\n" +
"G1 X95.703 Y112.641 E2.76115\n" +
"G1 X97.301 Y111.043 E2.82311\n" +
"G1 X98.071 Y111.187 E2.84458\n" +
"G1 X96.753 Y112.504 E2.89566\n" +
"G1 X97.750 Y112.421 E2.92308\n" +
"G1 X98.896 Y111.275 E2.96752\n" +
"G1 X99.791 Y111.294 E2.99206\n" +
"G1 X98.714 Y112.371 E3.03383\n" +
"G1 X99.628 Y112.371 E3.05890\n" +
"G1 X100.699 Y111.299 E3.10044\n" +
"G1 X101.642 Y111.270 E3.12630\n" +
"G1 X100.542 Y112.370 E3.16896\n" +
"G1 X101.438 Y112.388 E3.19353\n" +
"G1 X102.680 Y111.146 E3.24168\n" +
"G1 X103.277 Y111.026 E3.25838\n" +
"G1 X103.880 Y110.860 E3.27553\n" +
"G1 X102.284 Y112.455 E3.33739\n" +
"G1 X103.104 Y112.549 E3.36000\n" +
"G1 X105.515 Y110.138 E3.45349\n" +
"G1 X105.857 Y109.926 E3.46453\n" +
"G1 X106.355 Y109.545 E3.48172\n" +
"G1 X106.942 Y108.970 E3.50425\n" +
"G1 X107.193 Y108.634 E3.51575\n" +
"G1 X107.239 Y109.025 E3.52652\n" +
"G1 X107.293 Y109.273 E3.53349\n" +
"G1 X103.905 Y112.662 E3.66489\n" +
"G1 X104.681 Y112.799 E3.68652\n" +
"G1 X107.456 Y110.024 E3.79412\n" +
"G1 X107.503 Y110.240 E3.80018\n" +
"G1 X107.661 Y110.732 E3.81437\n" +
"G1 X105.446 Y112.948 E3.90027\n" +
"G1 X106.198 Y113.110 E3.92135\n" +
"G1 X107.901 Y111.407 E3.98738\n" +
"G1 X108.177 Y112.044 E4.00642\n" +
"G1 X106.949 Y113.272 E4.05404\n" +
"G1 X107.228 Y113.332 E4.06184\n" +
"G1 X107.668 Y113.467 E4.07447\n" +
"G1 X108.505 Y112.630 E4.10693\n" +
"G1 X108.643 Y112.876 E4.11466\n" +
"G1 X108.861 Y113.187 E4.12509\n" +
"G1 X108.366 Y113.682 E4.14426\n" +
"G1 E2.14426 F2400.00000\n" +
"G92 E0\n" +
"G1 X166.512 Y105.339 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X167.007 Y104.844 E2.02010\n" +
"G1 X167.742 Y105.067 E2.04218\n" +
"G1 X166.483 Y106.326 E2.09336\n" +
"G1 X166.435 Y107.332 E2.12231\n" +
"G1 X168.633 Y105.133 E2.21169\n" +
"G1 X169.053 Y105.130 E2.22375\n" +
"G1 X169.631 Y105.093 E2.24041\n" +
"G1 X166.261 Y108.463 E2.37742\n" +
"G1 X166.204 Y108.786 E2.38683\n" +
"G1 X165.920 Y109.762 E2.41606\n" +
"G1 X170.653 Y105.029 E2.60847\n" +
"G1 X171.370 Y104.984 E2.62911\n" +
"G1 X171.369 Y105.272 E2.63737\n" +
"G1 X165.141 Y111.499 E2.89053\n" +
"G1 X164.760 Y112.249 E2.91472\n" +
"G1 X164.395 Y112.812 E2.93399\n" +
"G1 X164.005 Y113.285 E2.95163\n" +
"G1 X164.440 Y113.158 E2.96467\n" +
"G1 X171.365 Y106.233 E3.24618\n" +
"G1 X171.374 Y107.182 E3.27345\n" +
"G1 X165.792 Y112.764 E3.50039\n" +
"G1 X167.124 Y112.390 E3.54015\n" +
"G1 X171.392 Y108.122 E3.71368\n" +
"G1 X171.410 Y109.062 E3.74071\n" +
"G1 X168.410 Y112.063 E3.86270\n" +
"G1 X169.695 Y111.735 E3.90084\n" +
"G1 X171.428 Y110.002 E3.97131\n" +
"G1 X171.475 Y110.913 E3.99753\n" +
"G1 X170.981 Y111.407 E4.01763\n" +
"G1 Z1.850 F7800.000\n" +
"G1 E2.01763 F2400.00000\n" +
"G92 E0\n" +
"G1 X35.136 Y103.961 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X35.215 Y102.489 E2.03038\n" +
"G1 X35.819 Y98.515 E2.11323\n" +
"G1 X36.719 Y95.008 E2.18784\n" +
"G1 X38.086 Y91.320 E2.26891\n" +
"G1 X40.191 Y86.974 E2.36843\n" +
"G1 X40.531 Y86.392 E2.38233\n" +
"G1 X40.814 Y86.006 E2.39219\n" +
"G1 X41.875 Y84.845 E2.42461\n" +
"G1 X42.743 Y83.992 E2.44969\n" +
"G1 X43.215 Y83.594 E2.46241\n" +
"G1 X43.668 Y83.280 E2.47378\n" +
"G1 X44.234 Y82.970 E2.48709\n" +
"G1 X45.039 Y82.634 E2.50506\n" +
"G1 X46.455 Y82.203 E2.53557\n" +
"G1 X48.823 Y81.725 E2.58536\n" +
"G1 X51.778 Y81.432 E2.64656\n" +
"G1 X58.379 Y81.379 E2.78262\n" +
"G1 X65.747 Y81.671 E2.93459\n" +
"G1 X71.410 Y82.412 E3.05229\n" +
"G1 X78.422 Y84.004 E3.20050\n" +
"G1 X80.448 Y84.639 E3.24425\n" +
"G1 X81.761 Y85.157 E3.27335\n" +
"G1 X82.643 Y85.603 E3.29372\n" +
"G1 X83.459 Y86.122 E3.31364\n" +
"G1 X84.601 Y86.999 E3.34332\n" +
"G1 X85.342 Y87.686 E3.36415\n" +
"G1 X85.965 Y88.388 E3.38348\n" +
"G1 X86.544 Y89.190 E3.40387\n" +
"G1 X87.053 Y90.069 E3.42481\n" +
"G1 X87.842 Y91.850 E3.46497\n" +
"G1 X89.413 Y96.079 E3.55794\n" +
"G1 X90.822 Y101.587 E3.67512\n" +
"G1 X91.243 Y104.292 E3.73154\n" +
"G1 X91.377 Y106.309 E3.77320\n" +
"G1 X91.346 Y107.717 E3.80223\n" +
"G1 X91.199 Y108.970 E3.82823\n" +
"G1 X90.973 Y110.012 E3.85020\n" +
"G1 X90.718 Y110.806 E3.86739\n" +
"G1 X90.427 Y111.476 E3.88244\n" +
"G1 X90.051 Y112.148 E3.89832\n" +
"G1 X89.587 Y112.810 E3.91497\n" +
"G1 X89.047 Y113.428 E3.93190\n" +
"G1 X88.461 Y113.971 E3.94837\n" +
"G1 X87.800 Y114.471 E3.96544\n" +
"G1 X87.068 Y114.917 E3.98311\n" +
"G1 X86.280 Y115.293 E4.00111\n" +
"G1 X85.032 Y115.745 E4.02846\n" +
"G1 X80.665 Y116.964 E4.12190\n" +
"G1 X77.701 Y117.539 E4.18414\n" +
"G1 X72.852 Y117.978 E4.28448\n" +
"G1 X65.192 Y118.022 E4.44236\n" +
"G1 X58.856 Y117.831 E4.57301\n" +
"G1 X53.006 Y117.141 E4.69441\n" +
"G1 X47.442 Y115.985 E4.81154\n" +
"G1 X42.598 Y114.571 E4.91555\n" +
"G1 X40.034 Y113.538 E4.97251\n" +
"G1 X38.314 Y112.682 E5.01211\n" +
"G1 X37.866 Y112.424 E5.02278\n" +
"G1 X37.530 Y112.194 E5.03116\n" +
"G1 X37.252 Y111.959 E5.03866\n" +
"G1 X37.042 Y111.741 E5.04490\n" +
"G1 X36.847 Y111.495 E5.05138\n" +
"G1 X36.582 Y111.086 E5.06142\n" +
"G1 X35.868 Y109.682 E5.09388\n" +
"G1 X35.546 Y108.868 E5.11193\n" +
"G1 X35.336 Y108.128 E5.12776\n" +
"G1 X35.178 Y107.229 E5.14659\n" +
"G1 X35.140 Y106.896 E5.15349\n" +
"G1 X35.066 Y105.248 E5.18749\n" +
"G1 X35.132 Y104.036 E5.21251\n" +
"G1 X34.712 Y103.696 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X44.894 Y82.093 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X45.943 Y81.736 E2.02990\n" +
"G1 X47.091 Y81.475 E2.06165\n" +
"G1 X47.521 Y81.416 E2.07338\n" +
"G1 X46.318 Y81.659 E2.10650\n" +
"G1 X44.966 Y82.071 E2.14464\n" +
"G1 E0.14464 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.365 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X41.471 Y84.456 E2.03381\n" +
"G1 X40.380 Y85.651 E2.07747\n" +
"G1 X40.062 Y86.084 E2.09198\n" +
"G1 X39.696 Y86.710 E2.11153\n" +
"G1 X38.434 Y89.315 E2.18962\n" +
"G1 X38.657 Y88.747 E2.20610\n" +
"G1 X39.196 Y87.542 E2.24170\n" +
"G1 X39.738 Y86.517 E2.27298\n" +
"G1 X40.203 Y85.784 E2.29641\n" +
"G1 X40.759 Y85.065 E2.32092\n" +
"G1 X41.400 Y84.384 E2.34614\n" +
"G1 X42.120 Y83.750 E2.37203\n" +
"G1 X42.406 Y83.541 E2.38159\n" +
"G1 E0.38159 F2400.00000\n" +
"G92 E0\n" +
"G1 X159.818 Y114.887 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.457 Y114.630 E2.01857\n" +
"G1 X162.252 Y113.737 E2.07266\n" +
"G1 X162.772 Y113.437 E2.08887\n" +
"G1 X163.195 Y113.147 E2.10269\n" +
"G1 X163.567 Y112.833 E2.11582\n" +
"G1 X163.860 Y112.528 E2.12723\n" +
"G1 X164.126 Y112.193 E2.13876\n" +
"G1 X164.456 Y111.684 E2.15514\n" +
"G1 X165.224 Y110.174 E2.20084\n" +
"G1 X165.588 Y109.252 E2.22758\n" +
"G1 X165.831 Y108.396 E2.25157\n" +
"G1 X166.007 Y107.400 E2.27887\n" +
"G1 X166.053 Y106.990 E2.28999\n" +
"G1 X166.131 Y105.243 E2.33718\n" +
"G1 X166.045 Y103.650 E2.38022\n" +
"G1 X166.085 Y103.716 E2.38228\n" +
"G1 X166.530 Y104.199 E2.40001\n" +
"G1 X167.033 Y104.512 E2.41601\n" +
"G1 X167.562 Y104.682 E2.43099\n" +
"G1 X168.128 Y104.747 E2.44637\n" +
"G1 X168.722 Y104.743 E2.46237\n" +
"G1 X171.761 Y104.555 E2.54451\n" +
"G1 X171.752 Y106.601 E2.59970\n" +
"G1 X171.827 Y110.674 E2.70960\n" +
"G1 X171.901 Y111.644 E2.73585\n" +
"G1 X169.811 Y112.201 E2.79418\n" +
"G1 X166.838 Y112.949 E2.87691\n" +
"G1 X163.159 Y114.021 E2.98027\n" +
"G1 X159.891 Y114.868 E3.07135\n" +
"G1 E1.07135 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.567 Y114.701 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.265 Y113.688 E2.09318\n" +
"G1 X105.546 Y113.317 E2.14061\n" +
"G1 X104.396 Y113.093 E2.17223\n" +
"G1 X103.705 Y112.982 E2.19112\n" +
"G1 X102.172 Y112.812 E2.23273\n" +
"G1 X101.574 Y112.770 E2.24890\n" +
"G1 X100.761 Y112.759 E2.27083\n" +
"G1 X98.675 Y112.761 E2.32711\n" +
"G1 X97.844 Y112.810 E2.34955\n" +
"G1 X96.288 Y112.983 E2.39178\n" +
"G1 X95.604 Y113.093 E2.41048\n" +
"G1 X94.454 Y113.317 E2.44209\n" +
"G1 X92.735 Y113.688 E2.48953\n" +
"G1 X89.433 Y114.701 E2.58271\n" +
"G1 X89.906 Y114.263 E2.60012\n" +
"G1 X90.530 Y113.549 E2.62570\n" +
"G1 X91.066 Y112.785 E2.65087\n" +
"G1 X91.501 Y112.007 E2.67491\n" +
"G1 X91.839 Y111.227 E2.69783\n" +
"G1 X92.129 Y110.322 E2.72348\n" +
"G1 X92.380 Y109.167 E2.75536\n" +
"G1 X92.541 Y107.800 E2.79250\n" +
"G1 X92.568 Y106.544 E2.82640\n" +
"G1 X92.842 Y107.268 E2.84729\n" +
"G1 X93.080 Y107.765 E2.86214\n" +
"G1 X93.364 Y108.243 E2.87715\n" +
"G1 X93.615 Y108.589 E2.88869\n" +
"G1 X93.944 Y108.966 E2.90219\n" +
"G1 X94.322 Y109.321 E2.91618\n" +
"G1 X94.749 Y109.648 E2.93068\n" +
"G1 X95.231 Y109.947 E2.94598\n" +
"G1 X95.787 Y110.220 E2.96269\n" +
"G1 X96.398 Y110.453 E2.98033\n" +
"G1 X97.115 Y110.655 E3.00044\n" +
"G1 X97.954 Y110.812 E3.02345\n" +
"G1 X98.816 Y110.899 E3.04683\n" +
"G1 X100.447 Y110.912 E3.09084\n" +
"G1 X101.201 Y110.898 E3.11118\n" +
"G1 X102.253 Y110.779 E3.13972\n" +
"G1 X102.885 Y110.655 E3.15711\n" +
"G1 X103.433 Y110.505 E3.17243\n" +
"G1 X104.158 Y110.244 E3.19322\n" +
"G1 X104.766 Y109.948 E3.21147\n" +
"G1 X105.251 Y109.648 E3.22685\n" +
"G1 X105.692 Y109.310 E3.24183\n" +
"G1 X106.226 Y108.785 E3.26205\n" +
"G1 X106.694 Y108.154 E3.28323\n" +
"G1 X106.949 Y107.709 E3.29708\n" +
"G1 X107.159 Y107.265 E3.31033\n" +
"G1 X107.432 Y106.543 E3.33113\n" +
"G1 X107.459 Y107.800 E3.36503\n" +
"G1 X107.620 Y109.167 E3.40216\n" +
"G1 X107.871 Y110.322 E3.43405\n" +
"G1 X108.161 Y111.227 E3.45970\n" +
"G1 X108.499 Y112.007 E3.48262\n" +
"G1 X108.934 Y112.785 E3.50666\n" +
"G1 X109.470 Y113.549 E3.53183\n" +
"G1 X110.094 Y114.263 E3.55741\n" +
"G1 X110.512 Y114.650 E3.57280\n" +
"G1 E1.57280 F2400.00000\n" +
"G92 E0\n" +
"G1 X40.182 Y114.887 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X36.841 Y114.021 E2.09310\n" +
"G1 X33.173 Y112.952 E2.19618\n" +
"G1 X30.884 Y112.341 E2.26008\n" +
"G1 X28.099 Y111.640 E2.33754\n" +
"G1 X28.173 Y110.674 E2.36368\n" +
"G1 X28.248 Y106.602 E2.47356\n" +
"G1 X28.242 Y104.557 E2.52871\n" +
"G1 X29.358 Y104.613 E2.55887\n" +
"G1 X31.288 Y104.744 E2.61106\n" +
"G1 X32.021 Y104.736 E2.63083\n" +
"G1 X32.452 Y104.677 E2.64258\n" +
"G1 X32.967 Y104.512 E2.65715\n" +
"G1 X33.451 Y104.211 E2.67254\n" +
"G1 X33.863 Y103.789 E2.68844\n" +
"G1 X33.955 Y103.639 E2.69320\n" +
"G1 X33.869 Y105.243 E2.73653\n" +
"G1 X33.947 Y106.990 E2.78372\n" +
"G1 X33.993 Y107.400 E2.79485\n" +
"G1 X34.169 Y108.397 E2.82214\n" +
"G1 X34.412 Y109.252 E2.84613\n" +
"G1 X34.776 Y110.174 E2.87288\n" +
"G1 X35.544 Y111.684 E2.91858\n" +
"G1 X35.874 Y112.193 E2.93495\n" +
"G1 X36.140 Y112.528 E2.94649\n" +
"G1 X36.433 Y112.833 E2.95789\n" +
"G1 X36.805 Y113.147 E2.97102\n" +
"G1 X37.228 Y113.437 E2.98485\n" +
"G1 X37.748 Y113.737 E3.00106\n" +
"G1 X39.543 Y114.630 E3.05515\n" +
"G1 X40.112 Y114.859 E3.07169\n" +
"G1 E1.07169 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.426 Y112.112 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.538 Y110.644 E2.03972\n" +
"G1 X27.613 Y106.597 E2.14891\n" +
"G1 X27.604 Y103.947 E2.22040\n" +
"G1 X27.720 Y103.929 E2.22357\n" +
"G1 X28.464 Y103.924 E2.24363\n" +
"G1 X31.306 Y104.108 E2.32048\n" +
"G1 X31.975 Y104.101 E2.33851\n" +
"G1 X32.311 Y104.055 E2.34768\n" +
"G1 X32.698 Y103.931 E2.35862\n" +
"G1 X33.050 Y103.712 E2.36983\n" +
"G1 X33.358 Y103.397 E2.38171\n" +
"G1 X33.699 Y102.839 E2.39933\n" +
"G1 X34.022 Y102.092 E2.42129\n" +
"G1 X35.365 Y98.031 E2.53667\n" +
"G1 X35.269 Y98.403 E2.54703\n" +
"G1 X34.657 Y102.431 E2.65695\n" +
"G1 X34.505 Y105.246 E2.73298\n" +
"G1 X34.581 Y106.940 E2.77874\n" +
"G1 X34.622 Y107.309 E2.78875\n" +
"G1 X34.789 Y108.254 E2.81464\n" +
"G1 X35.015 Y109.048 E2.83689\n" +
"G1 X35.356 Y109.912 E2.86198\n" +
"G1 X36.096 Y111.366 E2.90598\n" +
"G1 X36.391 Y111.822 E2.92063\n" +
"G1 X36.619 Y112.110 E2.93054\n" +
"G1 X36.868 Y112.369 E2.94022\n" +
"G1 X37.190 Y112.640 E2.95159\n" +
"G1 X37.567 Y112.898 E2.96390\n" +
"G1 X38.049 Y113.176 E2.97892\n" +
"G1 X39.804 Y114.050 E3.03181\n" +
"G1 X42.414 Y115.101 E3.10771\n" +
"G1 X47.306 Y116.529 E3.24519\n" +
"G1 X52.916 Y117.695 E3.39976\n" +
"G1 X58.815 Y118.390 E3.55998\n" +
"G1 X65.185 Y118.583 E3.73192\n" +
"G1 X72.879 Y118.538 E3.93949\n" +
"G1 X77.780 Y118.095 E4.07222\n" +
"G1 X80.794 Y117.510 E4.15506\n" +
"G1 X85.203 Y116.279 E4.27855\n" +
"G1 X86.497 Y115.811 E4.31566\n" +
"G1 X87.335 Y115.410 E4.34072\n" +
"G1 X88.116 Y114.935 E4.36539\n" +
"G1 X88.821 Y114.401 E4.38924\n" +
"G1 X89.449 Y113.819 E4.41234\n" +
"G1 X90.029 Y113.156 E4.43611\n" +
"G1 X90.527 Y112.446 E4.45949\n" +
"G1 X90.930 Y111.725 E4.48179\n" +
"G1 X91.243 Y111.003 E4.50301\n" +
"G1 X91.515 Y110.157 E4.52698\n" +
"G1 X91.753 Y109.062 E4.55720\n" +
"G1 X91.906 Y107.756 E4.59269\n" +
"G1 X91.938 Y106.297 E4.63207\n" +
"G1 X91.800 Y104.230 E4.68794\n" +
"G1 X91.372 Y101.474 E4.76317\n" +
"G1 X89.949 Y95.911 E4.91807\n" +
"G1 X88.361 Y91.639 E5.04102\n" +
"G1 X87.553 Y89.814 E5.09486\n" +
"G1 X87.015 Y88.884 E5.12385\n" +
"G1 X86.403 Y88.037 E5.15206\n" +
"G1 X85.743 Y87.294 E5.17885\n" +
"G1 X84.963 Y86.570 E5.20756\n" +
"G1 X83.780 Y85.663 E5.24777\n" +
"G1 X82.921 Y85.115 E5.27526\n" +
"G1 X81.991 Y84.645 E5.30337\n" +
"G1 X80.635 Y84.110 E5.34270\n" +
"G1 X78.568 Y83.462 E5.40112\n" +
"G1 X71.508 Y81.859 E5.59642\n" +
"G1 X65.795 Y81.112 E5.75187\n" +
"G1 X62.666 Y80.988 E5.83633\n" +
"G1 X64.084 Y81.000 E5.87458\n" +
"G1 X64.899 Y81.029 E5.89656\n" +
"G1 X67.005 Y81.185 E5.95352\n" +
"G1 X68.997 Y81.297 E6.00737\n" +
"G1 X70.895 Y81.475 E6.05878\n" +
"G1 X71.629 Y81.566 E6.07874\n" +
"G1 X76.090 Y82.297 E6.20069\n" +
"G1 X76.501 Y82.382 E6.21201\n" +
"G1 X79.384 Y83.100 E6.29216\n" +
"G1 X79.735 Y83.207 E6.30206\n" +
"G1 X81.369 Y83.802 E6.34897\n" +
"G1 X82.666 Y84.424 E6.38777\n" +
"G1 X83.958 Y85.187 E6.42825\n" +
"G1 X85.074 Y85.986 E6.46526\n" +
"G1 X85.837 Y86.657 E6.49269\n" +
"G1 X86.439 Y87.299 E6.51642\n" +
"G1 X86.956 Y87.971 E6.53931\n" +
"G1 X87.246 Y88.430 E6.55394\n" +
"G1 X87.647 Y89.123 E6.57554\n" +
"G1 X88.390 Y90.720 E6.62307\n" +
"G1 X89.763 Y94.504 E6.73165\n" +
"G1 X91.465 Y100.074 E6.88876\n" +
"G1 X91.784 Y101.176 E6.91972\n" +
"G1 X92.923 Y105.508 E7.04055\n" +
"G1 X93.129 Y106.229 E7.06079\n" +
"G1 X93.427 Y107.018 E7.08353\n" +
"G1 X93.641 Y107.464 E7.09688\n" +
"G1 X93.896 Y107.893 E7.11033\n" +
"G1 X94.113 Y108.193 E7.12032\n" +
"G1 X94.402 Y108.524 E7.13219\n" +
"G1 X94.734 Y108.836 E7.14446\n" +
"G1 X95.111 Y109.124 E7.15727\n" +
"G1 X95.540 Y109.390 E7.17088\n" +
"G1 X96.041 Y109.637 E7.18596\n" +
"G1 X96.597 Y109.849 E7.20202\n" +
"G1 X97.260 Y110.036 E7.22059\n" +
"G1 X98.044 Y110.182 E7.24211\n" +
"G1 X98.851 Y110.263 E7.26398\n" +
"G1 X100.444 Y110.277 E7.30696\n" +
"G1 X101.159 Y110.263 E7.32626\n" +
"G1 X102.155 Y110.150 E7.35331\n" +
"G1 X102.740 Y110.035 E7.36938\n" +
"G1 X103.242 Y109.899 E7.38341\n" +
"G1 X103.911 Y109.658 E7.40259\n" +
"G1 X104.459 Y109.391 E7.41903\n" +
"G1 X104.889 Y109.124 E7.43269\n" +
"G1 X105.274 Y108.829 E7.44577\n" +
"G1 X105.745 Y108.366 E7.46360\n" +
"G1 X106.161 Y107.805 E7.48243\n" +
"G1 X106.385 Y107.414 E7.49459\n" +
"G1 X106.573 Y107.016 E7.50647\n" +
"G1 X106.871 Y106.229 E7.52916\n" +
"G1 X107.077 Y105.508 E7.54940\n" +
"G1 X108.216 Y101.176 E7.67023\n" +
"G1 X108.535 Y100.074 E7.70120\n" +
"G1 X110.237 Y94.504 E7.85831\n" +
"G1 X111.543 Y90.895 E7.96184\n" +
"G1 X111.706 Y90.488 E7.97368\n" +
"G1 X112.467 Y88.908 E8.02096\n" +
"G1 X112.953 Y88.107 E8.04624\n" +
"G1 X113.482 Y87.393 E8.07021\n" +
"G1 X114.169 Y86.651 E8.09748\n" +
"G1 X114.613 Y86.251 E8.11361\n" +
"G1 X115.108 Y85.845 E8.13090\n" +
"G1 X116.045 Y85.185 E8.16180\n" +
"G1 X117.334 Y84.424 E8.20218\n" +
"G1 X118.631 Y83.802 E8.24099\n" +
"G1 X120.265 Y83.207 E8.28790\n" +
"G1 X120.616 Y83.100 E8.29780\n" +
"G1 X123.499 Y82.382 E8.37795\n" +
"G1 X123.910 Y82.297 E8.38927\n" +
"G1 X128.371 Y81.566 E8.51122\n" +
"G1 X129.105 Y81.475 E8.53118\n" +
"G1 X131.010 Y81.296 E8.58280\n" +
"G1 X133.107 Y81.140 E8.63950\n" +
"G1 X135.922 Y81.000 E8.71553\n" +
"G1 X137.334 Y80.988 E8.75363\n" +
"G1 X134.205 Y81.112 E8.83808\n" +
"G1 X128.492 Y81.859 E8.99353\n" +
"G1 X121.432 Y83.462 E9.18883\n" +
"G1 X119.365 Y84.110 E9.24726\n" +
"G1 X118.009 Y84.645 E9.28658\n" +
"G1 X117.079 Y85.115 E9.31469\n" +
"G1 X116.220 Y85.663 E9.34218\n" +
"G1 X115.037 Y86.570 E9.38239\n" +
"G1 X114.257 Y87.294 E9.41110\n" +
"G1 X113.597 Y88.037 E9.43790\n" +
"G1 X112.985 Y88.884 E9.46611\n" +
"G1 X112.447 Y89.814 E9.49509\n" +
"G1 X111.639 Y91.639 E9.54893\n" +
"G1 X110.051 Y95.911 E9.67188\n" +
"G1 X108.628 Y101.474 E9.82679\n" +
"G1 X108.200 Y104.230 E9.90202\n" +
"G1 X108.062 Y106.297 E9.95789\n" +
"G1 X108.094 Y107.756 E9.99726\n" +
"G1 X108.247 Y109.062 E10.03275\n" +
"G1 X108.485 Y110.157 E10.06297\n" +
"G1 X108.757 Y111.003 E10.08694\n" +
"G1 X109.070 Y111.725 E10.10816\n" +
"G1 X109.473 Y112.446 E10.13047\n" +
"G1 X109.971 Y113.156 E10.15384\n" +
"G1 X110.551 Y113.819 E10.17761\n" +
"G1 X111.179 Y114.401 E10.20071\n" +
"G1 X111.884 Y114.935 E10.22457\n" +
"G1 X112.665 Y115.410 E10.24923\n" +
"G1 X113.503 Y115.811 E10.27429\n" +
"G1 X114.797 Y116.279 E10.31140\n" +
"G1 X119.206 Y117.510 E10.43489\n" +
"G1 X122.220 Y118.095 E10.51773\n" +
"G1 X127.121 Y118.538 E10.65047\n" +
"G1 X134.815 Y118.583 E10.85803\n" +
"G1 X141.185 Y118.390 E11.02997\n" +
"G1 X147.084 Y117.695 E11.19019\n" +
"G1 X152.694 Y116.529 E11.34476\n" +
"G1 X157.586 Y115.101 E11.48224\n" +
"G1 X160.196 Y114.050 E11.55815\n" +
"G1 X161.951 Y113.176 E11.61103\n" +
"G1 X162.433 Y112.898 E11.62605\n" +
"G1 X162.810 Y112.640 E11.63836\n" +
"G1 X163.132 Y112.369 E11.64973\n" +
"G1 X163.381 Y112.110 E11.65941\n" +
"G1 X163.609 Y111.822 E11.66932\n" +
"G1 X163.904 Y111.366 E11.68398\n" +
"G1 X164.644 Y109.912 E11.72798\n" +
"G1 X164.985 Y109.048 E11.75306\n" +
"G1 X165.211 Y108.254 E11.77532\n" +
"G1 X165.377 Y107.309 E11.80120\n" +
"G1 X165.419 Y106.940 E11.81121\n" +
"G1 X165.495 Y105.246 E11.85698\n" +
"G1 X165.343 Y102.431 E11.93300\n" +
"G1 X164.731 Y98.403 E12.04293\n" +
"G1 X164.533 Y97.634 E12.06434\n" +
"G1 X165.526 Y100.764 E12.15292\n" +
"G1 X165.980 Y102.095 E12.19087\n" +
"G1 X166.300 Y102.835 E12.21261\n" +
"G1 X166.597 Y103.334 E12.22826\n" +
"G1 X166.939 Y103.705 E12.24188\n" +
"G1 X167.302 Y103.931 E12.25343\n" +
"G1 X167.697 Y104.058 E12.26460\n" +
"G1 X168.163 Y104.111 E12.27726\n" +
"G1 X168.699 Y104.108 E12.29173\n" +
"G1 X171.694 Y103.918 E12.37268\n" +
"G1 X172.287 Y103.929 E12.38867\n" +
"G1 X172.394 Y103.940 E12.39158\n" +
"G1 X172.399 Y103.990 E12.39294\n" +
"G1 X172.387 Y106.596 E12.46324\n" +
"G1 X172.462 Y110.644 E12.57245\n" +
"G1 X172.574 Y112.112 E12.61217\n" +
"G1 X169.971 Y112.816 E12.68492\n" +
"G1 X167.004 Y113.562 E12.76745\n" +
"G1 X163.327 Y114.634 E12.87075\n" +
"G1 X159.926 Y115.516 E12.96553\n" +
"G1 X158.894 Y115.750 E12.99409\n" +
"G1 X150.850 Y117.364 E13.21543\n" +
"G1 X149.131 Y117.675 E13.26255\n" +
"G1 X146.772 Y118.048 E13.32698\n" +
"G1 X144.124 Y118.429 E13.39914\n" +
"G1 X143.591 Y118.481 E13.41360\n" +
"G1 X135.362 Y119.081 E13.63616\n" +
"G1 X132.901 Y119.081 E13.70255\n" +
"G1 X130.023 Y119.047 E13.78019\n" +
"G1 X127.182 Y118.984 E13.85686\n" +
"G1 X126.904 Y118.965 E13.86437\n" +
"G1 X121.952 Y118.344 E13.99900\n" +
"G1 X119.636 Y117.858 E14.06284\n" +
"G1 X116.775 Y117.292 E14.14151\n" +
"G1 X115.586 Y116.998 E14.17457\n" +
"G1 X115.145 Y116.877 E14.18690\n" +
"G1 X113.341 Y116.293 E14.23805\n" +
"G1 X110.203 Y115.250 E14.32726\n" +
"G1 X107.105 Y114.303 E14.41464\n" +
"G1 X105.419 Y113.940 E14.46118\n" +
"G1 X104.285 Y113.719 E14.49234\n" +
"G1 X103.619 Y113.612 E14.51053\n" +
"G1 X102.114 Y113.445 E14.55137\n" +
"G1 X101.547 Y113.405 E14.56671\n" +
"G1 X100.757 Y113.395 E14.58803\n" +
"G1 X98.694 Y113.397 E14.64370\n" +
"G1 X97.895 Y113.444 E14.66528\n" +
"G1 X97.171 Y113.517 E14.68490\n" +
"G1 X96.377 Y113.613 E14.70648\n" +
"G1 X95.715 Y113.719 E14.72457\n" +
"G1 X94.581 Y113.940 E14.75572\n" +
"G1 X92.895 Y114.303 E14.80227\n" +
"G1 X89.797 Y115.250 E14.88964\n" +
"G1 X86.659 Y116.293 E14.97885\n" +
"G1 X84.855 Y116.877 E15.03001\n" +
"G1 X84.414 Y116.998 E15.04234\n" +
"G1 X83.219 Y117.294 E15.07556\n" +
"G1 X81.034 Y117.758 E15.13582\n" +
"G1 X78.053 Y118.343 E15.21776\n" +
"G1 X73.096 Y118.965 E15.35254\n" +
"G1 X72.822 Y118.984 E15.35993\n" +
"G1 X69.955 Y119.018 E15.43729\n" +
"G1 X67.094 Y119.081 E15.51448\n" +
"G1 X64.638 Y119.081 E15.58075\n" +
"G1 X56.409 Y118.481 E15.80331\n" +
"G1 X55.882 Y118.429 E15.81760\n" +
"G1 X53.527 Y118.057 E15.88193\n" +
"G1 X50.865 Y117.674 E15.95448\n" +
"G1 X48.110 Y117.165 E16.03005\n" +
"G1 X41.106 Y115.750 E16.22280\n" +
"G1 X40.074 Y115.516 E16.25138\n" +
"G1 X36.673 Y114.634 E16.34616\n" +
"G1 X33.002 Y113.564 E16.44930\n" +
"G1 X30.724 Y112.957 E16.51288\n" +
"G1 X27.498 Y112.134 E16.60271\n" +
"G1 E14.60271 F2400.00000\n" +
"G92 E0\n" +
"G1 X108.654 Y107.717 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X108.623 Y106.309 E2.02902\n" +
"G1 X108.757 Y104.292 E2.07069\n" +
"G1 X109.178 Y101.587 E2.12710\n" +
"G1 X110.587 Y96.079 E2.24429\n" +
"G1 X112.158 Y91.850 E2.33726\n" +
"G1 X112.947 Y90.069 E2.37742\n" +
"G1 X113.456 Y89.190 E2.39835\n" +
"G1 X114.035 Y88.388 E2.41874\n" +
"G1 X114.658 Y87.686 E2.43808\n" +
"G1 X115.399 Y86.999 E2.45891\n" +
"G1 X116.541 Y86.122 E2.48858\n" +
"G1 X117.357 Y85.603 E2.50851\n" +
"G1 X118.239 Y85.157 E2.52888\n" +
"G1 X119.552 Y84.639 E2.55797\n" +
"G1 X121.578 Y84.004 E2.60173\n" +
"G1 X128.590 Y82.412 E2.74994\n" +
"G1 X134.253 Y81.671 E2.86764\n" +
"G1 X141.621 Y81.379 E3.01961\n" +
"G1 X148.222 Y81.432 E3.15567\n" +
"G1 X151.177 Y81.725 E3.21687\n" +
"G1 X153.545 Y82.203 E3.26666\n" +
"G1 X154.961 Y82.634 E3.29716\n" +
"G1 X155.766 Y82.970 E3.31514\n" +
"G1 X156.332 Y83.280 E3.32845\n" +
"G1 X156.785 Y83.594 E3.33982\n" +
"G1 X157.257 Y83.992 E3.35254\n" +
"G1 X158.125 Y84.845 E3.37761\n" +
"G1 X159.186 Y86.006 E3.41004\n" +
"G1 X159.469 Y86.392 E3.41990\n" +
"G1 X159.809 Y86.974 E3.43379\n" +
"G1 X161.914 Y91.320 E3.53332\n" +
"G1 X163.281 Y95.008 E3.61439\n" +
"G1 X164.181 Y98.515 E3.68900\n" +
"G1 X164.785 Y102.489 E3.77184\n" +
"G1 X164.934 Y105.248 E3.82880\n" +
"G1 X164.860 Y106.896 E3.86280\n" +
"G1 X164.822 Y107.229 E3.86970\n" +
"G1 X164.664 Y108.128 E3.88852\n" +
"G1 X164.454 Y108.868 E3.90436\n" +
"G1 X164.132 Y109.682 E3.92240\n" +
"G1 X163.418 Y111.086 E3.95487\n" +
"G1 X163.153 Y111.495 E3.96491\n" +
"G1 X162.958 Y111.741 E3.97138\n" +
"G1 X162.748 Y111.959 E3.97762\n" +
"G1 X162.470 Y112.194 E3.98513\n" +
"G1 X162.134 Y112.424 E3.99351\n" +
"G1 X161.686 Y112.682 E4.00417\n" +
"G1 X159.966 Y113.538 E4.04377\n" +
"G1 X157.402 Y114.571 E4.10074\n" +
"G1 X152.558 Y115.985 E4.20474\n" +
"G1 X146.994 Y117.141 E4.32187\n" +
"G1 X141.144 Y117.831 E4.44328\n" +
"G1 X134.808 Y118.022 E4.57392\n" +
"G1 X127.148 Y117.978 E4.73181\n" +
"G1 X122.299 Y117.539 E4.83215\n" +
"G1 X119.335 Y116.964 E4.89438\n" +
"G1 X114.968 Y115.745 E4.98783\n" +
"G1 X113.720 Y115.293 E5.01518\n" +
"G1 X112.932 Y114.917 E5.03317\n" +
"G1 X112.200 Y114.471 E5.05085\n" +
"G1 X111.539 Y113.971 E5.06792\n" +
"G1 X110.953 Y113.428 E5.08438\n" +
"G1 X110.413 Y112.810 E5.10132\n" +
"G1 X109.949 Y112.148 E5.11797\n" +
"G1 X109.573 Y111.476 E5.13385\n" +
"G1 X109.282 Y110.806 E5.14890\n" +
"G1 X109.027 Y110.012 E5.16608\n" +
"G1 X108.801 Y108.970 E5.18805\n" +
"G1 X108.663 Y107.791 E5.21251\n" +
"G1 X108.654 Y107.717 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X152.479 Y81.416 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X152.912 Y81.475 E2.01182\n" +
"G1 X154.236 Y81.787 E2.04850\n" +
"G1 X155.105 Y82.092 E2.07336\n" +
"G1 X153.682 Y81.659 E2.11349\n" +
"G1 X152.552 Y81.431 E2.14459\n" +
"G1 E0.14459 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.586 Y83.535 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.886 Y83.754 E2.01002\n" +
"G1 X158.597 Y84.381 E2.03560\n" +
"G1 X159.241 Y85.065 E2.06094\n" +
"G1 X159.797 Y85.784 E2.08545\n" +
"G1 X160.262 Y86.517 E2.10888\n" +
"G1 X160.804 Y87.542 E2.14016\n" +
"G1 X161.343 Y88.747 E2.17576\n" +
"G1 X161.566 Y89.315 E2.19224\n" +
"G1 X160.304 Y86.710 E2.27033\n" +
"G1 X159.938 Y86.084 E2.28988\n" +
"G1 X159.620 Y85.651 E2.30439\n" +
"G1 X158.529 Y84.456 E2.34805\n" +
"G1 X157.642 Y83.584 E2.38159\n" +
"G1 E0.38159 F2400.00000\n" +
"G92 E0\n" +
"G1 X172.926 Y103.624 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X172.960 Y103.964 E2.00705\n" +
"G1 X172.948 Y106.592 E2.06122\n" +
"G1 X173.022 Y110.617 E2.14419\n" +
"G1 X173.154 Y112.342 E2.17984\n" +
"G1 X173.142 Y112.430 E2.18167\n" +
"G1 X173.084 Y112.498 E2.18351\n" +
"G1 X172.839 Y112.617 E2.18911\n" +
"G1 X172.482 Y112.727 E2.19683\n" +
"G1 X170.111 Y113.359 E2.24739\n" +
"G1 X167.151 Y114.103 E2.31030\n" +
"G1 X163.476 Y115.174 E2.38919\n" +
"G1 X160.059 Y116.060 E2.46195\n" +
"G1 X159.011 Y116.298 E2.48409\n" +
"G1 X150.955 Y117.915 E2.65345\n" +
"G1 X149.224 Y118.228 E2.68969\n" +
"G1 X146.856 Y118.603 E2.73912\n" +
"G1 X144.191 Y118.986 E2.79460\n" +
"G1 X143.638 Y119.040 E2.80605\n" +
"G1 X135.383 Y119.642 E2.97665\n" +
"G1 X132.898 Y119.642 E3.02786\n" +
"G1 X130.014 Y119.608 E3.08731\n" +
"G1 X127.156 Y119.544 E3.14622\n" +
"G1 X126.850 Y119.523 E3.15255\n" +
"G1 X121.860 Y118.897 E3.25620\n" +
"G1 X119.524 Y118.407 E3.30539\n" +
"G1 X116.654 Y117.840 E3.36569\n" +
"G1 X115.444 Y117.540 E3.39138\n" +
"G1 X114.984 Y117.414 E3.40120\n" +
"G1 X113.166 Y116.825 E3.44059\n" +
"G1 X110.032 Y115.784 E3.50865\n" +
"G1 X106.964 Y114.846 E3.57478\n" +
"G1 X105.306 Y114.489 E3.60974\n" +
"G1 X104.187 Y114.271 E3.63324\n" +
"G1 X103.544 Y114.168 E3.64666\n" +
"G1 X102.064 Y114.003 E3.67735\n" +
"G1 X101.524 Y113.966 E3.68850\n" +
"G1 X100.754 Y113.956 E3.70438\n" +
"G1 X98.710 Y113.958 E3.74649\n" +
"G1 X97.939 Y114.003 E3.76242\n" +
"G1 X97.233 Y114.074 E3.77704\n" +
"G1 X96.455 Y114.168 E3.79319\n" +
"G1 X95.813 Y114.271 E3.80659\n" +
"G1 X94.694 Y114.489 E3.83009\n" +
"G1 X93.036 Y114.846 E3.86505\n" +
"G1 X89.968 Y115.784 E3.93118\n" +
"G1 X86.834 Y116.825 E3.99924\n" +
"G1 X85.016 Y117.414 E4.03862\n" +
"G1 X84.556 Y117.540 E4.04845\n" +
"G1 X83.344 Y117.840 E4.07417\n" +
"G1 X81.146 Y118.307 E4.12050\n" +
"G1 X78.142 Y118.897 E4.18359\n" +
"G1 X73.150 Y119.523 E4.28728\n" +
"G1 X72.845 Y119.544 E4.29358\n" +
"G1 X69.965 Y119.578 E4.35295\n" +
"G1 X67.101 Y119.642 E4.41200\n" +
"G1 X64.617 Y119.642 E4.46318\n" +
"G1 X56.362 Y119.040 E4.63378\n" +
"G1 X55.811 Y118.986 E4.64519\n" +
"G1 X53.443 Y118.611 E4.69460\n" +
"G1 X50.774 Y118.228 E4.75017\n" +
"G1 X48.004 Y117.716 E4.80824\n" +
"G1 X40.989 Y116.298 E4.95574\n" +
"G1 X39.941 Y116.060 E4.97788\n" +
"G1 X36.524 Y115.174 E5.05064\n" +
"G1 X32.851 Y114.104 E5.12948\n" +
"G1 X30.584 Y113.499 E5.17785\n" +
"G1 X27.516 Y112.727 E5.24304\n" +
"G1 X27.157 Y112.615 E5.25080\n" +
"G1 X26.935 Y112.516 E5.25582\n" +
"G1 X26.858 Y112.430 E5.25819\n" +
"G1 X26.846 Y112.342 E5.26002\n" +
"G1 X26.978 Y110.617 E5.29567\n" +
"G1 X27.052 Y106.593 E5.37863\n" +
"G1 X27.043 Y103.872 E5.43470\n" +
"G1 X27.073 Y103.624 E5.43986\n" +
"G1 X27.121 Y103.526 E5.44211\n" +
"G1 X27.294 Y103.427 E5.44622\n" +
"G1 X27.671 Y103.370 E5.45407\n" +
"G1 X28.110 Y103.359 E5.46312\n" +
"G1 X29.433 Y103.419 E5.49042\n" +
"G1 X31.322 Y103.547 E5.52944\n" +
"G1 X31.934 Y103.541 E5.54205\n" +
"G1 X32.187 Y103.506 E5.54732\n" +
"G1 X32.460 Y103.418 E5.55324\n" +
"G1 X32.697 Y103.271 E5.55897\n" +
"G1 X32.913 Y103.050 E5.56535\n" +
"G1 X33.200 Y102.581 E5.57669\n" +
"G1 X33.497 Y101.892 E5.59214\n" +
"G1 X34.977 Y97.419 E5.68926\n" +
"G1 X36.150 Y93.728 E5.76907\n" +
"G1 X36.764 Y92.034 E5.80622\n" +
"G1 X38.140 Y88.530 E5.88381\n" +
"G1 X38.691 Y87.296 E5.91165\n" +
"G1 X39.252 Y86.235 E5.93639\n" +
"G1 X39.744 Y85.461 E5.95529\n" +
"G1 X40.332 Y84.700 E5.97511\n" +
"G1 X41.010 Y83.981 E5.99548\n" +
"G1 X41.769 Y83.312 E6.01633\n" +
"G1 X42.752 Y82.595 E6.04141\n" +
"G1 X43.694 Y82.031 E6.06403\n" +
"G1 X44.572 Y81.614 E6.08407\n" +
"G1 X45.129 Y81.408 E6.09631\n" +
"G1 X45.795 Y81.195 E6.11072\n" +
"G1 X46.990 Y80.923 E6.13599\n" +
"G1 X48.923 Y80.657 E6.17620\n" +
"G1 X49.869 Y80.579 E6.19576\n" +
"G1 X51.701 Y80.484 E6.23356\n" +
"G1 X54.151 Y80.387 E6.28409\n" +
"G1 X55.412 Y80.368 E6.31010\n" +
"G1 X64.097 Y80.439 E6.48909\n" +
"G1 X64.929 Y80.469 E6.50627\n" +
"G1 X67.041 Y80.626 E6.54991\n" +
"G1 X69.039 Y80.738 E6.59115\n" +
"G1 X70.956 Y80.917 E6.63083\n" +
"G1 X71.709 Y81.011 E6.64648\n" +
"G1 X76.192 Y81.746 E6.74010\n" +
"G1 X76.625 Y81.835 E6.74922\n" +
"G1 X79.533 Y82.560 E6.81099\n" +
"G1 X79.913 Y82.675 E6.81916\n" +
"G1 X81.587 Y83.285 E6.85588\n" +
"G1 X82.930 Y83.929 E6.88659\n" +
"G1 X84.261 Y84.714 E6.91843\n" +
"G1 X85.426 Y85.550 E6.94799\n" +
"G1 X86.227 Y86.254 E6.96997\n" +
"G1 X86.867 Y86.935 E6.98923\n" +
"G1 X87.416 Y87.650 E7.00781\n" +
"G1 X87.726 Y88.139 E7.01974\n" +
"G1 X88.145 Y88.863 E7.03699\n" +
"G1 X88.908 Y90.506 E7.07433\n" +
"G1 X90.295 Y94.327 E7.15809\n" +
"G1 X92.002 Y99.914 E7.27849\n" +
"G1 X92.325 Y101.027 E7.30238\n" +
"G1 X93.464 Y105.360 E7.39472\n" +
"G1 X93.662 Y106.053 E7.40958\n" +
"G1 X93.943 Y106.798 E7.42599\n" +
"G1 X94.136 Y107.200 E7.43517\n" +
"G1 X94.364 Y107.585 E7.44440\n" +
"G1 X94.552 Y107.843 E7.45099\n" +
"G1 X94.806 Y108.134 E7.45895\n" +
"G1 X95.097 Y108.407 E7.46718\n" +
"G1 X95.430 Y108.662 E7.47581\n" +
"G1 X95.812 Y108.899 E7.48508\n" +
"G1 X96.265 Y109.122 E7.49549\n" +
"G1 X96.773 Y109.316 E7.50670\n" +
"G1 X97.388 Y109.489 E7.51986\n" +
"G1 X98.124 Y109.627 E7.53530\n" +
"G1 X98.881 Y109.703 E7.55098\n" +
"G1 X100.441 Y109.716 E7.58312\n" +
"G1 X101.122 Y109.703 E7.59717\n" +
"G1 X102.070 Y109.595 E7.61683\n" +
"G1 X102.612 Y109.489 E7.62822\n" +
"G1 X103.073 Y109.364 E7.63806\n" +
"G1 X103.693 Y109.141 E7.65163\n" +
"G1 X104.188 Y108.900 E7.66298\n" +
"G1 X104.570 Y108.662 E7.67226\n" +
"G1 X104.906 Y108.405 E7.68097\n" +
"G1 X105.321 Y107.997 E7.69298\n" +
"G1 X105.691 Y107.498 E7.70579\n" +
"G1 X105.888 Y107.155 E7.71393\n" +
"G1 X106.057 Y106.797 E7.72209\n" +
"G1 X106.338 Y106.053 E7.73849\n" +
"G1 X106.536 Y105.360 E7.75335\n" +
"G1 X107.675 Y101.027 E7.84568\n" +
"G1 X107.998 Y99.914 E7.86957\n" +
"G1 X109.705 Y94.326 E7.98998\n" +
"G1 X111.019 Y90.695 E8.06957\n" +
"G1 X111.193 Y90.261 E8.07920\n" +
"G1 X111.974 Y88.641 E8.11628\n" +
"G1 X112.487 Y87.795 E8.13668\n" +
"G1 X113.050 Y87.035 E8.15616\n" +
"G1 X113.774 Y86.252 E8.17815\n" +
"G1 X114.247 Y85.826 E8.19126\n" +
"G1 X114.769 Y85.398 E8.20516\n" +
"G1 X115.740 Y84.714 E8.22966\n" +
"G1 X117.070 Y83.929 E8.26148\n" +
"G1 X118.413 Y83.285 E8.29219\n" +
"G1 X120.087 Y82.675 E8.32891\n" +
"G1 X120.467 Y82.560 E8.33708\n" +
"G1 X123.375 Y81.835 E8.39885\n" +
"G1 X123.808 Y81.746 E8.40797\n" +
"G1 X128.291 Y81.011 E8.50159\n" +
"G1 X129.044 Y80.917 E8.51724\n" +
"G1 X130.963 Y80.738 E8.55697\n" +
"G1 X133.072 Y80.581 E8.60054\n" +
"G1 X135.905 Y80.439 E8.65901\n" +
"G1 X144.588 Y80.368 E8.83796\n" +
"G1 X145.848 Y80.387 E8.86394\n" +
"G1 X147.682 Y80.482 E8.90179\n" +
"G1 X150.132 Y80.579 E8.95234\n" +
"G1 X151.077 Y80.657 E8.97187\n" +
"G1 X153.011 Y80.923 E9.01210\n" +
"G1 X153.800 Y81.096 E9.02876\n" +
"G1 X154.398 Y81.249 E9.04147\n" +
"G1 X155.231 Y81.536 E9.05964\n" +
"G1 X155.810 Y81.788 E9.07264\n" +
"G1 X156.304 Y82.031 E9.08400\n" +
"G1 X157.248 Y82.595 E9.10666\n" +
"G1 X158.233 Y83.313 E9.13179\n" +
"G1 X158.989 Y83.980 E9.15256\n" +
"G1 X159.668 Y84.700 E9.17296\n" +
"G1 X160.256 Y85.461 E9.19278\n" +
"G1 X160.748 Y86.235 E9.21168\n" +
"G1 X161.309 Y87.296 E9.23642\n" +
"G1 X161.860 Y88.530 E9.26426\n" +
"G1 X163.236 Y92.034 E9.34185\n" +
"G1 X163.851 Y93.729 E9.37903\n" +
"G1 X164.743 Y96.442 E9.43789\n" +
"G1 X166.058 Y100.589 E9.52754\n" +
"G1 X166.503 Y101.893 E9.55596\n" +
"G1 X166.800 Y102.579 E9.57136\n" +
"G1 X167.049 Y102.997 E9.58137\n" +
"G1 X167.300 Y103.269 E9.58901\n" +
"G1 X167.540 Y103.418 E9.59483\n" +
"G1 X167.816 Y103.507 E9.60081\n" +
"G1 X168.193 Y103.551 E9.60863\n" +
"G1 X168.680 Y103.547 E9.61866\n" +
"G1 X171.681 Y103.357 E9.68066\n" +
"G1 X172.321 Y103.369 E9.69384\n" +
"G1 X172.511 Y103.389 E9.69777\n" +
"G1 X172.770 Y103.451 E9.70327\n" +
"G1 X172.869 Y103.514 E9.70569\n" +
"G1 X172.892 Y103.557 E9.70669\n" +
"G1 X172.696 Y103.877 F7800.000\n" +
"G1 E7.70669 F2400.00000\n" +
"G92 E0\n" +
"G1 X171.369 Y105.479 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X170.875 Y104.984 E2.01926\n" +
"G1 X170.011 Y105.038 E2.04310\n" +
"G1 X171.365 Y106.392 E2.09586\n" +
"G1 X171.372 Y107.316 E2.12131\n" +
"G1 X169.146 Y105.091 E2.20798\n" +
"G1 X168.271 Y105.133 E2.23213\n" +
"G1 X171.389 Y108.251 E2.35358\n" +
"G1 X171.406 Y109.186 E2.37933\n" +
"G1 X167.292 Y105.072 E2.53957\n" +
"G1 X167.180 Y105.059 E2.54267\n" +
"G1 X166.512 Y104.843 E2.56199\n" +
"G1 X166.511 Y105.208 E2.57205\n" +
"G1 X171.424 Y110.121 E2.76338\n" +
"G1 X171.447 Y111.063 E2.78932\n" +
"G1 X166.472 Y106.087 E2.98311\n" +
"G1 X166.422 Y106.955 E3.00705\n" +
"G1 X171.005 Y111.538 E3.18556\n" +
"G1 X170.281 Y111.731 E3.20621\n" +
"G1 X166.300 Y107.750 E3.36125\n" +
"G1 X166.204 Y108.292 E3.37638\n" +
"G1 X166.142 Y108.511 E3.38265\n" +
"G1 X169.552 Y111.920 E3.51544\n" +
"G1 X168.819 Y112.105 E3.53627\n" +
"G1 X165.938 Y109.224 E3.64846\n" +
"G1 X165.678 Y109.881 E3.66794\n" +
"G1 X168.085 Y112.289 E3.76170\n" +
"G1 X167.352 Y112.473 E3.78252\n" +
"G1 X165.392 Y110.513 E3.85888\n" +
"G1 X165.082 Y111.121 E3.87767\n" +
"G1 X166.628 Y112.667 E3.93788\n" +
"G1 X165.917 Y112.874 E3.95826\n" +
"G1 X164.773 Y111.729 E4.00284\n" +
"G1 X164.414 Y112.288 E4.02113\n" +
"G1 X165.207 Y113.081 E4.05200\n" +
"G1 X164.496 Y113.288 E4.07238\n" +
"G1 X164.002 Y112.794 E4.09164\n" +
"G1 E2.09164 F2400.00000\n" +
"G92 E0\n" +
"G1 X107.193 Y109.128 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X106.699 Y108.634 E2.01919\n" +
"G1 X106.460 Y108.956 E2.03020\n" +
"G1 X106.282 Y109.131 E2.03705\n" +
"G1 X107.397 Y110.247 E2.08035\n" +
"G1 X107.503 Y110.734 E2.09404\n" +
"G1 X107.755 Y111.519 E2.11665\n" +
"G1 X105.816 Y109.579 E2.19192\n" +
"G1 X105.292 Y109.970 E2.20985\n" +
"G1 X108.580 Y113.258 E2.33745\n" +
"G1 X108.863 Y113.685 E2.35151\n" +
"G1 X107.750 Y113.343 E2.38344\n" +
"G1 X104.718 Y110.311 E2.50111\n" +
"G1 X104.098 Y110.605 E2.51995\n" +
"G1 X106.576 Y113.083 E2.61611\n" +
"G1 X105.423 Y112.845 E2.64841\n" +
"G1 X103.426 Y110.847 E2.72593\n" +
"G1 X102.706 Y111.042 E2.74640\n" +
"G1 X104.306 Y112.642 E2.80852\n" +
"G1 X103.263 Y112.513 E2.83738\n" +
"G1 X101.929 Y111.180 E2.88913\n" +
"G1 X101.108 Y111.273 E2.91182\n" +
"G1 X102.245 Y112.410 E2.95593\n" +
"G1 X101.847 Y112.382 E2.96686\n" +
"G1 X101.295 Y112.375 E2.98201\n" +
"G1 X100.221 Y111.300 E3.02370\n" +
"G1 X99.299 Y111.293 E3.04899\n" +
"G1 X100.378 Y112.372 E3.09084\n" +
"G1 X99.464 Y112.373 E3.11591\n" +
"G1 X98.361 Y111.269 E3.15872\n" +
"G1 X97.616 Y111.194 E3.17927\n" +
"G1 X97.315 Y111.138 E3.18767\n" +
"G1 X98.570 Y112.393 E3.23636\n" +
"G1 X97.722 Y112.459 E3.25971\n" +
"G1 X96.121 Y110.858 E3.32185\n" +
"G1 X95.951 Y110.810 E3.32670\n" +
"G1 X95.285 Y110.557 E3.34623\n" +
"G1 X94.675 Y110.256 E3.36490\n" +
"G1 X94.489 Y110.141 E3.37092\n" +
"G1 X96.899 Y112.550 E3.46445\n" +
"G1 X96.092 Y112.658 E3.48679\n" +
"G1 X92.707 Y109.273 E3.61817\n" +
"G1 X92.544 Y110.024 E3.63926\n" +
"G1 X95.316 Y112.797 E3.74688\n" +
"G1 X94.552 Y112.947 E3.76827\n" +
"G1 X92.338 Y110.733 E3.85416\n" +
"G1 X92.187 Y111.204 E3.86774\n" +
"G1 X92.099 Y111.408 E3.87384\n" +
"G1 X93.799 Y113.109 E3.93983\n" +
"G1 X93.047 Y113.271 E3.96095\n" +
"G1 X91.822 Y112.046 E4.00850\n" +
"G1 X91.494 Y112.632 E4.02694\n" +
"G1 X92.331 Y113.470 E4.05945\n" +
"G1 X91.632 Y113.684 E4.07953\n" +
"G1 X91.137 Y113.190 E4.09872\n" +
"G1 E2.09872 F2400.00000\n" +
"G92 E0\n" +
"G1 X33.488 Y105.338 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X32.993 Y104.843 E2.02009\n" +
"G1 X32.346 Y105.052 E2.03962\n" +
"G1 X32.257 Y105.065 E2.04221\n" +
"G1 X33.517 Y106.325 E2.09340\n" +
"G1 X33.565 Y107.330 E2.12231\n" +
"G1 X31.364 Y105.129 E2.21172\n" +
"G1 X31.023 Y105.133 E2.22152\n" +
"G1 X30.366 Y105.088 E2.24044\n" +
"G1 X33.738 Y108.460 E2.37745\n" +
"G1 X33.796 Y108.786 E2.38696\n" +
"G1 X34.077 Y109.757 E2.41600\n" +
"G1 X29.339 Y105.018 E2.60852\n" +
"G1 X28.632 Y104.978 E2.62885\n" +
"G1 X28.633 Y105.270 E2.63723\n" +
"G1 X34.855 Y111.491 E2.89000\n" +
"G1 X35.240 Y112.249 E2.91442\n" +
"G1 X35.605 Y112.812 E2.93368\n" +
"G1 X35.998 Y113.288 E2.95144\n" +
"G1 X35.569 Y113.163 E2.96428\n" +
"G1 X28.636 Y106.230 E3.24594\n" +
"G1 X28.622 Y107.173 E3.27303\n" +
"G1 X34.218 Y112.770 E3.50040\n" +
"G1 X32.892 Y112.400 E3.53996\n" +
"G1 X28.604 Y108.113 E3.71414\n" +
"G1 X28.587 Y109.053 E3.74115\n" +
"G1 X31.586 Y112.052 E3.86301\n" +
"G1 X30.301 Y111.724 E3.90112\n" +
"G1 X28.570 Y109.993 E3.97146\n" +
"G1 X28.561 Y110.457 E3.98480\n" +
"G1 X28.527 Y110.907 E3.99777\n" +
"G1 X29.021 Y111.402 E4.01786\n" +
"G1 Z2.150 F7800.000\n" +
"G1 E2.01786 F2400.00000\n" +
"G92 E0\n" +
"G1 X91.377 Y106.309 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X91.346 Y107.717 E2.02902\n" +
"G1 X91.199 Y108.970 E2.05503\n" +
"G1 X90.973 Y110.012 E2.07700\n" +
"G1 X90.718 Y110.806 E2.09418\n" +
"G1 X90.427 Y111.476 E2.10924\n" +
"G1 X90.051 Y112.148 E2.12511\n" +
"G1 X89.587 Y112.810 E2.14177\n" +
"G1 X89.047 Y113.428 E2.15870\n" +
"G1 X88.461 Y113.971 E2.17517\n" +
"G1 X87.800 Y114.471 E2.19223\n" +
"G1 X87.068 Y114.917 E2.20991\n" +
"G1 X86.280 Y115.293 E2.22790\n" +
"G1 X85.032 Y115.745 E2.25525\n" +
"G1 X80.665 Y116.964 E2.34870\n" +
"G1 X77.701 Y117.539 E2.41094\n" +
"G1 X72.852 Y117.978 E2.51127\n" +
"G1 X65.192 Y118.022 E2.66916\n" +
"G1 X58.856 Y117.831 E2.79981\n" +
"G1 X53.006 Y117.141 E2.92121\n" +
"G1 X47.442 Y115.985 E3.03834\n" +
"G1 X42.598 Y114.571 E3.14235\n" +
"G1 X40.034 Y113.538 E3.19931\n" +
"G1 X38.314 Y112.682 E3.23891\n" +
"G1 X37.866 Y112.424 E3.24957\n" +
"G1 X37.530 Y112.194 E3.25796\n" +
"G1 X37.252 Y111.959 E3.26546\n" +
"G1 X37.042 Y111.741 E3.27170\n" +
"G1 X36.847 Y111.495 E3.27817\n" +
"G1 X36.582 Y111.086 E3.28821\n" +
"G1 X35.868 Y109.682 E3.32068\n" +
"G1 X35.546 Y108.868 E3.33872\n" +
"G1 X35.336 Y108.128 E3.35456\n" +
"G1 X35.178 Y107.229 E3.37338\n" +
"G1 X35.140 Y106.896 E3.38029\n" +
"G1 X35.066 Y105.248 E3.41429\n" +
"G1 X35.215 Y102.489 E3.47124\n" +
"G1 X35.819 Y98.515 E3.55408\n" +
"G1 X36.719 Y95.008 E3.62870\n" +
"G1 X38.086 Y91.320 E3.70976\n" +
"G1 X40.191 Y86.974 E3.80929\n" +
"G1 X40.531 Y86.392 E3.82318\n" +
"G1 X40.814 Y86.006 E3.83304\n" +
"G1 X41.875 Y84.845 E3.86547\n" +
"G1 X42.743 Y83.992 E3.89054\n" +
"G1 X43.215 Y83.594 E3.90326\n" +
"G1 X43.668 Y83.280 E3.91463\n" +
"G1 X44.234 Y82.970 E3.92794\n" +
"G1 X45.039 Y82.634 E3.94592\n" +
"G1 X46.455 Y82.203 E3.97643\n" +
"G1 X48.823 Y81.725 E4.02621\n" +
"G1 X51.778 Y81.432 E4.08742\n" +
"G1 X58.379 Y81.379 E4.22347\n" +
"G1 X65.747 Y81.671 E4.37544\n" +
"G1 X71.410 Y82.412 E4.49315\n" +
"G1 X78.422 Y84.004 E4.64135\n" +
"G1 X80.448 Y84.639 E4.68511\n" +
"G1 X81.761 Y85.157 E4.71421\n" +
"G1 X82.643 Y85.603 E4.73457\n" +
"G1 X83.459 Y86.122 E4.75450\n" +
"G1 X84.601 Y86.999 E4.78418\n" +
"G1 X85.342 Y87.686 E4.80501\n" +
"G1 X85.965 Y88.388 E4.82434\n" +
"G1 X86.544 Y89.190 E4.84473\n" +
"G1 X87.053 Y90.069 E4.86567\n" +
"G1 X87.842 Y91.850 E4.90582\n" +
"G1 X89.413 Y96.079 E4.99880\n" +
"G1 X90.822 Y101.587 E5.11598\n" +
"G1 X91.243 Y104.292 E5.17240\n" +
"G1 X91.372 Y106.234 E5.21251\n" +
"G1 X91.377 Y106.309 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X47.490 Y81.422 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X46.318 Y81.659 E2.03225\n" +
"G1 X44.983 Y82.065 E2.06989\n" +
"G1 X45.908 Y81.746 E2.09626\n" +
"G1 X47.039 Y81.485 E2.12758\n" +
"G1 X47.416 Y81.432 E2.13784\n" +
"G1 E0.13784 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.365 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X41.471 Y84.456 E2.03381\n" +
"G1 X40.380 Y85.651 E2.07747\n" +
"G1 X40.062 Y86.084 E2.09198\n" +
"G1 X39.696 Y86.710 E2.11153\n" +
"G1 X38.469 Y89.244 E2.18748\n" +
"G1 X38.743 Y88.549 E2.20762\n" +
"G1 X39.153 Y87.630 E2.23477\n" +
"G1 X39.720 Y86.547 E2.26776\n" +
"G1 X40.204 Y85.783 E2.29215\n" +
"G1 X40.759 Y85.065 E2.31664\n" +
"G1 X41.400 Y84.385 E2.34185\n" +
"G1 X42.128 Y83.744 E2.36801\n" +
"G1 X42.416 Y83.534 E2.37762\n" +
"G1 E0.37762 F2400.00000\n" +
"G92 E0\n" +
"G1 X159.877 Y114.863 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.457 Y114.630 E2.01685\n" +
"G1 X162.252 Y113.737 E2.07093\n" +
"G1 X162.772 Y113.437 E2.08715\n" +
"G1 X163.195 Y113.147 E2.10097\n" +
"G1 X163.567 Y112.833 E2.11410\n" +
"G1 X163.860 Y112.528 E2.12551\n" +
"G1 X164.126 Y112.193 E2.13704\n" +
"G1 X164.456 Y111.684 E2.15341\n" +
"G1 X165.224 Y110.174 E2.19912\n" +
"G1 X165.588 Y109.252 E2.22586\n" +
"G1 X165.831 Y108.396 E2.24985\n" +
"G1 X166.007 Y107.400 E2.27714\n" +
"G1 X166.053 Y106.990 E2.28827\n" +
"G1 X166.131 Y105.243 E2.33546\n" +
"G1 X166.045 Y103.653 E2.37840\n" +
"G1 X166.328 Y104.007 E2.39060\n" +
"G1 X166.734 Y104.344 E2.40485\n" +
"G1 X167.188 Y104.575 E2.41859\n" +
"G1 X167.815 Y104.726 E2.43597\n" +
"G1 X168.639 Y104.746 E2.45822\n" +
"G1 X171.760 Y104.555 E2.54258\n" +
"G1 X171.751 Y106.340 E2.59074\n" +
"G1 X171.830 Y110.746 E2.70962\n" +
"G1 X171.900 Y111.644 E2.73392\n" +
"G1 X168.818 Y112.466 E2.81996\n" +
"G1 X166.526 Y113.042 E2.88370\n" +
"G1 X163.694 Y113.867 E2.96328\n" +
"G1 X159.950 Y114.844 E3.06767\n" +
"G1 E1.06767 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.569 Y114.702 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.216 Y113.675 E2.09458\n" +
"G1 X105.877 Y113.387 E2.13154\n" +
"G1 X104.250 Y113.070 E2.17625\n" +
"G1 X103.702 Y112.982 E2.19122\n" +
"G1 X102.040 Y112.802 E2.23633\n" +
"G1 X101.381 Y112.763 E2.25413\n" +
"G1 X100.578 Y112.760 E2.27579\n" +
"G1 X98.417 Y112.769 E2.33409\n" +
"G1 X96.917 Y112.907 E2.37472\n" +
"G1 X95.765 Y113.067 E2.40610\n" +
"G1 X94.123 Y113.387 E2.45123\n" +
"G1 X92.784 Y113.675 E2.48819\n" +
"G1 X89.431 Y114.702 E2.58277\n" +
"G1 X89.906 Y114.263 E2.60022\n" +
"G1 X90.530 Y113.549 E2.62581\n" +
"G1 X91.066 Y112.785 E2.65097\n" +
"G1 X91.501 Y112.007 E2.67501\n" +
"G1 X91.839 Y111.227 E2.69794\n" +
"G1 X92.129 Y110.322 E2.72358\n" +
"G1 X92.380 Y109.167 E2.75547\n" +
"G1 X92.541 Y107.800 E2.79260\n" +
"G1 X92.568 Y106.543 E2.82652\n" +
"G1 X92.859 Y107.305 E2.84851\n" +
"G1 X93.088 Y107.779 E2.86272\n" +
"G1 X93.376 Y108.261 E2.87787\n" +
"G1 X93.621 Y108.597 E2.88907\n" +
"G1 X93.944 Y108.967 E2.90233\n" +
"G1 X94.322 Y109.321 E2.91630\n" +
"G1 X94.747 Y109.646 E2.93073\n" +
"G1 X95.225 Y109.944 E2.94593\n" +
"G1 X95.786 Y110.220 E2.96281\n" +
"G1 X96.398 Y110.453 E2.98045\n" +
"G1 X97.111 Y110.654 E3.00045\n" +
"G1 X97.799 Y110.787 E3.01935\n" +
"G1 X98.858 Y110.901 E3.04807\n" +
"G1 X100.613 Y110.911 E3.09543\n" +
"G1 X101.155 Y110.900 E3.11006\n" +
"G1 X102.041 Y110.812 E3.13406\n" +
"G1 X102.707 Y110.692 E3.15233\n" +
"G1 X103.526 Y110.478 E3.17515\n" +
"G1 X104.210 Y110.222 E3.19485\n" +
"G1 X104.775 Y109.944 E3.21185\n" +
"G1 X105.253 Y109.646 E3.22705\n" +
"G1 X105.693 Y109.309 E3.24198\n" +
"G1 X106.215 Y108.794 E3.26178\n" +
"G1 X106.498 Y108.441 E3.27399\n" +
"G1 X106.742 Y108.077 E3.28581\n" +
"G1 X107.133 Y107.326 E3.30864\n" +
"G1 X107.432 Y106.543 E3.33125\n" +
"G1 X107.459 Y107.800 E3.36516\n" +
"G1 X107.620 Y109.167 E3.40230\n" +
"G1 X107.871 Y110.322 E3.43418\n" +
"G1 X108.161 Y111.227 E3.45983\n" +
"G1 X108.499 Y112.007 E3.48276\n" +
"G1 X108.934 Y112.785 E3.50680\n" +
"G1 X109.470 Y113.549 E3.53196\n" +
"G1 X110.094 Y114.263 E3.55755\n" +
"G1 X110.513 Y114.651 E3.57297\n" +
"G1 E1.57297 F2400.00000\n" +
"G92 E0\n" +
"G1 X40.123 Y114.863 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X36.306 Y113.867 E2.10641\n" +
"G1 X33.484 Y113.045 E2.18571\n" +
"G1 X30.226 Y112.176 E2.27667\n" +
"G1 X28.101 Y111.640 E2.33580\n" +
"G1 X28.170 Y110.746 E2.35998\n" +
"G1 X28.249 Y106.342 E2.47882\n" +
"G1 X28.243 Y104.559 E2.52690\n" +
"G1 X28.517 Y104.563 E2.53430\n" +
"G1 X31.339 Y104.745 E2.61059\n" +
"G1 X31.874 Y104.747 E2.62501\n" +
"G1 X32.435 Y104.682 E2.64026\n" +
"G1 X32.936 Y104.525 E2.65442\n" +
"G1 X33.427 Y104.231 E2.66986\n" +
"G1 X33.857 Y103.799 E2.68630\n" +
"G1 X33.955 Y103.638 E2.69139\n" +
"G1 X33.869 Y105.243 E2.73474\n" +
"G1 X33.947 Y106.990 E2.78193\n" +
"G1 X33.993 Y107.400 E2.79306\n" +
"G1 X34.169 Y108.397 E2.82036\n" +
"G1 X34.412 Y109.252 E2.84435\n" +
"G1 X34.776 Y110.174 E2.87109\n" +
"G1 X35.544 Y111.684 E2.91679\n" +
"G1 X35.874 Y112.193 E2.93316\n" +
"G1 X36.140 Y112.528 E2.94470\n" +
"G1 X36.433 Y112.833 E2.95611\n" +
"G1 X36.805 Y113.147 E2.96923\n" +
"G1 X37.228 Y113.437 E2.98306\n" +
"G1 X37.748 Y113.737 E2.99927\n" +
"G1 X39.543 Y114.630 E3.05336\n" +
"G1 X40.053 Y114.835 E3.06818\n" +
"G1 E1.06818 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.426 Y112.113 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.535 Y110.716 E2.03779\n" +
"G1 X27.613 Y106.337 E2.15594\n" +
"G1 X27.605 Y103.947 E2.22041\n" +
"G1 X28.029 Y103.921 E2.23187\n" +
"G1 X28.541 Y103.927 E2.24569\n" +
"G1 X31.361 Y104.109 E2.32191\n" +
"G1 X31.838 Y104.111 E2.33479\n" +
"G1 X32.303 Y104.057 E2.34740\n" +
"G1 X32.674 Y103.941 E2.35791\n" +
"G1 X33.032 Y103.727 E2.36916\n" +
"G1 X33.354 Y103.403 E2.38147\n" +
"G1 X33.692 Y102.852 E2.39892\n" +
"G1 X34.004 Y102.138 E2.41993\n" +
"G1 X35.354 Y98.073 E2.53547\n" +
"G1 X35.269 Y98.403 E2.54465\n" +
"G1 X34.657 Y102.431 E2.65457\n" +
"G1 X34.505 Y105.246 E2.73060\n" +
"G1 X34.581 Y106.940 E2.77636\n" +
"G1 X34.622 Y107.309 E2.78638\n" +
"G1 X34.789 Y108.254 E2.81226\n" +
"G1 X35.015 Y109.048 E2.83451\n" +
"G1 X35.356 Y109.912 E2.85960\n" +
"G1 X36.096 Y111.366 E2.90360\n" +
"G1 X36.391 Y111.822 E2.91825\n" +
"G1 X36.619 Y112.110 E2.92816\n" +
"G1 X36.868 Y112.369 E2.93785\n" +
"G1 X37.190 Y112.640 E2.94922\n" +
"G1 X37.567 Y112.898 E2.96152\n" +
"G1 X38.049 Y113.176 E2.97654\n" +
"G1 X39.804 Y114.050 E3.02943\n" +
"G1 X42.414 Y115.101 E3.10533\n" +
"G1 X47.306 Y116.529 E3.24281\n" +
"G1 X52.916 Y117.695 E3.39738\n" +
"G1 X58.815 Y118.390 E3.55761\n" +
"G1 X65.185 Y118.583 E3.72954\n" +
"G1 X72.879 Y118.538 E3.93711\n" +
"G1 X77.780 Y118.095 E4.06984\n" +
"G1 X80.794 Y117.510 E4.15268\n" +
"G1 X85.203 Y116.279 E4.27617\n" +
"G1 X86.497 Y115.811 E4.31328\n" +
"G1 X87.335 Y115.410 E4.33834\n" +
"G1 X88.116 Y114.935 E4.36301\n" +
"G1 X88.821 Y114.401 E4.38687\n" +
"G1 X89.449 Y113.819 E4.40997\n" +
"G1 X90.029 Y113.156 E4.43373\n" +
"G1 X90.527 Y112.446 E4.45711\n" +
"G1 X90.930 Y111.725 E4.47942\n" +
"G1 X91.243 Y111.003 E4.50063\n" +
"G1 X91.515 Y110.157 E4.52460\n" +
"G1 X91.753 Y109.062 E4.55482\n" +
"G1 X91.906 Y107.756 E4.59031\n" +
"G1 X91.938 Y106.297 E4.62969\n" +
"G1 X91.800 Y104.230 E4.68556\n" +
"G1 X91.372 Y101.474 E4.76079\n" +
"G1 X89.949 Y95.911 E4.91570\n" +
"G1 X88.361 Y91.639 E5.03865\n" +
"G1 X87.553 Y89.814 E5.09248\n" +
"G1 X87.015 Y88.884 E5.12147\n" +
"G1 X86.403 Y88.037 E5.14968\n" +
"G1 X85.743 Y87.294 E5.17648\n" +
"G1 X84.963 Y86.570 E5.20518\n" +
"G1 X83.780 Y85.663 E5.24539\n" +
"G1 X82.921 Y85.115 E5.27288\n" +
"G1 X81.991 Y84.645 E5.30099\n" +
"G1 X80.635 Y84.110 E5.34032\n" +
"G1 X78.568 Y83.462 E5.39875\n" +
"G1 X71.508 Y81.859 E5.59405\n" +
"G1 X65.795 Y81.112 E5.74949\n" +
"G1 X62.844 Y80.995 E5.82917\n" +
"G1 X64.899 Y81.029 E5.88461\n" +
"G1 X67.911 Y81.253 E5.96609\n" +
"G1 X69.442 Y81.339 E6.00746\n" +
"G1 X70.895 Y81.475 E6.04683\n" +
"G1 X71.453 Y81.544 E6.06201\n" +
"G1 X76.501 Y82.382 E6.20004\n" +
"G1 X79.445 Y83.118 E6.28190\n" +
"G1 X79.706 Y83.198 E6.28927\n" +
"G1 X81.346 Y83.793 E6.33634\n" +
"G1 X82.666 Y84.424 E6.37581\n" +
"G1 X83.993 Y85.209 E6.41740\n" +
"G1 X85.075 Y85.987 E6.45335\n" +
"G1 X85.837 Y86.657 E6.48072\n" +
"G1 X86.439 Y87.299 E6.50446\n" +
"G1 X86.950 Y87.965 E6.52711\n" +
"G1 X87.625 Y89.081 E6.56228\n" +
"G1 X88.287 Y90.469 E6.60378\n" +
"G1 X88.419 Y90.790 E6.61314\n" +
"G1 X89.775 Y94.540 E6.72072\n" +
"G1 X91.324 Y99.588 E6.86316\n" +
"G1 X91.783 Y101.170 E6.90760\n" +
"G1 X93.127 Y106.223 E7.04865\n" +
"G1 X93.443 Y107.053 E7.07262\n" +
"G1 X93.647 Y107.477 E7.08531\n" +
"G1 X93.907 Y107.910 E7.09892\n" +
"G1 X94.118 Y108.199 E7.10858\n" +
"G1 X94.402 Y108.525 E7.12024\n" +
"G1 X94.734 Y108.835 E7.13250\n" +
"G1 X95.109 Y109.123 E7.14524\n" +
"G1 X95.534 Y109.387 E7.15876\n" +
"G1 X96.041 Y109.637 E7.17399\n" +
"G1 X96.597 Y109.849 E7.19006\n" +
"G1 X97.258 Y110.035 E7.20856\n" +
"G1 X97.899 Y110.158 E7.22618\n" +
"G1 X98.339 Y110.213 E7.23816\n" +
"G1 X98.890 Y110.266 E7.25306\n" +
"G1 X100.609 Y110.275 E7.29945\n" +
"G1 X101.118 Y110.265 E7.31318\n" +
"G1 X101.953 Y110.182 E7.33581\n" +
"G1 X102.570 Y110.071 E7.35273\n" +
"G1 X103.334 Y109.871 E7.37402\n" +
"G1 X103.957 Y109.638 E7.39198\n" +
"G1 X104.466 Y109.387 E7.40728\n" +
"G1 X104.891 Y109.123 E7.42080\n" +
"G1 X105.275 Y108.829 E7.43383\n" +
"G1 X105.742 Y108.368 E7.45154\n" +
"G1 X105.985 Y108.065 E7.46202\n" +
"G1 X106.195 Y107.752 E7.47218\n" +
"G1 X106.553 Y107.065 E7.49306\n" +
"G1 X106.873 Y106.223 E7.51737\n" +
"G1 X108.217 Y101.170 E7.65843\n" +
"G1 X108.676 Y99.588 E7.70287\n" +
"G1 X110.225 Y94.540 E7.84533\n" +
"G1 X111.475 Y91.065 E7.94494\n" +
"G1 X111.715 Y90.467 E7.96231\n" +
"G1 X112.467 Y88.909 E8.00897\n" +
"G1 X112.975 Y88.075 E8.03532\n" +
"G1 X113.500 Y87.371 E8.05902\n" +
"G1 X114.167 Y86.653 E8.08546\n" +
"G1 X115.063 Y85.880 E8.11738\n" +
"G1 X116.012 Y85.207 E8.14876\n" +
"G1 X117.334 Y84.424 E8.19021\n" +
"G1 X118.654 Y83.793 E8.22968\n" +
"G1 X120.294 Y83.198 E8.27675\n" +
"G1 X120.555 Y83.118 E8.28412\n" +
"G1 X123.499 Y82.382 E8.36598\n" +
"G1 X128.547 Y81.544 E8.50401\n" +
"G1 X129.105 Y81.475 E8.51919\n" +
"G1 X130.566 Y81.338 E8.55877\n" +
"G1 X133.568 Y81.114 E8.63998\n" +
"G1 X135.727 Y81.007 E8.69830\n" +
"G1 X137.156 Y80.995 E8.73686\n" +
"G1 X134.205 Y81.112 E8.81653\n" +
"G1 X128.492 Y81.859 E8.97198\n" +
"G1 X121.432 Y83.462 E9.16728\n" +
"G1 X119.365 Y84.110 E9.22571\n" +
"G1 X118.009 Y84.645 E9.26503\n" +
"G1 X117.079 Y85.115 E9.29314\n" +
"G1 X116.220 Y85.663 E9.32063\n" +
"G1 X115.037 Y86.570 E9.36084\n" +
"G1 X114.257 Y87.294 E9.38955\n" +
"G1 X113.597 Y88.037 E9.41635\n" +
"G1 X112.985 Y88.884 E9.44456\n" +
"G1 X112.447 Y89.814 E9.47354\n" +
"G1 X111.639 Y91.639 E9.52738\n" +
"G1 X110.051 Y95.911 E9.65033\n" +
"G1 X108.628 Y101.474 E9.80523\n" +
"G1 X108.200 Y104.230 E9.88047\n" +
"G1 X108.062 Y106.297 E9.93633\n" +
"G1 X108.094 Y107.756 E9.97571\n" +
"G1 X108.247 Y109.062 E10.01120\n" +
"G1 X108.485 Y110.157 E10.04142\n" +
"G1 X108.757 Y111.003 E10.06539\n" +
"G1 X109.070 Y111.725 E10.08661\n" +
"G1 X109.473 Y112.446 E10.10891\n" +
"G1 X109.971 Y113.156 E10.13229\n" +
"G1 X110.551 Y113.819 E10.15606\n" +
"G1 X111.179 Y114.401 E10.17916\n" +
"G1 X111.884 Y114.935 E10.20301\n" +
"G1 X112.665 Y115.410 E10.22768\n" +
"G1 X113.503 Y115.811 E10.25274\n" +
"G1 X114.797 Y116.279 E10.28985\n" +
"G1 X119.206 Y117.510 E10.41334\n" +
"G1 X122.220 Y118.095 E10.49618\n" +
"G1 X127.121 Y118.538 E10.62892\n" +
"G1 X134.815 Y118.583 E10.83648\n" +
"G1 X141.185 Y118.390 E11.00842\n" +
"G1 X147.084 Y117.695 E11.16864\n" +
"G1 X152.694 Y116.529 E11.32321\n" +
"G1 X157.586 Y115.101 E11.46069\n" +
"G1 X160.196 Y114.050 E11.53660\n" +
"G1 X161.951 Y113.176 E11.58948\n" +
"G1 X162.433 Y112.898 E11.60450\n" +
"G1 X162.810 Y112.640 E11.61681\n" +
"G1 X163.132 Y112.369 E11.62818\n" +
"G1 X163.381 Y112.110 E11.63786\n" +
"G1 X163.609 Y111.822 E11.64777\n" +
"G1 X163.904 Y111.366 E11.66243\n" +
"G1 X164.644 Y109.912 E11.70643\n" +
"G1 X164.985 Y109.048 E11.73151\n" +
"G1 X165.211 Y108.254 E11.75377\n" +
"G1 X165.377 Y107.309 E11.77965\n" +
"G1 X165.419 Y106.940 E11.78966\n" +
"G1 X165.495 Y105.246 E11.83543\n" +
"G1 X165.343 Y102.431 E11.91145\n" +
"G1 X164.731 Y98.403 E12.02138\n" +
"G1 X164.531 Y97.625 E12.04304\n" +
"G1 X165.493 Y100.660 E12.12892\n" +
"G1 X165.996 Y102.138 E12.17104\n" +
"G1 X166.305 Y102.846 E12.19188\n" +
"G1 X166.568 Y103.289 E12.20578\n" +
"G1 X166.784 Y103.559 E12.21511\n" +
"G1 X167.086 Y103.810 E12.22569\n" +
"G1 X167.410 Y103.975 E12.23550\n" +
"G1 X167.898 Y104.092 E12.24904\n" +
"G1 X168.627 Y104.110 E12.26871\n" +
"G1 X171.687 Y103.918 E12.35142\n" +
"G1 X172.398 Y103.942 E12.37062\n" +
"G1 X172.387 Y106.336 E12.43521\n" +
"G1 X172.465 Y110.716 E12.55337\n" +
"G1 X172.573 Y112.109 E12.59106\n" +
"G1 X168.977 Y113.081 E12.69156\n" +
"G1 X166.693 Y113.655 E12.75510\n" +
"G1 X163.863 Y114.480 E12.83460\n" +
"G1 X159.682 Y115.571 E12.95117\n" +
"G1 X158.894 Y115.750 E12.97297\n" +
"G1 X150.850 Y117.364 E13.19431\n" +
"G1 X149.531 Y117.603 E13.23045\n" +
"G1 X146.155 Y118.137 E13.32266\n" +
"G1 X144.134 Y118.427 E13.37776\n" +
"G1 X135.361 Y119.081 E13.61505\n" +
"G1 X133.477 Y119.081 E13.66588\n" +
"G1 X129.362 Y119.032 E13.77690\n" +
"G1 X127.182 Y118.984 E13.83573\n" +
"G1 X121.920 Y118.338 E13.97874\n" +
"G1 X118.692 Y117.657 E14.06775\n" +
"G1 X116.494 Y117.223 E14.12818\n" +
"G1 X115.586 Y116.998 E14.15342\n" +
"G1 X115.254 Y116.906 E14.16272\n" +
"G1 X112.667 Y116.070 E14.23606\n" +
"G1 X110.252 Y115.267 E14.30472\n" +
"G1 X107.056 Y114.291 E14.39485\n" +
"G1 X105.749 Y114.010 E14.43092\n" +
"G1 X104.139 Y113.696 E14.47517\n" +
"G1 X103.618 Y113.612 E14.48941\n" +
"G1 X101.987 Y113.436 E14.53366\n" +
"G1 X101.361 Y113.399 E14.55058\n" +
"G1 X100.578 Y113.396 E14.57170\n" +
"G1 X98.447 Y113.405 E14.62918\n" +
"G1 X96.990 Y113.538 E14.66866\n" +
"G1 X95.870 Y113.694 E14.69917\n" +
"G1 X94.251 Y114.010 E14.74367\n" +
"G1 X92.944 Y114.291 E14.77974\n" +
"G1 X89.748 Y115.267 E14.86986\n" +
"G1 X87.333 Y116.070 E14.93853\n" +
"G1 X84.746 Y116.906 E15.01186\n" +
"G1 X84.414 Y116.998 E15.02116\n" +
"G1 X83.500 Y117.224 E15.04656\n" +
"G1 X80.370 Y117.889 E15.13288\n" +
"G1 X78.086 Y118.338 E15.19569\n" +
"G1 X72.822 Y118.984 E15.33874\n" +
"G1 X68.718 Y119.032 E15.44948\n" +
"G1 X66.518 Y119.081 E15.50882\n" +
"G1 X64.638 Y119.081 E15.55953\n" +
"G1 X55.872 Y118.428 E15.79667\n" +
"G1 X52.509 Y117.896 E15.88852\n" +
"G1 X50.464 Y117.602 E15.94425\n" +
"G1 X48.350 Y117.211 E16.00225\n" +
"G1 X41.106 Y115.750 E16.20160\n" +
"G1 X40.318 Y115.571 E16.22342\n" +
"G1 X36.137 Y114.480 E16.33999\n" +
"G1 X33.313 Y113.657 E16.41933\n" +
"G1 X30.066 Y112.791 E16.50997\n" +
"G1 X27.498 Y112.135 E16.58148\n" +
"G1 E14.58148 F2400.00000\n" +
"G92 E0\n" +
"G1 X108.654 Y107.717 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X108.623 Y106.309 E2.02902\n" +
"G1 X108.757 Y104.292 E2.07069\n" +
"G1 X109.178 Y101.587 E2.12710\n" +
"G1 X110.587 Y96.079 E2.24429\n" +
"G1 X112.158 Y91.850 E2.33726\n" +
"G1 X112.947 Y90.069 E2.37742\n" +
"G1 X113.456 Y89.190 E2.39835\n" +
"G1 X114.035 Y88.388 E2.41874\n" +
"G1 X114.658 Y87.686 E2.43808\n" +
"G1 X115.399 Y86.999 E2.45891\n" +
"G1 X116.541 Y86.122 E2.48858\n" +
"G1 X117.357 Y85.603 E2.50851\n" +
"G1 X118.239 Y85.157 E2.52888\n" +
"G1 X119.552 Y84.639 E2.55797\n" +
"G1 X121.578 Y84.004 E2.60173\n" +
"G1 X128.590 Y82.412 E2.74994";
                                        salientemarcos[5]+="G1 X134.253 Y81.671 E2.86764\n" +
"G1 X141.621 Y81.379 E3.01961\n" +
"G1 X148.222 Y81.432 E3.15567\n" +
"G1 X151.177 Y81.725 E3.21687\n" +
"G1 X153.545 Y82.203 E3.26666\n" +
"G1 X154.961 Y82.634 E3.29716\n" +
"G1 X155.766 Y82.970 E3.31514\n" +
"G1 X156.332 Y83.280 E3.32845\n" +
"G1 X156.785 Y83.594 E3.33982\n" +
"G1 X157.257 Y83.992 E3.35254\n" +
"G1 X158.125 Y84.845 E3.37761\n" +
"G1 X159.186 Y86.006 E3.41004\n" +
"G1 X159.469 Y86.392 E3.41990\n" +
"G1 X159.809 Y86.974 E3.43379\n" +
"G1 X161.914 Y91.320 E3.53332\n" +
"G1 X163.281 Y95.008 E3.61439\n" +
"G1 X164.181 Y98.515 E3.68900\n" +
"G1 X164.785 Y102.489 E3.77184\n" +
"G1 X164.934 Y105.248 E3.82880\n" +
"G1 X164.860 Y106.896 E3.86280\n" +
"G1 X164.822 Y107.229 E3.86970\n" +
"G1 X164.664 Y108.128 E3.88852\n" +
"G1 X164.454 Y108.868 E3.90436\n" +
"G1 X164.132 Y109.682 E3.92240\n" +
"G1 X163.418 Y111.086 E3.95487\n" +
"G1 X163.153 Y111.495 E3.96491\n" +
"G1 X162.958 Y111.741 E3.97138\n" +
"G1 X162.748 Y111.959 E3.97762\n" +
"G1 X162.470 Y112.194 E3.98513\n" +
"G1 X162.134 Y112.424 E3.99351\n" +
"G1 X161.686 Y112.682 E4.00417\n" +
"G1 X159.966 Y113.538 E4.04377\n" +
"G1 X157.402 Y114.571 E4.10074\n" +
"G1 X152.558 Y115.985 E4.20474\n" +
"G1 X146.994 Y117.141 E4.32187\n" +
"G1 X141.144 Y117.831 E4.44328\n" +
"G1 X134.808 Y118.022 E4.57392\n" +
"G1 X127.148 Y117.978 E4.73181\n" +
"G1 X122.299 Y117.539 E4.83215\n" +
"G1 X119.335 Y116.964 E4.89438\n" +
"G1 X114.968 Y115.745 E4.98783\n" +
"G1 X113.720 Y115.293 E5.01518\n" +
"G1 X112.932 Y114.917 E5.03317\n" +
"G1 X112.200 Y114.471 E5.05085\n" +
"G1 X111.539 Y113.971 E5.06792\n" +
"G1 X110.953 Y113.428 E5.08438\n" +
"G1 X110.413 Y112.810 E5.10132\n" +
"G1 X109.949 Y112.148 E5.11797\n" +
"G1 X109.573 Y111.476 E5.13385\n" +
"G1 X109.282 Y110.806 E5.14890\n" +
"G1 X109.027 Y110.012 E5.16608\n" +
"G1 X108.801 Y108.970 E5.18805\n" +
"G1 X108.663 Y107.791 E5.21251\n" +
"G1 X108.654 Y107.717 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X152.510 Y81.422 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X152.959 Y81.485 E2.01223\n" +
"G1 X154.243 Y81.789 E2.04781\n" +
"G1 X155.119 Y82.097 E2.07287\n" +
"G1 X153.682 Y81.659 E2.11340\n" +
"G1 X152.584 Y81.437 E2.14363\n" +
"G1 E0.14363 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.581 Y83.531 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.878 Y83.748 E2.00991\n" +
"G1 X158.598 Y84.383 E2.03580\n" +
"G1 X159.241 Y85.065 E2.06109\n" +
"G1 X159.796 Y85.783 E2.08558\n" +
"G1 X160.280 Y86.547 E2.10998\n" +
"G1 X160.847 Y87.630 E2.14296\n" +
"G1 X161.257 Y88.549 E2.17011\n" +
"G1 X161.531 Y89.244 E2.19025\n" +
"G1 X160.304 Y86.710 E2.26620\n" +
"G1 X159.938 Y86.084 E2.28575\n" +
"G1 X159.620 Y85.651 E2.30026\n" +
"G1 X158.529 Y84.456 E2.34393\n" +
"G1 X157.638 Y83.580 E2.37762\n" +
"G1 E0.37762 F2400.00000\n" +
"G92 E0\n" +
"G1 X172.930 Y103.630 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X172.960 Y103.974 E2.00711\n" +
"G1 X172.947 Y106.333 E2.05572\n" +
"G1 X173.026 Y110.689 E2.14553\n" +
"G1 X173.154 Y112.342 E2.17970\n" +
"G1 X173.142 Y112.430 E2.18152\n" +
"G1 X173.082 Y112.501 E2.18344\n" +
"G1 X172.913 Y112.587 E2.18734\n" +
"G1 X172.505 Y112.721 E2.19620\n" +
"G1 X169.118 Y113.624 E2.26844\n" +
"G1 X166.840 Y114.197 E2.31686\n" +
"G1 X164.013 Y115.020 E2.37755\n" +
"G1 X159.815 Y116.116 E2.46695\n" +
"G1 X159.011 Y116.298 E2.48394\n" +
"G1 X150.955 Y117.915 E2.65330\n" +
"G1 X149.625 Y118.155 E2.68115\n" +
"G1 X146.239 Y118.691 E2.75181\n" +
"G1 X144.194 Y118.985 E2.79438\n" +
"G1 X135.382 Y119.642 E2.97650\n" +
"G1 X133.474 Y119.642 E3.01583\n" +
"G1 X129.353 Y119.593 E3.10078\n" +
"G1 X127.156 Y119.544 E3.14606\n" +
"G1 X121.828 Y118.892 E3.25671\n" +
"G1 X118.579 Y118.206 E3.32513\n" +
"G1 X116.372 Y117.770 E3.37150\n" +
"G1 X115.444 Y117.540 E3.39121\n" +
"G1 X115.093 Y117.444 E3.39872\n" +
"G1 X112.492 Y116.602 E3.45505\n" +
"G1 X110.081 Y115.801 E3.50741\n" +
"G1 X106.915 Y114.834 E3.57564\n" +
"G1 X105.637 Y114.559 E3.60260\n" +
"G1 X104.041 Y114.248 E3.63610\n" +
"G1 X103.543 Y114.168 E3.64649\n" +
"G1 X101.940 Y113.995 E3.67972\n" +
"G1 X101.343 Y113.959 E3.69205\n" +
"G1 X100.578 Y113.957 E3.70781\n" +
"G1 X98.474 Y113.965 E3.75119\n" +
"G1 X97.054 Y114.095 E3.78058\n" +
"G1 X95.962 Y114.247 E3.80330\n" +
"G1 X94.363 Y114.559 E3.83687\n" +
"G1 X93.085 Y114.834 E3.86383\n" +
"G1 X89.919 Y115.801 E3.93205\n" +
"G1 X87.508 Y116.602 E3.98442\n" +
"G1 X84.907 Y117.444 E4.04074\n" +
"G1 X84.556 Y117.540 E4.04826\n" +
"G1 X83.626 Y117.771 E4.06801\n" +
"G1 X80.482 Y118.439 E4.13424\n" +
"G1 X78.174 Y118.891 E4.18272\n" +
"G1 X72.845 Y119.544 E4.29337\n" +
"G1 X68.727 Y119.593 E4.37825\n" +
"G1 X66.525 Y119.642 E4.42366\n" +
"G1 X64.618 Y119.642 E4.46296\n" +
"G1 X55.808 Y118.985 E4.64504\n" +
"G1 X52.426 Y118.450 E4.71561\n" +
"G1 X50.374 Y118.155 E4.75834\n" +
"G1 X48.244 Y117.762 E4.80297\n" +
"G1 X40.989 Y116.298 E4.95552\n" +
"G1 X40.185 Y116.116 E4.97251\n" +
"G1 X35.987 Y115.020 E5.06192\n" +
"G1 X33.162 Y114.197 E5.12257\n" +
"G1 X29.925 Y113.334 E5.19161\n" +
"G1 X27.491 Y112.720 E5.24335\n" +
"G1 X27.167 Y112.619 E5.25034\n" +
"G1 X26.933 Y112.515 E5.25563\n" +
"G1 X26.858 Y112.430 E5.25796\n" +
"G1 X26.846 Y112.342 E5.25979\n" +
"G1 X26.974 Y110.689 E5.29396\n" +
"G1 X27.053 Y106.333 E5.38376\n" +
"G1 X27.044 Y103.851 E5.43491\n" +
"G1 X27.072 Y103.625 E5.43961\n" +
"G1 X27.123 Y103.524 E5.44195\n" +
"G1 X27.294 Y103.427 E5.44599\n" +
"G1 X27.682 Y103.369 E5.45408\n" +
"G1 X28.025 Y103.360 E5.46116\n" +
"G1 X28.563 Y103.367 E5.47223\n" +
"G1 X31.380 Y103.549 E5.53041\n" +
"G1 X31.807 Y103.550 E5.53922\n" +
"G1 X32.186 Y103.507 E5.54707\n" +
"G1 X32.443 Y103.426 E5.55263\n" +
"G1 X32.684 Y103.282 E5.55842\n" +
"G1 X32.910 Y103.054 E5.56503\n" +
"G1 X33.194 Y102.592 E5.57621\n" +
"G1 X33.481 Y101.935 E5.59098\n" +
"G1 X33.974 Y100.486 E5.62253\n" +
"G1 X35.360 Y96.253 E5.71433\n" +
"G1 X36.262 Y93.416 E5.77570\n" +
"G1 X36.734 Y92.114 E5.80422\n" +
"G1 X38.226 Y88.332 E5.88803\n" +
"G1 X38.649 Y87.386 E5.90939\n" +
"G1 X39.234 Y86.267 E5.93542\n" +
"G1 X39.744 Y85.461 E5.95507\n" +
"G1 X40.332 Y84.700 E5.97489\n" +
"G1 X41.010 Y83.981 E5.99526\n" +
"G1 X41.776 Y83.306 E6.01631\n" +
"G1 X42.748 Y82.598 E6.04108\n" +
"G1 X43.694 Y82.031 E6.06382\n" +
"G1 X44.569 Y81.615 E6.08379\n" +
"G1 X45.753 Y81.207 E6.10959\n" +
"G1 X46.937 Y80.933 E6.13464\n" +
"G1 X48.923 Y80.657 E6.17597\n" +
"G1 X49.648 Y80.597 E6.19097\n" +
"G1 X52.267 Y80.461 E6.24502\n" +
"G1 X54.151 Y80.387 E6.28386\n" +
"G1 X55.120 Y80.372 E6.30384\n" +
"G1 X64.291 Y80.446 E6.49287\n" +
"G1 X64.929 Y80.469 E6.50604\n" +
"G1 X67.947 Y80.693 E6.56840\n" +
"G1 X69.484 Y80.780 E6.60012\n" +
"G1 X70.956 Y80.917 E6.63059\n" +
"G1 X71.533 Y80.989 E6.64259\n" +
"G1 X76.294 Y81.767 E6.74202\n" +
"G1 X76.625 Y81.835 E6.74898\n" +
"G1 X79.594 Y82.578 E6.81205\n" +
"G1 X79.883 Y82.666 E6.81828\n" +
"G1 X81.563 Y83.275 E6.85512\n" +
"G1 X82.930 Y83.929 E6.88635\n" +
"G1 X84.295 Y84.737 E6.91904\n" +
"G1 X85.427 Y85.550 E6.94776\n" +
"G1 X86.227 Y86.254 E6.96973\n" +
"G1 X86.867 Y86.935 E6.98899\n" +
"G1 X87.414 Y87.648 E7.00751\n" +
"G1 X88.119 Y88.814 E7.03560\n" +
"G1 X88.799 Y90.241 E7.06818\n" +
"G1 X88.942 Y90.587 E7.07589\n" +
"G1 X90.307 Y94.362 E7.15863\n" +
"G1 X91.861 Y99.428 E7.26783\n" +
"G1 X92.323 Y101.020 E7.30200\n" +
"G1 X93.661 Y106.051 E7.40929\n" +
"G1 X93.958 Y106.831 E7.42650\n" +
"G1 X94.141 Y107.211 E7.43519\n" +
"G1 X94.375 Y107.600 E7.44454\n" +
"G1 X94.556 Y107.849 E7.45089\n" +
"G1 X94.806 Y108.135 E7.45871\n" +
"G1 X95.097 Y108.407 E7.46693\n" +
"G1 X95.428 Y108.661 E7.47553\n" +
"G1 X95.807 Y108.897 E7.48472\n" +
"G1 X96.265 Y109.122 E7.49525\n" +
"G1 X96.773 Y109.316 E7.50646\n" +
"G1 X97.387 Y109.489 E7.51960\n" +
"G1 X97.986 Y109.604 E7.53218\n" +
"G1 X98.918 Y109.705 E7.55149\n" +
"G1 X100.605 Y109.714 E7.58627\n" +
"G1 X101.085 Y109.705 E7.59615\n" +
"G1 X101.875 Y109.627 E7.61252\n" +
"G1 X102.449 Y109.523 E7.62455\n" +
"G1 X103.164 Y109.336 E7.63978\n" +
"G1 X103.734 Y109.123 E7.65233\n" +
"G1 X104.193 Y108.897 E7.66287\n" +
"G1 X104.572 Y108.661 E7.67207\n" +
"G1 X104.906 Y108.405 E7.68073\n" +
"G1 X105.325 Y107.992 E7.69287\n" +
"G1 X105.532 Y107.733 E7.69971\n" +
"G1 X105.712 Y107.465 E7.70634\n" +
"G1 X106.040 Y106.835 E7.72099\n" +
"G1 X106.339 Y106.051 E7.73829\n" +
"G1 X107.677 Y101.020 E7.84558\n" +
"G1 X108.139 Y99.428 E7.87975\n" +
"G1 X109.693 Y94.362 E7.98896\n" +
"G1 X110.951 Y90.866 E8.06554\n" +
"G1 X111.201 Y90.241 E8.07942\n" +
"G1 X111.974 Y88.641 E8.11603\n" +
"G1 X112.510 Y87.761 E8.13727\n" +
"G1 X113.069 Y87.012 E8.15653\n" +
"G1 X113.774 Y86.252 E8.17789\n" +
"G1 X114.323 Y85.761 E8.19308\n" +
"G1 X114.722 Y85.434 E8.20371\n" +
"G1 X115.706 Y84.736 E8.22857\n" +
"G1 X117.070 Y83.929 E8.26123\n" +
"G1 X118.437 Y83.275 E8.29246\n" +
"G1 X120.117 Y82.666 E8.32930\n" +
"G1 X120.406 Y82.578 E8.33553\n" +
"G1 X123.375 Y81.835 E8.39860\n" +
"G1 X123.706 Y81.767 E8.40556\n" +
"G1 X128.467 Y80.989 E8.50499\n" +
"G1 X129.044 Y80.917 E8.51699\n" +
"G1 X130.519 Y80.779 E8.54751\n" +
"G1 X133.533 Y80.555 E8.60982\n" +
"G1 X135.711 Y80.446 E8.65475\n" +
"G1 X144.880 Y80.372 E8.84374\n" +
"G1 X145.848 Y80.387 E8.86368\n" +
"G1 X148.469 Y80.522 E8.91779\n" +
"G1 X150.353 Y80.597 E8.95665\n" +
"G1 X151.077 Y80.657 E8.97160\n" +
"G1 X153.063 Y80.933 E9.01293\n" +
"G1 X154.400 Y81.250 E9.04125\n" +
"G1 X155.278 Y81.554 E9.06041\n" +
"G1 X156.305 Y82.031 E9.08374\n" +
"G1 X157.252 Y82.598 E9.10650\n" +
"G1 X158.225 Y83.307 E9.13132\n" +
"G1 X158.989 Y83.980 E9.15230\n" +
"G1 X159.668 Y84.700 E9.17269\n" +
"G1 X160.256 Y85.461 E9.19251\n" +
"G1 X160.766 Y86.267 E9.21216\n" +
"G1 X161.351 Y87.386 E9.23819\n" +
"G1 X161.774 Y88.332 E9.25955\n" +
"G1 X163.266 Y92.114 E9.34336\n" +
"G1 X163.738 Y93.417 E9.37191\n" +
"G1 X165.014 Y97.295 E9.45605\n" +
"G1 X166.026 Y100.485 E9.52502\n" +
"G1 X166.519 Y101.935 E9.55660\n" +
"G1 X166.805 Y102.590 E9.57132\n" +
"G1 X167.030 Y102.970 E9.58042\n" +
"G1 X167.186 Y103.164 E9.58556\n" +
"G1 X167.396 Y103.339 E9.59118\n" +
"G1 X167.605 Y103.445 E9.59602\n" +
"G1 X167.971 Y103.533 E9.60377\n" +
"G1 X168.616 Y103.549 E9.61708\n" +
"G1 X171.674 Y103.357 E9.68022\n" +
"G1 X172.302 Y103.368 E9.69318\n" +
"G1 X172.556 Y103.395 E9.69843\n" +
"G1 X172.766 Y103.449 E9.70291\n" +
"G1 X172.869 Y103.515 E9.70543\n" +
"G1 X172.895 Y103.564 E9.70658\n" +
"G1 X172.828 Y103.751 F7800.000\n" +
"G1 E7.70658 F2400.00000\n" +
"G92 E0\n" +
"G1 X166.511 Y105.317 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X167.006 Y104.823 E2.02015\n" +
"G1 X167.214 Y104.929 E2.02688\n" +
"G1 X167.735 Y105.054 E2.04232\n" +
"G1 X166.484 Y106.305 E2.09330\n" +
"G1 X166.437 Y107.312 E2.12234\n" +
"G1 X168.621 Y105.128 E2.21132\n" +
"G1 X168.898 Y105.135 E2.21932\n" +
"G1 X169.618 Y105.091 E2.24011\n" +
"G1 X166.265 Y108.444 E2.37678\n" +
"G1 X166.204 Y108.786 E2.38678\n" +
"G1 X165.929 Y109.740 E2.41540\n" +
"G1 X170.641 Y105.028 E2.60744\n" +
"G1 X171.368 Y104.984 E2.62844\n" +
"G1 X171.367 Y105.263 E2.63647\n" +
"G1 X165.153 Y111.477 E2.88974\n" +
"G1 X164.760 Y112.249 E2.91470\n" +
"G1 X164.395 Y112.812 E2.93402\n" +
"G1 X163.999 Y113.291 E2.95197\n" +
"G1 X164.422 Y113.168 E2.96467\n" +
"G1 X171.365 Y106.225 E3.24764\n" +
"G1 X171.382 Y107.168 E3.27483\n" +
"G1 X165.777 Y112.773 E3.50326\n" +
"G1 X167.078 Y112.433 E3.54200\n" +
"G1 X171.399 Y108.112 E3.71811\n" +
"G1 X171.416 Y109.055 E3.74530\n" +
"G1 X168.360 Y112.110 E3.86982\n" +
"G1 X169.669 Y111.762 E3.90883\n" +
"G1 X171.433 Y109.998 E3.98072\n" +
"G1 X171.442 Y110.529 E3.99602\n" +
"G1 X171.472 Y110.919 E4.00728\n" +
"G1 X170.978 Y111.413 E4.02744\n" +
"G1 E2.02744 F2400.00000\n" +
"G92 E0\n" +
"G1 X108.370 Y113.687 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X108.865 Y113.193 E2.01918\n" +
"G1 X108.643 Y112.876 E2.02979\n" +
"G1 X108.508 Y112.635 E2.03736\n" +
"G1 X107.670 Y113.473 E2.06985\n" +
"G1 X106.959 Y113.270 E2.09014\n" +
"G1 X108.180 Y112.049 E2.13752\n" +
"G1 X107.903 Y111.412 E2.15658\n" +
"G1 X106.207 Y113.108 E2.22238\n" +
"G1 X105.449 Y112.951 E2.24359\n" +
"G1 X107.663 Y110.738 E2.32946\n" +
"G1 X107.503 Y110.240 E2.34381\n" +
"G1 X107.458 Y110.029 E2.34972\n" +
"G1 X104.685 Y112.802 E2.45729\n" +
"G1 X103.914 Y112.659 E2.47880\n" +
"G1 X107.294 Y109.278 E2.60994\n" +
"G1 X107.239 Y109.025 E2.61706\n" +
"G1 X107.195 Y108.648 E2.62745\n" +
"G1 X106.935 Y108.973 E2.63886\n" +
"G1 X106.358 Y109.543 E2.66110\n" +
"G1 X105.862 Y109.922 E2.67823\n" +
"G1 X105.529 Y110.129 E2.68899\n" +
"G1 X103.107 Y112.552 E2.78297\n" +
"G1 X102.282 Y112.463 E2.80573\n" +
"G1 X103.886 Y110.858 E2.86798\n" +
"G1 X103.091 Y111.066 E2.89053\n" +
"G1 X102.693 Y111.138 E2.90164\n" +
"G1 X101.439 Y112.392 E2.95028\n" +
"G1 X100.544 Y112.373 E2.97484\n" +
"G1 X101.649 Y111.267 E3.01772\n" +
"G1 X101.434 Y111.289 E3.02365\n" +
"G1 X100.713 Y111.289 E3.04342\n" +
"G1 X99.628 Y112.375 E3.08555\n" +
"G1 X98.710 Y112.379 E3.11073\n" +
"G1 X99.799 Y111.289 E3.15300\n" +
"G1 X99.071 Y111.290 E3.17298\n" +
"G1 X98.903 Y111.271 E3.17761\n" +
"G1 X97.758 Y112.416 E3.22201\n" +
"G1 X96.752 Y112.508 E3.24973\n" +
"G1 X98.078 Y111.182 E3.30117\n" +
"G1 X97.303 Y111.043 E3.32278\n" +
"G1 X95.699 Y112.647 E3.38499\n" +
"G1 X94.584 Y112.849 E3.41609\n" +
"G1 X96.583 Y110.849 E3.49366\n" +
"G1 X95.911 Y110.607 E3.51325\n" +
"G1 X93.440 Y113.078 E3.60912\n" +
"G1 X92.262 Y113.342 E3.64224\n" +
"G1 X95.289 Y110.315 E3.75968\n" +
"G1 X94.716 Y109.974 E3.77798\n" +
"G1 X91.406 Y113.284 E3.90638\n" +
"G1 X91.824 Y112.537 E3.92988\n" +
"G1 X92.187 Y111.699 E3.95493\n" +
"G1 X92.239 Y111.536 E3.95961\n" +
"G1 X94.191 Y109.585 E4.03533\n" +
"G1 X93.718 Y109.144 E4.05307\n" +
"G1 X92.599 Y110.263 E4.09648\n" +
"G1 X92.761 Y109.519 E4.11735\n" +
"G1 X92.805 Y109.143 E4.12774\n" +
"G1 X93.299 Y108.648 E4.14693\n" +
"G1 E2.14693 F2400.00000\n" +
"G92 E0\n" +
"G1 X35.507 Y113.291 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X36.001 Y112.797 E2.01927\n" +
"G1 X35.588 Y112.292 E2.03726\n" +
"G1 X34.796 Y113.084 E2.06815\n" +
"G1 X34.085 Y112.877 E2.08857\n" +
"G1 X35.229 Y111.733 E2.13317\n" +
"G1 X34.919 Y111.124 E2.15199\n" +
"G1 X33.373 Y112.670 E2.21227\n" +
"G1 X32.648 Y112.477 E2.23296\n" +
"G1 X34.610 Y110.515 E2.30943\n" +
"G1 X34.443 Y110.188 E2.31954\n" +
"G1 X34.323 Y109.883 E2.32857\n" +
"G1 X31.923 Y112.284 E2.42213\n" +
"G1 X31.197 Y112.090 E2.44282\n" +
"G1 X34.063 Y109.225 E2.55450\n" +
"G1 X33.858 Y108.511 E2.57496\n" +
"G1 X30.472 Y111.897 E2.70693\n" +
"G1 X29.743 Y111.708 E2.72770\n" +
"G1 X33.700 Y107.751 E2.88196\n" +
"G1 X33.578 Y106.954 E2.90416\n" +
"G1 X29.009 Y111.523 E3.08225\n" +
"G1 X28.529 Y111.402 E3.09592\n" +
"G1 X28.555 Y111.059 E3.10539\n" +
"G1 X33.528 Y106.086 E3.29925\n" +
"G1 X33.489 Y105.207 E3.32350\n" +
"G1 X28.574 Y110.122 E3.51509\n" +
"G1 X28.591 Y109.187 E3.54087\n" +
"G1 X32.705 Y105.072 E3.70125\n" +
"G1 X32.156 Y105.136 E3.71649\n" +
"G1 X31.725 Y105.134 E3.72837\n" +
"G1 X28.608 Y108.251 E3.84988\n" +
"G1 X28.624 Y107.316 E3.87566\n" +
"G1 X30.853 Y105.087 E3.96254\n" +
"G1 X29.991 Y105.032 E3.98637\n" +
"G1 X28.637 Y106.386 E4.03915\n" +
"G1 X28.633 Y105.470 E4.06437\n" +
"G1 X29.128 Y104.976 E4.08365\n" +
"G1 Z2.450 F7800.000\n" +
"G1 E2.08365 F2400.00000\n" +
"G92 E0\n" +
"G1 X91.377 Y106.309 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X91.346 Y107.708 E2.02886\n" +
"G1 X91.202 Y108.952 E2.05467\n" +
"G1 X90.973 Y110.012 E2.07700\n" +
"G1 X90.718 Y110.806 E2.09419\n" +
"G1 X90.427 Y111.476 E2.10925\n" +
"G1 X90.051 Y112.148 E2.12512\n" +
"G1 X89.587 Y112.810 E2.14177\n" +
"G1 X89.047 Y113.428 E2.15871\n" +
"G1 X88.461 Y113.971 E2.17517\n" +
"G1 X87.800 Y114.471 E2.19224\n" +
"G1 X87.068 Y114.917 E2.20992\n" +
"G1 X86.280 Y115.293 E2.22791\n" +
"G1 X85.032 Y115.745 E2.25526\n" +
"G1 X80.665 Y116.964 E2.34871\n" +
"G1 X77.701 Y117.539 E2.41095\n" +
"G1 X72.852 Y117.978 E2.51128\n" +
"G1 X65.192 Y118.022 E2.66917\n" +
"G1 X58.856 Y117.831 E2.79982\n" +
"G1 X53.006 Y117.141 E2.92122\n" +
"G1 X47.442 Y115.985 E3.03835\n" +
"G1 X42.598 Y114.571 E3.14235\n" +
"G1 X40.034 Y113.538 E3.19932\n" +
"G1 X38.314 Y112.682 E3.23892\n" +
"G1 X37.844 Y112.410 E3.25010\n" +
"G1 X37.530 Y112.193 E3.25797\n" +
"G1 X37.252 Y111.959 E3.26547\n" +
"G1 X37.042 Y111.741 E3.27171\n" +
"G1 X36.847 Y111.495 E3.27818\n" +
"G1 X36.582 Y111.086 E3.28821\n" +
"G1 X35.905 Y109.763 E3.31884\n" +
"G1 X35.546 Y108.868 E3.33872\n" +
"G1 X35.336 Y108.128 E3.35457\n" +
"G1 X35.178 Y107.229 E3.37339\n" +
"G1 X35.140 Y106.896 E3.38029\n" +
"G1 X35.066 Y105.248 E3.41429\n" +
"G1 X35.215 Y102.489 E3.47125\n" +
"G1 X35.819 Y98.515 E3.55409\n" +
"G1 X36.719 Y95.008 E3.62870\n" +
"G1 X38.086 Y91.320 E3.70977\n" +
"G1 X40.191 Y86.974 E3.80930\n" +
"G1 X40.531 Y86.392 E3.82319\n" +
"G1 X40.814 Y86.007 E3.83305\n" +
"G1 X41.823 Y84.898 E3.86395\n" +
"G1 X42.742 Y83.992 E3.89055\n" +
"G1 X43.215 Y83.594 E3.90327\n" +
"G1 X43.668 Y83.280 E3.91464\n" +
"G1 X44.234 Y82.970 E3.92795\n" +
"G1 X45.039 Y82.634 E3.94593\n" +
"G1 X46.455 Y82.203 E3.97643\n" +
"G1 X48.823 Y81.725 E4.02622\n" +
"G1 X51.778 Y81.432 E4.08743\n" +
"G1 X58.379 Y81.379 E4.22348\n" +
"G1 X65.747 Y81.671 E4.37545\n" +
"G1 X71.410 Y82.412 E4.49315\n" +
"G1 X78.422 Y84.004 E4.64136\n" +
"G1 X80.448 Y84.639 E4.68512\n" +
"G1 X81.761 Y85.157 E4.71421\n" +
"G1 X82.643 Y85.603 E4.73458\n" +
"G1 X83.459 Y86.122 E4.75451\n" +
"G1 X84.601 Y86.999 E4.78418\n" +
"G1 X85.342 Y87.686 E4.80501\n" +
"G1 X85.965 Y88.388 E4.82435\n" +
"G1 X86.544 Y89.190 E4.84474\n" +
"G1 X87.053 Y90.069 E4.86567\n" +
"G1 X87.789 Y91.721 E4.90295\n" +
"G1 X89.413 Y96.079 E4.99880\n" +
"G1 X90.790 Y101.448 E5.11304\n" +
"G1 X90.901 Y102.034 E5.12533\n" +
"G1 X91.243 Y104.292 E5.17241\n" +
"G1 X91.372 Y106.234 E5.21251\n" +
"G1 X91.377 Y106.309 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X47.458 Y81.428 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X46.318 Y81.659 E2.03139\n" +
"G1 X44.969 Y82.070 E2.06943\n" +
"G1 X45.864 Y81.759 E2.09499\n" +
"G1 X46.986 Y81.495 E2.12609\n" +
"G1 X47.384 Y81.439 E2.13693\n" +
"G1 E0.13693 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.364 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X41.419 Y84.509 E2.03582\n" +
"G1 X40.379 Y85.651 E2.07747\n" +
"G1 X40.062 Y86.084 E2.09197\n" +
"G1 X39.696 Y86.710 E2.11152\n" +
"G1 X38.502 Y89.175 E2.18539\n" +
"G1 X38.828 Y88.352 E2.20927\n" +
"G1 X39.111 Y87.719 E2.22798\n" +
"G1 X39.702 Y86.578 E2.26265\n" +
"G1 X40.204 Y85.783 E2.28802\n" +
"G1 X40.759 Y85.065 E2.31249\n" +
"G1 X41.400 Y84.385 E2.33770\n" +
"G1 X42.135 Y83.738 E2.36412\n" +
"G1 X42.422 Y83.529 E2.37368\n" +
"G1 E0.37368 F2400.00000\n" +
"G92 E0\n" +
"G1 X159.941 Y114.838 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.457 Y114.630 E2.01499\n" +
"G1 X162.253 Y113.736 E2.06912\n" +
"G1 X162.796 Y113.422 E2.08605\n" +
"G1 X163.196 Y113.145 E2.09917\n" +
"G1 X163.567 Y112.833 E2.11224\n" +
"G1 X163.860 Y112.528 E2.12365\n" +
"G1 X164.126 Y112.193 E2.13519\n" +
"G1 X164.455 Y111.686 E2.15151\n" +
"G1 X165.185 Y110.259 E2.19473\n" +
"G1 X165.587 Y109.255 E2.22390\n" +
"G1 X165.831 Y108.396 E2.24799\n" +
"G1 X166.007 Y107.400 E2.27529\n" +
"G1 X166.053 Y106.990 E2.28642\n" +
"G1 X166.131 Y105.243 E2.33361\n" +
"G1 X166.045 Y103.650 E2.37662\n" +
"G1 X166.323 Y104.002 E2.38871\n" +
"G1 X166.717 Y104.332 E2.40257\n" +
"G1 X167.168 Y104.568 E2.41631\n" +
"G1 X167.807 Y104.725 E2.43407\n" +
"G1 X168.575 Y104.748 E2.45479\n" +
"G1 X171.760 Y104.555 E2.54087\n" +
"G1 X171.751 Y106.080 E2.58200\n" +
"G1 X171.833 Y110.819 E2.70986\n" +
"G1 X171.899 Y111.644 E2.73220\n" +
"G1 X167.824 Y112.730 E2.84595\n" +
"G1 X166.215 Y113.135 E2.89071\n" +
"G1 X164.230 Y113.713 E2.94649\n" +
"G1 X160.014 Y114.818 E3.06407\n" +
"G1 E1.06407 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.570 Y114.704 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.168 Y113.663 E2.09597\n" +
"G1 X106.204 Y113.456 E2.12255\n" +
"G1 X103.718 Y112.983 E2.19083\n" +
"G1 X101.592 Y112.770 E2.24848\n" +
"G1 X100.407 Y112.761 E2.28043\n" +
"G1 X98.415 Y112.769 E2.33419\n" +
"G1 X96.737 Y112.929 E2.37964\n" +
"G1 X95.907 Y113.044 E2.40226\n" +
"G1 X92.842 Y113.660 E2.48659\n" +
"G1 X89.430 Y114.704 E2.58283\n" +
"G1 X89.906 Y114.263 E2.60032\n" +
"G1 X90.530 Y113.549 E2.62591\n" +
"G1 X91.066 Y112.785 E2.65107\n" +
"G1 X91.501 Y112.007 E2.67511\n" +
"G1 X91.839 Y111.227 E2.69804\n" +
"G1 X92.129 Y110.322 E2.72370\n" +
"G1 X92.383 Y109.149 E2.75608\n" +
"G1 X92.541 Y107.791 E2.79296\n" +
"G1 X92.568 Y106.543 E2.82663\n" +
"G1 X92.875 Y107.343 E2.84973\n" +
"G1 X93.096 Y107.794 E2.86329\n" +
"G1 X93.389 Y108.280 E2.87860\n" +
"G1 X93.627 Y108.604 E2.88945\n" +
"G1 X93.945 Y108.967 E2.90247\n" +
"G1 X94.322 Y109.321 E2.91641\n" +
"G1 X94.744 Y109.645 E2.93077\n" +
"G1 X95.220 Y109.940 E2.94587\n" +
"G1 X95.786 Y110.220 E2.96291\n" +
"G1 X96.398 Y110.453 E2.98056\n" +
"G1 X97.125 Y110.658 E3.00095\n" +
"G1 X98.121 Y110.830 E3.02821\n" +
"G1 X98.895 Y110.904 E3.04919\n" +
"G1 X100.779 Y110.909 E3.10002\n" +
"G1 X101.119 Y110.902 E3.10918\n" +
"G1 X102.042 Y110.812 E3.13419\n" +
"G1 X102.783 Y110.676 E3.15453\n" +
"G1 X103.558 Y110.468 E3.17618\n" +
"G1 X104.212 Y110.221 E3.19503\n" +
"G1 X104.780 Y109.940 E3.21214\n" +
"G1 X105.256 Y109.645 E3.22724\n" +
"G1 X105.694 Y109.309 E3.24213\n" +
"G1 X106.223 Y108.785 E3.26223\n" +
"G1 X106.493 Y108.447 E3.27388\n" +
"G1 X106.756 Y108.054 E3.28666\n" +
"G1 X107.118 Y107.361 E3.30773\n" +
"G1 X107.431 Y106.543 E3.33136\n" +
"G1 X107.459 Y107.797 E3.36518\n" +
"G1 X107.617 Y109.149 E3.40190\n" +
"G1 X107.870 Y110.322 E3.43427\n" +
"G1 X108.161 Y111.227 E3.45993\n" +
"G1 X108.499 Y112.007 E3.48286\n" +
"G1 X108.934 Y112.785 E3.50691\n" +
"G1 X109.474 Y113.554 E3.53224\n" +
"G1 X110.094 Y114.263 E3.55766\n" +
"G1 X110.515 Y114.653 E3.57312\n" +
"G1 E1.57312 F2400.00000\n" +
"G92 E0\n" +
"G1 X40.059 Y114.837 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X35.770 Y113.713 E2.11960\n" +
"G1 X33.795 Y113.138 E2.17510\n" +
"G1 X29.568 Y112.011 E2.29312\n" +
"G1 X28.102 Y111.640 E2.33392\n" +
"G1 X28.167 Y110.819 E2.35613\n" +
"G1 X28.249 Y106.082 E2.48394\n" +
"G1 X28.244 Y104.561 E2.52496\n" +
"G1 X28.597 Y104.566 E2.53448\n" +
"G1 X31.401 Y104.747 E2.61029\n" +
"G1 X31.876 Y104.747 E2.62311\n" +
"G1 X32.438 Y104.681 E2.63837\n" +
"G1 X32.918 Y104.531 E2.65192\n" +
"G1 X33.373 Y104.274 E2.66602\n" +
"G1 X33.844 Y103.816 E2.68374\n" +
"G1 X33.955 Y103.638 E2.68943\n" +
"G1 X33.869 Y105.243 E2.73279\n" +
"G1 X33.947 Y106.990 E2.77998\n" +
"G1 X33.993 Y107.400 E2.79111\n" +
"G1 X34.169 Y108.396 E2.81840\n" +
"G1 X34.413 Y109.255 E2.84249\n" +
"G1 X34.815 Y110.259 E2.87167\n" +
"G1 X35.545 Y111.686 E2.91489\n" +
"G1 X35.874 Y112.193 E2.93121\n" +
"G1 X36.140 Y112.528 E2.94275\n" +
"G1 X36.433 Y112.833 E2.95415\n" +
"G1 X36.804 Y113.145 E2.96723\n" +
"G1 X37.204 Y113.422 E2.98035\n" +
"G1 X37.747 Y113.736 E2.99728\n" +
"G1 X39.543 Y114.630 E3.05141\n" +
"G1 X39.989 Y114.809 E3.06437\n" +
"G1 E1.06437 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.427 Y112.114 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.531 Y110.788 E2.03587\n" +
"G1 X27.614 Y106.077 E2.16297\n" +
"G1 X27.606 Y103.948 E2.22042\n" +
"G1 X27.945 Y103.922 E2.22959\n" +
"G1 X28.621 Y103.930 E2.24784\n" +
"G1 X31.421 Y104.111 E2.32354\n" +
"G1 X31.839 Y104.111 E2.33481\n" +
"G1 X32.305 Y104.057 E2.34746\n" +
"G1 X32.664 Y103.944 E2.35759\n" +
"G1 X32.989 Y103.761 E2.36766\n" +
"G1 X33.345 Y103.414 E2.38109\n" +
"G1 X33.672 Y102.891 E2.39771\n" +
"G1 X33.987 Y102.181 E2.41867\n" +
"G1 X35.354 Y98.072 E2.53550\n" +
"G1 X35.269 Y98.403 E2.54472\n" +
"G1 X34.657 Y102.431 E2.65464\n" +
"G1 X34.505 Y105.246 E2.73067\n" +
"G1 X34.581 Y106.940 E2.77643\n" +
"G1 X34.622 Y107.309 E2.78645\n" +
"G1 X34.789 Y108.254 E2.81233\n" +
"G1 X35.015 Y109.050 E2.83464\n" +
"G1 X35.394 Y109.996 E2.86214\n" +
"G1 X36.096 Y111.367 E2.90369\n" +
"G1 X36.391 Y111.822 E2.91832\n" +
"G1 X36.619 Y112.110 E2.92823\n" +
"G1 X36.868 Y112.369 E2.93791\n" +
"G1 X37.190 Y112.640 E2.94926\n" +
"G1 X37.544 Y112.884 E2.96088\n" +
"G1 X38.048 Y113.176 E2.97659\n" +
"G1 X39.804 Y114.050 E3.02950\n" +
"G1 X42.414 Y115.101 E3.10540\n" +
"G1 X47.306 Y116.529 E3.24288\n" +
"G1 X52.916 Y117.695 E3.39745\n" +
"G1 X58.815 Y118.390 E3.55768\n" +
"G1 X65.185 Y118.583 E3.72961\n" +
"G1 X72.879 Y118.538 E3.93718\n" +
"G1 X77.780 Y118.095 E4.06991\n" +
"G1 X80.794 Y117.510 E4.15275\n" +
"G1 X85.203 Y116.279 E4.27624\n" +
"G1 X86.497 Y115.811 E4.31335\n" +
"G1 X87.335 Y115.410 E4.33841\n" +
"G1 X88.116 Y114.935 E4.36308\n" +
"G1 X88.821 Y114.401 E4.38693\n" +
"G1 X89.449 Y113.819 E4.41003\n" +
"G1 X90.029 Y113.156 E4.43380\n" +
"G1 X90.527 Y112.446 E4.45718\n" +
"G1 X90.930 Y111.725 E4.47948\n" +
"G1 X91.243 Y111.003 E4.50070\n" +
"G1 X91.515 Y110.157 E4.52468\n" +
"G1 X91.756 Y109.044 E4.55539\n" +
"G1 X91.906 Y107.747 E4.59062\n" +
"G1 X91.938 Y106.296 E4.62977\n" +
"G1 X91.800 Y104.232 E4.68558\n" +
"G1 X91.453 Y101.940 E4.74812\n" +
"G1 X91.337 Y101.326 E4.76497\n" +
"G1 X89.948 Y95.911 E4.91578\n" +
"G1 X88.308 Y91.509 E5.04251\n" +
"G1 X87.553 Y89.813 E5.09257\n" +
"G1 X87.015 Y88.884 E5.12153\n" +
"G1 X86.403 Y88.037 E5.14975\n" +
"G1 X85.743 Y87.294 E5.17654\n" +
"G1 X84.963 Y86.570 E5.20525\n" +
"G1 X83.780 Y85.663 E5.24546\n" +
"G1 X82.921 Y85.115 E5.27295\n" +
"G1 X81.991 Y84.645 E5.30106\n" +
"G1 X80.635 Y84.110 E5.34038\n" +
"G1 X78.568 Y83.462 E5.39881\n" +
"G1 X71.508 Y81.859 E5.59411\n" +
"G1 X65.795 Y81.112 E5.74956\n" +
"G1 X63.020 Y81.002 E5.82447\n" +
"G1 X64.899 Y81.029 E5.87515\n" +
"G1 X68.817 Y81.320 E5.98114\n" +
"G1 X69.883 Y81.380 E6.00994\n" +
"G1 X71.286 Y81.524 E6.04799\n" +
"G1 X76.515 Y82.385 E6.19095\n" +
"G1 X79.695 Y83.195 E6.27947\n" +
"G1 X81.324 Y83.783 E6.32619\n" +
"G1 X82.666 Y84.424 E6.36632\n" +
"G1 X84.027 Y85.232 E6.40902\n" +
"G1 X85.076 Y85.988 E6.44389\n" +
"G1 X85.837 Y86.657 E6.47124\n" +
"G1 X86.439 Y87.299 E6.49497\n" +
"G1 X86.951 Y87.965 E6.51765\n" +
"G1 X87.598 Y89.030 E6.55125\n" +
"G1 X88.278 Y90.450 E6.59371\n" +
"G1 X88.454 Y90.876 E6.60617\n" +
"G1 X89.787 Y94.576 E6.71226\n" +
"G1 X91.184 Y99.102 E6.84004\n" +
"G1 X91.781 Y101.163 E6.89793\n" +
"G1 X93.127 Y106.224 E7.03919\n" +
"G1 X93.459 Y107.089 E7.06418\n" +
"G1 X93.654 Y107.490 E7.07622\n" +
"G1 X93.918 Y107.927 E7.08999\n" +
"G1 X94.123 Y108.206 E7.09933\n" +
"G1 X94.403 Y108.525 E7.11076\n" +
"G1 X94.734 Y108.835 E7.12301\n" +
"G1 X95.107 Y109.121 E7.13569\n" +
"G1 X95.529 Y109.384 E7.14911\n" +
"G1 X96.040 Y109.637 E7.16450\n" +
"G1 X96.597 Y109.849 E7.18057\n" +
"G1 X97.265 Y110.037 E7.19930\n" +
"G1 X98.205 Y110.199 E7.22502\n" +
"G1 X98.926 Y110.268 E7.24456\n" +
"G1 X100.774 Y110.273 E7.29441\n" +
"G1 X101.082 Y110.267 E7.30271\n" +
"G1 X101.953 Y110.182 E7.32633\n" +
"G1 X102.643 Y110.056 E7.34525\n" +
"G1 X103.363 Y109.862 E7.36537\n" +
"G1 X103.958 Y109.638 E7.38253\n" +
"G1 X104.471 Y109.384 E7.39796\n" +
"G1 X104.893 Y109.121 E7.41138\n" +
"G1 X105.275 Y108.829 E7.42436\n" +
"G1 X105.750 Y108.359 E7.44238\n" +
"G1 X105.980 Y108.072 E7.45230\n" +
"G1 X106.209 Y107.729 E7.46342\n" +
"G1 X106.537 Y107.099 E7.48258\n" +
"G1 X106.873 Y106.224 E7.50786\n" +
"G1 X108.219 Y101.163 E7.64913\n" +
"G1 X108.816 Y99.102 E7.70702\n" +
"G1 X110.216 Y94.568 E7.83502\n" +
"G1 X110.452 Y93.870 E7.85492\n" +
"G1 X111.407 Y91.237 E7.93048\n" +
"G1 X111.723 Y90.447 E7.95344\n" +
"G1 X112.466 Y88.912 E7.99943\n" +
"G1 X113.048 Y87.966 E8.02938\n" +
"G1 X113.517 Y87.351 E8.05024\n" +
"G1 X114.166 Y86.654 E8.07593\n" +
"G1 X115.017 Y85.915 E8.10635\n" +
"G1 X115.978 Y85.229 E8.13822\n" +
"G1 X117.334 Y84.424 E8.18074\n" +
"G1 X118.674 Y83.784 E8.22081\n" +
"G1 X120.512 Y83.132 E8.27342\n" +
"G1 X123.721 Y82.337 E8.36260\n" +
"G1 X128.714 Y81.524 E8.49905\n" +
"G1 X130.125 Y81.379 E8.53733\n" +
"G1 X134.030 Y81.089 E8.64296\n" +
"G1 X135.533 Y81.013 E8.68356\n" +
"G1 X136.980 Y81.002 E8.72259\n" +
"G1 X134.205 Y81.112 E8.79750\n" +
"G1 X128.492 Y81.859 E8.95295\n" +
"G1 X121.432 Y83.462 E9.14825\n" +
"G1 X119.365 Y84.110 E9.20668\n" +
"G1 X118.009 Y84.645 E9.24600\n" +
"G1 X117.079 Y85.115 E9.27411\n" +
"G1 X116.220 Y85.663 E9.30161\n" +
"G1 X115.037 Y86.570 E9.34181\n" +
"G1 X114.257 Y87.294 E9.37052\n" +
"G1 X113.597 Y88.037 E9.39732\n" +
"G1 X112.985 Y88.884 E9.42553\n" +
"G1 X112.450 Y89.809 E9.45436\n" +
"G1 X111.588 Y91.763 E9.51195\n" +
"G1 X110.064 Y95.872 E9.63019\n" +
"G1 X108.628 Y101.475 E9.78621\n" +
"G1 X108.195 Y104.274 E9.86262\n" +
"G1 X108.062 Y106.303 E9.91748\n" +
"G1 X108.094 Y107.753 E9.95660\n" +
"G1 X108.244 Y109.044 E9.99168\n" +
"G1 X108.485 Y110.157 E10.02238\n" +
"G1 X108.757 Y111.003 E10.04636\n" +
"G1 X109.070 Y111.725 E10.06758\n" +
"G1 X109.473 Y112.447 E10.08989\n" +
"G1 X109.975 Y113.161 E10.11343\n" +
"G1 X110.551 Y113.820 E10.13703\n" +
"G1 X111.179 Y114.401 E10.16013\n" +
"G1 X111.884 Y114.935 E10.18398\n" +
"G1 X112.665 Y115.410 E10.20865\n" +
"G1 X113.503 Y115.811 E10.23371\n" +
"G1 X114.797 Y116.279 E10.27082\n" +
"G1 X119.206 Y117.510 E10.39431\n" +
"G1 X122.220 Y118.095 E10.47715\n" +
"G1 X127.121 Y118.538 E10.60989\n" +
"G1 X134.815 Y118.583 E10.81745\n" +
"G1 X141.185 Y118.390 E10.98939\n" +
"G1 X147.084 Y117.695 E11.14961\n" +
"G1 X152.694 Y116.529 E11.30418\n" +
"G1 X157.586 Y115.101 E11.44166\n" +
"G1 X160.196 Y114.050 E11.51757\n" +
"G1 X161.952 Y113.176 E11.57048\n" +
"G1 X162.456 Y112.884 E11.58619\n" +
"G1 X162.810 Y112.640 E11.59781\n" +
"G1 X163.132 Y112.369 E11.60915\n" +
"G1 X163.381 Y112.110 E11.61883\n" +
"G1 X163.609 Y111.822 E11.62874\n" +
"G1 X163.904 Y111.367 E11.64337\n" +
"G1 X164.605 Y109.996 E11.68493\n" +
"G1 X164.985 Y109.050 E11.71242\n" +
"G1 X165.211 Y108.254 E11.73473\n" +
"G1 X165.377 Y107.309 E11.76062\n" +
"G1 X165.419 Y106.940 E11.77063\n" +
"G1 X165.495 Y105.246 E11.81640\n" +
"G1 X165.343 Y102.431 E11.89242\n" +
"G1 X164.731 Y98.403 E12.00234\n" +
"G1 X164.558 Y97.732 E12.02104\n" +
"G1 X165.461 Y100.555 E12.10101\n" +
"G1 X166.013 Y102.181 E12.14731\n" +
"G1 X166.311 Y102.857 E12.16724\n" +
"G1 X166.553 Y103.268 E12.18012\n" +
"G1 X166.782 Y103.557 E12.19005\n" +
"G1 X167.072 Y103.801 E12.20029\n" +
"G1 X167.394 Y103.969 E12.21010\n" +
"G1 X167.894 Y104.092 E12.22396\n" +
"G1 X168.565 Y104.112 E12.24208\n" +
"G1 X171.679 Y103.918 E12.32626\n" +
"G1 X172.398 Y103.942 E12.34564\n" +
"G1 X172.386 Y106.076 E12.40321\n" +
"G1 X172.469 Y110.788 E12.53034\n" +
"G1 X172.573 Y112.109 E12.56608\n" +
"G1 X172.368 Y112.177 E12.57189\n" +
"G1 X167.983 Y113.346 E12.69432\n" +
"G1 X166.381 Y113.748 E12.73888\n" +
"G1 X164.399 Y114.326 E12.79457\n" +
"G1 X159.438 Y115.626 E12.93292\n" +
"G1 X158.894 Y115.750 E12.94798\n" +
"G1 X150.850 Y117.364 E13.16931\n" +
"G1 X149.932 Y117.530 E13.19448\n" +
"G1 X145.538 Y118.226 E13.31447\n" +
"G1 X144.134 Y118.427 E13.35276\n" +
"G1 X135.361 Y119.081 E13.59006\n" +
"G1 X134.054 Y119.081 E13.62532\n" +
"G1 X127.201 Y118.984 E13.81023\n" +
"G1 X121.887 Y118.333 E13.95463\n" +
"G1 X117.747 Y117.456 E14.06880\n" +
"G1 X116.213 Y117.153 E14.11099\n" +
"G1 X115.602 Y117.002 E14.12795\n" +
"G1 X111.992 Y115.846 E14.23022\n" +
"G1 X110.300 Y115.284 E14.27831\n" +
"G1 X107.008 Y114.279 E14.37118\n" +
"G1 X106.078 Y114.079 E14.39683\n" +
"G1 X103.627 Y113.613 E14.46415\n" +
"G1 X101.558 Y113.405 E14.52024\n" +
"G1 X100.406 Y113.397 E14.55130\n" +
"G1 X98.446 Y113.405 E14.60418\n" +
"G1 X96.811 Y113.560 E14.64849\n" +
"G1 X96.013 Y113.671 E14.67022\n" +
"G1 X92.998 Y114.277 E14.75319\n" +
"G1 X89.700 Y115.284 E14.84621\n" +
"G1 X88.008 Y115.846 E14.89430\n" +
"G1 X84.398 Y117.002 E14.99657\n" +
"G1 X83.782 Y117.154 E15.01369\n" +
"G1 X79.707 Y118.020 E15.12607\n" +
"G1 X78.119 Y118.332 E15.16972\n" +
"G1 X72.799 Y118.984 E15.31429\n" +
"G1 X65.946 Y119.081 E15.49920\n" +
"G1 X64.638 Y119.081 E15.53447\n" +
"G1 X55.872 Y118.428 E15.77160\n" +
"G1 X51.492 Y117.735 E15.89123\n" +
"G1 X50.064 Y117.529 E15.93016\n" +
"G1 X48.591 Y117.257 E15.97057\n" +
"G1 X41.106 Y115.750 E16.17654\n" +
"G1 X40.562 Y115.626 E16.19160\n" +
"G1 X35.601 Y114.326 E16.32995\n" +
"G1 X33.624 Y113.750 E16.38548\n" +
"G1 X29.408 Y112.626 E16.50319\n" +
"G1 X27.498 Y112.136 E16.55638\n" +
"G1 E14.55638 F2400.00000\n" +
"G92 E0\n" +
"G1 X108.654 Y107.714 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X108.623 Y106.315 E2.02884\n" +
"G1 X108.753 Y104.335 E2.06974\n" +
"G1 X109.178 Y101.587 E2.12704\n" +
"G1 X110.600 Y96.040 E2.24507\n" +
"G1 X112.108 Y91.972 E2.33448\n" +
"G1 X112.948 Y90.067 E2.37739\n" +
"G1 X113.456 Y89.190 E2.39829\n" +
"G1 X114.035 Y88.388 E2.41868\n" +
"G1 X114.658 Y87.686 E2.43801\n" +
"G1 X115.399 Y86.999 E2.45885\n" +
"G1 X116.541 Y86.122 E2.48852\n" +
"G1 X117.357 Y85.603 E2.50845\n" +
"G1 X118.239 Y85.157 E2.52881\n" +
"G1 X119.552 Y84.639 E2.55791\n" +
"G1 X121.578 Y84.004 E2.60167\n" +
"G1 X128.590 Y82.412 E2.74988\n" +
"G1 X134.253 Y81.671 E2.86758\n" +
"G1 X141.621 Y81.379 E3.01955\n" +
"G1 X148.222 Y81.432 E3.15560\n" +
"G1 X151.177 Y81.725 E3.21681\n" +
"G1 X153.545 Y82.203 E3.26659\n" +
"G1 X154.961 Y82.634 E3.29710\n" +
"G1 X155.766 Y82.970 E3.31508\n" +
"G1 X156.332 Y83.280 E3.32839\n" +
"G1 X156.785 Y83.594 E3.33976\n" +
"G1 X157.258 Y83.992 E3.35248\n" +
"G1 X158.177 Y84.898 E3.37908\n" +
"G1 X159.186 Y86.007 E3.40998\n" +
"G1 X159.469 Y86.392 E3.41984\n" +
"G1 X159.809 Y86.974 E3.43373\n" +
"G1 X161.914 Y91.320 E3.53326\n" +
"G1 X163.281 Y95.008 E3.61433\n" +
"G1 X164.181 Y98.515 E3.68894\n" +
"G1 X164.785 Y102.489 E3.77178\n" +
"G1 X164.934 Y105.248 E3.82873\n" +
"G1 X164.860 Y106.896 E3.86274\n" +
"G1 X164.822 Y107.229 E3.86964\n" +
"G1 X164.664 Y108.128 E3.88846\n" +
"G1 X164.454 Y108.868 E3.90431\n" +
"G1 X164.095 Y109.763 E3.92419\n" +
"G1 X163.418 Y111.086 E3.95482\n" +
"G1 X163.153 Y111.495 E3.96485\n" +
"G1 X162.958 Y111.741 E3.97132\n" +
"G1 X162.748 Y111.959 E3.97756\n" +
"G1 X162.470 Y112.193 E3.98506\n" +
"G1 X162.156 Y112.410 E3.99293\n" +
"G1 X161.686 Y112.682 E4.00411\n" +
"G1 X159.966 Y113.538 E4.04371\n" +
"G1 X157.402 Y114.571 E4.10068\n" +
"G1 X152.558 Y115.985 E4.20468\n" +
"G1 X146.994 Y117.141 E4.32181\n" +
"G1 X141.144 Y117.831 E4.44321\n" +
"G1 X134.808 Y118.022 E4.57386\n" +
"G1 X127.148 Y117.978 E4.73175\n" +
"G1 X122.299 Y117.539 E4.83208\n" +
"G1 X119.335 Y116.964 E4.89432\n" +
"G1 X114.968 Y115.745 E4.98777\n" +
"G1 X113.720 Y115.293 E5.01512\n" +
"G1 X112.932 Y114.917 E5.03311\n" +
"G1 X112.200 Y114.471 E5.05079\n" +
"G1 X111.539 Y113.971 E5.06786\n" +
"G1 X110.953 Y113.428 E5.08432\n" +
"G1 X110.417 Y112.814 E5.10113\n" +
"G1 X109.949 Y112.148 E5.11791\n" +
"G1 X109.573 Y111.476 E5.13378\n" +
"G1 X109.282 Y110.806 E5.14884\n" +
"G1 X109.027 Y110.012 E5.16602\n" +
"G1 X108.798 Y108.953 E5.18836\n" +
"G1 X108.663 Y107.788 E5.21251\n" +
"G1 X108.654 Y107.714 F7800.000\n" +
"G1 E3.21251 F2400.00000\n" +
"G92 E0\n" +
"G1 X152.542 Y81.428 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X153.012 Y81.494 E2.01282\n" +
"G1 X154.241 Y81.788 E2.04691\n" +
"G1 X155.133 Y82.101 E2.07240\n" +
"G1 X153.682 Y81.659 E2.11332\n" +
"G1 X152.615 Y81.443 E2.14269\n" +
"G1 E0.14269 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.577 Y83.528 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.870 Y83.742 E2.00980\n" +
"G1 X158.599 Y84.384 E2.03599\n" +
"G1 X159.241 Y85.065 E2.06125\n" +
"G1 X159.796 Y85.783 E2.08572\n" +
"G1 X160.298 Y86.578 E2.11108\n" +
"G1 X160.889 Y87.719 E2.14576\n" +
"G1 X161.172 Y88.353 E2.16449\n" +
"G1 X161.510 Y89.200 E2.18909\n" +
"G1 X160.304 Y86.710 E2.26371\n" +
"G1 X159.938 Y86.084 E2.28327\n" +
"G1 X159.621 Y85.651 E2.29776\n" +
"G1 X158.581 Y84.509 E2.33942\n" +
"G1 X157.634 Y83.576 E2.37529\n" +
"G1 E0.37529 F2400.00000\n" +
"G92 E0\n" +
"G1 X172.869 Y103.515 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X172.930 Y103.630 E2.00269\n" +
"G1 X172.954 Y103.786 E2.00594\n" +
"G1 X172.947 Y106.073 E2.05307\n" +
"G1 X173.029 Y110.761 E2.14971\n" +
"G1 X173.154 Y112.342 E2.18240\n" +
"G1 X173.142 Y112.430 E2.18422\n" +
"G1 X173.081 Y112.502 E2.18615\n" +
"G1 X172.928 Y112.581 E2.18971\n" +
"G1 X172.529 Y112.714 E2.19837\n" +
"G1 X168.124 Y113.888 E2.29234\n" +
"G1 X166.528 Y114.290 E2.32625\n" +
"G1 X164.549 Y114.866 E2.36874\n" +
"G1 X159.572 Y116.171 E2.47479\n" +
"G1 X159.011 Y116.298 E2.48663\n" +
"G1 X150.955 Y117.915 E2.65599\n" +
"G1 X150.026 Y118.083 E2.67545\n" +
"G1 X145.622 Y118.780 E2.76734\n" +
"G1 X144.194 Y118.985 E2.79707\n" +
"G1 X135.382 Y119.642 E2.97919\n" +
"G1 X134.050 Y119.642 E3.00664\n" +
"G1 X127.162 Y119.544 E3.14862\n" +
"G1 X121.795 Y118.886 E3.26007\n" +
"G1 X117.634 Y118.006 E3.34772\n" +
"G1 X116.091 Y117.700 E3.38015\n" +
"G1 X115.449 Y117.542 E3.39377\n" +
"G1 X111.818 Y116.379 E3.47236\n" +
"G1 X110.130 Y115.818 E3.50902\n" +
"G1 X106.867 Y114.822 E3.57934\n" +
"G1 X105.967 Y114.628 E3.59832\n" +
"G1 X103.546 Y114.168 E3.64910\n" +
"G1 X101.528 Y113.966 E3.69091\n" +
"G1 X100.405 Y113.958 E3.71404\n" +
"G1 X98.474 Y113.965 E3.75385\n" +
"G1 X96.876 Y114.117 E3.78693\n" +
"G1 X96.107 Y114.224 E3.80293\n" +
"G1 X93.135 Y114.822 E3.86541\n" +
"G1 X89.870 Y115.818 E3.93577\n" +
"G1 X88.182 Y116.379 E3.97243\n" +
"G1 X84.551 Y117.542 E4.05102\n" +
"G1 X83.907 Y117.701 E4.06468\n" +
"G1 X79.819 Y118.570 E4.15082\n" +
"G1 X78.207 Y118.886 E4.18468\n" +
"G1 X72.838 Y119.544 E4.29617\n" +
"G1 X65.950 Y119.642 E4.43815\n" +
"G1 X64.618 Y119.642 E4.46560\n" +
"G1 X55.808 Y118.985 E4.64768\n" +
"G1 X51.408 Y118.289 E4.73948\n" +
"G1 X49.973 Y118.083 E4.76937\n" +
"G1 X48.485 Y117.808 E4.80056\n" +
"G1 X40.989 Y116.298 E4.95816\n" +
"G1 X40.428 Y116.171 E4.97000\n" +
"G1 X35.451 Y114.866 E5.07605\n" +
"G1 X33.474 Y114.290 E5.11850\n" +
"G1 X29.267 Y113.169 E5.20822\n" +
"G1 X27.466 Y112.713 E5.24651\n" +
"G1 X27.178 Y112.623 E5.25273\n" +
"G1 X26.931 Y112.514 E5.25830\n" +
"G1 X26.858 Y112.430 E5.26059\n" +
"G1 X26.846 Y112.342 E5.26241\n" +
"G1 X26.971 Y110.761 E5.29510\n" +
"G1 X27.053 Y106.074 E5.39173\n" +
"G1 X27.045 Y103.830 E5.43797\n" +
"G1 X27.072 Y103.626 E5.44220\n" +
"G1 X27.125 Y103.521 E5.44464\n" +
"G1 X27.294 Y103.427 E5.44862\n" +
"G1 X27.694 Y103.368 E5.45695\n" +
"G1 X27.941 Y103.361 E5.46204\n" +
"G1 X28.643 Y103.370 E5.47651\n" +
"G1 X31.440 Y103.550 E5.53427\n" +
"G1 X31.807 Y103.550 E5.54184\n" +
"G1 X32.188 Y103.506 E5.54975\n" +
"G1 X32.440 Y103.427 E5.55518\n" +
"G1 X32.650 Y103.309 E5.56016\n" +
"G1 X32.906 Y103.060 E5.56752\n" +
"G1 X33.175 Y102.628 E5.57801\n" +
"G1 X33.464 Y101.977 E5.59268\n" +
"G1 X34.006 Y100.382 E5.62741\n" +
"G1 X35.744 Y95.089 E5.74223\n" +
"G1 X36.375 Y93.103 E5.78517\n" +
"G1 X36.704 Y92.195 E5.80507\n" +
"G1 X38.311 Y88.134 E5.89509\n" +
"G1 X38.606 Y87.475 E5.90997\n" +
"G1 X39.215 Y86.298 E5.93729\n" +
"G1 X39.744 Y85.461 E5.95770\n" +
"G1 X40.332 Y84.700 E5.97752\n" +
"G1 X41.010 Y83.981 E5.99788\n" +
"G1 X41.784 Y83.300 E6.01914\n" +
"G1 X42.743 Y82.601 E6.04359\n" +
"G1 X43.694 Y82.031 E6.06644\n" +
"G1 X44.570 Y81.615 E6.08642\n" +
"G1 X45.708 Y81.220 E6.11125\n" +
"G1 X46.883 Y80.943 E6.13614\n" +
"G1 X48.924 Y80.657 E6.17860\n" +
"G1 X49.427 Y80.615 E6.18902\n" +
"G1 X52.834 Y80.439 E6.25933\n" +
"G1 X54.827 Y80.377 E6.30043\n" +
"G1 X64.485 Y80.453 E6.49949\n" +
"G1 X64.929 Y80.469 E6.50865\n" +
"G1 X68.853 Y80.761 E6.58975\n" +
"G1 X69.927 Y80.821 E6.61191\n" +
"G1 X71.360 Y80.968 E6.64160\n" +
"G1 X76.630 Y81.836 E6.75168\n" +
"G1 X79.859 Y82.658 E6.82036\n" +
"G1 X81.540 Y83.265 E6.85719\n" +
"G1 X82.930 Y83.929 E6.88894\n" +
"G1 X84.330 Y84.760 E6.92249\n" +
"G1 X85.427 Y85.550 E6.95036\n" +
"G1 X86.227 Y86.254 E6.97232\n" +
"G1 X86.867 Y86.935 E6.99159\n" +
"G1 X87.414 Y87.648 E7.01011\n" +
"G1 X88.091 Y88.763 E7.03699\n" +
"G1 X88.790 Y90.221 E7.07032\n" +
"G1 X88.978 Y90.674 E7.08042\n" +
"G1 X90.319 Y94.398 E7.16201\n" +
"G1 X91.721 Y98.942 E7.26000\n" +
"G1 X92.321 Y101.013 E7.30445\n" +
"G1 X93.661 Y106.051 E7.41190\n" +
"G1 X93.973 Y106.865 E7.42986\n" +
"G1 X94.147 Y107.222 E7.43804\n" +
"G1 X94.385 Y107.615 E7.44752\n" +
"G1 X94.561 Y107.855 E7.45364\n" +
"G1 X94.806 Y108.135 E7.46131\n" +
"G1 X95.097 Y108.407 E7.46953\n" +
"G1 X95.426 Y108.660 E7.47808\n" +
"G1 X95.802 Y108.894 E7.48720\n" +
"G1 X96.265 Y109.122 E7.49785\n" +
"G1 X96.773 Y109.316 E7.50906\n" +
"G1 X97.389 Y109.490 E7.52225\n" +
"G1 X98.280 Y109.643 E7.54087\n" +
"G1 X98.954 Y109.708 E7.55483\n" +
"G1 X101.049 Y109.707 E7.59801\n" +
"G1 X101.875 Y109.627 E7.61512\n" +
"G1 X102.519 Y109.508 E7.62861\n" +
"G1 X103.191 Y109.328 E7.64295\n" +
"G1 X103.735 Y109.123 E7.65493\n" +
"G1 X104.198 Y108.894 E7.66558\n" +
"G1 X104.574 Y108.660 E7.67471\n" +
"G1 X104.906 Y108.405 E7.68333\n" +
"G1 X105.333 Y107.983 E7.69570\n" +
"G1 X105.527 Y107.740 E7.70211\n" +
"G1 X105.726 Y107.443 E7.70949\n" +
"G1 X106.026 Y106.869 E7.72284\n" +
"G1 X106.339 Y106.051 E7.74088\n" +
"G1 X107.679 Y101.013 E7.84832\n" +
"G1 X108.279 Y98.942 E7.89277\n" +
"G1 X109.682 Y94.396 E7.99082\n" +
"G1 X109.923 Y93.684 E8.00631\n" +
"G1 X110.883 Y91.037 E8.06435\n" +
"G1 X111.210 Y90.220 E8.08248\n" +
"G1 X111.973 Y88.642 E8.11862\n" +
"G1 X112.585 Y87.649 E8.14266\n" +
"G1 X113.088 Y86.990 E8.15974\n" +
"G1 X113.773 Y86.253 E8.18048\n" +
"G1 X114.400 Y85.697 E8.19775\n" +
"G1 X114.676 Y85.470 E8.20512\n" +
"G1 X115.672 Y84.759 E8.23033\n" +
"G1 X117.070 Y83.929 E8.26384\n" +
"G1 X118.459 Y83.266 E8.29557\n" +
"G1 X120.351 Y82.595 E8.33694\n" +
"G1 X123.608 Y81.787 E8.40611\n" +
"G1 X128.640 Y80.968 E8.51118\n" +
"G1 X130.076 Y80.821 E8.54092\n" +
"G1 X133.995 Y80.529 E8.62193\n" +
"G1 X135.517 Y80.453 E8.65333\n" +
"G1 X145.173 Y80.377 E8.85235\n" +
"G1 X145.848 Y80.387 E8.86627\n" +
"G1 X150.573 Y80.615 E8.96377\n" +
"G1 X151.076 Y80.657 E8.97418\n" +
"G1 X153.117 Y80.943 E9.01663\n" +
"G1 X154.399 Y81.250 E9.04381\n" +
"G1 X155.325 Y81.573 E9.06402\n" +
"G1 X156.305 Y82.031 E9.08632\n" +
"G1 X157.257 Y82.601 E9.10919\n" +
"G1 X158.218 Y83.301 E9.13369\n" +
"G1 X158.990 Y83.981 E9.15489\n" +
"G1 X159.668 Y84.700 E9.17527\n" +
"G1 X160.256 Y85.461 E9.19508\n" +
"G1 X160.785 Y86.298 E9.21550\n" +
"G1 X161.394 Y87.475 E9.24281\n" +
"G1 X161.689 Y88.134 E9.25770\n" +
"G1 X162.908 Y91.195 E9.32559\n" +
"G1 X163.626 Y93.106 E9.36767\n" +
"G1 X165.285 Y98.147 E9.47705\n" +
"G1 X165.993 Y100.380 E9.52534\n" +
"G1 X166.536 Y101.977 E9.56009\n" +
"G1 X166.811 Y102.600 E9.57413\n" +
"G1 X167.017 Y102.950 E9.58251\n" +
"G1 X167.186 Y103.164 E9.58812\n" +
"G1 X167.386 Y103.332 E9.59351\n" +
"G1 X167.594 Y103.441 E9.59835\n" +
"G1 X167.970 Y103.533 E9.60631\n" +
"G1 X168.556 Y103.551 E9.61841\n" +
"G1 X171.666 Y103.357 E9.68263\n" +
"G1 X172.285 Y103.366 E9.69538\n" +
"G1 X172.556 Y103.395 E9.70101\n" +
"G1 X172.764 Y103.448 E9.70543\n" +
"G1 X172.806 Y103.475 E9.70646\n" +
"G1 X172.828 Y103.639 F7800.000\n" +
"G1 E7.70646 F2400.00000\n" +
"G92 E0\n" +
"G1 X171.368 Y105.478 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X170.873 Y104.983 E2.01928\n" +
"G1 X170.007 Y105.036 E2.04319\n" +
"G1 X171.363 Y106.392 E2.09605\n" +
"G1 X171.380 Y107.327 E2.12181\n" +
"G1 X169.141 Y105.088 E2.20906\n" +
"G1 X168.269 Y105.135 E2.23312\n" +
"G1 X171.396 Y108.262 E2.35499\n" +
"G1 X171.412 Y109.196 E2.38075\n" +
"G1 X167.279 Y105.063 E2.54185\n" +
"G1 X166.695 Y104.920 E2.55844\n" +
"G1 X166.511 Y104.824 E2.56414\n" +
"G1 X166.511 Y105.213 E2.57486\n" +
"G1 X171.429 Y110.131 E2.76655\n" +
"G1 X171.445 Y111.066 E2.79231\n" +
"G1 X166.471 Y106.092 E2.98617\n" +
"G1 X166.421 Y106.960 E3.01014\n" +
"G1 X171.000 Y111.539 E3.18861\n" +
"G1 X170.275 Y111.732 E3.20930\n" +
"G1 X166.299 Y107.756 E3.36427\n" +
"G1 X166.204 Y108.292 E3.37925\n" +
"G1 X166.141 Y108.517 E3.38570\n" +
"G1 X169.550 Y111.926 E3.51858\n" +
"G1 X168.825 Y112.119 E3.53926\n" +
"G1 X165.936 Y109.230 E3.65186\n" +
"G1 X165.673 Y109.886 E3.67132\n" +
"G1 X168.099 Y112.312 E3.76590\n" +
"G1 X167.368 Y112.499 E3.78671\n" +
"G1 X165.391 Y110.522 E3.86378\n" +
"G1 X165.080 Y111.129 E3.88259\n" +
"G1 X166.634 Y112.683 E3.94316\n" +
"G1 X165.912 Y112.880 E3.96378\n" +
"G1 X164.769 Y111.737 E4.00835\n" +
"G1 X164.409 Y112.296 E4.02666\n" +
"G1 X165.201 Y113.087 E4.05752\n" +
"G1 X164.490 Y113.295 E4.07794\n" +
"G1 X163.996 Y112.800 E4.09721\n" +
"G1 E2.09721 F2400.00000\n" +
"G92 E0\n" +
"G1 X107.195 Y109.144 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X106.700 Y108.650 E2.01919\n" +
"G1 X106.449 Y108.963 E2.03020\n" +
"G1 X106.274 Y109.137 E2.03699\n" +
"G1 X107.402 Y110.266 E2.08076\n" +
"G1 X107.503 Y110.733 E2.09389\n" +
"G1 X107.762 Y111.540 E2.11712\n" +
"G1 X105.808 Y109.586 E2.19294\n" +
"G1 X105.283 Y109.975 E2.21086\n" +
"G1 X108.597 Y113.289 E2.33945\n" +
"G1 X108.867 Y113.690 E2.35269\n" +
"G1 X107.738 Y113.345 E2.38505\n" +
"G1 X104.709 Y110.315 E2.50259\n" +
"G1 X104.087 Y110.607 E2.52145\n" +
"G1 X106.555 Y113.076 E2.61722\n" +
"G1 X105.426 Y112.861 E2.64875\n" +
"G1 X103.416 Y110.850 E2.72675\n" +
"G1 X102.695 Y111.044 E2.74722\n" +
"G1 X104.297 Y112.646 E2.80937\n" +
"G1 X104.056 Y112.600 E2.81611\n" +
"G1 X103.257 Y112.520 E2.83814\n" +
"G1 X101.925 Y111.188 E2.88985\n" +
"G1 X101.095 Y111.272 E2.91273\n" +
"G1 X102.241 Y112.418 E2.95721\n" +
"G1 X101.873 Y112.382 E2.96737\n" +
"G1 X101.286 Y112.377 E2.98347\n" +
"G1 X100.200 Y111.291 E3.02561\n" +
"G1 X99.286 Y111.292 E3.05068\n" +
"G1 X100.368 Y112.374 E3.09267\n" +
"G1 X99.458 Y112.378 E3.11765\n" +
"G1 X98.346 Y111.266 E3.16078\n" +
"G1 X97.789 Y111.213 E3.17613\n" +
"G1 X97.294 Y111.127 E3.18994\n" +
"G1 X98.555 Y112.388 E3.23886\n" +
"G1 X97.720 Y112.468 E3.26187\n" +
"G1 X96.106 Y110.854 E3.32449\n" +
"G1 X95.284 Y110.556 E3.34845\n" +
"G1 X94.663 Y110.249 E3.36747\n" +
"G1 X94.462 Y110.124 E3.37397\n" +
"G1 X96.886 Y112.548 E3.46800\n" +
"G1 X96.083 Y112.659 E3.49024\n" +
"G1 X92.704 Y109.281 E3.62132\n" +
"G1 X92.542 Y110.032 E3.64241\n" +
"G1 X95.320 Y112.810 E3.75020\n" +
"G1 X94.559 Y112.964 E3.77150\n" +
"G1 X92.336 Y110.741 E3.85774\n" +
"G1 X92.187 Y111.204 E3.87110\n" +
"G1 X92.096 Y111.415 E3.87739\n" +
"G1 X93.798 Y113.117 E3.94342\n" +
"G1 X93.037 Y113.270 E3.96472\n" +
"G1 X91.818 Y112.052 E4.01198\n" +
"G1 X91.491 Y112.638 E4.03041\n" +
"G1 X92.328 Y113.476 E4.06291\n" +
"G1 X91.628 Y113.690 E4.08300\n" +
"G1 X91.134 Y113.195 E4.10218\n" +
"G1 E2.10218 F2400.00000\n" +
"G92 E0\n" +
"G1 X33.488 Y105.325 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X32.994 Y104.831 E2.02011\n" +
"G1 X32.924 Y104.870 E2.02240\n" +
"G1 X32.270 Y105.065 E2.04205\n" +
"G1 X33.516 Y106.312 E2.09276\n" +
"G1 X33.563 Y107.317 E2.12169\n" +
"G1 X31.382 Y105.135 E2.21042\n" +
"G1 X30.374 Y105.086 E2.23941\n" +
"G1 X33.736 Y108.448 E2.37612\n" +
"G1 X33.796 Y108.786 E2.38600\n" +
"G1 X34.071 Y109.741 E2.41456\n" +
"G1 X29.350 Y105.020 E2.60653\n" +
"G1 X28.635 Y104.974 E2.62715\n" +
"G1 X28.636 Y105.264 E2.63549\n" +
"G1 X34.841 Y111.469 E2.88785\n" +
"G1 X35.241 Y112.251 E2.91310\n" +
"G1 X35.605 Y112.812 E2.93231\n" +
"G1 X36.005 Y113.295 E2.95034\n" +
"G1 X35.587 Y113.173 E2.96286\n" +
"G1 X28.631 Y106.217 E3.24572\n" +
"G1 X28.615 Y107.159 E3.27280\n" +
"G1 X34.234 Y112.779 E3.50134\n" +
"G1 X32.927 Y112.430 E3.54025\n" +
"G1 X28.598 Y108.101 E3.71629\n" +
"G1 X28.582 Y109.043 E3.74337\n" +
"G1 X31.621 Y112.081 E3.86695\n" +
"G1 X30.314 Y111.733 E3.90583\n" +
"G1 X28.565 Y109.984 E3.97694\n" +
"G1 X28.555 Y110.602 E3.99471\n" +
"G1 X28.530 Y110.908 E4.00352\n" +
"G1 X29.025 Y111.402 E4.02363\n" +
"M106 S137.7\n" +
"G1 Z2.750 F7800.000\n" +
"G1 E2.02363 F2400.00000\n" +
"G92 E0\n" +
"G1 X41.661 Y85.059 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X42.742 Y83.992 E2.03131\n" +
"G1 X43.215 Y83.594 E2.04403\n" +
"G1 X43.668 Y83.280 E2.05540\n" +
"G1 X44.234 Y82.970 E2.06871\n" +
"G1 X45.039 Y82.634 E2.08669\n" +
"G1 X46.455 Y82.203 E2.11720\n" +
"G1 X48.823 Y81.725 E2.16698\n" +
"G1 X51.778 Y81.432 E2.22819\n" +
"G1 X58.379 Y81.379 E2.36424\n" +
"G1 X65.747 Y81.671 E2.51621\n" +
"G1 X71.410 Y82.412 E2.63391\n" +
"G1 X78.422 Y84.004 E2.78212\n" +
"G1 X80.448 Y84.639 E2.82588\n" +
"G1 X81.760 Y85.157 E2.85495\n" +
"G1 X82.599 Y85.571 E2.87423\n" +
"G1 X82.642 Y85.979 E2.88269\n" +
"G1 X82.667 Y86.072 E2.88467\n" +
"G1 X82.794 Y86.322 E2.89045\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X82.897 Y86.457 E2.89517\n" +
"G1 X83.066 Y86.601 E2.90133\n" +
"G1 X83.261 Y86.705 E2.90749\n" +
"G1 X83.409 Y86.746 E2.91176\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X83.704 Y86.777 E2.91787\n" +
"G1 X83.800 Y86.767 E2.91985\n" +
"G1 X84.188 Y86.659 E2.92815\n" +
"G1 X84.605 Y87.002 E2.93928\n" +
"G1 X85.342 Y87.686 E2.96001\n" +
"G1 X85.965 Y88.388 E2.97934\n" +
"G1 X86.544 Y89.190 E2.99973\n" +
"G1 X87.052 Y90.068 E3.02065\n" +
"G1 X87.677 Y91.453 E3.05196\n" +
"G1 X87.936 Y92.081 E3.06596\n" +
"G1 X89.186 Y95.430 E3.13964\n" +
"G1 X89.455 Y96.223 E3.15691\n" +
"G1 X90.759 Y101.312 E3.26518\n" +
"G1 X90.977 Y102.471 E3.28947\n" +
"G1 X91.243 Y104.296 E3.32748\n" +
"G1 X91.378 Y106.386 E3.37066\n" +
"G1 X91.342 Y107.772 E3.39923\n" +
"G1 X91.218 Y108.845 E3.42149\n" +
"G1 X90.986 Y109.981 E3.44539\n" +
"G1 X90.469 Y110.232 E3.45724\n" +
"G1 X90.312 Y110.354 E3.46133\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X90.142 Y110.485 E3.46728\n" +
"G1 X90.004 Y110.630 E3.47284\n" +
"G1 X89.767 Y110.976 E3.48451\n" +
"G1 X89.609 Y111.358 E3.49598\n" +
"G1 X89.532 Y111.764 E3.50745\n" +
"G1 X89.536 Y111.968 E3.51314\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X89.540 Y112.177 E3.51745\n" +
"G1 X89.663 Y112.724 E3.52899\n" +
"G1 X89.047 Y113.428 E3.54829\n" +
"G1 X88.461 Y113.971 E3.56476\n" +
"G1 X87.800 Y114.471 E3.58182\n" +
"G1 X87.068 Y114.917 E3.59950\n" +
"G1 X86.280 Y115.293 E3.61749\n" +
"G1 X85.032 Y115.745 E3.64484\n" +
"G1 X80.665 Y116.964 E3.73829\n" +
"G1 X77.701 Y117.539 E3.80053\n" +
"G1 X72.852 Y117.978 E3.90086\n" +
"G1 X65.192 Y118.022 E4.05875\n" +
"G1 X58.856 Y117.831 E4.18940\n" +
"G1 X53.006 Y117.141 E4.31080\n" +
"G1 X47.442 Y115.985 E4.42793\n" +
"G1 X42.598 Y114.571 E4.53194\n" +
"G1 X40.034 Y113.538 E4.58890\n" +
"G1 X38.314 Y112.682 E4.62851\n" +
"G1 X37.694 Y112.322 E4.64327\n" +
"G1 X37.715 Y111.930 E4.65136\n" +
"G1 X37.691 Y111.643 E4.65731\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X37.616 Y111.311 E4.66676\n" +
"G1 X37.541 Y111.115 E4.67259\n" +
"G1 X37.331 Y110.752 E4.68424\n" +
"G1 X37.050 Y110.441 E4.69588\n" +
"G1 X36.721 Y110.203 E4.70715\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X36.327 Y110.026 E4.71607\n" +
"G1 X35.977 Y109.933 E4.72353\n" +
"G1 X35.546 Y108.869 E4.74720\n" +
"G1 X35.336 Y108.128 E4.76306\n" +
"G1 X35.178 Y107.229 E4.78188\n" +
"G1 X35.140 Y106.896 E4.78878\n" +
"G1 X35.066 Y105.248 E4.82278\n" +
"G1 X35.215 Y102.489 E4.87974\n" +
"G1 X35.819 Y98.515 E4.96258\n" +
"G1 X36.719 Y95.008 E5.03719\n" +
"G1 X38.086 Y91.320 E5.11826\n" +
"G1 X40.333 Y86.697 E5.22421\n" +
"G1 X40.782 Y86.776 E5.23361\n" +
"G1 X40.918 Y86.771 E5.23641\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X41.001 Y86.767 E5.23873\n" +
"G1 X41.307 Y86.672 E5.24764\n" +
"G1 X41.574 Y86.481 E5.25675\n" +
"G1 X41.718 Y86.300 E5.26318\n" +
"G1 X41.846 Y85.998 E5.27227\n" +
"G1 X41.867 Y85.877 E5.27568\n" +
"G1 X41.864 Y85.634 E5.28243\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X41.831 Y85.460 E5.28609\n" +
"G1 X41.691 Y85.128 E5.29351\n" +
"G1 X41.366 Y84.656 F7800.000\n" +
"G1 E3.29351 F2400.00000\n" +
"G92 E0\n" +
"G1 X160.008 Y114.811 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.457 Y114.630 E2.01305\n" +
"G1 X162.254 Y113.736 E2.06720\n" +
"G1 X163.539 Y112.990 E2.10729\n" +
"G1 X163.488 Y111.846 E2.13816\n" +
"G1 X163.559 Y111.597 E2.14516\n" +
"G1 X163.637 Y111.458 E2.14947\n" +
"G1 X163.750 Y111.333 E2.15400\n" +
"G1 X163.961 Y111.196 E2.16079\n" +
"G1 X164.908 Y110.937 E2.18727\n" +
"G1 X165.587 Y109.257 E2.23613\n" +
"G1 X165.831 Y108.396 E2.26027\n" +
"G1 X166.007 Y107.400 E2.28757\n" +
"G1 X166.053 Y106.990 E2.29870\n" +
"G1 X166.131 Y105.243 E2.34589\n" +
"G1 X166.045 Y103.648 E2.38897\n" +
"G1 X166.319 Y103.997 E2.40094\n" +
"G1 X166.699 Y104.320 E2.41438\n" +
"G1 X167.148 Y104.561 E2.42814\n" +
"G1 X167.799 Y104.724 E2.44625\n" +
"G1 X168.513 Y104.750 E2.46552\n" +
"G1 X171.055 Y104.584 E2.53424\n" +
"G1 X171.760 Y104.555 E2.55326\n" +
"G1 X171.750 Y105.819 E2.58736\n" +
"G1 X171.837 Y110.891 E2.72421\n" +
"G1 X171.897 Y111.644 E2.74457\n" +
"G1 X165.895 Y113.230 E2.91205\n" +
"G1 X164.766 Y113.559 E2.94378\n" +
"G1 X160.080 Y114.792 E3.07448\n" +
"G1 E1.07448 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.571 Y114.704 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.106 Y113.647 E2.09773\n" +
"G1 X103.722 Y112.983 E2.19075\n" +
"G1 X101.590 Y112.770 E2.24854\n" +
"G1 X100.232 Y112.762 E2.28517\n" +
"G1 X98.413 Y112.769 E2.33426\n" +
"G1 X96.558 Y112.950 E2.38454\n" +
"G1 X96.054 Y113.020 E2.39825\n" +
"G1 X92.893 Y113.647 E2.48520\n" +
"G1 X89.429 Y114.704 E2.58289\n" +
"G1 X89.905 Y114.263 E2.60040\n" +
"G1 X90.964 Y113.054 E2.64376\n" +
"G1 X90.734 Y112.032 E2.67202\n" +
"G1 X90.730 Y111.864 E2.67655\n" +
"G1 X90.761 Y111.702 E2.68099\n" +
"G1 X90.824 Y111.550 E2.68543\n" +
"G1 X90.938 Y111.384 E2.69087\n" +
"G1 X91.103 Y111.254 E2.69653\n" +
"G1 X92.031 Y110.803 E2.72437\n" +
"G1 X92.313 Y109.511 E2.76005\n" +
"G1 X92.402 Y109.021 E2.77348\n" +
"G1 X92.536 Y107.856 E2.80511\n" +
"G1 X92.570 Y106.547 E2.84044\n" +
"G1 X92.892 Y107.380 E2.86454\n" +
"G1 X93.104 Y107.810 E2.87746\n" +
"G1 X93.419 Y108.328 E2.89384\n" +
"G1 X93.926 Y108.949 E2.91545\n" +
"G1 X94.322 Y109.320 E2.93009\n" +
"G1 X94.742 Y109.643 E2.94438\n" +
"G1 X95.214 Y109.937 E2.95938\n" +
"G1 X95.786 Y110.220 E2.97661\n" +
"G1 X96.407 Y110.456 E2.99452\n" +
"G1 X97.115 Y110.655 E3.01436\n" +
"G1 X97.937 Y110.809 E3.03691\n" +
"G1 X98.931 Y110.906 E3.06385\n" +
"G1 X100.997 Y110.907 E3.11959\n" +
"G1 X102.039 Y110.813 E3.14781\n" +
"G1 X102.885 Y110.655 E3.17103\n" +
"G1 X103.602 Y110.453 E3.19114\n" +
"G1 X104.214 Y110.220 E3.20881\n" +
"G1 X104.786 Y109.937 E3.22601\n" +
"G1 X105.258 Y109.643 E3.24102\n" +
"G1 X105.695 Y109.308 E3.25587\n" +
"G1 X106.232 Y108.775 E3.27627\n" +
"G1 X106.488 Y108.454 E3.28735\n" +
"G1 X106.771 Y108.031 E3.30109\n" +
"G1 X107.102 Y107.396 E3.32041\n" +
"G1 X107.431 Y106.544 E3.34505\n" +
"G1 X107.459 Y107.792 E3.37875\n" +
"G1 X107.608 Y109.096 E3.41413\n" +
"G1 X107.967 Y110.832 E3.46195\n" +
"G1 X108.815 Y111.206 E3.48698\n" +
"G1 X108.965 Y111.300 E3.49174\n" +
"G1 X109.121 Y111.457 E3.49772\n" +
"G1 X109.211 Y111.625 E3.50285\n" +
"G1 X109.259 Y111.782 E3.50729\n" +
"G1 X109.276 Y111.985 E3.51276\n" +
"G1 X109.036 Y113.054 E3.54232\n" +
"G1 X110.095 Y114.263 E3.58569\n" +
"G1 X110.516 Y114.653 E3.60117\n" +
"G1 E1.60117 F2400.00000\n" +
"G92 E0\n" +
"G1 X39.992 Y114.811 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X35.234 Y113.559 E2.13272\n" +
"G1 X34.105 Y113.230 E2.16445\n" +
"G1 X28.103 Y111.644 E2.33193\n" +
"G1 X28.163 Y110.891 E2.35229\n" +
"G1 X28.250 Y105.822 E2.48907\n" +
"G1 X28.245 Y104.563 E2.52302\n" +
"G1 X28.677 Y104.569 E2.53467\n" +
"G1 X31.464 Y104.749 E2.61002\n" +
"G1 X31.880 Y104.746 E2.62124\n" +
"G1 X32.441 Y104.681 E2.63648\n" +
"G1 X32.917 Y104.531 E2.64993\n" +
"G1 X33.367 Y104.278 E2.66386\n" +
"G1 X33.840 Y103.821 E2.68159\n" +
"G1 X33.955 Y103.637 E2.68747\n" +
"G1 X33.869 Y105.243 E2.73085\n" +
"G1 X33.947 Y106.990 E2.77804\n" +
"G1 X33.993 Y107.400 E2.78917\n" +
"G1 X34.169 Y108.397 E2.81646\n" +
"G1 X34.413 Y109.257 E2.84061\n" +
"G1 X35.092 Y110.937 E2.88947\n" +
"G1 X36.039 Y111.196 E2.91595\n" +
"G1 X36.250 Y111.333 E2.92274\n" +
"G1 X36.363 Y111.458 E2.92727\n" +
"G1 X36.447 Y111.603 E2.93181\n" +
"G1 X36.512 Y111.846 E2.93860\n" +
"G1 X36.461 Y112.990 E2.96947\n" +
"G1 X37.746 Y113.736 E3.00956\n" +
"G1 X39.543 Y114.630 E3.06371\n" +
"G1 X39.923 Y114.783 E3.07474\n" +
"G1 E1.07474 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.427 Y112.114 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.528 Y110.860 E2.03393\n" +
"G1 X27.614 Y105.818 E2.16999\n" +
"G1 X27.607 Y103.945 E2.22052\n" +
"G1 X27.881 Y103.923 E2.22795\n" +
"G1 X28.701 Y103.933 E2.25007\n" +
"G1 X31.483 Y104.113 E2.32526\n" +
"G1 X31.841 Y104.111 E2.33493\n" +
"G1 X32.308 Y104.056 E2.34760\n" +
"G1 X32.663 Y103.945 E2.35765\n" +
"G1 X32.984 Y103.764 E2.36759\n" +
"G1 X33.342 Y103.418 E2.38101\n" +
"G1 X33.671 Y102.893 E2.39773\n" +
"G1 X33.970 Y102.224 E2.41751\n" +
"G1 X35.371 Y98.006 E2.53741\n" +
"G1 X35.269 Y98.403 E2.54847\n" +
"G1 X34.657 Y102.431 E2.65839\n" +
"G1 X34.505 Y105.246 E2.73442\n" +
"G1 X34.581 Y106.940 E2.78018\n" +
"G1 X34.622 Y107.309 E2.79020\n" +
"G1 X34.789 Y108.254 E2.81608\n" +
"G1 X35.015 Y109.051 E2.83842\n" +
"G1 X35.562 Y110.404 E2.87779\n" +
"G1 X36.155 Y110.560 E2.89432\n" +
"G1 X36.431 Y110.682 E2.90246\n" +
"G1 X36.675 Y110.859 E2.91059\n" +
"G1 X36.877 Y111.083 E2.91873\n" +
"G1 X37.028 Y111.344 E2.92686\n" +
"G1 X37.083 Y111.485 E2.93093\n" +
"G1 X37.145 Y111.780 E2.93907\n" +
"G1 X37.153 Y111.930 E2.94314\n" +
"G1 X37.116 Y112.635 E2.96218\n" +
"G1 X38.048 Y113.176 E2.99123\n" +
"G1 X39.804 Y114.050 E3.04415\n" +
"G1 X42.414 Y115.101 E3.12006\n" +
"G1 X47.306 Y116.529 E3.25754\n" +
"G1 X52.916 Y117.695 E3.41211\n" +
"G1 X58.815 Y118.390 E3.57233\n" +
"G1 X65.185 Y118.583 E3.74427\n" +
"G1 X72.879 Y118.538 E3.95183\n" +
"G1 X77.780 Y118.095 E4.08457\n" +
"G1 X80.794 Y117.510 E4.16741\n" +
"G1 X85.203 Y116.279 E4.29090\n" +
"G1 X86.497 Y115.811 E4.32801\n" +
"G1 X87.335 Y115.410 E4.35307\n" +
"G1 X88.116 Y114.935 E4.37774\n" +
"G1 X88.821 Y114.401 E4.40159\n" +
"G1 X89.449 Y113.820 E4.42468\n" +
"G1 X90.273 Y112.878 E4.45843\n" +
"G1 X90.099 Y112.109 E4.47970\n" +
"G1 X90.093 Y111.811 E4.48776\n" +
"G1 X90.149 Y111.519 E4.49576\n" +
"G1 X90.262 Y111.245 E4.50376\n" +
"G1 X90.442 Y110.983 E4.51232\n" +
"G1 X90.519 Y110.902 E4.51536\n" +
"G1 X90.766 Y110.711 E4.52376\n" +
"G1 X91.476 Y110.366 E4.54506\n" +
"G1 X91.773 Y108.927 E4.58469\n" +
"G1 X91.902 Y107.811 E4.61499\n" +
"G1 X91.939 Y106.375 E4.65375\n" +
"G1 X91.801 Y104.241 E4.71144\n" +
"G1 X91.530 Y102.376 E4.76229\n" +
"G1 X91.306 Y101.191 E4.79482\n" +
"G1 X89.993 Y96.063 E4.93760\n" +
"G1 X89.714 Y95.242 E4.96100\n" +
"G1 X88.458 Y91.876 E5.05794\n" +
"G1 X88.192 Y91.231 E5.07676\n" +
"G1 X87.552 Y89.812 E5.11874\n" +
"G1 X87.015 Y88.884 E5.14765\n" +
"G1 X86.403 Y88.037 E5.17586\n" +
"G1 X85.743 Y87.294 E5.20266\n" +
"G1 X84.974 Y86.580 E5.23098\n" +
"G1 X84.319 Y86.040 E5.25387\n" +
"G1 X83.689 Y86.215 E5.27150\n" +
"G1 X83.471 Y86.182 E5.27745\n" +
"G1 X83.307 Y86.070 E5.28279\n" +
"G1 X83.195 Y85.875 E5.28887\n" +
"G1 X83.124 Y85.205 E5.30704\n" +
"G1 X81.988 Y84.644 E5.34125\n" +
"G1 X80.635 Y84.110 E5.38047\n" +
"G1 X78.568 Y83.462 E5.43889\n" +
"G1 X71.508 Y81.859 E5.63419\n" +
"G1 X65.795 Y81.112 E5.78964\n" +
"G1 X63.234 Y81.011 E5.85878\n" +
"G1 X64.653 Y81.020 E5.89706\n" +
"G1 X70.321 Y81.421 E6.05034\n" +
"G1 X71.110 Y81.502 E6.07174\n" +
"G1 X76.516 Y82.386 E6.21950\n" +
"G1 X79.666 Y83.186 E6.30717\n" +
"G1 X81.301 Y83.774 E6.35404\n" +
"G1 X82.666 Y84.424 E6.39484\n" +
"G1 X84.062 Y85.254 E6.43865\n" +
"G1 X85.076 Y85.988 E6.47244\n" +
"G1 X85.837 Y86.657 E6.49976\n" +
"G1 X86.439 Y87.299 E6.52350\n" +
"G1 X86.952 Y87.966 E6.54620\n" +
"G1 X87.571 Y88.979 E6.57823\n" +
"G1 X88.270 Y90.430 E6.62166\n" +
"G1 X88.490 Y90.963 E6.63721\n" +
"G1 X89.799 Y94.612 E6.74181\n" +
"G1 X91.043 Y98.617 E6.85494\n" +
"G1 X91.779 Y101.156 E6.92626\n" +
"G1 X93.127 Y106.225 E7.06775\n" +
"G1 X93.474 Y107.125 E7.09376\n" +
"G1 X93.661 Y107.503 E7.10515\n" +
"G1 X93.940 Y107.961 E7.11960\n" +
"G1 X94.392 Y108.515 E7.13889\n" +
"G1 X94.734 Y108.835 E7.15153\n" +
"G1 X95.105 Y109.120 E7.16414\n" +
"G1 X95.524 Y109.381 E7.17747\n" +
"G1 X96.041 Y109.637 E7.19302\n" +
"G1 X96.606 Y109.852 E7.20935\n" +
"G1 X97.260 Y110.036 E7.22766\n" +
"G1 X98.026 Y110.179 E7.24870\n" +
"G1 X98.962 Y110.270 E7.27405\n" +
"G1 X100.968 Y110.272 E7.32818\n" +
"G1 X101.951 Y110.183 E7.35481\n" +
"G1 X102.740 Y110.036 E7.37646\n" +
"G1 X103.403 Y109.849 E7.39503\n" +
"G1 X103.960 Y109.637 E7.41111\n" +
"G1 X104.476 Y109.381 E7.42665\n" +
"G1 X104.895 Y109.120 E7.43998\n" +
"G1 X105.276 Y108.828 E7.45291\n" +
"G1 X105.758 Y108.350 E7.47124\n" +
"G1 X105.974 Y108.079 E7.48059\n" +
"G1 X106.223 Y107.706 E7.49267\n" +
"G1 X106.522 Y107.133 E7.51011\n" +
"G1 X106.873 Y106.225 E7.53637\n" +
"G1 X108.221 Y101.156 E7.67786\n" +
"G1 X108.957 Y98.617 E7.74918\n" +
"G1 X110.203 Y94.605 E7.86252\n" +
"G1 X110.496 Y93.739 E7.88716\n" +
"G1 X111.339 Y91.407 E7.95407\n" +
"G1 X111.732 Y90.426 E7.98257\n" +
"G1 X112.465 Y88.912 E8.02797\n" +
"G1 X113.048 Y87.967 E8.05791\n" +
"G1 X113.536 Y87.329 E8.07958\n" +
"G1 X114.164 Y86.656 E8.10442\n" +
"G1 X114.823 Y86.074 E8.12813\n" +
"G1 X115.948 Y85.248 E8.16578\n" +
"G1 X117.334 Y84.424 E8.20928\n" +
"G1 X118.698 Y83.774 E8.25004\n" +
"G1 X120.453 Y83.150 E8.30028\n" +
"G1 X123.619 Y82.358 E8.38833\n" +
"G1 X128.889 Y81.502 E8.53236\n" +
"G1 X129.681 Y81.421 E8.55382\n" +
"G1 X134.492 Y81.063 E8.68396\n" +
"G1 X135.338 Y81.020 E8.70683\n" +
"G1 X136.766 Y81.011 E8.74535\n" +
"G1 X134.205 Y81.112 E8.81448\n" +
"G1 X128.492 Y81.859 E8.96993\n" +
"G1 X121.432 Y83.462 E9.16523\n" +
"G1 X119.365 Y84.110 E9.22366\n" +
"G1 X118.012 Y84.644 E9.26288\n" +
"G1 X116.876 Y85.205 E9.29708\n" +
"G1 X116.808 Y85.852 E9.31464\n" +
"G1 X116.725 Y86.032 E9.31998\n" +
"G1 X116.572 Y86.162 E9.32539\n" +
"G1 X116.449 Y86.207 E9.32894\n" +
"G1 X116.306 Y86.214 E9.33278\n" +
"G1 X115.681 Y86.040 E9.35029\n" +
"G1 X115.026 Y86.580 E9.37318\n" +
"G1 X114.257 Y87.294 E9.40150\n" +
"G1 X113.597 Y88.037 E9.42829\n" +
"G1 X112.985 Y88.884 E9.45650\n" +
"G1 X112.450 Y89.809 E9.48533\n" +
"G1 X112.124 Y90.519 E9.50639\n" +
"G1 X111.538 Y91.887 E9.54655\n" +
"G1 X110.076 Y95.833 E9.66007\n" +
"G1 X108.630 Y101.466 E9.81695\n" +
"G1 X108.469 Y102.382 E9.84204\n" +
"G1 X108.237 Y103.904 E9.88357\n" +
"G1 X108.189 Y104.335 E9.89527\n" +
"G1 X108.062 Y106.309 E9.94865\n" +
"G1 X108.094 Y107.749 E9.98749\n" +
"G1 X108.237 Y108.995 E10.02132\n" +
"G1 X108.523 Y110.382 E10.05954\n" +
"G1 X109.114 Y110.643 E10.07698\n" +
"G1 X109.356 Y110.795 E10.08468\n" +
"G1 X109.560 Y110.988 E10.09226\n" +
"G1 X109.653 Y111.106 E10.09630\n" +
"G1 X109.802 Y111.379 E10.10469\n" +
"G1 X109.887 Y111.664 E10.11269\n" +
"G1 X109.913 Y111.981 E10.12128\n" +
"G1 X109.905 Y112.088 E10.12418\n" +
"G1 X109.727 Y112.878 E10.14604\n" +
"G1 X110.551 Y113.820 E10.17979\n" +
"G1 X111.179 Y114.401 E10.20288\n" +
"G1 X111.884 Y114.935 E10.22673\n" +
"G1 X112.665 Y115.410 E10.25140\n" +
"G1 X113.503 Y115.811 E10.27646\n" +
"G1 X114.797 Y116.279 E10.31357\n" +
"G1 X119.206 Y117.510 E10.43706\n" +
"G1 X122.220 Y118.095 E10.51990\n" +
"G1 X127.121 Y118.538 E10.65263\n" +
"G1 X134.815 Y118.583 E10.86020\n" +
"G1 X141.185 Y118.390 E11.03214\n" +
"G1 X147.084 Y117.695 E11.19236\n" +
"G1 X152.694 Y116.529 E11.34693\n" +
"G1 X157.586 Y115.101 E11.48441\n" +
"G1 X160.196 Y114.050 E11.56031\n" +
"G1 X161.952 Y113.176 E11.61324\n" +
"G1 X162.884 Y112.635 E11.64229\n" +
"G1 X162.847 Y111.930 E11.66133\n" +
"G1 X162.855 Y111.780 E11.66540\n" +
"G1 X162.875 Y111.650 E11.66894\n" +
"G1 X162.968 Y111.360 E11.67717\n" +
"G1 X163.044 Y111.203 E11.68186\n" +
"G1 X163.218 Y110.966 E11.68980\n" +
"G1 X163.325 Y110.859 E11.69387\n" +
"G1 X163.569 Y110.682 E11.70201\n" +
"G1 X163.845 Y110.560 E11.71014\n" +
"G1 X164.438 Y110.404 E11.72668\n" +
"G1 X164.985 Y109.051 E11.76604\n" +
"G1 X165.211 Y108.254 E11.78838\n" +
"G1 X165.377 Y107.309 E11.81427\n" +
"G1 X165.419 Y106.940 E11.82428\n" +
"G1 X165.495 Y105.246 E11.87005\n" +
"G1 X165.343 Y102.431 E11.94607\n" +
"G1 X164.731 Y98.403 E12.05599\n" +
"G1 X164.629 Y98.006 E12.06705\n" +
"G1 X166.029 Y102.223 E12.18693\n" +
"G1 X166.317 Y102.867 E12.20595\n" +
"G1 X166.539 Y103.247 E12.21782\n" +
"G1 X166.779 Y103.554 E12.22836\n" +
"G1 X167.059 Y103.792 E12.23824\n" +
"G1 X167.379 Y103.964 E12.24805\n" +
"G1 X167.889 Y104.091 E12.26223\n" +
"G1 X168.504 Y104.114 E12.27883\n" +
"G1 X171.666 Y103.918 E12.36430\n" +
"G1 X172.397 Y103.942 E12.38403\n" +
"G1 X172.386 Y105.816 E12.43457\n" +
"G1 X172.472 Y110.860 E12.57067\n" +
"G1 X172.572 Y112.109 E12.60447\n" +
"G1 X172.392 Y112.170 E12.60961\n" +
"G1 X166.065 Y113.843 E12.78614\n" +
"G1 X164.936 Y114.172 E12.81789\n" +
"G1 X159.204 Y115.679 E12.97775\n" +
"G1 X150.858 Y117.363 E13.20743\n" +
"G1 X144.128 Y118.428 E13.39126\n" +
"G1 X135.366 Y119.081 E13.62826\n" +
"G1 X127.200 Y118.984 E13.84859\n" +
"G1 X121.854 Y118.327 E13.99387\n" +
"G1 X115.604 Y117.002 E14.16624\n" +
"G1 X110.346 Y115.300 E14.31533\n" +
"G1 X106.951 Y114.264 E14.41106\n" +
"G1 X103.629 Y113.613 E14.50240\n" +
"G1 X101.557 Y113.405 E14.55858\n" +
"G1 X100.232 Y113.398 E14.59432\n" +
"G1 X98.445 Y113.405 E14.64253\n" +
"G1 X96.632 Y113.582 E14.69166\n" +
"G1 X96.160 Y113.647 E14.70452\n" +
"G1 X93.048 Y114.265 E14.79012\n" +
"G1 X89.654 Y115.300 E14.88583\n" +
"G1 X84.409 Y116.998 E15.03457\n" +
"G1 X79.042 Y118.152 E15.18264\n" +
"G1 X78.151 Y118.327 E15.20714\n" +
"G1 X72.800 Y118.984 E15.35257\n" +
"G1 X64.634 Y119.081 E15.57290\n" +
"G1 X55.872 Y118.428 E15.80992\n" +
"G1 X49.668 Y117.458 E15.97930\n" +
"G1 X48.832 Y117.303 E16.00225\n" +
"G1 X40.796 Y115.679 E16.22341\n" +
"G1 X35.064 Y114.172 E16.38328\n" +
"G1 X33.935 Y113.843 E16.41502\n" +
"G1 X27.499 Y112.137 E16.59464\n" +
"G1 E14.59464 F2400.00000\n" +
"G92 E0\n" +
"G1 X44.956 Y82.074 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X45.821 Y81.771 E2.02473\n" +
"G1 X46.934 Y81.505 E2.05561\n" +
"G1 X47.428 Y81.435 E2.06906\n" +
"G1 X46.318 Y81.659 E2.09959\n" +
"G1 X45.027 Y82.052 E2.13599\n" +
"G1 E0.13599 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.364 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X40.997 Y84.927 E2.05183\n" +
"G1 X41.291 Y85.623 E2.07220\n" +
"G1 X41.310 Y85.722 E2.07492\n" +
"G1 X41.302 Y85.854 E2.07850\n" +
"G1 X41.262 Y85.963 E2.08162\n" +
"G1 X41.164 Y86.096 E2.08608\n" +
"G1 X41.036 Y86.179 E2.09020\n" +
"G1 X40.820 Y86.213 E2.09609\n" +
"G1 X40.022 Y86.072 E2.11796\n" +
"G1 X40.204 Y85.782 E2.12721\n" +
"G1 X40.759 Y85.065 E2.15167\n" +
"G1 X41.400 Y84.385 E2.17688\n" +
"G1 X42.143 Y83.731 E2.20356\n" +
"G1 X42.422 Y83.529 E2.21286\n" +
"G1 E0.21286 F2400.00000\n" +
"G92 E0\n" +
"G1 X39.982 Y86.135 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X38.521 Y89.141 E2.09017\n" +
"G1 X39.068 Y87.807 E2.12905\n" +
"G1 X39.684 Y86.608 E2.16542\n" +
"G1 X39.943 Y86.198 E2.17849\n" +
"G1 E0.17849 F2400.00000\n" +
"G92 E0\n" +
"G1 X152.572 Y81.435 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X153.065 Y81.504 E2.01343\n" +
"G1 X154.235 Y81.787 E2.04588\n" +
"G1 X154.911 Y82.033 E2.06530\n" +
"G1 X153.682 Y81.659 E2.09996\n" +
"G1 X152.646 Y81.450 E2.12848\n" +
"G1 E0.12848 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.637 Y83.579 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.946 Y83.807 E2.01034\n" +
"G1 X158.600 Y84.384 E2.03387\n" +
"G1 X159.241 Y85.065 E2.05910\n" +
"G1 X159.795 Y85.782 E2.08356\n" +
"G1 X159.978 Y86.072 E2.09282\n" +
"G1 X159.180 Y86.213 E2.11469\n" +
"G1 X159.054 Y86.208 E2.11807\n" +
"G1 X158.941 Y86.165 E2.12134\n" +
"G1 X158.835 Y86.094 E2.12479\n" +
"G1 X158.723 Y85.935 E2.13003\n" +
"G1 X158.694 Y85.818 E2.13330\n" +
"G1 X158.691 Y85.692 E2.13668\n" +
"G1 X158.732 Y85.555 E2.14056\n" +
"G1 X159.027 Y84.951 E2.15868\n" +
"G1 X157.691 Y83.632 E2.20933\n" +
"G1 E0.20933 F2400.00000\n" +
"G92 E0\n" +
"G1 X160.018 Y86.135 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.316 Y86.608 E2.01509\n" +
"G1 X160.932 Y87.807 E2.05146\n" +
"G1 X161.486 Y89.157 E2.09081\n" +
"G1 X160.050 Y86.202 E2.17942\n" +
"G1 E0.17942 F2400.00000\n" +
"G92 E0\n" +
"G1 X162.306 Y112.322 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X161.686 Y112.682 E2.01476\n" +
"G1 X159.966 Y113.538 E2.05437\n" +
"G1 X157.402 Y114.571 E2.11134\n" +
"G1 X152.558 Y115.985 E2.21534\n" +
"G1 X146.994 Y117.141 E2.33247\n" +
"G1 X141.144 Y117.831 E2.45387\n" +
"G1 X134.808 Y118.022 E2.58452\n" +
"G1 X127.148 Y117.978 E2.74241\n" +
"G1 X122.299 Y117.539 E2.84274\n" +
"G1 X119.335 Y116.964 E2.90498\n" +
"G1 X114.968 Y115.745 E2.99843\n" +
"G1 X113.720 Y115.293 E3.02578\n" +
"G1 X112.932 Y114.917 E3.04377\n" +
"G1 X112.200 Y114.471 E3.06145";
                                        salientemarcos[6]+="G1 X111.539 Y113.971 E3.07852\n" +
"G1 X110.953 Y113.428 E3.09498\n" +
"G1 X110.337 Y112.724 E3.11428\n" +
"G1 X110.462 Y112.170 E3.12597\n" +
"G1 X110.475 Y111.984 E3.12983\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X110.440 Y111.559 E3.14167\n" +
"G1 X110.322 Y111.163 E3.15315\n" +
"G1 X110.123 Y110.796 E3.16475\n" +
"G1 X109.975 Y110.609 E3.17138\n" +
"G1 X109.684 Y110.339 E3.18239\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X109.378 Y110.147 E3.18983\n" +
"G1 X109.014 Y109.986 E3.19805\n" +
"G1 X108.791 Y108.906 E3.22077\n" +
"G1 X108.654 Y107.711 E3.24557\n" +
"G1 X108.623 Y106.321 E3.27422\n" +
"G1 X108.748 Y104.384 E3.31423\n" +
"G1 X108.793 Y103.977 E3.32267\n" +
"G1 X109.022 Y102.473 E3.35403\n" +
"G1 X109.179 Y101.584 E3.37262\n" +
"G1 X110.612 Y96.001 E3.49143\n" +
"G1 X112.058 Y92.095 E3.57728\n" +
"G1 X112.636 Y90.746 E3.60752\n" +
"G1 X112.948 Y90.067 E3.62292\n" +
"G1 X113.456 Y89.190 E3.64382\n" +
"G1 X114.035 Y88.388 E3.66421\n" +
"G1 X114.658 Y87.686 E3.68354\n" +
"G1 X115.395 Y87.002 E3.70427\n" +
"G1 X115.812 Y86.659 E3.71540\n" +
"G1 X116.200 Y86.767 E3.72370\n" +
"G1 X116.296 Y86.777 E3.72568\n" +
"G1 X116.573 Y86.754 E3.73142\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X116.839 Y86.659 E3.73925\n" +
"G1 X117.023 Y86.534 E3.74543\n" +
"G1 X117.205 Y86.322 E3.75319\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X117.289 Y86.182 E3.75656\n" +
"G1 X117.359 Y85.971 E3.76113\n" +
"G1 X117.401 Y85.571 E3.76943\n" +
"G1 X118.240 Y85.157 E3.78871\n" +
"G1 X119.552 Y84.639 E3.81779\n" +
"G1 X121.578 Y84.004 E3.86154\n" +
"G1 X128.590 Y82.412 E4.00975\n" +
"G1 X134.253 Y81.671 E4.12745\n" +
"G1 X141.621 Y81.379 E4.27942\n" +
"G1 X148.222 Y81.432 E4.41548\n" +
"G1 X151.177 Y81.725 E4.47668\n" +
"G1 X153.545 Y82.203 E4.52647\n" +
"G1 X154.961 Y82.634 E4.55698\n" +
"G1 X155.766 Y82.970 E4.57495\n" +
"G1 X156.332 Y83.280 E4.58826\n" +
"G1 X156.785 Y83.594 E4.59963\n" +
"G1 X157.258 Y83.992 E4.61236\n" +
"G1 X158.346 Y85.067 E4.64389\n" +
"G1 X158.209 Y85.350 E4.65038\n" +
"G1 X158.134 Y85.629 E4.65634\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X158.135 Y85.894 E4.66370\n" +
"G1 X158.190 Y86.114 E4.67000\n" +
"G1 X158.295 Y86.318 E4.67636\n" +
"G1 X158.426 Y86.480 E4.68215\n" +
"G1 X158.687 Y86.668 E4.69108\n" +
"G1 X158.898 Y86.748 E4.69737\n" +
"G1 X159.082 Y86.771 E4.70250\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X159.218 Y86.776 E4.70531\n" +
"G1 X159.667 Y86.697 E4.71472\n" +
"G1 X161.914 Y91.320 E4.82067\n" +
"G1 X163.281 Y95.008 E4.90174\n" +
"G1 X164.181 Y98.515 E4.97635\n" +
"G1 X164.785 Y102.489 E5.05919\n" +
"G1 X164.934 Y105.248 E5.11614\n" +
"G1 X164.860 Y106.896 E5.15015\n" +
"G1 X164.822 Y107.229 E5.15705\n" +
"G1 X164.664 Y108.128 E5.17587\n" +
"G1 X164.454 Y108.869 E5.19173\n" +
"G1 X164.023 Y109.933 E5.21540\n" +
"G1 X163.673 Y110.026 E5.22286\n" +
"G1 X163.283 Y110.200 E5.23166\n" +
"M106 S255\n" +
"\n" +
"G1 F3600\n" +
"G1 X162.950 Y110.441 E5.24309\n" +
"G1 X162.669 Y110.752 E5.25473\n" +
"G1 X162.556 Y110.926 E5.26049\n" +
"G1 X162.446 Y111.150 E5.26742\n" +
"G1 X162.328 Y111.520 E5.27820\n" +
"G1 X162.309 Y111.643 E5.28166\n" +
"M106 S137.7\n" +
"\n" +
"G1 F3000\n" +
"G1 X162.285 Y111.930 E5.28761\n" +
"G1 X162.302 Y112.247 E5.29415\n" +
"G1 X162.480 Y112.791 F7800.000\n" +
"G1 E3.29415 F2400.00000\n" +
"G92 E0\n" +
"G1 X173.142 Y112.430 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X173.080 Y112.502 E2.00196\n" +
"G1 X172.943 Y112.576 E2.00517\n" +
"G1 X172.554 Y112.707 E2.01363\n" +
"G1 X166.215 Y114.383 E2.14876\n" +
"G1 X165.085 Y114.712 E2.17302\n" +
"G1 X159.331 Y116.225 E2.29564\n" +
"G1 X150.958 Y117.914 E2.47170\n" +
"G1 X144.192 Y118.985 E2.61287\n" +
"G1 X135.384 Y119.642 E2.79492\n" +
"G1 X127.162 Y119.544 E2.96439\n" +
"G1 X121.763 Y118.881 E3.07651\n" +
"G1 X115.450 Y117.542 E3.20951\n" +
"G1 X110.178 Y115.835 E3.32372\n" +
"G1 X106.815 Y114.809 E3.39618\n" +
"G1 X103.547 Y114.168 E3.46483\n" +
"G1 X101.527 Y113.966 E3.50666\n" +
"G1 X100.232 Y113.958 E3.53336\n" +
"G1 X98.473 Y113.965 E3.56960\n" +
"G1 X96.698 Y114.139 E3.60637\n" +
"G1 X96.253 Y114.200 E3.61562\n" +
"G1 X93.184 Y114.809 E3.68010\n" +
"G1 X89.822 Y115.835 E3.75255\n" +
"G1 X84.554 Y117.541 E3.86668\n" +
"G1 X79.155 Y118.701 E3.98050\n" +
"G1 X78.239 Y118.881 E3.99973\n" +
"G1 X72.838 Y119.544 E4.11189\n" +
"G1 X64.616 Y119.642 E4.28136\n" +
"G1 X55.807 Y118.985 E4.46341\n" +
"G1 X49.574 Y118.011 E4.59344\n" +
"G1 X48.725 Y117.854 E4.61124\n" +
"G1 X40.669 Y116.225 E4.78064\n" +
"G1 X34.915 Y114.712 E4.90326\n" +
"G1 X33.785 Y114.383 E4.92752\n" +
"G1 X27.189 Y112.627 E5.06819\n" +
"G1 X26.927 Y112.511 E5.07411\n" +
"G1 X26.858 Y112.430 E5.07629\n" +
"G1 X26.846 Y112.343 E5.07811\n" +
"G1 X26.968 Y110.833 E5.10932\n" +
"G1 X27.054 Y105.814 E5.21278\n" +
"G1 X27.046 Y103.809 E5.25410\n" +
"G1 X27.071 Y103.628 E5.25787\n" +
"G1 X27.127 Y103.519 E5.26041\n" +
"G1 X27.249 Y103.441 E5.26338\n" +
"G1 X27.448 Y103.395 E5.26760\n" +
"G1 X27.863 Y103.362 E5.27617\n" +
"G1 X28.723 Y103.373 E5.29390\n" +
"G1 X31.499 Y103.552 E5.35124\n" +
"G1 X31.807 Y103.550 E5.35758\n" +
"G1 X32.190 Y103.506 E5.36553\n" +
"G1 X32.439 Y103.427 E5.37092\n" +
"G1 X32.647 Y103.311 E5.37582\n" +
"G1 X32.903 Y103.063 E5.38317\n" +
"G1 X33.175 Y102.628 E5.39373\n" +
"G1 X33.448 Y102.019 E5.40750\n" +
"G1 X34.039 Y100.277 E5.44540\n" +
"G1 X36.487 Y92.792 E5.60771\n" +
"G1 X36.674 Y92.276 E5.61903\n" +
"G1 X38.396 Y87.936 E5.71526\n" +
"G1 X38.563 Y87.565 E5.72365\n" +
"G1 X39.197 Y86.330 E5.75225\n" +
"G1 X39.744 Y85.461 E5.77343\n" +
"G1 X40.332 Y84.700 E5.79324\n" +
"G1 X41.010 Y83.981 E5.81361\n" +
"G1 X41.792 Y83.293 E5.83507\n" +
"G1 X42.739 Y82.604 E5.85921\n" +
"G1 X43.694 Y82.031 E5.88217\n" +
"G1 X44.570 Y81.615 E5.90215\n" +
"G1 X45.662 Y81.232 E5.92601\n" +
"G1 X46.829 Y80.953 E5.95074\n" +
"G1 X48.920 Y80.657 E5.99425\n" +
"G1 X54.149 Y80.387 E6.10218\n" +
"G1 X64.675 Y80.460 E6.31912\n" +
"G1 X70.369 Y80.862 E6.43678\n" +
"G1 X71.184 Y80.946 E6.45366\n" +
"G1 X76.630 Y81.836 E6.56739\n" +
"G1 X79.830 Y82.649 E6.63543\n" +
"G1 X81.517 Y83.255 E6.67238\n" +
"G1 X82.930 Y83.929 E6.70465\n" +
"G1 X84.365 Y84.782 E6.73906\n" +
"G1 X84.547 Y84.908 E6.74361\n" +
"G1 X85.427 Y85.551 E6.76608\n" +
"G1 X86.227 Y86.254 E6.78803\n" +
"G1 X86.867 Y86.935 E6.80730\n" +
"G1 X87.415 Y87.649 E6.82583\n" +
"G1 X88.064 Y88.711 E6.85149\n" +
"G1 X88.782 Y90.201 E6.88557\n" +
"G1 X89.013 Y90.761 E6.89806\n" +
"G1 X90.331 Y94.434 E6.97850\n" +
"G1 X91.580 Y98.456 E7.06529\n" +
"G1 X92.319 Y101.006 E7.12002\n" +
"G1 X93.661 Y106.051 E7.22762\n" +
"G1 X93.988 Y106.899 E7.24634\n" +
"G1 X94.153 Y107.233 E7.25402\n" +
"G1 X94.399 Y107.636 E7.26375\n" +
"G1 X94.803 Y108.131 E7.27692\n" +
"G1 X95.097 Y108.407 E7.28524\n" +
"G1 X95.424 Y108.659 E7.29374\n" +
"G1 X95.797 Y108.891 E7.30280\n" +
"G1 X96.265 Y109.122 E7.31355\n" +
"G1 X96.782 Y109.319 E7.32495\n" +
"G1 X97.388 Y109.489 E7.33792\n" +
"G1 X98.105 Y109.624 E7.35297\n" +
"G1 X98.989 Y109.710 E7.37127\n" +
"G1 X100.943 Y109.711 E7.41155\n" +
"G1 X101.875 Y109.627 E7.43082\n" +
"G1 X102.612 Y109.489 E7.44629\n" +
"G1 X103.227 Y109.316 E7.45944\n" +
"G1 X103.735 Y109.122 E7.47066\n" +
"G1 X104.203 Y108.891 E7.48141\n" +
"G1 X104.576 Y108.659 E7.49047\n" +
"G1 X104.906 Y108.405 E7.49905\n" +
"G1 X105.340 Y107.974 E7.51165\n" +
"G1 X105.521 Y107.748 E7.51764\n" +
"G1 X105.740 Y107.420 E7.52575\n" +
"G1 X106.011 Y106.902 E7.53780\n" +
"G1 X106.339 Y106.051 E7.55659\n" +
"G1 X107.681 Y101.006 E7.66419\n" +
"G1 X108.420 Y98.456 E7.71892\n" +
"G1 X109.670 Y94.432 E7.80576\n" +
"G1 X109.967 Y93.554 E7.82485\n" +
"G1 X110.815 Y91.208 E7.87629\n" +
"G1 X111.219 Y90.200 E7.89867\n" +
"G1 X111.973 Y88.642 E7.93434\n" +
"G1 X112.585 Y87.649 E7.95838\n" +
"G1 X113.107 Y86.966 E7.97608\n" +
"G1 X113.773 Y86.253 E7.99619\n" +
"G1 X114.471 Y85.637 E8.01538\n" +
"G1 X115.638 Y84.780 E8.04523\n" +
"G1 X117.070 Y83.929 E8.07955\n" +
"G1 X118.483 Y83.256 E8.11182\n" +
"G1 X120.290 Y82.613 E8.15135\n" +
"G1 X123.506 Y81.808 E8.21967\n" +
"G1 X128.816 Y80.946 E8.33054\n" +
"G1 X129.631 Y80.862 E8.34744\n" +
"G1 X134.457 Y80.503 E8.44717\n" +
"G1 X135.322 Y80.460 E8.46503\n" +
"G1 X145.851 Y80.387 E8.68203\n" +
"G1 X151.080 Y80.657 E8.78996\n" +
"G1 X153.171 Y80.953 E8.83347\n" +
"G1 X154.397 Y81.249 E8.85947\n" +
"G1 X155.548 Y81.669 E8.88473\n" +
"G1 X156.305 Y82.031 E8.90202\n" +
"G1 X157.261 Y82.604 E8.92498\n" +
"G1 X158.298 Y83.370 E8.95157\n" +
"G1 X158.990 Y83.981 E8.97058\n" +
"G1 X159.668 Y84.700 E8.99095\n" +
"G1 X160.256 Y85.461 E9.01076\n" +
"G1 X160.803 Y86.330 E9.03194\n" +
"G1 X161.437 Y87.565 E9.06055\n" +
"G1 X161.604 Y87.937 E9.06894\n" +
"G1 X163.105 Y91.705 E9.15255\n" +
"G1 X163.514 Y92.794 E9.17651\n" +
"G1 X165.961 Y100.277 E9.33880\n" +
"G1 X166.552 Y102.019 E9.37669\n" +
"G1 X166.816 Y102.611 E9.39005\n" +
"G1 X167.003 Y102.931 E9.39771\n" +
"G1 X167.186 Y103.164 E9.40380\n" +
"G1 X167.377 Y103.326 E9.40896\n" +
"G1 X167.583 Y103.437 E9.41378\n" +
"G1 X167.968 Y103.533 E9.42197\n" +
"G1 X168.496 Y103.552 E9.43286\n" +
"G1 X171.656 Y103.358 E9.49812\n" +
"G1 X172.267 Y103.365 E9.51071\n" +
"G1 X172.556 Y103.395 E9.51670\n" +
"G1 X172.762 Y103.447 E9.52107\n" +
"G1 X172.870 Y103.515 E9.52369\n" +
"G1 X172.930 Y103.630 E9.52637\n" +
"G1 X172.954 Y103.786 E9.52962\n" +
"G1 X172.946 Y105.813 E9.57140\n" +
"G1 X173.032 Y110.833 E9.67488\n" +
"G1 X173.152 Y112.356 E9.70635\n" +
"G1 X173.047 Y112.437 F7800.000\n" +
"G1 E7.70635 F2400.00000\n" +
"G92 E0\n" +
"G1 X170.974 Y111.412 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X171.468 Y110.918 E2.02014\n" +
"G1 X171.437 Y109.990 E2.04688\n" +
"G1 X169.669 Y111.757 E2.11885\n" +
"G1 X168.365 Y112.102 E2.15769\n" +
"G1 X171.421 Y109.046 E2.28212\n" +
"G1 X171.405 Y108.103 E2.30929\n" +
"G1 X167.061 Y112.447 E2.48616\n" +
"G1 X165.757 Y112.791 E2.52500\n" +
"G1 X171.389 Y107.159 E2.75433\n" +
"G1 X171.373 Y106.216 E2.78149\n" +
"G1 X164.414 Y113.175 E3.06486\n" +
"G1 X163.931 Y113.304 E3.07924\n" +
"G1 X163.905 Y112.724 E3.09595\n" +
"G1 X165.328 Y111.301 E3.15391\n" +
"G1 X165.626 Y111.219 E3.16281\n" +
"G1 X165.773 Y110.856 E3.17408\n" +
"G1 X171.364 Y105.265 E3.40174\n" +
"G1 X171.366 Y104.971 E3.41023\n" +
"G1 X170.654 Y105.016 E3.43079\n" +
"G1 X165.930 Y109.740 E3.62314\n" +
"G1 X166.204 Y108.786 E3.65172\n" +
"G1 X166.264 Y108.446 E3.66166\n" +
"G1 X169.627 Y105.083 E3.79859\n" +
"G1 X168.769 Y105.139 E3.82335\n" +
"G1 X168.617 Y105.134 E3.82772\n" +
"G1 X166.437 Y107.314 E3.91651\n" +
"G1 X166.484 Y106.308 E3.94551\n" +
"G1 X167.737 Y105.054 E3.99656\n" +
"G1 X167.164 Y104.912 E4.01356\n" +
"G1 X167.006 Y104.826 E4.01874\n" +
"G1 X166.512 Y105.321 E4.03887\n" +
"G1 E2.03887 F2400.00000\n" +
"G92 E0\n" +
"G1 X108.254 Y113.656 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X108.748 Y113.161 E2.01904\n" +
"G1 X108.592 Y112.982 E2.02551\n" +
"G1 X108.758 Y112.245 E2.04610\n" +
"G1 X107.559 Y113.443 E2.09226\n" +
"G1 X107.013 Y113.277 E2.10781\n" +
"G1 X106.850 Y113.245 E2.11233\n" +
"G1 X108.631 Y111.463 E2.18093\n" +
"G1 X108.002 Y111.186 E2.19967\n" +
"G1 X106.091 Y113.096 E2.27324\n" +
"G1 X105.333 Y112.947 E2.29429\n" +
"G1 X107.587 Y110.693 E2.38111\n" +
"G1 X107.432 Y109.941 E2.40202\n" +
"G1 X104.574 Y112.798 E2.51208\n" +
"G1 X103.816 Y112.650 E2.53313\n" +
"G1 X107.277 Y109.189 E2.66642\n" +
"G1 X107.193 Y108.652 E2.68120\n" +
"G1 X106.952 Y108.953 E2.69170\n" +
"G1 X106.361 Y109.540 E2.71440\n" +
"G1 X105.868 Y109.919 E2.73133\n" +
"G1 X105.281 Y110.276 E2.75003\n" +
"G1 X103.013 Y112.545 E2.83740\n" +
"G1 X102.188 Y112.463 E2.85998\n" +
"G1 X103.759 Y110.892 E2.92047\n" +
"G1 X103.277 Y111.028 E2.93410\n" +
"G1 X102.587 Y111.156 E2.95322\n" +
"G1 X101.362 Y112.381 E3.00039\n" +
"G1 X100.459 Y112.376 E3.02497\n" +
"G1 X101.567 Y111.269 E3.06760\n" +
"G1 X101.272 Y111.296 E3.07565\n" +
"G1 X100.633 Y111.295 E3.09306\n" +
"G1 X99.553 Y112.376 E3.13466\n" +
"G1 X98.642 Y112.379 E3.15947\n" +
"G1 X99.726 Y111.295 E3.20123\n" +
"G1 X99.147 Y111.294 E3.21701\n" +
"G1 X98.848 Y111.265 E3.22517\n" +
"G1 X97.689 Y112.425 E3.26982\n" +
"G1 X96.684 Y112.522 E3.29732\n" +
"G1 X98.027 Y111.179 E3.34906\n" +
"G1 X97.263 Y111.036 E3.37023\n" +
"G1 X95.648 Y112.651 E3.43243\n" +
"G1 X94.516 Y112.876 E3.46385\n" +
"G1 X96.551 Y110.840 E3.54224\n" +
"G1 X95.887 Y110.597 E3.56150\n" +
"G1 X93.384 Y113.100 E3.65789\n" +
"G1 X92.491 Y113.277 E3.68269\n" +
"G1 X92.215 Y113.361 E3.69053\n" +
"G1 X95.271 Y110.305 E3.80823\n" +
"G1 X94.703 Y109.966 E3.82625\n" +
"G1 X91.369 Y113.301 E3.95466\n" +
"G1 X91.201 Y112.560 E3.97532\n" +
"G1 X92.709 Y111.053 E4.03338\n" +
"G1 X92.833 Y110.992 E4.03714\n" +
"G1 X92.851 Y110.911 E4.03941\n" +
"G1 X94.183 Y109.579 E4.09072\n" +
"G1 X93.713 Y109.142 E4.10821\n" +
"G1 X92.610 Y110.244 E4.15067\n" +
"G1 X92.784 Y109.361 E4.17518\n" +
"G1 X92.810 Y109.137 E4.18132\n" +
"G1 X93.304 Y108.643 E4.20036\n" +
"G1 E2.20036 F2400.00000\n" +
"G92 E0\n" +
"G1 X35.575 Y113.304 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F1200\n" +
"G1 X36.069 Y112.809 E2.01938\n" +
"G1 X36.112 Y111.843 E2.04621\n" +
"G1 X34.850 Y113.105 E2.09568\n" +
"G1 X34.135 Y112.896 E2.11632\n" +
"G1 X35.609 Y111.422 E2.17409\n" +
"G1 X34.884 Y111.224 E2.19492\n" +
"G1 X33.407 Y112.701 E2.25283\n" +
"G1 X32.677 Y112.508 E2.27376\n" +
"G1 X34.608 Y110.576 E2.34947\n" +
"G1 X34.342 Y109.919 E2.36913\n" +
"G1 X31.946 Y112.315 E2.46305\n" +
"G1 X31.216 Y112.122 E2.48399\n" +
"G1 X34.076 Y109.261 E2.59612\n" +
"G1 X33.868 Y108.546 E2.61676\n" +
"G1 X30.485 Y111.929 E2.74934\n" +
"G1 X29.755 Y111.736 E2.77028\n" +
"G1 X33.706 Y107.784 E2.92515\n" +
"G1 X33.582 Y106.985 E2.94757\n" +
"G1 X29.024 Y111.542 E3.12619\n" +
"G1 X28.532 Y111.412 E3.14031\n" +
"G1 X28.553 Y111.091 E3.14925\n" +
"G1 X33.530 Y106.114 E3.34432\n" +
"G1 X33.490 Y105.230 E3.36885\n" +
"G1 X28.569 Y110.151 E3.56175\n" +
"G1 X28.585 Y109.212 E3.58779\n" +
"G1 X32.727 Y105.069 E3.75016\n" +
"G1 X32.166 Y105.134 E3.76582\n" +
"G1 X31.736 Y105.137 E3.77775\n" +
"G1 X28.601 Y108.272 E3.90063\n" +
"G1 X28.617 Y107.333 E3.92668\n" +
"G1 X30.866 Y105.084 E4.01482\n" +
"G1 X29.998 Y105.028 E4.03891\n" +
"G1 X28.633 Y106.393 E4.09242\n" +
"G1 X28.636 Y105.466 E4.11811\n" +
"G1 X29.130 Y104.972 E4.13749\n" +
"M106 S127.5\n" +
"G1 Z3.050 F7800.000\n" +
"G1 E2.13749 F2400.00000\n" +
"G92 E0\n" +
"G1 X41.653 Y85.067 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X42.742 Y83.992 E2.03153\n" +
"G1 X43.215 Y83.594 E2.04426\n" +
"G1 X43.668 Y83.280 E2.05563\n" +
"G1 X44.234 Y82.970 E2.06894\n" +
"G1 X45.039 Y82.634 E2.08691\n" +
"G1 X46.455 Y82.203 E2.11742\n" +
"G1 X48.823 Y81.725 E2.16721\n" +
"G1 X51.778 Y81.432 E2.22841\n" +
"G1 X58.379 Y81.379 E2.36447\n" +
"G1 X65.747 Y81.671 E2.51644\n" +
"G1 X71.410 Y82.412 E2.63414\n" +
"G1 X78.422 Y84.004 E2.78235\n" +
"G1 X80.448 Y84.639 E2.82610\n" +
"G1 X81.760 Y85.157 E2.85518\n" +
"G1 X82.599 Y85.573 E2.87447\n" +
"G1 X82.642 Y85.979 E2.88289\n" +
"G1 X82.667 Y86.072 E2.88488\n" +
"G1 X82.763 Y86.280 E2.88960\n" +
"G1 X82.897 Y86.457 E2.89418\n" +
"G1 X83.066 Y86.601 E2.89875\n" +
"G1 X83.261 Y86.705 E2.90332\n" +
"G1 X83.475 Y86.765 E2.90789\n" +
"G1 X83.800 Y86.767 E2.91458\n" +
"G1 X84.188 Y86.659 E2.92288\n" +
"G1 X84.605 Y87.002 E2.93401\n" +
"G1 X85.342 Y87.686 E2.95474\n" +
"G1 X85.965 Y88.388 E2.97407\n" +
"G1 X86.544 Y89.190 E2.99446\n" +
"G1 X87.052 Y90.068 E3.01537\n" +
"G1 X87.595 Y91.256 E3.04230\n" +
"G1 X87.982 Y92.194 E3.06321\n" +
"G1 X88.977 Y94.846 E3.12159\n" +
"G1 X89.327 Y95.819 E3.14291\n" +
"G1 X89.476 Y96.297 E3.15322\n" +
"G1 X90.822 Y101.588 E3.26575\n" +
"G1 X91.097 Y103.181 E3.29907\n" +
"G1 X91.243 Y104.295 E3.32223\n" +
"G1 X91.377 Y106.334 E3.36434\n" +
"G1 X91.347 Y107.707 E3.39265\n" +
"G1 X91.228 Y108.782 E3.41493\n" +
"G1 X90.986 Y109.981 E3.44015\n" +
"G1 X90.483 Y110.223 E3.45166\n" +
"G1 X90.158 Y110.470 E3.46006\n" +
"G1 X90.004 Y110.631 E3.46465\n" +
"G1 X89.767 Y110.976 E3.47329\n" +
"G1 X89.609 Y111.358 E3.48180\n" +
"G1 X89.532 Y111.764 E3.49031\n" +
"G1 X89.540 Y112.177 E3.49884\n" +
"G1 X89.664 Y112.724 E3.51040\n" +
"G1 X89.047 Y113.428 E3.52970\n" +
"G1 X88.461 Y113.971 E3.54616\n" +
"G1 X87.800 Y114.471 E3.56323\n" +
"G1 X87.068 Y114.917 E3.58090\n" +
"G1 X86.280 Y115.293 E3.59890\n" +
"G1 X85.032 Y115.745 E3.62625\n" +
"G1 X80.665 Y116.964 E3.71969\n" +
"G1 X77.701 Y117.539 E3.78193\n" +
"G1 X72.852 Y117.978 E3.88227\n" +
"G1 X65.192 Y118.022 E4.04015\n" +
"G1 X58.856 Y117.831 E4.17080\n" +
"G1 X53.006 Y117.141 E4.29220\n" +
"G1 X47.442 Y115.985 E4.40933\n" +
"G1 X42.598 Y114.571 E4.51334\n" +
"G1 X40.034 Y113.538 E4.57031\n" +
"G1 X38.314 Y112.682 E4.60991\n" +
"G1 X37.694 Y112.322 E4.62468\n" +
"G1 X37.715 Y111.930 E4.63276\n" +
"G1 X37.671 Y111.514 E4.64140\n" +
"G1 X37.541 Y111.115 E4.65004\n" +
"G1 X37.331 Y110.752 E4.65868\n" +
"G1 X37.050 Y110.441 E4.66732\n" +
"G1 X36.710 Y110.195 E4.67596\n" +
"G1 X36.327 Y110.026 E4.68460\n" +
"G1 X35.977 Y109.933 E4.69206\n" +
"G1 X35.546 Y108.869 E4.71573\n" +
"G1 X35.336 Y108.128 E4.73159\n" +
"G1 X35.178 Y107.229 E4.75041\n" +
"G1 X35.140 Y106.896 E4.75732\n" +
"G1 X35.066 Y105.248 E4.79132\n" +
"G1 X35.215 Y102.489 E4.84827\n" +
"G1 X35.819 Y98.515 E4.93111\n" +
"G1 X36.719 Y95.008 E5.00573\n" +
"G1 X38.086 Y91.320 E5.08680\n" +
"G1 X40.333 Y86.697 E5.19274\n" +
"G1 X40.782 Y86.776 E5.20215\n" +
"G1 X41.102 Y86.748 E5.20878\n" +
"G1 X41.291 Y86.678 E5.21292\n" +
"G1 X41.499 Y86.547 E5.21798\n" +
"G1 X41.651 Y86.393 E5.22246\n" +
"G1 X41.769 Y86.208 E5.22697\n" +
"G1 X41.865 Y85.902 E5.23358\n" +
"G1 X41.871 Y85.668 E5.23840\n" +
"G1 X41.791 Y85.350 E5.24517\n" +
"G1 X41.686 Y85.134 E5.25011\n" +
"G1 X41.352 Y84.668 F7800.000\n" +
"G1 E3.25011 F2400.00000\n" +
"G92 E0\n" +
"G1 X44.943 Y82.078 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X45.777 Y81.783 E2.02388\n" +
"G1 X46.881 Y81.514 E2.05453\n" +
"G1 X47.397 Y81.441 E2.06858\n" +
"G1 X46.318 Y81.659 E2.09828\n" +
"G1 X45.014 Y82.056 E2.13504\n" +
"G1 E0.13504 F2400.00000\n" +
"G92 E0\n" +
"G1 X42.364 Y83.577 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X40.973 Y84.950 E2.05273\n" +
"G1 X41.268 Y85.555 E2.07086\n" +
"G1 X41.309 Y85.692 E2.07474\n" +
"G1 X41.305 Y85.845 E2.07885\n" +
"G1 X41.210 Y86.041 E2.08474\n" +
"G1 X41.041 Y86.173 E2.09051\n" +
"G1 X40.941 Y86.209 E2.09338\n" +
"G1 X40.820 Y86.213 E2.09665\n" +
"G1 X40.022 Y86.072 E2.11850\n" +
"G1 X40.205 Y85.782 E2.12776\n" +
"G1 X40.759 Y85.065 E2.15221\n" +
"G1 X41.400 Y84.385 E2.17741\n" +
"G1 X42.150 Y83.725 E2.20436\n" +
"G1 X42.432 Y83.520 E2.21377\n" +
"G1 X42.422 Y83.529 E2.21414\n" +
"G1 E0.21414 F2400.00000\n" +
"G92 E0\n" +
"G1 X39.979 Y86.142 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X38.553 Y89.076 E2.08801\n" +
"G1 X38.991 Y87.975 E2.11997\n" +
"G1 X39.667 Y86.638 E2.16040\n" +
"G1 X39.939 Y86.205 E2.17419\n" +
"G1 E0.17419 F2400.00000\n" +
"G92 E0\n" +
"G1 X160.093 Y114.776 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.457 Y114.630 E2.01057\n" +
"G1 X162.254 Y113.736 E2.06472\n" +
"G1 X163.539 Y112.990 E2.10481\n" +
"G1 X163.488 Y111.846 E2.13568\n" +
"G1 X163.553 Y111.603 E2.14247\n" +
"G1 X163.637 Y111.458 E2.14701\n" +
"G1 X163.750 Y111.333 E2.15154\n" +
"G1 X163.961 Y111.196 E2.15833\n" +
"G1 X164.908 Y110.937 E2.18481\n" +
"G1 X165.587 Y109.257 E2.23368\n" +
"G1 X165.831 Y108.396 E2.25782\n" +
"G1 X166.007 Y107.400 E2.28511\n" +
"G1 X166.053 Y106.990 E2.29624\n" +
"G1 X166.131 Y105.243 E2.34343\n" +
"G1 X166.045 Y103.646 E2.38657\n" +
"G1 X166.313 Y103.990 E2.39833\n" +
"G1 X166.673 Y104.302 E2.41119\n" +
"G1 X167.123 Y104.553 E2.42509\n" +
"G1 X167.790 Y104.722 E2.44365\n" +
"G1 X168.447 Y104.752 E2.46138\n" +
"G1 X171.192 Y104.575 E2.53559\n" +
"G1 X171.759 Y104.555 E2.55090\n" +
"G1 X171.750 Y105.558 E2.57796\n" +
"G1 X171.840 Y110.964 E2.72382\n" +
"G1 X171.896 Y111.644 E2.74222\n" +
"G1 X160.166 Y114.757 E3.06962\n" +
"G1 E1.06962 F2400.00000\n" +
"G92 E0\n" +
"G1 X110.573 Y114.707 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X107.056 Y113.635 E2.09917\n" +
"G1 X103.721 Y112.983 E2.19084\n" +
"G1 X101.589 Y112.770 E2.24864\n" +
"G1 X100.058 Y112.763 E2.28995\n" +
"G1 X98.411 Y112.769 E2.33439\n" +
"G1 X96.279 Y112.983 E2.39220\n" +
"G1 X92.944 Y113.635 E2.48386\n" +
"G1 X89.427 Y114.707 E2.58304\n" +
"G1 X89.905 Y114.264 E2.60061\n" +
"G1 X90.965 Y113.055 E2.64398\n" +
"G1 X90.734 Y112.032 E2.67227\n" +
"G1 X90.730 Y111.864 E2.67680\n" +
"G1 X90.761 Y111.702 E2.68124\n" +
"G1 X90.824 Y111.550 E2.68568\n" +
"G1 X90.936 Y111.387 E2.69101\n" +
"G1 X91.110 Y111.249 E2.69701\n" +
"G1 X92.032 Y110.806 E2.72461\n" +
"G1 X92.346 Y109.353 E2.76469\n" +
"G1 X92.414 Y108.946 E2.77584\n" +
"G1 X92.541 Y107.787 E2.80730\n" +
"G1 X92.568 Y106.598 E2.83936\n" +
"G1 X93.147 Y107.960 E2.87925\n" +
"G1 X93.437 Y108.479 E2.89532\n" +
"G1 X93.870 Y109.009 E2.91376\n" +
"G1 X94.273 Y109.383 E2.92860\n" +
"G1 X94.696 Y109.708 E2.94299\n" +
"G1 X95.173 Y110.006 E2.95816\n" +
"G1 X95.754 Y110.293 E2.97563\n" +
"G1 X96.372 Y110.529 E2.99350\n" +
"G1 X97.097 Y110.733 E3.01381\n" +
"G1 X97.946 Y110.891 E3.03711\n" +
"G1 X98.940 Y110.987 E3.06404\n" +
"G1 X101.060 Y110.987 E3.12124\n" +
"G1 X102.054 Y110.891 E3.14817\n" +
"G1 X102.903 Y110.733 E3.17147\n" +
"G1 X103.628 Y110.529 E3.19178\n" +
"G1 X104.246 Y110.293 E3.20965\n" +
"G1 X104.827 Y110.006 E3.22712\n" +
"G1 X105.304 Y109.708 E3.24229\n" +
"G1 X105.733 Y109.379 E3.25687\n" +
"G1 X106.190 Y108.946 E3.27386\n" +
"G1 X106.452 Y108.629 E3.28495\n" +
"G1 X106.676 Y108.294 E3.29582\n" +
"G1 X106.864 Y107.938 E3.30669\n" +
"G1 X107.432 Y106.603 E3.34582\n" +
"G1 X107.459 Y107.789 E3.37780\n" +
"G1 X107.603 Y109.062 E3.41238\n" +
"G1 X107.961 Y110.807 E3.46044\n" +
"G1 X108.892 Y111.248 E3.48823\n" +
"G1 X109.115 Y111.446 E3.49628\n" +
"G1 X109.211 Y111.625 E3.50175\n" +
"G1 X109.259 Y111.782 E3.50619\n" +
"G1 X109.276 Y111.985 E3.51167\n" +
"G1 X109.035 Y113.055 E3.54126\n" +
"G1 X110.095 Y114.264 E3.58463\n" +
"G1 X110.518 Y114.656 E3.60018\n" +
"G1 E1.60018 F2400.00000\n" +
"G92 E0\n" +
"G1 X39.907 Y114.776 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X28.104 Y111.644 E2.32942\n" +
"G1 X28.160 Y110.964 E2.34782\n" +
"G1 X28.250 Y105.562 E2.49358\n" +
"G1 X28.246 Y104.555 E2.52074\n" +
"G1 X28.736 Y104.571 E2.53397\n" +
"G1 X31.530 Y104.751 E2.60950\n" +
"G1 X31.886 Y104.746 E2.61911\n" +
"G1 X32.444 Y104.680 E2.63425\n" +
"G1 X32.914 Y104.532 E2.64753\n" +
"G1 X33.359 Y104.285 E2.66128\n" +
"G1 X33.836 Y103.827 E2.67911\n" +
"G1 X33.955 Y103.636 E2.68519\n" +
"G1 X33.869 Y105.243 E2.72859\n" +
"G1 X33.947 Y106.990 E2.77578\n" +
"G1 X33.993 Y107.400 E2.78691\n" +
"G1 X34.169 Y108.396 E2.81420\n" +
"G1 X34.413 Y109.257 E2.83835\n" +
"G1 X35.092 Y110.937 E2.88721\n" +
"G1 X36.039 Y111.196 E2.91369\n" +
"G1 X36.250 Y111.333 E2.92048\n" +
"G1 X36.363 Y111.458 E2.92501\n" +
"G1 X36.477 Y111.682 E2.93180\n" +
"G1 X36.512 Y111.846 E2.93634\n" +
"G1 X36.461 Y112.990 E2.96721\n" +
"G1 X37.746 Y113.736 E3.00730\n" +
"G1 X39.543 Y114.630 E3.06145\n" +
"G1 X39.837 Y114.748 E3.07000\n" +
"G1 E1.07000 F2400.00000\n" +
"G92 E0\n" +
"G1 X27.428 Y112.115 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3600\n" +
"G1 X27.525 Y110.933 E2.03200\n" +
"G1 X27.615 Y105.558 E2.17702\n" +
"G1 X27.608 Y103.943 E2.22059\n" +
"G1 X27.813 Y103.924 E2.22614\n" +
"G1 X28.343 Y103.918 E2.24045\n" +
"G1 X31.546 Y104.115 E2.32700\n" +
"G1 X31.845 Y104.110 E2.33506\n" +
"G1 X32.310 Y104.056 E2.34771\n" +
"G1 X32.661 Y103.945 E2.35763\n" +
"G1 X32.978 Y103.769 E2.36741\n" +
"G1 X33.339 Y103.423 E2.38091\n" +
"G1 X33.670 Y102.895 E2.39773\n" +
"G1 X33.954 Y102.267 E2.41632\n" +
"G1 X35.360 Y98.052 E2.53617\n" +
"G1 X35.269 Y98.403 E2.54595\n" +
"G1 X34.657 Y102.431 E2.65587\n" +
"G1 X34.505 Y105.246 E2.73190\n" +
"G1 X34.581 Y106.940 E2.77766\n" +
"G1 X34.622 Y107.309 E2.78768\n" +
"G1 X34.789 Y108.254 E2.81356\n" +
"G1 X35.015 Y109.051 E2.83590\n" +
"G1 X35.562 Y110.404 E2.87527\n" +
"G1 X36.155 Y110.560 E2.89181\n" +
"G1 X36.431 Y110.682 E2.89994\n" +
"G1 X36.675 Y110.859 E2.90808\n" +
"G1 X36.877 Y111.083 E2.91621\n" +
"G1 X36.960 Y111.209 E2.92028\n" +
"G1 X37.083 Y111.485 E2.92842\n" +
"G1 X37.145 Y111.780 E2.93655\n" +
"G1 X37.153 Y111.930 E2.94062\n" +
"G1 X37.116 Y112.635 E2.95966\n" +
"G1 X38.048 Y113.176 E2.98871\n" +
"G1 X39.804 Y114.050 E3.04164\n" +
"G1 X42.414 Y115.101 E3.11754\n" +
"G1 X47.306 Y116.529 E3.25502\n" +
"G1 X52.916 Y117.695 E3.40959\n" +
"G1 X58.815 Y118.390 E3.56982\n" +
"G1 X65.185 Y118.583 E3.74175\n" +
"G1 X72.879 Y118.538 E3.94932\n" +
"G1 X77.780 Y118.095 E4.08205\n" +
"G1 X80.794 Y117.510 E4.16489\n" +
"G1 X85.203 Y116.279 E4.28838\n" +
"G1 X86.497 Y115.811 E4.32549\n" +
"G1 X87.335 Y115.410 E4.35055\n" +
"G1 X88.116 Y114.935 E4.37522\n" +
"G1 X88.821 Y114.401 E4.39907\n" +
"G1 X89.449 Y113.820 E4.42216\n" +
"G1 X90.273 Y112.879 E4.45590\n" +
"G1 X90.099 Y112.109 E4.47720\n" +
"G1 X90.093 Y111.811 E4.48525\n" +
"G1 X90.149 Y111.519 E4.49325\n" +
"G1 X90.262 Y111.245 E4.50125\n" +
"G1 X90.441 Y110.985 E4.50976\n" +
"G1 X90.532 Y110.889 E4.51334\n" +
"G1 X90.777 Y110.704 E4.52161\n" +
"G1 X91.476 Y110.368 E4.54255\n" +
"G1 X91.721 Y109.234 E4.57384\n" +
"G1 X91.784 Y108.859 E4.58410\n" +
"G1 X91.906 Y107.745 E4.61434\n" +
"G1 X91.938 Y106.322 E4.65273\n" +
"G1 X91.801 Y104.240 E4.70900\n" +
"G1 X91.651 Y103.097 E4.74010\n" +
"G1 X91.372 Y101.478 E4.78444\n" +
"G1 X90.016 Y96.144 E4.93291\n" +
"G1 X89.859 Y95.641 E4.94712\n" +
"G1 X88.504 Y91.989 E5.05221\n" +
"G1 X88.109 Y91.033 E5.08010\n" +
"G1 X87.551 Y89.810 E5.11637\n" +
"G1 X87.015 Y88.884 E5.14522\n" +
"G1 X86.403 Y88.037 E5.17343\n" +
"G1 X85.743 Y87.294 E5.20023\n" +
"G1 X84.974 Y86.580 E5.22855\n" +
"G1 X84.319 Y86.040 E5.25143\n" +
"G1 X83.689 Y86.215 E5.26906\n" +
"G1 X83.471 Y86.182 E5.27502\n" +
"G1 X83.307 Y86.070 E5.28036\n" +
"G1 X83.195 Y85.875 E5.28644\n" +
"G1 X83.124 Y85.207 E5.30454\n" +
"G1 X81.988 Y84.644 E5.33875\n" +
"G1 X80.635 Y84.110 E5.37799\n" +
"G1 X78.568 Y83.462 E5.43642\n" +
"G1 X71.508 Y81.859 E5.63172\n" +
"G1 X65.795 Y81.112 E5.78716\n" +
"G1 X63.468 Y81.020 E5.84999\n" +
"G1 X64.911 Y81.030 E5.88891\n" +
"G1 X70.888 Y81.475 E6.05059\n" +
"G1 X76.483 Y82.379 E6.20348\n" +
"G1 X79.637 Y83.177 E6.29125\n" +
"G1 X81.278 Y83.764 E6.33828\n" +
"G1 X82.665 Y84.424 E6.37971\n" +
"G1 X84.135 Y85.304 E6.42594\n" +
"G1 X85.076 Y85.989 E6.45733\n" +
"G1 X85.856 Y86.677 E6.48539\n" +
"G1 X86.439 Y87.298 E6.50837\n" +
"G1 X86.960 Y87.978 E6.53149\n" +
"G1 X87.535 Y88.912 E6.56106\n" +
"G1 X88.229 Y90.341 E6.60391\n" +
"G1 X88.700 Y91.501 E6.63770\n" +
"G1 X89.810 Y94.644 E6.72760\n" +
"G1 X90.799 Y97.792 E6.81663\n" +
"G1 X91.778 Y101.152 E6.91103\n" +
"G1 X92.240 Y102.861 E6.95880\n" +
"G1 X92.685 Y104.835 E7.01339\n" +
"G1 X92.867 Y105.519 E7.03247\n" +
"G1 X93.079 Y106.167 E7.05087\n" +
"G1 X93.360 Y106.859 E7.07101\n" +
"G1 X93.717 Y107.677 E7.09508\n" +
"G1 X93.965 Y108.121 E7.10880\n" +
"G1 X94.334 Y108.573 E7.12456\n" +
"G1 X94.683 Y108.897 E7.13741\n" +
"G1 X95.058 Y109.185 E7.15016\n" +
"G1 X95.483 Y109.450 E7.16366\n" +
"G1 X96.008 Y109.710 E7.17947\n" +
"G1 X96.572 Y109.925 E7.19575\n" +
"G1 X97.242 Y110.113 E7.21452\n" +
"G1 X98.035 Y110.261 E7.23629\n" +
"G1 X98.970 Y110.351 E7.26163\n" +
"G1 X101.030 Y110.351 E7.31719\n" +
"G1 X101.965 Y110.261 E7.34254\n" +
"G1 X102.758 Y110.113 E7.36431\n" +
"G1 X103.428 Y109.925 E7.38308\n" +
"G1 X103.992 Y109.710 E7.39935\n" +
"G1 X104.517 Y109.450 E7.41516\n" +
"G1 X104.942 Y109.185 E7.42866\n" +
"G1 X105.320 Y108.895 E7.44152\n" +
"G1 X105.725 Y108.511 E7.45658\n" +
"G1 X105.942 Y108.250 E7.46574\n" +
"G1 X106.129 Y107.969 E7.47484\n" +
"G1 X106.289 Y107.664 E7.48412\n" +
"G1 X106.852 Y106.345 E7.52282\n" +
"G1 X107.140 Y105.495 E7.54702\n" +
"G1 X107.315 Y104.834 E7.56548\n" +
"G1 X107.760 Y102.861 E7.62003\n" +
"G1 X108.223 Y101.151 E7.66782\n" +
"G1 X109.097 Y98.132 E7.75262\n" +
"G1 X110.191 Y94.642 E7.85128\n" +
"G1 X110.540 Y93.609 E7.88069\n" +
"G1 X111.272 Y91.577 E7.93894\n" +
"G1 X111.741 Y90.406 E7.97299\n" +
"G1 X112.465 Y88.912 E8.01777\n" +
"G1 X113.047 Y87.967 E8.04771\n" +
"G1 X113.554 Y87.306 E8.07018\n" +
"G1 X114.163 Y86.657 E8.09420\n" +
"G1 X114.898 Y86.010 E8.12060\n" +
"G1 X115.912 Y85.272 E8.15445\n" +
"G1 X117.334 Y84.424 E8.19911\n" +
"G1 X118.722 Y83.764 E8.24055\n" +
"G1 X120.393 Y83.168 E8.28842\n" +
"G1 X123.517 Y82.379 E8.37535\n" +
"G1 X129.054 Y81.482 E8.52665\n" +
"G1 X135.154 Y81.027 E8.69168\n" +
"G1 X136.619 Y81.016 E8.73118\n" +
"G1 X134.205 Y81.112 E8.79634\n" +
"G1 X128.492 Y81.859 E8.95178\n" +
"G1 X121.432 Y83.462 E9.14708\n" +
"G1 X119.365 Y84.110 E9.20551\n" +
"G1 X118.012 Y84.644 E9.24476\n" +
"G1 X116.876 Y85.207 E9.27896\n" +
"G1 X116.804 Y85.880 E9.29719\n" +
"G1 X116.753 Y85.991 E9.30050\n" +
"G1 X116.617 Y86.135 E9.30584\n" +
"G1 X116.433 Y86.208 E9.31118\n" +
"G1 X116.311 Y86.215 E9.31448\n" +
"G1 X115.681 Y86.040 E9.33211\n" +
"G1 X115.026 Y86.580 E9.35500\n" +
"G1 X114.257 Y87.294 E9.38332\n" +
"G1 X113.597 Y88.037 E9.41011\n" +
"G1 X112.985 Y88.884 E9.43832\n" +
"G1 X112.449 Y89.810 E9.46718\n" +
"G1 X111.891 Y91.033 E9.50344\n" +
"G1 X111.487 Y92.013 E9.53204\n" +
"G1 X110.345 Y95.066 E9.61996\n" +
"G1 X109.986 Y96.135 E9.65039\n" +
"G1 X108.628 Y101.476 E9.79906\n" +
"G1 X108.392 Y102.821 E9.83589\n" +
"G1 X108.254 Y103.751 E9.86125\n" +
"G1 X108.185 Y104.378 E9.87828\n" +
"G1 X108.062 Y106.316 E9.93066\n" +
"G1 X108.094 Y107.746 E9.96924\n" +
"G1 X108.231 Y108.963 E10.00228\n" +
"G1 X108.519 Y110.369 E10.04100\n" +
"G1 X109.206 Y110.693 E10.06147\n" +
"G1 X109.346 Y110.787 E10.06602\n" +
"G1 X109.586 Y111.016 E10.07497\n" +
"G1 X109.802 Y111.379 E10.08637\n" +
"G1 X109.887 Y111.664 E10.09437\n" +
"G1 X109.913 Y111.981 E10.10296\n" +
"G1 X109.905 Y112.088 E10.10586\n" +
"G1 X109.727 Y112.879 E10.12775\n" +
"G1 X110.551 Y113.820 E10.16149\n" +
"G1 X111.179 Y114.401 E10.18457\n" +
"G1 X111.884 Y114.935 E10.20843\n" +
"G1 X112.665 Y115.410 E10.23309\n" +
"G1 X113.503 Y115.811 E10.25816\n" +
"G1 X114.797 Y116.279 E10.29527\n" +
"G1 X119.206 Y117.510 E10.41876\n" +
"G1 X122.220 Y118.095 E10.50160\n" +
"G1 X127.121 Y118.538 E10.63433\n" +
"G1 X134.815 Y118.583 E10.84190\n" +
"G1 X141.185 Y118.390 E11.01383\n" +
"G1 X147.084 Y117.695 E11.17406\n" +
"G1 X152.694 Y116.529 E11.32863\n" +
"G1 X157.586 Y115.101 E11.46611\n" +
"G1 X160.196 Y114.050 E11.54201\n" +
"G1 X161.952 Y113.176 E11.59493\n" +
"G1 X162.884 Y112.635 E11.62399\n" +
"G1 X162.847 Y111.930 E11.64302\n" +
"G1 X162.855 Y111.780 E11.64709\n" +
"G1 X162.917 Y111.485 E11.65523\n" +
"G1 X163.040 Y111.209 E11.66336\n" +
"G1 X163.123 Y111.083 E11.66744\n" +
"G1 X163.325 Y110.859 E11.67557\n" +
"G1 X163.569 Y110.682 E11.68371\n" +
"G1 X163.845 Y110.560 E11.69184\n" +
"G1 X164.438 Y110.404 E11.70838\n" +
"G1 X164.985 Y109.051 E11.74774\n" +
"G1 X165.211 Y108.254 E11.77009\n" +
"G1 X165.377 Y107.309 E11.79597\n" +
"G1 X165.419 Y106.940 E11.80599\n" +
"G1 X165.495 Y105.246 E11.85175\n" +
"G1 X165.343 Y102.431 E11.92777\n" +
"G1 X164.731 Y98.403 E12.03770\n" +
"G1 X164.639 Y98.047 E12.04760\n" +
"G1 X166.046 Y102.266 E12.16758\n" +
"G1 X166.322 Y102.878 E12.18567\n" +
"G1 X166.524 Y103.226 E12.19653\n" +
"G1 X166.776 Y103.551 E12.20762\n" +
"G1 X167.039 Y103.779 E12.21702\n" +
"G1 X167.360 Y103.957 E12.22691\n" +
"G1 X167.884 Y104.090 E12.24150\n" +
"G1 X168.441 Y104.116 E12.25654\n" +
"G1 X171.158 Y103.940 E12.32998\n" +
"G1 X171.657 Y103.918 E12.34347\n" +
"G1 X172.218 Y103.924 E12.35860\n" +
"G1 X172.397 Y103.943 E12.36345\n" +
"G1 X172.385 Y105.556 E12.40697\n" +
"G1 X172.475 Y110.933 E12.55205\n" +
"G1 X172.572 Y112.110 E12.58390\n" +
"G1 X158.960 Y115.734 E12.96389\n" +
"G1 X150.859 Y117.363 E13.18682\n" +
"G1 X144.128 Y118.428 E13.37065\n" +
"G1 X135.366 Y119.081 E13.60766\n" +
"G1 X127.200 Y118.984 E13.82798\n" +
"G1 X121.823 Y118.322 E13.97412\n" +
"G1 X115.590 Y116.998 E14.14601\n" +
"G1 X110.395 Y115.317 E14.29333\n" +
"G1 X106.902 Y114.252 E14.39182\n" +
"G1 X103.629 Y113.613 E14.48180\n" +
"G1 X101.556 Y113.405 E14.53798\n" +
"G1 X100.058 Y113.399 E14.57841\n" +
"G1 X98.444 Y113.405 E14.62195\n" +
"G1 X96.371 Y113.613 E14.67814\n" +
"G1 X93.098 Y114.252 E14.76812\n" +
"G1 X89.605 Y115.317 E14.86661\n" +
"G1 X84.410 Y116.998 E15.01393\n" +
"G1 X78.177 Y118.322 E15.18582\n" +
"G1 X72.800 Y118.984 E15.33196\n" +
"G1 X64.634 Y119.081 E15.55228\n" +
"G1 X55.872 Y118.428 E15.78928\n" +
"G1 X49.061 Y117.347 E15.97532\n" +
"G1 X41.040 Y115.734 E16.19605\n" +
"G1 X27.499 Y112.138 E16.57399\n" +
"G1 E14.57399 F2400.00000\n" +
"G92 E0\n" +
"G1 X158.347 Y85.067 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X158.209 Y85.350 E2.00649\n" +
"G1 X158.129 Y85.668 E2.01325\n" +
"G1 X158.135 Y85.895 E2.01794\n" +
"G1 X158.229 Y86.204 E2.02458\n" +
"G1 X158.353 Y86.399 E2.02935\n" +
"G1 X158.497 Y86.545 E2.03357\n" +
"G1 X158.687 Y86.668 E2.03824\n" +
"G1 X158.898 Y86.748 E2.04291\n" +
"G1 X159.218 Y86.776 E2.04952\n" +
"G1 X159.667 Y86.697 E2.05893\n" +
"G1 X161.914 Y91.320 E2.16487\n" +
"G1 X163.281 Y95.008 E2.24594\n" +
"G1 X164.181 Y98.515 E2.32055\n" +
"G1 X164.785 Y102.489 E2.40340\n" +
"G1 X164.934 Y105.248 E2.46035\n" +
"G1 X164.860 Y106.896 E2.49435\n" +
"G1 X164.822 Y107.229 E2.50126\n" +
"G1 X164.664 Y108.128 E2.52008\n" +
"G1 X164.454 Y108.869 E2.53594\n" +
"G1 X164.023 Y109.933 E2.55961\n" +
"G1 X163.673 Y110.026 E2.56707\n" +
"G1 X163.290 Y110.195 E2.57571\n" +
"G1 X162.950 Y110.441 E2.58435\n" +
"G1 X162.669 Y110.752 E2.59299\n" +
"G1 X162.459 Y111.115 E2.60163\n" +
"G1 X162.329 Y111.514 E2.61027\n" +
"G1 X162.285 Y111.930 E2.61891\n" +
"G1 X162.306 Y112.322 E2.62699\n" +
"G1 X161.686 Y112.682 E2.64176\n" +
"G1 X159.966 Y113.538 E2.68136\n" +
"G1 X157.402 Y114.571 E2.73833\n" +
"G1 X152.558 Y115.985 E2.84233\n" +
"G1 X146.994 Y117.141 E2.95946\n" +
"G1 X141.144 Y117.831 E3.08087\n" +
"G1 X134.808 Y118.022 E3.21152\n" +
"G1 X127.148 Y117.978 E3.36940\n" +
"G1 X122.299 Y117.539 E3.46974\n" +
"G1 X119.335 Y116.964 E3.53197\n" +
"G1 X114.968 Y115.745 E3.62542\n" +
"G1 X113.720 Y115.293 E3.65277\n" +
"G1 X112.932 Y114.917 E3.67077\n" +
"G1 X112.200 Y114.471 E3.68844\n" +
"G1 X111.539 Y113.971 E3.70551\n" +
"G1 X110.953 Y113.428 E3.72197\n" +
"G1 X110.336 Y112.724 E3.74126\n" +
"G1 X110.462 Y112.170 E3.75298\n" +
"G1 X110.475 Y111.977 E3.75696\n" +
"G1 X110.440 Y111.559 E3.76562\n" +
"G1 X110.322 Y111.163 E3.77413\n" +
"G1 X110.122 Y110.794 E3.78279\n" +
"G1 X110.005 Y110.641 E3.78676\n" +
"G1 X109.698 Y110.347 E3.79551\n" +
"G1 X109.482 Y110.204 E3.80085\n" +
"G1 X109.012 Y109.982 E3.81156\n" +
"G1 X108.786 Y108.875 E3.83485\n" +
"G1 X108.653 Y107.708 E3.85905\n" +
"G1 X108.623 Y106.328 E3.88751\n" +
"G1 X108.743 Y104.427 E3.92676\n" +
"G1 X108.810 Y103.823 E3.93929\n" +
"G1 X108.945 Y102.911 E3.95829\n" +
"G1 X109.178 Y101.588 E3.98597\n" +
"G1 X110.524 Y96.294 E4.09855\n" +
"G1 X110.874 Y95.253 E4.12119\n" +
"G1 X112.008 Y92.218 E4.18797\n" +
"G1 X112.405 Y91.256 E4.20941\n" +
"G1 X112.948 Y90.068 E4.23634\n" +
"G1 X113.456 Y89.190 E4.25725\n" +
"G1 X114.035 Y88.388 E4.27764\n" +
"G1 X114.658 Y87.686 E4.29697\n" +
"G1 X115.395 Y87.002 E4.31770\n" +
"G1 X115.812 Y86.659 E4.32883\n" +
"G1 X116.200 Y86.767 E4.33713\n" +
"G1 X116.525 Y86.765 E4.34382\n" +
"G1 X116.739 Y86.705 E4.34839\n" +
"G1 X116.934 Y86.601 E4.35296\n" +
"G1 X117.103 Y86.457 E4.35754\n" +
"G1 X117.237 Y86.280 E4.36211\n" +
"G1 X117.333 Y86.072 E4.36684\n" +
"G1 X117.358 Y85.979 E4.36882\n" +
"G1 X117.401 Y85.573 E4.37724\n" +
"G1 X118.240 Y85.157 E4.39653\n" +
"G1 X119.552 Y84.639 E4.42561\n" +
"G1 X121.578 Y84.004 E4.46937\n" +
"G1 X128.590 Y82.412 E4.61757\n" +
"G1 X134.253 Y81.671 E4.73527\n" +
"G1 X141.621 Y81.379 E4.88725\n" +
"G1 X148.222 Y81.432 E5.02330\n" +
"G1 X151.177 Y81.725 E5.08451\n" +
"G1 X153.545 Y82.203 E5.13429\n" +
"G1 X154.961 Y82.634 E5.16480\n" +
"G1 X155.766 Y82.970 E5.18277\n" +
"G1 X156.332 Y83.280 E5.19609\n" +
"G1 X156.785 Y83.594 E5.20745\n" +
"G1 X157.258 Y83.992 E5.22018\n" +
"G1 X158.293 Y85.014 E5.25017\n" +
"G1 X158.614 Y85.232 F7800.000\n" +
"G1 E3.25017 F2400.00000\n" +
"G92 E0\n" +
"G1 X155.015 Y82.065 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X153.682 Y81.659 E2.03759\n" +
"G1 X152.603 Y81.441 E2.06729\n" +
"G1 X153.118 Y81.514 E2.08133\n" +
"G1 X154.236 Y81.787 E2.11238\n" +
"G1 X154.944 Y82.040 E2.13266\n" +
"G1 E0.13266 F2400.00000\n" +
"G92 E0\n" +
"G1 X157.585 Y83.534 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X157.872 Y83.744 E2.00960\n" +
"G1 X158.600 Y84.385 E2.03576\n" +
"G1 X159.241 Y85.065 E2.06097\n" +
"G1 X159.795 Y85.782 E2.08541\n" +
"G1 X159.978 Y86.072 E2.09468\n" +
"G1 X159.180 Y86.213 E2.11653\n" +
"G1 X159.054 Y86.208 E2.11992\n" +
"G1 X158.941 Y86.165 E2.12318\n" +
"G1 X158.820 Y86.081 E2.12717\n" +
"G1 X158.718 Y85.918 E2.13235\n" +
"G1 X158.691 Y85.692 E2.13849\n" +
"G1 X158.732 Y85.555 E2.14236\n" +
"G1 X159.027 Y84.950 E2.16049\n" +
"G1 X157.641 Y83.583 E2.21301\n" +
"G1 E0.21301 F2400.00000\n" +
"G92 E0\n" +
"G1 X160.021 Y86.142 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F900\n" +
"G1 X160.333 Y86.638 E2.01581\n" +
"G1 X161.009 Y87.975 E2.05624\n" +
"G1 X161.446 Y89.074 E2.08814\n" +
"G1 X160.054 Y86.209 E2.17407\n" +
"G1 E0.17407 F2400.00000\n" +
"G92 E0\n" +
"G1 X172.870 Y103.515 F7800.000\n" +
"G1 E2.00000 F2400.00000\n" +
"G1 F3000\n" +
"G1 X172.930 Y103.630 E2.00268\n" +
"G1 X172.954 Y103.786 E2.00592\n" +
"G1 X172.946 Y105.554 E2.04235\n" +
"G1 X173.035 Y110.905 E2.15266\n" +
"G1 X173.154 Y112.343 E2.18239\n" +
"G1 X173.142 Y112.430 E2.18421\n" +
"G1 X173.080 Y112.503 E2.18619\n" +
"G1 X172.957 Y112.570 E2.18906\n" +
"G1 X172.578 Y112.701 E2.19732\n" +
"G1 X159.088 Y116.281 E2.48499\n" +
"G1 X150.958 Y117.914 E2.65590\n" +
"G1 X144.192 Y118.985 E2.79707\n" +
"G1 X135.384 Y119.642 E2.97912\n" +
"G1 X127.162 Y119.544 E3.14859\n" +
"G1 X121.731 Y118.876 E3.26138\n" +
"G1 X115.445 Y117.540 E3.39381\n" +
"G1 X110.227 Y115.852 E3.50686\n" +
"G1 X106.766 Y114.797 E3.58141\n" +
"G1 X103.547 Y114.168 E3.64903\n" +
"G1 X101.527 Y113.965 E3.69086\n" +
"G1 X100.058 Y113.959 E3.72114\n" +
"G1 X98.473 Y113.965 E3.75381\n" +
"G1 X96.453 Y114.168 E3.79565\n" +
"G1 X93.233 Y114.797 E3.86326\n" +
"G1 X89.773 Y115.852 E3.93781\n" +
"G1 X84.555 Y117.540 E4.05087\n" +
"G1 X78.269 Y118.876 E4.18329\n" +
"G1 X72.838 Y119.544 E4.29609\n" +
"G1 X64.616 Y119.642 E4.46555\n" +
"G1 X55.808 Y118.985 E4.64760\n" +
"G1 X48.962 Y117.899 E4.79045\n" +
"G1 X40.912 Y116.281 E4.95968\n" +
"G1 X27.418 Y112.700 E5.24743\n" +
"G1 X27.200 Y112.631 E5.25214\n" +
"G1 X26.928 Y112.512 E5.25827\n" +
"G1 X26.858 Y112.430 E5.26048\n" +
"G1 X26.846 Y112.343 E5.26229\n" +
"G1 X26.965 Y110.905 E5.29202\n" +
"G1 X27.054 Y105.555 E5.40232\n" +
"G1 X27.047 Y103.789 E5.43872\n" +
"G1 X27.070 Y103.630 E5.44202\n" +
"G1 X27.130 Y103.516 E5.44466\n" +
"G1 X27.248 Y103.441 E5.44756\n" +
"G1 X27.440 Y103.396 E5.45162\n" +
"G1 X27.784 Y103.363 E5.45873\n" +
"G1 X28.352 Y103.358 E5.47045\n" +
"G1 X31.560 Y103.554 E5.53668\n" +
"G1 X31.808 Y103.550 E5.54180\n" +
"G1 X32.192 Y103.505 E5.54977\n" +
"G1 X32.438 Y103.428 E5.55509\n" +
"G1 X32.642 Y103.314 E5.55990\n" +
"G1 X32.901 Y103.066 E5.56728\n" +
"G1 X33.175 Y102.629 E5.57792\n" +
"G1 X33.432 Y102.061 E5.59077\n" +
"G1 X34.071 Y100.174 E5.63184\n" +
"G1 X36.645 Y92.353 E5.80153\n" +
"G1 X38.479 Y87.745 E5.90376\n" +
"G1 X39.179 Y86.362 E5.93570\n" +
"G1 X39.745 Y85.460 E5.95763\n" +
"G1 X40.332 Y84.700 E5.97743\n" +
"G1 X41.010 Y83.981 E5.99780\n" +
"G1 X41.799 Y83.287 E6.01946\n" +
"G1 X42.734 Y82.607 E6.04328\n" +
"G1 X43.694 Y82.031 E6.06636\n" +
"G1 X44.570 Y81.615 E6.08635\n" +
"G1 X45.617 Y81.245 E6.10924\n" +
"G1 X46.775 Y80.963 E6.13380\n" +
"G1 X48.920 Y80.657 E6.17845\n" +
"G1 X53.961 Y80.394 E6.28248\n" +
"G1 X64.933 Y80.469 E6.50864\n" +
"G1 X70.953 Y80.917 E6.63305\n" +
"G1 X76.596 Y81.829 E6.75087\n" +
"G1 X79.800 Y82.640 E6.81898\n" +
"G1 X81.493 Y83.246 E6.85604\n" +
"G1 X82.930 Y83.929 E6.88883\n" +
"G1 X84.445 Y84.836 E6.92523\n" +
"G1 X85.427 Y85.551 E6.95026\n" +
"G1 X86.247 Y86.274 E6.97279\n" +
"G1 X86.867 Y86.935 E6.99147\n" +
"G1 X87.423 Y87.660 E7.01030\n" +
"G1 X88.027 Y88.642 E7.03406\n" +
"G1 X88.741 Y90.113 E7.06776\n" +
"G1 X89.224 Y91.301 E7.09419\n" +
"G1 X89.917 Y93.227 E7.13638\n" +
"G1 X90.342 Y94.469 E7.16344\n" +
"G1 X91.336 Y97.630 E7.23172\n" +
"G1 X92.317 Y101.000 E7.30408\n" +
"G1 X92.784 Y102.726 E7.34093\n" +
"G1 X93.229 Y104.701 E7.38265\n" +
"G1 X93.405 Y105.359 E7.39670\n" +
"G1 X93.606 Y105.974 E7.41003\n" +
"G1 X93.876 Y106.641 E7.42486\n" +
"G1 X94.219 Y107.427 E7.44254\n" +
"G1 X94.430 Y107.804 E7.45144\n" +
"G1 X94.744 Y108.189 E7.46167\n" +
"G1 X95.046 Y108.469 E7.47015\n" +
"G1 X95.378 Y108.724 E7.47879\n" +
"G1 X95.756 Y108.960 E7.48797\n" +
"G1 X96.233 Y109.195 E7.49893\n" +
"G1 X96.748 Y109.392 E7.51030\n" +
"G1 X97.369 Y109.567 E7.52360\n" +
"G1 X98.113 Y109.706 E7.53920\n" +
"G1 X98.997 Y109.790 E7.55750\n" +
"G1 X101.003 Y109.790 E7.59883\n" +
"G1 X101.887 Y109.706 E7.61713\n" +
"G1 X102.631 Y109.567 E7.63273\n" +
"G1 X103.252 Y109.392 E7.64603\n" +
"G1 X103.767 Y109.195 E7.65740\n" +
"G1 X104.244 Y108.960 E7.66836\n" +
"G1 X104.622 Y108.724 E7.67754\n" +
"G1 X104.955 Y108.468 E7.68621\n" +
"G1 X105.315 Y108.127 E7.69642\n" +
"G1 X105.491 Y107.915 E7.70211\n" +
"G1 X105.646 Y107.682 E7.70787\n" +
"G1 X105.782 Y107.423 E7.71390\n" +
"G1 X106.328 Y106.144 E7.74255\n" +
"G1 X106.603 Y105.333 E7.76021\n" +
"G1 X106.771 Y104.700 E7.77370\n" +
"G1 X107.216 Y102.726 E7.81541\n" +
"G1 X107.683 Y101.000 E7.85226\n" +
"G1 X108.561 Y97.970 E7.91728\n" +
"G1 X109.658 Y94.468 E7.99291\n" +
"G1 X110.011 Y93.424 E8.01563\n" +
"G1 X110.748 Y91.378 E8.06045\n" +
"G1 X111.228 Y90.179 E8.08707\n" +
"G1 X111.973 Y88.642 E8.12228\n" +
"G1 X112.585 Y87.649 E8.14632\n" +
"G1 X113.126 Y86.943 E8.16465\n" +
"G1 X113.773 Y86.254 E8.18413\n" +
"G1 X114.547 Y85.572 E8.20539\n" +
"G1 X115.603 Y84.803 E8.23232\n" +
"G1 X117.070 Y83.929 E8.26750\n" +
"G1 X118.507 Y83.246 E8.30030\n" +
"G1 X120.230 Y82.631 E8.33801\n" +
"G1 X123.404 Y81.829 E8.40548\n" +
"G1 X128.988 Y80.925 E8.52207\n" +
"G1 X135.131 Y80.467 E8.64904\n" +
"G1 X145.851 Y80.387 E8.86997\n" +
"G1 X151.080 Y80.657 E8.97790\n" +
"G1 X153.225 Y80.963 E9.02254\n" +
"G1 X154.398 Y81.249 E9.04742\n" +
"G1 X155.459 Y81.628 E9.07066\n" +
"G1 X156.306 Y82.031 E9.08998\n" +
"G1 X157.266 Y82.607 E9.11305\n" +
"G1 X158.223 Y83.307 E9.13749\n" +
"G1 X158.990 Y83.981 E9.15854\n" +
"G1 X159.668 Y84.700 E9.17891\n" +
"G1 X160.255 Y85.460 E9.19871\n" +
"G1 X160.821 Y86.362 E9.22065\n" +
"G1 X161.521 Y87.745 E9.25259\n" +
"G1 X163.365 Y92.380 E9.35540\n" +
"G1 X165.929 Y100.174 E9.52450\n" +
"G1 X166.568 Y102.061 E9.56557\n" +
"G1 X166.821 Y102.621 E9.57824\n" +
"G1 X166.990 Y102.912 E9.58517\n" +
"G1 X167.185 Y103.163 E9.59172\n" +
"G1 X167.363 Y103.317 E9.59657\n" +
"G1 X167.568 Y103.432 E9.60143\n" +
"G1 X167.967 Y103.533 E9.60989\n" +
"G1 X168.436 Y103.554 E9.61957\n" +
"G1 X171.648 Y103.358 E9.68590\n" +
"G1 X172.250 Y103.364 E9.69830\n" +
"G1 X172.557 Y103.395 E9.70467\n" +
"G1 X172.760 Y103.446 E9.70898\n" +
"G1 X172.806 Y103.476 E9.71011\n" +
"G1 X172.828 Y103.639 F7800.000\n" +
"G1 E7.71011 F2400.00000\n";
                } 
            }else if(linea.matches(fb19)){
                    String[] first2 = linea.split("[//(]");
                    String[] n3 = first2[1].split("[//)]");
                    String[] n4 = n3[0].split(",");

                    if(n4[0].toString().contains("simple")){  
                        lefttemplate[0] = variables.lefttemplate[0];
                        lefttemplate[1] = variables.lefttemplate[1];
                    }   
            }   
            else if(linea.matches(fb20)){
                    String[] first3 = linea.split("[//(]");
                    String[] n4 = first3[1].split("[//)]");
                    String[] n5 = n4[0].split(",");

                    if(n5[0].toString().contains("simple")){  
                       righttemplate[0] = variables.righttemplate[0];
                        righttemplate[1] = variables.righttemplate[1];
                    }   
            }  
        }
        //SE ADJUNTA EL MÉTODO LOOP AL MÉTODO SETUP
        salienteinicio += salienteloop + "}";
        codseg = salienteinicio.split("\n");
        //SE CREAN TABULACIONES
        for(int i=0; i<codseg.length; i++){
            for(int j=0; j<tab; j++){
                codseg[i] = "\t"+codseg[i];
            }
            salientetab +=codseg[i]+"\n"; 
            if(codseg[i].contains("{")){tab++;}
            if(codseg[i].contains("}")){tab--;}
        }
        
        
        
        //System.out.println("\n Código Intermedio Generado: \n"+salienteinicio);
    }

}
