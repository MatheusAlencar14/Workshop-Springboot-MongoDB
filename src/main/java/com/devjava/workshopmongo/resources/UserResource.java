package com.devjava.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devjava.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User matheus = new User("1", "Matheus Alencar", "matheus@gmail.com");
		User miguel = new User("2", "Miguel Costa", "miguel@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(matheus, miguel));
		return ResponseEntity.ok().body(list);
	}
}
