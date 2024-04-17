
package pe.edu.pucp.campoysoft.rrhh.model;

import java.util.Date;

public class Cliente extends Persona{
    
    private String codCliente;
    private boolean activo;
    
    public Cliente(){}
    
    
    public Cliente( String codCliente,boolean activo, String nombre, String apPaterno, String apMaterno, int dni, Date fechaNac, String direccion) {
        super( nombre, apPaterno, apMaterno, dni, fechaNac, direccion);
        this.codCliente=codCliente;
        this.activo = activo;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "Cliente{" + "codCliente=" + codCliente + ", activo=" + activo + '}' + str;
    }

   
}
