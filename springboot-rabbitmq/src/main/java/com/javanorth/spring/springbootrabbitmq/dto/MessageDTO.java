package com.javanorth.spring.springbootrabbitmq.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO {
    private String msgId;
    private String msg;
    private String date;

}
