package pe.edu.pucp.campoysoft.tech.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.tech.model.DebitoYape;

public interface DebitoYapeDAO {
    int insertar(DebitoYape debito);
    int modificar(DebitoYape debito);
    int eliminar(int idDebito);
    ArrayList<DebitoYape> listarTodas();
}
