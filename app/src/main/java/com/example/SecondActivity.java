package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.FirstActivity;
import com.example.savedata.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpEmailEditText, signUpPasswordEditText;
    private TextView signInTextView;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        mAuth = FirebaseAuth.getInstance();


        signUpEmailEditText = findViewById(R.id.signUpEmailedittextid);
        signUpPasswordEditText = findViewById(R.id.signUpPasswordedittextid);
        signInTextView = findViewById(R.id.signintext);
        signUpButton = findViewById(R.id.signUpbuttonid);
        progressBar = findViewById(R.id.progressbarid);

        signInTextView.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.signUpbuttonid){
            userRegister();



        }else if (v.getId()==R.id.signintext){
            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
            startActivity(intent);

        }

    }

    private void userRegister() {
        String email = signUpEmailEditText.getText().toString().toString();
        String password = signUpPasswordEditText.getText().toString().toString();

        if(email.isEmpty()){
            signUpEmailEditText.setError("Enter an email");
            signUpEmailEditText.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signUpEmailEditText.setError("Enter a valid email");
            signUpEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            signUpPasswordEditText.setError("Enter valid password");
            signUpPasswordEditText.requestFocus();
            return;
        }
        if(password.length()<6){
            signUpPasswordEditText.setError("Minimum length of password is 6");
            signUpPasswordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Register is successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Register is not successfully",Toast.LENGTH_LONG).show();


                }

            }
        });

    }
}