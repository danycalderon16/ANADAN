/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import static codigo.CodigoG.filamento;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import static util.Utils.resizeIcon;
import codigo.VentanaPrincipal;
import static codigo.VentanaPrincipal.intercode;
import static codigo.VentanaPrincipal.txtAreaEdit;
import ds.desktop.notify.DesktopNotify;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;

//DE COLORES
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class CodigoGenerado extends javax.swing.JDialog {

    /**
     * Creates new form CodigoGenerado1
     */
    public CodigoGenerado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Código Intermedio Generado");
        //CAMBIAR COLOR DEL JTEXTPANE-----------------------------------------------------------------
        Color bgColor = new Color(42, 43, 46);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(bgColor));
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        txtcod.putClientProperty("Nimbus.Overrides", defaults);
        txtcod.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        txtcod.setBackground(bgColor);
        //----------------------------------------------------------------------------------------------------

        this.setLocationRelativeTo(this);
        seticon();
    }

    public CodigoGenerado(java.awt.Frame parent, boolean modal, String codigoArduino) {
        super(parent, modal);
        initComponents();
        VentanaPrincipal.showTD2();
        VentanaPrincipal.expobjeto();
        this.setTitle("Código Intermedio Generado");
        //CAMBIAR COLOR DEL JTEXTPANE-----------------------------------------------------------------
        Color bgColor = new Color(42, 43, 46);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(bgColor));
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        txtcod.putClientProperty("Nimbus.Overrides", defaults);
        txtcod.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        txtcod.setBackground(bgColor);
        //----------------------------------------------------------------------------------------------------

        this.setLocationRelativeTo(this);
        seticon();
        llenarcodigo2(codigoArduino);
    }

    public CodigoGenerado(java.awt.Frame parent, boolean modal, String codigoArduino, String a) {
        super(parent, modal);
        initComponents();
        VentanaPrincipal.showTD2();
        VentanaPrincipal.expobjeto();
        this.setTitle("Código Intermedio Generado");
        //CAMBIAR COLOR DEL JTEXTPANE-----------------------------------------------------------------
        Color bgColor = new Color(42, 43, 46);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(bgColor));
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        txtcod.putClientProperty("Nimbus.Overrides", defaults);
        txtcod.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        txtcod.setBackground(bgColor);
        //----------------------------------------------------------------------------------------------------

        this.setLocationRelativeTo(this);
        seticon();
        llenarcodigo2(codigoArduino);
        generargcode();
        generararchivo();
            GuardarArchivo(file, txtcod.getText());
        if(!VentanaPrincipal.txtGive.getForeground().equals(Color.RED)){
            abrirarchivo();
        }
    }
    public void abrirarchivo(){
            try {
      //constructor of file class having file as argument  
      File file = new File("C:\\Anadan Files\\modelo.gcode");
      if (!Desktop.isDesktopSupported())
      //check if Desktop is supported by Platform or not  
      {
        System.out.println("not supported");
        return;
      }
      Desktop desktop = Desktop.getDesktop();
      if (file.exists()) //checks file exists or not  
      desktop.open(file); //opens the specified file  
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    }
    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje = "Archivo guardado";

        } catch (Exception e) {
        }
        return mensaje;
    }
    public File file = new File("C:\\Anadan Files\\modelo.gcode");

    public void generararchivo() {
        try {

            /*If file gets created then the createNewFile() 
          method would return true or if the file is 
          already present it would return false */
            System.out.println("115");
            boolean flag = file.createNewFile();
            if (flag) {
                System.out.println("El archivo se creó correctamente.");
            } else {
                System.out.println("El archivo ya está creado.");
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    public void llenarcodigo2(String arduino) {
        txtcod.setText(arduino);
    }

    public void seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logopro2.png")));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcod = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setFocusable(false);

        txtcod.setEditable(false);
        txtcod.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtcod.setForeground(new java.awt.Color(102, 255, 204));
        txtcod.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(txtcod);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem1.setText(".gcode");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Export as gcode");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Optimization");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu2MouseReleased(evt);
            }
        });

        jMenuItem3.setText("Optimize");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void generargcode() {
        VentanaPrincipal.txtGive.setText("");
        this.setTitle("Código G Generado");
        CodigoG codigoggenerado = new CodigoG(txtcod.getText());
        String[] copiacodigog;
        String[] copiacodigog2;
        String[] copiacodigog3;

        copiacodigog = codigoggenerado.getCodigoG();
        copiacodigog2 = codigoggenerado.getCodigoG2();
        copiacodigog3 = codigoggenerado.getCodigoG3();
        //System.out.println(copiacodigog[0]);
        txtcod.setText("");
        txtcod.setText(codigoggenerado.getCaracteristicas() + "\n");
                        switch(CodigoG.filamento){
                            case 190:
                                txtcod.setText(txtcod.getText() +"\n;La temperatura óptima de impresión para PVA es de "+filamento+" grados.");
                            break;
                            case 200:
                                txtcod.setText(txtcod.getText() +"\n;La temperatura óptima de impresión para HIPS es de "+filamento+" grados.");
                            break;
                            case 170:
                                txtcod.setText(txtcod.getText() +"\n;La temperatura óptima de impresión para LAYWOOD es de "+filamento+" grados.");
                            break;
                        }
                        
        txtcod.setText(txtcod.getText() + "\n");
        for (int i = 0; i < copiacodigog.length; i++) {
            txtcod.setText(txtcod.getText() + "\n" + copiacodigog[i]);
        }
        for (int i = 0; i < copiacodigog2.length; i++) {
            txtcod.setText(txtcod.getText() + "\n" + copiacodigog2[i]);
        }
        for (int i = 0; i < copiacodigog3.length; i++) {
            txtcod.setText(txtcod.getText() + "\n" + copiacodigog3[i]);
        }
        txtcod.setText(txtcod.getText() + "\n" + codigoggenerado.getCaracteristicas2());
        //txtcod.setText(codigoggenerado.getCodigoG().toString()+"");
        //System.out.println(codigoggenerado.getCodigoG()+"");
    }
    private void jMenuItem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseReleased
        generargcode();
    }//GEN-LAST:event_jMenuItem1MouseReleased
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    String name;
    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseReleased
        if (name == null) {

            if (seleccionar.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {

                archivo = seleccionar.getSelectedFile();
                if (archivo.getName().endsWith("gcode")) {
                    String Documento = txtcod.getText();
                    String mensaje = GuardarArchivo(archivo, Documento);
                    if (mensaje != null) {
                        showMessageDialog(null, mensaje);
                    } else {
                        showMessageDialog(null, "Archivo no compatible");
                    }
                } else {
                    showMessageDialog(null, "Fallo al guardar, coloque extencion .gcode");
                }
            }
            name = archivo.getName();
            return;
        } else {
        }

        File archivo = new File(name);
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(txtcod.getText());
            escribir.close();

            DesktopNotify.showDesktopMessage("ANADAN-INFORMATION", "Actualizaciones guardadas", DesktopNotify.SUCCESS, 5000L);
            //   showMessageDialog(null,"Actualizaciones guardadas");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2MouseReleased

    private void jMenu2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseReleased

    }//GEN-LAST:event_jMenu2MouseReleased

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseReleased
        Optimizacion op = new Optimizacion(txtcod.getText());
        txtcod.setText(op.getArduino());
    }//GEN-LAST:event_jMenuItem3MouseReleased


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
            java.util.logging.Logger.getLogger(CodigoGenerado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodigoGenerado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodigoGenerado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodigoGenerado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CodigoGenerado dialog = new CodigoGenerado(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txtcod;
    // End of variables declaration//GEN-END:variables
}
