package com.rnp.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.rnp.blog_app_apis.entities.User;
import com.rnp.blog_app_apis.exceptions.ResourceNotFoundException;
import com.rnp.blog_app_apis.payloads.UserDto;
import com.rnp.blog_app_apis.repositories.UserRepo;
import com.rnp.blog_app_apis.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userRepo;



	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
//		this.userRepo.save(user);
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
		
		
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user","id",userId)));

		user.setName(UserDto.getName());
		User.DtosetEmail(UserDto.getEmail());
		user.setPassword(UserDto.getPassword());
		usersetAbout(UserDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos =users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		 
		return userDtos;
	}

	@Override
	public void delete(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException( "User","Id",userId));
		this.userRepo.delete(user);

	}

	
	
	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;

		
		
	}
	
	
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
		 
		
	}
}
