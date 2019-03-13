package com.example.tic_tac_toe;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button [][] buttons= new Button [3][3];
    private boolean Player_1_turn=true;
    private int count;
    private int Player1Points;
    private int Player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1 = findViewById(R.id.text_view_1);
        textViewPlayer2 = findViewById(R.id.text_view_2);
         reset = findViewById(R.id.Reset);
         reset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 resetGame();
             }
         });
       for (int i = 0; i <3; i++){
           for(int j =0; j < 3; j++){
               String ID = "Button_"+i+j;
               int resID = getResources().getIdentifier(ID, "id", getPackageName());
               buttons [i][j] = findViewById(resID);
               buttons[i][j].setOnClickListener(this);
           }
       }


    }

    @Override
    public void onClick(View v) {
   if(!((Button) v).getText().equals("")){
       return;
   }
   else {
       if(Player_1_turn){
            ((Button) v).setText("X");
           ( (Button) v).setBackgroundColor(getResources().getColor(R.color.Player1));
       }
       else  {  ((Button) v).setText("O");
           ( (Button) v).setBackgroundColor(getResources().getColor(R.color.Player2));}

       if(getForWin()){
           if(Player_1_turn){
              player1Wins();

           }
           else  player2Wins();


       }
        else  { draw();}
       changePlayer();
       count++;

   };
    }
    private boolean getForWin(){
        for (int i = 0; i < 3; i++){
            if(buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons [i][0].getText().equals(""))
                return true;
        }
        for (int i = 0; i < 3; i++){
            if(buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons [0][i].getText().equals(""))
                return true;
        }

           if ( buttons[0][0].getText().equals(buttons[1][1].getText()) &&
           buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                   !buttons [0][0].getText().equals("")){
               return true;
        }
        if ( buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons [0][2].getText().equals("")){
            return true;
        }
        return false;

    }
    private void changePlayer(){
        Player_1_turn = ! Player_1_turn;
    }
    private void player1Wins(){
        Player1Points++;
        Toast.makeText(this, "Player 1  wins!", Toast.LENGTH_SHORT).show();
        resetBoards();
        updatePoints();

    }

    private void player2Wins(){
        Player2Points++;
        Toast.makeText(this, "Player 2  wins!", Toast.LENGTH_SHORT).show();
        resetBoards();
        updatePoints();
    }
    private void resetBoards(){
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3 ; j++){
                buttons [i][j].setText("");
                buttons[i][j].setBackgroundColor(getResources().getColor(R.color.Blank));
            }
        }
        count =0;
        Player_1_turn = true;
    }
    private void resetGame(){
        Player1Points = 0;
        Player2Points = 0;
        count = 0;
        updatePoints();
        resetBoards();
    }
    private void updatePoints(){
        textViewPlayer1.setText("Player 1: "+Player1Points);
        textViewPlayer2.setText("Player 2: "+Player2Points);

    }
    private void draw(){
        if(count==9){
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoards();}

    }

    @Override
    protected void onSaveInstanceState  ( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Player1Points", Player1Points);
        outState.putInt("Player2Points", Player2Points);
        outState.putInt("count", count);
        outState.putBoolean("Player_1_turn", Player_1_turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Player1Points = savedInstanceState.getInt("Player1Points");
        Player2Points = savedInstanceState.getInt("Player2Points");
        count = savedInstanceState.getInt("count");
        Player_1_turn = savedInstanceState.getBoolean("Player_1_turn");
    }
}
