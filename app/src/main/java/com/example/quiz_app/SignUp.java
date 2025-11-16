package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    EditText userName, userEmail, userPhone, password;
    Button register;
    TextView signIn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPhone = findViewById(R.id.userPhone);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        signIn = findViewById(R.id.signIn);
        progressBar = findViewById(R.id.progressbar);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = userName.getText().toString();
                String mEmail = userEmail.getText().toString().trim();
                String mPhone = userPhone.getText().toString().trim();
                String mPassword = password.getText().toString();

                if (TextUtils.isEmpty(mUserName)){
                    userName.setError("Please enter your name");
                    return;
                }if (TextUtils.isEmpty(mEmail)){
                    userEmail.setError("Please enter your email");
                    return;
                }if (TextUtils.isEmpty(mPhone)){
                    userPhone.setError("Please enter your phone number");
                    return;
                }if (TextUtils.isEmpty(mPassword)){
                    password.setError("Please enter your password");
                    return;
                }if (mPassword.length() < 8){
                    password.setError("Password must be greated than 8 caracters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}