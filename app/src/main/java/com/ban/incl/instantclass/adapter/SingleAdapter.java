package com.ban.incl.instantclass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ban.incl.instantclass.R;
import com.ban.incl.instantclass.util.phpDown;
import com.ban.incl.instantclass.vo.ClassVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    ArrayList<ClassVO> list = new ArrayList<ClassVO>();

    String returnVal;

    //생성자를 만든다.
    public SingleAdapter(Context context) {

        phpDown task = new phpDown();

        try {
            task.setMode("SELECT");
            returnVal = task.execute("getData.php").get();
            phpFinish(returnVal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void phpFinish(String str) {
        ClassVO vo;
        try{
            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);

                vo = new ClassVO();
                vo.setClassId(jo.getInt("class_id"));
                vo.setTitle(jo.getString("title"));
                vo.setContent(jo.getString("content"));
                vo.setStartTime(jo.getString("start_time"));
                vo.setEndTime(jo.getString("end_time"));
                vo.setAddr(jo.getString("addr"));
                vo.setPrice(jo.getString("price"));

                list.add(vo);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        if(convertView==null){
            view = inflater.inflate(R.layout.list_item, parent, false);
        }else{
            view = convertView;
        }

        return view;
    }
}
