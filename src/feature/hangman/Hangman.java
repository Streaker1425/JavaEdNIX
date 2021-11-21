package feature.hangman;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman  {
    //some features added to mimic vibe of old computers
    public static final int MAX_PLAYERS = 6;
    public static Player [] player = new Player[MAX_PLAYERS];
    public static void fillLeaderboard(){
        player[0] = new Player("Lucie     ", 16, 42);
        player[1] = new Player("redstoner ", 25, 16);
        player[2] = new Player("PhoenixSC ", 54, 47);
        player[3] = new Player("", 0,0);
        player[4] = new Player("", 0,0);
        player[5] = new Player("", 0,0);
    }

    public static int logInUser(){
        int amount = 0;
        System.out.println("Available profiles: ");
        for (Player tempPlayer : player) {
            if(tempPlayer.playerName.isEmpty()){
                break;
            }
            System.out.println(tempPlayer.playerName);
            amount++;
        }
        System.out.print("[create new profile]\n");
        System.out.print("Enter name of existing profile or of a new one: ");
        String userName;
        Scanner readInput = new Scanner(System.in);
        while (true) {
            userName = readInput.next();
            if (userName.length() > 10) {
                System.out.print("You can use only 10 symbols, try again: ");
            }else{
                while(userName.length()!=10){
                    userName = userName.concat(" ");
                }
                break;
            }
        }

        int ID = 0;
        for (Player tempPlayer : player) {
            if (tempPlayer.playerName.equalsIgnoreCase(userName)) {
                System.out.println("Using profile " + player[ID].playerName);
                return ID;
            } else if (tempPlayer.playerName.isEmpty()){
                break;
            } else{
                ID++;
            }
        }
        try {
            System.out.println("Using profile " + player[ID].playerName);
            player[ID].playerName = userName;
            return ID;
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Cant create new profile: limit reached!");
            return -1;
        }
    }
    public static void playGame(){
        int userID = logInUser();
        if(userID!= -1){
            HangmanGame game = new HangmanGame();
            boolean result = game.startGame();
            if(result){
                player[userID].gamesWon++;
            }else{
                player[userID].gamesLost++;
            }
        }
    }

    public static void leaderboards(){
        System.out.println("Name\t\t\tGames played\t\tGames won");
        for (Player tempPlayer : player) {
            if(tempPlayer.playerName.isEmpty()) {
                break;
            } else {
                System.out.println(tempPlayer.showInfo());
            }
        }
        System.out.println();
    }
    public static void mainMenu(){
        while (true) {
            Scanner readInput = new Scanner(System.in);
            int menuAnswer = 0;
            System.out.println("Enter number of menu option to choose");
            System.out.println("1. Play");
            System.out.println("2. Leaderboards");
            System.out.println("3. Exit");
            try {
                menuAnswer = readInput.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("You are supposed to enter a number");
            }
            switch (menuAnswer) {
                case 1:
                    playGame();
                    break;
                case 2:
                    leaderboards();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Cant figure out what menu option is this");
            }
        }
    }
    public static void main(String[] args) {
        fillLeaderboard();
        mainMenu();
    }
}


