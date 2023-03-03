package model;

public class Board {

    private Square last;

    private int columns;
    private int rows;

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

    public String showBoard() {
        return showBoard(last, 1, columns);
    }

    private String showBoard(Square current, int aRows, int aColumns) {
        if(aColumns == 0) return "";

        if(aRows == 1 && (aColumns%2)==0) {
            current = searchSquare(last,rows*aColumns);
        }
        if(aRows == 1 && (aColumns%2)!=0) {
            current = searchSquare(last, (rows*(aColumns-1))+aRows);
        }

        if(aRows > rows) { //Agregar espacio
            return "\n" + showBoard(current, 1, aColumns-1);
        }

        if((aColumns%2)==0){
            return "[" + space(current.getNumber()+"", columns*rows) + "] " + showBoard(current.getPrevious(), aRows + 1, aColumns);
        } else {
            return "[" + space(current.getNumber()+"", columns*rows) + "] " + showBoard(current.getNext(), aRows + 1, aColumns);
        }
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

    public boolean initializeObstacle (int exit, int start, int option) {
        if (!verifyObstacle(start, exit, last)) return false;
        if (option == 1){
            Snake head = new Snake(start);
            Snake tail = new Snake(exit);
            head.setExit(tail);
            tail.setEntrance(head);
            changeSquare(last, head);
            changeSquare(last, tail);
        }else {
            Ladder head = new Ladder(start);
            Ladder tail = new Ladder(exit);
            head.setExit(tail);
            tail.setEntrance(head);
            changeSquare(last, head);
            changeSquare(last, tail);
        }
        return true;
    }

    private void changeSquare (Square current, Square newNode){

        if (current.getNumber() == newNode.getNumber()){
            current.getNext().setPrevious(newNode);
            current.getPrevious().setNext(newNode);
        } else {
            changeSquare(current.getPrevious(), newNode);
        }
    }

    private boolean verifyObstacle (int start, int exit, Square current){
        if (current == null) return true;
        if (current.getNumber() == start || current.getNumber() == exit){
            if (current instanceof Snake || current instanceof Ladder){
                return false;
            }
        }
        return verifyObstacle(start, exit, current.getPrevious());
    }

}

