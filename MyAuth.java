import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

class MyAuth extends javax.mail.Authenticator
{
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication("ramunoida123@gmail.com","noida123");
	}
}