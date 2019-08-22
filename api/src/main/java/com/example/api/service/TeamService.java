package com.example.api.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.model.Team;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class TeamService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${team-service.path}")
	String teamServicePath;
	
	@HystrixCommand(fallbackMethod = "fallbackGetAllTeams",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "100000")
			}
	)
	public Set<Team> getAllTeams() {
		Set<Team> teams = restTemplate.exchange(
				teamServicePath + "/api/teams",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<LinkedHashSet<Team>>(){})
				.getBody();
		
		return teams;
	}
	
	public Set<Team> fallbackGetAllTeams() {
		Set<Team> response = new LinkedHashSet<Team>();
		response.add(new Team(0, "No teams", ""));
		
		return response;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackGetById",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "100000")
			}
	)
	public Team getById(Integer id) {
		Team team = restTemplate.getForObject(teamServicePath + "/api/teams/" + id, Team.class);
		return team;
	}
	
	public Team fallbackGetById(Integer id) {
		return new Team(0, "No Team", "");
	}
}
