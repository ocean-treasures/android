package oceantreasur.es.android_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class EndGameActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        this.image = (ImageView) findViewById(R.id.iv_end_game);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(R.drawable.treasure)
                .centerCrop()
                .into(image);
    }
}
