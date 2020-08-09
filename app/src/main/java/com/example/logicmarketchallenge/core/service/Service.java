package com.example.logicmarketchallenge.core.service;
import com.example.logicmarketchallenge.core.entities.Laptop;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("list")
    Call<List<Laptop>> getProductList();

}
