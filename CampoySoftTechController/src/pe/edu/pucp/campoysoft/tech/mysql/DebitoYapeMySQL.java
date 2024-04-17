/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.tech.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

    @Override
    public int insertar(DebitoYape debito) {
        int resultado = 0;
        try{
            try (Connection con = DBManager.getInstance().getConnection()) {
                String sql = "insert into DebitoYape(codigo_pago_yape,"
                        + "qr,fk_id_atencion,numero_celular_yape ,fecha_hora_emision"
                        + "fecha_hora_pago) "
                        + "values (?,?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                
                statement.setString(1, debito.getCodigo_pago_yape());
                //statement.setString(2, debito.getCliente().getCodCliente()); // fucking blob
                statement.setInt(3, debito.getAtencion().getIdAtencion());
                statement.setString(4, debito.getNumero_cel());
                statement.setDate(5, (Date) debito.getFecha_hora_emision());
                statement.setDate(6, (Date) debito.getFecha_hora_pago());

                resultado = statement.executeUpdate();
                System.out.println("Resultado " + resultado);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int modificar(DebitoYape debito) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idDebito) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DebitoYape> listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
