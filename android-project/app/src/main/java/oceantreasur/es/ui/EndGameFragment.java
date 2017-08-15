package oceantreasur.es.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oceantreasur.es.MainActivity;
import oceantreasur.es.R;
import oceantreasur.es.view.CustomButton;

public class EndGameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);

        CustomButton playAgainButton = (CustomButton) view.findViewById(R.id.btn_play_again);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).attachFragment(new StartGameFragment());
            }
        });

        return view;
    }
}
