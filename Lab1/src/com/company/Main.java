package com.company;

public class Main {
    public static void fillArrays(int[] array, int[] array_copy){
        for(int i = 0; i<N; i++){
            array[i] = (int)(Math.random()*100);
            array_copy[i] = array[i];
        }
    }

    public static void printArray(int [] array){
        for(int i:array) {
            System.out.print(i + " ");
        }
        System.out.println('\n');
    }

    public static void copyArray(int [] array, int[] arrayOriginal){
        for(int i = 0; i<N; i++){
            array[i] = arrayOriginal[i];
        }
    }

    public static int selectionSort(int [] array){
        int minElement, minIndex, buff, iterationsCount = 0;
        for(int i = 0; i<N-1; i++){
            minElement=array[i];
            minIndex=i;
            for(int j = i+1; j<N; j++) {
                if (array[j] < minElement) {
                    minElement = array[j];
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                buff = array[minIndex];
                array[minIndex] = array[i];
                array[i] = buff;
                iterationsCount++;
            }
        }
        return iterationsCount;
    }

    public static int bubbleSort(int [] array){
        int buff, iterationsCount = 0;
        for(int i=0;i<=N-2;i++)
            for(int j=i+1;j<=N-1;j++)
                if (array[i]>array[j]){
                    buff = array[i];
                    array[i] = array[j];
                    array[j] = buff;
                    iterationsCount++;
                }
        return iterationsCount;
    }
    public static int N = 200000;

    public static void main(String[] args) {
        int [] arr = new int[N];
        int [] arr_copy = new int[N];
       // int [] arr1 = new int[N];
        //int [] arr1_copy = new int[N];
        //int [] arr2 = new int[N];
        //int [] arr2_copy = new int[N];
        //System.out.println("Исходные массивы:");

        fillArrays(arr,arr_copy);
        //copyArray(arr_copy,arr);

        //fillArrays(arr1,arr1_copy);
        //copyArray(arr1_copy,arr1);

   //     fillArrays(arr2,arr2_copy);
        //copyArray(arr2_copy,arr2);

        System.out.println("Отсортированные массивы:");
        System.out.println("Выбором");

        System.out.println("Количество перестановок: " + selectionSort(arr));

        //System.out.println("Количество перестановок: " + selectionSort(arr1));

        //System.out.println("Количество перестановок: " + selectionSort(arr2));

        System.out.println("Пузырьком");

        System.out.println("Количество перестановок: " + bubbleSort(arr_copy));

//        System.out.println("Количество перестановок: " + bubbleSort(arr1_copy));

  //      System.out.println("Количество перестановок: " + bubbleSort(arr2_copy));
    }
}

