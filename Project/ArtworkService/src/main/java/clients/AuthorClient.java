package clients;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import DTOs.AuthorDTO;
import DTOs.UserDTO;
import entities.Author;
import entities.User;
import entities.GroupType;
import mappers.AuthorMapper;
import mappers.UserMapper;


public class AuthorClient {
	/*
	public Author getAuthorFromRestApiById(int id) {
		//Todo: implementare il metodo che esegue una chiamata come client jax-rs
		//all'user service per reperire un user.
		Author newAuthor = new Author();
		newAuthor.setAuthorName("Autore di prova");
		newAuthor.setGroupName("Gruppo di prova");
		newAuthor.setGroupType(groupMovement.CREW);
		return newAuthor;
		
	}
	
	*/
	public  Author getAuthorFromRestApiById(int id) throws Exception {

		String target = "http://localhost:8080/AuthorMicroservice/rest/AuthorMicroservice/{id}";

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(target).resolveTemplate("id", id);
		AuthorDTO response = myResource.request(MediaType.APPLICATION_JSON).get(AuthorDTO.class);

		AuthorMapper u = new AuthorMapper();
		Author retrieved = u.AuthorDTOToEntity(response);
	    client.close();
		return retrieved;
	}
	
	

}
