package com.eastblue.cisuba.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.eastblue.cisuba.Adapter.NearAdapter;
import com.eastblue.cisuba.Adapter.TicketAdapter;
import com.eastblue.cisuba.Model.ProductModel;
import com.eastblue.cisuba.Model.TicketModel;
import com.eastblue.cisuba.Network.Product;
import com.eastblue.cisuba.R;
import com.eastblue.cisuba.Util.HttpUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Sung on 2017. 4. 14..
 */

public class TicketActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.unused_lv)
    ListView unused_lv;
    @BindView(R.id.used_lv)
    ListView used_lv;

    TicketAdapter unusedTicketAdapter;
    TicketAdapter usedTicketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    void init() {
        unusedTicketAdapter = new TicketAdapter(this);
        unused_lv.setAdapter(unusedTicketAdapter);

        usedTicketAdapter = new TicketAdapter(this);
        used_lv.setAdapter(usedTicketAdapter);


        for (int i = 0; i < 5; i++) {
            TicketModel t = new TicketModel();
            unusedTicketAdapter.addItem(t);
            usedTicketAdapter.addItem(t);
        }
        setDynamicHeight(unused_lv);
        setDynamicHeight(used_lv);

        unusedTicketAdapter.notifyDataSetChanged();
        usedTicketAdapter.notifyDataSetChanged();

        unused_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TicketModel ticketModel = (TicketModel) unusedTicketAdapter.getItem(position);

            }
        });
        used_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TicketModel ticketModel = (TicketModel) usedTicketAdapter.getItem(position);

            }
        });
    }

    void searchTicket(String name) {
        HttpUtil.api(Product.class).getTicket(name, new Callback<List<TicketModel>>() {
            @Override
            public void success(List<TicketModel> ticketModels, Response response) {

                Log.d("size", ticketModels.size() + "");

                unusedTicketAdapter.setArray((ArrayList<TicketModel>) ticketModels);

                Log.d("unusedTicketAdapter", "called");
                unusedTicketAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }

    public static void setDynamicHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }


}
