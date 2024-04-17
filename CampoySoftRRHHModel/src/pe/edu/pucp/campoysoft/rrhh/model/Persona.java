
package pe.edu.pucp.campoysoft.rrhh.model;

import java.util.Date;


public abstract class Persona {
    private int idPersona;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private int dni;
    private Date fechaNac;
    private String direccion;
    
    public Persona(){}
    
    public Persona( String nombre, String apPaterno, String apMaterno, int dni, Date fechaNac, String direccion) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
    }
    

    
    
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + ", dni=" + dni + ", fechaNac=" + fechaNac + ", direccion=" + direccion + '}';
    }

    
    
}
