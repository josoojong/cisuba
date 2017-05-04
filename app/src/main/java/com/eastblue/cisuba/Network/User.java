package com.eastblue.cisuba.Network;

import com.eastblue.cisuba.Model.CodeModel;
import com.eastblue.cisuba.Model.UserModel;
import com.squareup.okhttp.Call;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * Created by Jo on 2017. 4. 24..
 */

public interface User {
    @FormUrlEncoded
    @POST("/members")
    void requestLogin(
            @Field("email") String email,
            @Field("password") String password,
            Callback<CodeModel> callback

    );

    @FormUrlEncoded
    @POST("/members/new")
    void requestJoin(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username,
            @Field("phone") String phone,
            Callback<CodeModel> callback
    );
}
