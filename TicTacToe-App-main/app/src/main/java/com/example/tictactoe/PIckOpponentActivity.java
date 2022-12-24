package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

public class PIckOpponentActivity extends AppCompatActivity {
    ListView listview;
    String[] opponentMenu;
    String player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_opponent);
        // скрыть панель действий по умолчанию
        Objects.requireNonNull(getSupportActionBar()).hide();

        // ресурсы доступны только из onCreate
        Resources res = getResources();
        opponentMenu = res.getStringArray(R.array.opponentMenu);

        // Создание адаптера списка (подключает список к массиву строк)
        listview = findViewById(R.id.lv_opponent);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_listview, android.R.id.text1, opponentMenu);
        listview.setAdapter(adapter);

        // добавить прослушиватель кликов в список
        listview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    // Компьютер выбран в качестве игрока 2
                    player = "Компьютер";
                    saveData();

                    // открытая игровая активность
                    Intent intent = new Intent(PIckOpponentActivity.this, PlayGameActivity.class);
                    startActivity(intent);
                    break;

                case 1:
                    // Другой игрок выбран в качестве игрока 2.
                    player = "Player 2";

                    // откройте активность выбора имени, чтобы можно было ввести имя игрока 2
                    Intent i = new Intent(PIckOpponentActivity.this, EnterNamesActivity.class);
                    i.putExtra("playerChoosing", "Player 2");
                    startActivity(i);
                    break;

                default:
                    // ошибка
                    break;

            }

        });
    }

    // Сохранить данные в общих настройках (вызывается, только если компьютер выбран вторым игроком)
    public void saveData(){
        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(player.equals("Компьютер")){
            // введите, что второй игрок был выбран компьютером
            editor.putString("secondPlayer", "Компьютер");
        }

        // зафиксировать общие настройки
        editor.apply();
    }
}