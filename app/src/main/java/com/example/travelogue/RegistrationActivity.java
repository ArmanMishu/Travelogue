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

public class RegistrationActivity extends AppCompatActivity {

    EditText name, email, password, address, phone, confirm_password;
    Button register;
    TextView login_page_link;
    ProgressDialog loadingBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login_page_link = findViewById(R.id.sign_in_link);

        name = findViewById(R.id.fullName);
        email = findViewById(R.id.email_reg_edit);
        password = findViewById(R.id.password_reg_edit);
        address = findViewById(R.id.address_reg_edit);
        phone = findViewById(R.id.mobile_reg_edit);
        confirm_password = findViewById(R.id.confirm_pass_reg_edit);

        register = findViewById(R.id.reg_signup_button);

        loadingBar = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();


        login_page_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Creating Account");
                loadingBar.setMessage("Please wait, while we're checking...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String user_name, user_phone, user_password, user_email, confirm_pass;

        user_name = name.getText().toString();
        user_phone = phone.getText().toString();
        user_password = password.getText().toString();
        user_email = email.getText().toString();
        confirm_pass = confirm_password.getText().toString();

        if (TextUtils.isEmpty(user_name)){
            Toast.makeText(this, "Name is Required.", Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
        else if (TextUtils.isEmpty(user_phone)){
            Toast.makeText(this, "Phone Number is Required.", Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
        else if (TextUtils.isEmpty(user_email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
            loadingBar.dismiss();
        }
        else if (TextUtils.isEmpty(user_password)){
            password.setError("Password cannot be empty");
            password.requestFocus();
            loadingBar.dismiss();
        }
        else if (user_password.length()<6){
            Toast.makeText(this, "Password must have at least 6 characters.", Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
        else if (user_password.equals(confirm_pass) == false){
            Toast.makeText(this, "Password doesn't match.", Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }
        else{

            auth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        loadingBar.dismiss();

                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, "An error occurred." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
        loadingBar.dismiss();
    }
}