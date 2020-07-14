package com.example.backbenchers_mad4124_fp.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.adapters.AdapterGridBasic;
import com.example.backbenchers_mad4124_fp.database.NotesDB;
import com.example.backbenchers_mad4124_fp.models.NoteImage;
import com.example.backbenchers_mad4124_fp.widget.SpacingItemDecoration;

import java.util.ArrayList;

public class NoteAttachmentActivity extends AppCompatActivity {

    private View parent_view;
    private RecyclerView recyclerView;
    private AdapterGridBasic mAdapter;
    private Bundle values;
    private ArrayList<NoteImage> images;

    private NotesDB notesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_attachment);
        parent_view = findViewById(android.R.id.content);
        notesDB = new NotesDB(this);
        values = getIntent().getExtras();
        images = notesDB.getNoteImagesByNoteId(values.getInt("selectedNoteId"));
        initComponent();
    }

    private int dpToPx(int dp) {
        Resources r = this.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpacingItemDecoration(3, dpToPx(2), true));
        recyclerView.setHasFixedSize(true);

        //set data and list adapter
        mAdapter = new AdapterGridBasic(this, images);
        recyclerView.setAdapter(mAdapter);
    }

}