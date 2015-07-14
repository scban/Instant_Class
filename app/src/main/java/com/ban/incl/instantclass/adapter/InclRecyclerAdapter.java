package com.ban.incl.instantclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ban.incl.instantclass.R;
import com.ban.incl.instantclass.vo.ClassVO;

import java.util.ArrayList;

/**
 * Created by sc.ban on 2015.06.28
 */
public class InclRecyclerAdapter extends RecyclerView.Adapter<InclRecyclerAdapter.MyViewHolder> {

    private ArrayList<ClassVO> listClass;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate;
        TextView txtAddr;
        TextView txtStartTime;
        TextView txtPrice;

//        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            this.txtAddr = (TextView) itemView.findViewById(R.id.txtAddr);
            this.txtStartTime = (TextView) itemView.findViewById(R.id.txtStartTime);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

//            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.txtStartTime);
        }
    }

    public InclRecyclerAdapter(ArrayList<ClassVO> listClass) {
        this.listClass = listClass;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

//        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView txtDate = holder.txtDate;
        TextView txtAddr = holder.txtAddr;
        TextView txtStartTime = holder.txtStartTime;
        TextView txtPrice = holder.txtPrice;

//        ImageView imageView = holder.imageViewIcon;

        txtDate.setText(listClass.get(listPosition).getDate());
        txtAddr.setText(listClass.get(listPosition).getAddr());
        txtStartTime.setText(listClass.get(listPosition).getStartTime());
        txtPrice.setText(listClass.get(listPosition).getPrice());

//        imageView.setImageResource(listClass.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

}