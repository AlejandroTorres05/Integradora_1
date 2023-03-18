package model;

public class Square {

    private final int number;

    private Player[] players;
    private Square next;
    private Square previous;

    public Square(int number) {

        this.number = number;
        this.players = new Player[3];
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

    public void addPlayer (Player player){

        for (int i = 0; i<players.length; i++){
            if (players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    public boolean validatePlayer(Player player){

        for (int i = 0; i<players.length; i++){
            if (players[i] != null){
                if (players[i].equals(player)) return true;
            }
        }

        return false;
    }

    public void deletePlayer (Player player){

        for (int i = 0; i<players.length; i++){

            if (players[i] != null){

                if (players[i].equals(player)){
                    players[i] = null;
                    return;
                }
            }
        }
    }

    public Player getPlayer (){
        for (int i = 0; i< players.length; i++){
            if (players[i] != null){
                return players[i];
            }
        }
        return null;
    }

    public String printPlayers (){
        String message = "";

        for (int i = 0; i<players.length; i++){
            if (players[i] != null){
                message += players[i].getId();
            }
        }

        return message;
    }
}
