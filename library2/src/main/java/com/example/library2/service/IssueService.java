package com.example.library2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library2.common.Constants;
import com.example.library2.Entity.Issue;
import com.example.library2.Entity.Member;
import com.example.library2.repo.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	
	public List<Issue> getAll() {
		return issueRepository.findAll();
	}
	
	public Issue get(Long id) {
		return issueRepository.findById(id).get();
	}
	
	public List<Issue> getAllUnreturned() {
		return issueRepository.findByReturned( Constants.book_NOT_RETURNED );
	}
	
	public Issue addNew(Issue issue) {
		issue.setIssueDate( new Date() );
		issue.setReturned( Constants.book_NOT_RETURNED );
		return issueRepository.save(issue);
	}
	
	public Issue save(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public Long getCountByMember(Member member) {
		return issueRepository.countByMemberAndReturned(member, Constants.book_NOT_RETURNED);
	}
}
