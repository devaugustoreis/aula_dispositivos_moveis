package com.ifsc.paint_refeito2;

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

    Path mPath;
    Paint mPaint;

    ArrayList<Path> allPaths;
    ArrayList<Paint> allPaints;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        /*
        for (Path p : allPaths) {
            canvas.drawPath(mPath, mPaint);
        }
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                /*allPaths.add(mPath);*/                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();
        return true;
    }
}
