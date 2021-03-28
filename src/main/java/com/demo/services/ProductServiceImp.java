package com.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.ProductRepository;
import com.demo.entities.Product;

@Service
public class ProductServiceImp implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(Product p) {
		return productRepository.save(p);
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.getOne(id);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product updateProduct(Long id, Product p) {
		Product product=productRepository.findById(id).get();
		product.setName(p.getName());
		product.setDescription(p.getDescription());
		product.setPrice(p.getPrice());
		return productRepository.save(product);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

}
