package controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import DAOs.UserDAO;
import DTOs.UserDTO;
import entities.User;
import mappers.UserMapper;

public class UserViewController {
	@Inject
	UserDAO dao;
	
	
	UserMapper mapper = new UserMapper();

	public UserDTO findById(int id) throws Exception {
		User u = dao.findById(id);
		return mapper.UserToDto(u);
	}

	public List<UserDTO> findAllUsers() {
		List<User> users = dao.findAll();
		List<UserDTO> usersDtos = users.stream().map(mapper::UserToDto).collect(Collectors.toList());
		return usersDtos;
	}
}
