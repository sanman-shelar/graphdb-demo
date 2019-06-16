package com.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/movie")
	public Movie getMovie(@RequestParam String title) {
		Movie result = movieRepository.findByTitle(title);
		return result;
	}
	
	@GetMapping
	public Collection<Movie> getAllMovies(){
		return (Collection<Movie>) movieRepository.findAll();
	}

	// load mock data
	@Bean
	public void loadData() {
		
		movieRepository.deleteAll();
		personRepository.deleteAll();
		
		Movie italianJob = new Movie();
		italianJob.setTitle("The Italian Job");
		italianJob.setReleased(1999);
		movieRepository.save(italianJob);

		Person mark = new Person();
		mark.setName("Mark Wahlberg");
		personRepository.save(mark);

		Role charlie = new Role();
		charlie.setMovie(italianJob);
		charlie.setPerson(mark);
		Collection<String> roleNames = new HashSet<>();
		roleNames.add("Charlie Croker");
		charlie.setRoles(roleNames);
		List<Role> roles = new ArrayList<>();
		roles.add(charlie);
		italianJob.setRoles(roles);
		movieRepository.save(italianJob);
	}
}
