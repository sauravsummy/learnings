package com.sunsum.dsa.recursion;

public class Printer {

    public static void printTriangle(int r, int c){
        /*       0 1 2 3 4 5
        *     5  # # # # # #
        *     4  # # # # #
        *     3  # # # #
        *     2  # # #
        *     1  # #
        *     0  #
        *
        * */
        if(r == 0){
            return;
        }
        if(c < r){
            System.out.print("* ");
            printTriangle(r,c+1);
        }else{
            System.out.println();
            printTriangle(r-1,0);
        }


    }
    public static void main(String[] args) {
        printTriangle(5,0);
    }
}
