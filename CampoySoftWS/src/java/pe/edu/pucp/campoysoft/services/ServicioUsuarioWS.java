/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.campoysoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.productotextil.dao.ProductoRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;
import pe.edu.pucp.campoysoft.productotextil.mysql.ProductoRolloMySQL;
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
    
}
