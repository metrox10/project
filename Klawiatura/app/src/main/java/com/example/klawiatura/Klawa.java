package com.example.klawiatura;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class Klawa extends LinearLayout {

    public Button buttonText, buttonM, buttonCamera, butttonSave, buttonToast, buttonRoz,
                   buttonCheckNFC, buttonNFC, buttonWiFi, buttonLoad;

    public Klawa(Context context) {
        this(context, null, 0);
    }

    public Klawa(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Klawa(Context context, AttributeSet attributeSet, int defStyleAttribute) {
        super(context, attributeSet, defStyleAttribute);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        buttonText = (Button) findViewById(R.id.button_Text);
        buttonM = (Button) findViewById(R.id.button_MP3);
        buttonCamera = (Button) findViewById(R.id.button_Camera);
        butttonSave = (Button) findViewById(R.id.button_Save);
        buttonToast = (Button) findViewById(R.id.button_Toast);
        buttonRoz = (Button) findViewById(R.id.button_Rozwin);

        buttonCheckNFC = (Button) findViewById(R.id.button_StatNFC);
        buttonCheckNFC.setVisibility(INVISIBLE);
        buttonNFC = (Button) findViewById(R.id.button_NFC);
        buttonNFC.setVisibility(INVISIBLE);
        buttonWiFi = (Button) findViewById(R.id.buttonWiFi);
        buttonWiFi.setVisibility(INVISIBLE);
        buttonLoad = (Button) findViewById(R.id.button_Load);
        buttonLoad.setVisibility(INVISIBLE);
    }
}
