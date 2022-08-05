package com.mwj.domain;

public class Relation {

    private int meId;
    private int heId;

    public int getMeId() {
        return meId;
    }

    public void setMeId(int meId) {
        this.meId = meId;
    }

    public int getHeId() {
        return heId;
    }

    public void setHeId(int heId) {
        this.heId = heId;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "meId=" + meId +
                ", heId=" + heId +
                '}';
    }
}
