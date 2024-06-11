package pe.edu.pucp.campoysoft.onlinemarket.mysql;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.CompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;

public class CompraMySQL implements CompraDAO{
     private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Compra compra) {
       int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertCompra(?,?,?,?,?,?,?,?,?)}");
            
            cs.registerOutParameter("_id_compra",java.sql.Types.INTEGER);
            cs.setInt("p_fk_id_cliente", compra.getCliente());
            cs.setString("p_estado_servicio", compra.getEstadoServicio().name());
            cs.setDouble("p_precio_total", compra.getPrecioTotal());
            cs.setInt("p_cantidad_total_rollos", compra.getCanTotalRollos());
            cs.setDouble("p_peso_total", compra.getPesoTotal());
            cs.setDouble("p_area_total", compra.getAreaTotal());
            cs.setString("p_cod_compra", compra.getCodCompra());
            cs.setInt("p_id_carrito", compra.getIdCarrito());
            cs.executeUpdate();
            compra.setIdAtencion(cs.getInt("_id_compra"));
            resultado = 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();cs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Compra compra) {
        int resultado = 0;
        try{
             con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateCompra(?,?,?,?,?,?,?,?,?,?)}");
            
            cs.setInt("atencion_id", compra.getIdAtencion());
            cs.setString("nuevo_estado_servicio", compra.getEstadoServicio().name());
            cs.setDouble("nuevo_precio_total", compra.getPrecioTotal());
            cs.setInt("nueva_cantidad_total_rollos", compra.getCanTotalRollos());
            cs.setDouble("nuevo_peso_total", compra.getPesoTotal());
            cs.setDouble("nuevo_area_total", compra.getAreaTotal());
            cs.setString("nuevo_cod_compra",  String.valueOf(compra.getCodCompra()));
            cs.setInt("nuevo_fk_id_empleado", compra.getIdEmpleado());
            cs.setBoolean("nuevo_activo_comp", true);
            cs.setInt("nuevo_id_carrito", compra.getIdCarrito());
            cs.executeUpdate();
            resultado = 1;
            cs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int elimniar(int codCompra) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteCompraById(?)}");
            cs.setInt("compra_id", codCompra);
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
    public ArrayList<Compra> listarTodas() {
        ArrayList<Compra> compras = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListCompras()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Compra compra = new Compra();
                compra.setIdAtencion(rs.getInt("id_atencion"));
                EstadoAtencion estado = EstadoAtencion.valueOf(rs.getString("estado_servicio"));
                compra.setEstadoServicio(estado);
                compra.setCodCompra(rs.getString("cod_compra"));
                compra.setIdCliente(rs.getInt("fk_id_cliente"));
                compra.setIdEmpleado(rs.getInt("fk_id_empleado"));
                compras.add(compra);  
            }
            rs.close();
            cs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return compras;
    }
    
}
