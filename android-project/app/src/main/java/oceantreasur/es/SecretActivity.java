package oceantreasur.es;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import oceantreasur.es.view.BaseUrlActivity;

/**
 * Created by Dean Panayotov on 8/16/2017
 */

public class SecretActivity extends Activity {

    private static final int REQUEST_CODE = 303;

    private int sum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game);

        ((TextView)findViewById(R.id.tv_word)).setText("???");

        ((ImageView)findViewById(R.id.iv_1)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fish));
        ((ImageView)findViewById(R.id.iv_2)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fish));
        ((ImageView)findViewById(R.id.iv_3)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fish));
        ((ImageView)findViewById(R.id.iv_4)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fish));

        setOnClickListener(new int[] {1}, findViewById(R.id.iv_1));
        setOnClickListener(new int[] {4}, findViewById(R.id.iv_2));
        setOnClickListener(new int[] {0, 2}, findViewById(R.id.iv_3));
        setOnClickListener(new int[] {3, 5}, findViewById(R.id.iv_4));
    }

    private void setOnClickListener(final int[] values, View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementSum(values);
                moveToNextScreen();
            }
        });
    }

    private void incrementSum(int[] values){
        for(int val : values){
            if(sum == val){
                sum++;
                return;
            }
        }
        sum = 0;
    }

    private void moveToNextScreen(){
        if(sum == 6){
            startActivityForResult(new Intent(this, BaseUrlActivity.class), REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            finish();
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
