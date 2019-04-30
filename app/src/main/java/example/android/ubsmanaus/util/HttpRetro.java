package example.android.ubsmanaus.util;

import java.util.List;

import example.android.ubsmanaus.Model.Cidade;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class HttpRetro {
    private static final String BASE_URL = "https://restcountries.eu/";

    //Inicializa Retrofit
    public static UbsInterface getCidadeClient() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return restAdapter.create(UbsInterface.class);
    }

    // Interface com métodos de requisicao
    public interface UbsInterface {
        @GET("rest/v1/all.")
        Call<List<Cidade>> getCidade();
    }
}