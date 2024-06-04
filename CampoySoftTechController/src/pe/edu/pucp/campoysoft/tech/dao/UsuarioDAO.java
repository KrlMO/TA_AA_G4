package pe.edu.pucp.campoysoft.tech.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.tech.model.Usuario;

public interface UsuarioDAO {
    int insertar(Usuario usuario);
    int modificar(Usuario usuario);
    int eliminar(int idUsuario);
    ArrayList<Usuario> listarTodas();
    int identificar_usu(String username,String password);
}
