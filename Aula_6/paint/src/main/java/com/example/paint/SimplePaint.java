package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimplePaint extends View {

    enum DrawingMode { LIVRE, LINHA, RETANGULO, CIRCULO }

    private DrawingMode drawingMode = DrawingMode.LIVRE;
    private Layer currentLayer;
    private Layer previewLayer;
    private ArrayList<Layer> arrayLayers;
    private int currentColor = Color.BLACK;
    private float currentStrokeWidth = 10f;
    private float startX, startY;


    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        arrayLayers = new ArrayList<>();
        addLayer(currentColor, currentStrokeWidth);
    }

    public void addLayer(int color, float strokeWidth) {
        currentLayer = new Layer(color, strokeWidth);
        arrayLayers.add(currentLayer);
    }

    public void setColor(int color) {
        currentColor = color;
        if (currentLayer != null) {
            currentLayer.getPaint().setColor(currentColor);
        }
    }

    public void setStrokeWidth(int strokeWidth) {
        currentStrokeWidth = strokeWidth;
        if (currentLayer != null) {
            currentLayer.getPaint().setStrokeWidth(currentStrokeWidth);
        }
    }

    public void setDrawingMode(DrawingMode mode) {
        this.drawingMode = mode;
    }

    public void backAction() {
        if (!arrayLayers.isEmpty()) {
            arrayLayers.remove(arrayLayers.size() - 1);

            if (!arrayLayers.isEmpty()) {
                currentLayer = arrayLayers.get(arrayLayers.size() - 1);
            } else {
                addLayer(currentColor, currentStrokeWidth);
            }

            invalidate();
        }
    }

    public void clearPaint() {
        arrayLayers.clear();
        invalidate();
        addLayer(currentColor, currentStrokeWidth);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for (Layer layer : arrayLayers) {
            if (layer.isSquare()) {
                canvas.drawRect(layer.getRectangle(), layer.getPaint());

            } else if (layer.isLine()) {
                canvas.drawLine(layer.getStartX(), layer.getStartY(), layer.getEndX(), layer.getEndY(), layer.getPaint());

            } else if (layer.isCircle()) {
                canvas.drawCircle(layer.getCenterX(), layer.getCenterY(), layer.getRadius(), layer.getPaint());

            } else {
                canvas.drawPath(layer.getPath(), layer.getPaint());
            }
        }

        if (previewLayer != null) {
            Paint dashedPaint = new Paint(previewLayer.getPaint());
            dashedPaint.setStyle(Paint.Style.STROKE);
            dashedPaint.setStrokeWidth(3);
            dashedPaint.setColor(Color.GRAY);

            if (previewLayer.isSquare()) {
                canvas.drawRect(previewLayer.getRectangle(), previewLayer.getPaint());

            } else if (previewLayer.isLine()) {
                canvas.drawLine(previewLayer.getStartX(), previewLayer.getStartY(), previewLayer.getEndX(), previewLayer.getEndY(), previewLayer.getPaint());

            } else if (previewLayer.isCircle()) {
                canvas.drawCircle(previewLayer.getCenterX(), previewLayer.getCenterY(), previewLayer.getRadius(), previewLayer.getPaint());

            } else {
                canvas.drawPath(previewLayer.getPath(), previewLayer.getPaint());
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;

                previewLayer = new Layer(currentColor, currentStrokeWidth);

                if (drawingMode == DrawingMode.LIVRE) {
                    previewLayer.getPath().moveTo(x, y);
                }

                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                if (previewLayer == null) return false;

                if (drawingMode == DrawingMode.LIVRE) {
                    previewLayer.getPath().lineTo(x, y);

                } else if (drawingMode == DrawingMode.LINHA) {
                    previewLayer.setLine(startX, startY, x, y);

                } else if (drawingMode == DrawingMode.RETANGULO) {
                    previewLayer.setRectangle(startX, startY, x, y);

                } else if (drawingMode == DrawingMode.CIRCULO) {
                    float radius = (float) Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
                    previewLayer.setCircle(startX, startY, radius);
                }

                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                if (previewLayer != null) {
                    Layer finalLayer = new Layer(currentColor, currentStrokeWidth);

                    if (drawingMode == DrawingMode.LIVRE) {
                        finalLayer.getPath().set(previewLayer.getPath());

                    } else if (drawingMode == DrawingMode.LINHA) {
                        finalLayer.setLine(previewLayer.getStartX(), previewLayer.getStartY(), previewLayer.getEndX(), previewLayer.getEndY());

                    } else if (drawingMode == DrawingMode.RETANGULO) {
                        finalLayer.setRectangle(previewLayer.getRectangle().left, previewLayer.getRectangle().top, previewLayer.getRectangle().right, previewLayer.getRectangle().bottom);

                    } else if (drawingMode == DrawingMode.CIRCULO) {
                        finalLayer.setCircle(previewLayer.getCenterX(), previewLayer.getCenterY(), previewLayer.getRadius());
                    }

                    arrayLayers.add(finalLayer);
                    previewLayer = null;
                }

                invalidate();
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }

}