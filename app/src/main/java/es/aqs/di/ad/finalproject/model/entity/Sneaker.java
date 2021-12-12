package es.aqs.di.ad.finalproject.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "Sneaker",
        indices = {@Index(value = "name", unique = true)},
        foreignKeys = {@ForeignKey(entity = SneakerBrand.class, parentColumns = "id", childColumns = "idSneakerBrand", onDelete = ForeignKey.CASCADE)})

public class Sneaker {

    public Sneaker() {
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "size")
    public String size;

    @NonNull
    @ColumnInfo(name = "buyDate")
    public String buyDate;

    @NonNull
    @ColumnInfo(name = "imageUrl")
    public String imageUrl;

    @NonNull
    @ColumnInfo(name = "idSneakerBrand")
    public long idSneakerBrand;

    public boolean isValid() {
        return !(name.isEmpty() || idSneakerBrand <= 0 ||
                size.isEmpty() || buyDate.isEmpty() || !(Integer.parseInt(size) >=35 && Integer.parseInt(size) <= 50) ||
                !buyDate.contains("/") || imageUrl.isEmpty());
    }
}
