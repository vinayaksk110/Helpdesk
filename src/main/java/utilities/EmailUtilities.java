package utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import testbase.HelpDeskConstants;

public class EmailUtilities {
	DateNTime dateNTime = new DateNTime();
	
	private String readHTMLEmailableReport() throws IOException{
		// Read the file from the local location and return it.
		@SuppressWarnings("deprecation")
		String EmailableReportContent = Files.toString(new File(HelpDeskConstants.WINDOWS_EMAILABLE_REPORT), Charsets.UTF_8);		
		return EmailableReportContent;
	}
	
	public String emailFormatter(int formatIndex, String email){
		StringBuilder formattedEmail= new StringBuilder(email);
		
		// Receive today's date and format the date using date and time utility
		String todaysDate = dateNTime.printCurrentDate();
		todaysDate = todaysDate.replace("/", "");
		
		// Append the formatted date after 'Plus' sign so that, 
		// emails are delivered to valid IDrive email address.  
		formattedEmail.insert(formatIndex, todaysDate);
		return formattedEmail.toString();
	}
	
	public void sendHTMLAttachmentAsEmail() throws EmailException {
		// Create the email message
		  HtmlEmail email = new HtmlEmail();
		  
		  // Set the required server, authentication and SSL settings
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(465);
		  //email.setSSLOnConnect(true);
		  //email.setSSL(true);
		  email.setSSLOnConnect(true);
		  //email.setAuthenticator(new DefaultAuthenticator("sam260916@gmail.com", "Stream!@#$"));
		  email.setAuthenticator(new DefaultAuthenticator("automationreports@idrive.com", "d@vteam12"));
		  
		  // Set the sender information.
		  //email.setFrom("sam260916@gmail.com", "IDrive Automation Testing");
		  email.setFrom("automationreports@idrive.com", "Automation Testing");
		  
		  // Form the email with subject, receiver and sender information.
		  email.setSubject("RemotePC functionality testing report");
		  email.addTo("karthik_ss@idrive.com", "Samanth Karthik");
		  //email.addCc("murali.t@idrive.com");
		  //email.addCc("kokila@idrive.com");
		  //email.addCc("prashanth.yadav@idrive.com", "Prasanth");
		  
		  // Read the html file from the local system and set it in HTML body.
		  String body = "";
		  try {
			body = this.readHTMLEmailableReport();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		  // set the html message
		  email.setHtmlMsg(body);

		  // set the alternative message
		  email.setTextMsg("Your email client does not support HTML messages");

		  // send the email
		  email.send();
	}

}
