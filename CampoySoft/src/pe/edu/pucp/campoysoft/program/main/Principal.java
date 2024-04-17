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
import pe.edu.pucp.campoysoft.rrhh.dao.ClienteDAO;
import pe.edu.pucp.campoysoft.rrhh.mysql.ClienteMySQL;
import pe.edu.pucp.campoysoft.rrhh.model.Cliente;

import pe.edu.pucp.campoysoft.rrhh.model.Administrador;
import pe.edu.pucp.campoysoft.rrhh.mysql.AdministradorMySQL;
import pe.edu.pucp.campoysoft.rrhh.dao.AdministradorDAO;
public class Principal {
    public static void main(String[] args){
        Empleado emp = new Empleado("EMP001" ,200, "Supervisor", true, "Sebastian", "Toledo", "Martinez", 72379187, new Date(), "Miraflores");
        EmpleadoDAO daoEmpleado = new EmpleadoMySQL();
        
        if(daoEmpleado.insertar(emp)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        
        emp = new Empleado("EMP002" ,200, "reponedor", true, "jesus", "cacerez", "Martinez", 72379187, new Date(), "Miraflores");
        if(daoEmpleado.insertar(emp)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        
        if(daoEmpleado.eliminar(1)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }

        emp.setCargo("asistente");
        emp.setIdPersona(2);
        if(daoEmpleado.modificar(emp)==1){
            System.out.println("Se ha registrado con exito");
        }else{  
            System.out.println("Ha ocurrido un error");
        }

        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados = daoEmpleado.listar();
        
        for(int i=0;i<empleados.size();i++){
            String str= empleados.get(i).toString();
            System.out.println(str);
        }

        Cliente cli = new Cliente( "CLI001",true, "Marello", "Aguilar", "quispe",17302833,new Date(),"La victoria");
        ClienteDAO daoCliente = new ClienteMySQL();
        
        if(daoCliente.insertar(cli)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        cli = new Cliente( "CLI002",true, "Pepito", "Enrique", "toledo",17302833,new Date(),"La victoria");
        if(daoCliente.insertar(cli)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        if(daoCliente.eliminar(3)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        cli.setApPaterno("Diaz");
        cli.setIdPersona(4);
        if(daoCliente.modificar(cli)==1){
            System.out.println("Se ha registrado con exito");
        }else{  
            System.out.println("Ha ocurrido un error");
        }
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes = daoCliente.listar();
        
        for(int i=0;i<clientes.size();i++){
            String str= clientes.get(i).toString();
            System.out.println(str);
        }
        

        
        Administrador adm = new Administrador( "ADM001",true, "Alvaro", "ventura", "santos",17302833,new Date(),"SJM");
        AdministradorDAO daoAdministrador = new AdministradorMySQL();
        if(daoAdministrador.insertar(adm)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        adm = new Administrador( "ADM002",true, "marlene", "quispe", "huaman",17302833,new Date(),"SJM");
        if(daoAdministrador.insertar(adm)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        
        if(daoAdministrador.eliminar(5)==1){
            System.out.println("Se ha registrado con exito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        adm.setActivo(true);
        adm.setIdPersona(5);
        if(daoAdministrador.modificar(adm)==1){
            System.out.println("Se ha registrado con exito");
        }else{  
            System.out.println("Ha ocurrido un error");
        }
        ArrayList<Administrador> administradores = new ArrayList<>();
        administradores = daoAdministrador.listar();
        
        for(int i=0;i<administradores.size();i++){
            String str= administradores.get(i).toString();
            System.out.println(str);
        }
    }
}
