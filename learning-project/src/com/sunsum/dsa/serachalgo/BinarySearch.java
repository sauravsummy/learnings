package com.sunsum.dsa.serachalgo;

public class BinarySearch {

    public static int binarySearch(int[] arr, int target){
        int start = 0;
        int end = arr.length - 1;
        while (start < end){
            int mid = start + (end- start)/2;
            if(arr[mid] == target){
                return mid;
            }

            if(target > arr[mid]){
                start = mid+1;
            }else{
                end = mid -1;
            }
        }
        return  -1;
    }

    public static int findPeak(int[]arr){
       //1,2,3,4,5,6,7,5,4,2,1
        var start = 0;
        var end = arr.length - 1;

        while (start <= end){
            int mid = start + (end - start)/2;
            if(arr[mid] > arr[mid-1] && arr[mid+1] <arr[mid]){
                return arr[mid];
            }else if(arr[mid] < arr[mid+1]){
                start = mid+1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[]arr = new int[]{10,10,11,12,13,14,15,16,17,18};
        int []mountain = new int[]{1,2,3,4,5,6,7,8,9,10,5,4,2,1};
        System.out.println(findPeak(mountain));
        //System.out.println(binarySearch(arr,1));
    }
}
