package com.xxin.demo.mail;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.soap.MimeHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    TemplateEngine templateEngine;

    @RequestMapping("sendemail")
    public Object sendEmail() throws MessagingException {
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom("1208668915@qq.com");
        message.setTo("1032130941@qq.com");
        message.setSubject("测试邮件主题");
        message.setText("测试邮件内容");
        this.mailSender.send(mimeMessage);
        return null;
    }
    @RequestMapping("/simple")
    public String  sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1208668915@qq.com");
        message.setTo("1032130941@qq.com");
        message.setSubject("SimpleMailMessage");
        message.setText("简单邮件");
        this.mailSender.send(message);
        return "发送成功";
    }
    @RequestMapping("/html")
    public String sendHtmlMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String html =
                "<html>" +
                "<body><h1>" +
                    "HTML 邮件" +
                "</h1></body>"+
                "</html>";
        helper.setFrom("1208668915@qq.com");
        helper.setTo("1032130941@qq.com");
        helper.setSubject("HtmlMailMessage");
        helper.setText(html,true);
        this.mailSender.send(mimeMessage);
        return "发送成功";
    }
    @RequestMapping("/attachment")
    public String sendAttachmentsMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("1208668915@qq.com");
        helper.setTo("1032130941@qq.com");
        helper.setSubject("AttachmentMailMessage");
        helper.setText("带附件");
        FileSystemResource resource = new FileSystemResource(new File("D:\\SSR.zip"));
        String fileName = resource.getFilename();
        helper.addAttachment(fileName,resource);
        this.mailSender.send(mimeMessage);
        return "发送成功";
    }
    @RequestMapping("/resource")
    public String sendResourceMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        String html =
                "<html><head></head><body>" +
                        "<h1>hello!!spring image html mail</h1>"+
                        "<a href=http://www.baidu.com>baidu</a>"	+
                        "<img src=cid:image/>" +
                        "</body></html>";
        helper.setFrom("1208668915@qq.com");
        helper.setTo("1032130941@qq.com");
        helper.setSubject("ResourceMailMessage");
        helper.setText(html,true);
        FileSystemResource resource = new FileSystemResource(new File("C:\\Users\\陈乙鑫\\Desktop\\bgp\\1.jpg"));
        helper.addInline("image",resource);
        this.mailSender.send(mimeMessage);
        return "发送成功";
    }
    @RequestMapping("/template")
    public String sendTemplateMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("id", "123");
        String content = templateEngine.process("/mail/inform",context);
        helper.setFrom("1208668915@qq.com");
        helper.setTo("1032130941@qq.com");
        helper.setSubject("SimpleMailMessage");
        helper.setText(content,true);
        this.mailSender.send(mimeMessage);
        return "发送成功";
    }
}
