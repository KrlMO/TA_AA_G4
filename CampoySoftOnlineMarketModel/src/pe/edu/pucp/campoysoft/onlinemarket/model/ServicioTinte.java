package pe.edu.pucp.campoysoft.onlinemarket.model;

import java.util.ArrayList;


public class ServicioTinte extends Atencion {
    private String codServicioTinte;
    private int id_servicio;
    private double horasTintado;
    private ArrayList<LineaServicioTinte> lineaServicios;
    
    public ServicioTinte(){}
    
    public ServicioTinte(String codServicioTinte, double horasTintado, int idAtencion, EstadoAtencion estadoServicio, int canTotalRollos, double precioTotal, double pesoTotal, double areaTotal,int id_cliente,int idEmpleado) {
        super(idAtencion, estadoServicio, canTotalRollos, precioTotal, pesoTotal, areaTotal,id_cliente,idEmpleado);
        this.codServicioTinte = codServicioTinte;
        this.horasTintado = horasTintado;
        this.lineaServicios = new ArrayList<>();
    }

    public String getCodServicioTinte() {
        return codServicioTinte;
    }

    public void setCodServicioTinte(String codServicioTinte) {
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

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }
    
    
}
