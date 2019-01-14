package com.example.d2z.utils;

import java.util.List;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.UserDetails;
import com.example.d2z.repository.UserRepository;

@Component
public class EmailUtil {
	
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String fromEmail, String toEmail, String name, String messageData, String subject){
		try{
        	MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("support@jpdglobal.com.au"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Contact Us -->"+subject);
            String msgBody = "<i>HI JPD Support Team,</i><br><br>";
            msgBody += "<b>Name : </b>"+name+"<br><br>";
            msgBody += "<b>From Email: </b>"+fromEmail+"<br><br>";
            msgBody += "<b>Message: </b>"+messageData+"<br><br>";
     	    message.setContent(msgBody, "text/html");
     	    Transport.send(message);
	    }catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public void senderEmail(Session session, String email, String toEmail, String name, String subject) {
		try{
				MimeMessage message = new MimeMessage(session);
				 message.setFrom(new InternetAddress("support@jpdglobal.com.au"));
			        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			        message.setSubject("Acknowledgement -->"+subject);
			        String msgBody = "<i>Dear </i>"+name+",<br><br>";
			        msgBody += "<b> Thank you for your enquiry, this has been received and we will endeavour to respond to you within 24 hours. </b> <br><br>";
			        msgBody += "<b> Thank you,</b> <br>";
			        msgBody += "<b> JPD Support Team</b> <br><br>";
			        message.setContent(msgBody, "text/html");
			 	    Transport.send(message);
		    }catch (Exception e) {
			      e.printStackTrace();
		    }
	}
	
	public void adminSignUpEmail(Session session, UserDetails Userdata) {
		try{
				 MimeMessage message = new MimeMessage(session);
				 message.setFrom(new InternetAddress("support@jpdglobal.com.au"));
			        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chris@jpdglobal.com.au"));
			        message.setSubject("JPD - Admin SignUp Details");
			        String msgBody = "<i>Dear </i> Chris,<br><br>";
			        msgBody += "<b>Admin signUp changes.</b><br><br>";
			        msgBody += "<b>Please find the below details,</b><br><br>";
			        msgBody += "<b>Access Level : </b>"+Userdata.getAccess_level()+"<br><br>";
			        msgBody += "<b>Legal Name : </b>"+Userdata.getLegalName()+"<br><br>";
			        msgBody += "<b>Authorised Contact : </b>"+Userdata.getAuthrorizedConatct()+"<br><br>";
			        msgBody += "<b>Email Address : </b>"+Userdata.getEmail_addr()+"<br><br>";
			        msgBody += "<b>Phone Number : </b>"+Userdata.getPhoneNumber()+"<br><br>";
			        msgBody += "<b>Level-1 User Name : </b>"+Userdata.getMgr_username()+"<br><br>";
			        msgBody += "<b>ARN Number : </b>"+Userdata.getArn_number()+"<br><br>";
			        msgBody += "<b>User Name : </b>"+Userdata.getUsername()+"<br><br>";
			        msgBody += "<b>Password : </b>"+Userdata.getPass_word()+"<br><br>";
			        msgBody += "<b> Thank you,</b> <br>";
			        msgBody += "<b> JPD Support Team</b> <br><br>";
			        message.setContent(msgBody, "text/html");
			 	    Transport.send(message);
			 	   
			 	   MimeMessage subMessage = new MimeMessage(session);
			 	   String SenderEmailAddress = userRepository.getEmailAddress(Userdata.getLegalName());
			 	   subMessage.setFrom(new InternetAddress("support@jpdglobal.com.au"));
			 	   		subMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SenderEmailAddress));
			 	   		subMessage.setSubject("JPD - SignUp Details");
				        String subMsgBody = "<i>Dear </i> "+Userdata.getUsername()+",<br><br>";
				        subMsgBody += "<b> Please find the Signup details</b><br><br>";
				        subMsgBody += "<b>User Name : </b>"+Userdata.getUsername()+"<br><br>";
				        subMsgBody += "<b>Password : </b>"+Userdata.getPass_word()+"<br><br>";
				        subMsgBody += "<b> Thank you,</b> <br>";
				        subMsgBody += "<b> JPD Support Team</b> <br><br>";
				        subMessage.setContent(subMsgBody, "text/html");
				 	    Transport.send(subMessage);
		    }catch (Exception e) {
			      e.printStackTrace();
		    }
	}

	public void userSignupEmail(Session session, List<ArnRegistration> arnRegisterData) {
		try {
				MimeMessage arnMessage = new MimeMessage(session);
				arnMessage.setFrom(new InternetAddress("support@jpdglobal.com.au"));
				arnMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chris@jpdglobal.com.au"));
				arnMessage.setSubject("JPD - User SignUp Details");
			        String msgBody = "<i>Dear </i> Chris,<br><br>";
			        msgBody += "<b>New user has been signedUp</b><br><br>";
			        msgBody += "<b>Please find the below details,</b><br><br>";
			        msgBody += "<b>Business type : </b>"+arnRegisterData.get(0).getBusinessType()+"<br><br>";
			        msgBody += "<b>Legal Name : </b>"+arnRegisterData.get(0).getLegalName()+"<br><br>";
			        msgBody += "<b>Authorised Contact : </b>"+arnRegisterData.get(0).getAuthrorizedConatct()+"<br><br>";
			        msgBody += "<b>Email Address : </b>"+arnRegisterData.get(0).getEmailAddress()+"<br><br>";
			        msgBody += "<b>Phone Number : </b>"+arnRegisterData.get(0).getPhoneNumber()+"<br><br>";
			        msgBody += "<b>Postal address : </b>"+arnRegisterData.get(0).getPostalAddress()+"<br><br>";
			        msgBody += "<b>Suburb : </b>"+arnRegisterData.get(0).getSubUrb()+"<br><br>";
			        msgBody += "<b>Postcode : </b>"+arnRegisterData.get(0).getPostCode()+"<br><br>";
			        msgBody += "<b>Country : </b>"+arnRegisterData.get(0).getCountry()+"<br><br>";
			        msgBody += "<b>Business and trading website name : </b>"+arnRegisterData.get(0).getWebsiteName()+"<br><br>";
			        msgBody += "<b>Tax identification number in your home country : </b>"+arnRegisterData.get(0).getTanNumber()+"<br><br>";
			        msgBody += "<b> Thank you,</b> <br>";
			        msgBody += "<b> JPD Support Team</b> <br><br>";
			        arnMessage.setContent(msgBody, "text/html");
			 	    Transport.send(arnMessage);
			}catch (Exception e) {
			      e.printStackTrace();
		  }
	}
	
}
