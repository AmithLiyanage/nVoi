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
                    "Authorization:key=AAAAGxdGLkk:APA91bHal-AOlElKupZ4_tmaR6sp36vORySxcT2WpUndjSP7tiQgl9bSboEG5aAkYGlsQzKaz430gbOsrUIjs30CPvm-jqnss9aMy5GWasCxK59YbD2vWV-mjBXpysugd68duvznE4Qb"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
