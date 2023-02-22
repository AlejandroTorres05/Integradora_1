package model;

public class Board {

    private Square first;
    private Square last;
    private int columns;

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void addSquare (Square square){

        if (this.first == null) {this.first = square;
            this.last = square; return;}
        last.setNext(square); square.setPrevious(last);
        this.last = square;
    }

    public String printTable (){

        return printTable(first, 1);
    }

    private String printTable (Square current,int column){
        if (current == null) return "";
        if (column == columns) {
            return  printTable(current.getNext(),1)
                    + "\n" + "[" + current.getNumber() + "]";
        }else {
            return printTable(current.getNext(), column +1)
                    + "[" + current.getNumber() + "]";
        }
    }
}
