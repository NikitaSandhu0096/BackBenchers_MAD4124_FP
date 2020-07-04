package com.example.backbenchers_mad4124_fp.ui.main.fargment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.adapters.SubjectsAdapter;
import com.example.backbenchers_mad4124_fp.models.Subject;

import java.util.ArrayList;

public class SubjectsFragment<onViewCreate> extends Fragment {
    private TextView txtTitle;
    private RecyclerView subjectrecyclerView;
    private ArrayList<Subject> subjects;
    private SubjectsAdapter subjectsAdapter;
    public SubjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_subjects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);
        subjectrecyclerView = view.findViewById(R.id.subjectsrecyclerView);

//        populateSubjects();
//
//        subjectsAdapter = new SubjectsAdapter(subjects);
//
//        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.VERTICAL);
//        subjectrecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
//        subjectrecyclerView.setAdapter(subjectsAdapter);
    }

//    private void populateSubjects(){

//    }
}
