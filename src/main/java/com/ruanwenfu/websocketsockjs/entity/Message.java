package com.ruanwenfu.websocketsockjs.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String content;
    private String fromUserId;
    private String toUserId;
}
