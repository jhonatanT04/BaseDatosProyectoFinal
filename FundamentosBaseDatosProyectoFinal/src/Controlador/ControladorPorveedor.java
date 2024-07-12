/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.DAOProveedores;
import Modelo.Proveedor.Proveedor;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ControladorPorveedor {
    private DAOProveedores daoProveedores;

    public ControladorPorveedor() {
        this.daoProveedores = new DAOProveedores();
    }

    public boolean agregarProveedor(int codigo, String nombre, String telefono, String direccion, String correo, String ruc) {
        Proveedor proveedor = new Proveedor(codigo, nombre, telefono, direccion, correo, ruc);
        return daoProveedores.insertarProveedor(proveedor);
    }

    public Proveedor buscarProveedorNombre(String nombre) {
        return daoProveedores.buscarProveedorPorNombre(nombre);
    }

    public Proveedor buscarProveedorRUC(String ruc){
        return daoProveedores.buscarProveedorPorRUC(ruc); 
    }
    
    public boolean actualizarProveedor(int codigo, String nombre, String telefono, String direccion, String correo, String ruc) {
        Proveedor proveedor = new Proveedor(codigo, nombre, telefono, direccion, correo, ruc);
        return daoProveedores.actualizarProveedor(proveedor);
    }

    public boolean eliminarProveedor(int codigo) {
        return daoProveedores.eliminarProveedor(codigo);
    }

    public List<Proveedor> listarProveedores() {
        return daoProveedores.listarProveedores();
    }
    
}
