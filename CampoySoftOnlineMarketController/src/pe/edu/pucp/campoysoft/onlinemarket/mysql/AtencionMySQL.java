package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.AtencionDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;

public class AtencionMySQL implements AtencionDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Atencion atencion) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertarAtencion(?,?,?,?,?,?)}");
            cs.setInt("p_fk_id_cliente", atencion.getCliente().getIdPersona());
            cs.setString("p_estado_servicio", atencion.getEstadoServicio().name());
            cs.setDouble("p_precio_total", atencion.getPrecioTotal());
            cs.setInt("p_cantidad_total_rollos", atencion.getCanTotalRollos());
            cs.setDouble("p_peso_total", atencion.getPesoTotal());
            cs.setDouble("p_area_total", atencion.getAreaTotal());            
            cs.executeUpdate();
            resultado = 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();cs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Atencion atencion) {
       
        return 0;
       
    }

    @Override
    public int eliminar(int idAtencion) {
        
        return 0;
        
    }

    @Override
    public ArrayList<Atencion> listarTodas() {
        
        return null;
    }
    
}
