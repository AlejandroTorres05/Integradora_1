package model;

public class Ladder extends Square{

    private final String name;
    private Square ladderLanding;

    public Ladder(int number, String name) {
        super(number);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Square getLadderLanding() {
        return ladderLanding;
    }
    public void setLadderLanding(Square ladderLanding) {
        this.ladderLanding = ladderLanding;
    }
}
