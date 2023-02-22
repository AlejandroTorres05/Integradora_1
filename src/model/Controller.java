package model;

public class Controller {

    private Board board;

    public void initializeBoard (int columns, int rows) {

        this.board = new Board();
        board.setColumns(columns);
        initializeTheBoard(columns*rows, 1);
    }

    private void initializeTheBoard (int square, int number) {
        if (number > square) return;
        board.addSquare(
                new Square(number)
        );
        initializeTheBoard(square,number+1);
    }

    // Este es un metodo de prueba para verificar la impresion. <3
    public String  print () {
        return board.printTable();
    }

}

