package go.go.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import go.go.model.User;
import go.go.utils.DaoUtils;

import java.security.MessageDigest;
import java.util.List;

@Singleton
public class UserDao {

	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory();

	public void addUser(User user) {
		user.setPassword(getHashedPassword(user.getPassword()));
		manager.persist(user);
	}

	public boolean validateUserCredentials(String userName, String password) {
		System.out.println(userName + "..." + password);
		String txtQuery = "SELECT u FROM User u WHERE u.username=:userName AND u.password=:password";
		TypedQuery<User> query = manager.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", getHashedPassword(password));
		return queryUser(query) != null;
	}

	public List<User> listUsers() {
		return manager.createQuery("SELECT u FROM User u ", User.class).getResultList();
	}

	public void deleteUser(User user) {
		manager.remove(user);
	}

	public User getUserById(Integer userId) {
		return manager.find(User.class, userId);
	}

	public User getUserByUsername(String uname) {
		return (User) manager.createQuery("SELECT u FROM User u WHERE u.username = \'" + uname + "\'")
				.getSingleResult();
	}

	public void updateUser(User user) {
		try {
			manager.getTransaction().begin();
			manager.merge(user);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		}
	}

	private User queryUser(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
}
