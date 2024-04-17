/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.program.main;

import java.util.ArrayList;
import java.util.Date;

import pe.edu.pucp.campoysoft.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.campoysoft.rrhh.mysql.EmpleadoMySQL;
import pe.edu.pucp.campoysoft.rrhh.model.Empleado;

public class Principal {
    public static void main(String[] args){
        Empleado emp = new Empleado("EMP007" ,200, "Supervisor", true, "Sebastian", "Toledo", "Martinez", 72379187, new Date(), "Miraflores");
        EmpleadoDAO daoEmpleado = new EmpleadoMySQL();
        
        if(daoEmpleado.insertar(emp)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }

//        if(daoEmpleado.eliminar(2)==1){
//            System.out.println("Se ha registrado con exito");
//        }else{
//            System.out.println("Ha ocurrido un error");
//        }
//
//        emp.setApPaterno("Calderon");
//        emp.setIdPersona(2);
//        if(daoEmpleado.modificar(emp)==1){
//            System.out.println("Se ha registrado con exito");
//        }else{  
//            System.out.println("Ha ocurrido un error");
//        }

        ArrayList<Empleado> empleados = new ArrayList<>();
        //empleados = emp.listar();
        
        for(int i=0;i<empleados.size();i++){
            String str= empleados.get(i).toString();
            System.out.println(str);
        }
            
    }
}
