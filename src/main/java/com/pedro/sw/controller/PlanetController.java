package com.pedro.sw.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedro.sw.exceptions.MultipleResultsFoundException;
import com.pedro.sw.exceptions.NoResultsFoundException;
import com.pedro.sw.model.Planet;
import com.pedro.sw.repository.PlanetsRepository;
import com.pedro.sw.service.PlanetService;
import com.pedro.sw.view.ViewBuilder;

@Controller
@RequestMapping("/planets")
public class PlanetController {

	@Autowired
	private PlanetsRepository planets;

	@GetMapping
	public ResponseEntity<Collection<Planet>> getPlanets() {
		return new ResponseEntity<>((Collection<Planet>) planets.findAll(), HttpStatus.OK);
	}

	@GetMapping("/create")
	public ModelAndView create(Planet planet) {
		ModelAndView mv = new ModelAndView("planets/create");
		mv.addObject(planet);
		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@RequestBody Planet planet) {

		try {
			planet = planets.save(planet);
			planet.setFilmsAppeared(PlanetService.getFilmsAppearedFromApi(planet.getName()));
			planet = planets.save(planet);
			return ViewBuilder.createView(planet, "Salvo com sucesso", HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return ViewBuilder.createAlreadyExist(planet);
		} catch (MultipleResultsFoundException e) {
			return ViewBuilder.createView(planet,
					"Planeta Salvo mas não foi possível obter o número de aparições pois a api retornou mais de um resultado para esse nome",
					HttpStatus.MULTIPLE_CHOICES);
		} catch (IOException | RestClientException e) {
			return ViewBuilder.createView(planet,
					"Planeta Salvo mas não foi possível obter o número de aparições pois não foi possível chamar a api",
					HttpStatus.SERVICE_UNAVAILABLE);
		} catch (NoResultsFoundException e) {
			return ViewBuilder.createView(planet,
					"Planeta Salvo mas não foi possível obter o número de aparições pois a api não retornou resultados para esse nome",
					HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/search", params = "name")
	public ModelAndView search(@RequestParam String name) {
		Planet planet = planets.findByNameContainingIgnoreCase(name);
		if (planet != null) {
			return ViewBuilder.showPlanetView(planet);
		}
		return ViewBuilder.notFoundError(name);
	}

	@RequestMapping(value = "/search", params = "id")
	public ModelAndView search(@RequestParam long id) {
		Optional<Planet> planet = planets.findById(id);
		if (planet.isPresent()) {
			return ViewBuilder.showPlanetView(planet.get());
		}
		return ViewBuilder.notFoundError(id);
	}

	@DeleteMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			planets.deleteById(id);
		} catch (Exception e) {
			return ViewBuilder.deleteView("Planeta não encontrado", HttpStatus.NOT_FOUND);
		}
		attributes.addFlashAttribute("mensagem", "Planet deletado com sucesso");
		return ViewBuilder.deleteView("Planeta deletado com sucesso.", HttpStatus.OK);

	}

}
