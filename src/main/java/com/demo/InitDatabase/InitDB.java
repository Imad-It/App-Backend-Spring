package com.demo.InitDatabase;

import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.demo.entities.Category;
import com.demo.entities.Product;
import com.demo.services.CategoryService;
import com.demo.services.ProductService;


@Component
@Transactional
public class InitDB implements CommandLineRunner{
	
	@Autowired
	private CategoryService categoryService;	
	@Autowired
	private ProductService productService;

	private final int NUMBER_OF_PRODUCTS= 18;
	private String description="Lorem ipsum dolor sit amet consectetur adipisicing elit. Sed, qui.";
	private String[] categories = {"Mobiles ", "Tablets ", "computers ", "Kameras ","Smartwatches "};
	private Random random = new Random();

	@Override
	public void run(String... args) throws Exception {		

		for(int i=1;i<=categories.length;i++) {
			categoryService.addCategory(new Category(categories[i-1]));
			for(int j=1;j<NUMBER_OF_PRODUCTS;j++) {				
				String name= categories[i-1]+String.valueOf(i)+String.valueOf(j);
				Product product=new Product(name, description, random.nextInt(300)+70,"./assets/images/"+i+".jpg",categoryService.getCategory(new Long(i)));
				productService.addProduct(product);
			}
		}	
	}

}
