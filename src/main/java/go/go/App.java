package go.go;

import go.go.dao.UserDao;
import go.go.enums.UserRole;
import go.go.model.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		dao.addUser(new User("Chris", "abcd", UserRole.MANAGER));
	}
}
