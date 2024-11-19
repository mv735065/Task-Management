package com.example.TaskManagement;

import com.example.TaskManagement.Entity.Task;
import com.example.TaskManagement.Enums.Status;
import com.example.TaskManagement.Repository.TaskRepository;
import com.example.TaskManagement.Service.ServiceImp.TaskSerivceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaskManagementApplicationTests {

	@Autowired
	private TaskSerivceImp taskService; // Actual service being tested

	@Autowired
	private TaskRepository taskRepository; // Real repository

	@BeforeEach
	void setUp() {
		// Prepopulate the database with a task if necessary
		Task task = new Task();
		task.setTitle("Sample Task");
		task.setDescription("Sample Description");
		task.setDue_date(java.time.LocalDate.now().plusDays(7));
		task.setStatus(Status.PENDING);
		taskRepository.save(task);
	}

	@Test
	void testMarkTaskAsComplete() throws Exception {
		// Retrieve the task by ID
		Task task = taskRepository.findAll().get(0);
		Integer taskId = task.getId();

		// Act: Mark the task as complete
		Task updatedTask = taskService.completeTask(taskId);

		// Assert: Check if the status is updated
		assertNotNull(updatedTask);
		assertEquals(Status.COMPLETED, updatedTask.getStatus());
	}
}

