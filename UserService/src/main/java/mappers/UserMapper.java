package mappers;

import DTOs.UserDTO;
import entities.User;

public class UserMapper {
	public UserDTO UserToDto(User user) {
		UserDTO DTO = new UserDTO();
		DTO.setUsername(user.getUsername());
		DTO.setId(user.getId());
		return DTO;
	}

	public User DTOToUser(UserDTO DTO) throws Exception {
		User u = new User();
		u.setUsername(DTO.getUsername());

		return u;
	}

}
