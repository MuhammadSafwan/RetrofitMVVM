package com.example.retrofitviewmodel.service;

import java.util.List;

import com.example.retrofitviewmodel.model.RetroPhoto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}
