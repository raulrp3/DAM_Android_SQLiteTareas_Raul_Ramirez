package com.example.alumno_fp.app_sqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ChoreAdapter extends RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder> {


    public static class ChoreViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView textId,textTitle,textDescription,textDate;

        ChoreViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            textId = itemView.findViewById(R.id.text_id);
            textTitle = itemView.findViewById(R.id.text_title);
            textDescription = itemView.findViewById(R.id.text_description);
            textDate = itemView.findViewById(R.id.text_date);
        }
    }

    List<Chore> listChore;
    Context context;

    ChoreAdapter(List<Chore> listChore,Context context){
        this.listChore = listChore;
        this.context = context;
    }

    @NonNull
    @Override
    public ChoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        final ChoreViewHolder cvh = new ChoreViewHolder(v);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoreViewHolder choreViewHolder, int i) {
        choreViewHolder.textId.setText(listChore.get(i).getId());
        choreViewHolder.textTitle.setText(listChore.get(i).getTitle());
        choreViewHolder.textDescription.setText(listChore.get(i).getDescription());
        choreViewHolder.textDate.setText(listChore.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return listChore.size();
    }

    public void removeChore(Chore chore){ listChore.remove(chore);}

    public Chore getChore(int index){return listChore.get(index);}
}
