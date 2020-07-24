package com.javanorth.spring.springbootmail.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequest {

    private String[] toAddress;
    private String subject;
    private String content;
}
