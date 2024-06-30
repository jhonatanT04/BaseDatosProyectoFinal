/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Personas.Persona;

/**
 *
 * @author venot
 */
public class Empleado extends Persona{
    
    
    private int EmpleadoCodigo;
    private char visualizacion;
    private String contrasenia;
    private char permiso;

    public Empleado(int EmpleadoCodigo, char visualizacion, String contrasenia, char permiso, int codigo, String cedula, String nombre, String apellido, String direccion, String telefono, String correo) {
        super(codigo, cedula, nombre, apellido, direccion, telefono, correo);
        this.EmpleadoCodigo = EmpleadoCodigo;
        this.visualizacion = visualizacion;
        this.contrasenia = contrasenia;
        this.permiso = permiso;
    }
    
    
    
    public int getEmpleadoCodigo() {
        return EmpleadoCodigo;
    }

    public void setEmpleadoCodigo(int EmpleadoCodigo) {
        this.EmpleadoCodigo = EmpleadoCodigo;
    }

    public char getVisualizacion() {
        return visualizacion;
    }

    public void setVisualizacion(char visualizacion) {
        this.visualizacion = visualizacion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public char getPermiso() {
        return permiso;
    }

    public void setPermiso(char permiso) {
        this.permiso = permiso;
    }

    
    
    
    
    
    
}
