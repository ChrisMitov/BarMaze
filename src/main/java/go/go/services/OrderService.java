package go.go.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import go.go.context.UserContext;
import go.go.dao.OrderDao;
import go.go.dao.OrderedProductsDao;
import go.go.dao.ProductDao;
import go.go.dao.UserDao;
import go.go.enums.OrderType;
import go.go.model.Order;
import go.go.model.OrderedProducts;

@Stateless
@Path("order")
public class OrderService {

	@Inject
	private OrderDao orderDAO;

	@Inject
	private OrderedProductsDao orderedDAO;

	@Inject
	private UserDao userDAO;
	@Inject
	private ProductDao productDAO;

	@Inject
	private UserContext context;

	@POST
	@Path("make")
	@Consumes(MediaType.APPLICATION_JSON)
	public void makeOrder(Collection<OrderedProducts> products) {
		int id = orderDAO.addOrder(new Order(Calendar.getInstance().getTime(),
				userDAO.getUserByUsername(context.getCurrentUser().getUsername()), OrderType.WAITING));
		System.err.println(id);
		System.err.println(products.size());
		for (OrderedProducts product : products) {
			System.out.println(product.getProduct());
			product.setProduct(productDAO.getProductByName(product.getProduct().getName()));
			System.out.println(product.getProduct());
			product.setOrder(orderDAO.getOrderById(id));
			orderedDAO.addOrderProduct(product);
		}
	}

	@GET
	@Produces("application/json")
	public Collection<Order> getOrders() {
		Collection<Order> orders = orderDAO.getAllOrders();
		for (Order order : orders) {
			System.err.println(order.getTimeStarted());
		}
		return orders;
	}
	
	@PUT
	@Path("{idOrder}")
	public void changeStatus(@PathParam("idOrder") int id, String type){
		System.out.println(type);
		Order order = orderDAO.getOrderById(id);
		OrderType[] values = OrderType.values();
		for (OrderType orderType : values) {
			if(orderType.toString().equals(type)){
				order.setType(orderType);
				order.setBarman(userDAO.getUserByUsername(context.getCurrentUser().getUsername()));
				if(orderType.equals(OrderType.COMPLETED)){
					order.setTimeFinished(Calendar.getInstance().getTime());
				}
				break;
			}
		}
		orderDAO.updateOrder(order);
	}
	

	@GET
	@Path("details/{idOrder}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<OrderedProducts> getDetails(@PathParam("idOrder") int id){
		return orderedDAO.getAllOrderedProducts(orderDAO.getOrderById(id));
	}
	
	@GET
	@Path("type")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderType[] getAllOrderTypes() {
		return OrderType.values();
	}
}
