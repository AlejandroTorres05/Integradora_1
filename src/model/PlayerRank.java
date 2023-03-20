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
            System.out.println("I'm capturing something");
            if(current.getLeft() == null){
                current.setLeft(node);
            }else{
                add(current.getLeft(), node);
            }
        }else if(node.getData().getScore() > current.getData().getScore()){
            System.out.println("I'm capturing something");
            if(current.getRight() == null){
                current.setRight(node);
            }else{
                add(current.getRight(), node);
            }
        }
    }

    public String printRank(){

        return printRank(root);
    }

    private String printRank(Node current){
        if(current == null){
            return "";
        }
        String message = "";

        message += printRank(current.getRight());
        message = current.getData().getId() + " with " + current.getData().getScore() +  " points" + "\n";
        message += printRank(current.getLeft());

        return message;
    }
}
