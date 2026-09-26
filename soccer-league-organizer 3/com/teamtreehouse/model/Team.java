package com.teamtreehouse.model;


import java.util.Set;
import java.util.HashSet;

public class Team {
  private String teamName;
  private String coachName;
  private Set<Player> players;
  
//constructor

  public Team (String teamName, String coachName) {
    this.teamName = teamName;
    this.coachName = coachName;
    this.players = new HashSet<>();
  }

//Getters

  public String getTeamName() {
    return teamName;
  } 

public String getCoachName() {
  return coachName;
  }
  
  public Set<Player> getPlayers() {
  return players;
  }
  
  public void addPlayer (Player player) {
    if (players.size() < 11) {
      players.add(player);
      
    } else {
       System.out.println("Sorry, team is full.");
    }
  }
  
  public void removePlayer (Player player) {
    players.remove(player);
  }
       
   
  }
  




