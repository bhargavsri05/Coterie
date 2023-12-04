package coterie.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentEmailReporter;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import java.util.*;

public class ExtentReportListener implements IReporter {

	private ExtentReports extent;

	public static void main(String[] args) {
		// Replace these values with your actual details
		String to = "parthasarathi.a@globaldigitalnext.com";
		//String from = "testCoterie@gmail.com";
		String from = "partha@codelogics.in";
	//	String from = "saroj@codelogics.in";
		// String from = "testercoterie@gmail.com";
		 //String host = "smtp-mail.outlook.com";
		 String host = "smtp.office365.com";
		//String host = "smtp.gmail.com";
		String port = "587";
		// String port="25";
		/*
		 * final String username = "testCoterie@gmail.com"; final String password =
		 * "uvou jrnj ezyo rcyt";
		 */
		
		//final String username = "saroj@codelogics.in";
		final String username = "partha@codelogics.in";
		final String password = "Dac10190";
		//final String password = "Zab80684";
		// final String password = "TheSoftestBunny1632!";
		// final String password = "DigiNext@123";
		String reportPath = "target/extentReport/AutomationReports.html";

		sendEmail(to, from, host, port, username, password, reportPath);
	}

	public static void sendEmail(String to, String from, String host, String port, String username, String password,
			String reportPath) {
		/*
		 * Properties properties = System.getProperties();
		 * properties.setProperty("mail.smtp.host", host);
		 * properties.setProperty("mail.smtp.auth", "true");
		 * properties.setProperty("mail.smtp.port", port);
		 * properties.setProperty("mail.smtp.starttls.enable", "true");
		 */
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a MimeMessage
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Test Automation Report");

			// Attach the Extent Report file
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(reportPath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(reportPath);
			multipart.addBodyPart(messageBodyPart);

			// Set the message content
			message.setContent(multipart);

			// Send the message
			Transport.send(message);
			System.out.println("Email sent successfully!");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	/*
	 * public static void main(String args[]) {
	 * sendEmailWithAttachment("target/extentReport/AutomationReports.html"); }
	 * 
	 * @Override public void generateReport(List<XmlSuite> xmlSuites, List<ISuite>
	 * suites, String outputDirectory) { // Initialize ExtentReports and
	 * ExtentHtmlReporter extent = new ExtentReports();
	 * 
	 * ExtentEmailReporter email = new ExtentEmailReporter("user/build/name/");
	 * ExtentReports extent = new ExtentReports(); extent.attachReporter(email);
	 * 
	 * // extent.attachReporter(htmlReporter);
	 * 
	 * // Add configuration settings if needed
	 * 
	 * // Add test information to the report (you need to adapt this based on your
	 * test // execution logic)
	 * 
	 * // Flush the report to generate the HTML file extent.flush();
	 * sendEmailWithAttachment("target/extentReport/AutomationReports.html"); }
	 * 
	 * public static void sendEmailWithAttachment(String filename) { final String
	 * username = "testercoterie@gmail.com"; // Replace with your email final String
	 * password = "DigiNext@123"; // Replace with your password
	 * 
	 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
	 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.smtp.host",
	 * "smtp.gmail.com"); // Replace with your SMTP host props.put("mail.smtp.port",
	 * "587"); // Replace with your port
	 * 
	 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	 * protected PasswordAuthentication getPasswordAuthentication() { return new
	 * PasswordAuthentication(username, password); } });
	 * 
	 * try { Message message = new MimeMessage(session); message.setFrom(new
	 * InternetAddress(username)); // Set sender's email
	 * 
	 * message.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse("parthasarathi.anbalagan@gmail.com")); // Set //
	 * recipient's // email message.setSubject("Test Automation Execution Report");
	 * // Email subject
	 * 
	 * // Create the email body BodyPart messageBodyPart = new MimeBodyPart();
	 * messageBodyPart.
	 * setText("Please find attached the automation test execution report.");
	 * 
	 * // Create the attachment MimeBodyPart attachmentPart = new MimeBodyPart();
	 * filename = "target/extentReport/AutomationReports.html"; // Replace with the
	 * actual path to your Extent Report DataSource source = new
	 * FileDataSource(filename); attachmentPart.setDataHandler(new
	 * DataHandler(source)); attachmentPart.setFileName(filename);
	 * 
	 * // Multipart for the email Multipart multipart = new MimeMultipart();
	 * multipart.addBodyPart(messageBodyPart);
	 * multipart.addBodyPart(attachmentPart);
	 * 
	 * message.setContent(multipart);
	 * 
	 * // Send the message Transport.send(message);
	 * 
	 * System.out.println("Email sent successfully!");
	 * 
	 * } catch (MessagingException e) { e.printStackTrace();
	 * 
	 * } }
	 */
}
