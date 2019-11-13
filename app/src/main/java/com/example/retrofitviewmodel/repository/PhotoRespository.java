package com.example.retrofitviewmodel.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitviewmodel.model.RetroPhoto;
import com.example.retrofitviewmodel.network.RetrofitClientInstance;
import com.example.retrofitviewmodel.service.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRespository {

    private static PhotoRespository photoRepository;

    public static PhotoRespository getInstance(){
        if (photoRepository == null){
            photoRepository = new PhotoRespository();
        }
        return photoRepository;
    }

    private GetDataService getDataService;

    public PhotoRespository(){
        getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }

    public MutableLiveData<List<RetroPhoto>> getPhoto(){
        final MutableLiveData<List<RetroPhoto>> photoData = new MutableLiveData<>();
        getDataService.getAllPhotos().enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                if(response.isSuccessful()){
                    photoData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                photoData.setValue(null);
            }
        });

        return photoData;
    }
}
