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

public class StartGameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        CustomButton playButton = (CustomButton) view.findViewById(R.id.btn_start);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragment gameFragment = new GameFragment();
                ((MainActivity) getActivity()).attachFragment(gameFragment, FragmentTags.GAME_FRAGMENT_TAG);
            }
        });

        return view;
    }
}
