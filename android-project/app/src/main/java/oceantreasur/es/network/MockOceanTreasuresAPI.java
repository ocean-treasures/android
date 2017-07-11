package oceantreasur.es.network;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

import oceantreasur.es.network.model.CheckAnswerRequest;
import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.network.model.Progress;
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
        NextWordResponse nextWordResponse;
        String result;

        String[] jsonFiles = {"json/next_word_response1.json",
                              "json/next_word_response2.json",
                              "json/next_word_response3.json",
                              "json/next_word_response4.json",
                              "json/next_word_response5.json"};

        Random random = new Random();
        int temp = random.nextInt(5);
        result = parseData(jsonFiles[temp]);
        Log.d("RND", temp + "");

        Gson gson = new Gson();
        nextWordResponse = gson.fromJson(result, NextWordResponse.class);

        return delegate.returningResponse(nextWordResponse).getNextWord();
    }

    @Override
    public Call<CheckAnswerResponse> checkAnswer(@Body CheckAnswerRequest body) {
        CheckAnswerResponse checkAnswerResponse = new CheckAnswerResponse();

        HashMap<Integer, String> data = new HashMap<>();

        data.put(1, "apple");
        data.put(2, "pear");
        data.put(3, "banana");
        data.put(4, "watermelon");
        data.put(6, "avocado");
        data.put(7, "apricot");
        data.put(8, "cherry");
        data.put(9, "grapefruit");
        data.put(10, "strawberry");
        data.put(11, "mango");
        data.put(12, "lemon");
        data.put(13, "orange");
        data.put(14, "pineapple");
        data.put(15, "plum");
        data.put(16, "melon");
        data.put(17, "raspberry");
        data.put(18, "coconut");
        data.put(19, "blueberry");
        data.put(20, "kiwi");
        data.put(21, "pomegranate");

        Random random = new Random();

//        String result;
//
//        result = parseData("json/check_word_response.json");
//
//        Gson gson = new Gson();
//        checkAnswerResponse = gson.fromJson(result, CheckAnswerResponse.class);

        checkAnswerResponse.setCorrect(body.getPicId() == body.getWordId());
        checkAnswerResponse.setWord(data.get(body.getPicId()));
        checkAnswerResponse.setProgress(new Progress(10, 10));

        return delegate.returningResponse(checkAnswerResponse).checkAnswer(body);
    }

    public String parseData(String jsonPath) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        AssetManager assetManager;
        InputStreamReader inputStreamReader = null;

        try {
            assetManager = OceanTreasuresApplication.getInstance().getApplicationContext().getAssets();
            inputStreamReader = new InputStreamReader(assetManager.open(jsonPath));

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
        return result.toString();
    }
}
