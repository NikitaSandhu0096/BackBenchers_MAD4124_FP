package com.example.backbenchers_mad4124_fp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class NewNoteActivity extends AppCompatActivity {

    private TextInputEditText title;
    FloatingActionButton cameraFab;
    FloatingActionButton audioFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        title = findViewById(R.id.titleTextInputEditText);
        cameraFab = findViewById(R.id.camerafab);
        audioFab = findViewById(R.id.audiofab);

        cameraFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}