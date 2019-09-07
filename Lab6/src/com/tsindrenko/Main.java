package com.tsindrenko;

import java.util.*;

public class Main {

    private static final int N = 600;
    private static Position startPoint;
    private static Position finishPoint;
    private static List<Integer> priority; //up, down, left, right
    private static Queue<Position> queue;

    private static void setPriority(int up,int down,int left,int right){
        priority = new ArrayList<>();
        priority.add(up);
        priority.add(down);
        priority.add(left);
        priority.add(right);
    }

    private static void getRoute(int [][] route, int [][] array){
        int currentX = finishPoint.getX();
        int currentY = finishPoint.getY();
        route[startPoint.getX()][startPoint.getY()] = -2;
        route[finishPoint.getX()][finishPoint.getY()] = -3;
        int currentPriority = 3;
        int currentDirection;
        boolean stop = false;
        boolean up,left,right,down,anotherWay = false, anotherWayTried = false;
        while(route[currentX][currentY]!=-2 && !stop){
            up = false;
            left = false;
            right = false;
            down = false;
            currentDirection = priority.indexOf(currentPriority);
            switch (currentDirection){
                case 0: {
                    if (currentX - 1 >= 0) {
                        if (route[currentX - 1][currentY] != -1) {
                            up = true;
                            if (array[currentX - 1][currentY] <= array[currentX][currentY] || anotherWay) {
                                if(anotherWay){
                                    anotherWay = false;
                                    anotherWayTried=true;
                                }
                                else
                                    anotherWayTried = false;
                                route[currentX - 1][currentY] = 1;
                                currentX--;
                                currentPriority = 3;
                            } else
                                currentPriority--;
                        } else
                            currentPriority--;
                    }
                    else {
                        currentPriority--;
                    }
                    break;
                }
                case 1: {
                    if (currentX + 1 < N) {
                        if (route[currentX + 1][currentY] != -1) {
                            down = true;
                            if (array[currentX + 1][currentY] <= array[currentX][currentY]|| anotherWay) {
                                if(anotherWay){
                                    anotherWay = false;
                                    anotherWayTried=true;
                                }
                                else
                                    anotherWayTried = false;
                                route[currentX + 1][currentY] = 1;
                                currentX++;
                                currentPriority = 3;
                            } else
                                currentPriority--;

                        } else
                            currentPriority--;
                    }
                    else {
                        currentPriority--;
                    }
                    break;
                }
                case 2: {
                    if (currentY - 1 >= 0) {
                        if (route[currentX][currentY - 1] != -1) {
                            left = true;
                            if (array[currentX][currentY - 1] <= array[currentX][currentY]|| anotherWay) {
                                if(anotherWay){
                                    anotherWay = false;
                                    anotherWayTried=true;
                                }
                                else
                                    anotherWayTried = false;
                                route[currentX][currentY - 1] = 1;
                                currentY--;
                                currentPriority = 3;
                            } else
                                currentPriority--;
                        } else
                            currentPriority--;
                    }
                    else {
                        currentPriority--;
                    }
                    break;
                }
                case 3: {
                    if (currentY + 1 < N) {
                        if (route[currentX][currentY + 1] != -1) {
                            right = true;
                            if (array[currentX][currentY + 1] <= array[currentX][currentY]|| anotherWay) {
                                if(anotherWay){
                                    anotherWay = false;
                                    anotherWayTried=true;
                                }
                                else
                                    anotherWayTried = false;
                                route[currentX][currentY + 1] = 1;
                                currentY++;
                                currentPriority = 3;
                            } else
                                currentPriority--;
                        } else
                            currentPriority--;
                    }
                    else {
                        currentPriority--;
                    }
                    break;
                }
                default: {
                    stop = true;
                }
            }

            if(currentPriority<0){
                if(up || down || left || right && !anotherWayTried){
                    anotherWay = true;
                    currentPriority = 3;
                }
                else{
                    stop = true;
                }

            }
            if(currentX-1>=0 && currentX-1==startPoint.getX()){
                if(currentY==startPoint.getY()){
                    stop=true;
                }
            }
            if(currentX+1<N && currentX+1==startPoint.getX()){
                if(currentY==startPoint.getY()){
                    stop=true;
                }
            }
            if(currentY-1>=0 && currentY-1==startPoint.getX()){
                if(currentX==startPoint.getX()){
                    stop=true;
                }
            }
            if(currentY+1<N && currentY+1==startPoint.getX()){
                if(currentX==startPoint.getX()){
                    stop=true;
                }
            }
            if(currentX==startPoint.getX() && currentY==startPoint.getY()){
                stop=true;
                route[startPoint.getX()][startPoint.getY()] = -2;
            }
        }
        /*for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(route[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    private static boolean makeWave(int [][] array, int i, int j){
        for(int x = i-1; x<i+2; x++)
            for(int y = j-1; y<j+2; y++) {
                if (x >= 0 && y >= 0 && x < N && y < N){
                    Position current = new Position(x,y);
                    if ((array[x][y] == 0 && !startPoint.equals(current))) {
                        if (x == finishPoint.getX() && y == finishPoint.getY()) {
                            array[x][y] = array[i][j] + 1;
                            queue.clear();
                            return true;
                        } else {
                            array[x][y] = array[i][j] + 1;
                            queue.add(new Position(x, y));
                        }
                    }
                }
            }
        queue.poll();
        return false;
    }

    private static void fourRays(int [][]array) {
        System.out.println(startPoint);
        System.out.println(finishPoint);
        int [][] route = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(array[i][j]==-1){
                    route[i][j] = 9;
                }
            }
        }
        route[startPoint.getX()][startPoint.getY()] = 7;
        route[finishPoint.getX()][finishPoint.getY()] = 7;
        int currentStartXUp = startPoint.getX();
        int currentStartXDown = startPoint.getX();
        int currentStartYLeft = startPoint.getY();
        int currentStartYRight = startPoint.getY();
        int currentFinishXUp = finishPoint.getX();
        int currentFinishXDown = finishPoint.getX();
        int currentFinishYLeft = finishPoint.getY();
        int currentFinishYRight = finishPoint.getY();
        boolean sU=true,sD=true,sL=true,sR=true,fU=true,fD=true,
                fL=true,fR=true,stop = false;
        while (!stop) {
            if(currentStartXUp-1>=0 && array[currentStartXUp-1][startPoint.getY()]!=-1){
                currentStartXUp--;
                if(route[currentStartXUp][startPoint.getY()] == 1){
                    System.out.println("Путь найден SU");
                    break;
                }
                route[currentStartXUp][startPoint.getY()] = 1;
            }
            else
                sU = false;
            if(currentStartXDown+1<N && array[currentStartXDown+1][startPoint.getY()]!=-1){
                currentStartXDown++;
                if(route[currentStartXDown][startPoint.getY()] == 1){
                    System.out.println("Путь найден SD");
                    break;
                }
                route[currentStartXDown][startPoint.getY()] = 1;
            }
            else
                sD = false;
            if(currentStartYLeft-1>=0 && array[startPoint.getX()][currentStartYLeft-1]!=-1){
                currentStartYLeft--;
                if(route[startPoint.getX()][currentStartYLeft] == 1){
                    System.out.println("Путь найден SL");
                    break;
                }
                route[startPoint.getX()][currentStartYLeft] = 1;
            }
            else
                sL = false;
            if(currentStartYRight+1<N && array[startPoint.getX()][currentStartYRight+1]!=-1){
                currentStartYRight++;
                if(route[startPoint.getX()][currentStartYRight] == 1){
                    System.out.println("Путь найден SR");
                    break;
                }
                route[startPoint.getX()][currentStartYRight] = 1;
            }
            else
                sR = false;

            if(currentFinishXUp-1>=0 && array[currentFinishXUp-1][finishPoint.getY()]!=-1){
                currentFinishXUp--;
                if(route[currentFinishXUp][finishPoint.getY()] == 1){
                    System.out.println("Путь найден FU");
                    break;
                }
                route[currentFinishXUp][finishPoint.getY()] = 1;
            }
            else
                fU = false;
            if(currentFinishXDown+1<N && array[currentFinishXUp+1][finishPoint.getY()]!=-1){
                currentFinishXDown++;
                if(route[currentFinishXDown][finishPoint.getY()] == 1){
                    System.out.println("Путь найден FD");
                    break;
                }
                route[currentFinishXDown][finishPoint.getY()] = 1;
            }
            else
                fD = false;
            if(currentFinishYLeft-1>=0 && array[finishPoint.getX()][currentFinishYLeft-1]!=-1){
                currentFinishYLeft--;
                if(route[finishPoint.getX()][currentFinishYLeft] == 1){
                    System.out.println("Путь найден FL");
                    break;
                }
                route[finishPoint.getX()][currentFinishYLeft] = 1;
            }
            else
                fL = false;
            if(currentFinishYRight+1<N && array[finishPoint.getX()][currentFinishYRight+1]!=-1){
                currentFinishYRight++;
                if(route[finishPoint.getX()][currentFinishYRight] == 1){
                    System.out.println("Путь найден FR");
                    break;
                }
                route[finishPoint.getX()][currentFinishYRight] = 1;
            }
            else
                fR = false;

            if(!(sU || sD || sL || sR || fU || fD || fL || fR)){
                System.out.println("Путь не найден");
                stop = true;
            }
        }
        /*for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(route[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    public static void main(String[] args) {
        int [][] field = new int[N][N];
        int [][] route = new int[N][N];
        int [][] array = new int[N][N];

        setPriority(3,0,1,2);

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(Math.random()*100<20){
                    field[i][j] = -1;
                    route[i][j] = -1;
                    array[i][j] = -1;
                }
            }
        }
        
        startPoint = new Position(1*N/10,3*N/10);
        finishPoint = new Position(4*N/10,2*N/10);

        while (field[startPoint.getX()][startPoint.getY()]==-1){
            startPoint.setX((int)(Math.random()*N));
            startPoint.setY((int)(Math.random()*N));
        }

        while (field[finishPoint.getX()][finishPoint.getY()]==-1 || finishPoint.equals(startPoint)){
            finishPoint.setX((int)(Math.random()*N));
            finishPoint.setY((int)(Math.random()*N));
        }
        long start = System.nanoTime();
        fourRays(array);
        long end = System.nanoTime();
        System.out.println("Четырелуча");
        System.out.println((end - start) / 1000000);
        queue = new LinkedList<>();
        boolean stop = false;
        start = System.nanoTime();
        while(!stop){
            queue.add(startPoint);
            while(!queue.isEmpty()){
                if(makeWave(field,queue.peek().getX(),queue.peek().getY())){
                    stop = true;
                    field[finishPoint.getX()][finishPoint.getY()]++;
                }
            }
            System.out.println("\n Волны \n");
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(!(i==startPoint.getX() && j==startPoint.getY()) && field[i][j]==0){
                        field[i][j] = -1;
                        route[i][j] = -1;
                    }
                }
            }

            stop = true;
        }
        end = System.nanoTime();
        System.out.println("Волновой");
        System.out.println((end - start) / 1000000);
    }
}

