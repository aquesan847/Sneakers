package es.aqs.di.ad.finalproject.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "SneakerBrand",
        indices = {@Index(value = "name", unique = true)})

public class SneakerBrand {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @Override
    public String toString() {
        return name;
    }
}
