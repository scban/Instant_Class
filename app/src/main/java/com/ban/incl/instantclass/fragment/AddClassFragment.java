package com.ban.incl.instantclass.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ban.incl.instantclass.R;
import com.ban.incl.instantclass.util.phpDown;
import com.ban.incl.instantclass.vo.ClassVO;


public class AddClassFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static AddClassFragment newInstance() {
        AddClassFragment fragment = new AddClassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AddClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_class, container, false);

        Button btnAddClass = (Button)view.findViewById(R.id.btn_add_class);

        btnAddClass.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_class:

                ClassVO vo = new ClassVO();

                EditText title      = (EditText)getView().findViewById(R.id.edtTitle);
                EditText date       = (EditText)getView().findViewById(R.id.edtDate);
                EditText startTime  = (EditText)getView().findViewById(R.id.edtStartTime);
                EditText endTime    = (EditText)getView().findViewById(R.id.edtEndTime);
                EditText content   = (EditText)getView().findViewById(R.id.edtContent);
                EditText place      = (EditText)getView().findViewById(R.id.edtPlace);

                vo.setTitle(title.getText().toString());
                vo.setDate(date.getText().toString());
                vo.setStartTime(startTime.getText().toString());
                vo.setEndTime(endTime.getText().toString());
                vo.setContent(content.getText().toString());
                vo.setPlace(place.getText().toString());

                title.setText("");
                date.setText("");
                startTime.setText("");
                endTime.setText("");
                content.setText("");
                place.setText("");


                phpDown task = new phpDown();

                try {
                    task.setMode("INSERT");
                    task.setInsertItem(vo);

                    task.execute("insertClass.php");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getActivity(), "Insert", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
