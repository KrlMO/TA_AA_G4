
package pe.edu.pucp.campoysoft.onlinemarket.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;

public interface CompraDAO {
    int insertar(Compra compra);
    int modificar(Compra compra);
    int elimniar(int codCompra);
    ArrayList<Compra> listarTodas();
    ArrayList<Compra> listarEmitidas();
    ArrayList<Compra> listarEntregadas();
    String actualizarStock(Compra compra);
}
