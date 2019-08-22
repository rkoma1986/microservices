package com.example.api.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.model.Player;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class PlayerService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${player-service.path}")
	String playerServicePath;
	
	@HystrixCommand(fallbackMethod = "fallbackGetAllPlayers",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "100000")
			}
	)
	public Set<Player> getAllPlayers() {
		Set<Player> players = restTemplate.exchange(
				playerServicePath + "/api/players",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Set<Player>>(){})
				.getBody();
		
		return players;
	}
	
	public Set<Player> fallbackGetAllPlayers() {
		Set<Player> response = new LinkedHashSet<Player>();
		response.add(new Player(0, "No player", null, 0));
		
		return response;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackGetByTeamId",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "100000")
			}
	)
	public Set<Player> getByTeamId(Integer teamId) {
		Set<Player> players = restTemplate.exchange(
				playerServicePath + "/api/players/team/" + teamId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Set<Player>>(){})
				.getBody();
		
		return players;
	}
	
	public Set<Player> fallbackGetByTeamId(Integer teamId) {
		Set<Player> response = new LinkedHashSet<Player>();
		response.add(new Player(0, "No player", null, 0));
		
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
	public Player getById(Integer id) {
		Player player = restTemplate.getForObject(playerServicePath + "/api/players/" + id, Player.class);
		
		return player;
	}
	
	public Player fallbackGetById(Integer id) {
		return new Player(0, "No player", null, 0);
	}
}
