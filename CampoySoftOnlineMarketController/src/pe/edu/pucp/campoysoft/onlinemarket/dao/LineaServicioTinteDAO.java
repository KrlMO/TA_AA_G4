/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.campoysoft.onlinemarket.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.onlinemarket.model.LineaServicioTinte;


public interface LineaServicioTinteDAO {
    int insertar(LineaServicioTinte lineaServTinte);
    int modificar(LineaServicioTinte lineaServTinte);
    int elimniar(int idLinSerTinte);
    ArrayList<LineaServicioTinte> listarTodas();
}
