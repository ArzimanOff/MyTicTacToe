package com.arziman_off.mytictactoe;

import android.content.Context;

public class GameSettings {
    private int leftPlayerMarkId;
    private int rightPlayerMarkId;

    private Context context;

    public GameSettings(int leftPlayerMarkId, int rightPlayerMarkId) {
        this.leftPlayerMarkId = leftPlayerMarkId;
        this.rightPlayerMarkId = rightPlayerMarkId;
    }

    public int getLeftPlayerMarkId() {
        return leftPlayerMarkId;
    }

    public void setLeftPlayerMarkId(int leftPlayerMarkId) {
        this.leftPlayerMarkId = leftPlayerMarkId;
    }

    public int getRightPlayerMarkId() {
        return rightPlayerMarkId;
    }

    public void setRightPlayerMarkId(int rightPlayerMarkId) {
        this.rightPlayerMarkId = rightPlayerMarkId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
