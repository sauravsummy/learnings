package com.sunsum.dsa.recursion;

import java.util.ArrayList;
import java.util.List;

public class SubsetProblem {

    public static void printAllSubSet(String str, String result){
        if(str.isEmpty()){
            System.out.println(result);
            return;
        }
        String fc =str.substring(0,1);
        printAllSubSet(str.substring(1),result+fc);
        printAllSubSet(str.substring(1),result);

    }

    public static void printSubset(int[]arr){
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        for(int num:arr){
            int n = outer.size();
            for(int i =0 ; i<n;i++){
                List<Integer> inner = new ArrayList<>(outer.get(i));
                inner.add(num);
                outer.add(inner);
            }
        }
        outer.forEach(System.out::println);
    }

    public static String remove(String str, String tobeRemoved, String result){
        if(str.isEmpty()){
            return result;
        }
        String fc = str.substring(0,1);
        if(fc.equals(tobeRemoved)){
            return remove(str.substring(1),tobeRemoved,result);
        }else{
            return remove(str.substring(1),tobeRemoved,result+fc);
        }
    }

    public static void main(String[] args) {
        //printAllSubSet("abc","");
        printSubset(new int[]{1,2,3,4});
        System.out.println(remove("abbcadfgca","a",""));
    }
}
