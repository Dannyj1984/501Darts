package com.example.android.a501darts;

import java.util.ArrayList;
import java.util.List;

public class Scorer {

    private List<Integer> scores;

    public Scorer(){}




    public void addPossScores(){

        scores = new ArrayList<>();

        for(int i = 1; i <162; i++){
            scores.add(i);
        }
        scores.add(164);
        scores.add(167);
        scores.add(170);
    }


}


