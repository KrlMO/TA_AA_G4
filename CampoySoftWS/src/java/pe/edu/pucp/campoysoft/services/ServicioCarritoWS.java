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
    
    @WebMethod(operationName = "insertarCompra")
    public int insertarCompra(@WebParam(name = "datos") Compra compra_in) {
        int resultado = 0;
        String error;
        try{
            compra = compra_in;  
            //
            daoCompra = new CompraMySQL();
            compra.setEstadoServicio(EstadoAtencion.Emitido);
            resultado = daoCompra.insertar(compra);
            //
            daoLineaCompra = new LineaCompraMySQL();
            for(int i=0;i<compra.getLineaCompras().size();i++){
                LineaCompra producto = compra.getLineaCompras().get(i);
                producto.setCompra(compra);
                daoLineaCompra.insertar(producto);
            }       
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            //return "Excepción capturada: " + ex.getMessage();
        }
        return resultado;
        
    }
    @WebMethod(operationName = "insertarServicio")
    public String insertarServicio(@WebParam(name = "datosS") ServicioTinte servicio_in,int prod_en_carrito, String[]tiposTelas ) {
        int resultado = 0;
        String error;
        try{
            servicio = servicio_in;  
            //
            if(servicio!=null){
                daoServicio = new ServicioTinteMySQL();
                servicio.setEstadoServicio(EstadoAtencion.Emitido);
                resultado = daoServicio.insertar(servicio,prod_en_carrito);
                //
                if (resultado > 0) {
                    daoLineaService = new LineaServicioTinteMySQL();

                    for (int i = 0; i < servicio.getLineaServicios().size(); i++) {
                        LineaServicioTinte service = servicio.getLineaServicios().get(i);
                        TipoTela tipoTela = TipoTela.valueOf(tiposTelas[i]);
                        service.setTipoTela(tipoTela);
                        service.setServTinte(servicio);
                        resultado = daoLineaService.insertar(service);
                    }
                    return "Inserción exitosa";
                } else {
                    return "Fallo al insertar el servicio en la base de datos";
                }
            }
            
            //resultado = 1;      
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return "Excepción capturada: " + ex.getMessage();
        }
        //return resultado;
        return "El objeto servicio se asigno correctamente";
    }
}
