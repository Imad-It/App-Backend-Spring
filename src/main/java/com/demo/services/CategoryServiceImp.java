package com.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.CategoryRepository;
import com.demo.entities.Category;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getCategories() {
		List<Category> categories=categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category getCategory(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	@Override
	public Category addCategory(Category c) {
		return categoryRepository.save(c);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
		
	}

	@Override
	public Category updateCategory(Long id, Category c) {
		c.setId(id);
		return categoryRepository.save(c);
	}

}
