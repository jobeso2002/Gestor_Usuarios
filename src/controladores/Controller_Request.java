package controladores;
import conexion.Conexion;
import interfaz.PeticionesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Controller_Request implements PeticionesDAO {

    private Conexion conexion = new Conexion();
    private Connection connection = conexion.DB_Conection();

    // Metodo para ingresar usuarios
    @Override
    public void addUser(String user, String pass) {
        try{
            String query = "INSERT INTO usuarios (user, password)VALUES (?,?)";
            PreparedStatement ps  = connection.prepareStatement(query);

            ps.setString(1, user);
            ps.setString(2, pass);

            ps.executeUpdate();

            System.out.println("Controlador ejecutado exitosamente (Ingreso usuario)");



        }catch(SQLException e){
            System.out.println("Error al ingresar un usuario: " + e.getMessage());

        }

    }
    //Metodo para actualizar usuarios
    @Override
    public void UpdateUser(String user, String pass, int id) {

        try {
            String query = "UPDATE usuarios SET user = ?, password = ? WHERE idusuarios= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,user);
            ps.setString(2, pass);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Usuario actualizado");
        } catch (SQLException ex) {
            System.out.println("El controlador no se ha ejecutado correctamente "+ ex.getMessage());
        }

    }

    @Override
    public void SelectUser(JTable mostrarDatos) {

        DefaultTableModel model = new DefaultTableModel(); // Asignamos una variable que tendra el modelo por defecto de la tabla.

        TableRowSorter<TableModel> orderTable = new TableRowSorter<TableModel>(model); //Nos permite ordenar datos segun las columnas


        mostrarDatos.setRowSorter(orderTable);

        String query = "SELECT*FROM usuarios";

        //Definimos las columnas de nuestro JTable
        model.addColumn("id");
        model.addColumn("Usuario");
        model.addColumn("Contraseña");

        mostrarDatos.setModel(model); // Añadimos las columnas

        String[] Datos = new String[3]; //Almacenamos temporalmente los datos

        Statement st;
        try{
            st= connection.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){

                Datos[0]= rs.getString(1);
                Datos[1]= rs.getString(2);
                Datos[2]= rs.getString(3);


                model.addRow(Datos);

            }


        }catch(SQLException e){

            System.out.println("Error al ejecutar controlador (Mostrar datos) " + e.getMessage());

        }

    }

    @Override
    public void DeleteUser(int ID) {

        try{
            String query = "DELETE FROM usuarios WHERE idusuarios = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            ps.executeUpdate();
            System.out.println("El controlador se ha ejecutado correctamente (Delete)");

        }catch(SQLException e){
            System.out.println("El controlador ha fallado: " +e.getMessage());

        }
    }

}