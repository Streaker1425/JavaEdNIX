package feature.hangman;

public class Player {
    public String playerName;
    public int gamesWon;
    public int gamesLost;

    public Player(String _name, int gWon, int gLost){
        playerName = _name;
        gamesLost = gLost;
        gamesWon = gWon;
    }
    public String showInfo(){
        while(playerName.length()!=10){
            playerName = playerName.concat(" ");
        }
        String info = playerName + "\t\t\t" + getGamesCount() + "\t\t\t\t\t" + gamesWon;
        return info;
    }
    public int getGamesCount(){
        return gamesWon + gamesLost;
    }
}
