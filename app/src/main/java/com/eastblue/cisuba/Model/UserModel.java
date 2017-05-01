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
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("last_login")
    @Expose
    public String lastLogin;
    @SerializedName("date_join")
    @Expose
    public String dateJoin;
    @SerializedName("phone")
    @Expose
    public String phone;

}
