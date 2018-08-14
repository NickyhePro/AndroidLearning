package com.nicky.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    private int activePlayer = 0;

    private boolean isActive = true;

    // false = unplayed
    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPosition = {
            {0, 1, 2}, {3, 4, 5,}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter] == 2 && isActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer++;

            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red);
                activePlayer--;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);


            for (int[] position : winningPosition) {

                boolean checkFull = true;

                for(int i = 0; i<gameState.length;i++)
                {
                    if(gameState[i] == 2)
                    {
                        checkFull = false;
                    }
                }

                if(checkFull)
                {
                    LinearLayout layout = findViewById(R.id.playAgainLayout);

                    TextView winnerMsg = findViewById(R.id.winningMessage);

                    if (gameState[position[0]] == gameState[position[1]] &&
                            gameState[position[0]] == gameState[position[2]] &&
                            gameState[position[0]] != 2)
                    {
                        if (gameState[position[0]] == 0) {
                            winnerMsg.setText("Yellow player has won!");
                        } else {
                            winnerMsg.setText("Red player has won!");
                        }


                        layout.setVisibility(View.VISIBLE);
                    }else
                    {
                        layout.setVisibility(View.VISIBLE);
                        winnerMsg.setText("Tie!");
                    }

                    isActive = false;
                }
                if (gameState[position[0]] == gameState[position[1]] &&
                        gameState[position[0]] == gameState[position[2]] &&
                        gameState[position[0]] != 2) {
                    LinearLayout layout = findViewById(R.id.playAgainLayout);

                    TextView winnerMsg = findViewById(R.id.winningMessage);

                    if (gameState[position[0]] == 0) {
                        winnerMsg.setText("Yellow player has won!");
                    } else {
                        winnerMsg.setText("Red player has won!");
                    }

                    isActive = false;
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view) {

        isActive = true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        //reset the game values

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gameLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
