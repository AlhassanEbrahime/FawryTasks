package productResources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import exception.ProductNotFoundException;
import model.Product;

import service.ProductService;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResouces {
	
	ProductService productService = new ProductService();
		
	
	@GET
	public Response getAllProducts() {
		List<Product> product = productService.getAllProducts();
		return Response.ok(product).build();
	}
	
	
	@GET
	@Path("/{id}")
	public Response getProductById(@PathParam("id")long id) {
		try {
		Product product = productService.getProduct(id);
		return Response.ok(product).build();
		} catch (ProductNotFoundException e) {
	        return Response.status(Response.Status.NOT_FOUND)
	                       .entity(e.getMessage())
	                       .build();
	    }
	}
	
	
	
	@POST
    public Response createProduct(Product product) {
        Product created = productService.addProduct(product);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
    
    // Update product
    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") long id, Product product) {
        Product updated = productService.updateProduct(id, product);
        return Response.ok(updated).build();
    }
    
    // Delete product
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        productService.removeProduct(id);
        return Response.noContent().build();
    }
	
	
	

}
 