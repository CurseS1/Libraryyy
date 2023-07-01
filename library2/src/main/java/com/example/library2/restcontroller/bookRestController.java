package com.example.library2.restcontroller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library2.Entity.book;
import com.example.library2.Entity.Category;
import com.example.library2.service.bookService;
import com.example.library2.service.CategoryService;

@RestController
@RequestMapping(value = "/rest/book")
public class bookRestController {

	@Autowired
	private bookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = {"/", "/list"})
	public List<book> all() {
		return bookService.getAll();
	}
	
	@GetMapping(value = "/{id}/list")
	public List<book> get(@PathVariable(name = "id") Long id) {
		Category category = categoryService.get(id);
		return bookService.getByCategory( category );
	}
	
	@GetMapping(value = "/{id}/available")
	public List<book> getAvailablebooks(@PathVariable(name = "id") Long id) {
		Category category = categoryService.get(id);
		return bookService.geAvailabletByCategory( category );
	}
	
}
