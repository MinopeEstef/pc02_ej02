package com.example.ejercicio02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistryActivity extends AppCompatActivity {
    EditText name;
    Spinner paisSpinner,depSpinner;
    RadioGroup radioGroup;
    Button btnCreate,btnBack;
    TextView result;
    String estado,person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        name = findViewById(R.id.name);
        paisSpinner = findViewById(R.id.paisSpinner);
        depSpinner = findViewById(R.id.depSpinner);
        radioGroup = findViewById(R.id.radioGroup);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBack);
        result = findViewById(R.id.result);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            person = intent.getStringExtra("name");
            name.setText(person);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pais = paisSpinner.getSelectedItem().toString();
                String departamento = depSpinner.getSelectedItem().toString();

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        estado = "Casado";
                        break;
                    case R.id.radioButton2:
                        estado = "Soltero";
                        break;
                    case R.id.radioButton3:
                        estado = "Divorciado";
                        break;
                    case R.id.radioButton4:
                        estado = "Viudo";
                        break;
                    default:
                        result.setText("No ha seleccionado una opción");
                        return;
                }
                String jsonString = null;
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", person);
                    jsonObject.put("pais", pais);
                    jsonObject.put("departamento", departamento);
                    jsonObject.put("estado", estado);
                    jsonString = jsonObject.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                result.setText(getResult(jsonString));
            }
        });

    }
    private String getResult(String  jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String nombre = jsonObject.getString("name");
            String pais = jsonObject.getString("pais");
            String departamento = jsonObject.getString("departamento");
            String estado = jsonObject.getString("estado");

            // Mostrar los datos en un TextView
            final String TXT_RESULTADO = "Sr. %s , usted vive en  %s  con departamento %s" +
                    " siendo %s .";
            return String.format(TXT_RESULTADO, nombre,pais,departamento,estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}