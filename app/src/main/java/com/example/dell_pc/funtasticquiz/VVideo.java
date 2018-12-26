package com.example.dell_pc.funtasticquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import static com.example.dell_pc.funtasticquiz.Start.aNo;
import static com.example.dell_pc.funtasticquiz.Start.hardqcount;
import static com.example.dell_pc.funtasticquiz.Start.hintcount;
import static com.example.dell_pc.funtasticquiz.Start.level;
import static com.example.dell_pc.funtasticquiz.Start.mscore;
import static com.example.dell_pc.funtasticquiz.Start.qN;
import static com.example.dell_pc.funtasticquiz.Start.timeleft;

public class VVideo extends AppCompatActivity {
    Intermediary inter = new Intermediary();
    private String mAnswer;
    private VideoView vvdio;
    private TextView vdScoreView;
    private TextView vdQuestionView,timertext;
    private TextView vdCountq;
    private Button vdButtonChoice1;
    private Button vdButtonChoice2;
    private Button vdButtonChoice3;
    private Button vdButtonChoice4;
    private Button vdButtonHint;
    private long video = 0;
    MediaController mediaController;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vvideo);
        mediaController = new MediaController(this);
        initializevd();
        if(level!=1){
            timertext.setVisibility(View.VISIBLE);
        }
        maintainLayout();
        vd_updateScore(0);
    }

    private void maintainLayout() {
        if (qN < 20) {
            if (aNo[qN] == 2 || aNo[qN] == 6 || aNo[qN] == 12 || aNo[qN] == 18) showLayout(2);
            else if (aNo[qN] == 4 || aNo[qN] == 14) showLayout(3);
            else if (aNo[qN] == 8) showLayout(4);
            else showLayout(1);
        } else {

            Intent intent = new Intent(VVideo.this, FinalResult.class);
            startActivity(intent);
            finish();

        }
    }

    private void showLayout(int i) {
        if (i == 1) {
            Intent intent = new Intent(VVideo.this, Simple.class);
            startActivity(intent);
            finish();
        } else if (i == 2) {
            Intent intent = new Intent(VVideo.this, IImage.class);
            startActivity(intent);
            finish();

        } else if (i == 3) {
            Intent intent = new Intent(VVideo.this, SSound.class);
            startActivity(intent);
            finish();

        } else {
            if(level!=1)showTime();

            Log.e("vd ", "oy");
            StartAnimations();
            playVideo();
        }
    }

    private void playVideo() {
        String path = "android.resource://" + getPackageName() + "/" + video;
        Uri uri = Uri.parse(path);
        vvdio.setVideoURI(uri);
        vvdio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                showBtn();
            }
        });
        vvdio.setMediaController(mediaController);
        mediaController.setAnchorView(vvdio);
        vvdio.start();
    }

    private void initializevd() {   //vd=video layout
        vdScoreView = findViewById(R.id.vd_score);
        vdQuestionView = findViewById(R.id.vd_question);

        vdButtonChoice1 = findViewById(R.id.vd_btn_one);
        vdButtonChoice2 = findViewById(R.id.vd_btn_two);
        vdButtonChoice3 = findViewById(R.id.vd_btn_three);
        vdButtonChoice4 = findViewById(R.id.vd_btn_four);
        vdButtonHint = findViewById(R.id.btn_hint);
        vvdio = findViewById(R.id.videoView2);
        timertext=findViewById(R.id.vd_timertext);

        if (qN < 20)
            video = inter.getIVS(level, aNo[qN]);
        vdCountq = findViewById(R.id.vd_countq);

    }


    private void updateQuestionvd() {
        vdQuestionView.setText(inter.getQuestion(level, aNo[qN]));
        vdButtonChoice1.setText(inter.getOp1(level, aNo[qN]));
        vdButtonChoice2.setText(inter.getOp2(level, aNo[qN]));
        vdButtonChoice3.setText(inter.getOp3(level, aNo[qN]));
        vdButtonChoice4.setText(inter.getOp4(level, aNo[qN]));
        mAnswer = inter.getCorrectAns(level, aNo[qN]).toLowerCase();
        chkVideo();
    }

    private void vd_updateScore(int y) {
        vdScoreView.setText("" + mscore);
        if (y == 0)
            vdCountq.setText((qN + 1) + "/20");

    }

    //buttons for choice
    public void btnone(View view) {
        String s = vdButtonChoice1.getText().toString().toLowerCase().trim();
        chkVideo();
        compareAns(s);
    }

    public void btntwo(View view) {
        String s = vdButtonChoice2.getText().toString().toLowerCase().trim();
        chkVideo();
        compareAns(s);
    }

    public void btnthree(View view) {
        String s = vdButtonChoice3.getText().toString().toLowerCase().trim();
        chkVideo();
        compareAns(s);
    }

    public void btnfour(View view) {
        String s = vdButtonChoice4.getText().toString().toLowerCase().trim();
        chkVideo();
        compareAns(s);
    }
    //End buttons

    private void chkVideo() {
        if (vvdio.isPlaying()) {
            vvdio.stopPlayback();
        }
    }

    public void compareAns(String s) {
        if (s.equals(mAnswer)) {
            if(level==3)hardqcount=0;
            showToast(1);
            mscore += 50;
            vd_updateScore(1);
            updateQnumb();
            if(level!=1)countDownTimer.cancel();
            maintainLayout();
        } else {
            if(level==3)++hardqcount;
            showToast(2);
            if(hardqcount>=2&&level==3){
                countDownTimer.cancel();

                Toast.makeText(this, "Consiqutively wrong answered!!Be carefull next time", Toast. LENGTH_LONG).show();
                Intent intent=new Intent(VVideo.this,FinalResult.class);
                startActivity(intent);
                finish();
            }
            else{
                vd_updateScore(1);
                updateQnumb();
                if(level!=1)countDownTimer.cancel();
                maintainLayout();
            }
        }

    }

    public void showBtn() {
        vdQuestionView.setVisibility(View.VISIBLE);
        vdButtonChoice1.setVisibility(View.VISIBLE);
        vdButtonChoice2.setVisibility(View.VISIBLE);
        vdButtonChoice3.setVisibility(View.VISIBLE);
        vdButtonChoice4.setVisibility(View.VISIBLE);
        vdButtonHint.setVisibility(View.VISIBLE);

        updateQuestionvd();
    }

    private void updateQnumb() {
        qN++;
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation animleft = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation animright = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation animfromtop = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation slideup = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation slidedown = AnimationUtils.loadAnimation(this, R.anim.slidedown);
        slidedown.reset();
        slideup.reset();
        anim.reset();
        animleft.reset();
        animright.reset();
        animfromtop.reset();

       /* LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        */

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        animleft = AnimationUtils.loadAnimation(this, R.anim.leftside);
        animright = AnimationUtils.loadAnimation(this, R.anim.rightside);
        animfromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        anim.reset();
        animleft.reset();
        animright.reset();

        vdButtonChoice1.clearAnimation();
        vdButtonChoice1.startAnimation(slideup);


        vdButtonChoice2.clearAnimation();
        vdButtonChoice2.startAnimation(animleft);
        vdButtonChoice3.clearAnimation();
        vdButtonChoice3.startAnimation(animright);

        vdButtonChoice4.clearAnimation();
        vdButtonChoice4.startAnimation(slidedown);
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
    public void vdHint(View view) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(VVideo.this);
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
        mscore-=20;
        vd_updateScore(1);
        ++hintcount;
        AlertDialog.Builder builder = new AlertDialog.Builder(VVideo.this);
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
                Toast.makeText(VVideo.this,"Times Upp!!Do hurry next time",Toast.LENGTH_LONG).show();
                countDownTimer.cancel();
                Intent intent = new Intent(VVideo.this, FinalResult.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
