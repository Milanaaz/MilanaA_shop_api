package com.example.milanaa_shop_api.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.milanaa_shop_api.models.ModelM;
import com.example.milanaa_shop_api.repositories.JemRepositoty;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private JemRepositoty jemRepositoty;
    private LiveData<List<ModelM>> modelMResponseLiveData;

    public HomeViewModel() {
        jemRepositoty = new JemRepositoty();
        modelMResponseLiveData = jemRepositoty.getDashJeminyList();
    }

    public LiveData<List<ModelM>> getModelMResponseLiveData() {
        return modelMResponseLiveData;
    }
}