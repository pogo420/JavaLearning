package com.hackerearth.codemonk;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MinimumANDxorOR {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i = 0;
        int testCases = Integer.parseInt(s.nextLine());
        int[] result = new int[testCases];

        while(i < testCases ){
            int n = Integer.parseInt(s.nextLine());
            int[] values = new int[n];
            int k = 0;
            for(String v: s.nextLine().split(" ")){
                values[k] = Integer.parseInt(v);
                k++;
            }

            Arrays.sort(values);
            result[i] = minXor(values);
            i++;
        }
        for(int val: result){
            System.out.println(val);
        }
    }

    private static int minXor(int[] values) {
        int min = Integer.MAX_VALUE;
        int temp = Integer.MIN_VALUE;

        for(int i = 0; i <values.length-1; i++){
                temp = values[i] ^ values[i+1];
//                System.out.println(temp);
                if (min > temp){
                    min = temp;
                }
            }
        return min;
    }
}
