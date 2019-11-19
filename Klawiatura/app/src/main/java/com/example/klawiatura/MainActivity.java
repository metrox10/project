package com.example.klawiatura;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText EDIT_TEXT = findViewById(R.id.editText);
        final Klawa MY_KLAWA = findViewById(R.id.keyboard);


        MY_KLAWA.findViewById(R.id.button_Text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EDIT_TEXT.setText("Edytuj");

            }
        });

        final MediaPlayer SOUND_MEDIA_PLAYER = MediaPlayer.create(this, R.raw.notti);
        MY_KLAWA.findViewById(R.id.button_MP3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SOUND_MEDIA_PLAYER.start();

            }
        });

        final Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        MY_KLAWA.findViewById(R.id.button_Camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cameraIntent);

            }
        });
        final String FILE_NAME = "text.txt";
        MY_KLAWA.findViewById(R.id.button_Save).setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    String toSave = EDIT_TEXT.getText().toString();

                    FileOutputStream fos = null;

                    try {
                        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                        fos.write(toSave.getBytes());

                        EDIT_TEXT.getText().clear();
                        Toast.makeText(MainActivity.this, "Zapisano w " +getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        );

        MY_KLAWA.findViewById(R.id.button_Toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "Przycisk Toast", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 800);
                toast.show();
            }
        });

        MY_KLAWA.findViewById(R.id.button_Rozwin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MY_KLAWA.findViewById(R.id.button_StatNFC).getVisibility() == View.INVISIBLE) {
                    MY_KLAWA.findViewById(R.id.button_StatNFC).setVisibility(View.VISIBLE);
                    MY_KLAWA.findViewById(R.id.button_NFC).setVisibility(View.VISIBLE);
                    MY_KLAWA.findViewById(R.id.buttonWiFi).setVisibility(View.VISIBLE);
                    MY_KLAWA.findViewById(R.id.button_Load).setVisibility(View.VISIBLE);

                    Button buttonExpand = MY_KLAWA.findViewById(R.id.button_Rozwin);
                    buttonExpand.setText("Ukryj");
                } else {
                    MY_KLAWA.findViewById(R.id.button_StatNFC).setVisibility(View.INVISIBLE);
                    MY_KLAWA.findViewById(R.id.button_NFC).setVisibility(View.INVISIBLE);
                    MY_KLAWA.findViewById(R.id.buttonWiFi).setVisibility(View.INVISIBLE);
                    MY_KLAWA.findViewById(R.id.button_Load).setVisibility(View.INVISIBLE);

                    Button buttonExpand = MY_KLAWA.findViewById(R.id.button_Rozwin);
                    buttonExpand.setText("Rozwiń");
                }
            }
        });

        final NfcAdapter NFC_ADAPTER = NfcAdapter.getDefaultAdapter(this);
        MY_KLAWA.findViewById(R.id.button_StatNFC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NFC_ADAPTER == null) {
                    Toast toast = Toast.makeText(MainActivity.this, "Brak NFC", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                    toast.show();
                } else if (NFC_ADAPTER.isEnabled()) {
                    Toast toast = Toast.makeText(MainActivity.this, "NFC Włączone", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "NFC Wyłączone", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 800);
                    toast.show();
                }
            }
        });

        MY_KLAWA.findViewById(R.id.button_NFC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                } else {
                    intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                }
                startActivity(intent);

            }
        });



        MY_KLAWA.findViewById(R.id.buttonWiFi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                }
                startActivity(intent);
            }
        });




            MY_KLAWA.findViewById(R.id.button_Load).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    FileInputStream fis = null;

                    try {
                        fis = openFileInput(FILE_NAME);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();
                        String text;

                        while ((text = br.readLine()) != null) {
                            sb.append(text).append("\n");
                        }

                        EDIT_TEXT.setText(sb.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
    }
}
