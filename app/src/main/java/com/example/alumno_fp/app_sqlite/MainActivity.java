package com.example.alumno_fp.app_sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    FloatingActionButton fbAdd;
    List<Chore> listChore;
    ChoreAdapter mAdapter;
    private final int CODE_SAVE = 1;
    private SQLiteDatabase db;
    private UtilsBD utilsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initData();
        initAdapter();

        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivityForResult(intent, CODE_SAVE);
            }
        });
    }

    private void initUI() {
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        fbAdd = findViewById(R.id.fb_add);

        listChore = new ArrayList<>();
        SQLiteHelper helper = new SQLiteHelper(this, "DBChores", null, 1);
        db = helper.getWritableDatabase();

        utilsDb = new UtilsBD(db);

    }

    private void initData() {
        listChore = utilsDb.readDb(listChore);
    }

    private void initAdapter() {
        mAdapter = new ChoreAdapter(listChore, MainActivity.this);
        rv.setAdapter(mAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new Swipe(mAdapter,utilsDb));
        itemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_SAVE) {
            String title = data.getStringExtra("TITLE");
            String description = data.getStringExtra("DESCRIPTION");
            String date = data.getStringExtra("DATE");

            utilsDb.writeDb(title, description, date);

            listChore.add(new Chore(String.valueOf(utilsDb.getLastID()), title, description, date));
            mAdapter.notifyDataSetChanged();
        }
    }
}
