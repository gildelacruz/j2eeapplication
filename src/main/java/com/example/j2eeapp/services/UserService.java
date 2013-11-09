package com.example.j2eeapp.services;

import com.example.j2eeapp.domain.UserEntity;


/**
 * 
 * Service providing service method to work with user data and entity 
 * @author GilMabel
 *
 */

public interface UserService {


	/**
	 * 	@param userEntity
	 * 	@return true if successful
	 */
	boolean createUser(UserEntity userEntity);
	
}
