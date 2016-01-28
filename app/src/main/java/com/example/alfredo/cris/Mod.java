package com.example.alfredo.cris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by alfredo on 21/10/15.
 */
public class Mod extends AppCompatActivity {

    private Button b_norm, b_btooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);

        b_norm = (Button) findViewById(R.id.button5);
        b_btooth = (Button) findViewById(R.id.button6);

        b_norm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mod.this, pre_game.class));
            }
        });

        b_btooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Mod.this, act_btooth.class);*/
                Intent intent = new Intent(Mod.this, DioBove.class);
                startActivity(intent);
            }
        });
    }
}