package com.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.entities.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	public Optional<Product> findById(Long id);
	public Product findByName(String name);
//	public List<Product> findByNameLike(String keyword);
	public List<Product> findByNameContains(String keyword);
}