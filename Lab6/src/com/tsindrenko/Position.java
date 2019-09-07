package com.tsindrenko;

public class Position {
    private int x;
    private int y;

    Position(){
        x=0;
        y=0;
    }
    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position obj){
        if(obj==this){
            return true;
        }
        else if(obj.getX()==x && obj.getY()==y){
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(x).append(" ").append(y);
        return builder.toString();
    }
}
