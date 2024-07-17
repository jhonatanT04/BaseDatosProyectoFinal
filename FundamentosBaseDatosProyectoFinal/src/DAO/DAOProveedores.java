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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOProveedores {

    public boolean insertarProveedor(Proveedor proveedor) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "INSERT INTO super_proveedores (prov_codigo, prov_nombre, prov_telefono, prov_direccion, prov_correo_electronico, prov_ruc) VALUES (seq_prov_codigo.NEXTVAL , ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(0, proveedor.getCodigo()); 
            pstmt.setString(2, proveedor.getNombre());
            pstmt.setString(3, proveedor.getTelefono());
            pstmt.setString(4, proveedor.getDireccion());
            pstmt.setString(5, proveedor.getCorreo());
            pstmt.setString(6, proveedor.getRuc());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA CONEXIÓN: " + e.getMessage());
            }
        }
    }

    public Proveedor buscarProveedorPorNombre(String nombre) {
        String sql = "SELECT prov_codigo, prov_ruc, prov_nombre, prov_telefono, prov_direccion, prov_correo_electronico FROM super_proveedores WHERE prov_nombre = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int codigo = rs.getInt("prov_codigo");
                String ruc = rs.getString("prov_ruc");
                String nombreProveedor = rs.getString("prov_nombre");
                String telefono = rs.getString("prov_telefono");
                String direccion = rs.getString("prov_direccion");
                String correo = rs.getString("prov_correo_electronico");

                System.out.println("Proveedor encontrado:");
                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + nombreProveedor);
                System.out.println("RUC: " + ruc);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Dirección: " + direccion);
                System.out.println("Correo Electrónico: " + correo);

                return new Proveedor(codigo, ruc, nombreProveedor, telefono, direccion, correo);
            } else {
                System.out.println("No se encontró ningún proveedor con el nombre " + nombre);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public Proveedor buscarProveedorPorCodigo(int codigo) {
        String sql = "SELECT prov_codigo, prov_ruc, prov_nombre, prov_telefono, prov_direccion, prov_correo_electronico FROM super_proveedores WHERE prov_codigo = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int codigoProveedor = rs.getInt("prov_codigo");
                String ruc = rs.getString("prov_ruc");
                String nombreProveedor = rs.getString("prov_nombre");
                String telefono = rs.getString("prov_telefono");
                String direccion = rs.getString("prov_direccion");
                String correo = rs.getString("prov_correo_electronico");

                System.out.println("Proveedor encontrado:");
                System.out.println("Código: " + codigoProveedor);
                System.out.println("Nombre: " + nombreProveedor);
                System.out.println("RUC: " + ruc);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Dirección: " + direccion);
                System.out.println("Correo Electrónico: " + correo);

                return new Proveedor(codigoProveedor, ruc, nombreProveedor, telefono, direccion, correo);
            } else {
                System.out.println("No se encontró ningún proveedor con el código " + codigo);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public Proveedor buscarProveedorPorRUC(String ruc) {
        String sql = "SELECT prov_codigo, prov_ruc, prov_nombre, prov_telefono, prov_direccion, prov_correo_electronico FROM super_proveedores WHERE prov_ruc = ?";
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ruc);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int codigo = rs.getInt("prov_codigo");
                String nombre = rs.getString("prov_nombre");
                String nombreProveedor = rs.getString("prov_nombre");
                String telefono = rs.getString("prov_telefono");
                String direccion = rs.getString("prov_direccion");
                String correo = rs.getString("prov_correo_electronico");

                System.out.println("Proveedor encontrado:");
                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + nombreProveedor);
                System.out.println("RUC: " + ruc);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Dirección: " + direccion);
                System.out.println("Correo Electrónico: " + correo);

                return new Proveedor(codigo, ruc, nombreProveedor, telefono, direccion, correo);
            } else {
                System.out.println("No se encontró ningún proveedor con el RUC " + ruc);
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return null;
        } finally {
            conexion.desconectar();
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

    public List<Proveedor> listarProveedores() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        String sql = "SELECT * FROM super_proveedores";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("prov_codigo");
                String nombre = rs.getString("prov_nombre");
                String telefono = rs.getString("prov_telefono");
                String direccion = rs.getString("prov_direccion");
                String correo = rs.getString("prov_correo_electronico");
                String ruc = rs.getString("prov_ruc");

                Proveedor proveedor = new Proveedor(codigo, nombre, telefono, direccion, correo, ruc);
                listaProveedores.add(proveedor);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }

        return listaProveedores;
    }

}
