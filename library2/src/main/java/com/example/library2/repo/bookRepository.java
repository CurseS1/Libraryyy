package com.example.library2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library2.Entity.book;
import com.example.library2.Entity.Category;

@Repository
public interface bookRepository extends JpaRepository<book, Long> {
	public book findByTag(String tag);
	public List<book> findByCategory(Category category);
	public List<book> findByCategoryAndStatus(Category category, Integer status);
	public Long countByStatus(Integer status);
}
