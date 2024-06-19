/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.rrhh.mysql;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Set;

import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;

public class EmpleadoMySQL implements EmpleadoDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    @Override
    public int insertar(Empleado empleado){
        int resultado = 0;
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertEmpleado(?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_empleado",java.sql.Types.INTEGER);
            cs.setString("p_nombre", empleado.getNombre());
            cs.setString("p_ap_paterno", empleado.getApPaterno());
            cs.setString("p_ap_materno", empleado.getApMaterno());
            cs.setString("p_dni", String.valueOf(empleado.getDni()));
            cs.setDate("p_fecha_nac", new java.sql.Date(empleado.getFechaNac().getTime()));
            cs.setString("p_direccion", empleado.getDireccion());
            cs.setDouble("p_salario", empleado.getSalario());
            cs.setString("p_cargo", empleado.getCargo());
            
            cs.executeUpdate();
            empleado.setIdPersona(cs.getInt("_id_empleado"));
            empleado.setCodEmpleado(cs.getString("_id_empleado"));
            resultado = empleado.getIdPersona();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Empleado empleado) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateEmpleado(?,?,?,?,?,?,?,?,?)}");
            
            cs.setString("nuevo_nombre", empleado.getNombre());
            cs.setString("nuevo_ap_paterno", empleado.getApPaterno());
            cs.setString("nuevo_ap_materno", empleado.getApMaterno());
            cs.setString("nuevo_dni", String.valueOf(empleado.getDni()));
            cs.setDate("nuevo_fecha_nac", new java.sql.Date(empleado.getFechaNac().getTime()));
            cs.setString("nuevo_direccion", empleado.getDireccion());
            cs.setInt("empleado_id", empleado.getIdPersona());
            cs.setDouble("nuevo_salario", empleado.getSalario());
            cs.setString("nuevo_cargo", empleado.getCargo());
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
    public int eliminar(int idEmpleado) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteEmpleadoById(?)}");
            cs.setInt("empleado_id", idEmpleado);
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
    public ArrayList<Empleado> listar() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListEmpleados()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setIdPersona(rs.getInt("id_empleado"));
                empleado.setCodEmpleado(rs.getString("cod_empleado"));
                empleado.setSalario(rs.getDouble("salario"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setActivo(true);
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApPaterno(rs.getString("ap_paterno"));
                empleado.setApMaterno(rs.getString("ap_materno"));
                empleado.setDni(Integer.parseInt(rs.getString("dni")));
                empleado.setFechaNac(rs.getDate("fecha_nac"));
                empleado.setDireccion(rs.getString("direccion"));
                empleados.add(empleado);    
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return empleados;
    }
}
