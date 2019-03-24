package com.example.projecttemp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecttemp.Model.Students;
import com.example.projecttemp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText Rollnumber;
    private EditText Password;
    private Button Login;
    private Button SignUp;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Rollnumber = (EditText) findViewById(R.id.strollnum);
        Password = (EditText) findViewById(R.id.stpassword);
        Login = (Button) findViewById(R.id.btnlogin);
        SignUp = findViewById(R.id.btnSignUp);

        loadingBar = new ProgressDialog(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void LoginUser(){
        String pw = Password.getText().toString();
        String rNum = Rollnumber.getText().toString();

        if(TextUtils.isEmpty(pw)){
            Toast.makeText(this,"Please enter your Password",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(rNum)){
            Toast.makeText(this,"Please enter your Roll number",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(rNum, pw);
        }

    }

    private void AllowAccessToAccount(final String rNum, final String pw) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Students").child(rNum).exists()){

                    Students studentData = dataSnapshot.child("Students").child(rNum).getValue(Students.class);

                    if(studentData.getRollNumber().equals(rNum)) {

                        if(studentData.getPassword().equals(pw)) {
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineStudent = studentData;
                            startActivity(intent);

                        }
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Account with this " + rNum + "doesnot exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
