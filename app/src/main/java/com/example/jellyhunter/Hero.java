package com.example.jellyhunter;

public class Hero {
    int[] locationYX;
    int image;

    public Hero(int[] locationYX, int image) {
        this.locationYX = locationYX;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public int[] getLocation() {
        return locationYX;
    }
    public void setLocation(int[] locationYX) {
        this.locationYX = locationYX;
    }
    public void setX(int x) {
        this.locationYX[1] = x;
    }
    public void setY(int y) {
        this.locationYX[0] = y;
    }
    public int getX() {
        return locationYX[1];
    }
    public int getY() {
        return locationYX[0];
    }
}
