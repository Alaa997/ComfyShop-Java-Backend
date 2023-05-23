package nl.fontys.s3.comfyshop.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
}
