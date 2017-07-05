package oceantreasur.es.android_project;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CustomClient {

    @GET("people/{id}")
    Call<CustomPOJO> getPerson(@Path("id") int id);

//    @POST("check_answer/{word_id}")
//    Call<CheckAnswerResponse> checkAnswer(@Path("word_id") int id, @Body CheckAnswerRequest body);

}
