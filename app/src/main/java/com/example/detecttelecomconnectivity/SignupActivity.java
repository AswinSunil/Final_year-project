package com.example.detecttelecomconnectivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class SignupActivity extends AppCompatActivity {

    EditText email,password,repassword;
    Button create;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        email = (EditText)findViewById(R.id.editText3);
        password = (EditText)findViewById(R.id.editText4);
        repassword = (EditText)findViewById((R.id.editText5));
        create = (Button)findViewById(R.id.button4);

        firebaseAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String dbemail = email.getText().toString().trim();
                String dbpassword = password.getText().toString().trim();
                String dbrepassword = repassword.getText().toString().trim();

                if (TextUtils.isEmpty(dbemail)) {
                    Toast.makeText(getApplicationContext(), "Please enter email..!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(dbpassword)) {
                    Toast.makeText(getApplicationContext(), "Please enter password..!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(dbrepassword)) {
                    Toast.makeText(getApplicationContext(), "Please RE-enter password..!", Toast.LENGTH_LONG).show();
                    return;
                }


                if(dbpassword.equals(dbrepassword)) {
                    firebaseAuth.createUserWithEmailAndPassword(dbemail,dbpassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registration successful! Proceeding to login screen", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration not successful!", Toast.LENGTH_LONG).show();

                                    }

                                }
                            });
                }
                else {
                    Toast.makeText(getApplicationContext(), "The passwords do not match!", Toast.LENGTH_LONG).show();

                }



            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
