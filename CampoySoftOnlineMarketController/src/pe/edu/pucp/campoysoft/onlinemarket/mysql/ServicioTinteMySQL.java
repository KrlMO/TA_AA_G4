
package pe.edu.pucp.campoysoft.onlinemarket.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;

public class ServicioTinteMySQL implements ServicioTinteDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(ServicioTinte servTinte) {
       int resultado = 0;
        try{
            try (Connection con = DBManager.getInstance().getConnection()) {
                String sql = "insert into ServicioTinte(id_servicio_tinte,"
                        + "cod_servicio_tinte,horas_tintado) "
                        + "values (?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, servTinte.getIdAtencion());
                statement.setString(2, Integer.toString(servTinte.getCodServicioTinte()));
                statement.setDouble(3, servTinte.getHorasTintado()); 
                
                resultado = statement.executeUpdate();
                System.out.println("Resultado " + resultado);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int modificar(ServicioTinte servTinte) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     
}
