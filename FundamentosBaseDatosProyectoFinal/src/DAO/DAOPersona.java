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
    
    public void modificarPersona(Persona per) throws SQLException {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String sql = "UPDATE super_personas SET per_cedula = ?, per_nombre = ?, per_apellido = ?, per_direccion = ?, per_telefono = ?, per_correo_electronico = ? WHERE per_codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, per.getCedula());
            stmt.setString(2, per.getNombre());
            stmt.setString(3, per.getApellido());
            stmt.setString(4, per.getDireccion());
            stmt.setString(5, per.getTelefono());
            stmt.setString(6, per.getCorreo());
            stmt.setInt(7, per.getCodigo());
            int rowsUpdated = stmt.executeUpdate();
            //return rowsUpdated > 0;
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
    
    
    
    public List<Persona> buscarPersonaPorNombreClientes(String nombre) throws SQLException {
        List<Persona> personas = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String sql = "SELECT * FROM super_personas WHERE per_nombre LIKE ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Persona persona = new Cliente(0, 'a', 0, sql, nombre, sql, sql, sql, nombre);
                persona.setCodigo(rs.getInt("per_codigo"));
                persona.setCedula(rs.getString("per_cedula"));
                persona.setNombre(rs.getString("per_nombre"));
                persona.setApellido(rs.getString("per_apellido"));
                persona.setDireccion(rs.getString("per_direccion"));
                persona.setTelefono(rs.getString("per_telefono"));
                persona.setCorreo(rs.getString("per_correo_electronico"));
                personas.add(persona);
            }
        }
        
        return personas;
    }
    
    public List<Persona> listarClientes() {
        List<Persona> personas = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String sql = "SELECT * FROM super_personas";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Persona persona = new Cliente(0, ' ', 0, sql, sql, sql, sql, sql, sql);
                
                persona.setCodigo(rs.getInt("per_codigo"));
                persona.setCedula(rs.getString("per_cedula"));
                persona.setNombre(rs.getString("per_nombre"));
                persona.setApellido(rs.getString("per_apellido"));
                persona.setDireccion(rs.getString("per_direccion"));
                persona.setTelefono(rs.getString("per_telefono"));
                persona.setCorreo(rs.getString("per_correo_electronico"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personas;
    }
    
}

