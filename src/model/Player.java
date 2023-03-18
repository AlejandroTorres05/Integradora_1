package model;

public class Player {

    private final char [] identifications = { '!', 'X', '%', '$', '#', '+', '&'};

    private int score;
    private char id;

    public Player(int id) {
        this.id = identifications[id];
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char getId() {
        return id;
    }
}
