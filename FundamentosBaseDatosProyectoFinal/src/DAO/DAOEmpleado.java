/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personas.Persona.Empleado;
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

    public void insertarEmpleado(Empleado empleado) throws SQLException{
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        String insertPersonaSQL = "INSERT INTO super_personas (per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico) VALUES (seq_super_personas.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        String insertEmpleadoSQL = "INSERT INTO super_empleados (emp_codigo, emp_visualizar, super_personas_per_codigo,emp_contrasenia,emp_permiso) VALUES (seq_super_empleados.NEXTVAL, ?, seq_super_personas.CURRVAL,?, ?)";

        try (PreparedStatement psPersona = conn.prepareStatement(insertPersonaSQL);
            PreparedStatement psEmpleado = conn.prepareStatement(insertEmpleadoSQL)) {

            psPersona.setString(1, empleado.getCedula());
            psPersona.setString(2, empleado.getNombre());
            psPersona.setString(3, empleado.getApellido());
            psPersona.setString(4, empleado.getDireccion());
            psPersona.setString(5, empleado.getTelefono());
            psPersona.setString(6, empleado.getCorreo());
            psPersona.executeUpdate();

            psEmpleado.setString(1, String.valueOf(empleado.getVisualizacion()));
            psEmpleado.setString(2, empleado.getContrasenia());
            psEmpleado.setString(3, String.valueOf(empleado.getPermiso()));
            
            psEmpleado.executeUpdate();
        }
    }
     public Empleado buscarClientePorCedula( String cedula) throws SQLException {
        String personaSQL = "SELECT per_codigo, per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, per_correo_electronico FROM super_personas WHERE per_cedula = ?";
        String empleadoSQL = "SELECT emp_codigo, emp_visualizar,emp_contrasenia,emp_permiso FROM super_empleados WHERE super_personas_per_codigo = ?";
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

                System.out.println("Persona encontrada:");
                System.out.println("Cédula: " + cedula);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Dirección: " + direccion);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Correo electrónico: " + correo);

                try (PreparedStatement psEmpleado = conn.prepareStatement(empleadoSQL)) {
                    psEmpleado.setInt(1, perCodigo);
                    ResultSet rsEmpleado = psEmpleado.executeQuery();

                    if (rsEmpleado.next()) {
                        int empCodigo = rsEmpleado.getInt("emp_codigo");
                        String visualizar = rsEmpleado.getString("emp_visualizar");
                        String contrasenia = rsEmpleado.getString("emp_contrasenia");
                        String permiso = rsEmpleado.getString("emp_permiso");
                        //emp_contrasenia,emp_permiso
                        System.out.println("Empleado encontrado:");
                        System.out.println("Código de empleado: " + empCodigo);
                        System.out.println("Visualizar: " + visualizar);
                        System.out.println("Contrasenia: " + contrasenia);
                        System.out.println("Permiso: " + permiso);
                        return new Empleado(empCodigo, visualizar.charAt(0), contrasenia, permiso.charAt(0), perCodigo, cedula, nombre, apellido, direccion, telefono, correo);
                    } else {
                        System.out.println("Empleado no encontrado para la cédula: " + cedula);
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
