package feature.hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private int guessesAmount = 8;
    private String wordToGuess;
    private String[] words = {
            "c sharp", "java", "kotlin", "spring", "minecraft",
            "virtual machine", "nix solutions", "class", "programming",
            "collection", "variable", "statement", "encapsulation",
            "polymorphism", "inheritance", "student", "byte", "boolean",
            "override", "constructor", "interface", "queue", "immutable"
    };
    private String selectWord()
    {
        Random rand = new Random();
        return words[rand.nextInt(words.length)];
    }
    private boolean letterIsInWord(String letter)//wanted to use char type, but it adds unnecessary transformations
    {
        if(wordToGuess.contains(letter)){
            return true;
        } else {
            return false;
        }
    }
    private void printWords(ArrayList<Boolean> isGsd){
        System.out.println("The word is: ");
        for(int i = 0; i < wordToGuess.length(); i++){
            char compare = wordToGuess.charAt(i);
            if(wordToGuess.charAt(i) == compare && isGsd.get(i)){
                System.out.print(wordToGuess.charAt(i) + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    public boolean startGame() {
        wordToGuess = selectWord();
        ArrayList<Boolean> isGuessed = new ArrayList<Boolean>(25);
        for (int i = 0; i < wordToGuess.length(); i++){
            if(wordToGuess.charAt(i) == ' '){
                isGuessed.add(true);
            }else{
                isGuessed.add(false);
            }
        }
        while(guessesAmount > 0){
            printWords(isGuessed);
            char tempLetter = guessLetter(isGuessed);
            for(int i = 0; i<wordToGuess.length(); i++)
            {
                if(wordToGuess.charAt(i) == tempLetter) {
                    isGuessed.set(i, true);
                }
            }
            if(!isGuessed.contains(false)){
                System.out.println("You won! The word is " + wordToGuess + '!');
                return true;
            }else if(tempLetter == '1') {
                return false;
            }
        }
        return false;
    }
    private char guessLetter(ArrayList<Boolean> isGsd){
        Scanner scan = new Scanner(System.in);
        String userGuess;
        while (true){
            System.out.print("Your guess is:");
            userGuess = scan.next();
            if(userGuess.length() > 1){
                System.out.println("You can only guess one letter at a time!");
            }else{
                break;
            }
        }
        if(letterIsInWord(userGuess)){
            System.out.println("You are right!");
            return userGuess.charAt(0);
        }else{
            guessesAmount--;
            System.out.println("You are wrong! " + guessesAmount + " attempts left!");
            if(guessesAmount == 0){
                System.out.println("Game over! Word was: " + wordToGuess);
                return '1';
            }
        }
        return 0;
    }
}
