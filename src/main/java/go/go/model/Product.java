package go.go.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import go.go.enums.ProductType;

@Entity
@Table(name = "Product")
@XmlRootElement
public class Product {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProduct;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;

	@Column(name = "ProductType")
	@Enumerated(EnumType.STRING)
	private ProductType type;

	public Product() {

	}

	public Product(String name, Double price, ProductType type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Product(int idProduct, String name, Double price, ProductType type) {
		this(name, price, type);
		this.idProduct = idProduct;

	}

	public int getIdProduct() {
		return idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProduct;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (idProduct != other.idProduct)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product{" +
				"idProduct=" + idProduct +
				", name='" + name + '\'' +
				", price=" + price +
				", type='" + type + '\'' +
				'}';
	}
}