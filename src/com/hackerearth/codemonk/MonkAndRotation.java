package com.hackerearth.codemonk;

import java.util.Scanner;

public class MonkAndRotation {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i = 0;
        int testCases = Integer.parseInt(s.nextLine());
        String[] result = new String[testCases];
            while(i < testCases ){
                String[] temp = s.nextLine().split(" ");
                int rotation = Integer.parseInt(temp[1]);
                String values = s.nextLine();
                result[i] = rotate(values, rotation);
                i++;
            }

            for( String j: result){
                System.out.println(j);
            }
    }

    private static String rotate(String value1, int rotation){
        String[] value = value1.split(" ");
        StringBuilder result = new StringBuilder();
        for(int j = value.length-(rotation%value.length); j < value.length; j++) {
            result.append(value[j]).append(" ");
        }
        for(int j = 0; j < value.length-(rotation%value.length); j++) {
            result.append(value[j]).append(" ");
        }
       return result.toString();
    }
}

