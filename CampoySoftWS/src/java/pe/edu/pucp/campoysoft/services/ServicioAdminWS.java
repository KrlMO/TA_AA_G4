package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.productotextil.dao.EspecificacionRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.dao.TinteDAO;
import pe.edu.pucp.campoysoft.productotextil.model.EspecificacionRollo;
import pe.edu.pucp.campoysoft.productotextil.model.Tinte;
import pe.edu.pucp.campoysoft.productotextil.mysql.EspecificacionRolloMySQL;
import pe.edu.pucp.campoysoft.productotextil.mysql.TinteMySQL;
import pe.edu.pucp.campoysoft.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.campoysoft.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;
import pe.edu.pucp.campoysoft.rrhh.mysql.AdministradorMySQL;
import pe.edu.pucp.campoysoft.rrhh.mysql.EmpleadoMySQL;

/**
 *
 * @author FERNANDO
 */
@WebService(serviceName = "ServicioAdminWS")
public class ServicioAdminWS {
    
    private AdministradorDAO daoAdministrador;
    private EmpleadoDAO daoEmpleado;
    private EspecificacionRolloDAO daoEspecificacion;
    private TinteDAO daoTinte;
    
    @WebMethod(operationName = "insertarEmpleado")
    public int insertarEmpleado(@WebParam(name = "empleado1")Empleado empleado1) {
        int resultado = 0;    
        try{
            daoEmpleado = new EmpleadoMySQL();
            resultado = daoEmpleado.insertar(empleado1);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    
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
    @WebMethod(operationName = "eliminarEmpleado")
    public int eliminarEmpleado(@WebParam(name = "empleado")int idEmpleado) {
        int resultado = 0;
        try{
            daoEmpleado = new EmpleadoMySQL();
            resultado = daoEmpleado.eliminar(idEmpleado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    //especificaciones
    @WebMethod(operationName = "listarEspecificacionRollo")
    public ArrayList<EspecificacionRollo> listarEspecificacionRollo(){
        ArrayList<EspecificacionRollo> especificaciones = new ArrayList<>();
        try{
            daoEspecificacion = new EspecificacionRolloMySQL();
            especificaciones = daoEspecificacion.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return especificaciones;
    }
    //tintes
    @WebMethod(operationName = "listarTintes")
    public ArrayList<Tinte> listarTintes(){
        ArrayList<Tinte> tintes = new ArrayList<>();
        try{
            daoTinte = new TinteMySQL();
            tintes = daoTinte.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return tintes;
    }
    
    @WebMethod(operationName = "ObtenerNombreEmpleado")
    public String ObtenerNombreEmpleado(@WebParam(name = "idEmpleado") int id){
        ArrayList<Empleado> listCli = new ArrayList<>();
        try{
            EmpleadoDAO daoCli = new EmpleadoMySQL();
            listCli = daoCli.listar();
            for(Empleado caux:listCli){
                if(id==caux.getIdPersona())
                    return caux.getNombre()+" "+caux.getApPaterno() +" "+caux.getApMaterno();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    @WebMethod(operationName = "BusquedaEmpleado")
    public Empleado busquedaEmpleado(@WebParam(name = "codEmp") String codEmp){
        Empleado empleado = new Empleado();
        try{
            daoAdministrador= new AdministradorMySQL();
            empleado = daoAdministrador.busquedaEmpleado(codEmp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return empleado;
    }
}
