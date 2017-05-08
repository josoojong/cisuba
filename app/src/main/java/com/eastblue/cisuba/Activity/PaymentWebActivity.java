package com.eastblue.cisuba.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.eastblue.cisuba.Manager.NetworkManager;
import com.eastblue.cisuba.R;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class PaymentWebActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings mWebSettings;
    String p_id, count, i_type, p_type, email, pay_method;

    String url = NetworkManager.SERVER_URL+"/payments/iamport";
    //String url = "http://www.naver.com";

    private WebViewClientClass niceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_web);

        niceClient = new WebViewClientClass();

        Intent intent = getIntent();
        p_id = intent.getStringExtra("p_id");
        count = intent.getStringExtra("count");
        p_type = intent.getStringExtra("p_type");
        i_type = intent.getStringExtra("i_type");
        email = intent.getStringExtra("email");
        pay_method = intent.getStringExtra("pay_method");
        String postData = "";
        try {
            postData = "p_id=" + URLEncoder.encode(p_id, "UTF-8") + "&count=" + URLEncoder.encode(count, "UTF-8") + "&p_type=" + URLEncoder.encode(p_type, "UTF-8")
                    +"&i_type="+URLEncoder.encode(i_type, "UTF-8")+ "&email=" + URLEncoder.encode(email, "UTF-8") + "&pay_method=" + URLEncoder.encode(pay_method, "UTF-8");
        } catch (Exception e) {

        }

        mWebView = (WebView)findViewById(R.id.pay_webview);
        mWebView.setWebViewClient(niceClient);
        //mWebView.getSettings().setJavaScriptEnabled(true);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        //mWebView.loadUrl("http://172.30.1.44:8081/mobile/iamport");
        //mWebView.setWebViewClient(new WebViewClientClass());

        mWebView.postUrl(url, postData.getBytes());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class WebViewClientClass extends WebViewClient{
        /*@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }*/

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("javascript:")) {
                Intent intent = null;

                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //IntentURI처리
                    Uri uri = Uri.parse(intent.getDataString());

                    startActivity(new Intent(Intent.ACTION_VIEW, uri)); //해당되는 Activity 실행
                    return true;
                } catch (URISyntaxException ex) {
                    return false;
                } catch (ActivityNotFoundException e) {
                    if ( intent == null )   return false;

                    //if ( handleNotFoundPaymentScheme(intent.getScheme()) )  return true; //설치되지 않은 앱에 대해 사전 처리(Google Play이동 등 필요한 처리)

                    String packageName = intent.getPackage();
                    if (packageName != null) { //packageName이 있는 경우에는 Google Play에서 검색을 기본
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
                        return true;
                    }

                    return false;
                }
            }

            return false;
        }
    }

}