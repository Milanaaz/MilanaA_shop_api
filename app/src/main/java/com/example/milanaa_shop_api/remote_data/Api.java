package com.example.milanaa_shop_api.remote_data;


import com.example.milanaa_shop_api.models.ModelM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("products")
    Call<List<ModelM>> getStoreProducts();
}
