package com.eastblue.cisuba.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eastblue.cisuba.Adapter.SharedPreferenceAdapter;
import com.eastblue.cisuba.Manager.NetworkManager;
import com.eastblue.cisuba.Model.CodeModel;
import com.eastblue.cisuba.Network.Payment;
import com.eastblue.cisuba.Network.Product;
import com.eastblue.cisuba.R;
import com.eastblue.cisuba.Util.HttpUtil;
import com.kakao.auth.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PaymentActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    TextView pay_name, pay_phone;
    Button btn_morning, btn_lunch, btn_dinner;
    Button btn_card;
    Button btn_plus,btn_minus;

    TextView tv_name,tv_address;
    TextView tv_count;

    ImageView mainImage;

    @BindView(R.id.tv_pay_price)
    TextView tvPrice;

    private String pay_way = "";
    private String type = "";
    private String pay_email = "";

    private int amount = 1;
    private int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        ButterKnife.bind(this);

        RadioGroup rg = (RadioGroup)findViewById(R.id.pay_radiogroup);
        rg.setOnCheckedChangeListener(this);

        pay_name = (TextView)findViewById(R.id.tv_pay_name);
        pay_phone = (TextView)findViewById(R.id.tv_pay_phone);

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_count = (TextView)findViewById(R.id.tv_pay_count);

        mainImage = (ImageView)findViewById(R.id.pay_product_image);

        init();

        btn_morning = (Button) findViewById(R.id.btn_morning);
        btn_lunch = (Button) findViewById(R.id.btn_lunch);
        btn_dinner = (Button) findViewById(R.id.btn_dinner);

        btn_morning.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn_morning.setBackgroundResource(R.drawable.background_payment_select);
                        btn_morning.setTextColor(Color.parseColor("#1a9eb8"));
                        btn_lunch.setBackgroundResource(R.drawable.background_payment);
                        btn_lunch.setTextColor(Color.BLACK);
                        btn_dinner.setBackgroundResource(R.drawable.background_payment);
                        btn_dinner.setTextColor(Color.BLACK);
                        type = "0";
                        break;
                    }
                }
                return false;
            }
        });

        btn_lunch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn_morning.setBackgroundResource(R.drawable.background_payment);
                        btn_morning.setTextColor(Color.BLACK);
                        btn_lunch.setBackgroundResource(R.drawable.background_payment_select);
                        btn_lunch.setTextColor(Color.parseColor("#1a9eb8"));
                        btn_dinner.setBackgroundResource(R.drawable.background_payment);
                        btn_dinner.setTextColor(Color.BLACK);
                        type = "1";
                        break;
                    }

                }
                return false;
            }
        });

        btn_dinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn_morning.setBackgroundResource(R.drawable.background_payment);
                        btn_morning.setTextColor(Color.BLACK);
                        btn_lunch.setBackgroundResource(R.drawable.background_payment);
                        btn_lunch.setTextColor(Color.BLACK);
                        btn_dinner.setBackgroundResource(R.drawable.background_payment_select);
                        btn_dinner.setTextColor(Color.parseColor("#1a9eb8"));
                        type = "2";
                        break;
                    }
                }
                return false;
            }
        });

        btn_card = (Button)findViewById(R.id.btn_card);
        btn_card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn_card.setBackgroundResource(R.drawable.background_payment_select);
                        btn_card.setTextColor(Color.parseColor("#1a9eb8"));
                        break;
                    }
                }
                return false;
            }
        });

        btn_plus = (Button)findViewById(R.id.count_plus);
        btn_plus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn_plus.setBackgroundResource(R.drawable.background_payment_select);
                        btn_plus.setTextColor(Color.parseColor("#1a9eb8"));
                        break;
                    }
                    case  MotionEvent.ACTION_UP: {
                        btn_plus.setBackgroundResource(R.drawable.background_payment);
                        btn_plus.setTextColor(Color.BLACK);
                        break;
                    }
                }
                return false;
            }
        });

        btn_minus = (Button)findViewById(R.id.count_minus);
        btn_minus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        btn_minus.setBackgroundResource(R.drawable.background_payment_select);
                        btn_minus.setTextColor(Color.parseColor("#1a9eb8"));
                        break;
                    }
                    case  MotionEvent.ACTION_UP: {
                        btn_minus.setBackgroundResource(R.drawable.background_payment);
                        btn_minus.setTextColor(Color.BLACK);
                        break;
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void init() {
        tv_count.setText(amount+"개");
        tvPrice.setText(price*amount+"원");
        //naver
        if (LoginActivity.mOAuthLoginModule != null) {
            if (LoginActivity.mOAuthLoginModule.getState(this).toString().equals("OK")) {
                pay_name.setText(LoginActivity.name);
            }
        }
        //kakao
        if(!Session.getCurrentSession().isClosed()) {
            pay_name.setText(SharedPreferenceAdapter.getUserName(PaymentActivity.this));
        }
        //eamil
        if(SharedPreferenceAdapter.getUserLogin(PaymentActivity.this).length() != 0) {
            pay_name.setText(SharedPreferenceAdapter.getUserName(PaymentActivity.this));
            pay_phone.setText(SharedPreferenceAdapter.getUserPhone(PaymentActivity.this));
            pay_email = SharedPreferenceAdapter.getUserEmail(PaymentActivity.this);
        }

        tv_name.setText("[" + ProductDetailActivity.highlightAddress + "] " + ProductDetailActivity.partner_Name);
        tv_address.setText(ProductDetailActivity.detailAddress);

        Glide.with(this).load(NetworkManager.SERVER_URL + ProductDetailActivity.Thumbnail).centerCrop().into(mainImage);

        System.out.println("payment test id = "+ProductDetailActivity.pk_id);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_mobile:
                Toast.makeText(this,"mobile",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick(R.id.btn_morning)
    void setPriceMorning() {
        price = Integer.parseInt(ProductDetailActivity.priceMorning);
        tvPrice.setText(price*amount+"원");
    }

    @OnClick(R.id.btn_lunch)
    void setPriceLunch() {
        price = Integer.parseInt(ProductDetailActivity.priceLunch);
        tvPrice.setText(price*amount+"원");
    }

    @OnClick(R.id.btn_dinner)
    void setPriceDinner() {
        price = Integer.parseInt(ProductDetailActivity.priceDinner);
        tvPrice.setText(price*amount+"원");
    }

    @OnClick(R.id.btn_card)
    void checkCard() {
        pay_way = "card";
    }

    @OnClick(R.id.btn_payment_pay)
    void toPay() {
        if(type.length() == 0) {
            Toast.makeText(this,"이용 시간을 선택하세요.",Toast.LENGTH_SHORT).show();
        } else if(pay_way.length() == 0) {
            Toast.makeText(this,"결제 수단을 선택하세요.",Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(PaymentActivity.this, PaymentWebActivity.class);
            intent.putExtra("p_id",ProductDetailActivity.pk_id);
            intent.putExtra("count",Integer.toString(amount));
            intent.putExtra("p_type",type);
            intent.putExtra("i_type",SharedPreferenceAdapter.getUserType(PaymentActivity.this));
            intent.putExtra("email",SharedPreferenceAdapter.getUserEmail(PaymentActivity.this));
            intent.putExtra("pay_method",pay_way);
            startActivity(intent);
            finish();

            /*HttpUtil.api(Payment.class).requestPay(
                    ProductDetailActivity.pk_id,
                    Integer.toString(amount),
                    type,
                    SharedPreferenceAdapter.getUserEmail(PaymentActivity.this),
                    pay_way,
                    new Callback<CodeModel>() {
                        @Override
                        public void success(CodeModel codeModel, Response response) {
                            if (codeModel.code.equals("1")) {
                                Toast.makeText(PaymentActivity.this, "success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PaymentActivity.this, PaymentWebActivity.class));
                                finish();
                            } else {
                                System.out.println("fail code = " + codeModel.code);
                                System.out.println("fail message = " + codeModel.message);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            System.out.println(error);
                        }
                    }
            );*/
        }
    }

    @OnClick(R.id.count_plus)
    void countPlus() {
        amount++;
        tv_count.setText(amount+"개");
        tvPrice.setText(price*amount+"원");
    }

    @OnClick(R.id.count_minus)
    void countMinus() {
        if(amount > 1)
            amount --;
        tv_count.setText(amount+"개");
        tvPrice.setText(price*amount+"원");
    }
}