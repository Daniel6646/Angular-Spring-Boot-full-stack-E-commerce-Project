package com.luv2code.ecommerce.config;

import java.util.ArrayList;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.luv2code.ecommerce.entity.Country;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	//allowed.origins =http://localhost:4200 in application.properties
	@Value("${allowed.origins}")
	private String allowedOrigins[];
	
	@Autowired
	EntityManager entityManager;
	
	
	public MyDataRestConfig(EntityManager theEntityManager) {
		
		this.entityManager =  theEntityManager;
	}
	
	
	
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors ) {
		
		HttpMethod [] theUnSupportedActions = {HttpMethod.POST,
				HttpMethod.PUT,
				HttpMethod.DELETE,
				HttpMethod.PATCH};
 		
		// disable HTTP methods for products post,put, delete
		
		//making classe readonly avoid post,put,delete etc
		disableHttpMethods(Product.class, config, theUnSupportedActions);
		disableHttpMethods(ProductCategory.class, config, theUnSupportedActions);
		disableHttpMethods(Country.class, config, theUnSupportedActions);
		disableHttpMethods(State.class, config, theUnSupportedActions);
		disableHttpMethods(Order.class, config, theUnSupportedActions);

		
		//call internal helper method to expose entity IDs
		exposeIds(config);
		
		// configure cors mapping 
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins); 
//		@Value("${allowed.origins}")
//		private String allowedOrigins[];
		//** recursing for all sub app
		//with ths we can remove @crossorigin from jparepos
	
	}



	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnSupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theClass)
		.withItemExposure((metadata,HttpMethods) -> HttpMethods.disable(theUnSupportedActions))
		.withCollectionExposure((metadata,HttpMethods) -> HttpMethods.disable(theUnSupportedActions));
	}





	private void exposeIds( RepositoryRestConfiguration config) {

        // expose entity ids
        //

        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }



}
