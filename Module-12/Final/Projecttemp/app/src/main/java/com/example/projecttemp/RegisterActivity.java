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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button RegisterBtn;
    private EditText name, rollNumber, password;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterBtn = (Button) findViewById(R.id.btn_register);
        name = (EditText) findViewById(R.id.et_name);
        rollNumber = (EditText) findViewById(R.id.et_rollNum);
        password = (EditText) findViewById(R.id.et_pw);

        loadingBar = new ProgressDialog(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

    }

    void CreateAccount() {
        String nam = name.getText().toString();
        String pw = password.getText().toString();
        String rNum = rollNumber.getText().toString();
        if(TextUtils.isEmpty(nam)){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pw)){
            Toast.makeText(this,"Please enter your Password",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(rNum)){
            Toast.makeText(this,"Please enter your Roll number",Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateRollNumber(nam,rNum,pw);
        }
    }

    private void validateRollNumber(final String nam, final String rNum, final String pw) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("Students").child(rNum).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("RollNumber", rNum);
                    userdataMap.put("Password", pw);
                    userdataMap.put("name",nam);

                    RootRef.child("Students").child(rNum).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(RegisterActivity.this, "Network error...Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(RegisterActivity.this, "This " + rNum + "already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
