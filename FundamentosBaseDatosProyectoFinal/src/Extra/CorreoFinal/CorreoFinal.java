/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extra.CorreoFinal;

import Extra.imagen.CrearPdf;
import Modelo.Factura.CabeceraFactura;
import Modelo.Factura.DetalleFactura;
import Modelo.Personas.Persona.Cliente;
import Modelo.Personas.Persona.Empleado;
import Modelo.Producto.Categoria;
import Modelo.Producto.Producto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.ImageIcon;
/**
 *
 * @author venot
 */
public class CorreoFinal {
    
    private String username = "supermercadobasedatos@gmail.com";;
    private String password = "ubor uasy tfzi okbf";
    private String ruta = "src/Extra/imagen/Factura.pdf";
    
    
    public void sendEmail(String to, String subject, String body) {
        // Configuración de las propiedades para el servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable",  "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        /*props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        //2525
        props.put("mail.smtp.port", "587");*/

        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            
            MimeBodyPart attachPart = new MimeBodyPart();
            //********
            
            try {
                attachPart.attachFile(ruta);
            } catch (Exception e) {
                e.printStackTrace();
            }
            multipart.addBodyPart(attachPart);

            
            message.setContent(multipart);

            
            Transport.send(message);

            System.out.println("Correo enviado con éxito.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
    public void factura(CabeceraFactura cabecera, List<DetalleFactura> detalles,List<Producto> productos, Cliente cliente) {
        //LOS PRODUCTOS Y DETALLES SE DEBEN INGRESAR EN EL MISMO ORDEN
        Document document = new Document(PageSize.LETTER);
        
        try {
            
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("src/Extra/imagen/Factura.pdf"));
            
            document.open();
            
            PdfContentByte cb = writer.getDirectContent();
            Graphics g = cb.createGraphicsShapes(PageSize.LETTER.getWidth(), PageSize.LETTER.getHeight());

            g.setColor(new Color(23, 117, 187));
            g.fillOval(95, 12, 51, 29);
                        
            Font font1 = new Font("Tahoma", Font.BOLD | Font.ITALIC, 10);
            g.setFont(font1);
            
            g.setColor(new Color(2, 175, 195));
            g.drawString("Supermercado",    70, 30);
            
            String imagePath = "src/Extra/imagen/logoSup.jpg";
            ImageIcon img1 = new ImageIcon(imagePath);
            
            
            if (img1.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                System.err.println("No se pudo cargar la imagen desde la ruta: " + imagePath);
            } else {
                g.drawImage(img1.getImage(), 10, 0, 50, 50, null);
            }
            
            //************************************************************************************************************
            Font font2 = new Font("Arial", Font.ROMAN_BASELINE, 8);
            g.setFont(font2);
            
            g.setColor(Color.BLACK);
            
            g.drawString("ANIA CORP EC SPM",    20, 80);
            g.drawString("RUC  01238634899",    20, 90);
            g.drawString("C. Vieja 12-30 y Avenida Turuhuayco 010105",    20, 110);
            g.drawString("CUENCA ECUADOR",    20, 120);
            g.drawString("0912362839",    20, 130);
            g.drawString(username,    20, 140);
            
            g.drawString("Numero de factura ",    390, 30);
            if (cabecera.getCodigo()<10000) {
                g.drawString("N.º",    390, 45);
                String num = String.valueOf(cabecera.getCodigo());
                int var = 5-num.length();
                for (int i = 0; i < var+1; i++) {
                    if (i==var) {
                        g.drawString(num,    402+(i*4), 45);
                        break;
                    }else{
                        g.drawString("0",    402+(i*4), 45);
                    }
                }
            }else{
                g.drawString("N.º : "+cabecera.getCodigo(),    465, 45);
            }
            g.drawString("Fecha :",    390, 60);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = sdf.format(cabecera.getFecha());
            g.drawString(fechaFormateada,    424, 60);
            
            g.setColor(Color.BLACK); 
            g.drawLine(20, 170, 500, 170);
             g.drawString("Datos Cliente",    20, 167);
            g.drawString(cliente.getApellido()+", "+cliente.getNombre(),    20, 180);
            g.drawString("CEDULA "+cliente.getCedula(),    20, 190);
            g.drawString(cliente.getCorreo(),    20, 200);
            g.drawString(cliente.getDireccion(),    20, 210);
            g.drawString("Tel : "+cliente.getTelefono(),    20, 220);
            g.setColor(Color.BLACK); 
            g.drawLine(20, 232, 500, 232);
            
            
            g.drawString("Cantidad",20, 245);
            g.drawString("Codigo procucto",80, 245);
            g.drawString("Nombre ",160, 245);
            g.drawString("Precio Unitario ",250, 245);
            g.drawString("Subtotal ",320, 245);
            g.drawString("IVA",380, 245);
            g.drawString("Total",450, 245);
            
            g.setColor(Color.BLACK); 
            g.drawLine(20, 251, 500, 251);
            int interlineado = 14;
            int num = 0;
            for (int i = 0; i < detalles.size(); i++) {
                g.setColor(Color.BLACK); 
                g.drawLine(20, 265+(i*interlineado), 500, 265+(i*interlineado));
                
                DetalleFactura detalle = detalles.get(i);
                Producto producto = productos.get(i);
                font2 = new Font("Arial", Font.ROMAN_BASELINE, 8);
                g.setFont(font2);
                g.setColor(Color.BLACK);
                
                g.drawString(String.valueOf(detalle.getCantidad()),20, 260+(i*15));
                g.drawString(String.valueOf(producto.getCodigo()),80, 260+(i*15));
                g.drawString(producto.getNombre(),160, 260+(i*15));
                g.drawString(String.valueOf(detalle.getPrecioUnitario()),250, 260+(i*15));
                g.drawString(String.valueOf(detalle.getSubTotal()),320, 260+(i*15));
                g.drawString(String.valueOf(detalle.getIva()),380, 260+(i*15));
                g.drawString(String.valueOf(detalle.getTotal()),450, 260+(i*15));
                num = 260+(i*15);
                
            }
            
            font2 = new Font("Arial", Font.ROMAN_BASELINE, 8);
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Subtotal",380, num + 20);
            g.drawString("Total IVA",380, num + 40);
            g.drawString("Valor Total ",380, num + 60);
            g.drawString(String.valueOf(cabecera.getSubTotal()),450, num + 20);
            g.drawString(String.valueOf(cabecera.getTotalIVA()),450, num + 40);
            g.drawString(String.valueOf(cabecera.getValorTotal()),450, num + 60);
            
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } finally {
            // step 5: we close the document
            document.close();
            this.sendEmail(cliente.getCorreo(), "Factura de ANIA CORP EC SPM ", cliente.getApellido()+", "+cliente.getNombre()+" Ha recibido una factura por el valor de " + cabecera.getValorTotal());
        }
    }
    
    
    
    public static void main(String[] args) {
        
        //CrearPdf pdfCreator = new CrearPdf();
        Cliente cli = new Cliente(12, 's', 23, "0102", "Juan", "Perez", "La casa", "0999999999", "venotacu@gmail.com");
        Empleado emp = new Empleado(23, 'a', "Ania1234", 'a', 12, "0101", "Ana", "Leon", "Otra casa", "091234", "ania");
        CabeceraFactura cab = new CabeceraFactura(9, new Timestamp(System.currentTimeMillis()), 12.2, 2.2, 14.4, 's', cli, emp);
        
        Categoria cat = new Categoria(12,"ania");
        List<DetalleFactura> detalles =  new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        
        
        Producto pr = new Producto(19,"Arroz", 5.66, 3, 0.68, 'a', cat);
        productos.add(pr);
        detalles.add(new DetalleFactura(41, 2, 5.66, 11.22, 1.16, 12.38, cab, pr));
        pr = new Producto(3,"Pollo", 3, 4, 0.3, 'b', cat);
        productos.add(pr);
        detalles.add(new DetalleFactura(99, 1, 3, 3, 0.3, 3.03, cab, pr));
        pr = new Producto(82,"Carne", 4.01, 23, 0.4, 'b', cat);
        productos.add(pr);
        detalles.add(new DetalleFactura(101, 10, 4.01, 40.1, 4, 42.1, cab, pr));
        //LOS PRODUCTOS Y DETALLES SE DEBEN INGRESAR EN EL MIMO ORDEN
        
        //pdfCreator.factura(cab, detalles, productos,"ania@gmail.com",new Timestamp(System.currentTimeMillis()),cli);
        CorreoFinal c = new CorreoFinal();
        c.factura(cab, detalles, productos, cli);
        /*
        String dateString = "12/31/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            // Convertir la cadena a un objeto Date
            java.util.Date parsedDate = dateFormat.parse(dateString);

            // Convertir el objeto Date a un Timestamp
            Timestamp timestamp = new Timestamp(parsedDate.getTime());

            // Mostrar el Timestamp
            System.out.println("Timestamp: " + timestamp);

            // Formatear el Timestamp de vuelta a una cadena si es necesario
            String formattedDate = dateFormat.format(timestamp);
            System.out.println("Fecha formateada: " + formattedDate);

            // Guardar el Timestamp en el formato deseado (en este caso, sólo se muestra en consola)
            // En una aplicación real, puedes almacenar este timestamp en una base de datos o un archivo
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        String to = "venotacu@gmail.com";
        String subject = "Ania";
        String body = "Ania JIJJAJA Este es el cuerpo del mensaje.";

        /* Archivo PDF adjunto
        String pdfFilePath = "src/Extra/imagen/Factura.pdf";

        CorreoFinal emailSender = new CorreoFinal();
        emailSender.sendEmail(to, subject, body, pdfFilePath);*/
    }
    
    
    
}
