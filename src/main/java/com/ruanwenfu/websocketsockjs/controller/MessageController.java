package com.ruanwenfu.websocketsockjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/user")
    public String message(Long id, ModelMap modelMap){
        modelMap.addAttribute("id",id);
        return "message/index";
    }
}
