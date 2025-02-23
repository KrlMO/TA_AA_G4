package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import pe.edu.pucp.campoysoft.config.DBManager;
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
    @WebMethod(operationName = "reportePDFAdmin")
    public byte[] reportePDF(@WebParam(name = "nombre")String nombre) throws Exception {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("GeneradoPor", nombre );
            String absolutePath = "C:\\Users\\USER\\Desktop\\NUEVO_FIN\\Campoy_TEX\\CampoySoftWS\\src\\java\\pe\\edu\\pucp\\campoysoft\\reports\\CampoySoft_Empleados.jrxml";
   
            byte[] byteArray = 
                    generarBuffer(absolutePath, parameters);
            return byteArray;            
            /*File myFile = new File("D:/temp/Report.pdf");
            byte[] byteArray = new byte[(int) myFile.length()];
            try (FileInputStream inputStream = new FileInputStream(myFile)) {
                inputStream.read(byteArray);
            }
            return byteArray;*/
         } catch (Exception ex) {
            System.out.println(ex);
        }
         return null;
    }
    
    public static byte[] generarBuffer(String inFileXML, Map<String, Object> parameters) throws Exception{
        //1- compilar el xml
        Connection conn = DBManager.getInstance().getConnection();
        //2- poblar el reporte
        JasperReport jasperReport = JasperCompileManager.compileReport(inFileXML);        
        //3- exportar a PDF y retorn el array de bytes
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);        
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    
    @WebMethod(operationName = "BusquedaEspecificacion")
    public EspecificacionRollo BusquedaEspecificacion(@WebParam(name = "cod_rollo") int codRollo){
        EspecificacionRollo especifi = new EspecificacionRollo();
        try{
            daoAdministrador= new AdministradorMySQL();
            especifi = daoAdministrador.busquedaEspecificacion(codRollo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return especifi;
        //comentario rando
    }
    //comentario rando
    @WebMethod(operationName = "insertarEspecificacionRollo")
    public int insertarEspecificacionRollo(@WebParam(name = "especi") EspecificacionRollo especi){
        int resultado = 0;
            try{
                daoEspecificacion= new EspecificacionRolloMySQL();
                resultado = daoEspecificacion.insertar(especi);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            return resultado;
    }
    
    @WebMethod(operationName = "eliminarTinte")
        public int eliminarTinte(@WebParam(name = "idtinte") int idTinte){
        int resultado = 0;
            try{
                daoTinte= new TinteMySQL();
                resultado = daoTinte.eliminar(idTinte);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            return resultado;
    }
        
    @WebMethod(operationName = "insertarTinte")
        public int insertarTinte(@WebParam(name = "Tinte") Tinte objTinte){
        int resultado = 0;
            try{
                daoTinte= new TinteMySQL();
                resultado = daoTinte.insertar(objTinte);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            return resultado;
    }
    
    @WebMethod(operationName = "BusquedaTinte")
    public Tinte BusquedaTinte(@WebParam(name = "cod_tinte") String codTinte){
        Tinte tinte = new Tinte();
        try{
            daoAdministrador= new AdministradorMySQL();
            tinte = daoAdministrador.busquedaTinte(codTinte);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tinte;
        //comentario rando
    }
    
    @WebMethod(operationName = "ListarTelas")
    public ArrayList<String> listarTipoTelas(){
        ArrayList<String> tiposTelas = new ArrayList<>();
            String cadena;
        try{
            daoEspecificacion= new EspecificacionRolloMySQL();
            tiposTelas = daoEspecificacion.listarTipoTelas();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tiposTelas;
    }
    
    @WebMethod(operationName = "ListarRollos")
    public ArrayList<String> listarRollos(){
        ArrayList<String> tiposRollo = new ArrayList<>();
            String cadena;
        try{
            daoEspecificacion= new EspecificacionRolloMySQL();
            tiposRollo = daoEspecificacion.listarTipoRollo();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tiposRollo;
    }
    
}
