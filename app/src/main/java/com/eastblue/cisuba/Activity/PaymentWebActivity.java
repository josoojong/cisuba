package com.eastblue.cisuba.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.eastblue.cisuba.R;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class PaymentWebActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings mWebSettings;
    String p_id, count, type, email, pay_method;

    String url = "http://172.30.1.44:8081/payments/iamport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_web);

        Intent intent = getIntent();
        p_id = intent.getStringExtra("p_id");
        count = intent.getStringExtra("count");
        type = intent.getStringExtra("type");
        email = intent.getStringExtra("email");
        pay_method = intent.getStringExtra("pay_method");
        String postData = "";
        try {
            postData = "p_id=" + URLEncoder.encode(p_id, "UTF-8") + "&count=" + URLEncoder.encode(count, "UTF-8") + "&type=" + URLEncoder.encode(type, "UTF-8")
                    + "&email=" + URLEncoder.encode(email, "UTF-8") + "&pay_method=" + URLEncoder.encode(pay_method, "UTF-8");
        } catch (Exception e) {

        }

        mWebView = (WebView)findViewById(R.id.pay_webview);
        //mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebSettings = mWebView.getSettings();
        //mWebSettings.setJavaScriptEnabled(true);

        //mWebView.loadUrl("http://172.30.1.44:8081/mobile/iamport");
        //mWebView.setWebViewClient(new WebViewClientClass());

        mWebView.postUrl(url, postData.getBytes());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class WebViewClientClass extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}