package com.cirtual.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.cirtual.entity.User;

/**
 * Interface to encapsulate and provide common functions related to User
 * 
 * @author ashutosh
 *
 */
public interface UserService {

	/**
	 * This method fetches the user from data store based on the email id of the
	 * user.
	 * 
	 * @param unique
	 *            email id of the user.
	 * @return the User to which the unique email belongs to. Null in case user
	 *         is not found.
	 */
	public User findUserByEmail(String email);

	/**
	 * This method fetches the user from data store based on the user id of the
	 * user.
	 * 
	 * @param unique
	 *            user id of the user.
	 * @return the User to which the unique user id belongs to. Null in case
	 *         user is not found.
	 */
	public User findById(Integer id);

	/**
	 * The function to persist the User in data store using CrudRepository.
	 * 
	 * @param user
	 *            object with details for storage.
	 */
	public void saveUser(User user);

	/**
	 * The function updates an existing user. Currently only firstName and
	 * lastName only can be updated.
	 * 
	 * @param user
	 *            id of the user of which profile need to be updated.
	 * @param user
	 *            object with modified details.
	 */
	public void updateUser(Integer id, User user);

	/**
	 * Function to check if a user with given unique user id exists in the data
	 * store.
	 * 
	 * @param user
	 *            id of the user.
	 * @return True/False depending on if the user exists or not.
	 */
	public boolean exists(Integer id);

	/**
	 * The function authenticates a user id by comparing it with the logged in
	 * user which is fetched from the Authentication of the
	 * SecurityContextHolder.
	 * 
	 * @param authentication
	 * @param user
	 *            id of the user to be authenticated.
	 * @return True/False depending on if the user is authentic or not.
	 */
	public boolean validateUser(Authentication authentication, Integer id);

	/**
	 * This function returns all the user details present in the data store.
	 * 
	 * @return list of all users present in the data store.
	 */
	public List<User> getAllUsers();
}