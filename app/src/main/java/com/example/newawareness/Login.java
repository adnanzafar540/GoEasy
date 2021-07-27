package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button submit;
    TextView email;
    TextView Password;
    FirebaseAuth mAuth;
        TextView signupAccount;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = (TextView) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        signupAccount = (TextView) findViewById(R.id.Createacount);
        submit = (Button) findViewById(R.id.submitt);
        signupAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUP.class);
                startActivity(intent);
                finish();
                email.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                       // String SituationName = Situation_Name.getText().toString();

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

    }

    public void singin(View view) {
        String Email = email.getText().toString();
        final String Pass = Password.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signe
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (Pass.length() < 6) {
                                Toast.makeText(Login.this,"Please Enter Password Length Above 6", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Login.this,"Not Authentic Loag_in Details", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(Login.this, Show_Add_records.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                });
    }


}