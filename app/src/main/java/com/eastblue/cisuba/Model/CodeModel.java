package com.eastblue.cisuba.Model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PJC on 2017-02-27.
 */

public class CodeModel {
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("user:")
    @Expose
    public UserModel user;

    @SerializedName("message")
    public String message;
}