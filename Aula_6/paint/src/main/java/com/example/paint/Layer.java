package com.example.paint;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;


public class Layer {

    private Paint paint;
    private Path path;
    private RectF rectangle;
    private boolean isSquare, isLine, isCircle;
    private float startX, startY, endX, endY, centerX, centerY, radius;


    public Layer(int color, float strokeWidth) {
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        isSquare = false;
        isLine = false;
        isCircle = false;
    }

    public Paint getPaint() {
        return paint;
    }

    public Path getPath() {
        return path;
    }

    public void setRectangle(float startX, float startY, float endX, float endY) {
        rectangle = new RectF(
                Math.min(startX, endX), Math.min(startY, endY),
                Math.max(startX, endX), Math.max(startY, endY)
        );
        isSquare = true;
    }

    public RectF getRectangle() {
        return rectangle;
    }

    public boolean isSquare() {
        return isSquare;
    }

    public void setLine(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        isLine = true;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public boolean isLine() {
        return isLine;
    }

    public void setCircle(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        isCircle = true;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }

    public boolean isCircle() {
        return isCircle;
    }
}
