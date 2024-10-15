package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button cleanBtn;
    SimplePaint simplePaint;
    SeekBar seekBar;
    TextView tvStrokeWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simplePaint = findViewById(R.id.simplePaint);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMin(0);
        seekBar.setMax(100);
        button = findViewById(R.id.button);
        cleanBtn = findViewById(R.id.limparBtn);

        tvStrokeWidth = findViewById(R.id.tvStrokeWidth);

        button.setOnClickListener(new View.OnClickListener() {
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

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.cleanPaint();
            }
        });
    }

    public void setColor(ColorEnvelope envelope) {
        simplePaint.setColor(envelope.getColor());
    }
}