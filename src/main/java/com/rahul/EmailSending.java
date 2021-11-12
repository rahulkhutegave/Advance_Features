package com.rahul;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSending {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");

		String subject = "Appointment Confirmation";
//		String message = "Hi!! your appointment is confirmated please be on time!";
		String to = "toemail@gmail.com";
		String from = "fromemail@gmail.com";

//		sendEmail(message, subject, to, from);
		sendEmail(subject, to, from);

		sendAttachmentEmail(subject, to, from);

	}

//send Email with Attachement
	private static void sendAttachmentEmail(String subject, String to, String from) throws Exception {

		Properties properties = System.getProperties();

		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("fromemail@gmail.com", "password**");
			}
		};

		Session session = Session.getInstance(properties, authenticator);

		MimeMessage mimeMsg = new MimeMessage(session);

		mimeMsg.setFrom(from);

		mimeMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		mimeMsg.setSubject(subject);

		String readTextFile = readTextFile();

		String qualifiedName = "/Advance_Features/Company_Logo.png";

		MimeMultipart mimeMultiPart = new MimeMultipart();

		MimeBodyPart fileMime = new MimeBodyPart();
		MimeBodyPart msgMime = new MimeBodyPart();

		msgMime.setText(readTextFile);

		File file = new File(qualifiedName);
		fileMime.attachFile(file);

		mimeMultiPart.addBodyPart(msgMime);
		mimeMultiPart.addBodyPart(fileMime);

		mimeMsg.setContent(mimeMultiPart);

		Transport.send(mimeMsg);

	}

	// Send Email Method
	private static void sendEmail(String subject, String to, String from) throws Exception {

		Properties properties = System.getProperties();

		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("fromemail@gmail.com", "password**");
			}
		};

		Session session = Session.getInstance(properties, authenticator);

		MimeMessage mimeMsg = new MimeMessage(session);

		mimeMsg.setFrom(from);

		mimeMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		mimeMsg.setSubject(subject);

		String readTextFile = readTextFile();

		mimeMsg.setText(readTextFile);

		Transport.send(mimeMsg);

		System.out.println(">>>>>>>>>>Email Sent Successfully>>>>>>>>>>");

	}
//reading message(Email_Body) from text file
	private static String readTextFile() throws IOException {
		StringBuilder sb = new StringBuilder("");
		String mailBody = "";

		String fileName = "Email_Body";
		FileReader fr = new FileReader(fileName);

		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		fr.close();
		br.close();

		mailBody = sb.toString();

		return mailBody;

	}

}
