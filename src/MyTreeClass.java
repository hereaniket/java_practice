import java.util.*;

public class MyTreeClass {

    public static void main(String[] args) {

        Integer arr[] = new Integer[] {5,19,3,90,33,88,123,55,66,99,44,43,45,36,37,38,991,801};
        int level = 0;
        while(Math.pow(2,level) < arr.length){
            level++;
        }
        level--;
        System.out.println("possible Levels "+level);

        Queue newList = new ArrayDeque(Arrays.asList(arr));

        while (Math.pow(2,level)-1 != newList.size()){
            newList.remove();
        }
        System.out.println("New Array "+newList);

        List<Node> list = new ArrayList<>();
        int currentLevel = 1;
        while(level >0){
            if (Math.pow(2,level)-1 == newList.size()){
                int range = (int)Math.pow(2,level)-1;
                while (range>level){
                    list.add(new Node((int)newList.toArray()[range-1], null, null));
                    range--;
                }

            } else {
                List<Node> tempList = new ArrayList<>();
                int range = (int)Math.pow(2,level)-1;
                while (range>=level){

                    int leftPick = (range+level)-1;
                    int rightPick = leftPick-1;
                    tempList.add(new Node((int)newList.toArray()[range-1], list.get(leftPick==1?leftPick:leftPick-1), list.get(rightPick==0? rightPick:rightPick-1)));
                    range--;
                }
                list.clear();
                list.addAll(tempList);
            }
            level--;
        }

        System.out.println(list);
    }

    public void buildTree(Node parent, Node left, Node right, int data){

    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
