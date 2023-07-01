package com.example.library2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library2.common.Constants;
import com.example.library2.Entity.book;
import com.example.library2.Entity.Category;
import com.example.library2.repo.bookRepository;

@Service
public class bookService {

	@Autowired
	private bookRepository bookRepository;
	
	@Autowired
	private IssuedbookService issuedbookService;
	
	public Long getTotalCount() {
		return bookRepository.count();
	}
	
	public Long getTotalIssuedbooks() {
		return bookRepository.countByStatus(Constants.book_STATUS_ISSUED);
	}
	
	public List<book> getAll() {
		return bookRepository.findAll();
	}
	
	public book get(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public book getByTag(String tag) {
		return bookRepository.findByTag(tag);
	}
	
	public List<book> get(List<Long> ids) {
		return bookRepository.findAllById(ids);
	}
	
	public List<book> getByCategory(Category category) {
		return bookRepository.findByCategory(category);
	}
	
	public List<book> geAvailabletByCategory(Category category) {
		return bookRepository.findByCategoryAndStatus(category, Constants.book_STATUS_AVAILABLE);
	}
	
	public book addNew(book book) {
		book.setCreateDate(new Date());
		book.setStatus( Constants.book_STATUS_AVAILABLE );
		return bookRepository.save(book);
	}
	
	public book save(book book) {
		return bookRepository.save(book);
	}
	
	public void delete(book book) {
		bookRepository.delete(book);
	}
	
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}
	
	public boolean hasUsage(book book) {
		return issuedbookService.getCountBybook(book)>0;
	}
}
