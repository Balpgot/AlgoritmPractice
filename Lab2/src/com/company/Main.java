package com.company;

import java.util.Scanner;

public class Main {

    public static String generate(int value){
        char [] alphabet = {'a','b','c','d','e'};
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<value; i++){
            builder.append(alphabet[(int)(Math.random()*4)]);
        }
        return builder.toString();
    }

    public static boolean KMP(String original, String search){
        int i = 0, search_length, original_length, count = 0;
        boolean found = false;
        original_length = original.length();
        search_length = search.length();
        while(i<original_length){
            for(int k  = 0; k<search_length; k++){
                count++;
                //System.out.println("I= "+ i);
                //System.out.println("K= "+ k);
                if((search.charAt(k) == original.charAt(i))){
                    i++;
                }
                else{
                    if(k==0) i++;
                    break;
                }
                if(k==search_length-1){
                    found = true;
                    break;
                }
            }
        }
        System.out.println("KMP steps = " + count);
        return found;
    }

    public static boolean BMH(String original, String search) {
        int[] alphabet = new int[256];
        boolean found = false;
        int count = 0;
        int original_length = original.length();
        int search_length = search.length();
        for (int i = search_length - 2; i >= 0; i--) {
            if (alphabet[search.charAt(i)] == 0) {
                alphabet[search.charAt(i)] = search_length - 1 - i;
            }
        }
        for (int i = 0; i < 256; i++) {
            if (alphabet[i] == 0) {
                alphabet[i] = search_length;
            }
        }
        int i = search_length-1;
        int current_i = search_length-1;
        while (i < original_length) {
            for (int k = search_length - 1; k >= 0; k--) {
                //System.out.println("original: "+original.charAt(current_i));
                //current_iSystem.out.println("search: "+search.charAt(k));
                count++;
                if (original.charAt(current_i) != search.charAt(k)) {
                    i+=alphabet[original.charAt(current_i)];
                    current_i = i;
                    break;
                }
                else if(original.charAt(current_i) == search.charAt(k)){
                    current_i--;
                }
                if(k==0){
                    found = true;
                }
            }
        }
        System.out.println("BMH steps = " + count);
        return found;
    }

    public static void main(String[] args) {
        String search, original;
        /*Scanner input = new Scanner(System.in);
        original = input.nextLine();
        search = input.nextLine();*/
        search = generate(5);
        original = generate(150);
        System.out.println(original);
        System.out.println(search);
        System.out.println("KMP result = " + KMP(original,search));
        System.out.println("BMH result = " + BMH(original,search));
    }
}
