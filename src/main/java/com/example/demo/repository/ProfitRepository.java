package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entity.Profit;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value = "profitRepository")
public interface ProfitRepository extends JpaRepository<Profit, Integer>{
	// nativeQuery default false
	@Query(nativeQuery = false, value = "SELECT p FROM Profit p WHERE p.invid = ?1")
    public List<Profit> findByInvId(@Param("invid") Integer invid);
}