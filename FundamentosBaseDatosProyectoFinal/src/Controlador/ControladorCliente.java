/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.DAOCliente;
import Modelo.Personas.Persona.Cliente;
import Modelo.Personas.Persona.Persona;
import java.sql.SQLException;
import javax.swing.JRootPane;

/**
 *
 * @author venot
 */
public class ControladorCliente {
    private DAOCliente daoCliente;
    
    public ControladorCliente() {
        daoCliente= new DAOCliente();
    }
    
    
    public boolean crearCliente(Cliente cliente) throws SQLException{
        return daoCliente.insertarCliente(cliente);
    }
    public Cliente buscarCliente(Persona per) throws SQLException{
        return daoCliente.buscarClientePorCedula(per);
    }
    
    public boolean actualizarCliente(Cliente cliente) throws SQLException{
        return daoCliente.modificarCliente( cliente);
    }
    
}
