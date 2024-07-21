/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.Cliente;

import Controlador.ControladorCliente;
import Controlador.ControladorPersona;
import Modelo.Personas.Persona.Cliente;
import Modelo.Personas.Persona.Persona;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Usuario
 */
public class ActualizarDatosClientes extends javax.swing.JInternalFrame {
    private ControladorCliente controladorCliente;
    private ControladorPersona controladorPersona;
    private Cliente cliente;
    private String primeraCedula;
    /**
     * Creates new form ActualizarDatos
     */
    public ActualizarDatosClientes() {
        initComponents();
        controladorCliente = new ControladorCliente();
        controladorPersona = new ControladorPersona();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("Cancelar");

        txtTelefono.setEditable(false);

        txtCorreo.setEditable(false);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombres:");

        jLabel8.setText("Apellidos:");

        jLabel9.setText("Direccion:");

        jLabel10.setText("Telefono:");

        jLabel11.setText("Correo Electronico:");

        txtNombre.setEditable(false);

        txtApellido.setEditable(false);

        txtDireccion.setEditable(false);

        jLabel12.setText("Cedula:");

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addGap(70, 70, 70)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnActualizar))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        if (txtCedula.getText().trim().isEmpty()==false) {
            if(txtCedula.getText().trim().length()>10||txtCedula.getText().trim().length()<10){
                JOptionPane.showInternalMessageDialog(rootPane, "El campo cedula lleva 10 digitos");
            }else{
                try {
                    Persona per =controladorPersona.buscarPersonaCliente(txtCedula.getText().trim());
                    if (per!=null) {
                        Cliente cli = controladorCliente.buscarCliente(per);
                        if (cli!=null) {
                            if (cli.getVisualizacion()=='A') {
                                txtApellido.setText(cli.getApellido());
                                txtNombre.setText(cli.getNombre());
                                txtCorreo.setText(cli.getCorreo());
                                txtDireccion.setText(cli.getDireccion());
                                txtTelefono.setText(cli.getTelefono());
                                cliente = cli;
                                primeraCedula =txtCedula.getText().trim();
                                habilitarCampos(true);
                                System.out.println("Primera cedula "+ primeraCedula);
                                
                            }else{
                                habilitarCampos(false);
                                //JOptionPane.showInternalMessageDialog(rootPane, "El cliente esta desactivado.");
                                int opc = JOptionPane.showConfirmDialog(rootPane, "El cliente con numero de cedula "+per.getCedula()+" se encuentra desactivado. ¿Desea actualizarlo?·");
                                if (opc==0) {
                                    int v =JOptionPane.showInternalConfirmDialog(rootPane, "Esta seguro de activar al cliente con numero de cedula "+cli.getCedula()+" y con nombre de "+cli.getNombre()+" "+cli.getApellido());
                                    if (v==0) {
                                        cli.setVisualizacion('A');
                                        controladorCliente.actualizarCliente(cli);
                                        System.out.println("Se actualiza cliente");
                                        JOptionPane.showInternalMessageDialog(rootPane, "La persona se actualizado el estado.·");
                                        this.limpiarCampos();
                                    }
                                }
                            }
                        }else{
                            habilitarCampos(false);
                            JOptionPane.showInternalMessageDialog(rootPane, "El cliente no existe en la base de datos.");
                            
                        }
                    }else{
                        habilitarCampos(false);
                        JOptionPane.showInternalMessageDialog(rootPane, "La persona no existe en la base de datos");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showInternalMessageDialog(rootPane, "Se debe llenar el campo cedula");
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (validarCampos()) {
            try {
                Persona per =controladorPersona.buscarPersonaCliente(txtCedula.getText().trim());
                if (per==null||txtCedula.getText().trim().equals(primeraCedula)) {
                    int opc = JOptionPane.showInternalConfirmDialog(rootPane, "Esta seguro que quiere modificar el cliente.");
                    if (opc==0) {
                        cliente.setCedula(txtCedula.getText().trim());
                        cliente.setNombre(txtNombre.getText().trim());
                        cliente.setApellido(txtApellido.getText().trim());
                        cliente.setDireccion(txtDireccion.getText().trim());
                        cliente.setTelefono(txtTelefono.getText().trim());
                        cliente.setCorreo(txtCorreo.getText().trim());
                        controladorCliente.actualizarCliente(cliente);
                        this.habilitarCampos(false);
                        this.limpiarCampos();
                    }
                }else{
                    JOptionPane.showInternalMessageDialog(rootPane, "Existe una persona con este numero de cedula "+per.getCedula());
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ActualizarDatosClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.habilitarCampos(false);
        this.limpiarCampos();
    }//GEN-LAST:event_jButton2ActionPerformed
    public void limpiarCampos(){
        txtCedula.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        primeraCedula="";
    }
    public void habilitarCampos(boolean opc){
        
        txtApellido.setEditable(opc);
        txtNombre.setEditable(opc);
        txtCorreo.setEditable(opc);
        txtDireccion.setEditable(opc);
        txtTelefono.setEditable(opc);
        btnBuscar.setEnabled(!opc);
        btnActualizar.setEnabled(opc);
        
    }
    private boolean validarCampos() {
        if (txtApellido.getText().trim().isEmpty()||txtNombre.getText().trim().isEmpty()||txtDireccion.getText().trim().isEmpty()||txtTelefono.getText().trim().isEmpty()||txtCorreo.getText().trim().isEmpty()) {
            JOptionPane.showInternalMessageDialog(rootPane, "Se deben llenar Todos los campos");
            return false;
        }else if ((txtApellido.getText().trim().length()>100)||txtNombre.getText().trim().length()>100||txtDireccion.getText().trim().length()>100||txtCorreo.getText().trim().length()>100){
            JOptionPane.showInternalMessageDialog(rootPane, "Los campos deben de llenarse con menos de 100 caracteres");
            return false;
        }else if (txtTelefono.getText().trim().length()>10||txtTelefono.getText().trim().length()<10){
            JOptionPane.showInternalMessageDialog(rootPane, "El campo de Telefono de deve tener 10 numeros ");
            //System.out.println(txtDireccion.getText().trim().length());
            return false;
        }else if (txtCedula.getText().trim().length() >10||txtCedula.getText().trim().length()<10){
            JOptionPane.showInternalMessageDialog(rootPane, "El campo de Cedula se deve tener 10 numeros ");
            return false;
        }
        else{
            return true;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
