package com.ban.incl.instantclass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ban.incl.instantclass.R;

import java.util.ArrayList;

/**
 * Created by ots on 2015-04-11.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList lst = new ArrayList();
    ArrayList lstTime = new ArrayList();
    LayoutInflater inflater;

    //생성자를 만든다.
    public MyAdapter(Context context) {
        lst.add("목동");          lstTime.add("09:00 ~ 11:00");
        lst.add("천호");          lstTime.add("12:00 ~ 13:00");
        lst.add("신도림");        lstTime.add("15:00 ~ 17:00");
        lst.add("오리");          lstTime.add("17:00 ~ 18:00");
        lst.add("서현");          lstTime.add("20:00 ~ 21:00");
        lst.add("잠실");          lstTime.add("20:00 ~ 22:00");

        //getSystemService은 Activity클래스의 메서드이다.
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    //아래의 재정의된 모든 메서드를 호출하는 주체는 ListView이다.
    //즉 ListView는 아래의 메서드를 통해 데이터 정보를 반영한다.
    //리스트뷰에게 리스트의 개수를 몇개로 구성해야하는지를 알게해준다.(호출자:ListView)
    public int getCount() {
        return lst.size();
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

        txt_title.setText((String)lst.get(position));
        txt_content.setText((String)lstTime.get(position));

//        Log.i(this.getClass().getName(), "View의 주소값:"+view);

        return layout;
    }
}
