package go.go.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import go.go.dao.ProductDao;
import go.go.enums.ProductType;
import go.go.model.Product;

@Stateless
@Path("product")
public class ProductRest {
	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private ProductDao productDao;

	@GET
	@Produces("application/json")
	public Collection<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {
		productDao.addProduct(product);
		return RESPONSE_OK;
	}

	@DELETE
	@Path("{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("productId") int id) {
		productDao.deleteProduct(id);
		return RESPONSE_OK;
	}

	@GET
	@Path("type")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductType[] getAllProductTypes() {
		return ProductType.values();
	}
}
