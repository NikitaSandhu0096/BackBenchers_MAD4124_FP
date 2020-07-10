package com.example.backbenchers_mad4124_fp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backbenchers_mad4124_fp.R;
import com.example.backbenchers_mad4124_fp.models.Notes;
import com.example.backbenchers_mad4124_fp.models.Subject;
import com.example.backbenchers_mad4124_fp.ui.activity.NewNoteActivity;
import com.example.backbenchers_mad4124_fp.ui.activity.NotesActivity;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private ArrayList <Notes> notesArrayList;
    private Integer selectedNoteId;
    public NotesAdapter(ArrayList<Notes> notesArrayList, Integer selectedNoteId){
        this.notesArrayList = notesArrayList;
        this.selectedNoteId = selectedNoteId;
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
        holder.txtNote.setText(note.getNOTE_TITLE());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Notes note = notesArrayList.get(position);
                Intent intent = new Intent(v.getContext(), NewNoteActivity.class);
                intent.putExtra("selectedNoteId", note.getNOTE_ID());
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

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNotes);
        }
    }
}
