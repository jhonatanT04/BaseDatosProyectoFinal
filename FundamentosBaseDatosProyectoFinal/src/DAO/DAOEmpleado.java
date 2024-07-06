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

    public boolean insertarEmpleado(Empleado empleado) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_empleados (emp_codigo, emp_visualizar, super_personas_per_codigo, emp_contrasenia, emp_permiso) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empleado.getCodigo());
            pstmt.setString(2, String.valueOf(empleado.getVisualizacion()));
            pstmt.setInt(3, empleado.getCodigo());
            pstmt.setString(4, empleado.getContrasenia());
            pstmt.setString(5, String.valueOf(empleado.getPermiso()));

            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    public void buscarEmpleado(int codigo) {
        if (codigo > 0) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_empleados WHERE emp_codigo = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, codigo);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Empleado encontrado:");
                    System.out.println("Código: " + rs.getInt("emp_codigo"));
                    System.out.println("Visualizar: " + rs.getString("emp_visualizar"));
                    System.out.println("Código Persona: " + rs.getInt("super_personas_per_codigo"));
                    System.out.println("Contraseña: " + rs.getString("emp_contrasenia"));
                    System.out.println("Permiso: " + rs.getString("emp_permiso"));
                } else {
                    System.out.println("No se encontró ningún empleado con el código " + codigo);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            System.out.println("El código del empleado no puede ser menor o igual a 0.");
        }
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        boolean llave = false;
        try {
            System.out.println("Actualizando empleado: " + empleado);

            String SQLr = "UPDATE super_empleados SET emp_visualizar=?, super_personas_per_codigo=?, emp_contrasenia=?, emp_permiso=? WHERE emp_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setString(1, String.valueOf(empleado.getVisualizacion()));
            sentencia.setInt(2, empleado.getCodigo());
            sentencia.setString(3, empleado.getContrasenia());
            sentencia.setString(4, String.valueOf(empleado.getPermiso()));
            sentencia.setInt(5, empleado.getCodigo());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se actualizó correctamente el empleado");
                llave = true;
            } else {
                System.out.println("No se pudo actualizar el empleado");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public boolean eliminarEmpleado(int codigo) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_empleados WHERE emp_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setInt(1, codigo);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminó correctamente el empleado con código " + codigo);
                llave = true;
            } else {
                System.out.println("No se pudo eliminar el empleado con el código " + codigo + " (No existe en la base de datos)");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

}
