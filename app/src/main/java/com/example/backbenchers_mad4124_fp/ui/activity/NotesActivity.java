package com.example.backbenchers_mad4124_fp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.adapters.NotesAdapter;
import com.example.backbenchers_mad4124_fp.database.NotesDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class NotesActivity extends AppCompatActivity implements Serializable, FloatingActionButton.OnClickListener {

    private RecyclerView notesrecyclerView;
    private NotesDB notesDB;
    private Integer selectedSubjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        FloatingActionButton notesFab = findViewById(R.id.notesFab);
        notesrecyclerView = findViewById(R.id.notesRecyclerView);
        notesDB = new NotesDB(this);
        selectedSubjectId = getIntent().getIntExtra("selectedSubjectId",1);

        populateNotes();

        notesFab.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        populateNotes();
    }

    private void populateNotes(){
        NotesAdapter notesAdapter = new NotesAdapter(notesDB.getNoteBySubjectId(selectedSubjectId), selectedSubjectId);

        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        notesrecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        notesrecyclerView.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_title:
                Toast.makeText(getApplicationContext(),"Title sort",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_date:
                Toast.makeText(getApplicationContext(),"Date sort",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(NotesActivity.this, NewNoteActivity.class);
        intent.putExtra("selectedSubjectId", selectedSubjectId);
        startActivity(intent);
    }
}