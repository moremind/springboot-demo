package com.javanorth.spring.springbootmail.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleMailDTO {

    private String[] toAddress;
    private String subject;
    private String content;
}
