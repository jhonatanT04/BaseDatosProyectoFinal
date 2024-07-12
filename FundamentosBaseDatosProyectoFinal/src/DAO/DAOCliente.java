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
        String insertClienteSQL = "INSERT INTO super_clientes (cli_codigo, cli_visualizar, super_personas_per_codigo) VALUES (seq_super_clientes.NEXTVAL, ?, ?)";
        
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
                    conn.close();
                }
            }
        } else {
            return false;
        }
    }
      
    
    
    
    public Cliente buscarClientePorCedula(Persona per) throws SQLException {        
        //daoPersona = new DAOPersona();
        //Persona per = daoPersona.buscarPersonaCliente(per.getCedula());
        //System.out.println(per);
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
        
        /*if (per == null) {
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
                    return new Cliente(cliCodigo,visualizar.charAt(0) , per.getCodigo(), per.getCedula(),per.getNombre(),per.getApellido() ,per.getDireccion(),per.getTelefono(), per.getCorreo());
                } else {
                    //System.out.println("Cliente no encontrado para la cédula: " + cedula);
                    //JOptionPane.showInternalMessageDialog(rootPane, "La persona con cedula  "+per.getCedula()+" no existe en clientes.");
                    return null;
                }
            }
        }else{
            JOptionPane.showInternalMessageDialog(rootPane, "La persona con cedula  "+cedula+" no existe en la base.");
            return null;
        }*/
        
        
    }
    
    
    
}
