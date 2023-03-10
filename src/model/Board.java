package model;

public class Board {

    public final String[] alphabet = {
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    };

    private Square last;
    private Player [] players;
    private int columns;
    private int rows;

    public Board (){
        this.players = new Player[3];
    }

    public Square getLast() {
        return last;
    }

    public int getColumns() {
        return columns;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }

    public void addSquare (Square square){

        if (this.last == null) {
            this.last = square;
            return;
        }

        square.setPrevious(last);
        square.getPrevious().setNext(square);
        last = square;
    }

    public String showBoardSquares() {
        return showBoardSquares(last, 1, columns);
    }

    private String showBoardSquares(Square current, int aRows, int aColumns) {
        if(aColumns == 0) return "";

        if(aRows == 1 && (aColumns%2)==0) {
            current = searchSquare(last,rows*aColumns);
        }
        if(aRows == 1 && (aColumns%2)!=0) {
            current = searchSquare(last, (rows*(aColumns-1))+aRows);
        }

        if(aRows > rows) { //Agregar espacio
            return "\n" + showBoardSquares(current, 1, aColumns-1);
        }

        if((aColumns%2)==0){
            return "[" + space(current.getNumber()+"", columns*rows) + "] " + showBoardSquares(current.getPrevious(), aRows + 1, aColumns);
        } else {
            return "[" + space(current.getNumber()+"", columns*rows) + "] " + showBoardSquares(current.getNext(), aRows + 1, aColumns);
        }
    }

    public Square searchSquare(int target){
        return searchSquare(last, target);
    }

    private Square searchSquare(Square current, int target) {
        if (current.getNumber() == target) {
            return current;
        } else {
            return searchSquare(current.getPrevious(), target);
        }
    }

    private String space(String n, int ntoCompare){
        if(String.valueOf(ntoCompare).length() == n.length()) return n;

        return space(n + " ", ntoCompare);
    }

    public boolean checkObstaclePosition(int obstacle1, int obstacle2){
        return checkObstaclePosition(obstacle1, obstacle2, last);
    }

    private boolean checkObstaclePosition(int obstacle1, int obstacle2, Square current) {
        if (current == null) {
            return false;
        }

        if (current.getNumber() == obstacle2 || current.getNumber() == obstacle1){
            if (current instanceof Snake || current instanceof Ladder){
                return true;
            }

        }

        return checkObstaclePosition(obstacle1, obstacle2, current.getPrevious());
    }

    public void changeSquare(Square newSquare){
        changeSquare(last, newSquare);
    }

    private void changeSquare(Square current, Square newSquare){
        if (current.getNumber() == newSquare.getNumber()) {
            newSquare.setPrevious(current.getPrevious());
            newSquare.setNext(current.getNext());

            // Caso cola de serpiente = 1
            // Caso escalera en final
            if (current.getPrevious() != null) {
                current.getPrevious().setNext(newSquare);
            }
            if (current.getNext() != null) {
                current.getNext().setPrevious(newSquare);
            }

        } else {
            changeSquare(current.getPrevious(), newSquare);
        }
    }

    public void generateSnakes(int snake1, int snake2, int n) {
        Snake snake1_Obj = new Snake(snake1, alphabet[n-1]);
        Snake snake2_Obj = new Snake(snake2, alphabet[n-1]);

        snake2_Obj.setTail(searchSquare(snake1));

        changeSquare(snake1_Obj);
        changeSquare(snake2_Obj);
    }

    public void generateLadders(int ladder1, int ladder2, int n) {
        Ladder ladder1_Obj = new Ladder(ladder1, n+"");
        Ladder ladder2_Obj = new Ladder(ladder2, n+"");

        ladder1_Obj.setLadderLanding(searchSquare(ladder1));

        changeSquare(ladder1_Obj);
        changeSquare(ladder2_Obj);
    }

    public String showBoardObstacles() {
        return showBoardObstacles(last, 1, columns);
    }

    private String showBoardObstacles(Square current, int aRows, int aColumns) {
        if(aColumns == 0) return "";

        if(aRows == 1 && (aColumns%2)==0) {
            current = searchSquare(last,rows*aColumns);
        }
        if(aRows == 1 && (aColumns%2)!=0) {
            current = searchSquare(last, (rows*(aColumns-1))+aRows);
        }

        if(aRows > rows) { //Agregar espacio
            return "\n" + showBoardObstacles(current, 1, aColumns-1);
        }

        String toPrint = " ";

        if (current instanceof Ladder){
            toPrint = ((Ladder) current).getName();
        } else if (current instanceof Snake){
            toPrint = ((Snake) current).getName();
        }


        if((aColumns%2)==0){
            return "[" + space(toPrint+"", columns*rows) + "] " + showBoardObstacles(current.getPrevious(), aRows + 1, aColumns);
        } else {
            return "[" + space(toPrint+"", columns*rows) + "] " + showBoardObstacles(current.getNext(), aRows + 1, aColumns);
        }
    }

    public void initializePlayers (int [] players, int i) {

        if (i == players.length) {
            addPlayersToStart(0);
            return;
        }
        this.players[i] = new Player(players[i]);
        initializePlayers(players, i+1);

    }

    private void addPlayersToStart (int i){

        if (i == players.length) return;
        searchSquare(1).addPlayer(players[i]);
        addPlayersToStart(i+1);
    }

    public char currentPlayer (int turn){
        return players[turn].getId();
    }

    public boolean movePlayer (int turn, int moves){

        Square current = validatePlayer(players[turn], last);
        return movePlayer(current, players[turn], moves, 1);
    }

    private boolean movePlayer (Square currentSquare, Player player, int moves, int currentMove){
        if (currentMove > moves){

            if (currentSquare == null){
                return true;
            }else {
                if (currentSquare.getPrevious() instanceof Snake && (Snake)((Snake) currentSquare.getPrevious()).getTail() != null){
                    movedBySnake(player, (Snake) currentSquare.getPrevious());
                }
                if (currentSquare.getPrevious() instanceof Ladder && (Ladder) ((Ladder) currentSquare.getPrevious()).getLadderLanding() != null){
                    movedByLadder(player, (Ladder) currentSquare.getPrevious());
                }
                return false;
            }

        }
        currentSquare.deletePlayer(player);
        currentSquare.getNext().addPlayer(player);
        return movePlayer(currentSquare.getNext(), player, moves, currentMove+1);
    }

    private Square validatePlayer (Player player, Square current){

        if (current.validatePlayer(player)) return current;
        return validatePlayer(player, current.getPrevious());
    }


    private void movedBySnake (Player player, Snake snake){
        snake.deletePlayer(player);
        snake.getTail().addPlayer(player);
    }

    private void movedByLadder (Player player, Ladder ladder){
        ladder.deletePlayer(player);
        ladder.getLadderLanding().addPlayer(player);
    }

    public Player getPlayerOfLevel () {
        return last.getPlayer();
    }

}

