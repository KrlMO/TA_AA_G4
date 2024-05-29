
package pe.edu.pucp.campoysoft.rrhh.model;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.campoysoft.productotextil.dao.ProductoRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.mysql.ProductoRolloMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.dao.ServicioTinteDAO;
import pe.edu.pucp.campoysoft.onlinemarket.dao.CompraDAO;
import pe.edu.pucp.campoysoft.onlinemarket.model.Atencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.Compra;
import pe.edu.pucp.campoysoft.onlinemarket.model.EstadoAtencion;
import pe.edu.pucp.campoysoft.onlinemarket.model.ServicioTinte;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.CompraMySQL;
import pe.edu.pucp.campoysoft.onlinemarket.mysql.ServicioTinteMySQL;

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
            if(productoRollos.get(i).getStock() >= 100){
                productoRollos.remove(i);
            }
        }
        
        return productoRollos;
    }
    
    public ArrayList<Atencion> listar_Atencion_Emitidas(){
        CompraDAO compraDAO = new CompraMySQL();
        ArrayList<Atencion> atenciones = new ArrayList<>();
        ArrayList<Compra> compras = new ArrayList<>();
        Compra compra;
        compras = compraDAO.listarTodas();
        for(int i = compras.size() - 1; i >= 0; i--){
            if(compras.get(i).getEstadoServicio()== EstadoAtencion.Emitido){
                compra = compras.get(i);
                atenciones.add(compra);
                
            }
        }
        ServicioTinteDAO servicioTinteDAO = new ServicioTinteMySQL();
        ArrayList<ServicioTinte> servicioTintes = new ArrayList<>();
        ServicioTinte servicioTinte;
        servicioTintes = servicioTinteDAO.listarTodas();
        for(int i = servicioTintes.size() - 1; i >= 0; i--){
            if(servicioTintes.get(i).getEstadoServicio()== EstadoAtencion.Emitido){
                servicioTinte = servicioTintes.get(i);
                atenciones.add(servicioTinte);
                
            }
        }
        return atenciones;
        
    }
    
    public ArrayList<Atencion> listar_Atencion_Entregadas(){
        CompraDAO compraDAO = new CompraMySQL();
        ArrayList<Atencion> atenciones = new ArrayList<>();
        ArrayList<Compra> compras = new ArrayList<>();
        Compra compra;
        compras = compraDAO.listarTodas();
        for(int i = compras.size() - 1; i >= 0; i--){
            if(compras.get(i).getEstadoServicio()== EstadoAtencion.Entregado){
                compra = compras.get(i);
                atenciones.add(compra);
                
            }
        }
        ServicioTinteDAO servicioTinteDAO = new ServicioTinteMySQL();
        ArrayList<ServicioTinte> servicioTintes = new ArrayList<>();
        ServicioTinte servicioTinte;
        servicioTintes = servicioTinteDAO.listarTodas();
        for(int i = servicioTintes.size() - 1; i >= 0; i--){
            if(servicioTintes.get(i).getEstadoServicio()== EstadoAtencion.Entregado){
                servicioTinte = servicioTintes.get(i);
                atenciones.add(servicioTinte);
                
            }
        }
        return atenciones;
        
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
    
   
    
    public void despachar_Atencion(int id){
        CompraDAO compraDAO = new CompraMySQL();
        ArrayList<Compra> compras = new ArrayList<>();
        Compra compra;
        compras = compraDAO.listarTodas();
        for(int i = compras.size() - 1; i >= 0; i--){
            if(compras.get(i).getIdAtencion()== id){
                compra=compras.get(i);
                compra.setEstadoServicio(EstadoAtencion.Entregado);
                compra.setIdEmpleado(this.getIdPersona());
                compraDAO.modificar(compra);
                return;
            }
        }
        ServicioTinteDAO servicioTinteDAO = new ServicioTinteMySQL();
        ArrayList<ServicioTinte> ServicioTintes = new ArrayList<>();
        ServicioTinte servicioTinte;
        ServicioTintes = servicioTinteDAO.listarTodas();
        for(int i = ServicioTintes.size() - 1; i >= 0; i--){
            if(ServicioTintes.get(i).getIdAtencion()== id){
                servicioTinte=ServicioTintes.get(i);;
                servicioTinte.setEstadoServicio(EstadoAtencion.Entregado);
                servicioTinte.setIdEmpleado(this.getIdPersona());
                servicioTinteDAO.modificar(servicioTinte);
            }
        }
        
    }


}
