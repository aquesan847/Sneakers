package es.aqs.di.ad.finalproject.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import es.aqs.di.ad.finalproject.model.repository.SneakerRepository;

public class CommonViewModel extends ViewModel {

    private Context context;
    private SneakerRepository repository;

    public CommonViewModel() {
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
