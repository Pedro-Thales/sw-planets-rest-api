package com.pedro.sw;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.pedro.sw.model.Planet;
import com.pedro.sw.repository.PlanetsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwPlanetsRestApiApplication.class)
@WebAppConfiguration
public class SwPlanetsRestApiApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON_UTF8.getType(),
			MediaType.APPLICATION_JSON_UTF8.getSubtype());
	
	private MockMvc mockMvc;
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private Planet planet;
	
	@Autowired
	private PlanetsRepository planets;
	
	@Autowired
	private WebApplicationContext context;
	
	@SuppressWarnings("unchecked")
	protected String json(Planet planet) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(
                planet, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(context).build();
        
        planets.deleteAll();
        
        planet = new Planet();
        
        planet.setName("Daggoba");
    	planet.setTerrain(Arrays.asList("deserto", "nada"));
    	planet.setClimate(Arrays.asList("chato", "umido"));

        planet = planets.save(planet);
        
    }
	
	@Test
    public void planetNotFound() throws Exception {
        mockMvc.perform(post("/planets/search?id=5")
                .content(this.json(new Planet()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void planetFound() throws Exception {
        mockMvc.perform(post("/planets/search?name=Daggoba")
                .content(this.json(new Planet()))
                .contentType(contentType))
                .andExpect(status().isOk());
    }
	
	
	@Test
    public void createPlanetNotInApi() throws Exception {
		
		planet = new Planet();
		
		planet.setName("Teste");
    	planet.setTerrain(Arrays.asList("deserto", "nada"));
    	planet.setClimate(Arrays.asList("chato", "umido"));

		
		String planetJson = json(planet);
		
        mockMvc.perform(post("/planets/create")
                .content(planetJson)
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void createPlanetInApi() throws Exception {
		
		planet = new Planet();
		
		planet.setName("Alderaan");
    	planet.setTerrain(Arrays.asList("grassland", "mountains"));
    	planet.setClimate(Arrays.asList("temperate"));

		String planetJson = json(planet);
		
        mockMvc.perform(post("/planets/create")
                .content(planetJson)
                .contentType(contentType))
                .andExpect(status().isCreated());
    }
	
	@Test
    public void createPlanetWithMultipleAnswersFromApi() throws Exception {
		
		planet = new Planet();
		
		planet.setName("da");
    	planet.setTerrain(Arrays.asList("mountains"));
    	planet.setClimate(Arrays.asList("temperate"));

		String planetJson = json(planet);
		
        mockMvc.perform(post("/planets/create")
                .content(planetJson)
                .contentType(contentType))
                .andExpect(status().isMultipleChoices());
    }
	
}
