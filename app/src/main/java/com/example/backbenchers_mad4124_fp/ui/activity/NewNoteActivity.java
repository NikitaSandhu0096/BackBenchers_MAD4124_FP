package com.example.backbenchers_mad4124_fp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.database.NotesDB;
import com.example.backbenchers_mad4124_fp.models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.spec.EncodedKeySpec;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    private TextInputEditText title;
    private TextInputEditText data;
    private FloatingActionButton cameraFab;
    private FloatingActionButton audioFab;
    private NotesDB noteDB;

    private Integer selectedSubjectId;
    private Integer selectedNoteId;

    private final Integer IMAGE_CAPTURE_REQUEST_CODE = 101;

    private ArrayList<String> selectedImages= new ArrayList<>();

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

        if (selectedNoteId >= 0){
            Notes temp = noteDB.getNoteByNoteId(selectedNoteId);
            title.setText(temp.getNoteTitle());
            data.setText(temp.getNoteData());
        }

        cameraFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String[] options = {"Image Library","Camera"};

            AlertDialog.Builder builder = new AlertDialog.Builder(NewNoteActivity.this);
            builder.setTitle("Image options");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0){
                        Toast.makeText(NewNoteActivity.this,"Image Library", Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, IMAGE_CAPTURE_REQUEST_CODE);
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CAPTURE_REQUEST_CODE){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("images", Context.MODE_PRIVATE);
            File file = new File(directory, System.currentTimeMillis() + ".jpeg");
            if (!file.exists()) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    selectedImages.add(file.toString());
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.noteSave:
                if (!title.getText().toString().isEmpty() || !data.getText().toString().isEmpty() || !selectedImages.isEmpty()){
                    if (selectedNoteId == -1){
                        //Create New Note
                        Notes newNote = new Notes(selectedSubjectId, title.getText().toString(), data.getText().toString());
                        long noteId = noteDB.addNote(newNote);
                        if (!selectedImages.isEmpty()){
                            for (String path: selectedImages){
                                noteDB.addNoteImages(noteId, path);
                            }
                        }
                    }else {
                        //Update the note
                        Notes note = noteDB.getNoteByNoteId(selectedNoteId);
                        note.setNoteTitle(title.getText().toString());
                        note.setNoteData(data.getText().toString());
                        noteDB.updateNote(note);

                        if(!selectedImages.isEmpty()){
                            for (String path: selectedImages){
                                noteDB.addNoteImages(note.getNoteId(), path);
                            }
                        }
                    }
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}