package oceantreasur.es.android_project;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class OceanTreasuresApplication extends Application {
    private static Context applicationContext;
    private static OceanTreasuresApplication instance;
    private static Retrofit retrofit;
    private static MockRetrofit mockRetrofit;
    private static OceanTreasuresAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = getApplicationContext();



        createRetrofitInstance();
        FontManager.init(getAssets());
    }

    private void createRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
//                .addNetworkInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//
//                request = request.newBuilder()
//                        .header("Content-Type", "application/json")
//                        .build();
//
//                return chain.proceed(request);
//            }
//        })
            .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(OceanTreasuresConstants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();

        if (OceanTreasuresConstants.IS_MOCK) {
            NetworkBehavior behavior = NetworkBehavior.create();

            mockRetrofit = new MockRetrofit.Builder(retrofit)
                    .networkBehavior(behavior)
                    .build();

            api = new MockOceanTreasuresAPI(mockRetrofit.create(OceanTreasuresAPI.class));
        }
        else {
            api = retrofit.create(OceanTreasuresAPI.class);
        }
    }

    public static Context getStaticContext() {
        return applicationContext;
    }

    public static OceanTreasuresApplication getInstance() {
        return instance;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static MockRetrofit getMockRetrofit() {
        return mockRetrofit;
    }

    public static OceanTreasuresAPI getApi() {
        return api;
    }
}
