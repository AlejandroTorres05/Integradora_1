package model;

public class Controller {

    private Board board;

    private PlayerRank rank;

    public void initializeBoard (int columns, int rows) {
        board = new Board();
        board.setRows(rows);
        board.setColumns(columns);
        rank = new PlayerRank();

        initializeTheBoard(columns*rows, 1);
        initializeObstacles(board.getColumns()-1, 0, 1);
        initializeObstacles(0, board.getRows()-1, 1);
        initializePlayers();
    }

    private void initializeTheBoard (int square, int number) {
        if (number > square) return;

        board.addSquare(new Square(number));
        initializeTheBoard(square,number+1);
    }

    public void initializeObstacles(int snakeNumber, int ladderNumber, int n) {
        int obstacle1 = 0;
        int obstacle2 = 0;

        boolean check = true;

        // Medida Temporal en caso de que el tablerro se pase de la cantidad de letras
        if (snakeNumber > board.alphabet.length) snakeNumber = board.alphabet.length;

        if (snakeNumber > 0) {
            do {
                // [0, n-1) +1 asi se evita que sea 0 y que sea n
                obstacle1 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 1;
                obstacle2 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 1;



            } while (!(obstacle1 < obstacle2) || board.checkObstaclePosition(obstacle1, obstacle2) || !(obstacle2 - obstacle1 >= 2));

            board.generateSnakes(obstacle1, obstacle2, n);
            initializeObstacles(snakeNumber-1, ladderNumber, n+1);
        }

        if (ladderNumber > 0) {
            do {
                // [0, n-1) +2 asi se evita que sea 0 y 1
                obstacle1 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 2;
                obstacle2 = (int)(Math.random() * (board.getRows() * board.getColumns())-1) + 2;

                check = board.checkObstaclePosition(obstacle1, obstacle2);

            } while (!(obstacle1 < obstacle2) || board.checkObstaclePosition(obstacle1, obstacle2) || !(obstacle2 - obstacle1 >= 2));

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

    private void initializePlayers (){

        int []  players = new int[3];
        boolean test = false;

        while (!test){

            players[0] = (int)(Math.random() * 6 + 1);
            players[1] = (int)(Math.random() * 6 + 1);
            players[2] = (int)(Math.random() * 6 + 1);

            if ( players[0] != players[1] && players[1] != players[2]
                    && players[2] != players[0]){

                board.initializePlayers(players, 0);
                test = true;
            }
        }
    }

    public char currentPlayer (int turn){
        return board.currentPlayer(turn);
    }

    public int movePlayer (int turn){

        int throwDice = (int)(Math.random()*6+1);
        board.movePlayer(turn, throwDice);
        return throwDice;
    }

    public boolean isInTheEnd (){

        Player winner = board.isPlayerInTheEnd();
        if (winner == null) return false;
        long seconds = System.currentTimeMillis() - board.getTimeOfStart();
        seconds /= 1000;
        winner.setScore((600 - seconds)/6);
        rank.add(new Node(winner));
        return true;

    }

    public String printRank (){
        return rank.printRank();
    }

}

