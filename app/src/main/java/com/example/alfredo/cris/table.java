package com.example.alfredo.cris;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/**
 * Created by alfredo on 08/10/15.
 */

public class table extends AppCompatActivity {
    private int r = 3, c = 3;
    private char[][] mtx = new char[r][c];
    private boolean sw;
    private String s1, s2;
    private TextView ply1, ply2, t, final_t, vic_ply1, vic_ply2;
    private ImageView imm2, imm3;
    private int turn = 0, v_ply1 = 0, v_ply2 = 0;
    private Button b3, b4;
    Random ran = new Random();

    LinearLayout ll1;
    LinearLayout ll2;
    LinearLayout ll3;
    LinearLayout ll4;
    LinearLayout ll5;
    LinearLayout ll6;
    LinearLayout ll7;
    LinearLayout ll8;
    LinearLayout ll9;

    ImageView imm4;
    ImageView imm5;
    ImageView imm6;
    ImageView imm7;
    ImageView imm8;
    ImageView imm9;
    ImageView imm10;
    ImageView imm11;
    ImageView imm12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Intent intent=getIntent(); // l'intent di questa activity
        String pkg = getPackageName();

        sw = intent.getBooleanExtra(pkg + ".Bool", false);
        s1=intent.getStringExtra(pkg + ".Str1");
        s2=intent.getStringExtra(pkg + ".Str2");

        ply1=(TextView) findViewById(R.id.textView6);
        ply2=(TextView) findViewById(R.id.textView7);

        t=(TextView) findViewById(R.id.textView5);
        final_t = (TextView) findViewById(R.id.textView8);
        final_t.setVisibility(View.GONE);

        vic_ply1=(TextView) findViewById(R.id.textView9);
        vic_ply2=(TextView) findViewById(R.id.textView10);

        b3=(Button) findViewById(R.id.button3);
        b4=(Button) findViewById(R.id.button4);
        b3.setEnabled(false);
        b4.setEnabled(false);

        ply1.setText(s1);
        ply2.setText(s2);
        ply1.setTextColor(Color.YELLOW);
        ply2.setTextColor(Color.WHITE);

        imm2=(ImageView) findViewById(R.id.imageView2);
        imm3=(ImageView) findViewById(R.id.imageView3);
        imm3.setRotation(180);      // rotare imageView3 di 180 gradi
        imm3.setVisibility(View.INVISIBLE);     // rende invisibile imm lasciano lo spazios

        init_mtx(mtx);

        ll1=(LinearLayout) findViewById(R.id.llv_1);
        ll2=(LinearLayout) findViewById(R.id.llv_2);
        ll3=(LinearLayout) findViewById(R.id.llv_3);
        ll4=(LinearLayout) findViewById(R.id.llv_4);
        ll5=(LinearLayout) findViewById(R.id.llv_5);
        ll6=(LinearLayout) findViewById(R.id.llv_6);
        ll7=(LinearLayout) findViewById(R.id.llv_7);
        ll8=(LinearLayout) findViewById(R.id.llv_8);
        ll9=(LinearLayout) findViewById(R.id.llv_9);


        imm4=(ImageView) findViewById(R.id.imageView4);
        imm5=(ImageView) findViewById(R.id.imageView5);
        imm6=(ImageView) findViewById(R.id.imageView6);
        imm7=(ImageView) findViewById(R.id.imageView7);
        imm8=(ImageView) findViewById(R.id.imageView8);
        imm9=(ImageView) findViewById(R.id.imageView9);
        imm10=(ImageView) findViewById(R.id.imageView10);
        imm11=(ImageView) findViewById(R.id.imageView11);
        imm12=(ImageView) findViewById(R.id.imageView12);

        t.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                reset();
                return false;
            }
        });

        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll1.setEnabled(false);

                set_turn(0, 0, imm4);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll2.setEnabled(false);

                set_turn(0, 1, imm5);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll3.setEnabled(false);

                set_turn(0, 2, imm6);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll4.setEnabled(false);

                set_turn(1, 0, imm7);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll5.setEnabled(false);

                set_turn(1, 1, imm8);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll6.setEnabled(false);

                set_turn(1, 2, imm9);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll7.setEnabled(false);

                set_turn(2, 0, imm10);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll8.setEnabled(false);

                set_turn(2, 1, imm11);

                if (turn >= 5)
                    fn(is_winner());
            }
        });

        ll9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll9.setEnabled(false);

                set_turn(2, 2, imm12);

                if (turn >= 5)
                    fn(is_winner());
            }
        });
    }

    public void fn(char k) {
        if (turn < 9) {
            if (k == 'X') {
                winner(k);
            } else if (k == 'O') {
                winner(k);
            }
        } else {
            winner(k);
        }
    }

    public char is_winner() {
        char k=colonne(mtx);
        if(k==' '){
            k=righe(mtx);
            if(k==' '){
                k=diagonale(mtx);
                if(k==' '){
                    k=antidiagonale(mtx);
                    return k;
                } else {
                    return k;
                }
            } else{
                return k;
            }
        } else{
            return k;
        }
    }

    public char ctrl(int x, int o) {
        if(x==3)
            return 'X';
        else if(o==3)
            return 'O';
        else
            return ' ';
    }

    public char colonne(char[][] m){
        int x=0, o=0;
        for (int i = 0; i < r; ++i){
            for (int j = 0; j < c; ++j){
                if (m[j][i] != ' '){
                    if(m[j][i] == 'X')
                        ++x;
                    else
                        ++o;
                }
            }
            if(x==3 || o==3)
                break;
            x=0; o=0;
        }
        return ctrl(x, o);
    }

    public char righe(char[][] m){
        int x = 0, o = 0;
        for (int i = 0; i < r; ++i){
            for (int j = 0; j < c; ++j){
                if (m[i][j] != ' '){
                    if(m[i][j] == 'X')
                        ++x;
                    else
                        ++o;
                }
            }
            if(x==3 || o==3)
                break;
            x=0; o=0;
        }
        return ctrl(x, o);
    }

    public char diagonale(char[][] m){
        int x=0, o=0;
        for(int i=0; i<r; ++i){
            if(m[i][i]!=' '){
                if(m[i][i]=='X')
                    ++x;
                else
                    ++o;
            }
        }
        return ctrl(x, o);
    }

    public char antidiagonale(char[][] m){
        int x=0, o=0;
        for(int i=0, j=2; i<r; ++i, --j){
            if(m[i][j]!=' ')
                if(m[i][j]=='X')
                    ++x;
                else
                    ++o;
        }
        return ctrl(x, o);
    }

    public void init_mtx(char m[][]){
        for (int i = 0; i < r; ++i){
            for(int j = 0; j < c; ++j){
                m[i][j] = ' ';
            }
        }
    }

    public void winner(char w) {
        imm2.setVisibility(View.GONE);
        imm3.setVisibility(View.GONE);
        ply1.setVisibility(View.GONE);
        ply2.setVisibility(View.GONE);
        t.setVisibility(View.GONE);
        final_t.setTextColor(Color.YELLOW);
        final_t.setTextSize(30);

        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);

        if(final_t.getAnimation() != null){
            final_t.getAnimation().cancel();
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                final_t.setAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                /*final_t.clearAnimation();*/
            }
        });

        if (w=='X'){
            final_t.setText(String.format(getResources().getString(R.string.winner), s1));
            final_t.setVisibility(View.VISIBLE);
            final_t.startAnimation(animation);

            ++v_ply1;
            if(v_ply1 > 0 || v_ply2 > 0) {
                vic_ply1.setVisibility(View.VISIBLE);
                vic_ply2.setVisibility(View.VISIBLE);
                vic_ply1.setText(String.format("%d", v_ply1));
                vic_ply2.setText(String.format("%d", v_ply2));
            }
            if (v_ply1 > v_ply2) {
                vic_ply1.setTextColor(Color.YELLOW);
                vic_ply1.setTextSize(35);
                vic_ply2.setTextColor(Color.WHITE);
                vic_ply2.setTextSize(25);
            }
            if (v_ply1 == v_ply2) {
                vic_ply1.setTextColor(Color.WHITE);
                vic_ply2.setTextColor(Color.WHITE);
                vic_ply1.setTextSize(30);
                vic_ply2.setTextSize(30);
            }
            if(v_ply1 < v_ply2) {
                vic_ply1.setTextColor(Color.WHITE);
                vic_ply2.setTextColor(Color.YELLOW);
                vic_ply1.setTextSize(25);
                vic_ply2.setTextSize(35);
            }
        } else if (w=='O'){
            final_t.setText(String.format(getResources().getString(R.string.winner), s2));
            final_t.setVisibility(View.VISIBLE);
            final_t.startAnimation(animation);

            ++v_ply2;
            if(v_ply1 > 0 || v_ply2 > 0) {
                vic_ply1.setVisibility(View.VISIBLE);
                vic_ply2.setVisibility(View.VISIBLE);
                vic_ply1.setText(String.format("%d", v_ply1));
                vic_ply2.setText(String.format("%d", v_ply2));
            }
            if (v_ply1 > v_ply2) {
                vic_ply1.setTextColor(Color.YELLOW);
                vic_ply1.setTextSize(35);
                vic_ply2.setTextColor(Color.WHITE);
                vic_ply2.setTextSize(25);
            }
            if (v_ply1 == v_ply2) {
                vic_ply1.setTextColor(Color.WHITE);
                vic_ply2.setTextColor(Color.WHITE);
                vic_ply1.setTextSize(30);
                vic_ply2.setTextSize(30);
            }
            if(v_ply1 < v_ply2) {
                vic_ply1.setTextColor(Color.WHITE);
                vic_ply2.setTextColor(Color.YELLOW);
                vic_ply1.setTextSize(25);
                vic_ply2.setTextSize(35);
            }
        } else {
            final_t.setText(getResources().getString(R.string.no_win));
            final_t.setVisibility(View.VISIBLE);
            final_t.startAnimation(animation);
        }

        ll1.setEnabled(false);
        ll2.setEnabled(false);
        ll3.setEnabled(false);
        ll4.setEnabled(false);
        ll5.setEnabled(false);
        ll6.setEnabled(false);
        ll7.setEnabled(false);
        ll8.setEnabled(false);
        ll9.setEnabled(false);

        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }

    public void reset(){
        init_mtx(mtx);

        imm4.clearAnimation();
        imm4.setVisibility(View.GONE);
        imm5.clearAnimation();
        imm5.setVisibility(View.GONE);
        imm6.clearAnimation();
        imm6.setVisibility(View.GONE);
        imm7.clearAnimation();
        imm7.setVisibility(View.GONE);
        imm8.clearAnimation();
        imm8.setVisibility(View.GONE);
        imm9.clearAnimation();
        imm9.setVisibility(View.GONE);
        imm10.clearAnimation();
        imm10.setVisibility(View.GONE);
        imm10.clearAnimation();
        imm11.setVisibility(View.GONE);
        imm11.clearAnimation();
        imm12.setVisibility(View.GONE);
        imm12.clearAnimation();

        ll1.setEnabled(true);
        ll2.setEnabled(true);
        ll3.setEnabled(true);
        ll4.setEnabled(true);
        ll5.setEnabled(true);
        ll6.setEnabled(true);
        ll7.setEnabled(true);
        ll8.setEnabled(true);
        ll9.setEnabled(true);

        turn = 0;
        t.setVisibility(View.VISIBLE);

        final_t.clearAnimation();
        final_t.setVisibility(View.GONE);
        imm2.setVisibility(View.VISIBLE);
        imm3.setVisibility(View.INVISIBLE);
        ply1.setTextColor(Color.YELLOW);
        ply2.setTextColor(Color.WHITE);
        ply1.setVisibility(View.VISIBLE);
        ply2.setVisibility(View.VISIBLE);

        b3.setVisibility(View.GONE);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b4.setVisibility(View.GONE);
    }

    public void best(){
        String d=getString(R.string.dio);
        Toast T= Toast.makeText(getApplicationContext(), d, Toast.LENGTH_SHORT);

        DisplayMetrics metrics;
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int h = metrics.heightPixels;
        final int w = metrics.widthPixels;

        T.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, ran.nextInt(w/2), ran.nextInt(h/4));
        T.show();
    }

    public void set_turn(int row, int col, final ImageView imm) {
        /* setta un'animazione sulla comparsa dell'immagine*/
        if(imm.getAnimation() != null){
            imm.getAnimation().cancel();
        }
                /* AGGIUNGERE "final" prima della variabile animation oppure ridichiarare "Animation animation=..." prima di usarla nel metodo*/
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                imm.setAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                /*imm.clearAnimation();*/
            }
        });

        if(sw)
            best();

        if(turn%2==0){
            imm2.setVisibility(View.INVISIBLE);
            ply2.setTextColor(Color.YELLOW);
            ply1.setTextColor(Color.WHITE);
            imm3.setVisibility(View.VISIBLE);

            /*imm.setAnimation(animation);*/
            imm.setImageResource(R.mipmap.ic_launcher);
            imm.startAnimation(animation);

            mtx[row][col] = 'X';
        } else {
            imm3.setVisibility(View.INVISIBLE);
            ply2.setTextColor(Color.WHITE);
            ply1.setTextColor(Color.YELLOW);
            imm2.setVisibility(View.VISIBLE);

            imm.setImageResource(R.mipmap.ic_launcher_pig);
            imm.startAnimation(animation);

            mtx[row][col] = 'O';
        }

        ++turn;
    }
}
