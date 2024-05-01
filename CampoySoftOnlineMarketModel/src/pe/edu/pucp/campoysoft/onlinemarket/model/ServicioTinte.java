package pe.edu.pucp.campoysoft.onlinemarket.model;

import java.util.ArrayList;


public class ServicioTinte extends Atencion {
    private int codServicioTinte;
    private double horasTintado;
    private ArrayList<LineaServicioTinte> lineaServicios;
    
    public ServicioTinte(){}
    
    public ServicioTinte(int codServicioTinte, double horasTintado, int idAtencion, EstadoAtencion estadoServicio, int canTotalRollos, double precioTotal, double pesoTotal, double areaTotal,int id_cliente,int idEmpleado) {
        super(idAtencion, estadoServicio, canTotalRollos, precioTotal, pesoTotal, areaTotal,id_cliente,idEmpleado);
        this.codServicioTinte = codServicioTinte;
        this.horasTintado = horasTintado;
    }

    public int getCodServicioTinte() {
        return codServicioTinte;
    }

    public void setCodServicioTinte(int codServicioTinte) {
        this.codServicioTinte = codServicioTinte;
    }

    public double getHorasTintado() {
        return horasTintado;
    }

    public void setHorasTintado(double horasTintado) {
        this.horasTintado = horasTintado;
    }

    public ArrayList<LineaServicioTinte> getLineaServicios() {
        return lineaServicios;
    }

    public void setLineaServicios(ArrayList<LineaServicioTinte> lineaServicios) {
        this.lineaServicios = lineaServicios;
    }
    
    
}
