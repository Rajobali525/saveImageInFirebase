package com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.SecondActivity;
import com.example.savedata.R;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signInEmailEditText, signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton,viewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        signInEmailEditText = findViewById(R.id.signInEmailedittextid);
        signInPasswordEditText = findViewById(R.id.signInPasswordedittextid);
        signUpTextView = findViewById(R.id.signUpTextviewid);
        signInButton = findViewById(R.id.signinbuttonid);
        viewdata = findViewById(R.id.viewdata);

        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        viewdata.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
     if(v.getId()==R.id.signinbuttonid){



     }
     else if (v.getId()==R.id.signUpTextviewid){
         Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
         startActivity(intent);

     } else if (v.getId()==R.id.viewdata) {
         Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
         startActivity(intent);


     }


    }

}