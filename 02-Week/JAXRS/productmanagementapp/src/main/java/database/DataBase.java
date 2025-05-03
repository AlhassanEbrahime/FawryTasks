package database;

import java.util.HashMap;
import java.util.Map;

import model.Product;

public class DataBase {

	private static Map<Long , Product> products = new HashMap<>();
	
	public static Map<Long, Product> getProducts(){
		return products;
	}
}
