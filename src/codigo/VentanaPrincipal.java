/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import ds.desktop.notify.DesktopNotify;
import frames.ModalLexico;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.UndoManager;
import modelos.LabelError;
import modelos.Simbolos;

import static util.Utils.*;

public class VentanaPrincipal extends javax.swing.JFrame {

    private ArrayList<String> identificadores = new ArrayList<String>();
    private ArrayList<LabelError> labelLexicoErrors;
    private ArrayList<Simbolos> simbolos = new ArrayList<Simbolos>();

    public VentanaPrincipal() {
        initComponents();
        seticon();
        undoManager();
        this.setTitle("ANADAN");
        setLocationRelativeTo(null);
        m = (DefaultTableModel) tblTablaSimbolos.getModel();
        numeroLineas();
        mnuMinimize.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplAreaEdit = new javax.swing.JPanel();
        scPanAreaEdit = new javax.swing.JScrollPane();
        txtAreaEdit = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablaSimbolos = new javax.swing.JTable();
        jScrollPaneConsole = new javax.swing.JScrollPane();
        txtConsolaPane = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        panelToolBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        icon_new = new javax.swing.JLabel();
        icon_save = new javax.swing.JLabel();
        icon_save_as = new javax.swing.JLabel();
        icon_table = new javax.swing.JLabel();
        icon_lexico = new javax.swing.JLabel();
        icon_sintactic = new javax.swing.JLabel();
        icon_run = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        icon_abrir = new javax.swing.JLabel();
        icon_undo = new javax.swing.JLabel();
        icon_redo = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        mnufile = new javax.swing.JMenu();
        mnuNew = new javax.swing.JMenuItem();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        mnuSaveAs = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        miNomalMode = new javax.swing.JMenuItem();
        miDarkMode = new javax.swing.JMenuItem();
        miRetroMode = new javax.swing.JMenuItem();
        miZoomInFont = new javax.swing.JMenuItem();
        javax.swing.JMenuItem miZoomOutFont = new javax.swing.JMenuItem();
        mnuTablaS = new javax.swing.JMenu();
        mnuFija = new javax.swing.JMenuItem();
        miDynamicTable = new javax.swing.JMenuItem();
        miMethod = new javax.swing.JMenuItem();
        mnuCompile = new javax.swing.JMenu();
        miCompileLexical = new javax.swing.JMenuItem();
        miCompileSyntax = new javax.swing.JMenuItem();
        miCompile = new javax.swing.JMenuItem();
        mnuMinimize = new javax.swing.JMenu();
        mnuUndo = new javax.swing.JMenu();
        mnuRedo = new javax.swing.JMenu();
        btnayuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAreaEdit.setColumns(20);
        txtAreaEdit.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtAreaEdit.setRows(5);
        scPanAreaEdit.setViewportView(txtAreaEdit);

        javax.swing.GroupLayout jplAreaEditLayout = new javax.swing.GroupLayout(jplAreaEdit);
        jplAreaEdit.setLayout(jplAreaEditLayout);
        jplAreaEditLayout.setHorizontalGroup(
            jplAreaEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scPanAreaEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jplAreaEditLayout.setVerticalGroup(
            jplAreaEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scPanAreaEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        getContentPane().add(jplAreaEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 800, 380));

        tblTablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COLUMN", "LINE", "LEXEME", "LEXICAL COMPONENT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTablaSimbolos);
        if (tblTablaSimbolos.getColumnModel().getColumnCount() > 0) {
            tblTablaSimbolos.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblTablaSimbolos.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblTablaSimbolos.getColumnModel().getColumn(2).setPreferredWidth(15);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 430, 380));

        jScrollPaneConsole.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPaneConsole.setViewportView(txtConsolaPane);

        getContentPane().add(jScrollPaneConsole, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 570, 220));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 430, 660, 220));

        panelToolBar.setBackground(new java.awt.Color(255, 255, 255));
        panelToolBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/split.png"))); // NOI18N
        panelToolBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 20, 30));

        icon_new.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        icon_new.setToolTipText("Nuevo");
        icon_new.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_new.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_newMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_new, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 20, 20));

        icon_save.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        icon_save.setToolTipText("Guardar");
        icon_save.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_saveMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 15, 20, 20));

        icon_save_as.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_save_as.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        icon_save_as.setToolTipText("Guardar como");
        icon_save_as.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_save_as.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_save_asMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_save_as, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 15, 20, 20));

        icon_table.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/table.png"))); // NOI18N
        icon_table.setToolTipText("Tabla de Simbolos");
        icon_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_table.setDisabledIcon(null);
        icon_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_tableMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 30));

        icon_lexico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_lexico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/analysis (1).png"))); // NOI18N
        icon_lexico.setToolTipText("Léxico");
        icon_lexico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_lexico.setDisabledIcon(null);
        icon_lexico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_lexicoMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_lexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 30, 30));

        icon_sintactic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_sintactic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Lexico.png"))); // NOI18N
        icon_sintactic.setToolTipText("Sintáctico");
        icon_sintactic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_sintactic.setDisabledIcon(null);
        icon_sintactic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_sintacticMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_sintactic, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 30, 30));

        icon_run.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_run.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        icon_run.setToolTipText("Compilar");
        icon_run.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_run.setDisabledIcon(null);
        icon_run.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_runMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_run, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/split.png"))); // NOI18N
        panelToolBar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 20, 30));

        icon_abrir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/OpenFile.png"))); // NOI18N
        icon_abrir.setToolTipText("Abrir");
        icon_abrir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_abrir.setDisabledIcon(null);
        icon_abrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_abrirMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_abrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 30, 30));

        icon_undo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_undo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Atras.png"))); // NOI18N
        icon_undo.setToolTipText("Deshacer");
        icon_undo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_undo.setDisabledIcon(null);
        icon_undo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_undoMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_undo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 30, 30));

        icon_redo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_redo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Deltante.png"))); // NOI18N
        icon_redo.setToolTipText("Rehacer");
        icon_redo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        icon_redo.setDisabledIcon(null);
        icon_redo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                icon_redoMouseReleased(evt);
            }
        });
        panelToolBar.add(icon_redo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 30, 30));

        getContentPane().add(panelToolBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 50));

        mnufile.setText("File");

        mnuNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NewFile.png"))); // NOI18N
        mnuNew.setText("New");
        mnuNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mnuNewMouseReleased(evt);
            }
        });
        mnufile.add(mnuNew);

        mnuOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/OpenFile.png"))); // NOI18N
        mnuOpen.setText("Open");
        mnuOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mnuOpenMouseReleased(evt);
            }
        });
        mnufile.add(mnuOpen);

        mnuSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SaveFile.png"))); // NOI18N
        mnuSave.setText("Save");
        mnuSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mnuSaveMouseReleased(evt);
            }
        });
        mnufile.add(mnuSave);

        mnuSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SaveAsFile.png"))); // NOI18N
        mnuSaveAs.setText("Save As");
        mnuSaveAs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mnuSaveAsMouseReleased(evt);
            }
        });
        mnufile.add(mnuSaveAs);

        barraMenu.add(mnufile);

        mnuEdit.setText("Edit");

        miNomalMode.setText("Normal mode");
        miNomalMode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miNomalModeMouseReleased(evt);
            }
        });
        mnuEdit.add(miNomalMode);

        miDarkMode.setText("Dark mode");
        miDarkMode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miDarkModeMouseReleased(evt);
            }
        });
        mnuEdit.add(miDarkMode);

        miRetroMode.setText("Retro mode");
        miRetroMode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miRetroModeMouseReleased(evt);
            }
        });
        mnuEdit.add(miRetroMode);

        miZoomInFont.setText("Aa+");
        miZoomInFont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miZoomInFontMouseReleased(evt);
            }
        });
        mnuEdit.add(miZoomInFont);

        miZoomOutFont.setText("Aa-");
        miZoomOutFont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miZoomOutFontMouseReleased(evt);
            }
        });
        mnuEdit.add(miZoomOutFont);

        barraMenu.add(mnuEdit);

        mnuTablaS.setText("Symbol Table");

        mnuFija.setText("Fixed");
        mnuFija.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mnuFijaMouseReleased(evt);
            }
        });
        mnuTablaS.add(mnuFija);

        miDynamicTable.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        miDynamicTable.setText("Dynamic");
        miDynamicTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miDynamicTableMouseReleased(evt);
            }
        });
        mnuTablaS.add(miDynamicTable);

        miMethod.setText("Method");
        miMethod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miMethodMouseReleased(evt);
            }
        });
        mnuTablaS.add(miMethod);

        barraMenu.add(mnuTablaS);

        mnuCompile.setText("Compile");
        mnuCompile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        miCompileLexical.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        miCompileLexical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Lexico.png"))); // NOI18N
        miCompileLexical.setText("Lexical");
        miCompileLexical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miCompileLexicalMouseReleased(evt);
            }
        });
        mnuCompile.add(miCompileLexical);

        miCompileSyntax.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        miCompileSyntax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/analysis (1).png"))); // NOI18N
        miCompileSyntax.setText("Syntax");
        miCompileSyntax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miCompileSyntaxMouseReleased(evt);
            }
        });
        miCompileSyntax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCompileSyntaxActionPerformed(evt);
            }
        });
        mnuCompile.add(miCompileSyntax);

        miCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Compilar.png"))); // NOI18N
        miCompile.setText(" Compile");
        mnuCompile.add(miCompile);

        barraMenu.add(mnuCompile);

        mnuMinimize.setText("Minimize");
        mnuMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnuMinimizeMousePressed(evt);
            }
        });
        barraMenu.add(mnuMinimize);

        mnuUndo.setText("Undo");
        mnuUndo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Atras.png"))); // NOI18N
        mnuUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnuUndoMousePressed(evt);
            }
        });
        barraMenu.add(mnuUndo);

        mnuRedo.setText("Redo");
        mnuRedo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnuRedoMousePressed(evt);
            }
        });
        barraMenu.add(mnuRedo);

        btnayuda.setText("Ayuda");
        btnayuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnayudaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnayudaMouseReleased(evt);
            }
        });
        barraMenu.add(btnayuda);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents
NumeroLinea lineatxtCodigo;

    public void numeroLineas() {
        lineatxtCodigo = new NumeroLinea(txtAreaEdit);
        scPanAreaEdit.setRowHeaderView(lineatxtCodigo);
        lineatxtCodigo.setBackground(new Color(50, 82, 136));
        lineatxtCodigo.setForeground(Color.WHITE);
        Font fuente = new Font("Rounded Mplus 1c Bold", 1, 14);
        lineatxtCodigo.setFont(fuente);
    }

    public static void notificar_er(String cad) {
        Errores += cad + "\n";
    }

    //----------------------------------------BUSCAR METODOS-----------------------
    public void buscaMethod() {
        tblMetodos.metod.setRowCount(0);
        Object[] O = new Object[3];
        for (int i = 0; i < tblTablaSimbolos.getRowCount(); i++) {

            if (m.getValueAt(i, 2) != null) {
                //System.out.println(m.getValueAt(i, 2).toString() + "   llego");
                switch (m.getValueAt(i, 2).toString()) {
                    case "setfilamenttype":
                        O[0] = "setfilamenttype";
                        O[1] = "1";
                        O[2] = "x<just>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "stop":
                        O[0] = "stop";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "getextrusorx":
                        O[0] = "getextrusorx";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "getextrusory":
                        O[0] = "getextrusory";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "getextrusorz":
                        O[0] = "getextrusorz";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "getfilamenttype":
                        O[0] = "getfilamenttype";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "setnewfilament":

                        O[0] = "setnewfilament";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "getfilament":

                        O[0] = "getfilament";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "gettemperature":

                        O[0] = "gettemperature";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "filltriangule":

                        O[0] = "filltriangule";
                        O[1] = "9";
                        O[2] = "x1<just>,y1<just>,z1<just>, x2<just>,y2<just>, z2<just>, x3<just>,y3<just>, z3<just>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "drawtriangule":
                        O[0] = "drawtriangule";
                        O[1] = "9";
                        O[2] = "x1<just>,y1<just>,z1<just>, x2<just>,y2<just>, z2<just>, x3<just>,y3<just>, z3<just>";
                        tblMetodos.metod.addRow(O);

                    case "fillrectangle":

                        O[0] = "fillrectangle";
                        O[1] = "12";
                        O[2] = "x1<just>,y1<just>,z1<just>, x2<just>,y2<just>,z2<just>, x3<just>,y3<just>,z3<just>, x4<just>,y4<just>,z4<just>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "drawrectangle":
                        O[0] = "drawrectangle";
                        O[1] = "12";
                        O[2] = "x1<just>,y1<just>,z1<just>, x2<just>,y2<just>,z2<just>, x3<just>,y3<just>,z3<just>, x4<just>,y4<just>,z4<just>";
                        tblMetodos.metod.addRow(O);

                        break;
                    case "sleep":
                        O[0] = "sleep";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "drawcircle":
                        O[0] = "drawcircle";
                        O[1] = "4";
                        O[2] = "x<just>,y<just>, z<just>, radio<just>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "fillcircle":
                        O[0] = "fillcircle";
                        O[1] = "4";
                        O[2] = "x<just>,y<just>, z<just>, radio<just>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "home":

                        O[0] = "home";
                        O[1] = "0";
                        O[2] = "<>";
                        tblMetodos.metod.addRow(O);

                        break;

                    case "printerport":

                        O[0] = "printerport";
                        O[1] = "1";
                        O[2] = "x<just>";
                        tblMetodos.metod.addRow(O);

                        break;

                    default:

                        break;

                }//switch
            }//if

        }
        rt();
    }
//------------------------------------------------------------------------------------------

    public void rt() {

        int col = tblMetodos.metod.getColumnCount();
        int ren = tblMetodos.metod.getRowCount();

        if (ren == -1) {
            showMessageDialog(null, "No existen métodos");
            return;
        }
        String[][] arreglo = new String[ren][col];
        if (ren == 0) {
            return;
        }
        for (int i = 0; i < ren; i++) {

            arreglo[i][0] = tblMetodos.metod.getValueAt(i, 0).toString();
            arreglo[i][1] = tblMetodos.metod.getValueAt(i, 1).toString();
            arreglo[i][2] = tblMetodos.metod.getValueAt(i, 2).toString();

        }

        bav(arreglo, 0, ren);
    }

    public void bav(String[][] arreglo, int ini, int ter) {

        for (int x = 0; x < ter - 1; x++) {

            for (int y = 0; y < ter - 1; y++) {
                String elementoActual = arreglo[y][0];
                String elementoSiguiente = arreglo[y + 1][0];

                String ele1 = arreglo[y][1];
                String elel1 = arreglo[y + 1][1];

                String ele2 = arreglo[y][2];
                String elel2 = arreglo[y + 1][2];
                if (elementoActual.compareTo(elementoSiguiente) > 0) {
                    // Intercambiar
                    arreglo[y][0] = elementoSiguiente;
                    arreglo[y + 1][0] = elementoActual;

                    arreglo[y][1] = elel1;
                    arreglo[y + 1][1] = ele1;

                    arreglo[y][2] = elel2;
                    arreglo[y + 1][2] = ele2;

                }
            }
        }

        Object[] obt = new Object[4];

        tblMetodos.metod.setRowCount(0);

        for (int M = 0; M < ter; M++) {

            //System.out.println(arreglo[M][0] + "  " + arreglo[M][1] + "  " + arreglo[M][2]);
            obt[0] = arreglo[M][0];//metodo
            obt[1] = arreglo[M][1];
            obt[2] = arreglo[M][2];

            if (buscardupl(obt[0].toString())) {
                continue;
            }

            tblMetodos.metod.addRow(obt);

        }

    }

    public static boolean buscardupl(String ar) {
        for (int i = 0; i < tblMetodos.metod.getRowCount(); i++) {
            String e = tblMetodos.metod.getValueAt(i, 0).toString();
            if (e.equals(ar)) {
                return true;
            }
        }
        return false;
    }

    public void seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logopro2.png")));   
        icon_save.setIcon(resizeIcon( new ImageIcon(getClass().getResource("/img/save.png")), icon_save.getWidth(), icon_save.getHeight()));
        icon_save_as.setIcon(resizeIcon( new ImageIcon(getClass().getResource("/img/save-as.png")), icon_save_as.getWidth(), icon_save_as.getHeight()));
        icon_new.setIcon(resizeIcon( new ImageIcon(getClass().getResource("/img/new_file.png")), icon_new.getWidth(), icon_new.getHeight()));
      
    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }
//private FileNameExtensionFilter filtro= new FileNameExtensionFilter ("Archivos de texto","txt");
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;

    public String AbrirArchivo(File archivo) {
        String documento = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;
            }
        } catch (Exception e) {
        }
        return documento;
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

    private void miCompileLexicalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCompileLexicalMouseReleased
        analisisLexico();
        System.out.println("Lexico");
    }//GEN-LAST:event_miCompileLexicalMouseReleased

    private void mnuSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSaveMouseReleased
        saveFile();
    }//GEN-LAST:event_mnuSaveMouseReleased

    private void mnuOpenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuOpenMouseReleased
       openFile();
    }//GEN-LAST:event_mnuOpenMouseReleased

    private void mnuSaveAsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSaveAsMouseReleased
        saveAsFile();
        
    }//GEN-LAST:event_mnuSaveAsMouseReleased

    private void mnuNewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuNewMouseReleased

        newFile();

    }//GEN-LAST:event_mnuNewMouseReleased

    private void mnuMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuMinimizeMousePressed
        mnuMinimize.setEnabled(false);
        jplAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 630);
        scPanAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 630);
        txtAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 630);
    }//GEN-LAST:event_mnuMinimizeMousePressed

    private void mnuFijaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuFijaMouseReleased
        new TablaFija().setVisible(true);
    }//GEN-LAST:event_mnuFijaMouseReleased

    private void miNomalModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miNomalModeMouseReleased
        txtAreaEdit.setForeground(Color.black);
        txtAreaEdit.setBackground(Color.white);
        tblTablaSimbolos.setForeground(Color.black);
        tblTablaSimbolos.setBackground(Color.white);
        txtConsolaPane.setForeground(Color.red);
        txtConsolaPane.setBackground(Color.white);
    }//GEN-LAST:event_miNomalModeMouseReleased

    private void miDynamicTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miDynamicTableMouseReleased
        /*TablaDinamica.me.setRowCount(0);
        rquicksort();*/

        showTD();
    }//GEN-LAST:event_miDynamicTableMouseReleased

    private void miDarkModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miDarkModeMouseReleased
        Color darkColor = new Color(42, 43, 46);
        txtAreaEdit.setForeground(Color.white);
        txtAreaEdit.setBackground(darkColor);

        tblTablaSimbolos.setForeground(Color.white);
        tblTablaSimbolos.setBackground(darkColor);
        txtConsolaPane.setForeground(Color.white);
        txtConsolaPane.setBackground(darkColor);
    }//GEN-LAST:event_miDarkModeMouseReleased

    private void miRetroModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miRetroModeMouseReleased
        txtAreaEdit.setForeground(Color.green);
        txtAreaEdit.setBackground(Color.black);

        tblTablaSimbolos.setForeground(Color.green);
        tblTablaSimbolos.setBackground(Color.black);
        txtConsolaPane.setForeground(Color.green);
        txtConsolaPane.setBackground(Color.black);
    }//GEN-LAST:event_miRetroModeMouseReleased

    private void miZoomInFontMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miZoomInFontMouseReleased
        Font fuente = new Font("Consolas", 1, tamanioletra + 5);
        txtAreaEdit.setFont(fuente);
        tamanioletra += 5;
    }//GEN-LAST:event_miZoomInFontMouseReleased

    private void miZoomOutFontMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miZoomOutFontMouseReleased
        Font fuente = new Font("Consolas", 1, tamanioletra - 5);
        txtAreaEdit.setFont(fuente);
        tamanioletra -= 5;
    }//GEN-LAST:event_miZoomOutFontMouseReleased
////cosas de antonio
    UndoManager editManager = new UndoManager();

    public void undoManager() {
        txtAreaEdit.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                editManager.addEdit(e.getEdit());
            }
        });
    }

    JMenuItem rehacerItem = new JMenuItem("Undo");

    public void deshacer() {
        if (editManager.canUndo()) {
            editManager.undo();
        }
    }

    JMenuItem deshacerItem = new JMenuItem("Redo");

    public void rehacer() {
        if (editManager.canRedo()) {
            editManager.redo();
        }
    }

    //fin cosas de antonio
    private void mnuUndoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuUndoMousePressed
        //UnDO
        deshacer();
    }//GEN-LAST:event_mnuUndoMousePressed

    private void mnuRedoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuRedoMousePressed
        //
        rehacer();
    }//GEN-LAST:event_mnuRedoMousePressed
    public void mostrarErrores() {
        if (txtConsolaPane.getText().equals("El programa no contiene errores :)")) {
            txtConsolaPane.setForeground(color_success);
            txtConsolaPane.setText("");
        }
        txtConsolaPane.setForeground(color_error);
        txtConsolaPane.setText(txtConsolaPane.getText() + Errores);
        if (!txtConsolaPane.getText().equals("")) {
            separaLineas();
        }
        if (txtConsolaPane.getText().equals("")) {
            txtConsolaPane.setForeground(color_success);
            txtConsolaPane.setText("Analisis realizado correctamente");
        }
    }

    public void separaLineas() {
        int i = 0;
        String[] lineas;
        String lin = txtConsolaPane.getText();
        lineas = lin.split("\n");
        //Linea: 5
        for (String linea : lineas) {
            String combinacion = linea.substring(8, 10);
            String replaceAll = combinacion.replaceAll("\\s", "");
            //System.out.println("q" + replaceAll + combinacion + linea);

        }

        buja(lineas, 0, lineas.length);
    }

    public void buja(String[] A, int ini, int ter) {

        String aux;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j < A.length - i; j++) {
                if (Integer.parseInt(A[j].substring(8, 10).replaceAll("\\s", "")) > Integer.parseInt((A[j + 1].substring(8, 10).replaceAll("\\s", "")))) {
                    aux = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = aux;
                }
            }
        }

        String erroresv = "";
        for (int M = 0; M < ter; M++) {
            erroresv += A[M] + '\n';
        }
        txtConsolaPane.setText(erroresv);
    }

    public void analisisLexico() {
        InformacionLexema c = new InformacionLexema();
        mnuMinimize.setEnabled(true);
        // jplAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 800, 630);
        //scPanAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 800, 423);
        //txtAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 800, 423);
        //jScrollPaneConsole.setBounds(jScrollPaneConsole.getX(), jScrollPaneConsole.getY(),
        //        jScrollPaneConsole.getX()+1200, jScrollPaneConsole.getY()+195);
        jPanel1.removeAll();
        labelLexicoErrors = new ArrayList<LabelError>();
        File fichero = new File("fichero.and");
        PrintWriter writer;

        try {
            writer = new PrintWriter(fichero);
            writer.print(txtAreaEdit.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = 0;
        txtConsolaPane.setText("");
        errores = "";
        int counter = 0;

        try {
            Reader lector = new BufferedReader(new FileReader("fichero.and"));
            Lexer lexer = new Lexer(lector);

            while (true) {

                m.setNumRows(i + 1);
                Tokens tokens = lexer.yylex();

                if (tokens == null) {
                    txtConsolaPane.setText(errores);
                    if (errores.equals("")) {
                        txtConsolaPane.setText("El programa no contiene errores :)\n");

                        txtConsolaPane.setForeground(color_success);
                    } else {
                        txtConsolaPane.setForeground(color_error);
                    }

                    System.out.println(labelLexicoErrors.size() + "");
                    if (!labelLexicoErrors.isEmpty()) {
                        System.out.println("llenar");
                        int num = 0;
                        for (int j = 0;j<labelLexicoErrors.size();j++) {
                            labelLexicoErrors.get(j).getLabel().setBounds(5, 5 + 15 * num, 500, 15 + 15 * num);
                            clickLabel(labelLexicoErrors.get(j));
                            jPanel1.add(labelLexicoErrors.get(j).getLabel());
                            num++;
                        }
                    }

                    return;
                }
                JLabel btn = new JLabel(lexer.yytext());
                btn.setForeground(color_error);
                btn.setFont(new Font("Verdana", Font.PLAIN, 12));
                btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                switch (tokens) {
                    case NUMERO_ERRONEO_MAS_PUNTOS:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        errores += (">Linea: " + (c.linea + 1) + " Columna: " + (c.columna) + ", Error Léxico,    Error cadena no valida:  " + lexer.yytext() + "\n");
                        i++;
                        btn.setText("Error en la liena: "+(c.linea+1)+". El número tiene puntos de más.");
                        labelLexicoErrors.add(new LabelError(btn,"NUMERO_ERRONEO_MAS_PUNTOS",c.linea+1));
                        counter++;
                        break;
                    case MAYUSCULAS_EN_CADENA:
                         tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        errores += (">Linea: " + (c.linea + 1) + " Columna: " + (c.columna) + ", Error Léxico,    Error cadena no valida:  " + lexer.yytext() + "\n");
                        i++;
                        btn.setText("Error en la liena: "+(c.linea+1)+". Hay una mayuscula.");
                        labelLexicoErrors.add(new LabelError(btn,"MAYUSCULAS_EN_CADENA",c.linea+1));
                        counter++;
                        break;
                    case MAL_NOMBRE_PARA_IDENTIFICADOR:
                         tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        errores += (">Linea: " + (c.linea + 1) + " Columna: " + (c.columna) + ", Error Léxico,    Error cadena no valida:  " + lexer.yytext() + "\n");
                        i++;
                        btn.setText("Error en la liena: "+(c.linea+1)+". El identificador esta mal escrito.");
                        labelLexicoErrors.add(new LabelError(btn,"MAL_NOMBRE_PARA_IDENTIFICADOR",c.linea+1));
                        counter++;
                        break;
                    case NUMERO_ERRONEO:
                         tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        errores += (">Linea: " + (c.linea + 1) + " Columna: " + (c.columna) + ", Error Léxico,    Error cadena no valida:  " + lexer.yytext() + "\n");
                        i++;
                        btn.setText("Error en la liena: "+(c.linea+1)+". El formato del numero es erroneo");
                        labelLexicoErrors.add(new LabelError(btn,"NUMERO_ERRONEO",c.linea+1));
                        counter++;
                        break;
                    case ERROR:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        errores += (">Linea: " + (c.linea + 1) + " Columna: " + (c.columna) + ", Error Léxico,    Error cadena no valida:  " + lexer.yytext() + "\n");
                        i++;
                        
                        btn.setText("Error en la liena: "+(c.linea+1)+". Error.");
                        labelLexicoErrors.add(new LabelError(btn,"ERROR",c.linea+1));
                        counter++;
                        break;
                    case Identificador:
                    case Numero:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt(tokens, i, 3);
                        i++;

                        break;
                    case Signo_de_igual:
                    case Signo_de_Suma:
                    case Signo_de_Resta:
                    case Signo_de_Multiplicación:
                    case Signo_de_División:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt(" Signo aritmetico: " + tokens, i, 3);
                        i++;
                        break;
                    case Parentesis_Abre:
                    case Parentesis_Cierra:
                    case Llave_Abre:
                    case Llave_Cierra:
                    case Corchete_Abre:
                    case Corchete_Cierra:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt(" Signo agrupacion: " + tokens, i, 3);
                        i++;

                        break;

                    case Menor_que:
                    case Mayor_que:
                    case Menor_o_igual_que:
                    case Mayor_o_igual_que:
                    case Diferente_que:
                    case Igual_que:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt(" Operador relacional: " + tokens, i, 3);
                        i++;

                        break;

                    case Mas_Mas:
                    case Menos_Menos:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt(" Operador incremental: " + tokens, i, 3);
                        i++;
                        break;

                    case Coma:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("Coma : " + tokens, i, 3);
                        i++;

                        break;

                    case PuntoYComa:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt(" Fin de sentencia: " + tokens, i, 3);
                        i++;

                        break;
                    case Else:
                    case While:
                    case End:
                    case Same:
                    case For:
                    case If:
                    case Get:
                    case Give:
                    case Select:
                    case Empty:
                    case Flag:
                    case Cut:
                    case Just:
                    case Begin:
                    case Model:
                    case Defect:
                    case New:
                    case Do:
                    case Goback:
                    case Broken:
                    case Home:
                    case Sleep:
                    case Method:
                    case Check:
                    case Trap:
                    case Class:
                    case Main:
                    case Stop:
                    case Getextrusorx:
                    case Getextrusory:
                    case Getextrusorz:
                    case Getfilamenttype:
                    case Setnewfilament:
                    case Getfilament:
                    case Gettemperature:
                    case Fillrectangle:
                    case Drawrectangle:
                    case Drawcircle:
                    case Fillcircle:
                    case Printerport:
                    case Setfilamenttype:
                    case Word:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("RESERVADA", i, 3);
                        i++;
                        break;
                    case Cadena:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("Constante de caracter", i, 3);
                        i++;
                        break;
                    default:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("Identificador", i, 3);
                        i++;
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static String Errores = "";
    private void miCompileSyntaxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCompileSyntaxMouseReleased
       analisisSintactico();
    }//GEN-LAST:event_miCompileSyntaxMouseReleased

    private void miMethodMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miMethodMouseReleased
        tblMetodos.setVisible(true);
        buscaMethod();
    }//GEN-LAST:event_miMethodMouseReleased

    private void btnayudaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnayudaMouseReleased

    }//GEN-LAST:event_btnayudaMouseReleased

    private void btnayudaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnayudaMousePressed

        try {
            Desktop d = Desktop.getDesktop();
            d.browse(URI.create("https://sites.google.com/ittepic.edu.mx/anadan"));
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_btnayudaMousePressed

    private void miCompileSyntaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCompileSyntaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miCompileSyntaxActionPerformed

    private void icon_newMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_newMouseReleased
        newFile();
    }//GEN-LAST:event_icon_newMouseReleased

    private void icon_abrirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_abrirMouseReleased
       openFile();
    }//GEN-LAST:event_icon_abrirMouseReleased

    private void icon_saveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_saveMouseReleased
        saveFile();
    }//GEN-LAST:event_icon_saveMouseReleased

    private void icon_save_asMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_save_asMouseReleased
        saveAsFile();
    }//GEN-LAST:event_icon_save_asMouseReleased

    private void icon_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_tableMouseReleased
        showTD();
    }//GEN-LAST:event_icon_tableMouseReleased

    private void icon_lexicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_lexicoMouseReleased
        analisisLexico();
    }//GEN-LAST:event_icon_lexicoMouseReleased

    private void icon_sintacticMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_sintacticMouseReleased
        analisisSintactico();
    }//GEN-LAST:event_icon_sintacticMouseReleased

    private void icon_runMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_runMouseReleased
        analisisSintactico();
    }//GEN-LAST:event_icon_runMouseReleased

    private void icon_undoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_undoMouseReleased
        deshacer();
    }//GEN-LAST:event_icon_undoMouseReleased

    private void icon_redoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_redoMouseReleased
        rehacer();
    }//GEN-LAST:event_icon_redoMouseReleased

    public void rquicksort() {

        int col = m.getColumnCount();
        int ren = m.getRowCount() - 1;

        if (ren == -1) {
            showMessageDialog(null, "No existen identificadores");
            return;
        }
        String[][] arreglo = new String[ren][col];
        if (ren == 0) {
            return;
        }
        for (int i = 0; i < ren; i++) {

            arreglo[i][0] = m.getValueAt(i, 2).toString();//lexema
            arreglo[i][1] = m.getValueAt(i, 3).toString(); //componentye lexico
        }

        //burbuja(arreglo, 0, ren);
    }

    public void burbuja(String[][] arreglo, int ini, int ter) {

        for (int x = 0; x < ter - 1; x++) {

            for (int y = 0; y < ter - 1; y++) {
                String elementoActual = arreglo[y][0];
                String elementoSiguiente = arreglo[y + 1][0];

                String elem1 = arreglo[y][1];
                String element1 = arreglo[y + 1][1];
                if (elementoActual.compareTo(elementoSiguiente) > 0) {
                    // Intercambiar
                    arreglo[y][0] = elementoSiguiente;
                    arreglo[y + 1][0] = elementoActual;

                    arreglo[y][1] = element1;
                    arreglo[y + 1][1] = elem1;

                }
            }
        }

        Object[] object = new Object[4];
        Object[] obj = new Object[1];

        for (int M = 0; M < ter; M++) {

            //System.out.println(arreglo[M][0] + "  " + arreglo[M][1] + "  " + arreglo[M][2]);
            object[0] = arreglo[M][0];//lexema
            obj[0] = arreglo[M][1]; //tipo 
            object[2] = arreglo[M][2]; //valor

            if (buscardup(object[0].toString()) || buscarid(obj[0].toString())) {
                continue;
            }

            TablaDinamica.me.addRow(object);

        }

        //tabaDinamica.setVisible(true);
    }

    public boolean buscardup(String ar) {
        for (int i = 0; i < TablaDinamica.me.getRowCount(); i++) {
            String e = TablaDinamica.me.getValueAt(i, 0).toString();
            if (e.equals(ar)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarid(String ar) {
        if (ar.equals("Identificador")) {
            return false;
        }
        return true;
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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
    String errores = "";
    String name;
    public int tamanioletra = 12;
    private DefaultTableModel m = new DefaultTableModel();
    public String arreglo[][];
    //TablaDinamica tabaDinamica = new TablaDinamica();

    public static TablaMetodos tblMetodos = new TablaMetodos();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenu btnayuda;
    private javax.swing.JLabel icon_abrir;
    private javax.swing.JLabel icon_lexico;
    private javax.swing.JLabel icon_new;
    private javax.swing.JLabel icon_redo;
    private javax.swing.JLabel icon_run;
    private javax.swing.JLabel icon_save;
    private javax.swing.JLabel icon_save_as;
    private javax.swing.JLabel icon_sintactic;
    private javax.swing.JLabel icon_table;
    private javax.swing.JLabel icon_undo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneConsole;
    private javax.swing.JPanel jplAreaEdit;
    private javax.swing.JMenuItem miCompile;
    private javax.swing.JMenuItem miCompileLexical;
    private javax.swing.JMenuItem miCompileSyntax;
    private javax.swing.JMenuItem miDarkMode;
    private javax.swing.JMenuItem miDynamicTable;
    private javax.swing.JMenuItem miMethod;
    private javax.swing.JMenuItem miNomalMode;
    private javax.swing.JMenuItem miRetroMode;
    private javax.swing.JMenuItem miZoomInFont;
    private javax.swing.JMenu mnuCompile;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenuItem mnuFija;
    private javax.swing.JMenu mnuMinimize;
    private javax.swing.JMenuItem mnuNew;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenu mnuRedo;
    private javax.swing.JMenuItem mnuSave;
    private javax.swing.JMenuItem mnuSaveAs;
    private javax.swing.JMenu mnuTablaS;
    private javax.swing.JMenu mnuUndo;
    private javax.swing.JMenu mnufile;
    private javax.swing.JPanel panelToolBar;
    private javax.swing.JScrollPane scPanAreaEdit;
    public static javax.swing.JTable tblTablaSimbolos;
    private javax.swing.JTextArea txtAreaEdit;
    private javax.swing.JEditorPane txtConsolaPane;
    // End of variables declaration//GEN-END:variables

    private void showModalLexical(LabelError err) {
        ModalLexico ml = new ModalLexico(err);
        if(ml.isOpen()){
            ml.close();
        }
        ml.setVisible(true);
    }

    private void clickLabel(LabelError le) {
        le.getLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
           }

            @Override
            public void mousePressed(MouseEvent e) {
         }

            @Override
            public void mouseReleased(MouseEvent e) {
                showModalLexical(le);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                   }

            @Override
            public void mouseExited(MouseEvent e) {
                 }
        });
    }

    private void newFile() {
        if (seleccionar.showDialog(null, "Nuevo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            name = archivo.getAbsolutePath();

            if (archivo.getName().endsWith("txt")) {
                String Documento = "";
                txtAreaEdit.setText("");
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {

                } else {
                    showMessageDialog(null, "Archivo no compatible");
                }
            } else {
                showMessageDialog(null, "Fallo al guardar, coloque extencion .txt");
            }
        }
    }

    private void openFile() {
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();

            name = archivo.getPath();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String documento = AbrirArchivo(archivo);
                    txtAreaEdit.setText(documento);
                } else {
                    showMessageDialog(null, "Archivo no compatible");
                }
            }
        }
    }

    private void saveFile() {
        if (name == null) {

            if (seleccionar.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {

                archivo = seleccionar.getSelectedFile();
                if (archivo.getName().endsWith("txt")) {
                    String Documento = txtAreaEdit.getText();
                    String mensaje = GuardarArchivo(archivo, Documento);
                    if (mensaje != null) {
                        showMessageDialog(null, mensaje);
                    } else {
                        showMessageDialog(null, "Archivo no compatible");
                    }
                } else {
                    showMessageDialog(null, "Fallo al guardar, coloque extencion .txt");
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
            escribir.print(txtAreaEdit.getText());
            escribir.close();

            DesktopNotify.showDesktopMessage("ANADAN-INFORMATION", "Actualizaciones guardadas", DesktopNotify.SUCCESS, 5000L);
            //   showMessageDialog(null,"Actualizaciones guardadas");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveAsFile() {
      if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();

            if (archivo.getName().endsWith("txt")) {
                String Documento = txtAreaEdit.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    showMessageDialog(null, mensaje);
                } else {
                    showMessageDialog(null, "Archivo no compatible");
                }
            } else {
                showMessageDialog(null, "Fallo al guardar, coloque extencion .txt");
            }
        }
    }

    private void showTD() {
        for (int i = 0; i < m.getRowCount() - 1; i++) {
            int linea = Integer.parseInt(m.getValueAt(i, 1).toString());
            String lexema = m.getValueAt(i, 2).toString();
            String componente = m.getValueAt(i, 3).toString();
            if (componente.equals("Identificador")) {
                simbolos.add(new Simbolos(componente, linea, lexema, "", ""));
            }
        }

        Collections.sort(simbolos);

        TablaDinamica ts = new TablaDinamica(simbolos);
        ts.setVisible(true);
    }

    private void analisisSintactico() {
        analisisLexico();

        Errores = "";

        mnuMinimize.setEnabled(true);
        //jplAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 370);
        //scPanAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 370);
        //txtAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 370);

        String ST = txtAreaEdit.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        try {
            s.parse();
        } catch (Exception ex) {
            //Symbol sym = s.getS();
            //Errores+=("    >Linea: "+(sym.right+1)+" Columna: "+(sym.left+1)+", Error en la estructura: "+ sym.value);
            //txtConsola.setForeground(Color.red);

            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarErrores();
    }
}
