package com.example.wesocial1_0.domain.dto;

import com.example.wesocial1_0.domain.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private Date lastVisit;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.lastVisit = user.getLastVisit();
    }
}
