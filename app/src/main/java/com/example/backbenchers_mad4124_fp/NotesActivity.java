package com.example.backbenchers_mad4124_fp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.models.Notes;

import org.w3c.dom.Text;

import java.io.Serializable;

public class NotesActivity extends AppCompatActivity implements Serializable {
    TextView note ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        note = findViewById(R.id.txtNotes);

        //Notes notes = (Notes) getIntent().getSerializableExtra("Subject");
        //note.setText(notes.getNote());


    }
}