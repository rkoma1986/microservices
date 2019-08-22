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

@Service
public class TeamService {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${team-service.path}")
	String teamServicePath;
	
	public Set<Team> getAllTeams() {
		Set<Team> teams = restTemplate.exchange(
				teamServicePath + "/api/teams",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<LinkedHashSet<Team>>(){})
				.getBody();
		
		return teams;
	}
	
	public Team getById(Integer id) {
		Team team = restTemplate.getForObject(teamServicePath + "/api/teams/" + id, Team.class);
		return team;
	}
}
