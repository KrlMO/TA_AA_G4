/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.model;

import java.util.List;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
/**
 *
 * @author USER
 */
public class CompraResultado {
    private List<Compra> listEmitidos;
    private List<Compra> listEntregados;

    public List<Compra> getListEmitidos() {
        return listEmitidos;
    }

    public void setListEmitidos(List<Compra> listEmitidos) {
        this.listEmitidos = listEmitidos;
    }

    public List<Compra> getListEntregados() {
        return listEntregados;
    }

    public void setListEntregados(List<Compra> listEntregados) {
        this.listEntregados = listEntregados;
    }
}
