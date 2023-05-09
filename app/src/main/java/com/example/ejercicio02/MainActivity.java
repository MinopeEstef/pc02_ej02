package com.example.ejercicio02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private EditText user,pass;
    private Spinner profileSpinner;

    Button btnLogin,btnClean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        profileSpinner = findViewById(R.id.profileSpinner);
        btnLogin = findViewById(R.id.btnLogin);
        btnClean = findViewById(R.id.btnClean);

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getText().clear();
                pass.getText().clear();
                profileSpinner.clearFocus();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                String profile = profileSpinner.getSelectedItem().toString();
                String[] datos = Data.DATA_JSON;

                if (username.isEmpty() && password.isEmpty() && profile.isEmpty()){
                    Log.d("string","entra a error");
                    Intent intent = new Intent(MainActivity.this,ErrorActivity.class);
                    startActivity(intent);
                }else{
                    for (int i = 0; i < datos.length; i++) {
                        if(datos[1].equals(username)
                                && datos[2].equals(password)
                                && datos[3].equals(profile)){
                            Intent intent = new Intent(MainActivity.this,RegistryActivity.class);
                            intent.putExtra("name",datos[0]);
                            startActivity(intent);
                        }else{
                            Log.d("string","entra a error");
                            Intent intent = new Intent(MainActivity.this,ErrorActivity.class);
                            startActivity(intent);
                        }
                    }
                }

            }
        });

    }


}