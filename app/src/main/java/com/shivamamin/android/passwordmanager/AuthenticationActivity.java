package com.shivamamin.android.passwordmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthenticationActivity extends AppCompatActivity {

    private final int SDK_VER = android.os.Build.VERSION.SDK_INT;
    private EditText pass;
    private Button authenticate;
    private static String password;

    public static Intent newIntent(Context packageContext, String pw) {
        password = pw;
        Intent intent = new Intent(packageContext, AuthenticationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        pass = (EditText) findViewById(R.id.authentication_edit_text);
        authenticate = (Button) findViewById(R.id.authentication_button);
        if(password.equals("")) {
            authenticate.setText("Set Password");
            authenticate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!pass.getText().toString().equals("")) {
                        Intent i = new Intent();
                        i.putExtra("password", pass.getText().toString());
                        setResult(RESULT_OK, i);
                        finish();
                    } else {
                        pass.setText("");
                    }
                }
            });
        } else {
            authenticate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(password.equals(pass.getText().toString())) {
                        Intent i = new Intent();
                        i.putExtra("authentication", true);
                        setResult(RESULT_OK, i);
                        finish();
                    } else {
                        pass.setText("");
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

    }
}
