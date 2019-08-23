package com.example.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.exception.EntityNotFoundException;
import com.example.api.model.Player;
import com.example.api.model.Team;
import com.example.api.service.PlayerService;
import com.example.api.service.TeamPlayerService;
import com.example.api.service.TeamService;

@RestController
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	TeamPlayerService teamPlayerService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	PlayerService playerService;

	@GetMapping("/teams-players")
	public ResponseEntity<Set<Team>> getAllTeamsWithPlayers() {
		Set<Team> teams = teamPlayerService.getAllTeams();
		
		return new ResponseEntity<Set<Team>>(teams, HttpStatus.OK);
	}
	
	@GetMapping("team/{id}")
	public ResponseEntity<Team> getTeamById(@PathVariable("id") Integer id) throws EntityNotFoundException {
		Team team = teamPlayerService.getTeamById(id);
		
		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}
	
	@GetMapping("player/{id}")
	public ResponseEntity<Player> getPlayerById(@PathVariable("id") Integer id) {
		Player player = teamPlayerService.getPlayerById(id);
		
		return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	@PostMapping("team/create")
	public ResponseEntity<Team> createTeam(@RequestBody Team team) {
		Team savedTeam = teamService.saveNewTeam(team);
		
		return new ResponseEntity<Team>(savedTeam, HttpStatus.OK);
	}
}
