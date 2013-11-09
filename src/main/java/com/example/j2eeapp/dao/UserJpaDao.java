package com.example.j2eeapp.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.example.j2eeapp.commons.dao.GenericJpaDao;
import com.example.j2eeapp.domain.UserEntity;


/**
 * Data access object Jpa implementation to work with User Entity database operations
 * @author GilMabel
 */
public class UserJpaDao extends GenericJpaDao<UserEntity,Long> implements UserDao {

	public UserJpaDao() {
		super(UserEntity.class);
	}
	
	/**
	 * Queries database for user name availability
	 * @param userName
	 * @return true if the username is available
	 */
	public boolean checkAvailable(String userName) {
		Assert.notNull(userName);
		
        Query query = getEntityManager().createQuery("select count(*) from " + getPersistentClass().getSimpleName() 
                                + " u where u.userName = :userName").setParameter("userName", userName);
    
        Long count = (Long) query.getSingleResult(); 
        
        return count < 1;
	}
	
	/** 
	 * Queries database for a username
	 * @param userName
	 * @return user entity
	 */
	public UserEntity loadUserByUserName(String userName) {
        Assert.notNull(userName);
        UserEntity user = null;
        Query query = getEntityManager().createQuery("select u from " + getPersistentClass().getSimpleName()
                        + " u where u.userName = :userName").setParameter("userName", userName);
        try {
                user = (UserEntity) query.getSingleResult();
        } catch(NoResultException e) {
        	//Do Nothing
        }
        return user;
	}
}
