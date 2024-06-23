/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.model.CompraResultado;
import pe.edu.pucp.campoysoft.model.ServicioTInteResultado;
import pe.edu.pucp.campoysoft.onlinemarket.dao.CompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaCompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.CompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.LineaCompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.LineaServicioTinteMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.ServicioTinteMySQL;
import pe.edu.pucp.campoysoft.productotextil.dao.ProductoRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.mysql.ProductoRolloMySQL;
import pe.edu.pucp.campoysoft.rrhh.dao.ClienteDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;
import pe.edu.pucp.campoysoft.rrhh.mysql.ClienteMySQL;

/**
 *
 * @author USER CarlosMO
 */
@WebService(serviceName = "ServicioEmpleadoWS")
public class ServicioEmpleadoWS {
    
    @WebMethod(operationName = "listarCompras")
    public CompraResultado listarCompras(@WebParam(name = "idEmp")int idEmp) {
        List<Compra> listEmitidos = new ArrayList<>();
        List<Compra> listEntregados = new ArrayList<>();
        try {
            CompraDAO daoCompra = new CompraMySQL();
            listEmitidos = daoCompra.listarEmitidas();
            listEntregados = daoCompra.listarEntregadas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompraResultado result = new CompraResultado();
        result.setListEmitidos(listEmitidos);
        result.setListEntregados(listEntregados);
        return result;
    }

    
    @WebMethod(operationName = "listarServicios")
    public ServicioTInteResultado listarServicios(@WebParam(name = "idEmp") int idEmp) {
        List<ServicioTinte> listEmitidos = new ArrayList<>();
        List<ServicioTinte> listEntregados = new ArrayList<>();
        try {
            ServicioTinteDAO daoServicio = new ServicioTinteMySQL();
            listEmitidos = daoServicio.listarEmitidos();
            listEntregados = daoServicio.listarEntregadas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServicioTInteResultado result = new ServicioTInteResultado();
        result.setListEmitidos(listEmitidos);
        result.setListEntregados(listEntregados);
        return result;
    }
    
    @WebMethod(operationName = "Atender")
    public void Atender(@WebParam(name = "atencion")int idAtencion, @WebParam(name = "empleado") int idEmp){
        try{
            Empleado emp = new Empleado();
            emp.setIdPersona(idEmp);
            emp.despachar_Atencion(idAtencion);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    @WebMethod(operationName = "ObtenerNombre")
    public String obtenerNombreCliente(@WebParam(name = "idCliente") int id){
        ArrayList<Cliente> listCli = new ArrayList<>();
        try{
            ClienteDAO daoCli = new ClienteMySQL();
            listCli = daoCli.listar();
            for(Cliente caux:listCli){
                if(id==caux.getIdPersona())
                    return caux.getNombre()+" "+caux.getApPaterno() +" "+caux.getApMaterno();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    @WebMethod(operationName = "obtenerListLineaCompra")
    public ArrayList<LineaCompra> obtenerListLineaCompra(@WebParam(name = "idAtencion")int idAtencion){
        ArrayList<LineaCompra> listLinea = new ArrayList<>();
        ArrayList<LineaCompra> listFin = new ArrayList<>();
        try{
            LineaCompraDAO daoLinCompra = new LineaCompraMySQL();
            listLinea = daoLinCompra.listarTodas();
            for(LineaCompra laux:listLinea){
                if(laux.getCompra().getIdAtencion()==idAtencion){
                    listFin.add(laux);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listFin;
    }
    
    @WebMethod(operationName = "obtenerListLineaServ")
    public ArrayList<LineaServicioTinte> obtenerListLineaServicio(@WebParam(name = "idAtencion")int idAtencion){
        ArrayList<LineaServicioTinte> listLinea = new ArrayList<>();
        ArrayList<LineaServicioTinte> listFin = new ArrayList<>();
        try{
            LineaServicioTinteDAO daoLinServicio = new LineaServicioTinteMySQL();
            listLinea = daoLinServicio.listarTodas();
            for(LineaServicioTinte laux:listLinea){
                if(laux.getServTinte().getIdAtencion()==idAtencion){
                    listFin.add(laux);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listFin;
    }
    
    @WebMethod(operationName = "listarProductosStockBajo")
    public ArrayList<ProductoRollo> listarProdBajoStock(){
        ArrayList<ProductoRollo> listProds = new ArrayList<>();
        try{
            ProductoRolloDAO daoProducto = new ProductoRolloMySQL();
            listProds = daoProducto.listarProductosBajoStock();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return listProds;
    }
    
    @WebMethod(operationName = "reponerProducto")
    public int modificarReponerPrdo(@WebParam(name = "id")int id, @WebParam(name = "cantRepo")int cantidadRep){
        int resultado;
        try{
            ProductoRolloDAO daoProducto = new ProductoRolloMySQL();
            ProductoRollo prod = daoProducto.obtenerProductoRollo(id);
            int nuevoStock = prod.getStock() + cantidadRep;
            prod.setStock(nuevoStock);
            
            resultado = daoProducto.modificar(prod);
        }catch(Exception e){
            System.out.println(e.getMessage());
            resultado = 0;
        }
        return resultado;
    }
    
    @WebMethod(operationName = "reportePDF")
    public byte[] reportePDF(@WebParam(name = "nombre")String nombre) throws Exception {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("GeneradoPor", nombre );
            String absolutePath = "C:\\Users\\USER\\Desktop\\fin back\\Campoy_TEX\\CampoySoftWS\\src\\java\\pe\\edu\\pucp\\campoysoft\\reports\\CampoySoft_usuario_compras.jrxml";
   
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
    
    
    
    
    @WebMethod(operationName = "reportePDFSer")
    public byte[] reportePDFSer(@WebParam(name = "nombre")String nombre) throws Exception {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("GeneradoPor", nombre );
            String absolutePath ="C:\\Users\\samt1\\OneDrive\\Documentos\\Universidad\\Programacion_3\\Campoy_TEX\\CampoySoftWS\\src\\java\\pe\\edu\\pucp\\campoysoft\\reports\\CampoySoft_Usuario_Servicios.jrxml";
   
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
}
