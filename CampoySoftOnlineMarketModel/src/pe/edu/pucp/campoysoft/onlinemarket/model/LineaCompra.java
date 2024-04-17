package pe.edu.pucp.campoysoft.onlinemarket.model;

import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;

public class LineaCompra {
    private static int index=0;
    private int idLineaCompra;
    private double longitud;
    private double precioUnitario;
    private double peso;
    private double ancho;
    private double area;
    private TipoTela tipoTela;
    private int cantRollo;
    private Compra compra;
    private ProductoRollo prodRollo;

    public LineaCompra(double longitud, double precioUnitario, double peso, double ancho, double area, TipoTela tipoTela, int cantRollo,Compra compra, ProductoRollo prodRollo) {
        this.idLineaCompra = ++index;
        this.longitud = longitud;
        this.precioUnitario = precioUnitario;
        this.peso = peso;
        this.ancho = ancho;
        this.area = area;
        this.tipoTela = tipoTela;
        this.cantRollo = cantRollo;
        this.compra = compra;
        this.prodRollo = prodRollo;
    }

    public int getIdLineaCompra() {
        return idLineaCompra;
    }

    public void setIdLineaCompra(int idLineaCompra) {
        this.idLineaCompra = idLineaCompra;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public TipoTela getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(TipoTela tipoTela) {
        this.tipoTela = tipoTela;
    }

    public int getCantRollo() {
        return cantRollo;
    }

    public void setCantRollo(int cantRollo) {
        this.cantRollo = cantRollo;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ProductoRollo getProdRollo() {
        return prodRollo;
    }

    public void setProdRollo(ProductoRollo prodRollo) {
        this.prodRollo = prodRollo;
    }
    
}
