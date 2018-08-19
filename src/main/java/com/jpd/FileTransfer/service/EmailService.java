package com.jpd.FileTransfer.service;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    public void sendSimpleMessage(List<String> files){
    	System.out.println(files.toString());
    	        final String username = "chris@d2z.com.au";
    	        final String password = "HelpMe2150";

    	        Properties props = new Properties();
    	        props.put("mail.smtp.auth", "true");
    	        props.put("mail.smtp.starttls.enable", "true");
    	        props.put("mail.smtp.host", "outlook.office365.com");
    	        props.put("mail.smtp.port", "587");

    	        Session session = Session.getInstance(props,
    	          new javax.mail.Authenticator() {
    	            protected PasswordAuthentication getPasswordAuthentication() {
    	                return new PasswordAuthentication(username, password);
    	            }
    	          });

    	        try {
    	        	MimeMessage message = new MimeMessage(session);
    	            message.setFrom(new InternetAddress("chris@d2z.com.au"));
    	            //message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chris@d2z.com.au"));
    	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ranjucse07@gmail.com, rafikahamed56@gmail.com,"
    	            																		+ "chris@d2z.com.au, manijajay@gmail.com"));
    	            message.setSubject("JPD-FTP Success Message Notification");
    	            message.setText("Below mention list of files processed successfully");
    	            StringBuilder sb1 = new StringBuilder(200);
    	            sb1.append("<html><body>"+ "<table style='border:2px solid black'>");
    	            for(String fileVal : files){
    	                sb1.append("<tr><br/><td>");
    	                sb1.append(fileVal)
    	                   .append("</td><br/>");
    	                sb1.append("</td><br/><td>");
    	                String in = sb1.toString();
    	            }
    	            sb1.append("</table></body></html>");
    	            
    	            StringBuilder buf = new StringBuilder();
    	            buf.append("<html>" +
    	                       "<body>" +
    	                       "<table>" +
    	                       "<tr>" +
    	                       "<th>File Name</th>" +
    	                       "</tr>");
    	            for (String fileVal : files) {
    	                buf.append("<tr><td>")
    	                   .append(fileVal)
    	                   .append("</td>");
    	            }
    	            buf.append("</table>" +
    	                       "</body>" +
    	                       "</html>");
    	            String html = buf.toString();
    	            message.setText(html.toString(), "utf-8", "html");
    	            Transport.send(message);
    	        } catch (MessagingException e) {
    	            throw new RuntimeException(e);
    	        }
    }

}
