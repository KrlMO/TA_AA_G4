
package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;


public class LineaServicioTinteMySQL implements LineaServicioTinteDAO{
    private Connection con;
    private java.sql.CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(LineaServicioTinte lineaServTinte) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertLineaServicioTinte(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_linea_servicio_tinte",java.sql.Types.INTEGER);
            cs.setInt("p_fk_id_servicio_tinte", lineaServTinte.getServTinte().getIdAtencion());
            cs.setInt("p_fk_id_tinte_destino", lineaServTinte.getTinteDestino().getIdTinte());
            cs.setString("p_tipo_tela_recibida", lineaServTinte.getTipoTela().name());
            cs.setDouble("p_longitud_recibida", lineaServTinte.getLongitud());
            cs.setDouble("p_peso_recibida", lineaServTinte.getPeso());
            cs.setDouble("p_ancho_recibida", lineaServTinte.getAncho());
            cs.setDouble("p_area_recibida", lineaServTinte.getArea());
            
            cs.executeUpdate();
            lineaServTinte.setIdLineaOrdenTinte(cs.getInt("_id_linea_servicio_tinte"));
            resultado = 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return 44;
        }
        return resultado;
    }

    @Override
    public int modificar(LineaServicioTinte lineaServTinte) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int elimniar(int idLinSerTinte) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<LineaServicioTinte> listarTodas() {
        ArrayList<LineaServicioTinte> lineasServ = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListLineaServicioTinte()}");
            rs = cs.executeQuery();
            while(rs.next()){
                LineaServicioTinte lineaServicio = new LineaServicioTinte();
                
                lineaServicio.setIdLineaOrdenTinte(rs.getInt("id_linea_servicio_tinte"));
                lineaServicio.getServTinte().setCodServicioTinte(rs.getString("fk_id_servicio_tinte"));
                lineaServicio.getTinteDestino().setIdTinte(rs.getInt("fk_id_tinte_destino"));
                TipoTela tipoTela = TipoTela.valueOf(rs.getString("tipo_tela_recibida"));
                
                lineaServicio.setTipoTela(tipoTela);
                lineaServicio.setLongitud(rs.getDouble("longitud_recibida"));  
                lineaServicio.setPeso(rs.getDouble("peso_recibida"));
                lineaServicio.setAncho(rs.getDouble("ancho_recibida"));
                lineaServicio.setArea(rs.getDouble("area_recibida"));
                lineaServicio.setActivo(rs.getBoolean("activo"));
                lineasServ.add(lineaServicio);
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return lineasServ;
    }
}