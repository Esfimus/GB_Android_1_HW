package com.esfimus.gbandroid1hw;

public class Presenter {
    private String input = "";

    public String getResult() {
        return input;
    }

    public String getSubResult() {
        return "Subresult";
    }

    public void passValue(String value) {
        input += value;
    }
}
