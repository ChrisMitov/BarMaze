package go.go.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import go.go.context.UserContext;

@Stateless
@Path("logout")
public class LogoutService {
	
	@Inject
    private UserContext context;

	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public void logout(){
		context.setCurrentUser(null);
	}
}
