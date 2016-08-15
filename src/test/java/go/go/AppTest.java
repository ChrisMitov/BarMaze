package go.go;

import java.util.Collection;

import org.junit.Test;

import go.go.dao.ProductDao;
import go.go.model.Product;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void test() {
//		Product product = new Product("Smqdovo2", 2.25, ProductType.RAKIQ);
//		System.out.println(product);
		ProductDao dao = new ProductDao();
//		dao.addProduct(product);
		Collection<Product> products = dao.getAllProducts();
		for (Product product2 : products) {
			System.out.println(product2);
		}
	}
}
   