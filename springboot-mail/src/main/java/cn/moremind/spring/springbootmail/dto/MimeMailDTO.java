package cn.moremind.spring.springbootmail.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MimeMailDTO {

    private String[] toAddress;
    private String subject;
    private String content;
}
