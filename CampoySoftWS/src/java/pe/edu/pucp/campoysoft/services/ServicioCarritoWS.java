/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.campoysoft.onlinemarket.dao.CompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.CompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.ServicioTinteMySQL;

/**
 *
 * @author FERNANDO
 */
@WebService(serviceName = "ServicioCarritoWS")
public class ServicioCarritoWS {

    private CompraDAO daoCompra;
    private ServicioTinteDAO daoServicio;
    
    @WebMethod(operationName = "insertarCompra")
    public int insertarCompra(@WebParam(name = "compra") Compra compra) {
        int resultado = 0;
        try{
            daoCompra = new CompraMySQL();
            resultado = daoCompra.insertar(compra);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarServicio")
    public int insertarServicio(@WebParam(name = "servicio") ServicioTinte servicio) {
        int resultado = 0;
        try{
            daoServicio = new ServicioTinteMySQL();
            resultado = daoServicio.insertar(servicio);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
