package cn.moremind.spring.springbootmail.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailDetail {
    private String sender;
    private String receiver;
    private String subject;
    private String content;
}
