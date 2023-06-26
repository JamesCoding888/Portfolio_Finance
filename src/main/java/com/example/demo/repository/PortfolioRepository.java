package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

}
