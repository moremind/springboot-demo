package cn.moremind.spring.springbootmail.service;

import cn.moremind.spring.springbootmail.dto.MimeFileMailDTO;
import cn.moremind.spring.springbootmail.dto.MimeMailDTO;
import cn.moremind.spring.springbootmail.dto.MimeMultiFileMailDTO;
import cn.moremind.spring.springbootmail.dto.SimpleMailDTO;

public interface MailService {
    /**
     * 发送简单文本邮件
     * @param simpleMailDTO
     */
    void sendSimpleMail(SimpleMailDTO simpleMailDTO);

    /**
     * 发送html格式的邮件
     * @param mimeMailDTO
     */
    void sendMimeMail(MimeMailDTO mimeMailDTO);

    /**
     * 发送附带附件的文本文件
     * @param mimeFileMailDTO
     */
    void sendMimeMailWithSimpleFile(MimeFileMailDTO mimeFileMailDTO);


    /**
     * send mail with multi files
     * @param mimeMultiFileMailDTO
     */
    void sendMimeMultiFileMail(MimeMultiFileMailDTO mimeMultiFileMailDTO);
}
