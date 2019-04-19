package com.hyjz.hnovel.bean;

import java.util.ArrayList;

public class BaseBeanList <T>{
    private ArrayList<T> result;

    public ArrayList<T> getResult() {
        return result;
    }

    public void setResult(ArrayList<T> result) {
        this.result = result;
    }
}
