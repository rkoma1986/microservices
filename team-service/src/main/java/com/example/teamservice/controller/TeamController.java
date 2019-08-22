package com.example.teamservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teamservice.model.Team;
import com.example.teamservice.service.TeamService;

@RestController
@RequestMapping("api/teams")
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@GetMapping
	public Set<Team> getAllTeams(){
		return  teamService.getAllTeams();
	}
	
	@GetMapping("/{id}")
	public Team getById(@PathVariable("id") Integer id) {
		return teamService.getTeamById(id);
	}
	
	@GetMapping("/country/{countryName}")
	public Set<Team> getByCountry(@PathVariable("countryName") String countryName) {
		return teamService.getTeamsByCountry(countryName);
	}
}
