package com.example.dell_pc.funtasticquiz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dell_pc.funtasticquiz.Start.hardqcount;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.dell_pc.funtasticquiz.Start.aNo;
import static com.example.dell_pc.funtasticquiz.Start.extremescore;
import static com.example.dell_pc.funtasticquiz.Start.hardscore;
import static com.example.dell_pc.funtasticquiz.Start.hintcount;
import static com.example.dell_pc.funtasticquiz.Start.level;
import static com.example.dell_pc.funtasticquiz.Start.mscore;
import static com.example.dell_pc.funtasticquiz.Start.qN;
import static com.example.dell_pc.funtasticquiz.Start.simplescore;
import static com.example.dell_pc.funtasticquiz.Start.timeleft;

public class FinalResult extends AppCompatActivity {

    TextView lvlStatus, curentScore, highScore, leveltext;
    Button noplevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        loadScore(); //ager score load kora or
        initialize();
        genraterandom();
        setstatus(); //status set kora
        setScore();
        curentScore.setText("" + mscore);
        highscore();
        String s = lvlStatus.getText().toString();
        mscore = 0;
        qN = 0;
        hintcount = 0;
        //normal logic section
        if (s.equals("Level failed")) {
            noplevel.setText("Try Again");
        } else {
            if (level == 1) {
                noplevel.setText("Level 2");
            } else if (level == 2) {
                noplevel.setText("Levle 3");
            } else noplevel.setText("Try Again");
        }
    }

    //status set kora j level finish oisay tar lagia
    private void setstatus() {
        String s = "";
        if (level == 1) {
            if (simplescore >= 750) {
                s = "Level already passed";
            } else if (mscore >= 750) {
                s = "Level passed";
            } else s = "Level failed";

        } else if (level == 2) {
            if (hardscore >= 800) {
                s = "Level already passed";
            } else if (mscore >= 800) {
                s = "Level passed";
            } else s = "Level failed";

        } else {
            if (extremescore >= 800) s = "Level already passed";
            else if (hardqcount < 2 && mscore >= 800)
                s = "Congrats!! You have successfully passed this level";
            else {
                hardqcount = 0;
                s = "Level failed";
            }
        }
        lvlStatus.setText(s);
    }

    //highsccore set kora or logey kunlevel or scoretable ekta okhtaou dekhni or
    private void highscore() {
        int high = 0;
        if (level == 1) {
            leveltext.setText("Level 1");
            high = Math.max(simplescore, mscore); //prev.. score or logey currect tr compare koria neya or max ta
        } else if (level == 2) {
            leveltext.setText("Level 2");
            high = Math.max(hardscore, mscore);
        } else if (level == 3) {
            leveltext.setText("Level 3");
            high = Math.max(extremescore, mscore);
        }
        highScore.setText("" + high);
    }

    //update score save kora
    private void setScore() {
        if (level == 1) {
            if (simplescore < mscore) //max score save kora or
                simplescore = mscore;
        } else if (level == 2) {
            if (hardscore < mscore)
                hardscore = mscore;
        } else {
            if (extremescore < mscore)
                extremescore = mscore;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("simple", simplescore);
        editor.putInt("hard", hardscore);
        editor.putInt("extreme", extremescore);
        editor.commit();
        loadScore();
    }

    private void loadScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("Score", Context.MODE_PRIVATE);
        simplescore = sharedPreferences.getInt("simple", 0);
        hardscore = sharedPreferences.getInt("hard", 0);
        extremescore = sharedPreferences.getInt("extreme", 0);
    }

    private void initialize() {
        curentScore = findViewById(R.id.result_show);
        lvlStatus = findViewById(R.id.level_status);
        highScore = findViewById(R.id.highscore);
        leveltext = findViewById(R.id.result_text);
        noplevel = findViewById(R.id.noplevel);
    }

    //abar random generate kora or karon user ekan taki new kunu level othoba same level ou jaita faroin
    private void genraterandom() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 20; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i = 0; i < 20; i++) {
            aNo[i] = list.get(i);
        }
    }

    public void returnn(View view) {
        Intent intent = new Intent(FinalResult.this, Start.class);
        startActivity(intent);
        finish();
    }

    private void maintainLayout() { //layout manager
        if (aNo[qN] == 2 || aNo[qN] == 6 || aNo[qN] == 12 || aNo[qN] == 18) showLayout(2);
        else if (aNo[qN] == 4 || aNo[qN] == 14) showLayout(3);
        else if (aNo[qN] == 8) showLayout(4);
        else showLayout(1);
    }

    private void showLayout(int i) {  //move to respective layout
        if (i == 1) {
            Intent intent = new Intent(FinalResult.this, Simple.class);
            startActivity(intent);
            finish();
        } else if (i == 2) {
            Intent intent = new Intent(FinalResult.this, IImage.class);
            startActivity(intent);
            finish();
        } else if (i == 3) {
            Intent intent = new Intent(FinalResult.this, SSound.class);
            startActivity(intent);
            finish();
        } else {
            // Log.e("vd ", "oy");
            Intent intent = new Intent(FinalResult.this, VVideo.class);
            startActivity(intent);
            finish();
        }
    }

    //button jekhtat try again or next kunu level o obatin oilay ou level or num thakbo egu dia hono jawa jaibo
    public void noplevel(View view) {
        String finalS = noplevel.getText().toString().trim();
        if (finalS.equals("Try Again")) {  //try again tinta level ei thaktey farey so tinta level chk kora oy
            if (level == 1) {
                timeleft = 0 * 60 * 1000;  //abar time set kora
                level = 1;     //abar level set kora
            } else if (level == 2) {
                timeleft = 5 * 60 * 1000;
                level = 2;
            } else {
                timeleft = 4 * 60 * 1000;
                level = 3;
            }
            maintainLayout();
        } else if (level == 1) {  //try agian nay maney level passed jodi level 1 pass oia thakey tailey level 2 set kora
            timeleft = 5 * 60 * 1000;
            level = 2;
            maintainLayout();
        } else if (level == 2) {     //try agian nay maney level passed jodi level 2 pass oia thakey tailey level 3 set kora
            timeleft = 4 * 60 * 1000;
            level = 3;
            boolean b = chkwarningextreme();   //start class o j reason deoa same
            if (b == false) {
                setwarningextreme();
                showwarningextreme();
            } else {

                maintainLayout();
            }
        }
    }

    private void showwarningextreme() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FinalResult.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.warning, (ViewGroup) findViewById(R.id.warninglayout));
        TextView t = view.findViewById(R.id.warningtext2);
        String s = "Consiqutively wrong answered of question lead to quiz over.\nSo be carefull\nGood luck";
        t.setText(s);
        builder.setView(view);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                level = 3;
                timeleft = 4 * 60 * 1000;
                maintainLayout();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean chkwarningextreme() {
        SharedPreferences sharedPreferences = getSharedPreferences("warning", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("extreme", "jesata");
        if (s.equals("dekha oigese")) return true;
        else return false;
    }

    private void setwarningextreme() {
        SharedPreferences sharedPreferences = getSharedPreferences("warning", Context.MODE_PRIVATE); //warning namey table create kora
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("extreme", "dekha oigese");  //extreme er under a dekha oigese string rakha or
        editor.commit();
    }
}
