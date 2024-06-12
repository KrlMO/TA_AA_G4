package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.LineaCompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;


public class LineaCompraMySQL implements LineaCompraDAO{
    private Connection con;
    private java.sql.CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(LineaCompra lineaCompra) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertLineaCompra(?,?,?,?)}");
            
            cs.registerOutParameter("_id_linea_compra",java.sql.Types.INTEGER);
            cs.setInt("p_fk_id_compra", lineaCompra.getCompra().getIdAtencion());
            cs.setInt("p_fk_id_producto", lineaCompra.getProdRollo().getIdProducto());
            cs.setInt("p_cant_rollo", lineaCompra.getCantRollo());
            
            cs.executeUpdate();
            lineaCompra.setIdLineaCompra(cs.getInt("_id_linea_compra"));
            resultado = 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();cs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(LineaCompra lineaCompra) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateLineaCompra(?,?,?,?)}");
            cs.setInt("_id_linea_compra", lineaCompra.getIdLineaCompra());
            cs.setInt("p_fk_id_compra", lineaCompra.getCompra().getIdAtencion());
            cs.setInt("p_fk_id_producto", lineaCompra.getProdRollo().getIdProducto());
            cs.setInt("p_cant_rollo", lineaCompra.getCantRollo());
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
    public int elimniar(int codLinea) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteLineaCompraById(?)}");
            cs.setInt("linea_compra_id", codLinea);
            cs.executeUpdate();
            resultado = 1;
            cs.close();

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();cs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<LineaCompra> listarTodas() {
        ArrayList<LineaCompra> lineasCompras = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListLineaCompra()}");
            rs = cs.executeQuery();
            while(rs.next()){
                LineaCompra lineaCompra = new LineaCompra();
                lineaCompra.setIdLineaCompra(rs.getInt("id_linea_compra"));
                lineaCompra.getCompra().setCodCompra(rs.getString("fk_id_compra"));
                lineaCompra.getProdRollo().setIdProducto(rs.getInt("fk_id_producto"));
                lineaCompra.setCantRollo(rs.getInt("cant_rollo"));
                lineaCompra.setActivo(true); 
                lineasCompras.add(lineaCompra);
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return lineasCompras;
    }
  
}
