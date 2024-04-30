
package pe.edu.pucp.campoysoft.rrhh.model;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.campoysoft.productotextil.dao.ProductoRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.mysql.ProductoRolloMySQL;


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
    
    public ArrayList<ProductoRollo> listar_proStock_bajo(){
        ProductoRolloDAO prDAO = new ProductoRolloMySQL();
        ArrayList<ProductoRollo> productoRollos = new ArrayList<>();
        productoRollos=prDAO.listar();
        for(int i = productoRollos.size() - 1; i >= 0; i--){
            if(productoRollos.get(i).getStock() <= 5){
                productoRollos.remove(i);
            }
        }
        
        return productoRollos;
    }
    
    public ProductoRollo encontrar_producto(int id){
        
        ProductoRolloDAO prDAO = new ProductoRolloMySQL();
        ArrayList<ProductoRollo> productoRollos = new ArrayList<>();
        ProductoRollo producto= new ProductoRollo();
        productoRollos=prDAO.listar();
        for(int i = productoRollos.size() - 1; i >= 0; i--){
            if(productoRollos.get(i).getIdProducto()== id){
                producto=productoRollos.get(i);
                break;
            }
        }
        
        return producto;
    }
    public void reponer_stock(int id_producto,int cantidad){
        ProductoRollo producto = this.encontrar_producto(id_producto);
        producto.setStock(producto.getStock()+cantidad);
        ProductoRolloDAO prDAO = new ProductoRolloMySQL();
        prDAO.modificar(producto);
    }
    
    public void despachar_Atencion(int id_atencion){
        
    }
    
}
