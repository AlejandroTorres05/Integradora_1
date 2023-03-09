package model;

public class Snake extends Square{

    private final String name;
    private Square tail;

    public Snake(int number, String name) {
        super(number);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Square getTail() {
        return tail;
    }
    public void setTail(Square tail) {
        this.tail = tail;
    }
}

