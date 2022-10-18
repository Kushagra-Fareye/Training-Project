package com.fareye.training.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fareye.training.annotations.DuplicateTitle.DuplicateTitle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

enum Status {
    IN_PROGRESS,
    DONE
}

@Getter
@Setter
@ToString
public class Todo {
    private Integer id;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @DuplicateTitle
    private String title;
    @NotNull
    private String body;
    private Status status;
    private User user;
    private Integer userId;

}
