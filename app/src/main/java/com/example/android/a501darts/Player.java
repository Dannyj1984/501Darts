package com.example.android.a501darts;

public class Player {

    private int score;
    private int darts;
    private int legs;
    private int sets;
    private float average;
    private int hundres, oneforties, oneeighties;
    private String name;
    private int highout;
    private int totalScore;
    private int totalDarts;


    public Player (){}


    //create new player with the name and all score set up. starting score is 501.
    public Player(String aName){
        this.name = aName;
        this.score = 501;
        this.sets = 0;
        this.legs = 0;
        this.darts = 0;
        this.totalDarts = 0;
        this.totalScore = 0;
    }
    // getters and setters
    public float getAverage() {
        return average;
    }

    public int getDarts() {
        return darts;
    }

    public int getLegs() {
        return legs;
    }

    public int getScore() {
        return score;
    }

    public int getSets() {
        return sets;
    }

    public String getName() {
        return name;
    }

    public int getTotalDarts() {
        return totalDarts;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getHighout() {
        return highout;
    }

    public int getHundres() {
        return hundres;
    }

    public int getOneeighties() {
        return oneeighties;
    }

    public int getOneforties() {
        return oneforties;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void setDarts(int darts) {
        this.darts = darts;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setHighout(int highout) {
        this.highout = highout;
    }

    public void setTotalDarts(int totalDarts) {
        this.totalDarts = totalDarts;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setHundres(int hundres) {
        this.hundres = hundres;
    }

    public void setOneeighties(int oneeighties) {
        this.oneeighties = oneeighties;
    }

    public void setOneforties(int oneforties) {
        this.oneforties = oneforties;
    }

    public void incrementLegs(){
        this.legs++;

    }

    public void incrementSets(){
        this.sets++;
    }

}
