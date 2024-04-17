
package pe.edu.pucp.campoysoft.rrhh.model;

import java.util.ArrayList;
import java.util.Date;


public class Empleado extends Persona{
    private String codEmpleado;
    private double salario;
    private String cargo;
    private boolean activo;
    
    public Empleado(){}
    
    public Empleado(String codEmpleado,double salario, String cargo, boolean activo, String nombre, String apPaterno, String apMaterno, int dni, Date fechaNac, String direccion) {
        super(nombre, apPaterno, apMaterno, dni, fechaNac, direccion);
        this.codEmpleado=codEmpleado;
        this.salario = salario;
        this.cargo = cargo;
        this.activo = activo;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }
    

    

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
        return "Empleado{" + "codEmpleado=" + codEmpleado + ", salario=" + salario + ", cargo=" + cargo + ", activo=" + activo + '}'+ str;
    }
    
    
    
}
