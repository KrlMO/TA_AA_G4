
package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;

public class ServicioTinteMySQL implements ServicioTinteDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(ServicioTinte servTinte, int prod_en_carrito) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertServicioTinte(?,?,?,?,?,?,?,?,?)}");

            cs.registerOutParameter("_id_servicio_tinte",java.sql.Types.INTEGER);
            cs.setInt("p_fk_id_cliente", servTinte.getCliente());
            cs.setString("p_estado_servicio", servTinte.getEstadoServicio().name());
            cs.setDouble("p_precio_total", servTinte.getPrecioTotal());
            cs.setInt("p_cantidad_total_rollos", servTinte.getCanTotalRollos());
            cs.setDouble("p_peso_total", servTinte.getPesoTotal());
            cs.setDouble("p_area_total", servTinte.getAreaTotal());
            cs.setDouble("p_horas_tintado", servTinte.getHorasTintado());
            cs.setInt("p_prod_en_carrito",prod_en_carrito);
            
            cs.executeUpdate();
            servTinte.setIdAtencion(cs.getInt("_id_servicio_tinte"));
            resultado = 1;
            cs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return 2;
        }
        return resultado;       
    }

    @Override
    public int modificar(ServicioTinte servTinte) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateServicioTinte(?,?,?,?,?,?,?,?,?,?,?)}");
            
            cs.setInt("atencion_id", servTinte.getIdAtencion());
            cs.setString("nuevo_estado_servicio", servTinte.getEstadoServicio().name());
            cs.setDouble("nuevo_precio_total", servTinte.getPrecioTotal());
            cs.setInt("nueva_cantidad_total_rollos", servTinte.getCanTotalRollos());
            cs.setDouble("nuevo_peso_total", servTinte.getPesoTotal());
            cs.setDouble("nuevo_area_total", servTinte.getAreaTotal());
            cs.setString("nuevo_cod_servicio_tinte",  String.valueOf(servTinte.getCodServicioTinte()));
            cs.setDouble("nuevo_horas_tintado", servTinte.getHorasTintado());
            cs.setInt("nuevo_fk_id_empleado", servTinte.getIdEmpleado());
            cs.setBoolean("nuevo_activo_serv", true);
            cs.setInt("nuevo_id_carrito", servTinte.getIdCarrito());
            cs.executeUpdate();
            resultado = 1;
            cs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int elimniar(int idServTinte) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteServicioTinteById(?)}");
            cs.setInt("servicio_id", idServTinte);
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
    public ArrayList<ServicioTinte> listarTodas() {
        ArrayList<ServicioTinte> listServ = new ArrayList<>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListServicioTintes()}");
            rs = cs.executeQuery();
            int cant;
            while(rs.next()){
                ServicioTinte serv = new ServicioTinte();
                serv.setIdAtencion(rs.getInt("id_atencion"));
                serv.setIdCliente(rs.getInt("fk_id_cliente"));
                EstadoAtencion est = EstadoAtencion.valueOf(rs.getString("estado_servicio"));
                serv.setEstadoServicio(est);
                serv.setIdEmpleado(rs.getInt("fk_id_empleado"));
                serv.setPrecioTotal(rs.getDouble("precio_total"));
                listServ.add(serv);
            }
            rs.close();
            cs.close();
            cant = listServ.size();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return listServ;
    }

    @Override
    public ArrayList<ServicioTinte> listarEmitidos() {
        ArrayList<ServicioTinte> listServ = new ArrayList<>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListServicioTintesEmitidos()}");
            rs = cs.executeQuery();
            int cant;
            while(rs.next()){
                ServicioTinte serv = new ServicioTinte();
                serv.setIdAtencion(rs.getInt("id_atencion"));
                serv.setIdCliente(rs.getInt("fk_id_cliente"));
                EstadoAtencion est = EstadoAtencion.valueOf(rs.getString("estado_servicio"));
                serv.setEstadoServicio(est);
                serv.setIdEmpleado(rs.getInt("fk_id_empleado"));
                serv.setPrecioTotal(rs.getDouble("precio_total"));
                listServ.add(serv);
            }
            rs.close();
            cs.close();
            cant = listServ.size();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return listServ;
    }

    @Override
    public ArrayList<ServicioTinte> listarEntregadas() {
        ArrayList<ServicioTinte> listServ = new ArrayList<>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListServicioTintesEntregados()}");
            rs = cs.executeQuery();
            int cant;
            while(rs.next()){
                ServicioTinte serv = new ServicioTinte();
                serv.setIdAtencion(rs.getInt("id_atencion"));
                serv.setIdCliente(rs.getInt("fk_id_cliente"));
                EstadoAtencion est = EstadoAtencion.valueOf(rs.getString("estado_servicio"));
                serv.setEstadoServicio(est);
                serv.setIdEmpleado(rs.getInt("fk_id_empleado"));
                serv.setPrecioTotal(rs.getDouble("precio_total"));
                listServ.add(serv);
            }
            rs.close();
            cs.close();
            cant = listServ.size();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return listServ;
    }
}
