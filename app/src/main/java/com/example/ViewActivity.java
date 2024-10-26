package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.HomeActivity;
import com.example.savedata.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Button btnDelete;
    DatabaseReference reference,DataRef;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);



        imageView = findViewById(R.id.image_single_view_activity);
        textView = findViewById(R.id.textView_single_view_activity);
        btnDelete = findViewById(R.id.btndelete);
        reference = FirebaseDatabase.getInstance().getReference().child("Car");
        String CarKey = getIntent().getStringExtra("CarKey");
        DataRef = FirebaseDatabase.getInstance().getReference().child("Car").child(CarKey);
        storageReference = FirebaseStorage.getInstance().getReference().child("CarImage").child(CarKey+".jpg");


        reference.child(CarKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String CarName = snapshot.child("CarName").getValue().toString();
                    String ImageUrl = snapshot.child("ImageUrl").getValue().toString();
                    Picasso.get().load(ImageUrl).into(imageView);
                    textView.setText(CarName);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            }
                        });

                    }
                });

            }
        });


    }
}