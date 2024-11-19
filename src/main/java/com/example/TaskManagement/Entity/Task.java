package com.example.TaskManagement.Entity;

import com.example.TaskManagement.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;


    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;



    @NotNull(message = "Due date cannot be empty ")
    private LocalDate due_date;


    @Enumerated(value=EnumType.STRING)
    private Status status;



    private LocalDateTime created_at;


    private LocalDateTime  updated_at;




}
