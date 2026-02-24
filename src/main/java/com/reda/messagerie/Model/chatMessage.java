package com.marwane.messagerie.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class chatMessage {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
    private String type;         // "text", "audio", "file"
    private String audioBase64;
    private String fileName;
    private String fileType;
    private String fileBase64;
}

