package com.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Branch;
import com.dto.Customer;
import com.dto.Item;
import com.itextpdf.html2pdf.HtmlConverter;

@WebServlet("/mail")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer customerObject = (Customer) request.getSession().getAttribute("customer");
        Branch branch = (Branch) request.getSession().getAttribute("branch");
        List<Item> orderItems = (List<Item>) request.getSession().getAttribute("orderitems");
        double totalPayment = (Double) request.getSession().getAttribute("pay");

        // Create the PDF invoice content
        String pdfContent = generatePdfContent(customerObject, branch, orderItems, totalPayment);
        byte[] pdfBytes = createPdf(pdfContent);

        // Send the email with the PDF attachment
        sendEmailWithAttachment(customerObject.getEmail(), pdfBytes);
        
        request.getRequestDispatcher("customerLast.jsp").include(request, response);
    }

    private String generatePdfContent(Customer customer, Branch branch, List<Item> items, double total) {
        StringBuilder content = new StringBuilder();
        content.append("<html><head>")
               .append("<style>")
               .append("body { font-family: Arial, sans-serif; color: #333; }")
               .append(".container { width: 100%; max-width: 800px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; }")
               .append(".header { background-color: #333; color: white; padding: 20px; text-align: left; border-radius: 10px 10px 0 0; display: flex; align-items: center; }")
               .append(".header img { width: 50px; margin-right: 20px; }")
               .append(".header h1 { margin: 0; font-size: 24px; }")
               .append(".branch-info { margin: 20px 0; text-align: right; font-size: 14px; color: #666; }")
               .append(".invoice-info { display: flex; justify-content: space-between; margin-top: 20px; }")
               .append(".invoice-info div { margin: 10px 0; }")
               .append(".invoice-info h2, .invoice-info p { margin: 0; font-size: 16px; }")
               .append(".table { width: 100%; margin-top: 20px; border-collapse: collapse; }")
               .append(".table th, .table td { padding: 10px; border: 1px solid #ddd; text-align: left; }")
               .append(".table th { background-color: #f5f5f5; font-weight: bold; }")
               .append(".totals { display: flex; justify-content: flex-end; margin-top: 20px; }")
               .append(".totals div { text-align: right; margin-right: 20px; }")
               .append(".totals h3, .totals p { margin: 0; }")
               .append(".footer { text-align: center; margin-top: 20px; font-size: 14px; color: #888; }")
               .append("</style></head><body>")

               .append("<div class='container'>")
               .append("<div class='header'><img src='https://example.com/logo.png' alt='Logo'><h1>Crave Courier</h1></div>")
               .append("<div class='branch-info'>")
               .append("<p><strong>").append(branch.getBranchname()).append("</strong><br>")
               .append(branch.getAddress()).append("<br>")
               .append("Contact: ").append(branch.getPhonenumber()).append("</p>")
               .append("</div>")
               .append("<div class='invoice-info'>")
               .append("<div><h2>Invoice To:</h2><p>")
               .append(customer.getName()).append("<br>")
               .append(customer.getEmail()).append("<br>+1 234 567 889</p></div>")
               .append("<div><h2>Invoice Details:</h2><p>Invoice #: #D56789<br>Date: ")
               .append(LocalDate.now()).append("</p></div>")
               .append("</div>")
               .append("<table class='table'>")
               .append("<tr><th>S.No.</th><th>Description</th><th>Price</th><th>Qty</th><th>Total</th></tr>");

        int serialNo = 1;
        for (Item item : items) {
            double totalPrice = item.getPrice() * item.getQuantity();
            content.append("<tr>")
                   .append("<td>").append(serialNo++).append("</td>")
                   .append("<td>").append(item.getProductName()).append("</td>")
                   .append("<td>Rs. ").append(item.getPrice()).append("</td>")
                   .append("<td>").append(item.getQuantity()).append("</td>")
                   .append("<td>Rs. ").append(totalPrice).append("</td>")
                   .append("</tr>");
        }

        content.append("</table>")
               .append("<div class='totals'>")
               .append("<div><p>Grand Total: Rs. ").append(total).append("</p></div>")
               .append("</div>")
               .append("<div class='footer'>")
               .append("<p>Thank you for choosing to dine with us. Visit Again &#128591;</p>")
               .append("</div>")
               .append("</div></body></html>");

        return content.toString();
    }

    private byte[] createPdf(String htmlContent) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, outputStream);
        return outputStream.toByteArray();
    }

    private void sendEmailWithAttachment(String recipientEmail, byte[] pdfBytes) {
    	String from = "sroy32067@gmail.com"; // Replace with your email
		String password = "qdgcyocrpmikudet";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Thank You for Your Order - Invoice Attached");

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Thank you for your order! Please find the attached invoice.");

            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            DataSource pdfDataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
            pdfBodyPart.setDataHandler(new DataHandler(pdfDataSource));
            pdfBodyPart.setFileName("Invoice.pdf");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(pdfBodyPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}