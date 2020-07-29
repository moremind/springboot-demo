package com.javanorth.spring.springbootmail.service;

import com.javanorth.spring.springbootmail.dto.MimeFileMailDTO;
import com.javanorth.spring.springbootmail.dto.MimeMailDTO;
import com.javanorth.spring.springbootmail.dto.MimeMultiFileMailDTO;
import com.javanorth.spring.springbootmail.dto.SimpleMailDTO;

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
