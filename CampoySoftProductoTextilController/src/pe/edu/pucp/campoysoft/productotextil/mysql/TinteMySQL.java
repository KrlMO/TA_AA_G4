/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.mysql;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.productotextil.dao.TinteDAO;
import pe.edu.pucp.campoysoft.productotextil.model.Tinte;

public class TinteMySQL implements TinteDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Tinte tinte){
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertTinte(?,?,?,?,?)}");
            cs.registerOutParameter("_id_tinte", java.sql.Types.INTEGER); // registra como parametro de salida
            cs.setString("p_nombre", tinte.getNombre());
            cs.setInt("p_R", tinte.getR());
            cs.setInt("p_G", tinte.getG());
            cs.setInt("p_B", tinte.getB());
            cs.executeUpdate();
            tinte.setIdTinte(cs.getInt("_id_tinte")); // seteas el id del empleado que se acaba de recibir
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
    public int modificar(Tinte tinte) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateTinte(?,?,?,?,?,?)}");
            cs.setInt("tinte_id", tinte.getIdTinte());
            cs.setString("nuevo_cod_tinte", tinte.getCodTinte());
            cs.setString("nuevo_nombre", tinte.getNombre());
            cs.setInt("nuevo_r", tinte.getR());
            cs.setInt("nuevo_g", tinte.getG());
            cs.setInt("nuevo_b", tinte.getB());
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
    public int eliminar(int idTinte) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteTinteById(?)}");
            cs.setInt("tinte_id", idTinte);
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
    public ArrayList<Tinte> listar() {
        ArrayList<Tinte> tintes = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListTintes()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Tinte tinte = new Tinte();
                tinte.setIdTinte(rs.getInt("id_tinte"));
                tinte.setCodTinte(rs.getString("cod_tinte"));
                tinte.setNombre(rs.getString("nombre"));
                tinte.setR(rs.getInt("R"));
                tinte.setG(rs.getInt("G"));
                tinte.setB(rs.getInt("B"));
                tinte.setActivo(true);
                tintes.add(tinte);//añado un empleado al arraylist empleados
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return tintes;
    }
    @Override
    public Tinte ObtenerTinte(int id) {
        Tinte tinte = null;
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde el DBManager
            con = DBManager.getInstance().getConnection();

            // Preparar la llamada al procedimiento almacenado
            cs = con.prepareCall("{call ListTinte(?)}");
            cs.setInt(1, id);

            // Ejecutar la consulta
            rs = cs.executeQuery();

            // Verificar si hay resultados
            if (rs.next()) {
                // Crear y mapear el objeto Tinte
                tinte = new Tinte();
                tinte.setIdTinte(rs.getInt("id_tinte"));
                tinte.setNombre(rs.getString("nombre"));
                tinte.setR(rs.getInt("R"));
                tinte.setG(rs.getInt("G"));
                tinte.setB(rs.getInt("B"));
                // Agrega aquí las propiedades adicionales según la estructura de la tabla
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return tinte;
    }
}
