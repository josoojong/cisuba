package com.eastblue.cisuba.Network;

import com.eastblue.cisuba.Model.CodeModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * Created by Jo on 2017. 4. 24..
 */

public interface Payment {
    @FormUrlEncoded
    @POST("/payments/iamport")
    void requestPay(
            @Field("p_id") String p_id,
            @Field("count") String count,
            @Field("p_type") String p_type,
            @Field("i_type") String i_type,
            @Field("email") String email,
            @Field("pay_method") String pay_method,
            Callback<CodeModel> callback

    );
}
