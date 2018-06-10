package com.pedro.sw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro.sw.model.Planet;

public interface PlanetsRepository extends JpaRepository<Planet, Long>{
	
	public Planet findByNameContainingIgnoreCase(String name);	
	
}
