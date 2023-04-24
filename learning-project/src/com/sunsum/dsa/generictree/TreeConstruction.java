package com.sunsum.dsa.generictree;

import java.util.*;

public class TreeConstruction {

    public static Node constructTree(int[] arr){
        Node root = null;
        Stack<Node> stack = new Stack<>();
        for(int i = 0; i<arr.length;i++){
            if(arr[i] == -1){
                stack.pop();
            }else{
                Node t = new Node(arr[i]);
                if(stack.size() > 0){
                    stack.peek().children.add(t);
                }else{
                    root = t;
                }
                stack.push(t);
            }
        }
        return root;
    }

    public static void displayTree(Node root){
        String s = root.data+"->";
        for(Node node : root.children){
            s+= node.data+"-";
        }
        s+=".";
        System.out.println(s);
        for(Node node: root.children){
            displayTree(node);
        }
    }

    public static int size(Node root){
        if(root == null){
            return -1;
        }
        int size = 1;
        for (Node node: root.children){
            size+= size(node);
        }
        return size;
    }

    public static int height(Node root){
        int height = -1;
        for(Node node : root.children){
            height = Math.max(height,height(node));
        }
        height+=1;
        return height;
    }

    public static int max(Node root){
        int max = Integer.MIN_VALUE;
        for(Node node: root.children){
            max = Math.max(max,max(node));
        }
        return Math.max(root.data,max);
    }
    public static void traverseLevel(Node root){
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(queue.size() > 0 ){
            Node node = queue.pop();
            System.out.print(node.data+" ");
            for(Node node1: node.children){
                queue.add(node1);
            }
        }

    }

    public static void traverseLevelByLine(Node root){
        ArrayDeque<Node> mainQueue = new ArrayDeque<>();
        ArrayDeque<Node> childQueue = new ArrayDeque<>();
        mainQueue.add(root);
        while(mainQueue.size() > 0 ){
            Node node = mainQueue.pop();
            System.out.print(node.data+" ");
            for(Node node1: node.children){
                childQueue.add(node1);
            }
            if(mainQueue.isEmpty()){
                mainQueue = childQueue;
                childQueue = new ArrayDeque<>();
                System.out.println();
            }
        }
    }

    public static void traverseByLineZigzag(Node root){
        Stack<Node> mst = new Stack<>();
        Stack<Node> cst = new Stack<>();
        mst.add(root);
        int i = 1;
        while(mst.size() > 0 ){
            Node node = mst.pop();
            System.out.print(node.data+" ");
            if(i%2 ==0){
                for(int j = node.children.size() -1; j >= 0; j--){
                    cst.add(node.children.get(j));
                }
            }else{
                for(Node node1: node.children){
                    cst.add(node1);
                }
            }

            if(mst.isEmpty()){
                mst = cst;
                cst = new Stack<>();
                System.out.println();
                i++;
            }
        }
    }

    public static void printLevelWise(Node root){
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        q.add(new Node(-1));
        while (q.size()>0){
            Node node = q.remove();
            if(node.data!=-1){
                System.out.print(node.data + " ");
                for (Node cn: node.children){
                    q.add(cn);
                }
            }else{
                if(!q.isEmpty()){
                    q.add(new Node(-1));
                    System.out.println();
                }
            }
        }

    }

    public static void printTreeByLevel(Node root){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node node = queue.remove();
                System.out.print(node.data+ " ");
                for(Node node1: node.children){
                    queue.add(node1);
                }
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        int[] arr = new int[]{10,11,13,-1,12,-1,-1,14,15,-1,16,-1,20,-1,-1,18,21,24,25,-1,-1,26,-1,-1};
        Node root = constructTree(arr);
        displayTree(root);
        System.out.println(size(root));
        System.out.println(max(root));
        System.out.println(height(root));
        System.out.println("------------------------Traversal----------------------------");
        traverseLevel(root);
        System.out.println();
        traverseLevelByLine(root);
        System.out.println("------------------------zigzag----------------------------");
        traverseByLineZigzag(root);
        System.out.println("------------------------Traversal----------------------------");
        printLevelWise(root);
        System.out.println("------------------------Traversal----------------------------");
        printTreeByLevel(root);

    }
}

