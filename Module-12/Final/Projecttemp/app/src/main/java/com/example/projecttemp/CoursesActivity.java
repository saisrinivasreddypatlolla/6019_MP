package com.example.projecttemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecttemp.Model.CourseDetails;
import com.example.projecttemp.Prevalent.Prevalent;
import com.example.projecttemp.ViewHolder.StudentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CoursesActivity extends AppCompatActivity {

    private DatabaseReference DataRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_courses);

        DataRef = FirebaseDatabase.getInstance().getReference().child("StudentDetails").child(Prevalent.currentOnlineStudent.getRollNumber()).child("Courses");


        recyclerView = findViewById(R.id.recycler_menu);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {

        super.onStart();


        FirebaseRecyclerOptions<CourseDetails> options = new FirebaseRecyclerOptions.Builder<CourseDetails>().setQuery(DataRef, CourseDetails.class).build();
        FirebaseRecyclerAdapter<CourseDetails, StudentViewHolder> adapter = new FirebaseRecyclerAdapter<CourseDetails, StudentViewHolder>(options) {



            @Override
            protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull CourseDetails model) {


                holder.courseName.setText(model.getCourseName());
                holder.courseStatus.setText(model.getStatus());
                holder.courseCGPA.setText(model.getCourseGrade());

            }

            @NonNull
            @Override
            public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singlecourse, viewGroup, false);
                StudentViewHolder holder = new StudentViewHolder(view);

                return holder;

            }


        };


        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }
}
