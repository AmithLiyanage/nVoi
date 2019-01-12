package com.nvoi.nvoi_new.Fragments;

import com.nvoi.nvoi_new.Notification.MyResponse;
import com.nvoi.nvoi_new.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAcfI1BBs:APA91bHyLsbPmplZVLEcAzDnNjIA1SvxdaS6G5SxRo62rGjPYpcsWxhWs_wkvw7w33g3QTTR6GTlYYKzyJn_seOa2gA7SqJ4R_MrbmWqFPtDbddrugbvtK0VptUpLBeAx3R73HBfSo6C"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
