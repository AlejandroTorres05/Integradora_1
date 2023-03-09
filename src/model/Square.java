package model;

public class Square {

    private final int number;
    private Square next;
    private Square previous;

    public Square(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Square getNext() {
        return next;
    }
    public void setNext(Square next) {
        this.next = next;
    }

    public Square getPrevious() {
        return previous;
    }
    public void setPrevious(Square previous) {
        this.previous = previous;
    }
}
