package com.example.wesocial1_0.domain.dto;

import com.example.wesocial1_0.domain.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private Date lastVisit;
    private String status;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastVisit = user.getLastVisit();
        this.status = user.getStatus();
    }
}
