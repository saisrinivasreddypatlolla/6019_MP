package com.example.projecttemp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.projecttemp.R;

public class AttendanceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView courseNam, courseStat, courseAttendance;
    public AttendanceViewHolder(@NonNull View itemView) {
        super(itemView);

        courseNam = (TextView) itemView.findViewById(R.id.attendance_course_name);
        courseStat = (TextView) itemView.findViewById(R.id.attendance_course_status);
        courseAttendance = (TextView) itemView.findViewById(R.id.attendance_course);

    }

    @Override
    public void onClick(View v) {

    }
}
