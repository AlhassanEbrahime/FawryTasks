package org.project;

import org.project.Product;

import java.util.*;

public class ProductRepository {
    private static final Map<Integer, Product> productMap = new HashMap<>();
    private static int idCounter = 1;

    public static List<Product> getAll() {
        return new ArrayList<>(productMap.values());
    }

    public static Product getById(int id) {
        return productMap.get(id);
    }

    public static Product create(Product product) {
        int id = idCounter++;
        product.setId(id);
        productMap.put(id, product);
        return product;
    }

    public static boolean update(int id, Product updatedProduct) {
        if (productMap.containsKey(id)) {
            updatedProduct.setId(id);
            productMap.put(id, updatedProduct);
            return true;
        }
        return false;
    }

    public static boolean delete(int id) {
        return productMap.remove(id) != null;
    }
}
