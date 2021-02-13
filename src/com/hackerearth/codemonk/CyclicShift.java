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

    private static double bi2Double(String binaryString){
        double result=0;
        for(int i = 0; i < binaryString.length(); i++){
//            System.out.println(binaryString.substring(i,i+1));
            result += Integer.parseInt(binaryString.substring(i,i+1)) * Math.pow(2,binaryString.length()-i+1);
        }
        return result;
    }

    private static int shift(String inputString, int shift) {

        // creating double string to ease rotation
        StringBuilder baseString = new StringBuilder();
        baseString.append(inputString).append(inputString);

        // to save shifts
        int maxShits = 0;
        int rep = 0;
        // storing binary string
        double[] binaryStrength = new double[inputString.length()];
        for(int i = 0; i < inputString.length();i++){
            binaryStrength[i] = bi2Double(baseString.substring(i,i+inputString.length()));
        }
//        System.out.println(Arrays.toString(binaryStrength));
        // calculating max
        double maxBin = Double.MIN_VALUE;
        for(double j : binaryStrength){
            if(maxBin < j){
                maxBin = j;
            }
        }
        // getting the index
        for(int j=0; j<binaryStrength.length; j++){
            if(maxBin == binaryStrength[j]){
                maxShits = j;
                rep = binaryStrength.length/j;
                break;
            }
        }
//        System.out.println(maxShits);
        // max string
//        String maxString = baseString.substring(maxShits,maxShits+inputString.length());
////        System.out.println(maxString);
//        int countK = 0;
//
////        while (countK < shift-1) {
//            StringBuilder baseString2 = new StringBuilder();
//            baseString2.append(maxString).append(maxString);
//
//            for(int i = 1; i <= maxString.length();i++){
////                System.out.println(baseString2.substring(i,i+maxString.length()) + ":" +maxString);
//                if (baseString2.substring(i,i+maxString.length()).equals(maxString)){
//                    maxShits += i;
//                    rep = maxString.length()/i;
//                    break;
//                }
//            }

//        countK ++;
//        }

        if (shift > 0) {
            return maxShits * rep;
        }
        return maxShits;
    }

}
