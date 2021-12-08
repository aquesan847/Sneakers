package es.aqs.di.ad.finalproject.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;

@Database(entities = {Sneaker.class, SneakerBrand.class}, version = 1, exportSchema = false)

public abstract class SneakerDatabase extends RoomDatabase {

    public abstract SneakerDao getDao();

    private static volatile SneakerDatabase INSTANCE;

    public static SneakerDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SneakerDatabase.class, "Sneaker").build();
        }
        return INSTANCE;
    }
}
