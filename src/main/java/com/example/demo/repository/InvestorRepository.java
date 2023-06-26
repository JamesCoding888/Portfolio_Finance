package com.example.demo.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Investor;

@Repository // e.q. @Repository(value = "investorRepository")
public interface InvestorRepository extends JpaRepository<Investor, Integer> {
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Investor SET balance=?2, email=?3, username=?4 WHERE id=?1")
	public void update(@Param("id") Integer id, @Param("balance") Integer balance, @Param("email") String email, @Param("username") String username);
	
	
	
	
	
}