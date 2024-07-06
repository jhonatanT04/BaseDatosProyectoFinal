/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Usuario
 */

import Modelo.Proveedor.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DAOProveedores {

    public boolean insertarProveedor(Proveedor proveedor) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_proveedores (prov_codigo, prov_nombre, prov_telefono, prov_direccion, prov_correo_electronico, prov_ruc) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, proveedor.getCodigo());
            pstmt.setString(2, proveedor.getNombre());
            pstmt.setString(3, proveedor.getTelefono());
            pstmt.setString(4, proveedor.getDireccion());
            pstmt.setString(5, proveedor.getCorreo());
            pstmt.setString(6, proveedor.getRuc());

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

    public void buscarProveedor(double codigo) {
        if (codigo > 0) {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conectar();

            String sql = "SELECT * FROM super_proveedores WHERE prov_codigo = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, codigo);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Proveedor encontrado:");
                    System.out.println("Código: " + rs.getDouble("prov_codigo"));
                    System.out.println("Nombre: " + rs.getString("prov_nombre"));
                    System.out.println("Teléfono: " + rs.getString("prov_telefono"));
                    System.out.println("Dirección: " + rs.getString("prov_direccion"));
                    System.out.println("Correo Electrónico: " + rs.getString("prov_correo_electronico"));
                    System.out.println("RUC: " + rs.getString("prov_ruc"));
                } else {
                    System.out.println("No se encontró ningún proveedor con el código " + codigo);
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            } finally {
                conexion.desconectar();
            }
        } else {
            System.out.println("El código del proveedor no puede ser menor o igual a 0.");
        }
    }

    public boolean actualizarProveedor(Proveedor proveedor) {
        boolean llave = false;
        try {
            System.out.println("Actualizando proveedor: " + proveedor);

            String SQLr = "UPDATE super_proveedores SET prov_nombre=?, prov_telefono=?, prov_direccion=?, prov_correo_electronico=?, prov_ruc=? WHERE prov_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setString(1, proveedor.getNombre());
            sentencia.setString(2, proveedor.getTelefono());
            sentencia.setString(3, proveedor.getDireccion());
            sentencia.setString(4, proveedor.getCorreo());
            sentencia.setString(5, proveedor.getRuc());
            sentencia.setDouble(6, proveedor.getCodigo());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se actualizó correctamente el proveedor");
                llave = true;
            } else {
                System.out.println("No se pudo actualizar el proveedor");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

    public boolean eliminarProveedor(double codigo) {
        boolean llave = false;
        try {
            String SQLr = "DELETE FROM super_proveedores WHERE prov_codigo=?";
            Conexion conexion = new Conexion();
            Connection con = conexion.conectar();
            PreparedStatement sentencia = con.prepareStatement(SQLr);

            sentencia.setDouble(1, codigo);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminó correctamente el proveedor con código " + codigo);
                llave = true;
            } else {
                System.out.println("No se pudo eliminar el proveedor con el código " + codigo + " (No existe en la base de datos)");
            }

            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return llave;
    }

}
