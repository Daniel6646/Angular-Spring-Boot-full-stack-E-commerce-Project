package com.luv2code.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.luv2code.ecommerce.entity.State;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

	
	List<State> findByCountryCode(@Param("code") String code);
	//To retrieve states for a given country code
	//http://localhost:8080/api/states/search/findByCountryCode?code=IN	 //code here is a field name in country table
	
	//http://localhost:8080/api/states
}
