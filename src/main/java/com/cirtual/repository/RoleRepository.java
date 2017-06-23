package com.cirtual.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cirtual.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByRole(String role);
}
