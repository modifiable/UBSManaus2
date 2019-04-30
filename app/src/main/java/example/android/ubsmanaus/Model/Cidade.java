package example.android.ubsmanaus.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cidade implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("topLevelDomain")
    public String topLevelDomain;
    @SerializedName("alpha2Code")
    public String alpha2Code;
    @SerializedName("alpha3Code")
    public String alpha3Code;
    @SerializedName("callingCodes")
    public String callingCodes;
    @SerializedName("capital")
    public String capital;
    @SerializedName("altSpellings")
    public String altSpellings;
    @SerializedName("region")
    public String region;
    @SerializedName("subregion")
    public String subregion;
    @SerializedName("population")
    public String population;
    @SerializedName("latlng")
    public String latlng;
    @SerializedName("demonym")
    public String demonym;
    @SerializedName("area")
    public String area;
    @SerializedName("gini")
    public String gini;
    @SerializedName("borders")
    public String borders;
    @SerializedName("timezones")
    public String timezones;
    @SerializedName("nativeName")
    public String nativeName;
    @SerializedName("numericCode")
    public String numericCode;
    @SerializedName("currencies")
    public String currencies;
    @SerializedName("languages")
    public String languages;
    @SerializedName("translations")
    public String translations;
    @SerializedName("it")
    public String it;
    @SerializedName("relevance")
    public String relevance;



    public Cidade(String name, String capital, String region, String population, String area, String languages, String relevance, String topLevelDomain, String alpha2Code, String alpha3Code,
               String callingCodes, String altSpellings, String subregion, String latlng, String demonym,
               String gini, String timezones, String borders, String nativeName, String numericCode, String currencies, String translations, String it) {

        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.numericCode = numericCode;
        this.currencies = currencies;
        this.languages = languages;
        this.translations = translations;
        this.it = it;
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

}