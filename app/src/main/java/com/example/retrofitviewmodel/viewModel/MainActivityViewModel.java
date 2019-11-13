package com.example.retrofitviewmodel.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitviewmodel.repository.PhotoRespository;
import com.example.retrofitviewmodel.model.RetroPhoto;
import com.example.retrofitviewmodel.service.GetDataService;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<RetroPhoto>> photo;
    private PhotoRespository photoRespository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    private GetDataService service;

    public void init() {
        if(photo != null)
            return;
        photoRespository = PhotoRespository.getInstance();
        photo = photoRespository.getPhoto();

    }

    public LiveData<List<RetroPhoto>> getPhotoRepsitory(){
        return photo;
    }
}
