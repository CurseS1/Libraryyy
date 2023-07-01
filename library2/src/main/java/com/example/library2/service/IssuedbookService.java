package com.example.library2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library2.common.Constants;
import com.example.library2.Entity.book;
import com.example.library2.Entity.Issuedbook;
import com.example.library2.repo.IssuedbookRepository;

@Service
public class IssuedbookService {

	@Autowired
	private IssuedbookRepository issuedbookRepository;
	
	public List<Issuedbook> getAll() {
		return issuedbookRepository.findAll();
	}
	
	public Issuedbook get(Long id) {
		return issuedbookRepository.findById(id).get();
	}
	
	public Long getCountBybook(book book) {
		return issuedbookRepository.countBybookAndReturned(book, Constants.book_NOT_RETURNED);
	}
	
	public Issuedbook save(Issuedbook issuedbook) {
		return issuedbookRepository.save(issuedbook);
	}
	
	public Issuedbook addNew(Issuedbook issuedbook) {
		issuedbook.setReturned( Constants.book_NOT_RETURNED );
		return issuedbookRepository.save(issuedbook);
	}

}
