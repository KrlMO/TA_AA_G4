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
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
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
    
    @WebMethod(operationName = "insertarDatosCompra")
    public int insertarDatosCompra(@WebParam(name = "datos") int idUsu,double precioTotal,int cant,double peso ,double area) {
        int resultado = 0;
        try{
            if(compra ==null){
                compra = new Compra();
            }
            compra.setCliente(idUsu);
            compra.setEstadoServicio(EstadoAtencion.Emitido);
            compra.setPrecioTotal(precioTotal);
            compra.setCanTotalRollos(cant);
            compra.setPesoTotal(peso);
            compra.setAreaTotal(area);
            resultado = idUsu;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            //hola
        }
        return resultado;
    }
    @WebMethod(operationName = "insertarDatosServicio")
    public int insertarDatosServicio(@WebParam(name = "datosS") int idUsu,double precioTotal,int cant,double peso ,double area, double horas_tintado) {
        int resultado = 0;
        try{
            if(servicio ==null){
                servicio = new ServicioTinte();
            }
            servicio.setCliente(idUsu);
            servicio.setEstadoServicio(EstadoAtencion.Emitido);
            servicio.setPrecioTotal(precioTotal);
            servicio.setCanTotalRollos(cant);
            servicio.setPesoTotal(peso);
            servicio.setAreaTotal(area);
            servicio.setHorasTintado(horas_tintado);
            resultado = idUsu;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @WebMethod(operationName = "insertarCompra")
    public int insertarCompra(@WebParam(name = "productos") ArrayList<LineaCompra>productos) {
        int resultado = 3;
        try{
            if(compra!=null){
                compra.setLineaCompras(productos);
                daoCompra = new CompraMySQL();
                resultado = daoCompra.insertar(compra);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarServicio")
    public int insertarServicio(@WebParam(name = "servicios") ArrayList<LineaServicioTinte>servicios) {
        int resultado = 0;
        try{
            if(servicio!=null){
                servicio.setLineaServicios(servicios);
                daoServicio = new ServicioTinteMySQL();
                resultado = daoServicio.insertar(servicio);
            }        
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return 2;
        }
        return resultado;
    }
    
}
