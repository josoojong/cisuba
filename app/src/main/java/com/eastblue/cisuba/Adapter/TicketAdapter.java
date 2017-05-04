package com.eastblue.cisuba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eastblue.cisuba.Manager.NetworkManager;
import com.eastblue.cisuba.Model.TicketModel;
import com.eastblue.cisuba.Model.TicketModel;
import com.eastblue.cisuba.R;

import java.util.ArrayList;

/**
 * Created by PJC on 2017-02-17.
 * This adapter is using in HomeFragment TOP RANK
 */

public class TicketAdapter extends BaseAdapter {

    private ArrayList<TicketModel> tList;
    private Context mContext;

    public TicketAdapter(Context context) {
        mContext = context;
        tList = new ArrayList<>();
    }

    public void setArray(ArrayList<TicketModel> array) {
        tList = array;
    }
    public void clearList() {
        tList.clear();
    }

    public void addItem(TicketModel item) {
        tList.add(item);
    }
    @Override
    public int getCount() {
        return tList.size();
    }

    @Override
    public Object getItem(int position) {
        return tList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewBinder viewBinder = null;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ticket, parent, false);
            viewBinder = new ViewBinder(convertView);
            convertView.setTag(viewBinder);
        } else {
            viewBinder = (ViewBinder) convertView.getTag();
        }

        viewBinder.bindObject(tList.get(position));

        return convertView;
    }

    public class ViewBinder {
        ImageView thumnail;
        TextView tvName;
        TextView tvDate;
        TextView tvType;
        TextView tvNum;

        public ViewBinder(View v) {
            thumnail = (ImageView) v.findViewById(R.id.thumnail_img);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvDate = (TextView) v.findViewById(R.id.tv_date);
            tvType = (TextView) v.findViewById(R.id.tv_type);
            tvNum = (TextView) v.findViewById(R.id.tv_num);
        }

        public void bindObject(TicketModel item) {
            //f(!item.isFreePartner) {
//                tvName.setText(item.partnerName);
//            //}
//            Glide.with(mContext).load(NetworkManager.SERVER_URL + item.mainThumbnail).centerCrop().into(imvImage);
        }
    }
}
