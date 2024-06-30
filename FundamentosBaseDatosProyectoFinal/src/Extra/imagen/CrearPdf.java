package Extra.imagen;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

public class CrearPdf {
    public void factura() {
        // step 1: creation of a document-object        
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
            Font font2 = new Font("Tahoma", Font.PLAIN, 15);
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Escanea el código QR para visitar la lista de reproducción de YouTube", 60, 460);
            g.drawString("del curso de GUI en Java", 210, 480);
            
            
            
            // Nueva página
            //document.newPage();
            
            // Dibujar formas en la segunda página
            //g.setColor(Color.GREEN);
            //g.drawLine(1, 1, 200, 200);

            //g.setColor(Color.BLUE);
            //g.drawRect(200, 200, 300, 300);
            
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
        pdfCreator.factura();
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
