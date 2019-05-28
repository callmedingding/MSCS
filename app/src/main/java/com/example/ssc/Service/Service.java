package com.example.ssc.Service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    //    @Headers({"Content-Type:application/json", "Accept:application/json"})
//    @Body GoogleWifiRequest param
    @GET("dblogin.php")
    Call<ResponseBody> getResult(@Query("id") String id,
                                 @Query("key") String key);
}
