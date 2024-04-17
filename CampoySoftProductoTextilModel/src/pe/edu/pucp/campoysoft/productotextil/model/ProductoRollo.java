/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.model;

/**
 *
 * @author samt1
 */
public class ProductoRollo {
    private int idProducto;
    private TipoTela tipoTela;
    private double longitud;
    private double precioUnitario;
    private double peso;
    private double ancho;
    private double area;
    private int stock;
    private Tinte tinte;
    private String descripcion;
    private boolean activo;

    public ProductoRollo(int idProducto, TipoTela tipoTela, double longitud, double precioUnitario, double peso, double ancho, double area, int stock, Tinte tinte, String descripcion, boolean activo) {
        this.idProducto = idProducto;
        this.tipoTela = tipoTela;
        this.longitud = longitud;
        this.precioUnitario = precioUnitario;
        this.peso = peso;
        this.ancho = ancho;
        this.area = area;
        this.stock = stock;
        this.tinte = tinte;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public TipoTela getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(TipoTela tipoTela) {
        this.tipoTela = tipoTela;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Tinte getTinte() {
        return tinte;
    }

    public void setTinte(Tinte tinte) {
        this.tinte = tinte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
