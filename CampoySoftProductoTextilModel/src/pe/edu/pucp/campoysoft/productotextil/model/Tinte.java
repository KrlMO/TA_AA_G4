/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.model;

/**
 *
 * @author samt1
 */
public class Tinte {
    private int idTinte;
    private String codTinte;
    private String nombre;
    private int R;
    private int G;
    private int B;

    public Tinte(int idTinte, String codTinte, String nombre, int R, int G, int B) {
        this.idTinte = idTinte;
        this.codTinte = codTinte;
        this.nombre = nombre;
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public int getIdTinte() {
        return idTinte;
    }

    public void setIdTinte(int idTinte) {
        this.idTinte = idTinte;
    }

    public String getCodTinte() {
        return codTinte;
    }

    public void setCodTinte(String codTinte) {
        this.codTinte = codTinte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getR() {
        return R;
    }

    public void setR(int R) {
        this.R = R;
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }
    
    
}
