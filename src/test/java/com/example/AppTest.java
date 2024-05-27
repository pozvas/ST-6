package com.example;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    private static Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game);
        assertEquals(State.PLAYING, game.state);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        for (char cell : game.board) {
            assertEquals(' ', cell);
        }
    }

    @Test
    public void testCheckStateWhenPlaying() {
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    public void testCheckStateForXWinTopRow() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateForOWinDiagonal() {
        game.board[0] = 'O';
        game.board[4] = 'O';
        game.board[8] = 'O';
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateForDrawScenario() {
        char[] drawBoard = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        System.arraycopy(drawBoard, 0, game.board, 0, drawBoard.length);
        assertEquals(State.DRAW, game.checkState(game.board));
    }

    @Test
    public void testAvailableMovesAtStart() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
    }

    @Test
    public void testEvaluatePositionXVictory() {
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.board[5] = 'X';
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    public void testEvaluatePositionOVictory() {
        game.board[6] = 'O';
        game.board[7] = 'O';
        game.board[8] = 'O';
        game.symbol = 'O';
        assertEquals(Game.INF, game.evaluatePosition(game.board, game.player2));
    }

    @Test
    public void testEvaluatePositionDrawScenario() {
        char[] drawBoard = {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'O'};
        System.arraycopy(drawBoard, 0, game.board, 0, drawBoard.length);
        assertEquals(0, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    public void testMinimaxMoveForPlayer2() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = ' ';
        game.board[3] = 'X';
        game.board[4] = ' ';
        game.board[5] = 'X';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testMinMoveCalculation() {
        game.board[2] = 'O';
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'O';
        int minMoveValue = game.MinMove(game.board, game.player1);
        assertTrue(minMoveValue <= 0);
    }

    @Test
    public void testMaxMoveCalculation() {
        game.board[1] = 'O';
        game.board[4] = 'X';
        game.board[6] = ' ';
        int maxMoveValue = game.MaxMove(game.board, game.player2);
        assertTrue(maxMoveValue >= 0);
    }

    @Test
    public void testPlayer1Symbol() {
        assertEquals('X', game.player1.symbol);
    }

    @Test
    public void testPlayer1MoveSetting() {
        game.player1.move = 3;
        assertEquals(3, game.player1.move);
    }

    @Test
    public void testPlayer1Selection() {
        game.player1.selected = false;
        assertFalse(game.player1.selected);
    }

    @Test
    public void testPlayer1WinStatus() {
        game.player1.win = false;
        assertFalse(game.player1.win);
    }

    @Test
    public void testCellRowIndex() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(1, cell.getRow());
    }

    @Test
    public void testCellColumnIndex() {
        TicTacToeCell cell = new TicTacToeCell(5, 1, 2);
        assertEquals(1, cell.getCol());
    }

    @Test
    public void testCellNumber() {
        TicTacToeCell cell = new TicTacToeCell(3, 0, 1);
        assertEquals(3, cell.getNum());
    }

    @Test
    public void testCellMarkerSetting() {
        TicTacToeCell cell = new TicTacToeCell(2, 1, 0);
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertFalse(cell.isEnabled());
    }
}