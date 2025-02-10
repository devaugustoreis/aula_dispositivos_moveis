package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {

    SimplePaint simplePaint;

    ImageButton colorPickerButton;
    ImageButton fingerBtn;
    ImageButton lineBtn;
    ImageButton squareBtn;
    ImageButton circleBtn;
    ImageButton backBtn;
    ImageButton clearBtn;

    SeekBar seekBar;
    TextView tvStrokeWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);

        colorPickerButton = findViewById(R.id.colorPickerBtn);
        fingerBtn = findViewById(R.id.fingerBtn);
        lineBtn = findViewById(R.id.lineBtn);
        squareBtn = findViewById(R.id.squareBtn);
        circleBtn = findViewById(R.id.circleBtn);
        backBtn = findViewById(R.id.backBtn);
        clearBtn = findViewById(R.id.clearBtn);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        seekBar.setMin(0);
        seekBar.setMax(100);

        tvStrokeWidth = findViewById(R.id.tvStrokeWidth);
        tvStrokeWidth.setText("10");


        colorPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ColorPickerDialog.Builder(MainActivity.this)
                        .setTitle("ColorPicker Dialog")
                        .setPreferenceName("MyColorPickerDialog")
                        .setPositiveButton("Confirmar",
                                new ColorEnvelopeListener() {
                                    @Override
                                    public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                        setColor(envelope);
                                    }
                                })
                        .setNegativeButton(getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                        .attachAlphaSlideBar(true) // the default value is true.
                        .attachBrightnessSlideBar(true)  // the default value is true.
                        .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                        .show();
            }
        });


        fingerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setDrawingMode(SimplePaint.DrawingMode.LIVRE);
            }
        });


        lineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setDrawingMode(SimplePaint.DrawingMode.LINHA);
            }
        });


        squareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setDrawingMode(SimplePaint.DrawingMode.RETANGULO);
            }
        });


        circleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setDrawingMode(SimplePaint.DrawingMode.CIRCULO);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simplePaint.backAction();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvStrokeWidth.setText(Integer.toString(progress));
                simplePaint.setStrokeWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.clearPaint();
            }
        });
    }

    public void setColor(ColorEnvelope envelope) {

        simplePaint.setColor(envelope.getColor());
    }
}