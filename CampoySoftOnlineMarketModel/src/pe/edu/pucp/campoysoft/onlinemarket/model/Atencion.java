package pe.edu.pucp.campoysoft.onlinemarket.model;

import pe.edu.pucp.campoysoft.rrhh.model.Cliente;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;


public class Atencion {
    private int idAtencion;
    private EstadoAtencion estadoServicio;
    private int canTotalRollos;
    private double precioTotal;
    private double pesoTotal;
    private double areaTotal;
    private Empleado empleado;
    private Cliente cliente;

    public Atencion(){}
    public Atencion(int idAtencion, EstadoAtencion estadoServicio, int canTotalRollos, double precioTotal, double pesoTotal, double areaTotal,Empleado empleado,Cliente cliente) {
        this.idAtencion = idAtencion;
        this.estadoServicio = estadoServicio;
        this.canTotalRollos = canTotalRollos;
        this.precioTotal = precioTotal;
        this.pesoTotal = pesoTotal;
        this.areaTotal = areaTotal;
        this.empleado = empleado;
        this.cliente = cliente;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public EstadoAtencion getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(EstadoAtencion estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public int getCanTotalRollos() {
        return canTotalRollos;
    }

    public void setCanTotalRollos(int canTotalRollos) {
        this.canTotalRollos = canTotalRollos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
