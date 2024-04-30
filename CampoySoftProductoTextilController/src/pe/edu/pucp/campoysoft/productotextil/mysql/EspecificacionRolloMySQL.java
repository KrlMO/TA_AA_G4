/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.productotextil.dao.EspecificacionRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.EspecificacionRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;

/**
 *
 * @author samt1
 */
public class EspecificacionRolloMySQL implements EspecificacionRolloDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;

    @Override//seria las misma plantilla para los demas CRUD's exepto para listar
        public int insertar(EspecificacionRollo especificacionRollo){
            int resultado = 0;
            try{
                con = DBManager.getInstance().getConnection();
                cs = con.prepareCall("{call InsertEspecificacionRollo(?,?,?,?,?,?,?)}");
                cs.registerOutParameter("_id_especificacion_rollo", java.sql.Types.INTEGER);
                cs.setString("p_tipo_tela", especificacionRollo.getTipoTela().name());
                cs.setString("p_tipo_rollo", especificacionRollo.getTipoRollo().name());
                cs.setDouble("p_ancho_rollo",especificacionRollo.getAnchoRollo());
                cs.setDouble("p_longitud_rollo",especificacionRollo.getLongitudRollo());
                cs.setDouble("p_area_rollo",especificacionRollo.getAreaRollo());
                cs.setDouble("p_peso_rollo",especificacionRollo.getPesoRollo()); 
    
                cs.executeUpdate();
                especificacionRollo.setIdEspecifiacionRollo(cs.getInt("_id_especificacion_rollo")); 
                resultado = 1;
                cs.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }finally{
                try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            }
            return resultado;
        }

        @Override
        public int modificar(EspecificacionRollo especificacionRollo) {
            int resultado = 0;
            try{
                con = DBManager.getInstance().getConnection();
                cs = con.prepareCall("{call UpdateEspecificacionRollo(?,?,?,?,?,?,?)}");
                cs.setInt("espe_rollo_id", especificacionRollo.getIdEspecifiacionRollo());
                cs.setString("nuevo_tipo_tela", especificacionRollo.getTipoTela().name());
                cs.setString("nuevo_tipo_rollo", especificacionRollo.getTipoRollo().name());
                cs.setDouble("nuevo_longitud", especificacionRollo.getLongitudRollo());
                cs.setDouble("nuevo_ancho", especificacionRollo.getAnchoRollo());
                cs.setDouble("nuevo_area", especificacionRollo.getAreaRollo());
                cs.setDouble("nuevo_peso", especificacionRollo.getPesoRollo());
                cs.executeUpdate();
                resultado = 1;
                cs.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }finally{
                try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            }
            return resultado;
        }

        @Override
        public int eliminar(int idEspecificacionRollo) {
            int resultado = 0;
            try{
                con = DBManager.getInstance().getConnection();
                cs = con.prepareCall("{call DeleteEspecificacionRolloById(?)}");
                cs.setInt("espe_rollo_id", idEspecificacionRollo);
                cs.executeUpdate();
                resultado = 1;
                cs.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }finally{
                try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            }
            return resultado;
        }

        @Override
        public ArrayList<EspecificacionRollo> listar() {
            ArrayList<EspecificacionRollo> especificacionesRollo = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListEspecificacionRollo()}");
            rs = cs.executeQuery();
            while(rs.next()){
                EspecificacionRollo especificacion = new EspecificacionRollo();
                especificacion.setIdEspecifiacionRollo(rs.getInt("id_especificacion_rollo"));
                especificacion.setTipoTela(TipoTela.valueOf(rs.getString("tipo_tela")));
                especificacion.setTipoRollo(TipoRollo.valueOf(rs.getString("tipo_rollo")));
                especificacion.setLongitudRollo(rs.getDouble("longitud_rollo"));
                especificacion.setAnchoRollo(rs.getDouble("ancho_rollo"));
                especificacion.setAreaRollo(rs.getDouble("area_rollo"));
                especificacion.setPesoRollo(rs.getDouble("peso_rollo"));
            }
            rs.close();
            cs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return especificacionesRollo;
        }
    
}
