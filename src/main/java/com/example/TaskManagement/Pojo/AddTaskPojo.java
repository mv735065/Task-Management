package com.example.TaskManagement.Pojo;

import com.example.TaskManagement.Enums.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskPojo {


    private String title;
    private String description;
    private LocalDate due_date;
    private Status status;
}
