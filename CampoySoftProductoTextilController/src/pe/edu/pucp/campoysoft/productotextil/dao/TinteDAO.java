/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.dao;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.productotextil.model.Tinte;

public interface TinteDAO {
    public ArrayList<Tinte> listar();
    public int insertar(Tinte tinte);
    public int modificar(Tinte tinte);
    public int eliminar (int idTinte);
}
