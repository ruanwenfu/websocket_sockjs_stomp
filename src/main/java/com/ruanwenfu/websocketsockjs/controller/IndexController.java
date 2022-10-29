package com.ruanwenfu.websocketsockjs.controller;

import com.ruanwenfu.websocketsockjs.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class IndexController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // 返回模板名 会去中 index.html
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 请求登录时需要携带用户id
    @GetMapping("/user")
    public String user(Long id, ModelMap modelMap){
        // 将参数传递给模板页面
        modelMap.addAttribute("id",id);
        return "user";
    }

    // 浏览器需要向服务器发送消息时才需要有这个请求地址，否则的话可以不用开发该方法
    // 底层应该是使用WebSocket的 OnMessage注解开发的（应该类似）
    // 服务器端接收到消息后会调用该方法
    // 接收请求协议 WebSocket
    @MessageMapping("/message")
    public void send(Message message){
        System.out.println("fromUserId = " + message.getFromUserId());
        System.out.println("toUserId = " + message.getToUserId());
        System.out.println("content = " + message.getContent());

        Map<String,String> map = new HashMap<>();
        map.put("toUserId",message.getToUserId());
        map.put("fromUserId",message.getFromUserId());
        map.put("content",message.getContent());
        // 转发到个人主题
        // 将消息转发到主题 /user/1/message/get
        simpMessagingTemplate.convertAndSendToUser(message.getToUserId(),"/message/get",map);
    }
}
