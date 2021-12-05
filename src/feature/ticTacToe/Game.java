package feature.ticTacToe;

import java.util.ArrayList;

public interface Game {

    void drawField(ArrayList<Integer> gameData);
    void playerMove(ArrayList<Integer> gameData, int playerNumber);
    boolean gameWon(ArrayList<Integer> gameData);
    void startGame();
    int userInput(int pNum);
}
