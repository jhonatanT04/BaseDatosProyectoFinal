package Extra.imagen;

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
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CrearPdf {
    
    public void factura(CabeceraFactura cabecera, List<DetalleFactura> detalles,List<Producto> productos, String correoEmpresa,Timestamp fecha, Cliente cliente) {
        // step 1: creation of a document-object      
        //LOS PRODUCTOS Y DETALLES SE DEBEN INGRESAR EN EL MIMO ORDEN
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
            g.drawString(correoEmpresa,    20, 140);
            
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
            String fechaFormateada = sdf.format(fecha);
            g.drawString(fechaFormateada,    424, 60);
            
            g.setColor(Color.BLACK); 
            g.drawLine(20, 170, 500, 170);
            
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
        }
    }

    public static void main(String[] args) {
        
        CrearPdf pdfCreator = new CrearPdf();
        Cliente cli = new Cliente(12, 's', 23, "0102", "Juan", "Perez", "La casa", "0999999999", "ania2@gmail.com");
        Empleado emp = new Empleado(23, 'a', "Ania1234", 'a', 12, "0101", "Ana", "Leon", "Otra casa", "091234", "ania");
        CabeceraFactura cab = new CabeceraFactura(9, new Timestamp(System.currentTimeMillis()), 12.2, 2.2, 14.4, 's', cli, emp);
        List<DetalleFactura> detalles =  new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        Categoria cat = new Categoria(12,"ania");
        Producto pr = new Producto(2,"Arroz", 12.3, 23, 0.12, 'a', cat);
        productos.add(pr);
        detalles.add(new DetalleFactura(12, 2, 3, 9, 0.12, 99, cab, pr));
        pr = new Producto(3,"Pollo", 99.3, 23, 32, 'b', cat);
        productos.add(pr);
        detalles.add(new DetalleFactura(99, 5, 12, 8, 0.03, 2, cab, pr));
        //LOS PRODUCTOS Y DETALLES SE DEBEN INGRESAR EN EL MIMO ORDEN
        pdfCreator.factura(cab, detalles, productos,"ania@gmail.com",new Timestamp(System.currentTimeMillis()),cli);
        
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
    }
}
