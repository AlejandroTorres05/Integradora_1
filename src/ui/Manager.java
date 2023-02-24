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

    }

    public void showMenu (){
        int option;

        do{
            System.out.println("""
                    Hello, Welcome to Snakes and Ladders.
                    
                    ¬| Please type the number of an option:
                    1. Play"
                    0. Exit"
                    """);

            option = sc.nextInt();
            executeMenu(option);

        } while (option != 0);
    }

    public void executeMenu (int option){

        switch (option){
            case 0:
                System.out.println("Clossing menu... GoodBye");
                break;
            case 1:
                initializeBoard();
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
        System.out.println(controller.showBoard());

    }
}