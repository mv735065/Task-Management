package com.example.TaskManagement.RequestDto;

import com.example.TaskManagement.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskDto {

    private String title;
    private String description;
    private LocalDate due_date;
    private Status status;
}
