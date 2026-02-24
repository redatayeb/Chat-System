package com.marwane.messagerie.Controller;

import com.marwane.messagerie.Model.chatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class chatController {

    @MessageMapping("/send") // Listens at /app/send
    @SendTo("/topic/messages") // Broadcasts to everyone subscribed to /topic/messages
    public chatMessage send(chatMessage message) {
        message.setTimestamp(LocalDateTime.now().toString());
        return message;
    }

    // Handling video call offer
    @MessageMapping("/offer")
    @SendTo("/topic/messages")
    public chatMessage handleOffer(chatMessage message) {
        message.setTimestamp(LocalDateTime.now().toString());
        message.setType("offer");
        return message;
    }

    // Handling video call answer
    @MessageMapping("/answer")
    @SendTo("/topic/messages")
    public chatMessage handleAnswer(chatMessage message) {
        message.setTimestamp(LocalDateTime.now().toString());
        message.setType("answer");
        return message;
    }

    // Handling ICE candidate
    @MessageMapping("/ice-candidate")
    @SendTo("/topic/messages")
    public chatMessage handleIceCandidate(chatMessage message) {
        message.setTimestamp(LocalDateTime.now().toString());
        message.setType("ice-candidate");
        return message;
    }
    // Handle rejected call
    @MessageMapping("/reject")
    @SendTo("/topic/messages")
    public chatMessage handleReject(chatMessage message) {
        message.setTimestamp(LocalDateTime.now().toString());
        message.setType("rejected");
        return message;
    }

}

