package com.example.android.a501darts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main_Menu extends AppCompatActivity {

    TextView chooseP1, chooseP2;
    Spinner p1Spinner, p2Spinner;
    Button newGameBtn;
    String playerOnesName, playerTwosName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        setContentView(R.layout.activity_main__menu);
        //Disable numberpad pop up when opening.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        chooseP1 = findViewById(R.id.chooseP1);
        chooseP2 = findViewById(R.id.chooseP2);

        p1Spinner = findViewById(R.id.spinnerP1);
        p2Spinner = findViewById(R.id.spinnerP2);

        newGameBtn = findViewById(R.id.newGameButton);

        /*
        newGame button
         */

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerOnesName = p1Spinner.getSelectedItem().toString();
                playerTwosName = p2Spinner.getSelectedItem().toString();
                changeToGame();
            }
        });
    }

    public void changeToGame()
    {
        Intent intent = new Intent(Main_Menu.this, MainActivity.class);
        String p1Name = playerOnesName = p1Spinner.getSelectedItem().toString();
        String p2Name = playerTwosName = p2Spinner.getSelectedItem().toString();
        intent.putExtra("message_key", playerOnesName);
        intent.putExtra("message_key2", playerTwosName);
        startActivity(intent);
    }


}
