package pingala.com.navigationdrawer1.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pingala.com.navigationdrawer1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveTab extends Fragment {


    public LiveTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_tab, container, false);
    }

}
