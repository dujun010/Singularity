package qid.email;

import javax.mail.Authenticator;  
import javax.mail.PasswordAuthentication;  
 
// 普通的通过构造函数传入身份验证信息的 Authenticator 子类  
/**
 * 邮件身份验证用户名密码信息类
 * 
 * @author tanjixiang
 * @version 1.0, 2013-11-01
 * @since et 1.0
 */
class MailAuthenticator extends Authenticator {  
    private String user;  
    private String pwd;  
 
    public MailAuthenticator(String user, String pwd) {  
        this.user = user;  
        this.pwd = pwd;  
    }  
 
    @Override 
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pwd);
    }  
} 

