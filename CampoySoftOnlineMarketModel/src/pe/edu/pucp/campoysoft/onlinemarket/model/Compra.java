package pe.edu.pucp.campoysoft.onlinemarket.model;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;


public class Compra extends Atencion {
    private int codCompra;
    private ArrayList<LineaCompra> lineaCompras;
    
    public Compra(){}
    public Compra(int codCompra, int idAtencion, EstadoAtencion estadoServicio, int canTotalRollos, double precioTotal, double pesoTotal, double areaTotal,Empleado empleado, Cliente cliente) {
        super(idAtencion, estadoServicio, canTotalRollos, precioTotal, pesoTotal, areaTotal,empleado,cliente);
        this.codCompra = codCompra;
    }

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public ArrayList<LineaCompra> getLineaCompras() {
        return lineaCompras;
    }

    public void setLineaCompras(ArrayList<LineaCompra> lineaCompras) {
        this.lineaCompras = lineaCompras;
    }

   
    
    
}
