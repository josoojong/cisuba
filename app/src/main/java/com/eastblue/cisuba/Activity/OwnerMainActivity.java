package com.eastblue.cisuba.Activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eastblue.cisuba.Adapter.SharedPreferenceAdapter;
import com.eastblue.cisuba.Adapter.TabPagerAdapter;
import com.eastblue.cisuba.Dialog.MainPopUpDialog;
import com.eastblue.cisuba.Fragment.ProfileFragment;
import com.eastblue.cisuba.Manager.NetworkManager;
import com.eastblue.cisuba.Model.BannerModel;
import com.eastblue.cisuba.Network.Product;
import com.eastblue.cisuba.R;
import com.eastblue.cisuba.Util.HttpUtil;
import com.kakao.auth.Session;
import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.KakaoParameterException;
import com.kakao.util.helper.log.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OwnerMainActivity extends AppCompatActivity {
    TextView tv_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        ButterKnife.bind(this);

        tv_name = (TextView)findViewById(R.id.ow_tv_name);
        tv_name.setText(SharedPreferenceAdapter.getUserName(OwnerMainActivity.this));


    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @OnClick(R.id.ow_btn_logout)
    void logout() {

        // AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        new AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니다?")
                //.setIcon()
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(SharedPreferenceAdapter.getUserLogin(OwnerMainActivity.this).length() != 0) {
                            SharedPreferenceAdapter.clearUserEmail(OwnerMainActivity.this);
                            finish();
                        }

                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();

    }

    @OnClick(R.id.ow_lin_payment_list)
    void toHistoryList() {
        startActivity(new Intent(this, OwnerHistoryActivity.class));
    }



}
