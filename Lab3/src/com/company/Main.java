package com.company;

import java.util.*;

public class Main {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(){
            this.value = 0;
            this.left = null;
            this.right = null;
        }

        public Node(int value){
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(Node another){
            this.value = another.value;
            this.left = another.left;
            this.right = another.right;
        }

        public void add(Node children){
            if(right!=null && children.value>=this.value){
                right.add(children);
            }
            else if(right==null && children.value>=this.value){
                this.right = children;
            }

            if(left!=null && children.value<this.value) {
                left.add(children);
            }
            else if(left==null && children.value<this.value){
                this.left = children;
            }
        }

        public void print(int level){
            //System.out.println("Current level : " + level);
            System.out.println("Value = " + this.value);
            //System.out.println("Child level: " + (level+1));*/
            if(left!=null){
                //System.out.println("Left: ");
                this.left.print(level+1);
            }
            /*else{
                System.out.println("Left: null");
            }*/
            //System.out.println("Child level: " + (level+1));
            if(right!=null){
                //System.out.println("Right: ");
                this.right.print(level+1);
            }
            /*else{
                System.out.println("Right: null");
            }*/
        }
    }

    public static void printByIteration(Node root){
        Queue<Node> queue=new LinkedList<> ();
        queue.add(root);
        do{
            System.out.print(queue.peek().value+" ");
            if (queue.peek().left!=null) queue.add(queue.peek().left);
            if (queue.peek().right!=null) queue.add(queue.peek().right);
            if (!queue.isEmpty()) queue.remove();
        }while (!queue.isEmpty());
        System.out.println('\n');
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<4096; i++){
            list.add((int)(Math.random()*10000));
        }
        for(Integer element :list){
            System.out.print(element + " ");
        }
        System.out.print('\n');
        Node root = new Node(list.get(0));
        for(int i = 1; i<list.size(); i++){
            root.add(new Node(list.get(i)));
        }

        long startWalkthroughTime = System.nanoTime();
        root.print(0);
        long endWalkthroughTime = System.nanoTime();
        System.out.print("Время выполнения: " + (endWalkthroughTime-startWalkthroughTime)/1000000 + " ms");
        startWalkthroughTime = System.nanoTime();
        printByIteration(root);
        endWalkthroughTime = System.nanoTime();
        System.out.print("Время выполнения: " + (endWalkthroughTime-startWalkthroughTime)/1000000 + " ms");
    }
}
