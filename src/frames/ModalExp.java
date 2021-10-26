/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelos.Expresion;
import static util.Utils.resizeIcon;

/**
 *
 * @author Calderon
 */
public class ModalExp extends javax.swing.JFrame {

    public ArrayList<Expresion> exp_list;
    private int index = 0;
    
    public ModalExp(ArrayList<Expresion> exps) {
        initComponents();
        setLocationRelativeTo(this); 
        icon_close.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/img/close_icon.png")), icon_close.getWidth(), icon_close.getHeight()));       
        this.exp_list = exps;        
        System.out.println(exps);
        for (Expresion exp : exps) {
            cmbExps.addItem(exp.getInfija());            
        }
        this.index = exps.size()-1;
        setData();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_exp = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lb_exp_post = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_exp_inf_parentesis = new javax.swing.JLabel();
        icon_close = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmbExps = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lb_exp_result = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_exp.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lb_exp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_exp.setText("x=mc2");
        lb_exp.setToolTipText("");
        lb_exp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lb_exp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 316, 45));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Expresión");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 85, 120, 30));

        lb_exp_post.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb_exp_post.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_exp_post.setText("x=mc2");
        lb_exp_post.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lb_exp_post, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 246, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Pilas");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Infija con parentesis");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 148, 167, 30));

        lb_exp_inf_parentesis.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb_exp_inf_parentesis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_exp_inf_parentesis.setText("x=mc2");
        lb_exp_inf_parentesis.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lb_exp_inf_parentesis, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 133, 246, 45));

        icon_close.setText("jLabel4");
        icon_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icon_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_closeMouseReleased(evt);
            }
        });
        jPanel1.add(icon_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 30, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 54, 710, 10));

        cmbExps.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciones una expresión" }));
        cmbExps.setAutoscrolls(true);
        cmbExps.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbExpsItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbExps, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 210, -1));

        jLabel4.setText("Expresiones");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 460, 150));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Resultado");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 120, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Postfija");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 120, 30));

        lb_exp_result.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb_exp_result.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_exp_result.setText("x=mc2");
        lb_exp_result.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lb_exp_result, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 246, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void icon_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_closeMouseReleased
        this.dispose();
    }//GEN-LAST:event_icon_closeMouseReleased

    private void cmbExpsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbExpsItemStateChanged
        // TODO add your handling code here:
        index = cmbExps.getSelectedIndex() - 1;
        setData();
    }//GEN-LAST:event_cmbExpsItemStateChanged

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
            java.util.logging.Logger.getLogger(ModalExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalExp(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbExps;
    private javax.swing.JLabel icon_close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lb_exp;
    private javax.swing.JLabel lb_exp_inf_parentesis;
    private javax.swing.JLabel lb_exp_post;
    private javax.swing.JLabel lb_exp_result;
    // End of variables declaration//GEN-END:variables

    private void setData() {
        jTextArea1.setText("");
        
        lb_exp.setText(exp_list.get(index).getInfija());
        lb_exp_inf_parentesis.setText(exp_list.get(index).getInfija_parentesis());
        lb_exp_post.setText(exp_list.get(index).getPostfija());
        for (String object : exp_list.get(index).getPila_ops()) {
            //jTextArea1.append("---------------------------------------\n");
            String ops[] = object.split(",");
            jTextArea1.append("|  ");
            for (String op : ops) {
                jTextArea1.append(op+"  |  ");                
            }
            jTextArea1.append("\n\n");
            //jTextArea1.append("---------------------------------------\n");
            
        }
        
        lb_exp_result.setText(exp_list.get(index).getResult());
    }
}
