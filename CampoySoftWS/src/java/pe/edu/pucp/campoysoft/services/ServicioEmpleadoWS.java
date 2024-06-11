/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.dao.CompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.CompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.ServicioTinteMySQL;
import pe.edu.pucp.campoysoft.rrhh.dao.ClienteDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;
import pe.edu.pucp.campoysoft.rrhh.mysql.ClienteMySQL;

/**
 *
 * @author USER
 */
@WebService(serviceName = "ServicioEmpleadoWS")
public class ServicioEmpleadoWS {
    
    @WebMethod(operationName = "listarCompras")
    public void listarCompras(@WebParam(name = "listaEmitidos")ArrayList<Compra> listEmitidos, @WebParam(name = "listaEntregados") ArrayList<Compra> listEntregados){
        try{
            CompraDAO daoCompra = new CompraMySQL();
            listEmitidos = daoCompra.listarTodas(); 
            for(int i=0;i<listEmitidos.size();i++){
                EstadoAtencion estado = listEmitidos.get(i).getEstadoServicio();
                if(estado == EstadoAtencion.Emitido)
                    listEmitidos.add(listEmitidos.get(i));
                else if(estado == EstadoAtencion.Entregado)
                    listEntregados.add(listEmitidos.get(i));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarServicios")
    public void listarServicios(@WebParam(name = "listaEmitidos")ArrayList<ServicioTinte> listEmitidos, @WebParam(name = "listaEntregados") ArrayList<ServicioTinte> listEntregados, @WebParam(name = "idEmp")int idEmp){
        try{
            ServicioTinteDAO daoServicio = new ServicioTinteMySQL();
            listEmitidos = daoServicio.listarTodas();
            for(int i=0;i<listEmitidos.size();i++){
                EstadoAtencion estado = listEmitidos.get(i).getEstadoServicio();
                if(idEmp == listEmitidos.get(i).getIdEmpleado()){
                    if (estado == EstadoAtencion.Emitido)
                        listEmitidos.add(listEmitidos.get(i));
                    else if (estado == EstadoAtencion.Entregado)
                        listEntregados.add(listEmitidos.get(i));
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
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
}
