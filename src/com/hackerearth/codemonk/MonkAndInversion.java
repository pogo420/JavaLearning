package com.hackerearth.codemonk;

import java.util.Scanner;

public class MonkAndInversion {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int i = 0;
        int testCases = Integer.parseInt(s.nextLine());
        int[] result = new int[testCases];

        while(i < testCases ){

            int n = Integer.parseInt(s.nextLine());
            int[][] temp = new int[n][n];
            int j = 0;

            while (j < n){
                int k = 0;
                String[] values = s.nextLine().split(" ");
                for (String value: values){
                    temp[j][k] = Integer.parseInt(value);
                    k++;
                }
                j++;
            }

            result[i] = inversionCount(temp);
            i++;
        }

        for(int val: result){
            System.out.println(val);
        }

    }

    private static int inversionCount(int[][] temp) {
        int count = 0;

        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp.length; j++) {
                int ref = temp[i][j];

                for(int i1 = i; i1 < temp.length; i1++) {
                    for (int j1 = j; j1 < temp.length; j1++) {
                            if (ref > temp[i1][j1]){
                                count ++;
                        }
                    }
                }
            }
        }
        return count;
    }

}

/*
1
3
1 2 3
4 5 6
7 8 9
 */
