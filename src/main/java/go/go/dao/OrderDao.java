package go.go.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import go.go.model.Order;
import go.go.utils.DaoUtils;

@Singleton
public class OrderDao {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory();

	public int addOrder(Order order) {
		manager.persist(order);
		System.out.println(order.getIdOrder());
		return order.getIdOrder();
	}

	public void updateOrder(Order order) {
		manager.merge(order);
	}
	
	public void deleteOrder(int id) {
		manager.remove(getOrderById(id));
	}

	public Order getOrderById(int orderId) {
		return manager.find(Order.class, orderId);
	}

//	public Collection<OrderView> getAllOrders() {
//		@SuppressWarnings("unchecked")
//		Collection<OrderView> o = manager.createNativeQuery(
//				"select p.name, p.price, o.quantity, b.time_started, b.time_finished, u.username as 'waiter', us.username as 'barman', b.type from BarOrder b "
//						+ "join User u on b.waiterId = u.ID " + "left outer join User us on b.barmanId = us.ID "
//						+ "join Ordered_Products o on b.idOrder = o.idOrder "
//						+ "join Product p on o.idProduct = p.IDPRODUCT;",OrderView.class).getResultList();
//		return o;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders() {
		List<Order> p = manager.createQuery("SELECT p FROM Order p ").getResultList();
		for (Order order : p) {
			System.out.println(order.getTimeStarted());
		}
		return p;
	}
}
