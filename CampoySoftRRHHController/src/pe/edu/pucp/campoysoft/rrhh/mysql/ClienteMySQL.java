/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.rrhh.mysql;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.rrhh.dao.ClienteDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;

public class ClienteMySQL implements ClienteDAO {
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Cliente cliente){
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertCliente(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_cliente",java.sql.Types.INTEGER);
            cs.setString("p_nombre", cliente.getNombre());
            cs.setString("p_ap_paterno", cliente.getApPaterno());
            cs.setString("p_ap_materno", cliente.getApMaterno());
            cs.setString("p_dni", String.valueOf(cliente.getDni()));
            cs.setDate("p_fecha_nac", new java.sql.Date(cliente.getFechaNac().getTime()));
            cs.setString("p_direccion", cliente.getDireccion());
            cs.setString("p_cod_cliente",cliente.getCodCliente());
            cs.executeUpdate();
            cliente.setIdPersona(cs.getInt("_id_cliente"));
            resultado = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Cliente cliente) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateCliente(?,?,?,?,?,?,?,?,?)}");
            
            cs.setString("nuevo_nombre", cliente.getNombre());
            cs.setString("nuevo_ap_paterno", cliente.getApPaterno());
            cs.setString("nuevo_ap_materno", cliente.getApMaterno());
            cs.setString("nuevo_dni", String.valueOf(cliente.getDni()));
            cs.setDate("nuevo_fecha_nac", new java.sql.Date(cliente.getFechaNac().getTime()));
            cs.setString("nuevo_direccion", cliente.getDireccion());
            cs.setInt("nuevo_activo_cli", cliente.isActivo() ? 1 : 0);
            cs.setInt("cliente_id", cliente.getIdPersona());
            cs.setString("nuevo_cod_cliente",cliente.getCodCliente());
            cs.executeUpdate();
            resultado = 1;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idCliente) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteClienteById(?)}");
            cs.setInt("cliente_id", idCliente);
            cs.executeUpdate(); 
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListClientes()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdPersona(rs.getInt("id_cliente"));
                cliente.setCodCliente(rs.getString("cod_cliente"));
                cliente.setIdPersona(rs.getInt("id_persona"));
                cliente.setActivo(true);
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApPaterno(rs.getString("ap_paterno"));
                cliente.setApMaterno(rs.getString("ap_materno"));
                cliente.setDni(Integer.parseInt(rs.getString("dni")));
                cliente.setFechaNac(rs.getDate("fecha_nac"));
                cliente.setDireccion(rs.getString("direccion"));
                clientes.add(cliente);    
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return clientes;
    }
}
