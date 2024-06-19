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
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import pe.edu.pucp.campoysoft.config.DBManager;
/**
 *ServiciosCampoyTextWS
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

    @WebMethod(operationName = "identificar_usu")
    public int identificar_usu(@WebParam(name = "username") String username,@WebParam(name = "password") String password) {
        UsuarioDAO usuDAO = new UsuarioMySQL();
        return usuDAO.identificar_usu(username, password);
    }
    
    @WebMethod(operationName = "Crear_Usuario")
    public int Crear_Usuario(@WebParam(name = "Usuario") Usuario usuario,@WebParam(name = "Cliente") Cliente cliente) {
        int resultado = 0;
        try{
            ClienteDAO clienteDAO = new ClienteMySQL();
            UsuarioDAO usuarioDAO = new UsuarioMySQL();
            cliente.setCodCliente("auxCodCli");
            clienteDAO.insertar(cliente);
            
            usuario.setPersona(cliente);
            resultado=usuarioDAO.insertar(usuario);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "Crear_Usuario_Empleado")
    public int Crear_Usuario_Empleado(@WebParam(name = "Usuario") Usuario usuario) {
        int resultado = 0;
        try{
            UsuarioDAO usuarioDAO = new UsuarioMySQL();
            resultado=usuarioDAO.insertar(usuario);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return resultado;
        //comentario rando
    }
    
    @WebMethod(operationName = "buscarProductos")
    public ArrayList<ProductoRollo> buscarProductos(@WebParam(name = "text") String text) {
        ArrayList<ProductoRollo> productoRollos = new ArrayList<>();
                
        ProductoRolloDAO daoPr = new ProductoRolloMySQL();
        productoRollos = daoPr.buscarProducto(text);
        return productoRollos;
    }
    
    @WebMethod(operationName = "obtenerIDPersona")
    public int obtenerIDPersona(@WebParam(name = "username") String username,@WebParam(name = "password") String password){
        
        try{
            UsuarioDAO daoUsuario = new UsuarioMySQL();
            return daoUsuario.obtenerDatos(username, password);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    @WebMethod(operationName = "ObtenerProductoRollo")
    public ProductoRollo ObtenerProductoRollo(@WebParam(name = "id_producto_rollo") int id_producto_rollo) {
        ProductoRollo producto = null;
        // Manejar posibles excepciones para TipoTela.valueOf
        try {
            ProductoRolloDAO daoPr = new ProductoRolloMySQL();
            producto = daoPr.obtenerProductoRollo(id_producto_rollo);
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        
        return producto;
    }
    
    @WebMethod(operationName = "holaAdmin")
    public Administrador holaAdmin(@WebParam(name = "nombAdmin")String nombre){
        Administrador admin = new Administrador();
        admin.setNombre(nombre);
        return admin;
    }
    
    @WebMethod(operationName = "holaCliente")
    public Cliente holaCli(@WebParam(name = "nombClie")String nombre){
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        return cli;
    }
    
    @WebMethod(operationName = "holaUsu")
    public Usuario holaUsu(@WebParam(name = "tipo_de_tela") String nombre) {
        Usuario usu = new Usuario();
        usu.setUsername(nombre);
        
        return usu;
    }
    
    @WebMethod(operationName = "historialServicios")
public ArrayList<String> historialServicios(@WebParam(name = "idCliente") String idCliente) {
    ArrayList<String> servicios = new ArrayList<String>();
    Connection con = null;
    CallableStatement cs = null;
    ResultSet rs = null;     

    try {
        con = DBManager.getInstance().getConnection();
        cs = con.prepareCall("{call ListarHistorialServicios(?)}");
        cs.setString(1, idCliente);  // Proporcionar el parámetro idCliente
        rs = cs.executeQuery();
        while (rs.next()) {
            String servicio = rs.getString("concatenado");
            servicios.add(servicio);
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    } finally {
        // Cerrar ResultSet, CallableStatement y Connection en el bloque finally
        try {
            if (rs != null) rs.close();
            if (cs != null) cs.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    return servicios;
}
    @WebMethod(operationName = "historialCompras")
public ArrayList<String> historialCompras(@WebParam(name = "idCliente") String idCliente) {
    ArrayList<String> compras = new ArrayList<String>();
    Connection con = null;
    CallableStatement cs = null;
    ResultSet rs = null;     

    try {
        con = DBManager.getInstance().getConnection();
        cs = con.prepareCall("{call ListarHistorialCompras(?)}");
        cs.setString(1, idCliente);  // Proporcionar el parámetro idCliente
        rs = cs.executeQuery();
        while (rs.next()) {
            String compra = rs.getString("concatenado");
            compras.add(compra);
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    } finally {
        // Cerrar ResultSet, CallableStatement y Connection en el bloque finally
        try {
            if (rs != null) rs.close();
            if (cs != null) cs.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    return compras;
}
    
    
    
}
