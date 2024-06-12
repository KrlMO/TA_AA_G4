package pe.edu.pucp.campoysoft.onlinemarket.model;

import pe.edu.pucp.campoysoft.productotextil.model.Tinte;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;


public class LineaServicioTinte {
    private int idLineaOrdenTinte;
    private double peso;
    private double ancho;
    private double area;
    private double longitud;
    private boolean activo;
    private Tinte tinteDestino;
    private TipoTela tipoTela;
    private ServicioTinte servTinte;

    public LineaServicioTinte(){}
    public LineaServicioTinte(int idLineaOrdenTinte, double peso, double ancho, double area, double longitud, Tinte tinteDestino, TipoTela tipoTela, ServicioTinte servTinte) {
        this.idLineaOrdenTinte = idLineaOrdenTinte;
        this.peso = peso;
        this.ancho = ancho;
        this.area = area;
        this.longitud = longitud;
        this.tinteDestino = tinteDestino;
        this.tipoTela = tipoTela;
        this.servTinte = servTinte;
    }

    public int getIdLineaOrdenTinte() {
        return idLineaOrdenTinte;
    }

    public void setIdLineaOrdenTinte(int idLineaOrdenTinte) {
        this.idLineaOrdenTinte = idLineaOrdenTinte;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Tinte getTinteDestino() {
        return tinteDestino;
    }

    public void setTinteDestino(Tinte tinteDestino) {
        this.tinteDestino = tinteDestino;
    }

    public TipoTela getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(TipoTela tipoTela) {
        this.tipoTela = tipoTela;
    }

    public ServicioTinte getServTinte() {
        return servTinte;
    }

    public void setServTinte(ServicioTinte servTinte) {
        this.servTinte = servTinte;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
