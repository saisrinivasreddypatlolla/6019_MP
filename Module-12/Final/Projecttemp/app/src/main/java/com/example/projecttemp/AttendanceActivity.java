package com.example.projecttemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projecttemp.Model.CourseDetails;
import com.example.projecttemp.Prevalent.Prevalent;
import com.example.projecttemp.ViewHolder.AttendanceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttendanceActivity extends AppCompatActivity {

    private DatabaseReference DataRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_attendance);

        DataRef = FirebaseDatabase.getInstance().getReference().child("StudentDetails").child(Prevalent.currentOnlineStudent.getRollNumber()).child("Courses");


        recyclerView = findViewById(R.id.recycler_attendance);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {

        super.onStart();


        FirebaseRecyclerOptions<CourseDetails> options = new FirebaseRecyclerOptions.Builder<CourseDetails>().setQuery(DataRef, CourseDetails.class).build();
        FirebaseRecyclerAdapter<CourseDetails, AttendanceViewHolder> adapter = new FirebaseRecyclerAdapter<CourseDetails, AttendanceViewHolder>(options) {



            @Override
            protected void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position, @NonNull CourseDetails model) {


                holder.courseNam.setText(model.getCourseName());
                holder.courseStat.setText(model.getStatus());
                Toast.makeText(AttendanceActivity.this, "this in attendanceActivity" + model.getAttendence(), Toast.LENGTH_SHORT).show();
                holder.courseAttendance.setText(model.getAttendence());

            }

            @NonNull
            @Override
            public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_course_attendance, viewGroup, false);
                AttendanceViewHolder holder = new AttendanceViewHolder(view);

                return holder;

            }


        };


        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }
}

