package com.cignex.controller;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cignex.bean.User;
import com.cignex.bean.UserDao;
import com.cignex.exception.UserNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDao userDao;

	@GetMapping("/list")
	public List<User> getAll() {
		return userDao.findAll();
	}

	@GetMapping("/get/{id}")
	public Resource<User> getUser(@PathVariable int id)  {
		User user=userDao.findOne(id);
		if(user==null) {
			throw new UserNotFoundException(" user not found with this id "+id);
		}
		Resource<User> resource=new Resource<User>(user);
		ControllerLinkBuilder builder=linkTo(methodOn(this.getClass()).getAll());
		resource.add(builder.withRel("all-user"));
		return resource;
	}

	@PostMapping("/save")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody User user) {
		User saveUser = userDao.save(user);
	    URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
	    return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		userDao.delete(id);
	}
}
