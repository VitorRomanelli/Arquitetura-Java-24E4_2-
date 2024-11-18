package br.edu.infnet.vitor_hugo_veras_romanelli.model.domain;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "TSpecialEditionFilm")
public class SpecialEditionFilm extends Film {

    private String bonusFeatures;
    private String specialFeature;
    private String editionType;
    private String packagingType;

    public SpecialEditionFilm() {}

    public SpecialEditionFilm(String title, LocalDate releaseDate, String genre, double rating, Director director,
                              String bonusFeatures, String specialFeature, String editionType, String packagingType) {
        super(title, releaseDate, genre, rating, director);
        this.bonusFeatures = bonusFeatures;
        this.specialFeature = specialFeature;
        this.editionType = editionType;
        this.packagingType = packagingType;
    }

    public String getBonusFeatures() {
        return bonusFeatures;
    }

    public void setBonusFeatures(String bonusFeatures) {
        this.bonusFeatures = bonusFeatures;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(String specialFeature) {
        this.specialFeature = specialFeature;
    }

    public String getEditionType() {
        return editionType;
    }

    public void setEditionType(String editionType) {
        this.editionType = editionType;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    @Override
    public String toString() {
        return super.toString() + ", bonusFeatures='" + bonusFeatures + "', specialFeature='" + specialFeature + 
               "', editionType='" + editionType + "', packagingType='" + packagingType + "'";
    }
}