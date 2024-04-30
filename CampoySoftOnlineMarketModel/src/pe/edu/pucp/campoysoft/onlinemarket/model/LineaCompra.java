package pe.edu.pucp.campoysoft.onlinemarket.model;

import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.TipoTela;

public class LineaCompra {

    private int idLineaCompra;
    private int cantRollo;
    private Compra compra;
    private ProductoRollo prodRollo;
    private boolean activo;
    public LineaCompra(){
        
    }

    public LineaCompra(int cantRollo, Compra compra, ProductoRollo prodRollo, boolean activo) {
        this.cantRollo = cantRollo;
        this.compra = compra;
        this.prodRollo = prodRollo;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }



    public int getIdLineaCompra() {
        return idLineaCompra;
    }

    public void setIdLineaCompra(int idLineaCompra) {
        this.idLineaCompra = idLineaCompra;
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
