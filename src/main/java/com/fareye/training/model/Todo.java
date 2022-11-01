package com.fareye.training.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fareye.training.annotations.DuplicateTitle.DuplicateTitle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

enum Status {
    IN_PROGRESS,
    DONE
}

@Getter
@Setter
@ToString
@Entity
@Repository
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id")
    private Integer id;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @DuplicateTitle
    private String title;
    @NotNull
    private String body;
    private Status status;

    @ManyToOne
    private User user;

}
