/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import static util.Utils.resizeIcon;
/**
 *
 * @author juani
 */
public class ErroresSinSem extends javax.swing.JDialog {

    /**
     * Creates new form ErroresSinSem
     */
    public ErroresSinSem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public ErroresSinSem(java.awt.Frame parent, boolean modal, String gram, String err) {
        super(parent, modal);
        initComponents();
        this.setSize(730,535);
        this.setLocationRelativeTo(null);
        
        //CAMBIAR COLOR DEL JTEXTPANE-----------------------------------------------------------------
        Color bgColor = new Color(42, 43, 46);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(bgColor));
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        txtprod.putClientProperty("Nimbus.Overrides", defaults);
        txtprod.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        txtprod.setBackground(bgColor);
        //----------------------------------------------------------------------------------------------------
        
        
        
        String perror = gram;
        String error = err;
        tipoerror(error);
        txterror3.setText(error);
        produc(perror);
        terminales(perror);
    }
    
    public void terminales(String errort){
        String sep1[]= errort.split("=");
        String sep2[]= sep1[1].split(" ");
        
        //txtTerminales.setText(errort);
        for(int i=1; i<=sep2.length-1; i++){
            if(sep2[i].substring(0, 1).equals(sep2[i].substring(0, 1).toUpperCase())){
                if(txtTerminales.getText().equals("")){
                    txtTerminales.setText(sep2[i]);
                }else{
                txtTerminales.setText(txtTerminales.getText()+", "+sep2[i]);
                }
            }else{
                if(txtNoTerminales.getText().equals("")){
                    txtNoTerminales.setText(sep2[i]);
                }else{
                txtNoTerminales.setText(txtTerminales.getText()+", "+sep2[i]);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jltrampa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtprod = new javax.swing.JTextPane();
        txterror3 = new javax.swing.JLabel();
        txterror = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTerminales = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNoTerminales = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.setLayout(null);

        jltrampa.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jltrampa.setForeground(new java.awt.Color(255, 51, 51));
        jltrampa.setText("HOLA");
        jPanel1.add(jltrampa);
        jltrampa.setBounds(90, 140, 310, 22);

        txtprod.setEditable(false);
        txtprod.setBackground(new java.awt.Color(102, 102, 102));
        txtprod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txtprod.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        txtprod.setForeground(new java.awt.Color(255, 255, 255));
        txtprod.setFocusable(false);
        jScrollPane1.setViewportView(txtprod);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(12, 112, 690, 116);

        txterror3.setBackground(new java.awt.Color(204, 204, 204));
        txterror3.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        txterror3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txterror3);
        txterror3.setBounds(44, 26, 625, 36);

        txterror.setBackground(new java.awt.Color(204, 204, 204));
        txterror.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        txterror.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txterror.setText("Producción donde se encontró el error:");
        jPanel1.add(txterror);
        txterror.setBounds(44, 69, 625, 36);

        btnAceptar.setBackground(new java.awt.Color(50, 82, 136));
        btnAceptar.setFont(new java.awt.Font("Consolas", 1, 16)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(204, 204, 204));
        btnAceptar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAceptar.setOpaque(true);
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAceptarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptarMouseEntered(evt);
            }
        });
        jPanel1.add(btnAceptar);
        btnAceptar.setBounds(280, 400, 143, 62);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("No Terminales:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 320, 150, 50);

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("Terminales:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 250, 120, 50);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtTerminales.setEditable(false);
        txtTerminales.setColumns(20);
        txtTerminales.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        txtTerminales.setForeground(new java.awt.Color(51, 51, 255));
        txtTerminales.setRows(5);
        txtTerminales.setFocusable(false);
        jScrollPane2.setViewportView(txtTerminales);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(140, 250, 560, 50);

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtNoTerminales.setEditable(false);
        txtNoTerminales.setColumns(20);
        txtNoTerminales.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        txtNoTerminales.setForeground(new java.awt.Color(204, 0, 153));
        txtNoTerminales.setRows(5);
        txtNoTerminales.setFocusable(false);
        jScrollPane4.setViewportView(txtNoTerminales);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(170, 320, 530, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseEntered
        //Color color1 = new Color(78,156,234);
        //btnAceptar.setForeground(color1);
        Color color2 = new Color(50,82,110);
        btnAceptar.setBackground(color2);
    }//GEN-LAST:event_btnAceptarMouseEntered
    
    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        //Color color1 = new Color(204,204,204);
        //btnAceptar.setForeground(color1);
        Color color2 = new Color(50,82,136);
        btnAceptar.setBackground(color2);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void btnAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnAceptarMouseClicked
    public void tipoerror(String a){
        if(a.substring(0,8).equals("Error se")){
            a = "Error Semántico";
        }else{
            a = "Error Sintáctico";
        }
        this.setTitle(a);
    }
    public void produc(String b){   
        String a[] = b.split("=");
        jltrampa.setText(a[0]+"=");
        txtprod.setText("\n\n"+"          "+a[1]);
    }
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
            java.util.logging.Logger.getLogger(ErroresSinSem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ErroresSinSem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ErroresSinSem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ErroresSinSem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ErroresSinSem dialog = new ErroresSinSem(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jltrampa;
    private javax.swing.JTextArea txtNoTerminales;
    private javax.swing.JTextArea txtTerminales;
    private javax.swing.JLabel txterror;
    private javax.swing.JLabel txterror3;
    private javax.swing.JTextPane txtprod;
    // End of variables declaration//GEN-END:variables
}
