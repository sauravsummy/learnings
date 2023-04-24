package com.sunsum.general;

public class CakeSlice {

    public static int getCakeCut(int cut){
        if(cut == 0){
            return 1;
        }
        if(cut == 1){
            return 2;
        }
        return cut + getCakeCut(cut - 1);
    }

    public static void main(String[] args) {
        System.out.println("slices: "+getCakeCut(4));
    }
}
