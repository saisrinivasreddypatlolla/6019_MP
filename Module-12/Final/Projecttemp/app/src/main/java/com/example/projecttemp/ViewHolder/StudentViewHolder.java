package com.example.projecttemp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.projecttemp.R;

public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView courseName, courseStatus, courseCGPA;


    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);

        courseName = (TextView) itemView.findViewById(R.id.course_name);
        courseStatus = (TextView) itemView.findViewById(R.id.course_status);
        courseCGPA = (TextView) itemView.findViewById(R.id.course_cgpa);

    }

    @Override
    public void onClick(View v) {

    }
}
