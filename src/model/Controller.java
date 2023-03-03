package model;
import java.lang.Math;

public class Controller {

    private Board board;

    public void initializeBoard (int columns, int rows) {
        board = new Board();
        board.setRows(rows);
        board.setColumns(columns);

        initializeTheBoard(columns*rows, 1);
        initializeObstacles(columns-1, 0, columns*rows, 1);
        initializeObstacles(rows-1, 0, columns*rows, 0);
    }

    private void initializeTheBoard (int square, int number) {
        if (number > square) return;

        board.addSquare(new Square(number));
        initializeTheBoard(square,number+1);
    }

    private void initializeObstacles (int number, int i, int maxValue, int option){
        if (i == number) return;
        int start = (int)(Math.random()*maxValue);
        int exit = (int)(Math.random()*maxValue);

        if (option == 1){
            while (start < exit){
                start = (int)(Math.random() * maxValue);
                exit = (int)(Math.random() * maxValue);
            }
            if (!board.initializeObstacle(exit, start, 1)){
                initializeObstacles(number,i, maxValue, 1);
            } else {
                initializeObstacles(number,i+1, maxValue, 1);
            }
        } else {
            while (start > exit){
                start = (int)(Math.random() * maxValue);
                exit = (int)(Math.random() * maxValue);
            }
            if (!board.initializeObstacle(exit, start, 0)){
                initializeObstacles(number,i, maxValue, 0);
            } else {
                initializeObstacles(number,i+1, maxValue, 0);
            }
        }
    }

    public String showBoard() {
        return board.showBoard();
    }

}

