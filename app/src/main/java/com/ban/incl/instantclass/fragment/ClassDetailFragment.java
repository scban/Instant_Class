package com.ban.incl.instantclass.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ban.incl.instantclass.R;
import com.ban.incl.instantclass.util.phpDown;
import com.ban.incl.instantclass.vo.ClassVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassDetailFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassDetailFragment newInstance(String param1, String param2) {
        ClassDetailFragment fragment = new ClassDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ClassDetailFragment() {
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
        View view = inflater.inflate(R.layout.fragment_class_detail, container, false);

        phpDown task = new phpDown();

        try {
            ClassVO vo = new ClassVO();
            vo.setClassId(2);

            task.setMode("DETAIL");
            task.setInsertItem(vo);

            String sVo = task.execute("getDataDetail.php").get();

            try{
                JSONObject root = new JSONObject(sVo);
                JSONArray ja = root.getJSONArray("results");
                JSONObject jo = ja.getJSONObject(0);

                EditText title      = (EditText)view.findViewById(R.id.edtUpTitle);
                EditText date       = (EditText)view.findViewById(R.id.edtUpDate);
                EditText startTime  = (EditText)view.findViewById(R.id.edtUpStartTime);
                EditText endTime    = (EditText)view.findViewById(R.id.edtUpEndTime);
                EditText content    = (EditText)view.findViewById(R.id.edtUpContent);
                EditText place      = (EditText)view.findViewById(R.id.edtUpPlace);

                Log.d("inclTest", "GetDetail >> title : " + title.getText());

                title.setText(jo.getString("title"));
                date.setText(jo.getString("date"));
                startTime.setText(jo.getString("startTime"));
                endTime.setText(jo.getString("endTime"));
                content.setText(jo.getString("content"));
                place.setText(jo.getString("place"));

            }catch(JSONException e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


//        Button btnAddClass = (Button)view.findViewById(R.id.btn_update_class);
//        Button btnDeleteAll = (Button)view.findViewById(R.id.btn_del_class);
//
//        btnAddClass.setOnClickListener(this);
//        btnDeleteAll.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_class:
                ClassVO vo = new ClassVO();

                EditText title      = (EditText)getView().findViewById(R.id.edtUpTitle);
                EditText date       = (EditText)getView().findViewById(R.id.edtUpDate);
                EditText startTime  = (EditText)getView().findViewById(R.id.edtUpStartTime);
                EditText endTime    = (EditText)getView().findViewById(R.id.edtUpEndTime);
                EditText content   = (EditText)getView().findViewById(R.id.edtUpContent);
                EditText place      = (EditText)getView().findViewById(R.id.edtUpPlace);

                vo.setTitle(title.getText().toString());
                vo.setDate(date.getText().toString());
                vo.setStartTime(startTime.getText().toString());
                vo.setEndTime(endTime.getText().toString());
                vo.setContent(content.getText().toString());
                vo.setPlace(place.getText().toString());

                phpDown task = new phpDown();



                Toast.makeText(getActivity(), "Update", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_del_class:
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
        /*
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
