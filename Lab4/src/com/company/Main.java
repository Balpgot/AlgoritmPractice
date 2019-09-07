package com.company;

import java.util.*;

public class Main {

    public static final int N = 500;

    public static int[][] Cruscal(int [][] array){
        int [][] minOstov = new int[N][N];
        List<List<Integer>> routes = new ArrayList<>();
        for(int i = 0; i<N; i++){
            routes.add(i, new ArrayList<>());
        }

        for(int i = 0; i<N; i++){
            routes.get(i).add(i);
        }

        boolean stop = false;
        int min;
        int minI;
        int minJ;
        boolean add;
        while (!stop){
            min = 999;
            minI = -1;
            minJ = -1;
            add = true;
            for(int i = 0; i<N; i++)
                for(int j = 0; j<N; j++){
                    if(array[i][j]<min && minOstov[i][j] == 0 && array[i][j] > 0){
                        min = array[i][j];
                        minI = i;
                        minJ = j;
                    }
                }

            for(int i = 0; i<routes.size(); i++)
                if(routes.get(i).contains(minI) && routes.get(i).contains(minJ) || minI<0){
                    add = false;
                    if(!(minI<0)){
                        minOstov[minI][minJ] = -1;
                    }
                }

            if(add) {
                minOstov[minI][minJ] = min;
                minOstov[minJ][minI] = min;


                if (!routes.get(minI).contains(minJ)) {
                    routes.get(minI).add(minJ);
                }

                if (!routes.get(minJ).contains(minI)) {
                    routes.get(minJ).add(minI);
                }

                for (int i = 0; i < routes.size(); i++) {
                    if ((routes.get(i).contains(minI) || routes.get(i).contains(minJ)) && !(routes.get(i).contains(minI) && routes.get(i).contains(minJ))) {
                        for (int j = 0; j < routes.get(minI).size(); j++) {
                            if (!routes.get(i).contains(routes.get(minI).get(j))) {
                                routes.get(i).add(routes.get(minI).get(j));
                            }
                        }
                    }
                }

                for (int i = 0; i < routes.size(); i++) {
                    if (routes.get(i).contains(minI) && routes.get(i).contains(minJ) && (routes.get(i).size() > routes.get(minI).size() || routes.get(i).size() > routes.get(minJ).size())) {
                        for (int j = 0; j < routes.get(i).size(); j++) {
                            if (!routes.get(minI).contains(routes.get(i).get(j))) {
                                routes.get(minI).add(routes.get(i).get(j));
                            }
                            if (!routes.get(minJ).contains(routes.get(i).get(j))) {
                                routes.get(minJ).add(routes.get(i).get(j));
                            }
                        }
                    }
                }
            }
            stop = false;
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).size() == N) {
                    stop = true;
                }
            }
        }

        for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if(minOstov[i][j]==-1){
                    minOstov[i][j] = 0;
                }
            }
        }

        System.out.println("Матрица минимального остова Крускала");

        /*for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(minOstov[i][j] + " ");
            }
            System.out.println();
        }*/

        return minOstov;
    }

    public static int[][] Prim(int [][] array){
        int [][] minOstov = new int[N][N];
        List<Integer> route = new ArrayList<>();
        route.add(0);
        int min;
        int minI;
        int minJ;
        while(route.size()!=N){
            min = 999;
            minI = -1;
            minJ = -1;
            for(int i = 0; i<N; i++)
                for(int j = 0; j<N; j++){
                    if(array[i][j]<min && minOstov[i][j] == 0 && array[i][j] > 0 && (route.contains(i)||route.contains(j)) && !(route.contains(i)&&route.contains(j))){
                        min = array[i][j];
                        minI = i;
                        minJ = j;
                    }
                }
            minOstov[minI][minJ] = min;
            minOstov[minJ][minI] = min;

            if(route.contains(minJ)){
                route.add(minI);
            }
            else
                route.add(minJ);
        }
        System.out.println("Матрица минимального остова Прима");

        /*for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(minOstov[i][j] + " ");
            }
            System.out.println();
        }*/
        return minOstov;
    }

    public static void main(String[] args) {

        int [][] array = new int[N][N];

	    for(int i = 0; i<N; i++)
	        for(int j = 0; j<N; j++)
	            if(i!=j){
                    array[i][j] = 1+(int)(Math.random()*50);
                }
                else{
                    array[i][j] = -1;
                }

        for(int i = 0; i<N; i++)
            for(int j = 0; j<N; j++){
                array[j][i] = array[i][j];
            }

        /*for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(array[i][j]+ " ");
            }
            System.out.println();
        }*/
        long start = System.nanoTime();
        Cruscal(array);
        long end = System.nanoTime();
        System.out.println("Крускал " + ((end - start) / 1000000));
        start = System.nanoTime();
        Prim(array);
        end = System.nanoTime();
        System.out.println("Прим " + ((end - start) / 1000000));
    }
}
