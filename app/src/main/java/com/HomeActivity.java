package com;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ViewActivity;
import com.example.savedata.MainActivity;
import com.example.savedata.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.FirebaseDatabaseKtxRegistrar;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {
    private EditText inputsearch;
    private RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
   FirebaseRecyclerOptions<Car> options;
   FirebaseRecyclerAdapter<Car,MyViewholder> adapter;
   DatabaseReference DataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        DataRef = FirebaseDatabase.getInstance().getReference().child("Car");
        inputsearch = findViewById(R.id.inputSearch);
        recyclerView =findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingbutton);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        loadData("");

        inputsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null){
                    loadData(s.toString());
                }else {
                    loadData("");
                }

            }
        });


    }

    private void loadData(String data) {
    Query query = DataRef.orderByChild("CarName").startAt(data.toLowerCase()).endAt(data.toLowerCase()+"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Car>().setQuery(query,Car.class).build();
        adapter = new FirebaseRecyclerAdapter<Car, MyViewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewholder myViewholder, int i, @NonNull Car car) {
                myViewholder.textView.setText(car.getCarName());
                Picasso.get().load(car.getImageUrl()).into(myViewholder.imageView);
                myViewholder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, ViewActivity.class);
                        intent.putExtra("CarKey",getRef(i).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
                return new MyViewholder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}