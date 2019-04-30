package example.android.ubsmanaus.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cidade implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("capital")
    public String capital;
    @SerializedName("region")
    public String region;
    @SerializedName("population")
    public String population;
    @SerializedName("latlng")
    public String latlng;
    @SerializedName("area")
    public String area;
    @SerializedName("languages")
    public String languages;
    @SerializedName("relevance")
    public String relevance;



    public Cidade(String name, String capital, String region, String population, String area, String languages, String relevance, String latlng) {

        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.latlng = latlng;
        this.area = area;
        this.languages = languages;
        this.relevance = relevance;

    }

    @Override
    public String toString() {
        return "Cidade{" +
                " name='" + name + '\'' +
                " região='" + region + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() { return region;   }

    public String getPopulation() {
        return population;
    }

    public String getArea() {
        return area;
    }

    public String getLanguages() {
        return languages;
    }

    public String getRelevance() {
        return relevance;
    }

    public String getLatlng() {
        return latlng;
    }

}