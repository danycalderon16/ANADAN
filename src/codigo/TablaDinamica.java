package codigo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Stack;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Simbolos;

public class TablaDinamica extends javax.swing.JFrame {

    private static ArrayList<Simbolos> simbolos = new ArrayList<Simbolos>();
    private DefaultTableModel m = new DefaultTableModel();

    static Map<String, TablaSimbolos> tablaSimbolos;

    static Stack<String> lista;
    //FORMATO PARA TABLAS------------------------------------------------------------------------------------------------------------------------ 
    public void formatoTabla(JTable tabla) {
        Color color1 = new Color(244,118,42);
        Color color2 = new Color(34, 40, 49);
        Font fuente = new Font("Consolas", Font.PLAIN, 12);
        //tabla.setFocusable(false);
        //tabla.setIntercellSpacing(new java.awt.Dimension(0, 0));
        //tabla.setRowHeight(25);
        //tabla.setRowMargin(0);
        //tabla.setShowVerticalLines(false);
        //tabla.setBackground(Color.WHITE);
        //tabla.setSelectionBackground(color1);
        //tabla.getTableHeader().setDefaultRenderer(new HeaderColor());
        //tabla.setFont(fuente);
        //tabla.setForeground(Color.BLACK);
        //tabla.setEditingRow(-1);
    }
    
    public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setForeground(Color.WHITE);
            setBackground(new java.awt.Color(34, 40, 49));
            setFont(new Font("Consolas", Font.PLAIN, 12));
            return this;
        }
    }
    public void seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logopro2.png")));
    }
    
    public TablaDinamica(ArrayList<Simbolos> simbolo) {
        this.simbolos = simbolo;
        limpiar();
        //formatoTabla(tblDinamica);
        seticon();
        TablaSimbolos.mostrar();
        initComponents();
        setLocationRelativeTo(null);
           // System.out.println("Simbolos en TD");
        for (Simbolos simbolos : simbolo) {
           // System.out.println(simbolos.toString());
        }
        tblDinamica.setAutoCreateRowSorter(true);
        m = (DefaultTableModel) tblDinamica.getModel();
        for (int i = 0; i < m.getRowCount(); i++) {
            m.removeRow(i);
        }
       
        for (Simbolos sim : simbolos) {
            m.addRow(new Object[]{sim.getLinea(), sim.getLexema(), "", ""});
        }
        Collection<codigo.Simbolo> sim = TablaSimbolos.enviarLista();
        System.out.println("Simbolos obtenido de TS");
        for (codigo.Simbolo sym : sim) {
            System.out.println(sym.toString());
        }
        for (int i = 0; i < m.getRowCount(); i++) {
            for (codigo.Simbolo symbol : sim) {
                if (symbol.nombre.equals(m.getValueAt(i, 1).toString())) {
                    m.setValueAt(symbol.nombre, i, 1); //identificador - lexema
                    m.setValueAt(symbol.tipo, i, 2);// tipo  de dato
                    m.setValueAt(symbol.valor, i, 3); //vaor variable
                    m.setValueAt(symbol.clase, i, 4); // tipo de varibale
                }
            }
        }
    }
    private void limpiar() {
        int r = m.getRowCount();
        for (int i = 0; i < r; i++) {
            m.removeRow(r);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDinamica = new javax.swing.JTable();

        setTitle("Tabla de simbolos");

        tblDinamica.setAutoCreateRowSorter(true);
        tblDinamica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LINEA", "IDENTIFICADOR", "TIPO", "VALOR", "CLASE"
            }
        ));
        jScrollPane1.setViewportView(tblDinamica);
        if (tblDinamica.getColumnModel().getColumnCount() > 0) {
            tblDinamica.getColumnModel().getColumn(0).setMinWidth(50);
            tblDinamica.getColumnModel().getColumn(0).setMaxWidth(70);
            tblDinamica.getColumnModel().getColumn(2).setMinWidth(100);
            tblDinamica.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TablaDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaDinamica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        me.setRowCount(0);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TablaDinamica(simbolos).setVisible(true);
            }
        });
    }
    public static DefaultTableModel me = new DefaultTableModel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblDinamica;
    // End of variables declaration//GEN-END:variables
}
