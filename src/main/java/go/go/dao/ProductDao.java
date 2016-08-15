package go.go.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import go.go.model.Product;
import go.go.utils.DaoUtils;

import java.util.List;

@Singleton
public class ProductDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory();

	public void addProduct(Product product) {
		manager.persist(product);
	}

	public void deleteProduct(int id) {
		manager.remove(getProductById(id));
	}

	public Product getProductById(int producttId) {
		return manager.find(Product.class, producttId);
	}
	
	public Product getProductByName(String name){
		return manager.createQuery("SELECT p FROM Product p where p.name = :name", Product.class).setParameter("name", name).getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		List<Product> p = manager.createQuery("SELECT p FROM Product p ").getResultList();
		return p;
	}
}
