package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.luv2code.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String theEmail);
	//behind d scn select * from Customer c where c.email = theEmail

	
	
//no @RepositoryRestResource hence it will not be exposed as REST API based on our configuration	
//means cant access data in browser using url as in countryRepstry	
}
