/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;

/**
 *
 * @author USER
 */
@WebService(serviceName = "ServicioEmpleadoWS")
public class ServicioEmpleadoWS {


    
    @WebMethod(operationName = "listarPedidosEmitidos")
    public ArrayList<Atencion> listarPedEmitidos(){
        ArrayList<Atencion> listPedEmi = new ArrayList<>();
        try{
            Empleado emp = new Empleado();
            listPedEmi = emp.listar_Atencion_Emitidas();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        
        return listPedEmi;
    }
    
    
    @WebMethod(operationName = "listarPedidosEntregados")
    public ArrayList<Atencion> listarPedEntregado(){
        ArrayList<Atencion> listPedEntre = new ArrayList<>();
        try{
            Empleado emp = new Empleado();
            listPedEntre = emp.listar_Atencion_Entregadas();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
                
        return listPedEntre;
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
}
