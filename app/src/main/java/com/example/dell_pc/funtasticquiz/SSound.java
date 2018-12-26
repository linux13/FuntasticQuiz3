package com.example.dell_pc.funtasticquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dell_pc.funtasticquiz.Start.aNo;
import static com.example.dell_pc.funtasticquiz.Start.hardqcount;
import static com.example.dell_pc.funtasticquiz.Start.hardscore;
import static com.example.dell_pc.funtasticquiz.Start.hintcount;
import static com.example.dell_pc.funtasticquiz.Start.level;
import static com.example.dell_pc.funtasticquiz.Start.mscore;
import static com.example.dell_pc.funtasticquiz.Start.qN;
import static com.example.dell_pc.funtasticquiz.Start.timeleft;

public class SSound extends AppCompatActivity {
    Intermediary inter = new Intermediary();
    private TextView sndScoreView;
    private TextView sndQuestionView;
    private TextView sndCountq, timertext;
    private Button sndButtonChoice1;
    private Button sndButtonChoice2;
    private Button sndButtonChoice3;
    private Button sndButtonChoice4;
    private Button sndButtonHint;
    private Button play, pause, stop;
    private int sound = 0, crntPos = 0;
    private String mAnswer;
    MediaPlayer mediaPlayer;
    boolean bl = false, track = false, chk = false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssound);
        initializesnd();
        if (level != 1) {
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
            chkSound();
            Intent intent = new Intent(SSound.this, FinalResult.class);
            startActivity(intent);
            finish();

        }
    }

    private void showLayout(int i) {
        if (i == 1) {
            Intent intent = new Intent(SSound.this, Simple.class);
            startActivity(intent);
            finish();

        } else if (i == 2) {
            Intent intent = new Intent(SSound.this, IImage.class);
            startActivity(intent);
            finish();
        } else if (i == 3) {
            if (level != 1) showTime();
            sound = (int) inter.getIVS(level, aNo[qN]);  //sound or resource neoa or (int) dia type cast kora or long to int o karon media player o long suppor korey na
            hideBtn();
            snd_updateScore(0);
            playMusic();
            StartAnimations();
        } else {
            //Log.e("vd ","oy");
            Intent intent = new Intent(SSound.this, VVideo.class);
            startActivity(intent);
            finish();

        }
    }

    private void playMusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), sound);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                crntPos = 0;
                showBtn();
            }
        });

    }


    private void updateQuestionsnd() {

        sndQuestionView.setText(inter.getQuestion(level, aNo[qN]));
        sndButtonChoice1.setText(inter.getOp1(level, aNo[qN]));
        sndButtonChoice2.setText(inter.getOp2(level, aNo[qN]));
        sndButtonChoice3.setText(inter.getOp3(level, aNo[qN]));
        sndButtonChoice4.setText(inter.getOp4(level, aNo[qN]));
        mAnswer = inter.getCorrectAns(level, aNo[qN]).toLowerCase();
    }

    private void initializesnd() { //img=image layout
        sndScoreView = findViewById(R.id.snd_score);
        sndQuestionView = findViewById(R.id.snd_question);
        sndButtonChoice1 = findViewById(R.id.snd_btn_one);
        sndButtonChoice2 = findViewById(R.id.snd_btn_two);
        sndButtonChoice3 = findViewById(R.id.snd_btn_three);
        sndButtonChoice4 = findViewById(R.id.snd_btn_four);
        sndButtonHint = findViewById(R.id.btn_hint);
        sndCountq = findViewById(R.id.snd_countq);
        timertext = findViewById(R.id.snd_timertext);
    }

    private void snd_updateScore(int y) {
        sndScoreView.setText("" + mscore);
        if (y == 0)
            sndCountq.setText((qN + 1) + "/20");
    }

    //Button for choices start
    public void btnone(View view) {
        String s = sndButtonChoice1.getText().toString().toLowerCase().trim();
        chkSound();  //user answer dilaily sound r solar dorkar nay ou off kora oy
        compareAns(s);
    }

    public void btntwo(View view) {
        String s = sndButtonChoice2.getText().toString().toLowerCase().trim();
        chkSound();
        compareAns(s);
    }

    public void btnthree(View view) {
        String s = sndButtonChoice3.getText().toString().toLowerCase().trim();
        chkSound();
        compareAns(s);
    }

    public void btnfour(View view) {
        String s = sndButtonChoice4.getText().toString().toLowerCase().trim();
        chkSound();
        compareAns(s);
    }
    //Buttons end

    //Buttons for mPlayer start
    public void play(View view) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), sound);
            mediaPlayer.start();
            chk = false;
        } else if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(crntPos);
            mediaPlayer.start();
        }

    }

    public void pause(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            crntPos = mediaPlayer.getCurrentPosition();
        }

    }

    public void stop(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
            crntPos = 0;
            if (track == false) {  //jatey button gula ekhbar ou show oy track dia okhta dekha oisay.user a duibar play stop korta faroin erday chk kora
                showBtn();
                StartAnimations();
            }
            track = true;
            chk = true;
        }

    }

    //Buttons end
    public void compareAns(String s) {
        if (s.equals(mAnswer)) {
            if (level == 3) hardqcount = 0;
            showToast(1);
            mscore += 50;
            snd_updateScore(1);
            updateQnumb();
            if (level != 1) countDownTimer.cancel();
            maintainLayout();

        } else {
            if (level == 3) ++hardqcount;
            showToast(2);
            if (hardqcount >= 2 && level == 3) {
                countDownTimer.cancel();
                Toast.makeText(this, "Consiqutively wrong answered!!Be carefull next time", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SSound.this, FinalResult.class);
                startActivity(intent);
                finish();
            } else {
                snd_updateScore(1);
                updateQnumb();
                if (level != 1) countDownTimer.cancel();
                maintainLayout();
            }
        }


    }

    //check kora jen sound kita soler ni jodi running thakey tailey cancel kora or
    void chkSound() {
        if (chk == false) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer = null;
            }
        }
    }

    //button show kora
    public void showBtn() {
        sndQuestionView.setVisibility(View.VISIBLE);
        sndButtonChoice1.setVisibility(View.VISIBLE);
        sndButtonChoice2.setVisibility(View.VISIBLE);
        sndButtonChoice3.setVisibility(View.VISIBLE);
        sndButtonChoice4.setVisibility(View.VISIBLE);
        sndButtonHint.setVisibility(View.VISIBLE);
        updateQuestionsnd();
    }

    //button hide korar lagi
    public void hideBtn() {
        sndQuestionView.setVisibility(View.GONE);
        sndButtonChoice1.setVisibility(View.GONE);
        sndButtonChoice2.setVisibility(View.GONE);
        sndButtonChoice3.setVisibility(View.GONE);
        sndButtonChoice4.setVisibility(View.GONE);
        sndButtonHint.setVisibility(View.GONE);

    }

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
        Animation animfromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        animleft.reset();
        animright.reset();
        animfromtop.reset();

        sndButtonChoice1.clearAnimation();
        sndButtonChoice1.startAnimation(animfromtop);


        sndButtonChoice2.clearAnimation();
        sndButtonChoice2.startAnimation(animleft);
        sndButtonChoice3.clearAnimation();
        sndButtonChoice3.startAnimation(animright);

        sndButtonChoice4.clearAnimation();
        sndButtonChoice4.startAnimation(animfromtop);

    }

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

    public void sndHint(View view) {
        if (hintcount >= 3 || mscore < 20) {
            if (hintcount >= 3 && mscore < 20) {
                showhintDialog1(1);
            } else if (hintcount >= 3) {
                showhintDialog1(2);

            } else showhintDialog1(3);

        } else {
            showhintDialog2();
        }
    }


    private void showhintDialog1(int a) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.viewforhint2, (ViewGroup) findViewById(R.id.hintlayout2));
        TextView textView = view.findViewById(R.id.hinttext2);
        String s = "";

        AlertDialog.Builder builder = new AlertDialog.Builder(SSound.this);
        if (a == 1)
            s = "Sorry you can't get hint\nYour score is below 20 and you have already used hint 3 times";
        else if (a == 2) s = "Sorry you can't get hint\nYour already have used hint 3 times";
        else s = "Sorry you can't get hint\nYour score is below 20";
        textView.setText(s);
        builder.setPositiveButton("Oukay", null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void showhintDialog2() {
        Toast.makeText(this, "Score is reduced by 20", Toast.LENGTH_LONG).show();

        ++hintcount;
        mscore -= 20;
        snd_updateScore(1);
        AlertDialog.Builder builder = new AlertDialog.Builder(SSound.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.viewforhints, (ViewGroup) findViewById(R.id.layouthint));
        TextView textView = view.findViewById(R.id.hinttext);
        textView.setText("" + inter.getHint(level, aNo[qN]));
        builder.setView(view);
        builder.setPositiveButton("Got it", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

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
                // aBoolean=true;
                Toast.makeText(SSound.this, "Times Upp!!Do hurry next time", Toast.LENGTH_LONG).show();
                countDownTimer.cancel();
                Intent intent = new Intent(SSound.this, FinalResult.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

}
