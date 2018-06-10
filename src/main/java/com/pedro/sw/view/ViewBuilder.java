package com.pedro.sw.view;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.pedro.sw.model.Planet;

public class ViewBuilder extends ModelAndView {

	public static ModelAndView createAlreadyExist(Planet planet) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.addObject("name", planet.getName());
		mav.addObject("err_code", 500);
		mav.setStatus(HttpStatus.CONFLICT);
		mav.addObject("description", "Planeta " + planet.getName() + " já existe");
		return mav;
	}

	public static ModelAndView notFoundError(Object name) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.addObject("search term", name);
		mav.addObject("err_code", 404);
		mav.setStatus(HttpStatus.NOT_FOUND);
		mav.addObject("description", "Planeta não encontrado");
		return mav;
	}

	public static ModelAndView createView(Planet planet, String message, HttpStatus status) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.setStatus(status);
		mav.addObject("message", message);
		mav.addObject("id", planet.getId());
		mav.addObject("name", planet.getName());
		mav.addObject("climate", planet.getClimate());
		mav.addObject("terrain", planet.getTerrain());
		mav.addObject("filmsAppeared:", planet.getFilmsAppeared());
		return mav;
	}
	
	public static ModelAndView showPlanetView(Planet planet) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.setStatus(HttpStatus.OK);
		mav.addObject("id", planet.getId());
		mav.addObject("name", planet.getName());
		mav.addObject("climate", planet.getClimate());
		mav.addObject("terrain", planet.getTerrain());
		mav.addObject("filmsAppeared:", planet.getFilmsAppeared());
		return mav;
	}
	
	public static ModelAndView deleteView(String message, HttpStatus status) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.setStatus(status);
		mav.addObject("message", message);
		return mav;
	}

}
