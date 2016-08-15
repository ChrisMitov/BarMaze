package go.go.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class OrderView {
	@Id
	private String name;
	private Integer price;
	private Integer quantity;
	private Date time_started;
	private Date time_finished;
	private String waiter;
	private String barman;
	private String type;

	public OrderView() {
		name = null;
		price = 0;
		quantity = 0;
		time_started = null;
		time_finished = null;
		waiter = null;
		barman = null;
		type = null;		
	}
	public OrderView(String name, Integer price, Integer quantity, Date time_started, Date time_finished, String waiter,
			String barman, String type) {
		this();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.time_started = time_started;
		this.time_finished = time_finished;
		this.waiter = waiter;
		this.barman = barman;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getTime_started() {
		return time_started;
	}

	public void setTime_started(Date time_started) {
		this.time_started = time_started;
	}

	public Date getTime_finished() {
		return time_finished;
	}

	public void setTime_finished(Date time_finished) {
		this.time_finished = time_finished;
	}

	public String getWaiter() {
		return waiter;
	}

	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}

	public String getBarman() {
		return barman;
	}

	public void setBarman(String barman) {
		this.barman = barman;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "OrderView [name=" + name + ", price=" + price + ", quantity=" + quantity + ", time_started="
				+ time_started + ", time_finished=" + time_finished + ", waiter=" + waiter + ", barman=" + barman
				+ ", type=" + type + "]";
	}

}
