package com.example.backbenchers_mad4124_fp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.adapters.NotesAdapter;
import com.example.backbenchers_mad4124_fp.adapters.SubjectsAdapter;
import com.example.backbenchers_mad4124_fp.models.Notes;
import com.example.backbenchers_mad4124_fp.models.Subject;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity implements Serializable {

    private RecyclerView notesrecyclerView;
    private ArrayList<Notes> notes;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notesrecyclerView = findViewById(R.id.notesRecyclerView);

        populateNotes();

        notesAdapter = new NotesAdapter(notes);

        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        notesrecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        notesrecyclerView.setAdapter(notesAdapter);
    }

    private void populateNotes(){
        notes = new ArrayList<>();
        notes.add(new Notes("Note 1"));
    }
}