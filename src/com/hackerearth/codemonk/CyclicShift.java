package com.hackerearth.codemonk;

import java.util.Scanner;

public class CyclicShift {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int i = 0;
        int testCases = Integer.parseInt(s.nextLine());
        int[] result = new int[testCases];

        while(i < testCases ){
            String[] temp = s.nextLine().split(" ");
            int N = Integer.parseInt(temp[0]);
            int K = Integer.parseInt(temp[1]);
            result[i] = shift(s.nextLine(), K);
            i++;
        }
        for(int val: result){
            System.out.println(val);
        }
    }

    private static int shift(String inputString, int shift) {

        // creating double string to ease rotation
        StringBuilder baseString = new StringBuilder();
        baseString.append(inputString).append(inputString);

        // to save shifts
        int maxShits = 0;

        // storing binary string
        int[] binaryStrength = new int[inputString.length()];
        for(int i = 0; i < inputString.length();i++){
            binaryStrength[i] = Integer.parseInt(baseString.substring(i,i+inputString.length()), 2);
        }
//        System.out.println(Arrays.toString(binaryStrength));
        // calculating max
        int maxBin = Integer.MIN_VALUE;
        for(int j : binaryStrength){
            if(maxBin < j){
                maxBin = j;
            }
        }
        // getting the index
        for(int j=0; j<binaryStrength.length; j++){
            if(maxBin == binaryStrength[j]){
                maxShits = j;
                break;
            }
        }
//        System.out.println(maxShits);
        // max string
        String maxString = baseString.substring(maxShits,maxShits+inputString.length());
//        System.out.println(maxString);
        int countK = 0;

        while (countK < shift-1){
            StringBuilder baseString2 = new StringBuilder();
            baseString2.append(maxString).append(maxString);

            for(int i = 1; i <= maxString.length();i++){
//                System.out.println(baseString2.substring(i,i+maxString.length()) + ":" +maxString);
                if (baseString2.substring(i,i+maxString.length()).equals(maxString)){
                    maxShits += i;
                    break;
                }
            }

        countK ++;
        }


        return maxShits;
    }

}
