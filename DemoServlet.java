import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class DemoServlet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String to1=req.getParameter("To");
		System.out.println(to1);
		String subje=req.getParameter("Subject");
		System.out.println(subje);
		String msg1=req.getParameter("Message");
		System.out.println(msg1);
		String pass=req.getParameter("Pass");
		System.out.println(pass);
		String from1=req.getParameter("From");
		System.out.println(from1);
		//char ch1 = (char)92;
		//System.out.println(ch1);
		//char ch2='ch1'+'ch1';
		//System.out.println(ch2);
		String file = req.getParameter("File");
		//String path11 = file.replace("\\" , "\\\\");
		System.out.println(file);
		//File f11 = new File(path11);
		//System.out.println(f11.getAbsolutePath());
		Properties props=new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port","465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.port","465");
		
		try
		{
			Session session=Session.getInstance(props,new MyAuth());
			//Session session=Session.getInstance(props);
			
			//Composing Message
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress("ramunoida123@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to1));
			message.setSubject(subje);
			Multipart body=new MimeMultipart();
			MimeBodyPart part1=new MimeBodyPart();
			part1.setText(msg1);
			MimeBodyPart part2=new MimeBodyPart();
			//String path1 ="E:\\servlet sat batch\\Proper Working Servlet e-mail Send wala\\LoadOnStartUp1\\Deepak.text";
			File f = new File(file);
			System.out.println("msg1"+f.getAbsolutePath());
			System.out.println("msg2"+f.exists());
			FileDataSource fds=new FileDataSource(f);
			part2.setDataHandler(new DataHandler(fds));
			part2.setFileName(fds.getName());
			//part2.setText(msg1);
			body.addBodyPart(part2);
			body.addBodyPart(part1);
			
			message.setContent(body);
			Transport.send(message);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		out.println("<html><body>");
		out.println("<br>");
		out.println("Mail Sent Via Servlet.........!!!");
		out.println("</body></html>");
	}
}
