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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Start extends AppCompatActivity {
    Button simple, hard, extreme;
    public static int qN = 0, level = 0, mscore = 0, simplescore = 0,
            hintcount = 0, hardscore = 0, extremescore = 0, hardqcount = 0;
    public static long timeleft;
    public static int[] aNo = new int[20];

//    qN oisay question gonar lagi
//    level dia level set kora oy
//    hintcount dia  koybar hint use oisay gona oy
//      hardqcount dia gona oy extreme ekhloge koyta wrong korsay okhta gona or
//  jatey ekhlogey duibar wrong oilay quiz over oijay

//    mscore oisay current score user or
//    r simplescore,hardscore,extreme score dia agor score gula rakha or sharedpref.. takia
//    jatey next level egunto user kita able ni run kortey
    //static use kora oisay karon shob class o eigula use kora jaibo bar declare kora lagto nay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        hardqcount = 0;
        mscore = 0;
        level = 0;
        qN = 0;
        hintcount = 0;
        initialize();
        StartAnimations();
        genraterandom();
        loadScore();     //agey taki score rakha oy jatey hard r extreme or lagi user kita capable ni dekkhar lagi
    }

    //setwarning()
    //hint or details user ekhbar deki lailay okhta dia note koria rakha or

    private void setwarning() {
        SharedPreferences sharedPreferences = getSharedPreferences("warning", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("warn", "dekha oigese");
        editor.commit();
    }

    //chkwarning()
    //eno dekha or hint or detail ala dialogue box kita agey dekhani oigese ni
    private boolean chkwarning() {
        SharedPreferences sharedPreferences = getSharedPreferences("warning", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("warn", "jesata");
        if (s.equals("dekha oigese")) return true;
        else return false;
    }

    //normal initialize
    private void initialize() {
        simple = findViewById(R.id.btnsimple);
        hard = findViewById(R.id.btnhard);
        extreme = findViewById(R.id.btnextreme);
    }

    //Buttons method declared in start.xml under individual button->onClick
    public void simple(View view) {
        boolean b = chkwarning();  //chkk kora or hint warning agey dekhani oisay ni
        if (b == false) {
            setwarning();   //csave kora or hint warning jen dekhani oiges
            showwarning();   //show kora or hint warning
        } else {
            level = 1;
            timeleft = 0;
            maintainLayout();
        }
    }

    public void hard(View view) {
        if (simplescore >= 750) {
            level = 2;
            timeleft = 5 * 60 * 1000;
            maintainLayout();
        } else showDialogue(1);
    }

    public void extreme(View view) {
        if (hardscore >= 800) {
            boolean b = chkwarningextreme(); //chkk kora or exreme level or duibar wrong or warning agey dekhani oisay ni
            if (b == false) {
                setwarningextreme();
                showwarningextreme();
            } else {
                level = 3;
                timeleft = 4 * 60 * 1000;
                maintainLayout();
            }
        } else showDialogue(2);
    }

    private void showwarningextreme() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
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

    // ekhloge duibar wrong kora jaito nay ou dialoge box dekhani oigese ni chk korar lagi
    private boolean chkwarningextreme() {
        SharedPreferences sharedPreferences = getSharedPreferences("warning", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("extreme", "jesta");
        if (s.equals("dekha oigese")) return true;
        else return false;
    }

    // ekhloge duibar wrong kora jaito nay ou dialoge box dekhani jen oiges okhta note koria rakha or
    private void setwarningextreme() {
        SharedPreferences sharedPreferences = getSharedPreferences("warning", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("extreme", "dekha oigese");
        editor.commit();
    }


    //randomly qustion set korar lagi random index generate kora or
    private void genraterandom() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 20; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i = 0; i < 20; i++) {
            aNo[i] = list.get(i);
            Log.e("rand", (i + 1) + " " + aNo[i]);
        }
    }

    //respective layout or lagi ready kora oy
    private void maintainLayout() { //layout manager
        if (aNo[qN] == 2 || aNo[qN] == 6 || aNo[qN] == 12 || aNo[qN] == 18) showLayout(2);
        else if (aNo[qN] == 4 || aNo[qN] == 14) showLayout(3);
        else if (aNo[qN] == 8) showLayout(4);
        else showLayout(1);
    }

    //respective layout o fata ni oy
    private void showLayout(int i) {  //move to respective layout
        if (i == 1) {
            Intent intent = new Intent(Start.this, Simple.class);
            startActivity(intent);
            finish();
        } else if (i == 2) {
            Intent intent = new Intent(Start.this, IImage.class);
            startActivity(intent);
            finish();
        } else if (i == 3) {
            Intent intent = new Intent(Start.this, SSound.class);
            startActivity(intent);
            finish();
        } else {
            // Log.e("vd ", "oy");
            Intent intent = new Intent(Start.this, VVideo.class);
            startActivity(intent);
            finish();
        }
    }

    //button or animation set kora oy
    private void StartAnimations() {


       /* LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        */

        Animation animleft = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation slideup = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation slidedown = AnimationUtils.loadAnimation(this, R.anim.slidedown);

        slidedown.reset();
        slideup.reset();
        animleft.reset();

        simple.clearAnimation();
        simple.startAnimation(slideup);


        extreme.clearAnimation();
        extreme.startAnimation(animleft);
        hard.clearAnimation();
        hard.startAnimation(slidedown);

//        driver.clearAnimation();
//        driver.startAnimation(animfromtop);


    }

    //previous score ogun load kora oy
    private void loadScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("Score", Context.MODE_PRIVATE);
        simplescore = sharedPreferences.getInt("simple", 0);
        hardscore = sharedPreferences.getInt("hard", 0);
        extremescore = sharedPreferences.getInt("extreme", 0);
    }


    //user or capabilityr ufrey warning message deya oy hard r extreme level or lagi. 1 oisay hard r 2 oisay extreme
    private void showDialogue(int a) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.showlocklevel, (ViewGroup) findViewById(R.id.locklevellayout));
        TextView t = view.findViewById(R.id.locktext);
        String s = "";
        if (a == 1) s = "Locked!!\nEarn minimum 750 at Simple level to unlock";
        else
            s = "Locked!!\nEarn minimum 800 at Hard level to unlock";
        t.setText(s);
        builder.setView(view);
        builder.setPositiveButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //details of hint show kora oy
    private void showwarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.warning, (ViewGroup) findViewById(R.id.warninglayout));
        TextView t = view.findViewById(R.id.warningtext2);
        String s = "1.Hint will be available only when your score is minimum 20 .\n\n" +
                "2.You can use hint only 3 times.\n\n" +
                "3.Hint will reduce your score by 20";
        t.setText(s);
        builder.setView(view);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                level = 1;
                timeleft = 0;
                maintainLayout();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
