package com.eastblue.cisuba.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.eastblue.cisuba.Adapter.HistoryListAdapter;
import com.eastblue.cisuba.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OwnerHistoryActivity extends AppCompatActivity {
    private HistoryListAdapter mHistoryListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_history_main);
        ButterKnife.bind(this);

        Spinner sp_year = (Spinner)findViewById(R.id.sp_year);


        mHistoryListAdapter = new HistoryListAdapter(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }


}
