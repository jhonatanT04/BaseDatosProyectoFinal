/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personas.Persona.Cliente;
import Modelo.Personas.Persona.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.AbstractList;
import javax.swing.JRootPane;

/**
 *
 * @author venot
 */
public class DAOCliente {
    private DAOPersona daoPersona= new DAOPersona();

    public boolean insertarCliente(Cliente cliente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String insertClienteSQL = "INSERT INTO super_clientes (cli_codigo, cli_visualizar, super_personas_per_codigo) VALUES (SEQ_SUPER_CLIENTES.NEXTVAL, ?, ?)";
        
        if (daoPersona.buscarPersonaCliente(cliente.getCedula()) == null) {
            try (PreparedStatement psCliente = conn.prepareStatement(insertClienteSQL)) {
                System.out.println("KKKKKKKKKKKKK");
                int cod =daoPersona.insertarPersona(cliente);
                System.out.println("AAAAAAAAAAAAA");
                psCliente.setString(1, String.valueOf(cliente.getVisualizacion()));
                psCliente.setString(2, String.valueOf(cod));
                psCliente.executeUpdate();
                return true;
            } finally {
                if (conn != null) {
                    conexion.desconectar();
                    conn.close();
                }
            }
        } else {
            return false;
        }
    }
      
    
    
    
    public Cliente buscarClientePorCedula(Persona per) throws SQLException {        
       
        String clienteSQL = "SELECT cli_codigo, cli_visualizar FROM super_clientes WHERE super_personas_per_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        try (PreparedStatement psCliente = conn.prepareStatement(clienteSQL)) {
            psCliente.setInt(1, per.getCodigo());
            ResultSet rsCliente = psCliente.executeQuery();
            if (rsCliente.next()) {
                int cliCodigo = rsCliente.getInt("cli_codigo");
                String visualizar = rsCliente.getString("cli_visualizar");
                System.out.println("Cliente encontrado:");
                System.out.println("Código de cliente: " + cliCodigo);
                System.out.println("Visualizar: " + visualizar);
                return new Cliente(cliCodigo, visualizar.charAt(0), per.getCodigo(), per.getCedula(), per.getNombre(), per.getApellido(), per.getDireccion(), per.getTelefono(), per.getCorreo());
                //return new Cliente(cliCodigo,visualizar.charAt(0) , per.getCodigo(), per.getCedula(),per.getNombre(),per.getApellido() ,per.getDireccion(),per.getTelefono(), per.getCorreo());
            }else {
                    //System.out.println("Cliente no encontrado para la cédula: " + cedula);
                   
                return null;
            }
        }
    }
    
     public boolean modificarCliente(Cliente cliente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String updateClienteSQL = "UPDATE super_clientes SET cli_visualizar = ? WHERE cli_codigo = ?";
        
            
        try (PreparedStatement psCliente = conn.prepareStatement(updateClienteSQL)) {
            daoPersona.modificarPersona(cliente);
            
            psCliente.setString(1, String.valueOf(cliente.getVisualizacion()));
            psCliente.setInt(2, cliente.getClienteCodigo());
            
            psCliente.executeUpdate();   
            return true;
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
     
     public List<Cliente> buscarPersonaPorNombre(List<Persona> personas) throws SQLException {        
         
        String clienteSQL = "SELECT cli_codigo, cli_visualizar FROM super_clientes WHERE super_personas_per_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        List<Cliente> listaClientes = new ArrayList<>();
        for (Persona per : personas) {
            try (PreparedStatement psCliente = conn.prepareStatement(clienteSQL)) {
                psCliente.setInt(1, per.getCodigo());
                ResultSet rsCliente = psCliente.executeQuery();
                if (rsCliente.next()) {
                    int cliCodigo = rsCliente.getInt("cli_codigo");
                    String visualizar = rsCliente.getString("cli_visualizar");
                    System.out.println("Cliente encontrado:");
                    System.out.println("Código de cliente: " + cliCodigo);
                    System.out.println("Visualizar: " + visualizar);
                    listaClientes.add(new Cliente(cliCodigo, visualizar.charAt(0), per.getCodigo(), per.getCedula(), per.getNombre(), per.getApellido(), per.getDireccion(), per.getTelefono(), per.getCorreo()));
                     
                //return new Cliente(cliCodigo,visualizar.charAt(0) , per.getCodigo(), per.getCedula(),per.getNombre(),per.getApellido() ,per.getDireccion(),per.getTelefono(), per.getCorreo());
                }
            }
        }
        return listaClientes;
        
    }
     
    public boolean eliminarCliente(int codigoCliente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
    
        String sql = "DELETE FROM super_clientes WHERE cli_codigo = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoCliente);
            int filasAfectadas = stmt.executeUpdate();
            
            return filasAfectadas > 0; // Devuelve true si se eliminó al menos una fila, false si no se eliminó ninguna
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    
}
