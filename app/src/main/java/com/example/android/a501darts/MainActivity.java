package com.example.android.a501darts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView player1, player2;
    private TextView gamesWonA, gamesWonB, teamScoreA, teamScoreB, teamACheckout, teamBCheckout, dartsTeamA, highoutA, averageA, setsA, setsB;
    private TextView dartsTeamB, highoutB, averageB, h100A, h100B, h140A, h140B, h180A, h180B, currentHigh;
    private Button t26;
    private Button t41;
    private Button t43;
    private Button t45;
    private Button t60;
    private Button t100;
    private Button bEnter;
    private Player p1, p2;
    private EditText newScore;
    private int turn, thisScore, turnScore, firstTurn;
    private Checkout check;
    private boolean win = false;
    private int p1LastScore, p2LastScore;
    static int OVERALL_HIGH_OUT;
    static String HIGH_OUT_PLAYER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Disable numberpad pop up when opening.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
/*
  Set up the views
 */
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        gamesWonA = findViewById(R.id.gamesWonA);
        gamesWonB = findViewById(R.id.gamesWonB);
        teamScoreA = findViewById(R.id.teamScoreA);
        teamScoreB = findViewById(R.id.teamScoreB);
        teamACheckout = findViewById(R.id.teamACheckout);
        teamBCheckout = findViewById(R.id.teamBCheckout);
        newScore = findViewById(R.id.newScore);
        dartsTeamA = findViewById(R.id.dartsTeamA);
        dartsTeamB = findViewById(R.id.dartsTeamB);
        highoutA = findViewById(R.id.highoutA);
        highoutB = findViewById(R.id.highoutB);
        averageA = findViewById(R.id.averageA);
        averageB = findViewById(R.id.averageB);
        currentHigh = findViewById(R.id.currentHigh);
        setsA = findViewById(R.id.setsA);
        setsB = findViewById(R.id.setsB);
        h100A = findViewById(R.id.h100A);
        h100B = findViewById(R.id.h100B);
        h140A = findViewById(R.id.h140A);
        h140B = findViewById(R.id.h140B);
        h180A = findViewById(R.id.h180A);
        h180B = findViewById(R.id.h180B);

        t26 = findViewById(R.id.t26A);
        t41 = findViewById(R.id.t41A);
        t43 = findViewById(R.id.t43A);
        t45 = findViewById(R.id.t45A);
        t60 = findViewById(R.id.t60A);
        t100 = findViewById(R.id.t100A);
        Button b1 = findViewById(R.id.buttonOneA);
        Button b2 = findViewById(R.id.buttonTwoA);
        Button b3 = findViewById(R.id.buttonThreeA);
        Button b4 = findViewById(R.id.buttonFourA);
        Button b5 = findViewById(R.id.buttonFiveA);
        Button b6 = findViewById(R.id.buttonSixA);
        Button b7 = findViewById(R.id.buttonSevenA);
        Button b8 = findViewById(R.id.buttonEightA);
        Button b9 = findViewById(R.id.buttonNineA);
        Button b0 = findViewById(R.id.buttonZeroA);
        Button bDelete = findViewById(R.id.buttonUndoA);
        bEnter = findViewById(R.id.buttonEnterA);
        Button save = findViewById(R.id.save);
        Button load = findViewById(R.id.load);
        Button startGame = findViewById(R.id.startGame);
        Button newGame = findViewById(R.id.newGame);
        Button reset = findViewById(R.id.reset);




        //Create new Scorer and Checkout objects
        check = new Checkout();
        check.addCheckouts();
        Scorer sc1 = new Scorer();
        sc1.addPossScores();

        /*
        reset
         */
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });



        /*
        save
         */

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        /*
        load
         */

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });


/*
  New game button setUp
 */

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScores();
                display();

                Log.d("p1 Aver is : ", String.valueOf(p1.getAverage()));
                Log.d("p1 high out is : ", String.valueOf(p1.getHighout()));

            }
        });

        /*
          Start game button setup
          creates new players 1 and 2.   sets the score, legs, darts and highout as per the
          Player details
          Sets the player average as per the Player details
          sets turn to 1 and highlights p1
         */

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p1Name = getIntent().getStringExtra("message_key");
                String p2Name = getIntent().getStringExtra("message_key2");



                if (p1Name.equals("") || p2Name.equals("")) {
                    Toast.makeText(getBaseContext(), "Please enter player names", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    p1 = new Player(p1Name);
                    p2 = new Player(p2Name);
                }

                Log.d("p1 name is : ", p1.getName());
                Log.d("p2 name is : ", p2.getName());



                turn = 1;
                firstTurn = 1;
                setTextBackground();
                display();

                currentHigh.setSelected(true);


            }

        });


        /*
          numberpad  logic
         */


        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "0"));
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "1"));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "2"));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "3"));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "4"));
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "5"));
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "6"));
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "7"));
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "8"));
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newScore.setText(newScore.getText().insert(newScore.getText().length(), "9"));
            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    newScore.setText(newScore.getText().delete(newScore.getText().length() - 1, newScore.getText().length()));
                } catch (Exception e) {

                    e.printStackTrace();

                }
            }
        });

        /*
          Quick score buttons set up.
          checks whose turn it is.
          adjusts the score for that player in relation to the button pressed
          displays the new score
          changes turn to the next player
         */

        t26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t26.setPressed(true);

                p1LastScore = p1.getScore();
                p2LastScore = p2.getScore();

                whatIsTurnScore();


                if (turn == 1) {

                    if ((p1.getScore() - 26 < 0) || (p1.getScore() - 26 == 1)) {

                        display();
                    } else {

                        p1.setScore(p1.getScore() - 26);
                        display();
                        checkWin();

                    }


                }

                if (turn == 2) {

                    if ((p2.getScore() - 26 < 0) || (p2.getScore() - 26 == 1)) {


                        displayScore();
                    } else {

                        p2.setScore(p2.getScore() - 26);
                        display();
                        checkWin();

                    }
                }

                incrementDarts();
                //totalDarts();
                totalScore();
                calculateAverage();
                if(!win){
                    changeTurn();
                }

                display();
                teamACheckout.setText(checkOut(p1.getScore()));
                teamBCheckout.setText(checkOut(p2.getScore()));
                reset();
                t26.setPressed(false);


            }
        });

        t41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t41.setPressed(true);

                p1LastScore = p1.getScore();
                p2LastScore = p2.getScore();

                whatIsTurnScore();


                if (turn == 1) {

                    if ((p1.getScore() - 41 < 0) || (p1.getScore() - 41 == 1)) {


                        displayScore();
                    } else if (p1.getScore() - 41 > 1) {


                        p1.setScore(p1.getScore() - 41);
                        display();


                    } else if (p1.getScore() - 41 == 0) {
                        p1.setScore(0);
                        win();
                    }


                }

                if (turn == 2) {

                    if ((p2.getScore() - 41 < 0) || (p2.getScore() - 41 == 1)) {


                        displayScore();
                    } else if (p2.getScore() - 41 > 1) {


                        p2.setScore(p2.getScore() - 41);
                        display();

                    } else if (p2.getScore() - 41 == 0) {
                        p2.setScore(0);
                        win();
                    }
                }
                incrementDarts();
                //totalDarts();
                calculateAverage();
                if(!win){
                    changeTurn();
                }

                display();

                teamACheckout.setText(checkOut(p1.getScore()));
                teamBCheckout.setText(checkOut(p2.getScore()));
                reset();
                t41.setPressed(false);

            }
        });

        t43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t43.setPressed(true);

                p1LastScore = p1.getScore();
                p2LastScore = p2.getScore();

                whatIsTurnScore();


                int buttonScore = 43;


                if (turn == 1) {

                    if ((p1.getScore() - buttonScore < 0) || (p1.getScore() - buttonScore == 1)) {


                        displayScore();
                    } else if (p1.getScore() - buttonScore > 1) {


                        p1.setScore(p1.getScore() - buttonScore);
                        display();

                    } else if (p1.getScore() - buttonScore == 0) {
                        p1.setScore(0);
                        win();
                    }


                }

                if (turn == 2) {

                    if ((p2.getScore() - buttonScore < 0) || (p2.getScore() - buttonScore == 1)) {


                        displayScore();
                    } else if (p2.getScore() - buttonScore > 1) {


                        p2.setScore(p2.getScore() - buttonScore);
                        display();
                    } else if (p2.getScore() - buttonScore == 0) {
                        p2.setScore(0);
                        win();
                    }
                }
                incrementDarts();
                //totalDarts();
                calculateAverage();
                if(!win){
                    changeTurn();
                }

                display();
                teamACheckout.setText(checkOut(p1.getScore()));
                teamBCheckout.setText(checkOut(p2.getScore()));
                reset();
                t43.setPressed(false);

            }
        });

        t45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t45.setPressed(true);

                p1LastScore = p1.getScore();
                p2LastScore = p2.getScore();

                whatIsTurnScore();


                int btnScore = 45;


                if (turn == 1) {

                    if ((p1.getScore() - btnScore < 0) || (p1.getScore() - btnScore == 1)) {


                        displayScore();
                    } else if (p1.getScore() - btnScore > 1) {

                        p1.setScore(p1.getScore() - btnScore);
                        display();

                    } else if (p1.getScore() - btnScore == 0) {
                        p1.setScore(0);
                        win();
                    }


                }

                if (turn == 2) {

                    if ((p2.getScore() - btnScore < 0) || (p2.getScore() - btnScore == 1)) {


                        displayScore();
                    } else if (p2.getScore() - btnScore > 1) {

                        p2.setScore(p2.getScore() - btnScore);
                        display();
                    } else if (p2.getScore() - btnScore == 0) {
                        p2.setScore(0);
                        win();
                    }
                }
                incrementDarts();
                //totalDarts();
                calculateAverage();
                if(!win){
                    changeTurn();
                }

                display();
                teamACheckout.setText(checkOut(p1.getScore()));
                teamBCheckout.setText(checkOut(p2.getScore()));
                reset();
                t45.setPressed(false);
            }
        });

        t60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t60.setPressed(true);

                p1LastScore = p1.getScore();
                p2LastScore = p2.getScore();

                whatIsTurnScore();


                int btnScore = 60;


                if (turn == 1) {

                    if ((p1.getScore() - btnScore < 0) || (p1.getScore() - btnScore == 1)) {


                        displayScore();
                    } else if (p1.getScore() - btnScore > 1) {

                        p1.setScore(p1.getScore() - btnScore);
                        display();

                    } else if (p1.getScore() - btnScore == 0) {
                        p1.setScore(0);
                        win();
                    }


                }

                if (turn == 2) {

                    if ((p2.getScore() - btnScore < 0) || (p2.getScore() - btnScore == 1)) {


                        displayScore();
                    } else if (p2.getScore() - btnScore > 1) {

                        p2.setScore(p2.getScore() - btnScore);
                        display();
                    } else if (p2.getScore() - btnScore == 0) {
                        p2.setScore(0);
                        win();
                    }
                }
                incrementDarts();
                //totalDarts();
                calculateAverage();
                if(!win){
                    changeTurn();
                }

                display();
                teamACheckout.setText(checkOut(p1.getScore()));
                teamBCheckout.setText(checkOut(p2.getScore()));
                reset();
                t60.setPressed(false);
            }
        });

        t100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t100.setPressed(true);

                p1LastScore = p1.getScore();
                p2LastScore = p2.getScore();

                whatIsTurnScore();


                int btnScore = 100;

                if (turn == 1) {

                    if ((p1.getScore() - btnScore < 0) || (p1.getScore() - btnScore == 1)) {


                        displayScore();
                    } else if (p1.getScore() - btnScore > 1) {

                        p1.setScore(p1.getScore() - btnScore);
                        p1.setHundres(p1.getHundres() + 1);
                        display();

                    } else if (p1.getScore() - btnScore == 0) {
                        p1.setScore(0);
                        win();
                    }


                }

                if (turn == 2) {

                    if ((p2.getScore() - btnScore < 0) || (p2.getScore() - btnScore == 1)) {


                        displayScore();
                    } else if (p2.getScore() - btnScore > 1) {

                        p2.setScore(p2.getScore() - btnScore);
                        p2.setHundres(p2.getHundres() + 1);
                        display();
                    } else if (p2.getScore() - btnScore == 0) {
                        p2.setScore(0);
                        win();
                    }
                }
                incrementDarts();
                //totalDarts();
                calculateAverage();
                if(!win){
                    changeTurn();
                }

                display();
                teamACheckout.setText(checkOut(p1.getScore()));
                teamBCheckout.setText(checkOut(p2.getScore()));
                reset();
                t100.setPressed(false);
            }
        });


        /*
          Enter button set up
          runs the checkScore method
          changes the newScore value back to null ready for the next turn.
          Changes turn to the next player
         */

        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    bEnter.setPressed(true);

                    p1LastScore = p1.getScore();
                    p2LastScore = p2.getScore();

                    whatIsTurnScore();
                    if (turnScore > 180) {
                        newScore.setText("");
                        turnScore = 0;
                    }


                    checkScore();
                    newScore.setText(null);

                    if (turn == 1) {
                        teamACheckout.setText(checkOut(p1.getScore()));
                    } else {
                        teamBCheckout.setText(checkOut(p2.getScore()));
                    }
                    incrementDarts();
                    //totalDarts();
                    calculateAverage();
                    if (p1.getScore() == 0) {
                        win();
                    }

                    if (p2.getScore() == 0) {
                        win();
                    }
                    if(!win){
                        changeTurn();
                    }
                    display();
                } catch (NullPointerException npe) {
                    Toast.makeText(getBaseContext(), "Please start a game", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {

                    e.printStackTrace();


                }
                reset();
                bEnter.setPressed(false);

            }
        });


    }

    /**
     * Takes the Score for that turn and and check whether the current score - thisScore
     * would cause the players score to be either 1 or < 0.
     * If this is true then change the text in the turnScore to "" and then run the displayscore()
     * and checkFinish()
     * If the value is about > and not 1 then run the calculateScore() to work out the new score.
     */
    public void checkScore() {

        whatIsTurnScore();

        if (turn == 1) {

            if ((p1.getScore() - turnScore < 0) || (p1.getScore() - turnScore == 1)) {
                newScore.setText(null);

                displayScore();


                Toast.makeText(this, "Bust", Toast.LENGTH_SHORT).show();
            } else if (p1.getScore() - turnScore > 1) {
                if ((turnScore > 99) && (turnScore < 140)) {
                    p1.setHundres(p1.getHundres() + 1);
                } else if ((turnScore > 139) && (turnScore < 180)) {
                    p1.setOneforties(p1.getOneforties() + 1);
                } else if (turnScore == 180) {
                    p1.setOneeighties(p1.getOneeighties() + 1);
                }
                calculateScore();


                displayScore();
            } else if (p1.getScore() - turnScore == 0) {
                p1.setScore(0);
            }
        }

        if (turn == 2) {
            if ((p2.getScore() - turnScore < 0) || (p2.getScore() - turnScore == 1)) {
                newScore.setText(null);
                teamBCheckout.setText(checkOut(p2.getScore()));
                displayScore();


                Toast.makeText(this, "Bust", Toast.LENGTH_SHORT).show();
            } else if (p2.getScore() - turnScore > 1) {

                if ((turnScore > 99) && (turnScore < 140)) {
                    p2.setHundres(p2.getHundres() + 1);
                } else if ((turnScore > 139) && (turnScore < 180)) {
                    p2.setOneforties(p2.getOneforties() + 1);
                } else if (turnScore == 180) {
                    p2.setOneeighties(p2.getOneeighties() + 1);
                }
                calculateScore();

                displayScore();

            } else if (p2.getScore() - turnScore == 0) {
                p2.setScore(0);
            }

        }


    }

    /**
     * Run turnScore() to get the score of this turn.
     * Check whose turn it is, and then set the player score to their current score - turnScore.
     */

    public void calculateScore() {

        turnScore();

        if (turn == 1) {
            p1.setScore(p1.getScore() - turnScore);

        } else if (turn == 2) {
            p2.setScore(p2.getScore() - turnScore);

        }


    }

    /**
     * Displays,
     * Displays the details for score, average, darts, highout, legs, sets
     * Finally a method to display all the stats
     */
    public void displayName(){
        player1.setText(p1.getName());
        player2.setText(p2.getName());
    }

    public void displayScore() {
        teamScoreA.setText(String.valueOf(p1.getScore()));
        teamScoreB.setText(String.valueOf(p2.getScore()));
    }


    public void displayAverage() {
        averageA.setText(getString(R.string.average) + String.valueOf(p1.getAverage()));
        averageB.setText(getString(R.string.average) + String.valueOf(p2.getAverage()));
    }

    public void displayDarts() {
        dartsTeamA.setText(getString(R.string.darts) + Integer.toString(p1.getDarts()));
        dartsTeamB.setText(getString(R.string.darts) + Integer.toString(p2.getDarts()));
    }

    public void displayHighout() {
        highoutA.setText(getString(R.string.highOut) + Integer.toString(p1.getHighout()));
        highoutB.setText(getString(R.string.highOut) + Integer.toString(p2.getHighout()));
    }

    public void displayLegs() {
        gamesWonA.setText(getString(R.string.legs) + Integer.toString(p1.getLegs()));
        gamesWonB.setText(getString(R.string.legs) + Integer.toString(p2.getLegs()));
    }

    public void displaySets() {
        setsA.setText(getString(R.string.sets) + Integer.toString(p1.getSets()));
        setsB.setText(getString(R.string.sets) + Integer.toString(p2.getSets()));

    }

    public void disply100s() {
        h100A.setText(getString(R.string.hundreds) + p1.getHundres());
        h100B.setText(getString(R.string.hundreds) + p2.getHundres());

    }

    public void disply140s() {
        h140A.setText(getString(R.string.oneForties) + p1.getOneforties());
        h140B.setText(getString(R.string.oneForties) + p2.getOneforties());

    }

    public void disply180s() {
        h180A.setText(getString(R.string.oneEighties) + p1.getOneeighties());
        h180B.setText(getString(R.string.oneEighties) + p2.getOneeighties());

    }

    public void displayCurrentHighOut(){
        currentHigh.setText(getString(R.string.currentHigh)+String.valueOf(OVERALL_HIGH_OUT));
    }


    public void display() {
        displayName();
        displayScore();
        displayAverage();
        displayDarts();
        displayHighout();
        displayLegs();
        displaySets();
        disply100s();
        disply140s();
        disply180s();
        displayCurrentHighOut();
    }

    /**
     * Cycles through turns and also changes the highlight on which players turn it is
     * by highlighting their name in green.
     */

    public void changeTurn() {
        if (turn == 1) {
            turn = 2;

        } else {
            turn = 1;

        }
        setTextBackground();
    }



        /*
        Add a new player to the map with their name as a key and average as a value.
         */
    //Todo need to setup so that the map is saved after each game and then retrieved at the start of a new game and the average displayed for the correct player.


    public void addPlayerToMap(String name, int average) {

        Map<String, Integer> playerAverage = new HashMap<>();
        playerAverage.put(name, average);
    }


    /**
     * calculate the score for this turn in an int format. Take the String value from the newScore
     * view and turn it into an int.
     */

    public void turnScore() {


        String scoreString = newScore.getText().toString();
        turnScore = Integer.parseInt(scoreString);

    }


    /*
      Get the map from Checkout class, check if the
     */


    public String checkOut(int remaining) {

        String result = "";


        Map<Integer, String> checkouts = check.getMap();

        Set<Integer> outs = checkouts.keySet();

        for (int i : outs) {
            if (i == remaining) {
                result = checkouts.get(i);


            }

        }
        return result;

    }

    public void incrementDarts() {
        if (turn == 1) {
            p1.setDarts(this.p1.getDarts() + 3);
            dartsTeamA.setText(String.valueOf(p1.getDarts()));
        } else {
            p2.setDarts(this.p2.getDarts() + 3);
            dartsTeamB.setText(String.valueOf(p2.getDarts()));
        }
    }





    public void totalScore() {

        if (turn == 1) {

            whatIsTurnScore();
            p1.setTotalScore(p1.getTotalScore() + turnScore);
        }
    }

    //todo sort out averages
    public void calculateAverage() {

        if (turn == 1) {
            p1.setTotalScore(p1.getTotalScore() + turnScore);

            p1.setTotalDarts(p1.getTotalDarts() + 1);

            float p1Average = p1.getTotalScore() / p1.getTotalDarts();
            p1.setAverage(p1Average);

        } else if (turn == 2) {
            p2.setTotalDarts(p2.getTotalDarts() + 1);
            p2.setTotalScore(p2.getTotalScore() + turnScore);

            float p2Average = p2.getTotalScore() / p2.getTotalDarts();
            p2.setAverage(p2Average);
        }


    }

    public void whatIsTurnScore() {
        if (t26.isPressed()) {
            turnScore = 26;
        }
        if (t41.isPressed()) {
            turnScore = 41;
        }

        if (t43.isPressed()) {
            turnScore = 43;
        }

        if (t45.isPressed()) {
            turnScore = 45;
        }

        if (t60.isPressed()) {
            turnScore = 60;
        }

        if (t100.isPressed()) {
            turnScore = 100;
        }

        if (bEnter.isPressed()) {
            turnScore = Integer.parseInt(newScore.getText().toString());
        }
    }

    public void checkWin() {
        if ((p1.getScore() == 0) || (p2.getScore() == 0)) {
            win();
        }
    }

    /*
    If leg is won, increment legs for the winning player and if legs = 5 then also increment sets and reset legs to 0
     */

    public void win() {
        if (turn == 1) {
            p1.incrementLegs();
            if (p1.getLegs() == 5) {
                p1.incrementSets();
                p1.setLegs(0);
                p2.setSets(0);

            }
            if (p1LastScore > p1.getHighout()) {
                p1.setHighout(p1LastScore);

            }

            if (p1.getHighout() > OVERALL_HIGH_OUT) {
                OVERALL_HIGH_OUT = p1.getHighout();
            }
        } else if (turn == 2) {
            p2.incrementLegs();
            if (p2.getLegs() == 5) {
                p2.incrementSets();
                p2.setLegs(0);
                p1.setSets(0);

            }
            if (p2LastScore > p2.getHighout()) {
                p2.setHighout(p2LastScore);
            }

            if (p2.getHighout() > OVERALL_HIGH_OUT) {
                OVERALL_HIGH_OUT = p2.getHighout();
            }



        }
        changeFirstTurn();
        win = true;



    }

    public void changeFirstTurn(){

        if(firstTurn == 1){
            firstTurn = 2;
            turn = 2;
            Toast.makeText(this, "turn is "+turn+" firstTurn is "+firstTurn, Toast.LENGTH_SHORT).show();

        }else{
            firstTurn = 1;
            turn = 1;

        }
        setTextBackground();

    }

    public void reset() {
        if (win) {
            p1.setScore(501);
            p2.setScore(501);

            teamACheckout.setText(R.string.noCheckout);
            teamBCheckout.setText(R.string.noCheckout);

            p1.setDarts(0);
            p2.setDarts(0);

            win = false;
            display();
        }
    }

    public void save() {
        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("p1name", p1.getName());
        editor.putString("p2name", p2.getName());
        editor.putInt("p1TotalScore", p1.getTotalScore());
        editor.putInt("p2TotalScore", p2.getTotalScore());
        editor.putInt("p1TotalDarts", p1.getTotalDarts());
        editor.putInt("p2TotalDarts", p2.getTotalDarts());

        editor.putInt("p1HighOut", p1.getHighout());
        editor.putInt("p2HighOut", p2.getHighout());
        editor.putInt("highOut", OVERALL_HIGH_OUT);
        editor.apply();



    }

    public void load(){

        //
        //load shared prefs

        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
        String p1name = sharedPref.getString("p1name", "");
        String p2name = sharedPref.getString("p2name", "");
        int p1TotalDarts = sharedPref.getInt("p1TotalDarts", 0);
        int p2TotalDarts = sharedPref.getInt("p2TotalDarts", 0);
        int p1TotalScore = sharedPref.getInt("p1TotalScore", 0);
        int p2TotalScore = sharedPref.getInt("p2TotalScore", 0);
        int p1HighOut = sharedPref.getInt("p1HighOut", 0);
        int p2HighOut = sharedPref.getInt("p2HighOut", 0);
        int oHO = sharedPref.getInt("highOut", 0);

        try {
            p1 = new Player(p1name);
            p2 = new Player(p2name);
            p1.setName(p1name);
            p2.setName(p2name);
            Log.d("p1 name is : ", p1.getName());
            p1.setHighout(p1HighOut);
            p2.setHighout(p2HighOut);
            p1.setTotalScore(p1TotalScore);
            p2.setTotalScore(p2TotalScore);
            p1.setTotalDarts(p1TotalDarts);
            p2.setTotalDarts(p2TotalDarts);

            p1.setAverage(p1.getTotalScore() / p1.getTotalDarts());
            p2.setAverage(p2.getTotalScore() / p2.getTotalDarts());
            Toast.makeText(this, "p1 avg is : "+p1.getAverage(), Toast.LENGTH_SHORT).show();

            OVERALL_HIGH_OUT = oHO;

            display();




        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "No Scores yet : ", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeActivity()
    {
        try {
            resetScores();
        } catch(Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(MainActivity.this, Main_Menu.class);
        startActivity(intent);
    }

    public void resetScores(){
        p1.setScore(501);
        p2.setScore(501);

        p1.setDarts(0);
        p2.setDarts(0);

        p1.setLegs(0);
        p2.setLegs(0);

        p1.setSets(0);
        p2.setSets(0);

        p1.setHundres(0);
        p2.setHundres(0);
        p1.setOneforties(0);
        p2.setOneforties(0);
        p1.setOneeighties(0);
        p2.setOneeighties(0);
    }

    public void setTextBackground(){

        if(turn == 1){
            player1.setBackgroundColor(Color.GREEN);
            player2.setBackgroundColor(Color.WHITE);
        }

        if(turn == 2){
            player1.setBackgroundColor(Color.WHITE);
            player2.setBackgroundColor(Color.GREEN);
        }
    }


}

