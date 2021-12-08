package es.aqs.di.ad.finalproject.model.entity;

import androidx.room.Embedded;

public class Sneaker_SneakerBrand {

    @Embedded
    public Sneaker sneaker;

    @Embedded(prefix = "SneakerBrand_")
    public SneakerBrand sneakerBrand;
}
