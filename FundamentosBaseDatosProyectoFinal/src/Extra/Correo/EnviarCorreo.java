/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extra.Correo;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author venot
 */
public class EnviarCorreo {
    private String username;
    private String password;

    public EnviarCorreo() {
        
        this.username = "supermercadobasedatos@gmail.com";
        this.password = "ubor uasy tfzi okbf";
        
        
    }

    public void sendEmail(String to, String subject, String body, String pdfFilePath) {
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
            try {
                attachPart.attachFile(pdfFilePath);
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

    public static void main(String[] args) {
        
        String to = "venotacu@gmail.com";
        String subject = "Ania";
        String body = "Ania JIJJAJA Este es el cuerpo del mensaje.";

        // Archivo PDF adjunto
        String pdfFilePath = "src/Extra/imagen/Factura.pdf";

        EnviarCorreo emailSender = new EnviarCorreo();
        emailSender.sendEmail(to, subject, body, pdfFilePath);
    }
}
