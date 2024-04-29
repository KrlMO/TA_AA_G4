
package pe.edu.pucp.campoysoft.rrhh.model;

import java.util.ArrayList;
import java.util.Date; 
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;
public class Cliente extends Persona{
    
    private String codCliente;
    private boolean activo;
    private ArrayList<Atencion> atenciones;
    
    public Cliente(){}
    
    
    public Cliente( String codCliente,boolean activo, String nombre, String apPaterno, String apMaterno, int dni, Date fechaNac, String direccion) {
        super( nombre, apPaterno, apMaterno, dni, fechaNac, direccion);
        this.codCliente=codCliente;
        this.activo = activo;
        this.atenciones = new ArrayList<Atencion>();
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

    public ArrayList<Atencion> getAtenciones() {
        return atenciones;
    }

    public void setAtenciones(ArrayList<Atencion> atenciones) {
        this.atenciones = atenciones;
    }
    
    @Override
    public String toString() {
        String str = super.toString();
        return "Cliente{" + "codCliente=" + codCliente + ", activo=" + activo + '}' + str;
    }
    

    public void ListarProductos(){
        
    }
   
}
