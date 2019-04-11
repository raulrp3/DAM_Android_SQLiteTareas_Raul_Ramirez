package com.example.alumno_fp.app_sqlite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class Swipe extends ItemTouchHelper.SimpleCallback {

    private ChoreAdapter mAdapter;
    private UtilsBD utilsBd;

    public Swipe(ChoreAdapter mAdapter,UtilsBD utilsBd){
        super(0,ItemTouchHelper.LEFT);
        this.mAdapter = mAdapter;
        this.utilsBd = utilsBd;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        Chore chore = mAdapter.getChore(position);

        mAdapter.removeChore(chore);
        utilsBd.deleteDb(Integer.valueOf(chore.getId()));

        mAdapter.notifyDataSetChanged();
    }
}
