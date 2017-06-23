package com.cirtual.repository;

import com.cirtual.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to provide methods to 
 * @author ashutosh
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	User findByEmail(String email);
	User findById(Integer id);
}
