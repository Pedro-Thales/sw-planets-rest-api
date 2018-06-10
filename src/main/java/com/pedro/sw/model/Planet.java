package com.pedro.sw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Planet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@NotEmpty
	@ElementCollection
	private List<String> climate;

	@NotEmpty
	@ElementCollection
	private List<String> terrain;

	@NotNull
	private int filmsAppeared;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getClimate() {
		return climate;
	}

	public void setClimate(List<String> climate) {
		this.climate = climate;
	}

	public List<String> getTerrain() {
		return terrain;
	}

	public void setTerrain(List<String> terrain) {
		this.terrain = terrain;
	}

	public int getFilmsAppeared() {
		return filmsAppeared;
	}

	public void setFilmsAppeared(int filmsAppeared) {
		this.filmsAppeared = filmsAppeared;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + filmsAppeared;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (filmsAppeared != other.filmsAppeared)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (terrain == null) {
			if (other.terrain != null)
				return false;
		} else if (!terrain.equals(other.terrain))
			return false;
		return true;
	}

}
