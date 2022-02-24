package controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import DAOs.AuthorDAO;
import DTOs.AuthorDTO;
import entities.Author;
import mappers.AuthorMapper;

public class AuthorViewController {
	@Inject
	AuthorDAO dao;
	AuthorMapper mapper = new AuthorMapper();

	public AuthorDTO findById(int id) throws Exception {
		Author author = dao.findByID(id);
		return mapper.AuthorEntityToDTO(author);
	}

	public List<AuthorDTO> findAllUsers() {
		List<Author> users = dao.findAll();
		List<AuthorDTO> authorDTOs = users.stream().map(mapper::AuthorEntityToDTO).collect(Collectors.toList());
		return authorDTOs;
	}
}
