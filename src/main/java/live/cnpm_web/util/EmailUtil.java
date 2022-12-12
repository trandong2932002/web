package live.cnpm_web.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class EmailUtil {
    private static String username = "";

    public static int sendEmail(String email_dist) {
        int code = (int) (((Math.random()) * 10000) + 1);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                String password = "";
                try {
                    File file = new File("/META-INF/email");
                    Scanner scanner = new Scanner(file);
                    username = scanner.nextLine();
                    password = scanner.nextLine();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        try {
            Message mgs = new MimeMessage(session);
            mgs.setFrom(new InternetAddress(username));
            mgs.addRecipient(Message.RecipientType.TO, new InternetAddress(email_dist));
            mgs.setSubject("Test send mail");
            mgs.setText("Your code verify is " + code);
            Transport.send(mgs);
            System.out.println("Gui gmail r a");
            System.out.println(code);
        } catch (AddressException e) {
            System.out.println("Truyen kh dc 1");
        } catch (MessagingException e) {
            System.out.println(e);
            System.out.println("truyen kh dc 2");
        }
        return code;
    }

    public static void sendEmail(String email_dist, String code) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("20133034@student.hcmute.edu.vn", "cctnak16032002");
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        try {
            Message mgs = new MimeMessage(session);
            mgs.setFrom(new InternetAddress("20133034@student.hcmute.edu.vn"));
            mgs.addRecipient(Message.RecipientType.TO, new InternetAddress(email_dist));
            mgs.setSubject("Test send mail");
            mgs.setText("Your code verify is " + code);
            Transport.send(mgs);
            System.out.println("Gui gmail r a");
            System.out.println(code);
        } catch (AddressException e) {
            System.out.println("Truyen kh dc 1");
        } catch (MessagingException e) {
            System.out.println(e);
            System.out.println("truyen kh dc 2");
        }

        System.out.println(code);
    }
}
