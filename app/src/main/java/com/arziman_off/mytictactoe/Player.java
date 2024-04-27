package com.arziman_off.mytictactoe;

public class Player {
    private Integer id;
    private int mark;

    public Player(Integer id, int mark) {
        this.id = id;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
