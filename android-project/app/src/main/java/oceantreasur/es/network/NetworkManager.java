package oceantreasur.es.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import oceantreasur.OceanTreasuresApplication;
import oceantreasur.es.R;
import oceantreasur.es.network.model.CheckAnswerRequest;
import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.util.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dean Panayotov on 8/15/2017
 */

public class NetworkManager {

    private static final String TAG = "NETWORK";

    public static void getNextWord(final SuccessListener<NextWordResponse> successListener, final Activity activity) {

        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();
        call.enqueue(getNetworkCallback(successListener, new FailureListener() {
            @Override
            public void onFailure() {
                getNextWord(successListener, activity);
            }
        }, activity));
    }

    public static void checkAnswer(final int wordId, final int picId, final SuccessListener<CheckAnswerResponse> successListener, final Activity activity) {
        CheckAnswerRequest request = new CheckAnswerRequest(wordId, picId);
        Call<CheckAnswerResponse> call = OceanTreasuresApplication.getApi().checkAnswer(request);
        call.enqueue(getNetworkCallback(successListener, new FailureListener(){
            @Override
            public void onFailure() {
                checkAnswer(wordId, picId, successListener, activity);
            }
        }, activity));
    }

    public static interface NetworkFailureListener {
        public void onFailure();
        public void onFailureThrowable(Throwable t);
        public void onFailureErrorCode(int code);
        public void onFailureMessage(String message);
    }

    public static class DefaultFailureListener implements NetworkFailureListener {

        private FailureListener failureListener;
        private Activity activity;

        public DefaultFailureListener(FailureListener failureListener, Activity activity){
            this.failureListener = failureListener;
            this.activity = activity;
        }


        //TODO
        //((MainActivity) getActivity()).attachFragment(new StartGameFragment());

        public void onFailure(){
            AlertDialog dialog = DialogUtil.getNeutralImageAlertDialog(R.string.neutral_button_text, R.drawable.fish, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    failureListener.onFailure();
                }
            }, activity);
            dialog.show();
        }

        @Override
        public void onFailureThrowable(Throwable t) {
            onFailureMessage(t.getMessage());
            t.printStackTrace();
        }

        @Override
        public void onFailureErrorCode(int code) {
            Log.e(TAG, "ERROR CODE: " + code);
        }

        @Override
        public void onFailureMessage(String message) {
            Log.e(TAG, "ERROR: " + message);
        }
    }

    public static interface SuccessListener<T> {
        public void onSuccess(T response);
    }

    public static interface FailureListener {
        public void onFailure();
    }

        private static <T> Callback<T> getNetworkCallback(final SuccessListener<T> successListener, final FailureListener failureListener, final Activity activity){
        return new Callback<T>() {

            DefaultFailureListener defaultFailureListener = new DefaultFailureListener(failureListener, activity);

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.code() == 200) {
                    successListener.onSuccess(response.body());
                }else{
                    defaultFailureListener.onFailureErrorCode(response.code());
                    defaultFailureListener.onFailureMessage(response.message());
                    defaultFailureListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                defaultFailureListener.onFailureThrowable(t);
                defaultFailureListener.onFailure();
            }
        };
    }
}
