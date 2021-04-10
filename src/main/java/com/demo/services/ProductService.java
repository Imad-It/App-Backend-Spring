package com.demo.services;

import java.util.List;
import com.demo.entities.Product;

public interface ProductService {

	public List<Product> getProducts();
	public Product addProduct(Product p);
	public Product getProduct(Long id);
	public void deleteProduct(Long id);
	public Product updateProduct(Long id, Product p);
	public Product findById(Long id);
	public List<Product> searchProduct(String keyword);
	
}
