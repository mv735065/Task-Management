package com.example.TaskManagement.Repository;

import com.example.TaskManagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task,Integer> {


}
