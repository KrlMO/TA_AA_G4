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
    private EspecificacionRollo especificiacionRollo;
    private Tinte tinte;
    private int stock;
    private String descripcion;
    private boolean activo;
    private double precioXmetro2;
    
    public ProductoRollo(){}
    
    public ProductoRollo(EspecificacionRollo especificiacionRollo, Tinte tinte, int stock, String descripcion, boolean activo, double precioXmetro2) {
        this.especificiacionRollo = especificiacionRollo;
        this.tinte = tinte;
        this.stock = stock;
        this.descripcion = descripcion;
        this.activo = activo;
        this.precioXmetro2 = precioXmetro2;
    }
    

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public EspecificacionRollo getEspecificiacionRollo() {
        return especificiacionRollo;
    }

    public void setEspecificiacionRollo(EspecificacionRollo especificiacionRollo) {
        this.especificiacionRollo = especificiacionRollo;
    }

    public Tinte getTinte() {
        return tinte;
    }

    public void setTinte(Tinte tinte) {
        this.tinte = tinte;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public double getPrecioXmetro2() {
        return precioXmetro2;
    }

    public void setPrecioXmetro2(double precioXmetro2) {
        this.precioXmetro2 = precioXmetro2;
    }
    


   
    
    
}
