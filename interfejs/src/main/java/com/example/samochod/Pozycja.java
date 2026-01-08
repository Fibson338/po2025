package com.example.samochod;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja() {
        this(0, 0);
    }

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void przesun(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
