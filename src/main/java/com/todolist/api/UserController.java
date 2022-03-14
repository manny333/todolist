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

import com.todolist.entity.User;
import com.todolist.services.UserService;

@RestController
@RequestMapping("v1.0/users")
public class UserController {

	@Autowired
	private UserService userService;

	// create a new user
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	// Read an user
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		Optional<User> oUser = userService.findById(id);

		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}
}
