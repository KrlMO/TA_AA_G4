/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.productotextil.dao.EspecificacionRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.EspecificacionRollo;

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
                cs.registerOutParameter("_id_especificacion_rollo", java.sql.Types.INTEGER); // registra como parametro de salida
                cs.setString("p_tipo_tela", especificacionRollo.getTipoTela().name());
                cs.setString("p_tipo_rollo", especificacionRollo.getTipoRollo().name());
                cs.setDouble("p_ancho_rollo",especificacionRollo.getAchoRollo());
                cs.setDouble("p_longitud_rollo",especificacionRollo.getLongitudRollo());
                cs.setDouble("p_area_rollo",especificacionRollo.getAreaRollo());
                cs.setDouble("p_peso_rollo",especificacionRollo.getPesoRollo()); 
    
                cs.executeUpdate();
                especificacionRollo.setIdEspecifiacionRollo(cs.getInt("_id_especificacion_rollo")); // seteas el id del empleado que se acaba de recibir
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
        public int modificar(EspecificacionRollo especificacionRollo) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int eliminar(int idEspecificacionRollo) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public ArrayList<EspecificacionRollo> listar() {
            ArrayList<EspecificacionRollo> especificacionesRollos = new ArrayList<>();
            try{

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }finally{
                try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            }
            return especificacionesRollos;//devuelve un arraylist con los datos de todos los empleados
        }
    
}
