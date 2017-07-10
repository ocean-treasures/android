package oceantreasur.es.android_project.network;

import oceantreasur.es.android_project.network.model.CheckAnswerRequest;
import oceantreasur.es.android_project.network.model.CheckAnswerResponse;
import oceantreasur.es.android_project.network.model.NextWordResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OceanTreasuresAPI {

    @GET("next_word")
    Call<NextWordResponse> getNextWord();

    @POST("check_answer/")
    Call<CheckAnswerResponse> checkAnswer(@Body CheckAnswerRequest body);

}
