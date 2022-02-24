package clients;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import DTOs.UserDTO;
import entities.User;
import mappers.UserMapper;

public class UserClient {
	/*
	public User getUserFromRestApiById(int id) {
		//Todo: implementare il metodo che esegue una chiamata come client jax-rs
		//all'user service per reperire un user.
		User new_user = new User();
		new_user.setUsername("test");
		return new_user;
		
	}
	*/
	public  User getUserFromRestApiById(int id) throws Exception {

		String target = "http://localhost:8080/UserMicroservice/rest/UserMicroservice/{id}";

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(target).resolveTemplate("id", id);
		UserDTO response = myResource.request(MediaType.APPLICATION_JSON).get(UserDTO.class);

		UserMapper u = new UserMapper();
		User retrieved = u.DTOToUser(response);
	    client.close();
		return retrieved;
	}
}
