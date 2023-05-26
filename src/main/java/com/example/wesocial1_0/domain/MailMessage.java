package com.example.wesocial1_0.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailMessage {
    private String address;
    private String title;
    private String messageText;
}
