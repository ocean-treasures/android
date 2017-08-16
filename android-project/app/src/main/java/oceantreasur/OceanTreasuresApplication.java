package oceantreasur;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import oceantreasur.es.network.MockOceanTreasuresAPI;
import oceantreasur.es.network.OceanTreasuresAPI;
import oceantreasur.es.network.OceanTreasuresConstants;
import oceantreasur.es.view.FontManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class OceanTreasuresApplication extends Application {

    private static final String SHARED_PREFS_NAME = "OCEAN_TREASURES_SP";
    private static final String BASE_URL = "BASE_URL";

    private static Context applicationContext;
    private static OceanTreasuresApplication instance;
    private static Retrofit retrofit;
    private static MockRetrofit mockRetrofit;
    private static OceanTreasuresAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;
        applicationContext = getApplicationContext();

        createRetrofitInstance();
        FontManager.init(getAssets());
    }

    private static void createRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
            .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
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

    private static String getBaseUrl(){
        String baseUrl = null;
        SharedPreferences sharedPreferences = getStaticContext().getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        sharedPreferences.getString(BASE_URL, OceanTreasuresConstants.BASE_URL);
        return baseUrl;
    }

    private static void setBaseUrl(String baseUrl){
        SharedPreferences sharedPreferences = getStaticContext().getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putString(BASE_URL, baseUrl).apply();
    }

    public static void rebuildRetrofitIntstance(String baseUrl){
        if(baseUrl != null && baseUrl.length() > 0) {
            setBaseUrl(baseUrl);
            createRetrofitInstance();
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
