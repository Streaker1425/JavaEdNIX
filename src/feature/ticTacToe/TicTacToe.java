package feature.ticTacToe;

import java.util.Scanner;

class ProgramMenu extends TicTacToeValues{
    public void menuChoice(int answer){
        TicTacToeGame game = new TicTacToeGame();
        switch (answer){
            case 1:
                game.startGame();
                break;
            case 2:
                return;
        }
    }
    public void mainMenu(){
        int answer;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println(MENU_OPTION_1);
            System.out.println(MENU_OPTION_2);
            answer = scan.nextInt();
            menuChoice(answer);
        }
    }
}

public class TicTacToe {
    public static void main(String[] args) {
        ProgramMenu menu = new ProgramMenu();
        menu.mainMenu();
    }
}
