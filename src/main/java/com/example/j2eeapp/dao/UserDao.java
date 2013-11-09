package com.example.j2eeapp.dao;

import com.example.j2eeapp.commons.dao.GenericDao;
import com.example.j2eeapp.domain.UserEntity;


/**
 * Data access object interface to work with User Entity database operations
 * @author GilMabel
 */
public interface UserDao extends GenericDao<UserEntity, Long> {

	/**
	 * Queries database for user name availability
	 * @param userName
	 * @return true if the username is available
	 */
	boolean checkAvailable (String userName);
	
	/**
	 * 
	 * Queries database for a username
	 * @param userName
	 * @return user entity
	 */
	UserEntity loadUserByUserName(String userName);
	
}
