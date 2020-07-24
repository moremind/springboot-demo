package com.javanorth.spring.springbootmail.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailFileRequest {

    private String[] toAddress;
    private String subject;
    private String content;
    private String filePath;
}
