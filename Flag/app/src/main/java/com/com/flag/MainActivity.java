package com.com.flag;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button PlayGame;
    EditText Name;
    AudioAttributes attrs = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build();
    SoundPool sp = new SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(attrs)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FirebaseDatabase database =  FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        int soundIds[] = new int[2];
        soundIds[0] = sp.load(this, R.raw.am_thanh_dung, 1);
        soundIds[1] = sp.load(this, R.raw.am_thanh_sai, 1);
        PlayGame = (Button) findViewById(R.id.ButtonMainPlay);
        Name = (EditText) findViewById(R.id.EditMainName);
//        myRef.setValue(Name);
        PlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent
                        (MainActivity.this, activity_entry.class);
                String PlayerName = Name.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("PlayerName", PlayerName);
                intent.putExtra("MyPackage", bundle);
                sp.play(soundIds[0],1,1,1,0,1);
                startActivity(intent);

            }
        });

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}