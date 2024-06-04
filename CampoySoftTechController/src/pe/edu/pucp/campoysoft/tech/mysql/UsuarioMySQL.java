
package pe.edu.pucp.campoysoft.tech.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.rrhh.model.Administrador;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;
import pe.edu.pucp.campoysoft.rrhh.model.Persona;
import pe.edu.pucp.campoysoft.tech.dao.UsuarioDAO;
import pe.edu.pucp.campoysoft.tech.model.Usuario;


public class UsuarioMySQL implements UsuarioDAO{
    
    private Connection con;
    private java.sql.CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(Usuario usuario) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertUsuario(?,?,?,?)}");
            
            cs.registerOutParameter("_id_usuario",java.sql.Types.INTEGER);
            cs.setInt("p_fk_id_persona", usuario.getPersona().getIdPersona());
            cs.setString("p_username", usuario.getUsername());
            cs.setString("p_password", usuario.getPassword());
            
            cs.executeUpdate();
            usuario.setIdusuario(cs.getInt("_id_usuario"));
            resultado = 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();cs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Usuario usuario) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call UpdateUsuario(?,?,?,?)}");
            cs.setInt(1, usuario.getIdusuario());
            cs.setString(2, usuario.getUsername());
            cs.setString(3, usuario.getPassword());
            cs.setInt(4, usuario.isActivo() ? 1 : 0);
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
    public int eliminar(int idUsuario) {
         int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_EMPLEADO(?)}");
            cs.setInt("usuario_id", idUsuario);
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
    public ArrayList<Usuario> listarTodas() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
            try{
                con = DBManager.getInstance().getConnection();
                cs = con.prepareCall("{call ListUsuarios()}");
                rs = cs.executeQuery();
                while(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdusuario(rs.getInt("id_usuario"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setActivo(true);
                    usuarios.add(usuario);//añado un empleado al arraylist empleados
                }
                rs.close();
                cs.close();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }finally{
                try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            }
            return usuarios;//devuelve un arraylist con los datos de todos los empleados
    }

    @Override
    public int identificar_usu(String username, String password) {
        int i=0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call BuscarUsuario(?,?,?)}");
            cs.setString("p_username", username);
            cs.setString("p_password", password);
            cs.registerOutParameter("resultado",java.sql.Types.INTEGER);
            cs.executeUpdate();
            i=cs.getInt("resultado");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
                try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return i;
    }

    @Override
    public int obtenerDatos(String usuario, String pass) {
        int id = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ObtenerInformacionPersona(?,?)}");
            cs.setString("p_usuario", usuario);
            cs.setString("p_password", pass);
            rs = cs.executeQuery();

            if (rs.next()) {
                // Obtener el valor de fk_id_persona de la fila actual
                id = rs.getInt("id_persona");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return id;
    }

}
