package com.example.backbenchers_mad4124_fp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.models.Notes;
import com.example.backbenchers_mad4124_fp.ui.activity.NewNoteActivity;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private ArrayList <Notes> notesArrayList;
    private Integer selectedSubjectId;
    public NotesAdapter(ArrayList<Notes> notesArrayList, Integer selectedSubjectId){
        this.notesArrayList = notesArrayList;
        this.selectedSubjectId = selectedSubjectId;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        NotesViewHolder notesViewHolder = new NotesViewHolder(view);
        return notesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {
        Notes note = this.notesArrayList.get(position);

        holder.txtNote.setText(note.getNoteTitle());
        holder.txtTimestamp.setText(note.getTimestamp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Notes note = notesArrayList.get(position);
                Intent intent = new Intent(v.getContext(), NewNoteActivity.class);
                intent.putExtra("selectedNoteId", note.getNoteId());
                intent.putExtra("selectedSubjectId",selectedSubjectId);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.notesArrayList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView txtNote;
        TextView txtTimestamp;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNotes);
            txtTimestamp = itemView.findViewById(R.id.txtTimestamp);
        }
    }
}
