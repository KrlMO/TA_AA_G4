/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.model;

import java.util.List;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
/**
 *
 * @author USER
 */
public class ServicioTInteResultado {
    private List<ServicioTinte> listEmitidos;
    private List<ServicioTinte> listEntregados;

    // Getters and setters
    public List<ServicioTinte> getListEmitidos() {
        return listEmitidos;
    }

    public void setListEmitidos(List<ServicioTinte> listEmitidos) {
        this.listEmitidos = listEmitidos;
    }

    public List<ServicioTinte> getListEntregados() {
        return listEntregados;
    }

    public void setListEntregados(List<ServicioTinte> listEntregados) {
        this.listEntregados = listEntregados;
    }
}
