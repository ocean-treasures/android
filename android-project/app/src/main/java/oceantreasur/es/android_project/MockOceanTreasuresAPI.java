package oceantreasur.es.android_project;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.mock.BehaviorDelegate;

public class MockOceanTreasuresAPI implements OceanTreasuresAPI {

    private final BehaviorDelegate<OceanTreasuresAPI> delegate;

    public MockOceanTreasuresAPI(BehaviorDelegate<OceanTreasuresAPI> service) {
        this.delegate = service;
    }

    @Override
    public Call<NextWordResponse> getNextWord() {
        NextWordResponse nextWordResponse = new NextWordResponse();
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        AssetManager assetManager;
        InputStreamReader inputStreamReader = null;

        try {
            assetManager = OceanTreasuresApplication.getInstance().getApplicationContext().getAssets();
            inputStreamReader = new InputStreamReader(assetManager.open("json/sample_data.json"));

            reader = new BufferedReader(inputStreamReader);

            String line;

            while((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Gson gson = new Gson();
        nextWordResponse = gson.fromJson(result.toString(), NextWordResponse.class);

        return delegate.returningResponse(nextWordResponse).getNextWord();
    }

    @Override
    public Call<CheckAnswerResponse> checkAnswer(@Body CheckAnswerRequest body) {
        return null;
    }
}
