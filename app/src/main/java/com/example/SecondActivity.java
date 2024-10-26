package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.FirstActivity;
import com.example.savedata.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpEmailEditText, signUpPasswordEditText;
    private TextView signInTextView;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        signUpEmailEditText = findViewById(R.id.signUpEmailedittextid);
        signUpPasswordEditText = findViewById(R.id.signUpPasswordedittextid);
        signInTextView = findViewById(R.id.signintext);
        signUpButton = findViewById(R.id.signUpbuttonid);

        signInTextView.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.signUpbuttonid){



        }else if (v.getId()==R.id.signintext){
            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
            startActivity(intent);

        }

    }
}