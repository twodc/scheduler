package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
