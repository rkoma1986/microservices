package com.example.playerservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.playerservice.model.Player;
import com.example.playerservice.service.PlayerService;

@RestController
@RequestMapping("api/players")
public class PlayerController {

	@Autowired
	PlayerService playerService;
	
	@GetMapping
	public Set<Player> getAll() {
		
		return playerService.getAllPlayers();
	}
	
	@GetMapping("/{id}")
	public Player getById(@PathVariable("id") Integer id) {
		return playerService.getPlayerById(id);
	}
	
	@GetMapping("/team/{teamId}")
	public Set<Player> getByTeam(@PathVariable("teamId") Integer teamId) {
		return playerService.getPlayersByTeam(teamId);
	}
	
	@PostMapping("/create")
	public Player createTeam(@RequestBody Player team) {
		return playerService.createPlayer(team);
	}
}
