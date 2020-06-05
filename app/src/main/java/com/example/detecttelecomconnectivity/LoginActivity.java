package com.example.detecttelecomconnectivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    Button signup;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button1);
        signup = findViewById(R.id.button2);

        firebaseAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String email1 = email.getText().toString();
                String password2 = password.getText().toString();

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplicationContext(), "Please enter your email..!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(getApplicationContext(), "Please enter your password..!", Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email1, password2)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);

                                } else {
                                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
            }
        });

    }


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
