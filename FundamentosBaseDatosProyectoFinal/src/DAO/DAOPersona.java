/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Personas.Persona.Cliente;
import Modelo.Personas.Persona.Empleado;
import Modelo.Personas.Persona.Persona;
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
public class DAOPersona {
    public int insertarPersona(Persona per) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String sql = "INSERT INTO super_personas (per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico) " +
                     "VALUES (super_personas_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"per_codigo"})) {
            stmt.setString(1, per.getCedula());
            stmt.setString(2, per.getNombre());
            stmt.setString(3, per.getApellido());
            stmt.setString(4, per.getDireccion());
            stmt.setString(5, per.getTelefono());
            stmt.setString(6, per.getCorreo());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el per_codigo generado.");
            }
            
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Persona buscarPersonaEmpleado(String cedula) throws SQLException{
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String sql = "SELECT per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico FROM super_personas WHERE per_cedula = ?";
        try (PreparedStatement psPersona = conn.prepareStatement(sql)) {
            psPersona.setString(1, cedula);
            ResultSet rsPersona = psPersona.executeQuery();
            if (rsPersona.next()) {
                int perCodigo = rsPersona.getInt("per_codigo");
                String nombre = rsPersona.getString("per_nombre");
                String apellido = rsPersona.getString("per_apellido");
                String direccion = rsPersona.getString("per_direccion");
                String telefono = rsPersona.getString("per_telefono");
                String correo = rsPersona.getString("per_correo_electronico");

                return new Empleado(0, 'a',"  ", 'a',perCodigo, cedula, nombre, apellido, direccion, telefono, correo);
                
            } else {
                System.out.println("Persona no encontrada para la cédula: " + cedula);
                return null;
            }
        }
    }
    public Persona buscarPersonaCliente(String cedula) throws SQLException{
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String sql = "SELECT per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico FROM super_personas WHERE per_cedula = ?";
        try (PreparedStatement psPersona = conn.prepareStatement(sql)) {
            psPersona.setString(1, cedula);
            ResultSet rsPersona = psPersona.executeQuery();
            if (rsPersona.next()) {
                int perCodigo = rsPersona.getInt("per_codigo");
                String nombre = rsPersona.getString("per_nombre");
                String apellido = rsPersona.getString("per_apellido");
                String direccion = rsPersona.getString("per_direccion");
                String telefono = rsPersona.getString("per_telefono");
                String correo = rsPersona.getString("per_correo_electronico");

                return new Cliente(0, 'a', perCodigo, cedula, nombre, apellido, direccion, telefono, correo);
                
            } else {
                System.out.println("Persona no encontrada para la cédula: " + cedula);
                System.out.println("Persona no encontrada para la cédula: " + cedula);
                return null;
            }
        }
    }
    
    
}
