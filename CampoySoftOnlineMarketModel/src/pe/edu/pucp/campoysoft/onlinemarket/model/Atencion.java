package pe.edu.pucp.campoysoft.onlinemarket.model;


public class Atencion {
    private int idAtencion;
    private EstadoAtencion estadoServicio;
    private int canTotalRollos;
    private double precioTotal;
    private double pesoTotal;
    private double areaTotal;
    private int idCliente;
    private int idEmpleado;

    public Atencion(){}
    public Atencion(int idAtencion, EstadoAtencion estadoServicio, int canTotalRollos, double precioTotal, double pesoTotal, double areaTotal,int id_cliente,int idEmpleado) {
        this.idAtencion = idAtencion;
        this.estadoServicio = estadoServicio;
        this.canTotalRollos = canTotalRollos;
        this.precioTotal = precioTotal;
        this.pesoTotal = pesoTotal;
        this.areaTotal = areaTotal;
        this.idCliente = id_cliente;
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public int getCliente() {
        return this.idCliente;
    }

    public void setCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
