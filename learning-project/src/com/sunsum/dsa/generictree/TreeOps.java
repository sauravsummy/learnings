package com.sunsum.dsa.generictree;

import java.util.*;

public class TreeOps {

    public Node createTree(int[]arr){
        Stack<Node> stack = new Stack<>();
        Node root = null;
        for(int num:arr){
            Node t = new Node(num);
            if(num == -1){
                stack.pop();
            }else{
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

    public void displayTree(Node root){
        String s = root.data+"->";
        for(Node n: root.children){
            s+=" "+n.data;
        }
        s+=".";
        System.out.println(s);
        for (Node n:root.children){
            displayTree(n);
        }
    }

    public int count(Node root){
        int count = 1;
        for(Node n: root.children){
            count+=count(n);
        }
        return count;
    }

    public void displayTreeByLevel1(Node root){
        Deque<Node> mq = new ArrayDeque<>();
        Deque<Node> cq = new ArrayDeque<>();
        mq.add(root);
        while (mq.size() > 0){
            Node t = mq.pop();
            System.out.print(t.data+ " ");
            cq.addAll(t.children);

            if(mq.size()==0){
                mq = cq;
                cq = new ArrayDeque<>();
                System.out.println();
            }
        }
    }

    public void displayTreeByLevel2(Node root){
        Deque<Node> mq = new ArrayDeque<>();
        mq.add(root);
        mq.add(new Node(-1));
        while (mq.size() > 0){
            Node t = mq.pop();
            if(t.data == -1 ){
                if(!mq.isEmpty()) {
                    mq.add(new Node(-1));
                }
              System.out.println();
            }else{
                System.out.print(t.data+ " ");
                for(Node n:t.children){
                    mq.add(n);
                }
            }
        }
    }

    public void displayTreeByLevel3(Node root){
        Deque<Node> mq = new ArrayDeque<>();
        mq.add(root);
        while (mq.size() > 0){
            int size = mq.size();
            for(int i =0; i< size;i++){
                Node t = mq.remove();
                System.out.print(t.data+" ");
                for(Node n: t.children){
                    mq.add(n);
                }
            }
            System.out.println();
        }
    }

    public void mirror(Node root){
        Collections.reverse(root.children);
        for(Node n: root.children){
            mirror(n);
        }
    }

    public Node linearize(Node node){
        if(node.children.size() == 0){
            return node;
        }
        Node lastNode = linearize(node.children.get(node.children.size()-1));
        while(node.children.size() > 1){
            Node lst = node.children.remove(node.children.size()-1);
            Node secondLastTail = linearize(node.children.get(node.children.size()-1));
            secondLastTail.children.add(lst);
        }
        return lastNode;
    }

    public boolean search(Node root, int target){
        if(root.data == target){
            return true;
        }
        for (Node child: root.children){
            boolean f = search(child,target);
            if(f){
                return true;
            }
        }
        return false;
    }

    public List<Integer> pathTillNode(Node root, int target){
        if(root.data == target){
            List<Integer> list = new ArrayList<>();
            list.add(root.data);
            return list;
        }

        for (Node child: root.children){
            List<Integer> pathTillNow = pathTillNode(child,target);
            if(pathTillNow.size() > 0){
                pathTillNow.add(root.data);
                return pathTillNow;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        TreeOps treeOps = new TreeOps();
        int[] arr = new int[]{1,2,5,-1,6,-1,7,-1,-1,3,8,12,-1,13,-1,14,-1,-1,9,-1,-1,4,10,-1,11,-1,-1,-1};
        Node root = treeOps.createTree(arr);
        treeOps.displayTree(root);
        System.out.println(treeOps.count(root));
        //treeOps.mirror(root);
        //treeOps.linearize(root);
        treeOps.displayTreeByLevel3(root);
        System.out.println(treeOps.search(root,20));
        System.out.println(treeOps.pathTillNode(root,13));
    }
}
