/*
 * 文件名：MailSender.java <br>
 * 版权：Copyright 2010-2015 nancal Tech.Co.Ltd.All
 * Rights Reserved.<br>
 * 描述：邮件发送
 */
package qid.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 发送邮件方法类
 * 
 * @author 杜军
 * @version 1.0, 2013-7-26
 * @since et 1.0
 */
public class MailSender {
	/**
     * 发送邮件<p>
     * 无需指定服务器地址和服务器端口号<p>
     * 
     * @author 杜军
     * 
     * @param fromMail
     *            发件人邮箱
     * @param username
     *            发件人名字
     * @param password
     *            发件人密码
     * @param toMail
     *            收件人邮箱
     * @param htmlSubject
     *            主题
     * @param htmlcontent
     *            内容
     * @param filename
     *            附件名称
     * @param attacheSubject
     *            附件主题
     * @param bccMails
     *            隐藏抄送
     * @since et 1.0
     */
	public static void sendMail(String fromMail,
            String username, String password, String[] toMail,
            String htmlSubject, String htmlcontent,String[] filename,
            String[] attacheSubject, final String bccMails[]) {
		
		String smtpHost="";
		String smtpPort="";
		String mark = fromMail.substring(fromMail.indexOf("@"),fromMail.length());
		if(mark.equals("@126.com")){//126邮箱
			smtpHost = "smtp.126.com";
			smtpPort = "465";
		}else if(mark.equals("@163.com")){//163邮箱
			smtpHost = "smtp.163.com";
			smtpPort = "465";
		}else if(mark.equals("@qq.com")){//qq企业邮箱
			smtpHost = "smtp.exmail.qq.com";
			smtpPort = "587";
		}else if(mark.equals("@yahoo.com")){//yahoo邮箱
			smtpHost = "smtp.mail.yahoo.com";
			smtpPort = "465";
		}else if(mark.equals("@263.net.cn")){//263邮箱
			smtpHost = "smtp.263.net.cn";
			smtpPort = "465";
		}else{//其他邮箱
			smtpHost = "smtp."+mark.substring(mark.indexOf("@")+1,mark.length());
			smtpPort = "465";
		}
        sendMail(smtpHost, smtpPort, fromMail, username, password, toMail, htmlSubject,htmlcontent, null, null, null);
    }
	
	
    /**
     * 发送邮件<p>
     * 		需要设置服务器地址和端口号<p>
     * 		不能发送附件<p>
     * 		不能抄送<p>
     * @author 杜军
     * 
     * @param smtpHost
     *            服务器
     * @param smtpPort
     *            端口
     * @param fromMail
     *            发件人邮箱
     * @param username
     *            发件人名字
     * @param password
     *            发件人密码
     * @param toMail
     *            收件人邮箱
     * @param htmlSubject
     *            主题
     * @param htmlcontent
     *            内容
     * @since et 1.0
     */
    public static void sendMail(String smtpHost, String smtpPort, String fromMail,
            String username, String password, String[] toMail,
            String htmlSubject, String htmlcontent) {
        sendMail(smtpHost, smtpPort, fromMail, username, password, toMail, htmlSubject,
                htmlcontent, null, null, null);
    }

    /**
     * 发送邮件
     * 
     * @param smtpHost
     *            服务器
     * @param smtpPort
     *            端口
     * @param fromMail
     *            发件人邮箱
     * @param username
     *            发件人名字
     * @param password
     *            发件人密码
     * @param toMail
     *            收件人邮箱
     * @param htmlSubject
     *            主题
     * @param htmlcontent
     *            内容
     * @param filename
     *            附件名称
     * @param attacheSubject
     *            附件主题
     * @param bccMails
     *            隐藏抄送
     * @return String  返回success代表成功，其他均为错误信息           
     */
    public static String sendMail(String smtpHost, String smtpPort, String fromMail,
            String username, String password, String[] toMail,
            String htmlSubject, String htmlcontent, String[] filename,
            String[] attacheSubject, final String bccMails[]) {
        Properties props = new Properties();
        // FIXME 参数校验
        // 初始化参数
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        
        //启用SSL
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY); //使用JSSE的SSL socketfactory来取代默认的socketfactory
		props.put("mail.smtp.socketFactory.fallback", "false");//只处理SSL的连接, 对于非SSL的连接不做处理
		props.put("mail.smtp.socketFactory.port", smtpPort);
        
        
        // 创建密码验证器
        //Authenticator authenticator = new MailAuthenticator(username, password);
        // 获取基于Properties Session对象

        Session session = Session.getInstance(props, null);
        session.setDebug(false);

        // 根据Session创建一个邮件消息
        MimeMessage mimeMsg = new MimeMessage(session);
        // 创建邮件发送者地址
        try {
            // 创建邮件发送者地址
            Address fromAdd = new InternetAddress(fromMail, username);
            // Address fromAdd = new InternetAddress(fromMail);
            mimeMsg.setFrom(fromAdd);
            // 创建邮件的接收者地址，并设置到邮件消息中
            //Address toAdd = new InternetAddress(toMail);
            //mimeMsg.setRecipient(Message.RecipientType.TO, toAdd);
            if (toMail != null && toMail.length > 0) {
                for (int i = 0; i < toMail.length; i++) {
                    mimeMsg.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(toMail[i]));
                }
            }
            
            // 有抄送
            // FIXME 验证抄送地址
            if (bccMails != null && bccMails.length > 0) {
                for (int i = 0; i < bccMails.length; i++) {
                    mimeMsg.addRecipient(Message.RecipientType.BCC,
                            new InternetAddress(bccMails[i]));
                }
            }

            // 设置邮件消息发送的时间
            mimeMsg.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            mainPart.addBodyPart(html);

            // 添加附件
            if (filename != null && filename.length > 0) {
                for (int i = 0; i < filename.length; i++) {
                    BodyPart bp = new MimeBodyPart();
                    FileDataSource fileds = new FileDataSource(filename[i]);
                    bp.setDataHandler(new DataHandler(fileds));

                    bp.setFileName(MimeUtility.encodeText(attacheSubject[i]));
                    bp.setHeader("content-id", filename[i]);
                    mainPart.addBodyPart(bp);
                }
            }
            mimeMsg.setContent(mainPart);

            // 设置HTML内容
            html.setContent(htmlcontent, "text/html; charset=utf-8");
            // html.setContent(body, "text/html");
            mimeMsg.setContent(mainPart);
            // mimeMsg.setSubject(MimeUtility.encodeText(htmlSubject));
            mimeMsg.setSubject(htmlSubject);
            // 发送邮件
            Transport trans = session.getTransport("smtp");
            trans.connect(smtpHost, fromMail, password);
            trans.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
            System.out.println("发送邮件成功");
            trans.close();
            return "success";
        } catch (AddressException e) {
            return e.getClass().getName()+" -- "+e.getMessage();
        } catch (MessagingException e) {
        	e.printStackTrace();
            return e.getClass().getName()+" -- "+e.getMessage();
        } catch (UnsupportedEncodingException e) {
            return e.getClass().getName()+" -- "+e.getMessage();
        } catch (Exception e) {
        	e.printStackTrace();
            return e.getClass().getName()+" -- "+e.getMessage();
        }
    }

    /**
     * 测试
     * 
     * @author 杜军
     * @param args
     * @since et 1.0
     */
    public static void main(String[] args) {
//        String[] filename = { "c:/test.xls", "c:/test2.xls" };
//        String[] AttacheSubject = { "test.xls",
//                "test2.xls" };
//        String sResult = .sendMail(
////                        "smtp.qq.com",//服务器地址
////                        "25",//服务器端口号
//                        "767855643@qq.com",//发件人邮箱
//                        "测试发件人姓名",//发件人名字
//                        "wodeqq1991",//发件人密码
//                        new String[]{"dujun_dj@126.com"},//收件人邮箱
//                        "测试html",//主题
//                        "<table border='1' bgColor='red'><tr><td>发送一个表格</td></tr></table>",//内容
//                        null, //附件名称
//                        null, //附件主题
//                        null  //隐藏抄送
//                        );
//        System.out.println("------------sResult:"+sResult);
    	MailSender.sendMail("dujun_dj@126.com", "测试发件人姓名", "13811300060", new String[]{"767855643@qq.com"}, "测试html", "<table border='1' bgColor='red'><tr><td>发送一个表格</td></tr></table>", null, null, null);
    }
}
