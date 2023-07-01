package com.example.library2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library2.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findAllByOrderByNameAsc();
}
