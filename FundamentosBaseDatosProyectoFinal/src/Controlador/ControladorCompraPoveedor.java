/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.DAOCompraProveedor;
import Modelo.Proveedor.CompraProveedor;
import java.security.Timestamp;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ControladorCompraPoveedor {

    private DAOCompraProveedor daoCompraProveedor;

    public ControladorCompraPoveedor() {
        this.daoCompraProveedor = new DAOCompraProveedor();
    }

    public boolean insertarCompraProveedor(CompraProveedor compraProveedor) {
        return daoCompraProveedor.insertarCompraProveedor(compraProveedor);
    }

    public CompraProveedor buscarCompraProveedor(int codigo) {
        return daoCompraProveedor.buscarCompraProveedor(codigo);
    }

    public boolean actualizarCompraProveedor(CompraProveedor compraProveedor) {
        return daoCompraProveedor.actualizarCompraProveedor(compraProveedor);
    }

    public boolean eliminarCompraProveedor(int codigo) {
        return daoCompraProveedor.eliminarCompraProveedor(codigo);
    }
    
    public List<CompraProveedor> listar(){
        return daoCompraProveedor.listarCompras();
    }
}
