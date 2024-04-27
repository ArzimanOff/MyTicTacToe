package com.arziman_off.mytictactoe;

import androidx.lifecycle.ViewModel;

public class SharedGameViewModel extends ViewModel {
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
