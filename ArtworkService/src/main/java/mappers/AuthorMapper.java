package mappers;

import DTOs.AuthorDTO;
import entities.Author;
import entities.GroupType;

public class AuthorMapper {
	public Author AuthorDTOToEntity(AuthorDTO dto) {
		Author a = new Author();
		a.setAuthorName(dto.getAuthorName());
		a.setGroupName(dto.getGroupName());
		a.setGroupType(GroupType.valueOf(dto.getGroupType()));
		a.setId(dto.getId());
		return a;
	}

	public AuthorDTO AuthorEntityToDTO(Author a) {
		AuthorDTO d = new AuthorDTO();
		d.setAuthorName(a.getAuthorName());
		d.setGroupName(a.getAuthorName());
		d.setGroupType(a.getGroupType().toString());
		d.setId(a.getId());
		return d;
	}

}
