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
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaCompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.CompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.LineaCompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.LineaServicioTinteMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.ServicioTinteMySQL;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;

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
    //
    private LineaCompraDAO daoLineaCompra;
    private LineaServicioTinteDAO daoLineaService;
    
    @WebMethod(operationName = "insertarDatosCompra")
    public int insertarDatosCompra(@WebParam(name = "datos") int idUsu,int cant,double peso ,double area) {
        int resultado = 0;
        try{
            if(compra ==null){
                compra = new Compra();
            }
            compra.setCliente(idUsu);
            compra.setEstadoServicio(EstadoAtencion.Emitido);
            compra.setCanTotalRollos(cant);
            compra.setPesoTotal(peso);
            compra.setAreaTotal(area);
            resultado = idUsu;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    @WebMethod(operationName = "insertarDatosServicio")
    public int insertarDatosServicio(@WebParam(name = "datosS") int idUsu,int cant,double peso ,double area, double horas_tintado) {
        int resultado = 0;
        try{
            if(servicio ==null){
                servicio = new ServicioTinte();
            }
            servicio.setCliente(idUsu);
            servicio.setEstadoServicio(EstadoAtencion.Emitido);
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
    public int insertarCompra(@WebParam(name = "productos") ArrayList<LineaCompra>productos,double precio) {
        int resultado = 3;
        try{
            if(compra!=null){
                compra.setLineaCompras(productos);
                compra.setPrecioTotal(precio);
                daoCompra = new CompraMySQL();
                resultado = daoCompra.insertar(compra);
                //
                daoLineaCompra = new LineaCompraMySQL();
                for(int i=0;i<productos.size();i++){
                    LineaCompra producto = productos.get(i);
                    producto.setCompra(compra);
                    daoLineaCompra.insertar(producto);
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarServicio")
    public int insertarServicio(@WebParam(name = "servicios") ArrayList<LineaServicioTinte>servicios, int prod_en_carrito, String palabra2,double precio) {
        int resultado = 0;
        try{
            if(servicio!=null){
                servicio.setLineaServicios(servicios);
                servicio.setPrecioTotal(precio);
                daoServicio = new ServicioTinteMySQL();
                daoServicio.insertar(servicio,prod_en_carrito);
                servicio.getPrecioTotal();
                //
                //
                daoLineaService = new LineaServicioTinteMySQL();
                for(int i=0;i<servicios.size();i++){
                    LineaServicioTinte service = servicios.get(i);
                    service.setServTinte(servicio);
                    service.setArea(30);
                    TipoTela tipoTela = TipoTela.valueOf(palabra2);
                    service.setTipoTela(tipoTela);
                    tipo = service.getTipoTela().name();
                    //ide_tinte = service.getTinteDestino().getIdTinte();
                    daoLineaService.insertar(service);
                }
                resultado = 1;
            }        
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        return resultado;
    }
    
}
