package com.example.backbenchers_mad4124_fp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.adapters.NotesAdapter;
import com.example.backbenchers_mad4124_fp.adapters.SubjectsAdapter;
import com.example.backbenchers_mad4124_fp.models.Notes;
import com.example.backbenchers_mad4124_fp.models.Subject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity implements Serializable {

    private RecyclerView notesrecyclerView;
    private ArrayList<Notes> notes;
    private NotesAdapter notesAdapter;
    private FloatingActionButton notesFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notesFab = findViewById(R.id.notesFab);

        notesrecyclerView = findViewById(R.id.notesRecyclerView);

        populateNotes();

        notesAdapter = new NotesAdapter(notes);

        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        notesrecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        notesrecyclerView.setAdapter(notesAdapter);

        notesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateNotes(){
        notes = new ArrayList<>();
        notes.add(new Notes("Note 1"));
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
}