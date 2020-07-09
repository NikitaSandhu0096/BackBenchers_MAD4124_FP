package com.example.backbenchers_mad4124_fp.ui.main.fargment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.backbenchers_mad4124_fp.MainActivity;
import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.adapters.SubjectsAdapter;
import com.example.backbenchers_mad4124_fp.database.NotesDB;
import com.example.backbenchers_mad4124_fp.models.Subject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SubjectsFragment<onViewCreate> extends Fragment implements FloatingActionButton.OnClickListener {
    private TextView txtTitle;
    private ArrayList<Subject> subjects;
    private SubjectsAdapter subjectsAdapter;
    private RecyclerView subjectrecyclerView;
    private NotesDB notesDB;

    public SubjectsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subjects, container, false);
    }

    private void refreshData(){
        subjectsAdapter = new SubjectsAdapter( notesDB.getAllSubjects());

        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        subjectrecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        subjectrecyclerView.setAdapter(subjectsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        subjectrecyclerView = view.findViewById(R.id.subjectsrecyclerView);
        notesDB = new NotesDB(this.getContext());
        refreshData();

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("New Subject");

        final EditText editText = new EditText(getContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Subject newSubject = new Subject(editText.getText().toString());
                NotesDB notesDB = new NotesDB(getActivity());
                if (notesDB.addSubject(newSubject)){
                    refreshData();
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
