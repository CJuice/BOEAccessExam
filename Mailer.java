package gov.texas.tceq.BOEAccessTest;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {

	//STATE
    private boolean doLogging = false;
    // Sender's email ID needs to be mentioned
    private static String fromEmailAddress = "ergis@tceq.texas.gov";
    
    //CONSTRUCTORS
    /**
     * default no-arg constructor
     */
    public Mailer() {
	}

	//METHODS
    /**
	 * Sends the emailBodyContent to the recipient addresses in the array of emails
	 * @author TNuernbe
	 * @author CSchaefe modified March 2017
	 * 
	 * @param examTakersName	the name of the user taking the exam
	 * @param emailRecipients	an array of email recipient addresses for those who will receive notice of a PASS
	 * @param emailBodyContent	the content of the email message
	 * 
	 */
	public void sendEMail(String examTakersName, String[] emailRecipients, String emailBodyContent) {

        // Make a new Date object. It will be initialized to the current time.
        Date now = new Date();

		try {
			String host = "tceq.texas.gov";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);
			
			for (int i = 0; i < emailRecipients.length; i++) {
				
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(fromEmailAddress));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipients[i]));

				// Set Subject: header field
				message.setSubject(examTakersName + " passed the BOE Access Exam (" + now.toString() + ")");

				// Now set the actual message
				message.setText(emailBodyContent, "utf-8", "html");

				// Send message
				Transport.send(message);
				if(doLogging){
					System.out.println("OK -- email sent");
				}
			}
		} catch (MessagingException mex) {
			System.out.println("Exception");
		}
	}
}
