package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ShowStandingsActivity extends AppCompatActivity {
    ListView scoreboard;
    Button b_back, b_reset;
    ArrayList<String> leaderboard = new ArrayList<>();
    ArrayList<String> listNames = new ArrayList<>();
    HashMap<String, Integer> sortedBoard = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_standings);
        // скрыть панель действий по умолчанию
        Objects.requireNonNull(getSupportActionBar()).hide();

        // получить данные из общих настроек
        getData();

        // начальные переменные
        b_back = findViewById(R.id.b_back);
        b_reset = findViewById(R.id.b_reset);
        scoreboard = findViewById(R.id.lv_leaderboard);

        // Создание адаптера списка (подключает список к массиву строк)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_listview, android.R.id.text1, leaderboard);
        scoreboard.setAdapter(adapter);

        // получить значения таблицы лидеров
        Object[] sortedScores = sortedBoard.values().toArray();
        Object[] sortedNames = sortedBoard.keySet().toArray();

        // добавить значения в табло
        for(int i = sortedNames.length; i > 0; i--){
            String name = String.valueOf(sortedNames[i-1]);
            int score = Integer.parseInt(String.valueOf(sortedScores[i-1]));
            leaderboard.add(name + " . . . . . . . . . . . " + score);
        }

        // добавить прослушиватель кликов к кнопке «Назад» (закрывает активность после просмотра результатов)
        b_back.setOnClickListener(view -> finish());

        // добавить прослушиватель кликов к кнопке «Назад» (закрывает активность после просмотра результатов)
        b_reset.setOnClickListener(view -> {
            // сбросить все турнирные таблицы для названных введенных
            getSharedPreferences("prefs", 0).edit().clear().apply();

            // Вернуться к основной деятельности
            Intent in = new Intent(ShowStandingsActivity.this, MainActivity.class);
            // сообщает активности, какое имя выбирается
            startActivity(in);

            finish();
        });

        // добавить прослушиватель onclick в список
        scoreboard.setOnItemClickListener((parent, view, position, id) -> {
            // получить текст из списка, нажатого
            String selectedFromList = (String) (scoreboard.getItemAtPosition(position));
            // получить имя из текста
            selectedFromList = selectedFromList.substring(0, selectedFromList.indexOf('.')-1).toLowerCase();

            // получить имя из текста
            Intent in = new Intent(ShowStandingsActivity.this, InDepthStatsActivity.class);
            // сообщает активности, какое имя выбирается
            in.putExtra("playerName", selectedFromList);
            startActivity(in);
        });
    }

    // получает данные из общих настроек
    public void getData(){
        SharedPreferences sh = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        // получает данные из общих настроек
        Gson gson = new Gson();
        String json = sh.getString("listNames", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        listNames = gson.fromJson(json, type);

        // если listNames не равен нулю, добавить каждую запись в общих настройках в таблицу лидеров
        if(listNames != null){
            for(int i=0; i < listNames.size(); i++){
                String name = listNames.get(i);
                int wins = sh.getInt(name, 0);

                // если listNames не равен нулю, добавить каждую запись в общих настройках в таблицу лидеров
                sortedBoard.put(name.toUpperCase(Locale.ROOT), wins);
            }
        }

        // сортировать значения в hashmap
        List<Map.Entry<String, Integer> > list = new LinkedList<>(sortedBoard.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        sortedBoard = temp;

    }

}