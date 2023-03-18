package ui;

import model.Controller;
import java.util.Scanner;

public class Manager {

    private Scanner sc;
    private Controller controller;

    public Manager (){
        this.sc = new Scanner(System.in);
        this.controller = new Controller();
    }

    public static void main(String[] args) {

        Manager manager = new Manager();

        manager.showMenu();
        manager.printRank();

    }

    public void showMenu (){
        int option;

        do{
            System.out.println("Hello, Welcome to Snakes and Ladders."
                    + "\n¬| Please type the number of an option:"
                    + "\n1. Play"
                    + "\n0. Exit");

            option = sc.nextInt();
            executeMenu(option);

        } while (option != 0);
    }

    public void executeMenu (int option){

        switch (option){
            case 0:
                System.out.println("Closing menu... GoodBye");
                break;
            case 1:
                initializeBoard();
                movePlayers();

                break;
            default:
                System.out.println(option + " is not a valid option");
                break;
        }
    }

    public void initializeBoard (){

        System.out.println("How many columns do you want");
        int columns = sc.nextInt();

        System.out.println("How many rows do you want?");
        int rows = sc.nextInt();

        controller.initializeBoard(columns,rows);
        System.out.println(controller.showBoardSquares());
        System.out.println(controller.showBoardObstacles());

    }

    public void movePlayers () {

        int turn = 0;
        int option;
        char currentPlayer;
        boolean run = false;

        while (!run){

            currentPlayer = controller.currentPlayer(turn);
            System.out.println("Player " + currentPlayer + ", please type an option:"
                + "\n1. throw dice"
                + "\n2. Show ladders and snakes");

            option = sc.nextInt();

            switch (option){
                case 1:

                    int movement = controller.movePlayer(turn);
                    System.out.println("Your throw was " + movement);
                    System.out.println(controller.showBoardSquares());
                    System.out.println(controller.showBoardObstacles());

                    break;
                case 2:
                    System.out.println(controller.showBoardObstacles());
                    break;
                default:
                    System.out.println(option + " is not an option"
                        + "\nYou have loused you turn");
                    break;
            }

            if (controller.isInTheEnd()) run = true;
            turn ++;
            if (turn == 3) turn = 0;
        }

    }

    public void printRank (){
        System.out.println("Here is the final Rank");
        System.out.println(controller.printRank());
        System.out.println("Good bay...");
    }

}