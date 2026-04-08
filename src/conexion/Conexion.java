package conexion;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection conectar = null;

    String user = "root";
    String password = "Mariajose09";
    String DB = "gestor_usuario";
    String Host = "localhost";
    String Port = "3306";

    String datos = "jdbc:mysql://" + Host + ":" + Port + "/" + DB;

    public Connection DB_Conection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conectar = DriverManager.getConnection(datos,user,password);

            System.out.println("la conexion ha sido exitosa");

            // CORRECCIÓN: antes creabas otra conexión innecesaria
            // ahora solo retornas la que ya creaste
            return conectar;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "no se ha podido acceder a la BD: " + e.getMessage());
            return null;
        }
    }
}

