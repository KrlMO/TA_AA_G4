package pe.edu.pucp.campoysoft.onlinemarket.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;

public interface ServicioTinteDAO {
    int insertar(ServicioTinte servTinte , int prod_en_carrito);
    int modificar(ServicioTinte servTinte);
    int elimniar(int idServTinte);
    ArrayList<ServicioTinte> listarTodas();
    ArrayList<ServicioTinte> listarEmitidos();
    ArrayList<ServicioTinte> listarEntregadas();
}
