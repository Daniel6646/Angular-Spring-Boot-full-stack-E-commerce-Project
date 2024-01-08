package com.luv2code.ecommerce.dao;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.luv2code.ecommerce.entity.Product;

//@CrossOrigin("http://localhost:4200") // only accept calls from web browser for this origin/port
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByCategoryId(@Param("id")Long id, org.springframework.data.domain.Pageable pageable );
	// spring rest exposed endpoint
	//to check after running spring boot
	//till api in lik described in app.properties file
	//http://localhost:8080/api/product-category
	//localhost:8080/api/product/search/findByCategoryId?Id=2
	
	//he REST API url for search/findByCategoryId is provided automatically by Spring Data REST. When using Spring Data REST, it will automatically expose API for you based on code in your Repository interface ... in our example we are using ProductRepository.
	// Also, the "/search" is provided by Spring Data REST. By default, any finder method defined in the repository will be exposed as an API.
	
	Page<Product> findByNameContaining(@Param("name") String name, org.springframework.data.domain.Pageable pageable );
	//convrtd in query like  [ containing -- like operation in sql ]
	// select * from Product p where p.name like concat ('%', name, '%');
	//http://localhost:8080/api/products/search/findByNameContaining?name=Python





}
