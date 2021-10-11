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
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIDefaults;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.UndoManager;
import modelos.LabelError;
import modelos.Simbolos;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import pila.Pila;

import static util.Utils.*;

public class VentanaPrincipal extends javax.swing.JFrame {

    public static ArrayList<LabelError> labelsErrores;
    private ArrayList<Simbolos> simbolos = new ArrayList<Simbolos>();

    public static boolean errores_lexicos = false;
    public static boolean errores_sintacticos = false;
    public static String exp = "";

    private int coor_7 = 0;
    private int height = 0;

    //ESTO ES PARA MARCAR CON COLORES LAS PALABRAS////////////////////////////////////////////////////////////////////////
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public VentanaPrincipal() {
        initComponents();
        seticon();
        undoManager();
        this.setTitle("ANADAN");
        setLocationRelativeTo(null);
        m = (DefaultTableModel) tblTablaSimbolos.getModel();
        numeroLineas();
        mnuMinimize.setEnabled(false);

        //UIDefaults defs = UIManager.getDefaults();
        //defs.put("TextPane.background", new ColorUIResource(Color.BLACK));
        //defs.put("TextPane.inactiveBackground", new ColorUIResource(Color.BLACK));
        //txtAreaEdit.updateUI(defs);
        //CAMBIAR COLOR DEL JTEXTPANE-----------------------------------------------------------------
        Color bgColor = new Color(42, 43, 46);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane.background", new ColorUIResource(bgColor));
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        txtAreaEdit.putClientProperty("Nimbus.Overrides", defaults);
        txtAreaEdit.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        txtAreaEdit.setBackground(bgColor);

        //bgColor = new Color(42, 43, 46);
        //UIDefaults defaults1 = new UIDefaults();
        //defaults1.put("EditorPane.background", new ColorUIResource(bgColor));
        //defaults1.put("EditorPane[Enabled].backgroundPainter", bgColor);
        //txtConsolaPane.putClientProperty("Nimbus.Overrides", defaults1);
        //txtConsolaPane.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        //txtConsolaPane.setBackground(bgColor);
        Color darkColor = new Color(153, 153, 153);
        tblTablaSimbolos.setForeground(Color.WHITE);
        tblTablaSimbolos.setBackground(darkColor);
        //---------------------------------------------------------------------------------------------

        Font fuente2 = new Font("Consolas", 1, tamanioletra + 5);
        tamanioletra++;
        txtAreaEdit.setFont(fuente2);
        ///ESTO ES PARA MARCAR CON COLORES LAS PALABRAS////////////////////////////////////////////////////////////////////////
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(174, 71, 178));
        final AttributeSet attrBlue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(13, 72, 233));
        final AttributeSet attrOrange = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(234, 76, 0));
        final AttributeSet attrPink = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(234, 0, 91));
        final AttributeSet attrWhite = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(248, 243, 245));
        final AttributeSet attrCom = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(192, 197, 211));
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(just|broken|word|flag|word)")) {
                            //setCharacterAttributes(wordL, wordL+1, attrWhite, false);
                            setCharacterAttributes(wordL + 1, wordR - wordL, attr, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(begin|end|setfilamenttype|fillrectangle|drawrectangle|sleep|fillcircle|drawcircle|drawtriangle|filltriangle|stop|getextrusorx|getextrusory|getextrusorz|getfilamenttype|setnewfilament|getfilament|gettemperatura|same|get|give|select|empty|cut|model|defect|new|goback|home|sleep|printerport|check|trap)")) {
                            setCharacterAttributes(wordL + 1, wordR - wordL, attrBlue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(method|class|main)")) {
                            setCharacterAttributes(wordL + 1, wordR - wordL, attrOrange, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(if|else|while|for|switch|do)")) {
                            setCharacterAttributes(wordL + 1, wordR - wordL, attrPink, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, attrWhite, false);
                        }
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*(private|public|protected)")) {
                    setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrWhite, false);
                }
            }
        };
        txtAreaEdit.setDocument(doc);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplAreaEdit = new javax.swing.JPanel();
        scPanAreaEdit = new javax.swing.JScrollPane();
        txtAreaEdit = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablaSimbolos = new javax.swing.JTable();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
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

        txtAreaEdit.setForeground(new java.awt.Color(248, 243, 245));
        txtAreaEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaEditKeyTyped(evt);
            }
        });
        scPanAreaEdit.setViewportView(txtAreaEdit);

        javax.swing.GroupLayout jplAreaEditLayout = new javax.swing.GroupLayout(jplAreaEdit);
        jplAreaEdit.setLayout(jplAreaEditLayout);
        jplAreaEditLayout.setHorizontalGroup(
            jplAreaEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplAreaEditLayout.createSequentialGroup()
                .addComponent(scPanAreaEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
        );
        jplAreaEditLayout.setVerticalGroup(
            jplAreaEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplAreaEditLayout.createSequentialGroup()
                .addComponent(scPanAreaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jplAreaEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 700, 390));

        tblTablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 430, 390));

        panelToolBar.setBackground(new java.awt.Color(153, 153, 153));
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

        getContentPane().add(panelToolBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 50));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 900));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(1228, 1000));
        jPanel1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1228, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 1130, 250));
        jScrollPane2.getAccessibleContext().setAccessibleName("");

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
        icon_save.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/img/save.png")), icon_save.getWidth(), icon_save.getHeight()));
        icon_save_as.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/img/save-as.png")), icon_save_as.getWidth(), icon_save_as.getHeight()));
        icon_new.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/img/new_file.png")), icon_new.getWidth(), icon_new.getHeight()));

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
        /*mnuMinimize.setEnabled(false);
        jplAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 630);
        scPanAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 630);
        txtAreaEdit.setBounds(jplAreaEdit.getX(), jplAreaEdit.getY(), 1230, 630);*/
    }//GEN-LAST:event_mnuMinimizeMousePressed

    private void mnuFijaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuFijaMouseReleased
        new TablaFija().setVisible(true);
    }//GEN-LAST:event_mnuFijaMouseReleased

    private void miNomalModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miNomalModeMouseReleased
        //txtAreaEdit.setForeground(Color.black);
        //txtAreaEdit.setBackground(Color.white);
        tblTablaSimbolos.setForeground(Color.black);
        tblTablaSimbolos.setBackground(Color.white);

        //CAMBIAR COLOR DEL JTEXTPANE-----------------------------------------------------------------
        //Color bgColor = new Color(255, 255, 255);
        //UIDefaults defaults = new UIDefaults();
        //defaults.put("TextPane.background", new ColorUIResource(bgColor));
        //defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        //txtAreaEdit.putClientProperty("Nimbus.Overrides", defaults);
        //txtAreaEdit.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        //txtAreaEdit.setBackground(bgColor);
        //---------------------------------------------------------------------------------------------
        //txtAreaEdit.setForeground(Color.black);

    }//GEN-LAST:event_miNomalModeMouseReleased

    private void miDynamicTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miDynamicTableMouseReleased
        /*TablaDinamica.me.setRowCount(0);
        rquicksort();*/

        showTD();
    }//GEN-LAST:event_miDynamicTableMouseReleased

    private void miDarkModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miDarkModeMouseReleased
        //Color darkColor = new Color(42, 43, 46);
        //txtAreaEdit.setForeground(Color.white);
        //txtAreaEdit.setBackground(darkColor);

        //tblTablaSimbolos.setForeground(Color.white);
        //tblTablaSimbolos.setBackground(darkColor);
        //txtConsolaPane.setForeground(Color.white);
        //txtConsolaPane.setBackground(darkColor);
    }//GEN-LAST:event_miDarkModeMouseReleased

    private void miRetroModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miRetroModeMouseReleased
        txtAreaEdit.setForeground(Color.green);
        txtAreaEdit.setBackground(Color.black);

        tblTablaSimbolos.setForeground(Color.green);
        tblTablaSimbolos.setBackground(Color.black);
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
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
        Collections.sort(labelsErrores);
        for (LabelError labelsErrore : labelsErrores) {
            if (labelsErrore.getTipo() == SINTACTICO) {
                errores_sintacticos = true;
            }
            System.out.println(labelsErrore);
        }

        if (labelsErrores.isEmpty()) {
            JLabel lb = new JLabel("El programa no contiene errores sintacticos.");
            lb.setForeground(color_success);
            lb.setBounds(5, 5, 700, 20);
            lb.setFont(new Font("Verdana", Font.PLAIN, 14));
            jPanel1.add(lb);
        } else {
            int num = 0;
            int y = 0;
            if (!errores_lexicos) {
                JLabel lb = new JLabel("El programa no contiene errores lexicos.");
                lb.setForeground(color_success);
                lb.setBounds(5, 5, 700, 20);
                lb.setFont(new Font("Verdana", Font.PLAIN, 14));
                jPanel1.add(lb);
                num=1;
            }

            for (LabelError le : labelsErrores) {
                y = 5 + 30 * num;
                le.getLabel().setBounds(5, y, 700, 20);
                clickLabel(le);
                jPanel1.add(le.getLabel());
                num++;
            }
        }
        if (!exp.isEmpty() && !errores_sintacticos) {
            resolverExp(exp);
        }

        /* if (!errores_lexicos ) {
            lb.setForeground(color_success);
            lb.setBounds(5, 5, 700, 20);
            lb.setFont(new Font("Verdana", Font.PLAIN, 14));
            jPanel1.add(lb);
            System.out.println("Sin errores lexicos");
                System.out.println(lb.getText());
                showMessageDialog(null, "1");
            if(!errores_sintacticos){
                System.out.println("Sin errores sintacticos");
                lb.setText("El programa no contiene errores sintacticos.");
                lb.setBounds(5, 25, 700, 20);
                lb.setFont(new Font("Verdana", Font.PLAIN, 14));
                jLabel1.add(lb);
                System.out.println(lb.getText());
                showMessageDialog(null, "2");
            }else{
                int num = 0;
                int y = 0;
                for (LabelError le : labelsErrores) {
                    y = 5 +30*num;
                    System.out.println("y:"+y);
                    le.getLabel().setBounds(5, y, 700, 20);
                    System.out.println(le.getLabel().getY());
                    clickLabel(le);
                    jPanel1.add(le.getLabel());
                    num++;
                    System.out.println("Mostrando errores lexicos y sintacticos");
                showMessageDialog(null, "3");
                }
            }
        }else{
            int num = 0;
            int y = 0;
            for (LabelError le : labelsErrores) {
                y = 5 +30*num;
                System.out.println("y:"+y);
                le.getLabel().setBounds(5, y, 700, 20);
                System.out.println(le.getLabel().getY());
                clickLabel(le);
                jPanel1.add(le.getLabel());
                num++;
                System.out.println("Mostrando errores lexicos y sintacticos");
                showMessageDialog(null, "4");
            }            
         }     */
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
    }

    public void analisisLexico() {
        errores_lexicos = false;
        errores_sintacticos = false;
        InformacionLexema c = new InformacionLexema();
        mnuMinimize.setEnabled(true);
        labelsErrores = new ArrayList<LabelError>();
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
        errores = "";
        int counter = 0;

        try {
            Reader lector = new BufferedReader(new FileReader("fichero.and"));
            Lexer lexer = new Lexer(lector);

            while (true) {

                m.setNumRows(i + 1);
                Tokens tokens = lexer.yylex();

                if (tokens == null) {
                    if (counter == 0) {
                        errores_lexicos = false;
                    } else {
                        errores_lexicos = true;
                    }
                    return;
                }
                JLabel lb = new JLabel(lexer.yytext());
                lb.setForeground(color_error);
                lb.setFont(new Font("Verdana", Font.PLAIN, 14));
                lb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                switch (tokens) {
                    case NUMERO_ERRONEO_MAS_PUNTOS:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        i++;
                        lb.setText("Error en la línea " + (c.linea + 1) + ". Error léxico. El número '" + lexer.yytext() + "' tiene puntos de más.");
                        labelsErrores.add(new LabelError(lb, "NUMERO_ERRONEO_MAS_PUNTOS", c.linea + 1, LEXICO));
                        counter++;
                        break;
                    case MAYUSCULAS_EN_CADENA:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        i++;
                        lb.setText("Error en la línea " + (c.linea + 1) + ". Error léxico. Las mayúsculas no están definidas en el lenguaje: '" + lexer.yytext() + "'");
                        labelsErrores.add(new LabelError(lb, "MAYUSCULAS_EN_CADENA", c.linea + 1, LEXICO));
                        counter++;
                        break;
                    case MAL_NOMBRE_PARA_IDENTIFICADOR:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        i++;
                        lb.setText("Error en la línea " + (c.linea + 1) + ". Error léxico. El identificador '" + lexer.yytext() + "' está mal escrito.");
                        labelsErrores.add(new LabelError(lb, "MAL_NOMBRE_PARA_IDENTIFICADOR", c.linea + 1, LEXICO));
                        counter++;
                        break;
                    case NUMERO_ERRONEO:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        i++;
                        lb.setText("Error en la línea " + (c.linea + 1) + ". Error léxico. El formato del número '" + lexer.yytext() + "' es erroneo");
                        labelsErrores.add(new LabelError(lb, "NUMERO_ERRONEO", c.linea + 1, LEXICO));
                        counter++;
                        break;
                    case ERROR:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("ERROR:CADENA_NO_VALIDA", i, 3);
                        i++;

                        lb.setText("Error en la línea " + (c.linea + 1) + ". Error léxico. Error '" + lexer.yytext() + "'");
                        labelsErrores.add(new LabelError(lb, "ERROR", c.linea + 1, LEXICO));
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
                        tblTablaSimbolos.setValueAt("Signo aritmetico: " + tokens, i, 3);
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
                        tblTablaSimbolos.setValueAt("Signo agrupacion: " + tokens, i, 3);
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
                        tblTablaSimbolos.setValueAt("Operador relacional: " + tokens, i, 3);
                        i++;

                        break;

                    case Mas_Mas:
                    case Menos_Menos:
                        tblTablaSimbolos.setValueAt(c.columna, i, 0);
                        tblTablaSimbolos.setValueAt(c.linea + 1, i, 1);
                        tblTablaSimbolos.setValueAt(lexer.yytext(), i, 2);
                        tblTablaSimbolos.setValueAt("Operador incremental: " + tokens, i, 3);
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
                        tblTablaSimbolos.setValueAt("Fin de sentencia: " + tokens, i, 3);
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
                    case True:
                    case False:
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

    private void txtAreaEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaEditKeyTyped
        txtAreaEdit.setForeground(new Color(248, 243, 245));
    }//GEN-LAST:event_txtAreaEditKeyTyped

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
    public int abc;

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
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTextPane txtAreaEdit;
    // End of variables declaration//GEN-END:variables
      ImageIcon imgENum1 = new ImageIcon("C:/ANADAN/src/Automatas/NumeroErroneo.jpg");
    public Icon NumErr1 = new ImageIcon(imgENum1.getImage().getScaledInstance(467, 318, Image.SCALE_DEFAULT));

    ImageIcon imgENum2 = new ImageIcon("C:/ANADAN/src/Automatas/NumeroErroneoMasSignosAlPrincipio.jpg");
    public Icon NumErr2 = new ImageIcon(imgENum2.getImage().getScaledInstance(362, 315, Image.SCALE_DEFAULT));

    ImageIcon imgEId1 = new ImageIcon("C:/ANADAN/src/Automatas/Identificador_inicio_mal.jpg");
    public Icon IdErr2 = new ImageIcon(imgEId1.getImage().getScaledInstance(481, 141, Image.SCALE_DEFAULT));

    ImageIcon imgECad1 = new ImageIcon("C:/ANADAN/src/Automatas/Cadena.jpg");
    public Icon CadErr1 = new ImageIcon(imgECad1.getImage().getScaledInstance(406, 136, Image.SCALE_DEFAULT));

    private void showModalLexical(LabelError err) {
        ModalLexico ml = new ModalLexico(err);

        //  err.get
        switch (err.getError()) {

            case "NUMERO_ERRONEO_MAS_PUNTOS":
                ModalLexico.jlbAutomata.setIcon(NumErr1);
                if (ml.isOpen()) {
                    ml.close();
                }

                //  System.out.println(err.getLabel().get);
                ModalLexico.jlbEstados.setText("Q={q1,q2,q3,q4,q5,q6,q7,q8,q9}");
                ModalLexico.jlbInicial.setText("S=q1");
                ModalLexico.jlbFinal.setText("F={q2,q4,q7,q9}");
                ModalLexico.txtAlfabeto.setText("Σ={0, 1, 2, 3, 4, 5, 6, 7,8, 9, +, -, *, /, ^, e, .}");
                ModalLexico.InfoError.setText("El error se provoca al intentar salir del estado (q4) con un punto");
                ml.setVisible(true);
                break;

            case "NUMERO_ERRONEO":
                ModalLexico.jlbAutomata.setIcon(NumErr2);
                if (ml.isOpen()) {
                    ml.close();
                }
                ModalLexico.jlbEstados.setText("Q={q1,q2,q3,q4,q5,q6,q7,q8,q9}");
                ModalLexico.jlbInicial.setText("S=q1");
                ModalLexico.jlbFinal.setText("F={q2,q4,q7,q9}");
                ModalLexico.txtAlfabeto.setText("Σ={0, 1, 2, 3, 4, 5, 6, 7,8, 9, +, -, *, /, ^, e, .}");
                ModalLexico.InfoError.setText("El error se provoca al intentar salir del estado (q2) con otra cosa que no sea un punto un digito o una 'e'");
                ml.setVisible(true);
                break;
            case "MAL_NOMBRE_PARA_IDENTIFICADOR":
                ModalLexico.jlbAutomata.setIcon(IdErr2);
                if (ml.isOpen()) {
                    ml.close();
                }
                ModalLexico.jlbEstados.setText("Q={q1,q2}");
                ModalLexico.jlbInicial.setText("S=q1");
                ModalLexico.jlbFinal.setText("F={q2}");
                ModalLexico.txtAlfabeto.setText("Σ={0, 1, 2, 3, 4, 5, 6, 7,8, 9,a,b,c,d,e,f,g,h,i,j,k,l,n,m,o,p,q,r,s,t,u,v,w,x,y,z,_}");
                ModalLexico.InfoError.setText("Los identificadores solo pueden iniciar con una letra y/o guion bajo");
                ml.setVisible(true);
                break;
            case "MAYUSCULAS_EN_CADENA":
                ModalLexico.jlbAutomata.setIcon(CadErr1);
                if (ml.isOpen()) {
                    ml.close();
                }
                ModalLexico.jlbEstados.setText("Q={q1}");
                ModalLexico.jlbInicial.setText("S=q1");
                ModalLexico.jlbFinal.setText("F={q1}");
                ModalLexico.txtAlfabeto.setText("Σ={a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, 0, 1, 2, 3,\n"
                        + "4, 5, 6, 7, 8, 9, !,\n"
                        + "%, ^, &, *, (, ), -, +, =, {, }, [, ], \\, |, ;, :, ”, <, ,, >, ., ?, /, \n"
                        + "tabulador, espacio en blanco, nueva\n"
                        + "línea, retorno de carro}");
                ModalLexico.InfoError.setText("Las mayusculas no se reconocen en este lenguaje");
                ml.setVisible(true);
                break;

            default:
                break;
        }

    }

    private void showModalSintaxtic(LabelError err) {
        System.out.println(err);
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
                System.out.println("Desde 1719: " + le);
                if (le.getTipo() == LEXICO) {
                    showModalLexical(le);
                } else {
                    if (le.getGramatica() != null) {
                        showMessageDialog(null, "Estamos trabajando en modal de gramáticas\n"
                                + le.getGramatica().getProduccion() + "\n" + le.getGramatica().getError());
                    } else {
                        showMessageDialog(null, "Estamos trabajando en modal de gramáticas\n");
                    }
                }
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
        /* if (seleccionar.showDialog(null, "Nuevo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            name = archivo.getAbsolutePath();

            if (archivo.getName().endsWith("txt")) {
                String Documento = "";
                txtAreaEdit.setText("");
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    jPanel1.removeAll();
                    jPanel1.revalidate();
                    jPanel1.repaint();
                } else {
                    showMessageDialog(null, "Archivo no compatible");
                }
            } else {
                showMessageDialog(null, "Fallo al guardar, coloque extencion .txt");
            }
        }*/
        int resp = JOptionPane.showConfirmDialog(null,
                "¿Desea guardar el Archivo?", "Advertencia", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (resp == 0) {
            mnuSave.doClick();
            limpiar();
        }
        if (resp == 1) {
            limpiar();
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
                    jPanel1.removeAll();
                    jPanel1.revalidate();
                    jPanel1.repaint();
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
        for (int i = 0; i < simbolos.size(); i++) {
            simbolos.remove(i);
        }
        for (int i = 0; i < m.getRowCount() - 1; i++) {
            int linea = Integer.parseInt(m.getValueAt(i, 1).toString());
            String lexema = m.getValueAt(i, 2).toString();
            String componente = m.getValueAt(i, 3).toString();
            if (componente.equals("Identificador")) {
                if (!existe(lexema)) {
                    simbolos.add(new Simbolos(componente, linea, lexema, "", ""));
                }
            }
        }

        Collections.sort(simbolos);

        TablaDinamica ts = new TablaDinamica(simbolos);
        ts.setVisible(true);
    }

    private boolean existe(String lexema) {
        for (int i = 0; i < simbolos.size(); i++) {
            if (lexema.equals(simbolos.get(i).getLexema())) {
                return true;
            }
        }
        return false;
    }

    private void analisisSintactico() {
        analisisLexico();
        TablaSimbolos.limpiar();

        mnuMinimize.setEnabled(true);
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

    private void resolverExp(String str) {
        str = str.replace(" ", "");
        String s1[] = str.split("=");
        str = s1[1];
        String newStr = "(";
        str = str.replace(" ", "");
        Pila pila = new Pila();

        //Agregando parentesis entre terminos de suma y resta
        String c[] = str.split("");
        for (int i = 0; i < c.length; i++) {
            if ("-".equals(c[i])) {
                newStr += ")-(";
            } else {
                if ("+".equals(c[i])) {
                    newStr += ")+(";
                } else {
                    newStr += c[i];
                }
            }
        }
        //System.out.println(newStr);

        //Creado pila con operadores y operandos en notacion infija
        pila.push("=");
        newStr = newStr + ")";
        String s2[] = newStr.split("");
        for (int i = 0; i < s2.length; i++) {
            pila.push(s2[i]);
        }
        Pila newPila = new Pila();
        String termino = "";
        while (!pila.isEmpty()) {
            String character = pila.pop();
            if ("*".equals(character)
                    || "/".equals(character)
                    || "+".equals(character)
                    || "-".equals(character)
                    || "(".equals(character)
                    || ")".equals(character)
                    || "=".equals(character)) {
                newPila.push(termino);
                newPila.push(character);
                termino = "";
            } else {
                termino = character + termino;
            }
        }

        newPila.push(s1[0]);
        //System.out.print("154 newPila: ");
        //newPila.imprimir();
        //Haciendo notacion postfija
        String postfija = "";
        Pila operadores = new Pila();

        while (!newPila.isEmpty()) {
            String character = newPila.pop();
            if ("*".equals(character)
                    || "/".equals(character)
                    || "+".equals(character)
                    || "-".equals(character)
                    || "(".equals(character)
                    || "=".equals(character)) {
                operadores.push(character);
            } else {
                if (")".equals(character)) {
                    String op = operadores.pop();
                    while (!"(".equals(op)) {
                        postfija += op + " ";
                        op = operadores.pop();
                    }
                } else {
                    //System.out.println(character);
                    postfija += character + " ";
                    //System.out.println(postfija);
                }
            }
        }

        //System.out.print("183 pilaOp: ");
        //operadores.imprimir();
        while (!operadores.isEmpty()) {
            String op = operadores.pop();
            //System.out.println("187 op: "+op);
            postfija += op + " ";
            //op = operadores.pop();

        }

        //System.out.println("189 Postfija: "+postfija);
        String post[] = postfija.split(" ");

        int i = 0;
        for (String string : post) {
            if (!string.isEmpty()) {
                i++;
            }
        }
        //System.out.println(i);
        String post_sin_espacios[] = new String[i];
        int j = 0;
        for (String string : post) {
            if (!string.isEmpty()) {
                post_sin_espacios[j++] = string;
            }
        }

        Pila pila_exp = new Pila();

        for (String exp : post_sin_espacios) {
            pila_exp.imprimir();
            if ("*".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op1 + "*" + op2 + "=" + (op1 * op2));
                pila_exp.push((op1 * op2) + "");
                continue;
            }
            if ("/".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op2 + "/" + op1 + "=" + (op2 / op1));
                pila_exp.push((op2 / op1) + "");
                continue;
            }
            if ("+".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op1 + "+" + op2 + "=" + (op1 + op2));
                pila_exp.push((op1 + op2) + "");
                continue;
            }
            if ("-".equals(exp)) {
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                System.out.println(op1 + "-" + op2 + "=" + (op1 - op2));
                pila_exp.push((op2 - op1) + "");
                continue;
            }
            if ("=".equals(exp)) {/*
                double op1 = Double.parseDouble(pila_exp.pop());
                double op2 = Double.parseDouble(pila_exp.pop());
                pila_exp.push((op1 * op2) + "");*/
                continue;
            }

            //    System.out.println(exp);
            pila_exp.push(exp);
        }
        pila_exp.imprimir();
        String result = pila_exp.pop();
        String id = pila_exp.pop();
        Simbolo sim = TablaSimbolos.buscar(id);
        //System.out.println(sim);
        if (sim.getTipo().equals("just")) {
            double r = Double.parseDouble(result);
            if( r%1 != 0){
                int r_int = (int) r;
                sim.setValor(r_int);
                sim.setTipo("broken");
                System.out.println("Cambio el tipo");
            }
            else
                sim.setValor(r);
            
        } else {
            float r_floar = (float) Double.parseDouble(result);
            sim.setValor(r_floar);
        }
        //System.out.println(sim);

    }

    private void limpiar() {
        int rowCount = m.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            m.removeRow(i);
        }
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
        txtAreaEdit.setText("");
    }
}
