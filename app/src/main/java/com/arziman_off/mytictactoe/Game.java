package com.arziman_off.mytictactoe;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int CIRCLE = 2;
    private boolean gameIsValid;
    private final Player playerLeft;
    private final Player playerRight;
    private final GameSettings settings;
    private Player currentStep;
    private Player winner;
    private int[][] marksArray = new int[3][3];
    private final List<int[]> indexesOfWinningCells = new ArrayList<>();
    public Game(GameSettings settings) {
        this.gameIsValid = true;
        this.playerLeft = new Player(CROSS, settings.getLeftPlayerMarkId());
        this.playerRight = new Player(CIRCLE, settings.getRightPlayerMarkId());
        this.currentStep = playerLeft;
        this.settings = settings;
    }

    int checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (marksArray[i][0] != EMPTY &&
                    marksArray[i][0] == marksArray[i][1] &&
                    marksArray[i][0] == marksArray[i][2]) {

                indexesOfWinningCells.add(new int[]{i, 0});
                indexesOfWinningCells.add(new int[]{i, 1});
                indexesOfWinningCells.add(new int[]{i, 2});
                return marksArray[i][0];
            }
        }

        // Проверка столбцов
        for (int i = 0; i < 3; i++) {
            if (marksArray[0][i] != EMPTY &&
                    marksArray[0][i] == marksArray[1][i] &&
                    marksArray[0][i] == marksArray[2][i]) {

                indexesOfWinningCells.add(new int[]{0, i});
                indexesOfWinningCells.add(new int[]{1, i});
                indexesOfWinningCells.add(new int[]{2, i});
                return marksArray[0][i];
            }
        }

        // Проверка диагоналей
        if (marksArray[0][0] != EMPTY &&
                marksArray[0][0] == marksArray[1][1] &&
                marksArray[0][0] == marksArray[2][2]) {

            indexesOfWinningCells.add(new int[]{0, 0});
            indexesOfWinningCells.add(new int[]{1, 1});
            indexesOfWinningCells.add(new int[]{2, 2});
            return marksArray[0][0];
        }
        if (marksArray[0][2] != EMPTY &&
                marksArray[0][2] == marksArray[1][1] &&
                marksArray[0][2] == marksArray[2][0]) {

            indexesOfWinningCells.add(new int[]{0, 2});
            indexesOfWinningCells.add(new int[]{1, 1});
            indexesOfWinningCells.add(new int[]{2, 0});
            return marksArray[0][2];
        }

        // Нет выигрышной комбинации
        return 0;
    }


    public void printArr() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(this.marksArray[i][j]);
            }
            System.out.println("\n");
        }
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<int[]> getIndexesOfWinningCells() {
        return indexesOfWinningCells;
    }

    public int[][] getMarksArray() {
        return marksArray;
    }

    public void setMarksArray(int[][] marksArray) {
        this.marksArray = marksArray;
    }

    public boolean isGameIsValid() {
        return gameIsValid;
    }

    public void setGameIsValid(boolean gameIsValid) {
        this.gameIsValid = gameIsValid;
    }

    public Player getPlayerLeft() {
        return playerLeft;
    }

    public Player getPlayerRight() {
        return playerRight;
    }

    public GameSettings getSettings() {
        return settings;
    }

    public Player getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Player currentStep) {
        this.currentStep = currentStep;
    }
}

