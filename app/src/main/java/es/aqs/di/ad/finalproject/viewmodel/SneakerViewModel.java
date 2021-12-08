package es.aqs.di.ad.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.model.entity.Sneaker_SneakerBrand;
import es.aqs.di.ad.finalproject.model.repository.SneakerRepository;

import java.util.List;

public class SneakerViewModel extends AndroidViewModel {

    private SneakerRepository repository;

    public SneakerViewModel(@NonNull Application application) {
        super(application);
        repository = new SneakerRepository(application);
    }

    public void insertSneaker(Sneaker... sneakers) {
        repository.insertSneaker(sneakers);
    }

    public void updateSneaker(Sneaker sneaker, String nameSneaker) {
        repository.updateSneaker(sneaker, nameSneaker);
    }

    public void deleteSneaker(Sneaker sneaker) {
        repository.deleteSneaker(sneaker);
    }

    public LiveData<List<Sneaker>> getSneakers() {
        return repository.getSneakers();
    }

    public LiveData<Sneaker> getSneaker(long id) {
        return repository.getSneaker(id);
    }

    public LiveData<Sneaker> getSneakerByName(String name) {
        return repository.getSneakerByName(name);
    }

    public void insertSneaker(Sneaker sneaker, SneakerBrand sneakerBrand) {
        repository.insertSneaker(sneaker, sneakerBrand);
    }

    public LiveData<List<Sneaker_SneakerBrand>> getAllSneakers() {
        return repository.getAllSneakers();
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }
}
