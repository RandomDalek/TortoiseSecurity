package hueller.jhonny.tortoisesecurity.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hueller.jhonny.tortoisesecurity.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailNewsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private String title;
    private String text;
    private TextView titleTextView;
    private TextView textTextView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @param text Parameter 2.
     * @return A new instance of fragment DetailNewsFragment.
     */

    public static DetailNewsFragment newInstance(String title, String text) {
        DetailNewsFragment fragment = new DetailNewsFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            text = getArguments().getString(TEXT);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleTextView= (TextView) getActivity().findViewById(R.id.textViewTitle);
        textTextView= (TextView) getActivity().findViewById(R.id.textViewText);
        titleTextView.setText(title);
        textTextView.setText(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_news, container, false);
    }


}
