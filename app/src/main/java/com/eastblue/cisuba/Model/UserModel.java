package com.eastblue.cisuba.Model;

import android.support.annotation.StringRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jo on 2017-04-24.
 */

public class UserModel {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("u_id")
    @Expose
    public String u_id;
    @SerializedName("u_name")
    @Expose
    public String u_name;
    @SerializedName("u_last_login")
    @Expose
    public String u_last_login;
    @SerializedName("u_date_join")
    @Expose
    public String u_date_join;
    @SerializedName("u_phone")
    @Expose
    public String u_phone;

}
