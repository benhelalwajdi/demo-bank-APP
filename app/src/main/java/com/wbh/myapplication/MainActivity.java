package com.wbh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mailTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  initComponent();
    }

    public void clickAction(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                EditText username = findViewById(R.id.input_email);
                EditText password = findViewById(R.id.input_password);
                if (!username.getText().equals("ffdcuser1")){
                    if (!password.getText().equals("123456")){
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        break;
                    }else {
                        Toast.makeText(getApplicationContext(),"Password is wrong",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Username is wrong",Toast.LENGTH_LONG).show();
                }
        }
    }

}