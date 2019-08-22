package com.example.playerservice.service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.playerservice.model.Player;

@Service
public class PlayerService {

	static Set<Player> players = new LinkedHashSet<>();
	
	static {
		players.add(new Player(1, "Gerard", "Pique", 3, 1));
		players.add(new Player(2, "Jordi", "Alba", 18, 1));
		players.add(new Player(3, "Lionel", "Messi", 10, 1));
		players.add(new Player(4, "Milan", "Borjan", 82, 2));
		players.add(new Player(5, "Marko", "Gobeljic", 77, 2));
		players.add(new Player(6, "Marko", "Marin", 10, 2));
		players.add(new Player(7, "Dejan", "Lovren", 32, 3));
		players.add(new Player(8, "Mohamed", "Salah", 11, 3));
		players.add(new Player(9, "Roberto", "Firmino", 9, 3));
		players.add(new Player(10, "Javi", "Martinez", 8, 4));
		players.add(new Player(11, "Jerome", "Boateng", 17, 4));
		players.add(new Player(12, "Phillipe", "Coutinho", 10, 4));
		players.add(new Player(13, "Alex", "Sandro", 12, 5));
		players.add(new Player(14, "Luka", "Coccolo", 28, 5));
		players.add(new Player(15, "Emre", "Can", 23, 5));
		players.add(new Player(16, "Toni", "Lato", 3, 6));
		players.add(new Player(17, "Jorrit", "Hendrix", 8, 6));
		players.add(new Player(18, "Donyell", "Malen", 9, 6));
		players.add(new Player(19, "Thiago", "Silva", 2, 7));
		players.add(new Player(20, "Colin", "Dagba", 31, 7));
		players.add(new Player(21, "Edinson", "Cavani", 9, 7));
		players.add(new Player(22, "Sergio", "Ramos", 4, 8));
		players.add(new Player(23, "Karin", "Benzema", 9, 8));
		players.add(new Player(24, "Gereth", "Bale", 11, 8));
		players.add(new Player(25, "Ranko", "Veselinovic", 3, 9));
		players.add(new Player(26, "Nemanja", "Vucic", 11, 9));
		players.add(new Player(27, "Nikola", "Drincic", 18, 9));
		players.add(new Player(28, "Rob", "Holding", 16, 10));
		players.add(new Player(29, "David", "Luiz", 23, 10));
		players.add(new Player(30, "Mesut", "Ozil", 10, 10));
	}
	
	public Set<Player> getAllPlayers() {
		return players;
	}
	
	public Set<Player> getPlayersByTeam(int teamId) {
		return players.stream()
				.filter(p -> p.getTeamId() == teamId)
				.collect(Collectors.toSet());
	}
	
	public Player getPlayerById (int id) {
		return players.stream()
				.filter(p -> p.getId() == id)
				.findFirst()
				.get();
	}
}
