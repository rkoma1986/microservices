package com.example.api.model;

public class Player {

	private int id;
	private String name;
	private String lastName;
	private Integer shirtNumber;
	private Team team;
	private Integer teamId;
	
	public Player() {
		super();
	}
	
	public Player(int id, String name, String lastName, int shirtNumber) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.shirtNumber = shirtNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(Integer shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
}
