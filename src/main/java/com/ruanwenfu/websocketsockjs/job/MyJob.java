package com.ruanwenfu.websocketsockjs.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class MyJob {
    // 通过一个模板类进行消息的发送
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // 通过定时器定时服务器端定时向某个topic推送消息
    // 因为是基于STOMP协议，所以不是直接用webSocket发消息，而是通过应用层协议往浏览器发送消息
    @Scheduled(fixedRate = 1000)
    public void sendMessage(){
        // 执行需要发送的目的topic
        // 所有订阅该 主题的客户端都可以收到消息
        // 至于主题上的消息如何被发往客户端,就是通过WebSocket的EndPoint sendText方法发过去的
        simpMessagingTemplate.convertAndSend("/server/sendMessageByServer",System.currentTimeMillis());
    }

    // 给指定的用户发送消息
    @Scheduled(fixedRate = 1000)
    public void sendMessageToUser(){
        // 其实也是发往主题，不过不同用户订阅的主题应该不一样
        // 难点在于如何确定某个用户
        // 推送给单个用户的主题默认时/user开头
        // 注意，注意，注意，这里不要user，因为convertAndSendToUser方法会自动拼接目标topic为 /user/${user}/url
        simpMessagingTemplate.convertAndSendToUser("1","/sendMessageByServer",System.currentTimeMillis());
    }
}
