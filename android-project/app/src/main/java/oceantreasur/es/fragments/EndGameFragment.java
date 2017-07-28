package oceantreasur.es.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oceantreasur.es.R;
import oceantreasur.es.view.CustomButton;

import static oceantreasur.es.fragments.FragmentConstants.MAIN_FRAGMENT;

/**
 * Created by Student on 7/28/2017.
 */

public class EndGameFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_end_game, container, false);

        CustomButton playAgainButton = (CustomButton) view.findViewById(R.id.btn_play_again);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }
}
