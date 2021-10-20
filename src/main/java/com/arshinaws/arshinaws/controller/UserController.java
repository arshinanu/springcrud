package com.arshinaws.arshinaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arshinaws.arshinaws.entity.User;
import com.arshinaws.arshinaws.exception.ResourceNotFoundException;
import com.arshinaws.arshinaws.repository.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserRepository userrep;
	
	@GetMapping()
	public List<User> getuser(){
		
		List<User> ulist=userrep.findAll();
		
		return ulist;
	}
	@GetMapping("/{id}")
	public User getuserbyid(@PathVariable(value="id") long userid)
	{
		return this.userrep.findById(userid).orElseThrow(()->new ResourceNotFoundException("user not found with :" +userid));
	}
	
	@PostMapping
	public User createuser(@RequestBody User user)
	{
		return this.userrep.save(user);
		
	}
	
	@PutMapping("/{id}")
	public User updateuser(@RequestBody User user ,@PathVariable ("id") long userid)
	{
		User us=this.userrep.findById(userid).orElseThrow(()->new ResourceNotFoundException("user not found with :" +userid));
		us.setFirstname(user.getFirstname());
		us.setLastname(user.getLastname());
		us.setEmail(user.getEmail());
		return this.userrep.save(us);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteuser(@PathVariable ("id") long userid)
	{
		User us=this.userrep.findById(userid).orElseThrow(()->new ResourceNotFoundException("user not found with :" +userid));
		 this.userrep.delete(us);
		return ResponseEntity.ok().build();
		
	}
}
