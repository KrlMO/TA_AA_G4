/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Iterator;
import pe.edu.pucp.campoysoft.productotextil.dao.ProductoRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;
import pe.edu.pucp.campoysoft.productotextil.mysql.ProductoRolloMySQL;
import pe.edu.pucp.campoysoft.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.campoysoft.rrhh.dao.ClienteDAO;
import pe.edu.pucp.campoysoft.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Administrador;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;
import pe.edu.pucp.campoysoft.rrhh.model.Persona;
import pe.edu.pucp.campoysoft.rrhh.mysql.AdministradorMySQL;
import pe.edu.pucp.campoysoft.rrhh.mysql.ClienteMySQL;
import pe.edu.pucp.campoysoft.rrhh.mysql.EmpleadoMySQL;
import pe.edu.pucp.campoysoft.tech.dao.UsuarioDAO;
import pe.edu.pucp.campoysoft.tech.mysql.UsuarioMySQL;
import pe.edu.pucp.campoysoft.tech.model.Usuario;
/**
 *ServiciosCampoyTextWS
 * http://localhost:8080/CampoySoftWS/servicios.disco
 * @author s
 */
@WebService(serviceName = "ServicioUsuarioWS")
public class ServicioUsuarioWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ListarProductosRollosXnombre")
    public ArrayList<ProductoRollo> ListarProductosRollosXnombre(@WebParam(name = "tipo_de_tela") String tipoTelaS) {
        ArrayList<ProductoRollo> productoRollos = new ArrayList<>();
         TipoTela tipoTela;
        // Manejar posibles excepciones para TipoTela.valueOf
        try {
            tipoTela = TipoTela.valueOf(tipoTelaS);
        } catch (IllegalArgumentException e) {
            // Manejar el caso donde el tipo de tela no es válido
            System.out.println("Tipo de tela no válido: " + tipoTelaS);
            return new ArrayList<>(); // Devolver una lista vacía o manejar de otra manera
        }

        ProductoRolloDAO daoPr = new ProductoRolloMySQL();
        productoRollos = daoPr.listarPorTipo(tipoTela);
        return productoRollos;
    }
    @WebMethod(operationName = "holaUsu")
    public Usuario holaUsu(@WebParam(name = "tipo_de_tela") String nombre) {
        Usuario usu = new Usuario();
        usu.setUsername(nombre);
        
        return usu;
    }
    @WebMethod(operationName = "identificar_usu")
    public int identificar_usu(@WebParam(name = "username") String username,@WebParam(name = "password") String password) {
        UsuarioDAO usuDAO = new UsuarioMySQL();
        return usuDAO.identificar_usu(username, password);
    }
    
    @WebMethod(operationName = "buscarProductos")
    public ArrayList<ProductoRollo> buscarProductos(@WebParam(name = "text") String text) {
        ArrayList<ProductoRollo> productoRollos = new ArrayList<>();
                
        ProductoRolloDAO daoPr = new ProductoRolloMySQL();
        productoRollos = daoPr.buscarProducto(text);
        return productoRollos;
    }
    
    @WebMethod(operationName = "obtenerInfoUsuario")
    public Persona obtenerInfoUsuario(@WebParam(name = "username") String username,@WebParam(name = "password") String password, @WebParam(name = "tipo") int i){
        Cliente cli;
        Empleado emp;
        Administrador admin;
        try{
            UsuarioDAO daoUsuario = new UsuarioMySQL();
            if(i==1){
                cli = new Cliente();
                cli = (Cliente)daoUsuario.obtenerDatos(username, password,i);
                return cli;
            }
            
            if(i==2){
                emp = new Empleado();
                emp = (Empleado)daoUsuario.obtenerDatos(username, password, i);
                return emp;
            }
            if(i==3){
                admin = new Administrador();
                admin = (Administrador) daoUsuario.obtenerDatos(username, password, i);
                return admin;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
