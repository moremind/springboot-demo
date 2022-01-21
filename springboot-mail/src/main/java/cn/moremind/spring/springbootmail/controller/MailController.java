package cn.moremind.spring.springbootmail.controller;

import cn.moremind.spring.springbootmail.request.MailMultiFileRequest;
import cn.moremind.spring.springbootmail.request.MailRequest;
import cn.moremind.spring.springbootmail.response.ResponseUtil;
import cn.moremind.spring.springbootmail.service.MailService;
import cn.moremind.spring.springbootmail.dto.MimeFileMailDTO;
import cn.moremind.spring.springbootmail.dto.MimeMailDTO;
import cn.moremind.spring.springbootmail.dto.MimeMultiFileMailDTO;
import cn.moremind.spring.springbootmail.dto.SimpleMailDTO;
import cn.moremind.spring.springbootmail.request.MailFileRequest;
import cn.moremind.spring.springbootmail.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;

    /**
     * send simple text email
     * @param request http request
     * @param mailRequest post params
     * @return response
     */
    @PostMapping("/sendSimpleMail")
    public ResponseUtil sendMail(HttpServletRequest request, @RequestBody MailRequest mailRequest) {
        LogUtil.info(MailController.class, "request params: {}", mailRequest);
        SimpleMailDTO mailDTO = SimpleMailDTO.builder().toAddress(mailRequest.getToAddress())
                .subject(mailRequest.getSubject())
                .content(mailRequest.getContent())
                .build();
        mailService.sendSimpleMail(mailDTO);
        return ResponseUtil.success(request.getRequestURI());
    }

    /**
     * send html mail
     * @param request http request
     * @param mailRequest post params, { @See MailRequest }
     * @return
     */
    @PostMapping("/sendMimeMail")
    public ResponseUtil sendMimeMail(HttpServletRequest request, @RequestBody MailRequest mailRequest) {
        LogUtil.info(MailController.class, "request params: {}", mailRequest);
        MimeMailDTO mimeMailDTO = MimeMailDTO.builder().toAddress(mailRequest.getToAddress())
                .subject(mailRequest.getSubject())
                .content(mailRequest.getContent())
                .build();
        mailService.sendMimeMail(mimeMailDTO);
        return ResponseUtil.success(request.getRequestURI());
    }

    /**
     * send mail with single file
     * @param request
     * @param mailRequest
     * @return
     */
    @PostMapping("/sendMimeFileMail")
    public ResponseUtil sendMimeFileMail(HttpServletRequest request, @RequestBody MailFileRequest mailRequest) {
        LogUtil.info(MailController.class, "request params: {}", mailRequest);
        MimeFileMailDTO mimeFileMailDTO = MimeFileMailDTO.builder().toAddress(mailRequest.getToAddress())
                .subject(mailRequest.getSubject())
                .content(mailRequest.getContent())
                .filePath(mailRequest.getFilePath())
                .build();

        mailService.sendMimeMailWithSimpleFile(mimeFileMailDTO);
        return ResponseUtil.success(request.getRequestURI());
    }

    @PostMapping("/sendMimeMultiFileMail")
    public ResponseUtil sendMimeMultiFileMail(HttpServletRequest request, @RequestBody MailMultiFileRequest mailMultiFileRequest) {
        LogUtil.info(MailController.class, "request params: {}", mailMultiFileRequest);

        MimeMultiFileMailDTO mimeMultiFileMailDTO = MimeMultiFileMailDTO.builder()
                .toAddress(mailMultiFileRequest.getToAddress())
                .subject(mailMultiFileRequest.getSubject())
                .content(mailMultiFileRequest.getContent())
                .filePath(mailMultiFileRequest.getFilePath())
                .build();
        mailService.sendMimeMultiFileMail(mimeMultiFileMailDTO);
        return ResponseUtil.success(request.getRequestURI());
    }

}
