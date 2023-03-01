package model;
import java.lang.Math;

public class Controller {

    private Board board;

    public void initializeBoard (int columns, int rows) {
        board = new Board();
        board.setRows(rows);
        board.setColumns(columns);

        initializeTheBoard(columns*rows, 1);
    }

    private void initializeTheBoard (int square, int number) {
        if (number > square) return;

        board.addSquare(new Square(number));
        initializeTheBoard(square,number+1);
    }

    private void initializeSnakes (int number, int i, int maxValue){
        if (i == number) return;
        int start = (int)(Math.random()*maxValue+1);
        int exit = (int)(Math.random()*maxValue+1);

        while (start > exit){
            start = (int)(Math.random() * maxValue);
            exit = (int)(Math.random() * maxValue);
        }

        initializeSnakes(number,i+1, maxValue);
    }

    private void initializeLadder (int number){

    }

    public String showBoard() {
        return board.showBoard();
    }

}

