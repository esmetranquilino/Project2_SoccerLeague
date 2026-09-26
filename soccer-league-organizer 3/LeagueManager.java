import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class LeagueManager {
    

  public static void main(String[] args) {
    //Scanner
    
    Scanner scanner = new Scanner(System.in);
    
    // list of players given to us
    
    Player[] players = Players.load();
    System.out.println();
    System.out.printf("There are currently %d registered players.%n", players.length);
    
    // allTeams collection
    
    List<Team> allTeams = new ArrayList<>();
    
    //Creating header
    
    System.out.println("----------------------");
    System.out.println("WELCOME TO SOCCER LEAGUE");
    System.out.println("----------------------");
    System.out.println();
    
    //Video shows menu as reoccurring. Let's do that here: 
    
    while (true) {
      //MENU 
      
      System.out.println("Menu");
      System.out.println();
    
      System.out.println("Create - Create New Team");
      System.out.println("Add - Add Players to Team");
      System.out.println("Remove - Remove Player from Team");
      System.out.println("Report - View Team's Height Report");
      System.out.println("Balance - View League Balance Report");
      System.out.println("Roster - View Roster");
    
      System.out.println("----------------------");
      System.out.println();
      System.out.printf("Select an option: ");
      String menuChoice = scanner.nextLine();
      System.out.println();
      
      // MENU DECISION: CREATE
      
      if (menuChoice.equalsIgnoreCase("Create")) {
      
        System.out.printf("Enter Team Name: ");
        String userTeamName = scanner.nextLine();
    
        System.out.printf("Enter Coach Name: ");
        String userCoachName = scanner.nextLine();
      
        //Team Object
    
        Team newTeam = new Team(userTeamName, userCoachName);
      
        // Store team in allTeams Collection
        allTeams.add(newTeam);
      
        //Confirmation of team creation
        System.out.println();
        System.out.printf("Team %s coached by %s was successfully added!%n", userTeamName, userCoachName);
        System.out.println("----------------------");
        
        // NEXT: ADD
      } else if (menuChoice.equalsIgnoreCase("Add")) {
      
        //Make sure a team exists
        
        if (allTeams.isEmpty()) {
          System.out.println("No teams available. Please create team.");
          
         // Teams already created  
        } else {
          
          // Displaying list of existing teams
            
          List<Team> sortedTeams = displayTeamOrder(allTeams);
          
          int number = 1; 
          for (Team team : sortedTeams) {
            System.out.printf("%d. %s%n", number, team.getTeamName());
            number++;
          }
         
          
          //Selecting a team 
          
          System.out.printf("Select a Team: ");
          int userTeamSelection = scanner.nextInt();
          System.out.println();
         
        // Gets selected team ^
    
          Team selectedTeam = sortedTeams.get(userTeamSelection -1);
          
          // Display list of available players
          
          List<Player> playerList = Arrays.asList(players);
          
          List<Player> sortedPlayers = displayPlayerOrder(playerList);
  
          int numCounter = 1;
          for(Player existingPlayer : sortedPlayers) {
            System.out.printf("%d. %s %s - Height: %d - Previous Experience: %b%n", numCounter, 
                              existingPlayer.getFirstName(),
                              existingPlayer.getLastName(),
                              existingPlayer.getHeightInInches(),
                              existingPlayer.isPreviousExperience()
                             );
            numCounter++;
          } 
      
        // Selecting a player
  
          System.out.printf("Select a Player: ");
          int userPlayerSelection = scanner.nextInt();

  
        // Gets selected player
  
          Player selectedPlayer = sortedPlayers.get(userPlayerSelection - 1);
      
        // Add player to team
          selectedTeam.addPlayer(selectedPlayer); 
        }
         
        // NEXT: REMOVE  
        } else if (menuChoice.equalsIgnoreCase("Remove")) {
           
            List<Team> sortedTeams = displayTeamOrder(allTeams);
          
            int teamNum = 1;
            for (Team currentTeam : sortedTeams) {
              System.out.printf("%d. %s%n", teamNum, currentTeam.getTeamName());
              teamNum++;
            }
            
          // Choosing team input
            System.out.printf("Select a Team: ");
            int userCurrentTeam= scanner.nextInt();
            System.out.println();
          
          // Get team list
           Team chosenTeam = sortedTeams.get(userCurrentTeam -1);
       
          
          //Shows players on selected team
        
           List<Player> sortedPlayers = displayPlayerOrder(chosenTeam.getPlayers());
          
           int playerCount = 1;
           for (Player currentPlayer : sortedPlayers) {
            System.out.printf("%d. %s %s - Height: %d - Previous Experience: %b%n", playerCount, 
                                currentPlayer.getFirstName(), 
                                currentPlayer.getLastName(),
                                currentPlayer.getHeightInInches(),
                                currentPlayer.isPreviousExperience()
                             );
            playerCount++;
           }
          
          //Allow user to choose player to remove
          
           System.out.printf("Select Player: ");
           int userRemovePlayer = scanner.nextInt();
           System.out.println();
          
          //Get selected player
          
          Player chosenPlayer = sortedPlayers.get(userRemovePlayer - 1);
          
          // Remove player from team
            chosenTeam.removePlayer(chosenPlayer);
        
          // HEIGHT REPORT
        } else if (menuChoice.equalsIgnoreCase("Report")) {
            List<Team> sortedTeams = displayTeamOrder(allTeams);
            
            int counter = 1;
            for (Team team : sortedTeams) {
              System.out.printf("%d. %s%n", counter, team.getTeamName());
              counter++;
            }
            System.out.print("Select Team: ");
            int userTeamSelection = scanner.nextInt();
            System.out.println();
                                
           Team selectedTeam = sortedTeams.get(userTeamSelection - 1);
        
           List<Player> heightGroupOne = new ArrayList<>(); // height group one will store 35-40inch
           List<Player> heightGroupTwo = new ArrayList<>(); // height group two will store 41-46 inch
           List<Player> heightGroupThree = new ArrayList<>(); //height group three will store 47-50inc
            
           for (Player player : selectedTeam.getPlayers()) {
            
             int height = player.getHeightInInches();
             
             if(height >= 35 && height <= 40) {
              heightGroupOne.add(player);
                
             } else if (height >= 41 && height <= 46) {
                heightGroupTwo.add(player);
             } else if (height >= 47 && height <= 50){
                heightGroupThree.add(player);
             }
           } // NEXT: DISPLAY GROUP
           System.out.println();
           System.out.println("Height Group - 35-40 inches:");
           System.out.println("----------------------");
           System.out.println();
           for (Player player : heightGroupOne) {
            System.out.printf("%s %s - %d inches%n", 
                              player.getFirstName(),
                              player.getLastName(),
                              player.getHeightInInches());
           }
           System.out.println();
           System.out.println("Height Group - 41-47 inches:");
           System.out.println("----------------------");
    
           for (Player player : heightGroupTwo) {
            System.out.printf("%s %s - %d inches%n", 
                              player.getFirstName(),
                              player.getLastName(),
                              player.getHeightInInches());
          
        
        }
           System.out.println();
           System.out.println("Height Group - 47-50 inches:");
           System.out.println("----------------------");
           
           for (Player player : heightGroupThree) {
             System.out.printf("%s %s - %d inches%n", 
                              player.getFirstName(),
                              player.getLastName(),
                              player.getHeightInInches());
            }
        
        
          System.out.println();
        
        //LEAGUE BALANCE REPORT
      } else if (menuChoice.equalsIgnoreCase("Balance")) {
          //Using map here
        
          Map<String, int[]> leagueBalanceReport = new HashMap<>();
          //We will be looping through every team
        
          for (Team team : allTeams) {
            int experiencedPlayer = 0;
            int inexperiencedPlayer = 0;
            
              //going through every player on team
              for (Player player : team.getPlayers()) {
                if (player.isPreviousExperience()) {
                  experiencedPlayer++;
                } else {
                    inexperiencedPlayer++;
                }
              } //Storing counts
              
              int[] counts = {experiencedPlayer, inexperiencedPlayer};
            
              leagueBalanceReport.put(team.getTeamName(), counts);
          }
        
          //Displaying Report
          System.out.println("LEAGUE BALANCE REPORT:");
        
          for (Team team : allTeams) {
            int[] counts = leagueBalanceReport.get(team.getTeamName());
            System.out.printf("%s - Experienced: %d - No Experience: %d%n",
                              team.getTeamName(),
                              counts[0],
                              counts[1]);
          }
        //ROSTER
      } else if (menuChoice.equalsIgnoreCase("Roster")) {
        
          List<Team> sortedTeams = displayTeamOrder(allTeams);
          
          int anotherCounter = 1;
          for (Team team : sortedTeams) {
            System.out.printf("%d. %s%n", anotherCounter, team.getTeamName());
            anotherCounter++;
          }
          System.out.print("Select Team: ");
          int userTeamSelection = scanner.nextInt();
          System.out.println();
        
          Team selectedTeam = sortedTeams.get(userTeamSelection - 1);
          
          List<Player> sortedPlayers = displayPlayerOrder(selectedTeam.getPlayers());
        
          for (Player player : sortedPlayers) {
            System.out.printf("%s %s - Height: %d - Previous Experience: %b%n",
                              player.getFirstName(),
                              player.getLastName(),
                              player.getHeightInInches(),
                              player.isPreviousExperience());
          }
      }
    } 
    
  } //////////////////////////////////////////////////////////
      public static List<Team> displayTeamOrder(List<Team> allTeams) {
        List<Team> sortedTeams = new ArrayList<>(allTeams);
        
        //using compareTo
          
        for (int i = 0; i < sortedTeams.size(); i++) {
          for (int j = i +1; j < sortedTeams.size(); j++) {
            if (sortedTeams.get(i).getTeamName().compareTo(sortedTeams.get(j).getTeamName()) > 0) {
              Team tempTeam = sortedTeams.get(i);
              sortedTeams.set(i, sortedTeams.get(j));
              sortedTeams.set(j, tempTeam);
            }
          }
        }
        //Returns ABC order list
          return sortedTeams;
      }
  
      public static List<Player> displayPlayerOrder(Collection<Player> players) {
        
        List<Player> sortedPlayers = new ArrayList<>(players);
        
        sortedPlayers.sort(null);
        
        return sortedPlayers;
      }
  } 
   