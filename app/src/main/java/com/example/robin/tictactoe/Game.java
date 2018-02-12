package com.example.robin.tictactoe;

/**
 * Created by Robin on 12-2-2018.
 */

public class Game {

    // set properties
    final private int BOARD_SIZE = 3;
    private Tile[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    // initiate game
    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = Tile.BLANK;
        playerOneTurn = true;
        gameOver = false;
        movesPlayed = 0;
    }

    // process players choice
    public Tile draw(int row, int column) {

        // check if game isn't already over
        if (gameOver || movesPlayed >= 9) {
            return Tile.INVALID;
        }

        // determine players choice
        Tile current = board[row][column];

        // fill in a cross or circle on chosen tile
        if (current == Tile.BLANK) {
            if (playerOneTurn) {
                board[row][column] = Tile.CROSS;
                playerOneTurn = false;
                movesPlayed = movesPlayed + 1;
                return Tile.CROSS;
            } else {
                board[row][column] = Tile.CIRCLE;
                playerOneTurn = true;
                movesPlayed = movesPlayed + 1;
                return Tile.CIRCLE;
            }
        }
        // make sure player doesn't change already chosen tile
        else {
            return Tile.INVALID;
        }
    }

    // check if player won the game
    public int won(int row, int column) {

        // check if player X won the game
        if ((board[row][0] == board[row][1] && board[row][1] == board[row][2]) && board[row][0] == Tile.CROSS ||
                board[0][column] == board[1][column] && board[1][column] == board[2][column] && board[0][column] == Tile.CROSS ||
                board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == Tile.CROSS ||
                board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == Tile.CROSS) {
            gameOver = true;
            return 2;
        }
        // check if player O won the game
        else if ((board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] == Tile.CIRCLE) ||
                (board[0][column] == board[1][column] && board[1][column] == board[2][column] && board[0][column] == Tile.CIRCLE) ||
                (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == Tile.CIRCLE) ||
                (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == Tile.CIRCLE)) {
            gameOver = true;
            return 1;
        }
        // check if it's a tie
        else if (movesPlayed == 9) {
            gameOver = true;
            return 0;
        }
        // play on if game hasn't ended yet
        else {
            return 6;
        }
    }
}

