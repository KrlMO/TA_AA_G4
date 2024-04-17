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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int codLinea) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<LineaCompra> listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
}
