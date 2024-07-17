/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personas.Persona.Empleado;
import Modelo.Personas.Persona.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class DAOEmpleado {
    private DAOPersona daoPersona= new DAOPersona();
    
    
    public boolean insertarEmpleado(Empleado empleado) throws SQLException{
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String insertClienteSQL = "INSERT INTO super_empleados (emp_codigo, emp_visualizar, super_personas_per_codigo, emp_contrasenia, emp_permiso) VALUES (seq_super_empleados.nextval, ?, ?,?,?)";
        
        if (daoPersona.buscarPersonaEmpleado(empleado.getCedula()) == null) {
            try (PreparedStatement psEmpleado = conn.prepareStatement(insertClienteSQL)) {
                //System.out.println("KKKKKKKKKKKKK");
                int cod =daoPersona.insertarPersona(empleado);
                //System.out.println("AAAAAAAAAAAAA");
                psEmpleado.setString(1, String.valueOf(empleado.getVisualizacion()));
                psEmpleado.setString(2, String.valueOf(cod));
                psEmpleado.setString(3, empleado.getContrasenia());
                psEmpleado.setString(4, String.valueOf(empleado.getPermiso()));
                psEmpleado.executeUpdate();
                return true;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } else {
            return false;
        }
    }
    public Empleado buscarEmpleadoPorCedula(Persona per) throws SQLException {
        String empleadoSQL = "SELECT emp_codigo, emp_visualizar, super_personas_per_codigo, emp_contrasenia, emp_permiso FROM super_empleados WHERE super_personas_per_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        try (PreparedStatement psCliente = conn.prepareStatement(empleadoSQL)) {
            psCliente.setInt(1, per.getCodigo());
            ResultSet rsCliente = psCliente.executeQuery();
            if (rsCliente.next()) {
                int empCodigo = rsCliente.getInt("emp_codigo");
                //String codigoVisu = rsCliente.getString("emp_visualizar");
                String visualizar = rsCliente.getString("emp_visualizar");
                String empContra = rsCliente.getString("emp_contrasenia");
                String empPermiso = rsCliente.getString("emp_permiso");
               
                
                return new Empleado(empCodigo, visualizar.charAt(0),  empContra, empPermiso.charAt(0),  per.getCodigo(), per.getCedula(), per.getNombre(), per.getApellido(), per.getDireccion(), per.getTelefono(), per.getCorreo());
                //return new Cliente(cliCodigo, visualizar.charAt(0), per.getCodigo(), per.getCedula(), per.getNombre(), per.getApellido(), per.getDireccion(), per.getTelefono(), per.getCorreo());
                //return new Cliente(cliCodigo,visualizar.charAt(0) , per.getCodigo(), per.getCedula(),per.getNombre(),per.getApellido() ,per.getDireccion(),per.getTelefono(), per.getCorreo());
            }else {
                    //System.out.println("Cliente no encontrado para la cédula: " + cedula);
                   
                return null;
            }
        }
    }
    public List<Empleado> ListarEmpleados(List<Persona> personas) throws SQLException {        
         
        String clienteSQL = "SELECT emp_codigo, emp_visualizar, super_personas_per_codigo, emp_contrasenia, emp_permiso FROM super_empleados WHERE super_personas_per_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        List<Empleado> listaClientes = new ArrayList<>();
        for (Persona per : personas) {
            try (PreparedStatement psCliente = conn.prepareStatement(clienteSQL)) {
                psCliente.setInt(1, per.getCodigo());
                ResultSet rsCliente = psCliente.executeQuery();
                if (rsCliente.next()) {
                    int empCodigo = rsCliente.getInt("emp_codigo");
                    String visualizar = rsCliente.getString("emp_visualizar");
                    String contrasenia = rsCliente.getString("emp_contrasenia");
                    String permiso = rsCliente.getString("emp_permiso");
                    System.out.println("Empleado encontrado:");
                    System.out.println("Código de cliente: " + empCodigo);
                    System.out.println("Visualizar: " + visualizar);
                    listaClientes.add(new Empleado(empCodigo, visualizar.charAt(0),  contrasenia, permiso.charAt(0),  per.getCodigo(), per.getCedula(), per.getNombre(), per.getApellido(), per.getDireccion(), per.getTelefono(), per.getCorreo()));
                     
                
                }
            }
        }
        return listaClientes;
        
    }
    public boolean modificarCliente(Empleado emp) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String updateClienteSQL = "UPDATE super_empleados SET emp_visualizar = ?, emp_contrasenia = ?, emp_permiso = ? WHERE emp_codigo = ?";
        
        try (PreparedStatement psCliente = conn.prepareStatement(updateClienteSQL)) {
            daoPersona.modificarPersona(emp);
            
            psCliente.setString(1, String.valueOf(emp.getVisualizacion()));
            psCliente.setString(2, emp.getContrasenia());
            psCliente.setString(3, String.valueOf(emp.getPermiso()));
            psCliente.setInt(4, emp.getEmpleadoCodigo());
            
            psCliente.executeUpdate();   
            return true;
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
