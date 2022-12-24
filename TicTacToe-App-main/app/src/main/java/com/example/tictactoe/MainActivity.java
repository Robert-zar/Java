package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    String[] homeMenu;
    LinearLayout ll_logo;
    TextView tv_home;
    int test;

    // Функция 1: Режим двух игроков
    // Функция 2: Приветствуем пользователя, имя игрока 2, когда наступает его очередь
    // Функция 3: Страница подробной статистики (когда пользователь щелкает свое имя в таблице лидеров, показывает более подробную информацию) |
    // Функция 4: (если функция 1 не считается) Функция для сброса статистики

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // скрыть панель действий по умолчанию
        Objects.requireNonNull(getSupportActionBar()).hide();

        getData();

        // получить начальные
        Resources res = getResources();
        homeMenu = res.getStringArray(R.array.homeMenu);
        ll_logo = findViewById(R.id.ll_logo);
        tv_home = findViewById(R.id.tv_home);

        // Создание адаптера списка
        listview = findViewById(R.id.lv_home);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_listview, android.R.id.text1, homeMenu);
        listview.setAdapter(adapter);

        // Определите, какой вариант выбрал пользователь, и откройте соответствующее действие/фрагмент
        listview.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    // запустить активность ввода имени
                    Intent intent = new Intent(MainActivity.this, EnterNamesActivity.class);
                    // сообщает активности, какое имя выбирается
                    intent.putExtra("playerChoosing", "Player 1");
                    startActivity(intent);
                    break;

                case 1:
                    // если общие настройки не существуют, откройте действие ввода имени, в противном случае откройте фрагмент выбора противника.
                    if(test == 999999999){
                        // запустить активность ввода имени
                        Intent intent1 = new Intent(MainActivity.this, EnterNamesActivity.class);
                        // сообщает активности, какое имя выбирается
                        intent1.putExtra("playerChoosing", "Player 1");
                        intent1.putExtra("firstRun", true);
                        startActivity(intent1);
                    } else {
                        // запустить/выбрать активность противника
                        Intent intent2 = new Intent(MainActivity.this, PIckOpponentActivity.class);
                        startActivity(intent2);
                    }
                    break;

                case 2:
                    // запуск турнирной таблицы
                    Intent intent3 = new Intent(MainActivity.this, ShowStandingsActivity.class);
                    startActivity(intent3);
                    break;

                default:
                    // ошибка
                    break;

            }

        });
    }

    // проверьте журнал компьютера (в основном, чтобы увидеть, существуют ли общие настройки)
    public void getData(){
        SharedPreferences sh = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        test = sh.getInt("computer", 999999999);
    }

}