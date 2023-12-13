package com.rx.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rx.blog.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	Optional<Roles> findByName(String roleName);
}
