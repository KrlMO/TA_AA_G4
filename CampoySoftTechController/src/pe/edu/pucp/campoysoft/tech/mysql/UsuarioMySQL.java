
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
    public Persona obtenerDatos(String usuario, String pass, int tipo) {
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ObtenerInformacionPersona(?,?,?)}");
            cs.setString("p_usuario", usuario);
            cs.setString("p_password", pass);
            rs = cs.executeQuery();
            
            if(tipo==1){
                Cliente cli = new Cliente();
                cli.setIdPersona(rs.getInt("id_persona"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApPaterno(rs.getString("ap_paterno"));
                cli.setApMaterno(rs.getString("ap_materno"));
                rs.close();
                cs.close();
                return cli;
            }
            if(tipo==2){
                Empleado emp = new Empleado();
                emp.setIdPersona(rs.getInt("id_persona"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApPaterno(rs.getString("ap_paterno"));
                emp.setApMaterno(rs.getString("ap_materno"));
                rs.close();
                cs.close();
                return emp;
            }
            if(tipo==3){
                Administrador admin = new Administrador();
                admin.setIdPersona(rs.getInt("id_persona"));
                admin.setNombre(rs.getString("nombre"));
                admin.setApPaterno(rs.getString("ap_paterno"));
                admin.setApMaterno(rs.getString("ap_materno"));
                rs.close();
                cs.close();
                return admin;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }
    
}
