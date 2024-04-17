/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;

public interface EmpleadoDAO {
    public ArrayList<Empleado> listar();
    public int insertar(Empleado empleado);
    public int modificar(Empleado empleado);
    public int eliminar (int idEmpleado);
}
