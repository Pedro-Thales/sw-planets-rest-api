package com.pedro.sw.service;

import java.io.IOException;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.sw.exceptions.MultipleResultsFoundException;
import com.pedro.sw.exceptions.NoResultsFoundException;

public class PlanetService {

	public static int getFilmsAppearedFromApi(String nome)
			throws MultipleResultsFoundException, IOException, NoResultsFoundException, RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		System.setProperty("http.agent", "Chrome");
		
		String response = restTemplate.getForObject("https://swapi.co/api/planets/?search=" + nome, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode nodes = mapper.readTree(response);
		if (nodes.get("count").asInt() > 1) {
			throw new MultipleResultsFoundException("More than one result found for the name: " + nome);
		}

		if (nodes.get("count").asInt() == 0) {
			throw new NoResultsFoundException("No results found for the name: " + nome);
		}
		
		int aparisons = nodes.withArray("results").get(0).withArray("films").size();
		return aparisons;

	}

}
