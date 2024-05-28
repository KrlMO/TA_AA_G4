package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;
import pe.edu.pucp.campoysoft.rrhh.mysql.EmpleadoMySQL;

/**
 *
 * @author FERNANDO
 */
@WebService(serviceName = "ServicioAdminWS")
public class ServicioAdminWS {
    
    private EmpleadoDAO daoEmpleado;
    
    @WebMethod(operationName = "listarEmpleados")
    public ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            daoEmpleado = new EmpleadoMySQL();
            empleados = daoEmpleado.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return empleados;
    }
    @WebMethod(operationName = "modificarEmpleado")
    public int modificarEmpleado(@WebParam(name = "empleado")Empleado empleado) {
        int resultado = 0;
        try{
            daoEmpleado = new EmpleadoMySQL();
            daoEmpleado.modificar(empleado);
            resultado = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
