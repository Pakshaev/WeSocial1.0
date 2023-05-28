package com.example.wesocial1_0.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;
}
