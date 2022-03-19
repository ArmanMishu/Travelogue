package com.example.travelogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    ProgressDialog loadingBar;

    Button log_in_button;
    EditText email_input, pass_input;
    TextView reg_page_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadingBar = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

        log_in_button = findViewById(R.id.SignInButton);
        email_input = findViewById(R.id.edit_email);
        pass_input = findViewById(R.id.edit_pass);
        reg_page_link = findViewById(R.id.sign_up_text);


        log_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Logging In");
                loadingBar.setMessage("Please wait, while we're checking...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                LoginUser();
            }
        });

        reg_page_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }

    private void LoginUser() {
        String user_password, user_email;
        user_password = pass_input.getText().toString();
        user_email = email_input.getText().toString();

        if (TextUtils.isEmpty(user_email)){
            email_input.setError("Email cannot be empty");
            email_input.requestFocus();
            loadingBar.dismiss();
        }
        else if (TextUtils.isEmpty(user_password)){
            pass_input.setError("Password cannot be empty");
            pass_input.requestFocus();
            loadingBar.dismiss();
        }
        else{

            auth.signInWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        loadingBar.dismiss();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Login Failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
        loadingBar.dismiss();
    }
}