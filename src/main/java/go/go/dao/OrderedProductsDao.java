package go.go.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import go.go.model.Order;
import go.go.model.OrderedProducts;
import go.go.utils.DaoUtils;

@Singleton
public class OrderedProductsDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory();

	public void addOrderProduct(OrderedProducts orderProducts) {
		manager.persist(orderProducts);
	}

	public void deleteOrderProduct(int id) {
		manager.remove(getOrderProductById(id));
	}

	public OrderedProducts getOrderProductById(int OrderedProductsId) {
		return manager.find(OrderedProducts.class, OrderedProductsId);
	}

	@SuppressWarnings("unchecked")
	public Collection<OrderedProducts> getAllOrderedProducts(Order order) {
		List<OrderedProducts> p = manager.createQuery("SELECT p FROM OrderedProducts p where p.order = :order ")
				.setParameter("order", order).getResultList();
		for (OrderedProducts orderedProducts : p) {
			System.out.println(orderedProducts);
		}
		return p;
	}
}
