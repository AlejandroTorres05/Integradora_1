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

    /*
    * Si estas probando el proyecto completo de forma rapida, debes dar tiempo cada vez que vayas
    * a terminar un recirrido. Porque el ranking debia se un BST, asi que si terminas una ronda muy
    * rapido, no se va a guardar la informacion del nuevo porque en nuestra estructura de datos no se contempla
    * el hecho de que haya keys iguales. 
    * */

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
        String message = "";

        message += printRank(current.getRight());
        message += current.getData().getId() + " with " + current.getData().getScore() +  " points" + "\n";
        message += printRank(current.getLeft());

        return message;
    }
}
