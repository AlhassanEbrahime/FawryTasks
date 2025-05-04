package service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import database.DataBase;
import exception.ProductNotFoundException;
import model.Product;

public class ProductService {

	private Map<Long, Product> products = DataBase.getProducts();
	
	
	
	public ProductService() {
		
		  	products.put(1L, new Product(1L, "Apple iPhone 14", 999));
		    products.put(2L, new Product(2L, "Samsung Galaxy S23", 899));
		    products.put(3L, new Product(3L, "Sony WH-1000XM5 Headphones", 399));
		    products.put(4L, new Product(4L, "Dell XPS 13 Laptop", 1199));
		    products.put(5L, new Product(5L, "Apple MacBook Air M2", 1299));
		    products.put(6L, new Product(6L, "Canon EOS R10 Camera", 979));
		    products.put(7L, new Product(7L, "Nintendo Switch OLED", 349));
		    products.put(8L, new Product(8L, "Amazon Echo Dot (5th Gen)", 49));
		    products.put(9L, new Product(9L, "Fitbit Charge 6", 159));
		    products.put(10L, new Product(10L, "Logitech MX Master 3S Mouse", 99));
	}
	


	public List<Product> getAllProducts(){
		return new ArrayList<Product>(products.values());
	}
	
	
	public Product getProduct(long id) {
		if(!products.containsKey(id)) {
			throw new ProductNotFoundException("No proudct found with id : "+id);
		}
		return products.get(id);
	}
	
	
	public Product addProduct(Product product) {
		product.setId((long)products.size()+1);
		return products.put(product.getId(), product);
	}
	
	
    public Product updateProduct(Long id, Product updatedProduct) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("Cannot update. Product with ID " + id + " not found");
        }
        updatedProduct.setId(id);
        products.put(id, updatedProduct);
        return updatedProduct;
    }
	
	public void removeProduct(long id) {
		products.remove(id);
	}
	
}
