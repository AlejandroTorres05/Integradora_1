package model;

public class Snake extends Square{

    private Square exit;
    private Square entrance;

    public Snake(int number) {
        super(number);
    }

    public Square getExit() {
        return exit;
    }

    public void setExit(Square exit) {
        this.exit = exit;
    }

    public Square getEntrance() {
        return entrance;
    }

    public void setEntrance(Square entrance) {
        this.entrance = entrance;
    }
}

