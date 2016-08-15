package go.go.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import go.go.dao.UserDao;
import go.go.enums.UserRole;
import go.go.model.User;

@Path("register")
@Stateless
public class RegisterService {

	@Inject
	private UserDao userDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User newUser) {
		System.out.println(newUser);
		userDAO.addUser(newUser);
		return Response.ok().build();
		// context.setCurrentUser(newUser);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserRole[] getUserRole() {
		return UserRole.values();
	}
}
