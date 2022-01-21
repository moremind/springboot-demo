package cn.moremind.spring.springbootrabbitmq.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageRequestDTO {
    private String msg;
}
