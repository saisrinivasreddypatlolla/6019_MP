package com.example.castvote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button b;
    EditText num,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button);
        num = (EditText) findViewById(R.id.name);
        name = (EditText) findViewById(R.id.number);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nm = name .getText().toString();
                final String no = num .getText().toString();
                Toast.makeText(MainActivity.this, "-->Welcome Number  " + no + "  Name " + nm , Toast.LENGTH_SHORT).show();
//                System.out.println("number " + no + "Name" + nm + "Welcome");
            }
        });
    }
}
