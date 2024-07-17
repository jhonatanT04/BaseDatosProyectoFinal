/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.DAOEmpleado;
import Modelo.Personas.Persona.Empleado;
import Modelo.Personas.Persona.Persona;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author venot
 */
public class ControladorEmpleado {
    private DAOEmpleado daoEmpleado;

    public ControladorEmpleado() {
        daoEmpleado = new DAOEmpleado();
    }
    
    public boolean crearCliente(Empleado emp) throws SQLException {
        return daoEmpleado.insertarEmpleado(emp);
    }
    public Empleado buscarEmpleado(Persona per) throws SQLException {
        return daoEmpleado.buscarEmpleadoPorCedula(per);
    }
    
    public boolean actualizarEmpleado(Empleado emp) throws SQLException{
        return daoEmpleado.modificarCliente(emp);
    }
    public List<Empleado> ListarEmpleados(List<Persona> persns) throws SQLException{
        return daoEmpleado.ListarEmpleados(persns);
    }
    
}
