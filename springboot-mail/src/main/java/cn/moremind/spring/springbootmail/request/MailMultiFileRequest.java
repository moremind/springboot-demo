package cn.moremind.spring.springbootmail.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailMultiFileRequest {

    /**
     * mail to
     */
    private String[] toAddress;

    /**
     * subject of mail
     */
    private String subject;

    /**
     * content of mail
     */
    private String content;

    /**
     * attach of mail
     */
    private String[] filePath;
}
