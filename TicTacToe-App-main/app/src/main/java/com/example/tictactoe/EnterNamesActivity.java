package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class EnterNamesActivity extends AppCompatActivity {
    Button b_done;
    EditText et_playerName;
    String playerChoosing;
    TextView title;
    ArrayList<String> listNames = new ArrayList<>();
    boolean firstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);
        // скрыть панель действий по умолчанию
        Objects.requireNonNull(getSupportActionBar()).hide();

        b_done = findViewById(R.id.b_done);
        et_playerName = findViewById(R.id.et_playerName);
        title = findViewById(R.id.t_enterNames);

        // получить данные из намерения
        Bundle bundle = getIntent().getExtras();
        // выяснить, для какого игрока выбирается имя
        playerChoosing = bundle.getString("playerChoosing");
        // выяснить, если это первый запуск приложения
        firstRun = bundle.getBoolean("firstRun");

        // Измените название, если выбираете имя для игрока 2
        if (playerChoosing.equals("Player 2")){
            title.setText("Введите имя 2-го игрока");
        }

        // выбрать, что произойдет, когда кнопка «Готово» нажата
        b_done.setOnClickListener(view -> {
            saveData();

            if(playerChoosing.equals("Player 2")){
                // открытая игровая активность
                Intent intent = new Intent(EnterNamesActivity.this, PlayGameActivity.class);
                startActivity(intent);
            } else if(firstRun){
                // запустить фрагмент выбора противника
                Intent intent1 = new Intent(EnterNamesActivity.this, PIckOpponentActivity.class);
                startActivity(intent1);
            }
        });
    }

    // Сохранить текущие данные
    public void saveData(){
        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // получить имя из editText
        String enteredName = et_playerName.getText().toString();

        // доступ к списку имен
        Gson g = new Gson();
        String j = sp.getString("listNames", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        listNames = g.fromJson(j, type);

        // if no listNames has been created, create one
        if(listNames == null){
            listNames = new ArrayList<>();
            listNames.add("computer");

            // create blank log value for computer
            editor.putInt("computer", 0);
            editor.putInt("computer_totalGames", 0);
            editor.putString("computer_recentOpponent", "name");
            editor.putString("computer_time", "N/A");
            editor.putInt("computer_totalMoves", 0);
        }

        // проверьте имя в текущем списке имен, если нет, создайте пустое значение журнала для этого имени и добавьте их в список
        int storedName = sp.getInt(enteredName.toLowerCase(), 10000000);
        if(storedName == 10000000){
            String lowerCaseName = enteredName.toLowerCase();

            // создать пустые значения журнала
            editor.putInt(lowerCaseName, 0);
            editor.putInt(lowerCaseName + "_totalGames", 0);
            editor.putString(lowerCaseName + "_recentOpponent", "name");
            editor.putString(lowerCaseName + "_time", "N/A");
            editor.putInt(lowerCaseName + "_totalMoves", 0);

            // добавить имя в список имен игроков
            listNames.add(enteredName.toLowerCase());
        }

        // сохранить имена текущих игроков для PlayGameActivity
        if(playerChoosing.equals("Player 1")){
            editor.putString("currentPlayerName", enteredName);
        } else if (playerChoosing.equals("Player 2")){
            editor.putString("secondPlayer", enteredName);
        }

        // преобразовать список в json и сохранить
        Gson gson = new Gson();
        String json = gson.toJson(listNames);
        editor.putString("listNames", json);

        // зафиксировать общие настройки
        editor.apply();
    }

    // когда поворачиваем телефон, эта функция вызывается для сохранения любых данных, которые вы хотите сохранить
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // сохранение индекса пакета в аргументе метода
        outState.putString("currentText", et_playerName.getText().toString());
        super.onSaveInstanceState(outState);
    }

    // когда телефон переворачивается, эта функция вызывается для восстановления любых сохраненных вами данных.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // get values from saved state
        et_playerName.setText(savedInstanceState.getString("currentText"));
        super.onRestoreInstanceState(savedInstanceState);
    }

}