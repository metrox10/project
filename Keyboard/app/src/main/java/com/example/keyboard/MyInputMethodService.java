package com.example.keyboard;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.widget.EditText;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.content.Intent;
import android.widget.Toast;
import android.app.AlertDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import static android.provider.Settings.ACTION_WIRELESS_SETTINGS;


public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {


    KeyboardView keyboardView;
    Keyboard keyboard_1_page;
    Keyboard keyboard_2_page;
    @Override
    public View onCreateInputView() {
        // get the KeyboardView and add our Keyboard layout to it
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.number_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    public View onCreateInputView2() {
        // get the KeyboardView and add our Keyboard layout to it
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.number_pad2);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }
    public View onCreateInputView3() {
        // get the KeyboardView and add our Keyboard layout to it
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.number_pad3);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }


    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);

        setInputView(onCreateInputView());
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }


    @Override

    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();


        if (inputConnection != null) {
            switch (primaryCode) {
                case Keyboard.KEYCODE_DELETE :
                    CharSequence selectedText = inputConnection.getSelectedText(0);
                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }
                    break;
                case 70:
                    inputConnection.setComposingText("Wiadomość", 1);
                    inputConnection.finishComposingText();

                    break;
                case 60:

                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    break;

                case 61:
                    final MediaPlayer mp = MediaPlayer.create(this, R.raw.notti);
                    mp.start();

                    break;


                case 62:

                    break;


                case 73:
                    Toast.makeText(this, "To jest TOAST", Toast.LENGTH_LONG).show();

                    break;

                case 74:
                    NfcManager manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
                    NfcAdapter adapter = manager.getDefaultAdapter();
                    if (adapter == null){
                        Toast.makeText(this, "Brak modułu NFC", Toast.LENGTH_LONG).show();
                    } else if (adapter.isEnabled()) {
                        Toast.makeText(this, "NFC ON", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "NFC OFF", Toast.LENGTH_LONG).show();
                    }

                    break;

                case 65:
                    NfcManager m_manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
                    NfcAdapter m_adapter = m_manager.getDefaultAdapter();

                    if (m_adapter == null){
                        Toast.makeText(this, "Brak modułu NFC", Toast.LENGTH_LONG).show();
                    } else if (m_adapter.isEnabled()) {
                        Toast.makeText(this, "NFC jest włączone.", Toast.LENGTH_LONG).show();
                        Intent intentNFC = new Intent(Settings.ACTION_NFC_SETTINGS);
                        intentNFC.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentNFC);
                    } else {
                        Toast.makeText(this, "NFC jest wyłączone.", Toast.LENGTH_LONG).show();
                        Intent intentNFC = new Intent(Settings.ACTION_NFC_SETTINGS);
                        intentNFC.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentNFC);
                    }

                    break;

                case 66:
                    Intent WIFIintent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    WIFIintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(WIFIintent);


                    break;
                case 71:
                    Intent BTintent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                    BTintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(BTintent);


                    break;

                case 67:
                    setInputView(onCreateInputView2());

                    break;

                case 68:
                    setInputView(onCreateInputView3());

                    break;

                case 69:
                    setInputView(onCreateInputView());

                    break;

                default:
                    char code = (char) primaryCode;
                    inputConnection.commitText(String.valueOf(code), 1);
            }
            AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            double vol = 0.5; //This will be half of the default system sound
            am.playSoundEffect(AudioManager.FX_KEY_CLICK, (float) vol);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}