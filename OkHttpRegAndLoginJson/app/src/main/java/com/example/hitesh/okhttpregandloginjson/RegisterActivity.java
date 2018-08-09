package com.example.hitesh.okhttpregandloginjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hitesh.okhttpregandloginjson.AsyncTasks.AsyncResponse;
import com.example.hitesh.okhttpregandloginjson.AsyncTasks.WebserviceCall;
import com.example.hitesh.okhttpregandloginjson.Helper.Config;
import com.example.hitesh.okhttpregandloginjson.Helper.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.et_name);
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String na = name.getText().toString();
                final String em = email.getText().toString();
                String pass = password.getText().toString();

                String[]keys = {"mode","r_name","r_email","r_password"};
                String[]values = {"registration",na,em,pass};

                String jsonRequest = Utils.createJsonRequest(keys,values);
                //Toast.makeText(RegisterActivity.this, ""+jsonRequest, Toast.LENGTH_SHORT).show();

                String URL = Config.MAIN_URL;
                new WebserviceCall(RegisterActivity.this, URL, jsonRequest, "Requesting...!!", true, new AsyncResponse() {
                    @Override
                    public void onCallback(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message =jsonObject.getString("message");
                            /*String email = jsonObject.getString("r_email");
                            String password = jsonObject.getString("r_password");*/
                            Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(String message) {

                    }
                }
                ).execute();


            }
        });

    }
}
