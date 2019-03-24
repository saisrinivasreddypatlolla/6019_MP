package com.example.projecttemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projecttemp.Model.Happenings;
import com.example.projecttemp.ViewHolder.HappeningsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HappeningsActivity extends AppCompatActivity {

    private DatabaseReference dataRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happenings);
        FirebaseApp.initializeApp(this);

        dataRef = FirebaseDatabase.getInstance().getReference().child("Happenings");


        recyclerView = findViewById(R.id.recycler_View);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.d("Hi", "Hello");
    }

    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerOptions<Happenings> options = new FirebaseRecyclerOptions.Builder<Happenings>().setQuery(dataRef, Happenings.class).build();

        FirebaseRecyclerAdapter<Happenings, HappeningsViewHolder> adapter = new FirebaseRecyclerAdapter<Happenings, HappeningsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull HappeningsViewHolder holder, int position, @NonNull Happenings model) {
                Toast.makeText(HappeningsActivity.this, "this is on bindView", Toast.LENGTH_SHORT).show();
                holder.eventName.setText(model.getEvent());
                holder.date.setText(model.getDate());
                holder.description.setText(model.getDescription());

            }

            @NonNull
            @Override
            public HappeningsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                Toast.makeText(HappeningsActivity.this, "this is on create holder", Toast.LENGTH_SHORT).show();
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.happenings_layout, viewGroup, false);
                HappeningsViewHolder holder = new HappeningsViewHolder(view);

                return holder;
            }
        };


        recyclerView.setAdapter(adapter);

        adapter.startListening();

    }
}
