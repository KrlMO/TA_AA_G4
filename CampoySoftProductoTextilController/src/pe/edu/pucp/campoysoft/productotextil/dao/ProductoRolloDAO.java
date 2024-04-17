/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.dao;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;

public interface ProductoRolloDAO {
    public ArrayList<ProductoRollo> listar();
    public int insertar(ProductoRollo productoRollo);
    public int modificar(ProductoRollo productoRollo);
    public int eliminar (int idProducto);
}
