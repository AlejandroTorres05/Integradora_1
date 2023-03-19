package model;

public class PlayerRank {

    private Node root;

    public void add(Node node){

        if(root == null){
            root = node;
            return;
        }
        add(root, node);
    }
    private void add(Node current, Node node){

        if(node.getData().getScore() < current.getData().getScore()){
            if(current.getLeft() == null){
                current.setLeft(node);
            }else{
                add(current.getLeft(), node);
            }
            System.out.println("I'm capturing something");
        }else if(node.getData().getScore() > current.getData().getScore()){
            if(current.getRight() == null){
                current.setRight(node);
            }else{
                add(current.getRight(), node);
            }
            System.out.println("I'm capturing something");
        }
    }

    public String printRank(){

        return printRank(root, "");
    }

    private String printRank(Node current, String message){
        if(current == null){
            return "";
        }

        printRank(current.getRight(), message);
        message = current.getData().getId() + " with " + current.getData().getScore() +  " points" + "\n";
        printRank(current.getLeft(), message);
        return message;
    }
}
