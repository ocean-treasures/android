package oceantreasur.es.android_project;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CorrectAnswerActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);

        this.image = (ImageView) findViewById(R.id.iv_correct);

//        image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//
//                ViewGroup.LayoutParams params =  image.getLayoutParams();
//
//                params.height = params.width;
//
//                image.setLayoutParams(params);
//            }
//        });
    }


}
