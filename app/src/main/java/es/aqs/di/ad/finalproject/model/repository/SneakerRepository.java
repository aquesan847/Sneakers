package es.aqs.di.ad.finalproject.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;

import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.model.entity.Sneaker_SneakerBrand;
import es.aqs.di.ad.finalproject.model.room.SneakerDao;
import es.aqs.di.ad.finalproject.model.room.SneakerDatabase;

import java.util.List;

public class SneakerRepository {

    private static final String INIT = "init";

    private SneakerDao dao;

    LiveData<List<Sneaker_SneakerBrand>> allSneakers;
    LiveData<List<Sneaker>> liveSneakers;
    LiveData<List<SneakerBrand>> liveSneakerBrands;

    LiveData<Sneaker> liveSneaker;
    LiveData<SneakerBrand> liveSneakerBrand;

    MutableLiveData<Long> liveInsertResult;
    MutableLiveData<List<Long>> liveInsertResults;

    SharedPreferences preferences;

    public SneakerRepository(Context context) {
        SneakerDatabase db = SneakerDatabase.getDatabase(context);
        dao = db.getDao();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        liveInsertResult = new MutableLiveData<>();
        liveInsertResults = new MutableLiveData<>();

        if(!getInit()) {
            sneakerBrandsPreload();
            setInit();
        }
    }

    public void insertSneaker(Sneaker sneaker, SneakerBrand sneakerBrand) {
        Runnable r = () -> {
            sneaker.idSneakerBrand = insertSneakerBrandGetId(sneakerBrand);
            if (sneaker.idSneakerBrand > 0) {
                dao.insertSneaker(sneaker);
            }
        };
        new Thread(r).start();
    }

    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }

    private long insertSneakerBrandGetId(SneakerBrand sneakerBrand) {
        List<Long> ids = dao.insertSneakerBrand(sneakerBrand);
        if (ids.get(0) < 1) {
            return dao.getIdSneakerBrand(sneakerBrand.name);
        } else {
            return ids.get(0);
        }
    }

    public void insertSneaker(Sneaker... sneakers) {
        Runnable r = () -> {
            List<Long> resultList = dao.insertSneaker(sneakers);
            liveInsertResult.postValue(resultList.get(0));
            liveInsertResults.postValue(resultList);
        };
        new Thread(r).start();
    }

    public void insertSneakerBrand(SneakerBrand... sneakerBrands) {
        Runnable r = () -> {
            dao.insertSneakerBrand(sneakerBrands);
        };
        new Thread(r).start();
    }

    public void updateSneaker(Sneaker sneaker, String nameSneaker) {
        Runnable r = () -> {
            dao.updateSneaker(sneaker.name,sneaker.size,sneaker.buyDate,sneaker.imageUrl,sneaker.idSneakerBrand, nameSneaker);

        };
        new Thread(r).start();
    }

    public void updateSneakerBrand (SneakerBrand... sneakerBrands) {
        Runnable r = () -> {
            dao.updateSneakerBrand(sneakerBrands);
        };
        new Thread(r).start();
    }

    public void deleteSneaker (Sneaker sneaker) {
        Runnable r = () -> {
            dao.deleteSneaker(sneaker.name);
        };
        new Thread(r).start();
    }

    public void deleteSneakerBrand (SneakerBrand... sneakerBrand) {
        Runnable r = () -> {
            dao.deleteSneakerBrand(sneakerBrand);
        };
        new Thread(r).start();
    }

    public LiveData<List<Sneaker>> getSneakers() {
        if(liveSneakers == null) {
            liveSneakers = dao.getSneakers();
        }
        return liveSneakers;
    }

    public LiveData<List<SneakerBrand>> getSneakerBrands() {
        if(liveSneakerBrands == null) {
            liveSneakerBrands = dao.getSneakerBrands();
        }
        return liveSneakerBrands;
    }

    public LiveData<Sneaker> getSneaker(long id) {
        if(liveSneaker == null) {
            liveSneaker = dao.getSneaker(id);
        }
        return liveSneaker;
    }

    public LiveData<Sneaker> getSneakerByName(String name) {
        if(liveSneaker == null) {
            liveSneaker = dao.getSneakerByName(name);
        }
        return liveSneaker;
    }

    public LiveData<SneakerBrand> getSneakerBrand(long id) {
        if(liveSneakerBrand == null) {
            liveSneakerBrand = dao.getSneakerBrand(id);
        }
        return liveSneakerBrand;
    }

    public LiveData<List<Sneaker_SneakerBrand>> getAllSneakers() {
        if(allSneakers == null) {
            allSneakers = dao.getAllSneakers();
        }
        return allSneakers;
    }

    public void sneakerBrandsPreload() {
        String[] sneakerBrandNames = new String[] {"Adidas", "Asics", "Converse", "Jordan", "New Balance", "Nike", "Puma", "Reebok",  "Vans", "Yeezy"};

        SneakerBrand[] sneakerBrands = new SneakerBrand[sneakerBrandNames.length];
        SneakerBrand sneakerBrand;
        int cont = 0;
        for (String s : sneakerBrandNames) {
            sneakerBrand = new SneakerBrand();
            sneakerBrand.name = s;
            sneakerBrands[cont] = sneakerBrand;
            cont++;
        }
        insertSneakerBrand(sneakerBrands);
    }

    public void setInit() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(INIT, true);
        editor.commit();
    }

    public boolean getInit() {
        return preferences.getBoolean(INIT, false);
    }
}
