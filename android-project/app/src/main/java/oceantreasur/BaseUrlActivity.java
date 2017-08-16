package oceantreasur;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import oceantreasur.es.R;

/**
 * Created by Dean Panayotov on 8/16/2017
 */

public class BaseUrlActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_url);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        OceanTreasuresApplication.rebuildRetrofitIntstance(((EditText)findViewById(R.id.base_url)).getText().toString());
        finish();
    }
}
