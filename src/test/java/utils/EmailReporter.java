package utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class EmailReporter {

    public static void execute(ArrayList<String> recipients,
                               String subjectMessage,
                               String content,
                               ArrayList<File> screenFiles) throws Exception {

        ArrayList<File> fileList = screenFiles;
        String[] to= new String[recipients.size()];
                to = recipients.toArray(to);

        EmailReporter.sendMail(
                "testobjectsdream@gmail.com",
                ";jkelm01@",
                "smtp.gmail.com",
                "465",
                "false",
                "true",
                false,
                "javax.net.ssl.SSLSocketFactory",
                "false",
                to,
                subjectMessage,
                content,
                fileList
        );
    }

    public static boolean sendMail(
                                   String userName,
                                   String passWord,
                                   String host,
                                   String port,
                                   String starttls,
                                   String auth,
                                   boolean debug,
                                   String socketFactoryClass,
                                   String fallback,
                                   String[] to,
                                   String subject,
                                   String text,
                                   ArrayList<File> fileList) {

//Object Instantiation of a properties file.
        Properties props = new Properties();
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.host", host);
        if (!"".equals(port)) {
            props.put("mail.smtp.port", port);
        }
        if (!"".equals(starttls)) {
            props.put("mail.smtp.starttls.enable", starttls);
            props.put("mail.smtp.auth", auth);
        }
        if (debug) {
            props.put("mail.smtp.debug", "true");
        } else {
            props.put("mail.smtp.debug", "false");
        }
        props.put("mail.smtp.protocol", "smtp");
        if (!"".equals(port)) {
            props.put("mail.smtp.socketFactory.port", port);
        }
        if (!"".equals(socketFactoryClass)) {
            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        if (!"".equals(fallback)) {
            props.put("mail.smtp.socketFactory.fallback", fallback);
        }
        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(debug);
            MimeMessage msg = new MimeMessage(session);

            if (subject == "" || subject == null) {
                subject = "WARNING!!! Subject text was not found.";
            }

            if (text == "" || text == null) {
                text = "WARNING!!! Message text was not found.";
            }

            if (fileList == null) {
                text = text + "\n WARNING!!! Attachment files was not found.";
            }
            msg.setSubject("AUTOMATED TEST RESULTS[ " + subject + "]");

            String[] list = text.split("\n");
            text = "";
            for (int i = 0; i < list.length; i++) {
                if (i < 4) {
                    String[] temp = list[i].split(":");
                    String tempResult = "";
                    for (int j = 1; j < temp.length; j++) {
                        tempResult = tempResult + temp[j];
                    }
                    text = text + "<strong>" + temp[0] + "</strong>" + ": " + tempResult + "<br>";
                } else {
                    text = text + list[i] + "<br>";
                }
            }

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text, "UTF-8", "html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            String pngFile = "";
            for (File file : fileList) {
                if (file.getAbsolutePath().endsWith(".png")) {
                    pngFile = file.getAbsolutePath();
                    break;
                }
            }

            if (!pngFile.equals("")) {
                String imageHtml = text + "<img src=\"cid:image\">";
                messageBodyPart.setContent(imageHtml, "text/html");
                messageBodyPart = new MimeBodyPart();
                DataSource fds = new FileDataSource(pngFile);

                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<image>");
                multipart.addBodyPart(messageBodyPart);
            }

            //for add all files to attachment
            if (fileList.size() == 0) {
                TestLogger.info("Can't find any files for sending e-mail");
            } else {
                for (int i = 0; i < fileList.size(); i++) {
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(fileList.get(i));
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(fileList.get(i).getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            msg.setContent(multipart);
            msg.setFrom(new InternetAddress(userName));

            for (int i = 0; i < to.length; i++) {
                msg.addRecipient(Message.RecipientType.TO, new
                        InternetAddress(to[i]));
            }
            msg.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, passWord);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            TestLogger.info("email sent");
            return true;
        } catch (Exception mex) {
            TestLogger.error("email not sent");
            mex.printStackTrace();
            return false;
        }
    }
}
