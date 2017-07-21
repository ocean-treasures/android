package oceantreasur.es;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.ViewUtils;

import static oceantreasur.es.R.layout.fish;

public class EndGameActivity extends BaseActivity {

    private int MAX_FISH = 5;
    private int MAX_DURATION = 14000;
    private int MIN_DURATION = 8000;
    private int MIN_TIME_OFFSET = 100;
    private int MAX_TIME_OFFSET = 500;
    private ImageView image;
    private CustomButton button;
    private ArrayList<View> fishArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        this.image = (ImageView) findViewById(R.id.iv_end_game);
        this.button = (CustomButton) findViewById(R.id.btn_play_again);

        if(image != null) {
            Glide.with(OceanTreasuresApplication.getStaticContext())
                    .load(R.drawable.treasure)
                    .centerCrop()
                    .into(image);
        }
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                   getBackToPlayActivity();
            }
        });

        if (ViewUtils.isTablet(this)) {
            RelativeLayout mainlayout = (RelativeLayout) findViewById(R.id.rl_end);
            ImageView logo = (ImageView) findViewById(R.id.iv_logo_end);
            ImageView chest = (ImageView) findViewById(R.id.chest_end);

            LayoutInflater Li = LayoutInflater.from(getApplicationContext());

            for(int i = 0; i < MAX_FISH; i++) {
                fishArr.add(Li.inflate(fish,null));
                mainlayout.addView(fishArr.get(i));
            }

            moveAnimation();
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private int choseRandomYPosition() {
        int height = getScreenHeight();
        Random rand = new Random();

        int yPosition = rand.nextInt(height) + 1;

        if(yPosition < height * (5/100)) {
            yPosition += 80;
        } else {
            if(yPosition > height * (95/100)) {
                yPosition -= 20;
            }
        }

        return yPosition;
    }

    private void moveAnimation() {

        for(int i = 0; i < fishArr.size(); i++) {
            setupAnimation(fishArr.get(i), generateRandom(MIN_DURATION, MAX_DURATION));
        }
    }

    private void setupAnimation(final View fish, final int durationTime) {
        int width = getScreenWidth();
        int height = choseRandomYPosition();

        Animation anim = new TranslateAnimation(-300, width + 400, height, height);
        anim.setDuration(durationTime);
        anim.setStartOffset(generateRandom(MIN_TIME_OFFSET, MAX_TIME_OFFSET));
        anim.setFillAfter(true);


        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                setupAnimation(fish, durationTime);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fish.startAnimation(anim);
    }

    private int generateRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }

    private void getBackToPlayActivity() {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
