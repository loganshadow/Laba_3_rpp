package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ActiShovDV extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);

        RecyclerView rv_listStudents = findViewById(R.id.rv_listStudents);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_listStudents.setLayoutManager(layoutManager);
        rv_listStudents.setHasFixedSize(true); //Тк знаем размер списка

        AdapterList adapter = new AdapterList(this);
        rv_listStudents.setAdapter(adapter);
    }
}