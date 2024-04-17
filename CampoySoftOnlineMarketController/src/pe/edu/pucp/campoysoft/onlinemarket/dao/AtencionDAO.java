package pe.edu.pucp.campoysoft.onlinemarket.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;

public interface AtencionDAO {
    int insertar(Atencion atencion);
    int modificar(Atencion atencion);
    int eliminar(int idAtencion);
    ArrayList<Atencion> listarTodas();
}
