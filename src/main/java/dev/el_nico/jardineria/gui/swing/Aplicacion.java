/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.el_nico.jardineria.gui.swing;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import dev.el_nico.jardineria.dao1.DaoHolder;
import dev.el_nico.jardineria.dao1.sql.ConexionJardineria;
import dev.el_nico.jardineria.modelo1.Cliente;
import dev.el_nico.jardineria.modelo1.Pedido;

/**
 *
 * @author Nico
 */
public class Aplicacion extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 3936351195585276644L;
    
    /* pkg */ DaoHolder daos = new ConexionJardineria();

    /**
     * Creates new form Aplicacion
     */
    public Aplicacion() {
        
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
       
        this.getRootPane().setBorder(new LineBorder(new Color(204,204,204)));

        initComponents();
        tabs.addChangeListener((e) -> {
            if (tabs.getSelectedIndex() == 0) {
                botonAnadir.setEnabled(true);
                botonEditar.setEnabled(true);
                botonEliminar.setEnabled(true);
            } else {
                botonAnadir.setEnabled(false);
                botonEditar.setEnabled(false);
                botonEliminar.setEnabled(false);
            }
        });
        try {
            this.setIconImage(ImageIO.read(new File("src/main/resources/img/icono-jardineria.png")));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
        ((BasicInternalFrameUI)jInternalFrame1.getUI()).setNorthPane(null);
        
        setLocationRelativeTo(null); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("all")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraDeTitulo = new javax.swing.JPanel();
        IconoYNombreVentana = new javax.swing.JLabel();
        fondoBotonCerrar = new javax.swing.JPanel();
        botonCerrar = new javax.swing.JButton();
        fondoBotonMinim = new javax.swing.JPanel();
        botonMinim = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        tabs = new javax.swing.JTabbedPane();
        scrollPaneClientes = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        scrollPanePedidos = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        botonAnadir = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 153));
        setMinimumSize(new java.awt.Dimension(800, 632));
        setPreferredSize(new java.awt.Dimension(800, 632));

        barraDeTitulo.setBackground(new java.awt.Color(255, 255, 255));
        barraDeTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barraDeTituloMouseDragged(evt);
            }
        });
        barraDeTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barraDeTituloMousePressed(evt);
            }
        });

        IconoYNombreVentana.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        IconoYNombreVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/leaf-solid.png"))); // NOI18N
        IconoYNombreVentana.setText("Jardinería");
        IconoYNombreVentana.setToolTipText("");
        IconoYNombreVentana.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                IconoYNombreVentanaMouseDragged(evt);
            }
        });
        IconoYNombreVentana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                IconoYNombreVentanaMousePressed(evt);
            }
        });

        fondoBotonCerrar.setBackground(new java.awt.Color(255, 255, 255));

        botonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/power-off-solid.png"))); // NOI18N
        botonCerrar.setToolTipText("");
        botonCerrar.setBorderPainted(false);
        botonCerrar.setContentAreaFilled(false);
        botonCerrar.setFocusPainted(false);
        botonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCerrarMouseExited(evt);
            }
        });
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoBotonCerrarLayout = new javax.swing.GroupLayout(fondoBotonCerrar);
        fondoBotonCerrar.setLayout(fondoBotonCerrarLayout);
        fondoBotonCerrarLayout.setHorizontalGroup(
            fondoBotonCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE)
        );
        fondoBotonCerrarLayout.setVerticalGroup(
            fondoBotonCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        fondoBotonMinim.setBackground(new java.awt.Color(255, 255, 255));

        botonMinim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/window-minimize-solid.png"))); // NOI18N
        botonMinim.setBorder(null);
        botonMinim.setBorderPainted(false);
        botonMinim.setContentAreaFilled(false);
        botonMinim.setFocusPainted(false);
        botonMinim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonMinimMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonMinimMouseExited(evt);
            }
        });
        botonMinim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMinimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fondoBotonMinimLayout = new javax.swing.GroupLayout(fondoBotonMinim);
        fondoBotonMinim.setLayout(fondoBotonMinimLayout);
        fondoBotonMinimLayout.setHorizontalGroup(
            fondoBotonMinimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoBotonMinimLayout.createSequentialGroup()
                .addComponent(botonMinim, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fondoBotonMinimLayout.setVerticalGroup(
            fondoBotonMinimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonMinim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout barraDeTituloLayout = new javax.swing.GroupLayout(barraDeTitulo);
        barraDeTitulo.setLayout(barraDeTituloLayout);
        barraDeTituloLayout.setHorizontalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraDeTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconoYNombreVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fondoBotonMinim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fondoBotonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        barraDeTituloLayout.setVerticalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraDeTituloLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IconoYNombreVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fondoBotonCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fondoBotonMinim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        jInternalFrame1.setBackground(new java.awt.Color(213, 217, 210));
        jInternalFrame1.setBorder(null);
        jInternalFrame1.setVisible(true);

        tabs.setToolTipText("");

        scrollPaneClientes.setBackground(new java.awt.Color(204, 204, 204));
        scrollPaneClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Nombre de Contacto", "Apellido de Contacto", "Teléfono", "Fax", "1ª Línea de Dirección", "2ª Línea de Dirección", "Ciudad", "Región", "País", "Código Postal", "Código del Empleado Representante de Ventas", "Límite de Crédito", "Tipo de Documento", "Documento Identificativo", "Correo Electrónico", "Contraseña"
            }
        ) {
            /**
             *
             */
            private static final long serialVersionUID = 577385002893993529L;
            Class<?>[] types = new Class[] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.setToolTipText("");
        tablaClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPaneClientes.setViewportView(tablaClientes);

        tabs.addTab("Clientes", new javax.swing.ImageIcon(getClass().getResource("/img/user-solid-peq.png")), scrollPaneClientes); // NOI18N

        scrollPanePedidos.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Fecha del Pedido", "Fecha Esperada", "Fecha de Entrega", "Estado del Pedido", "Comentarios", "Código del Cliente"
            }
        ) {
            /**
             *
             */
            private static final long serialVersionUID = 513501222696349590L;
            Class<?>[] types = new Class[] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPanePedidos.setViewportView(tablaPedidos);

        tabs.addTab("Pedidos", new javax.swing.ImageIcon(getClass().getResource("/img/file-invoice-solid.png")), scrollPanePedidos); // NOI18N

        jToolBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        botonAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-square-solid.png"))); // NOI18N
        botonAnadir.setToolTipText("Añadir ");
        botonAnadir.setFocusPainted(false);
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });
        jToolBar1.add(botonAnadir);

        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minus-square-solid.png"))); // NOI18N
        botonEliminar.setToolTipText("Eliminar ");
        botonEliminar.setFocusPainted(false);
        botonEliminar.setFocusable(false);
        botonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(botonEliminar);

        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pen-square-solid.png"))); // NOI18N
        botonEditar.setToolTipText("Modificar ");
        botonEditar.setFocusPainted(false);
        botonEditar.setFocusable(false);
        botonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(botonEditar);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");
        jMenu1.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cliente");
        jMenu2.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pedido");
        jMenu3.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jMenuBar1.add(jMenu3);

        jInternalFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabs.getAccessibleContext().setAccessibleName("Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraDeTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jInternalFrame1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseEntered
        fondoBotonCerrar.setBackground(new Color(253, 185, 186));
    }//GEN-LAST:event_botonCerrarMouseEntered

    private void botonCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseExited
        fondoBotonCerrar.setBackground(Color.white);
    }//GEN-LAST:event_botonCerrarMouseExited

    private void botonMinimMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMinimMouseEntered
        fondoBotonMinim.setBackground(new Color(189, 203, 216));
    }//GEN-LAST:event_botonMinimMouseEntered

    private void botonMinimMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMinimMouseExited
        fondoBotonMinim.setBackground(Color.white);
    }//GEN-LAST:event_botonMinimMouseExited

    Point location;
    int px = 0, 
        py = 0;
    
    private void barraDeTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraDeTituloMouseDragged
        location = this.getLocation(location);
        int x = location.x - px + evt.getX();
        int y = location.y - py + evt.getY();
        this.setLocation(x, y);
    }//GEN-LAST:event_barraDeTituloMouseDragged

    private void barraDeTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraDeTituloMousePressed
        px = evt.getX();
        py = evt.getY();
    }//GEN-LAST:event_barraDeTituloMousePressed

    private void IconoYNombreVentanaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconoYNombreVentanaMouseDragged
        barraDeTituloMouseDragged(evt); 
    }//GEN-LAST:event_IconoYNombreVentanaMouseDragged

    private void IconoYNombreVentanaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconoYNombreVentanaMousePressed
        barraDeTituloMousePressed(evt);
    }//GEN-LAST:event_IconoYNombreVentanaMousePressed

    private void botonMinimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMinimActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_ICONIFIED));
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_botonMinimActionPerformed

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
        int resul = JOptionPane.showConfirmDialog(null, "¿Salir del programa?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resul == JOptionPane.YES_OPTION) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }//GEN-LAST:event_botonCerrarActionPerformed

    private JDialog ventanaEditarCliente;
    public boolean editarClienteExiste = false;
    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        
        if (ventanaEditarCliente == null || !editarClienteExiste) {
            ventanaEditarCliente = new EditarCliente(this, null);
            ventanaEditarCliente.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            ventanaEditarCliente.setVisible(true);
        }
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        
        final DefaultTableModel TABLA_CLIENTES = (DefaultTableModel) getTablaClientes().getModel();

        int[] row = getTablaClientes().getSelectedRows();
        for (int i = 0; i < row.length; ++i) {
            TABLA_CLIENTES.removeRow(row[i] - i);
        }
        
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        
        int firstSelected = getTablaClientes().getSelectedRow();
        if (firstSelected != -1 && (ventanaEditarCliente == null || !editarClienteExiste)) {
            ventanaEditarCliente = new EditarCliente(this, firstSelected + 1);
            ventanaEditarCliente.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            ventanaEditarCliente.setVisible(true);
        }
        
    }//GEN-LAST:event_botonEditarActionPerformed

    /* pkg */ void rellenarTablas() {

        final DefaultTableModel TABLA_CLIENTES = (DefaultTableModel) getTablaClientes().getModel();
        for (Cliente cliente : daos.clientes().todos()) {
            TABLA_CLIENTES.addRow(cliente.objArray());
        }

        final DefaultTableModel TABLA_PEDIDOS = (DefaultTableModel) getTablaPedidos().getModel();
        for (Pedido pedido : daos.pedidos().todos()) {

            final Pedido.Fecha FECHA = pedido.get_fecha();
            final SimpleDateFormat FORMATTER = new SimpleDateFormat("d MMM YYYY");
            TABLA_PEDIDOS.addRow(new Object[] {
                pedido.get_codigo(),
                FORMATTER.format(FECHA.pedido().getTime()),
                FORMATTER.format(FECHA.esperada().getTime()),
                FECHA.entrega().isPresent() ? FORMATTER.format((FECHA.entrega().get().getTime())) : null,
                pedido.get_estado(),
                pedido.get_comentarios().orElse(null),
                pedido.get_codigo_cliente()
            });
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame app = new Aplicacion();
                app.setVisible(true);

                JDialog loginDialog = new Login(app, true);
                loginDialog.setVisible(true);
                loginDialog.setModalityType(ModalityType.APPLICATION_MODAL);
            }
        });
    }
    
    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public JTable getTablaPedidos() {
        return tablaPedidos;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IconoYNombreVentana;
    private javax.swing.JPanel barraDeTitulo;
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonMinim;
    private javax.swing.JPanel fondoBotonCerrar;
    private javax.swing.JPanel fondoBotonMinim;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane scrollPaneClientes;
    private javax.swing.JScrollPane scrollPanePedidos;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
