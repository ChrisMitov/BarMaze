package go.go.services;

import java.net.HttpURLConnection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import go.go.context.UserContext;
import go.go.dao.UserDao;
import go.go.model.User;

@Stateless
@Path("login")
public class LoginService {
	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserDao userDAO;

	@Inject
	private UserContext context;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(User user) {
		boolean isUserValid = userDAO.validateUserCredentials(user.getUsername(), user.getPassword());
		if (!isUserValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		context.setCurrentUser(user);
		return RESPONSE_OK;
	}
	
	@Path("authenticated")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response isAuthenticated() {
        if (context.getCurrentUser() == null) {
            return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
        }
        return RESPONSE_OK;
    }
	
	@Path("current")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser() {
        if (context.getCurrentUser() == null) {
            return null;
        }
        return context.getCurrentUser().getUsername();
    }
}
