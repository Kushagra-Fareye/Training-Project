package com.fareye.training.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoRequestDto {

    private Integer user_id;
    private String title;
    private String body;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;

}
