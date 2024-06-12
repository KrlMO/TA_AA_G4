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
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.CompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.ServicioTinteMySQL;

/**
 *
 * @author FERNANDO
 */
@WebService(serviceName = "ServicioCarritoWS")
public class ServicioCarritoWS {
    
    private Compra compra;
    private ServicioTinte servicio;
    private CompraDAO daoCompra;
    private ServicioTinteDAO daoServicio;
    
    @WebMethod(operationName = "insertarProductos")
    public int insertarProductos(@WebParam(name = "productos") ArrayList<LineaCompra>productos ) {
        int resultado = 0;
        try{
            if(compra ==null){
                compra = new Compra();
            }
            compra.setLineaCompras(productos);
            resultado = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "insertarServicios")
    public int insertarServicios(@WebParam(name = "servicios") ArrayList<LineaServicioTinte>servicios ) {
        int resultado = 0;
        try{
            if(servicio ==null){
                servicio = new ServicioTinte();
            }
            servicio.setLineaServicios(servicios);
            resultado = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    
    @WebMethod(operationName = "insertarCompra")
    public int insertarCompra() {
        int resultado = 0;
        try{
            if(compra != null){
                daoCompra = new CompraMySQL();
                resultado = daoCompra.insertar(compra);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarServicio")
    public int insertarServicio() {
        int resultado = 0;
        try{
            if(servicio != null){
                daoServicio = new ServicioTinteMySQL();
                resultado = daoServicio.insertar(servicio);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
