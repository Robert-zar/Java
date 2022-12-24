package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class InDepthStatsActivity extends AppCompatActivity {
    ListView stats;
    Button b_back2;
    TextView title;
    ArrayList<String> statsAL = new ArrayList<>();
    String player, lastOpponent, percentageFormatted, time, averageMovesFormatted;
    int wins, totalGames, totalMoves, MAX_DIGITS=26;
    double percentage, averageMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_depth_stats);
        // скрыть панель действий по умолчанию
        Objects.requireNonNull(getSupportActionBar()).hide();

        stats = findViewById(R.id.lv_stats);
        b_back2 = findViewById(R.id.b_back2);
        title = findViewById(R.id.title_inDepth);

        // получить имя игрока
        Bundle bundle = getIntent().getExtras();
        player = bundle.getString("playerName");

        getData();

        // изменить название, чтобы включить имя игрока
        String out = "Статистика для \n" + player.toUpperCase();
        title.setText(out);

        // add stats to arraylist
        statsAL.add(getFormattedString("Сыграно игр", String.valueOf(totalGames)));
        statsAL.add(getFormattedString("Выиграно игр", String.valueOf(wins)));
        statsAL.add(getFormattedString("Шанс на победу", percentageFormatted + "%"));
        statsAL.add(getFormattedString("Всего ходов", String.valueOf(totalMoves)));
        statsAL.add(getFormattedString("Среднее количество ходов", averageMovesFormatted));
        statsAL.add("Последняя игра " + lastOpponent.toUpperCase() + " в\n" + time.substring(0,10) + " на " + time.substring(11));

        // Создание адаптера списка (подключает список к массиву строк)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_listview, android.R.id.text1, statsAL);
        stats.setAdapter(adapter);

        // добавить прослушиватель кликов к кнопке «Назад» (закрывает активность после просмотра результатов)
        b_back2.setOnClickListener(view -> finish());

    }

    // Получить данные из общего файла настроек
    public void getData(){
        SharedPreferences sh = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        // получить данные из общих настроек
        wins = sh.getInt(player, 0);
        totalGames = sh.getInt(player + "_totalGames", 1);
        time = sh.getString(player + "_time", "N/A");
        lastOpponent = sh.getString(player + "_recentOpponent", "none");
        totalMoves = sh.getInt(player + "_totalMoves", 0);

        // выполнить некоторые расчеты с данными для других статистических данных
        percentage = (((double) wins)/totalGames) * 100;
        averageMoves = (((double)totalMoves)/totalGames);
        DecimalFormat fmt = new DecimalFormat("###.#");
        percentageFormatted = fmt.format(percentage);
        averageMovesFormatted = fmt.format(averageMoves);

    }

    // форматирует строку, которая будет помещена в список (меняет количество ., которые идут в строке, в зависимости от количества цифр в статистике)
    public String getFormattedString(String title, String variable){
        int digitsFirst = title.length();
        int digitsAfter = variable.length();

        String output = "";

        int digitsMiddle = MAX_DIGITS - digitsFirst - digitsAfter;

        output += title;
        for (int i = 0; i < digitsMiddle; i+=2){
            output += " .";
        }
        output += (" " + variable);

        return output;
    }
}