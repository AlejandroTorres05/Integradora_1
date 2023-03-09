package model;

public class Controller {

    private Board board;

    public void initializeBoard (int columns, int rows) {
        board = new Board();
        board.setRows(rows);
        board.setColumns(columns);

        initializeTheBoard(columns*rows, 1);
        initializeObstacles(board.getColumns()-1, 0, 1);
        initializeObstacles(0, board.getRows()-1, 1);
    }

    private void initializeTheBoard (int square, int number) {
        if (number > square) return;

        board.addSquare(new Square(number));
        initializeTheBoard(square,number+1);
    }

    public void initializeObstacles(int snakeNumber, int ladderNumber, int n) {
        int obstacle1 = 0;
        int obstacle2 = 0;

        if (snakeNumber > 0) {
            do {
                // [0, n-1) +1 asi se evita que sea 0 y que sea n
                obstacle1 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 1;
                obstacle2 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 1;

            } while (obstacle1 > obstacle2 && board.checkObstaclePosition(obstacle1, obstacle2));

            board.generateSnakes(obstacle1, obstacle2, n);
            initializeObstacles(snakeNumber-1, ladderNumber, n+1);
        }

        if (ladderNumber > 0) {
            do {
                // [0, n-1) +2 asi se evita que sea 0 y 1
                obstacle1 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 2;
                obstacle2 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 2;

            } while (obstacle1 > obstacle2 && board.checkObstaclePosition(obstacle1, obstacle2));

            board.generateLadders(obstacle1, obstacle2, n);
            initializeObstacles(snakeNumber, ladderNumber-1, n+1);
        }

    }

    public String showBoardSquares() {
        return board.showBoardSquares();
    }

    public String showBoardObstacles() {
        return board.showBoardObstacles();
    }

}

