package com.example.teamservice.service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.teamservice.model.Team;

@Service
public class TeamService {
	static Set<Team> teams = new LinkedHashSet<>();
	
	static {
		
		teams.add(new Team(1, "FC Barcelona", "Spain"));
		teams.add(new Team(2, "Crvena Zvezda", "Serbia"));
		teams.add(new Team(3, "Liverpool", "England"));
		teams.add(new Team(4, "Bayern Munich", "Germany"));
		teams.add(new Team(5, "Juventus", "Italy"));
		teams.add(new Team(6, "PSV", "Holland"));
		teams.add(new Team(7, "PSG", "France"));
		teams.add(new Team(8, "Real Madrid", "Spain"));
		teams.add(new Team(9, "Vojvodina", "Serbia"));
		teams.add(new Team(10, "Arsenal", "England"));
		teams.add(new Team(11, "Borussia Dortmund", "Germany"));
		teams.add(new Team(12, "Lazio", "Italy"));
		teams.add(new Team(13, "NAC Breda", "Holland"));
		teams.add(new Team(14, "Nant", "France"));
	}
	
	public Set<Team> getAllTeams() {
		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		return teams;
	}
	
	public Team getTeamById(int id) {
		
		return teams.stream()
				.filter(t -> t.getId() == id)
				.findFirst()
				.get();	
	}
	
	public Set<Team> getTeamsByCountry(String country) {
		return teams.stream()
				.filter(t -> t.getCountry().equals(country))
				.collect(Collectors.toSet());
	}
	
	public Team createTeam(Team team) {
		Integer id = teams.stream()
						.max(Comparator.comparing(Team::getId))
						.get().getId() + 1;
		team.setId(id);
		teams.add(team);
		
		return team;
	}
}
