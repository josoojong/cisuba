package com.eastblue.cisuba.Adapter;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eastblue.cisuba.Manager.NetworkManager;
import com.eastblue.cisuba.Model.ProductModel;
import com.eastblue.cisuba.R;

import java.util.ArrayList;


public class HistoryListAdapter extends BaseAdapter {

    private ArrayList<ProductModel> mList;
    private Context mContext;
    private static final int VIEW_COUNT = 2;
    private static final int VIEW_TYPE_FREE = 0;
    private static final int VIEW_TYPE_CHARGE = 1;


    public HistoryListAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public void setArray(ArrayList<ProductModel> array) {
        mList = array;
    }


    public void setItem(int index, ProductModel item) {
        mList.set(index, item);
        notifyDataSetChanged();
    }

    public void addItem(ProductModel item) {
        mList.add(item);
    }

    public void add(int index, ProductModel item) {
        mList.add(index, item);
        notifyDataSetChanged();
    }


    public void removeAll() {
        mList.clear();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    public Object getItem(String title) {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).partnerName.contains(title)) {
                return mList.get(i);
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ChargeViewBinder chargeViewBinder = null;
        ShowHistory showHistory = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.history_list,parent,false);
            showHistory = new ShowHistory(convertView);
            convertView.setTag(showHistory);
            /*if (getItemViewType(position) == VIEW_TYPE_CHARGE) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_near, parent, false);
                chargeViewBinder = new ChargeViewBinder(convertView);
                convertView.setTag(chargeViewBinder);

            }*/

        } else {
            /*if (getItemViewType(position) == VIEW_TYPE_CHARGE) {
                chargeViewBinder = (ChargeViewBinder) convertView.getTag();
            }*/
        }

        showHistory.bindObject();

        /*if (getItemViewType(position) == VIEW_TYPE_CHARGE) {
            chargeViewBinder.bindObject(mList.get(position));
        }*/


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).isFreePartner) {
            return VIEW_TYPE_FREE;
        } else {
            return VIEW_TYPE_CHARGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_COUNT;
    }

    private  class ShowHistory{
        private TextView tvSell;
        private TextView tvUsed;
        private TextView tvPrice;
        private TextView tvPrice2;

        public ShowHistory(View v) {
            tvSell = (TextView)v.findViewById(R.id.sell_date);
            tvUsed = (TextView)v.findViewById(R.id.used_date);
            tvPrice = (TextView)v.findViewById(R.id.history_price);
            tvPrice2 = (TextView)v.findViewById(R.id.history_price2);
        }

        public void bindObject() {
            tvSell.setText("1");
            tvUsed.setText("2");
            tvPrice.setText("3");
            tvPrice2.setText("4");
        }
    }

    private class ChargeViewBinder {
        private ImageView imvImage;
        private TextView tvKm;
        private TextView tvDiscount;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPriceMorning;
        private TextView tvPriceLunch;
        private TextView tvPriceDinner;

        public ChargeViewBinder(View v) {
            imvImage = (ImageView) v.findViewById(R.id.imv_image);
            tvKm = (TextView) v.findViewById(R.id.tv_km);
            tvDiscount = (TextView) v.findViewById(R.id.tv_discount);
            tvName = (TextView) v.findViewById(R.id.tv_name);
            tvAddress = (TextView) v.findViewById(R.id.tv_address);
            tvPriceMorning = (TextView) v.findViewById(R.id.tv_price_morning);
            tvPriceLunch = (TextView) v.findViewById(R.id.tv_price_lunch);
            tvPriceDinner = (TextView) v.findViewById(R.id.tv_price_dinner);
        }

        public void bindObject(ProductModel item) {
            tvName.setText("[" + item.highlightAddress + "] " + item.partnerName);
            tvAddress.setText(item.shortAddress);
            tvDiscount.setText(item.discount + " 원 할인");
            tvPriceMorning.setText("조조 " + Integer.parseInt(item.morningPrice) + "원");
            tvPriceLunch.setText("평일 " + Integer.parseInt(item.lunchPrice) + "원");
            tvPriceDinner.setText("야간 " + Integer.parseInt(item.dinnerPrice) + "원");
        }
    }
}
