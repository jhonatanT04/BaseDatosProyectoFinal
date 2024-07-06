/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Personas.Persona;

/**
 *
 * @author venot
 */
public class Cliente extends Persona{
    private int ClienteCodigo; 
    private char visualizacion;

    public Cliente(int ClienteCodigo, char visualizacion, int codigo, String cedula, String nombre, String apellido, String direccion, String telefono, String correo) {
        super(codigo, cedula, nombre, apellido, direccion, telefono, correo);
        this.ClienteCodigo = ClienteCodigo;
        this.visualizacion = visualizacion;
    }

    public int getClienteCodigo() {
        return ClienteCodigo;
    }

    public void setClienteCodigo(int ClienteCodigo) {
        this.ClienteCodigo = ClienteCodigo;
    }

    public char getVisualizacion() {
        return visualizacion;
    }

    public void setVisualizacion(char visualizacion) {
        this.visualizacion = visualizacion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "ClienteCodigo=" + ClienteCodigo + ", visualizacion=" + visualizacion +'}';
    }
    
    
    
    
}
