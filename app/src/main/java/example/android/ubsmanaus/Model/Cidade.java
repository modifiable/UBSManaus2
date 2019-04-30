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




    public Cidade(String name, String capital, String region) {

        this.name = name;
        this.capital = capital;
        this.region = region;

    }

    @Override
    public String toString() {
        return "Cidade ->" +
                " nome: " + name + "; " +
                " região: " + region + "; " +
                " capital: " + capital;
    }


}