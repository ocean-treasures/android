package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Typeface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private static final int PROGRESS_MAX = 100;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private ImageView topLeft;
    private ImageView topRight;
    private ImageView bottomLeft;
    private ImageView bottomRight;

    private TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.word = (TextView) findViewById(R.id.tv_word);
        this.topLeft = (ImageView) findViewById(R.id.iv_1);
        this.topRight = (ImageView) findViewById(R.id.iv_2);
        this.bottomLeft = (ImageView) findViewById(R.id.iv_3);
        this.bottomRight = (ImageView) findViewById(R.id.iv_4);

        getResponse();

        // ------------------RecyclerView----------------------------------------

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, OceanTreasuresConstants.NUM_OF_PICS/2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerViewAdapter(this);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        //--------------------------------------------------------------------------
    }

    public void getResponse() {
        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();

        call.enqueue(new Callback<NextWordResponse>() {
            @Override
            public void onResponse(Call<NextWordResponse> call, Response<NextWordResponse> response) {
                NextWordResponse nextWord = response.body();

                ArrayList<Integer> positions = getRandomPositionsForPics();
                ArrayList<String> urlData = new ArrayList<String>();

                for(int i = 0; i < OceanTreasuresConstants.NUM_OF_PICS; i++) {
                    urlData.add(nextWord.getPictures()[positions.get(i)].getResolvedUrl());
                }

                adapter.setPictures(urlData);

                setupProgressBar(nextWord.getProgress().getCurrent(), nextWord.getProgress().getMax());
               // loadImages();

                //loadImages(nextWord);
                loadText(nextWord);


                Log.d("ZAX", nextWord.toString());
            }

            @Override
            public void onFailure(Call<NextWordResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }

    public void setupProgressBar(int cur, int max) {
        progressBar.setMax(max * 10); // 50
        progressBar.setProgress(cur * 10);
    }

    public void loadImages(NextWordResponse nextWord) {
        ArrayList<Integer> positions = getRandomPositionsForPics();

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[positions.get(0)].getResolvedUrl())
                .fitCenter()
                .into(topLeft);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[positions.get(1)].getResolvedUrl())
                .fitCenter()
                .into(topRight);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[positions.get(2)].getResolvedUrl())
                .fitCenter()
                .into(bottomLeft);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[positions.get(3)].getResolvedUrl())
                .fitCenter()
                .into(bottomRight);
    }

    public void loadText(NextWordResponse nextWord) {
        word.setText(nextWord.getWord().getWord().toString());
    }

    public void onClick(View v) {
      //  boolean choice = false;

    }

    private void ChooseNextActivity(boolean choice) {
        if(choice) {
            Intent intent = new Intent(GameActivity.this, CorrectAnswerActivity.class);
            startActivity(intent);
        }
        if(!choice) {
            Intent intent = new Intent(GameActivity.this, WrongAnswerActivity.class);
            startActivity(intent);
        }
    }

    private static ArrayList<Integer> getRandomPositionsForPics() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();

        while (numbers.size() < OceanTreasuresConstants.NUM_OF_PICS) {

            int random = randomGenerator .nextInt(OceanTreasuresConstants.NUM_OF_PICS);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }
}

