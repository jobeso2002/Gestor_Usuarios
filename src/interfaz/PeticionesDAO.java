package interfaz;

import javax.swing.JTable;

public interface PeticionesDAO {
// Metodo para agregar un usuario

    void addUser(String user,String pass);

    //Meotod para actualizar el usuario

    void UpdateUser(String user,String pass,int id);

    //Metodo para llamar a los usuarios

    void SelectUser(JTable mostrarDatos);

    //Metodo para eliminar usuarios

    void DeleteUser(int ID);
}
