package com.cirtual.restrepository;

import com.cirtual.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	User findOneByUserName(String name);
	User findOneByEmailId(String email);
	User findOneByUserNameOrEmailId(String username, String emailId);
    List<User> findByfirstName(@Param("name") String name);
    
    @Modifying
    @Transactional
    @Query("update User u set u.firstName = :firstName, "
            + "u.lastName = :lastName, u.age = :age, u.profession = :profession "
            + "where u.userName = :userName")
    int updateUser(
            @Param("userName") String userName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("age") Integer age,
            @Param("profession") String profession);
}
