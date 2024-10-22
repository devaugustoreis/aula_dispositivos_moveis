package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimplePaint extends View {

    private Path myPath;
    private Paint myPaint;

    ArrayList<Path> allPaths;
    ArrayList<Paint> allPaints;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        allPaths = new ArrayList<Path>();
        allPaints = new ArrayList<Paint>();
        addLayer(Color.BLACK, 10f);

    }

    public void addLayer(int color, float strokeWidth){
        myPaint = new Paint();
        myPath = new Path();
        allPaints.add(myPaint);
        allPaths.add(myPath);
        setup(color, strokeWidth);
    }

    public void setup (int color, float strokeWidth){
        myPaint.setColor(color);
        myPaint.setStrokeWidth(strokeWidth);
        myPaint.setAntiAlias(true);
        myPaint.setStyle(Paint.Style.STROKE);
    }

    public void setColor(int color){
        Paint currentPaint = allPaints.get(allPaints.size() - 1);
        float strokeWidth = currentPaint.getStrokeWidth();
        addLayer(color, strokeWidth);
    }

    public void setStrokeWidth(int stroke) {
        Paint currentPaint = allPaints.get(allPaints.size() - 1);
        int color = currentPaint.getColor();
        addLayer(color, stroke);
    }


    public void backAction() {
        allPaints.remove(allPaints.size() - 1);
        allPaths.remove(allPaths.size() - 1);
        invalidate();
    }


    public void cleanPaint() {
        allPaints.clear();
        allPaths.clear();
        invalidate();
        addLayer(Color.BLACK, 10f);
    }


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int i=0;
        for (Path path : allPaths) {
            i++;
            canvas.drawPath(path, allPaints.get(i - 1));
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myPath.moveTo(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                myPath.lineTo(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                break;
            default:
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }
}
