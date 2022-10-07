// package com.fareye.training.annotations;

// import java.lang.annotation.ElementType;
// import java.lang.annotation.Retention;
// import java.lang.annotation.RetentionPolicy;
// import java.lang.annotation.Target;
// import java.util.List;

// import com.fareye.training.controller.TodoController;
// import com.fareye.training.model.Todo;

// public interface DuplicateTitle {

//     Integer userId() ;
//     String title();
//     Integer userId = userId;
//     String title = title;
//     List<Todo> allTodosForUser = TodoController.getAllTodosForUser(userId);
//     for(Todo todo:allTodosForUser){
//         if(todo.getTitle().equals(title)){

//         }
//     }

//     public String message() default "User Already has a Todo with same title.";

// }
