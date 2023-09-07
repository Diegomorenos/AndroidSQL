package com.cide.appadsotarde;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Buscar extends AppCompatActivity {

    Button regresar, btnActualizar, btnBuscar;

    EditText boxDoc, boxPass, boxEstado, boxFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        boxDoc = findViewById(R.id.boxDoc);
        boxPass = findViewById(R.id.boxPass);
        boxEstado = findViewById(R.id.boxEstado);
        boxFecha = findViewById(R.id.boxFecha);

        regresar = findViewById(R.id.regresar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnBuscar = findViewById(R.id.btnBuscar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Buscar.this, MenuPrincipal.class));
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar("https://kratoshoteleria.000webhostapp.com/buscar.php");
            }
        });
    }

    private void buscar(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    boxPass.setText();
                    boxEstado.setText();
                    boxFecha.setText();

                    Toast.makeText(getApplicationContext(), "Busqueda exitosa", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("nro_doc", boxDoc.getText().toString());
                parametros.put("contrasena",boxPass.getText().toString());
                parametros.put("usu_estado",boxEstado.getText().toString());
                parametros.put("restablecimiento",boxFecha.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}