package com.ban.incl.instantclass;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ban.incl.instantclass.adapter.SingleAdapter;

public class SingleListFragment extends Fragment {

    public SingleListFragment() {

    }

    public static SingleListFragment newInstance() {
        SingleListFragment fragment = new SingleListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_list, container, false);

        ListView list = (ListView)view.findViewById(R.id.singleList);

        SingleAdapter adapter = new SingleAdapter(view.getContext());
        list.setAdapter(adapter);

        return view;
    }


}
