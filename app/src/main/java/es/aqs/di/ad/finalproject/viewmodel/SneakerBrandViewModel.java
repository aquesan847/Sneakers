package es.aqs.di.ad.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.model.repository.SneakerRepository;

import java.util.List;

public class SneakerBrandViewModel extends AndroidViewModel {

    private SneakerRepository repository;

    public SneakerBrandViewModel(@NonNull Application application) {
        super(application);
        repository = new SneakerRepository(application);
    }

    public void insertSneakerBrand(SneakerBrand... sneakerBrands) {
        repository.insertSneakerBrand(sneakerBrands);
    }

    public void updateSneakerBrand(SneakerBrand... sneakerBrands) {
        repository.updateSneakerBrand(sneakerBrands);
    }

    public void deleteSneakerBrand(SneakerBrand... sneakerBrands) {
        repository.deleteSneakerBrand(sneakerBrands);
    }

    public LiveData<List<SneakerBrand>> getSneakerBrands() {
        return repository.getSneakerBrands();
    }

    public LiveData<SneakerBrand> getSneakerBrand(long id) {
        return repository.getSneakerBrand(id);
    }
}
