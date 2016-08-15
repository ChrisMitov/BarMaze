package go.go.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import go.go.enums.OrderType;

@Entity
@XmlRootElement
@Table(name = "BarOrder")
public class Order {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idOrder;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_started")
	private Date timeStarted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_finished")
	private Date timeFinished;

	@ManyToOne
	@JoinColumn(name = "barmanId")
	private User barman;

	@ManyToOne
	@JoinColumn(name = "waiterId")
	private User waiter;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private OrderType type;

	public Order() {
	}
	
	public Order(Date timeStarted, User waiter, OrderType type) {
		this.timeStarted = timeStarted;
		this.waiter = waiter;
		this.type = type;
	}

	public Order(Date timeStarted, User barman, User waiter, OrderType type) {
		this(timeStarted, waiter, type);
		this.barman = barman;		
	}

	public User getWaiter() {
		return waiter;
	}

	public void setWaiter(User waiter) {
		this.waiter = waiter;
	}

	public User getBarman() {
		return barman;
	}

	public void setBarman(User barman) {
		this.barman = barman;
	}

	public Date getTimeFinished() {
		return timeFinished;
	}

	public void setTimeFinished(Date timeFinished) {
		this.timeFinished = timeFinished;
	}

	public Date getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", timeStarted=" + timeStarted + ", timeFinished=" + timeFinished
				+ ", barman=" + barman + ", waiter=" + waiter + ", type=" + type + "]";
	}

}