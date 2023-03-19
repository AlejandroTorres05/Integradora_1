package model;

public class Player {

    private final char [] identifications = { '!', 'X', '%', '$', '#', '+', '&'};

    private long score;
    private char id;

    public Player(int id) {
        this.id = identifications[id];
        this.score = 0;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public char getId() {
        return id;
    }
}
