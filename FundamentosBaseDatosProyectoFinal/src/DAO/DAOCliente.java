/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personas.Persona.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author venot
 */
public class DAOCliente {
    
    public boolean insertarCliente(Cliente cliente) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String insertPersonaSQL = "INSERT INTO super_personas (per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico) VALUES (seq_super_personas.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        String insertClienteSQL = "INSERT INTO super_clientes (cli_codigo, cli_visualizar, super_personas_per_codigo) VALUES (seq_super_clientes.NEXTVAL, ?, seq_super_personas.CURRVAL)";

        try (PreparedStatement psPersona = conn.prepareStatement(insertPersonaSQL);
            PreparedStatement psCliente = conn.prepareStatement(insertClienteSQL)) {

            psPersona.setString(1, cliente.getCedula());
            psPersona.setString(2, cliente.getNombre());
            psPersona.setString(3, cliente.getApellido());
            psPersona.setString(4, cliente.getDireccion());
            psPersona.setString(5, cliente.getTelefono());
            psPersona.setString(6, cliente.getCorreo());
            psPersona.executeUpdate();

            psCliente.setString(1, String.valueOf(cliente.getVisualizacion()));
            psCliente.executeUpdate();
            return true;
        }
    }
    
    public Cliente buscarClientePorCedula( String cedula) throws SQLException {
        String personaSQL = "SELECT per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico FROM super_personas WHERE per_cedula = ?";
        String clienteSQL = "SELECT cli_codigo, cli_visualizar FROM super_clientes WHERE super_personas_per_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        try (PreparedStatement psPersona = conn.prepareStatement(personaSQL)) {
            psPersona.setString(1, cedula);
            ResultSet rsPersona = psPersona.executeQuery();

            if (rsPersona.next()) {
                int perCodigo = rsPersona.getInt("per_codigo");
                String nombre = rsPersona.getString("per_nombre");
                String apellido = rsPersona.getString("per_apellido");
                String direccion = rsPersona.getString("per_direccion");
                String telefono = rsPersona.getString("per_telefono");
                String correo = rsPersona.getString("per_correo_electronico");

                /*System.out.println("Persona encontrada:");
                System.out.println("Cédula: " + cedula);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Dirección: " + direccion);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Correo electrónico: " + correo);*/

                try (PreparedStatement psCliente = conn.prepareStatement(clienteSQL)) {
                    psCliente.setInt(1, perCodigo);
                    ResultSet rsCliente = psCliente.executeQuery();

                    if (rsCliente.next()) {
                        int cliCodigo = rsCliente.getInt("cli_codigo");
                        String visualizar = rsCliente.getString("cli_visualizar");
                        System.out.println("Cliente encontrado:");
                        System.out.println("Código de cliente: " + cliCodigo);
                        System.out.println("Visualizar: " + visualizar);
                        return new Cliente(cliCodigo,visualizar.charAt(0) , perCodigo, cedula, nombre, apellido, direccion, telefono, correo);
                        
                        
                    } else {
                        System.out.println("Cliente no encontrado para la cédula: " + cedula);
                        return null;
                    }
                }
            } else {
                System.out.println("Persona no encontrada para la cédula: " + cedula);
                return null;
            }
        }
    }
    
}
