package com.example.projecttemp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    GridLayout mainGrid;

    Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        logOut = (Button) findViewById(R.id.logout_btn);
        setSingleEvent(mainGrid);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++){
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            final int finalI1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
//                    Toast.makeText(HomeActivity.this, "clicked at index "+ finalI, Toast.LENGTH_SHORT).show();
                    if (finalI1 == 0){
                        Intent intent = new Intent(HomeActivity.this, CoursesActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI1 == 1){
                        Intent intent = new Intent(HomeActivity.this, AttendanceActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI1 == 2){
                        Intent intent = new Intent(HomeActivity.this, ToDoActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI1 == 3){
                        Intent intent = new Intent(HomeActivity.this, HappeningsActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(HomeActivity.this, "please set an activity for this card item", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
