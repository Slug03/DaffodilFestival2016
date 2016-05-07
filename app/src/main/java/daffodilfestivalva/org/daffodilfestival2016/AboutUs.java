package daffodilfestivalva.org.daffodilfestival2016;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import daffodilfestivalva.org.daffodilfestival2016.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {

    public AboutUs() {
        // Required empty public constructor
    }

    // This is called when this fragment gets ATTACHED to an activity.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    // Instance of the current activity's onCreate (this override is not needed)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button buttonOne = (Button) getView().findViewById(R.id.button);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                Uri uri = Uri.parse(getResources().getString(R.string.contact_us_url)); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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