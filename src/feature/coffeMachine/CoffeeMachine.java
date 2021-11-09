package feature.coffeMachine;

import java.util.Scanner;

class CoffeeMachineFunctionality extends CoffeeValues {

    private int water=400, milk=540, coffee=120, cups=9, money=540, posCups = 251, cupsAmount = 1;

    Scanner scan = new Scanner(System.in);

    private int insertVal(String name){
        System.out.println("How many " + name + " do you want to insert?" + '\n' + "> ");
        return scan.nextInt();
    }

    public void showIngridientsLeft(){
        System.out.println("There is " + water + "ml of water left in coffee machine");
        System.out.println("There is " + milk + "ml of milk left in coffee machine");
        System.out.println("There is " + coffee + "g of coffee left in coffee machine");
        System.out.println("There is " + cups + " cups left in coffee machine");
        System.out.println("There is " + money + " UAH in coffee machine");
    }

    public void insertIngridients(){
        while(true) {
            water = water + insertVal("water");
            if(water >= 0 && water <= WATER_CAPACITY) {
                break;
            }
            else if(water <= 0) {
                System.out.println("If you invented negative amount of things, contact scientists, not coffee machine. Try again.");
            }
            else{
                System.out.println("Cant insert that much! Try again.");
            }
        }
        while(true) {
            milk = milk + insertVal("milk");
            if(milk >= 0 && milk <= MILK_CAPACITY) {
                break;
            }
            else if(milk <= 0){
                System.out.println("If you invented negative amount of things, contact scientists, not coffee machine. Try again.");
            }
            else{
                System.out.println("Cant insert that much! Try again.");
            }
        }
        while(true) {
            coffee = coffee + insertVal("coffee");
            if(coffee >= 0 && coffee <= COFFEE_CAPACITY){
                break;
            }
            else if(coffee < 0){
                System.out.println("If you invented negative amount of things, contact scientists, not coffee machine. Try again.");
            }
            else{
                System.out.println("Cant insert that much! Try again.");
            }
        }
        while(true) {
            cups = cups + insertVal("cups");
            if(cups >= 0 && cups <= CUPS_CAPACITY){
                break;
            }
            else if(cups <= 0){
                System.out.println("If you invented negative amount of things, contact scientists, not coffee machine. Try again.");
            }
            else{
                System.out.println("Cant insert that much! Try again.");
            }
        }
        showIngridientsLeft();
    }
    public void askCupsAmount(){
        while(true) {
            System.out.println("How many cups do you need?" + '\n' + "> ");
            cupsAmount = scan.nextInt();
            if (cupsAmount > 99) {
                System.out.println("Why'd someone need so many? Okay");
                break;
            }
            else if(cupsAmount >= 250){
                System.out.println("Im not capable of making that much. Select lower value");
            }
            else if(cupsAmount < 0){
                System.out.println("Incorrect value!");
            }
            else break;
        }
        //обычно машины не говорят это клиенту, и мы не будем
//        System.out.println("For " + cupsAmount +" cups of coffee you will need:");
//        System.out.println(200 * cupsAmount + "ml of water");
//        System.out.println(50 * cupsAmount + "ml of milk");
//        System.out.println(15 * cupsAmount + "g of coffee beans");
    }
    public int defineCoffeeType(){
        while(true){
            System.out.println("What coffee do you want? (espresso, latte, cappuccino)");
            String ans = scan.next();
            if(ans.equalsIgnoreCase("espresso")) {
                return ESPRESSO_OPTION;
            }
            else if(ans.equalsIgnoreCase("latte")){
                return LATTE_OPTION;
            }
            else if(ans.equalsIgnoreCase("cappuccino")){
                return CAPPUCCINO_OPTION;
            }
            else{
                System.out.println("Cant figure out what type of coffee you need. Try again");
            }
        }
    }
    public void makeCoffee(int type){
        countCupsPossible(type);
        if(posCups<cupsAmount) {
            return;
        }
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        switch (type){
            case ESPRESSO_OPTION: {
                water = water - ESPRESSO_WATER;
                coffee = coffee - ESPRESSO_COFFEE;
                money = money + ESPRESSO_PRICE;
                cups--;
                break;
            }
            case LATTE_OPTION: {
                water = water - LATTE_WATER;
                milk = milk - LATTE_MILK;
                coffee = coffee - LATTE_COFFEE;
                money = money + LATTE_PRICE;
                cups--;
                System.out.println("Pouring milk into the cup");
                break;
            }
            case CAPPUCCINO_OPTION:  {
                water = water - CAPPUCCINO_WATER;
                milk = milk - CAPPUCCINO_MILK;
                coffee = coffee - CAPPUCCINO_COFFEE;
                money = money + CAPPUCINO_PRICE;
                cups--;
                System.out.println("Pouring milk into the cup");
                break;
            }
        }
        System.out.println("Coffee is ready");
    }

    public void countCupsPossible(int type){
        int[] IngridientsRequired = {0,0,0,0}; // 0 water 1 milk 2 coffee 3 cups
        switch (type){
            case ESPRESSO_OPTION: {
                IngridientsRequired[0] = water/ESPRESSO_WATER;
                IngridientsRequired[1] = 250;
                IngridientsRequired[2] = coffee/ESPRESSO_COFFEE;
                IngridientsRequired[3] = cups;
                break;
            }
            case LATTE_OPTION: {
                IngridientsRequired[0] = water/LATTE_WATER;
                IngridientsRequired[1] = milk/LATTE_MILK;
                IngridientsRequired[2] = coffee/LATTE_COFFEE;
                IngridientsRequired[3] = cups;
                break;
            }
            case CAPPUCCINO_OPTION:  {
                IngridientsRequired[0] = water/CAPPUCCINO_WATER;
                IngridientsRequired[1] = milk/CAPPUCCINO_MILK;
                IngridientsRequired[2] = coffee/CAPPUCCINO_COFFEE;
                IngridientsRequired[3] = cups;
                break;
            }
        }

        for(var memb : IngridientsRequired) {
            if (posCups > memb){
                 posCups = memb;
            }
        }
        if(posCups==cupsAmount){
            System.out.println("I can make that amount");
        }
        else if(posCups==0){
            System.out.println("Not enough ingridients to make a single cup of this type of coffee! Contact special employee");
            return;
        }
        else if(posCups>cupsAmount){
            posCups = posCups - cupsAmount;
            System.out.println("I can make that amount, and even " + posCups + " more");
        }
        else if(posCups<cupsAmount){
            if(posCups % 10 == 1){
                System.out.println("I cant make that amount, but i can make " + posCups + " cup");
            }
            else{
                System.out.println("I cant make that amount, but i can make " + posCups + " cups");
            }
            return;
        }
    }
    public void makeEntireCoffee() {
        askCupsAmount();
        makeCoffee(defineCoffeeType());
    }
    public void takeMoney(){
        while(true) {
            System.out.println("Enter security code or 0 to return!");
            int code = scan.nextInt();
            if (code == 1234){
                System.out.println(money + " UAH earned!");
                money = 0;
                return;
            }
            else if(code == 0) {
                return;
            }
        }
    }
    public void mainMenu(String UserAnswer){
        switch(UserAnswer){
            case "buy": {
                makeEntireCoffee();
                break;
            }
            case "refill": {
                insertIngridients();
                break;
            }
            case "take": {
                takeMoney();
                break;
            }
            case "remaining": {
                showIngridientsLeft();
                break;
            }
        }
    }
}

public class CoffeeMachine {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        CoffeeMachineFunctionality CoffeeMachine = new CoffeeMachineFunctionality();
        while (true){
            System.out.println("What do you want to do? (buy, refill, take, remaining, exit)");
            String answer = sc.next();
            if(answer.equalsIgnoreCase("exit")){
                return;
            }
            else{
                CoffeeMachine.mainMenu(answer);
            }
        }
    }

}