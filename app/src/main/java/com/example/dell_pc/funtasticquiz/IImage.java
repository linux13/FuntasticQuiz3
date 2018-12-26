package com.example.dell_pc.funtasticquiz;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import static com.example.dell_pc.funtasticquiz.Start.aNo;
import static com.example.dell_pc.funtasticquiz.Start.hardqcount;
import static com.example.dell_pc.funtasticquiz.Start.hintcount;
import static com.example.dell_pc.funtasticquiz.Start.level;
import static com.example.dell_pc.funtasticquiz.Start.mscore;
import static com.example.dell_pc.funtasticquiz.Start.qN;
import static com.example.dell_pc.funtasticquiz.Start.timeleft;

public class IImage extends AppCompatActivity {
    Intermediary inter = new Intermediary();
    private ImageView iimage;
    private TextView imgScoreView;
    private TextView imgQuestionView,timertext;
    private TextView imgCountq;
    private Button imgButtonChoice1;
    private Button imgButtonChoice2;
    private Button imgButtonChoice3;
    private Button imgButtonChoice4;
    private Button imgButtonHint;
    private String mAnswer;
    private int id = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iimage);
        initializeimg();
        if(level!=1){
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
            Intent intent = new Intent(IImage.this, FinalResult.class);
            startActivity(intent);
            finish();

        }
    }

    private void showLayout(int i) {
        if (i == 1) {
            Intent intent = new Intent(IImage.this, Simple.class);
            startActivity(intent);
            finish();

        } else if (i == 2) {
            if(level!=1)showTime();
            id = (int) inter.getIVS(level, aNo[qN]);
            img_updateScore(0);
            updateQuestionimg();
            StartAnimations();
        } else if (i == 3) {
            Intent intent = new Intent(IImage.this, SSound.class);
            startActivity(intent);
            finish();

        } else {
            Log.e("vd ", "oy");
            Intent intent = new Intent(IImage.this, VVideo.class);
            startActivity(intent);
            finish();
        }
    }

    private void initializeimg() { //img=image layout
        imgScoreView = findViewById(R.id.img_score);
        imgQuestionView = findViewById(R.id.img_question);
        imgButtonChoice1 = findViewById(R.id.img_btn_one);
        imgButtonChoice2 = findViewById(R.id.img_btn_two);
        imgButtonChoice3 = findViewById(R.id.img_btn_three);
        imgButtonChoice4 = findViewById(R.id.img_btn_four);
        imgButtonHint = findViewById(R.id.btn_hint);
        iimage = findViewById(R.id.imageView2);
        imgCountq = findViewById(R.id.img_countq);
        timertext=findViewById(R.id.img_timertext);

    }

    private void updateQuestionimg() {

        imgQuestionView.setText(inter.getQuestion(level, aNo[qN]));
        imgButtonChoice1.setText(inter.getOp1(level, aNo[qN]));
        imgButtonChoice2.setText(inter.getOp2(level, aNo[qN]));
        imgButtonChoice3.setText(inter.getOp3(level, aNo[qN]));
        imgButtonChoice4.setText(inter.getOp4(level, aNo[qN]));

        iimage.setImageResource(id);
        mAnswer = inter.getCorrectAns(level, aNo[qN]).toLowerCase();
    }

    private void img_updateScore(int y) {
        imgScoreView.setText("" + mscore);
        if (y == 0)
            imgCountq.setText((qN + 1) + "/20");

    }

    public void btnone(View view) {
        String s = imgButtonChoice1.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void btntwo(View view) {
        String s = imgButtonChoice2.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void btnthree(View view) {
        String s = imgButtonChoice3.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void btnfour(View view) {
        String s = imgButtonChoice4.getText().toString().toLowerCase().trim();
        compareAns(s);
    }

    public void compareAns(String s) {
        if (s.equals(mAnswer)) {
            if(level==3)hardqcount=0;
            showToast(1);
            mscore += 50;
            img_updateScore(1);
            updateQnumb();
            if(level!=1)countDownTimer.cancel();
            maintainLayout();
        } else {
            if(level==3)++hardqcount;
            showToast(2);
            if(hardqcount>=2&&level==3){
                countDownTimer.cancel();

                Toast.makeText(this, "Consiqutively wrong answered!!Be carefull next time", Toast. LENGTH_LONG).show();
                Intent intent=new Intent(IImage.this,FinalResult.class);
                startActivity(intent);
                finish();
            }
            else{
                img_updateScore(1);
                updateQnumb();
                if(level!=1)countDownTimer.cancel();
                maintainLayout();
            }
        }


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

        imgButtonChoice1.clearAnimation();
        imgButtonChoice1.startAnimation(slideup);


        imgButtonChoice2.clearAnimation();
        imgButtonChoice2.startAnimation(slideup);
        imgButtonChoice3.clearAnimation();
        imgButtonChoice3.startAnimation(slidedown);

        imgButtonChoice4.clearAnimation();
        imgButtonChoice4.startAnimation(slidedown);

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

    public void imgHint(View view) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(IImage.this);
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
        img_updateScore(1);
        ++hintcount;
        AlertDialog.Builder builder = new AlertDialog.Builder(IImage.this);
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
                Toast.makeText(IImage.this,"Times Upp!!Do hurry next time",Toast.LENGTH_LONG).show();
                countDownTimer.cancel();
                Intent intent = new Intent(IImage.this, FinalResult.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
