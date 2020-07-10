package com.example.backbenchers_mad4124_fp.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.database.NotesDB;
import com.example.backbenchers_mad4124_fp.models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class NewNoteActivity extends AppCompatActivity {

    private TextInputEditText title;
    private TextInputEditText data;
    private FloatingActionButton cameraFab;
    private FloatingActionButton audioFab;
    private NotesDB noteDB;

    private Integer selectedSubjectId;
    private Integer selectedNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        title = findViewById(R.id.titleTextInputEditText);
        data = findViewById(R.id.noteTextInputEditText);

        cameraFab = findViewById(R.id.camerafab);
        audioFab = findViewById(R.id.audiofab);
        selectedSubjectId = getIntent().getIntExtra("selectedSubjectId", -1);
        selectedNoteId = getIntent().getIntExtra("selectedNoteId", -1);
        noteDB = new NotesDB(this);
        this.setTitle("New Note");

        if (selectedNoteId > 0){
            Notes temp = noteDB.getNoteByNoteId(selectedNoteId);
            title.setText(temp.getNOTE_TITLE());
            data.setText(temp.getNOTE_DATA());
        }

//        cameraFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String[] options = {"Image Library","Camera"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(NewNoteActivity.this);
//                builder.setTitle("Image options");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (which == 0){
//                            Toast.makeText(NewNoteActivity.this,"Image Library", Toast.LENGTH_LONG).show();
//                        }else {
//                            Toast.makeText(NewNoteActivity.this,"Camera", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (selectedNoteId == -1){
            //Create New Note
            Notes newNote = new Notes(selectedSubjectId, title.getText().toString(), data.getText().toString());
            noteDB.addNote(newNote);
        }else{
            //Update the note
        }
    }
}