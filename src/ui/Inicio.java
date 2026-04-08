package ui;

import javax.swing.*;
import java.awt.*;
import controladores.Controller_Request;
import javax.swing.JTable;


public class Inicio extends JFrame {

    // Variables globales
    JTextField txtUsuario;
    JPasswordField txtPassword;
    JTextField txtId;

    Controller_Request controller = new Controller_Request();
    JTable tabla;

    public Inicio() {
        setTitle("GESTOR DE USUARIOS"); // Título de la ventana
        setSize(1200, 900); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar programa al cerrar ventana
        setMinimumSize(new Dimension(400, 200)); // Tamaño mínimo

        Paneles(); // Llamamos al método principal
    }

    // MÉTODO PRINCIPAL DONDE SE CREA TODO
    public void Paneles() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // IMPORTANTE para usar setBounds

        this.getContentPane().add(panel);

        // ====== ETIQUETA PRINCIPAL ======
        JLabel etiqueta = new JLabel("GESTOR DE USUARIOS", SwingConstants.CENTER);
        etiqueta.setBounds(0, 7, 1200, 90);
        etiqueta.setFont(new Font("Algerian", Font.ITALIC, 30));
        etiqueta.setOpaque(true);
        etiqueta.setBackground(Color.BLACK);
        etiqueta.setForeground(Color.WHITE);

        // ====== LABELS ======
        JLabel usuario = new JLabel("Usuario");
        usuario.setBounds(50, 90, 200, 100);
        usuario.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel password = new JLabel("Contraseña");
        password.setBounds(50, 190, 200, 100);
        password.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel id = new JLabel("ID");
        id.setBounds(650, 190, 200, 100);
        id.setFont(new Font("Arial", Font.BOLD, 20));

        // Agregar labels al panel
        panel.add(etiqueta);
        panel.add(usuario);
        panel.add(password);
        panel.add(id);

        // ====== CAJAS DE TEXTO ======
        cajaTextoUsuario(panel);
        cajaTextoPassword(panel);
        cajaTextoId(panel);

        // ====== BOTONES ======
        botonGuardar(panel);

        // ====== TABLA ======
        tablaUsuarios(panel);
    }

    // ====== CAJA DE TEXTO USUARIO ======
    public void cajaTextoUsuario(JPanel panel) {
        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 110, 950, 50);
        panel.add(txtUsuario);
    }

    // ====== CAJA DE TEXTO PASSWORD ======
    public void cajaTextoPassword(JPanel panel) {
        txtPassword = new JPasswordField();
        txtPassword.setBounds(180, 210, 400, 50);
        panel.add(txtPassword);
    }

    // ====== CAJA DE TEXTO ID ======
    public void cajaTextoId(JPanel panel) {
        txtId = new JTextField();
        txtId.setBounds(700, 210, 430, 50);
        panel.add(txtId);
    }

    // ====== BOTONES ======
    public void botonGuardar(JPanel panel) {

        JButton botonInsertar = new JButton("Insertar Usuario");
        JButton botonActualizarUsuario = new JButton("Actualizar Usuario");
        JButton botonEliminar = new JButton("Eliminar Usuario");
        JButton botonActualizarListaUsuario = new JButton("Actualizar Lista De usuarios");

        botonActualizarUsuario.setBounds(180, 290, 180, 50);
        botonInsertar.setBounds(420, 290, 180, 50);
        botonEliminar.setBounds(670, 290, 180, 50);
        botonActualizarListaUsuario.setBounds(900, 290, 200, 50);

        // ===== INSERTAR =====
        botonInsertar.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtPassword.getPassword());

            controller.addUser(user, pass);


            JOptionPane.showMessageDialog(null, "Usuario insertado");
            controller.SelectUser(tabla); // refresca tabla
        });

        // ===== ACTUALIZAR =====
        botonActualizarUsuario.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtPassword.getPassword());
            int id = Integer.parseInt(txtId.getText());

            controller.UpdateUser(user, pass, id);

            JOptionPane.showMessageDialog(null, "Usuario actualizado");
            controller.SelectUser(tabla);
        });

        // ===== ELIMINAR =====
        botonEliminar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());

            controller.DeleteUser(id);

            JOptionPane.showMessageDialog(null, "Usuario eliminado");
            controller.SelectUser(tabla);
        });

        // ===== LISTAR =====
        botonActualizarListaUsuario.addActionListener(e -> {
            controller.SelectUser(tabla);
        });

        panel.add(botonInsertar);
        panel.add(botonEliminar);
        panel.add(botonActualizarUsuario);
        panel.add(botonActualizarListaUsuario);
    }


    // ====== TABLA ======
    public void tablaUsuarios(JPanel panel) {

        // Columnas (sin datos)
        String[] columnas = {"ID", "Usuario", "Password"};

        Object[][] datos = {}; // tabla vacía

        tabla = new JTable(datos, columnas);

        JScrollPane scroll = new JScrollPane(tabla);

        // ESTE ES EL CUADRO GRANDE (puedes cambiar tamaño aquí)
        scroll.setBounds(100, 400, 1000, 300);

        panel.add(scroll);
    }


}
