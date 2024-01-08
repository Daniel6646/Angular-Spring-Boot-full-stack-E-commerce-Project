package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.luv2code.ecommerce.entity.Country;


//@CrossOrigin("http://localhost:4200") code added in MyAppconfig for this
//@RepositoryRestResource means we want to expose * acess its data with help of url below
@RepositoryRestResource(collectionResourceRel = "countries",path = "countries")
public interface CountryRepositiry extends JpaRepository<Country , Integer> {

//path = "countries" gets data and anendpoint to display the data 
//  /countries gets added in url used to retrieve data
// http://localhost:8080/api/countries	
	
// Country repo means there is a table named country
//so based on this we can access data that is in db with help of spring data rest 
// http://localhost:8080/api/countries country table name, path = "countries" to see its data

	
}
