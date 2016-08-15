package go.go.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Ordered_Products")
public class OrderedProducts {

	@Id
	@Column(name = "idOrderedProducts")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderedProductsId;

	@ManyToOne
	@JoinColumn(name = "idProduct")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "idOrder")
	private Order order;

	@Column(name = "quantity")
	private Integer quantity;

	public OrderedProducts() {
	}

	public OrderedProducts(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public OrderedProducts(Product product, Order order, Integer quantity) {
		this(product, quantity);
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderedProducts [orderedProductsId=" + orderedProductsId + ", product=" + product + ", order=" + order
				+ ", quantity=" + quantity + "]";
	}

}
