/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.el_nico.jardineria.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.border.LineBorder;

import dev.el_nico.jardineria.dao.DaoHolder;
import dev.el_nico.jardineria.dao.gson.ClientesGsonDao;
import dev.el_nico.jardineria.dao.gson.PedidosGsonDao;
import dev.el_nico.jardineria.dao.sql.ConexionJardineria;
import dev.el_nico.jardineria.dao.sql.ProductosSqlDao;

/**
 *
 * @author NICO2DAM
 */
public class Login extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = -6123873948791046596L;

    private char echo_char_por_defecto;
    
    private Aplicacion app;
    
    /**
     * Creates new form Login
     */
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        
        app = parent instanceof Aplicacion ? (Aplicacion) parent : null;

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
       
        this.getRootPane().setBorder(new LineBorder(new Color(204,204,204)));
        
        initComponents();
        echo_char_por_defecto = input_contrasena.getEchoChar();
        boton_para_ocultar_pass.setVisible(false);
        try {
            this.setIconImage(ImageIO.read(new File("src/main/resources/img/icono-jardineria.png")));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        setLocationRelativeTo(app);
        input_usuario.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contrasena_olvidada = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        he_olvidado_la_contrasena = new javax.swing.JButton();
        panel_contrasena = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 32767));
        jLabel2 = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 32767));
        input_contrasena = new javax.swing.JPasswordField();
        boton_para_ver_pass = new javax.swing.JButton();
        boton_para_ocultar_pass = new javax.swing.JButton();
        panel_usuario = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jLabel1 = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        input_usuario = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 32767));
        jPanel2 = new javax.swing.JPanel();
        boton_cancelar = new javax.swing.JButton();
        boton_aceptar = new javax.swing.JButton();
        errorInicioSesion = new javax.swing.JLabel();
        barraTitulo = new javax.swing.JPanel();
        iconoYNombreVentana = new javax.swing.JLabel();
        fondoBotonCerrar = new javax.swing.JPanel();
        botonCerrar = new javax.swing.JButton();

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("vaya por dios");

        javax.swing.GroupLayout contrasena_olvidadaLayout = new javax.swing.GroupLayout(contrasena_olvidada.getContentPane());
        contrasena_olvidada.getContentPane().setLayout(contrasena_olvidadaLayout);
        contrasena_olvidadaLayout.setHorizontalGroup(
            contrasena_olvidadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        contrasena_olvidadaLayout.setVerticalGroup(
            contrasena_olvidadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jardinería - Login");
        setBackground(new java.awt.Color(241, 247, 237));
        setForeground(java.awt.Color.red);
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(241, 247, 237));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(241, 247, 237));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("¡Bienvenid@!\n    SQL:   localhost:3306/jardineria\nGSON:   src/main/resources/json");
        jTextArea1.setToolTipText("");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 3));
        jTextArea1.setMargin(new java.awt.Insets(10, 10, 10, 10));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(241, 247, 237));

        he_olvidado_la_contrasena.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        he_olvidado_la_contrasena.setForeground(new java.awt.Color(0, 0, 200));
        he_olvidado_la_contrasena.setText("He olvidado la contraseña");
        he_olvidado_la_contrasena.setBorder(null);
        he_olvidado_la_contrasena.setContentAreaFilled(false);
        he_olvidado_la_contrasena.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        he_olvidado_la_contrasena.setFocusPainted(false);
        he_olvidado_la_contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                he_olvidado_la_contrasenaActionPerformed(evt);
            }
        });

        panel_contrasena.setBackground(new java.awt.Color(255, 255, 255));
        panel_contrasena.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_contrasena.setForeground(new java.awt.Color(255, 255, 255));
        panel_contrasena.setToolTipText("Contraseña");
        panel_contrasena.setLayout(new javax.swing.BoxLayout(panel_contrasena, javax.swing.BoxLayout.LINE_AXIS));
        panel_contrasena.add(filler4);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(63, 140, 101));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/key-solid.png"))); // NOI18N
        panel_contrasena.add(jLabel2);
        panel_contrasena.add(filler6);

        input_contrasena.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        input_contrasena.setText("admin");
        input_contrasena.setToolTipText("Contraseña");
        input_contrasena.setBorder(null);
        input_contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_contrasenaActionPerformed(evt);
            }
        });
        panel_contrasena.add(input_contrasena);

        boton_para_ver_pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eye-regular-azul.png"))); // NOI18N
        boton_para_ver_pass.setToolTipText("Ver contraseña");
        boton_para_ver_pass.setBorder(null);
        boton_para_ver_pass.setContentAreaFilled(false);
        boton_para_ver_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_para_ver_passActionPerformed(evt);
            }
        });
        panel_contrasena.add(boton_para_ver_pass);

        boton_para_ocultar_pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eye-slash-regular-azul-y-naranja.png"))); // NOI18N
        boton_para_ocultar_pass.setToolTipText("Ocultar contraseña");
        boton_para_ocultar_pass.setBorder(null);
        boton_para_ocultar_pass.setContentAreaFilled(false);
        boton_para_ocultar_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_para_ocultar_passActionPerformed(evt);
            }
        });
        panel_contrasena.add(boton_para_ocultar_pass);

        panel_usuario.setBackground(new java.awt.Color(255, 255, 255));
        panel_usuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_usuario.setForeground(new java.awt.Color(255, 255, 255));
        panel_usuario.setToolTipText("Usuario");
        panel_usuario.setLayout(new javax.swing.BoxLayout(panel_usuario, javax.swing.BoxLayout.LINE_AXIS));
        panel_usuario.add(filler1);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 140, 101));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-solid.png"))); // NOI18N
        panel_usuario.add(jLabel1);
        panel_usuario.add(filler8);

        input_usuario.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        input_usuario.setText("admin");
        input_usuario.setToolTipText("Usuario");
        input_usuario.setBorder(null);
        input_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_usuarioActionPerformed(evt);
            }
        });
        panel_usuario.add(input_usuario);
        panel_usuario.add(filler3);

        jPanel2.setBackground(new java.awt.Color(241, 247, 237));

        boton_cancelar.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        boton_cancelar.setText("Mejor usar GSON");
        boton_cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_cancelarActionPerformed(evt);
            }
        });
        jPanel2.add(boton_cancelar);

        boton_aceptar.setBackground(new java.awt.Color(0, 102, 102));
        boton_aceptar.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        boton_aceptar.setText("Iniciar sesión SQL");
        boton_aceptar.setToolTipText("");
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
            }
        });
        jPanel2.add(boton_aceptar);

        errorInicioSesion.setBackground(new java.awt.Color(255, 204, 204));
        errorInicioSesion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        errorInicioSesion.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(errorInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(he_olvidado_la_contrasena)
                        .addGap(4, 4, 4))
                    .addComponent(panel_contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(he_olvidado_la_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        barraTitulo.setBackground(new java.awt.Color(255, 255, 255));
        barraTitulo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        barraTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barraTituloMouseDragged(evt);
            }
        });
        barraTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barraTituloMousePressed(evt);
            }
        });

        iconoYNombreVentana.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        iconoYNombreVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/leaf-solid.png"))); // NOI18N
        iconoYNombreVentana.setText("Jardinería - Login");
        iconoYNombreVentana.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                iconoYNombreVentanaMouseDragged(evt);
            }
        });
        iconoYNombreVentana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iconoYNombreVentanaMousePressed(evt);
            }
        });

        fondoBotonCerrar.setBackground(new java.awt.Color(255, 255, 255));

        botonCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/times-solid.png"))); // NOI18N
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

        javax.swing.GroupLayout barraTituloLayout = new javax.swing.GroupLayout(barraTitulo);
        barraTitulo.setLayout(barraTituloLayout);
        barraTituloLayout.setHorizontalGroup(
            barraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconoYNombreVentana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fondoBotonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        barraTituloLayout.setVerticalGroup(
            barraTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoYNombreVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(barraTituloLayout.createSequentialGroup()
                .addComponent(fondoBotonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_para_ocultar_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_para_ocultar_passActionPerformed
        boton_para_ocultar_pass.setVisible(false);
        boton_para_ver_pass.setVisible(true);

        input_contrasena.setEchoChar(echo_char_por_defecto);
    }//GEN-LAST:event_boton_para_ocultar_passActionPerformed

    private void boton_para_ver_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_para_ver_passActionPerformed
        boton_para_ver_pass.setVisible(false);
        boton_para_ocultar_pass.setVisible(true);

        input_contrasena.setEchoChar((char)0);
    }//GEN-LAST:event_boton_para_ver_passActionPerformed

    private void input_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_contrasenaActionPerformed
        boton_aceptarActionPerformed(evt);
    }//GEN-LAST:event_input_contrasenaActionPerformed

    private void he_olvidado_la_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_he_olvidado_la_contrasenaActionPerformed
        JOptionPane.showMessageDialog(this, "papel de toilete", "oh no", JOptionPane.ERROR_MESSAGE, new ImageIcon("src/main/resources/img/toilet-paper-solid.png"));
    }//GEN-LAST:event_he_olvidado_la_contrasenaActionPerformed

    private void boton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_cancelarActionPerformed
        if (app != null) {
            final String jsonPath = Paths.get(System.getProperty("user.dir"), "src/main/resources/json").toString();
            ClientesGsonDao daoClientes = new ClientesGsonDao(jsonPath + "/clientes_di_practica4.json");
            PedidosGsonDao daoPedidos = new PedidosGsonDao(jsonPath + "/pedidos_di_practica4.json", daoClientes);

            app.daos = new DaoHolder<PedidosGsonDao, ClientesGsonDao, ProductosSqlDao>(daoPedidos, daoClientes, new ProductosSqlDao(null));
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            app.rellenarTablas();
        }
    }//GEN-LAST:event_boton_cancelarActionPerformed

    private void iconoYNombreVentanaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoYNombreVentanaMouseDragged
        barraTituloMouseDragged(evt);
    }//GEN-LAST:event_iconoYNombreVentanaMouseDragged

    private void iconoYNombreVentanaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoYNombreVentanaMousePressed
        barraTituloMousePressed(evt);
    }//GEN-LAST:event_iconoYNombreVentanaMousePressed

    private void botonCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseEntered
        fondoBotonCerrar.setBackground(new Color(253, 185, 186));
    }//GEN-LAST:event_botonCerrarMouseEntered

    private void botonCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCerrarMouseExited
        fondoBotonCerrar.setBackground(Color.white);
    }//GEN-LAST:event_botonCerrarMouseExited

    Point location;
    int px = 0, 
        py = 0;
    
    private void barraTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraTituloMouseDragged
        location = this.getLocation(location);
        int x = location.x - px + evt.getX();
        int y = location.y - py + evt.getY();
        this.setLocation(x, y);
    }//GEN-LAST:event_barraTituloMouseDragged

    private void barraTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraTituloMousePressed
        px = evt.getX();
        py = evt.getY();
    }//GEN-LAST:event_barraTituloMousePressed

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
        if (app != null) {
            app.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }//GEN-LAST:event_botonCerrarActionPerformed

    private int numErrores = 0;
    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        if (app != null) {
            if (((ConexionJardineria) app.daos).login(input_usuario.getText(), new String(input_contrasena.getPassword()))) {
                // Login ok, cerrar ventana de login
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                app.rellenarTablas();
                
            } else {
                // Login no ok, mostrar msj error
                if (++numErrores > 99) {
                    errorInicioSesion.setText("¡Demasiados errores!");
                } else {
                    errorInicioSesion.setText("¡Error al hacer login nº" + numErrores + "!");
                }
            }
        }
    }//GEN-LAST:event_boton_aceptarActionPerformed

    private void input_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_usuarioActionPerformed
        boton_aceptarActionPerformed(evt);
    }//GEN-LAST:event_input_usuarioActionPerformed

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
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        */
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel barraTitulo;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_cancelar;
    private javax.swing.JButton boton_para_ocultar_pass;
    private javax.swing.JButton boton_para_ver_pass;
    private javax.swing.JDialog contrasena_olvidada;
    private javax.swing.JLabel errorInicioSesion;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JPanel fondoBotonCerrar;
    private javax.swing.JButton he_olvidado_la_contrasena;
    private javax.swing.JLabel iconoYNombreVentana;
    private javax.swing.JPasswordField input_contrasena;
    private javax.swing.JTextField input_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panel_contrasena;
    private javax.swing.JPanel panel_usuario;
    // End of variables declaration//GEN-END:variables
}
