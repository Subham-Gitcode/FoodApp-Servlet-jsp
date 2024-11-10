package com.controller.staff;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Branch;
import com.dto.Customer;

@WebServlet("/staffmail")
public class StaffmailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		Customer customerObject = (Customer) request.getSession().getAttribute("custo");
		System.out.println(customerObject.getEmail());
		Branch b = (Branch) request.getSession().getAttribute("branch");

		String content = " <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            display: flex;\r\n"
				+ "            justify-content: center;\r\n"
				+ "            align-items: center;\r\n"
				+ "            height: 100vh;\r\n"
				+ "            margin: 0;\r\n"
				+ "            background-color: #f8f9fa;\r\n"
				+ "        }\r\n"
				+ "        .banner {\r\n"
				+ "            background-color: #4CAF50; /* Green background */\r\n"
				+ "            color: white; /* White text */\r\n"
				+ "            padding: 20px 40px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);\r\n"
				+ "            text-align: center;\r\n"
				+ "            font-size: 24px;\r\n"
				+ "        }\r\n"
				+ "        .banner h1 {\r\n"
				+ "            margin: 0;\r\n"
				+ "            font-size: 2.5em;\r\n"
				+ "        }\r\n"
				+ "        .banner p {\r\n"
				+ "            margin: 10px 0 0;\r\n"
				+ "            font-size: 1.2em;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "    <div class=\"banner\">\r\n"
				+ " <h1>Woohoo!</h1>\r\n"
				+"Hey "+customerObject.getName()+", your order has been successfully delivered."
				+ " We hope you like it! Don’t forget to give us your feedback \n"
				+ "Visit again "+b.getBranchname()
				+ "</body>";

		String name = customerObject.getName();
		long mob =customerObject.getMob();
		String from = "sroy32067@gmail.com"; // Replace with your email
		String password = "qdgcyocrpmikudet"; // Replace with your email password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		//

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerObject.getEmail()));
			message.setSubject("THANKU");
			message.setContent(content, "text/html");//
			Transport.send(message);
			request.getRequestDispatcher("userSignup.jsp").include(request, response);

		} catch (MessagingException e) {
			e.printStackTrace();
			response.getWriter().write("Failed to send email: " + e.getMessage());
		}
	
	
	
	
	}

}
