package com.restrepository;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cirtual.entity.User;
import com.cirtual.restrepository.UserRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Rule;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
    
/*	@Test
	public void findByUserNameShouldReturnUser(){
		User user = new User();
    	user.setFirstName("John");
    	user.setLastName("Cena");
    	user.setAge(32);
    	user.setProfession("Wrestler");
    	user.setEmailId("john.cena@ucantsee.me");
    	
    	this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Username must not be empty");
		user;
    	
		this.entityManager.persist(user);
        List<User> users = this.userRepository.findByfirstName("sboot");
        
        assertThat(user.getUsername()).isEqualTo("sboot");
        assertThat(user.getVin()).isEqualTo("123");
	}*/
	
    @Test
    public void testSaveUser(){
    	//setup User
    	User user = new User();
    	user.setFirstName("John");
    	user.setLastName("Cena");
    	user.setAge(32);
    	user.setProfession("Wrestler");
    	user.setEmailId("john.cena@ucantsee.me");
    	
    	//save User, verify has ID value after save
    	assertNull(user.getId());
    	userRepository.save(user);
    	assertNotNull(user.getId());
    	
    	// fetch from DB
    	User fetchedUser = userRepository.findOne(user.getId());
    	
    	// it should not be null
    	assertNotNull(fetchedUser);
    	
    	//should equal
        assertEquals(user.getId(), fetchedUser.getId());
        assertEquals(user.getProfession(), fetchedUser.getProfession());

        //update description and save
        fetchedUser.setProfession("Actor");
        userRepository.save(fetchedUser);

        //get from DB, should be updated
        User fetchedUpdatedProduct = userRepository.findOne(fetchedUser.getId());
        assertEquals(fetchedUser.getProfession(), fetchedUpdatedProduct.getProfession());

        //verify count of products in DB
        long userCount = userRepository.count();
        assertEquals(userCount, 1);

        //get all products, list should only have one
        Iterable<User> users = userRepository.findAll();

        int count = 0;

        for(User u : users){
            count++;
        }

        assertEquals(count, 1);
    }

}
