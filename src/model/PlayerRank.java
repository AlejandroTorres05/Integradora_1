package model;

public class PlayerRank {

    private Node root;

    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            add(root, node);
        }
    }
    private void add(Node current, Node node){

        if(node.getData().getScore() < current.getData().getScore()){
            if(current.getLeft() == null){
                current.setLeft(node);
            }else{
                add(current.getLeft(), node);
            }

        }else if(node.getData().getScore() > current.getData().getScore()){
            if(current.getRight() == null){
                current.setRight(node);
            }else{
                add(current.getRight(), node);
            }
        }
    }

    public String printRank(){

        return printRank(root, 1);
    }

    private String printRank(Node current, int top){
        if(current == null){
            return "";
        }

        String message = "";
        message += printRank(current.getRight(), top+1);
        message += top + ". " + current.getData().getId() + " with " + current.getData().getScore() +  " points" + "\n";
        message += printRank(current.getLeft(), top+2);
        return message;
    }
}
