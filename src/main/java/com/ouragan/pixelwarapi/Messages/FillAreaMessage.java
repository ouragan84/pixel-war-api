package com.ouragan.pixelwarapi.Messages;

public class FillAreaMessage {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int fillColor;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getFillColor() {
        return fillColor;
    }
}
