/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.tech.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.tech.dao.DebitoYapeDAO;
import pe.edu.pucp.campoysoft.tech.model.DebitoYape;

/**
 *
 * @author USER
 */
public class DebitoYapeMySQL implements DebitoYapeDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    @Override
    public int insertar(DebitoYape debito) {
        
        int resultado = 0;
            try{
                con = DBManager.getInstance().getConnection();
                cs = con.prepareCall("{call InsertDebitoYape(?,?,?,?,?,?)}");
                cs.setInt("p_codigo_pago_yape", debito.getId_debito_yape());
                cs.setBlob("p_qr", debito.getQr());
                cs.setInt("p_fk_id_atencion",debito.getAtencion().getIdAtencion());
                cs.setString("p_numero_celular_yape",debito.getNumero_cel());
                cs.setDate("p_fecha_hora_emisio", new java.sql.Date(debito.getFecha_hora_emision().getTime()));
                cs.setDate("p_fecha_hora_pago", new java.sql.Date(debito.getFecha_hora_pago().getTime()));
                cs.executeUpdate();
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
    public int modificar(DebitoYape debito) {
        int resultado = 0;
        try{
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }


    
}
