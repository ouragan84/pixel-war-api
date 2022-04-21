package com.ouragan.pixelwarapi.Messages;

public class PixelChangeMessage {

    private int x;
    private int y;
    private int color;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PixelChangeMessage(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public PixelChangeMessage() {
        this.x = -1;
        this.y = -1;
        this.color = 0;
    }

    // getters and setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }
}