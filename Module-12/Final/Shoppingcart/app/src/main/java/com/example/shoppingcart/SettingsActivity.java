package com.example.shoppingcart;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SettingsActivity extends AppCompatActivity {


    private ImageView profileImageView;
    private EditText fullNameET, userPhoneET, addressET;
    private TextView profileChangeTxtBtn, closeTextBtn, saveTextBtn;

    private Uri imageUri;
    private String myUrl = "";
    private StorageReference storageProfileRef;
    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        profileImageView = (ImageView) findViewById(R.id.settings_profile_image);
        fullNameET = (EditText) findViewById(R.id.settings_full_name);
        userPhoneET = (EditText) findViewById(R.id.settings_phone_number);
        addressET = (EditText) findViewById(R.id.settings_address);

        profileChangeTxtBtn = (TextView) findViewById(R.id.profile_image_change_btn);
        closeTextBtn = (TextView) findViewById(R.id.close_settings);
        saveTextBtn = (TextView) findViewById(R.id.update_account_settings);

        userInfoDisplay(profileImageView, fullNameET, userPhoneET, addressET);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveTextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checker.equals("clicked")) {
                    userInfoSaved();
                } else {
                    updateOnlyUserInfo();
                }
            }

        });

        profileChangeTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker = "clicked";
            }
        });

    }

    private void updateOnlyUserInfo() {

    }
    private void userInfoSaved() {

    }

    private void userInfoDisplay(final ImageView profileImageView, final EditText fullNameET, final EditText userPhoneET, final EditText addressET) {
        DatabaseReference  userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.onlineUser.getPhone());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    if(dataSnapshot.child("image").exists()) {
                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String phone= dataSnapshot.child("phone").getValue().toString();
                        String address = dataSnapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);
                        fullNameET.setText(name);
                        userPhoneET.setText(phone);
                        addressET.setText(address);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
