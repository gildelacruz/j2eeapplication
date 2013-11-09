package com.example.j2eeapp.commons.dao;

import java.io.Serializable;
import java.util.List;


/**
 * Generic Interface for Data Access Objects. To be extended or implemented 
 * Contains common persistence methods
 * @author GilMabel
 *
 */
public interface GenericDao <T, ID extends Serializable> {

	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T findByID (ID id);
	List<T> findAll();
	void flush();
	
}
