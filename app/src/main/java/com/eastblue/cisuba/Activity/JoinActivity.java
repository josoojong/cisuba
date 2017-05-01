package com.eastblue.cisuba.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.eastblue.cisuba.Model.CodeModel;
import com.eastblue.cisuba.Network.User;
import com.eastblue.cisuba.R;
import com.eastblue.cisuba.Util.HttpUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class JoinActivity extends AppCompatActivity {
    EditText et_email;
    EditText et_password, et_passwordcheck;
    EditText et_name;

    CheckBox cb_therms,cb_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);

        et_email = (EditText)findViewById(R.id.input_join_email);
        et_password = (EditText)findViewById(R.id.input_join_pass);
        et_passwordcheck = (EditText)findViewById(R.id.input_join_pass_config);
        et_name = (EditText)findViewById(R.id.input_join_name);

        cb_therms = (CheckBox)findViewById(R.id.cb_Therms);
        cb_privacy = (CheckBox)findViewById(R.id.cb_Privacy);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.join_close)
    void joinClose() {
        finish();
    }

    @OnClick(R.id.email_join)
    void email_join() {
        System.out.println("join test "+et_email.getText().toString());
        if(et_email.getText().toString().length() == 0 || et_password.getText().toString().length() == 0
                || et_passwordcheck.getText().toString().length() == 0 || et_name.getText().toString().length() == 0) {
            Toast.makeText(this,"입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            if (!et_password.getText().toString().equals(et_passwordcheck.getText().toString())) {
                Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
            } else {
                if(cb_therms.isChecked()) {
                    if(cb_privacy.isChecked()) {
                        HttpUtil.api(User.class).requestJoin(
                                et_email.getText().toString(),
                                et_password.getText().toString(),
                                et_name.getText().toString(),
                                "1111",
                                new Callback<CodeModel>() {
                                    @Override
                                    public void success(CodeModel codeModel, Response response) {
                                        if (codeModel.code.equals("1")) {
                                            Toast.makeText(JoinActivity.this, "Join success", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            System.out.println("login_test fail code = " + codeModel.code);
                                            System.out.println("login_test fail message = " + codeModel.message);
                                        }
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        System.out.println("join_test failure");
                                        System.out.println(error);
                                    }
                                }
                        );
                    } else {
                        Toast.makeText(this,"개인정보 수집 및 이용을 동의하세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,"이용약관을 동의하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @OnClick(R.id.tv_join_privacy)
    void open_privacy() {
        startActivity(new Intent(this, PrivacyActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @OnClick(R.id.tv_join_terms)
    void open_terms() {
        startActivity(new Intent(this, TermsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


}