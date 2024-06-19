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
import pe.edu.pucp.campoysoft.productotextil.model.EspecificacionRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;
import pe.edu.pucp.campoysoft.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Administrador;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;

public class AdministradorMySQL implements AdministradorDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Administrador administrador){
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertAdministrador(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_administrador",java.sql.Types.INTEGER);
            cs.setString("p_nombre", administrador.getNombre());
            cs.setString("p_ap_paterno", administrador.getApPaterno());
            cs.setString("p_ap_materno", administrador.getApMaterno());
            cs.setString("p_dni", String.valueOf(administrador.getDni()));
            cs.setDate("p_fecha_nac", new java.sql.Date(administrador.getFechaNac().getTime()));
            cs.setString("p_direccion", administrador.getDireccion());
            cs.setString("p_cod_admin",administrador.getCodAdmin());
            cs.executeUpdate();
            administrador.setIdPersona(cs.getInt("_id_administrador"));
            resultado = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public int modificar(Administrador administrador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateAdministrador(?,?,?,?,?,?,?,?,?)}");
            
            cs.setString("nuevo_nombre", administrador.getNombre());
            cs.setString("nuevo_ap_paterno", administrador.getApPaterno());
            cs.setString("nuevo_ap_materno", administrador.getApMaterno());
            cs.setString("nuevo_dni", String.valueOf(administrador.getDni()));
            cs.setDate("nuevo_fecha_nac", new java.sql.Date(administrador.getFechaNac().getTime()));
            cs.setString("nuevo_direccion", administrador.getDireccion());
            cs.setInt("nuevo_activo_per", administrador.isActivo() ? 1 : 0);
            cs.setInt("administrador_id", administrador.getIdPersona());
            cs.setString("nuevo_cod_admin",administrador.getCodAdmin());
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
    public int eliminar(int idAdministrador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteAdministradorById(?)}");
            cs.setInt("admin_id", idAdministrador);
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
    public ArrayList<Administrador> listar() {
        ArrayList<Administrador> adminstradores = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListAdministradores()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Administrador administrador = new Administrador();
                administrador.setIdPersona(rs.getInt("id_administrador"));
                administrador.setCodAdmin(rs.getString("cod_admin"));
                administrador.setIdPersona(rs.getInt("id_persona"));
                administrador.setActivo(true);
                administrador.setNombre(rs.getString("nombre"));
                administrador.setApPaterno(rs.getString("ap_paterno"));
                administrador.setApMaterno(rs.getString("ap_materno"));
                administrador.setDni(Integer.parseInt(rs.getString("dni")));
                administrador.setFechaNac(rs.getDate("fecha_nac"));
                administrador.setDireccion(rs.getString("direccion"));
                adminstradores.add(administrador);    
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return adminstradores;
    }

    @Override
    public Empleado busquedaEmpleado(String codEmp) {
        Empleado empleado = new Empleado();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call Buscar_Empleado_X_CodEmp(?)}");
            cs.setString("p_cod_emp", codEmp);
            rs = cs.executeQuery();
            while(rs.next()){
                empleado.setIdPersona(rs.getInt("id_persona"));
                empleado.setActivo(true);
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApPaterno(rs.getString("ap_paterno"));
                empleado.setApMaterno(rs.getString("ap_materno"));
                empleado.setDni(Integer.parseInt(rs.getString("dni")));
                empleado.setFechaNac(rs.getDate("fecha_nac"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setCodEmpleado(rs.getString("cod_empleado"));
                empleado.setSalario(rs.getDouble("salario"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setActivo(rs.getBoolean("activo"));
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return empleado;
    }
    
    public EspecificacionRollo busquedaEspecificacion(int codEsp) {
        EspecificacionRollo rollito = new EspecificacionRollo();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call Buscar_EspecificacionRollo_X_CodRollo(?)}");
            cs.setInt("p_cod_emp", codEsp);
            rs = cs.executeQuery();
            while(rs.next()){
                rollito.setIdEspecifiacionRollo(rs.getInt("id_especificacion_rollo"));
                rollito.setLongitudRollo(rs.getDouble("longitud_rollo"));
                rollito.setPesoRollo(rs.getDouble("peso_rollo"));
                rollito.setAreaRollo(rs.getDouble("area_rollo"));
                rollito.setTipoRollo(TipoRollo.valueOf(rs.getString("tipo_rollo")));
                rollito.setTipoTela(TipoTela.valueOf(rs.getString("tipo_tela")));
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return rollito;
    }
}
