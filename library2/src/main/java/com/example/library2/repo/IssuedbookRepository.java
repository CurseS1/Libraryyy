package com.example.library2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library2.Entity.book;
import com.example.library2.Entity.Issuedbook;

@Repository
public interface IssuedbookRepository extends JpaRepository<Issuedbook, Long> {
	public Long countBybookAndReturned(book book, Integer returned);
}
