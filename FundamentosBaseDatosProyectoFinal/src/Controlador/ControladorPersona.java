/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.DAOPersona;
import Modelo.Personas.Persona.Persona;
import java.sql.SQLException;


/**
 *
 * @author venot
 */
public class ControladorPersona {
   private DAOPersona daopersona;

    public ControladorPersona() {
        daopersona = new DAOPersona();
    }
   
   
   public void nuevaPersona(Persona per) throws SQLException{
       daopersona.insertarPersona(per);
   }
   public Persona buscarPersonaCliente(String Cedula) throws SQLException{
       //System.out.println(daopersona.buscarPersonaCliente(Cedula));
       return daopersona.buscarPersonaCliente(Cedula);
   }
   public Persona buscarPersonaEmpleado(String Cedula) throws SQLException{
       return daopersona.buscarPersonaEmpleado(Cedula);
   }
   
}
