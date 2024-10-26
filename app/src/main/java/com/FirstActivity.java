package com;

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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.SecondActivity;
import com.example.savedata.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signInEmailEditText, signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAugh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);


        mAugh = FirebaseAuth.getInstance();
        signInEmailEditText = findViewById(R.id.signInEmailedittextid);
        signInPasswordEditText = findViewById(R.id.signInPasswordedittextid);
        signUpTextView = findViewById(R.id.signUpTextviewid);
        signInButton = findViewById(R.id.signinbuttonid);

        progressBar = findViewById(R.id.progressbaroneid);

        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
     if(v.getId()==R.id.signinbuttonid){
         userLogin();



     }
     else if (v.getId()==R.id.signUpTextviewid){
         Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
         startActivity(intent);

     }


    }

    private void userLogin() {
        String email = signInEmailEditText.getText().toString().toString();
        String password = signInPasswordEditText.getText().toString().toString();

        if(email.isEmpty()){
            signInEmailEditText.setError("Enter an email");
            signInEmailEditText.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signInEmailEditText.setError("Enter a valid email");
            signInEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            signInPasswordEditText.setError("Enter valid password");
            signInPasswordEditText.requestFocus();
            return;
        }
        if(password.length()<6) {
            signInPasswordEditText.setError("Minimum length of password is 6");
            signInPasswordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAugh.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(FirstActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}