package com.eastblue.cisuba.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by PJC on 2017-02-28.
 */

public class TicketModel {

    @SerializedName("t_id")
    public long t_id;

    @SerializedName("u_id")
    public long u_id;

    @SerializedName("p_id")
    public long p_id;

    @SerializedName("type")
    public int type;

    @SerializedName("amount")
    public int amount;

    @SerializedName("date")
    public Date date;

    @SerializedName("status")
    public int status;

}