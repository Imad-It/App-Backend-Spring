package com.demo.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.demo.entities.Category;
import com.demo.entities.Product;
import com.demo.services.CategoryService;
import com.demo.services.ProductService;


@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class FrontController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public ResponseEntity<List<Category>> getCategories(){
		return new ResponseEntity<List<Category>>(categoryService.getCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/{idCat}")
	public ResponseEntity<Category> getCategory(@PathVariable Long idCat) {
		return new ResponseEntity<Category>(categoryService.getCategory(idCat),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Category> addCategory(@RequestBody Category c) {
		return new ResponseEntity<Category>(categoryService.addCategory(c), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{idCat}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long idCat) {
		categoryService.deleteCategory(idCat);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{idCat}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long idCat, @RequestBody Category c) {
		return new ResponseEntity<Category>(categoryService.updateCategory(idCat, c), HttpStatus.OK);
	}
	
	@GetMapping("/{idCat}/products")
	public ResponseEntity<List<Product>> getProducts(@PathVariable Long idCat) {
		Category category = categoryService.getCategory(idCat);
		List<Product> products= category.getItems();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/{idCat}/products")
	public void addProduct(@RequestPart("file") MultipartFile file,
						   @RequestPart("name") String name,
						   @RequestPart("desc") String desc,
						   @RequestPart("price") String price,
						   @PathVariable Long idCat) throws IOException {

		// Diese Pfad entspricht die Angular-Projekt-Ordner-Pfad+/src
		final String PATH="C:/JEE/ANGULAR_WORKSPACE/App-Frontend-Angular/src";
		Category category= categoryService.getCategory(idCat);
		Product product=new Product();
		product.setName(name);
		product.setDescription(desc);
		product.setPrice(Float.parseFloat(price));
		product.setCategory(category);
		
		category.getItems().add(product);
		Product savedProduct=productService.addProduct(product);		
		product.setPhoto("./assets/images/"+savedProduct.getId()+".jpg");
		Path pathPhoto=Paths.get(PATH+product.getPhoto());
		Files.write(pathPhoto,file.getBytes());
		productService.updateProduct(savedProduct.getId(), product);

	}
	
	
	@DeleteMapping("/{idCat}/products/{idPro}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long idCat,@PathVariable Long idPro){
		Product product=productService.getProduct(idPro);
		List<Product> products=categoryService.getCategory(idCat).getItems();

		for(int i=0; i<products.size();i++) {
			if(products.get(i)==product) {
				products.remove(i);
				break;
			}
		}
		
		productService.deleteProduct(idPro);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
