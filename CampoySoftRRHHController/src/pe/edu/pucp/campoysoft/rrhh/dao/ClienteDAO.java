/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;

public interface ClienteDAO {
    public ArrayList<Cliente> listar();
    public int insertar(Cliente cliente);
    public int modificar(Cliente cliente);
    public int eliminar (int idCliente);
}
