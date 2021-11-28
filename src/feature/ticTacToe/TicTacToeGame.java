package feature.ticTacToe;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeGame extends TicTacToeValues implements Game{

    boolean isEnded;
    @Override
    public void startGame() {
        isEnded = false;
        ArrayList<Integer> gameField = new ArrayList<>();
        for(int i = 0; i<9; i++) {
            gameField.add(0);
        }
        while(!isEnded){
            for(int i = 1; i<=2; i++){
                drawField(gameField);
                playerMove(gameField, i);
                if(gameWon(gameField)){
                    drawField(gameField);
                    System.out.println("Player " + i + " won!");
                    isEnded = true;
                    break;
                }
            }
        }
    }
    private void drawFieldMarks(ArrayList<Integer> gameData, int i, int j){
        switch (gameData.get(i*3+j))
        {
            case 0:
                System.out.print(EMPTY_FIELD);
                break;
            case 1:
                System.out.print(X_MARK);
                break;
            case 2:
                System.out.print(O_MARK);
                break;
            default: throw new RuntimeException("Cant draw field!");
        }
    }
    @Override
    public void drawField(ArrayList<Integer> gameData) {
        try{
            System.out.println(HORIZONTAL_HEADER);
            StringBuilder str = new StringBuilder();
            for(int i = 0; i<3; i++){
                str.append(i+1);
                str.append(' ');
                str.append(VERTICAL_LINE);
                System.out.print(str);
                for(int j = 0; j<3; j++){
                    drawFieldMarks(gameData, i, j);
                }
                System.out.println(VERTICAL_LINE);
                str.setLength(0);
            }
            System.out.println(HORIZONTAL_LINE);
        } catch (RuntimeException ex){
            System.out.println(ex);
        }

    }
    boolean isElementAt(ArrayList<Integer> arr, int val){
        for(int i = 0; i<arr.size(); i++){
            if(arr.get(i)==val){
                return true;
            }
        }
        return false;
    }
    @Override
    public void playerMove(ArrayList<Integer> gameData, int playerNumber) {
        while(true){
            int move = userInput(playerNumber);
            if(gameData.get(move)==0){
                gameData.set(move, playerNumber);
                break;
            }else{
                System.out.println("You cant place that there!");
            }
        }
    }

    @Override
    public boolean gameWon(ArrayList<Integer> gameData) {

            if(gameData.get(0)==gameData.get(1) && gameData.get(1) == gameData.get(2) && gameData.get(0)!=0){
                return true;
            } else if(gameData.get(3)==gameData.get(4) && gameData.get(4) == gameData.get(5) && gameData.get(3)!=0){
                return true;
            } else if(gameData.get(6)==gameData.get(7) && gameData.get(7) == gameData.get(8) && gameData.get(6)!=0){
                return true;
            } else if(gameData.get(0)==gameData.get(3) && gameData.get(3) == gameData.get(6) && gameData.get(0)!=0){
                return true;
            } else if(gameData.get(1)==gameData.get(4) && gameData.get(4) == gameData.get(7) && gameData.get(1)!=0){
                return true;
            } else if(gameData.get(2)==gameData.get(5) && gameData.get(5) == gameData.get(8) && gameData.get(2)!=0){
                return true;
            } else if(gameData.get(0)==gameData.get(4) && gameData.get(4) == gameData.get(8) && gameData.get(0)!=0){
                return true;
            }else if(gameData.get(2)==gameData.get(4) && gameData.get(2) == gameData.get(6) && gameData.get(2)!=0){
                return true;
            }else {
                return false;
            }


    }
    @Override
    public int userInput(int pNum){
        while(true){
            System.out.print("Player " + pNum + ", enter coordinates where to put ");
            if(pNum == 1){
                System.out.print("X");
            }else{
                System.out.print("O");
            }
            System.out.print(" (row, col)> ");
            Scanner scan = new Scanner(System.in);
            int row = scan.nextInt();
            int col = scan.nextInt();
            if(row<1 || row > 3){
                System.out.println("No such coordinate \"" + row + "\", try again!");
            }else if(col<1 || col > 3){
                System.out.println("No such coordinate \"" + col + "\", try again!");
            }
            else {
                return (row*3+col)-4;
            }
        }
    }
}
