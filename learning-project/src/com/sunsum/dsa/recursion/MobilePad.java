package com.sunsum.dsa.recursion;

public class MobilePad {

    /*
    *  1       2(abc) 3(def)
    *  4(ghi)  5(jkl) 6(mno)
    *  7(pqrs)  8(tuv) 9(wxyz)
    *          0()
    *
    * Given Mobile pad  digits, answer all possible text with that digits
    * example "23"
    *ans = ["ad","ae","af,"bd","be","bf","cd","ce","cf"]
    *
    * */

    public static void printPossibleText(String input, String result){
        if(input.isEmpty()){
            System.out.println(result);
            return;
        }
        int digit = input.charAt(0) - '0';
        int k =(digit - 2)*3;
        int l = k+3;
        if(digit == 8 || digit == 9){
            k=k+1;
            l = l+1;
        }
        if(digit == 7 || digit ==9){
            l = l+1;
        }

        for(int i = k ; i< l; i++){
            char ch = (char)('a' + i);
            printPossibleText(input.substring(1),result+ch);
        }
    }

    public static void main(String[] args) {
        printPossibleText("89","");
    }
}
