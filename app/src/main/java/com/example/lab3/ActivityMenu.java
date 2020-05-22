package com.example.lab3;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class ActivityMenu extends AppCompatActivity {

    private DateFormat format = new SimpleDateFormat("HH:mm:ss "); //"yyyy.MM.dd 'at'
    private DBHelpr dbHelper;
    private SQLiteDatabase database;
    private ArrayList<String> FIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menu);

        dbHelper = new DBHelpr(this);
        database = dbHelper.getWritableDatabase();
        //database.execSQL("DELETE FROM students");
        insertStartInfo();

        dbHelper.close();

        Button btn_openDB = findViewById(R.id.btn_openDB);
        Button btn_addItemDB = findViewById(R.id.btn_addItemDB);
        Button btn_replace = findViewById(R.id.btn_replace);


        btn_openDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActiShovDV.class);
                startActivity(intent);
            }
        });

        btn_addItemDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = dbHelper.getWritableDatabase();

                Random random = new Random();
                int number;
                number = random.nextInt(FIO.size());
                Calendar thisDate = Calendar.getInstance();
                String data = format.format(thisDate.getTime());

                String[] arrFIO = FIO.get(number).split(" ");
                database.execSQL("INSERT INTO students(first_name, middle_name, last_name, time) " +
                        "VALUES (\'" + arrFIO[1] + "\', \'" + arrFIO[2] + "\', \'" + arrFIO[0] + "\', \'" + data + "\');");
                Toast toast = Toast.makeText(ActivityMenu.this, "Запись добавлена!", Toast.LENGTH_LONG);
                toast.show();
                FIO.remove(number);

                dbHelper.close();
            }
        });

        btn_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = dbHelper.getWritableDatabase();
                database.execSQL("UPDATE students SET first_name = 'Иван', middle_name = 'Иванович', last_name = 'Иванов' WHERE id = (SELECT max(id) FROM students)");
                Toast toast = Toast.makeText(ActivityMenu.this, "Запись успешно изменена!", Toast.LENGTH_LONG);
                toast.show();

                dbHelper.close();
            }
        });
    }

    private void insertStartInfo() {

        FIO = new ArrayList<>();
        FIO.add("Кириченко Жанна Тимофеевна");
        FIO.add("Яшвили Александра Ипполитовна");
        FIO.add("Усилова Таисия Петровна");
        FIO.add("Юшкова Всеслава Петровна");
        FIO.add("Клименкова Эвелина Всеволодовна");
        FIO.add("Пушкин Александр Сергеевич");
        FIO.add("Багрова Светлана Данилевна");
        FIO.add("Ануфриева Владислава Игнатиевна");
        FIO.add("Соловаьева Ольга Яновна");
        FIO.add("Желвакова Бронислава Михеевна");
        FIO.add("Гарифуллин Алексей Аполлинариевич");
        FIO.add("Кая Рюрик Ипатович");
        FIO.add("Саянов Данила Филиппович");
        FIO.add("Балабанов Капитон Адамович");
        FIO.add("Кобзева Розалия Степановна");
        FIO.add("Касимов Соломон Дмитриевич");
        FIO.add("Братцев Демьян Арсениевич");
        FIO.add("Кочерёжкина Наталья Тимуровна");
        FIO.add("Диденкова Мирослава Потаповна");
        FIO.add("Садовничий Каролина Владленовна");
        FIO.add("Кадцын Феликс Игоревич");
        FIO.add("Корявова Агафья Ильевна");
        FIO.add("Расторгуева Изольда Игнатиевна");
        FIO.add("Полешко Порфирий Яковович");
        FIO.add("Макаркина Ева Ростиславовна");
        FIO.add("Вышегородских Потап Андроникович");



        Random random = new Random();
        int number;
        String[] arrFIO;

        for (int i = 0; i < 5; i++) {

            number = random.nextInt(FIO.size());

            Calendar thisDate = Calendar.getInstance();
            String data = format.format(thisDate.getTime());

            arrFIO = FIO.get(number).split(" ");

            database.execSQL("INSERT INTO students(first_name, middle_name, last_name, time) " +
                    "VALUES (\'" + arrFIO[1] + "\', \'" + arrFIO[2] + "\', \'" + arrFIO[0] + "\', \'" + data + "\');");


            FIO.remove(number);
        }

    }
}