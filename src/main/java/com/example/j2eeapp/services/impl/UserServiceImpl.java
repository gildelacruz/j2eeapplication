package com.example.j2eeapp.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.j2eeapp.dao.UserDao;
import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserService;



/**
 * Service providing service method to work with user data and entity 
 * @author GilMabel
 *
 */


public class UserServiceImpl implements UserService, UserDetailsService{

		private UserDao userDao;
	
	/**
	 * 	@param userEntity
	 * 	@return true if successful
	 */
	
	public boolean createUser(UserEntity userEntity) {
		userDao.save(userEntity);
		return true;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String userName)  throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
