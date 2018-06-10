package com.pedro.sw;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pedro.sw.model.Planet;
import com.pedro.sw.repository.PlanetsRepository;

@SpringBootApplication
public class SwPlanetsRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwPlanetsRestApiApplication.class, args);
	}
	
/*	@Bean
    public CommandLineRunner initializeDb(Planets planets){
        return (args) -> {
        	Planet planet = new Planet();
        	planet.setName("DAGGOBA");
        	planet.setFilmsAppeared(5);
        	planet.setTerrain(Arrays.asList("Deserto", "nada"));
        	planet.setClimate(Arrays.asList("chato", "umido"));
            planets.save(planet);
        };
	}*/

}
