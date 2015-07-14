package com.ban.incl.instantclass.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ban.incl.instantclass.R;
import com.ban.incl.instantclass.adapter.AllListAdapter;
import com.ban.incl.instantclass.adapter.MyAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class INClListFragment extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    public static INClListFragment newInstance(String param1, String param2) {
        INClListFragment fragment = new INClListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public INClListFragment() {
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        ListView lv = (ListView)getActivity().findViewById(R.id.list);
//        MyAdapter adapter = new MyAdapter(getActivity());
//        lv.setAdapter(adapter);

        // TODO: Change Adapter to display your content
//        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
//                R.layout.list_item_test, R.id.txt_title, DummyContent.ITEMS));

//        ListView lv = (ListView)getActivity().findViewById(R.id.list);
//        MyAdapter adapter = new MyAdapter(getActivity());
//        lv.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView lv = null;

        if(mParam1 == "1") {
            lv = getListView();
            MyAdapter adapter = new MyAdapter(getActivity());
            lv.setAdapter(adapter);
        } else if(mParam1 == "2") {
            lv = getListView();
            AllListAdapter adapter = new AllListAdapter(getActivity());
            lv.setAdapter(adapter);
        }
    }

    /*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            Log.d("inclTest >> ", ""+l.getId());

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ClassDetailFragment.newInstance("2", "2"))
                    .commit();

            Toast.makeText(getActivity(), position + " Click", Toast.LENGTH_SHORT).show();
        }
    }
*/
}
