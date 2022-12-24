package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity implements View.OnClickListener {
    Button b_quit;
    TextView a1, a2, a3, b1, b2, b3, c1, c2, c3, t_title;
    char[][] currentGame;
    TextView[][] currentGameTVs;
    int p1Wins, p2Wins, compWins, p1TotalGames, p2TotalGames, compTotalGames, p1TotalMoves, p2TotalMoves, compTotalMoves;
    boolean gameWon = false, player1Turn = true;
    String playerName, secondPlayer, p1RecentOpponent, p2RecentOpponent, compRecentOpponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        // скрыть панель действий по умолчанию
        Objects.requireNonNull(getSupportActionBar()).hide();

        //получить данные из общих настроек
        getData();

        // начальные переменные
        b_quit = findViewById(R.id.b_quit);
        t_title = findViewById(R.id.t_title);
        String output = "Добро пожаловать, " + playerName + "\nсделайте ход";
        t_title.setText(output);

        // привязать все TextViews к переменным
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);

        // добавить прослушиватель кликов в каждый TextView
        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);

        // создать игровое поле
        currentGame = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        // создать аналогичную игровую доску TextViews
        currentGameTVs = new TextView[][]{{a1, a2, a3}, {b1, b2, b3}, {c1, c2, c3}};

        // Добавляет функцию к кнопке выхода (закрывает игру и открывает основное действие)
        b_quit.setOnClickListener(view -> {
            finish();
            Intent i = new Intent(PlayGameActivity.this, MainActivity.class);
            startActivity(i);
        });

    }

    // имеет дело с прослушивателем кликов для каждого текстового представления игрового поля
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.a1:
                addTurn(0, 0);
                break;

            case R.id.a2:
                addTurn(0, 1);
                break;

            case R.id.a3:
                addTurn(0, 2);
                break;

            case R.id.b1:
                addTurn(1, 0);
                break;

            case R.id.b2:
                addTurn(1, 1);
                break;

            case R.id.b3:
                addTurn(1, 2);
                break;

            case R.id.c1:
                addTurn(2, 0);
                break;

            case R.id.c2:
                addTurn(2, 1);
                break;

            case R.id.c3:
                addTurn(2, 2);
                break;

            default:
                //error
                break;
        }

    }

    // Добавляет соответствующий ход на доску (выполняется с помощью асинхронной задачи GameAlgorithm)
    public void addTurn(int row, int column){
        new GameAlgorithm().execute(row, column);

    }

    // Асинхронная задача, выполняющая задачи добавления очереди
    private class GameAlgorithm extends AsyncTask<Integer, Void, Void>{
        String output1;
        @Override
        protected Void doInBackground(Integer... integers) {

            int row = integers[0];
            int column = integers[1];

            // если ход игрока X (или игра против компьютера), добавить ход
            if(player1Turn || secondPlayer.equals("Компьютер")){
                // добавить ход игрока X на доску
                currentGameTVs[row][column].setText(R.string.x);
                currentGame[row][column] = 'X';
                p1TotalMoves++;

                // проверить условия выигрыша
                if(checkPlayer1Win()){
                    openDialog(playerName + " победил!");
                    // добавить выигрыш к данным игрока 1
                    p1Wins++;
                    saveData();
                }

                // если вы играете против компьютера (и пользователь еще не выиграл), заставьте компьютеры двигаться
                if(secondPlayer.equals("Компьютер") && !checkPlayer1Win()){
                    // переместить компьютер и добавить его на доску
                    getCompMove();
                    compTotalMoves++;
                    output1 = "Твоя очередь";

                    // проверить, выиграл ли компьютер (и предотвратить одновременную победу компьютера и игрока)
                    if(!checkPlayer1Win() && checkPlayer2Win()){
                        openDialog(secondPlayer + " победил!");
                        compWins++;
                        saveData();
                    }

                    // иначе переключиться на ход игрока 2
                } else {
                    output1 = secondPlayer + "\nходит";
                }

                // иначе получить ход второго игрока
            } else {
                // Добавить ход второго игрока на доску
                currentGameTVs[row][column].setText(R.string.o);
                currentGame[row][column] = 'O';
                p2TotalMoves++;
                output1 = playerName + "\nходит";

                // проверить, выиграл ли игрок 2
                if(checkPlayer2Win()){
                    openDialog(secondPlayer + " победил!");
                    p2Wins++;
                    saveData();
                }
            }

            // проверить, является ли игра ничьей
            if(numSpacesLeft() == 0){
                openDialog("Ничья!");
                saveData();
            }

            // если игра не была выиграна, поменяйте ходы
            if(!gameWon){
                player1Turn = !player1Turn;
            } else {
                gameWon = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            t_title.setText(output1);
        }
    }

    // Проверить, выиграл ли игрок X
    public boolean checkPlayer1Win(){
        boolean userWon = false;

        // проверка горизонтальных выигрышей
        if ((currentGame[0][0] == currentGame[0][1] && currentGame[0][1] == currentGame[0][2] && currentGame[0][2] == 'X') ||
                (currentGame[1][0] == currentGame[1][1] && currentGame[1][1] == currentGame[1][2] && currentGame[1][2] == 'X') ||
                (currentGame[2][0] == currentGame[2][1] && currentGame[2][1] == currentGame[2][2] && currentGame[2][2] == 'X')){
            userWon = true;
        // проверить вертикальные выигрыши
        } else if ((currentGame[0][0] == currentGame[1][0] && currentGame[1][0] == currentGame[2][0] && currentGame[2][0] == 'X') ||
                (currentGame[0][1] == currentGame[1][1] && currentGame[1][1] == currentGame[2][1] && currentGame[2][1] == 'X') ||
                (currentGame[0][2] == currentGame[1][2] && currentGame[1][2] == currentGame[2][2] && currentGame[2][2] == 'X')){
            userWon = true;
        // проверить диагональные выигрыши
        } else if ((currentGame[0][0] == currentGame[1][1] && currentGame[1][1] == currentGame[2][2] && currentGame[2][2] == 'X') ||
                (currentGame[0][2] == currentGame[1][1] && currentGame[1][1] == currentGame[2][0] && currentGame[2][0] == 'X')){
            userWon = true;
        }

        return userWon;
    }

    // проверить, есть ли у игрока O выигрыш
    public boolean checkPlayer2Win(){
        boolean userWon = false;

        // проверка горизонтальных выигрышей
        if ((currentGame[0][0] == currentGame[0][1] && currentGame[0][1] == currentGame[0][2] && currentGame[0][2] == 'O') ||
                (currentGame[1][0] == currentGame[1][1] && currentGame[1][1] == currentGame[1][2] && currentGame[1][2] == 'O') ||
                (currentGame[2][0] == currentGame[2][1] && currentGame[2][1] == currentGame[2][2] && currentGame[2][2] == 'O')){
            userWon = true;
            // проверить вертикальные выигрыши
        } else if ((currentGame[0][0] == currentGame[1][0] && currentGame[1][0] == currentGame[2][0] && currentGame[2][0] == 'O') ||
                (currentGame[0][1] == currentGame[1][1] && currentGame[1][1] == currentGame[2][1] && currentGame[2][1] == 'O') ||
                (currentGame[0][2] == currentGame[1][2] && currentGame[1][2] == currentGame[2][2] && currentGame[2][2] == 'O')){
            userWon = true;
            // проверить диагональные выигрыши
        } else if ((currentGame[0][0] == currentGame[1][1] && currentGame[1][1] == currentGame[2][2] && currentGame[2][2] == 'O') ||
                (currentGame[0][2] == currentGame[1][1] && currentGame[1][1] == currentGame[2][0] && currentGame[2][0] == 'O')){
            userWon = true;
        }

        return userWon;
    }

    // получает случайное перемещение компьютеров
    public void getCompMove(){
        // random generator
        Random rand = new Random();

        // начальные переменные
        boolean valid = false;

        // проверяет, остались ли еще места для компьютера, если да, то комп случайным образом выбирает ход
        if(numSpacesLeft()>0) {
            do{
                // получить строку, столбец для перемещения компа
                int compRow = rand.nextInt(3);
                int compCol = rand.nextInt(3);
                // проверить, не занято ли место
                if (currentGame[compRow][compCol] != 'X' && currentGame[compRow][compCol] != 'O') {
                    currentGame[compRow][compCol] = 'O';
                    currentGameTVs[compRow][compCol].setText("O");
                    valid = true;
                }

            } while (!valid); // продолжать цикл до тех пор, пока не будет выбран допустимый ход
        }
    }

    // сбрасывает игровое поле
    public void resetBoard(){
        // превращает каждое значение TextView и массива обратно в пустое пространство
        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                currentGame[i][j] = ' ';
                currentGameTVs[i][j].setText(" ");
            }
        }

        // Сообщает пользователям, что снова настала очередь игрока 1
        String output = playerName + "," + "\nновая игра началась";
        t_title.setText(output);
        player1Turn = true;
    }

    // проверяет количество пустых мест на доске
    public int numSpacesLeft(){
        // initial variables
        int numSpaces=0;

        // перебирает 2D-массив и проверяет, сколько из них содержат пробелы
        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentGame[i][j] == ' ') {
                    numSpaces++;
                }
            }
        }

        return numSpaces;
    }

    // открывает диалоговое окно предупреждения, когда кто-то выигрывает
    public void openDialog(String text){
        PopUp wd = new PopUp(text);
        wd.show(getSupportFragmentManager(), "Диалог");
        gameWon = true;
    }

    // когда вы поворачиваете телефон, эта функция вызывается для сохранения любых данных, которые вы хотите сохранить
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // сохранить значения в GameBoard
        outState.putString("a1", a1.getText().toString());
        outState.putString("a2", a2.getText().toString());
        outState.putString("a3", a3.getText().toString());
        outState.putString("b1", b1.getText().toString());
        outState.putString("b2", b2.getText().toString());
        outState.putString("b3", b3.getText().toString());
        outState.putString("c1", c1.getText().toString());
        outState.putString("c2", c2.getText().toString());
        outState.putString("c3", c3.getText().toString());
        outState.putCharArray("row1", currentGame[0]);
        outState.putCharArray("row2", currentGame[1]);
        outState.putCharArray("row3", currentGame[2]);

        // сохранить другие значения
        outState.putString("title", t_title.getText().toString());
        outState.putBoolean("gameWon", gameWon);
        outState.putBoolean("player1Turn", player1Turn);

        super.onSaveInstanceState(outState);
    }

    // когда телефон переворачивается, эта функция вызывается для восстановления любых сохраненных вами данных.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saved) {
        // получить значения из сохраненного состояния
        a1.setText(saved.getString("a1"));
        a2.setText(saved.getString("a2"));
        a3.setText(saved.getString("a3"));
        b1.setText(saved.getString("b1"));
        b2.setText(saved.getString("b2"));
        b3.setText(saved.getString("b3"));
        c1.setText(saved.getString("c1"));
        c2.setText(saved.getString("c2"));
        c3.setText(saved.getString("c3"));

        t_title.setText(saved.getString("title"));
        gameWon = saved.getBoolean("gameWon");
        player1Turn = saved.getBoolean("player1Turn");

        currentGame = new char[][]{saved.getCharArray("row1"), saved.getCharArray("row2"), saved.getCharArray("row3")};

        super.onRestoreInstanceState(saved);
    }

    // Получить данные из общего файла настроек
    public void getData(){
        SharedPreferences sh = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        // Получить текущие имена игроков
        playerName = sh.getString("currentPlayerName", "Player 1");
        secondPlayer = sh.getString("secondPlayer", "Player 2");

        // получить данные для игрока 1
        p1Wins = sh.getInt(playerName.toLowerCase(), 0);
        p1TotalGames = sh.getInt(playerName.toLowerCase() + "_totalGames", 0);
        p1TotalMoves = sh.getInt(playerName.toLowerCase() + "_totalMoves", 0);

        // получить данные для игрока 2
        p2Wins = sh.getInt(secondPlayer.toLowerCase(), 0);
        p2TotalGames = sh.getInt(playerName.toLowerCase() + "_totalGames", 0);
        p2TotalMoves = sh.getInt(playerName.toLowerCase() + "_totalMoves", 0);

        // получить данные для компьютера
        compWins = sh.getInt("computer", 0);
        compTotalGames = sh.getInt("computer_totalGames", 0);
        compTotalMoves = sh.getInt("computer_totalMoves", 0);

    }

    // Сохраняйте данные в общих настройках и обновляйте статистику игрока
    public void saveData(){
        String time = getTime();

        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // сохранять игровые данные в зависимости от того, кто играл (компьютер или второй игрок)
        if (secondPlayer.equals("Компьютер")){
            compTotalGames++;
            compRecentOpponent = playerName.toLowerCase();
            p1RecentOpponent = "computer";

            editor.putInt("computer", compWins);
            editor.putInt("computer_totalGames", compTotalGames);
            editor.putString("computer_recentOpponent", compRecentOpponent);
            editor.putString("computer_time", time);
            editor.putInt("computer_totalMoves", compTotalMoves);
        } else {
            p2TotalGames++;
            p2RecentOpponent = playerName.toLowerCase();
            p1RecentOpponent = secondPlayer.toLowerCase();

            editor.putInt(secondPlayer.toLowerCase(), p2Wins);
            editor.putInt(secondPlayer.toLowerCase() + "_totalGames", p2TotalGames);
            editor.putString(secondPlayer.toLowerCase() + "_recentOpponent", p2RecentOpponent);
            editor.putString(secondPlayer.toLowerCase() + "_time", time);
            editor.putInt(secondPlayer.toLowerCase() + "_totalMoves", p2TotalMoves);
        }

        // добавить к общему количеству игр игрока 1
        p1TotalGames++;

        // сохраняет данные игрока 1 в общих настройках
        editor.putInt(playerName.toLowerCase(), p1Wins);
        editor.putInt(playerName.toLowerCase() + "_totalGames", p1TotalGames);
        editor.putString(playerName.toLowerCase() + "_recentOpponent", p1RecentOpponent);
        editor.putString(playerName.toLowerCase() + "_time", time);
        editor.putInt(playerName.toLowerCase() + "_totalMoves", p1TotalMoves);

        editor.apply();
    }


    // Возвращает отформатированную строку текущего системного времени
    public String getTime(){
        // получить время создания нового кода
        long time = System.currentTimeMillis();
        // Создайте объект DateFormatter для отображения даты в указанном формате.
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa dd/MM/yyyy", Locale.CANADA);
        // Создайте объект календаря, который будет преобразовывать значение даты и времени в миллисекундах в дату.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

}