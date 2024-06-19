
package pe.edu.pucp.campoysoft.rrhh.model;


import java.util.Date;

public class Administrador extends Persona {

    
    private String codAdmin;
    private boolean activo;
    
    public Administrador(){};
    
    public Administrador( String codAdmin,boolean activo, String nombre, String apPaterno, String apMaterno, int dni, Date fechaNac, String direccion) {
        super( nombre, apPaterno, apMaterno, dni, fechaNac, direccion);
        this.codAdmin=codAdmin;
        this.activo = activo;
    }

    public String getCodAdmin() {
        return codAdmin;
    }

    public void setCodAdmin(String codAdmin) {
        this.codAdmin = codAdmin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}