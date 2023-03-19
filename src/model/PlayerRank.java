package model;

public class PlayerRank {

    private Node root;

    public void add(Node node){

        if(root == null){
            root = node;
            return;
        }
        add(root, node);
        //Este printLine es para hacer las pruebas del metodo de agregar
        System.out.println(root.getData().getId());
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

        return printRank(root);
    }

    private String printRank(Node current){
        if(current == null){
            return "";
        }

        String left = printRank(current.getRight());
        String value = current.getData().getId() + " with " + current.getData().getScore() +  " points" + "\n";
        String right = printRank(current.getLeft());
        return left + value + right;
    }
}
