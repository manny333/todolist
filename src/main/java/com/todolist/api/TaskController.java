package com.todolist.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.entity.Task;
import com.todolist.services.TaskService;

@RestController
@RequestMapping("v1.0/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// create a new user
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Task task) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
	}

	// Read an user
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		Optional<Task> oTask = taskService.findById(id);

		if (!oTask.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oTask);
	}
}
