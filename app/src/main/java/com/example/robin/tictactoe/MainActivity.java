package com.example.robin.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int column;
    public int row;
    // initiate properties
    Game game;
    int numberOfTiles = 9;
    TextView TileText;
    TextView Winnertext;

    // save list of all button id's
    int[] tileId = new int[]{R.id.topleft, R.id.topcenter, R.id.topright,
            R.id.middleleft, R.id.middlecenter, R.id.middleright,
            R.id.bottomleft, R.id.bottomcenter, R.id.bottomright};

    // create new game or restore game session
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // restore game if possible
        if (savedInstanceState != null) {
            game = new Game();

            // restore text saying who's the winner
            String winner = savedInstanceState.getString("winner");
            Winnertext = findViewById(R.id.winner);
            Winnertext.setText(winner);

            // restore board
            for (int i = 0; i < tileId.length; i++) {
                TileText = findViewById(tileId[i]);
                if (TileText != null) {

                    // restore X's
                    if (savedInstanceState.getInt("save" + tileId[i]) == 2) {
                        TileText.setText("X");
                        column = i % 3;
                        row = i / 3;

                        game.draw(row, column);
                    }
                    // restore O's
                    else if (savedInstanceState.getInt("save" + tileId[i]) == 1) {
                        TileText.setText("O");
                        column = i % 3;
                        row = i / 3;

                        game.draw(row, column);
                    }
                }
            }
        }
        // create new game
        else {
            game = new Game();
        }
    }

    // save X's and O's
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < tileId.length; i++) {
            TileText = findViewById(tileId[i]);
            if (TileText.getText() == "X") {
                outState.putInt("save" + tileId[i], 2);
            } else if (TileText.getText() == "O") {
                outState.putInt("save" + tileId[i], 1);
            } else {
                outState.putInt("save" + tileId[i], 0);
            }
        }
        Winnertext = findViewById(R.id.winner);
        String winner = (String) Winnertext.getText();
        outState.putString("winner", winner);
    }

    // enter players entry and check if game is won
    public void tileClicked(View view) {

        // retrieve player's choice
        int id = view.getId();
        for (int i = 0; i < numberOfTiles; i++) {
            if (id == tileId[i]) {
                column = i % 3;
                row = i / 3;
                break;
            }
        }

        // find out which player played
        Tile tile = game.draw(row, column);

        // add players choice to screen
        switch (tile) {
            case CROSS:
                TileText = findViewById(id);
                TileText.setText("X");
                break;
            case CIRCLE:
                TileText = findViewById(id);
                TileText.setText("O");
                break;
            case INVALID:
                System.out.println("Tile already used");
                break;
        }

        // check if game is won
        Winnertext = findViewById(R.id.winner);
        int wonBy = game.won(row, column);

        if (wonBy == 2) {
            Winnertext.setText("X won!");
        } else if (wonBy == 1) {
            Winnertext.setText("O won!");
        } else if (wonBy == 0) {
            Winnertext.setText("It's a tie!");
        }
    }

    // start new game if reset button is clicked
    public void resetClicked(View v) {
        game = new Game();
        setContentView(R.layout.activity_main);
    }
}
