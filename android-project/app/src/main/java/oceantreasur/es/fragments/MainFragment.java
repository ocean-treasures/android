package oceantreasur.es.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oceantreasur.es.R;
import oceantreasur.es.view.CustomButton;

import static oceantreasur.es.fragments.FragmentConstants.GAME_FRAGMENT;

/**
 * Created by Student on 7/28/2017.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        CustomButton playButton = (CustomButton) view.findViewById(R.id.btn_start);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((ActivityMain)getActivity()).setViewAdapter(GAME_FRAGMENT);
            }
        });

        return view;
    }
}
