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

/**
 * Created by ots on 2015-04-11.
 */

public class AllListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String returnVal;

    ArrayList<ClassVO> list = new ArrayList<ClassVO>();

    //생성자를 만든다.
    public AllListAdapter(Context context) {

        phpDown task = new phpDown();

        try {
            task.setMode("SELECT");
            returnVal = task.execute("getData.php").get();
            phpFinish(returnVal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //getSystemService은 Activity클래스의 메서드이다.
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

    //아래의 재정의된 모든 메서드를 호출하는 주체는 ListView이다.
    //즉 ListView는 아래의 메서드를 통해 데이터 정보를 반영한다.
    //리스트뷰에게 리스트의 개수를 몇개로 구성해야하는지를 알게해준다.(호출자:ListView)
    public int getCount() {
        return list.size();
    }

    //리스트뷰에게 지정한 postion 변수에 해당하는 순서의 데이터를 추출할 수 있도록 해준다.
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    //리스트뷰는 getView 메서드를 getCount()반환값 만큼 호출하여, 각 item을 구성하게 된다.
    //이 예제의 경우 데이터가 13건이기 때문에 인플레이션 13번 일으켜 그 결과를 return해준다.
    public View getView(int position, View convertView, ViewGroup parent) {
        //getView메서드의 특징
        //스마트폰에 아이템이 화면에 보여지는 시점에 화면구성을 위해 호출된다.
        //따라서 내가 보여줄 데이터가 100건이라도 화면에 보여질 리스트의 개수가 13건이면
        //getView호출은 12번을 먼저 호출한 후, 화면에 가려졌다가 다시 보이게 되는 아이템이 발생하면
        //다시 getView가 발생한다.
        //따라서 한 아이템당 한번만 인플레이션이 이루어지도록 조건문을 넣어줘야 한다.
        View view=null;
        if(convertView==null){
            view = inflater.inflate(R.layout.list_item_test, parent, false);
        }else{
            view = convertView;
        }

        RelativeLayout layout = (RelativeLayout)view;

        //레이아웃 객체는 자신이 포함하고 있는 위젯들을 id로 검색할수 있다.
        TextView txt_title = (TextView)layout.findViewById(R.id.txt_title);
        TextView txt_content = (TextView)layout.findViewById(R.id.txt_content);
        TextView txt_start_time = (TextView)layout.findViewById(R.id.txt_start_time);
        TextView txt_end_time = (TextView)layout.findViewById(R.id.txt_end_time);
        TextView txt_date = (TextView)layout.findViewById(R.id.txt_date);
        TextView txt_place = (TextView)layout.findViewById(R.id.txt_place);

        txt_title.setText(list.get(position).getTitle());
//        txt_content.setText(list.get(position).getContent());
        txt_start_time.setText(list.get(position).getStartTime());
        txt_end_time.setText(list.get(position).getEndTime());
        txt_date.setText(list.get(position).getDate());
        txt_place.setText(list.get(position).getPlace());



        return layout;
    }

}
