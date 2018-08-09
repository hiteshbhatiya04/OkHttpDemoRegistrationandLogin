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

public class LoginActivity extends AppCompatActivity {

    EditText lemail,lpass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemail = (EditText)findViewById(R.id.et_lemail);
        lpass = (EditText)findViewById(R.id.et_lpassword);
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em = lemail.getText().toString();
                String pass = lpass.getText().toString();
                String[]keys = {"mode","l_email","l_password"};
                String[]values = {"userAuthantication",em,pass};

                String jsonRequest = Utils.createJsonRequest(keys,values);

                String URL = Config.MAIN_URL;
                new WebserviceCall(LoginActivity.this, URL, jsonRequest, "Requesting...!!", true, new AsyncResponse() {
                    @Override
                    public void onCallback(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("userDetail");
                            String message =jsonObject1.getString("r_name");
                            /*String email = jsonObject.getString("r_email");
                            String password = jsonObject.getString("r_password");*/
                            Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
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
