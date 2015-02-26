package hueller.jhonny.tortoisesecurity.view;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import hueller.jhonny.tortoisesecurity.R;

import hueller.jhonny.tortoisesecurity.controll.FragmentCommunicator;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link hueller.jhonny.tortoisesecurity.controll.FragmentCommunicator}
 * interface.
 */
public class NewsFragment extends Fragment implements ListView.OnItemClickListener {

    private FragmentCommunicator myCommunicator;
    private String tag="TortoiseSecurity";

    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(tag,"inizio oncreate newsFragment");
        super.onCreate(savedInstanceState);
        String[] newsTitle=getResources().getStringArray(R.array.NewsTitle);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,newsTitle);
        Log.i(tag,"fine oncreate newsFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(tag,"inizio onCreateView NewsFragment");
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Set the adapter
        mListView = (ListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        Log.i(tag,"fine onCreateView NewsFragment");
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(tag,"inizio onAttach NewsFragment");
        try {
            myCommunicator = (FragmentCommunicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        Log.i(tag,"fine onAttach NewsFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myCommunicator = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != myCommunicator) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            myCommunicator.clickChooseNews(position);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }
}
