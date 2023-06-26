package com.example.demo.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Classify;

/*
	Annotate the repository:
	
		1. If you only annotate the repository interface with @Repository without specifying the value attribute, 
	
	   	   Spring Boot will assign a default bean name to the repository based on the naming convention.
	
	   	2. The default bean name will be the uncapitalized version of the repository interface name. 
		
		   		@Repository(value = "classifyRepository")
	
	   	3. However, if you want to provide a custom bean name for the repository, you can specify the value attribute of the @Repository annotation.
	
	Inheritance of JpaRepository interface:
		
		1. The ClassifyRepository interface inherits various CRUD methods from the JpaRepository interface, such as save(), findById(), findAll(), deleteById(), etc. 
		
		   These methods can be used to perform standard CRUD operations on the Classify entity without explicitly defining them.
		
*/

@Repository // e.q. @Repository(value = "classifyRepository")
public interface ClassifyRepository extends JpaRepository<Classify, Integer> {
	/*
		This method defines a custom update operation for the Classify entity. 
		
		Annotation:
			
			1. @Transactional annotation to ensure that the update operation is executed within a transaction.
		
		    2. @Modifying annotation indicates that the query modifies data.
		
		    3. @Query annotation is used to define the custom SQL query. In this case, it updates the Classify table, setting the name and tx columns based on the provided parameters.
		
		Attribute:
			
			1. The nativeQuery attribute is set to true to indicate that the query is a native SQL query.
		
		Method parameters with annotation:
		
			1. @Param, which specifies the named parameters used in the query.
			
	*/
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Classify SET name=?2, tx=?3 WHERE id=?1")
	public void update(@Param("id") Integer id, @Param("name") String name, @Param("tx") Boolean tx);
	
	
	
	
	
}
