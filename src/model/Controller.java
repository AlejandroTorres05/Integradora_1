package model;

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

    public String showBoard() {
        return board.showBoard();
    }

}

