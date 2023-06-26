package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Asset;
import java.util.List;


@Repository(value = "assetRepository")
public interface AssetRepository extends JpaRepository<Asset, Integer>{
	// nativeQuery default false
    @Query(nativeQuery = false, value = "SELECT a FROM Asset a WHERE a.invid = ?1")
    public List<Asset> findByInvid(@Param("invid") Integer invid);
    
}