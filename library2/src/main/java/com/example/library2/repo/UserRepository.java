package com.example.library2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library2.Entity.user;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
	public List<user> findAllByOrderByDisplayNameAsc();
	public List<user> findAllByActiveOrderByDisplayNameAsc(Integer active);
	public user findByUsername(String username);
}
