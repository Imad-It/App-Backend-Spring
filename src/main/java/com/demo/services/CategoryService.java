package com.demo.services;

import java.util.List;
import com.demo.entities.Category;



public interface CategoryService {
	
	public List<Category> getCategories();
	public Category addCategory(Category c);
	public Category getCategory(Long id);
	public void deleteCategory(Long id);
	public Category updateCategory(Long id, Category c);

}
