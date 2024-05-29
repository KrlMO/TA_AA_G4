package pe.edu.pucp.campoysoft.onlinemarket.model;

import java.util.ArrayList;


public class Compra extends Atencion {
    private String codCompra;
    private ArrayList<LineaCompra> lineaCompras;
    private boolean activo;
    public Compra(){}
    public Compra(String codCompra, int idAtencion, EstadoAtencion estadoServicio, int canTotalRollos, double precioTotal, double pesoTotal, double areaTotal, int id_cliente,boolean activo,int idEmpleado) {
        super(idAtencion, estadoServicio, canTotalRollos, precioTotal, pesoTotal, areaTotal,id_cliente,idEmpleado);
        this.codCompra = codCompra;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(String codCompra) {
        this.codCompra = codCompra;
    }

    public ArrayList<LineaCompra> getLineaCompras() {
        return lineaCompras;
    }

    public void setLineaCompras(ArrayList<LineaCompra> lineaCompras) {
        this.lineaCompras = lineaCompras;
    }

   
    
    
}
