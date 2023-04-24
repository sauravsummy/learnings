package com.sunsum.general;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaStream {

    public static void main(String[] args) {
        String str = "summysaurav";
        //Map<String,Long> map = Arrays.stream(str.split("")).collect(Collectors.groupingBy(s->s, Collectors.counting()));
        //Arrays.stream(str.split("")).collect(Collectors.toMap(s->s,s->s+s));
        System.out.println(Arrays.stream(str.split("")).collect(Collectors.groupingBy(s->s)));
    }
}
