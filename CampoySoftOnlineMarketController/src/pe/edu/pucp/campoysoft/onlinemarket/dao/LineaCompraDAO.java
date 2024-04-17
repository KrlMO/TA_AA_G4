package pe.edu.pucp.campoysoft.onlinemarket.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaCompra;

public interface LineaCompraDAO {
    int insertar(LineaCompra lineaCompra);
    int modificar(LineaCompra lineaCompra);
    int elimniar(int codLinea);
    ArrayList<LineaCompra> listarTodas();
}
