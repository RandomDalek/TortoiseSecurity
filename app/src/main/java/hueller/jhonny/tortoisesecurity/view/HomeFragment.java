package hueller.jhonny.tortoisesecurity.view;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import hueller.jhonny.tortoisesecurity.R;
import hueller.jhonny.tortoisesecurity.controll.FragmentCommunicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    FragmentCommunicator myCommunicator;
    Button watchNewsButton;
    Button watchCoursesButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        watchCoursesButton= (Button) getActivity().findViewById(R.id.buttonCourses);
        watchNewsButton= (Button) getActivity().findViewById(R.id.buttonNews);
        watchNewsButton.setOnClickListener(this);
        watchCoursesButton.setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myCommunicator= (FragmentCommunicator) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myCommunicator=null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonNews){
            myCommunicator.watchNews();
        }else {
            Toast myToast=Toast.makeText(getActivity(),"Ancora non implementato",Toast.LENGTH_LONG);
            myToast.show();
        }
    }
}
