
package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaServicioTinte;


public class LineaServicioTinteMySQL implements LineaServicioTinteDAO{

    @Override
    public int insertar(LineaServicioTinte lineaServTinte) {
        int resultado = 0;
        try{
            try (Connection con = DBManager.getInstance().getConnection()) {
                String sql = "insert into LineaServicioTinte(fk_id_servicio_tinte,"
                        + "fk_id_tinte_destino,tipo_tela_recibida,longitud_recibida,peso_recibida,ancho_recibida,area_recibida) "
                        + "values (?,?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, lineaServTinte.getServTinte().getCodServicioTinte());
                statement.setInt(2, lineaServTinte.getTinteDestino().getIdTinte());
                statement.setString(3, lineaServTinte.getTipoTela().name());
                statement.setDouble(4, lineaServTinte.getLongitud());
                statement.setDouble(5, lineaServTinte.getPeso());
                statement.setDouble(6, lineaServTinte.getAncho());
                statement.setDouble(7, lineaServTinte.getArea());

                
                resultado = statement.executeUpdate();
                System.out.println("Resultado " + resultado);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
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
        ArrayList<ServicioTinte> servtintes = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListServicioTintes()}");
            rs = cs.executeQuery();
            while(rs.next()){
                ServicioTinte servtinte = new ServicioTinte();
                servtinte.setIdAtencion(rs.getInt("id_atencion"));
                EstadoAtencion estado = EstadoAtencion.valueOf(rs.getString("estado_servicio"));
                servtinte.setEstadoServicio(estado);
                servtinte.setCanTotalRollos(rs.getInt("cantidad_total_rollos"));
                servtinte.setPrecioTotal(rs.getDouble("precio_total"));
                servtinte.setPesoTotal(rs.getDouble("peso_total"));
                servtinte.setAreaTotal(rs.getDouble("area_total"));
                servtinte.setCodServicioTinte(rs.getInt("cod_servicio_tinte"));
                servtinte.setHorasTintado(rs.getDouble("horas_tintado"));
                servtintes.add(servtinte);
            }
            rs.close();
            cs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return servtintes;
    }

}
