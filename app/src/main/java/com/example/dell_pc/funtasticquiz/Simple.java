package com.example.dell_pc.funtasticquiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static com.example.dell_pc.funtasticquiz.Start.aNo;
import static com.example.dell_pc.funtasticquiz.Start.hardqcount;
import static com.example.dell_pc.funtasticquiz.Start.hintcount;
import static com.example.dell_pc.funtasticquiz.Start.level;
import static com.example.dell_pc.funtasticquiz.Start.mscore;
import static com.example.dell_pc.funtasticquiz.Start.qN;
import static com.example.dell_pc.funtasticquiz.Start.timeleft;

public class Simple extends AppCompatActivity {

    Intermediary inter = new Intermediary();

    private TextView smScoreView;
    private TextView smQuestionView;
    private TextView smCountq, timertext;
    private Button smButtonChoice1;
    private Button smButtonChoice2;
    private Button smButtonChoice3;
    private Button smButtonChoice4;
    private Button smButtonHint;

    private String mAnswer;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        initializesm();
        if (level != 1) {     //level 1 na oilay  timer text view show korbo
            timertext.setVisibility(View.VISIBLE);
        }
        maintainLayout();
    }

    private void maintainLayout() {
        if (qN < 20) {
            if (aNo[qN] == 2 || aNo[qN] == 6 || aNo[qN] == 12 || aNo[qN] == 18) showLayout(2);
            else if (aNo[qN] == 4 || aNo[qN] == 14) showLayout(3);
            else if (aNo[qN] == 8) showLayout(4);
            else showLayout(1);
        } else {
            Intent intent = new Intent(Simple.this, FinalResult.class);
            startActivity(intent);
            finish();

        }
    }

    private void showLayout(int i) {
        if (i == 1) {
            if (level != 1) showTime();
            sm_updateScore();
            updateQuestionsm();
            StartAnimations();

        } else if (i == 2) {
            Intent intent = new Intent(Simple.this, IImage.class);
            startActivity(intent);
            finish();

        } else if (i == 3) {
            Intent intent = new Intent(Simple.this, SSound.class);
            startActivity(intent);
            finish();

        } else {
            Intent intent = new Intent(Simple.this, VVideo.class);
            startActivity(intent);
            finish();
        }
    }


    private void initializesm() { //sm=simple layout
        smScoreView = findViewById(R.id.sm_score);
        smQuestionView = findViewById(R.id.sm_question);
        smButtonChoice1 = findViewById(R.id.sm_btn_one);
        smButtonChoice2 = findViewById(R.id.sm_btn_two);
        smButtonChoice3 = findViewById(R.id.sm_btn_three);
        smButtonChoice4 = findViewById(R.id.sm_btn_four);
        smButtonHint = findViewById(R.id.btn_hint);
        smCountq = findViewById(R.id.sm_countq);
        timertext = findViewById(R.id.sm_timertext);

    }

    //just question+choice+.. gula update kora
    private void updateQuestionsm() {

        smQuestionView.setText(inter.getQuestion(level, aNo[qN]));
        smButtonChoice1.setText(inter.getOp1(level, aNo[qN]));
        smButtonChoice2.setText(inter.getOp2(level, aNo[qN]));
        smButtonChoice3.setText(inter.getOp3(level, aNo[qN]));
        smButtonChoice4.setText(inter.getOp4(level, aNo[qN]));

        mAnswer = inter.getCorrectAns(level, aNo[qN]).toLowerCase();

    }

    //score text view update kora
    private void sm_updateScore() {
        smScoreView.setText("" + mscore);
        smCountq.setText((qN + 1) + "/20");
    }

    //choice button gular start
    public void btnone(View view) {
        String s = smButtonChoice1.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void btntwo(View view) {
        String s = smButtonChoice2.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void btnthree(View view) {
        String s = smButtonChoice3.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void btnfour(View view) {
        String s = smButtonChoice4.getText().toString().toLowerCase().trim();
        compareAns(s);
    }
    //choice button gular end

    //answer chek kora oy correct ki na
    public void compareAns(String s) {
        if (s.equals(mAnswer)) {
            if (level == 3)
                hardqcount = 0;  //level-3 normally duibar wrong kora jaito nay so protibar correct oilay hardcount=0 kora oy
            showToast(1);   //custom toast dekhani or 1 dia correcttakia jar okhta bujani
            mscore += 50;
            sm_updateScore();
            updateQnumb();
            if (level != 1) countDownTimer.cancel();
            maintainLayout();
        } else {
            if (level == 3)
                ++hardqcount;      //level-3 normally duibar wrong kora jaito nay erday wrong koraly gona or
            showToast(2);
            if (hardqcount >= 2 && level == 3) {      //levell-3 er lagi duibar wrong oigele quizover koria deya oijar

                countDownTimer.cancel();      //countdowntimer normally new thread o thakey so protibar layout switching or shomoy cancel kora lagey r nay
                //countdowntimer or bitror finish method call oijay automatic

                Toast.makeText(this, "Consiqutively wrong answered!!Be carefull next time", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Simple.this, FinalResult.class);
                startActivity(intent);
                finish();
            } else { //jodi level 3 nay othoba level 3 but duibar wrong ans ekhono oisay na er day just next qustion show kora or
                sm_updateScore();
                updateQnumb();
                if (level != 1) countDownTimer.cancel(); //same previous reason
                maintainLayout();
            }
        }

    }

    //question number update kora or
    private void updateQnumb() {
        qN++;
    }

    private void StartAnimations() {

       /* LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        */

        Animation animleft = AnimationUtils.loadAnimation(this, R.anim.leftside);
        Animation animright = AnimationUtils.loadAnimation(this, R.anim.rightside);

        animleft.reset();
        animright.reset();

        smButtonChoice1.clearAnimation();
        smButtonChoice1.startAnimation(animright);


        smButtonChoice2.clearAnimation();
        smButtonChoice2.startAnimation(animleft);
        smButtonChoice3.clearAnimation();
        smButtonChoice3.startAnimation(animright);

        smButtonChoice4.clearAnimation();
        smButtonChoice4.startAnimation(animleft);

    }

    //custom toasst dekhani oy 1 dia correct r 2 dia wrong or lagi
    private void showToast(int i) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout;
        if (i == 1) {
            layout = layoutInflater.inflate(R.layout.toastforcorrectans, (ViewGroup) findViewById(R.id.toastcorrect));

        } else
            layout = layoutInflater.inflate(R.layout.toastforworngans, (ViewGroup) findViewById(R.id.toastwrong));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    //hint button
    public void smHint(View view) {
        if (hintcount >= 3 || mscore < 20) {
            if (hintcount >= 3 && mscore < 20) {
                showhintDialog1(1);   //already used and score below 20 er lagi message dekhani
            } else if (hintcount >= 3) {
                showhintDialog1(2);   //already used er lagi message dekhani

            } else showhintDialog1(3);//score below 20 er lagi message dekhani

        } else {
            showhintDialog2(); //hint er lagi cpabale so hint dekhani or
        }
    }


    //hint message dekhani oy. 1 2 3 dia alada conditon or alada msg gula identify kora or
    private void showhintDialog1(int a) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.viewforhint2, (ViewGroup) findViewById(R.id.hintlayout2));
        TextView textView = view.findViewById(R.id.hinttext2);
        String s = "";

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Simple.this);
        if (a == 1)
            s = "Sorry you can't get hint\nYour score is below 20 and you have already used hint 3 times";
        else if (a == 2) s = "Sorry you can't get hint\nYour already have used hint 3 times";
        else s = "Sorry you can't get hint\nYour score is below 20";
        textView.setText(s);
        builder.setPositiveButton("Oukay", null);
        builder.setView(view);
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    //hint dekhani oy
    private void showhintDialog2() {
        Toast.makeText(this, "Score is reduced by 20", Toast.LENGTH_LONG).show();
        ++hintcount;  //hint koybar dekhani oisay gona or
        mscore -= 20;  //score khata or hint dekhsoin koria
        sm_updateScore();
        AlertDialog.Builder builder = new AlertDialog.Builder(Simple.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.viewforhints, (ViewGroup) findViewById(R.id.layouthint));
        TextView textView = view.findViewById(R.id.hinttext);
        textView.setText("" + inter.getHint(level, aNo[qN]));
        builder.setView(view);
        builder.setPositiveButton("Got it", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //timer set kora
    private void showTime() {

        countDownTimer = new CountDownTimer(timeleft, 1000) {


            @Override
            public void onTick(long l) {

                timeleft = l;
                int min = (int) timeleft / 60000;
                int sec = (int) timeleft % 60000 / 1000;
                String tt = "";
                tt += min;
                tt += ":";
                if (sec < 10) tt += "0";
                tt += sec;
                timertext.setText(tt);
            }

            @Override
            public void onFinish() {
                Toast.makeText(Simple.this, "Times Upp!!Do hurry next time", Toast.LENGTH_LONG).show();
                countDownTimer.cancel();
                Intent intent = new Intent(Simple.this, FinalResult.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
