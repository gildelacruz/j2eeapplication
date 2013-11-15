package com.example.j2eeapp.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

		
		if (!userDao.checkAvailable(userEntity.getUserName())) {
			getFacesContext().addMessage(null, constructErrorMessage(String.format("User name '%s' is not available", userEntity.getUserName()), null));
			return false;
		}
		
		try {
			userDao.save(userEntity);
		} catch (Exception e) {
			getFacesContext().addMessage(null, constructFatalMessage(String.format("Issue adding user '%s' message is '%s'", userEntity.getUserName(),e.getMessage()), null));			
			return false;
		}
		
		return true;
	}

	public UserDetails loadUserByUsername(String userName)  throws UsernameNotFoundException {
		UserEntity user = userDao.loadUserByUserName(userName);
		
		if (user == null){
			throw new UsernameNotFoundException(String.format("No such user with name provided '%s'", userName));
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		User userDetails = new User(user.getUserName(),user.getPassword(),authorities);
		return userDetails;
	}
	
	protected FacesMessage constructErrorMessage (String message, String detail){
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail);
	}

	protected FacesMessage constructInfoMessage (String message, String detail){
		return new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail);
	}
	
	protected FacesMessage constructFatalMessage (String message, String detail){
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail);
	}
	
	protected FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
