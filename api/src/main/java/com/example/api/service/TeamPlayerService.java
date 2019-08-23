package com.example.api.service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.exception.EntityNotFoundException;
import com.example.api.model.Player;
import com.example.api.model.Team;

@Service
public class TeamPlayerService {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	PlayerService playerService;

	public Set<Team> getAllTeams() {
		Set<Team> teams = teamService.getAllTeams();
		Set<Player> players = playerService.getAllPlayers();
		
		teams.stream()
					.forEach(t -> t.setPlayers(
							players.stream()
								.filter(p -> p.getTeamId() == t.getId())
								.sorted((p1, p2) -> p1.getShirtNumber().compareTo(p2.getShirtNumber()))
								.collect(Collectors.toCollection(LinkedHashSet::new))));
		
		return teams;
	}
	
	public Team getTeamById(Integer id) throws EntityNotFoundException {
		Team team = teamService.getById(id);
		if (team == null) {
			throw new EntityNotFoundException(Team.class, "id", id.toString());
		}
		team.setPlayers(playerService.getByTeamId(id));

		return team;
	}
	
	public Player getPlayerById(Integer id) {
		Player player = playerService.getById(id);
		player.setTeam(teamService.getById(player.getTeamId()));
		
		return player;
	}
	
	public Player createPlayer(Player player) throws EntityNotFoundException {
		if (player.getTeamId() != null && player.getTeamId() != 0) {
			Team team = teamService.getById(player.getTeamId());
			if (team == null) {
				throw new EntityNotFoundException(Team.class, "id", player.getTeamId().toString());
			}
		}
		return playerService.createPlayer(player);
	}
}
