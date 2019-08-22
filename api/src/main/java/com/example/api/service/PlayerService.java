package com.example.api.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.model.Player;

@Service
public class PlayerService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${player-service.path}")
	String playerServicePath;
	
	public Set<Player> getAllPlayers() {
		Set<Player> players = restTemplate.exchange(
				playerServicePath + "/api/players",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Set<Player>>(){})
				.getBody();
		
		return players;
	}
	
	public Set<Player> getByTeamId(Integer teamId) {
		Set<Player> players = restTemplate.exchange(
				playerServicePath + "/api/players/team/" + teamId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Set<Player>>(){})
				.getBody();
		
		return players;
	}
	
	public Player getById(Integer id) {
		Player player = restTemplate.getForObject(playerServicePath + "/api/players/" + id, Player.class);
		
		return player;
	}
}
