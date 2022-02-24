package controllers;

import javax.inject.Inject;

import DAOs.AuthorDAO;
import DTOs.AuthorCreationCommandDTO;
import DTOs.AuthorUpdateGroupNameCommandDTO;
import DTOs.AuthorUpdateGroupTypeCommandDTO;
import DTOs.AuthorUpdateNameCommandDTO;
import entities.Author;
import entities.GroupType;
import exceptions.BadRequestException;

public class AuthorCommandController {
	@Inject
	AuthorDAO authorDAO;

	public Author create(AuthorCreationCommandDTO request) throws Exception {
		Author author = new Author();
		author.setAuthorName(request.getAuthorName());
		author.setGroupName(request.getGroupName());
		author.setGroupType(GroupType.valueOf(request.getGroupType()));
		Author created = authorDAO.create(author);
		return created;
	}

	public Author updateName(int id, AuthorUpdateNameCommandDTO request) throws Exception {
		if (request.authorName == null || request.authorName.equals("")) {
			throw new BadRequestException("Valore nullo");
		}
		Author a = authorDAO.findByID(id);
		a.setAuthorName(request.authorName);
		Author updated = authorDAO.update(id, a);
		return updated;
	}

	public Author delete(int id) throws Exception {
		Author deleted = authorDAO.delete(id);
		return deleted;
	}

	public Author updateGroupName(int id, AuthorUpdateGroupNameCommandDTO request) throws Exception {
		if (request.groupName == null || request.groupName.equals("")) {
			throw new BadRequestException("Valore nullo");
		}
		Author a = authorDAO.findByID(id);
		a.setGroupName(request.groupName);
		Author updated = authorDAO.update(id, a);
		return updated;

	}

	public Author updateGroupType(int id, AuthorUpdateGroupTypeCommandDTO request) throws Exception {
		if (request.groupType == null || request.groupType.equals("")) {
			throw new BadRequestException("Valore nullo");
		}
		Author a = authorDAO.findByID(id);
		a.setGroupType(GroupType.valueOf(request.groupType));
		authorDAO.update(id, a);
		Author updated = authorDAO.update(id, a);
		return updated;

	}

}
