/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.rrhh.model.Administrador;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;


public interface AdministradorDAO {
    public ArrayList<Administrador> listar();
    public int insertar(Administrador administrador);
    public int modificar(Administrador administrador);
    public int eliminar (int idAdministrador);
    
    public Empleado busquedaEmpleado(String codEmp);
}
