/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.campoysoft.productotextil.dao;

import java.util.ArrayList;
import pe.edu.pucp.campoysoft.productotextil.model.EspecificacionRollo;

/**
 *
 * @author samt1
 */
public interface EspecificacionRolloDAO {
    public ArrayList<EspecificacionRollo> listar();
    public int insertar(EspecificacionRollo especificacionRollo);
    public int modificar(EspecificacionRollo especificacionRollo);
    public int eliminar (int idEspecificacionRollo);
    public ArrayList<String> listarTipoTelas();
    public ArrayList<String> listarTipoRollo();
}
//comentario rando