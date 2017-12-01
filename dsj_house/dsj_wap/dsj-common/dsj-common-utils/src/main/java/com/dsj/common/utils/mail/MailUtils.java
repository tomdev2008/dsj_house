package com.dsj.common.utils.mail;

import java.util.List;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 发送邮件Util
 */
@Component
public class MailUtils {
    
	private final Logger logger =Logger.getLogger(this.getClass());
	
    //邮箱
	//@Value("${mail.smtp.host}")
    private String mailServerHost;
	
	//@Value("${mail.smtp.from}")
    private String mailSenderAddress;
    
	//@Value("${mail.smtp.from.nick}")
	private String mailSenderNick;
    
	//@Value("${mail.sender.username}")
	private String mailSenderUsername;
    
	//@Value("${mail.sender.password}")
	private String mailSenderPassword;
    
    /**
     * 发送 邮件方法 (Html格式，支持附件)
     * 
     * @return void
     */
    public void sendEmail(MailInfo mailInfo) {
         
        try {
            HtmlEmail email = new HtmlEmail();
            // 配置信息
            email.setHostName(mailServerHost);
            email.setFrom(mailSenderAddress,mailSenderNick);
            email.setAuthentication(mailSenderUsername,mailSenderPassword);
            email.setCharset("UTF-8");
            email.setSubject(mailInfo.getSubject());
            email.setHtmlMsg(mailInfo.getContent());

            // 添加附件
            List<EmailAttachment> attachments = mailInfo.getAttachments();
            if (null != attachments && attachments.size() > 0) {
                for (int i = 0; i < attachments.size(); i++) {
                    email.attach(attachments.get(i));
                }
            }
            
            // 收件人
            List<String> toAddress = mailInfo.getToAddress();
            if (null != toAddress && toAddress.size() > 0) {
                for (int i = 0; i < toAddress.size(); i++) {
                        email.addTo(toAddress.get(i));
                }
            }
            // 抄送人
            List<String> ccAddress = mailInfo.getCcAddress();
            if (null != ccAddress && ccAddress.size() > 0) {
                for (int i = 0; i < ccAddress.size(); i++) {
                        email.addCc(ccAddress.get(i));
                }
            }
            //邮件模板 密送人
            List<String> bccAddress = mailInfo.getBccAddress();
            if (null != bccAddress && bccAddress.size() > 0) {
                for (int i = 0; i < bccAddress.size(); i++) {
                    email.addBcc(ccAddress.get(i));
                }
            }
            email.send();
            logger.info("邮件发送成功！");
        } catch (EmailException e) {
            e.printStackTrace();
        } 

    }
}