package com.example.alfredo.cris;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.view.KeyEvent;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alfredo on 08/10/15.
 */

public class pre_game extends AppCompatActivity {
    private EditText ed1, ed2;
    private Button btn2;
    private Switch sw;
    private String name1="", name2="";
    private TextView tv1, tv2;
    private boolean sw_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tv1=(TextView) findViewById(R.id.textView3);
        tv2=(TextView) findViewById(R.id.textView4);

        sw=(Switch) findViewById(R.id.switch1);
        // Cambiare lo stato di sw_v in funzione della pressione dello switch "switch1"
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw_v = true;
                } else {
                    sw_v = false;
                }
            }
        });


        ed1 = (EditText) findViewById(R.id.editText);
        String go = getString(R.string.done);
        ed1.setImeActionLabel(go, KeyEvent.KEYCODE_ENTER);
        ed1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.KEYCODE_ENTER) {
                    name1 = ed1.getText().toString();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                return false;
            }

        });
        ed2 = (EditText) findViewById(R.id.editText2);
        ed2.setImeActionLabel(go, KeyEvent.KEYCODE_ENTER);
        ed2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.KEYCODE_ENTER) {
                    name2 = ed2.getText().toString();
                }
                // chiude la tastiera alla pressione di "Go"
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed2.getWindowToken(), 0);
                return false;
            }
        });
        final String k = ed1.getText().toString();

        // metrics oggetto che conterrà l'altezza del dispositio in uso
        DisplayMetrics metrics;
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int h = metrics.heightPixels;

        btn2=(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed1.getText().toString().equals(k) && !ed2.getText().toString().equals(k)) {
                    name1 = ed1.getText().toString(); name2 = ed2.getText().toString();
                    Intent intent = new Intent(pre_game.this, table.class);
                    String pkg = getPackageName();   // per rendere univoci i nomi delle chiavi passate
                    intent.putExtra(pkg + ".Bool", sw_v);
                    intent.putExtra(pkg + ".Str1", name1);
                    intent.putExtra(pkg + ".Str2", name2);

                    startActivity(intent);

                    finish();
                    return;
                } else {
                    String str = getString(R.string.ply_done);
                    Toast T = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                    T.setGravity(Gravity.NO_GRAVITY, 0, h / 5);
                    T.show();
                }
            }
        });
    }
}
