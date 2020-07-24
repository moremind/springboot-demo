package com.javanorth.spring.springbootmail.service.imp;

import com.javanorth.spring.springbootmail.dto.MimeFileMailDTO;
import com.javanorth.spring.springbootmail.dto.MimeMailDTO;
import com.javanorth.spring.springbootmail.dto.SimpleMailDTO;
import com.javanorth.spring.springbootmail.exception.ExceptionEnum;
import com.javanorth.spring.springbootmail.exception.SendMailException;
import com.javanorth.spring.springbootmail.service.MailService;
import com.javanorth.spring.springbootmail.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Value("${mail.fromAddress}")
    private String fromAddress;

    /**
     * 发送简单文本文件
     * @param simpleMailDTO
     */
    @Override
    public void sendSimpleMail(SimpleMailDTO simpleMailDTO) {
        LogUtil.info(MailServiceImpl.class, "from: {}, mail info: {}", fromAddress, simpleMailDTO.toString());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        simpleMailMessage.setTo(simpleMailDTO.getToAddress());
        simpleMailMessage.setSubject(simpleMailDTO.getSubject());
        simpleMailMessage.setText(simpleMailDTO.getContent());
        try {
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            LogUtil.error(MailServiceImpl.class, "send mail exception: {}", e.getMessage());
            throw new SendMailException(ExceptionEnum.SEND_MAIL_EXCEPTION.getRetCode(),
                    ExceptionEnum.SEND_MAIL_EXCEPTION.getMessage());
        }
    }

    /**
     * 发送html格式的邮件
     * @param mimeMailDTO
     */
    @Override
    public void sendMimeMail(MimeMailDTO mimeMailDTO) {
        LogUtil.info(MailServiceImpl.class, "from: {}, mail info: {}", fromAddress, mimeMailDTO.toString());
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(fromAddress);
            mimeMessageHelper.setTo(mimeMailDTO.getToAddress());
            mimeMessageHelper.setSubject(mimeMailDTO.getSubject());
            mimeMessageHelper.setText(mimeMailDTO.getContent(), true);
            mailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMimeMailWithFile(MimeFileMailDTO mimeFileMailDTO) {
        LogUtil.info(MailServiceImpl.class, "from: {}, file-mail info: {}", fromAddress, mimeFileMailDTO.toString());
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(fromAddress);
            mimeMessageHelper.setTo(mimeFileMailDTO.getToAddress());
            mimeMessageHelper.setSubject(mimeFileMailDTO.getSubject());
            mimeMessageHelper.setText(mimeFileMailDTO.getContent(), true);

            LogUtil.info(MailServiceImpl.class, "file path: {}", mimeFileMailDTO.getFilePath());
            File file = new File(mimeFileMailDTO.getFilePath());
//            FileSystemResource resource = new FileSystemResource(file);
            LogUtil.info(MailServiceImpl.class, "file path: {}", mimeFileMailDTO.getFilePath());
            String fileName = mimeFileMailDTO.getFilePath().substring(mimeFileMailDTO.getFilePath().lastIndexOf(File.separator));
            mimeMessageHelper.addAttachment(fileName, file);
            mailSender.send(mimeMailMessage);

            LogUtil.info(MailServiceImpl.class, "send mail success");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
