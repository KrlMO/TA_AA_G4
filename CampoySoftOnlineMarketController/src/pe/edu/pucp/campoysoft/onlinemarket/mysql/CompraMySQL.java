package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import com.mysql.cj.jdbc.CallableStatement;
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
    private java.sql.CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Compra compra) {
       int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertCompra(?,?,?,?,?,?,?,?)}");
            
            cs.registerOutParameter("_id_compra",java.sql.Types.INTEGER);
            cs.setInt("p_fk_id_cliente", compra.getCliente().getIdPersona());
            cs.setString("p_estado_servicio", compra.getEstadoServicio().name());
            cs.setDouble("p_precio_total", compra.getPrecioTotal());
            cs.setInt("p_cantidad_total_rollos", compra.getCanTotalRollos());
            cs.setDouble("p_peso_total", compra.getPesoTotal());
            cs.setDouble("p_area_total", compra.getAreaTotal());
            cs.setString("p_cod_compra", Integer.toString(compra.getCodCompra()));
            
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
            try (Connection con = DBManager.getInstance().getConnection()) {
                String sql = "{CALL UpdateCompra(?,?,?,?,?,?,?,?,?)}";
                CallableStatement cs = (CallableStatement) con.prepareCall(sql);
                
                cs.setInt(1, compra.getIdAtencion());
                cs.setInt(2, compra.getIdAtencion());
                cs.setInt(3, compra.getIdAtencion());
                cs.setInt(4, compra.getIdAtencion());
                cs.setInt(5, compra.getIdAtencion());
                cs.setInt(6, compra.getIdAtencion());
                cs.setInt(7, compra.getIdAtencion());
                cs.setInt(8, compra.getIdAtencion());
                cs.setInt(9, compra.getIdAtencion());
            }
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
                compra.setCanTotalRollos(rs.getInt("cantidad_total_rollos"));
                compra.setPrecioTotal(rs.getDouble("precio_total"));
                compra.setPesoTotal(rs.getDouble("peso_total"));
                compra.setAreaTotal(rs.getDouble("area_total"));
                compra.setCodCompra(rs.getInt("id_compra"));
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
