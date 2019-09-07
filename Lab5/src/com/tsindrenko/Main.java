package com.tsindrenko;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static final int N = 1000;

    public static void Dijkstra(int array[][]){
        List<Integer> nodes = new ArrayList<>();
        nodes.add(0);
        int minNode = 0;
        int minLength;
        for(int i = 0; i<N; i++){
            minLength = 999;
            for(int j = 0; j<N; j++){
                if(array[i][j]<minLength && !nodes.contains(j)){
                    minLength = array[i][j];
                    minNode = j;
                }
            }
            for(int j = 0; j<N; j++){
                for(int k = 0; k<N; k++){
                    if(j!=k){
                        array[j][k] = Math.min(array[j][k],array[minNode][j]+array[minNode][k]);
                        array[k][j] = array[j][k];
                    }
                }
            }
            nodes.add(minNode);
        }

        System.out.println("Дийкстра");

    }

    public static void Floyd(int array[][]){
        for(int k = 0; k<N; k++)
            for(int i = 0; i<N; i++)
                for(int j = 0; j<N; j++)
                    if(array[i][j]!=999){
                        array[i][j] = Math.min(array[i][j], array[i][k] + array[k][j]);
                    }


        System.out.println("Флойд");
    }

    public static void main(String[] args) {
        int[][] array = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (i != j) {
                    array[i][j] = 1 + (int) (Math.random() * 15);
                    array[j][i] = array[i][j];
                } else {
                    array[i][j] = 999;
                }


        long start = System.nanoTime();
        Dijkstra(array);
        long end = System.nanoTime();
        System.out.println((end - start) / 1000000);
        start = System.nanoTime();
        Floyd(array);
        end = System.nanoTime();
        System.out.println((end - start) / 1000000);
    }
}
