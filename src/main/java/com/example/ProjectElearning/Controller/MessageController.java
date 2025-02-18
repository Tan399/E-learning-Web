package com.example.ProjectElearning.Controller;


import com.example.ProjectElearning.Model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MessageController {


    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message getContent(@RequestBody Message message){
        return message;
    }
}