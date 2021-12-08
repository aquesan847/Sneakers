package es.aqs.di.ad.finalproject.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.model.entity.Sneaker_SneakerBrand;

import java.util.List;

@Dao
public interface SneakerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertSneaker(Sneaker... sneakers);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertSneakerBrand(SneakerBrand... sneakerBrands);

    @Query("update sneaker set name=:name, size=:size, buyDate=:buyDate, imageUrl=:imageUrl, idSneakerBrand=:idSneakerBrand where name=:nameSneaker")
    void updateSneaker(String name, String size, String buyDate, String imageUrl, long idSneakerBrand, String nameSneaker);

    @Update
    void updateSneakerBrand(SneakerBrand... sneakerBrands);

    @Delete
    void deleteSneakerBrand(SneakerBrand... sneakerBrands);

    @Query("delete from Sneaker where name = :sneakerName")
    void deleteSneaker(String sneakerName);

    @Query("delete from SneakerBrand")
    int deleteAllSneakersBrand();

    @Query("delete from Sneaker")
    int deleteAllSneaker();

    @Query("select * from Sneaker order by name asc")
    LiveData<List<Sneaker>> getSneakers();

    @Query("select sk.*, skb.id, skb.name SneakerBrand_name from Sneaker sk join SneakerBrand skb on sk.idSneakerBrand = skb.id order by name asc")
    LiveData<List<Sneaker_SneakerBrand>> getAllSneakers();

    @Query("select * from SneakerBrand order by name asc")
    LiveData<List<SneakerBrand>> getSneakerBrands();

    @Query("select * from Sneaker where id = :id")
    LiveData<Sneaker> getSneaker(long id);

    @Query("select * from Sneaker where id = :name")
    LiveData<Sneaker> getSneakerByName(String name);

    @Query("select * from SneakerBrand where id = :id")
    LiveData<SneakerBrand> getSneakerBrand(long id);

    @Query("select id from SneakerBrand where name = :name")
    long getIdSneakerBrand(String name);

    @Query("select * from SneakerBrand where name = :name")
    SneakerBrand getSneakerBrand(String name);

}
