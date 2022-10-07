package com.fareye.training.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
enum Status{
    IN_PROGRESS,
    DONE
}

@Getter @Setter
public class Todo {
    private Integer id;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    // @DuplicateTitle(userId = this.getUserId(), title =this.getTitle());
    private String title;
    private String body;
    private Status status;
    private User user;
    private Integer userId;
    
}
