package daffodilfestivalva.org.daffodilfestival2016;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {




    public Schedule() {
        // Required empty public constructor
    }

    // This is called when this fragment gets ATTACHED to an activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // This is called when the activity to which this fragment is attached is created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_saturday, container, false);
    }

    // Instance of the current activity's onCreate (this override is not needed)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // This is called when the activity to which this fragment is attached is destroyed.
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // This is called when this fragment gets DETACHED to an activity.
    @Override
    public void onDetach() {
        super.onDetach();
    }
}