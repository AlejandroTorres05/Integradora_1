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

        last.setNext(square);
        square.setPrevious(last);
        this.last = square;
    }


}
