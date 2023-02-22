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

        System.out.println("Hello, \nwelcome to Snakes and Ladders. ");
        boolean start = false;

        while (!start) {

            System.out.println("Please type the number of an option: "
                + "\n 1. Play"
                + "\n 0. Exit");

            int option = sc.nextInt();
            if (executeMenu(option) == 0){
                start = true;
            }

        }
    }

    public int executeMenu (int option){

        switch (option){
            case 0:
                return 0;
            case 1:
                initializeBoard();
                return 1;
            default:
                System.out.println(option + " is not a valid option");
                return -1;
        }

    }

    public void initializeBoard (){

        System.out.println("How many columns do you want");
        int columns = sc.nextInt();
        System.out.println("How many rows do you want?");
        int rows = sc.nextInt();
        controller.initializeBoard(columns,rows);

        //Este es un llamado a un metodo de prueba para verificar que la inicializacion e impresion funciona
        System.out.println(controller.print());

    }
}