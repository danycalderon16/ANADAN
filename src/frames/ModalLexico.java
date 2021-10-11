/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelos.LabelError;
import static util.Utils.*;

/**
 *
 * @author danyc
 */
public class ModalLexico extends javax.swing.JFrame {

    private boolean show;

    public ModalLexico(LabelError automata) {
        initComponents();
        setLocationRelativeTo(this);
        show = true;
        icon_close_btn.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/img/close_icon.png")), icon_close_btn.getWidth(), icon_close_btn.getHeight()));
        lb_info_error.setText(automata.getLabel().getText());
       
     //   Error en la línea 1. Error léxico. El número '21.2.' tiene puntos de más.
        
    }
     
    public void recorreAutomata(String mensaje){
        System.out.println("hey im using wh");
        String[] mensajePartido= mensaje.split("'");
        String error= mensajePartido[1];
        
        String[] estados={"q1","q2","q3","q4","q5","q9","q10","q11","q12","13"};
        String [] estados1={"q1","q2","q3","q6","q7","q8","q12","13"};
        String inicial="q1";
        String[] estadosFinales={"q2","q4","q7","q9"};
        String[] alfabeto={"0", "1", "2", "3", "4", "5", "6", "7","8", "9", "+", "-", "*", "/", "^", "e", "."};
        String cadenaRecorrido="Cadena que entro: "+ error+"\n";
       
        for (int j = 0; j < 2;  j++) {    
           int c=-1;
            for (int i = 0;i < error.length(); i++) {
                c++;
                String opcion=estados[i];
                if(j==1){opcion=estados1[i];
                cadenaRecorrido="Cadena que entro: "+ error+"\n";
                }                
            switch(opcion){
                case "q1":
                    switch (error.charAt(c)) {
                        case '+':
                            cadenaRecorrido+="q1-- "+error.charAt(c)+" -->q2 \n";
                            break;
                        case '-':
                            cadenaRecorrido+="q1-- "+error.charAt(c)+" -->q2 \n";
                            break;
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            c--;
                            cadenaRecorrido+="q1-- E -->q2 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q1 no existe salida a otro estado con el caracter: '"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                    }
                   break;
                case "q2":
                    switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q2-- "+error.charAt(c)+" -->q3 \n";
                        break;
                        default:
                            cadenaRecorrido+="de q2 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                      InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                    }
                    break;
                    
                case "q3":
                    switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q3-- "+error.charAt(c)+" -->q3 \n";
                            i--;
                            break;
                        case '.':
                            cadenaRecorrido+="q3-- "+error.charAt(c)+" -->q4 \n";
                            break;
                        case '^':
                            cadenaRecorrido+="q3-- "+error.charAt(c)+" -->q6 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q3 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                        }
                   break;
                case "q4":
                    switch ( error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q4-- "+error.charAt(c)+" -->q5 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q4 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                        }
                    break;
                case "q5":
                    switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':  
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q5-- "+error.charAt(c)+" -->q5 \n";
                            i--;
                            break;
                        case '^':
                            cadenaRecorrido+="q5-- "+error.charAt(c)+" -->q9 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q5 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                        }
                    break;
                case "q6":
                    switch ( error.charAt(c)) {
                        case '+':
                            cadenaRecorrido+="q6-- "+error.charAt(c)+" -->q7 \n";
                            break;
                        case '-':
                            cadenaRecorrido+="q6-- "+error.charAt(c)+" -->q7 \n";
                            break;
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            i--;
                            cadenaRecorrido+="q6-- E -->q7 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q6 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                    }
                    break;
                case "q7":
                    switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q7-- "+error.charAt(c)+" -->q8 \n";
                        break;
                        default:
                            cadenaRecorrido+="de q7 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                      return;
                    }
                    break;
                case "q8":
                    switch (error.charAt(c)) {
                        case '.':
                            cadenaRecorrido+="q8-- "+error.charAt(c)+" -->q12 \n";
                        break;
                        default:
                            cadenaRecorrido+="de q8 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                      return;
                    }
                    break;
                case "q9":
                    switch (error.charAt(c)) {
                        case '+':
                            cadenaRecorrido+="q9-- "+error.charAt(c)+" -->q10 \n";
                            break;
                        case '-':
                            cadenaRecorrido+="q9-- "+error.charAt(c)+" -->q10 \n";
                            break;
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            i--;
                            cadenaRecorrido+="q9-- E -->q10 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q9 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                            return;
                    }
                    break;    
                case "q10":
                    switch (error.charAt(c)) {
                       case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q10-- "+error.charAt(c)+" -->q11 \n";
                        break;
                        default:
                            cadenaRecorrido+="de q10 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                      return;
                    }
                    break;
                case "q11":
                    switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q11-- "+error.charAt(c)+" -->q11 \n";
                            i--;
                            break;
                        case '.':
                            cadenaRecorrido+="q11-- "+error.charAt(c)+" -->q12 \n";
                            break;
                        default:
                            cadenaRecorrido+="de q11 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                      return;
                    }
                    break;
                case "q12":
                 switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q12-- "+error.charAt(c)+" -->q13 \n";
                        break;
                        default:
                            cadenaRecorrido+="de q12 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                      return;
                    }
                    break;
                case "q13":
                    switch (error.charAt(c)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':    
                        case '7':
                        case '8':
                        case '9':
                            cadenaRecorrido+="q13-- "+error.charAt(c)+" -->q13 \n";
                            i--;
                            break;
                        default:
                            cadenaRecorrido+="de q7 no existe salida a otro estado con el caracter:'"+error.charAt(c)+"'";
                            InfoError.setText("");
        InfoError.setText(cadenaRecorrido);
                      return;
                    }
                    break;
                default:
                    
                    break;
            }

                }//for caminos       
         
        }//for
        
    }//recorreAutomata
    public void close() {
        show = false;
        dispose();
    }

    public boolean isOpen() {
        return show;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        icon_close_btn = new javax.swing.JLabel();
        jlbAutomata = new javax.swing.JLabel();
        lb_info_error = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlbEstados = new javax.swing.JLabel();
        jlbInicial = new javax.swing.JLabel();
        jlbFinal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlfabeto = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        InfoError = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Ayuda sobre error léxico");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 470, 60));

        icon_close_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icon_close_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_close_btnMouseReleased(evt);
            }
        });
        jPanel1.add(icon_close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 20, 20));

        jlbAutomata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbAutomata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jlbAutomata, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 480, 370));

        lb_info_error.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_info_error.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(lb_info_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 610, 40));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 280, 30));

        jlbEstados.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlbEstados.setForeground(new java.awt.Color(0, 0, 0));
        jlbEstados.setText("Q={q1,q2,q3,q4,q5,q6,q7,q8,q9}");
        jPanel1.add(jlbEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 250, 30));

        jlbInicial.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlbInicial.setForeground(new java.awt.Color(0, 0, 0));
        jlbInicial.setText("S=q1");
        jPanel1.add(jlbInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 400, 250, 30));

        jlbFinal.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jlbFinal.setForeground(new java.awt.Color(0, 0, 0));
        jlbFinal.setText("F={q2,q4,q7,q9}");
        jPanel1.add(jlbFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 250, 30));

        txtAlfabeto.setColumns(20);
        txtAlfabeto.setRows(5);
        txtAlfabeto.setText("Σ={0, 1, 2, 3, 4, 5, 6, 7,8, 9, +, -, *, /, ^, e, .}");
        jScrollPane1.setViewportView(txtAlfabeto);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 330, 60));

        InfoError.setColumns(20);
        InfoError.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        InfoError.setForeground(new java.awt.Color(204, 0, 0));
        InfoError.setRows(5);
        jScrollPane2.setViewportView(InfoError);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 330, 210));

        jButton1.setText("Recorre Automata");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void icon_close_btnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_close_btnMouseReleased
        this.dispose();
    }//GEN-LAST:event_icon_close_btnMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        recorreAutomata(lb_info_error.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModalLexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalLexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalLexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalLexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalLexico(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea InfoError;
    private javax.swing.JLabel icon_close_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel jlbAutomata;
    public static javax.swing.JLabel jlbEstados;
    public static javax.swing.JLabel jlbFinal;
    public static javax.swing.JLabel jlbInicial;
    private javax.swing.JLabel lb_info_error;
    public static javax.swing.JTextArea txtAlfabeto;
    // End of variables declaration//GEN-END:variables

   
}
