/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Usuario
 */
import DAO.DAOCategoria;
import Modelo.Producto.Categoria;
import javax.swing.JComboBox;
import java.util.List;

public class ControladorCategoria {

    private DAOCategoria daoCategoria;

    public ControladorCategoria() {
        try {
            this.daoCategoria = new DAOCategoria(); // Intenta inicializar daoCategoria
        } catch (Exception e) {
            System.err.println("Error al inicializar DAO de categoría: " + e.getMessage());
            // Aquí puedes agregar más lógica de manejo de errores según sea necesario
        }
    }

    public boolean insertarCategoria(Categoria categoria) {
        return daoCategoria.insertarCategoria(categoria);
    }

    public void buscarCategoria(String nombre) {
        daoCategoria.buscarCategoria(nombre);
    }

    public boolean actualizarCategoria(Categoria categoria) {
        return daoCategoria.actualizarCategoria(categoria);
    }

    public boolean eliminarCategoria(String nombre) {
        return daoCategoria.eliminarCategoria(nombre);
    }

    public void cargarCategorias(JComboBox<String> comboBox) {
        daoCategoria.cargarCategorias(comboBox);
    }

    public int obtenerCodigoCategoria(String nombreCategoria) {
        return daoCategoria.obtenerCodigoCategoria(nombreCategoria);
    }
}
