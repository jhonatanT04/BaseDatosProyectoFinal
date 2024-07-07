/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extra.CorreoFinal;

import DAO.DAOCategoria;
import DAO.DAOProducto;
import DAO.DAOProveedores;
import Modelo.Producto.Categoria;
import Modelo.Producto.Producto;
import Modelo.Proveedor.Proveedor;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Pruebas1 {

    public static void main(String[] args) {
        DAOProducto productoDAO = new DAOProducto();
        DAOCategoria cate = new DAOCategoria();
        DAOProveedores pro = new DAOProveedores();
        
         
        //Proveedor prov = new Proveedor(1, "Juanito", "45464", "ajdhjsa", "bdsad", "hdjsahjd");
        pro.eliminarProveedor(1);
        //pro.insertarProveedor(prov);
        /*Categoria cat = new Categoria(1, "Producto");
        cate.insertarCategoria(cat);
        
        Producto pro = new Producto(1, "Azucar", 5.0, 4, 5, 'h', 1);
        productoDAO.insertarProducto(pro);*/
        /*boolean eliminado = productoDAO.eliminarProducto("Azucar"); // Intenta eliminar el producto con nombre "Azucar"
        if (eliminado) {
            System.out.println("Producto 'Azucar' eliminado correctamente.");
        } else {
            System.out.println("No se encontró el producto 'Azucar' para eliminar.");
        }*/
        
        
    }
}
