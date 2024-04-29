/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.mysql;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import pe.edu.pucp.campoysoft.config.DBManager;
import pe.edu.pucp.campoysoft.productotextil.dao.ProductoRolloDAO;
import pe.edu.pucp.campoysoft.productotextil.model.EspecificacionRollo;
import pe.edu.pucp.campoysoft.productotextil.model.ProductoRollo;
import pe.edu.pucp.campoysoft.productotextil.model.Tinte;

public class ProductoRolloMySQL implements ProductoRolloDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private String sql;
    
    @Override
    public int insertar(ProductoRollo productoRollo){
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call InsertProductoRollo(?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_producto", java.sql.Types.INTEGER); // registra como parametro de salida
            cs.setInt("p_fk_id_especificacion_rollo", productoRollo.getEspecificiacionRollo().getIdEspecifiacionRollo());
            cs.setInt("p_fk_id_tinte", productoRollo.getTinte().getIdTinte());
            cs.setDouble("p_precio_x_metro2", productoRollo.getPrecioXmetro2());
            cs.setInt("p_stock", productoRollo.getStock());
            cs.setString("p_descripcion", productoRollo.getDescripcion());
            cs.executeUpdate();
            productoRollo.setIdProducto(cs.getInt("_id_producto")); // seteas el id del empleado que se acaba de recibir
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(ProductoRollo productoRollo) {
        int resultado = 0;
        try{
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idProducto) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call DeleteProductoRolloById(?)}");
            cs.setInt("producto_id", idProducto);
            cs.executeUpdate();
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<ProductoRollo> listar() {
        ArrayList<ProductoRollo> productoRollos = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListProductoRollos()}");
            rs = cs.executeQuery();
            while(rs.next()){
                ProductoRollo productoRollo = new ProductoRollo();
                productoRollo.setIdProducto(rs.getInt("id_producto"));
                EspecificacionRollo especificacionRollo = new EspecificacionRollo();
                especificacionRollo.setIdEspecifiacionRollo(rs.getInt("fk_id_especificacion_rollo"));
                productoRollo.setEspecificiacionRollo(especificacionRollo);
                Tinte tinte = new Tinte();
                tinte.setIdTinte(rs.getInt("fk_id_tinte"));
                productoRollo.setPrecioXmetro2(rs.getDouble("precio_x_metro2"));
                productoRollo.setStock(rs.getInt("stock"));
                productoRollo.setDescripcion("descripcion");
                productoRollo.setActivo(true);
                productoRollos.add(productoRollo);//añado un empleado al arraylist empleados
            }
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return productoRollos;
    }
}
