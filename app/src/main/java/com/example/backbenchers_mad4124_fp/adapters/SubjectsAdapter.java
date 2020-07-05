package com.example.backbenchers_mad4124_fp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backbenchers_mad4124_fp.MainActivity;
import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.models.Subject;
import com.example.backbenchers_mad4124_fp.NotesActivity;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {
    private ArrayList<Subject> subjectArrayList;

    public SubjectsAdapter(ArrayList<Subject> subjectArrayList){
        this.subjectArrayList = subjectArrayList;
    }
    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        SubjectViewHolder subjectViewHolder = new SubjectViewHolder(view);
        return subjectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, final int position) {
        final Subject subject = this.subjectArrayList.get(position);
        holder.txtSubject.setText(subject.getSubjectName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject subject1 = subjectArrayList.get(position);
               Intent sIntent = new Intent(v.getContext(), NotesActivity.class);
                sIntent.putExtra("Subject",subject1.getSubjectName());
                v.getContext().startActivity(sIntent);
            //Toast.makeText(v.getContext(),"S : ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.subjectArrayList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubject;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubject = itemView.findViewById(R.id.txtSubjectName);
        }
    }
}
